package org.eclipse.gemoc.executionframework.event.manager;

import java.util.Map;

public class ReturnNotification extends BehavioralUnitNotification {

	private final Object result;
	
	public ReturnNotification(String behavioralUnit, Map<String, Object> arguments, Object result) {
		super(behavioralUnit, arguments);
		this.result = result;
	}
	
	public Object getResult() {
		return result;
	}
}
