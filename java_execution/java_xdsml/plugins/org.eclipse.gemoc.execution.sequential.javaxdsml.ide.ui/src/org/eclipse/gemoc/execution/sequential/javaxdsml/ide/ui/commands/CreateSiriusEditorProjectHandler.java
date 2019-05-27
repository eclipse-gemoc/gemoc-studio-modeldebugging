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
package org.eclipse.gemoc.execution.sequential.javaxdsml.ide.ui.commands;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.resources.IProject;
import org.eclipse.gemoc.xdsmlframework.ide.ui.commands.AbstractDslSelectHandler;
import org.eclipse.gemoc.xdsmlframework.ide.ui.xdsml.wizards.CreateEditorProjectWizardContextAction;
import org.eclipse.gemoc.xdsmlframework.ide.ui.xdsml.wizards.CreateEditorProjectWizardContextAction.CreateEditorProjectAction;

public class CreateSiriusEditorProjectHandler extends AbstractDslSelectHandler implements
		IHandler {
	
	@Override
	public Object executeForSelectedLanguage(ExecutionEvent event,
			IProject updatedGemocLanguageProject, String language)
			throws ExecutionException {
		CreateEditorProjectWizardContextAction action = new CreateEditorProjectWizardContextAction(updatedGemocLanguageProject);
		action.actionToExecute = CreateEditorProjectAction.CREATE_NEW_SIRIUS_PROJECT;
		action.execute();
		
		
		return null;
	}

	@Override
	public String getSelectionMessage() {
		return "Select language (*.dsl file) that will be used to initialize the new Sirius project";
	}

}
