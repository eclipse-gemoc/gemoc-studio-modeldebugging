package org.eclipse.gemoc.executionframework.mep.omniscient;

import java.util.concurrent.CompletableFuture;

import org.eclipse.lsp4j.jsonrpc.services.JsonRequest;

public interface IOmniscientDebugProtocolServer {

	@JsonRequest
	default CompletableFuture<Void> stepBackInto() {
		throw new UnsupportedOperationException();
	}
	
	@JsonRequest
	default CompletableFuture<Void> stepBackOver() {
		throw new UnsupportedOperationException();
	}
	
	@JsonRequest
	default CompletableFuture<Void> stepBackOut() {
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
