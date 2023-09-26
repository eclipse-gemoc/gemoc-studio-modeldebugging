/*******************************************************************************
 * Copyright (c) 2017 INRIA and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     INRIA - initial API and implementation
 *     I3S Laboratory - API update and bug fix
 *******************************************************************************/
package org.eclipse.gemoc.executionframework.engine.concurrency.deciders;

import java.net.URL;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Platform;
import org.eclipse.gemoc.executionframework.engine.concurrency.ILogicalStepDecider;
import org.eclipse.gemoc.xdsmlframework.api.extensions.Extension;
import org.osgi.framework.Bundle;

public class DeciderSpecificationExtension extends Extension
{

	private String getIconPath()
	{
		return getAttribute(DeciderSpecificationExtensionPoint.GEMOC_DECIDER_EXTENSION_POINT_ICONPATH);
	}
	
	public URL getIconURL() 
	{
		String bundleId = _configurationElement.getDeclaringExtension().getNamespaceIdentifier();
		Bundle bundle = Platform.getBundle(bundleId);
		return bundle.getResource(getIconPath());
	}

	public String getName() 
	{
		return getAttribute(DeciderSpecificationExtensionPoint.GEMOC_DECIDER_EXTENSION_POINT_NAME);
	}

	public String getDescription() 
	{
		return getAttribute(DeciderSpecificationExtensionPoint.GEMOC_DECIDER_EXTENSION_POINT_Description);
	}

	public String getDeciderClassName() 
	{
		return getAttribute(DeciderSpecificationExtensionPoint.GEMOC_DECIDER_EXTENSION_POINT_CLASS);
	}

	public ILogicalStepDecider instanciateDecider() throws CoreException 
	{
		return (ILogicalStepDecider)_configurationElement.createExecutableExtension(DeciderSpecificationExtensionPoint.GEMOC_DECIDER_EXTENSION_POINT_CLASS);
	}
	
}
