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

import fr.inria.diverse.melange.resource.MelangeResourceImpl
import java.util.ArrayDeque
import java.util.ArrayList
import java.util.Deque
import java.util.Iterator
import java.util.List
import java.util.function.BiPredicate
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EcorePackage
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.gemoc.dsl.debug.ide.IDSLDebugger
import org.eclipse.gemoc.dsl.debug.ide.event.IDSLDebugEventProcessor
import org.eclipse.gemoc.executionframework.engine.core.EngineStoppedException
import org.eclipse.gemoc.trace.commons.model.helper.StepHelper
import org.eclipse.gemoc.trace.commons.model.trace.MSE
import org.eclipse.gemoc.trace.commons.model.trace.MSEOccurrence
import org.eclipse.gemoc.trace.commons.model.trace.ParallelStep
import org.eclipse.gemoc.trace.commons.model.trace.Step
import org.eclipse.gemoc.xdsmlframework.api.core.IExecutionEngine
import org.eclipse.xtext.naming.DefaultDeclarativeQualifiedNameProvider
import org.eclipse.gemoc.commons.eclipse.emf.EObjectUtil

class GenericSequentialModelDebugger extends AbstractGemocDebugger {

	/**
	 * A fake instruction to prevent the stepping return to stop on each event.
	 */
	static final EObject FAKE_INSTRUCTION = EcorePackage.eINSTANCE;

	List<ToPushPop> toPushPop = new ArrayList();

	protected final String threadName = "Model debugging";

	protected int nbStackFrames = 0;

	protected boolean executionTerminated = false;

	new(IDSLDebugEventProcessor target, IExecutionEngine<?> engine) {
		super(target, engine);
	}

	/*
	 * This method is eventually called within a new engine thread. (non-Javadoc)
	 * 
	 * @see IDSLDebugger#start()
	 */
	override start() {
		engine.start();
	}

	override disconnect() {
		return;
	}

	protected def void setupStepReturnPredicateBreak() {
		val IExecutionEngine<?> seqEngine = engine as IExecutionEngine<?>;
		val Deque<Step<?>> stack = seqEngine.getCurrentStack();
		if (stack.size() > 1) {
			val Iterator<Step<?>> it = stack.iterator();
			it.next();
			addPredicateBreak(new BiPredicate<IExecutionEngine<?>, Step<?>>() {
				// The operation we want to step return
				Step<?> steppedReturn = it.next();

				override test(IExecutionEngine<?> t, Step<?> u) {
					// We finished stepping over once the step is not
					// there anymore
					return !seqEngine.getCurrentStack().contains(steppedReturn);
				}
			});
		}
	}

	override steppingReturn(String threadName) {
		// To send notifications
		super.steppingReturn(threadName);
		// We add a future break as soon as the step is returned
		setupStepReturnPredicateBreak();
	}

	def protected setupStepOverPredicateBreak() {
		addPredicateBreak(new BiPredicate<IExecutionEngine<?>, Step<?>>() {
			val IExecutionEngine<?> seqEngine = engine as IExecutionEngine<?>;
			// The operation we want to step over
			Step<?> steppedOver = seqEngine.getCurrentStep();

			override test(IExecutionEngine<?> t, Step<?> u) {
				// We finished stepping over once the step is not there
				// anymore
				return !seqEngine.getCurrentStack().contains(steppedOver);
			}
		});
	}

	override steppingOver(String threadName) {
		// To send notifications
		super.steppingOver(threadName);
		// We add a future break as soon as the step is over
		setupStepOverPredicateBreak();
	}

	override canStepInto(String threadName, EObject instruction) {
		val EObject currentInstruction = currentInstructions.get(threadName);
		val Step<?> currentStep = engine.getCurrentStep();
		val boolean correctObject = currentInstruction == instruction;
		val boolean canStepInto = !(currentStep instanceof ParallelStep<?, ?>);
		return correctObject && canStepInto;
	}

	override steppingInto(String threadName) {
		// To send notifications, but probably useless
		super.steppingInto(threadName);
		// We add a future break asap
		addPredicateBreak(new BiPredicate<IExecutionEngine<?>, Step<?>>() {
			override test(IExecutionEngine<?> t, Step<?> u) {
				// We finished stepping as soon as we encounter a new step
				return true;
			}
		});
	}

