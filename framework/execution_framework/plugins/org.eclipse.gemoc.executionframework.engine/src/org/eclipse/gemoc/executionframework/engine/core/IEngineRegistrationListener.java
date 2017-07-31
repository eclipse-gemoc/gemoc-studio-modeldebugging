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

import org.eclipse.gemoc.xdsmlframework.api.core.IExecutionEngine;

/**
 * Listener interface used to be notified when engines are registered and unregistered from the Engine registry
 * 
 * @author Didier Vojtisek<didier.vojtisek@inria.fr>
 *
 */
public interface IEngineRegistrationListener {

	/**
	 * method called by the registry when an engine is registered
	 * @param engine the registered engine
	 */
	void engineRegistered(IExecutionEngine engine);

	/**
	 * method called by the registry when an engine is unregistered
	 * @param engine the unregistered engine
	 */
	void engineUnregistered(IExecutionEngine engine);
	
}
