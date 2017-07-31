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
package org.eclipse.gemoc.trace.commons.tracemetamodel

import org.eclipse.emf.ecore.EClass
import org.eclipse.gemoc.trace.commons.EcoreCraftingUtil
import org.eclipse.emf.ecore.EOperation

class StepStrings {

	public static val String implicitStepSuffix = "_ImplicitStep";

	public static val String abstractSubStepSuffix = "_AbstractSubStep";

	public static val String globalImplicitStepName = "ImplicitStep";

	public static val String package_BigSteps = "BigSteps";

	public static val String ref_BigStepToSub = "subSteps";

	public static def String implicitStepClassName(EClass macroStepClass) {
		return macroStepClass.getName() + implicitStepSuffix;
	}

	public static def String stepClassName(EClass containingClass, EOperation rule) {
		val String prefix =
		if (containingClass != null) {
			EcoreCraftingUtil.getFQN(containingClass, "_").toFirstUpper + "_"	
		} else {
			"Root_"
		}
		return prefix + rule.name.toFirstUpper
	}

	public static def String subStepClassName(EClass containingClass, EOperation rule) {
		return stepClassName(containingClass,rule)
	}

	public static def String abstractSubStepClassName(EClass containingClass, EOperation rule) {
		return stepClassName(containingClass, rule) + abstractSubStepSuffix
	}

	public static def String implicitStepClassName(EClass containingClass, EOperation rule) {
		return stepClassName(containingClass, rule) + implicitStepSuffix
	}

}
