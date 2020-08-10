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



// TODO find a way to adpat to the browser page size
// possible way: https://stackoverflow.com/questions/16265123/resize-svg-when-window-is-resized-in-d3-js
const svgDimensions = { width: 500, height: 500 };
const margin = { left: 5, right: 5, top: 10, bottom: 10 };
const chartDimensions = {
  width: svgDimensions.width - margin.left - margin.right,
  height: svgDimensions.height - margin.bottom - margin.top
};

export class GemocD3Timeline {
	//svg : any;
	svg : d3.Selection<SVGSVGElement, unknown, HTMLElement, any>;
	
	constructor(containerId : string) {
		this.svg  = d3
			.select(containerId)
			.append("svg")
			.attr("width", svgDimensions.width)
			.attr("height", svgDimensions.height)
			.attr("style", "background-color: #FBFAF0");
			
	}
	
	draw() {
		//this.drawAxis();
		this.drawTest();
		
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
        var yBuffer=150;
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
			.attr("cy", 150)
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
	
};
