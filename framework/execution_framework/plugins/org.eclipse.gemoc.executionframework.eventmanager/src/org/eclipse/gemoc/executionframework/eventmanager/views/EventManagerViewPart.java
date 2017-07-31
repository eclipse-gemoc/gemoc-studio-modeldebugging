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
package org.eclipse.gemoc.executionframework.eventmanager.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.gemoc.executionframework.ui.views.engine.EngineSelectionDependentViewPart;
import org.eclipse.gemoc.xdsmlframework.api.core.IExecutionEngine;

import javafx.embed.swt.FXCanvas;
import javafx.scene.Scene;

public class EventManagerViewPart extends EngineSelectionDependentViewPart {

	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "org.eclipse.gemoc.executionframework.eventmanager.views.EventManager";

	private FXCanvas fxCanvas;
	
	private EventManagerRenderer eventManagerRenderer;

	public void createPartControl(Composite parent) {
		fxCanvas = new FXCanvas(parent, SWT.NONE);
		eventManagerRenderer = new EventManagerRenderer();
		Scene scene = new Scene(eventManagerRenderer);
		fxCanvas.setScene(scene);
		
		parent.getShell().addListener(SWT.Resize, (e) -> {
			
		});
	}

	public void setFocus() {
	}

	@Override
	public void engineSelectionChanged(IExecutionEngine engine) {
		eventManagerRenderer.setExecutedModel(engine.getExecutionContext().getResourceModel());
		engine.getExecutionContext().getExecutionPlatform().addEngineAddon(eventManagerRenderer);
	}
}
