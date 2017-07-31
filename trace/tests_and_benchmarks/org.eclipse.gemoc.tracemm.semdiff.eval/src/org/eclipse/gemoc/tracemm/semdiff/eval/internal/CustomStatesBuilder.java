/*
 * Copyright (c) 2014 Vienna University of Technology.
 * All rights reserved. This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0 which accompanies 
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * Tanja Mayerhofer - initial API and implementation
 */
package org.eclipse.gemoc.tracemm.semdiff.eval.internal;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.resource.Resource;
import org.modelexecution.fumldebug.core.event.ActivityEntryEvent;
import org.modelexecution.fumldebug.core.event.ActivityEvent;
import org.modelexecution.fumldebug.core.event.ActivityExitEvent;
import org.modelexecution.xmof.states.builder.StatesBuilder;

public class CustomStatesBuilder extends StatesBuilder {
	
	private ActivityEvent currentActivityEvent = null;
	private List<org.modelexecution.fumldebug.core.event.Event> handledEvents = new ArrayList<org.modelexecution.fumldebug.core.event.Event>();

	public CustomStatesBuilder(Resource modelResource) {
		super(modelResource);
	}

	@Override
	public void notify(org.modelexecution.fumldebug.core.event.Event event) {
		super.notify(event);
		if (isActivityEntry(event)) {
			currentActivityEvent = (ActivityEntryEvent)event;
		} else if (event instanceof ActivityExitEvent) {
			currentActivityEvent = (ActivityExitEvent)event;
		}
	}

	/**
	 * Only one state per activity execution is captured
	 */
	@Override
	protected boolean isNewStateRequired() {
		return !handledEvents.contains(currentActivityEvent);
	}
	
	@Override
	protected void addNewState() {
		handledEvents.add(currentActivityEvent);
		super.addNewState();
	}

}
