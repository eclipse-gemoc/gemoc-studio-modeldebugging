/*******************************************************************************
 * Copyright (c) 2016 Inria and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Inria - initial API and implementation
 *******************************************************************************/
package org.eclipse.gemoc.executionframework.engine;

import org.eclipse.gemoc.commons.eclipse.logging.backends.DefaultLoggingBackend;
import org.eclipse.gemoc.commons.eclipse.pde.GemocPlugin;
import org.eclipse.gemoc.executionframework.engine.core.GemocRunningEnginesRegistry;
import org.osgi.framework.BundleContext;

import org.eclipse.gemoc.commons.eclipse.messagingsystem.api.MessagingSystemManager;
import org.eclipse.gemoc.commons.eclipse.messagingsystem.api.MessagingSystem;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends GemocPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.eclipse.gemoc.executionframework.engine"; //$NON-NLS-1$

	// The shared instance
	private static Activator plugin;

	/**
	 * Returns the shared instance
	 * 
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return Activator.plugin;
	}

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
		Activator.plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext
	 * )
	 */
	@Override
	public void stop(BundleContext context) throws Exception {
		Activator.plugin = null;
		super.stop(context);
	}

	/**
	 * List of engines that have registered to be running in this eclipse
	 */
	public GemocRunningEnginesRegistry gemocRunningEngineRegistry = new GemocRunningEnginesRegistry();

	@Override
	public String getId() {
		return PLUGIN_ID;
	}

//	@Override
//	public DefaultLoggingBackend resolveLoggingBackend() {
//		return org.eclipse.gemoc.executionframework.engine.commons.Activator.getDefault().resolveLoggingBackend();
//	}

	private DefaultLoggingBackend _loggingBackend;
	@Override
	public DefaultLoggingBackend resolveLoggingBackend() {
		if (_loggingBackend == null)
		{
			_loggingBackend = new DefaultLoggingBackend(this);
			MessagingSystemManager msm = new MessagingSystemManager();
			MessagingSystem ms = msm.createBestPlatformMessagingSystem(PLUGIN_ID, "Execution Engine");
			_loggingBackend.setMessagingSystem(ms);
		}
		return _loggingBackend;
	}


}
