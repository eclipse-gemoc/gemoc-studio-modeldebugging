package org.eclipse.gemoc.executionframework.engine.concurrency.strategies.filters

import java.util.Comparator
import java.util.HashSet
import java.util.Set
import org.eclipse.emf.ecore.EObject
import org.eclipse.gemoc.executionframework.engine.concurrency.strategies.EnumeratingFilteringStrategy
import org.eclipse.gemoc.trace.commons.model.generictrace.GenericSmallStep
import org.eclipse.gemoc.trace.commons.model.trace.MSE
import org.eclipse.gemoc.trace.commons.model.trace.ParallelStep
import org.eclipse.gemoc.trace.commons.model.trace.SmallStep
import org.eclipse.gemoc.trace.commons.model.trace.Step
import org.eclipse.xtend.lib.annotations.Accessors

/**
 * Remove steps that differ only in objects of a specific type -- specify that objects of that type are not considered to have identity (e.g., parts in the PLS example).
 * 
 * This is a filtering strategy to allow it to interact with concurrency computation to recognise that it is still meaningful to have two potentially concurrent applications 
 * of assemble (in the PLS case), for example. It's just not meaningful to have four ``different'' atomic assemble steps where there is only one machine. Hence, this is a filtering
 * strategy that needs to be applied after all possible concurrent executions have been computed.
 */
class ForceAbsenceOfActionsOnClassStrategy  implements EnumeratingFilteringStrategy {

	/**
	 * Objects of these types should not be considered to have independent identity. So, while we can require to match multiple, distinct objects in one rule match, two rule matches 
	 * should only be considered different if they differ in matches for objects that are not of one of these types.
	 */
	@Accessors
	var Set<EObject> toBeAbsentObject

	new(Set<EObject> toBeAbsentObject) {
		this.toBeAbsentObject = toBeAbsentObject
	}
	
	new() {
		this(emptySet)
	}

	override Set<ParallelStep<? extends Step<?>, ?>> filter(Set<ParallelStep<? extends Step<?>, ?>> steps, Comparator<Step<?>> stepComparator) {
		var Set<ParallelStep<? extends Step<?>, ?>> res = new HashSet<ParallelStep<? extends Step<?>, ?>>(steps)
		for(ParallelStep<? extends Step<?>,?> ps : steps){
			for(Step<?> s : ps.subSteps){
				if (s.shouldBeAbsent){
					res.remove(ps)
				}
			}
		}
		return res
	}
	
	def boolean shouldBeAbsent(Step s) {
		var MSE mse = (s as GenericSmallStep).mseoccurrence.mse;
		return (mse.getCaller() !== null && (mse.getCaller().isToBePresentType)) 
				|| 
			   (((s as SmallStep).footprint != null) && ((s as SmallStep).footprint.accesses.exists[e | e.isToBePresentType]))
	}
	

	private def boolean isToBePresentType(EObject eo) {
		toBeAbsentObject.exists[nit|(nit == eo)]
	}
}
