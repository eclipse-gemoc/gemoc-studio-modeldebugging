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
import java.util.function.Supplier;

import org.eclipse.gemoc.trace.gemoc.api.IMultiDimensionalTraceAddon;
import org.eclipse.gemoc.trace.metp.addonbased.server.metp.data.EngineEventType;
import org.eclipse.gemoc.trace.metp.addonbased.server.metp.data.TraceCapabilities;
import org.eclipse.gemoc.trace.metp.addonbased.server.metp.services.IModelExecutionTraceProtocolClient;
import org.eclipse.gemoc.trace.metp.addonbased.server.metp.services.IModelExecutionTraceProtocolServer;
import org.eclipse.gemoc.xdsmlframework.api.core.IExecutionEngine;
import org.eclipse.lsp4j.jsonrpc.Endpoint;
import org.eclipse.lsp4j.jsonrpc.ResponseErrorException;
import org.eclipse.lsp4j.jsonrpc.json.JsonRpcMethod;
import org.eclipse.lsp4j.jsonrpc.json.JsonRpcMethodProvider;
import org.eclipse.lsp4j.jsonrpc.messages.ResponseError;
import org.eclipse.lsp4j.jsonrpc.services.ServiceEndpoints;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class METPServerImpl implements IModelExecutionTraceProtocolServer, Endpoint, JsonRpcMethodProvider,
		IClientAware<IModelExecutionTraceProtocolClient> {

	private static final Logger LOG = LoggerFactory.getLogger(METPServerImpl.class);

	private Map<String, JsonRpcMethod> supportedMethods;

	protected IModelExecutionTraceProtocolClient traceClient;

	
	
	protected boolean initialized = false;
	
	/**
	 * id of the engine in the engine manager (ie.
	 * org.eclipse.gemoc.executionframework.engine.Activator.getDefault().gemocRunningEngineRegistry)
	 */
	protected String engineId;

	protected IExecutionEngine<?> engine;
	protected ServerAddonTraceListener serverAddonTraceListener;

	public METPServerImpl(String engineId) {
		this.engineId = engineId;

	}

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
	// * IModelExecutionAddonProtocolServer *
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

	// **************************************
	// * IModelExecutionTraceProtocolServer *
	// **************************************
	@Override
	public CompletableFuture<TraceCapabilities> initializeTrace() {
		LOG.info("CompletableFuture<Void> initializeTrace()");
		CompletableFuture<TraceCapabilities> future = CompletableFuture.supplyAsync(new Supplier<TraceCapabilities>() {
			@Override
			public TraceCapabilities get() {
				// find the engine in the registry
				METPServerImpl.this.engine = org.eclipse.gemoc.executionframework.engine.Activator.getDefault().gemocRunningEngineRegistry
						.getRunningEngines().get(METPServerImpl.this.engineId);
		
				IMultiDimensionalTraceAddon<?, ?, ?, ?, ?> traceAddon = METPServerImpl.this.engine.getAddon(IMultiDimensionalTraceAddon.class);
				if(traceAddon != null) {
					serverAddonTraceListener = new ServerAddonTraceListener(METPServerImpl.this);
					traceAddon.getTraceNotifier().addListener(serverAddonTraceListener);
					initialized = true;
					TraceCapabilities capabilities = new TraceCapabilities();
					return capabilities;
				} else {
					ResponseError error = new ResponseError();
					error.setMessage("Missing trace capability in engine (no IEngineAddon implementing IMultiDimensionalTraceAddon added to the execution)");
					throw new ResponseErrorException(error);
				}
				
			}
		});
		return future;
	}
	// **************************************
	// * Destructor *
	// **************************************

	/**
	 * dispose unused resources and remove listeners
	 */
	public void dispose() {
		IMultiDimensionalTraceAddon<?, ?, ?, ?, ?> traceAddon = this.engine.getAddon(IMultiDimensionalTraceAddon.class);
		if(traceAddon != null) {
			traceAddon.getTraceNotifier().removeListener(serverAddonTraceListener);
		}
	}

}
