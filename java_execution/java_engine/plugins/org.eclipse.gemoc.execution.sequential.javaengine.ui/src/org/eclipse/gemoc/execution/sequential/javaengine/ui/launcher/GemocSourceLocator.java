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
package org.eclipse.gemoc.execution.sequential.javaengine.ui.launcher;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.debug.core.model.IStackFrame;
import org.eclipse.debug.internal.ui.DebugUIPlugin;
import org.eclipse.debug.internal.ui.DelegatingModelPresentation;
import org.eclipse.debug.ui.ISourcePresentation;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.gemoc.execution.sequential.javaengine.ui.Activator;

import fr.inria.diverse.melange.resource.MelangeResourceImpl;
import org.eclipse.gemoc.trace.commons.model.trace.Step;
import org.eclipse.gemoc.dsl.debug.ide.DSLSourceLocator;
import org.eclipse.gemoc.dsl.debug.ide.adapter.DSLStackFrameAdapter;

public class GemocSourceLocator extends DSLSourceLocator implements ISourcePresentation {

	@Override
	public Object getSourceElement(IStackFrame stackFrame) {
		final Object res;
		if (stackFrame instanceof DSLStackFrameAdapter) {
			final DSLStackFrameAdapter eStackFrame = (DSLStackFrameAdapter) stackFrame;
			final EObject instruction = eStackFrame.getCurrentInstruction();
			if (instruction instanceof Step) {
				res = ((Step) instruction).getMseoccurrence().getMse();
			} else if (instruction != null) {
				res = instruction;
			} else {
				res = eStackFrame.getContext();
			}
		} else {
			res = null;
		}
		return res;
	}

	@Override
	public IEditorInput getEditorInput(Object element) {
		if (element instanceof EObject) {
			
			EObject eObject = (EObject) element;
			EObject target = eObject;
			
			Resource res = eObject.eResource();
			if(res != null && res.getResourceSet() != null) {
				
				MelangeResourceImpl mr = null;
				for(Resource candidate : res.getResourceSet().getResources()) {
					if(candidate instanceof MelangeResourceImpl) {
						mr = (MelangeResourceImpl) candidate;
						break;
					}
				}
				
				if(mr != null) {
					String uriFragment = res.getURIFragment(eObject);
					target = mr.getWrappedResource().getEObject(uriFragment);
				}
			}
			
			 Resource r = eObject.eResource();
			 if (r instanceof XtextResource) {
				 URI uri = target.eResource().getURI();
			     if(uri.toPlatformString(true) !=  null){
			    	 IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(uri.toPlatformString(true)));
			    	 IFileEditorInput input = new FileEditorInput(file);
			    	 return input;
			     }
			 }
			 else {
				 //Default
			 return getPresentation().getEditorInput(eObject);
			}
		}
		return null;
	}

	@Override
	public String getEditorId(IEditorInput input, Object element) {
		if (element instanceof EObject) {
			EObject eObject = (EObject) element;
			Resource r = eObject.eResource();
			if (r instanceof XtextResource) {
				return ((XtextResource) r).getLanguageName();
			}
		}
		 
		//Default
		ISourcePresentation presentation = getPresentation();
		return presentation.getEditorId(input, element);
	}  
	private ISourcePresentation getPresentation() {
		String id = Activator.DEBUG_MODEL_ID;
		return ((DelegatingModelPresentation)DebugUIPlugin.getModelPresentation()).getPresentation(id);
	}
}
