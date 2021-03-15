package org.eclipse.gemoc.executionframework.mep.trace;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.apache.log4j.Logger;
import org.eclipse.gemoc.executionframework.mep.trace.types.SerializableExecutionContext;
import org.eclipse.gemoc.executionframework.mep.trace.types.SerializableStep;
import org.eclipse.lsp4j.jsonrpc.Endpoint;
import org.eclipse.lsp4j.jsonrpc.json.JsonRpcMethod;
import org.eclipse.lsp4j.jsonrpc.json.JsonRpcMethodProvider;
import org.eclipse.lsp4j.jsonrpc.services.ServiceEndpoints;

public class TraceManagerAddonProtocolServer implements Endpoint, ITraceManagerAddonProtocolServer, JsonRpcMethodProvider, TraceManagerAddonProtocolClientAware {

	private static final Logger LOG = Logger.getLogger(TraceManagerAddonProtocolServer.class);
	
	ITraceManagerAddonProtocolClient client;
	
	private Map<String, JsonRpcMethod> supportedMethods;
	
	@Override
	public void connect(ITraceManagerAddonProtocolClient client) {
		this.client = client;		
	}

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

	@Override
	public void notify(String method, Object parameter) {
		LOG.info("notify " + method);
	}

	@Override
	public CompletableFuture<?> request(String method, Object parameter) {
		throw new UnsupportedOperationException("The json request \'" + method + "\' is unknown.");
	}

	@Override
	public CompletableFuture<Void> notifyEngineAboutToStart(SerializableExecutionContext serializableExecutionContext) {
		LOG.info("notifyEngineAboutToStart");
		CompletableFuture<Void> future = CompletableFuture.runAsync(new Runnable() {
			@Override
			public void run() {
				TraceAddonSingleton.getTraceAddon().engineAboutToStart(serializableExecutionContext.getExecutionContext());
			}
		});
		return future;
	}

	@Override
	public CompletableFuture<Void> notifyAboutToExecuteStep(SerializableStep serializableStep) {
		LOG.info("notifyAboutToExecuteStep");
		CompletableFuture<Void> future = CompletableFuture.runAsync(new Runnable() {
			@Override
			public void run() {
				TraceAddonSingleton.getTraceAddon().aboutToExecuteStep(serializableStep.getStep());
			}
		});
		return future;
	}

	@Override
	public CompletableFuture<Void> notifyStepExecuted(SerializableStep serializableStep) {
		LOG.info("notifyStepExecuted");
		CompletableFuture<Void> future = CompletableFuture.runAsync(new Runnable() {
			@Override
			public void run() {
				TraceAddonSingleton.getTraceAddon().stepExecuted(serializableStep.getStep());
			}
		});
		return future;
	}

}
