package org.eclipse.gemoc.executionframework.mep.trace;

import java.util.concurrent.CompletableFuture;

import org.eclipse.gemoc.executionframework.mep.trace.types.SerializableExecutionContext;
import org.eclipse.gemoc.executionframework.mep.trace.types.SerializableStep;
import org.eclipse.lsp4j.jsonrpc.services.JsonRequest;

public interface ITraceManagerAddonProtocolServer {

	@JsonRequest
	default CompletableFuture<Void> notifyEngineAboutToStart(SerializableExecutionContext context) {
		throw new UnsupportedOperationException();
	}
	
	@JsonRequest
	default CompletableFuture<Void> notifyAboutToExecuteStep(SerializableStep step) {
		throw new UnsupportedOperationException();
	}
	
	@JsonRequest
	default CompletableFuture<Void> notifyStepExecuted(SerializableStep step) {
		throw new UnsupportedOperationException();
	}

}
