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
package org.eclipse.gemoc.xdsmlframework.ide.ui.xdsml.wizards;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWizard;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.wizards.IWizardDescriptor;
import org.eclipse.gemoc.commons.eclipse.core.resources.NewProjectWorkspaceListener;
import org.eclipse.gemoc.commons.eclipse.ui.WizardFinder;
import org.eclipse.gemoc.xdsmlframework.extensions.sirius.wizards.NewGemocDebugRepresentationWizard;
import org.eclipse.gemoc.xdsmlframework.ide.ui.Activator;

import fr.inria.diverse.melange.metamodel.melange.Language;

//import org.eclipse.emf.ecoretools.design.wizard.EcoreModelerWizard;

/**
 * This class is both a context for the wizard and a Command that will be
 * executed
 * 
 * @author dvojtise
 *
 */
public class CreateAnimatorProjectWizardContextAction {

	public enum CreateAnimatorProjectAction {
		CREATE_NEW_SIRIUS_PROJECT, SELECT_EXISTING_OD_PROJECT
	};

	public CreateAnimatorProjectAction actionToExecute = CreateAnimatorProjectAction.CREATE_NEW_SIRIUS_PROJECT;

	// one of these must be set, depending on it it will work on the file or
	// directly in the model
	protected IProject gemocLanguageIProject = null;	
	protected Language melangeLanguage = null;

	public CreateAnimatorProjectWizardContextAction(
			IProject updatedGemocLanguageProject) {
		gemocLanguageIProject = updatedGemocLanguageProject;
	}
	
	public CreateAnimatorProjectWizardContextAction(
			IProject updatedGemocLanguageProject,
			Language language) {
		gemocLanguageIProject = updatedGemocLanguageProject;
		melangeLanguage = language;
	}

	

	public void execute() {
		switch (actionToExecute) {

		case CREATE_NEW_SIRIUS_PROJECT:
			createNewODProject();
			break;

		case SELECT_EXISTING_OD_PROJECT:
		//	selectExistingSiriusProject();
			// may be we should do something in the Melange file ?
			break;
		default:
			break;
		}

	}

	protected void createNewODProject() {
		final IWizardDescriptor descriptor = WizardFinder
				.findNewWizardDescriptor(org.eclipse.gemoc.xdsmlframework.extensions.sirius.Activator.PLUGIN_ID
						+ ".wizards.NewGemocDebugRepresentationWizard");
		// Then if we have a wizard, open it.
		if (descriptor != null) {
			NewProjectWorkspaceListener workspaceListener = new NewProjectWorkspaceListener();
			ResourcesPlugin.getWorkspace().addResourceChangeListener(
					workspaceListener);
			try {
				IWorkbenchWizard wizard;
				wizard = descriptor.createWizard();
				((NewGemocDebugRepresentationWizard) wizard).setInitialProjectName(gemocLanguageIProject.getName());
				if(melangeLanguage != null){
					((NewGemocDebugRepresentationWizard) wizard)
					.setInitialLanguageName(melangeLanguage.getName());
				}
				IWorkbench workbench = PlatformUI.getWorkbench();
				wizard.init(workbench, null);
				WizardDialog wd = new WizardDialog(workbench
						.getActiveWorkbenchWindow().getShell(), wizard);
				wd.create();
				wd.setTitle(wizard.getWindowTitle());
				int res = wd.open();
				if (res == WizardDialog.OK) {
					ResourcesPlugin.getWorkspace()
							.removeResourceChangeListener(workspaceListener);
					IProject createdProject = workspaceListener
							.getLastCreatedProject();
					// update the project configuration model
					if (createdProject != null) {
						/* SiriusAnimatorProject animatorProject = Xdsml_baseFactoryImpl.eINSTANCE
								.createSiriusAnimatorProject();
						animatorProject
								.setProjectName(createdProject.getName());
						addOrUpdateProjectToConf(animatorProject); */
						// DVK may be we should update something the melange files ?
					} else {
						Activator
								.error("not able to detect which project was created by wizard",
										null);
					}
				}
			} catch (CoreException e) {
				Activator.error(e.getMessage(), e);
			} finally {
				ResourcesPlugin.getWorkspace().removeResourceChangeListener(
						workspaceListener);
			}
		} else {
			Activator
					.error("wizard with id=org.eclipse.gemoc.xdsmlframework.extensions.sirius.wizards.NewGemocDebugRepresentationWizard not found",
							null);
		}
	}

	/* protected void selectExistingSiriusProject() {
		// launch the appropriate wizard
		// TODO filter only projects related to the current domain model
		SelectAnyIProjectDialog dialog = new SelectODesignIProjectDialog(
				PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell());
		int res = dialog.open();
		if (res == WizardDialog.OK) {
			// update the project model
			String projectName = ((IResource) dialog.getResult()[0]).getName();
			AnimatorProject animatorProject = Xdsml_baseFactoryImpl.eINSTANCE
					.createSiriusAnimatorProject();
			animatorProject.setProjectName(projectName);
			// TODO detection of the current extension
			addOrUpdateProjectToConf(animatorProject);

		}
	} */


}
