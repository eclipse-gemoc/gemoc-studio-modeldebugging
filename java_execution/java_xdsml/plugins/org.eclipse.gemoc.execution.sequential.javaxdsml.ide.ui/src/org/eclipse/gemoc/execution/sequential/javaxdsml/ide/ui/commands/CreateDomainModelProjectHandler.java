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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.util.Properties;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.gemoc.xdsmlframework.ide.ui.commands.AbstractDslSelectHandler;
import org.eclipse.gemoc.xdsmlframework.ide.ui.xdsml.wizards.CreateDomainModelWizardContextAction;
import org.eclipse.gemoc.xdsmlframework.ide.ui.xdsml.wizards.CreateDomainModelWizardContextAction.CreateDomainModelAction;
//import org.eclipse.jface.dialogs.MessageDialog;

public class CreateDomainModelProjectHandler extends AbstractDslSelectHandler implements
		IHandler {

	@Override
	public Object executeForSelectedLanguage(ExecutionEvent event,
			IProject updatedGemocLanguageProject, String language)
			throws ExecutionException {
		CreateDomainModelWizardContextAction action = new CreateDomainModelWizardContextAction(
				updatedGemocLanguageProject); 
		action.actionToExecute = CreateDomainModelAction.CREATE_NEW_EMF_PROJECT;
		action.execute();
		
		if(action.getCreatedEcoreUri() != null){
			waitForAutoBuild();
			updateDsl(event,updatedGemocLanguageProject,language,action.getCreatedEcoreUri());
		}
		
		return null;
	}
	
	protected void updateDsl(ExecutionEvent event, IProject project, String language, String ecoreURI) {
		
		IFile dslFile = getDslFileFromProject(project);
		Properties dslProp = new Properties();
		try {
			
			dslProp.load(dslFile.getContents());
			String allSyntaxes = (String) dslProp.get("syntax");
			if(allSyntaxes == null || allSyntaxes.isEmpty()) {
				allSyntaxes = ecoreURI;
			}
			else {
				allSyntaxes = allSyntaxes + "," + ecoreURI;
			}
			dslProp.put("syntax", allSyntaxes);
			dslProp.store(new FileOutputStream(new File(dslFile.getLocationURI())), "");
			
		} catch (IOException | CoreException e) {
			e.printStackTrace();
		}
	}

	protected void waitForAutoBuild() {
		boolean wasInterrupted = false;
		do {
			try {
				Job.getJobManager().join(ResourcesPlugin.FAMILY_AUTO_BUILD,	null);
				wasInterrupted = false;
			} catch (OperationCanceledException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				wasInterrupted = true;
			}
		} while (wasInterrupted);
	}

	@Override
	public String getSelectionMessage() {
		return "Select Melange language that will be used to initialize the new Domain Model project";
	}
}
