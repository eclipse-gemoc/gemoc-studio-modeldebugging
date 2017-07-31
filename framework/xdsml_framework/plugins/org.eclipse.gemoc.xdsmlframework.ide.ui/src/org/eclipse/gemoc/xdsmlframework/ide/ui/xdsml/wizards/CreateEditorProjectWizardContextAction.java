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

import java.util.ArrayList;
import java.util.Iterator;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.WizardDialog;
//import org.eclipse.sirius.editor.tools.internal.wizards.ViewpointSpecificationProjectWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWizard;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.wizards.IWizardDescriptor;
import org.eclipse.gemoc.commons.eclipse.core.resources.FileFinderVisitor;
import org.eclipse.gemoc.commons.eclipse.core.resources.NewProjectWorkspaceListener;
import org.eclipse.gemoc.commons.eclipse.ui.WizardFinder;
import org.eclipse.gemoc.xdsmlframework.extensions.sirius.wizards.NewGemocSiriusProjectWizard;
import org.eclipse.gemoc.xdsmlframework.ide.ui.Activator;

//import org.eclipse.emf.ecoretools.design.wizard.EcoreModelerWizard;

/**
 * This class is both a context for the wizard and a Command that will be
 * executed
 * 
 * @author dvojtise
 *
 */
public class CreateEditorProjectWizardContextAction {

	public enum CreateEditorProjectAction {
		CREATE_NEW_EMFTREE_PROJECT, CREATE_NEW_XTEXT_PROJECT, CREATE_NEW_SIRIUS_PROJECT, SELECT_EXISTING_EMFTREE_PROJECT, SELECT_EXISTING_XTEXT_PROJECT, SELECT_EXISTING_OD_PROJECT
	};

	public CreateEditorProjectAction actionToExecute = CreateEditorProjectAction.CREATE_NEW_EMFTREE_PROJECT;

	// one of these must be set, depending on it it will work on the file or
	// directly in the model
	protected IProject gemocLanguageIProject = null;
	protected IProject createdProject = null;

	public CreateEditorProjectWizardContextAction(
			IProject updatedGemocLanguageProject) {
		gemocLanguageIProject = updatedGemocLanguageProject;
	}

	

	public void execute() {
		switch (actionToExecute) {
		case CREATE_NEW_EMFTREE_PROJECT:
			createNewEMFTreeProject();
			break;
		case CREATE_NEW_XTEXT_PROJECT:
			createNewXTextProject();
			break;

		case CREATE_NEW_SIRIUS_PROJECT:
			createNewODProject();
			break;
		case SELECT_EXISTING_EMFTREE_PROJECT:
			// maybe we should do something in the melange file ?
			break;
		case SELECT_EXISTING_XTEXT_PROJECT:
			// maybe we should do something in the melange file ?
			break;
		case SELECT_EXISTING_OD_PROJECT:
			// maybe we should do something in the melange file ?
			break;
		default:
			break;
		}

	}

	protected void createNewEMFTreeProject() {
		// launch the appropriate wizard

		// "org.eclipse.emf.importer.ui.EMFProjectWizard" = create EMFProject
		// from existing Ecore file
		/*
		 * IWizardDescriptor descriptor = PlatformUI .getWorkbench()
		 * .getNewWizardRegistry() .findWizard(
		 * "fr.obeo.mda.pim.ecore.design.wizard");
		 * 
		 * // Then if we have a wizard, open it. if (descriptor != null) { //
		 * add a listener to capture the creation of the resulting project
		 * NewProjectWorkspaceListener workspaceListener = new
		 * NewProjectWorkspaceListener();
		 * ResourcesPlugin.getWorkspace().addResourceChangeListener
		 * (workspaceListener); try { IWizard wizard; wizard =
		 * descriptor.createWizard(); // this wizard need some dedicated
		 * initialization ((EcoreModelerWizard
		 * )wizard).init(PlatformUI.getWorkbench(), (IStructuredSelection)
		 * PlatformUI
		 * .getWorkbench().getActiveWorkbenchWindow().getSelectionService
		 * ().getSelection());
		 * //((EcoreModelWizard)wizard).init(PlatformUI.getWorkbench(),
		 * (IStructuredSelection) selection); WizardDialog wd = new
		 * WizardDialog(
		 * PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),
		 * wizard); wd.create(); wd.setTitle(wizard.getWindowTitle());
		 * 
		 * int res = wd.open(); if(res == WizardDialog.OK){
		 * ResourcesPlugin.getWorkspace
		 * ().removeResourceChangeListener(workspaceListener); IProject
		 * createdProject = workspaceListener.getLastCreatedProject(); // update
		 * the project configuration model if(createdProject != null){
		 * addEMFProjectToConf(createdProject.getName()); } else{
		 * addEMFProjectToConf(""); } } } catch (CoreException e) {
		 * Activator.error(e.getMessage(), e); } finally{ // make sure to remove
		 * listener in all situations
		 * ResourcesPlugin.getWorkspace().removeResourceChangeListener
		 * (workspaceListener); } }
		 */
		MessageDialog.openWarning(PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getShell(),
				"Gemoc Language Workbench UI", "Action not implemented yet");
	}

