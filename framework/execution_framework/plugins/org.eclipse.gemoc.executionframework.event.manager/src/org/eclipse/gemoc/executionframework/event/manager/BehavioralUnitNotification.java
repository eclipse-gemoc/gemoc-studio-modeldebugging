package org.eclipse.gemoc.executionframework.event.manager;

import java.util.HashMap;
import java.util.Map;

public abstract class BehavioralUnitNotification {

	private final String behavioralUnit;
	
	private final Map<String, Object> arguments = new HashMap<>();
	
	public BehavioralUnitNotification(String behavioralUnit, Map<String, Object> arguments) {
		this.behavioralUnit = behavioralUnit;
		this.arguments.putAll(arguments);
	}
	
	public String getBehavioralUnit() {
		return behavioralUnit;
	}
	
	public Map<String, Object> getArguments() {
		return arguments;
	}
}
