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
package org.eclipse.gemoc.dsl.debug.ide.tests.adapter;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.debug.core.DebugEvent;
import org.eclipse.debug.core.IDebugEventSetListener;

/**
 * Implementation of {@link IDebugEventSetListener} for test purpose.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class TestDebugEventSetListener implements IDebugEventSetListener {

	/**
	 * {@link List} of received {@link DebugEvent}.
	 */
	final List<DebugEvent[]> eventsList = new ArrayList<DebugEvent[]>();

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.debug.core.IDebugEventSetListener#handleDebugEvents(org.eclipse.debug.core.DebugEvent[])
	 */
	public synchronized void handleDebugEvents(DebugEvent[] events) {
		eventsList.add(events);
		notify();
	}

	/**
	 * Gets the {@link List} of received {@link DebugEvent}.
	 * 
	 * @return the {@link List} of received {@link DebugEvent}
	 */
	public List<DebugEvent[]> getEventsList() {
		return eventsList;
	}

	/**
	 * Wait until an a call to {@link TestDebugEventSetListener#handleDebugEvents(DebugEvent[])} is done.
	 */
	public synchronized void waitForEvent() {
		try {
			wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
