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

import java.util.Collection;

import org.eclipse.gemoc.xdsmlframework.api.extensions.ExtensionPoint;

public class DeciderSpecificationExtensionPoint extends ExtensionPoint<DeciderSpecificationExtension>
{

	public static final String GEMOC_DECIDER_EXTENSION_POINT = "org.eclipse.gemoc.gemoc_language_workbench.deciders";
	public static final String GEMOC_DECIDER_EXTENSION_POINT_NAME = "Name";
	public static final String GEMOC_DECIDER_EXTENSION_POINT_CLASS = "Class";
	public static final String GEMOC_DECIDER_EXTENSION_POINT_Description = "Description";
	public static final String GEMOC_DECIDER_EXTENSION_POINT_ICONPATH = "Icon";


	protected DeciderSpecificationExtensionPoint() 
	{
		super(DeciderSpecificationExtension.class);
	}

	
	private static DeciderSpecificationExtensionPoint _singleton;
	
	private static DeciderSpecificationExtensionPoint getExtensionPoint()
	{
		if (_singleton == null)
		{
			_singleton = new DeciderSpecificationExtensionPoint();
		}
		return _singleton;
	}
		
	static public Collection<DeciderSpecificationExtension> getSpecifications() 
	{
		return getExtensionPoint().internal_getSpecifications();
	}


	@Override
	protected String getExtensionPointName()
	{
		return GEMOC_DECIDER_EXTENSION_POINT;
	}
	
	static public DeciderSpecificationExtension findDefinition(String deciderName)
	{
		for (DeciderSpecificationExtension extension : getSpecifications())
		{
			if (extension.getName().equals(deciderName))
			{
				return extension;
			}
		}
		return null;
	}
}
