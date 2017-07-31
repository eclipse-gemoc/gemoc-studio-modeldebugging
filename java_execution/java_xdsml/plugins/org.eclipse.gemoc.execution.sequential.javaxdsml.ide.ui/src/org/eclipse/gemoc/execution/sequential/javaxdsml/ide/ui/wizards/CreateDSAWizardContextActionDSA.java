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
import org.eclipse.core.resources.IResource;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.PlatformUI;
import org.eclipse.gemoc.execution.sequential.javaxdsml.ide.ui.dialogs.SelectDSAIProjectDialog;

public class CreateDSAWizardContextActionDSA extends CreateDSAWizardContextBase {

	public CreateDSAWizardContextActionDSA(IProject gemocLanguageIProject) {
		super(gemocLanguageIProject);
	}

	public void createNewDSAProject() {
	}
	
	public void selectExistingDSAProject(){
		// launch the appropriate wizard
		SelectDSAIProjectDialog dialog = new SelectDSAIProjectDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell());
		int res = dialog.open();
		if(res == WizardDialog.OK){
			// update the project model
			// maybe we should update melange model
		}
	}	
	
}
