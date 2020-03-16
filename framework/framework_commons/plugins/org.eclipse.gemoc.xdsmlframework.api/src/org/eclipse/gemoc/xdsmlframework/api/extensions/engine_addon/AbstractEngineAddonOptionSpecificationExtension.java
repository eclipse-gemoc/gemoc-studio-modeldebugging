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
package org.eclipse.gemoc.xdsmlframework.api.extensions.engine_addon;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.gemoc.xdsmlframework.api.extensions.Extension;

public class AbstractEngineAddonOptionSpecificationExtension extends Extension
{

	public AbstractEngineAddonOptionSpecificationExtension() {
	}
	public AbstractEngineAddonOptionSpecificationExtension(IConfigurationElement configurationElement) {
		_configurationElement = configurationElement;
	}
	
	public String getId()
	{
		return getAttribute(EngineAddonSpecificationExtensionPoint.GEMOC_ENGINE_ADDON_EXTENSION_POINT_OPTION_ID);
	}

	public String getName()
	{
		return getAttribute(EngineAddonSpecificationExtensionPoint.GEMOC_ENGINE_ADDON_EXTENSION_POINT_OPTION_NAME);
	}
	
	public String getShortDescription()
	{
		return getAttribute(EngineAddonSpecificationExtensionPoint.GEMOC_ENGINE_ADDON_EXTENSION_POINT_OPTION_SHORTDESCRIPTION);
	}
	
		
}
