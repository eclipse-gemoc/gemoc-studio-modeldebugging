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

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;

/**
 * This class adds to org.eclipse.debug.ui.AbstractLaunchConfigurationTab some
 * additional helper methods in order to create elements in the UI
 *
 */
public abstract class AbstractLaunchConfigurationTab extends org.eclipse.debug.ui.AbstractLaunchConfigurationTab {

	public AbstractLaunchConfigurationTab() {
		super();
	}

	/**
	 * Create a group with the default number of columns (3)
	 * 
	 * @param parent
	 * @param text
	 * @return
	 */
	protected Group createGroup(Composite parent, String text) {
		return createGroup(parent, text, 3);
	}

	/**
	 * create a group with the specified number of columns
	 * 
	 * @param parent
	 * @param text
	 * @param nbColumn
	 * @return
	 */
	protected Group createGroup(Composite parent, String text, int nbColumn) {
		Group group = new Group(parent, SWT.NULL);
		group.setText(text);
		GridLayout locationLayout = new GridLayout();
		locationLayout.numColumns = nbColumn;
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