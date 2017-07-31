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
package org.eclipse.gemoc.xdsmlframework.ide.ui.commands;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.gemoc.commons.eclipse.core.resources.FileFinderVisitor;
import org.eclipse.gemoc.xdsmlframework.ide.ui.Activator;
import org.eclipse.gemoc.xdsmlframework.ui.utils.dialogs.SelectAnyMelangeLanguageDialog;

import fr.inria.diverse.melange.metamodel.melange.Language;


/**
 * This handler allows to use the input selection to retrieve a Melange language and run an action
 * it either search directly in the selection  or ask to the user the Melange file and the melange language depending on the selection content
 * @author dvojtise
 *
 */
public abstract class AbstractMelangeSelectHandler extends AbstractGemocLanguageProjectHandler {
	
	public static String MELANGE_EDITOR = "fr.inria.diverse.melange.Melange";

	public abstract Object executeForSelectedLanguage(ExecutionEvent event, IProject updatedGemocLanguageProject,
			Language language) throws ExecutionException;

	public abstract String getSelectionMessage();

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {

		// get the optional selection and eventually project data to preset the
		// wizard
		final IProject updatedGemocLanguageProject = getUpdatedGemocLanguageProjectFromSelection(event);

		Language lang = getMelangeLanguageFromSelection(event);
		if(lang != null && updatedGemocLanguageProject != null){
			// go for it, we have everything in the selection
			executeForSelectedLanguage(event, updatedGemocLanguageProject, lang);
			return null;
		}
		
		final IFile melangeFile = getMelangeFileFromSelection(event);
		final ResourceSet rs = new ResourceSetImpl();
		if (melangeFile != null) {
			// we have only one Melange file in the selection
			final URI uri = URI.createPlatformResourceURI(melangeFile.getFullPath().toString(), true);
			rs.getResource(uri, true);
		} else {
			// we will search for all .melange files in the project
			FileFinderVisitor melangeProjectVisitor = new FileFinderVisitor("melange");
			try {
				updatedGemocLanguageProject.accept(melangeProjectVisitor);
				for (IFile projectMelangeIFile : melangeProjectVisitor.getFiles()) {
					// consider all melange files in the project, get them in
					// the ResourceSet
					if (!(projectMelangeIFile.getFullPath().toString().contains("/bin/") | projectMelangeIFile
							.getFullPath().toString().contains("/target/"))) {
						// FIXME stupid check to remove some typical duplicates,
						// a better impl should look into java output

						final URI uri = URI.createPlatformResourceURI(projectMelangeIFile.getFullPath().toString(),
								true);
						rs.getResource(uri, true);
					}
				}
			} catch (CoreException e) {
				Activator.error(e.getMessage(), e);
			}
		}

		final LabelProvider labelProvider = new LabelProvider() {
			public String getText(Object element) {
				if (element instanceof Language) {
					return ((Language) element).getName();
				} else
					return super.getText(element);
			}
		};
		final SelectAnyMelangeLanguageDialog dialog = new SelectAnyMelangeLanguageDialog(rs, labelProvider);
		dialog.setTitle("Select Melange language");
		dialog.setMessage(getSelectionMessage());
		final int res = dialog.open();
		if (res == WizardDialog.OK) {
			lang = (Language) dialog.getFirstResult();
			executeForSelectedLanguage(event, updatedGemocLanguageProject, lang);
		}

		return null;
	}

}
