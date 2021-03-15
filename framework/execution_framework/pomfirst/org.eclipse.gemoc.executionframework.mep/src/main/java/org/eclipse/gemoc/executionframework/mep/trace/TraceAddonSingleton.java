package org.eclipse.gemoc.executionframework.mep.trace;


import java.util.concurrent.Semaphore;

public class TraceAddonSingleton {
	
	private static volatile IRemoteTraceAddon traceAddon = null;
	private static volatile Semaphore semaphore = new Semaphore(0);
	
	public static void setTraceAddon(IRemoteTraceAddon traceAddon) {
		if (TraceAddonSingleton.traceAddon == null) {
			TraceAddonSingleton.traceAddon = traceAddon;
			semaphore.release();
		}
	}
	
	public static IRemoteTraceAddon acquireTraceAddon() {
		try {
			semaphore.acquire();
			return traceAddon;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static IRemoteTraceAddon getTraceAddon() {
		return traceAddon;
	}
	
	public static void releaseTraceAddon() {
		if (traceAddon != null) {
			semaphore.release();
		}
	}

}
