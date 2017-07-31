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
package org.eclipse.gemoc.executionframework.extensions.sirius;

import java.util.Map.Entry;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchListener;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.gemoc.xdsmlframework.api.core.EngineStatus.RunStatus;
import org.eclipse.gemoc.xdsmlframework.api.core.IExecutionEngine;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.eclipse.gemoc.executionframework.extensions.sirius"; //$NON-NLS-1$

	// The shared instance
	private static Activator plugin;
	
	/**
	 * The constructor
	 */
	public Activator() {
		IWorkbench workbench = PlatformUI.getWorkbench();
		//final IWorkbenchPage activePage = workbench.getActiveWorkbenchWindow().getActivePage();
		workbench.addWorkbenchListener(new IWorkbenchListener() {
			public boolean preShutdown(IWorkbench workbench, boolean forced) {
				
				// close all editors (a bit too strong ;-)  )
				// activePage.closeEditors(activePage.getEditorReferences(), false);
				
				// try to close only Sirius sessions related to engines
				for (Entry<String, IExecutionEngine> engineEntry : org.eclipse.gemoc.executionframework.engine.Activator.getDefault().gemocRunningEngineRegistry.getRunningEngines().entrySet())
			    {	
					try{
						// stop any running engine
						IExecutionEngine engine = engineEntry.getValue();
						if(engine.getRunningStatus() != RunStatus.Stopped){
							
							engine.dispose();
						}
						
						// ensure to clear sirius session
						URI uri = engine.getExecutionContext().getRunConfiguration().getAnimatorURI();
						if (uri != null) {
							Session session = SessionManager.INSTANCE.getSession(uri, new NullProgressMonitor());
							session.close(new NullProgressMonitor());
							SessionManager.INSTANCE.remove(session);
						}
							
					} catch (Exception e){ /* we don't care try the other */}
		    	}
				
				return true;
			}
			public void postShutdown(IWorkbench workbench) {
			}
		}); 
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

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}

}
