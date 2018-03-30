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
package org.eclipse.gemoc.execution.sequential.javaxdsml.ide.ui.templates;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.gemoc.commons.eclipse.pde.wizards.pages.pde.ui.BaseProjectWizardFields;
import org.eclipse.gemoc.commons.eclipse.pde.wizards.pages.pde.ui.templates.ITemplateSection;
import org.eclipse.gemoc.commons.eclipse.pde.wizards.pages.pde.ui.templates.NewProjectTemplateWizard;
import org.eclipse.pde.internal.ui.PDEPlugin;
import org.eclipse.xtext.util.Strings;

public class MelangeSequentialSingleLanguageNewWizard extends NewProjectTemplateWizard{

	@Override
	public void init(BaseProjectWizardFields data) {
		super.init(data);
		setWindowTitle(WizardTemplateMessages.MelangeSequentialSingleLanguageTemplate_wtitle);
		
		try {
			String project = getData().projectName;
			String SUFFIX = ".dsl";
			if(project.endsWith(SUFFIX)){
				
				int startSuffix = project.length() - SUFFIX.length();
				int startName = project.lastIndexOf(".", startSuffix-1) + 1;
				
				String packageName = project.substring(0, startName - 1).toLowerCase();
				String languageName = project.substring(startName, startSuffix);
				languageName = Strings.toFirstUpper(languageName);

				ITemplateSection[] selections = getTemplateSections();
				MelangeSequentialSingleLanguageTemplate selection = (MelangeSequentialSingleLanguageTemplate) selections[0];
				selection.updateOptions(packageName, languageName, languageName);
			}
		} catch (Exception e) {}
	}
	
	@Override
	public ITemplateSection[] createTemplateSections() {
		return new ITemplateSection[] {new MelangeSequentialSingleLanguageTemplate()};
	}

	

	
	
}
