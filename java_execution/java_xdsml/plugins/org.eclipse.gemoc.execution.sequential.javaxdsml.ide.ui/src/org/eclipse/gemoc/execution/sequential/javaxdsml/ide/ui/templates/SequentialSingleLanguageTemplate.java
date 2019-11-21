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
package org.eclipse.gemoc.execution.sequential.javaxdsml.ide.ui.templates;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.StringJoiner;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.ui.dialogs.WorkspaceResourceDialog;
import org.eclipse.gemoc.commons.eclipse.core.resources.FileFinderVisitor;
import org.eclipse.gemoc.commons.eclipse.pde.manifest.ManifestChanger;
import org.eclipse.gemoc.commons.eclipse.pde.wizards.pages.pde.ui.BaseProjectWizardFields;
import org.eclipse.gemoc.commons.eclipse.pde.wizards.pages.pde.ui.templates.AbstractStringWithButtonOption;
import org.eclipse.gemoc.commons.eclipse.pde.wizards.pages.pde.ui.templates.TemplateOption;
import org.eclipse.gemoc.execution.sequential.javaxdsml.ide.ui.Activator;
import org.eclipse.gemoc.execution.sequential.javaxdsml.ide.ui.wizards.pages.NewGemocLanguageProjectWizardFields;
import org.eclipse.gemoc.xdsmlframework.commons.ui.k3.dialogs.SelectDSAIProjectDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.xtext.util.Strings;
import org.osgi.framework.BundleException;


public class SequentialSingleLanguageTemplate extends JavaxdsmlTemplateSection {
	public static final String KEY_LANGUAGE_FILE_NAME = "languageFileName"; //$NON-NLS-1$
	public static final String KEY_ASPECTCLASS_POSTFIX = "aspectClassPostfix"; //$NON-NLS-1$
	public static final String KEY_LANGUAGE_NAME = "languageName"; //$NON-NLS-1$
	public static final String METAMODEL_NAME = "MyLanguage"; //$NON-NLS-1$
	public static final String KEY_ECOREFILE_PATH = "ecoreFilePath"; //$NON-NLS-1$


	public static final String KEY_LANGUAGE_NAME_LCFIRST = "languageNameLCFirst"; //$NON-NLS-1$
	public static final String KEY_IS_SYNTAX_COMMENTED = "isSyntaxStatementCommented"; //$NON-NLS-1$
	public static final String KEY_ASPECTS = "listOfAspects"; //$NON-NLS-1$
	
	protected static final List<String> FILE_EXTENSIONS = Arrays.asList(new String [] { "ecore" });
	

	NewGemocLanguageProjectWizardFields _data;
	
	// other data specific to this template
	public IFile 		ecoreIFile;
	protected String 	ecoreProjectPath;
	protected String 	dsaProjectName;
	
	private TemplateOption dsaProjectLocationOption;
	
	public SequentialSingleLanguageTemplate() {
		super();
		setPageCount(1);
		createOptions();
	}
	
