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
package org.eclipse.gemoc.executionframework.ui;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.Status;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.gemoc.executionframework.ui.views.engine.EngineSelectionManager;
import org.osgi.framework.BundleContext;


/**
 * The activator class controls the plug-in life cycle
 *
 * @author dvojtise
 */
public class Activator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.eclipse.gemoc.gemoc_language_workbench.ui"; //$NON-NLS-1$

	public static final String GEMOC_PROJECT_CONFIGURATION_FILE_EXTENSION = "xdsml";
	public static final String GEMOC_PROJECT_CONFIGURATION_FILE = "project."
			+ GEMOC_PROJECT_CONFIGURATION_FILE_EXTENSION;

	// extension point constants
	

	public static final String MODEL_LOADER_CLASS_NAMEPART = "ModelLoader";
	public static final String CODEEXECUTOR_CLASS_NAMEPART = "CodeExecutor";
	public static final String SOLVER_CLASS_NAMEPART = "Solver";
	public static final String EXTENSION_GENERATED_CLASS_FOLDER_NAME = "src/main/xdsml-java-gen/";

	// The shared instance
	private static Activator plugin;

	private final EngineSelectionManager engineSelectionManager = new EngineSelectionManager();
	



	private final List<IMSEPresenter> eventPresenters = new ArrayList<>();
	
	/**
	 * The constructor
	 */
	public Activator() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext
	 * )
	 */
	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext
	 * )
	 */
	/* (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	@Override
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 * 
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}

	/**
	 * Returns an image descriptor for the image file at the given plug-in
	 * relative path
	 * 
	 * @param path
	 *            the path
	 * @return the image descriptor
	 */
	public static ImageDescriptor getImageDescriptor(String path) {
		return imageDescriptorFromPlugin(PLUGIN_ID, path);
	}

	public static void warn(String msg, Throwable e) {
		Activator.getDefault().getLog().log(new Status(Status.WARNING, PLUGIN_ID, Status.OK, msg, e));
	}

	public static void error(String msg, Throwable e) {
		Activator.getDefault().getLog().log(new Status(Status.ERROR, PLUGIN_ID, Status.OK, msg, e));
	}


	
	/**
	 * Gets the {@link List} of registered {@link IMSEPresenter}s.
	 * 
	 * @return the {@link List} of registered {@link IMSEPresenter}s
	 */
	public List<IMSEPresenter> getEventPresenters() {
		return eventPresenters;
	}
	

	public EngineSelectionManager getEngineSelectionManager() {
		return engineSelectionManager;
	}
}
