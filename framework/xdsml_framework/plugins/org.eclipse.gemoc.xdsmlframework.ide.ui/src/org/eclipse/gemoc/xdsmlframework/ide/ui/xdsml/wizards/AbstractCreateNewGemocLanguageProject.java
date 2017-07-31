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
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.dialogs.WizardNewProjectCreationPage;
import org.eclipse.gemoc.xdsmlframework.ide.ui.Activator;
import org.eclipse.gemoc.xdsmlframework.ide.ui.xdsml.wizards.pages.AskLanguageNameWizardPage;

public abstract class AbstractCreateNewGemocLanguageProject extends Wizard implements INewWizard {

	protected WizardNewProjectCreationPage _askProjectNamePage;
	protected AskLanguageNameWizardPage _askLanguageNamePage;
//	private ToggleNatureAction nature;
	
	protected IProject createdProject = null; 
	
	

	public AbstractCreateNewGemocLanguageProject() {
		super();
		this.setWindowTitle("Create Domain Model");
		_askProjectNamePage = new WizardNewProjectCreationPage("NewGemocLanguageProjectName");
		_askProjectNamePage.setTitle("Project");
		_askProjectNamePage.setDescription("Create a new Gemoc Language Project");
		_askProjectNamePage.setInitialProjectName("org.company.my_language.xdsml");
		addPage(_askProjectNamePage);

		_askLanguageNamePage = new AskLanguageNameWizardPage("NewGemocLanguageName");
		_askLanguageNamePage.setTitle("Language");
		_askLanguageNamePage.setDescription("Specify the language name");
		addPage(_askLanguageNamePage);	
	}

	@Override
	public void addPages() {
		super.addPages();
	}

	@Override
	public boolean needsPreviousAndNextButtons() {
		return true;
	}
	
	@Override
	public boolean canFinish() {
		return getContainer().getCurrentPage() == _askLanguageNamePage
				&& _askLanguageNamePage.isPageComplete();
	}
	
	@Override
	public IWizardPage getNextPage(IWizardPage page) {
		if (page == _askProjectNamePage) {
			int index = _askProjectNamePage.getProjectName().indexOf(".xdsml");
			if(index != -1){
				_askLanguageNamePage.setLanguageName(_askProjectNamePage.getProjectName().substring(0, index));		
			}
			else{
				_askLanguageNamePage.setLanguageName(_askProjectNamePage.getProjectName());
			}
		}
		return super.getNextPage(page);
	}
		
	@Override
	public boolean performFinish() {
		
		try {
			createdProject = _askProjectNamePage.getProjectHandle();
			final String languageName = _askLanguageNamePage.getLanguageName();

			IWorkspace workspace = ResourcesPlugin.getWorkspace(); 
			final IProjectDescription description = workspace.newProjectDescription(createdProject.getName());
			if (!_askProjectNamePage.getLocationPath().equals(workspace.getRoot().getLocation()))
				description.setLocation(_askProjectNamePage.getLocationPath());
			//description.setLocationURI(_askProjectNamePage.getLocationURI());
			
			IWorkspaceRunnable operation = new IWorkspaceRunnable() {
				 public void run(IProgressMonitor monitor) throws CoreException {
					 createdProject.create(description, monitor);
					 createdProject.open(monitor);
					 initializeProject(createdProject, languageName);
					 createdProject.refreshLocal(IResource.DEPTH_INFINITE, monitor);
					 createdProject.touch(new NullProgressMonitor()); // [FT] One touch to force eclipse to serialize the project properties that will update accordingly the gemoc actions in the menu.
					 //createdProject.build(IncrementalProjectBuilder.FULL_BUILD, new NullProgressMonitor());
				 }
			};
			ResourcesPlugin.getWorkspace().run(operation, null);
		} catch (CoreException exception) {
			Activator.error(exception.getMessage(), exception);
			return false;
		}
		return true;
	}

	public void init(IWorkbench workbench, IStructuredSelection selection) {
	}

	protected abstract void initializeProject(IProject project, String languageName);
	
	public IProject getCreatedProject() {
		return createdProject;
	}
}
