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


/** The websocket url to use for connecting the timeline webapp  */
let websocketUrl: string;


function initializeTimelineApp(url : string ) {
   	websocketUrl = url;
	alert("initializeTimelineApp was called");
	d3.select("#debug").append("p").html("Connecting to "+url);
	// refresh D3 widget
	// TODO
};

(<any>document).initializeTimelineApp =  initializeTimelineApp;

export class TimelineWebsocketApp {
	websocketUrl : String;
	timeline : GemocD3Timeline;
	
	initWS(url : string) {
		this.websocketUrl = url;
		d3.select("#debug").append("p").html("Connecting to "+url);
		
		// build d3 chart in #d3timeline
		this.timeline = new GemocD3Timeline("#d3timeline");
		
	}
	initWSForEngine(url : string, engineName : string) {
		this.websocketUrl = url;
		d3.select("#debug").append("p").html("Connecting to "+url);
		d3.select("#debug").append("p").html("For engine "+engineName);
		
		// build d3 chart in #d3timeline
		this.timeline = new GemocD3Timeline("d3timeline");
		
	}
};

