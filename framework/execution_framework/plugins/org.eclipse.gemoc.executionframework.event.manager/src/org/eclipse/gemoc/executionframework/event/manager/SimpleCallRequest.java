package org.eclipse.gemoc.executionframework.event.manager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SimpleCallRequest implements ICallRequest {

	private final String behavioralUnit;

	private final List<Object> arguments;

	private final boolean runToCompletion;

	private final String metalanguage;

	public SimpleCallRequest(String behavioralUnit, List<Object> arguments,
			boolean runToCompletion, String metalanguage) {
		this.behavioralUnit = behavioralUnit;
		this.arguments = Collections.unmodifiableList(new ArrayList<>(arguments));
		this.runToCompletion = runToCompletion;
		this.metalanguage = metalanguage;
	}

	public String getBehavioralUnit() {
		return behavioralUnit;
	}

	public List<Object> getArguments() {
		return arguments;
	}

	@Override
	public boolean isRunToCompletion() {
		return runToCompletion;
	}
	
	public String getMetalanguage() {
		return metalanguage;
	}
}
