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
package org.eclipse.gemoc.executionframework.debugger;

import java.util.ArrayList
import java.util.List
import java.util.function.BiPredicate
import org.eclipse.core.runtime.IStatus
import org.eclipse.core.runtime.Status
import org.eclipse.emf.ecore.EObject
import org.eclipse.gemoc.dsl.debug.ide.event.IDSLDebugEventProcessor
import org.eclipse.gemoc.executionframework.engine.core.EngineStoppedException
import org.eclipse.gemoc.trace.commons.model.trace.Dimension
import org.eclipse.gemoc.trace.commons.model.trace.State
import org.eclipse.gemoc.trace.commons.model.trace.Step
import org.eclipse.gemoc.trace.commons.model.trace.TracedObject
import org.eclipse.gemoc.trace.commons.model.trace.Value
import org.eclipse.gemoc.trace.gemoc.api.IMultiDimensionalTraceAddon
import org.eclipse.gemoc.trace.gemoc.api.ITraceExplorer
import org.eclipse.gemoc.trace.gemoc.api.ITraceViewListener
import org.eclipse.gemoc.xdsmlframework.api.core.IExecutionEngine
import org.eclipse.jface.dialogs.ErrorDialog

class OmniscientGenericSequentialModelDebugger extends GenericSequentialModelDebugger implements ITraceViewListener {

	var ITraceExplorer<Step<?>, State<?, ?>, TracedObject<?>, Dimension<?>, Value<?>> traceExplorer

	var steppingOverStackFrameIndex = -1

	var steppingReturnStackFrameIndex = -1

	val List<EObject> callerStack = new ArrayList

	val List<Step<?>> previousCallStack = new ArrayList

	new(IDSLDebugEventProcessor target, IExecutionEngine<?> engine) {
		super(target, engine)
	}

	def private void pushStackFrame(String threadName, Step<?> step, EObject specificInstruction) {
		val info = getMSEFrameInformation(step);
		pushStackFrame(threadName, info.prettyLabel, info.caller, specificInstruction)
		callerStack.add(0, info.caller)
	}
	
	def private void pushStackFrame(String threadName, Step<?> step) {
		val info = getMSEFrameInformation(step);
		pushStackFrame(threadName, info.prettyLabel, info.caller, info.caller)
		callerStack.add(0, info.caller)
	}

	override void popStackFrame(String threadName) {
		super.popStackFrame(threadName)
		callerStack.remove(0)
	}

	override void aboutToExecuteStep(IExecutionEngine<?> executionEngine, Step<?> step) {
		val mseOccurrence = step.mseoccurrence
		if (mseOccurrence !== null) {
			val boolean shallContinue = control(threadName, step)
			if (! shallContinue) {
				throw new EngineStoppedException("Debug thread has stopped.");
			}
		}
	}

	override void resume() {
		if (!executionTerminated) {
			if (traceExplorer.inReplayMode) {
				traceExplorer.loadLastState
			}
			super.resume
		}
	}

	override void resume(String threadName) {
		if (!executionTerminated) {
			if (traceExplorer.inReplayMode) {
				traceExplorer.loadLastState
			}
			super.resume(threadName)
		}
	}

	override void terminate() {
		super.terminate()
		Activator.^default.debuggerSupplier = [|null]
	}

	override protected void setupStepOverPredicateBreak() {
		if (steppingOverStackFrameIndex != -1) {
			val seqEngine = engine as IExecutionEngine<?>
			val stack = traceExplorer.callStack
			val idx = stack.size - steppingOverStackFrameIndex - 1
			// We add a future break as soon as the step is over
			addPredicateBreak(new BiPredicate<IExecutionEngine<?>, Step<?>>() {
				// The operation we want to step over
				Step<?> steppedOver = stack.get(idx)

				override test(IExecutionEngine<?> t, Step<?> u) {
					return !seqEngine.getCurrentStack().contains(steppedOver)
				}
			})
			steppingOverStackFrameIndex = -1
		} else {
			super.setupStepOverPredicateBreak
		}
	}

	override void stepInto(String threadName) {
		if (traceExplorer.inReplayMode || executionTerminated) {
			if (!traceExplorer.stepInto && !executionTerminated) {
				traceExplorer.loadLastState
				super.stepInto(threadName)
			}
		} else {
			super.stepInto(threadName)
		}
	}

	override void stepOver(String threadName) {
		if (traceExplorer.inReplayMode || executionTerminated) {
			if (!traceExplorer.stepOver && !executionTerminated) {
				steppingOverStackFrameIndex = nbStackFrames - 1
				traceExplorer.loadLastState
				super.stepOver(threadName)
			}
		} else {
			super.stepOver(threadName)
		}
	}

