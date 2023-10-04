package org.eclipse.gemoc.executionframework.engine.concurrency.strategies.filters

import org.chocosolver.solver.Model
import org.chocosolver.solver.expression.discrete.arithmetic.ArExpression
import org.eclipse.gemoc.executionframework.engine.concurrency.strategies.SymbolicFilteringStrategy
import org.eclipse.xtend.lib.annotations.Accessors

import static extension org.eclipse.gemoc.executionframework.engine.concurrency.ChocoHelper.*

class MaxNumberOfStepsStrategy implements SymbolicFilteringStrategy {
	
	@Accessors
	var int maxNumberOfSteps

	new(int maxNumberOfSteps) {
		this.maxNumberOfSteps = maxNumberOfSteps
	}

	new() {
		this(2)
	}
	
	override filterSymbolically(Model symbolicPossibleSteps) {
		val variables = symbolicPossibleSteps.smallStepVariables
		
		if (variables.size > maxNumberOfSteps) {
			variables.tail.fold(variables.head as ArExpression)[expr, v | expr.add(v)].le(maxNumberOfSteps).post()		
		}
	}
}
