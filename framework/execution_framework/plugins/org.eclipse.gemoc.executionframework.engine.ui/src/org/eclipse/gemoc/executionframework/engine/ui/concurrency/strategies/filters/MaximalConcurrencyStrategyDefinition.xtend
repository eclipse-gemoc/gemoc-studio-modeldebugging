package org.eclipse.gemoc.executionframework.engine.ui.concurrency.strategies.filters

import org.eclipse.debug.internal.ui.SWTFactory
import org.eclipse.gemoc.executionframework.engine.concurrency.strategies.Strategy
import org.eclipse.gemoc.executionframework.engine.concurrency.strategies.filters.MaximalConcurrencyStrategy
import org.eclipse.gemoc.executionframework.engine.ui.concurrency.strategies.LaunchConfigurationContext
import org.eclipse.gemoc.executionframework.engine.ui.concurrency.strategies.StrategyControlUpdateListener
import org.eclipse.swt.SWT
import org.eclipse.swt.events.SelectionEvent
import org.eclipse.swt.events.SelectionListener
import org.eclipse.swt.layout.GridData
import org.eclipse.swt.layout.GridLayout
import org.eclipse.swt.widgets.Button
import org.eclipse.swt.widgets.Composite
import org.eclipse.swt.widgets.Control
import org.eclipse.swt.widgets.Group

class MaximalConcurrencyStrategyDefinition extends FilteringStrategyDefinition {
	new() {
		super("uk.ac.kcl.inf.xdsml.strategies.max_concurrency", "Maximum concurrency", MaximalConcurrencyStrategy)
	}
	
	override getUIControl(Composite parent, LaunchConfigurationContext lcc, StrategyControlUpdateListener scul) {
		val control = createGroup(parent, "")
		control.layoutData = new GridData(SWT.FILL, SWT.CENTER, true, false)
		
		val keepAtomic = SWTFactory.createCheckButton(control, "Keep atomic steps?", null, false, 1)
		
		if (scul !== null) {
			val listener = new SelectionListener() {
			
							override widgetDefaultSelected(SelectionEvent e) {}
			
							override widgetSelected(SelectionEvent e) {
								scul.controlUpdated(MaximalConcurrencyStrategyDefinition.this)
							}
						}
			keepAtomic.addSelectionListener(listener)
		}

		control
	}
	
	private def Group createGroup(Composite parent, String text) {
		val group = new Group(parent, SWT.NULL)
		group.setText(text)

		val locationLayout = new GridLayout()
		locationLayout.numColumns = 5
		locationLayout.marginHeight = 10
		locationLayout.marginWidth = 10
		group.setLayout(locationLayout)

		group
	}

	override encodeConfigInformation(Control uiElement) {
		val group = uiElement as Group
		
		val keepAtomic = group.children.head as Button
		
		'''Atomic:«keepAtomic.selection»'''
	}

	override initaliseControl(Control uiElement, String configData) {
		val group = uiElement as Group
		
		val keepAtomic = group.children.head as Button

		keepAtomic.selection = (configData == "Atomic:true")
	}

	override void initaliseControl(Control uiElement, Strategy strategy) {
		val group = uiElement as Group
		
		val keepAtomic = group.children.head as Button

		if (strategy instanceof MaximalConcurrencyStrategy) {
			keepAtomic.selection = strategy.keepAtomicSteps
		}
	}

	override initialise(Strategy strategy, String configData, LaunchConfigurationContext lcc) {
		if (strategy instanceof MaximalConcurrencyStrategy) {	
			strategy.keepAtomicSteps = (configData == "Atomic:true")
		}
	}
}
