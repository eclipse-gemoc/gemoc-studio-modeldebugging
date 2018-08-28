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

import java.io.IOException;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.ICoreRunnable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.gemoc.commons.eclipse.pde.manifest.ManifestChanger;
import org.eclipse.gemoc.dsl.Dsl;
import org.eclipse.gemoc.execution.sequential.javaxdsml.api.extensions.languages.SequentialLanguageDefinitionExtensionPoint;
import org.eclipse.gemoc.execution.sequential.javaxdsml.ide.ui.Activator;
import org.eclipse.gemoc.xdsmlframework.api.extensions.languages.LanguageDefinitionExtensionPoint;
import org.eclipse.gemoc.xdsmlframework.ide.ui.builder.pde.PluginXMLHelper;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.jdom2.Element;
import org.osgi.framework.BundleException;

public class GemocSequentialLanguageBuilder extends IncrementalProjectBuilder {

	class GemocSequentialLanguageProjectDeltaVisitor implements IResourceDeltaVisitor {
		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.core.resources.IResourceDeltaVisitor#visit(org.eclipse.core.resources.IResourceDelta)
		 */
		public boolean visit(IResourceDelta delta) throws CoreException {
			IResource resource = delta.getResource();
			switch (delta.getKind()) {
			case IResourceDelta.ADDED:
				updateProjectPluginConfiguration(resource);
				checkConsistency(resource);
				break;
			case IResourceDelta.REMOVED:
				updateProjectPluginConfiguration(resource);
				checkConsistency(resource);
				break;
			case IResourceDelta.CHANGED:
				updateProjectPluginConfiguration(resource);
				checkConsistency(resource);
				break;
			}
			//return true to continue visiting children.
			return true;
		}
	}

	class GemocSequentialLanguageResourceVisitor implements IResourceVisitor {
		public boolean visit(IResource resource) {
			
			updateProjectPluginConfiguration(resource);
			checkConsistency(resource);
			//return true to continue visiting children.
			if (resource instanceof IFolder || resource instanceof IProject) 
				return true;
			else return false;
		}
	}

