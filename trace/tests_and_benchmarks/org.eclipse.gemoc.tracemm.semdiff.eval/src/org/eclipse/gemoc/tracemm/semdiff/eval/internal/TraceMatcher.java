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
package org.eclipse.gemoc.tracemm.semdiff.eval.internal;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.epsilon.ecl.EclModule;
import org.eclipse.epsilon.ecl.MatchRule;
import org.eclipse.epsilon.ecl.trace.Match;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.uml2.uml.UMLPackage;
import org.modelexecution.fumldebug.core.trace.tracemodel.TracemodelPackage;
import org.modelexecution.xmof.diff.util.EpsilonUtil;
import org.modelexecution.xmof.states.states.StatesPackage;
import org.modelexecution.xmof.vm.util.EMFUtil;

import org.eclipse.gemoc.tracemm.semdiff.eval.internal.TraceMatchingEvent.EventType;

public class TraceMatcher {

	private static final String LEFT_MODEL_NAME = "Left";
	private static final String RIGHT_MODEL_NAME = "Right";

	private ResourceSet resourceSet;
	
	private Resource metamodelResource;
	private Resource configurationResource;
	private Resource tracemetamodelResource;
	private Resource leftModelResource;
	private Resource rightModelResource;
	
	private File matchRules;
	
	private boolean eolRuntimeException = false;
	
	private LinkedHashSet<TraceMatchingListener> listeners = new LinkedHashSet<TraceMatchingListener>();
	
	public TraceMatcher() {
		setupResourceSet();
	}
	
	private void setupResourceSet() {
		resourceSet = EMFUtil.createResourceSet();
		EMFUtil.registerXMIFactory(resourceSet);
		EMFUtil.registerEcoreFactory(resourceSet);
	}
	
	public boolean match(String leftModelPath, String rightModelPath, String metamodelPath, String configurationPath, String tracemetamodelPath, String matchRulesPath) {
		loadResources(leftModelPath, rightModelPath, metamodelPath,
				configurationPath, tracemetamodelPath, matchRulesPath);
		EclModule eclModule = createEclModuleForTraceMatching();
		EpsilonUtil.initEclModule(eclModule);

		Object left = leftModelResource.getContents().get(0);
		Object right = rightModelResource.getContents().get(0);
		MatchRule semanticMatchRule = EpsilonUtil.getSemanticMatchRule(eclModule, left, right);
		
		Match match = null;
		try {
			notifyMatchingStart();
			long start = getTime();
			match = EpsilonUtil.matchRule(eclModule, semanticMatchRule, left, right);
			long end = getTime();
 			System.out.println(";" + (end-start));
			notifyMatchingEnd();
		} catch(EolRuntimeException e) {
			eolRuntimeException = true;
		}
		unloadResources();
		//return match != null? match.isMatching() : false;
		return match.isMatching();
	}
	
	private void notifyMatchingStart() {
		notify(EventType.MATCHING_START);
	}
	
	private void notifyMatchingEnd() {
		notify(EventType.MATCHING_END);
	}

	private void notify(EventType eventType) {
		TraceMatchingEvent event = new TraceMatchingEvent(getTime(), eventType);
		for (TraceMatchingListener listener : listeners) {
			listener.notify(event);
		}
	}
	
	public void registerListener(TraceMatchingListener listener) {
		this.listeners.add(listener);
	}
	
	private long getTime() {
		Calendar c = Calendar.getInstance();
		return c.getTimeInMillis();
	}
	
	public boolean matchedWithoutErrors() {
		return !eolRuntimeException;
	}

	private void loadResources(String leftModelPath, String rightModelPath,
			String metamodelPath, String configurationPath,
			String tracemetamodelPath, String matchRulesPath) {
		if (metamodelResource != null) {
			metamodelResource = Util.loadResource(resourceSet, metamodelPath);
		}
		configurationResource = Util.loadResource(resourceSet,
				configurationPath);
		leftModelResource = Util.loadResource(resourceSet, leftModelPath);
		rightModelResource = Util.loadResource(resourceSet, rightModelPath);
		tracemetamodelResource = tracemetamodelPath != null ? Util
				.loadResource(resourceSet, tracemetamodelPath) : null;
		matchRules = EMFUtil.createFile(matchRulesPath);
	}
	
	private void unloadResources() {
		for(Resource resource : resourceSet.getResources()) {
			try {
				resource.unload();
			} catch(Exception e) {
			}
		}
		resourceSet = null;
	}
	
	private EclModule createEclModuleForTraceMatching() {
		EPackage traceEPackage = tracemetamodelResource == null ? TracemodelPackage.eINSTANCE
				: (EPackage) tracemetamodelResource.getContents().get(0);
		EPackage statesEPackage = StatesPackage.eINSTANCE;
		EPackage umlEPackage = UMLPackage.eINSTANCE;

		Collection<EPackage> ePackages = new HashSet<EPackage>();
		if (metamodelResource != null)
			ePackages.add(EMFUtil.getRootEPackage(metamodelResource));
		ePackages.add(traceEPackage);
		ePackages.add(statesEPackage);
		ePackages.add(umlEPackage);
		ePackages.addAll(EMFUtil.getEPackages(configurationResource));

		EclModule moduleSemantics = EpsilonUtil.createEclModule(matchRules,
				leftModelResource, LEFT_MODEL_NAME, rightModelResource,
				RIGHT_MODEL_NAME, ePackages);

		EpsilonUtil.setNativeTypeDelegateToModule(moduleSemantics, this
				.getClass().getClassLoader());

		moduleSemantics.getContext().setWarningStream(
				new PrintStream(new OutputStream() {
					@Override
					public void write(int b) throws IOException {
					}
				}));

		return moduleSemantics;
	}
}
