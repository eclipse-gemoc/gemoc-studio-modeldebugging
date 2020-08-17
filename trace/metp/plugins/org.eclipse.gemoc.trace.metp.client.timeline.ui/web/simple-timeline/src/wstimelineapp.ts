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
import * as d3 from "d3";
import {GemocD3Timeline} from "./gemocd3timeline";

import {ModelExecutionTraceProtocol} from "modelexecutiontraceprotocol"
import * as traceEcore from "./traceEcore"
//import {TraceClient} from "./traceClient";

/*import {
	TraceProtocolClient
} from './traceprotocolClient';

export {
	TraceProtocolClient
}*/


/** The websocket url to use for connecting the timeline webapp  */
/*let hostname: string;


function initializeTimelineApp(port: number,  host?: string) {
   	//websocketUrl = url;
	alert("initializeTimelineApp was called");
	hostname = '127.0.0.1';
	if (typeof host === 'string') {
	} else {
		
	}
	d3.select("#debug").append("p").html("Connecting to "+url);
	// refresh D3 widget
	// TODO
};

(<any>document).initializeTimelineApp =  initializeTimelineApp;*/

//const ReconnectingWebSocket = require('reconnecting-websocket');

export class TimelineWebsocketApp {
	websocketHost : string;
	websocketPort : number;
	timeline : GemocD3Timeline;
	websocket : WebSocket;
	engineId : string
	
	private messageSequence: number;
	private pendingRequests = new Map<number, (e: ModelExecutionTraceProtocol.Response) => void>();
	
	constructor() {
		this.messageSequence = 1;
	}
	
	initWS(port: number,  host?: string) {
		this.websocketPort = port;
		if (typeof host === 'string') {
			this.websocketHost = host;
		} else {
			this.websocketHost = '127.0.0.1';	
		}
		d3.select("#debug").append("p").html("initWS() Connecting to ws://"+this.websocketHost+":"+this.websocketPort+"/metp/"+this.engineId);
		
		this.initWScommon();
		
		// build d3 chart in #d3timeline
		this.timeline = new GemocD3Timeline("#d3timeline");
		
	}
	initWSForEngine(engineName : string, port: number,  host?: string) {
		this.engineId = engineName;
		this.websocketPort = port;
		if (typeof host === 'string') {
			this.websocketHost = host;
		} else {
			this.websocketHost = '127.0.0.1';	
		}
		d3.select("#debug").append("p").html("initWSForEngine() Connecting to ws://"+this.websocketHost+":"+this.websocketPort+"/metp/"+this.engineId);
		d3.select("#debug").append("p").html("For engine "+engineName);
		
		//this.websocket = this.createWebSocket(this.websocketUrl);
		//this.websocket = new WebSocket('ws://'+this.websocketHost+':'+this.websocketPort);
		this.initWScommon();
		
		/*var metpClient = new TraceClient();
	    metpClient.start(this.websocketPort);*/
		
		
		// build d3 chart in #d3timeline
		this.timeline = new GemocD3Timeline("#d3timeline");
	}
	
	initWScommon(){
		this.websocket = new WebSocket('ws://'+this.websocketHost+':'+this.websocketPort+'/metp/'+this.engineId);
		/*this.websocket.onopen = function(e) {
		  alert("[open] Connection established");
		  alert("Sending to server");
		  this.websocket.send("My name is John");
		};*/

		this.websocket.onopen = (evt: MessageEvent) => {
			this.initializeTraceRequest().then(
				event => { // success
					const eventResp = <ModelExecutionTraceProtocol.InitializeTraceResponse> event;
					console.log(`[initializeTraceRequest] Success with ${eventResp}`);
				},
				event => { // failed
					const eventResp = <ModelExecutionTraceProtocol.InitializeTraceResponse> event;
					console.log(`[initializeTraceRequest] Error with ${eventResp}`);
				},
			);
			/*const traceReq = new ModelExecutionTraceProtocol.;
			const json = JSON.stringify(InitializeTraceRequest);
			this.websocket.send(json);*/
			/*
		  	alert("[open] Connection established");
		  	alert("Sending to server");
		  	this.websocket.send("My name is John");*/
		};

		/*this.websocket.onmessage = (evt: MessageEvent) => {
            const data: Data = JSON.parse(evt.data);
            this.setState((prevState: State) => {
                return {data: prevState.data.concat(data).slice(-hertz * slidingTimeWindowSec * 1000)}
            })
        };*/
		/*this.websocket.onmessage = function(event) {
		  alert(`[message] Data received from server: ${event.data}`);
		};*/
		this.websocket.onmessage = (evt: MessageEvent) => {
			//alert(`[message] Data received from server: ${evt.data}`);
			this.dispatchMessage(evt.data);
		};
		
		
		this.websocket.onclose = (evt: CloseEvent) => {
			if (evt.wasClean) {
				alert(`[close] Connection closed cleanly, code=${evt.code} reason=${evt.reason}`);
			} else {
				// e.g. server process killed or network down
				// event.code is usually 1006 in this case
				alert('[close] Connection died');
			}
		};
		
		this.websocket.onerror = (evt: Event) => {
			alert(`[error] ${evt}`);
		};
		
		/*this.websocket.onerror = function(error) {
			error.
		  alert(`[error] ${error.message}`);
		};*/
	}
	
