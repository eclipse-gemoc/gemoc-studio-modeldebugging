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
package org.eclipse.gemoc.dsl.debug.ide.tests.adapter;

import org.eclipse.gemoc.dsl.debug.DebugPackage;
import org.eclipse.gemoc.dsl.debug.DebugTarget;
import org.eclipse.gemoc.dsl.debug.DebugTargetState;
import org.eclipse.gemoc.dsl.debug.ThreadUtils;
import org.eclipse.gemoc.dsl.debug.ide.DSLEclipseDebugIntegration;
import org.eclipse.gemoc.dsl.debug.ide.ModelUpdater;
import org.eclipse.gemoc.dsl.debug.ide.adapter.DSLDebugTargetAdapter;
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
import org.eclipse.gemoc.dsl.debug.ide.event.model.ResumeRequest;
import org.eclipse.gemoc.dsl.debug.ide.event.model.StartRequest;
import org.eclipse.gemoc.dsl.debug.ide.event.model.SuspendRequest;
import org.eclipse.gemoc.dsl.debug.ide.event.model.TerminateRequest;
import org.eclipse.gemoc.dsl.debug.ide.tests.event.TestEventProcessor;
import org.eclipse.gemoc.dsl.debug.tests.AbstractDebugTests;

import org.eclipse.debug.core.DebugEvent;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.debug.core.model.IThread;
import org.eclipse.emf.ecore.EObject;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Tests {@link DSLDebugTargetAdapter}.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class DSLDebugTargetAdapterTests extends AbstractDebugTests {

	/**
	 * Test {@link DSLDebugTargetAdapter#start()}.
	 */
	@Test
	public void start() {
		DebugTarget eDebugTarget = DebugPackage.eINSTANCE.getDebugFactory().createDebugTarget();
		eDebugTarget.setName("Debug target");
		final TestEventProcessor testEventProcessor = new TestEventProcessor();
		final DSLEclipseDebugIntegration integration = new DSLEclipseDebugIntegration("id", null,
				eDebugTarget, new ModelUpdater(), testEventProcessor);
		final DSLDebugTargetAdapter debugTarget = integration.getDebugTarget();
		debugTarget.start();

		assertEquals(1, testEventProcessor.getEvents().size());
		assertTrue(testEventProcessor.getEvents().get(0) instanceof StartRequest);
	}

	/**
	 * Test {@link DSLDebugTargetAdapter#isAdapterForType(Object)}.
	 */
	@Test
	public void isAdapterForType() {
		DebugTarget eDebugTarget = DebugPackage.eINSTANCE.getDebugFactory().createDebugTarget();
		eDebugTarget.setName("Debug target");
		final TestEventProcessor testEventProcessor = new TestEventProcessor();
		final DSLEclipseDebugIntegration integration = new DSLEclipseDebugIntegration("id", null,
				eDebugTarget, new ModelUpdater(), testEventProcessor);
		final DSLDebugTargetAdapter debugTarget = integration.getDebugTarget();

		assertTrue(debugTarget.isAdapterForType(IDebugTarget.class));
	}

	/**
	 * Test {@link DSLDebugTargetAdapter#terminate()}.
	 * 
	 * @throws DebugException
	 *             if fail
	 */
	@Test
	public void terminate() throws DebugException {
		DebugTarget eDebugTarget = DebugPackage.eINSTANCE.getDebugFactory().createDebugTarget();
		eDebugTarget.setName("Debug target");
		final TestEventProcessor testEventProcessor = new TestEventProcessor();
		final DSLEclipseDebugIntegration integration = new DSLEclipseDebugIntegration("id", null,
				eDebugTarget, new ModelUpdater(), testEventProcessor);
		final DSLDebugTargetAdapter debugTarget = integration.getDebugTarget();

		debugTarget.terminate();

		assertEquals(1, testEventProcessor.getEvents().size());
		assertTrue(testEventProcessor.getEvents().get(0) instanceof TerminateRequest);
		assertEquals(null, ((TerminateRequest)testEventProcessor.getEvents().get(0)).getThreadName());
		assertEquals(DebugTargetState.TERMINATING, eDebugTarget.getState());
	}

	/**
	 * Test {@link DSLDebugTargetAdapter#resume()}.
	 * 
	 * @throws DebugException
	 *             if fail
	 */
	@Test
	public void resume() throws DebugException {
		DebugTarget eDebugTarget = DebugPackage.eINSTANCE.getDebugFactory().createDebugTarget();
		eDebugTarget.setName("Debug target");
		final TestEventProcessor testEventProcessor = new TestEventProcessor();
		final DSLEclipseDebugIntegration integration = new DSLEclipseDebugIntegration("id", null,
				eDebugTarget, new ModelUpdater(), testEventProcessor);
		final DSLDebugTargetAdapter debugTarget = integration.getDebugTarget();

		debugTarget.resume();

		assertEquals(1, testEventProcessor.getEvents().size());
		assertTrue(testEventProcessor.getEvents().get(0) instanceof ResumeRequest);
		assertEquals(null, ((ResumeRequest)testEventProcessor.getEvents().get(0)).getThreadName());
	}

	/**
	 * Test {@link DSLDebugTargetAdapter#suspend()}.
	 * 
	 * @throws DebugException
	 *             if fail
	 */
	@Test
	public void suspend() throws DebugException {
		DebugTarget eDebugTarget = DebugPackage.eINSTANCE.getDebugFactory().createDebugTarget();
		eDebugTarget.setName("Debug target");
		final TestEventProcessor testEventProcessor = new TestEventProcessor();
		final DSLEclipseDebugIntegration integration = new DSLEclipseDebugIntegration("id", null,
				eDebugTarget, new ModelUpdater(), testEventProcessor);
		final DSLDebugTargetAdapter debugTarget = integration.getDebugTarget();

		debugTarget.suspend();

		assertEquals(1, testEventProcessor.getEvents().size());
		assertTrue(testEventProcessor.getEvents().get(0) instanceof SuspendRequest);
		assertEquals(null, ((SuspendRequest)testEventProcessor.getEvents().get(0)).getThreadName());
	}

	/**
	 * Test {@link DSLDebugTargetAdapter#getThreads()}.
	 * 
	 * @throws DebugException
	 *             if fail
	 */
	@Test
	public void getThreads() throws DebugException {
		DebugTarget eDebugTarget = DebugPackage.eINSTANCE.getDebugFactory().createDebugTarget();
		eDebugTarget.setName("Debug target");
		final TestEventProcessor testEventProcessor = new TestEventProcessor();
		final DSLEclipseDebugIntegration integration = new DSLEclipseDebugIntegration("id", null,
				eDebugTarget, new ModelUpdater(), testEventProcessor);
		final DSLDebugTargetAdapter debugTarget = integration.getDebugTarget();

		createThreads(eDebugTarget);

		final IThread[] threads = debugTarget.getThreads();

		assertEquals(0, testEventProcessor.getEvents().size());
		assertEquals(8, threads.length);
	}

	/**
	 * Test {@link DSLDebugTargetAdapter#hasThreads()}.
	 * 
	 * @throws DebugException
	 *             if fail
	 */
	@Test
	public void hasThreads() throws DebugException {
		DebugTarget eDebugTarget = DebugPackage.eINSTANCE.getDebugFactory().createDebugTarget();
		eDebugTarget.setName("Debug target");
		final TestEventProcessor testEventProcessor = new TestEventProcessor();
		final DSLEclipseDebugIntegration integration = new DSLEclipseDebugIntegration("id", null,
				eDebugTarget, new ModelUpdater(), testEventProcessor);
		final DSLDebugTargetAdapter debugTarget = integration.getDebugTarget();

		assertTrue(!debugTarget.hasThreads());

		createThreads(eDebugTarget);

		assertTrue(debugTarget.hasThreads());
	}

	/**
	 * Test {@link DSLDebugTargetAdapter#getName()}.
	 * 
	 * @throws DebugException
	 *             if fail
	 */
	@Test
	public void getName() throws DebugException {
		DebugTarget eDebugTarget = DebugPackage.eINSTANCE.getDebugFactory().createDebugTarget();
		eDebugTarget.setName("Debug target");
		final TestEventProcessor testEventProcessor = new TestEventProcessor();
		final DSLEclipseDebugIntegration integration = new DSLEclipseDebugIntegration("id", null,
				eDebugTarget, new ModelUpdater(), testEventProcessor);
		final DSLDebugTargetAdapter debugTarget = integration.getDebugTarget();

		assertEquals("Debug target", debugTarget.getName());
	}

	/**
	 * Test {@link DSLDebugTargetAdapter#handleEvent(org.eclipse.gemoc.dsl.debug.ide.event.IDSLDebugEvent)}.
	 * 
	 * @throws DebugException
	 *             if fail
	 */
	@Test
	public void handleEventSuspendedReply() throws DebugException {
		DebugTarget eDebugTarget = DebugPackage.eINSTANCE.getDebugFactory().createDebugTarget();
		eDebugTarget.setName("Debug target");
		final TestEventProcessor testEventProcessor = new TestEventProcessor();
		final DSLEclipseDebugIntegration integration = new DSLEclipseDebugIntegration("id", null,
				eDebugTarget, new ModelUpdater(), testEventProcessor);
		final DSLDebugTargetAdapter debugTarget = integration.getDebugTarget();
		TestDebugEventSetListener listener = new TestDebugEventSetListener();

		createThreads(eDebugTarget);
		debugTarget.getThreads(); // force adapter creation to avoid create event

		DebugPlugin.getDefault().addDebugEventListener(listener);
		try {
			debugTarget.handleEvent(new SuspendedReply(eDebugTarget.getThreads().get(runningThreadIndex)
					.getName()));
			listener.waitForEvent();
		} finally {
			DebugPlugin.getDefault().removeDebugEventListener(listener);
		}

		assertEquals(2, listener.getEventsList().size());
		DebugEvent[] events = listener.getEventsList().get(0);
		assertEquals(1, events.length);
		DebugEvent event = events[0];
		assertTrue(event.getSource() == integration.getThread(eDebugTarget.getThreads().get(
				runningThreadIndex)));
		assertEquals(DebugEvent.SUSPEND, event.getKind());
		assertEquals(DebugEvent.CLIENT_REQUEST, event.getDetail());

		events = listener.getEventsList().get(1);
		assertEquals(1, events.length);
		event = events[0];
		assertTrue(event.getSource() == debugTarget);
		assertEquals(DebugEvent.CHANGE, event.getKind());
		assertEquals(DebugEvent.STATE, event.getDetail());
	}

	/**
	 * Test {@link DSLDebugTargetAdapter#handleEvent(org.eclipse.gemoc.dsl.debug.ide.event.IDSLDebugEvent)}.
	 * 
	 * @throws DebugException
	 *             if fail
	 */
	@Test
	public void handleEventSteppedReply() throws DebugException {
		DebugTarget eDebugTarget = DebugPackage.eINSTANCE.getDebugFactory().createDebugTarget();
		eDebugTarget.setName("Debug target");
		final TestEventProcessor testEventProcessor = new TestEventProcessor();
		final DSLEclipseDebugIntegration integration = new DSLEclipseDebugIntegration("id", null,
				eDebugTarget, new ModelUpdater(), testEventProcessor);
		final DSLDebugTargetAdapter debugTarget = integration.getDebugTarget();
		TestDebugEventSetListener listener = new TestDebugEventSetListener();

		createThreads(eDebugTarget);
		debugTarget.getThreads(); // force adapter creation to avoid create event

		DebugPlugin.getDefault().addDebugEventListener(listener);
		try {
			debugTarget.handleEvent(new SteppedReply(eDebugTarget.getThreads().get(runningThreadIndex)
					.getName()));
			listener.waitForEvent();
		} finally {
			DebugPlugin.getDefault().removeDebugEventListener(listener);
		}

		assertEquals(2, listener.getEventsList().size());
		DebugEvent[] events = listener.getEventsList().get(0);
		assertEquals(1, events.length);
		DebugEvent event = events[0];
		assertTrue(event.getSource() == integration.getThread(eDebugTarget.getThreads().get(
				runningThreadIndex)));
		assertEquals(DebugEvent.SUSPEND, event.getKind());
		assertEquals(DebugEvent.STEP_END, event.getDetail());

		events = listener.getEventsList().get(1);
		assertEquals(1, events.length);
		event = events[0];
		assertTrue(event.getSource() == debugTarget);
		assertEquals(DebugEvent.CHANGE, event.getKind());
		assertEquals(DebugEvent.STATE, event.getDetail());
	}

	/**
	 * Test {@link DSLDebugTargetAdapter#handleEvent(org.eclipse.gemoc.dsl.debug.ide.event.IDSLDebugEvent)}.
	 * 
	 * @throws DebugException
	 *             if fail
	 */
	@Test
	public void handleEventBreakpointReply() throws DebugException {
		DebugTarget eDebugTarget = DebugPackage.eINSTANCE.getDebugFactory().createDebugTarget();
		eDebugTarget.setName("Debug target");
		final TestEventProcessor testEventProcessor = new TestEventProcessor();
		final DSLEclipseDebugIntegration integration = new DSLEclipseDebugIntegration("id", null,
				eDebugTarget, new ModelUpdater(), testEventProcessor);
		final DSLDebugTargetAdapter debugTarget = integration.getDebugTarget();
		TestDebugEventSetListener listener = new TestDebugEventSetListener();

		createThreads(eDebugTarget);
		debugTarget.getThreads(); // force adapter creation to avoid create event

		DebugPlugin.getDefault().addDebugEventListener(listener);
		try {
			debugTarget.handleEvent(new BreakpointReply(eDebugTarget.getThreads().get(runningThreadIndex)
					.getName()));
			listener.waitForEvent();
		} finally {
			DebugPlugin.getDefault().removeDebugEventListener(listener);
		}

		assertEquals(2, listener.getEventsList().size());
		DebugEvent[] events = listener.getEventsList().get(0);
		assertEquals(1, events.length);
		DebugEvent event = events[0];
		assertTrue(event.getSource() == integration.getThread(eDebugTarget.getThreads().get(
				runningThreadIndex)));
		assertEquals(DebugEvent.SUSPEND, event.getKind());
		assertEquals(DebugEvent.BREAKPOINT, event.getDetail());

		events = listener.getEventsList().get(1);
		assertEquals(1, events.length);
		event = events[0];
		assertTrue(event.getSource() == debugTarget);
		assertEquals(DebugEvent.CHANGE, event.getKind());
		assertEquals(DebugEvent.STATE, event.getDetail());
	}

	/**
	 * Test {@link DSLDebugTargetAdapter#handleEvent(org.eclipse.gemoc.dsl.debug.ide.event.IDSLDebugEvent)}.
	 * 
	 * @throws DebugException
	 *             if fail
	 */
	@Test
	public void handleEventTerminatedReplyThread() throws DebugException {
		DebugTarget eDebugTarget = DebugPackage.eINSTANCE.getDebugFactory().createDebugTarget();
		eDebugTarget.setName("Debug target");
		final TestEventProcessor testEventProcessor = new TestEventProcessor();
		final DSLEclipseDebugIntegration integration = new DSLEclipseDebugIntegration("id", null,
				eDebugTarget, new ModelUpdater(), testEventProcessor);
		final DSLDebugTargetAdapter debugTarget = integration.getDebugTarget();
		TestDebugEventSetListener listener = new TestDebugEventSetListener();

		createThreads(eDebugTarget);
		debugTarget.getThreads(); // force adapter creation to avoid create event

		DebugPlugin.getDefault().addDebugEventListener(listener);
		try {
			debugTarget.handleEvent(new TerminatedReply(eDebugTarget.getThreads().get(runningThreadIndex)
					.getName()));
			listener.waitForEvent();
		} finally {
			DebugPlugin.getDefault().removeDebugEventListener(listener);
		}

		assertEquals(1, listener.getEventsList().size());
		DebugEvent[] events = listener.getEventsList().get(0);
		assertEquals(1, events.length);
		DebugEvent event = events[0];
		assertTrue(event.getSource() == integration.getThread(eDebugTarget.getThreads().get(
				runningThreadIndex)));
		assertEquals(DebugEvent.TERMINATE, event.getKind());
	}

	/**
	 * Test {@link DSLDebugTargetAdapter#handleEvent(org.eclipse.gemoc.dsl.debug.ide.event.IDSLDebugEvent)}.
	 * 
	 * @throws DebugException
	 *             if fail
	 */
	@Test
	public void handleEventTerminatedReply() throws DebugException {
		DebugTarget eDebugTarget = DebugPackage.eINSTANCE.getDebugFactory().createDebugTarget();
		eDebugTarget.setName("Debug target");
		final TestEventProcessor testEventProcessor = new TestEventProcessor();
		final DSLEclipseDebugIntegration integration = new DSLEclipseDebugIntegration("id", null,
				eDebugTarget, new ModelUpdater(), testEventProcessor);
		final DSLDebugTargetAdapter debugTarget = integration.getDebugTarget();
		TestDebugEventSetListener listener = new TestDebugEventSetListener();

		createThreads(eDebugTarget);
		debugTarget.getThreads(); // force adapter creation to avoid create event

		DebugPlugin.getDefault().addDebugEventListener(listener);
		try {
			debugTarget.handleEvent(new TerminatedReply());
			listener.waitForEvent();
		} finally {
			DebugPlugin.getDefault().removeDebugEventListener(listener);
		}

		assertEquals(1, listener.getEventsList().size());
		DebugEvent[] events = listener.getEventsList().get(0);
		assertEquals(1, events.length);
		DebugEvent event = events[0];
		assertTrue(event.getSource() == debugTarget);
		assertEquals(DebugEvent.TERMINATE, event.getKind());
	}

	/**
	 * Test {@link DSLDebugTargetAdapter#handleEvent(org.eclipse.gemoc.dsl.debug.ide.event.IDSLDebugEvent)}.
	 * 
	 * @throws DebugException
	 *             if fail
	 */
	@Test
	public void handleEventSpawnRunningThreadReply() throws DebugException {
		DebugTarget eDebugTarget = DebugPackage.eINSTANCE.getDebugFactory().createDebugTarget();
		eDebugTarget.setName("Debug target");
		final TestEventProcessor testEventProcessor = new TestEventProcessor();
		final DSLEclipseDebugIntegration integration = new DSLEclipseDebugIntegration("id", null,
				eDebugTarget, new ModelUpdater(), testEventProcessor);
		final DSLDebugTargetAdapter debugTarget = integration.getDebugTarget();
		TestDebugEventSetListener listener = new TestDebugEventSetListener();

		createThreads(eDebugTarget);
		debugTarget.getThreads(); // force adapter creation to avoid create event

		DebugPlugin.getDefault().addDebugEventListener(listener);
		final EObject context = DebugPackage.eINSTANCE.getDebugFactory().createVariable();
		try {
			debugTarget.handleEvent(new SpawnRunningThreadReply("thread", context));
			listener.waitForEvent();
		} finally {
			DebugPlugin.getDefault().removeDebugEventListener(listener);
		}

		assertEquals(9, eDebugTarget.getThreads().size());

		assertEquals(1, listener.getEventsList().size());
		DebugEvent[] events = listener.getEventsList().get(0);
		assertEquals(1, events.length);
		DebugEvent event = events[0];
		assertTrue(event.getSource() == debugTarget);
		assertEquals(DebugEvent.CHANGE, event.getKind());
		assertEquals(DebugEvent.CONTENT, event.getDetail());
	}

	/**
	 * Test {@link DSLDebugTargetAdapter#handleEvent(org.eclipse.gemoc.dsl.debug.ide.event.IDSLDebugEvent)}.
	 * 
	 * @throws DebugException
	 *             if fail
	 */
	@Test
	public void handleEventResumingReply() throws DebugException {
		DebugTarget eDebugTarget = DebugPackage.eINSTANCE.getDebugFactory().createDebugTarget();
		eDebugTarget.setName("Debug target");
		final TestEventProcessor testEventProcessor = new TestEventProcessor();
		final DSLEclipseDebugIntegration integration = new DSLEclipseDebugIntegration("id", null,
				eDebugTarget, new ModelUpdater(), testEventProcessor);
		final DSLDebugTargetAdapter debugTarget = integration.getDebugTarget();
		TestDebugEventSetListener listener = new TestDebugEventSetListener();

		createThreads(eDebugTarget);
		debugTarget.getThreads(); // force adapter creation to avoid create event

		DebugPlugin.getDefault().addDebugEventListener(listener);
		try {
			debugTarget.handleEvent(new ResumingReply(eDebugTarget.getThreads().get(suspendedThreadIndex)
					.getName()));
			listener.waitForEvent();
		} finally {
			DebugPlugin.getDefault().removeDebugEventListener(listener);
		}

		assertEquals(2, listener.getEventsList().size());
		DebugEvent[] events = listener.getEventsList().get(0);
		assertEquals(1, events.length);
		DebugEvent event = events[0];
		assertTrue(event.getSource() == integration.getThread(eDebugTarget.getThreads().get(
				suspendedThreadIndex)));
		assertEquals(DebugEvent.RESUME, event.getKind());
		assertEquals(DebugEvent.CLIENT_REQUEST, event.getDetail());

		events = listener.getEventsList().get(1);
		assertEquals(1, events.length);
		event = events[0];
		assertTrue(event.getSource() == debugTarget);
		assertEquals(DebugEvent.CHANGE, event.getKind());
		assertEquals(DebugEvent.STATE, event.getDetail());
	}

	/**
	 * Test {@link DSLDebugTargetAdapter#handleEvent(org.eclipse.gemoc.dsl.debug.ide.event.IDSLDebugEvent)}.
	 * 
	 * @throws DebugException
	 *             if fail
	 */
	@Test
	public void handleEventStepReturnResumingReply() throws DebugException {
		DebugTarget eDebugTarget = DebugPackage.eINSTANCE.getDebugFactory().createDebugTarget();
		eDebugTarget.setName("Debug target");
		final TestEventProcessor testEventProcessor = new TestEventProcessor();
		final DSLEclipseDebugIntegration integration = new DSLEclipseDebugIntegration("id", null,
				eDebugTarget, new ModelUpdater(), testEventProcessor);
		final DSLDebugTargetAdapter debugTarget = integration.getDebugTarget();
		TestDebugEventSetListener listener = new TestDebugEventSetListener();

		createThreads(eDebugTarget);
		debugTarget.getThreads(); // force adapter creation to avoid create event

		final EObject context = DebugPackage.eINSTANCE.getDebugFactory().createVariable();
		final EObject instruction = DebugPackage.eINSTANCE.getDebugFactory().createVariable();
		ThreadUtils.pushStackFrameReply(eDebugTarget.getThreads().get(suspendedThreadIndex), "frame",
				context, instruction, false);
		DebugPlugin.getDefault().addDebugEventListener(listener);
		try {
			debugTarget.handleEvent(new StepReturnResumingReply(eDebugTarget.getThreads().get(
					suspendedThreadIndex).getName()));
			listener.waitForEvent();
		} finally {
			DebugPlugin.getDefault().removeDebugEventListener(listener);
		}

		assertEquals(2, listener.getEventsList().size());
		DebugEvent[] events = listener.getEventsList().get(0);
		assertEquals(1, events.length);
		DebugEvent event = events[0];
		assertTrue(event.getSource() == integration.getThread(eDebugTarget.getThreads().get(
				suspendedThreadIndex)));
		assertEquals(DebugEvent.RESUME, event.getKind());
		assertEquals(DebugEvent.STEP_RETURN, event.getDetail());

		events = listener.getEventsList().get(1);
		assertEquals(1, events.length);
		event = events[0];
		assertTrue(event.getSource() == debugTarget);
		assertEquals(DebugEvent.CHANGE, event.getKind());
		assertEquals(DebugEvent.STATE, event.getDetail());
	}

	/**
	 * Test {@link DSLDebugTargetAdapter#handleEvent(org.eclipse.gemoc.dsl.debug.ide.event.IDSLDebugEvent)}.
	 * 
	 * @throws DebugException
	 *             if fail
	 */
	@Test
	public void handleEventStepOverResumingReply() throws DebugException {
		DebugTarget eDebugTarget = DebugPackage.eINSTANCE.getDebugFactory().createDebugTarget();
		eDebugTarget.setName("Debug target");
		final TestEventProcessor testEventProcessor = new TestEventProcessor();
		final DSLEclipseDebugIntegration integration = new DSLEclipseDebugIntegration("id", null,
				eDebugTarget, new ModelUpdater(), testEventProcessor);
		final DSLDebugTargetAdapter debugTarget = integration.getDebugTarget();
		TestDebugEventSetListener listener = new TestDebugEventSetListener();

		createThreads(eDebugTarget);
		debugTarget.getThreads(); // force adapter creation to avoid create event

		DebugPlugin.getDefault().addDebugEventListener(listener);
		try {
			debugTarget.handleEvent(new StepOverResumingReply(eDebugTarget.getThreads().get(
					suspendedThreadIndex).getName()));
			listener.waitForEvent();
		} finally {
			DebugPlugin.getDefault().removeDebugEventListener(listener);
		}

		assertEquals(2, listener.getEventsList().size());
		DebugEvent[] events = listener.getEventsList().get(0);
		assertEquals(1, events.length);
		DebugEvent event = events[0];
		assertTrue(event.getSource() == integration.getThread(eDebugTarget.getThreads().get(
				suspendedThreadIndex)));
		assertEquals(DebugEvent.RESUME, event.getKind());
		assertEquals(DebugEvent.STEP_OVER, event.getDetail());

		events = listener.getEventsList().get(1);
		assertEquals(1, events.length);
		event = events[0];
		assertTrue(event.getSource() == debugTarget);
		assertEquals(DebugEvent.CHANGE, event.getKind());
		assertEquals(DebugEvent.STATE, event.getDetail());
	}

	/**
	 * Test {@link DSLDebugTargetAdapter#handleEvent(org.eclipse.gemoc.dsl.debug.ide.event.IDSLDebugEvent)}.
	 * 
	 * @throws DebugException
	 *             if fail
	 */
	@Test
	public void handleEventStepIntoResumingReply() throws DebugException {
		DebugTarget eDebugTarget = DebugPackage.eINSTANCE.getDebugFactory().createDebugTarget();
		eDebugTarget.setName("Debug target");
		final TestEventProcessor testEventProcessor = new TestEventProcessor();
		final DSLEclipseDebugIntegration integration = new DSLEclipseDebugIntegration("id", null,
				eDebugTarget, new ModelUpdater(), testEventProcessor);
		final DSLDebugTargetAdapter debugTarget = integration.getDebugTarget();
		TestDebugEventSetListener listener = new TestDebugEventSetListener();

		createThreads(eDebugTarget);
		debugTarget.getThreads(); // force adapter creation to avoid create event

		eDebugTarget.getThreads().get(suspendedThreadIndex).getTopStackFrame()
				.setCanStepIntoCurrentInstruction(true);
		DebugPlugin.getDefault().addDebugEventListener(listener);
		try {
			debugTarget.handleEvent(new StepIntoResumingReply(eDebugTarget.getThreads().get(
					suspendedThreadIndex).getName()));
			listener.waitForEvent();
		} finally {
			DebugPlugin.getDefault().removeDebugEventListener(listener);
		}

		assertEquals(2, listener.getEventsList().size());
		DebugEvent[] events = listener.getEventsList().get(0);
		assertEquals(1, events.length);
		DebugEvent event = events[0];
		assertTrue(event.getSource() == integration.getThread(eDebugTarget.getThreads().get(
				suspendedThreadIndex)));
		assertEquals(DebugEvent.RESUME, event.getKind());
		assertEquals(DebugEvent.STEP_INTO, event.getDetail());

		events = listener.getEventsList().get(1);
		assertEquals(1, events.length);
		event = events[0];
		assertTrue(event.getSource() == debugTarget);
		assertEquals(DebugEvent.CHANGE, event.getKind());
		assertEquals(DebugEvent.STATE, event.getDetail());
	}

	/**
	 * Test {@link DSLDebugTargetAdapter#handleEvent(org.eclipse.gemoc.dsl.debug.ide.event.IDSLDebugEvent)}.
	 * 
	 * @throws DebugException
	 *             if fail
	 */
	@Test
	public void handleEventVariableReply() throws DebugException {
		DebugTarget eDebugTarget = DebugPackage.eINSTANCE.getDebugFactory().createDebugTarget();
		eDebugTarget.setName("Debug target");
		final TestEventProcessor testEventProcessor = new TestEventProcessor();
		final DSLEclipseDebugIntegration integration = new DSLEclipseDebugIntegration("id", null,
				eDebugTarget, new ModelUpdater(), testEventProcessor);
		final DSLDebugTargetAdapter debugTarget = integration.getDebugTarget();
		TestDebugEventSetListener listener = new TestDebugEventSetListener();

		createThreads(eDebugTarget);
		debugTarget.getThreads(); // force adapter creation to avoid create event

		DebugPlugin.getDefault().addDebugEventListener(listener);
		try {
			debugTarget.handleEvent(new VariableReply(eDebugTarget.getThreads().get(suspendedThreadIndex)
					.getName(), eDebugTarget.getThreads().get(suspendedThreadIndex).getTopStackFrame()
					.getName(), "Object", "variable", null, false));
			listener.waitForEvent();
		} finally {
			DebugPlugin.getDefault().removeDebugEventListener(listener);
		}

		assertEquals(1, eDebugTarget.getThreads().get(suspendedThreadIndex).getTopStackFrame().getVariables()
				.size());

		assertEquals(1, listener.getEventsList().size());
		DebugEvent[] events = listener.getEventsList().get(0);
		assertEquals(1, events.length);
		DebugEvent event = events[0];
		assertTrue(event.getSource() == integration.getThread(eDebugTarget.getThreads().get(
				suspendedThreadIndex)));
		assertEquals(DebugEvent.CHANGE, event.getKind());
		assertEquals(DebugEvent.CONTENT, event.getDetail());
	}

	/**
	 * Test {@link DSLDebugTargetAdapter#handleEvent(org.eclipse.gemoc.dsl.debug.ide.event.IDSLDebugEvent)}.
	 * 
	 * @throws DebugException
	 *             if fail
	 */
	@Test
	public void handleEventDeleteVariableReply() throws DebugException {
		DebugTarget eDebugTarget = DebugPackage.eINSTANCE.getDebugFactory().createDebugTarget();
		eDebugTarget.setName("Debug target");
		final TestEventProcessor testEventProcessor = new TestEventProcessor();
		final DSLEclipseDebugIntegration integration = new DSLEclipseDebugIntegration("id", null,
				eDebugTarget, new ModelUpdater(), testEventProcessor);
		final DSLDebugTargetAdapter debugTarget = integration.getDebugTarget();
		TestDebugEventSetListener listener = new TestDebugEventSetListener();

		createThreads(eDebugTarget);
		debugTarget.getThreads(); // force adapter creation to avoid create event

		ThreadUtils.setVariableReply(eDebugTarget.getThreads().get(suspendedThreadIndex).getTopStackFrame(),
				"Object", "variable", null, false);
		DebugPlugin.getDefault().addDebugEventListener(listener);
		try {
			debugTarget.handleEvent(new DeleteVariableReply(eDebugTarget.getThreads().get(
					suspendedThreadIndex).getName(), "variable"));
			listener.waitForEvent();
		} finally {
			DebugPlugin.getDefault().removeDebugEventListener(listener);
		}

		assertEquals(0, eDebugTarget.getThreads().get(suspendedThreadIndex).getTopStackFrame().getVariables()
				.size());

		assertEquals(1, listener.getEventsList().size());
		DebugEvent[] events = listener.getEventsList().get(0);
		assertEquals(1, events.length);
		DebugEvent event = events[0];
		assertTrue(event.getSource() == integration.getThread(eDebugTarget.getThreads().get(
				suspendedThreadIndex)));
		assertEquals(DebugEvent.CHANGE, event.getKind());
		assertEquals(DebugEvent.CONTENT, event.getDetail());
	}

	/**
	 * Test {@link DSLDebugTargetAdapter#handleEvent(org.eclipse.gemoc.dsl.debug.ide.event.IDSLDebugEvent)}.
	 * 
	 * @throws DebugException
	 *             if fail
	 */
	@Test
	public void handleEventPushStackFrameReply() throws DebugException {
		DebugTarget eDebugTarget = DebugPackage.eINSTANCE.getDebugFactory().createDebugTarget();
		eDebugTarget.setName("Debug target");
		final TestEventProcessor testEventProcessor = new TestEventProcessor();
		final DSLEclipseDebugIntegration integration = new DSLEclipseDebugIntegration("id", null,
				eDebugTarget, new ModelUpdater(), testEventProcessor);
		final DSLDebugTargetAdapter debugTarget = integration.getDebugTarget();
		TestDebugEventSetListener listener = new TestDebugEventSetListener();

		createThreads(eDebugTarget);
		debugTarget.getThreads(); // force adapter creation to avoid create event

		final EObject context = DebugPackage.eINSTANCE.getDebugFactory().createVariable();
		final EObject instruction = DebugPackage.eINSTANCE.getDebugFactory().createVariable();
		DebugPlugin.getDefault().addDebugEventListener(listener);
		try {
			debugTarget.handleEvent(new PushStackFrameReply(eDebugTarget.getThreads().get(
					suspendedThreadIndex).getName(), "frame", context, instruction, false));
			listener.waitForEvent();
		} finally {
			DebugPlugin.getDefault().removeDebugEventListener(listener);
		}

		assertEquals("frame", eDebugTarget.getThreads().get(suspendedThreadIndex).getTopStackFrame()
				.getName());

		assertEquals(1, listener.getEventsList().size());
		DebugEvent[] events = listener.getEventsList().get(0);
		assertEquals(1, events.length);
		DebugEvent event = events[0];
		assertTrue(event.getSource() == integration.getThread(eDebugTarget.getThreads().get(
				suspendedThreadIndex)));
		assertEquals(DebugEvent.CHANGE, event.getKind());
		assertEquals(DebugEvent.CONTENT, event.getDetail());
	}

	/**
	 * Test {@link DSLDebugTargetAdapter#handleEvent(org.eclipse.gemoc.dsl.debug.ide.event.IDSLDebugEvent)}.
	 * 
	 * @throws DebugException
	 *             if fail
	 */
	@Test
	public void handleEventPopStackFrameReply() throws DebugException {
		DebugTarget eDebugTarget = DebugPackage.eINSTANCE.getDebugFactory().createDebugTarget();
		eDebugTarget.setName("Debug target");
		final TestEventProcessor testEventProcessor = new TestEventProcessor();
		final DSLEclipseDebugIntegration integration = new DSLEclipseDebugIntegration("id", null,
				eDebugTarget, new ModelUpdater(), testEventProcessor);
		final DSLDebugTargetAdapter debugTarget = integration.getDebugTarget();
		TestDebugEventSetListener listener = new TestDebugEventSetListener();

		createThreads(eDebugTarget);
		debugTarget.getThreads(); // force adapter creation to avoid create event

		final EObject context = DebugPackage.eINSTANCE.getDebugFactory().createVariable();
		final EObject instruction = DebugPackage.eINSTANCE.getDebugFactory().createVariable();
		ThreadUtils.pushStackFrameReply(eDebugTarget.getThreads().get(suspendedThreadIndex), "frame",
				context, instruction, false);
		DebugPlugin.getDefault().addDebugEventListener(listener);
		try {
			debugTarget.handleEvent(new PopStackFrameReply(eDebugTarget.getThreads()
					.get(suspendedThreadIndex).getName()));
			listener.waitForEvent();
		} finally {
			DebugPlugin.getDefault().removeDebugEventListener(listener);
		}

		assertEquals(eDebugTarget.getThreads().get(suspendedThreadIndex).getBottomStackFrame(), eDebugTarget
				.getThreads().get(suspendedThreadIndex).getTopStackFrame());

		assertEquals(1, listener.getEventsList().size());
		DebugEvent[] events = listener.getEventsList().get(0);
		assertEquals(1, events.length);
		DebugEvent event = events[0];
		assertTrue(event.getSource() == integration.getThread(eDebugTarget.getThreads().get(
				suspendedThreadIndex)));
		assertEquals(DebugEvent.CHANGE, event.getKind());
		assertEquals(DebugEvent.CONTENT, event.getDetail());
	}

	/**
	 * Test {@link DSLDebugTargetAdapter#handleEvent(org.eclipse.gemoc.dsl.debug.ide.event.IDSLDebugEvent)}.
	 * 
	 * @throws DebugException
	 *             if fail
	 */
	@Test
	public void handleEventSetCurrentInstructionReply() throws DebugException {
		DebugTarget eDebugTarget = DebugPackage.eINSTANCE.getDebugFactory().createDebugTarget();
		eDebugTarget.setName("Debug target");
		final TestEventProcessor testEventProcessor = new TestEventProcessor();
		final DSLEclipseDebugIntegration integration = new DSLEclipseDebugIntegration("id", null,
				eDebugTarget, new ModelUpdater(), testEventProcessor);
		final DSLDebugTargetAdapter debugTarget = integration.getDebugTarget();
		TestDebugEventSetListener listener = new TestDebugEventSetListener();

		createThreads(eDebugTarget);
		debugTarget.getThreads(); // force adapter creation to avoid create event

		final EObject instruction = DebugPackage.eINSTANCE.getDebugFactory().createVariable();
		DebugPlugin.getDefault().addDebugEventListener(listener);
		try {
			debugTarget.handleEvent(new SetCurrentInstructionReply(eDebugTarget.getThreads().get(
					suspendedThreadIndex).getName(), instruction, false));
			listener.waitForEvent();
		} finally {
			DebugPlugin.getDefault().removeDebugEventListener(listener);
		}

		assertEquals(instruction, eDebugTarget.getThreads().get(suspendedThreadIndex).getTopStackFrame()
				.getCurrentInstruction());

		assertEquals(1, listener.getEventsList().size());
		DebugEvent[] events = listener.getEventsList().get(0);
		assertEquals(1, events.length);
		DebugEvent event = events[0];
		assertTrue(event.getSource() == integration.getThread(eDebugTarget.getThreads().get(
				suspendedThreadIndex)));
		assertEquals(DebugEvent.CHANGE, event.getKind());
		assertEquals(DebugEvent.CONTENT, event.getDetail());
	}

	/**
	 * Test {@link DSLDebugTargetAdapter#handleEvent(org.eclipse.gemoc.dsl.debug.ide.event.IDSLDebugEvent)}.
	 * 
	 * @throws DebugException
	 *             if fail
	 */
	@Test
	public void handleEventSetVariableValueReply() throws DebugException {
		DebugTarget eDebugTarget = DebugPackage.eINSTANCE.getDebugFactory().createDebugTarget();
		eDebugTarget.setName("Debug target");
		final TestEventProcessor testEventProcessor = new TestEventProcessor();
		final DSLEclipseDebugIntegration integration = new DSLEclipseDebugIntegration("id", null,
				eDebugTarget, new ModelUpdater(), testEventProcessor);
		final DSLDebugTargetAdapter debugTarget = integration.getDebugTarget();
		TestDebugEventSetListener listener = new TestDebugEventSetListener();

		createThreads(eDebugTarget);
		debugTarget.getThreads(); // force adapter creation to avoid create event
		debugTarget.handleEvent(new VariableReply(eDebugTarget.getThreads().get(suspendedThreadIndex)
				.getName(), eDebugTarget.getThreads().get(suspendedThreadIndex).getTopStackFrame().getName(),
				"Object", "variable", null, false));

		DebugPlugin.getDefault().addDebugEventListener(listener);
		try {
			debugTarget.handleEvent(new SetVariableValueReply(eDebugTarget.getThreads().get(
					suspendedThreadIndex).getName(), eDebugTarget.getThreads().get(suspendedThreadIndex)
					.getTopStackFrame().getName(), "variable", "value"));
			listener.waitForEvent();
		} finally {
			DebugPlugin.getDefault().removeDebugEventListener(listener);
		}

		assertEquals(1, eDebugTarget.getThreads().get(suspendedThreadIndex).getTopStackFrame().getVariables()
				.size());

		assertEquals(1, listener.getEventsList().size());
		DebugEvent[] events = listener.getEventsList().get(0);
		assertEquals(1, events.length);
		DebugEvent event = events[0];
		assertTrue(event.getSource() == integration.getThread(eDebugTarget.getThreads().get(
				suspendedThreadIndex)));
		assertEquals(DebugEvent.CHANGE, event.getKind());
		assertEquals(DebugEvent.CONTENT, event.getDetail());
	}

}
