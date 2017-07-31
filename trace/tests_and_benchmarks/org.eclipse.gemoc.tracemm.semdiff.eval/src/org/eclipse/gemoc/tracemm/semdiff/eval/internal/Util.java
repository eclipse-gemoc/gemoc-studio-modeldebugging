/*******************************************************************************
 * Copyright (c) 2016 Inria and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Inria - initial API and implementation
 *******************************************************************************/
package org.eclipse.gemoc.tracemm.semdiff.eval.internal;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.modelexecution.xmof.vm.util.EMFUtil;

public abstract class Util {

	public static Resource loadResource(ResourceSet resourceSet, String filePath) {
		URI uri = null;
		if (filePath.contains("platform:/plugin/")) {
			uri = EMFUtil.createPlatformPluginURI(filePath.replaceAll(
					"platform:/plugin", ""));
		} else if (filePath.contains("platform:/resource/")) {
			uri = EMFUtil.createPlatformPluginURI(filePath.replaceAll(
					"platform:/resource", ""));
		} else {
			uri = EMFUtil.createFileURI(filePath);
		}
		return EMFUtil.loadResource(resourceSet, uri);
	}

}
