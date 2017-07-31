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
package org.eclipse.gemoc.executionframework.debugger.ui.breakpoints;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.debug.ui.IDetailPane;
import org.eclipse.debug.ui.IDetailPaneFactory;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.gemoc.executionframework.debugger.GemocBreakpoint;

import org.eclipse.gemoc.dsl.debug.ide.DSLBreakpoint;

/**
 * The {@link IDetailPaneFactory} creating {@link GemocBreakpointDetailPane}.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 *
 */
public class BreakpointDetailPaneFactory implements IDetailPaneFactory {

	@Override
	public Set<String> getDetailPaneTypes(IStructuredSelection selection) {
		final Set<String> res = new HashSet<String>();

		final IBreakpoint breakpoint = (IBreakpoint) selection
				.getFirstElement();
		if (breakpoint instanceof DSLBreakpoint) {
			res.add(GemocBreakpointDetailPane.PANEL_TYPE);
		}

		return res;
	}

	@Override
	public String getDefaultDetailPane(IStructuredSelection selection) {
		final String res;

		final IBreakpoint breakpoint = (IBreakpoint) selection
				.getFirstElement();
		if (breakpoint instanceof GemocBreakpoint) {
			res = GemocBreakpointDetailPane.PANEL_TYPE;
		} else {
			res = null;
		}

		return res;
	}

	@Override
	public IDetailPane createDetailPane(String paneID) {
		final IDetailPane res;

		if (GemocBreakpointDetailPane.PANEL_TYPE.equals(paneID)) {
			res = new GemocBreakpointDetailPane();
		} else {
			res = null;
		}

		return res;
	}

	@Override
	public String getDetailPaneName(String paneID) {
		final String res;

		if (GemocBreakpointDetailPane.PANEL_TYPE.equals(paneID)) {
			res = "Gemoc breakpoint settings";
		} else {
			res = null;
		}

		return res;
	}

	@Override
	public String getDetailPaneDescription(String paneID) {
		return getDetailPaneName(paneID);
	}
	
}
