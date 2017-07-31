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

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.modelexecution.xmof.states.states.StateSystem;

import org.eclipse.gemoc.tracemm.semdiff.eval.internal.ModelExecutor;
import org.eclipse.gemoc.tracemm.semdiff.eval.internal.reporting.TraceReport;
import org.eclipse.gemoc.tracemm.semdiff.eval.internal.reporting.TraceReportEntry;

public abstract class TraceExtraction extends Evaluation {

	private static TraceReport report;
 
	@BeforeClass
	public static void initializeReport() {
		report = new TraceReport();
	}
	
	@AfterClass
	public static void printReport() {
		report.printReportToFile();
	}
	
	@Test
	public void testmodel_2() {
		executeFumlTestmodel(2, domainSpecific());
	}
	
	@Test
	public void anonCompany_ExampleB_V1_false_false() {
		executeAnonExampleB(1, false, false, domainSpecific());
	}
	
	@Test
	public void anonCompany_ExampleB_V1_true_false() {
		executeAnonExampleB(1, true, false, domainSpecific());
	}
	
	@Test
	public void anonCompany_ExampleB_V1_false_true() {
		executeAnonExampleB(1, false, true, domainSpecific());
	}
	
	@Test
	public void anonCompany_ExampleB_V1_true_true() {
		executeAnonExampleB(1, true, true, domainSpecific());
	}
	
	@Test
	public void anonCompany_ExampleB_V2_false_false() {
		executeAnonExampleB(2, false, false, domainSpecific());
	}
	
	@Test
	public void anonCompany_ExampleB_V2_true_false() {
		executeAnonExampleB(2, true, false, domainSpecific());
	}
	
	@Test
	public void anonCompany_ExampleB_V2_false_true() {
		executeAnonExampleB(2, false, true, domainSpecific());
	}
	
	@Test
	public void anonCompany_ExampleB_V2_true_true() {
		executeAnonExampleB(2, true, true, domainSpecific());
	}
	
	@Test
	public void anonCompany_ExampleB_V3_true_true_true() {
		executeAnonExampleB(3, true, true, true, domainSpecific());
	}
	
	@Test
	public void anonCompany_ExampleB_V3_true_true_false() {
		executeAnonExampleB(3, true, true, false, domainSpecific());
	}
	
	@Test
	public void anonCompany_ExampleB_V3_true_false_true() {
		executeAnonExampleB(3, true, false, true, domainSpecific());
	}
	
	@Test
	public void anonCompany_ExampleB_V3_true_false_false() {
		executeAnonExampleB(3, true, false, false, domainSpecific());
	}
	
	@Test
	public void anonCompany_ExampleB_V3_false_true_true() {
		executeAnonExampleB(3, false, true, true, domainSpecific());
	}
	
	@Test
	public void anonCompany_ExampleB_V3_false_true_false() {
		executeAnonExampleB(3, false, true, false, domainSpecific());
	}
	
	@Test
	public void anonCompany_ExampleB_V3_false_false_true() {
		executeAnonExampleB(3, false, false, true, domainSpecific());
	}
	
	@Test
	public void anonCompany_ExampleB_V3_false_false_false() {
		executeAnonExampleB(3, false, false, false, domainSpecific());
	}
	
	@Test
	public void nokia_ExampleA_V1_1_1() {
		executeNokiaExampleA(1, 1, 1, domainSpecific());
	}
	
	@Test
	public void nokia_ExampleA_V1_1_2() {
		executeNokiaExampleA(1, 1, 2, domainSpecific());
	}
	
	@Test
	public void nokia_ExampleA_V1_2_1() {
		executeNokiaExampleA(1, 2, 1, domainSpecific());
	}
	
	@Test
	public void nokia_ExampleA_V1_2_2() {
		executeNokiaExampleA(1, 2, 2, domainSpecific());
	}
	
	@Test
	public void nokia_ExampleA_V2_1_1() {
		executeNokiaExampleA(2, 1, 1, domainSpecific());
	}
	
	@Test
	public void nokia_ExampleA_V2_1_2() {
		executeNokiaExampleA(2, 1, 2, domainSpecific());
	}
	
	@Test
	public void nokia_ExampleA_V2_2_1() {
		executeNokiaExampleA(2, 2, 1, domainSpecific());
	}
	
	@Test
	public void nokia_ExampleA_V2_2_2() {
		executeNokiaExampleA(2, 2, 2, domainSpecific());
	}
	
	@Test
	public void nokia_ExampleA_V3_1_1() {
		executeNokiaExampleA(3, 1, 1, domainSpecific());
	}
	
	@Test
	public void nokia_ExampleA_V3_1_2() {
		executeNokiaExampleA(3, 1, 2, domainSpecific());
	}
	
	@Test
	public void nokia_ExampleA_V3_2_1() {
		executeNokiaExampleA(3, 2, 1, domainSpecific());
	}
	
	@Test
	public void nokia_ExampleA_V3_2_2() {
		executeNokiaExampleA(3, 2, 2, domainSpecific());
	}
	
