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
package org.eclipse.gemoc.execution.sequential.javaxdsml.ide.ui.wizards;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.pde.internal.ui.elements.ElementList;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.gemoc.execution.sequential.javaxdsml.ide.ui.builder.AddRemoveGemocSequentialLanguageNatureHandler;

import org.eclipse.gemoc.commons.eclipse.pde.wizards.pages.pde.TemplateListSelectionPage;
import org.eclipse.gemoc.commons.eclipse.pde.wizards.pages.pde.WizardElement;
import fr.inria.diverse.melange.ui.wizards.NewMelangeProjectWizard;
import fr.inria.diverse.melange.ui.wizards.pages.NewMelangeProjectWizardPage;

@SuppressWarnings("restriction")
public class CreateNewGemocSequentialLanguageProject extends NewMelangeProjectWizard {

	
	public CreateNewGemocSequentialLanguageProject() {
		super();	
	}
	
	@Override
	public void addPages() {
		super.addPages();
		
		NewMelangeProjectWizardPage firstPage = (NewMelangeProjectWizardPage) getPage("wizardPage"); 
		firstPage.setTitle("Project");
		firstPage.setDescription("Create a new Gemoc Sequential Language Project");
		firstPage.updateNameProject("org.company.mySequentialLanguage");
	}

	@Override
	public void configureProject(IProject project, IProgressMonitor monitor) {
		super.configureProject(project, monitor);
		new AddRemoveGemocSequentialLanguageNatureHandler().configureNature(project);
	}
	
	/**
	 * Look for extension point="org.eclipse.gemoc.commons.eclipse.pde.projectContent"
	 * and filter wizards
	 */
	@Override
	public ElementList getAvailableCodegenWizards() {
		ElementList superRes = super.getAvailableCodegenWizards();
		ElementList newRes = new ElementList("CodegenWizards"); //$NON-NLS-1$
		
		for (Object element : superRes.getChildren()) {
			if(element instanceof WizardElement){
				WizardElement wizardElem = (WizardElement) element;
				String id = wizardElem.getID();
				if(id.equals("org.eclipse.gemoc.execution.sequential.javaxdsml.ide.ui.templates.projectContent.SequentialSingleLanguage") ||
				   id.equals("org.eclipse.gemoc.execution.sequential.javaxdsml.ide.ui.templates.projectContent.SequentialExtendedLanguage")		){
					newRes.add(wizardElem);
				}
			}
		}
		
		return newRes;
	}
	
	@Override
	public void createPageControls(Composite pageContainer) {
		super.createPageControls(pageContainer);
		TemplateListSelectionPage templatesPage = getTemplateListSelectionPage(context);
		templatesPage.setUseTemplate(true);
		// select this template as default
		templatesPage.selectTemplate("org.eclipse.gemoc.execution.sequential.javaxdsml.ide.ui.templates.projectContent.SequentialSingleLanguage");
	}
}
