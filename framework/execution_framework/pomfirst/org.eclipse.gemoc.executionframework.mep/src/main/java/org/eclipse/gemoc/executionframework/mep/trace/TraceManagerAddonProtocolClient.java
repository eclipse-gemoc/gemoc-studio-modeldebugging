package org.eclipse.gemoc.executionframework.mep.trace;

import java.util.concurrent.CompletableFuture;

import org.apache.log4j.Logger;
import org.eclipse.gemoc.executionframework.mep.trace.types.SerializableExecutionContext;
import org.eclipse.gemoc.executionframework.mep.trace.types.SerializableStep;
import org.eclipse.gemoc.trace.commons.model.trace.Dimension;
import org.eclipse.gemoc.trace.commons.model.trace.State;
import org.eclipse.gemoc.trace.commons.model.trace.Step;
import org.eclipse.gemoc.trace.commons.model.trace.TracedObject;
import org.eclipse.gemoc.trace.commons.model.trace.Value;
import org.eclipse.gemoc.trace.gemoc.api.ITraceExplorer;
import org.eclipse.gemoc.trace.gemoc.traceaddon.GenericTraceEngineAddon;
import org.eclipse.gemoc.xdsmlframework.api.core.IExecutionEngine;
import org.eclipse.lsp4j.jsonrpc.Endpoint;

public class TraceManagerAddonProtocolClient extends GenericTraceEngineAddon implements Endpoint, ITraceManagerAddonProtocolClient, TraceManagerAddonProtocolServerAware {

	private static final Logger LOG = Logger.getLogger(TraceManagerAddonProtocolClient.class);
	
	private ITraceManagerAddonProtocolServer server;
		
	@Override
	public void connect(ITraceManagerAddonProtocolServer server) {
		this.server = server;		
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
	public void engineAboutToStart(IExecutionEngine<?> engine) {
		this.server.notifyEngineAboutToStart(new SerializableExecutionContext(engine.getExecutionContext())).join();
	}
	
	@Override
	public void aboutToExecuteStep(IExecutionEngine<?> executionEngine, Step<?> step) {
		this.server.notifyAboutToExecuteStep(new SerializableStep(step)).join();
	}

	@Override
	public void stepExecuted(IExecutionEngine<?> executionEngine, Step<?> step) {
		this.server.notifyStepExecuted(new SerializableStep(step)).join();
	}

	private TraceExplorerProtocolClient headlessTraceExplorer;
	
	public void setTraceExplorer(TraceExplorerProtocolClient traceExplorer) {
		this.headlessTraceExplorer = traceExplorer;
	}
	
	@Override
	public ITraceExplorer<Step<?>, State<?,?>, TracedObject<?>, Dimension<?>, Value<?>> getTraceExplorer() {
		return this.headlessTraceExplorer;
	}
	
}
