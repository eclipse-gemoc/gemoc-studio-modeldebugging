package org.eclipse.gemoc.executionframework.mep.trace;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.eclipse.gemoc.executionframework.mep.trace.types.SerializableStep;
import org.eclipse.lsp4j.jsonrpc.services.JsonRequest;

public interface ITraceExplorerProtocolServer {
	
	@JsonRequest
	default CompletableFuture<Boolean> inReplayMode() {
		throw new UnsupportedOperationException();
	}
	
	@JsonRequest
	default CompletableFuture<Void> loadLastState() {
		throw new UnsupportedOperationException();
	}
	
	@JsonRequest
	default CompletableFuture<List<SerializableStep>> getCallStack() {
		throw new UnsupportedOperationException();
	}
	
	@JsonRequest
	default CompletableFuture<Boolean> stepInto() {
		throw new UnsupportedOperationException();
	}
	
	@JsonRequest
	default CompletableFuture<Boolean> stepOver() {
		throw new UnsupportedOperationException();
	}
	
	@JsonRequest
	default CompletableFuture<Boolean> stepReturn() {
		throw new UnsupportedOperationException();
	}
	
	@JsonRequest
	default CompletableFuture<Boolean> stepBackInto() {
		throw new UnsupportedOperationException();
	}
	
	@JsonRequest
	default CompletableFuture<Boolean> stepBackOver() {
		throw new UnsupportedOperationException();
	}
	
	@JsonRequest
	default CompletableFuture<Boolean> stepBackOut() {
		throw new UnsupportedOperationException();
	}
	
	@JsonRequest
	default CompletableFuture<Boolean> canStepBackInto() {
		throw new UnsupportedOperationException();
	}
	
	@JsonRequest
	default CompletableFuture<Boolean> canStepBackOver() {
		throw new UnsupportedOperationException();
	}
	
	@JsonRequest
	default CompletableFuture<Boolean> canStepBackOut() {
		throw new UnsupportedOperationException();
	}
	
}
