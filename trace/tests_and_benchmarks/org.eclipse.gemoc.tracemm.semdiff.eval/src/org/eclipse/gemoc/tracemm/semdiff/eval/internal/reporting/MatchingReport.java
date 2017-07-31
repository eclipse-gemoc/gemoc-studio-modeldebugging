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

public class MatchingReport extends Report {

	private static final String MATCHING_REPORT_FOLDER = "/matching/";
	private static final String GENERIC_MATCHING_REPORT_FOLDER = MATCHING_REPORT_FOLDER
			+ "/trace_generic/";
	private static final String DOMAINSPECIFIC_MATCHING_REPORT_FOLDER = MATCHING_REPORT_FOLDER
			+ "/trace_domainspecific/";

	private boolean domainSpecificMatching = false;

	public void setDomainSpecificMatching() {
		this.domainSpecificMatching = true;
	}
	
	@Override
	protected String printHeader() {
		return "left" + ENTRY_SEPARATOR + "right" + ENTRY_SEPARATOR
				+ "matchingtime" + LINE_SEPARATOR;
	}

	@Override
	protected String getReportFolder() {
		return REPORT_FOLDER
				+ (domainSpecificMatching ? DOMAINSPECIFIC_MATCHING_REPORT_FOLDER
						: GENERIC_MATCHING_REPORT_FOLDER);
	}
}
