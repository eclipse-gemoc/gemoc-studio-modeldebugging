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
package org.eclipse.gemoc.trace.commons;

import java.io.File;
import java.io.IOException;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

public class EMFUtil {

	public static void registerXMIFactory(ResourceSet resourceSet) {
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
				.put("xmi", new XMIResourceFactoryImpl());
	}
	
	public static void registerEcoreFactory(ResourceSet resourceSet) {
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
				.put("ecore", new EcoreResourceFactoryImpl());
	}
	
	public static URI createFileURI(String path) {
		return URI.createFileURI(createFile(path).getAbsolutePath());
	}
	
	public static File createFile(String path) {
		return new File(path);
	}
	
	public static Resource loadModelURI(URI uri, ResourceSet rs) throws IOException {
		Resource res = rs.createResource(uri);
		res.load(null);
		EcoreUtil.resolveAll(rs);// IMPORTANT
		return res;
	}
	
	public static Resource loadModelURI(String uri, ResourceSet rs) throws IOException {
		Resource res = rs.createResource(URI.createURI(uri));
		res.load(null);
		EcoreUtil.resolveAll(rs);// IMPORTANT
		return res;
	}

	public static Resource loadModelPath(String path, ResourceSet rs) throws IOException {
		Resource res = rs.createResource(EMFUtil.createFileURI(path));
		res.load(null);
		EcoreUtil.resolveAll(rs);// IMPORTANT
		return res;
	}

}
