package org.eclipse.gemoc.executionframework.mep.events;

public enum StoppedReason {
	REACHED_BREAKPOINT ("breakpoint"),
	REACHED_NEXT_LOGICAL_STEP ("step"),
	REACHED_SIMULATION_END ("end"),
	TIME ("time");
	
	private final String reason;
	
	private StoppedReason(String reason) {
		this.reason = reason;
	}
	
	@Override
	public String toString() {
		return this.reason;
	}
}
