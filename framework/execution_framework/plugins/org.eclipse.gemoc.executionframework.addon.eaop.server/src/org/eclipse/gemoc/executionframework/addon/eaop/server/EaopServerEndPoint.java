package org.eclipse.gemoc.executionframework.addon.eaop.server;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.eclipse.gemoc.commons.utils.ExtensibleInputStream;
import org.eclipse.gemoc.executionframework.addon.eaop.server.rpclaunch.EaopJsonRPCLauncher;
import org.eclipse.gemoc.protocols.eaop.api.services.IEngineAddonProtocolClient;
import org.eclipse.lsp4j.jsonrpc.Launcher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ServerEndpoint("/metp/{engineId}")
public class EaopServerEndPoint {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EaopServerEndPoint.class);
	
	boolean supportHeader = true;
	
	/** 
	 * map of streams associated to the websocket sessions
	 * each stream is in charge of converting the received messages to the stream used by json rcp
	 */
	Map<Session, ExtensibleInputStream> activeSessions = new HashMap<Session, ExtensibleInputStream>();
	
	EaopServerImpl server;
	
	/**
	 * 
	 * @param session
	 * @param engineIndexString
	 */
	@OnOpen
	public void onOpen(Session session, @PathParam("engineId") String engineIdEncodedString) {
		LOGGER.info("Starting connection...");
		
		String engineId = "";
		try {
			engineId = URLDecoder.decode(engineIdEncodedString, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			LOGGER.error(e.getMessage(), e);
		}
		
		// create server 
		server =  new EaopServerImpl(engineId);
		
		
		ExtensibleInputStream in = new ExtensibleInputStream();
		activeSessions.put(session, in);
		OutputStream outputstream = new OutputStream() {

			ByteArrayOutputStream internal = new ByteArrayOutputStream();
			
			@Override
			public void write(int b) throws IOException {
				internal.write(b);
			}
			
			@Override
			public void flush() throws IOException {
				String response = new String(internal.toByteArray(), "UTF-8");
				if(!supportHeader) { //LSP specifies that there is a header but messages from Monaco do not have a header
					response = response.substring(response.indexOf("{"));
				}
				session.getBasicRemote().sendText(response);
				internal.reset();
				
				LOGGER.debug("Response sent: "+ response);
				System.out.println("[DEBUG] Sent: " + response);
			}
		};
		
		Launcher<IEngineAddonProtocolClient> serverSideLauncher = EaopJsonRPCLauncher.createLauncher(server, IEngineAddonProtocolClient.class, in, outputstream);
		server.connectClient(serverSideLauncher.getRemoteProxy());
	
		serverSideLauncher.startListening();
	}
	@OnClose
	public void onClose(Session session, CloseReason closeReason) {
		activeSessions.remove(session);
		LOGGER.debug("Closing session "+session.getId()+" "+session.getRequestURI());
	}
	
	@OnMessage
	public void onMessage(String message, Session session) {
		if(!message.startsWith("Content-Length:")) { //LSP specifies that there is a header but messages from Monaco do not have a header
			supportHeader = false;
			message = addHeader(message);
		}
		
		System.out.println("[DEBUG] Message: " + message);

		ExtensibleInputStream inputStream = activeSessions.get(session);
		if(inputStream != null) {
			inputStream.addMessage(message);
		}
	} 
	
	public String addHeader(String requestContent) {
		String header = "";
		try {
			int length = requestContent.getBytes("UTF-8").length;
			header = "Content-Length: " + length + "\n\n";
		} catch (UnsupportedEncodingException e) {
			LOGGER.error(e.getMessage());
			//Activator.logError(e.getMessage(), e);
		}
				
		return header + requestContent;
	}
	
	@OnError
	public void onError(Session session, Throwable throwable) {
		LOGGER.error("Error "+throwable.getMessage()+" on session "+session.getId()+" "+session.getRequestURI(), throwable);
	}

}
