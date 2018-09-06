/*******************************************************************************
 * Copyright (c) 2008, 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.gemoc.executionframework.extensions.sirius.debug;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.internal.session.danalysis.DAnalysisSessionImpl;
import org.eclipse.sirius.common.tools.api.editing.EditingDomainFactoryService;
import org.eclipse.sirius.common.tools.api.util.SiriusCrossReferenceAdapterImpl;
import org.eclipse.sirius.tools.internal.resource.ResourceSetUtil;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.sirius.viewpoint.ViewpointFactory;

/**
 * Debug {@link Session} factory.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 *
 */
@SuppressWarnings("restriction")
public final class DebugSessionFactory {

	/**
	 * The instance.
	 */
	public final static DebugSessionFactory INSTANCE = new DebugSessionFactory();

	/** Avoid instantiation. */
	private DebugSessionFactory() {
	}

	/**
	 * {@inheritDoc}
	 */
	public Session createSession(ResourceSet set, URI sessionResourceURI) throws CoreException {
		return createSession(set, sessionResourceURI, new NullProgressMonitor());
	}

	public Session createSession(ResourceSet set, final URI sessionResourceURI, IProgressMonitor monitor) throws CoreException {
		final TransactionalEditingDomain transactionalEditingDomain = EditingDomainFactoryService.INSTANCE.getEditingDomainFactory().createEditingDomain(set);

		// Configure the resource set, its is done here and not before the
		// editing domain creation which could provide its own resource set.
		transactionalEditingDomain.getResourceSet().eAdapters().add(new SiriusCrossReferenceAdapterImpl());

		// Create or load the session.
		boolean alreadyExistingResource = exists(sessionResourceURI, transactionalEditingDomain.getResourceSet());
		Session session = null;
		if (alreadyExistingResource) {
			session = loadSessionModelResource(sessionResourceURI, transactionalEditingDomain, monitor);
		} else {
			session = createSessionResource(sessionResourceURI, transactionalEditingDomain, monitor);
		}
		return session;
	}

	/**
	 * Used to check if a Resource exists at this URI with EMF 2.3 since from
	 * EMF 2.4 URIConverter.exists(URI) can be used.
	 * 
	 * @param sessionResourceURI
	 *            the URI of the Resource to which checks existence
	 * @param resourceSet
	 * @return true if a Resource exists at this URI
	 */
	private boolean exists(URI sessionResourceURI, ResourceSet resourceSet) {
		boolean exists = false;
		InputStream inputStream = null;
		try {
			inputStream = resourceSet.getURIConverter().createInputStream(sessionResourceURI);
			if (inputStream != null) {
				exists = true;
			}
		} catch (IOException e) {
			// Do nothing, we consider that the resource does not exist
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					// Do nothing, we consider that the resource does not exist
				}
			}
		}
		return exists;
	}

	private Session loadSessionModelResource(URI sessionResourceURI, TransactionalEditingDomain transactionalEditingDomain, IProgressMonitor monitor)
			throws CoreException {
		ResourceSet resourceSet = transactionalEditingDomain.getResourceSet();
		// Make ResourceSet aware of resource loading with progress monitor
		
		// !!! WARNING !!! 
		// The following line is setting the URIConverter of the resource set.
		//ResourceSetUtil.setProgressMonitor(resourceSet, new SubProgressMonitor(monitor, 2));

		Session session = null;
		try {
			monitor.beginTask("Session loading", 4);
			// Get resource
			final Resource sessionModelResource = resourceSet.getResource(sessionResourceURI, true);
			if (sessionModelResource != null) {
				DAnalysis analysis = null;
				if (!sessionModelResource.getContents().isEmpty() && (sessionModelResource.getContents().get(0) instanceof DAnalysis)) {
					analysis = (DAnalysis) sessionModelResource.getContents().get(0);
					session = new DAnalysisSessionImpl(analysis);
					monitor.worked(2);
				} else {
					session = createSessionResource(sessionResourceURI, transactionalEditingDomain, new SubProgressMonitor(monitor, 2));
				}
			}
		} catch (WrappedException e) {
			throw new CoreException(new Status(IStatus.ERROR, SiriusPlugin.ID, "Error while loading representations file", e));
		} finally {
			monitor.done();
			ResourceSetUtil.resetProgressMonitor(resourceSet);
		}
		return session;
	}

	private Session createSessionResource(final URI sessionResourceURI, final TransactionalEditingDomain transactionalEditingDomain, IProgressMonitor monitor)
			throws CoreException {
		Session session = null;
		try {
			monitor.beginTask("Session creation", 2);
			Resource sessionModelResource = new ResourceSetImpl().createResource(sessionResourceURI);
			DAnalysis analysis = ViewpointFactory.eINSTANCE.createDAnalysis();
			sessionModelResource.getContents().add(analysis);
			try {
				sessionModelResource.save(Collections.emptyMap());
			} catch (IOException e) {
				throw new CoreException(new Status(IStatus.ERROR, SiriusPlugin.ID, "session creation failed", e));
			}
			monitor.worked(1);
			// Now load it from the TED
			sessionModelResource = transactionalEditingDomain.getResourceSet().getResource(sessionResourceURI, true);
			if (sessionModelResource.getContents().isEmpty()) {
				throw new CoreException(new Status(IStatus.ERROR, SiriusPlugin.ID, "session creation failed: the resource content is empty."));
			}
			analysis = (DAnalysis) sessionModelResource.getContents().get(0);
			session = new DAnalysisSessionImpl(analysis);
			monitor.worked(1);
		} finally {
			monitor.done();
		}
		return session;
	}

	/**
	 * {@inheritDoc}
	 */
	public Session createDefaultSession(ResourceSet set, URI sessionResourceURI) throws CoreException {
		return createSession(set, sessionResourceURI, new NullProgressMonitor());
	}

}
