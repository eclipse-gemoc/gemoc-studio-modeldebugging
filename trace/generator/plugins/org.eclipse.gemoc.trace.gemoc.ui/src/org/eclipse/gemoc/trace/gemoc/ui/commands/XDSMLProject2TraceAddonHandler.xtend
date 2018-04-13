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
package org.eclipse.gemoc.trace.gemoc.ui.commands

import org.eclipse.gemoc.trace.gemoc.generator.TraceAddonGeneratorIntegration
import org.eclipse.core.commands.ExecutionEvent
import org.eclipse.core.commands.ExecutionException
import org.eclipse.core.commands.IHandler
import org.eclipse.core.resources.IFile
import org.eclipse.core.resources.IProject
import org.eclipse.core.runtime.IProgressMonitor
import org.eclipse.core.runtime.Status
import org.eclipse.core.runtime.jobs.Job
import org.eclipse.jface.dialogs.InputDialog
import org.eclipse.jface.window.Window
import org.eclipse.gemoc.xdsmlframework.ide.ui.xdsml.wizards.MelangeXDSMLProjectHelper
import org.eclipse.gemoc.xdsmlframework.ide.ui.commands.AbstractDslSelectHandler

/**
 * Handler that allows to get an XDSML project (containing a melange file) 
 * and will ask for selection of the language (if necessary) and launch the generation
 */
class XDSMLProject2TraceAddonHandler extends AbstractDslSelectHandler implements IHandler {
	val static String pluginId = "org.eclipse.gemoc.trace.gemoc.ui"

	override Object executeForSelectedLanguage(ExecutionEvent event, IProject updatedGemocLanguageProject,
		String language) throws ExecutionException {

		val IFile dslFile = getDslFileFromSelection(event);
		val baseProjectName = MelangeXDSMLProjectHelper.baseProjectName(dslFile.project)

		// If the base project name doesn't end with the language name, we suggest it		
		val suggestedBasePluginName = if (baseProjectName.endsWith(language.toLowerCase))
				baseProjectName
			else
				baseProjectName + "." + language.toLowerCase
		val suggestedPluginName = suggestedBasePluginName + ".trace"

		val InputDialog inputDialog = new InputDialog(null,
			"Create MultiDimensional Trace addon for language " + language, "Enter project name ",
			suggestedPluginName, null)
		inputDialog.open();
		if (inputDialog.getReturnCode() == Window.OK) {
			val String projectName = inputDialog.getValue();
			val Job j = new Job("Generating trace addon plugin for " + dslFile.toString) {
				override protected run(IProgressMonitor monitor) {
					try {

						TraceAddonGeneratorIntegration.generateAddon(dslFile, language, projectName, true,
							monitor)

					} catch (Exception e) {
						return new Status(Status.ERROR, pluginId, "An error occured while generating the trace addon. Please expand below for the complete error stack trace.",e)
					}
					return new Status(Status.OK, pluginId, "Multidimensional Trace addon plugin generated.")
				}
			}
			// And we start the job
			j.schedule
		}

		return null;
	}

	override String getSelectionMessage() {
		return "Select Language that is used to initialize the Multidimensional Trace addon creation wizard";
	}

}
