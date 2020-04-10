package org.eclipse.gemoc.executionframework.engine.ui.launcher.tabs;

import org.eclipse.debug.internal.ui.SWTFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;

public class LaunchConfUtils {

	/**
	 * Create a group with the default number of columns (3)
	 * 
	 * @param parent
	 * @param text
	 * @return
	 */
	public static Group createGroup(Composite parent, String text) {
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
	public static  Group createGroup(Composite parent, String text, int nbColumn) {
		Group group = new Group(parent, SWT.NULL);
		group.setText(text);
		GridLayout locationLayout = new GridLayout(nbColumn, false);
		locationLayout.numColumns = nbColumn;
		locationLayout.marginHeight = 10;
		locationLayout.marginWidth = 10;
		group.setLayout(locationLayout);
		GridData gd = new GridData(SWT.FILL, SWT.CENTER, true, false);
		group.setLayoutData(gd);
		return group;
	}

	/**
	 * 
	 * @param parent      the Parent of this argument tab
	 * @param labelString the label of the input text to create
	 */
	public static  Label createTextLabelLayout(Composite parent, String labelString) {
		Label inputLabel = new Label(parent, SWT.NONE);
		inputLabel.setText(labelString); // $NON-NLS-1$
		GridData gd = new GridData(SWT.LEFT, SWT.CENTER, true, false);
		// gd.horizontalSpan = 2;
		inputLabel.setLayoutData(gd);
		return inputLabel;
	}

	/**
	 * 
	 * @param parent      the Parent of this argument tab
	 * @param labelString the label of the input text to create
	 * @param toolTipText the text for tool tip
	 */
	public static Label createTextLabelLayout(Composite parent, String labelString, String toolTipText) {
		//GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		//parent.setLayoutData(gd);
		Label inputLabel = new Label(parent, SWT.NONE);
		inputLabel.setText(labelString); // $NON-NLS-1$
		inputLabel.setToolTipText(toolTipText);
		GridData gd = new GridData(SWT.LEFT, SWT.CENTER, true, false);
		// gd.horizontalSpan = 2;
		inputLabel.setLayoutData(gd);
		return inputLabel;
	}	
	
	public static Button createCheckButton(Composite parent, String label) {
		return SWTFactory.createCheckButton(parent, label, null, false, 1);
	}
}
