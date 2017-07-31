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
 package org.eclipse.gemoc.opsemanticsview.gen.k3

import opsemanticsview.OperationalSemanticsView
import opsemanticsview.OpsemanticsviewFactory
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EPackage
import org.eclipse.xtend.lib.annotations.Accessors
import org.eclipse.gemoc.xdsmlframework.commons.DynamicAnnotationHelper

class K3DynamicElementsFinder {

	// Input
	private val EPackage abstractSyntax
	private val EPackage executionMetamodel

	// In-out
	@Accessors(PUBLIC_GETTER, PROTECTED_SETTER)
	private var OperationalSemanticsView view

	new(EPackage abstractSyntax, EPackage executionMetamodel, OperationalSemanticsView view) {
		this.abstractSyntax = abstractSyntax
		this.executionMetamodel = executionMetamodel
		this.view = view
	}

	def void find() {
		findDynamicParts
		if (abstractSyntax != null)
			findMappingAsToExe
	}

	private def void findDynamicParts() {

		for (c : executionMetamodel.eAllContents.filter(EClass).toSet) {

			// Either partially mutable or not mutable at all
			if (!DynamicAnnotationHelper.isDynamic(c)) {

				val mutableProperties = c.EStructuralFeatures.filter[p|DynamicAnnotationHelper.isDynamic(p)]
				if (mutableProperties != null && !mutableProperties.empty) {
					for (p : mutableProperties) {
						view.dynamicProperties.add(p)
					}
				}
			} // Or completely mutable
			else {
				view.dynamicClasses.add(c)
				for (p : c.EStructuralFeatures) {
					view.dynamicProperties.add(p)
				}

			}
		}

	}

	private def void findMappingAsToExe() {
		explorePackageRecursively(abstractSyntax, executionMetamodel)
	}

	private def void explorePackageRecursively(EPackage asPackage, EPackage exePackage) {
		for (asClass : asPackage.EClassifiers.filter(EClass)) {
			val exeClass = exePackage.EClassifiers.filter(EClass).findFirst[exeC|asClass.name.equals(exeC.name)]
			val mappingEntry = OpsemanticsviewFactory.eINSTANCE.createExecutionToASEntry => [
				ASclass = asClass
				executionClass = exeClass
			]
			view.executionToASmapping.add(mappingEntry)
		}

		for (asSubPackage : asPackage.ESubpackages) {
			val exeSubPackage = asPackage.ESubpackages.findFirst[p|p.name.equals(asSubPackage.name)]
			explorePackageRecursively(asSubPackage, exeSubPackage)
		}
	}

}
