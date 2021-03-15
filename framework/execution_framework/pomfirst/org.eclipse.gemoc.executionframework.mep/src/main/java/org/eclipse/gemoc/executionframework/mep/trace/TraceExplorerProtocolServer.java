package org.eclipse.gemoc.executionframework.mep.trace;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.apache.log4j.Logger;
import org.eclipse.gemoc.executionframework.mep.trace.types.SerializableStep;
import org.eclipse.gemoc.trace.commons.model.trace.Step;
import org.eclipse.gemoc.trace.gemoc.api.ITraceExplorer;
import org.eclipse.gemoc.trace.gemoc.traceaddon.GenericTraceEngineAddon;
import org.eclipse.lsp4j.jsonrpc.Endpoint;
import org.eclipse.lsp4j.jsonrpc.json.JsonRpcMethod;
import org.eclipse.lsp4j.jsonrpc.json.JsonRpcMethodProvider;
import org.eclipse.lsp4j.jsonrpc.services.ServiceEndpoints;

import com.google.common.base.Supplier;

public class TraceExplorerProtocolServer implements Endpoint, ITraceExplorerProtocolServer, JsonRpcMethodProvider, TraceExplorerProtocolClientAware, ITraceExplorerEventListener {

	private static final Logger LOG = Logger.getLogger(TraceExplorerProtocolServer.class);
	
	ITraceExplorerProtocolClient client;
	
	private Map<String, JsonRpcMethod> supportedMethods;
	
	@Override
	public void connect(ITraceExplorerProtocolClient client) {
		this.client = client;
	}
	
