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

import org.eclipse.gemoc.opsemanticsview.gen.OperationalSemanticsViewGenerator
import java.util.Set
import org.eclipse.jdt.core.IType
import org.eclipse.jdt.core.IJavaProject
import org.eclipse.jdt.core.JavaCore
import org.eclipse.core.resources.IProject
import opsemanticsview.OpsemanticsviewFactory
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.EPackage
import java.util.Properties

class K3OperationalSemanticsViewGenerator implements OperationalSemanticsViewGenerator {

	override generate(Properties language, IProject melangeProject) {
		val aspectClasses = findAspects(language, melangeProject)
		
		val result = OpsemanticsviewFactory.eINSTANCE.createOperationalSemanticsView

		val selectedLanguage = language.get("name") as String
		val ecoreUri = (language.get("syntax") as String).split(",").head
		val rs = new ResourceSetImpl
		val executionMetamodelResource = rs.getResource(URI.createURI(ecoreUri), true)
		val executionMetamodel = executionMetamodelResource.contents.filter(EPackage).head
		
		
//		val LanguageOperator inheritance = language.operators.filter(Inheritance).head
//		val abstractSyntax = if (inheritance != null) {
//			val asURI = URI.createURI(inheritance.getTargetLanguage().syntax.ecoreUri)
//			val asResource = rs.getResource(asURI,true)
//			asResource.contents.filter(EPackage).head
//		} else {
//			null
//		}
		val abstractSyntax = null //FIXME: check where it is used
		
		val K3DynamicElementsFinder dynFinder = new K3DynamicElementsFinder(abstractSyntax, executionMetamodel, result);
		dynFinder.find();


		val K3StepExtractor eventsgen = new K3StepExtractor(aspectClasses, selectedLanguage, executionMetamodel, result);
		eventsgen.generate();

		result.abstractSyntax = abstractSyntax
		result.executionMetamodel = executionMetamodel

		return result
	}

	private static def Set<IType> findAspects(Properties language, IProject melangeProject) {
		val aspectNames = (language.get("behavior") as String).split(",")
		val IJavaProject javaProject = JavaCore.create(melangeProject);
		val aspectClasses = aspectNames.map[it|javaProject.findType(it)].toSet
		return aspectClasses
	}

	override canHandle(Properties language, IProject melangeProject) {
		!(language.get("behavior") as String).empty
	}

}
