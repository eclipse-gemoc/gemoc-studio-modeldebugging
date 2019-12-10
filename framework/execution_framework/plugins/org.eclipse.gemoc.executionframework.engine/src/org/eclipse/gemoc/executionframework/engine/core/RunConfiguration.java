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
package org.eclipse.gemoc.executionframework.engine.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map.Entry;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.emf.common.util.URI;
import org.eclipse.gemoc.dsl.debug.ide.launch.AbstractDSLLaunchConfigurationDelegate;
import org.eclipse.gemoc.xdsmlframework.api.core.IRunConfiguration;
import org.eclipse.gemoc.xdsmlframework.api.extensions.engine_addon.EngineAddonSpecificationExtension;
import org.eclipse.gemoc.xdsmlframework.api.extensions.engine_addon.EngineAddonSpecificationExtensionPoint;

public class RunConfiguration implements IRunConfiguration {

	protected ILaunchConfiguration _launchConfiguration;

	public RunConfiguration(ILaunchConfiguration launchConfiguration) throws CoreException {
		_launchConfiguration = launchConfiguration;
		extractInformation();
	}

	protected void extractInformation() throws CoreException {
		_languageName = getAttribute(LAUNCH_SELECTED_LANGUAGE, "");
		_modelURI = URI.createPlatformResourceURI(getAttribute(AbstractDSLLaunchConfigurationDelegate.RESOURCE_URI, ""), true);
		String animatorURIAsString = getAttribute("airdResource", "");
		if (animatorURIAsString != null && !animatorURIAsString.equals("")) {
			_animatorURI = URI.createPlatformResourceURI(animatorURIAsString, true);
			_animationDelay = getAttribute(LAUNCH_DELAY, 0);
		}
		// TODO
		// _deadlockDetectionDepth = getAttribute(LAUNCH_DEADLOCK_DETECTION_DEPTH, 10);
		_melangeQuery = getAttribute(LAUNCH_MELANGE_QUERY, "");

		for (EngineAddonSpecificationExtension extension : EngineAddonSpecificationExtensionPoint.getSpecifications()) {
			String extensionName = extension.getName() != null ? extension.getName() : extension.getId();
			_engineAddonExtensions.put(extension, getAttribute(extensionName, false));
		}

		_breakStart = getAttribute(LAUNCH_BREAK_START, Boolean.FALSE);
		_debugModelID = getAttribute(DEBUG_MODEL_ID, ".debugModel");
	}

	protected String getAttribute(String attributeName, String defaultValue) throws CoreException {
		return _launchConfiguration.getAttribute(attributeName, defaultValue);
	}

	protected Integer getAttribute(String attributeName, Integer defaultValue) throws CoreException {
		return _launchConfiguration.getAttribute(attributeName, defaultValue);
	}

	protected Boolean getAttribute(String attributeName, Boolean defaultValue) throws CoreException {
		return _launchConfiguration.getAttribute(attributeName, defaultValue);
	}

	private int _animationDelay = 0;

	public int getAnimationDelay() {
		return _animationDelay;
	}

	private URI _modelURI;

	@Override
	public URI getExecutedModelURI() {
		return _modelURI;
	}

	private String _melangeQuery = "";

	@Override
	public String getMelangeQuery() {
		return _melangeQuery;
	}

	@Override
	public URI getExecutedModelAsMelangeURI() {
		if (_melangeQuery.isEmpty())
			return _modelURI;
		String melangeURIString = _modelURI.toString().replace("platform:/", "melange:/") + _melangeQuery;
		return URI.createURI(melangeURIString);
	}

	private URI _animatorURI;

	@Override
	public URI getAnimatorURI() {
		return _animatorURI;
	}

	private HashMap<EngineAddonSpecificationExtension, Boolean> _engineAddonExtensions = new HashMap<>();

	@Override
	public Collection<EngineAddonSpecificationExtension> getEngineAddonExtensions() {
		ArrayList<EngineAddonSpecificationExtension> result = new ArrayList<EngineAddonSpecificationExtension>();
		for (Entry<EngineAddonSpecificationExtension, Boolean> e : _engineAddonExtensions.entrySet()) {
			if (e.getValue())
				result.add(e.getKey());
		}
		return result;
	}

	private String _languageName;

	@Override
	public String getLanguageName() {
		return _languageName;
	}

	// TODO
	// private int _deadlockDetectionDepth = 0;

	// @Override
	// public int getDeadlockDetectionDepth() {
	// return _deadlockDetectionDepth;
	// }

	private boolean _breakStart;

	@Override
	public boolean getBreakStart() {
		return _breakStart;
	}

	private String _debugModelID;

	@Override
	public String getDebugModelID() {
		return _debugModelID;
	}

}
