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
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.XtextEditor;
import org.eclipse.xtext.ui.editor.model.IXtextDocument;
import org.eclipse.xtext.ui.editor.utils.EditorUtils;
import org.eclipse.gemoc.xdsmlframework.ide.ui.commands.AbstractMelangeSelectHandler;
import org.eclipse.gemoc.xdsmlframework.ide.ui.xdsml.wizards.CreateDomainModelWizardContextAction;
import org.eclipse.gemoc.xdsmlframework.ide.ui.xdsml.wizards.CreateDomainModelWizardContextAction.CreateDomainModelAction;
//import org.eclipse.jface.dialogs.MessageDialog;

import fr.inria.diverse.melange.metamodel.melange.Language;

public class CreateDomainModelProjectHandler extends AbstractMelangeSelectHandler implements
		IHandler {

	@Override
	public Object executeForSelectedLanguage(ExecutionEvent event,
			IProject updatedGemocLanguageProject, Language language)
			throws ExecutionException {
		CreateDomainModelWizardContextAction action = new CreateDomainModelWizardContextAction(
				updatedGemocLanguageProject); 
		action.actionToExecute = CreateDomainModelAction.CREATE_NEW_EMF_PROJECT;
		action.execute();
		
		if(action.getCreatedEcoreUri() != null){
			waitForAutoBuild();
			updateMelange(event,language,action.getCreatedEcoreUri());
		}
		
		return null;
	}
	
	protected void updateMelange(ExecutionEvent event, Language language, String ecoreURI){
		// Compute offset & new string
		int startOffset = -1;
		int length = -1;
		String newRegion = null;
		
		EStructuralFeature operators = language.eClass().getEStructuralFeature("operators");
		List<INode> nodesOp = NodeModelUtils.findNodesForFeature(language, operators);
		int lastOffset = Integer.MAX_VALUE;
		for(INode node : nodesOp){
			if(node.getOffset() < lastOffset) lastOffset = node.getOffset();
		}
		if(lastOffset != Integer.MAX_VALUE){
				startOffset = lastOffset;
				length = 0;
				newRegion = "syntax \""+ecoreURI+"\"\n\t";
		}
		else if(nodesOp.isEmpty()){ //no operators, search for '{'
			EStructuralFeature name = language.eClass().getEStructuralFeature("name");
			List<INode> nameNode = NodeModelUtils.findNodesForFeature(language, name);
			INode node = nameNode.get(0);
			int endName = node.getEndOffset();
			
			
			XtextEditor editor = EditorUtils.getActiveXtextEditor();
			if (editor != null && editor.getLanguageName().equals(MELANGE_EDITOR)) { 
				String content = editor.getDocument().get();
				startOffset = content.indexOf("{", endName) + 1;
				length = 0;
				newRegion = "\n\tsyntax \""+ecoreURI+"\"\n\t";
			}
			else{
				String melangeWSLocation = language.eResource().getURI().toPlatformString(true);
				URI uri = language.eResource().getURI();
				String melangeLocation =ResourcesPlugin.getWorkspace().getRoot().getLocation().toOSString()+melangeWSLocation;
				List<String> lines;
				try {
					lines = Files.readAllLines(Paths.get(melangeLocation));
					StringBuffer newContent = new StringBuffer();
					lines.forEach(
							line -> newContent.append(line+"\n")
							);
					startOffset = newContent.indexOf("{", endName) + 1;
					length = 0;
					newRegion = "\n\tsyntax \""+ecoreURI+"\"\n\t";
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		// Replace in document or Melange file
		if(startOffset != -1 && length != -1 && newRegion != null){
			int _startOffset = startOffset;
			int _length = length;
			String _newRegion = newRegion;
			XtextEditor editor = EditorUtils.getActiveXtextEditor();
			if (editor != null && editor.getLanguageName().equals(MELANGE_EDITOR)) { //Update the editor content
				IXtextDocument document = editor.getDocument();
				document.modify((XtextResource it) -> {
					document.replace(_startOffset,_length, _newRegion);
					return null; // no computed value
				});
			}
			else{ //Update the Melange file content
				
				try {
					//Load Melange file
					String melangeWSLocation = language.eResource().getURI().toPlatformString(true);
					URI uri = language.eResource().getURI();
					String melangeLocation =ResourcesPlugin.getWorkspace().getRoot().getLocation().toOSString()+melangeWSLocation;
					List<String> lines = Files.readAllLines(Paths.get(melangeLocation));
					
					StringBuffer newContent = new StringBuffer();
					lines.forEach(
							line -> newContent.append(line+"\n")
							);
					
					newContent.replace(startOffset,startOffset+length, newRegion);
					
					//Write new content
					Files.write(Paths.get(melangeLocation), newContent.toString().getBytes());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
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
