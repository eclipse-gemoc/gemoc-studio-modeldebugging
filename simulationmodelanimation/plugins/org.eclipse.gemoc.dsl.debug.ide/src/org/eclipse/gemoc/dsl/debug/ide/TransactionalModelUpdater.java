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
package org.eclipse.gemoc.dsl.debug.ide;

import org.eclipse.gemoc.dsl.debug.DebugTarget;
import org.eclipse.gemoc.dsl.debug.DebugTargetUtils;
import org.eclipse.gemoc.dsl.debug.StackFrame;
import org.eclipse.gemoc.dsl.debug.Thread;
import org.eclipse.gemoc.dsl.debug.ThreadUtils;
import org.eclipse.gemoc.dsl.debug.Variable;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;

/**
 * An EMF transaction {@link IModelUpdater}.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class TransactionalModelUpdater extends ModelUpdater {

	/**
	 * The {@link TransactionalEditingDomain} to use.
	 */
	protected final TransactionalEditingDomain domain;

	/**
	 * Constructor.
	 * 
	 * @param domain
	 *            the {@link TransactionalEditingDomain} to use
	 */
	public TransactionalModelUpdater(TransactionalEditingDomain domain) {
		this.domain = domain;
	}

	/**
	 * Executes the given {@link Command}.
	 * 
	 * @param command
	 *            the {@link Command}
	 */
	protected void doExecute(Command command) {
		domain.getCommandStack().execute(command);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.gemoc.dsl.debug.ide.IModelUpdater#terminateRequest(org.eclipse.gemoc.dsl.debug.DebugTarget)
	 */
	public void terminateRequest(final DebugTarget target) {
		final Command command = new RecordingCommand(domain, "Terminate Request") {

			@Override
			protected void doExecute() {
				DebugTargetUtils.terminateRequest(target);
			}

		};
		doExecute(command);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.gemoc.dsl.debug.ide.IModelUpdater#disconnectRequest(org.eclipse.gemoc.dsl.debug.DebugTarget)
	 */
	public void disconnectRequest(final DebugTarget target) {
		final Command command = new RecordingCommand(domain, "Disconnect Request") {

			@Override
			protected void doExecute() {
				DebugTargetUtils.disconnectRequest(target);
			}

		};
		doExecute(command);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.gemoc.dsl.debug.ide.IModelUpdater#spawnRunningThreadReply(org.eclipse.gemoc.dsl.debug.DebugTarget,
	 *      java.lang.String, org.eclipse.emf.ecore.EObject)
	 */
	public void spawnRunningThreadReply(final DebugTarget target, final String threadName,
			final EObject threadContext) {
		final Command command = new RecordingCommand(domain, "Spawn Running Thread Reply") {

			@Override
			protected void doExecute() {
				DebugTargetUtils.spawnRunningThreadReply(target, threadName, threadContext);
			}

		};
		doExecute(command);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.gemoc.dsl.debug.ide.IModelUpdater#terminatedReply(org.eclipse.gemoc.dsl.debug.DebugTarget)
	 */
	public void terminatedReply(final DebugTarget target) {
		final Command command = new RecordingCommand(domain, "Terminated Reply") {

			@Override
			protected void doExecute() {
				DebugTargetUtils.terminatedReply(target);
			}

		};
		doExecute(command);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.gemoc.dsl.debug.ide.IModelUpdater#deleteVariableReply(org.eclipse.gemoc.dsl.debug.Thread,
	 *      java.lang.String)
	 */
	public void deleteVariableReply(final Thread thread, final String name) {
		final Command command = new RecordingCommand(domain, "Delete Variable Reply") {

			@Override
			protected void doExecute() {
				ThreadUtils.deleteVariableReply(thread, name);
			}

		};
		doExecute(command);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.gemoc.dsl.debug.ide.IModelUpdater#popStackFrameReply(org.eclipse.gemoc.dsl.debug.Thread)
	 */
	public StackFrame popStackFrameReply(final Thread thread) {
		final Command command = new RecordingCommand(domain, "Pop Stack Frame Reply") {
			StackFrame res;

			@Override
			protected void doExecute() {
				res = ThreadUtils.popStackFrameReply(thread);
			}

			@Override
			public Collection<?> getResult() {
				final java.util.List<Object> list = new ArrayList<Object>();
				list.add(res);
				return list;
			}

		};
		doExecute(command);
		return (StackFrame)command.getResult().iterator().next();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.gemoc.dsl.debug.ide.IModelUpdater#pushStackFrameReply(org.eclipse.gemoc.dsl.debug.Thread,
	 *      java.lang.String, org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EObject, boolean)
	 */
	public StackFrame pushStackFrameReply(final Thread thread, final String name, final EObject context,
			final EObject instruction, final boolean canStepInto) {
		final Command command = new RecordingCommand(domain, "Push Stack Frame Reply") {
			StackFrame res;

			@Override
			protected void doExecute() {
				res = ThreadUtils.pushStackFrameReply(thread, name, context, instruction, canStepInto);
			}

			@Override
			public Collection<?> getResult() {
				final java.util.List<Object> list = new ArrayList<Object>();
				list.add(res);
				return list;
			}

		};
		doExecute(command);
		return (StackFrame)command.getResult().iterator().next();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.gemoc.dsl.debug.ide.IModelUpdater#stepIntoReply(org.eclipse.gemoc.dsl.debug.Thread)
	 */
	public void stepIntoReply(final Thread thread) {
		final Command command = new RecordingCommand(domain, "Step Into Reply") {

			@Override
			protected void doExecute() {
				ThreadUtils.stepIntoReply(thread);
			}

		};
		doExecute(command);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.gemoc.dsl.debug.ide.IModelUpdater#stepOverReply(org.eclipse.gemoc.dsl.debug.Thread)
	 */
	public void stepOverReply(final Thread thread) {
		final Command command = new RecordingCommand(domain, "Step Over Reply") {

			@Override
			protected void doExecute() {
				ThreadUtils.stepOverReply(thread);
			}

		};
		doExecute(command);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.gemoc.dsl.debug.ide.IModelUpdater#stepReturnReply(org.eclipse.gemoc.dsl.debug.Thread)
	 */
	public void stepReturnReply(final Thread thread) {
		final Command command = new RecordingCommand(domain, "Step Return Reply") {

			@Override
			protected void doExecute() {
				ThreadUtils.stepReturnReply(thread);
			}

		};
		doExecute(command);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.gemoc.dsl.debug.ide.IModelUpdater#resumedReply(org.eclipse.gemoc.dsl.debug.Thread)
	 */
	public void resumedReply(final Thread thread) {
		final Command command = new RecordingCommand(domain, "Resumed Reply") {

			@Override
			protected void doExecute() {
				ThreadUtils.resumedReply(thread);
			}

		};
		doExecute(command);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.gemoc.dsl.debug.ide.IModelUpdater#setCurrentInstructionReply(org.eclipse.gemoc.dsl.debug.Thread,
	 *      org.eclipse.emf.ecore.EObject, boolean)
	 */
	public void setCurrentInstructionReply(final Thread thread, final EObject instruction,
			final boolean canStepInto) {
		final Command command = new RecordingCommand(domain, "Set Current Instruction Reply") {

			@Override
			protected void doExecute() {
				ThreadUtils.setCurrentInstructionReply(thread, instruction, canStepInto);
			}

		};
		doExecute(command);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.gemoc.dsl.debug.ide.IModelUpdater#suspendedReply(org.eclipse.gemoc.dsl.debug.Thread)
	 */
	public void suspendedReply(final Thread thread) {
		final Command command = new RecordingCommand(domain, "Suspended Reply") {

			@Override
			protected void doExecute() {
				ThreadUtils.suspendedReply(thread);
			}

		};
		doExecute(command);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.gemoc.dsl.debug.ide.IModelUpdater#terminatedReply(org.eclipse.gemoc.dsl.debug.Thread)
	 */
	public void terminatedReply(final Thread thread) {
		final Command command = new RecordingCommand(domain, "Terminated Reply") {

			@Override
			protected void doExecute() {
				ThreadUtils.terminatedReply(thread);
			}

		};
		doExecute(command);
	}

	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.gemoc.dsl.debug.ide.ModelUpdater#setVariableReply(org.eclipse.gemoc.dsl.debug.StackFrame,
	 *      java.lang.String, java.lang.String, java.lang.Object, boolean)
	 */
	public void setVariableReply(final StackFrame stackFrame, final String declarationTypeName,
			final String name, final Object value, final boolean supportModifications) {
		final Command command = new RecordingCommand(domain, "Set Variable Reply") {

			@Override
			protected void doExecute() {
				ThreadUtils.setVariableReply(stackFrame, declarationTypeName, name, value,
						supportModifications);
			}

		};
		doExecute(command);
	}

	@Override
	public void setVariableValueReply(final Variable variable, final Object value) {
		final Command command = new RecordingCommand(domain, "Set Variable Reply") {

			@Override
			protected void doExecute() {
				ThreadUtils.setVariableValueReply(variable, value);
			}

		};
		doExecute(command);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.gemoc.dsl.debug.ide.IModelUpdater#terminateRequest(org.eclipse.gemoc.dsl.debug.Thread)
	 */
	public void terminateRequest(final Thread thread) {
		final Command command = new RecordingCommand(domain, "Terminate Request") {

			@Override
			protected void doExecute() {
				ThreadUtils.terminateRequest(thread);
			}

		};
		doExecute(command);
	}

}
