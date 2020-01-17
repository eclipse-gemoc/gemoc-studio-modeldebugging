/*******************************************************************************
 * Copyright (c) 2020 Inria and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Inria - initial API and implementation
 *******************************************************************************/
package org.eclipse.gemoc.xdsmlframework.api.engine_addon;

import java.util.List;
import java.util.stream.Collectors;


/**
 * Rule defining how to sort addon calls for a given engine event
 */
public class EngineAddonSortingRule {

	public enum EngineEvent {
		engineAboutToStart, engineStarted, engineInitialized, engineAboutToStop, engineStopped, engineAboutToDispose,
		aboutToSelectStep, proposedStepsChanged, stepSelected, aboutToExecuteStep, stepExecuted, engineStatusChanged
	};

	public enum Priority {
		BEFORE, AFTER
	};

	protected IEngineAddon owner;
	protected EngineEvent event;
	protected Priority priority;
	protected List<String> addonsWithTags;

	/**
	 * @param event
	 * @param priority
	 * @param addonsWithTags
	 */
	public EngineAddonSortingRule(IEngineAddon owner, EngineEvent event, Priority priority, List<String> addonsWithTags) {
		super();
		this.owner = owner;
		this.event = event;
		this.priority = priority;
		this.addonsWithTags = addonsWithTags;
	}

	public EngineEvent getEvent() {
		return event;
	}

	public Priority getPriority() {
		return priority;
	}

	public List<String> getAddonsWithTags() {
		return addonsWithTags;
	}
	
	public String toReadableString() {
		return owner.getAddonID()+" "+priority +" "+ addonsWithTags.stream().collect(Collectors.joining(", ", "[", "]"))+ " for "+event;
	}
	

}