	@Test
	public void nokia_ExampleA_V4_1_1() {
		executeNokiaExampleA(4, 1, 1, domainSpecific());
	}
	
	@Test
	public void nokia_ExampleA_V4_1_2() {
		executeNokiaExampleA(4, 1, 2, domainSpecific());
	}
	
	@Test
	public void nokia_ExampleA_V4_2_1() {
		executeNokiaExampleA(4, 2, 1, domainSpecific());
	}
	
	@Test
	public void nokia_ExampleA_V4_2_2() {
		executeNokiaExampleA(4, 2, 2, domainSpecific());
	}
	
	@Test
	public void ibm_2557_1_3() {
		executeIBM2557(1, 3, domainSpecific());
	}

	@Test
	public void ibm_2557_1_2() {
		executeIBM2557(1, 2, domainSpecific());
	}
	
	@Test
	public void ibm_2557_1_1() {
		executeIBM2557(1, 1, domainSpecific());
	}
	
	@Test
	public void ibm_2557_2_3() {
		executeIBM2557(2, 3, domainSpecific());
	}
	
	@Test
	public void ibm_2557_2_2() {
		executeIBM2557(2, 2, domainSpecific());
	}
	
	@Test
	public void ibm_2557_2_1() {
		executeIBM2557(2, 1, domainSpecific());
	}

	protected abstract boolean domainSpecific();
	
	public EObject executeFumlTestmodel(int modelNumber, boolean domainSpecific) {
		String modelPath = getFumlTestmodelModelPath();
		String parameterDefinitionPath = deriveFumlTestmodelParameterDefinitionPath(modelNumber);
		String tracemodelPath = deriveFumlTestmodelTracemodelPath(modelNumber,
				domainSpecific);
		EObject trace = executeFumlModel(modelPath, parameterDefinitionPath,
				tracemodelPath, domainSpecific);
		return trace;
	}

	public EObject executeAnonExampleB(int version, boolean exists,
			boolean found, boolean domainSpecific) {
		return executeAnonExampleB(version, exists, found, false, domainSpecific);
	}
	
	public EObject executeAnonExampleB(int version, boolean exists,
			boolean found, boolean acc, boolean domainSpecific) {
		String modelPath = deriveAnonExampleModelPath(version);
		String parameterDefinitionPath = deriveAnonExampleParameterDefinitionPath(
				version, exists, found, acc);
		String tracemodelPath = deriveAnonExampleTracemodelPath(version,
				exists, found, acc, domainSpecific);
		EObject trace = executeFumlModel(modelPath, parameterDefinitionPath,
				tracemodelPath, domainSpecific);
		return trace;
	}
	
	private EObject executeNokiaExampleA(int version, int f, int d,
			boolean domainSpecific) {
		String modelPath = deriveNokiaExampleAModelPath(version);
		String parameterDefinitionPath = deriveNokiaExampleAParameterDefinitionPath(
				version, f, d);
		String tracemodelPath = deriveNokiaExampleATracemodelPath(version, f,
				d, domainSpecific);
		EObject trace = executeFumlModel(modelPath, parameterDefinitionPath,
				tracemodelPath, domainSpecific);
		return trace;
	}
	
	private EObject executeIBM2557(int version, int var2558, boolean domainSpecific) {
		String modelPath = deriveIBM2557ModelPath(version);
		String parameterDefinitionPath = deriveIBM2557ParameterDefinitionPath(
				version, var2558);
		String tracemodelPath = deriveIBM2557TracemodelPath(version, var2558, domainSpecific);
		EObject trace = executeFumlModel(modelPath, parameterDefinitionPath,
				tracemodelPath, domainSpecific);
		return trace;
		
	}

	private EObject executeFumlModel(String modelPath,
			String parameterDefinitionPath, String tracemodelPath,
			boolean domainSpecific) {
		if(domainSpecific)
			report.setDomainSpecific();
		ModelExecutor executor = new ModelExecutor();
		EObject trace = executor.execute(modelPath, parameterDefinitionPath,
				FUML_METMODEL_PATH, FUML_CONFIGURATION_PATH,
				getFumlTracemetamodelPath(domainSpecific), tracemodelPath,
				getFumlAdditionalModelInputPaths());
		addReportEntry(trace, tracemodelPath);
		return trace;
	}

	private void addReportEntry(EObject trace, String tracemodelPath) {
		int statenumber = -1;
		if (trace instanceof StateSystem) {
			StateSystem stateSystem = (StateSystem) trace;
			statenumber = stateSystem.getStates().size();
		} else {
			EStructuralFeature globalTraceFeature = trace.eClass().getEStructuralFeature("globalTrace");
			EList<?> globalStates = (EList<?>)trace.eGet(globalTraceFeature);
			statenumber = globalStates.size();
		}
		TraceReportEntry reportEntry = new TraceReportEntry(tracemodelPath, statenumber);
		report.addReportEntry(reportEntry);
	}

}
