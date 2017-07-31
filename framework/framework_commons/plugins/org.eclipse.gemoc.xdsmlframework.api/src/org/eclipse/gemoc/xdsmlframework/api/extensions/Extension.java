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

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Status;
import org.eclipse.gemoc.xdsmlframework.api.Activator;

public class Extension 
{

	protected IConfigurationElement _configurationElement;

	void setSpecification(IConfigurationElement configurationElement) 
	{
		_configurationElement = configurationElement;
	}

	protected String getAttribute(String attributeName) {
		return _configurationElement.getAttribute(attributeName);
	}

	protected Object instanciate(String attributeName) throws CoreException {
		try
		{
			return _configurationElement.createExecutableExtension(attributeName);
		}
		catch(CoreException e)
		{
			String message = "Instanciation of one agent failed: " + e.getMessage() + " (see inner exception for more detail).";
			CoreException exception = new CoreException(new Status(Status.ERROR, Activator.PLUGIN_ID, message, e));
			throw exception;
		}
	}

	protected void throwInstanciationCoreException() throws CoreException {
		String message = "Instanciation succeeded but object is not of correct type.";
		CoreException exception = new CoreException(new Status(Status.ERROR, Activator.PLUGIN_ID, message));
		throw exception;
	}


}
