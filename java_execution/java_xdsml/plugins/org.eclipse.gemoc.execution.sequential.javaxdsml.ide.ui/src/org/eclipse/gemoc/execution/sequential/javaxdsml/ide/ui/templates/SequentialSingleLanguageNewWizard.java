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

import org.eclipse.gemoc.commons.eclipse.pde.wizards.pages.pde.ui.BaseProjectWizardFields;
import org.eclipse.gemoc.commons.eclipse.pde.wizards.pages.pde.ui.templates.ITemplateSection;
import org.eclipse.gemoc.commons.eclipse.pde.wizards.pages.pde.ui.templates.NewProjectTemplateWizard;
import org.eclipse.xtext.util.Strings;

public class SequentialSingleLanguageNewWizard extends NewProjectTemplateWizard{

	@Override
	public void init(BaseProjectWizardFields data) {
		super.init(data);
		setWindowTitle(WizardTemplateMessages.SequentialSingleLanguageTemplate_wtitle);
		
		try {
			String project = getData().projectName;
			String SUFFIX = ".xdsml";
			if(project.endsWith(SUFFIX)){
				
				int startSuffix = project.length() - SUFFIX.length();
				int startName = project.lastIndexOf(".", startSuffix-1) + 1;
				
				String packageName = project.substring(0, startName - 1).toLowerCase();
				String languageName = project.substring(startName, startSuffix);
				languageName = Strings.toFirstUpper(languageName);

				ITemplateSection[] selections = getTemplateSections();
				SequentialSingleLanguageTemplate selection = (SequentialSingleLanguageTemplate) selections[0];
				selection.updateOptions(packageName, languageName, languageName);
			}
		} catch (Exception e) {}
	}
	
	@Override
	public ITemplateSection[] createTemplateSections() {
		return new ITemplateSection[] {new SequentialSingleLanguageTemplate()};
	}
}
