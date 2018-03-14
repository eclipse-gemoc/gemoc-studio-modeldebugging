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
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gemoc.execution.sequential.javaxdsml.ide.ui.Activator;
import org.eclipse.gemoc.xdsmlframework.ide.ui.commands.AbstractMelangeSelectHandler;
import org.eclipse.gemoc.xdsmlframework.ide.ui.xdsml.wizards.CreateEditorProjectWizardContextAction;
import org.eclipse.gemoc.xdsmlframework.ide.ui.xdsml.wizards.CreateEditorProjectWizardContextAction.CreateEditorProjectAction;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.XtextEditor;
import org.eclipse.xtext.ui.editor.model.IXtextDocument;
import org.eclipse.xtext.ui.editor.utils.EditorUtils;

import fr.inria.diverse.melange.metamodel.melange.Language;

public class CreateXtextEditorProjectHandler extends AbstractMelangeSelectHandler implements
		IHandler {

	@Override
	public Object executeForSelectedLanguage(ExecutionEvent event,
			IProject updatedGemocLanguageProject, Language language)
			throws ExecutionException {
		CreateEditorProjectWizardContextAction action = new CreateEditorProjectWizardContextAction(
				updatedGemocLanguageProject);
		action.actionToExecute = CreateEditorProjectAction.CREATE_NEW_XTEXT_PROJECT;
		action.execute();
		
		if(action.getSiriusPath() != null){
			waitForAutoBuild();
			updateMelange(event,language,action.getXtextPath());
		}
		
		return null;
	}
	
	@Override
	public String getSelectionMessage() {
		return "Select Melange language that will be used to initialize the new XText project";
	}

	protected void updateMelange(ExecutionEvent event, Language language, String xtextPath){
		// Compute offset & new string
		int startOffset = -1;
		int length = -1;
		String newRegion = null;
		
		EStructuralFeature xtext = language.eClass().getEStructuralFeature("xtext");
		List<INode> nodesXtext = NodeModelUtils.findNodesForFeature(language, xtext);
		
		if(!nodesXtext.isEmpty()){
			INode nodeXtext = nodesXtext.get(0);
			startOffset = nodeXtext.getOffset();
			length = nodeXtext.getLength();
			newRegion = "\""+xtextPath+"\"";
		}
		else{//insert after operators
			EStructuralFeature operators = language.eClass().getEStructuralFeature("operators");
			List<INode> nodesOp = NodeModelUtils.findNodesForFeature(language, operators);
			int lastOffset = -1;
			for(INode node : nodesOp){
				if(node.getEndOffset() > lastOffset) lastOffset = node.getEndOffset();
			}
			if(lastOffset != -1){
				
					startOffset = lastOffset;
					length = 0;
					newRegion = "\n\n\txtext \""+xtextPath+"\"";
				
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
					String melangeLocation = ResourcesPlugin.getWorkspace().getRoot().getLocation().toOSString()+melangeWSLocation;
					List<String> lines = Files.readAllLines(Paths.get(melangeLocation));
					
					StringBuffer newContent = new StringBuffer();
					lines.forEach(
							line -> newContent.append(line+"\n")
							);
					
					newContent.replace(startOffset,startOffset+length, newRegion);
					
					//Write new content
					Files.write(Paths.get(melangeLocation), newContent.toString().getBytes());
				} catch (IOException e) {
					Activator.error(e.getMessage(), e);
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
				Activator.warn(e.getMessage(), e);
			} catch (InterruptedException e) {
				wasInterrupted = true;
			}
		} while (wasInterrupted);
	}
}
