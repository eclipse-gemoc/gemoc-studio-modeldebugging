package org.eclipse.gemoc.executionframework.engine.concurrency.strategies.concurrency

import java.util.List
import org.eclipse.gemoc.executionframework.engine.concurrency.strategies.ConcurrencyStrategy
import org.eclipse.gemoc.trace.commons.model.trace.SmallStep
import org.eclipse.xtend.lib.annotations.Accessors

/**
 * A strategy that only allows steps to be executed concurrently, if they are connected to MSEs whose action is in a given list
 */
class SetOfActionsStrategy  implements ConcurrencyStrategy {

	@Accessors
	var List<String> allowedActions

	new(List<String> allowedActions) {
		this.allowedActions = allowedActions;
	}
	
	new() {
		this(emptyList)
	}
	
	override canBeConcurrent(SmallStep<?> step1, SmallStep<?> step2) {
		allowedActions.contains(step1.mseoccurrence.mse.action.name) &&
		allowedActions.contains(step2.mseoccurrence.mse.action.name)
	}
}
