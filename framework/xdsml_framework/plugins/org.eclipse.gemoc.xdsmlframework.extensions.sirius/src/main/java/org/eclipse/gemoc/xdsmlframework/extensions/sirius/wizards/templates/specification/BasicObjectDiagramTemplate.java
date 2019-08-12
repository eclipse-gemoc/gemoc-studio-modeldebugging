/*******************************************************************************
 *  Copyright (c) 2019 Inria and others.
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 * 
 *  Contributors:
 *     Inria - initial API and implementation
 *******************************************************************************/
package org.eclipse.gemoc.xdsmlframework.extensions.sirius.wizards.templates.specification;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.ui.dialogs.WorkspaceResourceDialog;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.gemoc.commons.eclipse.core.resources.FileFinderVisitor;
import org.eclipse.gemoc.commons.eclipse.emf.EMFResource;
import org.eclipse.gemoc.commons.eclipse.emf.URIHelper;
import org.eclipse.gemoc.commons.eclipse.pde.wizards.pages.pde.ui.BaseProjectWizardFields;
import org.eclipse.gemoc.commons.eclipse.pde.wizards.pages.pde.ui.templates.AbstractStringWithButtonOption;
import org.eclipse.gemoc.commons.eclipse.pde.wizards.pages.pde.ui.templates.TemplateOption;
import org.eclipse.gemoc.dsl.Dsl;
import org.eclipse.gemoc.dsl.Entry;
import org.eclipse.gemoc.xdsmlframework.extensions.sirius.Activator;
import org.eclipse.gemoc.xdsmlframework.extensions.sirius.IHelpContextIds;
import org.eclipse.gemoc.xdsmlframework.extensions.sirius.m2m.Ecore2BasicObjectDiagramSpecification;
import org.eclipse.gemoc.xdsmlframework.extensions.sirius.wizards.pages.NewGemocSiriusProjectWizardFields;
import org.eclipse.gemoc.xdsmlframework.extensions.sirius.wizards.templates.SiriusTemplateSection;
import org.eclipse.gemoc.xdsmlframework.extensions.sirius.wizards.templates.TemplateMessages;
import org.eclipse.gemoc.xdsmlframework.ui.utils.ENamedElementQualifiedNameLabelProvider;
import org.eclipse.gemoc.xdsmlframework.ui.utils.dialogs.SelectAnyEObjectDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.sirius.viewpoint.description.Group;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.xtext.EcoreUtil2;


public class BasicObjectDiagramTemplate extends SiriusTemplateSection {
	public static final String KEY_DIAGRAM_NAME = "diagramName"; //$NON-NLS-1$
	public static final String KEY_DIAGRAM_ROOT_ECLASS = "diagramRootEClass"; //$NON-NLS-1$
	public static final String KEY_ECOREFILE_PATH = "ecoreFilePath"; //$NON-NLS-1$
	

	protected static final List<String> FILE_EXTENSIONS = Arrays.asList(new String [] { "ecore" });

	NewGemocSiriusProjectWizardFields mainPagesData;
	// template data
	IFile ecoreIFile;
	String diagramRootEClassName;
	
	/**
	 * Constructor for HelloWorldTemplate.
	 */
	public BasicObjectDiagramTemplate() {
		setPageCount(1);
		createOptions();
	}

	/** 
	 * used to retrieve the template folder
	 */
	public String getSectionId() {
		return "miniAspectSample"; //$NON-NLS-1$
	}

	/*
	 * @see ITemplateSection#getNumberOfWorkUnits()
	 */
	public int getNumberOfWorkUnits() {
		return super.getNumberOfWorkUnits() + 1;
	}

