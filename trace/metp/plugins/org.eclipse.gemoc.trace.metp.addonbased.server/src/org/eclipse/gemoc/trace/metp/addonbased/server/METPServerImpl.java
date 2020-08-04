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
package org.eclipse.gemoc.trace.metp.addonbased.server;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.eclipse.gemoc.trace.metp.addonbased.server.metp.data.EngineEventType;
import org.eclipse.gemoc.trace.metp.addonbased.server.metp.services.IModelExecutionAddonProtocolClient;
import org.eclipse.gemoc.trace.metp.addonbased.server.metp.services.IModelExecutionTraceProtocolClient;
import org.eclipse.gemoc.trace.metp.addonbased.server.metp.services.IModelExecutionTraceProtocolServer;
import org.eclipse.lsp4j.jsonrpc.Endpoint;
import org.eclipse.lsp4j.jsonrpc.json.JsonRpcMethod;
import org.eclipse.lsp4j.jsonrpc.json.JsonRpcMethodProvider;
import org.eclipse.lsp4j.jsonrpc.services.ServiceEndpoints;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class METPServerImpl implements IModelExecutionTraceProtocolServer, Endpoint, JsonRpcMethodProvider, IClientAware<IModelExecutionTraceProtocolClient> {

	private static final Logger LOG = LoggerFactory.getLogger(METPServerImpl.class);

	private Map<String, JsonRpcMethod> supportedMethods;

	
	protected IModelExecutionTraceProtocolClient traceClient;


	@Override
	public void connectClient(IModelExecutionTraceProtocolClient client) {
		traceClient = client;
	}

	// ********************************************************
	// * org.eclipse.lsp4j.jsonrpc.json.JsonRpcMethodProvider *
	// ********************************************************
	@Override
	public Map<String, JsonRpcMethod> supportedMethods() {
		if (supportedMethods != null) {
			return supportedMethods;
		}
		Map<String, JsonRpcMethod> supportedMethods = new LinkedHashMap<>();
		supportedMethods.putAll(ServiceEndpoints.getSupportedMethods(getClass()));

		for (JsonRpcMethod supportedMethod : supportedMethods.values()) {
			LOG.info("supported method " + supportedMethod.getMethodName() + " " + supportedMethod.getParameterTypes());
		}
		this.supportedMethods = supportedMethods;
		return supportedMethods;
	}

	// **************************************
	// * org.eclipse.lsp4j.jsonrpc.Endpoint *
	// **************************************
	/**
	 * jsonrpc notifications
	 */
	@Override
	public void notify(String method, Object parameter) {
		LOG.info("notify " + method);
	}

	/**
	 * from org.eclipse.lsp4j.jsonrpc.Endpoint jsonrpc request
	 */
	@Override
	public CompletableFuture<?> request(String method, Object parameter) {
		throw new UnsupportedOperationException("The json request \'" + method + "\' is unknown.");
	}

	// **************************************
	// * IModelExecutionTraceProtocolServer *
	// **************************************
	@Override
	public CompletableFuture<Void> initialize(String engineId, EngineEventType eventType) {
		LOG.info("CompletableFuture<Void> initialize(String engineId, EngineEventType eventType)");
		CompletableFuture<Void> future = CompletableFuture.runAsync(new Runnable() {
			@Override
			public void run() {
				// TODO
			}
		});
		return future;
	}

}
