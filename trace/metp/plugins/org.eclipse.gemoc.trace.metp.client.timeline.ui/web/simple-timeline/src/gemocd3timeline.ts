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
import * as d3_scale  from 'd3-scale'
import * as d3_axis  from 'd3-axis'
import * as test_data from './data'
import * as genericTraceEcore from "./genericTraceEcore"


var w = window,
    d = document,
    e = d.documentElement,
    g = d.getElementsByTagName('body')[0],
    x = w.innerWidth || e.clientWidth || g.clientWidth,
    y = w.innerHeight|| e.clientHeight|| g.clientHeight;

var svgDimensions = { width: x-10, height: 100 };
const margin = { left: 5, right: 5, top: 10, bottom: 10 };
const chartDimensions = {
  width: svgDimensions.width - margin.left - margin.right,
  height: svgDimensions.height - margin.bottom - margin.top
};

export class GemocD3Timeline {
	
	private containerID : string
	//svg : any;
	public svg : d3.Selection<SVGSVGElement, unknown, HTMLElement, any>;
	
	private AllRTDStatesLineYOffset : number // y position of the AllRTDStatesLine
	private rtdStateHeight : number	 // heigth of all RTD state graphical elements
	private xPadding : number	// padding of elements
	private yPadding : number
	private fontSize : string
	private minSvgWidth : number
	
	constructor(containerId : string) {
		this.containerID = containerId;
		
		// place the y offsets of the various elements in the svg
		this.AllRTDStatesLineYOffset = 0;
		
		// set some sizes
		this.rtdStateHeight = 20;
		this.xPadding = 2;
		this.yPadding = 2;
		this.fontSize = "12px";
		this.minSvgWidth = 150;

		/*var that : GemocD3Timeline = this; // when 'this'' is not accessible, create a local var to replace it
		// register for resize	
		d3.select(window).on('resize', function(d,i){ 
					that.resizeGemocD3TimelineSVGToBrowserSize();
	                });*/
		
		
		this.initSvg();

		
			
	}
	
	initSvg(){
		// clear if any previous svg there
		d3.select(this.containerID).select("svg").remove();
		
		this.svg  = d3
			.select(this.containerID)
			.append("svg")
			.attr("width", svgDimensions.width)
			.attr("height", svgDimensions.height)
			.attr("style", "background-color: #FBFAF0");
		

		
		// enable tooltip
		d3.select("body").append("div")   
		    .attr("class", "tooltip") 
			.attr("id", "tooltip")              
		    .style("opacity", 0);
	}
	
	
	draw() {
		//this.drawAxis();
		//this.drawTest();
		
	}
	redraw(trace: genericTraceEcore.Trace) {
		this.initSvg();
		// basic behavior: the svg grows according to the data in order to use the browser native scrollbar, a more efficient version should filter the represented data and implements its own scroll
		this.resizeSVGToData(trace);
		this.drawAllRTDStatesLine(trace);
	}
	
