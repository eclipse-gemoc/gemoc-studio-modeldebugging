/*******************************************************************************
 * Copyright (c) 2020 Inria and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Inria - initial API and implementation
 *******************************************************************************/
package org.eclipse.gemoc.trace.metp.addonbased.server.launch;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

import org.eclipse.gemoc.trace.metp.addonbased.server.metp.messages.METPNotificationMessage;
import org.eclipse.gemoc.trace.metp.addonbased.server.metp.messages.METPRequestMessage;
import org.eclipse.gemoc.trace.metp.addonbased.server.metp.messages.METPResponseMessage;
import org.eclipse.lsp4j.jsonrpc.Endpoint;
import org.eclipse.lsp4j.jsonrpc.MessageConsumer;
import org.eclipse.lsp4j.jsonrpc.RemoteEndpoint;
import org.eclipse.lsp4j.jsonrpc.messages.RequestMessage;
import org.eclipse.lsp4j.jsonrpc.messages.ResponseError;

public class METPRemoteEndpoint extends RemoteEndpoint {

	private final AtomicInteger nextSeqId = new AtomicInteger();

	public METPRemoteEndpoint(MessageConsumer out, Endpoint localEndpoint) {
		super(out, localEndpoint);
	}

	public METPRemoteEndpoint(MessageConsumer out, Endpoint localEndpoint,
			Function<Throwable, ResponseError> exceptionHandler) {
		super(out, localEndpoint, exceptionHandler);
	}

	@Override
	protected METPRequestMessage createRequestMessage(String method, Object parameter) {
		METPRequestMessage requestMessage = new METPRequestMessage();
		requestMessage.setId(nextSeqId.incrementAndGet());
		requestMessage.setMethod(method);
		requestMessage.setParams(parameter);
		return requestMessage;
	}

	@Override
	protected METPResponseMessage createResponseMessage(RequestMessage requestMessage) {
		METPResponseMessage responseMessage = new METPResponseMessage();
		responseMessage.setResponseId(nextSeqId.incrementAndGet());
		responseMessage.setRawId(requestMessage.getRawId());
		responseMessage.setMethod(requestMessage.getMethod());
		return responseMessage;
	}

	@Override
	protected METPNotificationMessage createNotificationMessage(String method, Object parameter) {
		METPNotificationMessage notificationMessage = new METPNotificationMessage();
		notificationMessage.setId(nextSeqId.incrementAndGet());
		notificationMessage.setMethod(method);
		notificationMessage.setParams(parameter);
		return notificationMessage;
	}

}