	override protected void setupStepReturnPredicateBreak() {
		if (steppingReturnStackFrameIndex != -1) {
			val seqEngine = engine as IExecutionEngine<?>
			val stack = traceExplorer.callStack
			val idx = stack.size - steppingReturnStackFrameIndex - 1
			addPredicateBreak(new BiPredicate<IExecutionEngine<?>, Step<?>>() {
				Step<?> steppedReturn = stack.get(idx)

				override test(IExecutionEngine<?> t, Step<?> u) {
					return !seqEngine.getCurrentStack().contains(steppedReturn)
				}
			})
			steppingReturnStackFrameIndex = -1
		} else {
			super.setupStepReturnPredicateBreak
		}
	}

	override void stepReturn(String threadName) {
		if (traceExplorer.inReplayMode || executionTerminated) {
			if (!traceExplorer.stepReturn && !executionTerminated) {
				steppingReturnStackFrameIndex = nbStackFrames - 2
				traceExplorer.loadLastState
				super.stepReturn(threadName)
			}
		} else {
			super.stepReturn(threadName)
		}
	}

	def void stepBackInto() {
		traceExplorer.stepBackInto
	}

	def void stepBackOver() {
		traceExplorer.stepBackOver
	}

	def void stepBackOut() {
		traceExplorer.stepBackOut
	}

	def boolean canStepBackInto() {
		return traceExplorer.canStepBackInto
	}

	def boolean canStepBackOver() {
		return traceExplorer.canStepBackOver
	}

	def boolean canStepBackOut() {
		return traceExplorer.canStepBackOut
	}

//	def private setupStepValuePredicateBreak(IValueTrace valueTrace, int valueIndex) {
//		addPredicateBreak(new BiPredicate<IBasicExecutionEngine, MSEOccurrence>() {
//			override test(IBasicExecutionEngine t, MSEOccurrence u) {
//				val i = valueTrace.getActiveValueIndex(currentStateIndex)
//				val j = valueIndex
//				return i == j
//			}
//		});
//	}
	override validateVariableValue(String threadName, String variableName, String value) {
		if (traceExplorer.inReplayMode) {
			ErrorDialog.openError(null, "Illegal variable value set",
				"Cannot set the value of a variable when in replay mode",
				new Status(IStatus.ERROR, Activator.PLUGIN_ID, "Illegal variable value set"))
			return false
		}
		return super.validateVariableValue(threadName, variableName, value)
	}

	override void engineStarted(IExecutionEngine<?> executionEngine) {
		val Activator activator = Activator.getDefault()
		activator.debuggerSupplier = [this]
		super.engineStarted(executionEngine)
		val traceAddons = executionEngine.getAddonsTypedBy(IMultiDimensionalTraceAddon)
		val traceAddon = traceAddons.iterator().next()
		traceExplorer = traceAddon.getTraceExplorer()
		traceExplorer.registerCommand(this, [|update])
	}

	override void engineAboutToStop(IExecutionEngine<?> engine) {
		traceExplorer.loadLastState
		super.engineAboutToStop(engine)
	}

	override void engineStopped(IExecutionEngine<?> executionEngine) {
		val Activator activator = Activator.getDefault()
		activator.debuggerSupplier = null
		super.engineStopped(executionEngine)
	}

	override updateStack(String threadName, EObject instruction) {
		var i = 0
		while (i < previousCallStack.size && i < traceExplorer.callStack.size &&
			previousCallStack.get(i) == traceExplorer.callStack.get(i)) {
			i++
		}
		for (var j = i; j < previousCallStack.size; j++) {
			popStackFrame(threadName)
		}
		val callStackSize = traceExplorer.callStack.size
		for (var j = i; j < callStackSize; j++) {
			if (j>0) {
				// update parent frame current instruction before pushing new frame
				// this make sure to better distinguish context and current instruction
				val info = getMSEFrameInformation(traceExplorer.callStack.get(j))
				setCurrentInstruction(threadName, info.caller)
			}
			if(j < callStackSize -1){
				val childStep = traceExplorer.callStack.get(j+1)
				val childCaller = getMSEFrameInformation(childStep).caller;
				pushStackFrame(threadName, traceExplorer.callStack.get(j), childCaller)
			
			} else {	
				pushStackFrame(threadName, traceExplorer.callStack.get(j))
			}
			
			if (!callerStack.empty) {
				setCurrentInstruction(threadName, callerStack.get(0))
			}
		}
		previousCallStack.clear
		previousCallStack.addAll(traceExplorer.callStack)
	}

	override update() {
		if (executedModelRoot !== null) {
			try {
				if (!callerStack.empty) {
					updateData(threadName, callerStack.findFirst[true])
				} else {
				}
			} catch (IllegalStateException e) {
				// Shhh
			}
		}
	}
}
