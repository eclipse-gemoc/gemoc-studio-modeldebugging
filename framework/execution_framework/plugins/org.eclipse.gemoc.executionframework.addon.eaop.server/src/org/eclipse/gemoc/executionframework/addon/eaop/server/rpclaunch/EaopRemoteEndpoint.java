package org.eclipse.gemoc.executionframework.addon.eaop.server.rpclaunch;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

import org.eclipse.gemoc.protocols.eaop.api.messages.EaopNotificationMessage;
import org.eclipse.gemoc.protocols.eaop.api.messages.EaopRequestMessage;
import org.eclipse.gemoc.protocols.eaop.api.messages.EaopResponseMessage;
import org.eclipse.lsp4j.jsonrpc.Endpoint;
import org.eclipse.lsp4j.jsonrpc.MessageConsumer;
import org.eclipse.lsp4j.jsonrpc.RemoteEndpoint;
import org.eclipse.lsp4j.jsonrpc.messages.RequestMessage;
import org.eclipse.lsp4j.jsonrpc.messages.ResponseError;

public class EaopRemoteEndpoint extends RemoteEndpoint {

	private final AtomicInteger nextSeqId = new AtomicInteger();
	
	public EaopRemoteEndpoint(MessageConsumer out, Endpoint localEndpoint) {
		super(out, localEndpoint);
	}
	
	public EaopRemoteEndpoint(MessageConsumer out, Endpoint localEndpoint,
			Function<Throwable, ResponseError> exceptionHandler) {
		super(out, localEndpoint, exceptionHandler);
	}
	@Override
	protected EaopRequestMessage createRequestMessage(String method, Object parameter) {
		EaopRequestMessage requestMessage = new EaopRequestMessage();
		requestMessage.setId(nextSeqId.incrementAndGet());
		requestMessage.setMethod(method);
		requestMessage.setParams(parameter);
		return requestMessage;
	}

	@Override
	protected EaopResponseMessage createResponseMessage(RequestMessage requestMessage) {
		EaopResponseMessage responseMessage = new EaopResponseMessage();
		responseMessage.setResponseId(nextSeqId.incrementAndGet());
		responseMessage.setRawId(requestMessage.getRawId());
		responseMessage.setMethod(requestMessage.getMethod());
		return responseMessage;
	}

	@Override
	protected EaopNotificationMessage createNotificationMessage(String method, Object parameter) {
		EaopNotificationMessage notificationMessage = new EaopNotificationMessage();
		notificationMessage.setId(nextSeqId.incrementAndGet());
		notificationMessage.setMethod(method);
		notificationMessage.setParams(parameter);
		return notificationMessage;
	}

}
