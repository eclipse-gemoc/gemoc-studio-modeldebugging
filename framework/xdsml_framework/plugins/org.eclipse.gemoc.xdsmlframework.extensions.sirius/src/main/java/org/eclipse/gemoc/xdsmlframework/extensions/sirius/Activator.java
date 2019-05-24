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
package org.eclipse.gemoc.xdsmlframework.extensions.sirius;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.gemoc.commons.eclipse.messagingsystem.api.MessagingSystem;
import org.eclipse.gemoc.commons.eclipse.messagingsystem.api.MessagingSystemManager;
import org.eclipse.gemoc.commons.eclipse.messagingsystem.ui.ConsoleLogLevel;
import org.eclipse.gemoc.commons.eclipse.messagingsystem.ui.EclipseMessagingSystem;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator extends AbstractUIPlugin {

	private static Activator plugin;

	public static Activator getDefault() {
		return plugin;
	}
	
	public static final String PLUGIN_ID = "org.eclipse.gemoc.xdsmlframework.extensions.sirius"; //$NON-NLS-1$


	protected static MessagingSystem messagingSystem = null;

	public static MessagingSystem getMessagingSystem() {
		if (messagingSystem == null) 
		{
			MessagingSystemManager msm = new MessagingSystemManager();
			messagingSystem = msm.createBestPlatformMessagingSystem(PLUGIN_ID, "Execution Engine");
			if (messagingSystem instanceof EclipseMessagingSystem)
				((EclipseMessagingSystem) messagingSystem).setConsoleLogLevel(ConsoleLogLevel.DEV_DEBUG);
		}
		return messagingSystem;
	}
	
	/**
	 * This method logs an error message and an associated exception (as a trace)
	 * @param message String
	 */
	public static void logErrorMessage(String message, Throwable e) {
		if (message == null)
			message= "";
		// eclipse logger
		getDefault().getLog().log(new Status(IStatus.ERROR, PLUGIN_ID, IStatus.ERROR, message, e));		
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

}
