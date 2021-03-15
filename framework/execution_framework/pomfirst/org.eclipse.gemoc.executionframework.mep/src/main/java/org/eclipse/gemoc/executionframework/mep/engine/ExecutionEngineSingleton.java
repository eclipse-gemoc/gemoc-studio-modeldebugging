package org.eclipse.gemoc.executionframework.mep.engine;


import java.util.concurrent.Semaphore;

public class ExecutionEngineSingleton {
	
	private static volatile IMEPEngine engine = null;
	private static volatile Semaphore semaphore = new Semaphore(0);
	
	public static void setEngine(IMEPEngine engine) {
		if (ExecutionEngineSingleton.engine == null) {
			ExecutionEngineSingleton.engine = engine;
			semaphore.release();
		}
	}
	
	public static IMEPEngine acquireEngine() {
		try {
			semaphore.acquire();
			return engine;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void releaseEngine() {
		if (engine != null) {
			semaphore.release();
		}
	}

}