	override pushStackFrame(String threadName, String frameName, EObject context, EObject instruction) {
		super.pushStackFrame(threadName, frameName, context, instruction);
		nbStackFrames++;
	}

	override popStackFrame(String threadName) {
		super.popStackFrame(threadName);
		nbStackFrames--;
	}

	protected val DefaultDeclarativeQualifiedNameProvider nameprovider = new DefaultDeclarativeQualifiedNameProvider();

	private def String prettyObjectName(Object o) {
		switch (o) {
			EObject:prettyObjectName(o)
			String: '''"«o»"'''
			default:
				o.toString
		}
	}
	
	private def String prettyObjectName(EObject o) {
		val typeName = o.eClass().getName()
		var String objectName
		val qn = nameprovider.getFullyQualifiedName(o)
		if(qn !== null) {
			objectName = qn.toString
		} else {
			val String resBasedName = EObjectUtil.getResourceBasedName(o, false);
			if( resBasedName !== null) {
				objectName = resBasedName
			} else {
				objectName = o.toString
			}
		}
		
		return '''[«typeName»] «objectName»'''
	}

	protected def String prettyFrameName(MSEOccurrence mseoccurrence, boolean implicit) {
		if (mseoccurrence !== null) {
			val mse = mseoccurrence.mse
			val String args = mseoccurrence.parameters.map[prettyObjectName].join(", ")
			return prettyFrameName(mse, implicit) + "(" + args + ")"
		}
	}

	protected def String prettyFrameName(MSE mse, boolean implicit) {
		if (mse !== null) {
			var EObject caller = mse.caller
			val String objectName = prettyObjectName(caller)
			val String opName = if (implicit) {
					mse.action?.name + "_implicitStep"
				} else {
					mse.action?.name
				}
			val String prettyName = objectName + "#" + opName
			return prettyName
		}
	}

	static class MSEFrameInformation {
		public val EObject caller;
		public val String prettyLabel;

		new(EObject caller, String prettyLabel) {
			this.caller = caller;
			this.prettyLabel = prettyLabel;
		}
	}

	protected def MSEFrameInformation getMSEFrameInformation(Step<?> step) {
		val mseOccurrence = step.mseoccurrence
		val container = step.eContainer
		var String prettyName = ""
		var EObject caller = null
		// If there is an MSE Occurrence, we show its info
		if (mseOccurrence !== null) {
			caller = mseOccurrence.mse.caller
			prettyName = prettyFrameName(mseOccurrence, false)
		} // If the step is contained in a step with an MSE, we show the parent MSE info + "implicit"
		else if (container !== null && container instanceof Step<?> && (container as Step<?>).mseoccurrence !== null) {
			val parentMSE = (container as Step<?>).mseoccurrence.mse
			caller = parentMSE.caller
			prettyName = prettyFrameName(parentMSE, true) + "()"
		} // If this is a ParallelStep, we show its internal steps in the label
		else if (step instanceof ParallelStep<?, ?>) {
			caller = step
			prettyName = ''' «StepHelper::getStepName(step)» («StepHelper::getMSEs(step).map([it.getName()]).join(", ")»)'''
		} // Else...
		else {
			caller = step
			prettyName = "Unknown step"
		}

		return new MSEFrameInformation(caller, prettyName)
	}

	override updateStack(String threadName, EObject instruction) {
		// Catching the stack up with events that occurred since last suspension
		// We use a virtual stack to replay the last events to avoid pushing
		// stackframes that would be popped right after.
		val Deque<Step<?>> virtualStack = new ArrayDeque();
		for (ToPushPop m : toPushPop) {
			if (m.push) {
				// We push the mse onto the virtual stack.
				virtualStack.push(m.step);
			} else {
				if (virtualStack.isEmpty()) {
					// The virtual stack is empty, we pop the top stackframe off
					// of the real stack.
					popStackFrame(threadName);
				} else {
					// The virtual stack is not empty, we pop the top stackframe
					// off of it.
					virtualStack.pop();
				}
			}
		}

		// We then push the missing stackframes onto the real stack.
		val Iterator<Step<?>> iterator = virtualStack.descendingIterator();
		while (iterator.hasNext()) {
			val Step<?> step = iterator.next();
			val info = getMSEFrameInformation(step);
			pushStackFrame(threadName, info.prettyLabel, info.caller, info.caller);
		}

		setCurrentInstruction(threadName, instruction);

		toPushPop.clear();
	}

