/*******************************************************************************
 * Copyright (c) 2016 Inria and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Inria - initial API and implementation
 *******************************************************************************/
package org.eclipse.gemoc.tracemm.semdiff.eval.internal.reporting;

public class MatchingReportEntry extends ReportEntry {

	private String leftTracemodel;
	private String rightTracemodel;
	private long matchingTime = -1;
	
	public MatchingReportEntry(String leftTracemodel, String rightTracemodel,
			long executionTime) {
		super();
		this.leftTracemodel = leftTracemodel;
		this.rightTracemodel = rightTracemodel;
		this.matchingTime = executionTime;
	}
	
	public String getLeftTracemodel() {
		return leftTracemodel;
	}
	
	public String getRightTracemodel() {
		return rightTracemodel;
	}
	
	public long getMatchingTime() {
		return matchingTime;
	}
	
	@Override
	public String print() {
		StringBuffer str = new StringBuffer();
			str.append(leftTracemodel);
			str.append(Report.ENTRY_SEPARATOR);
			str.append(rightTracemodel);
			str.append(Report.ENTRY_SEPARATOR);
			str.append(matchingTime);
		return str.toString();
	}
}
