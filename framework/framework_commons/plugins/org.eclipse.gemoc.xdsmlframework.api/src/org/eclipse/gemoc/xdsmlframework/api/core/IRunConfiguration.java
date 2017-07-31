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
package org.eclipse.gemoc.xdsmlframework.api.core;

import java.util.Collection;

import org.eclipse.emf.common.util.URI;
import org.eclipse.gemoc.xdsmlframework.api.extensions.engine_addon.EngineAddonSpecificationExtension;

public interface IRunConfiguration {
	
	// main launch parameters
	public static final String LAUNCH_MODEL_PATH = "GEMOC_LAUNCH_MODEL_PATH";
	public static final String LAUNCH_DELAY = "GEMOC_ANIMATE_DELAY";
	public static final String LAUNCH_SELECTED_LANGUAGE = "GEMOC_LAUNCH_SELECTED_LANGUAGE";
	public static final String LAUNCH_MELANGE_QUERY = "GEMOC_LAUNCH_MELANGE_QUERY";
	public static final String LAUNCH_MODEL_ENTRY_POINT = "LAUNCH_MODEL_ENTRY_POINT";
	public static final String LAUNCH_METHOD_ENTRY_POINT = "LAUNCH_METHOD_ENTRY_POINT";
	public static final String LAUNCH_INITIALIZATION_METHOD = "GEMOC_LAUNCH_INITIALIZATION_METHOD";
	public static final String LAUNCH_INITIALIZATION_ARGUMENTS = "GEMOC_LAUNCH_INITIALIZATION_ARGUMENTS";
	public static final String LAUNCH_BREAK_START = "GEMOC_LAUNCH_BREAK_START";
	public static final String DEBUG_MODEL_ID = "GEMOC_DEBUG_MODEL_ID";

	// parameters that should be derived from the language in future version
	public static final String LAUNCH_DEADLOCK_DETECTION_DEPTH = "GEMOC_LAUNCH_DEADLOCK_DETECTION_DEPTH";

	String getLanguageName();

	URI getExecutedModelURI();
	
	URI getExecutedModelAsMelangeURI();
	
	String getMelangeQuery();
	
	URI getAnimatorURI();

	int getAnimationDelay();
	
	int getDeadlockDetectionDepth();

	Collection<EngineAddonSpecificationExtension> getEngineAddonExtensions();
		
	String getExecutionEntryPoint();
	
	String getModelEntryPoint();

	String getModelInitializationMethod();
	
	String getModelInitializationArguments();
	
	String getDebugModelID();

	boolean getBreakStart();

	
}
