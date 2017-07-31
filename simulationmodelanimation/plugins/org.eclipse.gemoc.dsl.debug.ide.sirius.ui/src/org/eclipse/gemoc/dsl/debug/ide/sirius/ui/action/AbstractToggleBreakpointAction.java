/*******************************************************************************
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.gemoc.dsl.debug.ide.sirius.ui.action;

import org.eclipse.gemoc.dsl.debug.ide.sirius.ui.DSLToggleBreakpointsUtils;
import org.eclipse.gemoc.dsl.debug.ide.sirius.ui.DebugSiriusIdeUiPlugin;

import java.util.Collection;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;
import org.eclipse.ui.PlatformUI;

/**
 * Toggle breakpoint {@link org.eclipse.sirius.tools.api.ui.IExternalJavaAction IExternalJavaAction}.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public abstract class AbstractToggleBreakpointAction implements IExternalJavaAction {

	/**
	 * Toggle breakpoint utility.
	 */
	private final DSLToggleBreakpointsUtils toggleUtility = createToggleBreakpointsUtils();

	/**
	 * Creates an instance of {@link DSLToggleBreakpointsUtils}.
	 * 
	 * @return the created {@link DSLToggleBreakpointsUtils

	 */
	protected DSLToggleBreakpointsUtils createToggleBreakpointsUtils() {
		return new DSLToggleBreakpointsUtils(getModelIdentifier());
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.sirius.tools.api.ui.IExternalJavaAction#execute(java.util.Collection, java.util.Map)
	 */
	public void execute(Collection<? extends EObject> selections, Map<String, Object> parameters) {
		final ISelection selection = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
				.getSelection();
		if (toggleUtility.canToggleBreakpoints(selection)) {
			try {
				toggleUtility.toggleBreakpoints(selection);
			} catch (CoreException e) {
				DebugSiriusIdeUiPlugin.getPlugin().getLog().log(
						new Status(IStatus.ERROR, DebugSiriusIdeUiPlugin.ID, e.getLocalizedMessage(), e));
			}
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.sirius.tools.api.ui.IExternalJavaAction#canExecute(java.util.Collection)
	 */
	public boolean canExecute(Collection<? extends EObject> selections) {
		final ISelection selection = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
				.getSelection();
		return toggleUtility.canToggleBreakpoints(selection);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.sirius.tools.api.ui.IExternalJavaAction2#mayDeleteElements()
	 */
	public boolean mayDeleteElements() {
		return false;
	}

	/**
	 * Gets the debug model identifier.
	 * 
	 * @return the debug model identifier
	 */
	protected abstract String getModelIdentifier();

}
