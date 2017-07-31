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
package org.eclipse.gemoc.dsl.debug.ide.ui;

import org.eclipse.gemoc.dsl.debug.ide.DSLBreakpoint;

import java.util.Iterator;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.Platform;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;

/**
 * Utility class for breakpoint {@link DSLBreakpoint} toggling.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class DSLToggleBreakpointsUtils {

	/**
	 * The debug model identifier.
	 */
	protected final String identifier;

	/**
	 * Constructor.
	 * 
	 * @param identifier
	 *            the debug model identifier
	 */
	public DSLToggleBreakpointsUtils(String identifier) {
		this.identifier = identifier;
	}

	/**
	 * Toggles a {@link DSLBreakpoint} for the given selection.
	 * 
	 * @param selection
	 *            the {@link ISelection}
	 * @throws CoreException
	 *             if {@link DSLBreakpoint} can't be retrieved or installed
	 */
	public void toggleBreakpoints(ISelection selection) throws CoreException {
		if (selection instanceof IStructuredSelection) {
			@SuppressWarnings("unchecked")
			final Iterator<Object> it = ((IStructuredSelection)selection).iterator();
			while (it.hasNext()) {
				final Object selected = it.next();
				EObject instruction = getInstruction(selected);
				if (instruction != null) {
					toggleBreakpoint(selected, instruction);
				}
			}
		}
	}

	/**
	 * Toggles the {@link DSLBreakpoint} for the given {@link EObject instruction}.
	 * 
	 * @param selected
	 *            the selected {@link Object}
	 * @param instruction
	 *            the {@link EObject instruction}
	 * @throws CoreException
	 *             if {@link DSLBreakpoint} can't be retrieved or installed
	 */
	protected void toggleBreakpoint(Object selected, EObject instruction) throws CoreException {
		DSLBreakpoint breakpoint = getBreakpoint(instruction);
		if (breakpoint != null) {
			breakpoint.delete();
		} else {
			breakpoint = createBreakpoint(selected, instruction);
			DebugPlugin.getDefault().getBreakpointManager().addBreakpoint(breakpoint);
		}
	}

	/**
	 * Creates a {@link DSLBreakpoint} for the given instruction.
	 * 
	 * @param selected
	 *            the selected {@link Object}
	 * @param instruction
	 *            the {@link EObject instruction}
	 * @return the created {@link DSLBreakpoint} for the given instruction
	 * @throws CoreException
	 *             if the {@link DSLBreakpoint} instantiation fails
	 */
	protected DSLBreakpoint createBreakpoint(Object selected, EObject instruction) throws CoreException {
		return new DSLBreakpoint(identifier, instruction, true);
	}

	/**
	 * Gets the {@link DSLBreakpoint} for the given {@link EObject instruction}.
	 * 
	 * @param instruction
	 *            the {@link EObject instruction}
	 * @return the {@link DSLBreakpoint} for the given {@link EObject instruction} if nay, <code>null</code>
	 *         otherwise
	 */
	protected DSLBreakpoint getBreakpoint(EObject instruction) {
		DSLBreakpoint res = null;

		IBreakpoint[] breakpoints = DebugPlugin.getDefault().getBreakpointManager()
				.getBreakpoints(identifier);
		final URI instructionURI = EcoreUtil.getURI(instruction);
		for (IBreakpoint breakpoint : breakpoints) {
			if (breakpoint instanceof DSLBreakpoint && ((DSLBreakpoint)breakpoint).getURI() != null
					&& ((DSLBreakpoint)breakpoint).getURI().equals(instructionURI)) {
				res = (DSLBreakpoint)breakpoint;
				break;
			}
		}

		return res;
	}

	/**
	 * Tells if we can toggle a {@link DSLBreakpoint} for the given {@link ISelection}.
	 * 
	 * @param selection
	 *            the {@link ISelection}
	 * @return <code>true</code> if we can toggle a {@link DSLBreakpoint} for the given {@link ISelection},
	 *         <code>false</code> otherwise
	 */
	public boolean canToggleBreakpoints(ISelection selection) {
		boolean res = false;

		if (selection instanceof IStructuredSelection) {
			@SuppressWarnings("unchecked")
			final Iterator<Object> it = ((IStructuredSelection)selection).iterator();
			while (it.hasNext()) {
				final Object selected = it.next();
				if (selected != null) {
					res = getInstruction(selected) != null;
					break;
				}
			}
		}

		return res;
	}

	/**
	 * Gets an {@link EObject instruction} from the given {@link Object}.
	 * 
	 * @param selected
	 *            the {@link Object}
	 * @return the corresponding {@link EObject instruction} or <code>null</code> if no {@link EObject
	 *         instruction} match
	 */
	protected EObject getInstruction(Object selected) {
		final EObject res;

		if (selected instanceof EObject) {
			res = (EObject)selected;
		} else if (selected instanceof IAdaptable) {
			final EObject adapter = (EObject)((IAdaptable)selected).getAdapter(EObject.class);
			if (adapter != null) {
				res = adapter;
			} else {
				res = (EObject)Platform.getAdapterManager().getAdapter(selected, EObject.class);
			}
		} else if (selected != null) {
			res = (EObject)Platform.getAdapterManager().getAdapter(selected, EObject.class);
		} else {
			res = null;
		}

		return res;
	}

}
