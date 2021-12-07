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
package org.eclipse.gemoc.trace.gemoc.traceaddon;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.gemoc.executionframework.debugger.IDynamicPartAccessor;
import org.eclipse.gemoc.executionframework.debugger.MutableField;
import org.eclipse.gemoc.trace.commons.model.generictrace.BooleanAttributeValue;
import org.eclipse.gemoc.trace.commons.model.generictrace.DoubleAttributeValue;
import org.eclipse.gemoc.trace.commons.model.generictrace.DoubleObjectAttributeValue;
import org.eclipse.gemoc.trace.commons.model.generictrace.GenericDimension;
import org.eclipse.gemoc.trace.commons.model.generictrace.GenericSequentialStep;
import org.eclipse.gemoc.trace.commons.model.generictrace.GenericSmallStep;
import org.eclipse.gemoc.trace.commons.model.generictrace.GenericState;
import org.eclipse.gemoc.trace.commons.model.generictrace.GenericStep;
import org.eclipse.gemoc.trace.commons.model.generictrace.GenericTracedObject;
import org.eclipse.gemoc.trace.commons.model.generictrace.GenericValue;
import org.eclipse.gemoc.trace.commons.model.generictrace.GenerictraceFactory;
import org.eclipse.gemoc.trace.commons.model.generictrace.IntegerAttributeValue;
import org.eclipse.gemoc.trace.commons.model.generictrace.IntegerObjectAttributeValue;
import org.eclipse.gemoc.trace.commons.model.generictrace.LongAttributeValue;
import org.eclipse.gemoc.trace.commons.model.generictrace.LongObjectAttributeValue;
import org.eclipse.gemoc.trace.commons.model.generictrace.ManyBooleanAttributeValue;
import org.eclipse.gemoc.trace.commons.model.generictrace.ManyDoubleAttributeValue;
import org.eclipse.gemoc.trace.commons.model.generictrace.ManyIntegerAttributeValue;
import org.eclipse.gemoc.trace.commons.model.generictrace.ManyLongAttributeValue;
import org.eclipse.gemoc.trace.commons.model.generictrace.ManyReferenceValue;
import org.eclipse.gemoc.trace.commons.model.generictrace.ManyStringAttributeValue;
import org.eclipse.gemoc.trace.commons.model.generictrace.SingleReferenceValue;
import org.eclipse.gemoc.trace.commons.model.generictrace.StringAttributeValue;
import org.eclipse.gemoc.trace.commons.model.launchconfiguration.LaunchConfiguration;
import org.eclipse.gemoc.trace.commons.model.trace.BigStep;
import org.eclipse.gemoc.trace.commons.model.trace.MSEModel;
import org.eclipse.gemoc.trace.commons.model.trace.Step;
import org.eclipse.gemoc.trace.commons.model.trace.Trace;
import org.eclipse.gemoc.trace.commons.model.trace.TracedObject;
import org.eclipse.gemoc.trace.gemoc.Activator;
import org.eclipse.gemoc.trace.gemoc.api.ITraceConstructor;
import org.eclipse.gemoc.xdsmlframework.api.engine_addon.modelchangelistener.ModelChange;
import org.eclipse.gemoc.xdsmlframework.api.engine_addon.modelchangelistener.NewObjectModelChange;
import org.eclipse.gemoc.xdsmlframework.api.engine_addon.modelchangelistener.NonCollectionFieldModelChange;
import org.eclipse.gemoc.xdsmlframework.api.engine_addon.modelchangelistener.PotentialCollectionFieldModelChange;
import org.eclipse.gemoc.xdsmlframework.api.engine_addon.modelchangelistener.RemovedObjectModelChange;

public class GenericTraceConstructor implements ITraceConstructor {

	private Trace<GenericSequentialStep, GenericTracedObject, GenericState> traceRoot;
	private Resource executedModel;
	private MSEModel mseModel;
	private Resource traceResource;
	private final Map<EObject, TracedObject<?>> exeToTraced;
	private final Deque<GenericSequentialStep> context = new LinkedList<>();
	private GenericState lastState;
	
