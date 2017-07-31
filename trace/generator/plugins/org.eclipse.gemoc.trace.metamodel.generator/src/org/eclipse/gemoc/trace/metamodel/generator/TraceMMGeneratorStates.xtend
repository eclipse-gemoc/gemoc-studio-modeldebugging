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
package org.eclipse.gemoc.trace.metamodel.generator

import org.eclipse.gemoc.trace.commons.EcoreCraftingUtil
import org.eclipse.gemoc.trace.commons.ExecutionMetamodelTraceability
import java.util.ArrayList
import java.util.HashMap
import java.util.HashSet
import java.util.Map
import java.util.Set
import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.EReference
import org.eclipse.emf.ecore.EStructuralFeature
import org.eclipse.emf.ecore.EcoreFactory
import org.eclipse.emf.ecore.util.EcoreUtil

import static org.eclipse.gemoc.trace.commons.EcoreCraftingUtil.*
import opsemanticsview.OperationalSemanticsView

class TraceMMGeneratorStates {

	// Inputs
	private val OperationalSemanticsView mmext
	private val TraceMMExplorer traceMMExplorer
	private val String languageName
	private val boolean gemoc

	// Input/Output (already accessible because created before)
	private val EPackage tracemmresult
	private val TraceMMGenerationTraceability traceability

	// Transient stuff 
	private val Map<EClass, EClass> runtimeToTraced = new HashMap
	private val Set<EClass> allRuntimeClasses = new HashSet<EClass>
	private val Set<EClass> allStaticClasses = new HashSet<EClass>
	private val Set<EClass> allNewEClasses
	private val Set<EClass> multipleOrig = new HashSet

	new(OperationalSemanticsView mmext,TraceMMGenerationTraceability traceability,
		TraceMMExplorer traceMMExplorer, String languageName, EPackage tracemmresult, boolean gemoc) {
		this.mmext = mmext
		this.allNewEClasses = mmext.eAllContents.toSet.filter(EClass).toSet
		this.traceability = traceability
		this.traceMMExplorer = traceMMExplorer
		this.languageName = languageName
		this.tracemmresult = tracemmresult
		this.gemoc = gemoc
	}

	private def void cleanup() {
		val allCreatedEClasses = this.tracemmresult.eAllContents.filter(EClass).toSet

		for (c : allCreatedEClasses) {
			cleanupAnnotations(c);
		}

		// TODO is this this necessary?
		for (r : runtimeToTraced.values.filter(EReference)) {
			r.EOpposite = null
		}
	}

	public def void process() {
		handleTraceClasses()
		cleanup()
	}

	private def void cleanupAnnotations(EClass eClass) {
		val traceabilityAnnotation = ExecutionMetamodelTraceability.getTraceabilityAnnotation(eClass);
		eClass.EAnnotations.clear
		if (traceabilityAnnotation != null) {
			eClass.EAnnotations.add(traceabilityAnnotation);
		}
	}

	private def EPackage obtainTracedPackage(EPackage runtimePackage) {
		var EPackage result = traceMMExplorer.statesPackage

		if (runtimePackage != null) {
			val tracedSuperPackage = obtainTracedPackage(runtimePackage.ESuperPackage)
			val String tracedPackageName = TraceMMStrings.package_createTracedPackage(runtimePackage)
			result = tracedSuperPackage.ESubpackages.findFirst[p|p.name.equals(tracedPackageName)]
			if (result == null) {
				result = EcoreFactory.eINSTANCE.createEPackage
				result.name = tracedPackageName
				result.nsURI = languageName + "_" + result.name // TODO
				result.nsPrefix = "" // TODO
				tracedSuperPackage.ESubpackages.add(result)
			}
		}
		return result
	}

	private def String computeTraceabilityAnnotationValue(EClass extendedClass) {
		var String traceabilityAnnotationValue = null;
		val dynamicProperties = extendedClass.EStructuralFeatures.filter[f|mmext.dynamicProperties.contains(f)].toSet
		if (!dynamicProperties.empty) {
			val mutableProperty = dynamicProperties.get(0);
			val String mutablePropertyTraceabilityValue = ExecutionMetamodelTraceability.
				getTraceabilityAnnotationValue(mutableProperty)
			if (mutablePropertyTraceabilityValue != null) {
				val classSubstringStartIndex = mutablePropertyTraceabilityValue.lastIndexOf("/");
				traceabilityAnnotationValue = mutablePropertyTraceabilityValue.substring(0, classSubstringStartIndex);
			}
		}
		return traceabilityAnnotationValue;
	}

