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
package org.eclipse.gemoc.xdsmlframework.ide.ui.xdsml.wizards;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.ui.PlatformUI;
import org.eclipse.gemoc.xdsmlframework.ide.ui.xdsml.wizards.CreateEditorProjectWizardContextAction.CreateEditorProjectAction;




/**
 * Select the concrete action that will be launched
 * optionnally ask for missing info not present in the context 
 * @author dvojtise
 *
 */
public class CreateEditorProjectWizardPage  extends WizardPage {

	
	protected CreateEditorProjectWizardContextAction context;
	
	private Composite 	container;
	
	public CreateEditorProjectWizardPage(String pageName, CreateEditorProjectWizardContextAction context) {
		super(pageName);
		this.setDescription("Select the wizard you wish to use to create a concrete syntax editor project for your executable language.");
		this.setTitle(pageName);
		this.context = context;
	}
	
	@Override
	public void performHelp(){
		//PlatformUI.getWorkbench().getHelpSystem().displayHelp();
		PlatformUI.getWorkbench().getHelpSystem().displayHelpResource("/org.eclipse.gemoc.gemoc_language_workbench.documentation/html/ConcreteSyntaxConfigurator.html");
	}

	@Override
	public void createControl(Composite parent) {
		
		container = new Composite(parent, SWT.FILL);
		container.setLayout(new RowLayout (SWT.VERTICAL));
		/*Text 		txtProjectName;
		txtProjectName = new Text(container, SWT.BORDER);
		txtProjectName.setBounds(93, 7, 255, 21);
		txtProjectName.setText("Ceci est un essai");		
		*/
		Group actionSelection = new Group(container, SWT.FILL);
		actionSelection.setLayout (new RowLayout (SWT.VERTICAL));
		actionSelection.setText("Select action");
		//actionSelection.setBounds(10, 10, 193, 85);
	    
	    Button createEMFProject = new Button(actionSelection, SWT.RADIO);
	    createEMFProject.setText("Create new EMF Tree Editor project");
	    createEMFProject.setSelection(true);
	    createEMFProject.addListener (SWT.Selection, new Listener () {
			public void handleEvent (Event event) {
				// update context and potentially add new pages ?
				context.actionToExecute = CreateEditorProjectAction.CREATE_NEW_EMFTREE_PROJECT;
			}
		});
	    Button createXTextProject = new Button(actionSelection, SWT.RADIO);
	    createXTextProject.setText("Create new xText Editor project");
	    createXTextProject.addListener (SWT.Selection, new Listener () {
			public void handleEvent (Event event) {
				// update context and potentially add new pages ?
				context.actionToExecute = CreateEditorProjectAction.CREATE_NEW_XTEXT_PROJECT;
			}
		});
	    Button createODProject = new Button(actionSelection, SWT.RADIO);
	    createODProject.setText("Create new Sirius Viewpoint Specification project");
	    createODProject.addListener (SWT.Selection, new Listener () {
			public void handleEvent (Event event) {
				// update context and potentially add new pages ?
				context.actionToExecute = CreateEditorProjectAction.CREATE_NEW_SIRIUS_PROJECT;
			}
		});
	    Button selectExistingEMFTreeProject = new Button(actionSelection, SWT.RADIO);
	    selectExistingEMFTreeProject.setText("Select existing tree editor project");  
	    selectExistingEMFTreeProject.addListener (SWT.Selection, new Listener () {
			public void handleEvent (Event event) {
				// update context and potentially add new pages ?
				context.actionToExecute = CreateEditorProjectAction.SELECT_EXISTING_EMFTREE_PROJECT;
			}
		}); 
		
	    Button selectExistingXTextProject = new Button(actionSelection, SWT.RADIO);
	    selectExistingXTextProject.setText("Select existing Xtext editor project");  
	    selectExistingXTextProject.addListener (SWT.Selection, new Listener () {
			public void handleEvent (Event event) {
				// update context and potentially add new pages ?
				context.actionToExecute = CreateEditorProjectAction.SELECT_EXISTING_XTEXT_PROJECT;
			}
		});
	    
	    Button selectExistingSiriusProject = new Button(actionSelection, SWT.RADIO);
	    selectExistingSiriusProject.setText("Select existing Sirius Viewpoint Specification project");  
	    selectExistingSiriusProject.addListener (SWT.Selection, new Listener () {
			public void handleEvent (Event event) {
				// update context and potentially add new pages ?
				context.actionToExecute = CreateEditorProjectAction.SELECT_EXISTING_OD_PROJECT;
			}
		});
	    
		// Required to avoid an error in the system
		setControl(container);
		setPageComplete(true);
	}
	

}
