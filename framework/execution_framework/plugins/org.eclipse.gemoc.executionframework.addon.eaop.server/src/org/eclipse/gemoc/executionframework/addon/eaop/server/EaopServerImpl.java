package org.eclipse.gemoc.executionframework.addon.eaop.server;

import java.util.concurrent.CompletableFuture;

import org.eclipse.gemoc.protocols.eaop.api.services.IEngineAddonProtocolClient;
import org.eclipse.gemoc.protocols.eaop.api.services.IEngineAddonProtocolServer;
import org.eclipse.gemoc.xdsmlframework.api.core.IExecutionEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EaopServerImpl implements IEngineAddonProtocolServer, org.eclipse.lsp4j.jsonrpc.Endpoint{

	private static final Logger LOG = LoggerFactory.getLogger(EaopServerImpl.class);
	
	
	/**
	 * id of the engine in the engine manager (ie.
	 * org.eclipse.gemoc.executionframework.engine.Activator.getDefault().gemocRunningEngineRegistry)
	 */
	protected String engineId;

	protected IExecutionEngine<?> engine;
	
	protected IEngineAddonProtocolClient eaopClient;

	public EaopServerImpl(String engineId) {
		this.engineId = engineId;
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

}
