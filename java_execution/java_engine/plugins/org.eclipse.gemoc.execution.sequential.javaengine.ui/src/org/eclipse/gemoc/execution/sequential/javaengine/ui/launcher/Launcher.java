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
package org.eclipse.gemoc.execution.sequential.javaengine.ui.launcher;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gemoc.commons.eclipse.messagingsystem.api.MessagingSystem;
import org.eclipse.gemoc.commons.eclipse.ui.ViewHelper;
import org.eclipse.gemoc.execution.sequential.javaengine.PlainK3ExecutionEngine;
import org.eclipse.gemoc.execution.sequential.javaengine.SequentialModelExecutionContext;
import org.eclipse.gemoc.execution.sequential.javaengine.ui.Activator;
import org.eclipse.gemoc.executionframework.engine.commons.EngineContextException;
import org.eclipse.gemoc.executionframework.engine.commons.ModelExecutionContext;
import org.eclipse.gemoc.executionframework.engine.ui.commons.RunConfiguration;
import org.eclipse.gemoc.executionframework.engine.ui.launcher.AbstractSequentialGemocLauncher;
import org.eclipse.gemoc.executionframework.ui.views.engine.EnginesStatusView;
import org.eclipse.gemoc.trace.commons.model.launchconfiguration.LaunchConfiguration;
import org.eclipse.gemoc.trace.commons.model.launchconfiguration.LaunchConfigurationParameter;
import org.eclipse.gemoc.trace.commons.model.launchconfiguration.LaunchconfigurationPackage;
import org.eclipse.gemoc.xdsmlframework.api.core.ExecutionMode;
import org.eclipse.gemoc.xdsmlframework.api.core.IExecutionEngine;
import org.eclipse.gemoc.xdsmlframework.api.core.IRunConfiguration;

public class Launcher extends AbstractSequentialGemocLauncher {

	public final static String TYPE_ID = Activator.PLUGIN_ID + ".launcher";

	@Override
	protected IExecutionEngine createExecutionEngine(RunConfiguration runConfiguration, ExecutionMode executionMode)
			throws CoreException, EngineContextException {
		// create and initialize engine
		IExecutionEngine executionEngine = new PlainK3ExecutionEngine();
		ModelExecutionContext executioncontext = new SequentialModelExecutionContext(runConfiguration, executionMode);
		executioncontext.getExecutionPlatform().getModelLoader().setProgressMonitor(this.launchProgressMonitor);
		executioncontext.initializeResourceModel();
		executionEngine.initialize(executioncontext);
		return executionEngine;
	}

	@Override
	protected String getLaunchConfigurationTypeID() {
		return TYPE_ID;
	}

	@Override
	protected String getDebugJobName(ILaunchConfiguration configuration, EObject firstInstruction) {
		return "Gemoc debug job";
	}

	@Override
	protected String getPluginID() {
		return Activator.PLUGIN_ID;
	}

	@Override
	public String getModelIdentifier() {
		return Activator.DEBUG_MODEL_ID;
	}

	@Override
	protected void prepareViews() {
		ViewHelper.retrieveView(EnginesStatusView.ID);
	}

	@Override
	protected RunConfiguration parseLaunchConfiguration(ILaunchConfiguration configuration) throws CoreException {
		return new RunConfiguration(configuration);
	}

	@Override
	protected void error(String message, Exception e) {
		Activator.error(message, e);
	}

	@Override
	protected MessagingSystem getMessagingSystem() {
		return Activator.getDefault().getMessaggingSystem();
	}

	@Override
	protected void setDefaultsLaunchConfiguration(ILaunchConfigurationWorkingCopy configuration) {

	}
	
	@Override
	public Map<String, Object> parseLaunchConfiguration(LaunchConfiguration launchConfiguration) {
		Map<String, Object> attributes = new HashMap<>();
		for (LaunchConfigurationParameter param : launchConfiguration.getParameters()) {
			switch (param.eClass().getClassifierID()) {
				case LaunchconfigurationPackage.LANGUAGE_NAME_PARAMETER: {
					attributes.put(IRunConfiguration.LAUNCH_SELECTED_LANGUAGE, param.getValue());
				}
				case LaunchconfigurationPackage.MODEL_URI_PARAMETER: {
					attributes.put("Resource", param.getValue());
				}
				case LaunchconfigurationPackage.ANIMATOR_URI_PARAMETER: {
					attributes.put("airdResource", param.getValue());
				}
				case LaunchconfigurationPackage.ENTRY_POINT_PARAMETER: {
					attributes.put(IRunConfiguration.LAUNCH_METHOD_ENTRY_POINT, param.getValue());
				}
				case LaunchconfigurationPackage.MODEL_ROOT_PARAMETER: {
					attributes.put(IRunConfiguration.LAUNCH_MODEL_ENTRY_POINT, param.getValue());
				}
				case LaunchconfigurationPackage.INITIALIZATION_METHOD_PARAMETER: {
					attributes.put(IRunConfiguration.LAUNCH_INITIALIZATION_METHOD, param.getValue());
				}
				case LaunchconfigurationPackage.INITIALIZATION_ARGUMENTS_PARAMETER: {
					attributes.put(IRunConfiguration.LAUNCH_INITIALIZATION_ARGUMENTS, param.getValue());
				}
				case LaunchconfigurationPackage.ADDON_EXTENSION_PARAMETER: {
					attributes.put(param.getValue(), true);
				}
			}
		}
		return attributes;
	}


}
