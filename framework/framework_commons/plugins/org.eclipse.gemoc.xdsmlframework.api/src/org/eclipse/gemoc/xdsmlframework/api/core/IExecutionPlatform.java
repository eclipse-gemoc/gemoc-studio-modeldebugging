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

import java.util.List;

import org.eclipse.gemoc.xdsmlframework.api.engine_addon.EngineAddonSortingRule;
import org.eclipse.gemoc.xdsmlframework.api.engine_addon.IEngineAddon;

public interface IExecutionPlatform extends IDisposable {

	/**
	 * @return The model loader used to load the model to be executed.
	 */
	IModelLoader getModelLoader();

	void addEngineAddon(IEngineAddon addon);

	void removeEngineAddon(IEngineAddon addon);

	/** 
	 * @return an iterable on the list of all addons enabled for this execution
	 */
	Iterable<IEngineAddon> getEngineAddons();
	
	/**
	 * 
	 * @param engineEvent
	 * @return an iterable on the list of all addons enabled for this execution that are sorted using EngineAddonSortingRule for the given engineEvent
	 */
	List<IEngineAddon> getSortedEngineAddons(EngineAddonSortingRule.EngineEvent engineEvent);

}
