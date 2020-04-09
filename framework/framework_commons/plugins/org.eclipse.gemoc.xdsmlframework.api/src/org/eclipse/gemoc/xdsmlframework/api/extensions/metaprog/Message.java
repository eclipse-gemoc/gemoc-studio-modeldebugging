package org.eclipse.gemoc.xdsmlframework.api.extensions.metaprog;

/**
 * Messages carry information from a validation rule to the DslValidator which will then display them on the IDE if their Severity is different than DEFAULT.
 * 
 * @author GUEGUEN Ronan
 *
 */
public class Message {
	
	private String content;
	private Severity severity;
	
	/**
	 * Creates an empty Message
	 */
	public Message() {}
	
	/**
	 * Creates a Message with a content and a Severity.
	 * 
	 * @param content -Information that will be displayed in the IDE
	 * @param severity -Severity of the Message. The Severity of the Message can take any of the following values:
	 *  <ul>
	 * 		<li>ERROR,</li>
	 * 		<li>WARNING,</li>
	 * 		<li>INFO or</li>
	 * 		<li>DEFAULT</li>
	 * </ul>
	 */
	public Message(String messageContent, Severity messageSeverity) {
		content = messageContent;
		severity = messageSeverity;
	}
	
	/**
	 * Returns a String containg the content of the Message.
	 * 
	 * @return a String containing the content of the message
	 */
	final public String getContent() {
		return content;
	}
	
	/**
	 * Returns the Severity of the Message.
	 * The Severity of the Message can be any of the following values:
	 * <ul>
	 * 		<li>ERROR,</li>
	 * 		<li>WARNING,</li>
	 * 		<li>INFO or</li>
	 * 		<li>DEFAULT</li>
	 * </ul>
	 * 
	 * @retun the severity of the Message
	 */
	final public Severity getSeverity() {
		return severity;
	}
	

}
