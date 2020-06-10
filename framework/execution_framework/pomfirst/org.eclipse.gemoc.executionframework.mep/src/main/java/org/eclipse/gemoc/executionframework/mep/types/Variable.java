package org.eclipse.gemoc.executionframework.mep.types;

public class Variable {

	private String name;
	private String value;
	
	
	public Variable(String name, String value) {
		this.name = name;
		this.value = value;
	}
	
	public String getName() {
		return name;
	}
	
	public String getValue() {
		return value;
	}

}
