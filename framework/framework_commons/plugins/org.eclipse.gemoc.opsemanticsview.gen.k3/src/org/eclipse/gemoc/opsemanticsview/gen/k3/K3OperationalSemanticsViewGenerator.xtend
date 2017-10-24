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

import java.util.Set
import opsemanticsview.OpsemanticsviewFactory
import org.eclipse.core.resources.IProject
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl
import org.eclipse.gemoc.dsl.Dsl
import org.eclipse.gemoc.dsl.SimpleValue
import org.eclipse.gemoc.opsemanticsview.gen.OperationalSemanticsViewGenerator
import org.eclipse.jdt.core.IJavaProject
import org.eclipse.jdt.core.IType
import org.eclipse.jdt.core.JavaCore

class K3OperationalSemanticsViewGenerator implements OperationalSemanticsViewGenerator {

	override generate(Dsl language, IProject melangeProject) {
		val aspectClasses = findAspects(language, melangeProject)
		
		val result = OpsemanticsviewFactory.eINSTANCE.createOperationalSemanticsView

		val selectedLanguage = language.name
		val ecoreUri = 
			language
			.abstractSyntax
			.values
			.filter[v | v instanceof SimpleValue]
			.map[v| v as SimpleValue]
			.filter[v | v.name == "ecore"]
			.head
			.values
			.head
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

	private static def Set<IType> findAspects(Dsl language, IProject melangeProject) {
		val SimpleValue semantic = language.getSemantic()
				.getValues()
				.filter[v | v instanceof SimpleValue]
				.map[v | v as SimpleValue]
				.filter[v | v.getName() == "k3"]
				.head
		if(semantic !== null) {
			val aspectNames = semantic.values
			val IJavaProject javaProject = JavaCore.create(melangeProject);
			val aspectClasses = aspectNames.map[it|javaProject.findType(it)].toSet
			return aspectClasses
		}
		return newHashSet()
	}

	override canHandle(Dsl language, IProject melangeProject) {
		val SimpleValue semantic = language.getSemantic()
			.getValues()
			.filter[v | v instanceof SimpleValue]
			.map[v | v as SimpleValue]
			.filter[v | v.getName() == "k3"]
			.head
		return semantic !== null && !semantic.values.isEmpty
	}

}