	private void createOptions() {
		//addOption(KEY_PACKAGE_NAME, TemplateMessages.MiniAspectSampleTemplate_packageName, (String) null, 0);
		addOption(KEY_DIAGRAM_NAME, TemplateMessages.BasicObjectDiagramTemplate_diagramName, "BasicObjectDiagram", 0);
		TemplateOption ecoreLocationOption  = new AbstractStringWithButtonOption(this, KEY_ECOREFILE_PATH, TemplateMessages.BasicObjectDiagramTemplate_ecoreFilePath) {
			@Override
			public String doSelectButton() {
				final IWorkbenchWindow workbenchWindow = PlatformUI
						.getWorkbench().getActiveWorkbenchWindow();
				Object selection = null;
				if (workbenchWindow.getSelectionService().getSelection() instanceof IStructuredSelection) {
					selection = ((IStructuredSelection) workbenchWindow
							.getSelectionService().getSelection())
							.getFirstElement();
				}
				final IFile selectedEcoreFile = selection != null
						&& selection instanceof IFile
						&& FILE_EXTENSIONS.contains(((IFile) selection)
								.getFileExtension()) ? (IFile) selection : null;
				ViewerFilter viewerFilter = new ViewerFilter() {
					@Override
					public boolean select(Viewer viewer, Object parentElement,
							Object element) {
						if (element instanceof IFile) {
							IFile file = (IFile) element;
							return FILE_EXTENSIONS.contains(file
									.getFileExtension())
									&& (selectedEcoreFile == null || !selectedEcoreFile
											.getFullPath().equals(
													file.getFullPath()));
						}
						return true;
					}
				};
				final IFile[] files = WorkspaceResourceDialog
						.openFileSelection(workbenchWindow.getShell(), null,
								"Select ecore", true, null,
								Collections.singletonList(viewerFilter));
				if (files.length > 0) {
					BasicObjectDiagramTemplate.this.ecoreIFile = files[0];
					//txtPathEcore.setText(files[i].getFullPath().toOSString());
					//UserEcoreBasicAspectTemplate.this._data.ecoreProjectPath = files[0].getProject().getFullPath().toOSString();
					return files[0].getFullPath().toOSString();
				}

				return null;
			}
		};
		registerOption(ecoreLocationOption, (String) null, 0);
		TemplateOption rootEClassOption  = new AbstractStringWithButtonOption(this, KEY_DIAGRAM_ROOT_ECLASS, TemplateMessages.BasicObjectDiagramTemplate_diagramRootEClass) {
			@Override
			public String doSelectButton() {
				
				Resource ecoreRes = EMFResource.getResource(BasicObjectDiagramTemplate.this.ecoreIFile);
				ENamedElementQualifiedNameLabelProvider labelProvider = new ENamedElementQualifiedNameLabelProvider();
				SelectAnyEObjectDialog dialog = new SelectAnyEObjectDialog(
						PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),
						ecoreRes.getResourceSet(),
						labelProvider )
				{
					protected boolean select(EObject obj) {
						return obj instanceof EClass;
					}
				};
				int res = dialog.open();
				if (res == WizardDialog.OK) {
					EObject selection = (EObject) dialog.getFirstResult();
					BasicObjectDiagramTemplate.this.diagramRootEClassName = labelProvider.getText(selection);
					return BasicObjectDiagramTemplate.this.diagramRootEClassName;
				}
				return null;
			}
		};
		registerOption(rootEClassOption, (String) null, 0);
	}

	public void addPages(Wizard wizard) {
		WizardPage page = createPage(0, IHelpContextIds.TEMPLATE_BasicObjectDiagram);
		page.setTitle(TemplateMessages.BasicObjectDiagramTemplate_title);
		page.setDescription(TemplateMessages.BasicObjectDiagramTemplate_desc);
		wizard.addPage(page);
		markPagesAdded();
	}

	public boolean isDependentOnParentWizard() {
		return true;
	}

	protected void initializeFields(BaseProjectWizardFields data) {
		mainPagesData = (NewGemocSiriusProjectWizardFields)data;
		//	initializeOption(KEY_PACKAGE_NAME, packageName);
		if(ecoreIFile != null){
			initializeOption(KEY_ECOREFILE_PATH,ecoreIFile.getFullPath().toOSString());
		}
		// initialize ecore location from dsl file information, (ie. look for "ecore" entry in the property file)
		if(mainPagesData.dslFilePath != null && !mainPagesData.dslFilePath.isEmpty()) {
			Resource res = (new ResourceSetImpl()).getResource(URI.createURI(mainPagesData.dslFilePath), true);
			Dsl dsl = (Dsl) res.getContents().get(0);
			
			Optional<Entry> ecore = dsl.getEntries()
				.stream()
				.filter(entry -> entry.getKey().equals("ecore"))
				.findFirst();
			if(ecore.isPresent()) {
				String[] ecores = ecore.get().getValue().split(",");
				if(ecores.length > 0) {
					String ecoreUri = ecores[0];
					String path = URIHelper.removePlatformScheme(URI.createURI(ecoreUri));
					initializeOption(KEY_ECOREFILE_PATH,path);
					ecoreIFile =  ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(path));
					
				}
			}
		}
		// get first EClass as root
		Resource ecoreRes = EMFResource.getResource(BasicObjectDiagramTemplate.this.ecoreIFile);
		Optional<EClass> eClass = EcoreUtil2.eAllContentsAsList(ecoreRes)
				.stream()
				.filter(e -> e instanceof EClass)
				.map(e -> (EClass)e).findAny();
		if(eClass.isPresent()) {
			initializeOption(KEY_DIAGRAM_ROOT_ECLASS,new ENamedElementQualifiedNameLabelProvider().getText(eClass.get()));
		}
	}
	

	public String getUsedExtensionPoint() {
		return "org.eclipse.ui.actionSets"; //$NON-NLS-1$
	}


	/* (non-Javadoc)
	 * @see org.eclipse.pde.ui.templates.ITemplateSection#getFoldersToInclude()
	 */
	public String[] getNewFiles() {
		return new String[] {"icons/"}; //$NON-NLS-1$
	}
	

	/* (non-Javadoc)
	 * @see org.eclipse.gemoc.xdsmlframework.extensions.sirius.wizards.templates.SiriusTemplateSection#generateFiles(org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	protected void generateFiles(IProgressMonitor monitor) throws CoreException {
		super.generateFiles(monitor);

		// update the .odesign file with appropriate data
		FileFinderVisitor visitor = new FileFinderVisitor("odesign");
		this.project.accept(visitor);
		this.project.exists();
		final IFile file = visitor.getFile();
		if(file != null) {
			try {
				// load ecore and design files (they are in 2 separate resourceSet)
				Resource odesignRes = EMFResource.getResource(file);
				Resource ecoreRes = EMFResource.getResource(ecoreIFile);
				ecoreRes.load(Collections.EMPTY_MAP);
				odesignRes.load(Collections.EMPTY_MAP);
				
				Group contents = (Group) odesignRes.getContents().get(0);
				EList<Viewpoint> viewpoints = contents.getOwnedViewpoints();
				Viewpoint viewpoint = viewpoints.get(0);
				String diagramName = (String) this.getValue(KEY_DIAGRAM_NAME);
				String rootEClassName = (String) this.getValue(KEY_DIAGRAM_ROOT_ECLASS);
				List<EClass> allEClasses = EcoreUtil2.eAllContentsAsList(ecoreRes)
						.stream()
						.filter(e -> e instanceof EClass)
						.map(e -> (EClass)e)
						.collect(Collectors.toList());
				List<EPackage> allEPackages = EcoreUtil2.eAllContentsAsList(ecoreRes)
						.stream()
						.filter(p -> p instanceof EPackage)
						.map(p -> (EPackage)p)
						.collect(Collectors.toList());
				Ecore2BasicObjectDiagramSpecification diagramBuilder = new Ecore2BasicObjectDiagramSpecification(
						viewpoint, 
						diagramName, 
						allEClasses, 
						allEPackages, 
						rootEClassName);
				diagramBuilder.addBasicObjectDiagram();
				
				
				odesignRes.save(null);
			} catch (IOException e) {
				Activator.logErrorMessage(e.getMessage(), e);
			}

		}
		
	}
	
}
