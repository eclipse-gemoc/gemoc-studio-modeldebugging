/*******************************************************************************
 * Copyright (c) 2016, 2019 Inria and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Inria - initial API and implementation
 *******************************************************************************/
package org.eclipse.gemoc.execution.sequential.javaxdsml.ide.ui.builder;

import java.util.Iterator;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.gemoc.commons.eclipse.core.resources.IProjectUtils;
import org.eclipse.gemoc.execution.sequential.javaxdsml.ide.ui.Activator;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;


public class AddRemoveGemocSequentialLanguageNatureHandler extends AbstractHandler {

	//private ISelection selection;

	public Object execute(ExecutionEvent event) throws ExecutionException {
		ISelection selection = HandlerUtil.getCurrentSelection(event);
		//
		if (selection instanceof IStructuredSelection) {
			for (Iterator<?> it = ((IStructuredSelection) selection).iterator(); it
					.hasNext();) {
				Object element = it.next();
				IProject project = null;
				if (element instanceof IProject) {
					project = (IProject) element;
				} else if (element instanceof IAdaptable) {
					project = (IProject) ((IAdaptable) element)
							.getAdapter(IProject.class);
				}
				if (project != null) {
					try {
						IProjectUtils.toggleNature(project, GemocSequentialLanguageNature.NATURE_ID);
					} catch (CoreException e) {
						Activator.error("Failed to toggle nature "+GemocSequentialLanguageNature.NATURE_ID+" on "+project.getName(), e);
					}
				}
			}
		}

		return null;
	}
}