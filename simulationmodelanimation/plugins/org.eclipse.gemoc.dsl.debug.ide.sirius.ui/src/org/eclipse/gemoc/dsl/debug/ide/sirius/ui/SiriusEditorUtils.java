/*******************************************************************************
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.gemoc.dsl.debug.ide.sirius.ui;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.business.api.query.EObjectQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.ui.business.api.dialect.DialectEditor;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.business.api.dialect.marker.TraceabilityMarkerNavigationProvider;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.sirius.viewpoint.DView;
import org.eclipse.sirius.viewpoint.ViewpointPackage;

/**
 * Sirius editors utility class.
 *
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public final class SiriusEditorUtils {

	/**
	 * Constructor.
	 */
	private SiriusEditorUtils() {
		// nothing to do here
	}

	/**
	 * Gets the {@link List} of {@link Session} referencing the given semantic {@link URI}.
	 * 
	 * @param uri
	 *            the semantic {@link URI}
	 * @return the {@link List} of {@link Session} referencing the given semantic {@link URI}
	 */
	public static List<Session> getSessions(URI uri) {
		final List<Session> res = new ArrayList<Session>();
		final URI resourceURI = uri.trimFragment();
		for (Session session : SessionManager.INSTANCE.getSessions()) {
			for (Resource resource : session.getSemanticResources()) {
				if (resourceURI.equals(resource.getURI())) {
					res.add(session);
					break;
				}
			}
		}

		return res;
	}

	/**
	 * Gets the {@link List} of {@link DRepresentation} representing the given instruction.
	 * 
	 * @param session
	 *            the {@link Session}
	 * @param instructionURI
	 *            the instruction {@link URI}
	 * @return the {@link List} of {@link DRepresentation} representing the given instruction
	 */
	public static List<DRepresentation> getRepresentations(Session session, URI instructionURI) {
		final List<DRepresentation> res = new ArrayList<DRepresentation>();

		final EObject instruction = session.getTransactionalEditingDomain().getResourceSet().getEObject(
				instructionURI, false);

		for (DView view : session.getSelectedViews()) {
			for (DRepresentationDescriptor rdescriptor : view.getOwnedRepresentationDescriptors()) {
				DRepresentation representation = rdescriptor.getRepresentation();
				if (representSemanticElement(representation, instruction)) {
					res.add(representation);
				}
			}
		}

		return res;
	}

	/**
	 * Tells if the given {@link DRepresentation} represents the given {@link EObject instruction}.
	 * 
	 * @param representation
	 *            the {@link DRepresentation}
	 * @param instruction
	 *            the {@link EObject instruction}
	 * @return <code>true</code> if the given {@link DRepresentation} represents the given {@link EObject
	 *         instruction}, <code>false</code> otherwise
	 */
	public static boolean representSemanticElement(DRepresentation representation, EObject instruction) {
		boolean res = false;

		if (representation.eCrossReferences().contains(instruction)) {
			res = true;
		} else {
			for (final DRepresentationElement representationElement : representation
					.getRepresentationElements()) {
				if (representationElement.eCrossReferences().contains(instruction)) {
					res = true;
					break;
				}
			}
		}

		return res;
	}

	/**
	 * Show the given {@link EObject instruction}.
	 * 
	 * @param editorPart
	 *            the opened {@link DialectEditor}
	 * @param instruction
	 *            the {@link EObject instruction} to show
	 */
	public static void showInstruction(DialectEditor editorPart, EObject instruction) {
		final URI resourceURI = instruction.eResource().getURI();
		if (resourceURI.isPlatformResource()) {
			final String resourcePath = resourceURI.toPlatformString(true);
			final IResource resource = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(
					resourcePath));
			try {
				final IMarker marker = resource.createMarker(EValidator.MARKER);
				marker.setAttribute(EValidator.URI_ATTRIBUTE, EcoreUtil.getURI(instruction).toString());
				final TraceabilityMarkerNavigationProvider navigationProvider = new TraceabilityMarkerNavigationProvider(
						(DialectEditor)editorPart);
				navigationProvider.gotoMarker(marker);
				marker.delete();
			} catch (CoreException e) {
				DebugSiriusIdeUiPlugin.INSTANCE.log(e);
			}
		}
	}

	/**
	 * Show the given {@link List} of {@link EObject instruction}.
	 * 
	 * @param editorPart
	 *            the opened {@link DialectEditor}
	 * @param instructions
	 *            the {@link List} of {@link EObject instruction} to show
	 */
	public static void showInstructions(DialectEditor editorPart, List<EObject> instructions) {
		Set<DRepresentationElement> representationElements = new LinkedHashSet<DRepresentationElement>();
		for (EObject instruction : instructions) {
			for (EObject eObj : new EObjectQuery(instruction).getInverseReferences(ViewpointPackage.eINSTANCE
					.getDRepresentationElement_SemanticElements())) {
				if (eObj instanceof DRepresentationElement) {
					representationElements.add((DRepresentationElement)eObj);
				}
			}
			showInstruction(editorPart, instruction);
		}
		DialectUIManager.INSTANCE.setSelection(editorPart, new ArrayList<DRepresentationElement>(
				representationElements));
	}
}