	drawAllRTDStatesLine(trace: genericTraceEcore.Trace){
		
		// find tooltip
		var div = d3.select("#tooltip")
		
		
		var dataIndex=1;
        var xBuffer=20;
        var yBuffer=50;
        var lineLength=400;
        //var spacing =50;

		var bulletR = 5;
		var bulletWidth = bulletR*2;
		var xSpacing = bulletR;
		const xPadding = this.xPadding;
		const yPadding = this.yPadding;
		
		var that : GemocD3Timeline = this; // when 'this'' is not accessible, create a local var to replace it
		// title text
		this.svg.append("text")
    		.attr("x", 0)
			.attr("y", this.AllRTDStatesLineYOffset+15)
    		.text("All Executions States")
			.attr("font-family", "sans-serif")
            .attr("font-size", this.fontSize);
		
		
		const yOffset = this.AllRTDStatesLineYOffset + 25;
		const rtdWidth = this.rtdStateHeight;
		// draw one rounded rectangle with text per state on a line
		var g = this.svg.selectAll(".aggregatedRTDState")
			.data(trace.states)
			.enter().append("g")	// g.transform is used to place both the text and the graphical element (Note: is this efficient ?)
			.attr("class", "aggregatedRTDState")
			.attr("transform", function(d, i){	
				const x = xPadding + i*(rtdWidth + xPadding);
				const y = yOffset;
				return "translate(" + x + "," + y + ")";
			});
		g.append("rect")
			.attr("width", rtdWidth)
    		.attr("height", this.rtdStateHeight)
			.attr("rx", this.rtdStateHeight/2)
			//.attr("ry", 6)
			.style("fill", "blue")
			.on("mouseover", function(state) {  
	            div.transition()        
	                .duration(200)      
	                .style("opacity", .9);      
	            div .html(state._id + "<br/>"  + that.presentSateValues(state))  
	                .style("left", (d3.event.pageX) + "px")     
	                .style("top", (d3.event.pageY - 28) + "px");    
	            })                  
	        .on("mouseout", function(d) {       
	            div.transition()        
	                .duration(500)      
	                .style("opacity", 0);   
	        });
		g.append("text")
			.attr("x", xPadding)
			.attr("y", this.rtdStateHeight-yPadding )
			.attr("font-family", "sans-serif")
            .attr("font-size", this.fontSize)
		    .style("fill", "white")
		    .text(function(d, i) {
		    	return i; // use position as label
		    });
		
	};
	
	presentSateValues(state : genericTraceEcore.State) : string {
		var str = "";
		for( let valueRef of state.values) {
			str += valueRef.eClass + " " + valueRef.$ref+"<br>\n";
		}
		return str;
	}
	
	resizeSVGToData(trace: genericTraceEcore.Trace){
		const rtdWidth = this.rtdStateHeight;
		svgDimensions.width = Math.max(trace.states.length * (rtdWidth + this.xPadding), this.minSvgWidth);
		// svgDimensions.height = y - margin.bottom - margin.top;
		this.svg.attr("width", svgDimensions.width);
	}
	
	drawAxis() {
		var scale = d3_scale.scaleLinear();
		scale.domain([0, 1]);
		scale.range([0, 800]);
		var axis = d3_axis.axisTop(scale);
		axis.scale(scale);
	}
	
	drawTest() {
		var dataIndex=1;
        var xBuffer=20;
        var yBuffer=50;
        var lineLength=400;
        var spacing =50;

		var div = d3.select("body").append("div")   
		    .attr("class", "tooltip")               
		    .style("opacity", 0);

		this.svg.append("g").selectAll("circle").data(test_data.resultCollectionSpainApr19).enter().append("circle")                               
	        .attr("r", 5)   
	    	.attr("cx", function(d,i){
	                var spacing = lineLength/(test_data.resultCollectionSpainApr19.length);
                    return xBuffer+(i*spacing);
	                })
			.attr("cy", 50)
			.on("mouseover", function(d) {      
	            div.transition()        
	                .duration(200)      
	                .style("opacity", .9);      
	            div .html(d.party + "<br/>"  + d.seats)  
	                .style("left", (d3.event.pageX) + "px")     
	                .style("top", (d3.event.pageY - 28) + "px");    
	            })                  
	        .on("mouseout", function(d) {       
	            div.transition()        
	                .duration(500)      
	                .style("opacity", 0);   
	        }); 
		
		/*this.svg
			.append("circle")
			.attr("r", 20)
			.attr("cx", 20)
			.attr("cy", 20);*/
	}
	
	/**
	 * allows to resize the svg to the size of the browser windows
     * typical use:
     *    var that : GemocD3Timeline = this;
	 * 	  d3.select(window).on('resize', function(d,i){that.resizeGemocD3TimelineSVG();});
     * cf. https://stackoverflow.com/questions/16265123/resize-svg-when-window-is-resized-in-d3-js
	 */
	resizeGemocD3TimelineSVGToBrowserSize () {
		x = w.innerWidth || e.clientWidth || g.clientWidth;
    	// y = w.innerHeight|| e.clientHeight|| g.clientHeight;
		svgDimensions.width = x - 10;
		// svgDimensions.height = y - margin.bottom - margin.top;
		this.svg.attr("width", svgDimensions.width);
		// this.svg.attr("height", y);
	}
	
};
