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
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.gemoc.executionframework.value.model.value.BooleanAttributeValue;
import org.eclipse.gemoc.executionframework.value.model.value.BooleanObjectAttributeValue;
import org.eclipse.gemoc.executionframework.value.model.value.FloatAttributeValue;
import org.eclipse.gemoc.executionframework.value.model.value.FloatObjectAttributeValue;
import org.eclipse.gemoc.executionframework.value.model.value.IntegerAttributeValue;
import org.eclipse.gemoc.executionframework.value.model.value.IntegerObjectAttributeValue;
import org.eclipse.gemoc.executionframework.value.model.value.SingleReferenceValue;
import org.eclipse.gemoc.executionframework.value.model.value.StringAttributeValue;
import org.eclipse.gemoc.executionframework.value.model.value.Value;
import org.eclipse.gemoc.executionframework.value.model.value.ValuePackage;
import org.eclipse.gemoc.executionframework.event.manager.IEventManager;
import org.eclipse.gemoc.executionframework.event.manager.IEventManagerListener;
import org.eclipse.gemoc.executionframework.event.model.event.EventOccurrence;
import org.eclipse.gemoc.executionframework.ui.views.engine.EngineSelectionDependentViewPart;
import org.eclipse.gemoc.trace.commons.model.trace.Step;
import org.eclipse.gemoc.xdsmlframework.api.core.IExecutionEngine;
import org.eclipse.gemoc.xdsmlframework.api.engine_addon.IEngineAddon;
import org.eclipse.gemoc.executionframework.behavioralinterface.behavioralInterface.BehavioralInterface;
import org.eclipse.gemoc.executionframework.behavioralinterface.behavioralInterface.Event;
import org.eclipse.gemoc.executionframework.behavioralinterface.behavioralInterface.EventParameter;
import org.eclipse.gemoc.executionframework.behavioralinterface.behavioralInterface.EventType;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.xtext.naming.DefaultDeclarativeQualifiedNameProvider;
import org.eclipse.xtext.naming.QualifiedName;

public class EventManagerViewPart extends EngineSelectionDependentViewPart implements IEngineAddon {

	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "org.eclipse.gemoc.executionframework.event.ui.views.EventManager";

	private IEventManager eventManager;

	private final List<BehavioralInterface> behavioralInterfaces = new ArrayList<>();

	private final List<Event> events = new ArrayList<>();

	private final List<BehavioralInterface> selectedBehavioralInterfaces = new ArrayList<>();

	private final Map<EventParameter, EClass> parameterToParameterClass = new HashMap<>();

	private final Map<EClass, List<EObject>> parameterClassToMatchingModelElements = new HashMap<>();

	private Resource executedModel;

	private EPackage ePackage;

	private org.eclipse.swt.widgets.List behavioralInterfaceList;

	private org.eclipse.swt.widgets.List exposedEventOccurrenceList;

	private Group eventOccurrenceConfiguratorGroup;

	private final Map<Event, List<EventOccurrence>> eventToEventOccurrences = new HashMap<>();

	private IExecutionEngine<?> executionEngine;

	private final IEventManagerListener eventListener = new IEventManagerListener() {
		@Override
		public Set<BehavioralInterface> getBehavioralInterfaces() {
			return eventManager.getBehavioralInterfaces();
		}

		@Override
		public void eventReceived(EventOccurrence event) {
			final String s = event.getEvent().getName() + "(" + event.getArgs().stream()
					.map(a -> getValueString(a.getValue()))
					.reduce((s1, s2) -> s1 + ", " + s2).orElse("") + ")";
			Display.getDefault().asyncExec(new Runnable() {
				public void run() {
					exposedEventOccurrenceList.add(s, 0);
				}
			});

		}
	};
	
	private final DefaultDeclarativeQualifiedNameProvider nameProvider = new DefaultDeclarativeQualifiedNameProvider();

	private final Function<Object, String> namingFunction = o -> {
		if (o == null) {
			return null;
		}
		if (o instanceof EObject) {
			final EObject eObject = (EObject) o;
			final QualifiedName fqn = nameProvider.apply(eObject);
			if (fqn == null || fqn.isEmpty()) {
				if (eObject.eResource() == null) {
					final String s = o.toString();
					return s.substring(s.lastIndexOf(".") + 1);
				}
				return eObject.eResource().getURIFragment(eObject);
			}
			return fqn.getLastSegment();
		}
		final String s = o.toString();
		return s.substring(s.lastIndexOf(".") + 1);
	};

