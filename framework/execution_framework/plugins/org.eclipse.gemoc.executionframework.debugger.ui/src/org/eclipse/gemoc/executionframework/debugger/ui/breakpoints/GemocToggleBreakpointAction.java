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
package org.eclipse.gemoc.executionframework.debugger.ui.breakpoints;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.gemoc.executionframework.debugger.GemocBreakpoint;
import org.eclipse.gemoc.executionframework.engine.ui.launcher.AbstractGemocLauncher;

import fr.inria.diverse.melange.resource.MelangeResource;
import org.eclipse.gemoc.dsl.debug.ide.DSLBreakpoint;
import org.eclipse.gemoc.dsl.debug.ide.sirius.ui.DSLToggleBreakpointsUtils;
import org.eclipse.gemoc.dsl.debug.ide.sirius.ui.action.AbstractToggleBreakpointAction;

/**
 * commons class for all Gemoc based models
 * @author dvojtise
 *
 */
public class GemocToggleBreakpointAction extends AbstractToggleBreakpointAction {

	@Override
	protected String getModelIdentifier() {
		return AbstractGemocLauncher.MODEL_ID;
	}

	@Override
	protected DSLToggleBreakpointsUtils createToggleBreakpointsUtils() {
		return new DSLToggleBreakpointsUtils(getModelIdentifier()){
			
			@Override
			protected DSLBreakpoint createBreakpoint(Object selected,
					EObject instruction) throws CoreException {
				final Resource res = ((EObject)instruction).eResource();
				final ResourceSet resSet = res.getResourceSet();
				final MelangeResource mr = resSet.getResources().stream().filter(r -> {
					return r instanceof MelangeResource;
				}).map(r -> (MelangeResource)r).findFirst().orElse(null);
				if (mr != null) {
					final String fragmentURI = res.getURIFragment(instruction);
					instruction = mr.getWrappedResource().getEObject(fragmentURI);
				}
				return new GemocBreakpoint(identifier, instruction, true);
			}
			
		};
	}
	
}
