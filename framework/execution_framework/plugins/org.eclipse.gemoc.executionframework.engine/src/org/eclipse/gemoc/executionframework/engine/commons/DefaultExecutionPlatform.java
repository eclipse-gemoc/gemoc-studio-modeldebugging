/*******************************************************************************
 * Copyright (c) 2016 Inria and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Inria - initial API and implementation
 *******************************************************************************/
package org.eclipse.gemoc.executionframework.engine.commons;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.gemoc.executionframework.engine.Activator;
import org.eclipse.gemoc.xdsmlframework.api.core.IExecutionPlatform;
import org.eclipse.gemoc.xdsmlframework.api.core.IModelLoader;
import org.eclipse.gemoc.xdsmlframework.api.core.IRunConfiguration;
import org.eclipse.gemoc.xdsmlframework.api.engine_addon.IEngineAddon;
import org.eclipse.gemoc.xdsmlframework.api.extensions.engine_addon.EngineAddonSpecificationExtension;
import org.eclipse.gemoc.xdsmlframework.api.extensions.languages.LanguageDefinitionExtension;

public class DefaultExecutionPlatform implements IExecutionPlatform {

	protected IModelLoader _modelLoader;
	protected Collection<IEngineAddon> _addons;

	public DefaultExecutionPlatform(LanguageDefinitionExtension _languageDefinition, IRunConfiguration runConfiguration) throws CoreException {
		_modelLoader = _languageDefinition.instanciateModelLoader();
		_addons = new ArrayList<IEngineAddon>();

		// instanciate addons (this includes language specific addons)
		for (EngineAddonSpecificationExtension extension : runConfiguration.getEngineAddonExtensions()) {
			Activator.getDefault().debug("Enabled addon: "+extension.getName());
			addEngineAddon(extension.instanciateComponent());
		}
	}

	@Override
	public IModelLoader getModelLoader() {
		return _modelLoader;
	}

	@Override
	public Iterable<IEngineAddon> getEngineAddons() {
		synchronized (_addonLock) {
			return Collections.unmodifiableCollection(new ArrayList<IEngineAddon>(_addons));
		}
	}

	@Override
	public void dispose() {
		_addons.clear();
	}

	private Object _addonLock = new Object();

	@Override
	public void addEngineAddon(IEngineAddon addon) {
		synchronized (_addonLock) {
			_addons.add(addon);
		}
	}

	@Override
	public void removeEngineAddon(IEngineAddon addon) {
		synchronized (_addonLock) {
			_addons.remove(addon);
		}
	}

}
