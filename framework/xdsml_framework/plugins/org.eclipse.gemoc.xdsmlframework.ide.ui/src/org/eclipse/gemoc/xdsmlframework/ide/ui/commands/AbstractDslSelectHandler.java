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
import org.eclipse.core.resources.IProject;

/**
 * This handler allows to use the input selection to retrieve a Melange language and run an action
 * it either search directly in the selection  or ask to the user the Melange file and the melange language depending on the selection content
 * @author dvojtise
 *
 */
public abstract class AbstractDslSelectHandler extends AbstractGemocLanguageProjectHandler {
	
	public static String MELANGE_EDITOR = "fr.inria.diverse.melange.Melange";

	public abstract Object executeForSelectedLanguage(ExecutionEvent event, IProject updatedGemocLanguageProject,
			String language) throws ExecutionException;

	public abstract String getSelectionMessage();

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {

		// get the optional selection and eventually project data to preset the
		// wizard
		final IProject updatedGemocLanguageProject = getUpdatedGemocLanguageProjectFromSelection(event);

		String lang = getDslNameFromSelection(event);
		if(lang != null && updatedGemocLanguageProject != null){
			// go for it, we have everything in the selection
			executeForSelectedLanguage(event, updatedGemocLanguageProject, lang);
		}
		return null;
	}

}
