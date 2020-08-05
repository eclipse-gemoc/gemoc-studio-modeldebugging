package org.eclipse.gemoc.trace.metp.client.timeline.ui;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.eclipse.gemoc.trace.metp.client.timeline.ui"; //$NON-NLS-1$

	// The shared instance
	private static Activator plugin;
	
	/**
	 * The constructor
	 */
	public Activator() {
	}

	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		
		// print in the console the name of the port
		Display.getDefault().syncExec(new Runnable() {

			@Override
			public void run() {
				if (PlatformUI.isWorkbenchRunning()
						&& PlatformUI.getWorkbench().getActiveWorkbenchWindow() != null) {
					
				}
				System.out.println("GEMOC websocket server opened on port "+org.eclipse.gemoc.ws.server.Activator.getDefault().getAssignedPort());
				System.out.println("TODO display known end points");
			}
		});
	}

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
}