	IDynamicPartAccessor dynamicPartAccessor;
	
	public GenericTraceConstructor(Resource executedModel, Resource traceResource, Map<EObject, TracedObject<?>> exeToTraced, IDynamicPartAccessor dynamicPartAccessor) {
		this.executedModel = executedModel;
		this.traceResource = traceResource;
		this.exeToTraced = exeToTraced;
		this.dynamicPartAccessor = dynamicPartAccessor;
	}
	
	private Set<Resource> getAllExecutedModelResources() {
		Set<Resource> allResources = new HashSet<>();
		allResources.add(executedModel);
		allResources.addAll(org.eclipse.gemoc.commons.eclipse.emf.EMFResource.getRelatedResources(executedModel));
		allResources.removeIf(r -> r == null);
		return allResources;
	}

	private boolean addNewObjectToStateIfDynamic(EObject object, GenericState state) {
		List<MutableField> fields = dynamicPartAccessor.extractMutableField(object);
		final List<EStructuralFeature> mutableProperties = fields.stream()
				.map(f -> f.getMutableProperty())
				.collect(Collectors.toList());
		if (dynamicPartAccessor.isDynamic(object) || !fields.isEmpty()) {
			return addNewObjectToState(object, mutableProperties, lastState);
		}
		return true;
	}
	
