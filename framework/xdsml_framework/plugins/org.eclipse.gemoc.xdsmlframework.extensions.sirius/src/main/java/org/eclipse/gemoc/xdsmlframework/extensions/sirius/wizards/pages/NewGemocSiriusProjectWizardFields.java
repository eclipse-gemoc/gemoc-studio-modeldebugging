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
package org.eclipse.gemoc.xdsmlframework.extensions.sirius.wizards.pages;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.gemoc.commons.eclipse.pde.wizards.pages.pde.ui.BaseProjectWizardFields;

public class NewGemocSiriusProjectWizardFields extends BaseProjectWizardFields {
	
	// main fields (Ie. that are present in the main wizard page)

	public String 			baseGemocProject;
	public boolean			updateGemocDSLFile = true;
	public String 			dslFilePath;
	

	
	
	public NewGemocSiriusProjectWizardFields () {
		super();
		this.projectName 			= "org.company.language.design";
		this.projectLocation 		= ResourcesPlugin.getWorkspace().getRoot().getLocation().toOSString()+"/"+this.projectName;
		
		this.baseGemocProject		= "org.company.language";
		this.updateGemocDSLFile		= true;
		this.dslFilePath			= "";
	}
}
