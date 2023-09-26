/*******************************************************************************
 * Copyright (c) 2017, 2019 INRIA and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     INRIA - initial API and implementation
 *******************************************************************************/

package org.eclipse.gemoc.executionframework.engine.ui.launcher;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.debug.ui.DebugUITools;
import org.eclipse.debug.ui.ILaunchGroup;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.gemoc.commons.eclipse.messagingsystem.api.MessagingSystem;
import org.eclipse.gemoc.commons.eclipse.ui.ViewHelper;
import org.eclipse.gemoc.dsl.debug.ide.adapter.IDSLCurrentInstructionListener;
import org.eclipse.gemoc.executionframework.engine.commons.EngineContextException;
import org.eclipse.gemoc.executionframework.engine.concurrency.AbstractConcurrentExecutionEngine;
import org.eclipse.gemoc.executionframework.engine.concurrency.AbstractConcurrentModelExecutionContext;
import org.eclipse.gemoc.executionframework.engine.concurrency.ConcurrentRunConfiguration;
import org.eclipse.gemoc.executionframework.engine.concurrency.IConcurrentRunConfiguration;
import org.eclipse.gemoc.executionframework.engine.core.RunConfiguration;
import org.eclipse.gemoc.executionframework.engine.ui.Activator;
import org.eclipse.gemoc.executionframework.engine.ui.concurrency.strategy_selector.StrategySelectionView;
import org.eclipse.gemoc.executionframework.engine.ui.concurrency.views.step.LogicalStepsView;
import org.eclipse.gemoc.executionframework.extensions.sirius.services.AbstractGemocAnimatorServices;
import org.eclipse.gemoc.executionframework.extensions.sirius.services.AbstractGemocDebuggerServices;
import org.eclipse.gemoc.executionframework.ui.views.engine.EnginesStatusView;
import org.eclipse.gemoc.xdsmlframework.api.core.EngineStatus.RunStatus;
import org.eclipse.gemoc.xdsmlframework.api.core.ExecutionMode;
import org.eclipse.gemoc.xdsmlframework.api.core.IExecutionEngine;
import org.eclipse.gemoc.xdsmlframework.api.core.IExecutionPlatform;
import org.eclipse.gemoc.xdsmlframework.api.engine_addon.IEngineAddon;
import org.eclipse.gemoc.xdsmlframework.api.extensions.languages.LanguageDefinitionExtension;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;

