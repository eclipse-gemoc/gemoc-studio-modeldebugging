/*******************************************************************************
 * Copyright (c) 2020 Inria and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Inria - initial API and implementation
 *******************************************************************************/
package org.eclipse.gemoc.trace.metp.addonbased.server;

import javax.websocket.server.ServerContainer;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Status;
import org.eclipse.jetty.server.Server;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends Plugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.eclipse.gemoc.trace.metp.addonbased.server"; //$NON-NLS-1$

	public static final String MANAGED_JETTYSERVER_NAME = PLUGIN_ID; // $NON-NLS-1$

	// The shared instance
	private static Activator plugin;
	private BundleContext context;

	public static BundleContext getBundleContext() {
		return getDefault().context;
	}

	public static Activator getDefault() {
		return plugin;
	}

	public static synchronized void logError(String message, Throwable ex) {
		if (message == null) {
			message = ""; //$NON-NLS-1$
		}
		Status errorStatus = new Status(IStatus.ERROR, PLUGIN_ID, IStatus.OK, message, ex);
		Activator.getDefault().getLog().log(errorStatus);
	}

	public static synchronized void logStatus(IStatus errorStatus) {
		Activator.getDefault().getLog().log(errorStatus);
	}

	protected Server server;
	protected ServerContainer wsContainer;

	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		this.context = context;

	}

	@Override
	public void stop(BundleContext context) throws Exception {
		this.context = null;
		plugin = null;
		super.stop(context);
	}

}
