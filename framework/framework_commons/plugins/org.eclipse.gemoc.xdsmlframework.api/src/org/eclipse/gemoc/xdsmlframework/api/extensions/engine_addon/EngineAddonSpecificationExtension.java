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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.gemoc.xdsmlframework.api.engine_addon.IEngineAddon;
import org.eclipse.gemoc.xdsmlframework.api.extensions.Extension;
import org.eclipse.gemoc.xdsmlframework.api.extensions.languages.LanguageDefinitionExtensionPoint;

public class EngineAddonSpecificationExtension extends Extension
{

	public EngineAddonSpecificationExtension() {
	}
	public EngineAddonSpecificationExtension(IConfigurationElement configurationElement) {
		_configurationElement = configurationElement;
	}
	
	public String getId()
	{
		return getAttribute(EngineAddonSpecificationExtensionPoint.GEMOC_ENGINE_ADDON_EXTENSION_POINT_ID);
	}

	public String getName()
	{
		return getAttribute(EngineAddonSpecificationExtensionPoint.GEMOC_ENGINE_ADDON_EXTENSION_POINT_NAME);
	}
	
	public String getShortDescription()
	{
		return getAttribute(EngineAddonSpecificationExtensionPoint.GEMOC_ENGINE_ADDON_EXTENSION_POINT_SHORTDESCRIPTION);
	}
	
	public String getAddonGroupId()
	{
		return getAttribute(EngineAddonSpecificationExtensionPoint.GEMOC_ENGINE_ADDON_EXTENSION_POINT_ADDONGROUPID);
	}
	
	/**
	 * 
	 * @return the list of ids of the views that should be opened when using the addon
	 */
	public List<String> getOpenViewIds()
	{
		String viewIds = getAttribute(EngineAddonSpecificationExtensionPoint.GEMOC_ENGINE_ADDON_EXTENSION_POINT_OPENVIEWIDS);
		ArrayList<String> idsList = new ArrayList<String>();
		if(viewIds != null){
			for(String s : viewIds.split(",")){
				idsList.add(s.trim());
			}
		}
		return idsList;
	}
	
	/**
	 * 
	 * @return the list of boolean option ids for the given engine
	 */
	public List<String> getAddonBooleanOptionsIds() {
		ArrayList<String> booleanOptionIDs = new ArrayList<String>();
		for (IConfigurationElement childConfElement : _configurationElement
				.getChildren(EngineAddonSpecificationExtensionPoint.GEMOC_ENGINE_ADDON_EXTENSION_POINT_BOOLEANOPTION)) {
			childConfElement.getName();
			final String booleanOptionIDString = childConfElement
					.getAttribute(EngineAddonSpecificationExtensionPoint.GEMOC_ENGINE_ADDON_EXTENSION_POINT_OPTION_ID);
			if (booleanOptionIDString != null) {
				for( String fileExtension : booleanOptionIDString.split(",")){
					booleanOptionIDs.add(fileExtension.trim());
				}
			}
		}
		return booleanOptionIDs;
	}
	/**
	 * 
	 * @return the list of boolean option ids for the given engine
	 */
	public List<String> getAddonStringOptionsIds() {
		ArrayList<String> optionIDs = new ArrayList<String>();
		for (IConfigurationElement childConfElement : _configurationElement
				.getChildren(EngineAddonSpecificationExtensionPoint.GEMOC_ENGINE_ADDON_EXTENSION_POINT_STRINGOPTION)) {
			childConfElement.getName();
			final String optionIDString = childConfElement
					.getAttribute(EngineAddonSpecificationExtensionPoint.GEMOC_ENGINE_ADDON_EXTENSION_POINT_OPTION_ID);
			if (optionIDString != null) {
				for( String fileExtension : optionIDString.split(",")){
					optionIDs.add(fileExtension.trim());
				}
			}
		}
		return optionIDs;
	}
	
	final public Collection<EngineAddonBooleanOptionSpecificationExtension> getAddonBooleanOptionSpecificationExtensions() {
		ArrayList<EngineAddonBooleanOptionSpecificationExtension> addonOptions = new ArrayList<EngineAddonBooleanOptionSpecificationExtension>();
		for (IConfigurationElement childConfElement : _configurationElement
				.getChildren(EngineAddonSpecificationExtensionPoint.GEMOC_ENGINE_ADDON_EXTENSION_POINT_BOOLEANOPTION)) {
			EngineAddonBooleanOptionSpecificationExtension engineAddonSpecificationExtension = new EngineAddonBooleanOptionSpecificationExtension(childConfElement);
			addonOptions.add(engineAddonSpecificationExtension);
		}
		return addonOptions;
	}
	final public Collection<EngineAddonStringOptionSpecificationExtension> getAddonStringOptionSpecificationExtensions() {
		ArrayList<EngineAddonStringOptionSpecificationExtension> addonOptions = new ArrayList<EngineAddonStringOptionSpecificationExtension>();
		for (IConfigurationElement childConfElement : _configurationElement
				.getChildren(EngineAddonSpecificationExtensionPoint.GEMOC_ENGINE_ADDON_EXTENSION_POINT_STRINGOPTION)) {
			EngineAddonStringOptionSpecificationExtension engineAddonSpecificationExtension = new EngineAddonStringOptionSpecificationExtension(childConfElement);
			addonOptions.add(engineAddonSpecificationExtension);
		}
		return addonOptions;
	}

	
	public IEngineAddon instanciateComponent() throws CoreException
	{
		Object instance = instanciate(EngineAddonSpecificationExtensionPoint.GEMOC_ENGINE_ADDON_EXTENSION_POINT_CLASS);
		try
		{
			IEngineAddon castedInstance = (IEngineAddon)instance;			
			return castedInstance;
		}
		catch(Exception e)
		{
			throwInstanciationCoreException();			
		}
		return null;
	}
	
	public String getComponentClassName()
	{
		return getAttribute(EngineAddonSpecificationExtensionPoint.GEMOC_ENGINE_ADDON_EXTENSION_POINT_CLASS);
	}
	
	
	public Boolean getDefaultActivationValue() 
	{
		return Boolean.parseBoolean(getAttribute(EngineAddonSpecificationExtensionPoint.GEMOC_ENGINE_ADDON_EXTENSION_POINT_DEFAULT));
	}
	
	
	
}
