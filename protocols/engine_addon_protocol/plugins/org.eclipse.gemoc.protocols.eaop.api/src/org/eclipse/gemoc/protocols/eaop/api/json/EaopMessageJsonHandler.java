package org.eclipse.gemoc.protocols.eaop.api.json;

import java.util.Map;
import java.util.function.Consumer;

import org.eclipse.lsp4j.jsonrpc.json.JsonRpcMethod;
import org.eclipse.lsp4j.jsonrpc.json.MessageJsonHandler;

import com.google.gson.GsonBuilder;

public class EaopMessageJsonHandler extends MessageJsonHandler {
	public EaopMessageJsonHandler(Map<String, JsonRpcMethod> supportedMethods) {
		super(supportedMethods);
	}

	public EaopMessageJsonHandler(Map<String, JsonRpcMethod> supportedMethods, Consumer<GsonBuilder> configureGson) {
		super(supportedMethods, configureGson);
	}

	public GsonBuilder getDefaultGsonBuilder() {
		GsonBuilder gsonBuilder = super.getDefaultGsonBuilder();
		gsonBuilder.registerTypeAdapterFactory(new EaopMessageTypeAdapter.Factory(this));
		// add more adapter if required
		return gsonBuilder;
	}
}
