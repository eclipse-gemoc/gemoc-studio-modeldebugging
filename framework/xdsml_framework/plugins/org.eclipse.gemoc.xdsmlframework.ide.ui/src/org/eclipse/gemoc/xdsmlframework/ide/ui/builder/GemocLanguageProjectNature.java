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
package org.eclipse.gemoc.xdsmlframework.ide.ui.builder;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.ICommand;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IProjectNature;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.gemoc.commons.eclipse.jdt.JavaProject;
import org.eclipse.gemoc.commons.eclipse.pde.manifest.ManifestChanger;
import org.eclipse.gemoc.commons.eclipse.pde.ui.PluginConverter;
import org.eclipse.gemoc.xdsmlframework.ide.ui.Activator;
import org.eclipse.gemoc.xdsmlframework.ide.ui.builder.pde.PluginXMLHelper;
import org.osgi.framework.BundleException;

public class GemocLanguageProjectNature implements IProjectNature {

	/**
	 * ID of this project nature
	 */
	public static final String NATURE_ID = Activator.PLUGIN_ID+".GemocLanguageProjectNature";

	private IProject project;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.resources.IProjectNature#configure()
	 */
	public void configure() throws CoreException {
		// configure the requirement 
		requirementConfigure();
		
		IProjectDescription desc = project.getDescription();
		ICommand[] commands = desc.getBuildSpec();

		for (int i = 0; i < commands.length; ++i) {
			if (commands[i].getBuilderName().equals(GemocLanguageProjectBuilder.BUILDER_ID)) {
				return;
			}
		}

		ICommand[] newCommands = new ICommand[commands.length + 1];
		System.arraycopy(commands, 0, newCommands, 0, commands.length);
		ICommand command = desc.newCommand();
		command.setBuilderName(GemocLanguageProjectBuilder.BUILDER_ID);
		newCommands[newCommands.length - 1] = command;
		desc.setBuildSpec(newCommands);
		project.setDescription(desc, null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.resources.IProjectNature#deconfigure()
	 */
	public void deconfigure() throws CoreException {
		IProjectDescription description = getProject().getDescription();
		ICommand[] commands = description.getBuildSpec();
		for (int i = 0; i < commands.length; ++i) {
			if (commands[i].getBuilderName().equals(GemocLanguageProjectBuilder.BUILDER_ID)) {
				ICommand[] newCommands = new ICommand[commands.length - 1];
				System.arraycopy(commands, 0, newCommands, 0, i);
				System.arraycopy(commands, i + 1, newCommands, i,
						commands.length - i - 1);
				description.setBuildSpec(newCommands);
				project.setDescription(description, null);			
				return;
			}
		}
	}

	/**
	 * Action done during the configure() that cannot be undone during the deconfigure()
	 * this is the case of the addition of required natures, and changes in the files (plugin, manifest, ...)
	 * @throws CoreException
	 */
	public void requirementConfigure() throws CoreException {
		try {
			JavaProject.create(project);
			addPluginNature(project);
			updateManifestFile(project);
		} catch (IOException e) {
			Activator.error("Problem while adding Gemoc Language nature to project. " + e.getMessage(), e);
		}
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
	
	
	private void addPluginNature(IProject project) throws CoreException {
		if(!project.hasNature("org.eclipse.pde.PluginNature")) 
		{
			try {
				// create first the plugin.xml file
				PluginXMLHelper.createEmptyTemplateFile(project.getFile(PluginXMLHelper.PLUGIN_FILENAME), false);					
				// convert to plugin and add necessary entries in the build.properties
				PluginConverter.convert(project);							
			} 
			catch (InvocationTargetException | InterruptedException e) 
			{
				Activator.error("cannot add org.eclipse.pde.PluginNature nature to project due to " + e.getMessage(), e);
			}								
		}
	}
	private void updateManifestFile(IProject project){
		// complement manifest
		ManifestChanger changer = new ManifestChanger(project);
		try {
			changer.addPluginDependency(org.eclipse.gemoc.xdsmlframework.api.Activator.PLUGIN_ID, "4.0.0", true, true);
			changer.addPluginDependency("org.eclipse.emf.ecore.xmi", "2.8.0", true, true);	
			changer.addPluginDependency("org.eclipse.gemoc.executionframework.engine", "4.0.0", true, true);
			changer.commit();	
		} catch (BundleException | IOException | CoreException e) {
			Activator.error("Failed to update manifest "+e.getMessage(), e);
		}
	}

}
