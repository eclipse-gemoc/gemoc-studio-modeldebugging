package org.eclipse.gemoc.executionframework.engine.concurrency;

public class ConcurrentStepException extends Exception {

	private static final long serialVersionUID = 9140570714416166932L;

	public ConcurrentStepException(Exception e) {
		super(e);
	}
	
	public ConcurrentStepException(String message, Exception cause) {
		super(message, cause);
	}
	
	public ConcurrentStepException(String message) {
		super(message);
	}

}
