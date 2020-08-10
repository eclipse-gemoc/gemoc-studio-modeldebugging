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
//import * as D3Timeline from "./d3-timeline/d3-timeline" 

/*const svgDimensions = { width: 500, height: 500 };
const margin = { left: 5, right: 5, top: 10, bottom: 10 };
const chartDimensions = {
  width: svgDimensions.width - margin.left - margin.right,
  height: svgDimensions.height - margin.bottom - margin.top
};

const createBlankSvg = () =>
  d3
    .select("body")
    .append("svg")
    .attr("width", svgDimensions.width)
    .attr("height", svgDimensions.height)
    .attr("style", "background-color: #FBFAF0");

const createYScale = () =>
  d3
    .scaleLinear()
    .domain([0, 100])  // nb dimensions
    .range([0, chartDimensions.height]);

const createChartGroup = () =>
  svg
    .append("g")
    .attr("transform", `translate(${margin.left}, ${margin.top})`)
    .attr("width", chartDimensions.width)
    .attr("height", chartDimensions.height);

const svg = createBlankSvg();
const yScale = createYScale();
const chartGroup = createChartGroup();*/

//let webTimeline = new wstimelineapp.TimelineWebsocketApp();
(<any>document).webTimeline = new wstimelineapp.TimelineWebsocketApp();


///  test timeline
var timeline : GemocD3Timeline;
timeline = new GemocD3Timeline("#testtimeline");
timeline.draw();

/*var width = 500;
var testData = [
        {times: [{"starting_time": 1355752800000, "ending_time": 1355759900000}, {"starting_time": 1355767900000, "ending_time": 1355774400000}]},
        {times: [{"starting_time": 1355759910000, "ending_time": 1355761900000}, ]},
        {times: [{"starting_time": 1355761910000, "ending_time": 1355763910000}]}
      ];

(<any>document).chart = D3Timeline;

var svg = d3.select("#timeline1").append("svg").attr("width", width)
          .datum(testData).call((<any>document).chart);*/

//const webTimeline = new wstimelineapp.TimelineWebsocketApp();



//generateBarChart(chartGroup);

/*const svg = d3
  .select("body")
  .append("svg")
  .attr("width", 1024)
  .attr("height", 800)
  .attr("style", "background-color: #FBFAF0");*/


/*svg
  .append("text")
  .attr("x", 100)
  .attr("y", 100)
  .text("Hello d3js");

svg
  .append("circle")
  .attr("r", 20)
  .attr("cx", 20)
  .attr("cy", 20);*/

function sayHello() {
	alert("say hello was called");
    return "Hello!";
};

(<any>document).sayHello =  sayHello;
