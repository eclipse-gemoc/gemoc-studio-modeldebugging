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
package org.eclipse.gemoc.execution.sequential.javaengine;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.gemoc.executionframework.engine.core.RunConfiguration;

public class K3RunConfiguration extends RunConfiguration implements IK3RunConfiguration {

	public K3RunConfiguration(ILaunchConfiguration launchConfiguration) throws CoreException {
		super(launchConfiguration);
	}

	protected void extractInformation() throws CoreException {
		super.extractInformation();
		_methodEntryPoint = getAttribute(LAUNCH_METHOD_ENTRY_POINT, "");
		_modelEntryPoint = getAttribute(LAUNCH_MODEL_ENTRY_POINT, "");
		_modelInitializationMethod = getAttribute(LAUNCH_INITIALIZATION_METHOD, "");
		_modelInitializationArguments = getAttribute(LAUNCH_INITIALIZATION_ARGUMENTS, "");
	}

	private String _methodEntryPoint;
	private String _modelEntryPoint;
	private String _modelInitializationMethod;
	private String _modelInitializationArguments;

	@Override
	public String getExecutionEntryPoint() {
		return _methodEntryPoint;
	}

	@Override
	public String getModelEntryPoint() {
		return _modelEntryPoint;
	}

	@Override
	public String getModelInitializationMethod() {
		return _modelInitializationMethod;
	}

	@Override
	public String getModelInitializationArguments() {
		return _modelInitializationArguments;
	}

}
