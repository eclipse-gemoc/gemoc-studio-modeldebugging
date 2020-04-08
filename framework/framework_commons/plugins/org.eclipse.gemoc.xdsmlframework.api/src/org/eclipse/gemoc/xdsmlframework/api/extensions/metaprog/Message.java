package org.eclipse.gemoc.xdslframework.api.extension.metaprog;

public class Message {
	
	public String content;
	public Severity severity;
	
	public Message() {}
	
	public Message(String messageContent, Severity messageSeverity) {
		content = messageContent;
		severity = messageSeverity;
	}
	
	public String getContent() {
		return content;
	}
	
	public Severity getSeverity() {
		return severity;
	}
	

}
