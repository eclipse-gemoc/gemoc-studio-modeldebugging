package org.eclipse.gemoc.executionframework.addon.eaop.server;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.gemoc.commons.eclipse.messagingsystem.api.MessagingSystem;
import org.eclipse.gemoc.commons.eclipse.messagingsystem.api.MessagingSystemManager;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {
	// The plug-in ID
	public static final String PLUGIN_ID = "org.eclipse.gemoc.executionframework.addon.eaop.server"; //$NON-NLS-1$
	public static final String CONSOLE_NAME = "Modeling Workbench Console"; //$NON-NLS-1$
	
	// The shared instance
	private static Activator plugin;
	private static BundleContext context;

	static BundleContext getContext() {
		return context;
	}
	
	public static Activator getDefault() {
		return plugin;
	}

	public void start(BundleContext bundleContext) throws Exception {
		plugin = this;
		Activator.context = bundleContext;
		
		// activate ws server
		org.eclipse.gemoc.ws.server.Activator.getDefault();
		
	}

	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
	}

	/**
	 *  set of EaopServers that are connected 
	 */
	Set<EaopServerImpl> startedEaopServers = new HashSet<EaopServerImpl>();

	public synchronized Set<EaopServerImpl> getStartedEaopServer() {
		return startedEaopServers;
	}
	
	
	
	
	protected MessagingSystem messaggingSystem = null;

	public MessagingSystem getMessaggingSystem() {
		if (messaggingSystem == null) {
			MessagingSystemManager msm = new MessagingSystemManager();
			messaggingSystem = msm.createBestPlatformMessagingSystem("org.eclipse.gemoc.executionframework.engine", CONSOLE_NAME);
		}
		return messaggingSystem;
	}
	public static void debug(String msg) {
		Activator.getDefault().getMessaggingSystem().debug(msg, PLUGIN_ID);
	}
	public static void info(String msg) {
		Activator.getDefault().getMessaggingSystem().info(msg, PLUGIN_ID);
	}

	public static void warn(String msg, Throwable e) {
		// Activator.getDefault().getLog().log(new Status(Status.WARNING, PLUGIN_ID,
		// Status.OK, msg, e));
		Activator.getDefault().getMessaggingSystem().warn(msg, PLUGIN_ID, e);
	}

	public static void error(String msg, Throwable e) {
		// Activator.getDefault().getLog().log(new Status(Status.ERROR, PLUGIN_ID,
		// Status.OK, msg, e));
		Activator.getDefault().getMessaggingSystem().error(msg, PLUGIN_ID, e);
	}
	
}