	public static final String BUILDER_ID = "org.eclipse.gemoc.execution.sequential.javaxdsml.ide.ui.GemocSequentialLanguageBuilder";

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.internal.events.InternalBuilder#build(int,
	 *      java.util.Map, org.eclipse.core.runtime.IProgressMonitor)
	 */
	protected IProject[] build(int kind, Map<String, String> args, IProgressMonitor monitor)
			throws CoreException {
		if (kind == FULL_BUILD) {
			fullBuild(monitor);
		} else {
			IResourceDelta delta = getDelta(getProject());
			if (delta == null) {
				fullBuild(monitor);
			} else {
				incrementalBuild(delta, monitor);
			}
		}
		return null;
	}

	public void checkConsistency(IResource resource){
		// TODO DVK, check consistency of plugin.xml according to existence of projects referenced in the xdsml  
	}
	
	/**
	 * Update plugin.xml according to the model
	 * 
	 * @param resource
	 */
	private void updateProjectPluginConfiguration(IResource resource) {
		
		if (resource instanceof IFile 
			&& resource.getFileExtension().equals("dsl")
			&& !resource.getLocation().toString().contains("/bin/")) {
			
			IFile file = (IFile) resource;
			IProject project = file.getProject();
			try {
				IJavaProject javaProject = (IJavaProject) project.getNature(JavaCore.NATURE_ID);
				if(javaProject.getOutputLocation().isPrefixOf(file.getFullPath())){
					// ignore melange files in target folder, we should consider only those outside it (in model or src folders for exmaple)
					return;
				};
			} catch (CoreException e) {
				Activator.error(e.getMessage(), e);
			}
			if (file.exists()) {
				Job job = Job.create("Update GEMOC Project Plugin Configuration of "+project.getName(), (ICoreRunnable) monitor -> {
					Resource res = (new ResourceSetImpl()).getResource(URI.createPlatformResourceURI(file.getFullPath().toString(),true), true);
					if(res != null && res.getContents().size() > 0 && res.getContents().get(0) instanceof Dsl) {
						Dsl dsl = (Dsl) res.getContents().get(0);
						String languageName = dsl.getName();
						if(languageName == null || languageName.isEmpty()) {
							languageName = file.getName();
						}
						setPluginLanguageNameAndFilePath(project, file, languageName);
						
					}
					
					//Use default model loader
					updateModelLoaderClass(project, null);
					ManifestChanger manifestChanger = new ManifestChanger(project);
					try {
						manifestChanger.addPluginDependency(org.eclipse.gemoc.executionframework.extensions.sirius.Activator.PLUGIN_ID);
						manifestChanger.commit();
					} catch (BundleException | IOException | CoreException e) {
						e.printStackTrace();
					}
				});
				// lock the whole project
				job.setRule(project);
				// Start the Job
				job.schedule(500);
			}
		}
	}

	/**
	 * create or replace existing ModelLoaderClass by an implementation that is
	 * able to load models of the domain
	 * 
	 * @param project
	 * @param ld
	 */
	protected void updateModelLoaderClass(IProject project, String modelLoaderClass) {
		// update plugin.xml
		IFile pluginfile = project.getFile(PluginXMLHelper.PLUGIN_FILENAME);
		PluginXMLHelper.createEmptyTemplateFile(pluginfile, false);
		PluginXMLHelper helper = new PluginXMLHelper();
		helper.loadDocument(pluginfile);
		Element gemocExtensionPoint = helper.getOrCreateExtensionPoint(SequentialLanguageDefinitionExtensionPoint.GEMOC_SEQUENTIAL_LANGUAGE_EXTENSION_POINT);
		helper.updateXDSMLDefinitionAttributeInExtensionPoint(
				gemocExtensionPoint,
				LanguageDefinitionExtensionPoint.GEMOC_LANGUAGE_EXTENSION_POINT_XDSML_DEF_LOADMODEL_ATT,
				modelLoaderClass != null ? modelLoaderClass : "org.eclipse.gemoc.executionframework.extensions.sirius.modelloader.DefaultModelLoader");
		helper.saveDocument(pluginfile);
	}
	
	protected void setPluginLanguageNameAndFilePath(IProject project, IFile dslFile , final String languageName) {
		IFile pluginfile = project.getFile(PluginXMLHelper.PLUGIN_FILENAME);
		PluginXMLHelper.createEmptyTemplateFile(pluginfile, false);
		PluginXMLHelper helper = new PluginXMLHelper();
		helper.loadDocument(pluginfile);
		Element gemocExtensionPoint = helper.getOrCreateExtensionPoint(SequentialLanguageDefinitionExtensionPoint.GEMOC_SEQUENTIAL_LANGUAGE_EXTENSION_POINT);
		helper.updateXDSMLDefinitionInExtensionPoint(gemocExtensionPoint, languageName);
		helper.updateXDSMLDefinitionAttributeInExtensionPoint(gemocExtensionPoint,
				LanguageDefinitionExtensionPoint.GEMOC_LANGUAGE_EXTENSION_POINT_XDSML_DEF_XDSML_FILE_PATH_ATT,
				"platform:/plugin/" + project.getName() + "/" + dslFile.getProjectRelativePath());
		helper.saveDocument(pluginfile);
	}
	
	protected void clean(IProgressMonitor monitor) throws CoreException {
		// delete markers set and files created
	}



	protected void fullBuild(final IProgressMonitor monitor)
			throws CoreException {
		try {
			getProject().accept(new GemocSequentialLanguageResourceVisitor());
		} catch (CoreException e) {
		}
	}

	protected void incrementalBuild(IResourceDelta delta,
			IProgressMonitor monitor) throws CoreException {
		// the visitor does the work.
		delta.accept(new GemocSequentialLanguageProjectDeltaVisitor());
	}
}
