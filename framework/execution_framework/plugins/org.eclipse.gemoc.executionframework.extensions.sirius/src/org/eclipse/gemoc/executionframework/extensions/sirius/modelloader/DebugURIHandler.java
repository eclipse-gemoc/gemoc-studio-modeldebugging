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
package org.eclipse.gemoc.executionframework.extensions.sirius.modelloader;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.URIHandler;
import org.eclipse.emf.ecore.resource.impl.URIHandlerImpl;

/**
 * Creates {@link InputStream} from the worskpace and {@link OutputStream} to
 * nowhere. This prevent saving files.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 *
 */
public final class DebugURIHandler extends URIHandlerImpl implements URIHandler {

	
	private List<URIHandler>  otherHandlers;
	
	public DebugURIHandler(EList<URIHandler> uriHandlers) {
		otherHandlers = new ArrayList<>();
		otherHandlers.addAll(uriHandlers);
	}

	@Override
	public boolean canHandle(URI uri) {
		return uri.fileExtension() == null || !uri.fileExtension().equals("trace");
	}
	
	@Override
	public InputStream createInputStream(URI uri, Map<?, ?> options) throws IOException {
		for (URIHandler handler : otherHandlers) {
			if (handler.canHandle(uri))
	        {
				return handler.createInputStream(uri, options);
	        }
		}
		return super.createInputStream(uri, options);
	}

	@Override
	public OutputStream createOutputStream(URI uri, Map<?, ?> options)
			throws IOException {
		final OutputStream res = new OutputStream() {

			@Override
			public void write(int b) throws IOException {
				// noting to do here
			}
		};
		return res;
	}

}