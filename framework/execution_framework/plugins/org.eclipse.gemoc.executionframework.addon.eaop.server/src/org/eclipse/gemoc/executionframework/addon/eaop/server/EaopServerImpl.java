package org.eclipse.gemoc.executionframework.addon.eaop.server;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

import org.eclipse.gemoc.protocols.eaop.api.data.GetStateArguments;
import org.eclipse.gemoc.protocols.eaop.api.data.GetStateResponse;
import org.eclipse.gemoc.protocols.eaop.api.data.GetStepArguments;
import org.eclipse.gemoc.protocols.eaop.api.data.GetStepResponse;
import org.eclipse.gemoc.protocols.eaop.api.data.InitializeArguments;
import org.eclipse.gemoc.protocols.eaop.api.data.InitializeResponse;
import org.eclipse.gemoc.protocols.eaop.api.services.IEngineAddonProtocolClient;
import org.eclipse.gemoc.protocols.eaop.api.services.IEngineAddonProtocolServer;
import org.eclipse.gemoc.xdsmlframework.api.core.IExecutionEngine;
import org.eclipse.lsp4j.jsonrpc.json.JsonRpcMethod;
import org.eclipse.lsp4j.jsonrpc.json.JsonRpcMethodProvider;
import org.eclipse.lsp4j.jsonrpc.services.ServiceEndpoints;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
public class EaopServerImpl implements IEngineAddonProtocolServer, org.eclipse.lsp4j.jsonrpc.Endpoint, JsonRpcMethodProvider{

	private static final Logger LOG = LoggerFactory.getLogger(EaopServerImpl.class);
	
	
	/**
	 * id of the engine in the engine manager (ie.
	 * org.eclipse.gemoc.executionframework.engine.Activator.getDefault().gemocRunningEngineRegistry)
	 */
	//protected String engineId;

	//protected IExecutionEngine<?> engine;
	
	/**
	 * regexp for engines listened by this EaopServer
	 */
	protected String engineIdRegExp =  ".*";
	
	protected IEngineAddonProtocolClient eaopClient;

	public EaopServerImpl() {
		//this.engineId = engineId;
		//initEMFJSON();
	}
	
	public void connectClient(IEngineAddonProtocolClient client) {
		eaopClient = client;
		
	}
	
	// **************************************
	// * org.eclipse.lsp4j.jsonrpc.Endpoint *
	// **************************************
	/**
	 * from org.eclipse.lsp4j.jsonrpc.Endpoint jsonrpc request
	 */
	@Override
	public CompletableFuture<?> request(String method, Object parameter) {
		throw new UnsupportedOperationException("The json request \'" + method + "\' is unknown.");
	}
	/**
	 * jsonrpc notifications
	 */
	@Override
	public void notify(String method, Object parameter) {
		LOG.debug("notify " + method);
	}


	// ********************************************************
	// * org.eclipse.lsp4j.jsonrpc.json.JsonRpcMethodProvider *
	// ********************************************************

	private Map<String, JsonRpcMethod> supportedMethods;
	
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
	
	// ****************************************************************************
	// * org.eclipse.gemoc.protocols.eaop.api.services.IEngineAddonProtocolServer *
	// ****************************************************************************
	@Override
	public CompletableFuture<InitializeResponse> initialize(InitializeArguments arguments) {
		LOG.info("CompletableFuture<InitializeResponse> initialize()");
		//return IEngineAddonProtocolServer.super.initialize(arguments);
		
		engineIdRegExp =  ".*";
		
		CompletableFuture<InitializeResponse> future = CompletableFuture.supplyAsync(new Supplier<InitializeResponse>() {

			@Override
			public InitializeResponse get() {
				// TODO Auto-generated method stub
				InitializeResponse response = new InitializeResponse();
				return response;
			}
		
		});
		return future;
	}
	
	@Override
	public CompletableFuture<GetStateResponse> getState(GetStateArguments arguments) {
		// TODO Auto-generated method stub
		return IEngineAddonProtocolServer.super.getState(arguments);
	}

	@Override
	public CompletableFuture<GetStepResponse> getStep(GetStepArguments arguments) {
		// TODO Auto-generated method stub
		return IEngineAddonProtocolServer.super.getStep(arguments);
	}	
	
	
	// helper methods
	
	
/*
	protected IExecutionEngine<?> getEngine() {
		return org.eclipse.gemoc.executionframework.engine.Activator.getDefault().gemocRunningEngineRegistry.getRunningEngines().get(this.engineId);
	}
	
	public static EaopEngineAddon getEaopEngineAddon(IExecutionEngine<?> engine) {
		return engine.getAddon(EaopEngineAddon.class);
	}*/
}
