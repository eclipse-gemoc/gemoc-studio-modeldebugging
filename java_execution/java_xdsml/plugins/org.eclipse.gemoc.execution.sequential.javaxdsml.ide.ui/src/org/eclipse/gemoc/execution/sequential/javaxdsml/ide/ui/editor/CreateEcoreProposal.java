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
package org.eclipse.gemoc.execution.sequential.javaxdsml.ide.ui.editor;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecoretools.design.ui.wizard.EcoreModelerWizard;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.wizards.IWizardDescriptor;
import org.eclipse.gemoc.commons.eclipse.core.resources.FileFinderVisitor;
import org.eclipse.gemoc.commons.eclipse.core.resources.NewProjectWorkspaceListener;
import org.eclipse.gemoc.commons.eclipse.pde.manifest.ManifestChanger;
import org.eclipse.gemoc.commons.eclipse.ui.WizardFinder;
import org.eclipse.gemoc.xdsmlframework.ide.ui.Activator;

import fr.inria.diverse.melange.metamodel.melange.Language;
import fr.inria.diverse.melange.metamodel.melange.ModelTypingSpace;
import fr.inria.diverse.melange.ui.contentassist.IProposal;

public class CreateEcoreProposal implements IProposal{

	private IProject ecoreProject;
	private String packageName = "packageName";
	private String languageName = "languageName";
	
	@Override
	public String getDisplayText() {
		return "-- Create a Domain Model Project --";
	}

	@Override
	public String getReplacementText() {
		
		IWizardDescriptor descriptor = WizardFinder.findNewWizardDescriptor("org.eclipse.ecoretools.emf.design.wizardID");

		// Then if we have a wizard, open it.
		if (descriptor != null) {
			// add a listener to capture the creation of the resulting project
			NewProjectWorkspaceListener workspaceListener = new NewProjectWorkspaceListener();
			ResourcesPlugin.getWorkspace().addResourceChangeListener(
					workspaceListener);
			try {
				IWizard wizard = descriptor.createWizard();
				// this wizard need some dedicated initialization
				String newProjectName = (packageName+"."+languageName+".model").toLowerCase();
				((EcoreModelerWizard) wizard).setInitialProjectName(newProjectName);
				((EcoreModelerWizard) wizard).init(PlatformUI.getWorkbench(),null);
				WizardDialog wd = new WizardDialog(PlatformUI.getWorkbench()
						.getActiveWorkbenchWindow().getShell(), wizard);
				wd.create();
				wd.setTitle(wizard.getWindowTitle());

				int res = wd.open();
				if (res == WizardDialog.OK) {
					ResourcesPlugin.getWorkspace().removeResourceChangeListener(workspaceListener);
					ecoreProject = workspaceListener.getLastCreatedProject();
				}
			} catch (CoreException e) {
				Activator.error(e.getMessage(), e);
			} finally {
				// make sure to remove listener in all situations
				ResourcesPlugin.getWorkspace().removeResourceChangeListener(
						workspaceListener);
				if(ecoreProject != null){
					return getCreatedEcoreUri();
				}
			}
		}
		
		return ""; 
	}

	@Override
	public void configureProject(IProject project) {
		ManifestChanger manifestChanger = new ManifestChanger(project);
		try {
			manifestChanger.addPluginDependency(ecoreProject.getName());
			manifestChanger.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private String getCreatedEcoreUri(){
		FileFinderVisitor ecoreProjectVisitor = new FileFinderVisitor(
				"ecore");
		try {
			ecoreProject.accept(ecoreProjectVisitor);
			IFile ecoreIFile = ecoreProjectVisitor.getFile();
			if (ecoreIFile != null) {
				return "syntax \"platform:/resource"+ecoreIFile.getFullPath().toString()+"\"";
			}
		} catch (CoreException e) {
			Activator.error(e.getMessage(), e);
		}
		return "";
	}

	@Override
	public void configureProposal(EObject context) {
		if(context instanceof Language){
			Language lang = (Language) context;
			this.packageName = ((ModelTypingSpace)lang.eContainer()).getName();
			this.languageName = lang.getName();
		}
	}
}