	@SuppressWarnings("unchecked")
	private GenericValue getGenericValue(EObject object, EStructuralFeature mutableProperty, GenericState state) {
		GenericValue result = null;
		List<MutableField> fields = dynamicPartAccessor.extractMutableField(object);
		Optional<MutableField> dynamicProperty = fields.stream().filter(field -> field.getMutableProperty().getName().equals(mutableProperty.getName())).findFirst();
		if (mutableProperty instanceof EAttribute) {
			final EClassifier eType = mutableProperty.getEType();
			if (eType == EcorePackage.Literals.EINT) {
				if (mutableProperty.getUpperBound() == -1) {
					final ManyIntegerAttributeValue value = GenerictraceFactory.eINSTANCE.createManyIntegerAttributeValue();
					if(dynamicProperty.isPresent()) {
						value.getAttributeValue().addAll((EList<Integer>) dynamicProperty.get().getValue());
					}
					result = value;
				}
				else {
					final IntegerAttributeValue value = GenerictraceFactory.eINSTANCE.createIntegerAttributeValue();
					if(dynamicProperty.isPresent()) {
						value.setAttributeValue((Integer)dynamicProperty.get().getValue());
					}
					result = value;
				}
			} else  if (eType == EcorePackage.Literals.EINTEGER_OBJECT) {
				final IntegerObjectAttributeValue value = GenerictraceFactory.eINSTANCE.createIntegerObjectAttributeValue();
				if(dynamicProperty.isPresent()) {
					value.setAttributeValue((Integer)dynamicProperty.get().getValue());
				}
				result = value;
			} else if (eType == EcorePackage.Literals.EBOOLEAN) {
				if (mutableProperty.getUpperBound() == -1) {
					final ManyBooleanAttributeValue value = GenerictraceFactory.eINSTANCE.createManyBooleanAttributeValue();
					if(dynamicProperty.isPresent()) {
						value.getAttributeValue().addAll((EList<Boolean>) dynamicProperty.get().getValue());
					}
					result = value;
				}
				else {
					final BooleanAttributeValue value = GenerictraceFactory.eINSTANCE.createBooleanAttributeValue();
					if(dynamicProperty.isPresent()) {
						value.setAttributeValue((Boolean)dynamicProperty.get().getValue());
					}
					result = value;
				}
			} else if (eType == EcorePackage.Literals.ESTRING) {
				if (mutableProperty.getUpperBound() == -1) {
					final ManyStringAttributeValue value = GenerictraceFactory.eINSTANCE.createManyStringAttributeValue();
					if(dynamicProperty.isPresent()) {
						value.getAttributeValue().addAll((EList<String>) dynamicProperty.get().getValue());
					}
					result = value;
				}
				else {
					final StringAttributeValue value = GenerictraceFactory.eINSTANCE.createStringAttributeValue();
					if(dynamicProperty.isPresent()) {
						value.setAttributeValue((String)dynamicProperty.get().getValue());
					}
					result = value;
				}
			} else if (eType == EcorePackage.Literals.EDOUBLE) {
				if (mutableProperty.getUpperBound() == -1) {
					final ManyDoubleAttributeValue value = GenerictraceFactory.eINSTANCE.createManyDoubleAttributeValue();
					if(dynamicProperty.isPresent()) {
						value.getAttributeValue().addAll((EList<Double>) dynamicProperty.get().getValue());
					}
					result = value;
				}
				else {
					final DoubleAttributeValue value = GenerictraceFactory.eINSTANCE.createDoubleAttributeValue();
					if(dynamicProperty.isPresent()) {
						value.setAttributeValue((Double)dynamicProperty.get().getValue());
					}
					result = value;
				}
			}  else if (eType == EcorePackage.Literals.EDOUBLE_OBJECT) {
				final DoubleObjectAttributeValue value = GenerictraceFactory.eINSTANCE.createDoubleObjectAttributeValue();
				if(dynamicProperty.isPresent()) {
					value.setAttributeValue((Double)dynamicProperty.get().getValue());
				}
				result = value;
			} else if (eType == EcorePackage.Literals.ELONG) {
				if (mutableProperty.getUpperBound() == -1) {
					final ManyLongAttributeValue value = GenerictraceFactory.eINSTANCE.createManyLongAttributeValue();
					if(dynamicProperty.isPresent()) {
						value.getAttributeValue().addAll((EList<Long>) dynamicProperty.get().getValue());
					}
					result = value;
				}
				else {
					final LongAttributeValue value = GenerictraceFactory.eINSTANCE.createLongAttributeValue();
					if(dynamicProperty.isPresent()) {
						value.setAttributeValue((Long)dynamicProperty.get().getValue());
					}
					result = value;
				}
			}  else if (eType == EcorePackage.Literals.ELONG_OBJECT) {
				final LongObjectAttributeValue value = GenerictraceFactory.eINSTANCE.createLongObjectAttributeValue();
				if(dynamicProperty.isPresent()) {
					value.setAttributeValue((Long)dynamicProperty.get().getValue());
				}
				result = value;
			} else {
				Activator.error("eType "+eType+" not supported yet (used by "+mutableProperty.getName()+" of "+object.eClass()+ ");\n "
						+ "Please use another type in your RTD or consider to contribute to GEMOC framework", 
						new java.lang.UnsupportedOperationException("eType "+eType+" not supported yet (used by "+mutableProperty.getName()+" of "+object.eClass()+ ")"));
			}
		} else if (mutableProperty instanceof EReference) {
			if (mutableProperty.isMany()) {
				List<EObject> modelElements = new ArrayList<>();
				if(dynamicProperty.isPresent()) {
					modelElements = (List<EObject>) dynamicProperty.get().getValue();
				}
				final ManyReferenceValue value = GenerictraceFactory.eINSTANCE.createManyReferenceValue();
				for (EObject o : modelElements) {
					if (dynamicPartAccessor.isDynamic(o) || !fields.isEmpty()) {
						final TracedObject<?> tracedObj = exeToTraced.get(o);
						if(tracedObj == null) {
						Activator.error("Cannot find traced object for "+o+" (used by "+mutableProperty.getName()+" of "+object.eClass()+ ");\n"
								+ "Did you correctly declare it as Runtime Data ?", 
								new Exception("Cannot find traced object for "+o+" (used by "+mutableProperty.getName()+" of "+object.eClass()+ ")"));
						}
						value.getReferenceValues().add(tracedObj);
					} else {
						value.getReferenceValues().add(o);
					}
				}
				result = value;
			} else {
				EObject o = null;
				if(dynamicProperty.isPresent()) {
					o = (EObject) dynamicProperty.get().getValue();
				}
				final SingleReferenceValue value = GenerictraceFactory.eINSTANCE.createSingleReferenceValue();
				if (o != null  && dynamicPartAccessor.isDynamic(o)) {
					final TracedObject<?> tracedObj = exeToTraced.get(o);
					if(tracedObj == null) {
					Activator.error("Cannot find traced object for "+o+" (used by "+mutableProperty.getName()+" of "+object.eClass()+ ");\n"
							+ "Did you correctly declare it as Runtime Data ?", 
							new Exception("Cannot find traced object for "+o+" (used by "+mutableProperty.getName()+" of "+object.eClass()+ ")"));
					} 
					value.setReferenceValue(tracedObj);
				} else {
					value.setReferenceValue(o);
				}
				result = value;
			}
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	private boolean addNewObjectToState(EObject object, List<EStructuralFeature> mutableProperties, GenericState state) {
		boolean added = false;
		if (!exeToTraced.containsKey(object)) {
			List<MutableField> fields = dynamicPartAccessor.extractMutableField(object);
			final GenericTracedObject tracedObject = GenerictraceFactory.eINSTANCE.createGenericTracedObject();
			if (dynamicPartAccessor.isDynamic(object) || !fields.isEmpty()) {
				tracedObject.setOriginalObject(object);
			}
			exeToTraced.put(object, tracedObject);
			for (EStructuralFeature mutableProperty : mutableProperties) {
				Optional<MutableField> dynamicProperty = fields.stream().filter(field -> field.getMutableProperty().getName().equals(mutableProperty.getName())).findFirst();
				final GenericDimension dimension = GenerictraceFactory.eINSTANCE.createGenericDimension();
				GenericValue firstValue = null;
				dimension.setDynamicProperty(mutableProperty);
				tracedObject.getAllDimensions().add(dimension);
				if (mutableProperty instanceof EAttribute) {
					final EClassifier eType = mutableProperty.getEType();
					if (eType == EcorePackage.Literals.EINT) {
						if (mutableProperty.getUpperBound() == -1) {
							final ManyIntegerAttributeValue value = GenerictraceFactory.eINSTANCE.createManyIntegerAttributeValue();
							if(dynamicProperty.isPresent()) {
								value.getAttributeValue().addAll((EList<Integer>) dynamicProperty.get().getValue());
							}
							firstValue = value;
						}
						else {
							final IntegerAttributeValue value = GenerictraceFactory.eINSTANCE.createIntegerAttributeValue();
							if(dynamicProperty.isPresent()) {
								value.setAttributeValue((Integer)dynamicProperty.get().getValue());
							}
							firstValue = value;
						}
					} else if (eType == EcorePackage.Literals.EBOOLEAN) {
						if (mutableProperty.getUpperBound() == -1) {
							final ManyBooleanAttributeValue value = GenerictraceFactory.eINSTANCE.createManyBooleanAttributeValue();
							if(dynamicProperty.isPresent()) {
								value.getAttributeValue().addAll((EList<Boolean>) dynamicProperty.get().getValue());
							}
							firstValue = value;
						}
						else {
							final BooleanAttributeValue value = GenerictraceFactory.eINSTANCE.createBooleanAttributeValue();
							if(dynamicProperty.isPresent()) {
								value.setAttributeValue((Boolean)dynamicProperty.get().getValue());
							}
							firstValue = value;
						}
					} else if (eType == EcorePackage.Literals.ESTRING) {
						if (mutableProperty.getUpperBound() == -1) {
							final ManyStringAttributeValue value = GenerictraceFactory.eINSTANCE.createManyStringAttributeValue();
							if(dynamicProperty.isPresent()) {
								value.getAttributeValue().addAll((EList<String>) dynamicProperty.get().getValue());
							}
							firstValue = value;
						}
						else {
							final StringAttributeValue value = GenerictraceFactory.eINSTANCE.createStringAttributeValue();
							if(dynamicProperty.isPresent()) {
								value.setAttributeValue((String)dynamicProperty.get().getValue());
							}
							firstValue = value;
						}
					} else if (eType == EcorePackage.Literals.EDOUBLE) {
						if (mutableProperty.getUpperBound() == -1) {
							final ManyDoubleAttributeValue value = GenerictraceFactory.eINSTANCE.createManyDoubleAttributeValue();
							if(dynamicProperty.isPresent()) {
								value.getAttributeValue().addAll((EList<Double>) dynamicProperty.get().getValue());
							}
							firstValue = value;
						}
						else {
							final DoubleAttributeValue value = GenerictraceFactory.eINSTANCE.createDoubleAttributeValue();
							if(dynamicProperty.isPresent()) {
								value.setAttributeValue((Double)dynamicProperty.get().getValue());
							}
							firstValue = value;
						}
					} else if (eType == EcorePackage.Literals.ELONG) {
						if (mutableProperty.getUpperBound() == -1) {
							final ManyLongAttributeValue value = GenerictraceFactory.eINSTANCE.createManyLongAttributeValue();
							if(dynamicProperty.isPresent()) {
								value.getAttributeValue().addAll((EList<Long>) dynamicProperty.get().getValue());
							}
							firstValue = value;
						}
						else {
							final LongAttributeValue value = GenerictraceFactory.eINSTANCE.createLongAttributeValue();
							if(dynamicProperty.isPresent()) {
								value.setAttributeValue((Long)dynamicProperty.get().getValue());
							}
							firstValue = value;
						}
					} else if (eType == EcorePackage.Literals.EINTEGER_OBJECT) {
						final IntegerObjectAttributeValue value = GenerictraceFactory.eINSTANCE.createIntegerObjectAttributeValue();
						if(dynamicProperty.isPresent()) {
							value.setAttributeValue((Integer)dynamicProperty.get().getValue());
						}
						firstValue = value;
					} else if (eType == EcorePackage.Literals.EDOUBLE_OBJECT) {
						final DoubleObjectAttributeValue value = GenerictraceFactory.eINSTANCE.createDoubleObjectAttributeValue();
						if(dynamicProperty.isPresent()) {
							value.setAttributeValue((Double)dynamicProperty.get().getValue());
						}
						firstValue = value;
					} else if (eType == EcorePackage.Literals.ELONG_OBJECT) {
						final LongObjectAttributeValue value = GenerictraceFactory.eINSTANCE.createLongObjectAttributeValue();
						if(dynamicProperty.isPresent()) {
							value.setAttributeValue((Long)dynamicProperty.get().getValue());
						}
						firstValue = value;
					} else {
						Activator.error("eType "+eType+" not supported yet (used by "+mutableProperty.getName()+" of "+object.eClass()+ "); "
								+ "Please use another type in your RTD or consider to contribute to GEMOC framework", 
								new java.lang.UnsupportedOperationException("eType "+eType+" not supported yet (used by "+mutableProperty.getName()+" of "+object.eClass()+ ")"));
					}
				} else if (mutableProperty instanceof EReference) {
					if (mutableProperty.isMany()) {
						List<EObject> modelElements = new ArrayList<>();
						if(dynamicProperty.isPresent()) {
							modelElements = (List<EObject>) dynamicProperty.get().getValue();
						}
						final ManyReferenceValue value = GenerictraceFactory.eINSTANCE.createManyReferenceValue();
						for (EObject o : modelElements) {
							addNewObjectToStateIfDynamic(o, state);
							final TracedObject<?> tracedObj = exeToTraced.get(o);
							if(tracedObj != null) {
								value.getReferenceValues().add(tracedObj);
							} else {
								value.getReferenceValues().add(o);
							}
						}
						firstValue = value;
					} else {
						EObject o = null;
						if(dynamicProperty.isPresent()) {
							o = (EObject) dynamicProperty.get().getValue();
						}
						if (o != null) {
							addNewObjectToStateIfDynamic(o, state);
							final SingleReferenceValue value = GenerictraceFactory.eINSTANCE.createSingleReferenceValue();
							final TracedObject<?> tracedObj = exeToTraced.get(o);
							if(tracedObj != null) {
								value.setReferenceValue(tracedObj);
							} else {
								value.setReferenceValue(o);
							}
							firstValue = value;
						}
					}
				}
				if (firstValue != null) {
					dimension.getValues().add(firstValue);
					state.getValues().add(firstValue);
				}
			}
			traceRoot.getTracedObjects().add(tracedObject);
			added = true;
		}
		return added;
	}
	
	private void addInitialState() {
		if (lastState == null) {
			// Creation of the initial state
			Set<Resource> allResources = getAllExecutedModelResources();
			
			lastState = GenerictraceFactory.eINSTANCE.createGenericState();
			for (Resource r : allResources) {
				for (TreeIterator<EObject> i = r.getAllContents(); i.hasNext();) {
					final EObject o = i.next();
					addNewObjectToStateIfDynamic(o, lastState);
				}
			}
			this.traceRoot.getStates().add(lastState);
		}
	}
	
	private boolean copiedState = false;
	
	private GenericState copyState(GenericState oldState) {
		GenericState newState = GenerictraceFactory.eINSTANCE.createGenericState();
		newState.getValues().addAll(oldState.getValues());
		copiedState = true;
		return newState;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void addState(List<ModelChange> modelChanges) {
		if (lastState == null) {
			addInitialState();
		}
		if (!modelChanges.isEmpty()) {
			boolean stateChanged = false;
			// We start by a (shallow) copy of the last state
			// But we will have to rollback a little by replacing values that changed
			GenericState newState = copyState(lastState);
			for (ModelChange modelChange : modelChanges) {
				EObject o = modelChange.getChangedObject();
				List<MutableField> fields = dynamicPartAccessor.extractMutableField(o);
				// We only look at constructable objects that have mutable fields
				// Here we have nothing to rollback, just a new object to add
				if (modelChange instanceof NewObjectModelChange) {
					stateChanged = true;
					addNewObjectToStateIfDynamic(o, newState);
				}
				// We only look at constructable objects that have mutable fields
				// Here we must rollback to remove the values of the removed object from the copied state
				else if (modelChange instanceof RemovedObjectModelChange) {
					stateChanged = true;
					final List<GenericValue> values = newState.getValues();
					List<GenericDimension> dimensions = ((GenericTracedObject) exeToTraced.get(o)).getAllDimensions();
					if (dimensions != null && dimensions.size()>0) {
						for (GenericDimension d :dimensions) {
							if (d.getValues() != null && d.getValues().size()>0) {
								values.remove(d.getValues().get(d.getValues().size() - 1));
							}
						}
					}
				}
				// Here we must look at non-collection mutable fields
				// We must rollback the last values from the copied state, and add new values as well
				// ie. mix of remove and new
				else if (modelChange instanceof NonCollectionFieldModelChange) {
					stateChanged = true;
					EStructuralFeature p = ((NonCollectionFieldModelChange) modelChange).getChangedField();
					Optional<MutableField> dynamicProperty = fields.stream().filter(field -> field.getMutableProperty().getName().equals(p.getName())).findFirst();
					// Rollback: we remove the last value of this field from the new state
					final GenericTracedObject tracedObject = (GenericTracedObject) exeToTraced.get(o);
					final GenericDimension dimension = tracedObject.getAllDimensions().stream()
							.filter(d -> d.getDynamicProperty().getName().equals(p.getName()))
							.findFirst().orElse(null);
					if (dimension != null) {
						final List<GenericValue> values = dimension.getValues();
						if(dynamicProperty.isPresent()) {
							final Object pValue = dynamicProperty.get().getValue();
							if (pValue instanceof EObject) {
								addNewObjectToStateIfDynamic((EObject) pValue, newState);
							}
						}
						final GenericValue lastValue = values.isEmpty() ? null : values.get(values.size() - 1);
						if (lastValue != null) {
							newState.getValues().remove(lastValue);
						}
						final GenericValue newValue = getGenericValue(o, p, newState);
						values.add(newValue);
						newState.getValues().add(newValue);
					}
				}
				// Here we look at collection mutable fields
				// We must first manually find out if the collection changed...
				// If it changed we must rollback the last values from the copied state, and add new values as well
				else if (modelChange instanceof PotentialCollectionFieldModelChange) {
					final EStructuralFeature p = ((PotentialCollectionFieldModelChange) modelChange).getChangedField();
					Optional<MutableField> dynamicProperty = fields.stream().filter(field -> field.getMutableProperty().getName().equals(p.getName())).findFirst();
					final GenericTracedObject tracedObject = (GenericTracedObject) exeToTraced.get(o);
					// We compare the last collection in the value sequence, and the current one in the potentially changed object
					final GenericDimension dimension = tracedObject.getAllDimensions().stream()
							.filter(d -> d.getDynamicProperty() == p)
							.findFirst().orElse(null);
					if (dimension != null) {
						final List<GenericValue> dimensionValues = dimension.getValues();
						final GenericValue lastDimensionValue = dimensionValues.get(dimensionValues.size() - 1);
						boolean change = false;
						if (lastDimensionValue instanceof ManyReferenceValue) {
							final ManyReferenceValue lastValue = (ManyReferenceValue) lastDimensionValue;
							List<EObject> values = new ArrayList<>();
							if(dynamicProperty.isPresent()) {
								values = (List<EObject>) dynamicProperty.get().getValue();
							}
							for (EObject eObj : values) {
								addNewObjectToStateIfDynamic(eObj, newState);
							}
							if (lastValue != null && lastValue.getReferenceValues().size() == values.size()) {
								java.util.Iterator<EObject> it = values.iterator();
								for (EObject aPreviousValue : lastValue.getReferenceValues()) {
									EObject aCurrentValue = it.next();
									if (aPreviousValue != exeToTraced.get(aCurrentValue)) {
										change = true;
										break;
									}
								}
							} else {
								change = true;
							}
						}
						else if (lastDimensionValue instanceof ManyStringAttributeValue) {
							final ManyStringAttributeValue lastValue = (ManyStringAttributeValue) lastDimensionValue;
							List<String> values = new ArrayList<>();
							if(dynamicProperty.isPresent()) {
								values = (List<String>) dynamicProperty.get().getValue();
							}
							if (lastValue != null && lastValue.getAttributeValue().size() == values.size()) {
								if (!lastValue.getAttributeValue().equals(values)) {
									change = true;
									break;
								}
							} else {
								change = true;
							}
						}
						else if (lastDimensionValue instanceof ManyIntegerAttributeValue) {
							final ManyIntegerAttributeValue lastValue = (ManyIntegerAttributeValue) lastDimensionValue;
							List<Integer> values = new ArrayList<>();
							if(dynamicProperty.isPresent()) {
								values = (List<Integer>) dynamicProperty.get().getValue();
							}
							if (lastValue != null && lastValue.getAttributeValue().size() == values.size()) {
								if (!lastValue.getAttributeValue().equals(values)) {
									change = true;
									break;
								}
							} else {
								change = true;
							}
						}
						else if (lastDimensionValue instanceof ManyBooleanAttributeValue) {
							final ManyBooleanAttributeValue lastValue = (ManyBooleanAttributeValue) lastDimensionValue;
							List<Boolean> values = new ArrayList<>();
							if(dynamicProperty.isPresent()) {
								values = (List<Boolean>) dynamicProperty.get().getValue();
							}
							if (lastValue != null && lastValue.getAttributeValue().size() == values.size()) {
								if (!lastValue.getAttributeValue().equals(values)) {
									change = true;
									break;
								}
							} else {
								change = true;
							}
						}
						else if (lastDimensionValue instanceof ManyDoubleAttributeValue) {
							final ManyDoubleAttributeValue lastValue = (ManyDoubleAttributeValue) lastDimensionValue;
							List<Double> values = new ArrayList<>();
							if(dynamicProperty.isPresent()) {
								values = (List<Double>) dynamicProperty.get().getValue();
							}
							if (lastValue != null && lastValue.getAttributeValue().size() == values.size()) {
								if (!lastValue.getAttributeValue().equals(values)) {
									change = true;
									break;
								}
							} else {
								change = true;
							}
						}
						else if (lastDimensionValue instanceof ManyLongAttributeValue) {
							final ManyLongAttributeValue lastValue = (ManyLongAttributeValue) lastDimensionValue;
							List<Long> values = new ArrayList<>();
							if(dynamicProperty.isPresent()) {
								values = (List<Long>) dynamicProperty.get().getValue();
							}
							if (lastValue != null && lastValue.getAttributeValue().size() == values.size()) {
								if (!lastValue.getAttributeValue().equals(values)) {
									change = true;
									break;
								}
							} else {
								change = true;
							}
						}
						if (change) {
							stateChanged = true;
							// Rollback: we remove the last value of this field from the new state
							newState.getValues().remove(lastDimensionValue);
							// And we create a proper new value
							GenericValue newValue = getGenericValue(o, p, newState);
							dimension.getValues().add(newValue);
							newState.getValues().add(newValue);
						}
					}
				}
			}
			if (stateChanged) {
				final GenericStep currentStep = context.peekFirst();
				if (currentStep != null && currentStep instanceof BigStep) {
					final GenericState startingState = lastState;
					final GenericState endingState = newState;
					addImplicitStep((BigStep<GenericStep, GenericState>) currentStep, startingState, endingState);
				}
				lastState = newState;
				traceRoot.getStates().add(lastState);
			} else if (copiedState) {
				newState.getValues().clear();
			}
			copiedState = false;
		}
	}
	
	private void addImplicitStep(BigStep<GenericStep, GenericState> currentStep, GenericState startingState, GenericState endingState) {
		GenericSmallStep implicitStep = GenerictraceFactory.eINSTANCE.createGenericSmallStep();
		implicitStep.setStartingState(startingState);
		implicitStep.setEndingState(endingState);
		currentStep.getSubSteps().add(implicitStep);
	}
	
	@Override
	public void addStep(Step<?> step) {
		GenericSequentialStep step_cast = null;
		if (step != null && step instanceof GenericSequentialStep) {
			step_cast = (GenericSequentialStep) step;
			if (mseModel == null) {
				mseModel = org.eclipse.gemoc.trace.commons.model.trace.TraceFactory.eINSTANCE.createMSEModel();
				traceResource.getContents().add(mseModel);
			}
			mseModel.getOwnedMSEs().add(step_cast.getMseoccurrence().getMse());
			GenericState state = traceRoot.getStates().get(traceRoot.getStates().size() - 1);
			step_cast.setStartingState(state);
			if (!context.isEmpty() && context.getFirst() != null) {
				context.getFirst().getSubSteps().add(step_cast);
			} else {
				traceRoot.getRootStep().getSubSteps().add(step_cast);
			}
		}
		context.push(step_cast);
	}

	@Override
	public void endStep(Step<?> step) {
		GenericStep popped = context.pop();
		if (popped != null)
			popped.setEndingState(lastState);
	}

	@Override
	public EObject initTrace(LaunchConfiguration launchConfiguration) {
		// Create root
		traceRoot = GenerictraceFactory.eINSTANCE.createGenericTrace();
		traceRoot.setLaunchconfiguration(launchConfiguration);

		// Create root sequential step
		GenericSequentialStep rootStep = GenerictraceFactory.eINSTANCE.createGenericSequentialStep();
		traceRoot.setRootStep(rootStep);

		// Put in the resource
		traceResource.getContents().add(traceRoot);

		return traceRoot;
	}

	@Override
	public void save() {
		try {
			traceResource.save(null);
		} catch (java.io.IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void save(URI uri) {
		try {
			traceResource.setURI(uri);
			traceResource.save(null);
		} catch (java.io.IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean isPartialTraceConstructor() {
		return false;
	}

}
