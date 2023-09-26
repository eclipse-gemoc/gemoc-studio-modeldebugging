package org.eclipse.gemoc.executionframework.engine.concurrency.strategies

import java.util.Comparator
import java.util.Set
import org.eclipse.gemoc.trace.commons.model.trace.ParallelStep
import org.eclipse.gemoc.trace.commons.model.trace.Step

interface EnumeratingFilteringStrategy extends FilteringStrategy {
	/** 
	 * Produce a filtered version of the set of steps provided.
	 */
	def Set<ParallelStep<? extends Step<?>, ?>> filter(Set<ParallelStep<? extends Step<?>, ?>> steps, Comparator<Step<?>> stepComparator) 
	
}
