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
package org.eclipse.gemoc.xdsmlframework.ide.ui.commands;


import java.util.Iterator;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.xtext.resource.EObjectAtOffsetHelper;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.XtextEditor;
import org.eclipse.xtext.util.concurrent.IUnitOfWork;

import fr.inria.diverse.melange.metamodel.melange.Language;

public abstract class AbstractGemocLanguageProjectHandler extends AbstractHandler {

	
	protected IProject getUpdatedGemocLanguageProjectFromSelection(ExecutionEvent event) {
		IProject updatedGemocLanguageProject = null;
		ISelection selection = HandlerUtil.getActiveWorkbenchWindow(event).getActivePage().getSelection();
		if (selection != null & selection instanceof IStructuredSelection) {
			IStructuredSelection strucSelection = (IStructuredSelection) selection;
			for (@SuppressWarnings("unchecked")
				Iterator<Object> iterator = strucSelection.iterator(); 
				iterator.hasNext();) {
				
				Object element = iterator.next();

				if (element instanceof IResource) {
					updatedGemocLanguageProject = ((IResource) element)
							.getProject();

				}
				if (element instanceof IAdaptable) {
					IResource res = (IResource) ((IAdaptable) element)
							.getAdapter(IResource.class);
					if (res != null) {
						updatedGemocLanguageProject = res.getProject();
					}
				}

				/*
				 * MessageDialog.openInformation(
				 * HandlerUtil.getActiveWorkbenchWindow(event).getShell(),
				 * "Gemoc Language Workbench UI",
				 * "Create Domain Model Project command was executed. Selected elment ="
				 * +element.toString());
				 */
			}
		}
		else if (selection != null & selection instanceof ITextSelection) {
			IResource res = (IResource) HandlerUtil
					.getActiveWorkbenchWindow(event).getActivePage()
					.getActiveEditor().getEditorInput()
					.getAdapter(IResource.class);
			if (res != null) {
				updatedGemocLanguageProject = res.getProject();
			}
		}
		return updatedGemocLanguageProject;
	}

	protected IFile getMelangeFileFromSelection(ExecutionEvent event){
		IFile selectedMelangeIFile = null;
		ISelection selection = HandlerUtil.getActiveWorkbenchWindow(event).getActivePage().getSelection();
		if (selection != null & selection instanceof IStructuredSelection) {
			IStructuredSelection strucSelection = (IStructuredSelection) selection;
			for (@SuppressWarnings("unchecked")
				Iterator<Object> iterator = strucSelection.iterator(); 
				iterator.hasNext();) {
				
				Object element = iterator.next();

				if (element instanceof IFile) {
					selectedMelangeIFile = (IFile) element;

				}
				if (element instanceof IAdaptable) {
					IFile res = (IFile) ((IAdaptable) element)
							.getAdapter(IFile.class);
					if (res != null) {
						selectedMelangeIFile = res;
					}
				}
			}
		}
		return selectedMelangeIFile;
	}
	
	protected Language getMelangeLanguageFromSelection(ExecutionEvent event){
		Language selectedMelangeLanguage = null;
		ISelection selection = HandlerUtil.getActiveWorkbenchWindow(event).getActivePage().getSelection();
		if (selection != null & selection instanceof IStructuredSelection) {
			IStructuredSelection strucSelection = (IStructuredSelection) selection;
			for (@SuppressWarnings("unchecked")
				Iterator<Object> iterator = strucSelection.iterator(); 
				iterator.hasNext();) {
				
				Object element = iterator.next();

				if (element instanceof Language) {
					selectedMelangeLanguage = (Language) element;

				}
				if (element instanceof IAdaptable) {
					Language res = (Language) ((IAdaptable) element).getAdapter(Language.class);
					if (res != null) {
						selectedMelangeLanguage = res;
					}
				}
			}
		}
		else {
			// try selection from xtexteditor
			final XtextEditor editor = org.eclipse.xtext.ui.editor.utils.EditorUtils.getActiveXtextEditor(event);
			if (editor != null) {
				final ITextSelection textSelection = (ITextSelection)editor.getSelectionProvider().getSelection();
				final IUnitOfWork<Language, XtextResource> _function = (XtextResource it) -> {
			        int _offset = textSelection.getOffset();
			        return this.getSelectedLanguage(it, _offset);
				};
				
				final Language lang = editor.getDocument().readOnly(_function);
				if(lang != null){
					return lang;
				}
			}
		}
		return selectedMelangeLanguage;
	}
	
	protected Language getSelectedLanguage(XtextResource resource, int offset){
		final EObjectAtOffsetHelper eObjectAtOffsetHelper =
			resource.getResourceServiceProvider().get(EObjectAtOffsetHelper.class);
		EObject selectedElement = eObjectAtOffsetHelper.resolveContainedElementAt(resource, offset);
		if (selectedElement != null) {
			EObject currentElem = selectedElement;
			while(currentElem != null){
				if(currentElem instanceof Language){
					return (Language)currentElem;
				}
				currentElem = currentElem.eContainer();
			}
		}
		return null;
	}
	
}