	public void createPartControl(Composite parent) {
		parent.setLayout(new FillLayout());
		final SashForm sashForm = new SashForm(parent, SWT.HORIZONTAL);
		createBehavioralInterfaceList(sashForm);
		createEventOccurrenceConfiguratorList(sashForm);
		createExposedEventOccurrenceList(sashForm);
		sashForm.setWeights(new int[] { 3, 5, 2 });
	}

	private Group createSimpleGroup(Composite parent, String text) {
		Group group = new Group(parent, SWT.NULL);
		group.setText(text);
		GridLayout locationLayout = new GridLayout();
		locationLayout.marginHeight = 10;
		locationLayout.marginWidth = 10;
		group.setLayout(locationLayout);
		return group;
	}

	private void createBehavioralInterfaceList(Composite parent) {
		Group group = createSimpleGroup(parent, "Behavioral Interfaces");
		behavioralInterfaceList = new org.eclipse.swt.widgets.List(group, SWT.V_SCROLL | SWT.MULTI | SWT.BORDER);
		GridData gd = new GridData(GridData.FILL_BOTH);
		behavioralInterfaceList.setLayoutData(gd);
		behavioralInterfaceList.addSelectionListener(SelectionListener.widgetSelectedAdapter(e -> {
			selectBehavioralInterface();
		}));
	}

	private void createEventOccurrenceConfiguratorList(Composite parent) {
		eventOccurrenceConfiguratorGroup = createSimpleGroup(parent, "Accepted Event Occurrences");
	}

	private void createExposedEventOccurrenceList(Composite parent) {
		Group group = createSimpleGroup(parent, "Exposed Event Occurrences");
		exposedEventOccurrenceList = new org.eclipse.swt.widgets.List(group, SWT.V_SCROLL | SWT.BORDER);
		GridData gd = new GridData(GridData.FILL_BOTH);
		exposedEventOccurrenceList.setLayoutData(gd);
	}

	private void fillBehavioralInterfaceList(Set<BehavioralInterface> behavioralInterfaces) {
		this.behavioralInterfaces.addAll(behavioralInterfaces);
		behavioralInterfaceList.setItems(this.behavioralInterfaces.stream().map(itf -> itf.getName())
				.collect(Collectors.toList()).toArray(new String[0]));
		refreshEventParameters();
		refreshEventArguments();
		behavioralInterfaceList.select(0);
		selectBehavioralInterface();
	}

	private void selectBehavioralInterface() {
		clearEventOccurrenceConfiguratorGroup();
		selectedBehavioralInterfaces.clear();
		Arrays.stream(behavioralInterfaceList.getSelectionIndices())
				.forEach(i -> selectedBehavioralInterfaces.add(behavioralInterfaces.get(i)));
		fillEventOccurrenceConfiguratorGroup(eventOccurrenceConfiguratorGroup);
	}
	
	private void clearBehavioralInterfaceList() {
		clearEventOccurrenceConfiguratorGroup();
		parameterToParameterClass.clear();
		parameterClassToMatchingModelElements.clear();
		exposedEventOccurrenceList.removeAll();
		behavioralInterfaceList.removeAll();
	}

