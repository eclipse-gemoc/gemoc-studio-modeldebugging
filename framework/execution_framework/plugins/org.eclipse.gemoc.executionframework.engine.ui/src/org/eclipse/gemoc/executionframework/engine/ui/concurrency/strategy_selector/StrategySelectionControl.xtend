package org.eclipse.gemoc.executionframework.engine.ui.concurrency.strategy_selector

import java.util.ArrayList
import java.util.HashMap
import java.util.List
import org.eclipse.debug.core.ILaunchConfiguration
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy
import org.eclipse.debug.internal.ui.SWTFactory
import org.eclipse.gemoc.executionframework.engine.concurrency.AbstractConcurrentExecutionEngine
import org.eclipse.gemoc.executionframework.engine.concurrency.ConcurrentRunConfiguration
import org.eclipse.gemoc.executionframework.engine.concurrency.strategies.Strategy
import org.eclipse.gemoc.executionframework.engine.ui.concurrency.launcher.LaunchConfigurationStrategiesTab
import org.eclipse.gemoc.executionframework.engine.ui.concurrency.strategies.LaunchConfigurationContext
import org.eclipse.gemoc.executionframework.engine.ui.concurrency.strategies.StrategyDefinition
import org.eclipse.gemoc.executionframework.engine.ui.concurrency.strategies.StrategyRegistry
import org.eclipse.swt.SWT
import org.eclipse.swt.custom.ScrolledComposite
import org.eclipse.swt.events.SelectionEvent
import org.eclipse.swt.events.SelectionListener
import org.eclipse.swt.layout.GridData
import org.eclipse.swt.layout.GridLayout
import org.eclipse.swt.widgets.Button
import org.eclipse.swt.widgets.Composite
import org.eclipse.swt.widgets.Control
import org.eclipse.xtend.lib.annotations.Accessors
import org.eclipse.xtext.xbase.lib.Procedures.Procedure2

/**
 * Control for selecting and configuring strategies.
 */
class StrategySelectionControl extends Composite {

	val strategySelections = new HashMap<StrategyDefinition, Boolean>
	val components = new HashMap<StrategyDefinition, Pair<Button, Control>>
	val LaunchConfigurationContext configContext

	@Accessors(PUBLIC_SETTER)
	var StrategyConfigurationUpdateListener updateListener = null

	var ScrolledComposite scrollPane
	var Composite content

	new(Composite parent, LaunchConfigurationContext configContext) {
		super(parent, SWT.FILL)

		layout = new GridLayout(1, false)

		this.configContext = configContext

		// FIXME: This seems wrong: the control will be used without a launch tab, too...
		updateListener = new LaunchConfigurationStrategiesTab(configContext)

		StrategyRegistry.INSTANCE.strategies.forEach [ sd |
			strategySelections.put(sd, false)
		]

		scrollPane = new ScrolledComposite(this, SWT.V_SCROLL.bitwiseOr(SWT.H_SCROLL))
		scrollPane.layout = new GridLayout(1, false)
		scrollPane.layoutData = new GridData(SWT.FILL, SWT.FILL, true, true)

		content = new Composite(scrollPane, SWT.NONE)
		content.layout = new GridLayout(2, false)
		content.layoutData = new GridData(SWT.FILL, SWT.FILL, true, true)

		createLayout

		scrollPane.content = content
		scrollPane.expandHorizontal = true
		scrollPane.expandVertical = true
		scrollPane.minSize = content.computeSize(SWT.DEFAULT, SWT.DEFAULT)

		requestLayout
	}

	def initialiseFrom(ILaunchConfiguration configuration) {
		initialiseFrom(
			configuration.getAttribute(StrategyRegistry.STRATEGIES_CONFIG_KEY, #[]),
			[ control, extension sd |
				control.initaliseControl(
					configuration.getAttribute(sd.strategyID + ConcurrentRunConfiguration.STRATEGIES_CONFIG_DATA_KEY,
						""))
			]
		)
		scrollPane.minSize = content.computeSize(SWT.DEFAULT, SWT.DEFAULT)
		requestLayout
	}

	def updateSelectionsFrom(AbstractConcurrentExecutionEngine<?, ?> engine) {
		val List<Strategy> strategies = new ArrayList
		strategies.addAll(engine.concurrencyStrategies)
		strategies.addAll(engine.filteringStrategies)
		val groupedStrategies = strategies.groupBy[StrategyRegistry::INSTANCE.strategyDefinitionOf(it)]
		initialiseFrom(groupedStrategies.keySet.map[strategyID], [ control, extension sd |
			control.initaliseControl(groupedStrategies.get(sd)?.head)
		])
	}

	def saveConfiguration(ILaunchConfigurationWorkingCopy configuration) {
		val selectedStrategies = strategySelections.filter[hd, selected|selected].keySet
		configuration.setAttribute(StrategyRegistry.STRATEGIES_CONFIG_KEY,
			selectedStrategies.map[it.getStrategyID].toList)
		selectedStrategies.forEach [ extension hd |
			val strategyComponent = components.get(hd).value
			if (strategyComponent !== null) {
				configuration.setAttribute(hd.getStrategyID + ConcurrentRunConfiguration.STRATEGIES_CONFIG_DATA_KEY,
					strategyComponent.encodeConfigInformation())
			}
		]
	}

	private def createLayout() {
		strategySelections.keySet.sortBy[strategyID].reverseView.forEach [ sd |

			val checkbox = createCheckButton(content, sd.humanReadableLabel)
			checkbox.selection = strategySelections.get(sd)

			val uiControl = sd.getUIControl(content, configContext, [
				val isSelected = strategySelections.get(sd)

				if (isSelected) {
					// No point updating the detailed strategy config otherwise...
					updateListener.onStrategyConfigurationHasChanged(sd, isSelected, components.get(sd).value)
				}
			])
			if (uiControl !== null) {
				uiControl.enabled = strategySelections.get(sd)
			}
			components.put(sd, new Pair(checkbox, uiControl))

			checkbox.addSelectionListener(new SelectionListener() {

				override widgetSelected(SelectionEvent e) {
					strategySelections.put(sd, checkbox.selection)
					if (uiControl !== null) {
						uiControl.enabled = strategySelections.get(sd)
					}
					updateListener.onStrategyConfigurationHasChanged(sd, checkbox.selection, uiControl)
				}

				override widgetDefaultSelected(SelectionEvent e) {}
			})
		]
	}

	private def Button createCheckButton(Composite parent, String label) {
		SWTFactory.createCheckButton(parent, label, null, false, 1)
	}

	private def initialiseFrom(
		Iterable<String> selectedStrategiesIDs,
		Procedure2<Control, StrategyDefinition> initialiseControl
	) {
		strategySelections.keySet.forEach[hd|strategySelections.put(hd, false)]

		selectedStrategiesIDs.forEach [ sid |
			strategySelections.put(StrategyRegistry.INSTANCE.get(sid), true)
		]

		strategySelections.forEach [ extension sd, selected |
			val strategyComponents = components.get(sd)
			val checkbox = strategyComponents.key
			if (checkbox !== null) {
				checkbox.selection = selected
			}

			val hComponent = strategyComponents.value
			if (hComponent !== null) {
				initialiseControl.apply(hComponent, sd)
				hComponent.enabled = selected
			}
		]
	}
}
