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
package org.eclipse.gemoc.executionframework.ui.views.engine;

import org.eclipse.ui.IViewSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.gemoc.commons.eclipse.ui.ViewHelper;
import org.eclipse.gemoc.executionframework.ui.Activator;

/**
 * Views that are dependent on the engine selection in the EnginesStatusView may subclass this to get registered to it
 *
 */
public abstract class EngineSelectionDependentViewPart extends ViewPart implements
		IEngineSelectionListener {

	
	public EngineSelectionDependentViewPart()
	{
	}
	
	@Override
	public void init(IViewSite site) throws PartInitException {
		super.init(site);
		startListeningToEngineSelectionChange();
	}
	
	@Override
	public void dispose() {
		super.dispose();
		stopListeningToEngineSelectionChange();
	}


	protected void startListeningToEngineSelectionChange() {
		// make sure the EngineStatusView is open
		ViewHelper.retrieveView(EnginesStatusView.ID);
		// register this view as listener
		Activator.getDefault().getEngineSelectionManager().addEngineSelectionListener(this);
	}

	protected void stopListeningToEngineSelectionChange() {
		Activator.getDefault().getEngineSelectionManager().removeEngineSelectionListener(this);
	}


}
