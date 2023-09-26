package org.eclipse.gemoc.executionframework.engine.concurrency.strategies.filters

import java.util.Comparator
import java.util.HashSet
import java.util.List
import java.util.Set
import org.eclipse.emf.ecore.EClass
import org.eclipse.gemoc.executionframework.engine.concurrency.strategies.EnumeratingFilteringStrategy
import org.eclipse.gemoc.trace.commons.model.trace.ParallelStep
import org.eclipse.gemoc.trace.commons.model.trace.SmallStep
import org.eclipse.gemoc.trace.commons.model.trace.Step
import org.eclipse.xtend.lib.annotations.Accessors

import static extension org.eclipse.gemoc.executionframework.engine.concurrency.strategies.FootprintUtil.*


/**
 * Remove steps that differ only in objects of a specific type -- specify that objects of that type are not considered to have identity (e.g., parts in the PLS example).
 * 
 * This is a filtering strategy to allow it to interact with concurrency computation to recognise that it is still meaningful to have two potentially concurrent applications 
 * of assemble (in the PLS case), for example. It's just not meaningful to have four ``different'' atomic assemble steps where there is only one machine. Hence, this is a filtering
 * strategy that needs to be applied after all possible concurrent executions have been computed.
 */
class NonIdentityElementsStrategy  implements EnumeratingFilteringStrategy {

	/**
	 * Objects of these types should not be considered to have independent identity. So, while we can require to match multiple, distinct objects in one rule match, two rule matches 
	 * should only be considered different if they differ in matches for objects that are not of one of these types.
	 */
	@Accessors
	var List<? extends EClass> nonIdentityTypes

	new(List<? extends EClass> nonIdentityTypes) {
		this.nonIdentityTypes = nonIdentityTypes
	}
	
	new() {
		this(emptyList)
	}

	override Set<ParallelStep<? extends Step<?>, ?>> filter(Set<ParallelStep<? extends Step<?>, ?>> steps, Comparator<Step<?>> stepComparator) {
		steps.fold(new HashSet<ParallelStep<? extends Step<?>, ?>>)[acc, step |
			if (!acc.exists[s2|equivalentSteps(step, s2)]) {
				acc += step
			} 
			
			acc
		] 
	}
	
	private def equivalentSteps(ParallelStep<? extends Step<?>,?> s1, ParallelStep<? extends Step<?>,?> s2) {
		if (s1.subSteps.size == s2.subSteps.size) {
			// TODO This can probably be done more efficiently
			s1.subSteps.forall[ss1 | s2.subSteps.exists[ss2 | ss1.equivalentFootprints(ss2)]] &&
			s2.subSteps.forall[ss2 | s1.subSteps.exists[ss1 | ss1.equivalentFootprints(ss2)]]
		} else {
			false
		}
	}
		
	private def equivalentFootprints(Step<?> s1, Step<?> s2) {
		if (s1 instanceof SmallStep<?>) {
			if (s2 instanceof SmallStep<?>) {
				if (s1.footprint === null || s2.footprint === null){
					return true // if footprint is not present then by default it can be concurrent
				}
				val s2Footprint = s2.footprint.accesses;
				
				(s1 === s2) || 
				(s1.footprint.equalTo(s2.footprint)) ||
				((s1.mseoccurrence.mse.action.name == s2.mseoccurrence.mse.action.name) &&
					s1.footprint.accesses.forall[o | 
						o.eClass.isNonIdentityType || s2Footprint.contains(o)
					] &&
					s1.footprint.instantiations.forall[isNonIdentityType] &&
					s2.footprint.instantiations.forall[isNonIdentityType]
				)
				
			}
		}
	}

	private def isNonIdentityType(EClass type) {
		nonIdentityTypes.exists[nit|(nit === type) || nit.isSuperTypeOf(type)]
	}
}
