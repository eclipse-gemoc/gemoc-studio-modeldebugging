package org.eclipse.gemoc.executionframework.engine.ui.concurrency.strategies.filters

import org.eclipse.gemoc.executionframework.engine.concurrency.strategies.Strategy
import org.eclipse.gemoc.executionframework.engine.ui.concurrency.strategies.StrategyDefinition

abstract class FilteringStrategyDefinition extends StrategyDefinition {
	new(String ID, String label, Class<? extends Strategy> clazz) {
		super(ID, label, StrategyDefinition.StrategyGroup.FILTERING_STRATEGY, clazz)
	}
}
