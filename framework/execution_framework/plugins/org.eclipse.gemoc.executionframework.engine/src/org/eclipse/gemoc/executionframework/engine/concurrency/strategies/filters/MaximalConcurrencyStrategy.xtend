package org.eclipse.gemoc.executionframework.engine.concurrency.strategies.filters

import java.util.Comparator
import java.util.HashSet
import java.util.Set
import org.eclipse.gemoc.executionframework.engine.concurrency.strategies.EnumeratingFilteringStrategy
import org.eclipse.gemoc.trace.commons.model.trace.ParallelStep
import org.eclipse.gemoc.trace.commons.model.trace.Step
import org.eclipse.xtend.lib.annotations.Accessors

/**
 * Remove steps where a parallel step with a superset of the steps exists. As a result, only the steps with the maximal concurrency will be shown.
 * 
 * The user can configure whether the strategy should always keep atomic steps.
 */
class MaximalConcurrencyStrategy implements EnumeratingFilteringStrategy {

	@Accessors
	var boolean keepAtomicSteps = true

	override Set<ParallelStep<? extends Step<?>, ?>> filter(Set<ParallelStep<? extends Step<?>, ?>> steps, Comparator<Step<?>> stepComparator) {
		steps.sortBy[-subSteps.size].fold(new HashSet<ParallelStep<? extends Step<?>, ?>>)[acc, step |
			if (!acc.exists[s2|s2.superStepOf(step, stepComparator)]) {
				acc += step
			} else if (keepAtomicSteps && (step.subSteps.size === 1)) {
				acc += step
			}
			
			acc
		] 		
	}
	
	/**
	 * True if s1 has all the substeps of s2.
	 */
	private def superStepOf(ParallelStep<? extends Step<?>,?> s1, ParallelStep<? extends Step<?>,?> s2, Comparator<Step<?>> stepComparator) {
		if (s1.subSteps.size >= s2.subSteps.size) {
			s2.subSteps.forall[ss2 | s1.subSteps.exists[ss1 | stepComparator.compare(ss1, ss2) === 0]]
		}
	}
}
