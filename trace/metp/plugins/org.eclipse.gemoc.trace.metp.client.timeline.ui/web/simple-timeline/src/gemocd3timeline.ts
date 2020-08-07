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
	
	constructor(containerId : String) {
		this.svg  = d3
			.select("#"+containerId)
			.append("svg")
			.attr("width", svgDimensions.width)
			.attr("height", svgDimensions.height)
			.attr("style", "background-color: #FBFAF0");	

		this.svg
			.append("circle")
			.attr("r", 20)
			.attr("cx", 20)
			.attr("cy", 20);
	}
	
};
