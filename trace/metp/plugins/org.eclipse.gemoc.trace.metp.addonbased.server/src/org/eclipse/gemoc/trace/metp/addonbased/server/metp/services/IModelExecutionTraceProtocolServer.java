/*---------------------------------------------------------------------------------------------
 * Copyright (c) 2020 Inria and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *--------------------------------------------------------------------------------------------*/

package org.eclipse.gemoc.trace.metp.addonbased.server.metp.services;

import java.util.concurrent.CompletableFuture;
import org.eclipse.lsp4j.jsonrpc.services.JsonRequest;
import org.eclipse.gemoc.trace.metp.addonbased.server.metp.data.*;
/** Server interface for the model execution trace protocol.
	Auto-generated from json schema. Do not edit manually.
*/
public interface  IModelExecutionTraceProtocolServer {

	/** InitializeTrace request; value of command field is 'initializeTrace'.
		The 'initializeTrace' request is sent as the first request from the client to the model execution trace
		Until the Model Execution Trace has responded to with an 'initializeTrace' response, the client must not send any additional requests or events to the Model Execution Trace.
		In addition the Model Execution Trace is not allowed to send any requests or events to the client until it has responded with an 'initializeTrace' response.
		The 'initializeTrace' request may only be sent once.
	*/
	@JsonRequest
	default CompletableFuture<TraceCapabilities> initializeTrace() {
		throw new UnsupportedOperationException();
	}

	/** GetFullTrace request; value of command field is 'getFullTrace'.
		The 'getFullTrace' request is sent to ask for the EMF resource containing the trace
	*/
	@JsonRequest
	default CompletableFuture<String> getFullTrace(GetFullTraceRequestArguments arguments) {
		throw new UnsupportedOperationException();
	}
}

