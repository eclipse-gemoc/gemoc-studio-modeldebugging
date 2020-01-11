/*******************************************************************************
 * Copyright (c) 2015, 2017 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.gemoc.dsl.debug.ide.adapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IMarkerDelta;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.DebugEvent;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.debug.core.model.IMemoryBlock;
import org.eclipse.debug.core.model.IProcess;
import org.eclipse.debug.core.model.IThread;
import org.eclipse.emf.common.util.URI;
import org.eclipse.gemoc.dsl.debug.DebugTarget;
import org.eclipse.gemoc.dsl.debug.DebugTargetState;
import org.eclipse.gemoc.dsl.debug.DebugTargetUtils;
import org.eclipse.gemoc.dsl.debug.StackFrame;
import org.eclipse.gemoc.dsl.debug.Thread;
import org.eclipse.gemoc.dsl.debug.Variable;
import org.eclipse.gemoc.dsl.debug.ide.Activator;
import org.eclipse.gemoc.dsl.debug.ide.DSLBreakpoint;
import org.eclipse.gemoc.dsl.debug.ide.DSLEclipseDebugIntegration;
import org.eclipse.gemoc.dsl.debug.ide.event.IDSLDebugEvent;
import org.eclipse.gemoc.dsl.debug.ide.event.IDSLDebugEventProcessor;
import org.eclipse.gemoc.dsl.debug.ide.event.debugger.BreakpointReply;
import org.eclipse.gemoc.dsl.debug.ide.event.debugger.DeleteVariableReply;
import org.eclipse.gemoc.dsl.debug.ide.event.debugger.PopStackFrameReply;
import org.eclipse.gemoc.dsl.debug.ide.event.debugger.PushStackFrameReply;
import org.eclipse.gemoc.dsl.debug.ide.event.debugger.ResumingReply;
import org.eclipse.gemoc.dsl.debug.ide.event.debugger.SetCurrentInstructionReply;
import org.eclipse.gemoc.dsl.debug.ide.event.debugger.SetVariableValueReply;
import org.eclipse.gemoc.dsl.debug.ide.event.debugger.SpawnRunningThreadReply;
import org.eclipse.gemoc.dsl.debug.ide.event.debugger.StepIntoResumingReply;
import org.eclipse.gemoc.dsl.debug.ide.event.debugger.StepOverResumingReply;
import org.eclipse.gemoc.dsl.debug.ide.event.debugger.StepReturnResumingReply;
import org.eclipse.gemoc.dsl.debug.ide.event.debugger.SteppedReply;
import org.eclipse.gemoc.dsl.debug.ide.event.debugger.SuspendedReply;
import org.eclipse.gemoc.dsl.debug.ide.event.debugger.TerminatedReply;
import org.eclipse.gemoc.dsl.debug.ide.event.debugger.VariableReply;
import org.eclipse.gemoc.dsl.debug.ide.event.model.AddBreakpointRequest;
import org.eclipse.gemoc.dsl.debug.ide.event.model.ChangeBreakPointRequest;
import org.eclipse.gemoc.dsl.debug.ide.event.model.DisconnectRequest;
import org.eclipse.gemoc.dsl.debug.ide.event.model.RemoveBreakpointRequest;
import org.eclipse.gemoc.dsl.debug.ide.event.model.ResumeRequest;
import org.eclipse.gemoc.dsl.debug.ide.event.model.StartRequest;
import org.eclipse.gemoc.dsl.debug.ide.event.model.SuspendRequest;
import org.eclipse.gemoc.dsl.debug.ide.event.model.TerminateRequest;

/**
 * The {@link DebugTarget} DSL debug model.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class DSLDebugTargetAdapter extends AbstractDSLDebugElementAdapter implements IDebugTarget, IDSLDebugEventProcessor {

	/**
	 * The {@link List} of {@link IDSLSuspendListener} to notify.
	 */
	protected final List<IDSLCurrentInstructionListener> currentInstructionListeners = new ArrayList<IDSLCurrentInstructionListener>();

	/**
	 * Constructor.
	 * 
	 * @param factory
	 *            the {@link DSLEclipseDebugIntegration} factory
	 */
	public DSLDebugTargetAdapter(DSLEclipseDebugIntegration factory) {
		super(factory);
	}

	/**
	 * Starts the debugger with a {@link StartRequest}.
	 */
	public void start() {
		// register as a breakpoint listener
		DebugPlugin.getDefault().getBreakpointManager().addBreakpointListener(this);

		// add current break points
		IBreakpoint[] breakpoints = DebugPlugin.getDefault().getBreakpointManager().getBreakpoints(
				getModelIdentifier());
		for (IBreakpoint breakpoint : breakpoints) {
			breakpointAdded(breakpoint);
		}
		factory.getDebugger().handleEvent(new StartRequest());
	}

	@Override
	public boolean isAdapterForType(Object type) {
		return type == IDebugTarget.class || super.isAdapterForType(type);
	}

	/**
	 * Gets the {@link DebugTarget}.
	 * 
	 * @return the {@link DebugTarget}
	 */
	protected DebugTarget getHost() {
		assert target instanceof DebugTarget;
		return (DebugTarget)target;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.debug.core.model.ITerminate#canTerminate()
	 */
	public boolean canTerminate() {
		return DebugTargetUtils.canTerminate(getHost());
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.debug.core.model.ITerminate#isTerminated()
	 */
	public boolean isTerminated() {
		return DebugTargetUtils.isTerminated(getHost());
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.debug.core.model.ITerminate#terminate()
	 */
	public void terminate() throws DebugException {
		factory.getModelUpdater().terminateRequest(getHost());
		factory.getDebugger().handleEvent(new TerminateRequest());
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.debug.core.model.ISuspendResume#canResume()
	 */
	public boolean canResume() {
		return DebugTargetUtils.canResume(getHost());
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.debug.core.model.ISuspendResume#canSuspend()
	 */
	public boolean canSuspend() {
		return DebugTargetUtils.canSuspend(getHost());
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.debug.core.model.ISuspendResume#isSuspended()
	 */
	public boolean isSuspended() {
		return DebugTargetUtils.isSuspended(getHost());
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.debug.core.model.ISuspendResume#resume()
	 */
	public void resume() throws DebugException {
		factory.getDebugger().handleEvent(new ResumeRequest());
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.debug.core.model.ISuspendResume#suspend()
	 */
	public void suspend() throws DebugException {
		factory.getDebugger().handleEvent(new SuspendRequest());
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.debug.core.IBreakpointListener#breakpointAdded(org.eclipse.debug.core.model.IBreakpoint)
	 */
	public void breakpointAdded(IBreakpoint breakpoint) {
		if (supportsBreakpoint(breakpoint)) {
			try {
				if (breakpoint.isEnabled()) {
					URI uri = ((DSLBreakpoint)breakpoint).getURI();
					factory.getDebugger().handleEvent(new AddBreakpointRequest(uri));
					try {
						for (Entry<String, Object> entry : breakpoint.getMarker().getAttributes()
								.entrySet()) {
							factory.getDebugger().handleEvent(new ChangeBreakPointRequest(
									((DSLBreakpoint)breakpoint).getURI(), entry.getKey(), (Serializable)entry
											.getValue()));
						}
					} catch (CoreException e) {
						Activator.getDefault().error(e);
					}
				}
			} catch (CoreException e) {
				Activator.getDefault().error(e);
			}
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.debug.core.IBreakpointListener#breakpointRemoved(org.eclipse.debug.core.model.IBreakpoint,
	 *      org.eclipse.core.resources.IMarkerDelta)
	 */
	public void breakpointRemoved(IBreakpoint breakpoint, IMarkerDelta delta) {
		if (supportsBreakpoint(breakpoint)) {
			try {
				if (breakpoint.isEnabled()) {
					// TODO EMF representation of breakpoints ?
					final URI uri = ((DSLBreakpoint)breakpoint).getURI();
					factory.getDebugger().handleEvent(new RemoveBreakpointRequest(uri));
				}
			} catch (CoreException e) {
				Activator.getDefault().error(e);
			}
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.debug.core.IBreakpointListener#breakpointChanged(org.eclipse.debug.core.model.IBreakpoint,
	 *      org.eclipse.core.resources.IMarkerDelta)
	 */
	public void breakpointChanged(IBreakpoint breakpoint, IMarkerDelta delta) {
		if (supportsBreakpoint(breakpoint)) {
			try {
				final URI uri = ((DSLBreakpoint)breakpoint).getURI();
				final IMarker marker = breakpoint.getMarker();
				for (Entry<String, Object> entry : delta.getAttributes().entrySet()) {
					final Object markerValue = marker.getAttribute(entry.getKey());
					final Object deltaValue = entry.getValue();
					if ((markerValue != null && !markerValue.equals(deltaValue)) || (deltaValue != null
							&& !deltaValue.equals(markerValue))) {
						if (delta.getKind() == IResourceDelta.ADDED) {
							factory.getDebugger().handleEvent(new ChangeBreakPointRequest(uri, entry.getKey(),
									(Serializable)deltaValue));
						} else {
							factory.getDebugger().handleEvent(new ChangeBreakPointRequest(uri, entry.getKey(),
									(Serializable)markerValue));
						}
					}
				}
			} catch (CoreException e) {
				Activator.getDefault().error(e);
			}
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.debug.core.model.IDisconnect#canDisconnect()
	 */
	public boolean canDisconnect() {
		return DebugTargetUtils.canDisconnect(getHost());
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.debug.core.model.IDisconnect#disconnect()
	 */
	public void disconnect() throws DebugException {
		factory.getDebugger().handleEvent(new DisconnectRequest());
		factory.getModelUpdater().disconnectRequest(getHost());
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.debug.core.model.IDisconnect#isDisconnected()
	 */
	public boolean isDisconnected() {
		return getHost().getState() == DebugTargetState.DISCONNECTED;
	}

	/**
	 * {@inheritDoc} Unused method.
	 *
	 * @see org.eclipse.debug.core.model.IMemoryBlockRetrieval#supportsStorageRetrieval()
	 */
	public boolean supportsStorageRetrieval() {
		return false;
	}

	/**
	 * {@inheritDoc} Unused method.
	 *
	 * @see org.eclipse.debug.core.model.IMemoryBlockRetrieval#getMemoryBlock(long, long)
	 */
	public IMemoryBlock getMemoryBlock(long startAddress, long length) throws DebugException {
		return null;
	}

	/**
	 * {@inheritDoc} Unused method.
	 *
	 * @see org.eclipse.debug.core.model.IDebugTarget#getProcess()
	 */
	public IProcess getProcess() {
		return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.debug.core.model.IDebugTarget#getThreads()
	 */
	public IThread[] getThreads() throws DebugException {
		final List<IThread> res = new ArrayList<IThread>();

		for (Thread thread : getHost().getThreads()) {
			res.add(factory.getThread(thread));
		}

		return res.toArray(new IThread[res.size()]);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.debug.core.model.IDebugTarget#hasThreads()
	 */
	public boolean hasThreads() throws DebugException {
		return getHost().getThreads().size() > 0;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.debug.core.model.IDebugTarget#getName()
	 */
	public String getName() throws DebugException {
		return getHost().getName();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.debug.core.model.IDebugTarget#supportsBreakpoint(org.eclipse.debug.core.model.IBreakpoint)
	 */
	public boolean supportsBreakpoint(IBreakpoint breakpoint) {
		return breakpoint instanceof DSLBreakpoint && breakpoint.getModelIdentifier().equals(
				getModelIdentifier()) && ((DSLBreakpoint)breakpoint).getURI() != null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.gemoc.dsl.debug.ide.event.IDSLDebugEventProcessor#handleEvent(org.eclipse.gemoc.dsl.debug.ide.event.IDSLDebugEvent)
	 */
	public Object handleEvent(IDSLDebugEvent event) {
		Object res = null;

		if (event instanceof SuspendedReply) {
			handleSuspendReply((SuspendedReply)event);
		} else if (event instanceof TerminatedReply) {
			handleTerminatedReply((TerminatedReply)event);
		} else if (event instanceof SpawnRunningThreadReply) {
			handleSpawnRunningThreadReply((SpawnRunningThreadReply)event);
		} else if (event instanceof ResumingReply) {
			handleResumingReply((ResumingReply)event);
		} else if (event instanceof VariableReply) {
			handleVariableReply((VariableReply)event);
		} else if (event instanceof DeleteVariableReply) {
			handleDeleteVariableReply((DeleteVariableReply)event);
		} else if (event instanceof PushStackFrameReply) {
			handlePushStackFrameReply((PushStackFrameReply)event);
		} else if (event instanceof PopStackFrameReply) {
			handlePopStackFrameReply((PopStackFrameReply)event);
		} else if (event instanceof SetCurrentInstructionReply) {
			handleSetCurrentInstructionReply((SetCurrentInstructionReply)event);
		} else if (event instanceof SetVariableValueReply) {
			handleSetVariableValueReply((SetVariableValueReply)event);
		}

		return res;
	}

	/**
	 * Handles the given {@link SetVariableValueReply}.
	 * 
	 * @param variableValueReply
	 *            the given {@link SetVariableValueReply}
	 */
	private void handleSetVariableValueReply(SetVariableValueReply variableValueReply) {
		final Thread eThread = DebugTargetUtils.getThread(getHost(), variableValueReply.getThreadName());
		final StackFrame eFrame = DebugTargetUtils.getStackFrame(eThread, variableValueReply.getStackName());
		final Variable eVariable = DebugTargetUtils.getVariable(eFrame, variableValueReply.getVariableName());
		// EMF model change
		factory.getModelUpdater().setVariableValueReply(eVariable, variableValueReply.getValue());
		// Eclipse change
		factory.getThread(eThread).fireChangeEvent(DebugEvent.CONTENT);
	}

	/**
	 * Handles the given {@link SetCurrentInstructionReply}.
	 * 
	 * @param setCurrentInstructionReply
	 *            the {@link SetCurrentInstructionReply}.
	 */
	private void handleSetCurrentInstructionReply(SetCurrentInstructionReply setCurrentInstructionReply) {
		final Thread eThread = DebugTargetUtils.getThread(getHost(), setCurrentInstructionReply
				.getThreadName());
		// EMF model change
		factory.getModelUpdater().setCurrentInstructionReply(eThread, setCurrentInstructionReply
				.getInstruction(), setCurrentInstructionReply.isCanStepInto());
		// Eclipse change
		factory.getThread(eThread).fireChangeEvent(DebugEvent.CONTENT);
		// notify current instruction listeners
		fireCurrentInstructionChangedEvent(eThread.getTopStackFrame());
	}

	/**
	 * Handles the given {@link PopStackFrameReply}.
	 * 
	 * @param popStackFrameReply
	 *            the {@link PopStackFrameReply}
	 */
	private void handlePopStackFrameReply(PopStackFrameReply popStackFrameReply) {
		final Thread eThread = DebugTargetUtils.getThread(getHost(), popStackFrameReply.getThreadName());
		// EMF model change
		final StackFrame eFrame = factory.getModelUpdater().popStackFrameReply(eThread);
		// Eclipse change
		factory.getThread(eThread).fireChangeEvent(DebugEvent.CONTENT);
		// notify current instruction listeners
		fireCurrentInstructionTerminatedEvent(eFrame);
	}

	/**
	 * Handles the given {@link PushStackFrameReply}.
	 * 
	 * @param pushStackFrameReply
	 *            the {@link PushStackFrameReply}
	 */
	private void handlePushStackFrameReply(PushStackFrameReply pushStackFrameReply) {
		final Thread eThread = DebugTargetUtils.getThread(getHost(), pushStackFrameReply.getThreadName());
		// EMF model change
		final StackFrame eFrame = factory.getModelUpdater().pushStackFrameReply(eThread, pushStackFrameReply
				.getName(), pushStackFrameReply.getContext(), pushStackFrameReply.getCurrentInstruction(),
				pushStackFrameReply.isCanStepInto());
		// Eclipse change
		factory.getThread(eThread).fireChangeEvent(DebugEvent.CONTENT);
		// notify current instruction listeners
		fireCurrentInstructionChangedEvent(eFrame);
	}

	/**
	 * Handles the given {@link DeleteVariableReply}.
	 * 
	 * @param deleteVariableReply
	 *            the {@link DeleteVariableReply}
	 */
	private void handleDeleteVariableReply(DeleteVariableReply deleteVariableReply) {
		final Thread eThread = DebugTargetUtils.getThread(getHost(), deleteVariableReply.getThreadName());
		// EMF model change
		factory.getModelUpdater().deleteVariableReply(eThread, deleteVariableReply.getName());
		// Eclipse change
		factory.getThread(eThread).fireChangeEvent(DebugEvent.CONTENT);
	}

	/**
	 * Handles the given {@link VariableReply}.
	 * 
	 * @param variableReply
	 *            the {@link VariableReply}
	 */
	private void handleVariableReply(VariableReply variableReply) {
		final Thread eThread = DebugTargetUtils.getThread(getHost(), variableReply.getThreadName());
		final StackFrame eStackFrame = DebugTargetUtils.getStackFrame(eThread, variableReply.getStackName());
		// EMF model change
		factory.getModelUpdater().setVariableReply(eStackFrame, variableReply.getDeclarationTypeName(),
				variableReply.getVariableName(), variableReply.getValue(), variableReply
						.supportModifications());
		// Eclipse change
		factory.getThread(eThread).fireChangeEvent(DebugEvent.CONTENT);
	}

	/**
	 * Handles the given {@link ResumingReply}.
	 * 
	 * @param resumingReply
	 *            the {@link ResumingReply}
	 */
	private void handleResumingReply(ResumingReply resumingReply) {
		final Thread eThread = DebugTargetUtils.getThread(getHost(), resumingReply.getThreadName());
		if (resumingReply instanceof StepIntoResumingReply) {
			// EMF model change
			factory.getModelUpdater().stepIntoReply(eThread);
			// Eclipse change
			factory.getThread(eThread).fireResumeEvent(DebugEvent.STEP_INTO);
		} else if (resumingReply instanceof StepOverResumingReply) {
			// EMF model change
			factory.getModelUpdater().stepOverReply(eThread);
			// Eclipse change
			factory.getThread(eThread).fireResumeEvent(DebugEvent.STEP_OVER);
		} else if (resumingReply instanceof StepReturnResumingReply) {
			// EMF model change
			factory.getModelUpdater().stepReturnReply(eThread);
			// Eclipse change
			factory.getThread(eThread).fireResumeEvent(DebugEvent.STEP_RETURN);
		} else {
			// EMF model change
			factory.getModelUpdater().resumedReply(eThread);
			// Eclipse change
			factory.getThread(eThread).fireResumeEvent(DebugEvent.CLIENT_REQUEST);
		}
		fireChangeEvent(DebugEvent.STATE);
	}

	/**
	 * Handles the given {@link SpawnRunningThreadReply}.
	 * 
	 * @param spawnThreadReply
	 *            the {@link SpawnRunningThreadReply}
	 */
	private void handleSpawnRunningThreadReply(SpawnRunningThreadReply spawnThreadReply) {
		// EMF model change
		factory.getModelUpdater().spawnRunningThreadReply(getHost(), spawnThreadReply.getThreadName(),
				spawnThreadReply.getContext());
		// Eclipse change
		fireChangeEvent(DebugEvent.CONTENT);
	}

	/**
	 * Handles the given {@link TerminatedReply}.
	 * 
	 * @param terminatedReply
	 *            the {@link TerminatedReply}
	 */
	private void handleTerminatedReply(TerminatedReply terminatedReply) {
		final String threadName = terminatedReply.getThreadName();
		if (threadName == null) {
			// EMF model change
			factory.getModelUpdater().terminatedReply(getHost());
			// unregister as a breakpoint listener
			DebugPlugin.getDefault().getBreakpointManager().removeBreakpointListener(this);
			// Eclipse change
			fireTerminateEvent();
		} else {
			// EMF model change
			Thread eThread = DebugTargetUtils.getThread(getHost(), threadName);
			factory.getModelUpdater().terminatedReply(eThread);
			// Eclipse change
			DSLThreadAdapter thread = factory.getThread(eThread);
			thread.fireTerminateEvent();
			// notify current instruction listeners
			StackFrame eFrame = eThread.getTopStackFrame();
			while (eFrame != null) {
				fireCurrentInstructionTerminatedEvent(eFrame);
				eFrame = eFrame.getParentFrame();
			}
		}
	}

	/**
	 * Handles the given {@link SuspendedReply}.
	 * 
	 * @param suspendReply
	 *            the {@link SuspendedReply}
	 */
	private void handleSuspendReply(SuspendedReply suspendReply) {
		final Thread eThread = DebugTargetUtils.getThread(getHost(), suspendReply.getThreadName());
		final DSLThreadAdapter thread = factory.getThread(eThread);

		// EMF model change
		factory.getModelUpdater().suspendedReply(eThread);
		// Eclipse change
		if (suspendReply instanceof SteppedReply) {
			thread.fireSuspendEvent(DebugEvent.STEP_END);
		} else if (suspendReply instanceof BreakpointReply) {
			thread.fireSuspendEvent(DebugEvent.BREAKPOINT);
		} else {
			thread.fireSuspendEvent(DebugEvent.CLIENT_REQUEST);
		}
		fireChangeEvent(DebugEvent.STATE);
		// notify our suspend listeners

	}

	/**
	 * Adds the given {@link IDSLSuspendListener}.
	 * 
	 * @param listener
	 *            the {@link IDSLSuspendListener} to add
	 */
	public void addCurrentInstructionListener(IDSLCurrentInstructionListener listener) {
		if (!currentInstructionListeners.contains(listener)) {
			currentInstructionListeners.add(listener);
		}
	}

	/**
	 * Removes the given {@link IDSLSuspendListener}.
	 * 
	 * @param listener
	 *            the {@link IDSLSuspendListener} to remove
	 */
	public void removeCurrentInstructionListener(IDSLCurrentInstructionListener listener) {
		currentInstructionListeners.remove(listener);
	}

	/**
	 * Notifies a change of current instruction for the given {@link Thread}.
	 * 
	 * @param frame
	 *            the {@link StackFrame}
	 */
	protected void fireCurrentInstructionChangedEvent(StackFrame frame) {
		for (IDSLCurrentInstructionListener listener : currentInstructionListeners) {
			listener.currentInstructionChanged(getModelIdentifier(), frame);
		}
	}

	/**
	 * Notifies a change of current instruction for the given {@link StackFrame}.
	 * 
	 * @param frame
	 *            the {@link StackFrame}
	 */
	protected void fireCurrentInstructionTerminatedEvent(StackFrame frame) {
		for (IDSLCurrentInstructionListener listener : currentInstructionListeners) {
			listener.terminated(getModelIdentifier(), frame);
		}
	}

	/**
	 * Gets the {@link List} of {@link IDSLCurrentInstructionListener}.
	 * 
	 * @return the {@link List} of {@link IDSLCurrentInstructionListener}
	 */
	public List<IDSLCurrentInstructionListener> getCurrentInstructionListeners() {
		return currentInstructionListeners;
	}

}
