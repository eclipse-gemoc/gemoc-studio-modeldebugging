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
package org.eclipse.gemoc.execution.sequential.javaxdsml.ide.ui.builder;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectNature;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.gemoc.commons.eclipse.core.resources.IProjectUtils;
import org.eclipse.gemoc.xdsmlframework.ide.ui.builder.GemocLanguageProjectNature;

/**
 * This nature is mainly to help eclipse UI to have the proper popup menus
 * There is no need for a builder since it is supposed to be done by the generic GemocLanguageProjectBuilder in org.eclipse.gemoc.xdsmlframework.ide.ui
 *
 */
public class GemocSequentialLanguageNature implements IProjectNature {

	/**
	 * ID of this project nature
	 */
	public static final String NATURE_ID = "org.eclipse.gemoc.execution.sequential.javaxdsml.ide.ui.GemocSequentialLanguageNature";

	private IProject project;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.resources.IProjectNature#configure()
	 */
	public void configure() throws CoreException {

		// configure the requirement 
		requirementConfigure();
		
		/*  no builder for now
		IProjectDescription desc = project.getDescription();
		ICommand[] commands = desc.getBuildSpec();

		for (int i = 0; i < commands.length; ++i) {
			if (commands[i].getBuilderName().equals(GemocSequentialLanguageBuilder.BUILDER_ID)) {
				return;
			}
		} 

		ICommand[] newCommands = new ICommand[commands.length + 1];
		System.arraycopy(commands, 0, newCommands, 0, commands.length);
		ICommand command = desc.newCommand();
		command.setBuilderName(GemocSequentialLanguageBuilder.BUILDER_ID);
		newCommands[newCommands.length - 1] = command;
		desc.setBuildSpec(newCommands);
		project.setDescription(desc, null);
		*/
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.resources.IProjectNature#deconfigure()
	 */
	public void deconfigure() throws CoreException {
		/*
		IProjectDescription description = getProject().getDescription();
		ICommand[] commands = description.getBuildSpec();
		for (int i = 0; i < commands.length; ++i) {
			if (commands[i].getBuilderName().equals(GemocSequentialLanguageBuilder.BUILDER_ID)) {
				ICommand[] newCommands = new ICommand[commands.length - 1];
				System.arraycopy(commands, 0, newCommands, 0, i);
				System.arraycopy(commands, i + 1, newCommands, i,
						commands.length - i - 1);
				description.setBuildSpec(newCommands);
				project.setDescription(description, null);			
				return;
			}
		}
		*/
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.resources.IProjectNature#getProject()
	 */
	public IProject getProject() {
		return project;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.resources.IProjectNature#setProject(org.eclipse.core.resources.IProject)
	 */
	public void setProject(IProject project) {
		this.project = project;
	}
	
	/**
	 * Action done during the configure() that cannot be undone during the deconfigure()
	 * this is the case of the addition of required natures, and changes in the files (plugin, manifest, ...)
	 * @throws CoreException
	 */
	public void requirementConfigure() throws CoreException {
		IProjectUtils.addNature(project, GemocLanguageProjectNature.NATURE_ID);
	}

}
