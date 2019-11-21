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
package org.eclipse.gemoc.xdsmlframework.commons.ui.k3.wizards;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.wizards.IWizardDescriptor;
import org.eclipse.gemoc.commons.eclipse.core.resources.NewProjectWorkspaceListener;
import org.eclipse.gemoc.commons.eclipse.pde.wizards.pages.pde.TemplateListSelectionPage;
import org.eclipse.gemoc.commons.eclipse.ui.WizardFinder;
import org.eclipse.gemoc.executionframework.ui.xdsml.activefile.ActiveFile;
import org.eclipse.gemoc.executionframework.ui.xdsml.activefile.ActiveFileEcore;
import org.eclipse.gemoc.xdsmlframework.commons.ui.k3.Activator;
import org.eclipse.gemoc.xdsmlframework.ui.utils.XDSMLProjectHelper;

import fr.inria.diverse.k3.ui.wizards.NewK3ProjectWizard;
import fr.inria.diverse.k3.ui.wizards.pages.NewK3ProjectWizardFields.KindsOfProject;

public class CreateDSAWizardContextActionDSAK3 extends CreateDSAWizardContextBase {
	
	IProject createdProject;

	public CreateDSAWizardContextActionDSAK3(IProject gemocLanguageIProject) {
		super(gemocLanguageIProject);
	}
	

	public void createNewDSAProject() {
		createNewDSAProject(null);
	}
	
//	@Override
	public void createNewDSAProject(IFile ecoreFile) {
		// launch DSA Kermeta New wizard		
		IWizardDescriptor descriptor = WizardFinder.findNewWizardDescriptor("fr.inria.diverse.k3.ui.wizards.WizardNewProjectK3Plugin");
		
		// Then if we have a wizard, open it.
			if(descriptor == null) Activator.error("failled to find wizard descriptor with id = fr.inria.diverse.k3.ui.wizards.WizardNewProjectK3Plugin", null);
			if (descriptor != null) {
			// add a listener to capture the creation of the resulting project
			NewProjectWorkspaceListener workspaceListener = new NewProjectWorkspaceListener();
			ResourcesPlugin.getWorkspace().addResourceChangeListener(workspaceListener);
			try {
				IWizard wizard;
				wizard = descriptor.createWizard();
				// this wizard need some dedicated initialization
				NewK3ProjectWizard k3Wizard = (NewK3ProjectWizard)wizard;
				//k3Wizard.init() is empty -> useless?
//				k3Wizard.init(PlatformUI.getWorkbench(), (IStructuredSelection) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService().getSelection());
				
				if(ecoreFile == null){
					ActiveFile activeFileEcore = new ActiveFileEcore(_gemocLanguageIProject);
					ecoreFile = activeFileEcore.getActiveFile();
				}
				
				try{
					String p = ecoreFile.getLocationURI().toString().replaceFirst("\\.ecore$", ".genmodel");
					GenModel genmodel = loadGenmodel(p);
					GenPackage root = genmodel.getGenPackages().get(0);
					String base = root.getBasePackage();
					if(base ==  null) base = "";
					k3Wizard.getContext().basePackage = base;
					
				}
				catch(Exception e){
				}
				
				k3Wizard.getContext().ecoreIFile = ecoreFile;
				
				WizardDialog wd = new WizardDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), wizard);
				
				wd.create();

				k3Wizard.getPageProject().setProjectName(XDSMLProjectHelper.baseProjectName(_gemocLanguageIProject)+".k3dsa");
				k3Wizard.getPageProject().setProjectKind(KindsOfProject.PLUGIN);
				// set field as much as possible
				
				if (ecoreFile != null) {
					TemplateListSelectionPage templatePage = k3Wizard.getTemplateListSelectionPage(k3Wizard.getContext());
					templatePage.setUseTemplate(true);
					templatePage.setInitialTemplateId("fr.inria.diverse.k3.ui.templates.projectContent.UserEcoreBasicAspect");
					templatePage.selectTemplate("fr.inria.diverse.k3.ui.templates.projectContent.UserEcoreBasicAspect");
					//((NewK3ProjectWizard)wizard).getPageProject().setEcoreLoaded(ecoreFile);
				}
				wd.setTitle("New Kermeta 3 project");
				
				
				int res = wd.open();
				if(res == WizardDialog.OK){
					//((KermetaProjectNewWizard )wizard).performFinish();
					ResourcesPlugin.getWorkspace().removeResourceChangeListener(workspaceListener);
					createdProject = workspaceListener.getLastCreatedProject();
					// update the project configuration model
					if(createdProject != null){
						// maybe we should update the melange model
					}
				}
			} catch (CoreException e) {
				Activator.error(e.getMessage(), e);
			}
			finally{
				// make sure to remove listener in all situations
				ResourcesPlugin.getWorkspace().removeResourceChangeListener(workspaceListener);
			}
		}
	}
	
	public IProject getLastCreatedProject(){
		return createdProject;
	}

	private GenModel loadGenmodel(String path) {
		try {
			if (!EPackage.Registry.INSTANCE.containsKey(GenModelPackage.eNS_URI))
				EPackage.Registry.INSTANCE.put(GenModelPackage.eNS_URI, GenModelPackage.eINSTANCE);

			Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("genmodel", new XMIResourceFactoryImpl());

			ResourceSet rs = new ResourceSetImpl();
			URI uri = URI.createURI(path);
			Resource pkg = rs.getResource(uri, true);

			return (GenModel) pkg.getContents().get(0);
		} catch (Exception e) {
			Activator.error(e.getMessage(), e);
		}

		return null;
	}
}
