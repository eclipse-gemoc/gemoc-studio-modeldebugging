package org.eclipse.gemoc.executionframework.mep.events;

import org.eclipse.gemoc.executionframework.mep.engine.MEPEvent;

public class Stopped extends MEPEvent {

	private StoppedReason reason;
	
	public Stopped(Object source, StoppedReason reason) {
		super(source);
		this.reason = reason;
	}

	public StoppedReason getReason() {
		return reason;
	}

}
