package org.eclipse.gemoc.executionframework.engine.ui.concurrency.strategies.filters

import java.util.Set
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EPackage
import org.eclipse.gemoc.executionframework.engine.concurrency.strategies.Strategy
import org.eclipse.gemoc.executionframework.engine.concurrency.strategies.filters.ForceEventPresenceStrategy
import org.eclipse.gemoc.executionframework.engine.concurrency.strategies.filters.NonIdentityElementsStrategy
import org.eclipse.gemoc.executionframework.engine.ui.concurrency.strategies.LaunchConfigurationContext
import org.eclipse.gemoc.executionframework.engine.ui.concurrency.strategies.StrategyControlUpdateListener
import org.eclipse.swt.SWT
import org.eclipse.swt.events.SelectionEvent
import org.eclipse.swt.events.SelectionListener
import org.eclipse.swt.layout.GridData
import org.eclipse.swt.widgets.Composite
import org.eclipse.swt.widgets.Control
import org.eclipse.swt.widgets.List

class ForceEventPresenceStrategyDefinition extends FilteringStrategyDefinition {
	new() {
		super("org.eclipse.gemoc.xdsml.strategies.keep_only_wanted", "Force Presence",
			ForceEventPresenceStrategy)
	}

	override getUIControl(Composite parent, LaunchConfigurationContext lcc, StrategyControlUpdateListener scul) {
		val control = new List(parent, SWT.MULTI.bitwiseOr(SWT.V_SCROLL).bitwiseOr(SWT.BORDER))
		control.layoutData = new GridData(SWT.FILL, SWT.CENTER, true, false)

		lcc.addMetamodelChangeListener([ evt |
			control.updateMetamodels(evt.newValue as Set<EPackage>)
		])

		control.updateMetamodels(lcc.metamodels)
		
		if (scul !== null) {
			control.addSelectionListener(new SelectionListener() {
				
				override widgetDefaultSelected(SelectionEvent e) { }
				
				override widgetSelected(SelectionEvent e) {
					scul.controlUpdated(ForceEventPresenceStrategyDefinition.this)
				}				
			})
		}

		control
	}

	override initaliseControl(Control uiElement, String configData) {
		val list = uiElement as List
		if (list.items.size > 0) {
			val namesToSelect = configData.split("@@")

			list.select(#[0..list.itemCount-1].flatten.filter[namesToSelect.contains(list.items.get(it))])
		}
	}

	override void initaliseControl(Control uiElement, Strategy strategy) {
		val list = uiElement as List
		list.setSelection(#[] as int[])
		
		if (strategy instanceof NonIdentityElementsStrategy) {
			list.selection = strategy.nonIdentityTypes.map[name]
		}
	}

	override encodeConfigInformation(Control uiElement) {
		val list = uiElement as List

		list.selectionIndices.map[i | list.items.get(i)].join("@@")
	}

	override initialise(Strategy strategy, String configData, LaunchConfigurationContext lcc) {
		if (strategy instanceof ForceEventPresenceStrategy) {
			lcc.addMetamodelChangeListener([ evt |
				strategy.updateMetamodels(evt.newValue as Set<EPackage>, configData)
			])
			
			strategy.updateMetamodels(lcc.metamodels as Set<EPackage>, configData)			
		}
	}

	def updateMetamodels(List control, Set<EPackage> metamodels) {
		control.items = emptyList

		if (metamodels !== null) {
			metamodels.flatMap[mm|mm.eAllContents.filter(EClass).toIterable].forEach [ c |
				control.add(c.name)
			]
		}
	}

	def updateMetamodels(ForceEventPresenceStrategy nieh, Set<EPackage> metamodels, String configData) {
		nieh.toBePresentTypes.clear
		
		if (metamodels !== null) {
			val classNames = configData.split("@@").toList
			nieh.toBePresentTypes = metamodels.flatMap[ep | ep.eAllContents.filter(EClass).filter[ec | classNames.contains(ec.name)].toIterable].toList
		}
	}
}
