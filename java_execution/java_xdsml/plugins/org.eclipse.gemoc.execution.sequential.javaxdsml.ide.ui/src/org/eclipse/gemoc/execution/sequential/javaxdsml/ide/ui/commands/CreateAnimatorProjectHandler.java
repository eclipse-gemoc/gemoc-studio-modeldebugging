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
import org.eclipse.gemoc.xdsmlframework.ide.ui.commands.AbstractMelangeSelectHandler;
import org.eclipse.gemoc.xdsmlframework.ide.ui.xdsml.wizards.CreateAnimatorProjectWizardContextAction;
import org.eclipse.gemoc.xdsmlframework.ide.ui.xdsml.wizards.CreateAnimatorProjectWizardContextAction.CreateAnimatorProjectAction;

import fr.inria.diverse.melange.metamodel.melange.Language;

public class CreateAnimatorProjectHandler extends AbstractMelangeSelectHandler implements
		IHandler {
	
	@Override
	public Object executeForSelectedLanguage(ExecutionEvent event,
			IProject updatedGemocLanguageProject, Language language)
			throws ExecutionException {
		CreateAnimatorProjectWizardContextAction action = new CreateAnimatorProjectWizardContextAction(
				updatedGemocLanguageProject, language);
		action.actionToExecute = CreateAnimatorProjectAction.CREATE_NEW_SIRIUS_PROJECT;
		action.execute();
		return null;
	}

	@Override
	public String getSelectionMessage() {
		return "Select Melange language that will be used to initialize the new Animator project";
	}

}
