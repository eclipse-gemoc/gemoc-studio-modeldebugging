/*******************************************************************************
 * Copyright (c) 2016, 2017 Inria and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Inria - initial API and implementation
 *******************************************************************************/
package org.eclipse.gemoc.executionframework.event.ui.views;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.gemoc.executionframework.event.manager.EventInstance;
import org.eclipse.gemoc.executionframework.event.manager.IEventManager;
import org.eclipse.gemoc.executionframework.event.model.event.Event;
import org.eclipse.xtext.naming.DefaultDeclarativeQualifiedNameProvider;
import org.eclipse.xtext.naming.QualifiedName;

import javafx.application.Platform;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;

public class EventTableView extends TableView<EventInstance> {

	private final EClass eventClass;

	private final EFactory factory;

	private final Resource executedModel;

	private final List<EClass> eventParameterClasses = new ArrayList<>();

	private final Map<EReference, EClass> referenceToParameterClass = new HashMap<>();

	private final Map<EReference, List<EObject>> referenceToMatchingModelElements = new HashMap<>();

	private final ObservableList<EventInstance> events = FXCollections.observableArrayList();

	private final Function<EventInstance, Boolean> canDisplayEventFunction;

	public EventTableView(final EClass eventClass, final Resource executedModel, final IEventManager eventManager) {
		this.eventClass = eventClass;
		this.factory = eventClass.getEPackage().getEFactoryInstance();
		this.executedModel = executedModel;
		extractEventParameters();
		setItems(events);
		setEditable(true);
		
		canDisplayEventFunction = (event) -> {
			return eventManager.canSendEvent(event);
		};

		final List<TableColumn<EventInstance, ?>> columns = new ArrayList<>();
		eventClass.getEAllStructuralFeatures().stream().forEach(f -> {
			if (f instanceof EReference) {
				final String name = f.getName().substring(0, f.getName().indexOf("Provider"));
				final TableColumn<EventInstance, String> col = new TableColumn<EventInstance, String>(name);
				col.setCellValueFactory(new EventInstanceReferencePropertyValueFactory((EReference) f));
				columns.add(col);
			} else {
				final String name = f.getName();
				final TableColumn<EventInstance, String> col = new TableColumn<EventInstance, String>(name);
				col.setEditable(true);
				col.setCellValueFactory(new EventInstanceAttributePropertyValueFactory((EAttribute) f));
				col.setCellFactory(TextFieldTableCell.forTableColumn());
				col.setOnEditCommit(editEvent -> {
					EventInstance event = editEvent.getRowValue();
					event.getParameters().put(f, editEvent.getNewValue());
				});
				columns.add(col);
			}
		});

		getColumns().setAll(columns);

	}

	public void refreshEvents() {
		Platform.runLater(() -> {
			referenceToMatchingModelElements.clear();
			gatherPotentialParameters();

			final List<EventInstance> newEvents = computeAllPossibleEvents().stream().filter(m -> {
				return !events.stream().anyMatch(event -> {
					return m.entrySet().stream().allMatch(entry -> {
						final EStructuralFeature ref = entry.getKey();
						final Object val1 = event.getParameters().get(ref);
						final Object val2 = entry.getValue();
						return val1 == val2;
					});
				});
			}).map(m -> {
				return new EventInstance((Event) factory.create(eventClass), m);
			}).filter(event -> canDisplayEventFunction.apply(event)).collect(Collectors.toList());

			final List<EventInstance> toRemove = events.stream().filter(event -> !canDisplayEventFunction.apply(event))
					.collect(Collectors.toList());
			events.removeAll(toRemove);
			events.addAll(newEvents);
		});
	}

	private void extractEventParameters() {
		eventClass.getEReferences().forEach(f -> {
			final List<EGenericType> genericTypes = ((EClass) f.getEType()).getEGenericSuperTypes();
			final List<EGenericType> typeArguments = genericTypes.get(0).getETypeArguments();
			final EClass correspondingClass = (EClass) typeArguments.get(0).getEClassifier();
			eventParameterClasses.add(correspondingClass);
			referenceToParameterClass.put(f, correspondingClass);
		});
	}

	private void gatherPotentialParameters() {
		final Set<EReference> eventParameters = referenceToParameterClass.keySet();
		executedModel.getAllContents().forEachRemaining(modelElement -> {
			final EClass elementClass = modelElement.eClass();
			eventParameters.stream().filter(r -> {
				final EClass parameterClass = referenceToParameterClass.get(r);
				return elementClass.getClassifierID() == parameterClass.getClassifierID()
						|| elementClass.getEAllSuperTypes().contains(parameterClass);
			})
			.forEach(r -> {
				List<EObject> elements = referenceToMatchingModelElements.get(r);
				if (elements == null) {
					elements = new ArrayList<>();
					referenceToMatchingModelElements.put(r, elements);
				}
				elements.add(modelElement);
			});
		});
	}

	private List<Map<EStructuralFeature, Object>> computeAllPossibleEvents() {
		final List<Map<EStructuralFeature, Object>> result = new ArrayList<>();
		final int nbEvents = referenceToMatchingModelElements.values().stream().map(l -> l.size())
				.reduce((i1, i2) -> i1 * i2).orElse(0);
		final List<Map.Entry<EReference, List<EObject>>> entries = new ArrayList<>(
				referenceToMatchingModelElements.entrySet());
		for (int i = 0; i < nbEvents; i++) {
			int j = 1;
			final Map<EStructuralFeature, Object> parametersAssociation = new HashMap<>();
			for (Map.Entry<EReference, List<EObject>> entry : entries) {
				final List<EObject> modelElements = entry.getValue();
				parametersAssociation.put(entry.getKey(), modelElements.get((i / j) % modelElements.size()));
				j *= modelElements.size();
			}
			result.add(parametersAssociation);
		}
		return result;
	}

	static class EventInstanceReferencePropertyValueFactory
			implements Callback<CellDataFeatures<EventInstance, String>, ObservableValue<String>> {

		private final EReference reference;
		private final DefaultDeclarativeQualifiedNameProvider nameprovider = new DefaultDeclarativeQualifiedNameProvider();
		private final Function<Object, String> stringGetter = (o) -> {
			if (o instanceof EObject) {
				QualifiedName qname = nameprovider.getFullyQualifiedName((EObject) o);
				if (qname != null) {
					return qname.toString();
				}
			}
			final String string = o.toString();
			return string.substring(string.lastIndexOf(".") + 1);
		};

		public EventInstanceReferencePropertyValueFactory(EReference reference) {
			this.reference = reference;
		}

		@Override
		public ObservableValue<String> call(CellDataFeatures<EventInstance, String> p) {
			EventInstance event = p.getValue();
			Object refValue = event.getParameters().get(reference);
			if (refValue != null) {
				String string = stringGetter.apply(refValue);
				ObservableValue<String> result = new ReadOnlyObjectWrapper<String>(string);
				return result;
			}
			return null;
		}
	}

	static class EventInstanceAttributePropertyValueFactory
			implements Callback<CellDataFeatures<EventInstance, String>, ObservableValue<String>> {

		private final EAttribute attribute;

		public EventInstanceAttributePropertyValueFactory(EAttribute attribute) {
			this.attribute = attribute;
		}

		@Override
		public ObservableValue<String> call(CellDataFeatures<EventInstance, String> p) {
			EventInstance event = p.getValue();
			Object refValue = event.getParameters().get(attribute);
			if (refValue != null) {
				ObservableValue<String> result = new ReadOnlyObjectWrapper<String>(refValue.toString());
				return result;
			}
			return null;
		}
	}
}
