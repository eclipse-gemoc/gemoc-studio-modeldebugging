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
package org.eclipse.gemoc.xdsmlframework.extensions.sirius.wizards.pages;

import java.util.Arrays;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.gemoc.commons.eclipse.core.resources.IFileUtils;
import org.eclipse.gemoc.commons.eclipse.pde.wizards.pages.pde.ErrorMessage;
import org.eclipse.gemoc.commons.eclipse.ui.dialogs.SelectAnyIFileDialog;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.sirius.editor.editorPlugin.SiriusEditorPlugin;
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
import org.eclipse.ui.PlatformUI;


public class NewGemocSiriusProjectMainWizardPage extends WizardPage {

	protected NewGemocSiriusProjectWizardFields		context;
	
	protected static final List<String> FILE_EXTENSIONS = Arrays.asList(new String [] { "ecore" });
	protected ErrorMessage[] errorMessage;
	protected boolean 	enableNext = true;
	
	protected Composite 	container;
	protected Label 		lblProjectName;
//	protected Label 		lblTemplateEcore;
	protected Text 			txtProjectName;
	protected Text 			txtProjectLocation;
//	protected Text 			txtPathEcore;
	protected Button		btnBrowseLocation;
//	protected Button 		btnBrowseEcore;
	//protected Button 		btnCreateEmfProject;
	protected Button 		btnCheckLocation;
//	protected Button 		btnCheckEcore;
//	protected Button 		btnCheckSLE;
	
	protected Text			txtBaseGemocProject;
	protected Button 		btnBrowseGemocProject;
	protected Text			txtDSLFile;
	protected Button 		btnBrowseDSLFile;
	
	protected Group 		grpGeneral;
	protected Group 		grpBaseGemocProject;
//protected Group 		grpSLEOptions;
	protected Group 		grpTemplateOptions;

	public NewGemocSiriusProjectMainWizardPage(NewGemocSiriusProjectWizardFields context){
		super("wizardPage");
		this.context = context;
		setTitle(SiriusEditorPlugin.getPlugin().getString(
				"_UI_ViewpointSpecificationProjectWizard_label")+" for GEMOC");
		setDescription("This wizard creates a new Sirius specification project for GEMOC");
		this.errorMessage =  new ErrorMessage[4];
		this.errorMessage[0] = new ErrorMessage("A project with this name already exists.", false);
		this.errorMessage[1] = new ErrorMessage("Please select an ecore file.", false);
		this.errorMessage[2] = new ErrorMessage("dsl file doesn't exist", false);
		this.errorMessage[3] = new ErrorMessage("base dsl file not set", false);
	}
	
	/**
	 * Constructor for KermetaNewWizardDashboard.
	 * @param pageName
	 */
	public NewGemocSiriusProjectMainWizardPage(ISelection selection) {
		super("wizardPage");
		setTitle(SiriusEditorPlugin.getPlugin().getString(
				"_UI_ViewpointSpecificationProjectWizard_label")+" for GEMOC");
		setDescription("This wizard creates a new Sirius specification project for GEMOC");
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
		lblProjectName.setText("project name ");
		new Label(grpGeneral, SWT.NONE);
		new Label(grpGeneral, SWT.NONE);
		new Label(grpGeneral, SWT.NONE);
		
		txtProjectName = new Text(grpGeneral, SWT.BORDER);
		txtProjectName.setText(this.context.projectName);
		GridData projectNameGrid = new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1);
		projectNameGrid.widthHint = 500;
		txtProjectName.setLayoutData(projectNameGrid);
		new Label(grpGeneral, SWT.NONE);
				
