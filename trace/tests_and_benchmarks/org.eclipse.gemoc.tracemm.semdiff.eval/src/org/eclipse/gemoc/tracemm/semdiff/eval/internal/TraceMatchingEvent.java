/*******************************************************************************
 * Copyright (c) 2016 Inria and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Inria - initial API and implementation
 *******************************************************************************/
package org.eclipse.gemoc.tracemm.semdiff.eval.internal;

public class TraceMatchingEvent {

	private long timepoint = -1;
	private EventType type;
	
	public TraceMatchingEvent(long timepoint, EventType type) {
		this.timepoint = timepoint;
		this.type = type;
	}
	
	public long getTimepoint() {
		return timepoint;
	}
	
	public EventType getType() {
		return type;
	}
	
	public enum EventType {
		INITIALIZATION_START,
		INITIALIZATION_END,
		MATCHING_START,
		MATCHING_END
	}
}
