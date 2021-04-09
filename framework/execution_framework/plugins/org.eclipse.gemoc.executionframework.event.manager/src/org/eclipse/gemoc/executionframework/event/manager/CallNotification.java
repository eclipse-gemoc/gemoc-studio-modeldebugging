package org.eclipse.gemoc.executionframework.event.manager;

import java.util.Map;

public class CallNotification extends BehavioralUnitNotification {

	public CallNotification(String behavioralUnit, Map<String, Object> arguments) {
		super(behavioralUnit, arguments);
	}
}