public abstract class AbstractConcurrentLauncher<R extends IConcurrentRunConfiguration, C extends AbstractConcurrentModelExecutionContext<R, ? extends IExecutionPlatform, ? extends LanguageDefinitionExtension>>
		extends AbstractGemocLauncher<C> {

	public final static String TYPE_ID = Activator.PLUGIN_ID + ".launcher";

	private AbstractConcurrentExecutionEngine<C, R> _executionEngine;

	protected abstract AbstractConcurrentExecutionEngine<C, R> createEngine(R runConfiguration,
			ExecutionMode executionMode) throws EngineContextException, CoreException;

	protected abstract R createRunConfiguration(ILaunchConfiguration launchConfiguration) throws CoreException;

	protected abstract Set<String> getAdditionalViewsIDs();

	@Override
	public void launch(final ILaunchConfiguration configuration, final String mode, final ILaunch launch,
			IProgressMonitor monitor) throws CoreException {
		try {

			// make sure to have the engine view when starting the engine
			PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
				@Override
				public void run() {
					ViewHelper.retrieveView(EnginesStatusView.ID);
					ViewHelper.showView(LogicalStepsView.ID);
					ViewHelper.showView(StrategySelectionView.ID);
					for (String s : getAdditionalViewsIDs()) {
						ViewHelper.showView(s);
					}
				}
			});

			// We detect if we are running in debug mode or not
			ExecutionMode executionMode = null;
			if (ILaunchManager.DEBUG_MODE.equals(mode)) {
				executionMode = ExecutionMode.Animation;
			} else {
				executionMode = ExecutionMode.Run;
			}

			// We parse the run configuration
			final R runConfiguration = createRunConfiguration(configuration);

			// We stop the launch if an engine is already running for this model
			if (isEngineAlreadyRunning(runConfiguration.getExecutedModelURI())) {
				return;
			}

			_executionEngine = createEngine(runConfiguration, executionMode);
			debug("About to initialize and run " + _executionEngine.getName());

			openViewsRecommandedByAddons(runConfiguration);

			// And we start it within a dedicated job
			Job job = new Job(getDebugJobName(configuration, getFirstInstruction(configuration))) {
				@Override
				protected IStatus run(IProgressMonitor monitor) {
					// If we are debugging, we add the animator and we start
					// the execution using the super class
					// AbstractDSLLaunchConfigurationDelegateUI
					// This will start yet another job and eventually start
					// the engine
					if (ILaunchManager.DEBUG_MODE.equals(mode)) {
						IEngineAddon animator = AbstractGemocAnimatorServices.getAnimator();
						_executionEngine.getExecutionContext().getExecutionPlatform().addEngineAddon(animator);
						try {
							AbstractConcurrentLauncher.super.launch(configuration, mode, launch, monitor);
							return new Status(IStatus.OK, getPluginID(), "Execution was launched successfully");
						} catch (CoreException e) {
							e.printStackTrace();
							return new Status(IStatus.ERROR, getPluginID(), "Could not start debugger.");
						}
					}

					// If we are not debugging, we simply start the engine
					// from the current job
					else {
						_executionEngine.start();
						debug("Execution finished.");
						return new Status(IStatus.OK, getPluginID(), "Execution was launched successfully");
					}
				}
			};
			debug("Initialization done, starting engine...");
			job.schedule();

		} catch (Exception e) {
			String message = "Error occured when starting execution engine: " + e.getMessage()
					+ " (see inner exception).";
			// error(message);
			Activator.error(message, e);
			throw new CoreException(new Status(Status.ERROR, Activator.PLUGIN_ID, message, e));
		}
	}

	private boolean isEngineAlreadyRunning(URI launchedModelURI) throws CoreException {
		// make sure there is no other running engine on this model
		Collection<IExecutionEngine<?>> engines = org.eclipse.gemoc.executionframework.engine.Activator
				.getDefault().gemocRunningEngineRegistry.getRunningEngines().values();
		for (IExecutionEngine<?> engine : engines) {
			IExecutionEngine<?> observable = (IExecutionEngine<?>) engine;
			if (observable.getRunningStatus() != RunStatus.Stopped
					&& observable.getExecutionContext().getResourceModel().getURI().equals(launchedModelURI)) {
				String message = "An engine is already running on this model, please stop it first";
				warn(message);
				return true;
			}
		}
		return false;
	}

	protected void debug(String message) {
		getMessagingSystem().debug(message, getPluginID());
	}

	protected void info(String message) {
		getMessagingSystem().info(message, getPluginID());
	}

	protected void warn(final String message) {
		getMessagingSystem().warn(message, getPluginID());
		PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
			@Override
			public void run() {
				MessageDialog.openWarning(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),
						"GEMOC Engine Launcher", message);
			}
		});
	}

	protected void error(final String message) {
		getMessagingSystem().error(message, getPluginID());
		PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
			@Override
			public void run() {
				MessageDialog.openError(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),
						"GEMOC Engine Launcher", message);
			}
		});
	}

	private MessagingSystem getMessagingSystem() {
		return Activator.getDefault().getMessaggingSystem();
	}

	@Override
	protected String getLaunchConfigurationTypeID() {
		return TYPE_ID;
	}

	@Override
	protected EObject getFirstInstruction(ISelection selection) {
		return EcorePackage.eINSTANCE;
	}

	@Override
	protected EObject getFirstInstruction(IEditorPart editor) {
		return EcorePackage.eINSTANCE;
	}

	@Override
	protected EObject getFirstInstruction(ILaunchConfiguration configuration) {
		return EcorePackage.eINSTANCE;
	}

	@Override
	protected String getDebugTargetName(ILaunchConfiguration configuration, EObject firstInstruction) {
		return "Gemoc debug target";
	}

	@Override
	protected List<IDSLCurrentInstructionListener> getCurrentInstructionListeners() {
		List<IDSLCurrentInstructionListener> result = super.getCurrentInstructionListeners();
		result.add(AbstractGemocDebuggerServices.LISTENER);
		return result;
	}

	@Override
	protected String getDebugJobName(ILaunchConfiguration configuration, EObject firstInstruction) {
		return "Gemoc Concurrent debug job";
	}

	@Override
	protected String getPluginID() {
		return Activator.PLUGIN_ID;
	}

	@Override
	public String getModelIdentifier() {
		if (_executionEngine instanceof AbstractConcurrentExecutionEngine)
			return Activator.PLUGIN_ID + ".debugModel";
		else
			return MODEL_ID;
	}

	@Override
	protected ILaunchConfiguration[] createLaunchConfiguration(IResource file, EObject firstInstruction, String mode)
			throws CoreException {
		ILaunchConfiguration[] launchConfigs = super.createLaunchConfiguration(file, firstInstruction, mode);

		if (launchConfigs.length == 1) {
			// open configuration for further editing
			if (launchConfigs[0] instanceof ILaunchConfigurationWorkingCopy) {
				ILaunchConfigurationWorkingCopy configuration = (ILaunchConfigurationWorkingCopy) launchConfigs[0];

				String selectedLanguage = configuration.getAttribute(RunConfiguration.LAUNCH_SELECTED_LANGUAGE, "");
				if (selectedLanguage.equals("")) {

					// TODO try to infer possible language and other attribute
					// from project content and environment
					configuration.setAttribute(ConcurrentRunConfiguration.LAUNCH_SELECTED_DECIDER,
							ConcurrentRunConfiguration.DECIDER_ASKUSER_STEP_BY_STEP);
					final ILaunchGroup group = DebugUITools.getLaunchGroup(configuration, mode);
					if (group != null) {
						ILaunchConfiguration savedLaunchConfig = configuration.doSave();
						// open configuration for user validation and inputs
						DebugUITools.openLaunchConfigurationDialogOnGroup(
								PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),
								new StructuredSelection(savedLaunchConfig), group.getIdentifier(), null);
						// DebugUITools.openLaunchConfigurationDialog(PlatformUI.getWorkbench()
						// .getActiveWorkbenchWindow().getShell(),
						// savedLaunchConfig, group.getIdentifier(), null);
					}
				}
			}
		}
		return launchConfigs;

	}

	@Override
	public IExecutionEngine<C> getExecutionEngine() {
		return _executionEngine;
	}

}
