package org.eclipse.gemoc.executionframework.mep.omniscient;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.apache.log4j.Logger;
import org.eclipse.gemoc.executionframework.debugger.IGemocDebugger;
import org.eclipse.gemoc.executionframework.debugger.OmniscientGenericSequentialModelDebugger;
import org.eclipse.gemoc.executionframework.mep.engine.ExecutionEngineSingleton;
import org.eclipse.lsp4j.jsonrpc.Endpoint;
import org.eclipse.lsp4j.jsonrpc.json.JsonRpcMethod;
import org.eclipse.lsp4j.jsonrpc.json.JsonRpcMethodProvider;
import org.eclipse.lsp4j.jsonrpc.services.ServiceEndpoints;

import com.google.common.base.Supplier;

public class OmniscientDebugProtocolServer
		implements Endpoint, IOmniscientDebugProtocolServer, JsonRpcMethodProvider, OmniscientDebugProtocolClientAware {

	private static final Logger LOG = Logger.getLogger(OmniscientDebugProtocolServer.class);

	private IOmniscientDebugProtocolClient client;

	private Map<String, JsonRpcMethod> supportedMethods;

	public OmniscientDebugProtocolServer() {

	}

	@Override
	public CompletableFuture<Boolean> canStepBackInto() {
		LOG.info("canStepBackInto");
		CompletableFuture<Boolean> future = CompletableFuture.supplyAsync(new Supplier<Boolean>() {
			@Override
			public Boolean get() {
				boolean result = false;
				IGemocDebugger debugger = ExecutionEngineSingleton.acquireEngine().getDebugger();
				if (debugger instanceof OmniscientGenericSequentialModelDebugger) {
					result = ((OmniscientGenericSequentialModelDebugger) debugger).canStepBackInto();
				}
				ExecutionEngineSingleton.releaseEngine();
				return result;
			}
		});
		return future;
	}

	@Override
	public CompletableFuture<Boolean> canStepBackOut() {
		LOG.info("canStepBackOut");
		CompletableFuture<Boolean> future = CompletableFuture.supplyAsync(new Supplier<Boolean>() {
			@Override
			public Boolean get() {
				boolean result = false;
				IGemocDebugger debugger = ExecutionEngineSingleton.acquireEngine().getDebugger();
				if (debugger instanceof OmniscientGenericSequentialModelDebugger) {
					result = ((OmniscientGenericSequentialModelDebugger) debugger).canStepBackOut();
				}
				ExecutionEngineSingleton.releaseEngine();
				return result;
			}
		});
		return future;
	}

	@Override
	public CompletableFuture<Boolean> canStepBackOver() {
		LOG.info("canStepBackOver");
		CompletableFuture<Boolean> future = CompletableFuture.supplyAsync(new Supplier<Boolean>() {
			@Override
			public Boolean get() {
				boolean result = false;
				IGemocDebugger debugger = ExecutionEngineSingleton.acquireEngine().getDebugger();
				if (debugger instanceof OmniscientGenericSequentialModelDebugger) {
					result = ((OmniscientGenericSequentialModelDebugger) debugger).canStepBackOver();
				}
				ExecutionEngineSingleton.releaseEngine();
				return result;
			}
		});
		return future;
	}

	@Override
	public void connect(IOmniscientDebugProtocolClient client) {
		this.client = client;
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
	public CompletableFuture<Void> stepBackInto() {
		LOG.info("stepBackInto");
		CompletableFuture<Void> future = CompletableFuture.runAsync(new Runnable() {
			@Override
			public void run() {
				IGemocDebugger debugger = ExecutionEngineSingleton.acquireEngine().getDebugger();
				if (debugger instanceof OmniscientGenericSequentialModelDebugger) {
					((OmniscientGenericSequentialModelDebugger) debugger).stepBackInto();
				}
				ExecutionEngineSingleton.releaseEngine();
			}
		});
		return future;
	}

	@Override
	public CompletableFuture<Void> stepBackOut() {
		LOG.info("stepBackOut");
		CompletableFuture<Void> future = CompletableFuture.runAsync(new Runnable() {
			@Override
			public void run() {
				IGemocDebugger debugger = ExecutionEngineSingleton.acquireEngine().getDebugger();
				if (debugger instanceof OmniscientGenericSequentialModelDebugger) {
					((OmniscientGenericSequentialModelDebugger) debugger).stepBackOut();
				}
				ExecutionEngineSingleton.releaseEngine();
			}
		});
		return future;
	}

	@Override
	public CompletableFuture<Void> stepBackOver() {
		LOG.info("stepBackOver");
		CompletableFuture<Void> future = CompletableFuture.runAsync(new Runnable() {
			@Override
			public void run() {
				IGemocDebugger debugger = ExecutionEngineSingleton.acquireEngine().getDebugger();
				if (debugger instanceof OmniscientGenericSequentialModelDebugger) {
					((OmniscientGenericSequentialModelDebugger) debugger).stepBackOver();
				}
				ExecutionEngineSingleton.releaseEngine();
			}
		});
		return future;
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

}
