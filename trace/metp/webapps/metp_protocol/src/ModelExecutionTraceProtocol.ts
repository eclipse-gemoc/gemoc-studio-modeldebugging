/*---------------------------------------------------------------------------------------------
 * Copyright (c) 2020 Inria and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *--------------------------------------------------------------------------------------------*/

'use strict';

/** Declaration module describing the model execution trace protocol.
	Auto-generated from json schema. Do not edit manually.
*/
export module ModelExecutionTraceProtocol {

	/** Base class of requests, responses, and events. */
	export interface ProtocolMessage {
		/** Sequence number (also known as message ID). For protocol messages of type 'request' this ID can be used to cancel the request. */
		seq: number;
		/** Message type.
			Values: 'request', 'response', 'event', etc.
		*/
		type: string;
	}

	/** A client or debug adapter initiated request. */
	export interface Request extends ProtocolMessage {
		// type: 'request';
		/** The command to execute. */
		command: string;
		/** Object containing arguments for the command. */
		arguments?: any;
	}

	/** A debug adapter initiated event. */
	export interface Event extends ProtocolMessage {
		// type: 'event';
		/** Type of event. */
		event: string;
		/** Event-specific information. */
		body?: any;
	}

	/** Response for a request. */
	export interface Response extends ProtocolMessage {
		// type: 'response';
		/** Sequence number of the corresponding request. */
		request_seq: number;
		/** Outcome of the request.
			If true, the request was successful and the 'body' attribute may contain the result of the request.
			If the value is false, the attribute 'message' contains the error in short form and the 'body' may contain additional information (see 'ErrorResponse.body.error').
		*/
		success: boolean;
		/** The command requested. */
		command: string;
		/** Contains the raw error in short form if 'success' is false.
			This raw error might be interpreted by the frontend and is not shown in the UI.
			Some predefined values exist.
			Values: 
			'cancelled': request was cancelled.
			etc.
		*/
		message?: string;
		/** Contains request result if success is true and optional error details if success is false. */
		body?: any;
	}

	/** On error (whenever 'success' is false), the body can provide more details. */
	export interface ErrorResponse extends Response {
		body: {
			/** An optional, structured error message. */
			error?: Message;
		};
	}

	/** Event message for 'statesAdded' event type.
		The event indicates that some states habe been added to the trace
	*/
	export interface StatesAddedEvent extends Event {
		// event: 'statesAdded';
		body: {
			/** The list of added states (EMF EObjects) serialized using EMFJSON */
			stateListAsEMFJSON: string;
		};
	}

	/** Event message for 'stepsStarted' event type.
		The event indicates that some states have started
	*/
	export interface StepsStartedEvent extends Event {
		// event: 'stepsStarted';
		body: {
			/** The list of started states (EMF EObjects) serialized using EMFJSON */
			stateListAsEMFJSON: string;
		};
	}

	/** Event message for 'stepsEnded' event type.
		The event indicates that some states have ended
	*/
	export interface StepsEndedEvent extends Event {
		// event: 'stepsEnded';
		body: {
			/** The list of ended states (EMF EObjects) serialized using EMFJSON */
			stateListAsEMFJSON: string;
		};
	}

	/** Event message for 'valuesAdded' event type.
		The event indicates that values have been added
	*/
	export interface ValuesAddedEvent extends Event {
		// event: 'valuesAdded';
		body: {
			/** The list of added values (EMF EObjects) serialized using EMFJSON */
			valueListAsEMFJSON: string;
		};
	}

	/** Event message for 'dimensionsAdded' event type.
		The event indicates that dimensions have been added
	*/
	export interface DimensionsAddedEvent extends Event {
		// event: 'dimensionsAdded';
		body: {
			/** The list of added dimensions (EMF EObjects) serialized using EMFJSON */
			dimensionListAsEMFJSON: string;
		};
	}

	/** InitializeTrace request; value of command field is 'initializeTrace'.
		The 'initializeTrace' request is sent as the first request from the client to the model execution trace
		Until the Model Execution Trace has responded to with an 'initializeTrace' response, the client must not send any additional requests or events to the Model Execution Trace.
		In addition the Model Execution Trace is not allowed to send any requests or events to the client until it has responded with an 'initializeTrace' response.
		The 'initializeTrace' request may only be sent once.
	*/
	export interface InitializeTraceRequest extends Request {
		// command: 'initializeTrace';
	}

	/** Response to 'initializeTrace' request. */
	export interface InitializeTraceResponse extends Response {
		/** The capabilities of this Model Execution Trace. */
		body?: TraceCapabilities;
	}

	/** GetFullTrace request; value of command field is 'getFullTrace'.
		The 'getFullTrace' request is sent to ask for the EMF resource containing the trace
	*/
	export interface GetFullTraceRequest extends Request {
		// command: 'getFullTrace';
		arguments?: GetFullTraceRequestArguments;
	}

	/** Arguments for 'getFullTrace' request. */
	export interface GetFullTraceRequestArguments {
		/** The serialization format of the resource
			Values: 'json', 'xmi', etc.
		*/
		serializationFormat?: string;
	}

	/** Response to 'getFullTrace' request. */
	export interface GetFullTraceResponse extends Response {
		/** the Trace EMF model in the desired textual format */
		body?: string;
	}

	/** Information about the capabilities of a Model Execution trace. */
	export interface TraceCapabilities {
		/** The model execution trace supports the 'XXX' request. */
		supportsXXXRequest?: boolean;
	}

	/** A structured message object. Used to return errors from requests. */
	export interface Message {
		/** Unique identifier for the message. */
		id: number;
		/** A format string for the message. Embedded variables have the form '{name}'.
			If variable name starts with an underscore character, the variable does not contain user data (PII) and can be safely used for telemetry purposes.
		*/
		format: string;
		/** An object used as a dictionary for looking up the variables in the format string. */
		variables?: { [key: string]: string; };
		/** If true send to telemetry. */
		sendTelemetry?: boolean;
		/** If true show user. */
		showUser?: boolean;
		/** An optional url where additional information about this message can be found. */
		url?: string;
		/** An optional label that is presented to the user as the UI for opening the url. */
		urlLabel?: string;
	}
}

