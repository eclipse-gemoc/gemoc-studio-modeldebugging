package org.eclipse.gemoc.executionframework.engine.ui.concurrency.strategies.filters

import org.eclipse.gemoc.executionframework.engine.concurrency.strategies.Strategy
import org.eclipse.gemoc.executionframework.engine.concurrency.strategies.filters.MaxNumberOfStepsStrategy
import org.eclipse.gemoc.executionframework.engine.ui.concurrency.strategies.LaunchConfigurationContext
import org.eclipse.gemoc.executionframework.engine.ui.concurrency.strategies.StrategyControlUpdateListener
import org.eclipse.swt.SWT
import org.eclipse.swt.layout.GridData
import org.eclipse.swt.widgets.Composite
import org.eclipse.swt.widgets.Control
import org.eclipse.swt.widgets.Text

class MaxNumberOfStepsStrategyDefinition extends FilteringStrategyDefinition {
	new() {
		super("uk.ac.kcl.inf.xdsml.strategies.num_steps", "Concurrency limit", MaxNumberOfStepsStrategy)
	}

	override getUIControl(Composite parent, LaunchConfigurationContext lcc, StrategyControlUpdateListener scul) {
		val control = new Text(parent, SWT.SINGLE.bitwiseOr(SWT.BORDER))
		control.layoutData = new GridData(SWT.FILL, SWT.CENTER, true, false)

		if (scul !== null) {
			control.addModifyListener[scul.controlUpdated(this)]
		}

		control
	}

	override initaliseControl(Control uiElement, String configData) {
		val txt = uiElement as Text

		try {
			val num = Integer.parseInt(configData)
			txt.text = num.toString
		} catch (NumberFormatException nfe) {
			txt.text = "0"
		}
	}

	override void initaliseControl(Control uiElement, Strategy strategy) {
		val txt = uiElement as Text
		txt.text = ""

		if (strategy instanceof MaxNumberOfStepsStrategy) {
			txt.text = strategy.maxNumberOfSteps.toString
		}
	}

	override encodeConfigInformation(Control uiElement) {
		val txt = uiElement as Text

		try {
			val num = Integer.parseInt(txt.text)
			num.toString
		} catch (NumberFormatException nfe) {
			"0"
		}
	}

	override initialise(Strategy strategy, String configData, LaunchConfigurationContext lcc) {
		if (strategy instanceof MaxNumberOfStepsStrategy) {
			try {
				val num = Integer.parseInt(configData)
				strategy.maxNumberOfSteps = num
			} catch (NumberFormatException nfe) {
				System.err.println("Couldn't initalise strategy: " + nfe.message)
				nfe.printStackTrace
			}
		}
	}
}
