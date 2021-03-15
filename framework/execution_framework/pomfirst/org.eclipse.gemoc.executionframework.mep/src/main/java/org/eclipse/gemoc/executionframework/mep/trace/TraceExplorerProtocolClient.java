package org.eclipse.gemoc.executionframework.mep.trace;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.apache.log4j.Logger;
import org.eclipse.gemoc.executionframework.mep.trace.types.SerializableStep;
import org.eclipse.gemoc.trace.commons.model.trace.Step;
import org.eclipse.gemoc.trace.gemoc.traceaddon.GenericTraceExplorer;
import org.eclipse.lsp4j.jsonrpc.Endpoint;

public class TraceExplorerProtocolClient extends GenericTraceExplorer implements Endpoint, ITraceExplorerProtocolClient, TraceExplorerProtocolServerAware {

	private static final Logger LOG = Logger.getLogger(TraceExplorerProtocolClient.class);

	private ITraceExplorerProtocolServer server;
	
	public TraceExplorerProtocolClient() {
		super(null);
	}
	
	@Override
	public void connect(ITraceExplorerProtocolServer server) {
		this.server = server;
	}

	@Override
	public CompletableFuture<?> request(String method, Object parameter) {
		throw new UnsupportedOperationException("The json request \'" + method + "\' is unknown.");
	}

	@Override
	public void notify(String method, Object parameter) {
		LOG.info("notify " + method);
	}
	
	@Override
	public void loadLastState() {
		this.server.loadLastState().join();
	}

	@Override
	public boolean stepInto() {
		return this.server.stepInto().join();
	}

	@Override
	public boolean stepOver() {
		return this.server.stepOver().join();
	}

	@Override
	public boolean stepReturn() {
		return this.server.stepReturn().join();
	}

	@Override
	public boolean canStepBackInto() {
		return this.server.canStepBackInto().join();
	}

	@Override
	public boolean canStepBackOver() {
		return this.server.canStepBackOver().join();
	}

	@Override
	public boolean canStepBackOut() {
		return this.server.canStepBackOut().join();
	}

	@Override
	public boolean stepBackInto() {
		return this.server.stepBackInto().join();
	}

	@Override
	public boolean stepBackOver() {
		return this.server.stepBackOver().join();
	}

	@Override
	public boolean stepBackOut() {
		return this.server.stepBackOut().join();
	}

	@Override
	public boolean isInReplayMode() {
		return this.server.inReplayMode().join();
	}

	@Override
	public List<Step<?>> getCallStack() {
		List<Step<?>> result = new ArrayList<>();
		for (SerializableStep step : this.server.getCallStack().join()) {
			result.add(step.getStep());
		}
		return result;
	}
	
	@Override
	public CompletableFuture<Void> updatedCallStack() {
		CompletableFuture<Void> future = CompletableFuture.runAsync(new Runnable() {
			@Override
			public void run() {
				notifyListeners();
			}
		});
		return future;
	}
	
}
