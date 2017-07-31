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
package org.eclipse.gemoc.dsl.debug.ide.ui.handler;

import org.eclipse.gemoc.dsl.debug.ide.ui.DSLToggleBreakpointsUtils;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.handlers.HandlerUtil;

/**
 * Toggles a {@link org.eclipse.gemoc.dsl.debug.ide.DSLBreakpoint DSLBreakpoint}.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public abstract class AbstractToggleBreakpointHandler extends AbstractHandler {

	/**
	 * The {@link DSLToggleBreakpointsUtils}.
	 */
	protected final DSLToggleBreakpointsUtils breakpointUtils;

	/**
	 * Constructor.
	 */
	public AbstractToggleBreakpointHandler() {
		breakpointUtils = new DSLToggleBreakpointsUtils(getModelIdentifier());
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.core.commands.AbstractHandler#execute(org.eclipse.core.commands.ExecutionEvent)
	 */
	public Object execute(ExecutionEvent event) throws ExecutionException {
		final ISelection selection = HandlerUtil.getCurrentSelectionChecked(event);
		try {
			breakpointUtils.toggleBreakpoints(selection);
		} catch (CoreException e) {
			throw new ExecutionException("Error while toggling breakpoint.", e);
		}

		return null;
	}

	/**
	 * Gets the debug model identifier.
	 * 
	 * @return the debug model identifier
	 */
	protected abstract String getModelIdentifier();

}
