package org.eclipse.gemoc.executionframework.mep.types;

public class SourceBreakpoint {
	
	private long line;
	
	public SourceBreakpoint(long line) {
		this.line = line;
	}
	
	public long getLine() {
		return this.line;
	}

}
