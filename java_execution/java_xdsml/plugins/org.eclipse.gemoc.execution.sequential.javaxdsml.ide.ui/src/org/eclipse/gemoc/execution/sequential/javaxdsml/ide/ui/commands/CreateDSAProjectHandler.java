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
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.gemoc.dsl.Dsl;
import org.eclipse.gemoc.dsl.DslFactory;
import org.eclipse.gemoc.dsl.DslPackage;
import org.eclipse.gemoc.dsl.Entry;
import org.eclipse.gemoc.execution.sequential.javaxdsml.ide.ui.Activator;
import org.eclipse.gemoc.execution.sequential.javaxdsml.ide.ui.templates.SequentialSingleLanguageTemplate;
import org.eclipse.gemoc.execution.sequential.javaxdsml.ide.ui.wizards.CreateDSAWizardContextActionDSAK3;
import org.eclipse.gemoc.xdsmlframework.ide.ui.commands.AbstractDslSelectHandler;

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
		Resource res = (new ResourceSetImpl()).getResource(URI.createURI(dslFile.getFullPath().toOSString()), true);
		Dsl dsl = (Dsl) res.getContents().get(0);
		Optional<Entry> semantics = dsl.getEntries()
				.stream()
				.filter(entry -> entry.getKey().equals("k3"))
				.findFirst();
		if(semantics.isPresent()) {
			semantics.get().setValue(semantics.get().getValue() + "," + aspects.stream().collect(Collectors.joining(", ")));
		}
		else {
			Entry k3Entry = ((DslFactory)DslPackage.eINSTANCE.getEFactoryInstance()).createEntry();
			k3Entry.setKey("k3");
			k3Entry.setValue(aspects.stream().collect(Collectors.joining(",")));
			dsl.getEntries().add(k3Entry);
		}
		try {
			res.save(new HashMap());
		} catch (IOException e) {
			Activator.error(e.getMessage(), e);
		}
	}
	
	protected IFile getFirstEcore(ExecutionEvent event, String language){
		
		String ecoreURI = null;
		IFile dslFile = getDslFileFromSelection(event);
		Resource res = (new ResourceSetImpl()).getResource(URI.createURI(dslFile.getFullPath().toOSString()), true);
		Dsl dsl = (Dsl) res.getContents().get(0);
		Optional<Entry> syntax = dsl.getEntries()
			.stream()
			.filter(entry -> entry.getKey().equals("ecore"))
			.findFirst();
		if(syntax.isPresent() && !syntax.get().getValue().isEmpty()) {
			ecoreURI = syntax.get().getValue();
			if(ecoreURI.contains(",")) {
				ecoreURI = ecoreURI.split(",")[0];
			}
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
				Activator.warn(e.getMessage(), e);
			} catch (InterruptedException e) {
				wasInterrupted = true;
			}
		} while (wasInterrupted);
	}
}
