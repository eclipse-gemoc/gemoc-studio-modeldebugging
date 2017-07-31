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
import fr.inria.diverse.melange.metamodel.melange.Language
import java.util.Set
import org.eclipse.jdt.core.IType
import org.eclipse.jdt.core.IJavaProject
import org.eclipse.jdt.core.JavaCore
import org.eclipse.core.resources.IProject
import opsemanticsview.OpsemanticsviewFactory
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.EPackage
import fr.inria.diverse.melange.metamodel.melange.Inheritance
import fr.inria.diverse.melange.metamodel.melange.LanguageOperator


class K3OperationalSemanticsViewGenerator implements OperationalSemanticsViewGenerator {

	override generate(Language language, IProject melangeProject) {
		val aspectClasses = findAspects(language, melangeProject)
		
		val result = OpsemanticsviewFactory.eINSTANCE.createOperationalSemanticsView

		val selectedLanguage = language.name
		val rs = new ResourceSetImpl
		val executionMetamodelResource = rs.getResource(URI.createURI(language.syntax.ecoreUri), true)
		val executionMetamodel = executionMetamodelResource.contents.filter(EPackage).head
		
		
		val LanguageOperator inheritance = language.operators.filter(Inheritance).head
		val abstractSyntax = if (inheritance != null) {
			val asURI = URI.createURI(inheritance.getTargetLanguage().syntax.ecoreUri)
			val asResource = rs.getResource(asURI,true)
			asResource.contents.filter(EPackage).head
		} else {
			null
		}
		
		val K3DynamicElementsFinder dynFinder = new K3DynamicElementsFinder(abstractSyntax, executionMetamodel, result);
		dynFinder.find();


		val K3StepExtractor eventsgen = new K3StepExtractor(aspectClasses, selectedLanguage, executionMetamodel, result);
		eventsgen.generate();

		result.abstractSyntax = abstractSyntax
		result.executionMetamodel = executionMetamodel

		return result
	}

	private static def Set<IType> findAspects(Language language, IProject melangeProject) {
		val aspectNames = language.semantics.map[aspectTypeRef.type.qualifiedName].toList
		val IJavaProject javaProject = JavaCore.create(melangeProject);
		val aspectClasses = aspectNames.map[it|javaProject.findType(it)].toSet
		return aspectClasses

	}

	override canHandle(Language language, IProject melangeProject) {
		!language.semantics.empty
	}

}
