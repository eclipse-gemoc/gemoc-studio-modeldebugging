/*******************************************************************************
 * Copyright (c) 2016, 2020 Inria and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Inria - initial API and implementation
 *******************************************************************************/
package org.eclipse.gemoc.trace.gemoc.api;

import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.gemoc.trace.commons.model.trace.Dimension;
import org.eclipse.gemoc.trace.commons.model.trace.State;
import org.eclipse.gemoc.trace.commons.model.trace.Step;
import org.eclipse.gemoc.trace.commons.model.trace.Trace;
import org.eclipse.gemoc.trace.commons.model.trace.TracedObject;
import org.eclipse.gemoc.trace.commons.model.trace.Value;
import org.eclipse.gemoc.xdsmlframework.api.engine_addon.IEngineAddon;

public interface IMultiDimensionalTraceAddon<StepSubType extends Step<?>, StateSubType extends State<?,?>, TracedObjectSubType extends TracedObject<?>, DimensionSubType extends Dimension<?>, ValueSubType extends Value<?>> extends IEngineAddon {

	ITraceExplorer<StepSubType, StateSubType, TracedObjectSubType, DimensionSubType, ValueSubType> getTraceExplorer();
	
	ITraceConstructor getTraceConstructor();
	
	ITraceExtractor<StepSubType, StateSubType, TracedObjectSubType, DimensionSubType, ValueSubType> getTraceExtractor();
	
	ITraceNotifier getTraceNotifier();

	IStepFactory getFactory();

	void load(Resource traceResource);

	boolean isAddonForTrace(EObject traceRoot);

	Trace<?,?,?> getTrace();
	
	@Override
	default List<String> getTags() {
		// add the "MultiDimensionalTrace" tag to the list in order 
		// to enable possibility to sort addons with regards to this group
		return  Arrays.asList(GROUP_TAG, getAddonID());
	}
	
	public final String GROUP_TAG = "MultiDimensionalTrace";
}