	private def boolean isInPackage(EPackage c, EPackage p) {
		if (c != null && p != null && c == p) {
			return true
		} else if (c.ESuperPackage != null) {
			return isInPackage(c.ESuperPackage, p)
		} else {
			return false
		}
	}

	private def Set<EClass> getSubTypesOf(EClass c) {
		val result = new HashSet<EClass>
		for (someEClass : mmext.executionMetamodel.eAllContents.toSet.filter(EClass)) {
			if (someEClass.ESuperTypes.contains(c)) {
				result.add(someEClass)
			}
		}
		return result
	}


	private def void getAllInheritance(Set<EClass> result, EClass c) {
		if (!result.contains(c)) {
			result.add(c)
			for (sup : c.ESuperTypes) {
				getAllInheritance(result, sup)
			}
			for (sub : getSubTypesOf(c)) {
				getAllInheritance(result, sub)
			}
		}
	}

	private def Set<EClass> getAllInheritance(EClass c) {
		val result = new HashSet<EClass>()
		getAllInheritance(result, c)
		return result
	}

	private def void handleTraceClasses() {

		// First we find ALL classes linked to runtime properties
		for (dp : mmext.dynamicProperties) {
			val extendedExistingClass = dp.EContainingClass
			allRuntimeClasses.add(extendedExistingClass)
			val allInheritance = getAllInheritance(extendedExistingClass)
			allRuntimeClasses.addAll(allInheritance)
		}

		val baseClassToNewEClass = new HashMap

		for (c : allNewEClasses) {
			baseClassToNewEClass.put(mmext.executionMetamodel.eAllContents.toSet.filter(EClass).findFirst[cls|cls.name == c.name], c)
		}

		for (c : allNewEClasses) {
			val allInheritance = getAllInheritance(mmext.executionMetamodel.eAllContents.toSet.filter(EClass).findFirst [cls|
				cls.name == c.name
			])
			allRuntimeClasses.addAll(allInheritance.map [ cls |
				val newEClass = baseClassToNewEClass.get(cls)
				if(newEClass == null) cls else newEClass
			])
		}

		// We also store the dual set of classes not linked to anything dynamic
		allStaticClasses.addAll(mmext.executionMetamodel.eAllContents.toSet.filter(EClass).filter[c|!allRuntimeClasses.contains(c)])

		// Here we find classes that inherit from multiple concrete classes
		// This allows later to handle multiple non-conflicting "originalObject" references in such cases
		for (rc : allRuntimeClasses) {
			val concreteSuperTypes = rc.EAllSuperTypes.filter[c|!c.abstract && allRuntimeClasses.contains(c)].toSet
			multipleOrig.addAll(concreteSuperTypes)
		}

		val tracedClasses = new ArrayList
		// We go through all dynamic classes and we create traced versions of them
		// we sort them by name to ensure reproducibility of the generated ecore file
		val runtimeClasses = allRuntimeClasses.toList
		val runtimeClassesSorted = runtimeClasses.sortBy[name]
		for (runtimeClass : runtimeClassesSorted) {
			val tracedClass = handleTraceClass(runtimeClass)
			tracedClasses.add(tracedClass)
		}
	}

