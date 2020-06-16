package org.eclipse.gemoc.executionframework.mep.launch;

import java.util.function.Function;

import org.eclipse.lsp4j.jsonrpc.Endpoint;
import org.eclipse.lsp4j.jsonrpc.MessageConsumer;
import org.eclipse.lsp4j.jsonrpc.debug.DebugRemoteEndpoint;
import org.eclipse.lsp4j.jsonrpc.messages.ResponseError;

public class MEPRemoteEndpoint extends DebugRemoteEndpoint {

	public MEPRemoteEndpoint(MessageConsumer out, Endpoint localEndpoint) {
		super(out, localEndpoint);
	}
	
	public MEPRemoteEndpoint(MessageConsumer out, Endpoint localEndpoint,
			Function<Throwable, ResponseError> exceptionHandler) {
		super(out, localEndpoint, exceptionHandler);
	}

}
