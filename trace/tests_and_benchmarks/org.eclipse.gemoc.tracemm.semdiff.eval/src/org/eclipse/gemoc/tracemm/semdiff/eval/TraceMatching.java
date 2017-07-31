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
package org.eclipse.gemoc.tracemm.semdiff.eval;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import org.eclipse.gemoc.tracemm.semdiff.eval.internal.MatchResult;
import org.eclipse.gemoc.tracemm.semdiff.eval.internal.TraceMatcher;
import org.eclipse.gemoc.tracemm.semdiff.eval.internal.TraceMatchingEvent;
import org.eclipse.gemoc.tracemm.semdiff.eval.internal.TraceMatchingEvent.EventType;
import org.eclipse.gemoc.tracemm.semdiff.eval.internal.reporting.MatchingReport;
import org.eclipse.gemoc.tracemm.semdiff.eval.internal.reporting.MatchingReportEntry;
import org.eclipse.gemoc.tracemm.semdiff.eval.internal.TraceMatchingListener;

public abstract class TraceMatching extends Evaluation implements
		TraceMatchingListener {

	private static final String ITERATIONS_PROGRAM_ARGUMENT = "iterations";

	private static MatchingReport report;
	
	private TraceMatchingEvent previousStartEvent = null;
	private String currentLeftPath = null;
	private String currentRightPath = null;
	
	@BeforeClass
	public static void initializeReport() {
		report = new MatchingReport();
	}

	@AfterClass
	public static void printReport() {
		report.printReportToFile();
	}
	
	@Override
	public void notify(TraceMatchingEvent event) {
		if (event.getType() == EventType.MATCHING_START) {
			previousStartEvent = event;
		} else if (event.getType() == EventType.MATCHING_END) {
			long start = previousStartEvent.getTimepoint();
			long end = event.getTimepoint();
			report.addReportEntry(new MatchingReportEntry(currentLeftPath, currentRightPath, end-start));
			previousStartEvent = null;
//			System.out.println(end-start);
		}
	}
	
	@Test
	public void testmodel_2() {
		MatchResult result = matchFumlTestmodel(2, 2);
		assertTrue(result.matchedWithoutErrors());
		assertTrue(result.matches());
	}

	@Test
	public void anonCompany_ExampleB_V1_V2_false_false() {
		MatchResult result = matchAnonExampleB(1, 2, false, false);
		assertTrue(result.matchedWithoutErrors());
		assertTrue(result.matches());
	}

	@Test
	public void anonCompany_ExampleB_V1_V2_false_true() {
		MatchResult result = matchAnonExampleB(1, 2, false, true);
		assertTrue(result.matchedWithoutErrors());
		assertTrue(result.matches());
	}
	
	@Test
	public void anonCompany_ExampleB_V1_V2_true_false() {
		MatchResult result = matchAnonExampleB(1, 2, true, false);
		assertTrue(result.matchedWithoutErrors());
		assertFalse(result.matches());
	}
	
	@Test
	public void anonCompany_ExampleB_V1_V2_true_true() {
		MatchResult result = matchAnonExampleB(1, 2, true, true);
		assertTrue(result.matchedWithoutErrors());
		assertFalse(result.matches());
	}
	
	@Test
	public void anonCompany_ExampleB_V2_V3_true_true_true() {
		MatchResult result = matchAnonExampleB(2, 3, true, true, true);
		assertTrue(result.matchedWithoutErrors());
		assertTrue(result.matches());
	}
	
	@Test
	public void anonCompany_ExampleB_V2_V3_true_true_false() {
		MatchResult result = matchAnonExampleB(2, 3, true, true, false);
		assertTrue(result.matchedWithoutErrors());
		assertTrue(result.matches());
	}
	
	@Test
	public void anonCompany_ExampleB_V2_V3_true_false_true() {
		MatchResult result = matchAnonExampleB(2, 3, true, false, true);
		assertTrue(result.matchedWithoutErrors());
		assertTrue(result.matches());
	}
	
	@Test
	public void anonCompany_ExampleB_V2_V3_true_false_false() {
		MatchResult result = matchAnonExampleB(2, 3, true, false, false);
		assertTrue(result.matchedWithoutErrors());
		assertTrue(result.matches());
	}
	
	@Test
	public void anonCompany_ExampleB_V2_V3_false_true_true() {
		MatchResult result = matchAnonExampleB(2, 3, false, true, true);
		assertTrue(result.matchedWithoutErrors());
		assertTrue(result.matches());
	}
	
	@Test
	public void anonCompany_ExampleB_V2_V3_false_true_false() {
		MatchResult result = matchAnonExampleB(2, 3, false, true, false);
		assertTrue(result.matchedWithoutErrors());
		assertTrue(result.matches());
	}
	
	@Test
	public void anonCompany_ExampleB_V2_V3_false_false_true() {
		MatchResult result = matchAnonExampleB(2, 3, false, false, true);
		assertTrue(result.matchedWithoutErrors());
		assertFalse(result.matches());
	}
	
	@Test
	public void anonCompany_ExampleB_V2_V3_false_false_false() {
		MatchResult result = matchAnonExampleB(2, 3, false, false, false);
		assertTrue(result.matchedWithoutErrors());
		assertFalse(result.matches());
	}
	
	@Test
	public void nokia_ExampleA_V1_V2_1_1() {
		matchNokiaExampleA(1, 2, 1, 1);
	}
	
	@Test
	public void nokia_ExampleA_V1_V2_1_2() {
		matchNokiaExampleA(1, 2, 1, 2);
	}
	
	@Test
	public void nokia_ExampleA_V1_V2_2_1() {
		matchNokiaExampleA(1, 2, 2, 1);
	}
	
	@Test
	public void nokia_ExampleA_V1_V2_2_2() {
		matchNokiaExampleA(1, 2, 2, 2);
	}
	
	@Test
	public void nokia_ExampleA_V2_V3_1_1() {
		matchNokiaExampleA(2, 3, 1, 1);
	}
	
	@Test
	public void nokia_ExampleA_V2_V3_1_2() {
		matchNokiaExampleA(2, 3, 1, 2);
	}
	
	@Test
	public void nokia_ExampleA_V2_V3_2_1() {
		matchNokiaExampleA(2, 3, 2, 1);
	}
	
	@Test
	public void nokia_ExampleA_V2_V3_2_2() {
		matchNokiaExampleA(2, 3, 2, 2);
	}
	
	@Test
	public void nokia_ExampleA_V3_V4_1_1() {
		matchNokiaExampleA(3, 4, 1, 1);
	}
	
	@Test
	public void nokia_ExampleA_V3_V4_1_2() {
		matchNokiaExampleA(3, 4, 1, 2);
	}
	
	@Test
	public void nokia_ExampleA_V3_V4_2_1() {
		matchNokiaExampleA(3, 4, 2, 1);
	}
	
	@Test
	public void nokia_ExampleA_V3_V4_2_2() {
		matchNokiaExampleA(3, 4, 2, 2);
	}
	
	@Test
	public void IBM_2557_V1_V2_1() {
		matchIBM2557(1, 2, 1);
	}
	
	@Test
	public void IBM_2557_V1_V2_2() {
		matchIBM2557(1, 2, 2);
	}
	
	@Test
	public void IBM_2557_V1_V2_3() {
		matchIBM2557(1, 2, 3);
	}

	protected abstract boolean domainSpecific();

	protected MatchResult matchFumlTestmodel(int modelNumber1,
			int modelNumber2) {
		String leftTracemodelPath = deriveFumlTestmodelTracemodelPath(
				modelNumber1, domainSpecific());
		String rightTracemodelPath = deriveFumlTestmodelTracemodelPath(
				modelNumber2, domainSpecific());
		MatchResult matchResult = matchFumlTraces(leftTracemodelPath,
				rightTracemodelPath);
		return matchResult;
	}

	protected MatchResult matchAnonExampleB(int version1, int version2,
			boolean exists, boolean found) {
		return matchAnonExampleB(version1, version2, exists, found, false);
	}
	
	protected MatchResult matchAnonExampleB(int version1, int version2,
			boolean exists, boolean found, boolean acc) {
		String leftTracemodePath = deriveAnonExampleTracemodelPath(version1,
				exists, found, acc, domainSpecific());
		String rightTracemodelPath = deriveAnonExampleTracemodelPath(version2,
				exists, found, acc, domainSpecific());
		MatchResult matchResult = matchFumlTraces(leftTracemodePath,
				rightTracemodelPath);
		return matchResult;
	}

	private MatchResult matchNokiaExampleA(int version1, int version2, int f, int d) {
		String leftTracemodelPath = deriveNokiaExampleATracemodelPath(version1, f, d, domainSpecific());
		String rightTracemodelPath = deriveNokiaExampleATracemodelPath(version2, f, d, domainSpecific());
		MatchResult matchResult = matchFumlTraces(leftTracemodelPath,
				rightTracemodelPath);
		assertTrue(matchResult.matchedWithoutErrors());
		assertFalse(matchResult.matches());
		return matchResult;
	}
	
	private MatchResult matchIBM2557(int version1, int version2, int var2558) {
		String leftTracemodelPath = deriveIBM2557TracemodelPath(version1, var2558, domainSpecific());
		String rightTracemodelPath = deriveIBM2557TracemodelPath(version2, var2558, domainSpecific());
		MatchResult matchResult = matchFumlTraces(leftTracemodelPath,
				rightTracemodelPath);
		assertTrue(matchResult.matchedWithoutErrors());
		assertFalse(matchResult.matches());
		return matchResult;
	}
	
	private MatchResult matchFumlTraces(String leftTracemodelPath,
			String rightTracemodelPath) {
System.out.print("left: " + leftTracemodelPath + " - " + "right: " + rightTracemodelPath);
		if (domainSpecific()) 
			report.setDomainSpecificMatching();
		setTracemodelPaths(leftTracemodelPath, rightTracemodelPath);
		MatchResult matchResult = null;
		for (int i = 0; i < getIterationNumber(); ++i) {
//			System.out.println(i);
			TraceMatcher matcher = setupTraceMatcher();
			boolean match = matcher.match(leftTracemodelPath,
					rightTracemodelPath, FUML_METMODEL_PATH,
					FUML_CONFIGURATION_PATH,
					getFumlTracemetamodelPath(domainSpecific()),
					getFumlMatchrules(domainSpecific()));
			matchResult = updateMatchResult(matchResult, match,
					matcher.matchedWithoutErrors());
		}
		unsetTracemodelPaths();
		return matchResult;
	}

	private int getIterationNumber() {
		int interations = 1;
		String loopProgramArgument = System.getProperty(ITERATIONS_PROGRAM_ARGUMENT);
		if(loopProgramArgument != null) {
			interations = Integer.parseInt(loopProgramArgument);
		}
		return interations;
	}

	private void setTracemodelPaths(String leftTracemodelPath, String rightTracemodelPath) {
		currentLeftPath = leftTracemodelPath;
		currentRightPath = rightTracemodelPath;
	}
	
	private void unsetTracemodelPaths() {
		currentLeftPath = null;
		currentRightPath = null;
	}

	private TraceMatcher setupTraceMatcher() {
		TraceMatcher matcher = new TraceMatcher();
		matcher.registerListener(this);
		return matcher;
	}

	private MatchResult updateMatchResult(MatchResult matchResult,
			boolean matches, boolean matchedWithoutErrors) {
		MatchResult result = null;
		if (matchResult == null) {
			result = new MatchResult(matches, matchedWithoutErrors);
		} else {
			result = matchResult;
			if (result.matches() != matches) {
				result.setMatches(false);
				result.setMatchingInconclusive();
			}
			if (result.matchedWithoutErrors() != matchedWithoutErrors) {
				result.setMatchedWithoutErrors(false);
			}
		}
		return result;
	}
}
