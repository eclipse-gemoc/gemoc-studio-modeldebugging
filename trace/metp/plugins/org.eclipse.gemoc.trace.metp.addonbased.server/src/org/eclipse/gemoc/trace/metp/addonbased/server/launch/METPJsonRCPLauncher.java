/*******************************************************************************
 * Copyright (c) 2020 Inria and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Inria - initial API and implementation
 *******************************************************************************/
package org.eclipse.gemoc.trace.metp.addonbased.server.launch;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import org.eclipse.gemoc.trace.metp.addonbased.server.metp.json.METPMessageJsonHandler;
import org.eclipse.lsp4j.jsonrpc.Endpoint;
import org.eclipse.lsp4j.jsonrpc.Launcher;
import org.eclipse.lsp4j.jsonrpc.MessageConsumer;
import org.eclipse.lsp4j.jsonrpc.RemoteEndpoint;
import org.eclipse.lsp4j.jsonrpc.json.JsonRpcMethod;
import org.eclipse.lsp4j.jsonrpc.json.MessageJsonHandler;
import org.eclipse.lsp4j.jsonrpc.json.StreamMessageConsumer;
import org.eclipse.lsp4j.jsonrpc.services.ServiceEndpoints;

/**
 * Specialized launcher for the MEP Server Protocol.
 * It binds together:
 * - the Implementation of the of the METP server
 * - input and output streams of the JSONRCP
 * - the representaiton of the remote object (ie. client)
 */
public class METPJsonRCPLauncher {

	private METPJsonRCPLauncher() {
	}

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
				return new METPMessageJsonHandler(supportedMethods, configureGson);
			else
				return new METPMessageJsonHandler(supportedMethods);
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
					remoteEndpoint = new METPRemoteEndpoint(outgoingMessageStream, localEndpoint);
				else
					remoteEndpoint = new METPRemoteEndpoint(outgoingMessageStream, localEndpoint, exceptionHandler);
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
