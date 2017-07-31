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


/**
 * Exception thrown when the user stop the engine in plainK3 mode or if the engine is stopped normally
 * This allows to catch it and adapt the Engine response in this normal situation
 * @author Didier Vojtisek<didier.vojtisek@inria.fr>
 *
 */
public class EngineStoppedException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8485492707115640348L;
	
	

	public EngineStoppedException(String message) {
		super(message);
	}
	
	public EngineStoppedException(String message, Throwable t) {
		super(message, t);
	}

}
