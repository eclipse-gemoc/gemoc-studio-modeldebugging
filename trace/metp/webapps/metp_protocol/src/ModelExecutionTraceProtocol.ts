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

	/** Cancel request; value of command field is 'cancel'.
		The 'cancel' request is used by the frontend in two situations:
		- to indicate that it is no longer interested in the result produced by a specific request issued earlier
		- to cancel a progress sequence. Clients should only call this request if the capability 'supportsCancelRequest' is true.
		This request has a hint characteristic: a debug adapter can only be expected to make a 'best effort' in honouring this request but there are no guarantees.
		The 'cancel' request may return an error if it could not cancel an operation but a frontend should refrain from presenting this error to end users.
		A frontend client should only call this request if the capability 'supportsCancelRequest' is true.
		The request that got canceled still needs to send a response back. This can either be a normal result ('success' attribute true)
		or an error response ('success' attribute false and the 'message' set to 'cancelled').
		Returning partial results from a cancelled request is possible but please note that a frontend client has no generic way for detecting that a response is partial or not.
		 The progress that got cancelled still needs to send a 'progressEnd' event back.
		 A client should not assume that progress just got cancelled after sending the 'cancel' request.
	*/
	export interface CancelRequest extends Request {
		// command: 'cancel';
		arguments?: CancelArguments;
	}

	/** Arguments for 'cancel' request. */
	export interface CancelArguments {
		/** The ID (attribute 'seq') of the request to cancel. If missing no request is cancelled.
			Both a 'requestId' and a 'progressId' can be specified in one request.
		*/
		requestId?: number;
		/** The ID (attribute 'progressId') of the progress to cancel. If missing no progress is cancelled.
			Both a 'requestId' and a 'progressId' can be specified in one request.
		*/
		progressId?: string;
	}

	/** Response to 'cancel' request. This is just an acknowledgement, so no body field is required. */
	export interface CancelResponse extends Response {
	}

	/** Event message for 'initialized' event type.
		This event indicates that the debug adapter is ready to accept configuration requests (e.g. SetBreakpointsRequest, SetExceptionBreakpointsRequest).
		A debug adapter is expected to send this event when it is ready to accept configuration requests (but not before the 'initialize' request has finished).
		The sequence of events/requests is as follows:
		- adapters sends 'initialized' event (after the 'initialize' request has returned)
		- frontend sends zero or more 'setBreakpoints' requests
		- frontend sends one 'setFunctionBreakpoints' request (if capability 'supportsFunctionBreakpoints' is true)
		- frontend sends a 'setExceptionBreakpoints' request if one or more 'exceptionBreakpointFilters' have been defined (or if 'supportsConfigurationDoneRequest' is not defined or false)
		- frontend sends other future configuration requests
		- frontend sends one 'configurationDone' request to indicate the end of the configuration.
	*/
	export interface InitializedEvent extends Event {
		// event: 'initialized';
	}

	/** Event message for 'stopped' event type.
		The event indicates that the execution of the debuggee has stopped due to some condition.
		This can be caused by a break point previously set, a stepping request has completed, by executing a debugger statement etc.
	*/
	export interface StoppedEvent extends Event {
		// event: 'stopped';
		body: {
			/** The reason for the event.
				For backward compatibility this string is shown in the UI if the 'description' attribute is missing (but it must not be translated).
				Values: 'step', 'breakpoint', 'exception', 'pause', 'entry', 'goto', 'function breakpoint', 'data breakpoint', 'instruction breakpoint', etc.
			*/
			reason: string;
			/** The full reason for the event, e.g. 'Paused on exception'. This string is shown in the UI as is and must be translated. */
			description?: string;
			/** The thread which was stopped. */
			threadId?: number;
			/** A value of true hints to the frontend that this event should not change the focus. */
			preserveFocusHint?: boolean;
			/** Additional information. E.g. if reason is 'exception', text contains the exception name. This string is shown in the UI. */
			text?: string;
			/** If 'allThreadsStopped' is true, a debug adapter can announce that all threads have stopped.
				- The client should use this information to enable that all threads can be expanded to access their stacktraces.
				- If the attribute is missing or false, only the thread with the given threadId can be expanded.
			*/
			allThreadsStopped?: boolean;
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

	/** Initialize request; value of command field is 'initialize'.
		The 'initialize' request is sent as the first request from the client to the debug adapter
		in order to configure it with client capabilities and to retrieve capabilities from the debug adapter.
		Until the debug adapter has responded to with an 'initialize' response, the client must not send any additional requests or events to the debug adapter.
		In addition the debug adapter is not allowed to send any requests or events to the client until it has responded with an 'initialize' response.
		The 'initialize' request may only be sent once.
	*/
	export interface InitializeRequest extends Request {
		// command: 'initialize';
		arguments: InitializeRequestArguments;
	}

	/** Arguments for 'initialize' request. */
	export interface InitializeRequestArguments {
		/** The ID of the (frontend) client using this adapter. */
		clientID?: string;
		/** The human readable name of the (frontend) client using this adapter. */
		clientName?: string;
		/** The ID of the debug adapter. */
		adapterID: string;
		/** The ISO-639 locale of the (frontend) client using this adapter, e.g. en-US or de-CH. */
		locale?: string;
		/** If true all line numbers are 1-based (default). */
		linesStartAt1?: boolean;
		/** If true all column numbers are 1-based (default). */
		columnsStartAt1?: boolean;
		/** Determines in what format paths are specified. The default is 'path', which is the native format.
			Values: 'path', 'uri', etc.
		*/
		pathFormat?: string;
		/** Client supports the optional type attribute for variables. */
		supportsVariableType?: boolean;
		/** Client supports the paging of variables. */
		supportsVariablePaging?: boolean;
		/** Client supports the runInTerminal request. */
		supportsRunInTerminalRequest?: boolean;
		/** Client supports memory references. */
		supportsMemoryReferences?: boolean;
		/** Client supports progress reporting. */
		supportsProgressReporting?: boolean;
	}

	/** Response to 'initialize' request. */
	export interface InitializeResponse extends Response {
		/** The capabilities of this debug adapter. */
		body?: Capabilities;
	}

	/** Attach request; value of command field is 'attach'.
		The attach request is sent from the client to the debug adapter to attach to a debuggee that is already running.
		Since attaching is debugger/runtime specific, the arguments for this request are not part of this specification.
	*/
	export interface AttachRequest extends Request {
		// command: 'attach';
		arguments: AttachRequestArguments;
	}

	/** Arguments for 'attach' request. Additional attributes are implementation specific. */
	export interface AttachRequestArguments {
		/** Optional data from the previous, restarted session.
			The data is sent as the 'restart' attribute of the 'terminated' event.
			The client should leave the data intact.
		*/
		__restart?: any;
	}

	/** Response to 'attach' request. This is just an acknowledgement, so no body field is required. */
	export interface AttachResponse extends Response {
	}

	/** Information about the capabilities of a Model Execution trace. */
	export interface TraceCapabilities {
		/** The model execution trace supports the 'XXX' request. */
		supportsXXXRequest?: boolean;
	}

	/** Information about the capabilities of a debug adapter. */
	export interface Capabilities {
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

