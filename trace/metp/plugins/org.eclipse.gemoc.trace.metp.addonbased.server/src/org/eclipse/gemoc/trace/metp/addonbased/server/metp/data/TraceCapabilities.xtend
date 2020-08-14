package org.eclipse.gemoc.trace.metp.addonbased.server.metp.data


import org.eclipse.lsp4j.generator.JsonRpcData

/**
 * Information about the capabilities of a Model Execution Trace.
 */
@JsonRpcData
class TraceCapabilities {
	/**
	 * The Model Execution Trace supports the 'XXX' request.
	 * <p>
	 * This is an optional property.
	 */
	Boolean supportsXXXRequest;	
}