	public receivedStepsStarted(o: Object) {
		let  steps : traceEcore.Step[];
		steps = <traceEcore.Step[]>o;
		//console.log("receivedStepsStarted "+o ); 
		console.log("receivedStepsStarted " + steps.length + " Step"); 
		steps.forEach(step => console.log("   started: " + step._id + " Step"));	
	}
	public receivedStepsEnded(o: Object) {
		
		let  steps : traceEcore.Step[];
		steps = <traceEcore.Step[]>o;
		//console.log("receivedStepsStarted "+o ); 
		console.log("receivedStepsEnded " + steps.length + " Step"); 
		steps.forEach(step => console.log("   ended: " + step._id + " Step"));
	}
	public received( event: ModelExecutionTraceProtocol.Event) {
		switch(event.event) {
			case "stepsStarted": {
				const rawData = JSON.parse(event.body);
				this.receivedStepsStarted(rawData);
				break;
			}
			case "stepsEnded": {
				const rawData = JSON.parse(event.body);
				this.receivedStepsEnded(rawData);
				break;
			}   
			default: { 
      			console.log("Unkwown event "+event.event); 
			    break;              
			}
		}
	}
	
	// ---- protocol requests -------------------------------------------------------------------------------------------------

	public initializeTraceRequest(): Promise<ModelExecutionTraceProtocol.InitializeTraceResponse> {
		return this.send('initializeTrace');
	}
	
	// ---- protocol messages 
	public send(command: 'initializeTrace') : Promise<ModelExecutionTraceProtocol.InitializeTraceResponse>; 
	public send(command: string, args?: any) : Promise<ModelExecutionTraceProtocol.Response>;
	
	public send(command: string, args?: any): Promise<ModelExecutionTraceProtocol.Response> {

		return new Promise((completeDispatch, errorDispatch) => {
			this.doSend(command, args, (result: ModelExecutionTraceProtocol.Response) => {
				if (result.success) {
					completeDispatch(result);
				} else {
					errorDispatch(new Error(result.message));
				}
			});
		});
	}
	
	private doSend(command: string, args: any, clb: (result: ModelExecutionTraceProtocol.Response) => void): void {

		const request: ModelExecutionTraceProtocol.Request = {
			type: 'request',
			seq: this.messageSequence++,
			command: command
		};
		if (args && Object.keys(args).length > 0) {
			request.arguments = args;
		}

		// store callback for this request
		this.pendingRequests.set(request.seq, clb);

		const json = JSON.stringify(request);
		this.websocket.send(json);
		//this.outputStream.write(`Content-Length: ${Buffer.byteLength(json, 'utf8')}\r\n\r\n${json}`, 'utf8');
	}
	
	/** sort messages, event/notification from responses */
	private dispatchMessage(body: string): void {

		const rawData = JSON.parse(body);

		if (typeof rawData.event !== 'undefined') {
			const event = <ModelExecutionTraceProtocol.Event> rawData;
			//alert(`[received event] ${body}`);
			console.log(`[received event] ${body}`);
			// dispatch the event
			this.received(event);
			//this.emit(event.event, event);
		} else {
			const response = <ModelExecutionTraceProtocol.Response> rawData;
			const clb = this.pendingRequests.get(response.request_seq);
			if (clb) {
				alert(`[received response] ${body}`);
				console.log(`[received response] ${body}`);
				console.log(`[received response] ${body}`);
				this.pendingRequests.delete(response.request_seq);
				clb(response);
			}
		}
	}
};

