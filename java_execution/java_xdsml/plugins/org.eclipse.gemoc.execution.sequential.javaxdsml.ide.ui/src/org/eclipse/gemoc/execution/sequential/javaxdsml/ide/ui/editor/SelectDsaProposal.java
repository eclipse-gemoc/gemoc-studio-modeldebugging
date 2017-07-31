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
package org.eclipse.gemoc.execution.sequential.javaxdsml.ide.ui.editor;

import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.ui.PlatformUI;
import org.eclipse.gemoc.commons.eclipse.pde.manifest.ManifestChanger;
import org.eclipse.gemoc.commons.eclipse.ui.dialogs.SelectAnyIProjectDialog;
import org.eclipse.gemoc.execution.sequential.javaxdsml.ide.ui.templates.SequentialTemplate;

import fr.inria.diverse.melange.ui.contentassist.IProposal;

public class SelectDsaProposal implements IProposal{
	
	private IProject dsaProject;

	@Override
	public String getDisplayText() {
			return "-- Import existing DSA project --";
	}

	@Override
	public String getReplacementText() {
		SelectAnyIProjectDialog dialog = new SelectAnyIProjectDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell());
		if (dialog.open() == Dialog.OK) {
			Object[] selections = dialog.getResult();
			if(selections != null 
				&& selections.length != 0
				&& selections[0] instanceof IProject 
			){
				dsaProject = (IProject) selections[0];
				Set<String> aspects = SequentialTemplate.getAspectClassesList(dsaProject);
				final StringBuilder insertion = new StringBuilder();
				for (String asp : aspects) {
					insertion.append("\twith " + asp + "\n");
				}
				insertion.replace(0, 1, "");//remove the first \t
				return insertion.toString();
			}
		}
		
		return "";
	}

	@Override
	public void configureProject(IProject project) {
		ManifestChanger manifestChanger = new ManifestChanger(project);
		try {
			manifestChanger.addPluginDependency(dsaProject.getName());
			manifestChanger.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void configureProposal(EObject context) {
		// TODO Auto-generated method stub
	}
}
