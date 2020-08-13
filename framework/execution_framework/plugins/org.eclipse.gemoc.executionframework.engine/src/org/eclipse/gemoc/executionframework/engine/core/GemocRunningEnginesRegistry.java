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
package org.eclipse.gemoc.executionframework.engine.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Optional;

import org.eclipse.gemoc.xdsmlframework.api.core.IExecutionEngine;

public class GemocRunningEnginesRegistry {

	/**
	 * List of engines that have registered to be running in this eclipse
	 */
	protected HashMap<String, IExecutionEngine<?>> runningEngines = new HashMap<String, IExecutionEngine<?>>();

	/**
	 * Add the given engine with this name
	 * 
	 * @param baseName
	 *                     (not used !? should be removed from API)
	 * @param engine
	 *                     to add
	 * @return the unique id used for this engine
	 */
	public synchronized String registerEngine(String baseName, IExecutionEngine<?> engine) {
		int uniqueInstance = 0;
		String engineId = Integer.toString(uniqueInstance);
		synchronized (runningEngines) {
			while (runningEngines.containsKey(engineId)) {
				uniqueInstance = uniqueInstance + 1;
				engineId = Integer.toString(uniqueInstance);
			}
			runningEngines.put(engineId, engine);
		}
		notifyEngineRegistered(engine);
		return engineId;
	}

	/**
	 * remove the engine using its ID in the registry
	 * @param engineUID
	 */
	public void unregisterEngine(String engineUID) {
		synchronized (runningEngines) {
			IExecutionEngine<?> engine = runningEngines.get(engineUID);
			if (engine != null) {
				runningEngines.remove(engineUID);
				notifyEngineUnregistered(engine);
			}
		}
	}
	
	public void unregisterEngine(IExecutionEngine<?> engine) {
		synchronized (runningEngines) {
			for (Entry<String, IExecutionEngine<?>> es : runningEngines.entrySet()) {
				if (es.getValue().equals(engine)) {
					runningEngines.remove(es.getKey());
					return;
				}
			}
		}
	}

	public HashMap<String, IExecutionEngine<?>> getRunningEngines() {
		synchronized (runningEngines) {
			return new HashMap<String, IExecutionEngine<?>>(runningEngines);
		}
	}

	private List<IEngineRegistrationListener> _engineRegistrationListeners = new ArrayList<IEngineRegistrationListener>();

	private void notifyEngineRegistered(IExecutionEngine<?> engine) {
		synchronized (_engineRegistrationListeners) {
			for (IEngineRegistrationListener l : _engineRegistrationListeners) {
				l.engineRegistered(engine);
			}
		}
	}

	private void notifyEngineUnregistered(IExecutionEngine<?> engine) {
		synchronized (_engineRegistrationListeners) {
			for (IEngineRegistrationListener l : _engineRegistrationListeners) {
				l.engineUnregistered(engine);
			}
		}
	}

	public void addEngineRegistrationListener(IEngineRegistrationListener listener) {
		synchronized (_engineRegistrationListeners) {
			_engineRegistrationListeners.add(listener);
		}
	}

	public void removeEngineRegistrationListener(IEngineRegistrationListener listener) {
		synchronized (_engineRegistrationListeners) {
			_engineRegistrationListeners.remove(listener);
		}
	}

	/**
	 * return the unique name used as key in this registry for the searched engine
	 * 
	 * @param searchedEngine
	 * @return
	 */
	public Optional<String> getIdForEngine(IExecutionEngine<?> searchedEngine) {
		synchronized (runningEngines) {
			for (Entry<String, IExecutionEngine<?>> es : runningEngines.entrySet()) {
				if (es.getValue().equals(searchedEngine)) {
					return Optional.of(es.getKey());
				}
			}
			return Optional.empty();
		}
	}

}
