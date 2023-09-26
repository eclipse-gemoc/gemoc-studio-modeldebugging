
package org.eclipse.gemoc.executionframework.engine.ui.concurrency.strategies

import org.eclipse.gemoc.executionframework.engine.concurrency.strategies.Strategy
import org.eclipse.swt.widgets.Composite
import org.eclipse.swt.widgets.Control
import org.eclipse.xtend.lib.annotations.AccessorType
import org.eclipse.xtend.lib.annotations.Accessors

import static extension org.eclipse.xtend.lib.annotations.AccessorType.*

class StrategyDefinition {

	@Accessors(AccessorType.PUBLIC_GETTER)
	val String strategyID
	@Accessors(AccessorType.PUBLIC_GETTER)
	val String humanReadableLabel

	enum StrategyGroup {
		CONCURRENCY_STRATEGY,
		FILTERING_STRATEGY
	}

	@Accessors(AccessorType.PUBLIC_GETTER)
	val StrategyDefinition.StrategyGroup group

	val Class<? extends Strategy> clazz

	new(String ID, String label, StrategyDefinition.StrategyGroup group, Class<? extends Strategy> clazz) {
		strategyID = ID
		humanReadableLabel = label
		this.group = group
		this.clazz = clazz
	}

	def instantiate() {
		clazz.getConstructor().newInstance()
	}

	/**
	 * Override this to define additional initialisation for a newly created strategy.
	 * 
	 * @param strategy the strategy to configure
	 * @param configData a string containing configuration data as defined by a previous call to {@link #encodeConfigInformation}. May be <code>null</code> if this strategy definition does not define any additional configuration data.
	 * @param lcc contextual information for the configuration
	 */
	def void initialise(Strategy strategy, String configData, LaunchConfigurationContext lcc) {}

	/**
	 * Provide an optional control to display in a launch tab to allow users to provide additional configuration information for the strategy. 
	 */
	def Control getUIControl(Composite parent, LaunchConfigurationContext lcc, StrategyControlUpdateListener scul) { null }

	/**
	 * Encode the user choice in a string that can be saved in a launch configuration.
	 */
	def String encodeConfigInformation(Control uiElement) { null }

	/**
	 * Initialise this strategy definition's control from the given configData.
	 */
	def void initaliseControl(Control uiElement, String configData) {}

	/**
	 * Initialise this strategy definition's control from the given strategy.
	 */
	def void initaliseControl(Control uiElement, Strategy strategy) {}
	
	def boolean isStrategyInstance(Strategy strategy) {
		return strategy.class == clazz
	}

}
