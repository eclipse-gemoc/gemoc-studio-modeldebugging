package org.eclipse.gemoc.executionframework.addon.eaop.server;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {

	private static BundleContext context;

	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
		
		// activate ws server
		org.eclipse.gemoc.ws.server.Activator.getDefault();
	}

	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
	}

}
