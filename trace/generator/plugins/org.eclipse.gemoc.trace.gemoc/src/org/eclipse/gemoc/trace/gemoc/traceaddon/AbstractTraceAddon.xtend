/*******************************************************************************
 * Copyright (c) 2016, 2017 Inria and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Inria - initial API and implementation
 *******************************************************************************/
package org.eclipse.gemoc.trace.gemoc.traceaddon

import com.google.common.collect.BiMap
import com.google.common.collect.HashBiMap
import java.util.ArrayList
import java.util.HashSet
import java.util.List
import java.util.Map
import java.util.Set
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl
import org.eclipse.emf.transaction.RecordingCommand
import org.eclipse.emf.transaction.util.TransactionUtil
import org.eclipse.gemoc.commons.eclipse.emf.EMFResource
import org.eclipse.gemoc.executionframework.engine.core.CommandExecution
import org.eclipse.gemoc.trace.commons.model.trace.Dimension
import org.eclipse.gemoc.trace.commons.model.trace.State
import org.eclipse.gemoc.trace.commons.model.trace.Step
import org.eclipse.gemoc.trace.commons.model.trace.Trace
import org.eclipse.gemoc.trace.commons.model.trace.TracedObject
import org.eclipse.gemoc.trace.commons.model.trace.Value
import org.eclipse.gemoc.trace.gemoc.api.IMultiDimensionalTraceAddon
import org.eclipse.gemoc.trace.gemoc.api.IStateManager
import org.eclipse.gemoc.trace.gemoc.api.ITraceConstructor
import org.eclipse.gemoc.trace.gemoc.api.ITraceExplorer
import org.eclipse.gemoc.trace.gemoc.api.ITraceExtractor
import org.eclipse.gemoc.trace.gemoc.api.ITraceNotifier
import org.eclipse.gemoc.xdsmlframework.api.core.IExecutionContext
import org.eclipse.gemoc.xdsmlframework.api.core.IExecutionEngine
import org.eclipse.gemoc.xdsmlframework.api.engine_addon.IEngineAddon
import org.eclipse.gemoc.xdsmlframework.api.engine_addon.modelchangelistener.BatchModelChangeListener
import org.eclipse.gemoc.xdsmlframework.api.extensions.engine_addon.EngineAddonSpecificationExtensionPoint

abstract class AbstractTraceAddon implements IEngineAddon, IMultiDimensionalTraceAddon<Step<?>, State<?, ?>, TracedObject<?>, Dimension<?>, Value<?>> {

	IExecutionContext<?, ?, ?> _executionContext
	ITraceExplorer<Step<?>, State<?, ?>, TracedObject<?>, Dimension<?>, Value<?>> traceExplorer
	ITraceExtractor<Step<?>, State<?, ?>, TracedObject<?>, Dimension<?>, Value<?>> traceExtractor
	ITraceConstructor traceConstructor
	ITraceNotifier traceNotifier
	BatchModelChangeListener traceListener
	var boolean needTransaction = true
	BatchModelChangeListener listenerAddon
	Trace<Step<?>, TracedObject<?>, State<?, ?>> trace
	
	
	protected boolean activateUpdateEquivalenceClasses = true;

	protected abstract def ITraceConstructor constructTraceConstructor(Resource modelResource, Resource traceResource,
		Map<EObject, TracedObject<?>> exeToTraced)

	protected abstract def IStateManager<State<?, ?>> constructStateManager(Resource modelResource,
		Map<TracedObject<?>, EObject> tracedToExe)

	override getTraceExplorer() {
		return traceExplorer
	}

	override getTraceConstructor() {
		return traceConstructor
	}

	override getTraceExtractor() {
		return traceExtractor
	}

	override getTraceNotifier() {
		return traceNotifier
	}

	override void load(Resource traceResource) {
		val root = traceResource.contents.head
		if (root instanceof Trace<?, ?, ?>) {
			trace = root as Trace<Step<?>, TracedObject<?>, State<?, ?>>
			traceExplorer = new GenericTraceExplorer(trace)
			traceExtractor = new GenericTraceExtractor(trace, activateUpdateEquivalenceClasses)
		} else {
			traceExplorer = null
			traceExtractor = null
		}
	}

	private static def String getEPackageFQN(EPackage p, String separator) {
		val EPackage superP = p.getESuperPackage
		if (superP !== null) {
			return getEPackageFQN(superP, separator) + separator + p.name
		} else {
			return p.name.toFirstUpper
		}
	}

	override aboutToExecuteStep(IExecutionEngine<?> executionEngine, Step<?> step) {
		manageStep(step, true)
	}

