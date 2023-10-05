/*******************************************************************************
 * Copyright (c) 2017 INRIA and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     INRIA - initial API and implementation
 *     I3S Laboratory - API update and bug fix
 *******************************************************************************/
package org.eclipse.gemoc.trace.gemoc.concurrent;

import org.eclipse.gemoc.commons.eclipse.logging.backends.DefaultLoggingBackend;
import org.eclipse.gemoc.commons.eclipse.messagingsystem.api.MessagingSystem;
import org.eclipse.gemoc.commons.eclipse.messagingsystem.api.MessagingSystemManager;
import org.eclipse.gemoc.commons.eclipse.GemocPlugin;
import org.osgi.framework.BundleContext;

public class Activator extends GemocPlugin {

	// The plug-in ID
		public static final String PLUGIN_ID = "org.eclipse.gemoc.trace.gemoc.concurrent"; //$NON-NLS-1$
		
		// The shared instance
		private static Activator plugin;

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

		@Override
		public String getId() {
			return PLUGIN_ID;
		}

		private DefaultLoggingBackend _loggingBackend;
		@Override
		public DefaultLoggingBackend resolveLoggingBackend() {
			if (_loggingBackend == null)
			{
				_loggingBackend = new DefaultLoggingBackend(this);
				MessagingSystemManager msm = new MessagingSystemManager();
				MessagingSystem ms = msm.createBestPlatformMessagingSystem(
						org.eclipse.gemoc.executionframework.engine.Activator.PLUGIN_ID, 
						org.eclipse.gemoc.executionframework.engine.Activator.CONSOLE_NAME);
				_loggingBackend.setMessagingSystem(ms);
			}
			return _loggingBackend;
		}

}