	private void fillEventOccurrenceConfiguratorGroup(Composite parent) {
		ScrolledComposite scroll = new ScrolledComposite(parent, SWT.V_SCROLL);
		scroll.setLayout(new GridLayout(1, false));
		scroll.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		scroll.setBackgroundMode(SWT.INHERIT_NONE);

		Composite content = new Composite(scroll, SWT.NONE);
		content.setLayout(new GridLayout(1, false));
		content.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true, false));

		selectedBehavioralInterfaces.stream().flatMap(b -> b.getEvents().stream()).filter(e -> e.getType() != EventType.EXPOSED)
				.forEach(e -> new EventOccurrenceConfigurator(content, eventManager, e, parameterToParameterClass,
						parameterClassToMatchingModelElements, namingFunction));

		scroll.setContent(content);
		scroll.setExpandHorizontal(true);
		scroll.setExpandVertical(true);
		scroll.setMinSize(content.computeSize(SWT.DEFAULT, SWT.DEFAULT));
	}

	private void clearEventOccurrenceConfiguratorGroup() {
		Arrays.stream(eventOccurrenceConfiguratorGroup.getChildren()).forEach(c -> c.dispose());
	}

	private void refreshEventParameters() {
		behavioralInterfaces.forEach(itf -> itf.getEvents().forEach(e -> e.getParams().forEach(param -> {
			parameterToParameterClass.computeIfAbsent(param, p -> {
				final String type = p.getType();
				EClass result = null;
				switch (type) {
				case "java.lang.Boolean":
				case "boolean":
				case "java.lang.Float":
				case "float":
				case "java.lang.Integer":
				case "int":
				case "java.lang.String":
					break;
				default:
					result = ePackage.getEClassifiers().stream()
							.filter(c -> c instanceof EClass && ((EClass) c).getInstanceTypeName().equals(type))
							.findFirst().map(c -> (EClass) c).orElse(null);
				}
				return result;
			});
		})));
	}

	private void refreshEventArguments() {
		parameterToParameterClass.values().forEach(c -> {
			final List<EObject> elements = parameterClassToMatchingModelElements.computeIfAbsent(c,
					k -> new ArrayList<>());
			executedModel.getAllContents().forEachRemaining(modelElement -> {
				final EClass elementClass = modelElement.eClass();
				if (c.getEPackage() == elementClass.getEPackage()
						&& (elementClass.getClassifierID() == c.getClassifierID()
								|| elementClass.getEAllSuperTypes().contains(c))) {
					if (!elements.contains(modelElement)) {
						elements.add(modelElement);
					}
				}
			});
		});
	}

	protected Label createTextLabelLayout(Composite parent, String labelString) {
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		parent.setLayoutData(gd);
		Label inputLabel = new Label(parent, SWT.NONE);
		inputLabel.setText(labelString);
		return inputLabel;
	}

	private void setExecutedModel(Resource executedModel) {
		this.executedModel = executedModel;
		this.ePackage = this.executedModel.getContents().stream().findFirst().map(o -> o.eClass().getEPackage())
				.orElse(null);
	}

	@Override
	public void engineSelectionChanged(IExecutionEngine<?> engine) {
		executionEngine = engine;
		if (eventManager != null) {
			eventManager.removeListener(eventListener);
			exposedEventOccurrenceList.removeAll();
		}
		behavioralInterfaces.clear();
		clearBehavioralInterfaceList();
		clearEventOccurrenceConfiguratorGroup();
		if (executionEngine != null) {
			setExecutedModel(executionEngine.getExecutionContext().getResourceModel());
			eventManager = executionEngine.getAddonsTypedBy(IEventManager.class).stream().findFirst().orElse(null);
			eventManager.addListener(eventListener);
			events.clear();
			fillBehavioralInterfaceList(eventManager.getBehavioralInterfaces());
			executionEngine.getExecutionContext().getExecutionPlatform().addEngineAddon(this);
		} else {
			executedModel = null;
			events.clear();
			eventToEventOccurrences.clear();
		}
	}

	private String getValueString(Value val) {
		String result = "";
		switch(val.eClass().getClassifierID()) {
		case ValuePackage.BOOLEAN_ATTRIBUTE_VALUE:
			result += ((BooleanAttributeValue) val).isAttributeValue();
			break;
		case ValuePackage.BOOLEAN_OBJECT_ATTRIBUTE_VALUE:
			result += ((BooleanObjectAttributeValue) val).getAttributeValue();
			break;
		case ValuePackage.INTEGER_ATTRIBUTE_VALUE:
			result += ((IntegerAttributeValue) val).getAttributeValue();
			break;
		case ValuePackage.INTEGER_OBJECT_ATTRIBUTE_VALUE:
			result += ((IntegerObjectAttributeValue) val).getAttributeValue();
			break;
		case ValuePackage.FLOAT_ATTRIBUTE_VALUE:
			result += ((FloatAttributeValue) val).getAttributeValue();
			break;
		case ValuePackage.FLOAT_OBJECT_ATTRIBUTE_VALUE:
			result += ((FloatObjectAttributeValue) val).getAttributeValue();
			break;
		case ValuePackage.STRING_ATTRIBUTE_VALUE:
			result += ((StringAttributeValue) val).getAttributeValue();
			break;
		case ValuePackage.SINGLE_REFERENCE_VALUE:
			result += namingFunction.apply(((SingleReferenceValue) val).getReferenceValue());
			break;
		}
		return result;
	}
	
	@Override
	public void setFocus() {
	}

	@Override
	public void aboutToExecuteStep(IExecutionEngine<?> engine, Step<?> stepToExecute) {
		refreshEventArguments();
	}

	@Override
	public void stepExecuted(IExecutionEngine<?> engine, Step<?> stepExecuted) {
		refreshEventArguments();
	}
}