	override stepExecuted(IExecutionEngine<?> engine, Step<?> step) {
		manageStep(step, false)
	}

	private def manageStep(Step<?> step, boolean add) {
		if (step !== null) {
			modifyTrace([
				traceConstructor.addState(listenerAddon.getChanges(this))
				if (add) {
					traceConstructor.addStep(step)
				} else {
					traceConstructor.endStep(step)
				}
				// Updating the trace extractor and explorer with the last changes
				traceNotifier.notifyListener(traceExtractor)
				traceNotifier.notifyListener(traceExplorer)
				// Updating other trace listeners with the last changes
				traceNotifier.notifyListeners
				// Updating the state of the trace explorer
				traceExplorer.updateCallStack(step)
			])
			/*try {
				traceConstructor.save()
			} catch (Throwable t) {
			}*/
		}
	}

	/**
	 * To construct the trace manager
	 */
	override engineAboutToStart(IExecutionEngine<?> engine) {
		if (_executionContext === null) {
			_executionContext = engine.executionContext
			
			
			// load addon options from the execution context
			this.activateUpdateEquivalenceClasses = _executionContext.runConfiguration.getAttribute("org.eclipse.gemoc.trace.gemoc.addon_booleanOption", false);

			val modelResource = _executionContext.resourceModel

			// Creating the resource of the trace
			// val ResourceSet rs = modelResource.getResourceSet()
			val ResourceSet rs = new ResourceSetImpl

			// We check whether or not we need transactions
			val ed = TransactionUtil.getEditingDomain(rs)
			needTransaction = ed !== null

			val URI traceModelURI = URI.createPlatformResourceURI(
				_executionContext.getWorkspace().getExecutionPath().toString() + "/execution.trace", false)
			val Resource traceResource = rs.createResource(traceModelURI)

			// We construct a new listener addon if required
			this.listenerAddon = new BatchModelChangeListener(
				EMFResource.getRelatedResources(engine.executionContext.resourceModel))
			listenerAddon.registerObserver(this)

			val launchConfiguration = engine.extractLaunchConfiguration

			val BiMap<EObject, TracedObject<?>> exeToTraced = HashBiMap.create

			// We construct the trace constructor, using the concrete generated method
			traceConstructor = constructTraceConstructor(modelResource, traceResource, exeToTraced)

			// We initialize the trace
			modifyTrace([traceConstructor.initTrace(launchConfiguration)])

			// And we enable trace exploration by loading it in a new trace explorer
			val root = traceResource.contents.head
			if (root instanceof Trace<?, ?, ?>) {
				trace = root as Trace<Step<?>, TracedObject<?>, State<?, ?>>
				val stateManager = constructStateManager(modelResource, exeToTraced.inverse)
				traceExplorer = new GenericTraceExplorer(trace, stateManager)
				traceExtractor = new GenericTraceExtractor(trace, activateUpdateEquivalenceClasses)
				traceListener = new BatchModelChangeListener(EMFResource.getRelatedResources(traceResource))
				traceNotifier = new GenericTraceNotifier(traceListener)
				traceNotifier.addListener(traceExtractor)
				traceNotifier.addListener(traceExplorer)
			}
		}
	}

	/**
	 * Wrapper using lambda to always use a RecordingCommand when modifying the trace
	 */
	private def void modifyTrace(Runnable r, String message) {
		if (needTransaction) {
			val ed = TransactionUtil.getEditingDomain(_executionContext.resourceModel)
			val Set<Throwable> catchedException = new HashSet
			var RecordingCommand command = new RecordingCommand(ed, message) {
				protected override void doExecute() {
					try {
						r.run
					} catch (Throwable t) {
						catchedException.add(t)
					}
				}
			}
			CommandExecution.execute(ed, command)
			if (!catchedException.empty)
				throw catchedException.head
		} else {
			r.run
		}
	}

	/**
	 * Same as above, but without message.
	 */
	private def void modifyTrace(Runnable r) {
		modifyTrace(r, "")
	}

	public override List<String> validate(List<IEngineAddon> otherAddons) {

		val ArrayList<String> errors = new ArrayList<String>()

		var boolean found = false
		var addonName = ""
		for (IEngineAddon iEngineAddon : otherAddons) {
			if (iEngineAddon instanceof AbstractTraceAddon && iEngineAddon !== this) {
				found = true
				addonName = EngineAddonSpecificationExtensionPoint.getName(iEngineAddon)
			}
		}

		if (found) {
			val thisName = EngineAddonSpecificationExtensionPoint.getName(this)
			errors.add(thisName + " can't run with " + addonName)
		}

		return errors
	}

	override getTrace() {
		return trace
	}
}
