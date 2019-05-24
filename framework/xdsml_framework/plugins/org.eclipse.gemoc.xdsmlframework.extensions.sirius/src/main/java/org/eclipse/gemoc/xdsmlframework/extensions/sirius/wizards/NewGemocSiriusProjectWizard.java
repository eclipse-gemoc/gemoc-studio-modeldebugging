/*******************************************************************************
 * Copyright (c) 2017 Inria and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Inria - initial API and implementation
 *******************************************************************************/
package org.eclipse.gemoc.xdsmlframework.extensions.sirius.wizards;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.gemoc.commons.eclipse.pde.wizards.pages.pde.AbstractNewProjectWizardWithTemplates;
import org.eclipse.gemoc.commons.eclipse.pde.wizards.pages.pde.ui.IProjectContentWizard;
import org.eclipse.gemoc.commons.eclipse.pde.wizards.pages.pde.ui.ProjectTemplateApplicationOperation;
import org.eclipse.gemoc.xdsmlframework.extensions.sirius.Activator;
import org.eclipse.gemoc.xdsmlframework.extensions.sirius.wizards.pages.NewGemocSiriusProjectMainWizardPage;
import org.eclipse.gemoc.xdsmlframework.extensions.sirius.wizards.pages.NewGemocSiriusProjectWizardFields;
import org.eclipse.gemoc.xdsmlframework.extensions.sirius.wizards.pages.NewODesignFileWizardPage;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.editor.editorPlugin.SiriusEditorPlugin;
import org.eclipse.sirius.ui.tools.api.project.ViewpointSpecificationProject;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

public class NewGemocSiriusProjectWizard extends AbstractNewProjectWizardWithTemplates implements INewWizard {


	
	protected NewGemocSiriusProjectWizardFields 		context;	
	
	/**
	 * Remember the workbench during initialization.
	 */
	private IWorkbench workbench;
	
	protected NewGemocSiriusProjectMainWizardPage 		projectPage;
	//WizardPageCustomNewProjectK3Plugin 	projectPageCustom	 = new WizardPageCustomNewProjectK3Plugin(this.context);
	
	/**
	 * This is the file creation page.
	 */
	private NewODesignFileWizardPage newOdesignPage;

	
	public NewGemocSiriusProjectWizard() {
		context = new NewGemocSiriusProjectWizardFields();
	}
	
	@Override
	public void addPages() {
		projectPage			 = new NewGemocSiriusProjectMainWizardPage(this.context);
		
		addPage(projectPage);			
		addPage(getTemplateListSelectionPage(context));
		
		newOdesignPage = new NewODesignFileWizardPage(
				"ODesign Model", this.context); //$NON-NLS-1$
		newOdesignPage.setTitle(SiriusEditorPlugin.getPlugin().getString(
				"_UI_SiriusModelWizard_label")); //$NON-NLS-1$
		newOdesignPage.setDescription(SiriusEditorPlugin.getPlugin().getString(
				"_UI_SiriusModelWizard_description")); //$NON-NLS-1$
		addPage(newOdesignPage);

		super.addPages();
		
	}
	
	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		this.workbench = workbench;
		setWindowTitle("New Viewpoint Specification Project for GEMOC");
		setDefaultPageImageDescriptor(ExtendedImageRegistry.INSTANCE
				.getImageDescriptor(SiriusEditorPlugin.INSTANCE
						.getImage("full/wizban/banner_viewpoint_specification_project.gif")));
		
	}
	
	@Override
	public boolean performFinish() {	
		try {
			IWorkspace workspace = ResourcesPlugin.getWorkspace(); 
			final IProjectDescription description = workspace.newProjectDescription(this.context.projectName);
			if (!this.context.projectLocation.equals(workspace.getRoot().getLocation().toOSString()))
				description.setLocation(new Path(this.context.projectLocation));
			
			final IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(this.context.projectName);
			IWorkspaceRunnable operation = new IWorkspaceRunnable() {
				public void run(IProgressMonitor monitor) throws CoreException {
					
					// if user do not reach page 2, the VSM name is defined according to
					// the project name
				/*	if (!newOdesignPage.isVsmNameChanged) {
						newOdesignPage.modelName.setText(newOdesignPage
								.extractModelName(newOdesignPage.firstPage
										.getProjectName()));
					}*/
					Path projectLocationPath = new Path(context.projectLocation);
					ViewpointSpecificationProject
							.createNewViewpointSpecificationProject(workbench,
									context.projectName, 
									projectLocationPath, 
									newOdesignPage.getModelName().getText(), 
									newOdesignPage.getInitialObjectName(), 
									newOdesignPage.getEncoding(), 
									getContainer());
					
					// launch the template
					
					IProjectContentWizard contentWizard = templateSelectionPage.getSelectedWizard();
					try {
						getContainer().run(false, true, new ProjectTemplateApplicationOperation(context, project, contentWizard));
					} catch (InvocationTargetException e) {
						Activator.logErrorMessage(e.getMessage(), e);
					} catch (InterruptedException e) {
						Activator.logErrorMessage(e.getMessage(), e);
					}
					
					//setClassPath(project, monitor);
					project.refreshLocal(IResource.DEPTH_INFINITE, monitor);
				}
			};
			ResourcesPlugin.getWorkspace().run(operation, null);
			
		} catch (Exception exception) {
			Activator.logErrorMessage(exception.getMessage(), exception);
			return false;
		}
		return true;
	}
	
	
	@Override
	public boolean isHelpAvailable() {
		return true;
	}

	
	public NewGemocSiriusProjectWizardFields getContext() {
		return context;
	}
	


	
	
	public NewGemocSiriusProjectMainWizardPage getPageProject() {
		return this.projectPage;
	}


	@Override
	public String getTargetPluginId() {		
		return Activator.PLUGIN_ID;
	}
	
	
	
}
