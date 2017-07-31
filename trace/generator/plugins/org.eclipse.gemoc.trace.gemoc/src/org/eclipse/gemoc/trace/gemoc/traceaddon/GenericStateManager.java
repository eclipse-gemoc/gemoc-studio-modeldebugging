/*******************************************************************************
 * Copyright (c) 2017 Inria and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Inria - initial API and implementation
 *******************************************************************************/
package org.eclipse.gemoc.trace.gemoc.traceaddon;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gemoc.executionframework.engine.core.CommandExecution;

import org.eclipse.gemoc.trace.commons.model.generictrace.BooleanAttributeValue;
import org.eclipse.gemoc.trace.commons.model.generictrace.GenericAttributeValue;
import org.eclipse.gemoc.trace.commons.model.generictrace.GenericDimension;
import org.eclipse.gemoc.trace.commons.model.generictrace.GenericState;
import org.eclipse.gemoc.trace.commons.model.generictrace.GenericTracedObject;
import org.eclipse.gemoc.trace.commons.model.generictrace.IntegerAttributeValue;
import org.eclipse.gemoc.trace.commons.model.generictrace.ManyReferenceValue;
import org.eclipse.gemoc.trace.commons.model.generictrace.SingleReferenceValue;
import org.eclipse.gemoc.trace.commons.model.generictrace.StringAttributeValue;
import org.eclipse.gemoc.trace.commons.model.trace.State;
import org.eclipse.gemoc.trace.commons.model.trace.TracedObject;
import org.eclipse.gemoc.trace.gemoc.api.IStateManager;

public class GenericStateManager implements IStateManager<State<?, ?>> {
	
	private final Resource modelResource;
	
	private final Map<TracedObject<?>, EObject> tracedToExe;
	
	public GenericStateManager(Resource modelResource, Map<TracedObject<?>, EObject> tracedToExe) {
		this.modelResource = modelResource;
		this.tracedToExe = tracedToExe;
	}
	
	@Override
	public void restoreState(State<?, ?> state) {
		
		if (modelResource != null && state instanceof GenericState) {
			try {
				final TransactionalEditingDomain ed = TransactionUtil.getEditingDomain(modelResource);
				if (ed != null) {
					final RecordingCommand command = new RecordingCommand(ed, "") {
						protected void doExecute() {
							restoreStateExecute((GenericState) state);
						}
					};
					CommandExecution.execute(ed, command);
				}
			} catch (Exception e) {
				throw e;
			}
		}
	}
	
	public void restoreStateExecute(GenericState state) {
		state.getValues().forEach(v -> {
			GenericDimension dimension = (GenericDimension) v.eContainer();
			GenericTracedObject tracedObject = (GenericTracedObject) dimension.eContainer();
			EObject originalObject = tracedObject.getOriginalObject();
			if (originalObject == null) {
				originalObject = tracedToExe.get(tracedObject);
			}
			if (v instanceof GenericAttributeValue) {
				if (v instanceof IntegerAttributeValue) {
					originalObject.eSet(dimension.getDynamicProperty(), ((IntegerAttributeValue) v).getAttributeValue());
				} else if (v instanceof BooleanAttributeValue) {
					originalObject.eSet(dimension.getDynamicProperty(), ((BooleanAttributeValue) v).isAttributeValue());
				} else {
					originalObject.eSet(dimension.getDynamicProperty(), ((StringAttributeValue) v).getAttributeValue());
				}
			} else {
				if (v instanceof SingleReferenceValue) {
					final EObject refVal = ((SingleReferenceValue) v).getReferenceValue();
					if (refVal instanceof GenericTracedObject) {
						final EObject exe = tracedToExe.get(refVal);
						originalObject.eSet(dimension.getDynamicProperty(), exe);
					} else {
						originalObject.eSet(dimension.getDynamicProperty(), refVal);
					}
				} else {
					final List<EObject> values = new BasicEList<EObject>();
					values.addAll(((ManyReferenceValue) v).getReferenceValues().stream().map(refVal -> {
						if (refVal instanceof GenericTracedObject) {
							return tracedToExe.get(refVal);
						} else {
							return refVal;
						}
					}).collect(Collectors.toList()));
					originalObject.eSet(dimension.getDynamicProperty(), values);
				}
			}
		});
	}
}
