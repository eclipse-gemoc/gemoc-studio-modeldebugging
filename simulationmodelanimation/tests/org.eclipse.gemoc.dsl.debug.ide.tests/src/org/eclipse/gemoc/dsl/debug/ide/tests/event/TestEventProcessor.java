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
package org.eclipse.gemoc.dsl.debug.ide.tests.event;

import org.eclipse.gemoc.dsl.debug.ide.event.IDSLDebugEvent;
import org.eclipse.gemoc.dsl.debug.ide.event.IDSLDebugEventProcessor;

import java.util.ArrayList;
import java.util.List;

/**
 * A test implementation of {@link IDSLDebugEventProcessor} that log
 * {@link IDSLDebugEventProcessor#handleEvent(IDSLDebugEvent) handled} {@link IDSLDebugEvent event}.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class TestEventProcessor implements IDSLDebugEventProcessor {

	/**
	 * Logged {@link IDSLDebugEvent event}.
	 */
	private final List<IDSLDebugEvent> events = new ArrayList<IDSLDebugEvent>();

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.gemoc.dsl.debug.ide.event.IDSLDebugEventProcessor#handleEvent(org.eclipse.gemoc.dsl.debug.ide.event.IDSLDebugEvent)
	 */
	public Object handleEvent(IDSLDebugEvent event) {
		events.add(event);
		return null;
	}

	public List<IDSLDebugEvent> getEvents() {
		return events;
	}

}
