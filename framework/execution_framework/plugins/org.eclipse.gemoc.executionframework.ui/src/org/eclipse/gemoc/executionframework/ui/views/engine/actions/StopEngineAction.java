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
package org.eclipse.gemoc.executionframework.ui.views.engine.actions;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.ISharedImages;
import org.eclipse.gemoc.commons.eclipse.ui.ViewHelper;
import org.eclipse.gemoc.executionframework.ui.Activator;
import org.eclipse.gemoc.executionframework.ui.views.engine.EnginesStatusView;
import org.eclipse.gemoc.xdsmlframework.api.core.EngineStatus.RunStatus;
import org.eclipse.gemoc.xdsmlframework.api.core.IExecutionEngine;

public class StopEngineAction extends AbstractEngineAction {

	public StopEngineAction() {
		super();
	}

	@Override
	protected void init() {
		super.init();
		setText("Stop");
		setToolTipText("Stop selected engines");
		ImageDescriptor id = Activator.imageDescriptorFromPlugin(Activator.PLUGIN_ID, ISharedImages.IMG_ELCL_STOP);
		setImageDescriptor(id);
	}

	@Override
	protected void updateButton() {
		super.updateButton();
	}

	@Override
	public void run() {
		EnginesStatusView view = ViewHelper.retrieveView(EnginesStatusView.ID);
		if (view.getSelectedEngine() != null) {
			view.getSelectedEngine().stop();
		} else {
			showMessage(view.getSite(), "please select an engine to stop");
		}
	}

	@Override
	public void engineSelectionChanged(IExecutionEngine engine) {
		_currentSelectedEngine = engine;

		if (_currentSelectedEngine == null) {
			setEnabled(false);
		} else {
			setEnabled(!_currentSelectedEngine.getRunningStatus().equals(RunStatus.Stopped));

		}
	}

}
