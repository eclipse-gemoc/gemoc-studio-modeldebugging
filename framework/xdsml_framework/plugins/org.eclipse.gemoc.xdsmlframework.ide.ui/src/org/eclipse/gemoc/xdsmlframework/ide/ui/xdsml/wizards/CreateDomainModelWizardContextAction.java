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

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecoretools.design.ui.wizard.EcoreModelerWizard;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.wizards.IWizardDescriptor;
import org.eclipse.gemoc.commons.eclipse.core.resources.FileFinderVisitor;
import org.eclipse.gemoc.commons.eclipse.core.resources.NewProjectWorkspaceListener;
import org.eclipse.gemoc.commons.eclipse.ui.WizardFinder;
import org.eclipse.gemoc.xdsmlframework.ide.ui.Activator;
import org.eclipse.gemoc.xdsmlframework.ui.utils.dialogs.SelectEMFIProjectDialog;

import org.eclipse.gemoc.commons.eclipse.pde.manifest.ManifestChanger;

/**
 * This class is both a context for the wizard and a Command that will be
 * executed
 * 
 * @author dvojtise
 *
 */
public class CreateDomainModelWizardContextAction {

	public enum CreateDomainModelAction {
		CREATE_NEW_EMF_PROJECT, SELECT_EXISTING_EMF_PROJECT
	};

	public CreateDomainModelAction actionToExecute = CreateDomainModelAction.CREATE_NEW_EMF_PROJECT;

	// one of these must be set, depending on it it will work on the file or
	// directly in the model
	protected IProject gemocLanguageIProject = null;
	//protected LanguageDefinition gemocLanguageModel = null;
	private IProject createdProject = null;

	public CreateDomainModelWizardContextAction(
			IProject updatedGemocLanguageProject) {
		gemocLanguageIProject = updatedGemocLanguageProject;
	}

	/*public CreateDomainModelWizardContextAction(
			IProject updatedGemocLanguageProject,
			LanguageDefinition rootModelElement) {
		gemocLanguageIProject = updatedGemocLanguageProject;
		gemocLanguageModel = rootModelElement;
	}*/

	public void execute() {
		switch (actionToExecute) {
		case CREATE_NEW_EMF_PROJECT:
			createNewEMFProject();
			break;
		case SELECT_EXISTING_EMF_PROJECT:
			selectExistingEMFProject();
			break;

		default:
			break;
		}

	}

	protected void createNewEMFProject() {
		// launch the appropriate wizard

		// "org.eclipse.emf.importer.ui.EMFProjectWizard" = create EMFProject
		// from existing Ecore file

		IWizardDescriptor descriptor = WizardFinder
				.findNewWizardDescriptor("org.eclipse.ecoretools.emf.design.wizardID");

		// Then if we have a wizard, open it.
		if (descriptor != null) {
			// add a listener to capture the creation of the resulting project
			NewProjectWorkspaceListener workspaceListener = new NewProjectWorkspaceListener();
			ResourcesPlugin.getWorkspace().addResourceChangeListener(
					workspaceListener);
			try {
				IWizard wizard;
				wizard = descriptor.createWizard();
				// this wizard need some dedicated initialization
				((EcoreModelerWizard) wizard)
						.setInitialProjectName(MelangeXDSMLProjectHelper
								.baseProjectName(gemocLanguageIProject)
								+ ".model");
				((EcoreModelerWizard) wizard).init(PlatformUI.getWorkbench(),
						(IStructuredSelection) PlatformUI.getWorkbench()
								.getActiveWorkbenchWindow()
								.getSelectionService().getSelection());
				// ((EcoreModelWizard)wizard).init(PlatformUI.getWorkbench(),
				// (IStructuredSelection) selection);
				WizardDialog wd = new WizardDialog(PlatformUI.getWorkbench()
						.getActiveWorkbenchWindow().getShell(), wizard);
				wd.create();
				wd.setTitle(wizard.getWindowTitle());

				int res = wd.open();
				if (res == WizardDialog.OK) {
					ResourcesPlugin.getWorkspace()
							.removeResourceChangeListener(workspaceListener);
					createdProject = workspaceListener
							.getLastCreatedProject();
					// update the project configuration model
					if (createdProject != null) {
						addEMFProjectToConf(createdProject);
					}
				}
			} catch (CoreException e) {
				Activator.error(e.getMessage(), e);
			} finally {
				// make sure to remove listener in all situations
				ResourcesPlugin.getWorkspace().removeResourceChangeListener(
						workspaceListener);
			}
		}
	}

	protected void selectExistingEMFProject() {
		// launch the appropriate wizard
		SelectEMFIProjectDialog dialog = new SelectEMFIProjectDialog(PlatformUI
				.getWorkbench().getActiveWorkbenchWindow().getShell());
		int res = dialog.open();
		if (res == WizardDialog.OK) {
			// update the project model
			addEMFProjectToConf(((IProject) dialog.getResult()[0]));
		}
	}

	protected void addEMFProjectToConf(IProject emfProject) {
		if (gemocLanguageIProject != null) {
			addEMFProjectToConf(emfProject, gemocLanguageIProject);
		}		
	}

	protected void addEMFProjectToConf(IProject emfProject,
			IProject gemocProject) {
		ManifestChanger manifestChanger = new ManifestChanger(gemocProject);
		try {
			manifestChanger.addPluginDependency(emfProject.getName());
			manifestChanger.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public String getCreatedEcoreUri(){
		if(createdProject != null){
			FileFinderVisitor ecoreProjectVisitor = new FileFinderVisitor(
					"ecore");
			try {
				createdProject.accept(ecoreProjectVisitor);
				IFile ecoreIFile = ecoreProjectVisitor.getFile();
				if (ecoreIFile != null) {
					return "platform:/resource"+ecoreIFile.getFullPath().toString();
				}
			} catch (CoreException e) {
				Activator.error(e.getMessage(), e);
			}
		}
		return "";
	}

}
