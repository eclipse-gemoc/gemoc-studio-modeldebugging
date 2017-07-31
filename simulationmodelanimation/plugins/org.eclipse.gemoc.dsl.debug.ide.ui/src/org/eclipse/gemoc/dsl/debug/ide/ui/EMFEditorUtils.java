/*******************************************************************************
 * Copyright (c) 2015, 2017 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.gemoc.dsl.debug.ide.ui;

import org.eclipse.gemoc.dsl.debug.ide.DSLBreakpoint;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.ui.URIEditorInput;
import org.eclipse.emf.common.ui.viewer.IViewerProvider;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;

/**
 * Utility class for EMF editors.
 *
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public final class EMFEditorUtils {

	/**
	 * Constructor.
	 */
	private EMFEditorUtils() {
		// nothing to do here
	}

	/**
	 * Gets the default {@link IEditorInput} for the given {@link Object}.
	 * 
	 * @param element
	 *            the {@link Object}
	 * @return the default {@link IEditorInput} for the given {@link Object} if any, <code>null</code>
	 *         otherwise
	 */
	public static IEditorInput getEditorInput(Object element) {
		final IEditorInput res;
		if (element instanceof EObject) {
			Resource resource = ((EObject)element).eResource();
			if (resource != null) {
				res = getEditorInputFromURI(resource.getURI());
			} else {
				res = null;
			}
		} else if (element instanceof DSLBreakpoint) {
			res = getEditorInputFromURI(((DSLBreakpoint)element).getURI().trimFragment());
		} else {
			res = null;
		}
		return res;
	}

	/**
	 * Gets the {@link IEditorInput} from the given {@link URI}.
	 * 
	 * @param uri
	 *            the {@link URI}
	 * @return the {@link IEditorInput} from the given {@link URI} or <code>null</code> if none can be created
	 */
	public static IEditorInput getEditorInputFromURI(URI uri) {
		final IEditorInput res;

		if (uri != null) {
			if (uri.isPlatformResource()) {
				String path = uri.toPlatformString(true);
				IResource workspaceResource = ResourcesPlugin.getWorkspace().getRoot().findMember(
						new Path(path));
				if (workspaceResource instanceof IFile) {
					res = new FileEditorInput((IFile)workspaceResource);
				} else {
					res = new URIEditorInput(uri);
				}
			} else {
				res = new URIEditorInput(uri);
			}
		} else {
			res = null;
		}

		return res;
	}

	/**
	 * Gets the default editor ID for the given {@link IEditorInput} and element.
	 * 
	 * @param input
	 *            the {@link IEditorInput}
	 * @param element
	 *            the element
	 * @return the default editor ID for the given {@link IEditorInput} and element if any, <code>null</code>
	 *         otherwise
	 */
	public static String getEditorID(IEditorInput input, Object element) {
		final String res;
		if (input instanceof URIEditorInput) {
			res = PlatformUI.getWorkbench().getEditorRegistry().getDefaultEditor(
					((URIEditorInput)input).getURI().lastSegment()).getId();
		} else if (input instanceof IFileEditorInput) {
			IEditorDescriptor defaultEditor = PlatformUI.getWorkbench().getEditorRegistry().getDefaultEditor(
					((IFileEditorInput)input).getFile().getName());
			if (defaultEditor != null) {
				res = defaultEditor.getId();
			} else {
				res = "org.eclipse.emf.ecore.presentation.ReflectiveEditorID";
			}
		} else {
			res = null;
		}
		return res;
	}

	/**
	 * Selects the given instruction in the given {@link IEditorPart}.
	 * 
	 * @param editorPart
	 *            the {@link IEditorPart}
	 * @param instructionUri
	 *            the instruction {@link URI}
	 */
	public static void selectInstruction(IEditorPart editorPart, final URI instructionUri) {
		if (editorPart instanceof IViewerProvider) {
			if (editorPart instanceof IEditingDomainProvider) {
				final EditingDomain domain = ((IEditingDomainProvider)editorPart).getEditingDomain();
				final EObject selection = domain.getResourceSet().getEObject(instructionUri, false);
				if (selection != null) {
					((IViewerProvider)editorPart).getViewer().setSelection(
							new StructuredSelection(selection), true);
				} else {
					DebugIdeUiPlugin.getPlugin().log(
							new IllegalStateException("can't find source for " + instructionUri));
				}
			}
		}
	}

	/**
	 * Selects the given instruction in the given {@link IEditorPart}.
	 * 
	 * @param editorPart
	 *            the {@link IEditorPart}
	 * @param instructionUris
	 *            the {@link List} of instruction {@link URI}
	 */
	public static void selectInstructions(IEditorPart editorPart, final List<URI> instructionUris) {
		if (editorPart instanceof IViewerProvider) {
			if (editorPart instanceof IEditingDomainProvider) {
				final EditingDomain domain = ((IEditingDomainProvider)editorPart).getEditingDomain();
				final List<EObject> selection = new ArrayList<EObject>();
				for (URI uri : instructionUris) {
					EObject eObject = domain.getResourceSet().getEObject(uri, false);
					if (eObject != null) {
						selection.add(eObject);
					}
				}
				if (!selection.isEmpty()) {
					((IViewerProvider)editorPart).getViewer().setSelection(
							new StructuredSelection(selection), true);
				} else {
					DebugIdeUiPlugin.getPlugin().log(
							new IllegalStateException("can't find source for " + instructionUris));
				}
			}
		}
	}

}
