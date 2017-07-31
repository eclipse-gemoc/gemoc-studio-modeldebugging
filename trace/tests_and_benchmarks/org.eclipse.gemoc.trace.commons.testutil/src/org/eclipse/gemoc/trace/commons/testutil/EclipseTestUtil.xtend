package org.eclipse.gemoc.trace.commons.testutil

import org.eclipse.core.runtime.jobs.Job
import org.eclipse.swt.widgets.Display
import org.eclipse.ui.PlatformUI
import org.eclipse.ui.IWindowListener
import org.eclipse.ui.IWorkbenchWindow

class EclipseTestUtil {

	public static def void waitForJobs() {
		while (!Job.getJobManager().isIdle())
			delay(100);
	}


	public static def void waitForJobsThenWindowClosed() {
		waitForJobsThenWait(1000000000);
	}
	
	public static def void waitForJobsThenWait(long waitTimeMillis) {
		waitForJobs();
		delay(waitTimeMillis);
	}
	
	public static def void waitUIThread(long waitTimeMillis) {
		delay(waitTimeMillis);
	}
	

	static var closed = false;

	private static def void delay(long waitTimeMillis) {
		val Display display = Display.getCurrent();

		// We try to capture when the window is closed by the tester
		PlatformUI.getWorkbench.addWindowListener(
			new IWindowListener() {

				override windowActivated(IWorkbenchWindow window) {
				}

				override windowClosed(IWorkbenchWindow window) {
					closed = true
				}

				override windowDeactivated(IWorkbenchWindow window) {
				}

				override windowOpened(IWorkbenchWindow window) {
				}

			}
		)

		// If this is the UI thread,
		// then process input.
		if (display != null) {
			val long endTimeMillis = System.currentTimeMillis() + waitTimeMillis;
			while (System.currentTimeMillis() < endTimeMillis && !closed) {
				if (!display.readAndDispatch())
					display.sleep();
			}
			display.update();
		}
      // Otherwise, perform a simple sleep.
		else {
			try {
				Thread.sleep(waitTimeMillis);
			} catch (InterruptedException e) {
				// Ignored.
			}
		}
	}

}