	override updateData(String threadName, EObject instruction) {
		var EObject realInstruction = instruction;
		if (instruction === null) {
			updateVariables(threadName);
			updateStack(threadName, null);
			return;
		}

		// We don't want to deal with logical steps since we are in sequential mode
		if (instruction instanceof Step<?>) {
			val Step<?> step = instruction as Step<?>;
			if (step.getMseoccurrence() !== null) {
				realInstruction = step.getMseoccurrence().getMse().getCaller();
			}
		} else if (instruction instanceof MSEOccurrence) {
			realInstruction = instruction.getMse().getCaller();
		}

		super.updateData(threadName, realInstruction);
	}

	override shouldBreak(EObject instruction) {
		if (instruction instanceof Step<?>) {
			return shouldBreakStep(instruction as Step<?>);
		} else if (instruction == FAKE_INSTRUCTION) {
			// Part of the breakpoint simulation to suspend the execution once
			// the end has been reached.
			return true;
		}
		return false;
	}

	private def boolean hasRegularBreakpointTrue(EObject o) {
		var EObject target = o;
		// Try to get the original object if 'o' comes from
		// a downcast resource
		val Resource res = o.eResource();
		if (res !== null) {

			val MelangeResourceImpl mr = res.getResourceSet().getResources().filter(MelangeResourceImpl).head;

			if (mr !== null) {
				val String uriFragment = res.getURIFragment(o);
				target = mr.getWrappedResource().getEObject(uriFragment);
			}
		}

		return super.shouldBreak(target) &&
			(Boolean.valueOf(getBreakpointAttributes(target, GemocBreakpoint.BREAK_ON_LOGICAL_STEP) as String) ||
				Boolean.valueOf(getBreakpointAttributes(target, GemocBreakpoint.BREAK_ON_MSE_OCCURRENCE) as String)) // && checkBreakpointProperty(target, o)
		;

	}

	private def boolean shouldBreakStep(Step<?> step) {
		if (shouldBreakPredicates(engine, step))
			return true;
		if (step.getMseoccurrence() !== null) {

			// If still no break yet, we look at regular breakpoints on MSE
			val MSE mse = step.getMseoccurrence().getMse();
			if (hasRegularBreakpointTrue(mse)) {
				return true;
			}
			// If still no break yet, we look at regular breakpoints on MSE's caller
			val EObject caller = mse.getCaller();
			if (hasRegularBreakpointTrue(caller)) {
				return true;
			}
		}
		return false;
	}

	override getNextInstruction(String threadName, EObject currentInstruction, Stepping stepping) {
		// We always return fakeinstruction to make sure to not stop and to
		// handle everything with shouldBreak
		return FAKE_INSTRUCTION;
	}

	override engineStarted(IExecutionEngine<?> executionEngine) {
		spawnRunningThread(threadName, engine.getExecutionContext().getResourceModel().getContents().get(0));
	}

	override engineStopped(IExecutionEngine<?> engine) {
		if (!isTerminated(threadName)) {
			terminated(threadName);
		}
	}

	override aboutToExecuteStep(IExecutionEngine<?> executionEngine, Step<?> step) {
		val ToPushPop stackModification = new ToPushPop(step, true);
		toPushPop.add(stackModification);
		val boolean shallcontinue = control(threadName, step);
		if (!shallcontinue) {
			throw new EngineStoppedException("Debug thread has stopped.");
		}
	}

	override stepExecuted(IExecutionEngine<?> engine, Step<?> step) {
		val ToPushPop stackModification = new ToPushPop(step, false);
		toPushPop.add(stackModification);
	}

	override engineAboutToStop(IExecutionEngine<?> engine) {
		// Simulating breakpoint
		// TODO maybe display a warning informing the user the execution has
		// ended, as resuming execution will prevent further interactions with the
		// trace and the debugging facilities, which might not be desirable.
		executionTerminated = true;
		// does break the flow with the concurrent approach
		//control(threadName, FAKE_INSTRUCTION);
	}

	override terminate() {
		super.terminate();
		engine.stop();
	}

	private static class ToPushPop {
		public Step<?> step;
		public boolean push;

		new(Step<?> step, boolean push) {
			this.step = step;
			this.push = push;
		}
	}
}
