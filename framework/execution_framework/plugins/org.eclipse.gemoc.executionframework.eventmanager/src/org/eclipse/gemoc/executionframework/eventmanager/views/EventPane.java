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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.resource.Resource;

import fr.inria.diverse.k3.al.annotationprocessor.stepmanager.IEventManager;
import javafx.scene.layout.BorderPane;

public class EventPane extends BorderPane {

	private final EventTableView tableView;
	
	public EventPane(EClass eventClass, final Resource executedModel, final IEventManager eventManager) {
		tableView = new EventTableView(eventClass, executedModel, eventManager);
		setCenter(tableView);
		tableView.refreshEvents();
	}
}
