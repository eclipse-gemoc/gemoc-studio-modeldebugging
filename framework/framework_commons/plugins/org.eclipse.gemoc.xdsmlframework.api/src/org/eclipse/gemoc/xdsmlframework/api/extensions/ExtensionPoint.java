/*******************************************************************************
 * Copyright (c) 2016, 2017 Inria and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Inria - initial API and implementation
 *******************************************************************************/
package org.eclipse.gemoc.xdsmlframework.api.extensions;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;

public abstract class ExtensionPoint<T extends Extension>
{

	private Class<? extends Extension> _class;

	protected ExtensionPoint(Class<? extends Extension> extensionClazz)
	{
	    _class = extensionClazz;
	}
	
	private T createInstance() 
			throws InstantiationException, IllegalAccessException
	{
		@SuppressWarnings("unchecked") T instance = (T)_class.newInstance();
		instance.setSpecification(null);
		return instance;
	}

	protected abstract String getExtensionPointName();
	
	protected Collection<T> internal_getSpecifications()
	{
		IConfigurationElement[] confElements = getExtensions(getExtensionPointName());
		ArrayList<T> specs = new ArrayList<T>();
		for (int i = 0; i < confElements.length; i++) 
		{						
			try 
			{
				T s = createInstance();
				s.setSpecification(confElements[i]);
				specs.add(s);
			} 
			catch (InstantiationException | IllegalAccessException e) 
			{
				// silent catch
				e.printStackTrace();
				//throw new ExtensionPointException(e.getMessage(), e);
			} 
		}	
		return specs;
	}
	
	protected static IConfigurationElement[] getExtensions(String extensionPointName) {
		return Platform.getExtensionRegistry().getConfigurationElementsFor(extensionPointName);
	}

}
