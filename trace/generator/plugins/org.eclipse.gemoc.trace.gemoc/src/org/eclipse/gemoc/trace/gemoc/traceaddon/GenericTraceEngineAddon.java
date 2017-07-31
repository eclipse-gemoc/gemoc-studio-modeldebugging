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

import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

import org.eclipse.gemoc.trace.commons.model.trace.State;
import org.eclipse.gemoc.trace.commons.model.trace.Trace;
import org.eclipse.gemoc.trace.commons.model.trace.TracedObject;
import org.eclipse.gemoc.trace.gemoc.api.IStateManager;
import org.eclipse.gemoc.trace.gemoc.api.IStepFactory;
import org.eclipse.gemoc.trace.gemoc.api.ITraceConstructor;

public class GenericTraceEngineAddon extends AbstractTraceAddon {

	private GenericTraceStepFactory factory = null;
	
	@Override
	public IStepFactory getFactory() {
		if (factory == null) {
			factory = new GenericTraceStepFactory();
		}
		return factory;
	}

	@Override
	public ITraceConstructor constructTraceConstructor(Resource modelResource,
			Resource traceResource, Map<EObject, TracedObject<?>> exeToTraced) {
		return new GenericTraceConstructor(modelResource, traceResource, exeToTraced);
	}

	@Override
	public boolean isAddonForTrace(EObject traceRoot) {
		return traceRoot instanceof Trace;
	}

	@Override
	public IStateManager<State<?, ?>> constructStateManager(Resource modelResource, Map<TracedObject<?>, EObject> tracedToExe) {
		return new GenericStateManager(modelResource, tracedToExe);
	}

}
