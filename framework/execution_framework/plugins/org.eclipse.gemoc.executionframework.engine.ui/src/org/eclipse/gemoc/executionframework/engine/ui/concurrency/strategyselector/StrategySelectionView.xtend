
package org.eclipse.gemoc.executionframework.engine.ui.concurrency.strategyselector

import java.beans.PropertyChangeListener
import java.beans.PropertyChangeSupport
import java.util.ArrayList
import java.util.List
import java.util.Set
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EPackage
import org.eclipse.gemoc.executionframework.engine.concurrency.AbstractConcurrentExecutionEngine
import org.eclipse.gemoc.executionframework.engine.concurrency.strategies.ConcurrencyStrategy
import org.eclipse.gemoc.executionframework.engine.concurrency.strategies.Strategy
import org.eclipse.gemoc.executionframework.engine.ui.concurrency.strategies.LaunchConfigurationContext
import org.eclipse.gemoc.executionframework.engine.ui.concurrency.strategies.StrategyDefinition
import org.eclipse.gemoc.executionframework.ui.views.engine.EngineSelectionDependentViewPart
import org.eclipse.gemoc.xdsmlframework.api.core.IExecutionEngine
import org.eclipse.swt.SWT
import org.eclipse.swt.layout.GridData
import org.eclipse.swt.layout.GridLayout
import org.eclipse.swt.widgets.Composite
import org.eclipse.swt.widgets.Control
import org.eclipse.swt.widgets.Label
import org.eclipse.xtend.lib.annotations.Accessors

class StrategySelectionView extends EngineSelectionDependentViewPart implements StrategyConfigurationUpdateListener {

	public static val ID = "org.eclipse.gemoc.executionframework.engine.io.views.StrategySelectionView"

	var StrategySelectionControl strategyControl

	private static class EngineWrappingLaunchConfigurationContext implements LaunchConfigurationContext {
		val pcs = new PropertyChangeSupport(this)

		@Accessors(PUBLIC_GETTER)
		var Set<String> semantics = emptySet
		@Accessors(PUBLIC_GETTER)
		var Set<EPackage> metamodels = emptySet
		var EObject modelRoot= null

		@Accessors(PUBLIC_GETTER)
		var IExecutionEngine<?> engine = null

		def setEngine(IExecutionEngine<?> engine) {
			this.engine = engine
			try {
				val oldSemantics = semantics
				val oldmms = metamodels

				if (engine instanceof AbstractConcurrentExecutionEngine<?, ?>) {
					semantics = engine.semanticRules
					metamodels = engine.abstractSyntax
					modelRoot = engine.executionContext.resourceModel.contents.get(0)
				} else {
					semantics = emptySet
					metamodels = emptySet
					modelRoot = null
				}

				pcs.firePropertyChange(SEMANTICS, oldSemantics, semantics)
				if (oldmms !== metamodels) {
					pcs.firePropertyChange(METAMODELS, oldmms, metamodels)
				}
			} catch (Exception e) {
				e.printStackTrace
			}
		}

		override addMetamodelChangeListener(PropertyChangeListener pcl) {
			pcs.addPropertyChangeListener(METAMODELS, pcl)
		}

		override addSemanticsChangeListener(PropertyChangeListener pcl) {
			pcs.addPropertyChangeListener(SEMANTICS, pcl)
		}
		
		override getModelRoot() {
			return modelRoot
		}
		
	}

	/**
	 *  Engine wrapper that ignores requests to send update events. 
	 * 
	 * To be used when updating strategies from the selection in the view so that they don't get accidentally updated whenever the engine 
	 * is switched.
	 */
	private static class NonNotifyingEngineWrappingLaunchConfigurationContext implements LaunchConfigurationContext {
		val EngineWrappingLaunchConfigurationContext delegate

		new(EngineWrappingLaunchConfigurationContext delegate) {
			this.delegate = delegate
		}

		override getMetamodels() {
			delegate.metamodels
		}

		override addMetamodelChangeListener(PropertyChangeListener pcl) {
			// Ignore
		}

		override getSemantics() {
			delegate.getSemantics
		}

		override addSemanticsChangeListener(PropertyChangeListener pcl) {
			// Ignore
		}
		
		override getModelRoot() {
			delegate.modelRoot
		}
		
		override getEngine() {
			return delegate.engine
		}
		
		

	}

	val configContext = new EngineWrappingLaunchConfigurationContext

	override createPartControl(Composite parent) {
//		val content = new Composite(parent, SWT.NULL)
		val gl = new GridLayout(1, false)
		gl.marginHeight = 0
		parent.layout = gl

		parent.layoutData = new GridData(SWT.FILL, SWT.FILL, true, true)

		createLayout(parent)

		parent.layout()
	}

	private def createLayout(Composite parent) {
		createTextLabelLayout(parent, "Update strategy selection below. This will take effect from the next step.")

		strategyControl = new StrategySelectionControl(parent, configContext)
		strategyControl.layoutData =  new GridData(SWT.FILL, SWT.FILL, true, true)
		strategyControl.updateListener = this
	}

	protected def createTextLabelLayout(Composite parent, String labelString) {
		val gd = new GridData(GridData.FILL_HORIZONTAL)
		parent.setLayoutData(gd)
		val label = new Label(parent, SWT.NONE)
		label.setText(labelString)
	}

	override setFocus() {}

	override engineSelectionChanged(IExecutionEngine<?> engine) {
		configContext.setEngine(engine) // need to explictly call setter because Xtend is stupid
		if (engine instanceof AbstractConcurrentExecutionEngine) {
			strategyControl.updateSelectionsFrom(engine)
		}
	}

	override onStrategyConfigurationHasChanged(StrategyDefinition sd, boolean selected, Control control) {
		if (configContext.engine !== null) {
			if (configContext.engine instanceof AbstractConcurrentExecutionEngine) {
				val engine = configContext.engine as AbstractConcurrentExecutionEngine

				val List<Strategy> strategies = new ArrayList
				strategies.addAll(engine.concurrencyStrategies)
				strategies.addAll(engine.filteringStrategies)

				strategies.filter[sd.isStrategyInstance (it)].forEach [
					if (it instanceof ConcurrencyStrategy) {
						engine.concurrencyStrategies.remove(it)
					} else {
						engine.filteringStrategies.remove(it)
					}
				]

				if (selected) {
					val strategy = sd.instantiate
					sd.initialise(strategy, sd.encodeConfigInformation(control),
						new NonNotifyingEngineWrappingLaunchConfigurationContext(configContext))

					if (strategy instanceof ConcurrencyStrategy) {
						engine.concurrencyStrategies += strategy
					} else {
						engine.filteringStrategies += strategy
					}
				}
			}
		}
	}
}
