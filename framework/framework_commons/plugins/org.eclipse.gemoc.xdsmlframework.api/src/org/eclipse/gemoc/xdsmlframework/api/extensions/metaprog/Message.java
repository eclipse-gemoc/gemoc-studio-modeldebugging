package org.eclipse.gemoc.xdsmlframework.api.extensions.metaprog;

public class Message {
	
	private String content;
	private Severity severity;
	
	public Message() {}
	
	/**
	 * 
	 * 
	 * 
	 */
	public Message(String messageContent, Severity messageSeverity) {
		content = messageContent;
		severity = messageSeverity;
	}
	
	/**
	 * @author GUEGUEN Ronan
	 * @return the content of the message
	 */
	final public String getContent() {
		return content;
	}
	
	/**
	 * @author GUEGUEN Ronan
	 * @retun the severity of the message
	 */
	final public Severity getSeverity() {
		return severity;
	}
	

}