	private def EClass handleTraceClass(EClass runtimeClass) {

		if (!allRuntimeClasses.contains(runtimeClass))
			return runtimeClass

		if (! runtimeToTraced.containsKey(runtimeClass)) {
			

			// Creating the traced version of the class
			val tracedClass = EcoreFactory.eINSTANCE.createEClass
			tracedClass.name = TraceMMStrings.class_createTraceClassName(runtimeClass)
			tracedClass.abstract = runtimeClass.abstract || runtimeClass.interface
			runtimeToTraced.put(runtimeClass, tracedClass)

			// Storing traceability stuff
			traceability.putTracedClasses(runtimeClass, tracedClass)

			// "Copying" super types
			for (superType : runtimeClass.ESuperTypes.filter[t|allRuntimeClasses.contains(t)]) {
				val tracedSuperType = handleTraceClass(superType)
				tracedClass.ESuperTypes.add(tracedSuperType)
			}

			val boolean notNewClass = !allNewEClasses.contains(runtimeClass)
			val boolean notAbstract = !tracedClass.abstract

			// Adding the SpecificTracedObject super type
			if (tracedClass.ESuperTypes.empty) {
				val tracedObjectGenericSuperType = EcoreFactory.eINSTANCE.createEGenericType
				tracedObjectGenericSuperType.EClassifier = traceMMExplorer.specificTracedObjectClass
				val dimensionClassTracedObjectTypeBinding = EcoreFactory.eINSTANCE.createEGenericType
				tracedObjectGenericSuperType.ETypeArguments.add(dimensionClassTracedObjectTypeBinding)
				// And binds its type parameters to the runtime and specific dimension classes
				dimensionClassTracedObjectTypeBinding.EClassifier = traceMMExplorer.specificDimensionClass
				dimensionClassTracedObjectTypeBinding.ETypeArguments.add(EcoreFactory.eINSTANCE.createEGenericType)
				tracedClass.EGenericSuperTypes.add(tracedObjectGenericSuperType)
			}

			// We recreate the same package organization
			val tracedPackage = obtainTracedPackage(runtimeClass.EPackage)
			tracedPackage.EClassifiers.add(tracedClass)

			// If this is a class extension, then we add a reference, to be able to refer to the element of the original model (if originally static element of the model)
			val dynamicProperties = runtimeClass.EStructuralFeatures.filter[f|mmext.dynamicProperties.contains(f)]
			if (notNewClass && !dynamicProperties.empty) {
				val traceabilityAnnotationValue = computeTraceabilityAnnotationValue(runtimeClass);
				if (traceabilityAnnotationValue != null)
					ExecutionMetamodelTraceability.createTraceabilityAnnotation(tracedClass,
						traceabilityAnnotationValue);
			}

			// Also we must check that there isn't already a concrete class in the super classes, which would have its own origObj ref
			val boolean onlyAbstractSuperTypes = runtimeClass.EAllSuperTypes.forall [ c |
				!allRuntimeClasses.contains(c) || c.abstract
			]
			if (notNewClass && notAbstract && onlyAbstractSuperTypes) {
				val refName = if (multipleOrig.contains(runtimeClass)) {
						TraceMMStrings.ref_OriginalObject_MultipleInheritance(runtimeClass)
					} else {
						TraceMMStrings.ref_OriginalObject
					}
				val EReference ref = addReferenceToClass(tracedClass, refName, runtimeClass)
				traceability.addRefs_originalObject(tracedClass, ref)
			}


			// Then going through all properties for the remaining generation
			var Set<EStructuralFeature> runtimeProperties = new HashSet<EStructuralFeature>
			if (allNewEClasses.contains(runtimeClass))
				runtimeProperties.addAll(runtimeClass.EStructuralFeatures)
			else {
				if (!dynamicProperties.empty) {
					runtimeProperties.addAll(dynamicProperties);
				}
			}

			// Storing traceability stuff
			if (!runtimeProperties.isEmpty)
				traceability.addRuntimeClass(runtimeClass)

			val dimensionsGetters = new ArrayList

			// We go through the runtime properties of this class
			for (runtimeProperty : runtimeProperties) {

				// Storing traceability stuff
				traceability.addMutableProperty(runtimeClass, runtimeProperty)

				// ------------ Value class
				val valueClass = EcoreFactory.eINSTANCE.createEClass
				valueClass.name = TraceMMStrings.class_createStateClassName(runtimeClass, runtimeProperty)

				// We copy the property inside the value class
				val copiedProperty = EcoreUtil.copy(runtimeProperty) as EStructuralFeature
				if (copiedProperty instanceof EReference) {
					copiedProperty.containment = false
					copiedProperty.EOpposite = null
					copiedProperty.EType = handleTraceClass(runtimeProperty.EType as EClass)
					copiedProperty.derived = false
					copiedProperty.changeable = true
					copiedProperty.volatile = false

					// The value class inherits the SpecificReferenceValue abstract class
					val valueGenericSuperType = EcoreFactory.eINSTANCE.createEGenericType
					valueGenericSuperType.EClassifier = traceMMExplorer.specificReferenceValueClass
					val valueTypeBinding = EcoreFactory.eINSTANCE.createEGenericType
					valueGenericSuperType.ETypeArguments.add(valueTypeBinding)
					// And binds its type parameter to the generated traced object class
					valueTypeBinding.EClassifier = copiedProperty.EType
					valueClass.EGenericSuperTypes.add(valueGenericSuperType)
				} else {
					// The value class inherits the SpecificAttributeValue abstract class
					valueClass.ESuperTypes.add(traceMMExplorer.specificAttributeValueClass)
				}
				valueClass.EStructuralFeatures.add(copiedProperty)
				traceMMExplorer.statesPackage.EClassifiers.add(valueClass)

				traceability.putMutablePropertyToValueProperty(runtimeProperty, copiedProperty)

				ExecutionMetamodelTraceability.createTraceabilityAnnotation(valueClass,
					ExecutionMetamodelTraceability.getTraceabilityAnnotationValue(runtimeProperty))

				// ------------ Dimension class
				val dimensionClass = EcoreFactory.eINSTANCE.createEClass
				dimensionClass.name = TraceMMStrings.class_createDimensionClassName(runtimeClass, runtimeProperty)
				// The dimension class inherits the SpecificDimension abstract class
				val dimensionGenericSuperType = EcoreFactory.eINSTANCE.createEGenericType
				dimensionGenericSuperType.EClassifier = traceMMExplorer.specificDimensionClass
				val dimensionTypeBinding = EcoreFactory.eINSTANCE.createEGenericType
				dimensionGenericSuperType.ETypeArguments.add(dimensionTypeBinding)
				// And binds its type parameter to the generated value class
				dimensionTypeBinding.EClassifier = valueClass
				dimensionClass.EGenericSuperTypes.add(dimensionGenericSuperType)
				traceMMExplorer.statesPackage.EClassifiers.add(dimensionClass)

				val dimensionRef = addReferenceToClass(tracedClass, dimensionClass.name.toFirstLower, dimensionClass)
				dimensionRef.containment = true
				dimensionRef.lowerBound = 0
				dimensionRef.upperBound = 1
				dimensionsGetters.add(EcoreCraftingUtil.stringGetter(dimensionRef))

				traceability.putDimensionClass(runtimeProperty, dimensionClass)
				traceability.putDimensionRef(runtimeProperty, dimensionRef)

				traceability.putValueClass(runtimeProperty, valueClass)
			}
			val getDimensionsInternal = EcoreFactory.eINSTANCE.createEOperation
			val getDimensionsAnnotation = EcoreFactory.eINSTANCE.createEAnnotation
			getDimensionsInternal.EAnnotations.add(getDimensionsAnnotation)
			getDimensionsInternal.name = "getDimensionsInternal"
			getDimensionsInternal.lowerBound = 0
			getDimensionsInternal.upperBound = -1
			val dimensionGenericSuperType = EcoreFactory.eINSTANCE.createEGenericType
			dimensionGenericSuperType.EClassifier = traceMMExplorer.specificDimensionClass
			val dimensionTypeBinding = EcoreFactory.eINSTANCE.createEGenericType
			dimensionGenericSuperType.ETypeArguments.add(dimensionTypeBinding)
			getDimensionsInternal.EGenericType = dimensionGenericSuperType
			getDimensionsAnnotation.source = GenModelPackage.eNS_URI
			getDimensionsAnnotation.details.put("body", '''
				final EList<SpecificDimension<?>> result = new org.eclipse.emf.ecore.util.BasicInternalEList<SpecificDimension<?>>(Object.class);
				result.addAll(super.getDimensionsInternal());
				«FOR getter : dimensionsGetters»
					result.add(«getter»);
				«ENDFOR»
				return result;
			''')
			tracedClass.EOperations.add(getDimensionsInternal)

			return tracedClass
		} else {
			return runtimeToTraced.get(runtimeClass)
		}
	}

}
