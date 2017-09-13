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
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.util.URI;
import org.eclipse.gemoc.execution.sequential.javaxdsml.ide.ui.templates.SequentialSingleLanguageTemplate;
import org.eclipse.gemoc.execution.sequential.javaxdsml.ide.ui.wizards.CreateDSAWizardContextActionDSAK3;
import org.eclipse.gemoc.xdsmlframework.ide.ui.commands.AbstractDslSelectHandler;
import org.eclipse.xtext.util.Strings;

import com.google.common.collect.Lists;

public class CreateDSAProjectHandler extends AbstractDslSelectHandler implements IHandler  {

	@Override
	public Object executeForSelectedLanguage(ExecutionEvent event,
			IProject updatedGemocLanguageProject, String language)
			throws ExecutionException {
		CreateDSAWizardContextActionDSAK3 action = new CreateDSAWizardContextActionDSAK3(
				updatedGemocLanguageProject);
		action.createNewDSAProject(getFirstEcore(event,language));
		
		if(action.getLastCreatedProject() != null){
			waitForAutoBuild();
			
			Set<String> aspects = SequentialSingleLanguageTemplate.getAspectClassesList(action.getLastCreatedProject());
			updateDsl(event,updatedGemocLanguageProject,language,Lists.newArrayList(aspects));
		}
		
		return null;
	}
	
	@Override
	public String getSelectionMessage() {
		return "Select Melange language that will be used to initialize the new DSA project";
	}

	protected void updateDsl(ExecutionEvent event, IProject project, String language, List<String> aspects){
		
		IFile dslFile = getDslFileFromProject(project);
		Properties dslProp = new Properties();
		try {
			
			dslProp.load(dslFile.getContents());
			String allbehaviors = (String) dslProp.get("behavior");
			if(allbehaviors == null || allbehaviors.isEmpty()) {
				allbehaviors = Strings.concat(",", aspects);
			}
			else {
				allbehaviors = allbehaviors + "," + Strings.concat(",", aspects);
			}
			dslProp.put("behavior", allbehaviors);
			dslProp.store(new FileOutputStream(new File(dslFile.getLocationURI())), "");
			
		} catch (IOException | CoreException e) {
			e.printStackTrace();
		}
	}
	
	protected IFile getFirstEcore(ExecutionEvent event, String language){
		
		String ecoreURI = null;
		IFile dslFile = getDslFileFromSelection(event);
		Properties dslProp = new Properties();
		try {
			
			dslProp.load(dslFile.getContents());
			String allSyntaxes = (String) dslProp.get("syntax");
			if(allSyntaxes.contains(",")) {
				ecoreURI = allSyntaxes.split(",")[0];
			}
			else {
				ecoreURI = allSyntaxes;
			}
			
		} catch (IOException | CoreException e) {
			e.printStackTrace();
		}
		
		if(ecoreURI != null){
			final URI uri = org.eclipse.emf.common.util.URI.createURI(ecoreURI);
			final String filePath = uri.toPlatformString(true);
			final IPath path = new Path(filePath);
			return ResourcesPlugin.getWorkspace().getRoot().getFile(path);
		}
		
		return null;
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
}
