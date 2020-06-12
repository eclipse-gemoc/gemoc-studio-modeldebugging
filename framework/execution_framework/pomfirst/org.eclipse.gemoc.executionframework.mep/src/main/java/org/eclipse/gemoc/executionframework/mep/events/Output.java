package org.eclipse.gemoc.executionframework.mep.events;

import org.eclipse.gemoc.executionframework.mep.engine.MEPEvent;

public class Output extends MEPEvent {

	private String output;
	
	public Output(Object source, String output) {
		super(source);
		this.output = output;
	}

	public String getOutput() {
		return output;
	}

}
