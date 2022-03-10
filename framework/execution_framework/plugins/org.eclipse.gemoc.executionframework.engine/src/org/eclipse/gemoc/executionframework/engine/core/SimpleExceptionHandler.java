package org.eclipse.gemoc.executionframework.engine.core;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.transaction.ExceptionHandler;
import org.eclipse.emf.transaction.RollbackException;

public class SimpleExceptionHandler implements ExceptionHandler {

	private Exception e;

	@Override
	public void handleException(Exception e) {
		this.e = e;
	}

	public Throwable getException() {
		if (e instanceof RollbackException) {
			RollbackException e_cast = (RollbackException) e;
			return getFirstExceptionOfStatus(e_cast.getStatus());
		}
		return e;
	}

	
	private Throwable getFirstExceptionOfStatus(IStatus s) {
		if (s.getException() != null) {
			return s.getException();
		}
		
		for (IStatus c : s.getChildren()) {
			Throwable t = getFirstExceptionOfStatus(c);
			if (t != null) {
				return t;
			}
		}
		return null;
	}
}
