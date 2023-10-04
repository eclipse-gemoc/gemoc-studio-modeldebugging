package org.eclipse.gemoc.executionframework.engine.ui.concurrency.launcher;

import org.eclipse.debug.ui.AbstractLaunchConfigurationTab;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;

/**
 * 
 * common functions for launch configuration tabs
 */
public abstract class LaunchConfigurationTab extends AbstractLaunchConfigurationTab {

	public LaunchConfigurationTab() {
		super();
	}

	protected Group createGroup(Composite parent, String text) {
		Group group = new Group(parent, SWT.NULL);
		group.setText(text);
		GridLayout locationLayout = new GridLayout();
		locationLayout.numColumns = 5;
		locationLayout.marginHeight = 10;
		locationLayout.marginWidth = 10;
		group.setLayout(locationLayout);
		return group;
	}

	/**
	 * 
	 * @param parent      the Parent of this argument tab
	 * @param labelString the label of the input text to create
	 */
	protected void createTextLabelLayout(Composite parent, String labelString) {
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		parent.setLayoutData(gd);
		Label inputLabel = new Label(parent, SWT.NONE);
		inputLabel.setText(labelString); // $NON-NLS-1$
		// gd = new GridData();
		// gd.horizontalSpan = 2;
		// inputLabel.setLayoutData(gd);
	}

	/**
	 * 
	 * @param parent      the Parent of this argument tab
	 * @param labelString the label of the input text to create
	 * @param toolTipText the text for tool tip
	 */
	protected void createTextLabelLayout(Composite parent, String labelString, String toolTipText) {
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		parent.setLayoutData(gd);
		Label inputLabel = new Label(parent, SWT.NONE);
		inputLabel.setText(labelString); // $NON-NLS-1$
		inputLabel.setToolTipText(toolTipText);
		// gd = new GridData();
		// gd.horizontalSpan = 2;
		// inputLabel.setLayoutData(gd);
	}

}