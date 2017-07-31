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
package org.eclipse.gemoc.trace.gemoc.generator.util

import org.eclipse.gemoc.trace.commons.EcoreCraftingUtil
import java.util.HashSet
import java.util.Set
import opsemanticsview.OperationalSemanticsView
import opsemanticsview.Rule
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EReference
import org.eclipse.emf.ecore.EStructuralFeature
import org.eclipse.xtend.lib.annotations.Accessors

/**
 * Given a set of references to classes and properties from the execution metamodel,
 * will filter out all other elements from the execution extension transient model.
 * 
 * For now it is implemented through fully qualified name comparisons.
 */
class ExtensionFilter {

	// Input
	val Set<EClass> chosenClasses
	val Set<? extends EStructuralFeature> chosenProperties

	// Input / Output
	val OperationalSemanticsView executionExtension

	// Output
	@Accessors(#[PUBLIC_GETTER, PRIVATE_SETTER])
	var boolean didFilterSomething = false

	// Transient
	val Set<EClass> retainedClasses = new HashSet
	val Set<EStructuralFeature> retainedProperties = new HashSet
	val Set<Rule> retainedRules = new HashSet

	new(OperationalSemanticsView executionExtension, Set<EClass> chosenClasses,
		Set<? extends EStructuralFeature> chosenProperties) {
		if (chosenClasses != null)
			this.chosenClasses = chosenClasses
		else
			this.chosenClasses = #{}
		if (chosenProperties != null)
			this.chosenProperties = chosenProperties
		else
			this.chosenProperties = #{}
		this.executionExtension = executionExtension
	}

	public def void execute() {

		if (!chosenClasses.empty || !chosenProperties.empty) {

			val Set<String> chosenClassesFQNs = chosenClasses.map[c|EcoreCraftingUtil.getFQN(c, ".")].toSet
			val Set<String> chosenPropertiesFQNs = chosenProperties.map [ p |
				EcoreCraftingUtil.getFQN(p.getEContainingClass, ".") + "." + p.name
			].toSet

			for (element : executionExtension.dynamicClasses) {
				val fqn = EcoreCraftingUtil.getFQN(element, ".")
				if (chosenClassesFQNs.contains(fqn)) {
					retainedClasses.add(element)
					retainedClasses.addAll((element))
					retainedProperties.addAll(element.getEStructuralFeatures)
				}
			}

			for (element : executionExtension.dynamicProperties) {
				val fqn = EcoreCraftingUtil.getFQN(element.getEContainingClass, ".") + "." + element.name
				if (chosenPropertiesFQNs.contains(fqn)) {
					retainedProperties.add(element)
					retainedClasses.add(element.getEContainingClass)
					retainedClasses.addAll((element.getEContainingClass))
					if (element instanceof EReference) {
						retainedClasses.add(element.getEReferenceType)
						retainedClasses.addAll((element.getEReferenceType))
					}
				}
			}

			for (element : executionExtension.rules) {
				if (element.stepRule) {
					retainedRules.add(element)
					for (paramClass : element.operation.getEParameters.map[p|p.getEType].filter(EClass)) {
						retainedClasses.add(paramClass)
					}
					if (element.operation.getEType instanceof EClass) {
						retainedClasses.add(element.operation.getEType as EClass)
					}
					retainedClasses.add(element.containingClass)
				}
			}

			// Hack: we replace indirect supertype relationships by direct ones
			for (c1 : retainedClasses) {
				for (c2 : retainedClasses.filter[c|c != c1]) {
					if (c1.getEAllSuperTypes.contains(c2)) {
						if (!hasSuperTypePathContainedIn(c1, c2, retainedClasses)) {
							c1.getESuperTypes.add(c2)
						}
					}
				}
			}

			// Hack: likewise, we replace indirect rule calls by direct ones
			for (r1 : retainedRules) {
				for (r2 : retainedRules.filter[r|r != r1]) {
					if (callsIndirectly(r1, r2, new HashSet)) {
						r1.calledRules.add(r2)
					}
				}
			}

//			// Second hack: if some leaf class is abstract, we make it concrete
//			for (c : retainedClasses.filter[abstract]) {
//				val subClasses = retainedClasses.filter[c2|c2.ESuperTypes.contains(c)]
//				if (subClasses.empty)
//					c.abstract = false
//			}
//
//			// Remove super types that are not retained
//			for (c : retainedClasses) {
//				c.ESuperTypes.removeIf([c2|!retainedClasses.contains(c2)])
//			}

			// Remove refs to other rules that are not retained
			for (r : retainedRules) {
				r.calledRules.removeIf([r2|!retainedRules.contains(r)])
				r.overridenBy.removeIf([r2|!retainedRules.contains(r)])
				if (!retainedRules.contains(r.overrides))
					r.overrides = null
			}

			for (element : executionExtension.eAllContents.toSet) {
				tryRemove(element)
			}

		}
	}

	def boolean callsIndirectly(Rule origin, Rule destination, Set<Rule> visited) {
		if (!visited.contains(origin)) {
			if (origin == destination) {
				return true
			}

			for (r : origin.calledRules) {
				val boolean result = callsIndirectly(r, destination, visited)
				if (result)
					return true
			}

		}
		return false
	}

	static class CallPath {
		public boolean isContainedInSet = false
	}

	def static Set<CallPath> findCallPaths(Rule origin, Rule destination, Set<Rule> mustBeContainedIn,
		Set<Rule> visited, boolean containedSoFar) {
		val result = new HashSet<CallPath>
		if (!visited.contains(origin)) {
			visited.add(origin)

			if (origin == destination) {
				val path = new CallPath
				path.isContainedInSet = containedSoFar
				result.add(path)
			}

			for (s : origin.calledRules) {
				val containedSoFarNext = containedSoFar && mustBeContainedIn.contains(s)
				val interResult = ExtensionFilter.findCallPaths(s, destination,
					mustBeContainedIn, visited, containedSoFarNext)
				result.addAll(interResult)
			}
		}
		return result
	}

	private static def boolean hasSuperTypePathContainedIn(EClass origin, EClass destination, Set<EClass> containedIn) {

		if (origin == destination)
			return true

		for (s : origin.getESuperTypes.filter[s|containedIn.contains(s)]) {
			val result = hasSuperTypePathContainedIn(s, destination, containedIn)
			if (result)
				return true
		}

		return false
	}

	private def Set<EObject> tryRemove(EObject element) {
//		val removedElements = new HashSet<EObject>
//		if (element instanceof EClass) {
//			if (!retainedClasses.contains(element)) {
//				didFilterSomething = true
//				val package = element.EPackage
//				package.EClassifiers.remove(element)
//				removedElements.add(element)
//				val removedAgain = cleanEPackage(package)
//				removedElements.addAll(removedAgain)
//			}
//		} else if (element instanceof EStructuralFeature) {
//			if (!retainedProperties.contains(element)) {
//				didFilterSomething = true
//				val container = element.eContainer
//				if (container instanceof ClassExtension) {
//					container.newProperties.remove(element)
//					removedElements.add(element)
//					if (container.newProperties.empty) {
//						executionExtension.classesExtensions.remove(container)
//						removedElements.add(container)
//					}
//				} else if (container instanceof EClass) {
//					container.EStructuralFeatures.remove(element)
//					removedElements.add(element)
//				}
//			}
//		} else if (element instanceof Rule) {
//			if (!retainedRules.contains(element)) {
//				executionExtension.rules.remove(element)
//				removedElements.add(element)
//				for (r : executionExtension.rules) {
//					r.calledRules.remove(element)
//					if (r.overrides == element)
//						r.overrides = null
//					r.overridenBy.remove(element)
//				}
//			}
//		}
//		return removedElements
	}

//	private def Set<EObject> cleanEPackage(EPackage p) {
//		val removedElements = new HashSet<EObject>
//		if (p != null) {
//			if (p.EClassifiers.empty && p.ESubpackages.empty) {
//				val container = p.eContainer
//				if (container != null) {
//					if (container instanceof EPackage) {
//						container.ESubpackages.remove(p)
//						removedElements.add(p)
//						val removedAgain = cleanEPackage(container)
//						removedElements.addAll(removedAgain)
//					} else if (container instanceof Ecorext) {
//						container.newPackages.remove(p)
//						removedElements.add(p)
//					}
//				}
//			}
//		}
//		return removedElements
//	}

}
