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
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.gemoc.commons.eclipse.core.resources.FileFinderVisitor;
import org.eclipse.gemoc.dsl.Dsl;
import org.eclipse.gemoc.xdsmlframework.ide.ui.Activator;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;

public abstract class AbstractGemocLanguageProjectHandler extends AbstractHandler {

	public static final String DSL_EXTENSION = "dsl";

	protected IProject getUpdatedGemocLanguageProjectFromSelection(ExecutionEvent event) {
		IProject updatedGemocLanguageProject = null;
		ISelection selection = HandlerUtil.getActiveWorkbenchWindow(event).getActivePage().getSelection();
		if (selection != null & selection instanceof IStructuredSelection) {
			IStructuredSelection strucSelection = (IStructuredSelection) selection;
			for (@SuppressWarnings("unchecked")
			Iterator<Object> iterator = strucSelection.iterator(); iterator.hasNext();) {

				Object element = iterator.next();

				if (element instanceof IResource) {
					updatedGemocLanguageProject = ((IResource) element).getProject();

				}
				if (element instanceof IAdaptable) {
					IResource res = (IResource) ((IAdaptable) element).getAdapter(IResource.class);
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
		} else if (selection != null & selection instanceof ITextSelection) {
			IResource res = (IResource) HandlerUtil.getActiveWorkbenchWindow(event).getActivePage().getActiveEditor()
					.getEditorInput().getAdapter(IResource.class);
			if (res != null) {
				updatedGemocLanguageProject = res.getProject();
			}
		}
		return updatedGemocLanguageProject;
	}

	protected IFile getDslFileFromSelection(ExecutionEvent event) {
		IFile selectedDslIFile = null;
		ISelection selection = HandlerUtil.getActiveWorkbenchWindow(event).getActivePage().getSelection();
		if (selection != null & selection instanceof IStructuredSelection) {
			IStructuredSelection strucSelection = (IStructuredSelection) selection;
			for (@SuppressWarnings("unchecked")
			Iterator<Object> iterator = strucSelection.iterator(); iterator.hasNext();) {

				Object element = iterator.next();

				if (element instanceof IFile && ((IFile) element).getFileExtension().equals(DSL_EXTENSION)) {
					selectedDslIFile = (IFile) element;

				}
				if (element instanceof IAdaptable) {
					IFile res = (IFile) ((IAdaptable) element).getAdapter(IFile.class);
					if (res != null && res.getFileExtension().equals(DSL_EXTENSION)) {
						selectedDslIFile = res;
					}
				}
			}
		}

		if (selectedDslIFile == null) {
			// we will search for all .dsl files in the project
			IProject updatedGemocLanguageProject = getUpdatedGemocLanguageProjectFromSelection(event);
			return getDslFileFromProject(updatedGemocLanguageProject);
		}

		return selectedDslIFile;
	}

	protected IFile getDslFileFromProject(IProject updatedGemocLanguageProject) {
		FileFinderVisitor dslProjectVisitor = new FileFinderVisitor(DSL_EXTENSION);
		try {
			updatedGemocLanguageProject.accept(dslProjectVisitor);
			for (IFile projectDslIFile : dslProjectVisitor.getFiles()) {
				// consider all dsl files in the project
				if (!(projectDslIFile.getFullPath().toString().contains("/bin/")
						| projectDslIFile.getFullPath().toString().contains("/target/"))) {
					return projectDslIFile;
				}
			}
		} catch (CoreException e) {
			Activator.error(e.getMessage(), e);
		}
		return null;
	}

	protected String getDslNameFromSelection(ExecutionEvent event) {
		IFile dslFile = getDslFileFromSelection(event);
		Resource res = (new ResourceSetImpl()).getResource(URI.createURI(dslFile.getLocationURI().toString()), true);
		return ((Dsl) res.getContents().get(0)).getName();
	}
}
