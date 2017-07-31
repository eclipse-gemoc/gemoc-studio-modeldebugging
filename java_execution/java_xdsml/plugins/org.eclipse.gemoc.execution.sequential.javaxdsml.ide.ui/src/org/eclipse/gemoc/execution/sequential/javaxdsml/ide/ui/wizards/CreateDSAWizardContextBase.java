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
import org.eclipse.gemoc.commons.eclipse.pde.manifest.ManifestChanger;


public class CreateDSAWizardContextBase {

	protected IProject _gemocLanguageIProject;
	
	public CreateDSAWizardContextBase(IProject gemocLanguageIProject) {
		_gemocLanguageIProject = gemocLanguageIProject;
	}

	protected void addDSAProjectToConf(String projectName,IProject gemocLanguageIProject) {
		ManifestChanger manifestChanger = new ManifestChanger(gemocLanguageIProject);
		try {
			manifestChanger.addPluginDependency(projectName);
			manifestChanger.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	

}