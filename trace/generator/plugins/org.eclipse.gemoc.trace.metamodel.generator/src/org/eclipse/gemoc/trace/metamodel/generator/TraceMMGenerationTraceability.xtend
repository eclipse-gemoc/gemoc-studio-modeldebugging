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

import java.util.HashMap
import java.util.HashSet
import java.util.Map
import java.util.Set
import opsemanticsview.OperationalSemanticsView
import opsemanticsview.Rule
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EReference
import org.eclipse.emf.ecore.EStructuralFeature
import org.eclipse.xtend.lib.annotations.Accessors

/**
 * Second output of the transformation: a class both to access to parts
 * of the trace metamodel, and with links between the original metamodels
 * and the trace metamodel.
 */
class TraceMMGenerationTraceability {

	new(TraceMMExplorer traceMMExplorer, OperationalSemanticsView mmext) {
		this.traceMMExplorer = traceMMExplorer
		this.mmext = mmext
	}

	@Accessors(PUBLIC_GETTER, PACKAGE_SETTER)
	private val TraceMMExplorer traceMMExplorer
	
	@Accessors(PUBLIC_GETTER, PACKAGE_SETTER)
	private val OperationalSemanticsView mmext

	private Set<EClass> runtimeClasses = new HashSet<EClass>

	package def void addRuntimeClass(EClass c) {
		runtimeClasses.add(c)
	}

	public def Set<EClass> getRuntimeClasses() {
		return runtimeClasses.immutableCopy
	}

	private Map<EClass, Set<EStructuralFeature>> mutableProperties = new HashMap<EClass, Set<EStructuralFeature>>

	package def void addMutableProperty(EClass c, EStructuralFeature r) {
		if (!mutableProperties.containsKey(c))
			mutableProperties.put(c, new HashSet)
		mutableProperties.get(c).add(r)
	}

	public def Set<EStructuralFeature> getMutablePropertiesOf(EClass c) {
		if (mutableProperties.containsKey(c)) {
			return mutableProperties.get(c).immutableCopy
		} else {
			return #{}
		}
	}

	public def Set<EStructuralFeature> getAllMutableProperties() {
		return mutableProperties.values.flatten.toSet
	}

	val tracedClasses = new HashMap<EClass, EClass>

	package def void putTracedClasses(EClass runtimeClass, EClass tracedClass) {
		tracedClasses.put(runtimeClass, tracedClass)
	}

	public def EClass getTracedClass(EClass mutableClass) {
		return tracedClasses.get(mutableClass)
	}
	
	public def Set<EClass> getTracedClassSet() {
		val result = new HashSet
		result.addAll(tracedClasses.keySet)
		return result
	}
	
	val dimensionClasses = new HashMap<EStructuralFeature, EClass>
	
	public def putDimensionClass(EStructuralFeature property, EClass dimension) {
		dimensionClasses.put(property, dimension)
	}
	
	public def EClass getDimensionClass(EStructuralFeature property) {
		return dimensionClasses.get(property)
	}
	
	val dimensionRefs = new HashMap<EStructuralFeature, EReference>
	
	public def putDimensionRef(EStructuralFeature property, EReference dimensionRef) {
		dimensionRefs.put(property, dimensionRef)
	}
	
	public def EReference getDimensionRef(EStructuralFeature property) {
		return dimensionRefs.get(property)
	}
	
	public def Set<EClass> getNewClasses() {
		return mmext.dynamicClasses.toSet
	}

	public def boolean hasTracedClass(EClass mutableClass) {
		return tracedClasses.containsKey(mutableClass)
	}

	public def Set<EClass> getAllMutableClasses() {
		return tracedClasses.keySet;
	}
	
	

	public def EClass getRealMutableClass(EClass tracedClass) {
		val mutClass = tracedClasses.entrySet.findFirst[p|p.value == tracedClass]
		if (mutClass != null)
			return mutClass.key
		else
			return null
	}

	private Map<EClass, Set<EReference>> refs_originalObject = new HashMap<EClass, Set<EReference>>

	package def void addRefs_originalObject(EClass c1, EReference r) {
		if (!refs_originalObject.containsKey(c1))
			refs_originalObject.put(c1, new HashSet)
		refs_originalObject.get(c1).add(r)
	}

