package org.eclipse.gemoc.executionframework.mep.types;

public class StackFrame {

	private long id;
	private String name;
	private long line;
	private long column;
	
	public StackFrame(long id, String name, long line, long column) {
		this.id = id;
		this.name = name;
		this.line = line;
		this.column = column;
	}
	
	public long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public long getLine() {
		return line;
	}
	
	public long getColumn() {
		return column;
	}
	
}
