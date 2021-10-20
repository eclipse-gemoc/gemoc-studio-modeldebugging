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
package org.eclipse.gemoc.executionframework.engine.ui.launcher.tabs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.gemoc.executionframework.engine.core.RunConfiguration;
import org.eclipse.gemoc.executionframework.engine.ui.Activator;
import org.eclipse.gemoc.xdsmlframework.api.engine_addon.IEngineAddon;
import org.eclipse.gemoc.xdsmlframework.api.extensions.engine_addon.EngineAddonSpecificationExtension;
import org.eclipse.gemoc.xdsmlframework.api.extensions.engine_addon_group.EngineAddonGroupSpecificationExtension;
import org.eclipse.gemoc.xdsmlframework.api.extensions.languages.LanguageDefinitionExtension;
import org.eclipse.gemoc.xdsmlframework.api.extensions.languages.LanguageDefinitionExtensionPoint;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;

public abstract class AbstractLaunchConfigurationDataProcessingTab extends AbstractLaunchConfigurationTab
		implements ILaunchLanguageSelectionListener {

	private HashMap<EngineAddonSpecificationExtension, EngineAddonLaunchConfigWidget> _components = new HashMap<>();
	
	private HashMap<EngineAddonSpecificationExtension, EngineAddonLaunchConfigWidget> _languageSpecificComponents = new HashMap<>();
	
	protected String _currentLanguageName;
	
	protected Group _languageSpecificGroup;
	
	protected Composite groupParent;

	protected AbstractLaunchConfigurationDataProcessingTab() {
		// add all extensions returned by getExtensionSpecifications()
		for (EngineAddonSpecificationExtension extension : getExtensionSpecifications()) {
			_components.put(extension, null);
		}
	}

	/**
	 * return the list of extension specifications that apply to this launcher It
	 * acts as a filter on
	 * EngineAddonSpecificationExtensionPoint.getSpecifications()
	 * 
	 * @return
	 */
	protected abstract Collection<EngineAddonSpecificationExtension> getExtensionSpecifications();

	protected abstract Collection<EngineAddonGroupSpecificationExtension> getGroupExtensionSpecifications();

	@Override
	public void createControl(Composite parent) {
		Composite content = new Composite(parent, SWT.NULL);
		GridLayout gl = new GridLayout(1, false);
		gl.marginHeight = 0;
		content.setLayout(gl);
		content.layout();
		setControl(content);
		createLayout(content);
	}

	private void createLayout(Composite parent) {
		HashMap<String, Group> groupmap = new HashMap<String, Group>();

		for (EngineAddonGroupSpecificationExtension extension : getGroupExtensionSpecifications()) {
			String gname = extension.getName() != null ? extension.getName() : extension.getId();
			groupmap.put(extension.getId(), LaunchConfUtils.createGroup(parent, gname, 2));
		}
		groupParent = parent;
		groupmap.put("", LaunchConfUtils.createGroup(groupParent, "", 2));

		for (EngineAddonSpecificationExtension extension : _components.keySet()) {
			Group parentGroup = groupmap.get("");
			final String addonGroupId = extension.getAddonGroupId();
			if (addonGroupId != null) {
				// refine the parentGroup if specified
				parentGroup = groupmap.get(extension.getAddonGroupId());
				if (parentGroup == null) {
					// back to the unsorted group
					parentGroup = groupmap.get("");
				}
			}

			EngineAddonLaunchConfigWidget addonWidget = new EngineAddonLaunchConfigWidget(this, parentGroup, extension);
			_components.put(extension, addonWidget);
		}

		// display language specific components
		createLanguageSpecificGroup(groupParent);
		
		// remove empty groups
		for (Group g : groupmap.values()) {
			if (g.getChildren().length == 0) {
				g.dispose();
				parent.layout(true);
			}
		}
	}
	
	/**
	 * create the group if the current language is valid
	 * the group is created only if not already done (user must dispose and  clear the _languageSpecificGroup variable in order to change language) 
	 * @param parent
	 */
	protected void createLanguageSpecificGroup(Composite parent) {
		// display language specific components
		if(_languageSpecificGroup == null && _currentLanguageName != null && !_currentLanguageName.isEmpty()) {
			_languageSpecificGroup = LaunchConfUtils.createGroup(parent, _currentLanguageName, 2);
			LanguageDefinitionExtension langDefExtension = LanguageDefinitionExtensionPoint.findDefinition(_currentLanguageName);
			if(langDefExtension != null) {
				for(EngineAddonSpecificationExtension extension : langDefExtension.getLanguageSpecificEngineAddonSpecificationExtensions()){
					EngineAddonLaunchConfigWidget addonWidget = new EngineAddonLaunchConfigWidget(this, _languageSpecificGroup, extension);
					_languageSpecificComponents.put(extension, addonWidget);
				}
			}
			// remove group if empty
			if(_languageSpecificGroup.getChildren().length == 0) {
				_languageSpecificGroup.dispose();
				parent.layout(true);
				_languageSpecificGroup = null;
			}
		}
	}
	
	protected Button createComponentForExtension(Composite parentGroup, EngineAddonSpecificationExtension extension) {
		if(! extension.getAddonBooleanOptionsIds().isEmpty() || ! extension.getAddonStringOptionsIds().isEmpty()) {
			// TODO need to create options for this addon
		}
		
		Button checkbox = createCheckButton(parentGroup, extension.getName() + ":");
		checkbox.setToolTipText(extension.getId() + " contributed by " + extension.getContributorName());
		// checkbox.setSelection(extension.getDefaultActivationValue());
		checkbox.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				updateLaunchConfigurationDialog();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		String desc;
		if (extension.getShortDescription() != null) {
			desc = extension.getShortDescription();
		} else
			desc = "";
		LaunchConfUtils.createTextLabelLayout(parentGroup, desc, "contributed by " + extension.getContributorName());
		return checkbox;
	}

	@Override
	public void setDefaults(ILaunchConfigurationWorkingCopy configuration) {
		for (EngineAddonSpecificationExtension entry : _components.keySet()) {
			String extensionName = entry.getName() != null ? entry.getName() : entry.getId();
			configuration.setAttribute(extensionName, entry.getDefaultActivationValue());
		}
		for (EngineAddonSpecificationExtension entry : _languageSpecificComponents.keySet()) {
			String extensionName = entry.getName() != null ? entry.getName() : entry.getId();
			configuration.setAttribute(extensionName, entry.getDefaultActivationValue());
		}
	}

	@Override
	public void initializeFrom(ILaunchConfiguration configuration) {
		for (EngineAddonSpecificationExtension extension : _components.keySet()) {
			try {
				String extensionName = extension.getName() != null ? extension.getName() : extension.getId();
				boolean value = configuration.getAttribute(extensionName, false);
				// _componentsActive.put(extension, value);
				EngineAddonLaunchConfigWidget configWidget = _components.get(extension);
				Button checkbox = configWidget.mainCheckButton;
				checkbox.setSelection(value);
				configWidget.setOptionsEnabled(value);
				configWidget.optionInitializeFrom(configuration, extension);
			} catch (CoreException e) {
				Activator.error(e.getMessage(), e);
			}
		}
		
		try {
			RunConfiguration gemocRunConf = new RunConfiguration(configuration);
			_currentLanguageName = gemocRunConf.getLanguageName();
			createLanguageSpecificGroup(groupParent);
			//LanguageDefinitionExtension landDefExtension = LanguageDefinitionExtensionPoint.findDefinition(_currentLanguageName);
			Collection<EngineAddonSpecificationExtension> languageSpecificEngineAddonSpecificationExtensions = _languageSpecificComponents.keySet();
			for(EngineAddonSpecificationExtension extension : languageSpecificEngineAddonSpecificationExtensions){
				//Button checkbox = createComponentForExtension(_languageSpecificGroup, extension);
				try {
					String extensionName = extension.getName() != null ? extension.getName() : extension.getId();
					boolean value = configuration.getAttribute(extensionName, extension.getDefaultActivationValue());
					// _componentsActive.put(extension, value);
					EngineAddonLaunchConfigWidget configWidget = _languageSpecificComponents.get(extension);
					Button checkbox = configWidget.mainCheckButton;
					checkbox.setSelection(value);
					configWidget.setOptionsEnabled(value);
					configWidget.optionInitializeFrom(configuration, extension);
				} catch (CoreException e) {
					Activator.error(e.getMessage(), e);
				}
			}
			
		} catch (CoreException e) {
			Activator.error(e.getMessage(), e);
		}
	}

	@Override
	public void performApply(ILaunchConfigurationWorkingCopy configuration) {
		for (Entry<EngineAddonSpecificationExtension, EngineAddonLaunchConfigWidget> entry : _components.entrySet()) {
			String extensionName = entry.getKey().getName() != null ? entry.getKey().getName() : entry.getKey().getId();
			configuration.setAttribute(extensionName, entry.getValue().mainCheckButton.getSelection());
			entry.getValue().optionsPerformApply(configuration);
		}
		for (Entry<EngineAddonSpecificationExtension, EngineAddonLaunchConfigWidget> entry : _languageSpecificComponents.entrySet()) {
			String extensionName = entry.getKey().getName() != null ? entry.getKey().getName() : entry.getKey().getId();
			configuration.setAttribute(extensionName, entry.getValue().mainCheckButton.getSelection());
			entry.getValue().optionsPerformApply(configuration);
		}
	}

	@Override
	public boolean isValid(ILaunchConfiguration config) {
		// Validate each addon
		try {
			List<IEngineAddon> addons = new ArrayList<IEngineAddon>();
			for (Entry<EngineAddonSpecificationExtension, EngineAddonLaunchConfigWidget> entry : _components.entrySet()) {
				if (entry.getValue().mainCheckButton.getSelection()) {
					addons.add(entry.getKey().instanciateComponent());
				}
			}
			for (Entry<EngineAddonSpecificationExtension, EngineAddonLaunchConfigWidget> entry : _languageSpecificComponents.entrySet()) {
				if (entry.getValue().mainCheckButton.getSelection()) {
					addons.add(entry.getKey().instanciateComponent());
				}
			}
			List<String> errors = new ArrayList<String>();
			for (IEngineAddon iEngineAddon : addons) {
				errors.addAll(iEngineAddon.validate(addons));
			}
			if (!errors.isEmpty()) {
				for (String msg : errors) {
					setErrorMessage(msg);
				}
				return false;
			}
		} catch (Exception e) {
			Activator.error(e.getMessage(), e);
		}

		setErrorMessage(null);
		return true;
	}
	
	public void languageChanged(String newLanguageName) {
		//Activator.error("TODO implement adaptation when a language changes", new Exception());
		if(_currentLanguageName != null 
				&& !_currentLanguageName.equals(newLanguageName) 
				&& newLanguageName!= null 
				&& !newLanguageName.isEmpty()) {
			// clear current 
			if(_languageSpecificGroup != null) {
				_languageSpecificGroup.dispose();
				groupParent.layout(true);
				_languageSpecificGroup = null;
			}
			_languageSpecificComponents.clear();
			
			// create componet for new language
			_currentLanguageName = newLanguageName;
			createLanguageSpecificGroup(groupParent);
			groupParent.layout(true);
		}
		
		
	}

	
	// called by widgets when they are selected
	public void updateTab() {
		// todo deal with enabled/disabled addon with options
		
		
		updateLaunchConfigurationDialog();
	}
	
}
