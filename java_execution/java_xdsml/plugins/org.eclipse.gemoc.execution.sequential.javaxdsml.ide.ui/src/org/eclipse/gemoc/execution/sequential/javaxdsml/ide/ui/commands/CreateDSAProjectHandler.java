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
import org.eclipse.gemoc.execution.sequential.javaxdsml.ide.ui.templates.SequentialSingleLanguageTemplate;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.gemoc.dsl.Dsl;
import org.eclipse.gemoc.dsl.DslFactory;
import org.eclipse.gemoc.dsl.DslPackage;
import org.eclipse.gemoc.dsl.SimpleValue;
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
		Optional<SimpleValue> semantic = dsl
			.getSemantic()
			.getValues()
			.stream()
			.filter(v -> v instanceof SimpleValue)
			.map(v -> (SimpleValue) v)
			.filter(v -> v.getName().equals("k3"))
			.findFirst();
		
		if(semantic.isPresent()) {
			semantic.get().getValues().addAll(aspects);
		}
		else {
			SimpleValue newAspects = ((DslFactory)DslPackage.eINSTANCE.getEFactoryInstance()).createSimpleValue();
			newAspects.setName("k3");
			newAspects.getValues().addAll(aspects);
			dsl.getSemantic().getValues().add(newAspects);
		}
		try {
			res.save(new HashMap());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	protected IFile getFirstEcore(ExecutionEvent event, String language){
		
		String ecoreURI = null;
		IFile dslFile = getDslFileFromSelection(event);
		Resource res = (new ResourceSetImpl()).getResource(URI.createURI(dslFile.getFullPath().toOSString()), true);
		Dsl dsl = (Dsl) res.getContents().get(0);
		Optional<SimpleValue> syntax = dsl
			.getAbstractSyntax()
			.getValues()
			.stream()
			.filter(v -> v instanceof SimpleValue)
			.map(v -> (SimpleValue) v)
			.filter(v -> v.getName().equals("ecore"))
			.findFirst();
		if(syntax.isPresent() && !syntax.get().getValues().isEmpty()) {
			ecoreURI = syntax.get().getValues().get(0);
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
