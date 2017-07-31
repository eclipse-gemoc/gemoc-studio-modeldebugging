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
package org.eclipse.gemoc.xdsmlframework.api.extensions.languages;

import org.eclipse.gemoc.xdsmlframework.api.extensions.ExtensionPoint;

public abstract class LanguageDefinitionExtensionPoint extends ExtensionPoint<LanguageDefinitionExtension>
{


//	public static final String GEMOC_LANGUAGE_EXTENSION_POINT = "org.gemoc.gemoc_language_workbench.xdsml";
	public static final String GEMOC_LANGUAGE_EXTENSION_POINT_XDSML_DEF = "XDSML_Definition";
	public static final String GEMOC_LANGUAGE_EXTENSION_POINT_XDSML_DEF_NAME_ATT = "name";
	public static final String GEMOC_LANGUAGE_EXTENSION_POINT_XDSML_DEF_LOADMODEL_ATT = "modelLoader_class";
//	public static final String GEMOC_LANGUAGE_EXTENSION_POINT_XDSML_DEF_CODEEXECUTOR_ATT = "codeExecutor_class";
	public static final String GEMOC_LANGUAGE_EXTENSION_POINT_XDSML_DEF_XDSML_FILE_PATH_ATT = "xdsmlFilePath";
	public static final String GEMOC_LANGUAGE_EXTENSION_POINT_ENGINE_ADDON_DEF = "EngineAddon_Definition";
	public static final String GEMOC_LANGUAGE_EXTENSION_POINT_ENGINE_ADDON_DEF_ENGINE_ADDON_ATT = "engineAddon_class";
	public static final String GEMOC_LANGUAGE_EXTENSION_POINT_XDSML_CUSTOMIZATION_DEF = "XDSML_Definition_Customization";
	public static final String GEMOC_LANGUAGE_EXTENSION_POINT_XDSML_CUSTOMIZATION_DEF_FILEEXTENSIONS_ATT = "fileExtensions";
	
	
	protected LanguageDefinitionExtensionPoint() 
	{
		super(LanguageDefinitionExtension.class);
	}
//
//	private static LanguageDefinitionExtensionPoint _singleton;
//	
//	private static LanguageDefinitionExtensionPoint getExtensionPoint()
//	{
//		if (_singleton == null)
//		{
//			_singleton = new LanguageDefinitionExtensionPoint();
//		}
//		return _singleton;
//	}
		
//	static public Collection<LanguageDefinitionExtension> getSpecifications() 
//	{
//		return getExtensionPoint().internal_getSpecifications();
//	}
//	
//	static public LanguageDefinitionExtension findDefinition(String languageName)
//	{
//		for (LanguageDefinitionExtension extension : getSpecifications())
//		{
//			if (extension.getName().equals(languageName))
//			{
//				return extension;
//			}
//		}
//		return null;
//	}


	
//	@Override
//	protected String getExtensionPointName()
//	{
//		return GEMOC_LANGUAGE_EXTENSION_POINT;
//	}
		
}
