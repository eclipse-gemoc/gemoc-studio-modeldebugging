package org.eclipse.gemoc.executionframework.event.manager;

public class StopRequest implements ICallRequest {

	@Override
	public boolean isRunToCompletion() {
		return true;
	}

}