	public void init() {
		IRemoteTraceAddon traceAddon = TraceAddonSingleton.acquireTraceAddon();
		if (traceAddon instanceof GenericTraceEngineAddon) {
			ITraceExplorer<?, ?, ?, ?, ?> traceExplorer = ((GenericTraceEngineAddon) traceAddon).getTraceExplorer();
			if (traceExplorer instanceof IRemoteTraceExplorer) {
				((IRemoteTraceExplorer) traceExplorer).addTraceExplorerEventListener(this);
			}
		}
		TraceAddonSingleton.releaseTraceAddon();
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
	public CompletableFuture<Boolean> inReplayMode() {
		LOG.info("inReplayMode");
		CompletableFuture<Boolean> future = CompletableFuture.supplyAsync(new Supplier<Boolean>() {
			@Override
			public Boolean get() {
				boolean result = false;
				IRemoteTraceAddon traceAddon = TraceAddonSingleton.getTraceAddon();
				if (traceAddon instanceof GenericTraceEngineAddon) {
					result = ((GenericTraceEngineAddon) traceAddon).getTraceExplorer().isInReplayMode();
				}
				return result;
			}
		});
		return future;
	}

	@Override
	public CompletableFuture<Void> loadLastState() {
		LOG.info("loadLastState");
		CompletableFuture<Void> future = CompletableFuture.runAsync(new Runnable() {
			@Override
			public void run() {
				IRemoteTraceAddon traceAddon = TraceAddonSingleton.getTraceAddon();
				if (traceAddon instanceof GenericTraceEngineAddon) {
					((GenericTraceEngineAddon) traceAddon).getTraceExplorer().loadLastState();
				}
			}
		});
		return future;
	}

	@Override
	public CompletableFuture<List<SerializableStep>> getCallStack() {
		LOG.info("getCallStack");
		CompletableFuture<List<SerializableStep>> future = CompletableFuture.supplyAsync(new Supplier<List<SerializableStep>>() {
			@Override
			public List<SerializableStep> get() {
				List<SerializableStep> result = new ArrayList<>();
				IRemoteTraceAddon traceAddon = TraceAddonSingleton.getTraceAddon();
				if (traceAddon instanceof GenericTraceEngineAddon) {
					for (Step<?> step : ((GenericTraceEngineAddon) traceAddon).getTraceExplorer().getCallStack()) {
						result.add(new SerializableStep(step));
					}
				}
				return result;
			}
		});
		return future;
	}

	@Override
	public CompletableFuture<Boolean> stepInto() {
		LOG.info("stepInto");
		CompletableFuture<Boolean> future = CompletableFuture.supplyAsync(new Supplier<Boolean>() {
			@Override
			public Boolean get() {
				boolean result = false;
				IRemoteTraceAddon traceAddon = TraceAddonSingleton.getTraceAddon();
				if (traceAddon instanceof GenericTraceEngineAddon) {
					result = ((GenericTraceEngineAddon) traceAddon).getTraceExplorer().stepInto();
				}
				return result;
			}
		});
		return future;
	}

	@Override
	public CompletableFuture<Boolean> stepOver() {
		LOG.info("stepOver");
		CompletableFuture<Boolean> future = CompletableFuture.supplyAsync(new Supplier<Boolean>() {
			@Override
			public Boolean get() {
				boolean result = false;
				IRemoteTraceAddon traceAddon = TraceAddonSingleton.getTraceAddon();
				if (traceAddon instanceof GenericTraceEngineAddon) {
					result = ((GenericTraceEngineAddon) traceAddon).getTraceExplorer().stepOver();
				}
				return result;
			}
		});
		return future;
	}

	@Override
	public CompletableFuture<Boolean> stepReturn() {
		LOG.info("stepReturn");
		CompletableFuture<Boolean> future = CompletableFuture.supplyAsync(new Supplier<Boolean>() {
			@Override
			public Boolean get() {
				boolean result = false;
				IRemoteTraceAddon traceAddon = TraceAddonSingleton.getTraceAddon();
				if (traceAddon instanceof GenericTraceEngineAddon) {
					result = ((GenericTraceEngineAddon) traceAddon).getTraceExplorer().stepReturn();
				}
				return result;
			}
		});
		return future;
	}

	@Override
	public CompletableFuture<Boolean> stepBackInto() {
		LOG.info("stepBackInto");
		CompletableFuture<Boolean> future = CompletableFuture.supplyAsync(new Supplier<Boolean>() {
			@Override
			public Boolean get() {
				boolean result = false;
				IRemoteTraceAddon traceAddon = TraceAddonSingleton.getTraceAddon();
				if (traceAddon instanceof GenericTraceEngineAddon) {
					result = ((GenericTraceEngineAddon) traceAddon).getTraceExplorer().stepBackInto();
				}
				return result;
			}
		});
		return future;
	}

	@Override
	public CompletableFuture<Boolean> stepBackOver() {
		LOG.info("stepBackOver");
		CompletableFuture<Boolean> future = CompletableFuture.supplyAsync(new Supplier<Boolean>() {
			@Override
			public Boolean get() {
				boolean result = false;
				IRemoteTraceAddon traceAddon = TraceAddonSingleton.getTraceAddon();
				if (traceAddon instanceof GenericTraceEngineAddon) {
					result = ((GenericTraceEngineAddon) traceAddon).getTraceExplorer().stepBackOver();
				}
				return result;
			}
		});
		return future;
	}

	@Override
	public CompletableFuture<Boolean> stepBackOut() {
		LOG.info("stepBackOut");
		CompletableFuture<Boolean> future = CompletableFuture.supplyAsync(new Supplier<Boolean>() {
			@Override
			public Boolean get() {
				boolean result = false;
				IRemoteTraceAddon traceAddon = TraceAddonSingleton.getTraceAddon();
				if (traceAddon instanceof GenericTraceEngineAddon) {
					result = ((GenericTraceEngineAddon) traceAddon).getTraceExplorer().stepBackOut();
				}
				return result;
			}
		});
		return future;
	}

	@Override
	public CompletableFuture<Boolean> canStepBackInto() {
		LOG.info("canStepBackInto");
		CompletableFuture<Boolean> future = CompletableFuture.supplyAsync(new Supplier<Boolean>() {
			@Override
			public Boolean get() {
				boolean result = false;
				IRemoteTraceAddon traceAddon = TraceAddonSingleton.getTraceAddon();
				if (traceAddon instanceof GenericTraceEngineAddon) {
					result = ((GenericTraceEngineAddon) traceAddon).getTraceExplorer().canStepBackInto();
				}
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
				IRemoteTraceAddon traceAddon = TraceAddonSingleton.getTraceAddon();
				if (traceAddon instanceof GenericTraceEngineAddon) {
					result = ((GenericTraceEngineAddon) traceAddon).getTraceExplorer().canStepBackOver();
				}
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
				IRemoteTraceAddon traceAddon = TraceAddonSingleton.getTraceAddon();
				if (traceAddon instanceof GenericTraceEngineAddon) {
					result = ((GenericTraceEngineAddon) traceAddon).getTraceExplorer().canStepBackOut();
				}
				return result;
			}
		});
		return future;
	}

	@Override
	public void updatedCallStack() {
		this.client.updatedCallStack().join();
	}

}
