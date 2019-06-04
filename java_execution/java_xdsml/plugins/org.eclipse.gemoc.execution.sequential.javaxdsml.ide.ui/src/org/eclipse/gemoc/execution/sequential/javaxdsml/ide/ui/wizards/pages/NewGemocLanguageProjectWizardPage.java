/*******************************************************************************
 * Copyright (c) 2017 Inria and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Inria - initial API and implementation
 *******************************************************************************/
package org.eclipse.gemoc.execution.sequential.javaxdsml.ide.ui.wizards.pages;

import java.util.Arrays;
import java.util.List;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class NewGemocLanguageProjectWizardPage extends WizardPage {

	protected NewGemocLanguageProjectWizardFields		context;
	
	protected static final List<String> FILE_EXTENSIONS = Arrays.asList(new String [] { "ecore" });
	protected boolean 	enableNext = true;
	
	protected Composite 	container;
	protected Label 		lblProjectName;
	
	protected Text 			txtProjectName;
	protected Text 			txtProjectLocation;
	protected Button		btnBrowseLocation;
	protected Button 		btnCheckLocation;
	

	protected Group 		grpGeneral;

	public NewGemocLanguageProjectWizardPage(NewGemocLanguageProjectWizardFields context){
		super("wizardPage");
		this.context = context;
		setTitle("New GEMOC Language project");
		setDescription("This wizard creates a new GEMOC Language  project");
	}
	
	/**
	 * Constructor for KermetaNewWizardDashboard.
	 * @param pageName
	 */
	public NewGemocLanguageProjectWizardPage(ISelection selection) {
		super("wizardPage");
		setTitle("New Melange project");
		setDescription("This wizard creates a new GEMOC Language project");
		setPageComplete(true);
	}

	@Override
	public void createControl(Composite parent) {
		container = new Composite(parent, SWT.NULL);
		container.setLayout(new GridLayout(1, false));
		
		
		//-----------------------------------------------
		grpGeneral = new Group(container, SWT.NONE);
		grpGeneral.setText("General");
		grpGeneral.setLayout(new GridLayout(4, false));

		lblProjectName = new Label(grpGeneral, SWT.NONE);
		lblProjectName.setText("Project name");
		new Label(grpGeneral, SWT.NONE);
		new Label(grpGeneral, SWT.NONE);
		new Label(grpGeneral, SWT.NONE);
		
		txtProjectName = new Text(grpGeneral, SWT.BORDER);
		txtProjectName.setText(this.context.projectName);
		txtProjectName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1));
		new Label(grpGeneral, SWT.NONE);
				
		txtProjectName.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent arg0) {
				checkPageFields();
				updateNameProject(txtProjectName.getText());
			}
		});
		
		btnCheckLocation = new Button(grpGeneral, SWT.CHECK);
		btnCheckLocation.setText("Use default location");
		btnCheckLocation.setSelection(true);
		new Label(grpGeneral, SWT.NONE);
		new Label(grpGeneral, SWT.NONE);
		new Label(grpGeneral, SWT.NONE);
		
		btnCheckLocation.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (btnCheckLocation.getSelection()) {
					txtProjectLocation.setEnabled(false);
					btnBrowseLocation.setEnabled(false);
				}
				else {
					txtProjectLocation.setEnabled(true);
					btnBrowseLocation.setEnabled(true);
				} 
			}
		});
		txtProjectLocation = new Text(grpGeneral, SWT.BORDER);
		txtProjectLocation.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1));
		txtProjectLocation.setText(this.context.projectLocation);
		
		btnBrowseLocation = new Button(grpGeneral, SWT.NONE);
		btnBrowseLocation.setText("Browse...");
		
		btnBrowseLocation.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				txtProjectLocation.setText(locationDialog());
			}
		});
		
		
		//initialization of enabled state of controls
		txtProjectLocation.setEnabled(false);
		btnBrowseLocation.setEnabled(false);
		
		//check if the name of the project is legal
		checkPageFields();
		
		// Required to avoid an error in the system
		setControl(container);
		setPageComplete(true);
	}
	
	protected String locationDialog () {
		DirectoryDialog dirDialog = new DirectoryDialog(new Shell());
	    dirDialog.setText("Select location directory");
	    this.context.projectLocation = dirDialog.open();
	    return this.context.projectLocation;
	}
	
	protected boolean existNameProject () {
		boolean bFinder = false;
		int i = 0;
		while (bFinder == false && i < ResourcesPlugin.getWorkspace().getRoot().getProjects().length) {
  		  if (ResourcesPlugin.getWorkspace().getRoot().getProjects()[i].getName().contentEquals(txtProjectName.getText())) {
  			  bFinder = true;
  		  }
  		  i++;
		}
		return bFinder;
	}
	
	
	/**
	 * verifies the fields data and indicates if the page can be completed or not
	 */
	protected void checkPageFields(){
		if (existNameProject()) {
			setErrorMessage("A project already exist with this name");
			setPageComplete(false);
		}
		else {
			setErrorMessage(null);
			setPageComplete(true);					
		}
	}
	
	
	public void updateNameProject (String nameProject) {
		this.context.projectName = nameProject;
	}
	
/*	protected void updateNextButton (boolean enable) {
		enableNext = enable;
		canFlipToNextPage();
		getWizard().getContainer().updateButtons();
		this.context.indexTransfomation = this.combo.getSelectionIndex();
	}*/
	
	
	
	@Override
	public boolean canFlipToNextPage () {
		return enableNext;
	}
	

	public void setProjectName(String nameProject) {
		this.txtProjectName.setText(nameProject);
		this.context.projectName = nameProject;
	}
	
	
}