		txtProjectName.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent arg0) {
				validatePage();
				updateNameProject(txtProjectName.getText());
			}
		});
		
		btnCheckLocation = new Button(grpGeneral, SWT.CHECK);
		btnCheckLocation.setText("use default location");
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
		txtProjectLocation.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent arg0) {
				validatePage();
				updateProjectLocation(txtProjectLocation.getText());
			}
		});
		
		btnBrowseLocation = new Button(grpGeneral, SWT.NONE);
		btnBrowseLocation.setText("Browse...");
		
		btnBrowseLocation.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				txtProjectLocation.setText(locationDialog());
			}
		});
		
		//-----------------------------------------------

		grpBaseGemocProject = new Group(container, SWT.NONE);
		grpBaseGemocProject.setText("Base XDSML");
		//grpBaseGemocProject.setLayout(new FillLayout(SWT.HORIZONTAL));
		grpBaseGemocProject.setLayout(new GridLayout());
		grpBaseGemocProject.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		//createGemocProjectNameComposite(grpBaseGemocProject);
		createDslFileComposite(grpBaseGemocProject);
		
		//-----------------------------------------------
		
		//initialization of enabled state of controls
		txtProjectLocation.setEnabled(false);
		btnBrowseLocation.setEnabled(false);
		
		validatePage();
		
		// Required to avoid an error in the system
		setControl(container);
		setPageComplete(true);
	}
	
	private Text createDslFileComposite(final Composite composite) {
		GridLayout layout;
		final Composite dslFileComposite = new Composite(composite,
				SWT.NONE);
		dslFileComposite.setLayoutData(new GridData(
				GridData.FILL_HORIZONTAL));
		layout = new GridLayout();
		layout.numColumns = 3;
		dslFileComposite.setLayout(layout);
		Label label = new Label(dslFileComposite, SWT.None);
		label.setText("DSL file path:");
		txtDSLFile = new Text(dslFileComposite, SWT.SINGLE);
		txtDSLFile.setText(this.context.dslFilePath);
		txtDSLFile.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		txtDSLFile.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				validatePage();
				updateDslFilePath(txtDSLFile.getText());
				try {
					IFile dslFile = IFileUtils.getIFileFromWorkspaceOrFileSystem(txtDSLFile.getText());
					if(dslFile.exists()) {
						updateBaseGemocProject(dslFile.getProject().getName());
					}
				} catch (CoreException e1) {}
			}
		});
		btnBrowseDSLFile = new Button(dslFileComposite, SWT.NONE);
		btnBrowseDSLFile.setText("Browse...");
		
		btnBrowseDSLFile.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				SelectAnyIFileDialog dsldialog = new SelectAnyIFileDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell());
				dsldialog.setPattern("*.dsl");
				dsldialog.setTitle("Select base DSL file");
				if (dsldialog.open() == Dialog.OK) {
					Object[] selections = dsldialog.getResult();
					if(selections != null 
						&& selections.length != 0
						&& selections[0] instanceof IFile 
					){
						IFile dslFile = (IFile) selections[0];
						txtDSLFile.setText(dslFile.getFullPath().toString());
						//validatePage();
					}
				}
			}
		});


		return txtDSLFile;
	}

	protected String locationDialog () {
		DirectoryDialog dirDialog = new DirectoryDialog(new Shell());
	    dirDialog.setText("Select location directory");
	    this.context.projectLocation = dirDialog.open();
	    return this.context.projectLocation;
	}

	
	protected void activErrorMessage (int index, boolean bActiv) {
		this.errorMessage[index].setActive(bActiv);
		setMessageError();
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
	
	private boolean isSet(Text text) {
		return text != null && !"".equals(text.getText());
	}
	
	
	protected boolean validatePage() {
		boolean validPage = true;
		/*if (validProjectName && resourceExistsInWorkspace()) {
			validProjectName = false;
		}
		// check for collision with existing folder of different case on disk
		if (validProjectName && !StringUtil.isEmpty(getProjectName())) {
			if (resourceExistsOnDisk()) {
				validProjectName = false;
			}
		}*/
		if (existNameProject()) {
			activErrorMessage (0, true);
			validPage = false;
		}
		else {
			  activErrorMessage(0 , false);			
		}
		if(isSet(txtDSLFile)) {
			activErrorMessage (3, false);
		} else {
			activErrorMessage (3, true);
			validPage = false;
		}
		if(isSet(txtDSLFile) ) {
			IFile dslFile;
			try {
				dslFile = IFileUtils.getIFileFromWorkspaceOrFileSystem(txtDSLFile.getText());
				if(dslFile.exists()) {
					activErrorMessage (2, false);
				} else {
					activErrorMessage (2, true);
					validPage = false;
				}
			} catch (CoreException | IllegalArgumentException e) {
				activErrorMessage (2, true);
				validPage = false;
			}
		}
		if(validPage ) {
			setPageComplete(true);
		} else {
			setPageComplete(false);
		}
		return validPage;
	}
	
	protected void setMessageError () {
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < this.errorMessage.length; i++) {
			if (this.errorMessage[i].isActive()) {
				result.append(this.errorMessage[i].getMessageError() + "\n");
			}
		}
		if (result.length() > 0) {
			setErrorMessage(result.toString());
		}
		else {
			setErrorMessage(null);
		}
	}
	
	protected void updateNameProject (String nameProject) {
		this.context.projectName = nameProject;
		if(btnCheckLocation.getSelection()) {
			// if use default location then also update the location field accordingly
			txtProjectLocation.setText(ResourcesPlugin.getWorkspace().getRoot().getLocation().toOSString()+"/"+nameProject);
		}
	}
	protected void updateProjectLocation (String projectLocation) {
		this.context.projectLocation = projectLocation;
	}
	protected void updateBaseGemocProject(String text) {
		this.context.baseGemocProject = text;
	}
	protected void updateDslFilePath(String text) {
		this.context.dslFilePath = text;
	}
	
	@Override
	public boolean canFlipToNextPage () {
		return enableNext;
	}
	

	public void setProjectName(String nameProject) {
		this.txtProjectName.setText(nameProject);
		this.context.projectName = nameProject;
	}
	
}
