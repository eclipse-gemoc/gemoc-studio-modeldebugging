/*******************************************************************************
 * Copyright (c) 2016, 2017 Inria and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Inria - initial API and implementation
 *******************************************************************************/
package org.eclipse.gemoc.trace.commons

import org.eclipse.emf.ecore.EModelElement
import org.eclipse.emf.ecore.EcoreFactory
import org.eclipse.emf.ecore.EAnnotation

abstract class ExecutionMetamodelTraceability {
	
	public static val String TRACEABILITY_ANNOTATION_SOURCE = "http://www.modelexecution.org/trace/XMOF";
	public static val String TRACEABILITY_ANNOTATION_KEY = "executionMetamodelElement";
	
	public static def void createTraceabilityAnnotation(EModelElement elementToAnnotate, String traceabilityAnnotationValue) {
		val traceabilityAnnotation = EcoreFactory.eINSTANCE.createEAnnotation;
		traceabilityAnnotation.source = TRACEABILITY_ANNOTATION_SOURCE;
		traceabilityAnnotation.details.put(TRACEABILITY_ANNOTATION_KEY, traceabilityAnnotationValue);
		elementToAnnotate.EAnnotations.add(traceabilityAnnotation);
	}
	
	public static def String getTraceabilityAnnotationValue(EModelElement annotatedElement) {
		var String traceabilityAnnotationValue = null;
		val traceabilityAnnotation = getTraceabilityAnnotation(annotatedElement);
		if(traceabilityAnnotation != null) {
			traceabilityAnnotationValue = traceabilityAnnotation.details.get(TRACEABILITY_ANNOTATION_KEY);
		}		
		return traceabilityAnnotationValue;
	} 
	
	public static def EAnnotation getTraceabilityAnnotation(EModelElement annotatedElement) {
		return annotatedElement.getEAnnotation(TRACEABILITY_ANNOTATION_SOURCE);
	}
}