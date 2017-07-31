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
package org.eclipse.gemoc.executionframework.engine.commons;

public class EngineContextException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8964957013028069014L;

	public EngineContextException(String message) 
	{
		super(message);
	}

	public EngineContextException(String message, Exception innerException) 
	{
		super(message, innerException);
	}

}
