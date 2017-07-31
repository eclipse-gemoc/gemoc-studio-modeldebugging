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

public class TraceReportEntry extends ReportEntry {

	private String tracemodel;
	private int statenumber;
	
	public TraceReportEntry(String tracemodel, int statenumber) {
		super();
		this.tracemodel = tracemodel;
		this.statenumber = statenumber;
	}

	public int getStatenumber() {
		return statenumber;
	}

	public String getTracemodel() {
		return tracemodel;
	}
	
	@Override
	public String print() {
		StringBuffer str = new StringBuffer();
			str.append(tracemodel);
			str.append(Report.ENTRY_SEPARATOR);
			str.append(statenumber);
		return str.toString();
	}
}
