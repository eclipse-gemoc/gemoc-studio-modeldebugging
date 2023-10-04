package org.eclipse.gemoc.executionframework.engine.ui.concurrency.strategies.concurrency

import org.eclipse.gemoc.executionframework.engine.concurrency.strategies.Strategy
import org.eclipse.gemoc.executionframework.engine.ui.concurrency.strategies.StrategyDefinition

abstract class ConcurrencyStrategyDefinition extends StrategyDefinition {
	new(String ID, String label, Class<? extends Strategy> clazz) {
		super(ID, label, StrategyDefinition.StrategyGroup.CONCURRENCY_STRATEGY, clazz)
	}
}
