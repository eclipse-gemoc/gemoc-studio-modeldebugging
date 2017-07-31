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
package org.eclipse.gemoc.executionframework.debugger;

import java.util.function.Supplier;

import org.eclipse.gemoc.commons.eclipse.logging.backends.DefaultLoggingBackend;
import org.eclipse.gemoc.commons.eclipse.pde.GemocPlugin;
import org.eclipse.gemoc.xdsmlframework.api.core.IExecutionEngine;
import org.osgi.framework.BundleContext;

import org.eclipse.gemoc.commons.eclipse.messagingsystem.api.MessagingSystemManager;
import org.eclipse.gemoc.commons.eclipse.messagingsystem.api.MessagingSystem;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends GemocPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.executionframework.debugger"; //$NON-NLS-1$

	// The shared instance
	private static Activator plugin;

	private Supplier<IExecutionEngine> engineSupplier;

	private Supplier<String> bundleSymbolicNameSupplier;

	public void setHandlerFieldSuppliers(Supplier<IExecutionEngine> engineSupplier,
			Supplier<String> bundleSymbolicNameSupplier) {
		this.engineSupplier = engineSupplier;
		this.bundleSymbolicNameSupplier = bundleSymbolicNameSupplier;
	}

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
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.
	 * BundleContext )
	 */
	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		Activator.plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.
	 * BundleContext )
	 */
	@Override
	public void stop(BundleContext context) throws Exception {
		Activator.plugin = null;
		super.stop(context);
	}

	@Override
	public String getId() {
		return PLUGIN_ID;
	}

	private DefaultLoggingBackend _loggingBackend;

	@Override
	public DefaultLoggingBackend resolveLoggingBackend() {
		if (_loggingBackend == null) {
			_loggingBackend = new DefaultLoggingBackend(this);
			MessagingSystemManager msm = new MessagingSystemManager();
			MessagingSystem ms = msm.createBestPlatformMessagingSystem(PLUGIN_ID, "Debugger");
			_loggingBackend.setMessagingSystem(ms);
		}
		return _loggingBackend;
	}

	public Supplier<IExecutionEngine> getEngineSupplier() {
		return engineSupplier;
	}

	public Supplier<String> getBundleSymbolicNameSupplier() {
		return bundleSymbolicNameSupplier;
	}

}
