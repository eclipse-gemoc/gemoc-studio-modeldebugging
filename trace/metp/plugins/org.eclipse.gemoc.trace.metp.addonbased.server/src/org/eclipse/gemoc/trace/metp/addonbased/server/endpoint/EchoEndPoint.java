package org.eclipse.gemoc.trace.metp.addonbased.server.endpoint;

import javax.websocket.OnMessage;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/echo") 
public class EchoEndPoint {
	
	
	@OnMessage
	public void traiterOnMessage(String message) {
	   System.out.println("Message recu par WebSocket : "+message);
	} 
}
