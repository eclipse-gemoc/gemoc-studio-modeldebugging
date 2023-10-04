package org.eclipse.gemoc.executionframework.engine.ui.concurrency.strategies.concurrency

import org.eclipse.debug.internal.ui.SWTFactory
import org.eclipse.gemoc.executionframework.engine.concurrency.strategies.Strategy
import org.eclipse.gemoc.executionframework.engine.concurrency.strategies.concurrency.OverlapStrategy
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

class OverlapStrategyDefinition extends ConcurrencyStrategyDefinition {
	new() {
		super("uk.ac.kcl.inf.xdsml.strategies.overlap", "Overlap", OverlapStrategy)
	}
	
	override getUIControl(Composite parent, LaunchConfigurationContext lcc, StrategyControlUpdateListener scul) {
		val control = createGroup(parent, "")
		control.layoutData = new GridData(SWT.FILL, SWT.CENTER, true, false)
		
		val fullOverlap = SWTFactory.createCheckButton(control, "Full overlap?", null, false, 1)
		val onlyStatic = SWTFactory.createCheckButton(control, "Static overlap only?", null, false, 1)
		
		if (scul !== null) {
			val listener = new SelectionListener() {
			
							override widgetDefaultSelected(SelectionEvent e) {}
			
							override widgetSelected(SelectionEvent e) {
								scul.controlUpdated(OverlapStrategyDefinition.this)
							}
						}
			fullOverlap.addSelectionListener(listener)
			onlyStatic.addSelectionListener(listener)
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
		
		val fullOverlap = group.children.head as Button
		val onlyStatic = group.children.get(1) as Button
		
		'''Full:«fullOverlap.selection»@@Static:«onlyStatic.selection»'''
	}

	override initaliseControl(Control uiElement, String configData) {
		val group = uiElement as Group
		
		val fullOverlap = group.children.head as Button
		val onlyStatic = group.children.get(1) as Button

		val data = configData.split("@@")
		if (data.size === 2) {
			fullOverlap.selection = (data.get(0) == "Full:true")
			onlyStatic.selection = (data.get(1) == "Static:true")		
		} else {
			fullOverlap.selection = false 
			onlyStatic.selection = false
		}
	}

	override void initaliseControl(Control uiElement, Strategy strategy) {
		val group = uiElement as Group
		
		val fullOverlap = group.children.head as Button
		val onlyStatic = group.children.get(1) as Button

		if (strategy instanceof OverlapStrategy) {
			fullOverlap.selection = strategy.requireFullOverlap
			onlyStatic.selection = strategy.requireOnlyStaticOverlap
		}
	}

	override initialise(Strategy strategy, String configData, LaunchConfigurationContext lcc) {
		if (strategy instanceof OverlapStrategy) {	
			val data = configData.split("@@")
			
			if (data.size === 2) {
				strategy.requireFullOverlap = (data.get(0) == "Full:true")
				strategy.requireOnlyStaticOverlap = (data.get(1) == "Static:true")			
			} else {
				strategy.requireFullOverlap = false
				strategy.requireOnlyStaticOverlap = false				
			}
		}
	}
}
