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
package org.eclipse.gemoc.execution.sequential.javaxdsml.ide.ui.templates;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.ui.PlatformUI;
import org.eclipse.gemoc.commons.eclipse.core.resources.FileFinderVisitor;
import org.eclipse.gemoc.execution.sequential.javaxdsml.ide.ui.Activator;
import org.eclipse.gemoc.execution.sequential.javaxdsml.ide.ui.dialogs.SelectDSAIProjectDialog;
import org.osgi.framework.BundleException;

import org.eclipse.gemoc.commons.eclipse.pde.manifest.ManifestChanger;
import org.eclipse.gemoc.commons.eclipse.pde.wizards.pages.pde.ui.templates.AbstractStringWithButtonOption;
import org.eclipse.gemoc.commons.eclipse.pde.wizards.pages.pde.ui.templates.TemplateOption;
import fr.inria.diverse.melange.ui.templates.melange.SimpleMTTemplate;

public class SequentialTemplate extends SimpleMTTemplate{
	public static final String KEY_ASPECTS = "listOfAspects"; //$NON-NLS-1$
	
	// other data specific to this template
	protected String 	dsaProjectName;
	
	private TemplateOption dsaProjectLocationOption;
	
	public SequentialTemplate() {
		super();
		dsaProjectLocationOption  = new AbstractStringWithButtonOption(this, KEY_ASPECTS, "DSA project") {

			@Override
			public String doSelectButton() {
				
				SelectDSAIProjectDialog dialog = new SelectDSAIProjectDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell());
				dialog.open();
				
				Object[] selection = dialog.getResult();
				if(selection != null && selection.length > 0){
					dsaProjectName = ((IProject) selection[0]).getName();
					return dsaProjectName;
				}
				
				return null;
			}
		};
		dsaProjectLocationOption.setRequired(false);
		registerOption(dsaProjectLocationOption, (String) null, 0);
	}
	
	public void updateOptions(String packageName, String languageName){
		TemplateOption[] allOptions = getOptions(0);
		for(TemplateOption option : allOptions){
			if(option.getName().equals(KEY_PACKAGE_NAME) && packageName != null){
				option.setValue(packageName);
			}
			else if(option.getName().equals(KEY_METAMODEL_NAME) && languageName != null){
				option.setValue(languageName);
			}
		}
	}
	
	@Override
	public String getSectionId() {
		return "SequentialLanguage";
	}
	
	@Override
	public URL getTemplateLocation() {
		// Need to override to get the local Activator
		try {
			String[] candidates = getDirectoryCandidates();
			for (int i = 0; i < candidates.length; i++) {
				if (Activator.getDefault().getBundle().getEntry(candidates[i]) != null) {
					URL candidate = new URL(getInstallURL(), candidates[i]);
					return candidate;
				}
			}
		} catch (MalformedURLException e) { // do nothing
		}
		return null;
	}
	
	@Override
	protected URL getInstallURL() {
		// Need to override to get the local Activator
		return Activator.getDefault().getBundle().getEntry("/");
	}
	
	/** find the mapping properties file in the project and retrieves the aspectClasses
	 * 
	 * @param k3IProject
	 * @return
	 */
	public static Set<String> getAspectClassesList(IProject k3IProject){
		HashSet<String> aspectClasses = new HashSet<String>();
		FileFinderVisitor projectVisitor = new FileFinderVisitor("properties");
		try {
			//ResourcesPlugin.getWorkspace().getRoot().getProject(languageDefinition.getDsaProject().getProjectName());
			k3IProject.accept(projectVisitor);
			List<IFile> possibleAspectMappingPropertiesFiles = projectVisitor.getFiles();
			for(IFile aspectMappingPropertiesFile : possibleAspectMappingPropertiesFiles){
				if(aspectMappingPropertiesFile.getName().endsWith("k3_aspect_mapping.properties")){
					Properties properties = new Properties();
					if(aspectMappingPropertiesFile.exists()){
						try {
							properties.load(aspectMappingPropertiesFile.getContents());
							for(Object commaSeparatedPropvalues:properties.values()){
								for(String propVal :((String)commaSeparatedPropvalues).split(",")){
									aspectClasses.add(propVal);
								}
							}
						} catch (IOException e) {
							Activator.error(e.getMessage(), e);
						} catch (CoreException e) {
							Activator.error(e.getMessage(), e);
						}
					}
				}
			}
			
		} catch (CoreException e) {
			Activator.error(e.getMessage(), e);
		}
		return aspectClasses;
	}
	
	@Override
	public void execute(IProject project, IProgressMonitor monitor)
			throws CoreException {
		
		//Replace KEY_ASPECTS' value (which is a project name) by a list of aspects 
		final String DEFAULT_VALUE = "/*\n *\twith qualified.class.name\n */\n";
		
		String selection = dsaProjectName;
		if(selection != null && !selection.isEmpty()){
			
			IProject dsaProject = ResourcesPlugin.getWorkspace().getRoot().getProject(selection);
			Set<String> aspects = getAspectClassesList(dsaProject);
			
			StringBuilder templateWith = new StringBuilder();
			if(aspects.isEmpty()){
				templateWith.append(DEFAULT_VALUE);
			}
			else{
				for(String aspect : aspects){
					templateWith.append("\twith "+aspect+"\n");
				}
			}
			dsaProjectLocationOption.setValue(templateWith.toString());
		}
		else{
			dsaProjectLocationOption.setValue(DEFAULT_VALUE);
		}
		
		//Then do the normal stuff
		super.execute(project, monitor);
	}
	
	@Override
	protected void generateFiles(IProgressMonitor monitor) throws CoreException {
		super.generateFiles(monitor);
		
		// now also fix the project configuration
		if(this.dsaProjectName != null && !this.dsaProjectName.isEmpty()){
			ManifestChanger manifestChanger;
			try {
				manifestChanger = new ManifestChanger(project.getFile("META-INF/MANIFEST.MF"));
				manifestChanger.addPluginDependency(this.dsaProjectName, "0.0.0", false, true);
				manifestChanger.commit();
			} catch (IOException | BundleException e) {
			}
		}
	}
}