	private void createOptions() {
		addOption(KEY_PACKAGE_NAME, WizardTemplateMessages.SequentialSingleLanguageTemplate_packageName,
				WizardTemplateMessages.SequentialSingleLanguageTemplate_packageNameToolTip, 
				(String) null, 0);
		addOption(KEY_LANGUAGE_NAME, WizardTemplateMessages.SequentialSingleLanguageTemplate_languageMetamodelName,
				WizardTemplateMessages.SequentialSingleLanguageTemplate_languageMetamodelNameToolTip, 
				METAMODEL_NAME, 0);
		

		addBlankField(0);
		addOption(KEY_LANGUAGE_FILE_NAME, WizardTemplateMessages.SequentialSingleLanguageTemplate_languageFileName, 
				WizardTemplateMessages.SequentialSingleLanguageTemplate_languageFileNameTooltip, 
				WizardTemplateMessages.SequentialSingleLanguageTemplate_languageDefaultFileName, 0);

		addBlankField(0);
		TemplateOption ecoreLocationOption  = new AbstractStringWithButtonOption(this, KEY_ECOREFILE_PATH, 
				WizardTemplateMessages.SequentialSingleLanguageTemplate_ecoreFileLocation,
				WizardTemplateMessages.SequentialSingleLanguageTemplate_ecoreFileLocationTooltip) {
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
					SequentialSingleLanguageTemplate.this.ecoreIFile = files[0];
					//txtPathEcore.setText(files[i].getFullPath().toOSString());
					SequentialSingleLanguageTemplate.this.ecoreProjectPath = files[0].getProject().getFullPath().toString();
					String ecorePath = files[0].getFullPath().toString();
					if(ecorePath.charAt(0) == '/')
						ecorePath = ecorePath.substring(1);
					return ecorePath;
				}

				return null;
			}
		};
		ecoreLocationOption.setRequired(false);
		registerOption(ecoreLocationOption, (String) null, 0);
		dsaProjectLocationOption  = new AbstractStringWithButtonOption(this, KEY_ASPECTS, 
				WizardTemplateMessages.SequentialSingleLanguageTemplate_dsaProjectName,
				WizardTemplateMessages.SequentialSingleLanguageTemplate_dsaProjectNameTooltip) {

			@Override
			public String doSelectButton() {
				
				SelectDSAIProjectDialog dialog = new SelectDSAIProjectDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell());
				dialog.open();
				
				Object[] selection = dialog.getResult();
				if(selection != null && selection.length > 0){
					dsaProjectName = ((IProject) selection[0]).getName();
					return dsaProjectName;
				}
				
				return null;
			}
		};
		dsaProjectLocationOption.setRequired(false);
		registerOption(dsaProjectLocationOption, (String) null, 0);
	}
	
	public void addPages(Wizard wizard) {
		WizardPage page = createPage(0, IHelpContextIds.TEMPLATE_SEQUENTIAL_SINGLE_LANGUAGE);
		page.setTitle(WizardTemplateMessages.SequentialSingleLanguageTemplate_title);
		page.setDescription(WizardTemplateMessages.SequentialSingleLanguageTemplate_desc);
		wizard.addPage(page);
		markPagesAdded();
	}

	public boolean isDependentOnParentWizard() {
		return true;
	}
	
	public void updateOptions(String packageName, String languageName, String fileName){
		TemplateOption[] allOptions = getOptions(0);
		for(TemplateOption option : allOptions){
			if(option.getName().equals(KEY_PACKAGE_NAME) && packageName != null){
				option.setValue(packageName);
			}
			else if(option.getName().equals(KEY_LANGUAGE_NAME) && languageName != null){
				option.setValue(languageName);
			}
			else if(option.getName().equals(KEY_LANGUAGE_FILE_NAME) && fileName != null){
				option.setValue(fileName);
			}
		}
	}
	
	protected void initializeFields(BaseProjectWizardFields data) {
		final String projectName = ((NewGemocLanguageProjectWizardFields)data).projectName;
		String packageName = inferPackageNameFromProjectName(projectName);
		initializeOption(KEY_PACKAGE_NAME, packageName);
		_data = (NewGemocLanguageProjectWizardFields) data;
		String languageName = inferLanguageNameFromProjectName(projectName);
		updateOptions(packageName, languageName, languageName);
	}

	/**
	 * Infers a name for the package based on the project name.
	 * It uses the dot as separator and avoid xdsml, model, and dsl as name.
	 * The returned name has it first letter capitalized.
	 * For example, on org.company.myLanguage.melange, it will return org.company.mylanguage
	 * @param projectName
	 * @return
	 */
	protected String inferPackageNameFromProjectName(String projectName){
		String projectNameCandidate = projectName;
		projectNameCandidate = removePostFix(projectNameCandidate, ".xdsml");
		projectNameCandidate = removePostFix(projectNameCandidate, ".model");
		projectNameCandidate = removePostFix(projectNameCandidate, ".dsl");		
		return getFormattedPackageName(projectNameCandidate);
	}
	
	/**
	 * Infers a name for the language based on the project name.
	 * It uses the dot as separator and avoid xdsml and model as name.
	 * The returned name has it first letter capitalized.
	 * For example, on org.company.mylanguage, it will return Mylanguage
	 * @param projectName
	 * @return
	 */
	protected String inferLanguageNameFromProjectName(String projectName){
		String projectNameCandidate = projectName;
		projectNameCandidate = removePostFix(projectNameCandidate, ".xdsml");
		projectNameCandidate = removePostFix(projectNameCandidate, ".model");
		projectNameCandidate = removePostFix(projectNameCandidate, ".dsl");
		if(projectNameCandidate.contains(".") && !projectNameCandidate.endsWith(".")){
			projectNameCandidate = projectNameCandidate.substring(projectNameCandidate.lastIndexOf(".")+1);
		}		
		return Strings.toFirstUpper(projectNameCandidate);
	}
	
	protected String removePostFix(String baseString, String postFix){
		if(baseString.endsWith(postFix)) {
			return baseString.substring(0, baseString.lastIndexOf(postFix));
		} else {
			return baseString;
		}
	}

	public String getUsedExtensionPoint() {
		return "org.eclipse.ui.actionSets"; //$NON-NLS-1$
	}
	
	@Override
	public String getSectionId() {
		return "SequentialSingleLanguage";
	}
	
	@Override
	public URL getTemplateLocation() {
		// Need to override to get the local Activator
		try {
			String[] candidates = getDirectoryCandidates();
			for (int i = 0; i < candidates.length; i++) {
				if (Activator.getDefault().getBundle().getEntry(candidates[i]) != null) {
					URL candidate = new URL(getInstallURL(), candidates[i]);
					return candidate;
				}
			}
		} catch (MalformedURLException e) { // do nothing
		}
		return null;
	}
	
	@Override
	protected URL getInstallURL() {
		// Need to override to get the local Activator
		return Activator.getDefault().getBundle().getEntry("/");
	}
	
	/** find the mapping properties file in the project and retrieves the aspectClasses
	 * 
	 * @param k3IProject
	 * @return
	 */
	public static Set<String> getAspectClassesList(IProject k3IProject){
		HashSet<String> aspectClasses = new HashSet<String>();
		FileFinderVisitor projectVisitor = new FileFinderVisitor("properties");
		try {
			//ResourcesPlugin.getWorkspace().getRoot().getProject(languageDefinition.getDsaProject().getProjectName());
			k3IProject.accept(projectVisitor);
			List<IFile> possibleAspectMappingPropertiesFiles = projectVisitor.getFiles();
			for(IFile aspectMappingPropertiesFile : possibleAspectMappingPropertiesFiles){
				if(aspectMappingPropertiesFile.getName().endsWith("k3_aspect_mapping.properties")){
					Properties properties = new Properties();
					if(aspectMappingPropertiesFile.exists()){
						try {
							properties.load(aspectMappingPropertiesFile.getContents());
							for(Object commaSeparatedPropvalues:properties.values()){
								for(String propVal :((String)commaSeparatedPropvalues).split(",")){
									aspectClasses.add(propVal);
								}
							}
						} catch (IOException e) {
							Activator.error(e.getMessage(), e);
						} catch (CoreException e) {
							Activator.error(e.getMessage(), e);
						}
					}
				}
			}
			
		} catch (CoreException e) {
			Activator.error(e.getMessage(), e);
		}
		return aspectClasses;
	}
	
	@Override
	public void execute(IProject project, IProgressMonitor monitor)
			throws CoreException {

		// adds a virtual option in order to have replacement for these keys
		addOption(KEY_LANGUAGE_NAME_LCFIRST, 
						(String) null,
						(String) null, 
						Strings.toFirstLower(getStringOption(KEY_LANGUAGE_NAME)), 0);
		addOption(KEY_IS_SYNTAX_COMMENTED, 
				(String) null,
				(String) null, 
				ecoreIFile == null?"//":"", 0);
		
		//Replace KEY_ASPECTS' value (which is a project name) by a list of aspects 
		final String DEFAULT_VALUE = "qualified.class.name";
		
		String selection = dsaProjectName;
		if(selection != null && !selection.isEmpty()){
			
			IProject dsaProject = ResourcesPlugin.getWorkspace().getRoot().getProject(selection);
			
			List<String> aspects = new ArrayList<>(getAspectClassesList(dsaProject));
			Collections.sort(aspects);
			
			StringJoiner sj = new StringJoiner(",");
			
			
			
			StringBuilder templateWith = new StringBuilder();
			if(aspects.isEmpty()){
				templateWith.append(DEFAULT_VALUE);
			}
			else{
				for(String aspect : aspects){
					sj.add(aspect);
				}
				templateWith.append(sj);
			}
			dsaProjectLocationOption.setValue(templateWith.toString());
		}
		else{
			dsaProjectLocationOption.setValue(DEFAULT_VALUE);
		}
		
		//Then do the normal stuff
		super.execute(project, monitor);
	}
	
	@Override
	protected void generateFiles(IProgressMonitor monitor) throws CoreException {
		super.generateFiles(monitor);
		
		// now also fix the project configuration
		if(this.dsaProjectName != null && !this.dsaProjectName.isEmpty()){
			ManifestChanger manifestChanger;
			try {
				manifestChanger = new ManifestChanger(project.getFile("META-INF/MANIFEST.MF"));
				manifestChanger.addPluginDependency(this.dsaProjectName, "0.0.0", false, true);
				manifestChanger.commit();
			} catch (IOException | BundleException e) {
			}
		}
		if(this.ecoreIFile != null){
			ManifestChanger manifestChanger;
			try {
				manifestChanger = new ManifestChanger(project.getFile("META-INF/MANIFEST.MF"));
				manifestChanger.addPluginDependency(this.ecoreIFile.getProject().getName(), "0.0.0", false, true);
				manifestChanger.commit();
			} catch (IOException | BundleException e) {
			}
		}
	}
	
	@Override
	public void validateOptions(TemplateOption source) {
		super.validateOptions(source);
		if( source.getName().contentEquals(KEY_LANGUAGE_NAME)){
			String langName = getStringOption(KEY_LANGUAGE_NAME);
			if(langName!=null && !langName.isEmpty() && !Character.isUpperCase(langName.charAt(0))){
				flagErrorOnOption(source, WizardTemplateMessages.FirstCharUpperError);
			}
		}
	}
	

	
}
