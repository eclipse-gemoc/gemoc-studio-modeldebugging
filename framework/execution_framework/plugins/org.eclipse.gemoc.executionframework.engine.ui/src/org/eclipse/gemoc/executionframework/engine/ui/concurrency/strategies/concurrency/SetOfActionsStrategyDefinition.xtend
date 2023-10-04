package org.eclipse.gemoc.executionframework.engine.ui.concurrency.strategies.concurrency

import java.util.Set
import org.eclipse.gemoc.executionframework.engine.concurrency.strategies.Strategy
import org.eclipse.gemoc.executionframework.engine.concurrency.strategies.concurrency.SetOfActionsStrategy
import org.eclipse.gemoc.executionframework.engine.ui.concurrency.strategies.LaunchConfigurationContext
import org.eclipse.gemoc.executionframework.engine.ui.concurrency.strategies.StrategyControlUpdateListener
import org.eclipse.swt.SWT
import org.eclipse.swt.events.SelectionEvent
import org.eclipse.swt.events.SelectionListener
import org.eclipse.swt.layout.GridData
import org.eclipse.swt.widgets.Composite
import org.eclipse.swt.widgets.Control
import org.eclipse.swt.widgets.List

class SetOfActionsStrategyDefinition extends ConcurrencyStrategyDefinition {
	
	new() {
		super("uk.ac.kcl.inf.xdsml.strategies.set_of_actions", "Set Of Rules", SetOfActionsStrategy)
	}

	override getUIControl(Composite parent, LaunchConfigurationContext lcc, StrategyControlUpdateListener scul) {
		val control = new List(parent, SWT.MULTI.bitwiseOr(SWT.V_SCROLL).bitwiseOr(SWT.BORDER))
		control.layoutData = new GridData(SWT.FILL, SWT.CENTER, true, false)

		lcc.addSemanticsChangeListener([ evt |
			control.updateSemantics(evt.newValue as Set<String>)
		])

		control.updateSemantics(lcc.semantics)

		if (scul !== null) {
			control.addSelectionListener(new SelectionListener() {

				override widgetDefaultSelected(SelectionEvent e) {}

				override widgetSelected(SelectionEvent e) {
					scul.controlUpdated(SetOfActionsStrategyDefinition.this)
				}
			})
		}

		control
	}

	override encodeConfigInformation(Control uiElement) {
		val list = uiElement as List

		list.selectionIndices.map[i|list.items.get(i)].join("@@")
	}

	override initaliseControl(Control uiElement, String configData) {
		val list = uiElement as List
		if (list.items.size > 0) {
			val namesToSelect = configData.split("@@")

			list.select(#[0 .. list.itemCount - 1].flatten.filter[namesToSelect.contains(list.items.get(it))])
		}
	}

	override void initaliseControl(Control uiElement, Strategy strategy) {
		val list = uiElement as List
		list.setSelection(#[] as int[])

		if (strategy instanceof SetOfActionsStrategy) {
			list.selection = strategy.allowedActions.clone
		}
	}

	override initialise(Strategy strategy, String configData, LaunchConfigurationContext lcc) {
		if (strategy instanceof SetOfActionsStrategy) {	
			lcc.addSemanticsChangeListener([ evt |
				strategy.updateSemantics(evt.newValue as Set<String>, configData)
			])
	
			strategy.updateSemantics(lcc.semantics, configData)
		}
	}

	def updateSemantics(List control, Set<String> semantics) {
		control.items = emptyList

		if (semantics !== null) {
			semantics.forEach [ r |
				control.add(r)
			]
		}
	}

	def updateSemantics(SetOfActionsStrategy soas, Set<String> semantics, String configData) {
		soas.allowedActions.clear

		if (semantics !== null) {
			val actionNames = configData.split("@@").toList
			soas.allowedActions = semantics.filter[r|actionNames.contains(r)].toList
		}
	}
}
