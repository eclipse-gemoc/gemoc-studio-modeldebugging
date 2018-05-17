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

import java.io.IOException;
import java.util.HashMap;
import java.util.Optional;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.gemoc.dsl.Dsl;
import org.eclipse.gemoc.dsl.DslFactory;
import org.eclipse.gemoc.dsl.DslPackage;
import org.eclipse.gemoc.dsl.Entry;
import org.eclipse.gemoc.execution.sequential.javaxdsml.ide.ui.Activator;
import org.eclipse.gemoc.xdsmlframework.ide.ui.commands.AbstractDslSelectHandler;
import org.eclipse.gemoc.xdsmlframework.ide.ui.xdsml.wizards.CreateEditorProjectWizardContextAction;
import org.eclipse.gemoc.xdsmlframework.ide.ui.xdsml.wizards.CreateEditorProjectWizardContextAction.CreateEditorProjectAction;

public class CreateXtextEditorProjectHandler extends AbstractDslSelectHandler implements
		IHandler {

	@Override
	public Object executeForSelectedLanguage(ExecutionEvent event,
			IProject updatedGemocLanguageProject, String language)
			throws ExecutionException {
		CreateEditorProjectWizardContextAction action = new CreateEditorProjectWizardContextAction(
				updatedGemocLanguageProject);
		action.actionToExecute = CreateEditorProjectAction.CREATE_NEW_XTEXT_PROJECT;
		action.execute();
		
		if(action.getSiriusPath() != null){
			waitForAutoBuild();
			updateDsl(event,updatedGemocLanguageProject,language,action.getXtextPath());
		}
		
		return null;
	}
	
	@Override
	public String getSelectionMessage() {
		return "Select Melange language that will be used to initialize the new XText project";
	}
	
	protected void updateDsl(ExecutionEvent event, IProject project, String language, String xtextPath){
		
		IFile dslFile = getDslFileFromProject(project);
		Resource res = (new ResourceSetImpl()).getResource(URI.createURI(dslFile.getFullPath().toOSString()), true);
		Dsl dsl = (Dsl) res.getContents().get(0);
		
		Optional<Entry> xtext = dsl.getEntries()
				.stream()
				.filter(entry -> entry.getKey().equals("xtext"))
				.findFirst();
		if(xtext.isPresent()) {
			xtext.get().setValue(xtextPath);
		}
		else {
			Entry xtextEntry = ((DslFactory)DslPackage.eINSTANCE.getEFactoryInstance()).createEntry();
			xtextEntry.setKey("xtext");
			xtextEntry.setValue(xtextPath);
			dsl.getEntries().add(xtextEntry);
		}
		try {
			res.save(new HashMap());
		} catch (IOException e) {
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
				Activator.warn(e.getMessage(), e);
			} catch (InterruptedException e) {
				wasInterrupted = true;
			}
		} while (wasInterrupted);
	}
}
