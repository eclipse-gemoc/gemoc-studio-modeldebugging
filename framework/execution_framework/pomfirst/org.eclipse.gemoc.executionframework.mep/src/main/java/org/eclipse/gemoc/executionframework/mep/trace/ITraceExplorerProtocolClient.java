package org.eclipse.gemoc.executionframework.mep.trace;

import java.util.concurrent.CompletableFuture;

import org.eclipse.lsp4j.jsonrpc.services.JsonRequest;

public interface ITraceExplorerProtocolClient {
	
	@JsonRequest
	default CompletableFuture<Void> updatedCallStack() {
		throw new UnsupportedOperationException();
	}
	
}
