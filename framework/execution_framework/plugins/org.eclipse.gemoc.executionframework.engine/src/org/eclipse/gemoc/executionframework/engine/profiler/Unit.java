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
package org.eclipse.gemoc.executionframework.engine.profiler;

public class Unit {

	private String _unitName;
	
	public Unit(String unitName) 
	{
		_unitName = unitName;
	}

	public String getName()
	{
		return _unitName;
	}
	
}
