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
package org.eclipse.gemoc.xdsmlframework.commons

import org.eclipse.emf.ecore.EModelElement
import org.eclipse.emf.ecore.EStructuralFeature
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EAnnotation
import org.eclipse.emf.ecore.EcoreFactory

class DynamicAnnotationHelper {
	public static val DYNAMIC_ANNOTATION_URI = "aspect"

	private static def boolean isDynamic(EModelElement o) {
		return o.EAnnotations.exists[a|a.source.equals(DYNAMIC_ANNOTATION_URI)]
	}

	public static def boolean isDynamic(EClass c) {
		return isDynamic(c as EModelElement)
	}

	public static def boolean isDynamic(EStructuralFeature p) {
		return isDynamic(p as EModelElement) || isDynamic(p.EContainingClass)
	}

	public static def EAnnotation createDynamicAnnotation() {
		val EAnnotation dynamicAnnotation = EcoreFactory.eINSTANCE.createEAnnotation();
		dynamicAnnotation.setSource(DYNAMIC_ANNOTATION_URI);
		return dynamicAnnotation;
	}
}
