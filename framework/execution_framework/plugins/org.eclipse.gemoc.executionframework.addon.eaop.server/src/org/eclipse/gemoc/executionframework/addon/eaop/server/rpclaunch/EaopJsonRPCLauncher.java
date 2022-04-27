package org.eclipse.gemoc.executionframework.addon.eaop.server.rpclaunch;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import org.eclipse.gemoc.protocols.eaop.api.json.EaopMessageJsonHandler;
import org.eclipse.lsp4j.jsonrpc.Endpoint;
import org.eclipse.lsp4j.jsonrpc.Launcher;
import org.eclipse.lsp4j.jsonrpc.MessageConsumer;
import org.eclipse.lsp4j.jsonrpc.RemoteEndpoint;
import org.eclipse.lsp4j.jsonrpc.json.JsonRpcMethod;
import org.eclipse.lsp4j.jsonrpc.json.MessageJsonHandler;
import org.eclipse.lsp4j.jsonrpc.json.StreamMessageConsumer;
import org.eclipse.lsp4j.jsonrpc.services.ServiceEndpoints;

/**
 * Specialized launcher for the EAOP Protocol.
 * It binds together:
 * - the Implementation of the of the EAOP server
 * - input and output streams of the JSONRPC
 * - the representation of the remote object (ie. client)
 */
public class EaopJsonRPCLauncher {
	
	private EaopJsonRPCLauncher() {}
	
	/**
	 * Create a new Launcher for a given local service object, a given remote
	 * interface and an input and output stream.
	 *
	 * @param localService    - an object on which classes RPC methods are looked up
	 * @param remoteInterface - an interface on which RPC methods are looked up
	 * @param in              - inputstream to listen for incoming messages
	 * @param out             - outputstream to send outgoing messages
	 */
	public static <T> Launcher<T> createLauncher(Object localService, Class<T> remoteInterface, InputStream in,
			OutputStream out) {
		return new Builder<T>().setLocalService(localService).setRemoteInterface(remoteInterface).setInput(in)
				.setOutput(out).create();
	}

	/**
	 * Launcher builder for the debug protocol. Adapts the JSON-RPC message classes
	 * to the JSON format used by the debug protocol.
	 */
	public static class Builder<T> extends Launcher.Builder<T> {

		@Override
		protected MessageJsonHandler createJsonHandler() {
			Map<String, JsonRpcMethod> supportedMethods = getSupportedMethods();
			if (configureGson != null)
				return new EaopMessageJsonHandler(supportedMethods, configureGson);
			else
				return new EaopMessageJsonHandler(supportedMethods);
		}

		@Override
		protected RemoteEndpoint createRemoteEndpoint(MessageJsonHandler jsonHandler) {
			try {
				MessageConsumer outgoingMessageStream = new StreamMessageConsumer(output, jsonHandler);
				outgoingMessageStream = wrapMessageConsumer(outgoingMessageStream);
				Endpoint localEndpoint = ServiceEndpoints.toEndpoint(localServices);
				RemoteEndpoint remoteEndpoint;

				// !!!! nosuchfieldException that is trapped by quarkus
				// check if this is a version issue ?
				if (exceptionHandler == null)
					remoteEndpoint = new EaopRemoteEndpoint(outgoingMessageStream, localEndpoint);
				else
					remoteEndpoint = new EaopRemoteEndpoint(outgoingMessageStream, localEndpoint, exceptionHandler);
				jsonHandler.setMethodProvider(remoteEndpoint);
				return remoteEndpoint;
			} catch (Exception e) {
				System.out.println("   issue in createRemoteEndpoint" + e);
				e.printStackTrace();
				return null;
			}
		}

	}

}
