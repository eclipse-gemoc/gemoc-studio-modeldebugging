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
package org.eclipse.gemoc.trace.metp.addonbased.server.metp.json;

import java.util.Map;
import java.util.function.Consumer;

import org.eclipse.lsp4j.jsonrpc.debug.adapters.DebugEnumTypeAdapter;
import org.eclipse.lsp4j.jsonrpc.debug.adapters.DebugMessageTypeAdapter;
import org.eclipse.lsp4j.jsonrpc.json.JsonRpcMethod;
import org.eclipse.lsp4j.jsonrpc.json.MessageJsonHandler;

import com.google.gson.GsonBuilder;

/**
 * 
 * deal with the conversion from Model Execution Trace Protocol style JSON
 * to/from LSP4J's JSONRPC implementation 
 * Note: reuse {@link DebugMessageTypeAdapter} from LSP4J. (Maybe this can be simplified or
 * replaced by our own copy in order to remove dependency to org.eclipse.lsp4j.jsonrpc.debug)
 */
public class METPMessageJsonHandler extends MessageJsonHandler {

	public METPMessageJsonHandler(Map<String, JsonRpcMethod> supportedMethods) {
		super(supportedMethods);
	}

	public METPMessageJsonHandler(Map<String, JsonRpcMethod> supportedMethods, Consumer<GsonBuilder> configureGson) {
		super(supportedMethods, configureGson);
	}

	public GsonBuilder getDefaultGsonBuilder() {
		return super.getDefaultGsonBuilder().registerTypeAdapterFactory(new DebugMessageTypeAdapter.Factory(this))
				.registerTypeAdapterFactory(new DebugEnumTypeAdapter.Factory());
	}

}
