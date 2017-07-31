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
package org.eclipse.gemoc.xdsmlframework.api.extensions.engine_addon_group;

import org.eclipse.gemoc.xdsmlframework.api.extensions.Extension;

public class EngineAddonGroupSpecificationExtension extends Extension
{

	public String getId()
	{
		return getAttribute(EngineAddonGroupSpecificationExtensionPoint.GEMOC_ENGINE_ADDON_GROUP_EXTENSION_POINT_ID);
	}

	public String getName()
	{
		return getAttribute(EngineAddonGroupSpecificationExtensionPoint.GEMOC_ENGINE_ADDON_GROUP_EXTENSION_POINT_NAME);
	}
	
}
