/*******************************************************************************
 * Copyright (c) 2016, 2019 Inria and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Inria - initial API and implementation
 *******************************************************************************/
package org.eclipse.gemoc.execution.sequential.javaengine.ui.launcher.tabs;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.stream.Collectors;

import org.eclipse.gemoc.executionframework.engine.ui.launcher.tabs.AbstractLaunchConfigurationDataProcessingTab;
import org.eclipse.gemoc.xdsmlframework.api.extensions.engine_addon.EngineAddonSpecificationExtension;
import org.eclipse.gemoc.xdsmlframework.api.extensions.engine_addon.EngineAddonSpecificationExtensionPoint;
import org.eclipse.gemoc.xdsmlframework.api.extensions.engine_addon_group.EngineAddonGroupSpecificationExtension;
import org.eclipse.gemoc.xdsmlframework.api.extensions.engine_addon_group.EngineAddonGroupSpecificationExtensionPoint;

public class LaunchConfigurationBackendsTab extends AbstractLaunchConfigurationDataProcessingTab 
{

	@Override
	public String getName() 
	{
		return "Engine Addons";
	}
	
	@Override
	protected Collection<EngineAddonSpecificationExtension> getExtensionSpecifications() 
	{
		return EngineAddonSpecificationExtensionPoint.getSpecifications().stream()
				.filter(extension -> 
					extension.getAddonGroupId() == null || 
					extension.getAddonGroupId().equals("Sequential.AddonGroup") || 
					extension.getAddonGroupId().equals("General.AddonGroup")
				)
				.collect(Collectors.toList());
	}
	
	@Override
	protected Collection<EngineAddonGroupSpecificationExtension> getGroupExtensionSpecifications() 
	{
		HashMap<String,EngineAddonGroupSpecificationExtension> result = new HashMap<String,EngineAddonGroupSpecificationExtension>();
		// ensures to get only one group for a given id
		for (Iterator<EngineAddonGroupSpecificationExtension> iterator = EngineAddonGroupSpecificationExtensionPoint.getSpecifications().iterator(); iterator.hasNext();) {
			EngineAddonGroupSpecificationExtension engineAddonGroupSpecificationExtension =  iterator.next();
			result.put(engineAddonGroupSpecificationExtension.getId(), engineAddonGroupSpecificationExtension);
		}
		return result.values();
	}
		
}
