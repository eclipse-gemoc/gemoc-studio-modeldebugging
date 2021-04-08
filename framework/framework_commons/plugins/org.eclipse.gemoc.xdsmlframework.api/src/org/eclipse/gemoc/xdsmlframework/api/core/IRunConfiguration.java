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
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.URI;
import org.eclipse.gemoc.xdsmlframework.api.extensions.engine_addon.EngineAddonSpecificationExtension;

public interface IRunConfiguration {
	
	// main launch parameters
	public static final String LAUNCH_MODEL_PATH = "GEMOC_LAUNCH_MODEL_PATH";
	public static final String LAUNCH_DELAY = "GEMOC_ANIMATE_DELAY";
	public static final String LAUNCH_SELECTED_LANGUAGE = "GEMOC_LAUNCH_SELECTED_LANGUAGE";
	public static final String LAUNCH_MELANGE_QUERY = "GEMOC_LAUNCH_MELANGE_QUERY";
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
	
	/**
	 * return a string attribute from the runconfiguration identified by attributeName
	 * returns the default value if not found
	 * This is useful for addon options for example
	 * @param attributeName
	 * @param defaultValue
	 * @return
	 */
	String getAttribute(String attributeName, String defaultValue);

	/**
	 * return an integer attribute from the runconfiguration identified by attributeName
	 * returns the default value if not found
	 * This is useful for addon options for example
	 * @param attributeName
	 * @param defaultValue
	 * @return
	 */	
	Integer getAttribute(String attributeName, Integer defaultValue);

	/**
	 * return a boolean attribute from the runconfiguration identified by attributeName
	 * returns the default value if not found
	 * This is useful for addon options for example
	 * @param attributeName
	 * @param defaultValue
	 * @return
	 */	
	Boolean getAttribute(String attributeName, Boolean defaultValue);

	/**
	 * return a map attribute from the runconfiguration identified by attributeName
	 * returns the default value if not found
	 * This is useful for addon options for example
	 * @param attributeName
	 * @param defaultValue
	 * @return
	 */	
	Map<String, String> getAttribute(String attributeName, Map<String, String> defaultValue);

	/**
	 * return a set attribute from the runconfiguration identified by attributeName
	 * returns the default value if not found
	 * This is useful for addon options for example
	 * @param attributeName
	 * @param defaultValue
	 * @return
	 */	
	Set<String> getAttribute(String attributeName, Set<String> defaultValue);
	
	/**
	 * the list of enabled engine addons
	 * This list includes the language specific addons
	 * @return
	 */
	Collection<EngineAddonSpecificationExtension> getEngineAddonExtensions();
		
	String getDebugModelID();

	boolean getBreakStart();

	
}
