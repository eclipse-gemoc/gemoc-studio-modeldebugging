package org.eclipse.gemoc.executionframework.event.manager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CompositeCallRequest implements ICallRequest {
	
	private final List<SimpleCallRequest> callRequests;
	
	public CompositeCallRequest(List<SimpleCallRequest> callRequests) {
		this.callRequests = Collections.unmodifiableList(new ArrayList<>(callRequests));
	}
	
	public List<SimpleCallRequest> getCallRequests() {
		return callRequests;
	}

	@Override
	public boolean isRunToCompletion() {
		return true;
	}
}
