package org.eclipse.gemoc.executionframework.engine.ui.concurrency.strategies

/**
 * Provides a way for changes in a StrategyDefinition-provided UI control to be communicated to the wider world, if there is interest.
 */
interface StrategyControlUpdateListener {
	/**
	 * Indicates that a user-initiated change (as opposed to a change as a result of an event from the LaunchConfigurationContext) 
	 * has occurred to the control for the given strategy. It is the listener's responsibility to know how to deal with this and to 
	 * find out the new state of the control.
	 */
	def void controlUpdated(StrategyDefinition sd)
}
