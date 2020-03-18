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

import java.util.Collection;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.gemoc.xdsmlframework.api.engine_addon.IEngineAddon;
import org.eclipse.gemoc.xdsmlframework.api.extensions.ExtensionPoint;

public class EngineAddonSpecificationExtensionPoint extends ExtensionPoint<EngineAddonSpecificationExtension>
{

	public static final String GEMOC_ENGINE_ADDON_EXTENSION_POINT = "org.eclipse.gemoc.gemoc_language_workbench.engine_addon";
	public static final String GEMOC_ENGINE_ADDON_EXTENSION_POINT_ID = "id";
	public static final String GEMOC_ENGINE_ADDON_EXTENSION_POINT_NAME = "name";
	public static final String GEMOC_ENGINE_ADDON_EXTENSION_POINT_SHORTDESCRIPTION = "shortDescription";
	public static final String GEMOC_ENGINE_ADDON_EXTENSION_POINT_CLASS = "class";
	public static final String GEMOC_ENGINE_ADDON_EXTENSION_POINT_DEFAULT = "default";
	public static final String GEMOC_ENGINE_ADDON_EXTENSION_POINT_ADDONGROUPID = "addonGroupId";
	public static final String GEMOC_ENGINE_ADDON_EXTENSION_POINT_OPENVIEWIDS = "openViewIds";

	public static final String GEMOC_ENGINE_ADDON_EXTENSION_POINT_BOOLEANOPTION = "addon_booleanOption";
	public static final String GEMOC_ENGINE_ADDON_EXTENSION_POINT_STRINGOPTION = "addon_stringOption";
	public static final String GEMOC_ENGINE_ADDON_EXTENSION_POINT_OPTION_ID = "id";
	public static final String GEMOC_ENGINE_ADDON_EXTENSION_POINT_OPTION_NAME = "name";
	public static final String GEMOC_ENGINE_ADDON_EXTENSION_POINT_OPTION_SHORTDESCRIPTION = "shortDescription";
	public static final String GEMOC_ENGINE_ADDON_EXTENSION_POINT_OPTION_DEFAULTVALUE = "defaultValue";

	protected EngineAddonSpecificationExtensionPoint() 
	{
		super(EngineAddonSpecificationExtension.class);
	}

	private static EngineAddonSpecificationExtensionPoint _singleton;
	
	private static EngineAddonSpecificationExtensionPoint getExtensionPoint()
	{
		if (_singleton == null)
		{
			_singleton = new EngineAddonSpecificationExtensionPoint();
		}
		return _singleton;
	}
		
	static public Collection<EngineAddonSpecificationExtension> getSpecifications()
	{
		return getExtensionPoint().internal_getSpecifications();
	}
	
	@Override
	protected String getExtensionPointName() 
	{
		return GEMOC_ENGINE_ADDON_EXTENSION_POINT;
	}
	
	/**
	 * Find the name of 'addon' in addon spectifications extension point.
	 * 
	 * Return null if not found
	 */
	public static String getName(IEngineAddon addon){
		
		for (EngineAddonSpecificationExtension engineAddonSpecificationExtension : getSpecifications()) {
			try {
				Class<? extends IEngineAddon> clazz = engineAddonSpecificationExtension.instanciateComponent().getClass();
				if(addon.getClass().equals(clazz)){
					return engineAddonSpecificationExtension.getName();
				}
				
			} catch (CoreException e) {}
		}
		
		return null;
	}
	
}
