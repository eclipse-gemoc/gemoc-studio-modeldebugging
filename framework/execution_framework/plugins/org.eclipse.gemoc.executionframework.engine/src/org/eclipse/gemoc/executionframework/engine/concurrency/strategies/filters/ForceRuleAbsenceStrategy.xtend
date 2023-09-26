package org.eclipse.gemoc.executionframework.engine.concurrency.strategies.filters

import java.util.Comparator
import java.util.HashSet
import java.util.List
import java.util.Set
import org.eclipse.gemoc.executionframework.engine.concurrency.strategies.EnumeratingFilteringStrategy
import org.eclipse.gemoc.trace.commons.model.generictrace.GenericSmallStep
import org.eclipse.gemoc.trace.commons.model.trace.MSE
import org.eclipse.gemoc.trace.commons.model.trace.ParallelStep
import org.eclipse.gemoc.trace.commons.model.trace.Step
import org.eclipse.xtend.lib.annotations.Accessors

/**
 * Remove steps that contains one of the rules chosen
 */
class ForceRuleAbsenceStrategy  implements EnumeratingFilteringStrategy {

	/**
	 * These rules should not appear in the solution for any object
	 */
	@Accessors
	var List<String> toBeAbsentRule

	new(List<String> allowedActions) {
		this.toBeAbsentRule = allowedActions;
	}
	
	new() {
		this(emptyList)
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
		return (mse.name.isToBeAbsentType)
//				|| 
//			   (((s as SmallStep).footprint != null) && ((s as SmallStep).footprint.accesses.exists[e | e.eClass.isToBeAbsentType]))
	}
	

	private def boolean isToBeAbsentType(String MSEName) {
		for(String dseQName: toBeAbsentRule){
			var String DSEName = dseQName.substring(dseQName.lastIndexOf('.')+1)
			var String DSEType = MSEName.substring(MSEName.lastIndexOf('_')+1)
			if(DSEName == DSEType){
				return true
			}
		}
		return false
//		toBeAbsentDSE.exists[dse|(dse.substring(dse.lastIndexOf('.')) == MSEName.substring(MSEName.lastIndexOf('_')))]
	}
}
