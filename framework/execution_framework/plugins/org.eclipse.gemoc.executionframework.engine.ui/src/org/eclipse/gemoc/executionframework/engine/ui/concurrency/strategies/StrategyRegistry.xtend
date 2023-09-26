package org.eclipse.gemoc.executionframework.engine.ui.concurrency.strategies

import java.util.HashMap
import org.eclipse.gemoc.executionframework.engine.concurrency.strategies.Strategy
import org.eclipse.gemoc.executionframework.engine.ui.concurrency.strategies.concurrency.OverlapStrategyDefinition
import org.eclipse.gemoc.executionframework.engine.ui.concurrency.strategies.concurrency.SetOfActionsStrategyDefinition
import org.eclipse.gemoc.executionframework.engine.ui.concurrency.strategies.filters.ForceAbsenceOfActionsOnClassDefinition
import org.eclipse.gemoc.executionframework.engine.ui.concurrency.strategies.filters.ForceEventPresenceStrategyDefinition
import org.eclipse.gemoc.executionframework.engine.ui.concurrency.strategies.filters.ForceRuleAbsenceStrategyDefinition
import org.eclipse.gemoc.executionframework.engine.ui.concurrency.strategies.filters.MaxNumberOfStepsStrategyDefinition
import org.eclipse.gemoc.executionframework.engine.ui.concurrency.strategies.filters.MaximalConcurrencyStrategyDefinition
import org.eclipse.gemoc.executionframework.engine.ui.concurrency.strategies.filters.NonIdentityElementsStrategyDefinition

/**
 * Registry of strategy descriptions. Eventually to be filled from an extension point.
 * 
 */
class StrategyRegistry {

	public static val INSTANCE = new StrategyRegistry

	public static val STRATEGIES_CONFIG_KEY = "uk.ac.kcl.inf.xdsml.strategies"
	
	private new() {
		add(new ForceAbsenceOfActionsOnClassDefinition)
		add(new OverlapStrategyDefinition)
		add(new SetOfActionsStrategyDefinition)
		add(new NonIdentityElementsStrategyDefinition)
		add(new MaxNumberOfStepsStrategyDefinition)
		add(new MaximalConcurrencyStrategyDefinition)
		add(new ForceEventPresenceStrategyDefinition)
		add(new ForceRuleAbsenceStrategyDefinition)
		
	}

	val registry = new HashMap<String, StrategyDefinition>()

	def add(StrategyDefinition strategy) {
		registry.put(strategy.getStrategyID, strategy)
	}

	def getStrategies() {
		registry.values
	}

	def get(String ID) {
		registry.get(ID)
	}
	
	def StrategyDefinition strategyDefinitionOf(Strategy strategy) {
		registry.filter[x, sd| sd.isStrategyInstance(strategy)].get(0)
	}
}
