package org.eclipse.gemoc.executionframework.engine.ui.launcher.tabs;

import java.util.HashMap;
import java.util.Optional;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.gemoc.executionframework.engine.ui.Activator;
import org.eclipse.gemoc.xdsmlframework.api.extensions.engine_addon.EngineAddonBooleanOptionSpecificationExtension;
import org.eclipse.gemoc.xdsmlframework.api.extensions.engine_addon.EngineAddonSpecificationExtension;
import org.eclipse.gemoc.xdsmlframework.api.extensions.engine_addon.EngineAddonStringOptionSpecificationExtension;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class EngineAddonLaunchConfigWidget {

	
	
	Button mainCheckButton;
	Group optionGroup = null;
	private HashMap<EngineAddonBooleanOptionSpecificationExtension, Button> booleanOptionButtons = new HashMap<>();
	private HashMap<EngineAddonStringOptionSpecificationExtension, Text> stringOptionTexts = new HashMap<>();
	AbstractLaunchConfigurationDataProcessingTab parentTab;
	EngineAddonSpecificationExtension extension;
	
	
	
	public EngineAddonLaunchConfigWidget(AbstractLaunchConfigurationDataProcessingTab parentTab, Composite parent, EngineAddonSpecificationExtension extension) {
		this.parentTab = parentTab;
		this.extension = extension;
		createComponentForExtension(parent, extension);
	}

	protected void createComponentForExtension(Composite parentGroup, EngineAddonSpecificationExtension extension) {
		if(! extension.getAddonBooleanOptionsIds().isEmpty() || ! extension.getAddonStringOptionsIds().isEmpty()) {
			// TODO need to create options for this addon
		}
		
		mainCheckButton = LaunchConfUtils.createCheckButton(parentGroup, extension.getName() + ":");
		mainCheckButton.setToolTipText(extension.getId() + " contributed by " + extension.getContributorName());
		// checkbox.setSelection(extension.getDefaultActivationValue());
		mainCheckButton.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				setOptionsEnabled(mainCheckButton.getSelection());
				parentTab.updateTab();
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
		
		
		// create option group if required
		if(!extension.getAddonBooleanOptionsIds().isEmpty() || !extension.getAddonStringOptionsIds().isEmpty()) {
			optionGroup = LaunchConfUtils.createGroup(parentGroup, "addon options", 2);
			GridData gridData = new GridData(SWT.FILL, SWT.CENTER, true, false); //new GridData(GridData.VERTICAL_ALIGN_END);
		    gridData.horizontalSpan = 2;
		    gridData.horizontalIndent = 30;
		    gridData.horizontalAlignment = GridData.FILL;
		    
		    optionGroup.setLayoutData(gridData);
		    
		    for(EngineAddonBooleanOptionSpecificationExtension booleanOption : extension.getAddonBooleanOptionSpecificationExtensions()) {
		    	Label l = LaunchConfUtils.createTextLabelLayout(optionGroup, booleanOption.getName()+":");
		    	GridData gd = new GridData(SWT.RIGHT, SWT.TOP, false, false); // do not grab excess space
				l.setLayoutData(gd);
		    	Button b = LaunchConfUtils.createCheckButton(optionGroup, ""); // button on right so no label
				b.addSelectionListener(new SelectionListener() {

					@Override
					public void widgetSelected(SelectionEvent e) {
						parentTab.updateTab();
					}

					@Override
					public void widgetDefaultSelected(SelectionEvent e) {
					}
				});
				if (extension.getShortDescription() != null) {
					b.setToolTipText(booleanOption.getShortDescription());
					l.setToolTipText(booleanOption.getShortDescription());
				}
				this.booleanOptionButtons.put(booleanOption, b);
		    }
		    for(EngineAddonStringOptionSpecificationExtension stringOption : extension.getAddonStringOptionSpecificationExtensions()) {
		    	
		    	Label l = LaunchConfUtils.createTextLabelLayout(optionGroup, stringOption.getName()+":");
		    	GridData gd = new GridData(SWT.RIGHT, SWT.TOP, false, false); // do not grab excess space
				l.setLayoutData(gd);
				// Model location text
		    	Text t = new Text(optionGroup, SWT.SINGLE | SWT.BORDER);
				t.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
				t.setFont(null);
				t.addModifyListener(new ModifyListener() {
					@Override
					public void modifyText(ModifyEvent arg0) {
						parentTab.updateTab();
					}
				});
				if (extension.getShortDescription() != null) {
					t.setToolTipText(stringOption.getShortDescription());
				}
				this.stringOptionTexts.put(stringOption, t);
		    }
		}
	}

	
	public void setOptionsEnabled(boolean enabled) {
		if( optionGroup != null) {
			optionGroup.setEnabled(enabled);
			for(Control c: optionGroup.getChildren()) {
				c.setEnabled(enabled);
			}
		}
	}
	
	
	public void optionInitializeFrom(ILaunchConfiguration configuration, EngineAddonSpecificationExtension extension) {
		if( optionGroup != null) {
			for(EngineAddonBooleanOptionSpecificationExtension booleanOption : booleanOptionButtons.keySet()) {
				try {
					String key = booleanOption.getId();
					Optional<EngineAddonBooleanOptionSpecificationExtension> booleanOptionSpec = extension.getAddonBooleanOptionSpecificationExtensions().stream().filter(s -> key.equals(s.getId()) ).findFirst();
					boolean defaultValue = booleanOptionSpec.isPresent() ? booleanOptionSpec.get().getDefaultValue() : false;
					boolean value = configuration.getAttribute(key, defaultValue);
					booleanOptionButtons.get(booleanOption).setSelection(value);
				} catch (CoreException e) {
					Activator.error(e.getMessage(), e);
				}
			}

			for(EngineAddonStringOptionSpecificationExtension option : stringOptionTexts.keySet()) {
				try {
					String key = option.getId();
					String value = configuration.getAttribute(key, "");
					stringOptionTexts.get(option).setText(value);
				} catch (CoreException e) {
					Activator.error(e.getMessage(), e);
				}
			}
		}
	}
	public void optionsPerformApply(ILaunchConfigurationWorkingCopy configuration){
		if( optionGroup != null) {
			for(EngineAddonBooleanOptionSpecificationExtension booleanOption : booleanOptionButtons.keySet()) {
				String key = booleanOption.getId();
				configuration.setAttribute(key, booleanOptionButtons.get(booleanOption).getSelection());
			}

			for(EngineAddonStringOptionSpecificationExtension option : stringOptionTexts.keySet()) {
				String key = option.getId();
				configuration.setAttribute(key, stringOptionTexts.get(option).getText());
			}
		}
	}
	
}
