package org.eclipse.gemoc.executionframework.engine.concurrency.strategies

import org.chocosolver.solver.Model

/**
 * A filtering strategy that filters by adding constraints to the symbolic representation of the concurrency model.
 * 
 * Filtering in this way is more efficient than "unfolding" the symbolic representation and then removing logical steps from this extension.
 * 
 */
interface SymbolicFilteringStrategy extends Strategy{

	/**
	 * Add extra constraints to the given symbolic representation.
	 */
	def void filterSymbolically(Model symbolicPossibleSteps)
}
