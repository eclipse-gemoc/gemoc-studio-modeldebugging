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
import * as wstimelineapp from "./wstimelineapp";
import { GemocD3Timeline } from "./gemocd3timeline";


(<any>document).webTimeline = new wstimelineapp.TimelineWebsocketApp();


///  test timeline
//var timeline : GemocD3Timeline;
//timeline = new GemocD3Timeline("#testtimeline");
//timeline.draw();


function sayHello() {
	alert("say hello was called");
    return "Hello!";
};

(<any>document).sayHello =  sayHello;