	public def Set<EReference> getRefs_originalObject(EClass class1) {
		val Set<EReference> res = new HashSet<EReference>
		val existingRefs = class1.EAllSuperTypes.map[c|getRefs_originalObject(c)].flatten.toSet
		res.addAll(existingRefs)
		val refsForThisClass = refs_originalObject.get(class1)
		if (refsForThisClass != null && !refsForThisClass.isEmpty)
			res.addAll(refsForThisClass)
		return res
	}

//	private Map<EStructuralFeature, EReference> traceOf = new HashMap<EStructuralFeature, EReference>
//
//	package def void putTraceOf(EStructuralFeature r1, EReference r2) {
//		traceOf.put(r1, r2)
//	}
//
//	public def EReference getTraceOf(EStructuralFeature s) {
//		return traceOf.get(s)
//	}

	private Map<EStructuralFeature, EClass> valueClass = new HashMap<EStructuralFeature, EClass>

	package def void putValueClass(EStructuralFeature r, EClass c) {
		valueClass.put(r, c)
	}

	public def EClass getValueClass(EStructuralFeature s) {
		return valueClass.get(s)
	}

	private Map<EStructuralFeature, EReference> stateClassToValueClass = new HashMap<EStructuralFeature, EReference>

	package def void putStateClassToValueClass(EStructuralFeature r1, EReference r2) {
		stateClassToValueClass.put(r1, r2)
	}

	public def EReference getStateClassToValueClass(EStructuralFeature s) {
		if (mutablePropertyToValueProperty.containsValue(s)) {
			val key = mutablePropertyToValueProperty.entrySet.findFirst[entry|entry.value == s].key
			return stateClassToValueClass.get(key)
		} else {
			return stateClassToValueClass.get(s)	
		}
		
	}

	private Set<EClass> stepClasses = new HashSet<EClass>

	package def void addStepClass(EClass c) {
		stepClasses.add(c)
	}

	public def Set<EClass> getStepClasses() {
		return stepClasses.immutableCopy
	}

	private val Map<Rule, EClass> stepRuleToStepClass = new HashMap

	package def void addStepRuleToStepClass(Rule stepRule, EClass stepClass) {
		stepRuleToStepClass.put(stepRule, stepClass)
	}

	public def EClass getStepClassFromStepRule(Rule stepRule) {
		return stepRuleToStepClass.get(stepRule)
	}

	private Set<EClass> bigStepClasses = new HashSet

	package def void addBigStepClass(EClass c) {
		bigStepClasses.add(c)
	}

	public def Set<EClass> getBigStepClasses() {
		return bigStepClasses.immutableCopy
	}
	
	private Map<EClass,EClass> implicitStepClasses = new HashMap
	
	package def void putImplicitStepClass(EClass step, EClass containgClass) {
		implicitStepClasses.put(step,containgClass)
	}

	public def Set<EClass> getImplicitStepClasses() {
		return implicitStepClasses.keySet.immutableCopy
	}
	
	public def EClass getImplicitStepContainingClass(EClass implicitStepClass) {
		return implicitStepClasses.get(implicitStepClass)
	}

	private val Map<EClass, EReference> stepSequences = new HashMap

	package def void addStepSequence(EClass stepClass, EReference trace) {
		stepSequences.put(stepClass, trace)
	}

	public def EReference getStepSequence(EClass stepClass) {
		return stepSequences.get(stepClass)
	}

	def boolean hasExeClass(EClass tracedClass) {
		return tracedClasses.keySet.exists[k|tracedClasses.get(k) == tracedClass];
	}

	def EClass getExeClass(EClass tracedClass) {
		return tracedClasses.keySet.findFirst[k|tracedClasses.get(k) == tracedClass];
	}
	
	private val Map<EStructuralFeature, EStructuralFeature> mutablePropertyToValueProperty = new HashMap
	
	def void putMutablePropertyToValueProperty(EStructuralFeature mutableProperty, EStructuralFeature valueProperty) {
		mutablePropertyToValueProperty.put(mutableProperty,valueProperty)
	}
	
	def EStructuralFeature getValuePropertyOfMutableProperty(EStructuralFeature mutableProperty) {
		return mutablePropertyToValueProperty.get(mutableProperty)
	}
	
	
	
	

}
