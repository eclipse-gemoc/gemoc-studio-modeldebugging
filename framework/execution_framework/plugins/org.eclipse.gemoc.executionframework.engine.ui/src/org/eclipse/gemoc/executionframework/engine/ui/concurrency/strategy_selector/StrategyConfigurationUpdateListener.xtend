package org.eclipse.gemoc.executionframework.engine.ui.concurrency.strategy_selector

import org.eclipse.gemoc.executionframework.engine.ui.concurrency.strategies.StrategyDefinition
import org.eclipse.swt.widgets.Control

interface StrategyConfigurationUpdateListener {
	/** 
	 * Called whenever the user makes changes to the selection status or additional information of a strategy.
	 * 
	 * @param sd the strategy definiton for which the configuration information has been changed
	 * @param isSelected true if the user has selected the strategy to be included
	 * @param uiControl the control, if any, provided by the strategy definition for the user to make additional changes to 
	 *                  the strategy configuration. The strategy definition knows how to use this to configure a given strategy.
	 *                  May be <code>null</code>
	 */
	def void onStrategyConfigurationHasChanged(StrategyDefinition sd, boolean isSelected, Control uiControl)

}
