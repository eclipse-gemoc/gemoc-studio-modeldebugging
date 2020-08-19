/*---------------------------------------------------------------------------------------------
 * Copyright (c) 2020 Inria and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *--------------------------------------------------------------------------------------------*/

package org.eclipse.gemoc.trace.metp.addonbased.server.metp.data;

import com.google.gson.annotations.SerializedName
import java.util.Map
import org.eclipse.lsp4j.generator.JsonRpcData
import org.eclipse.lsp4j.jsonrpc.messages.Either
import org.eclipse.lsp4j.jsonrpc.validation.NonNull

/** Declaration of parameters, response bodies, and event bodies for the model execution trace protocol.
	Auto-generated from json schema. Do not edit manually.
*/

@JsonRpcData
class StatesAddedEventArguments  {
	/** The list of added states (EMF EObjects) serialized using EMFJSON */
	// @NonNull
	String stateListAsEMFJSON;
}

@JsonRpcData
class StepsStartedEventArguments  {
	/** The list of started states (EMF EObjects) serialized using EMFJSON */
	// @NonNull
	String stateListAsEMFJSON;
}

@JsonRpcData
class StepsEndedEventArguments  {
	/** The list of ended states (EMF EObjects) serialized using EMFJSON */
	// @NonNull
	String stateListAsEMFJSON;
}

@JsonRpcData
class ValuesAddedEventArguments  {
	/** The list of added values (EMF EObjects) serialized using EMFJSON */
	// @NonNull
	String valueListAsEMFJSON;
}

@JsonRpcData
class DimensionsAddedEventArguments  {
	/** The list of added dimensions (EMF EObjects) serialized using EMFJSON */
	// @NonNull
	String dimensionListAsEMFJSON;
}

/** Response to 'initializeTrace' request. */
@JsonRpcData
class InitializeTraceResponse  {
	/** The capabilities of this Model Execution Trace. */
	TraceCapabilities body;
}

/** Arguments for 'getFullTrace' request. */
@JsonRpcData
class GetFullTraceRequestArguments  {
	/** The serialization format of the resource
		Values: 'json', 'xmi', etc.
	*/
	String serializationFormat;
}

/** Response to 'getFullTrace' request. */
@JsonRpcData
class GetFullTraceResponse  {
	/** the Trace EMF model in the desired textual format */
	String body;
}

/** Information about the capabilities of a Model Execution trace. */
@JsonRpcData
class TraceCapabilities  {
	/** The model execution trace supports the 'XXX' request. */
	boolean supportsXXXRequest;
}

/** A structured message object. Used to return errors from requests. */
@JsonRpcData
class Message  {
	/** Unique identifier for the message. */
	// @NonNull
	Integer id;
	/** A format string for the message. Embedded variables have the form '{name}'.
		If variable name starts with an underscore character, the variable does not contain user data (PII) and can be safely used for telemetry purposes.
	*/
	// @NonNull
	String format;
	/** An object used as a dictionary for looking up the variables in the format string. */
	Map<String, String> variables;
	/** If true send to telemetry. */
	boolean sendTelemetry;
	/** If true show user. */
	boolean showUser;
	/** An optional url where additional information about this message can be found. */
	String url;
	/** An optional label that is presented to the user as the UI for opening the url. */
	String urlLabel;
}