	protected void createNewXTextProject() {
		/*
		 * MessageDialog.openWarning(
		 * PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),
		 * "Gemoc Language Workbench UI",
		 * "Action not completly implemented yet");
		 */
		// create xtext project from existing ecore model
		// wizard id =
		// org.eclipse.xtext.xtext.ui.wizard.ecore2xtext.NewXtextProjectFromEcoreWizard
		// launch the appropriate wizard

		IWizardDescriptor descriptor = WizardFinder
				.findNewWizardDescriptor("org.eclipse.xtext.xtext.ui.wizard.ecore2xtext.NewXtextProjectFromEcoreWizard");
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
				// ((EcoreModelerWizard )wizard).init(PlatformUI.getWorkbench(),
				// (IStructuredSelection)
				// PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService().getSelection());
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
					ArrayList<IProject> newlyCreatedProjects = workspaceListener
							.getNewlyCreatedProjects();
					// find the created project with xtext files in it
					FileFinderVisitor fileFinder = new FileFinderVisitor(
							"xtext");
					for (Iterator<IProject> iterator = newlyCreatedProjects
							.iterator(); iterator.hasNext();) {
						IProject iProject = (IProject) iterator.next();
						iProject.accept(fileFinder);
						if (fileFinder.getFile() != null) {
							createdProject = iProject;
							break;
						}
					}
					// update the project configuration model
					if (createdProject != null) {						
						// maybe we should do something in the melange file ?
					} else {
						Activator
								.error("not able to detect which project was created by wizard",
										null);
					}
				}
			} catch (CoreException e) {
				Activator.error(e.getMessage(), e);
			} finally {
				// make sure to remove listener in all situations
				ResourcesPlugin.getWorkspace().removeResourceChangeListener(
						workspaceListener);
			}
		} else {
			Activator
					.error("wizard with id=org.eclipse.xtext.xtext.ui.wizard.ecore2xtext.NewXtextProjectFromEcoreWizard not found",
							null);
		}
	}

	protected void createNewODProject() {
		final IWizardDescriptor descriptor = WizardFinder
				.findNewWizardDescriptor("org.eclipse.gemoc.xdsmlframework.extensions.sirius.wizards.NewGemocSiriusProjectWizard");
		// Then if we have a wizard, open it.
		if (descriptor != null) {
			NewProjectWorkspaceListener workspaceListener = new NewProjectWorkspaceListener();
			ResourcesPlugin.getWorkspace().addResourceChangeListener(
					workspaceListener);
			try {
				IWorkbenchWizard wizard;
				wizard = descriptor.createWizard();
				 ((NewGemocSiriusProjectWizard)wizard).setInitialProjectName(MelangeXDSMLProjectHelper
						                                                .baseProjectName(gemocLanguageIProject));

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
					createdProject = workspaceListener
							.getLastCreatedProject();
					// update the project configuration model
					if (createdProject != null) {
						// maybe we should do something in the melange file ?
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
					.error("wizard with id=org.eclipse.sirius.ui.specificationproject.wizard not found",
							null);
		}
	}

	
	
	public String getSiriusPath(){
		if(createdProject != null){
			FileFinderVisitor odesignProjectVisitor = new FileFinderVisitor(
					"odesign");
			try {
				createdProject.accept(odesignProjectVisitor);
				IFile odesignIFile = odesignProjectVisitor.getFile();
				if (odesignIFile != null) {
					return "/"+createdProject.getName()+"/"+odesignIFile.getProjectRelativePath().toString();
				}
			} catch (CoreException e) {
				Activator.error(e.getMessage(), e);
			}
		}
		return "";
	}

	public String getXtextPath(){
		if(createdProject != null){
			FileFinderVisitor odesignProjectVisitor = new FileFinderVisitor(
					"xtext");
			try {
				createdProject.accept(odesignProjectVisitor);
				IFile odesignIFile = odesignProjectVisitor.getFile();
				if (odesignIFile != null) {
					return "/"+createdProject.getName()+"/"+odesignIFile.getProjectRelativePath().toString();
				}
			} catch (CoreException e) {
				Activator.error(e.getMessage(), e);
			}
		}
		return "";
	}
}
