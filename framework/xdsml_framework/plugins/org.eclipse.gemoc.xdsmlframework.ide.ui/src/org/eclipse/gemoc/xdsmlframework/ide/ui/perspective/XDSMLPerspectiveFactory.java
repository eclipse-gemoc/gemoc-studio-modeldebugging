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
package org.eclipse.gemoc.xdsmlframework.ide.ui.perspective;

import org.eclipse.debug.ui.IDebugUIConstants;
import org.eclipse.jdt.ui.JavaUI;
import org.eclipse.pde.internal.launching.IPDEConstants;
import org.eclipse.pde.internal.ui.IPDEUIConstants;
import org.eclipse.pde.internal.ui.views.target.TargetStateView;
import org.eclipse.sirius.ui.tools.api.views.modelexplorerview.IModelExplorerView;
import org.eclipse.sirius.ui.tools.internal.wizards.ModelingProjectWizard;
import org.eclipse.sirius.ui.tools.internal.wizards.NewSessionWizard;
import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import org.eclipse.ui.console.IConsoleConstants;


/**
 * Base perspective inspired from Plugin, Java and Sirius perspective
 * @author dvojtise
 *
 */
public class XDSMLPerspectiveFactory implements IPerspectiveFactory {

    
    /**
     * {@inheritDoc}
     */
    public void createInitialLayout(final IPageLayout layout) {
        defineActions(layout);
        defineLayout(layout);
    }

    /**
     * add items and actions set to the window
     * 
     * @param layout
     *            layout of the perspective
     */
    protected void defineActions(final IPageLayout layout) {
        // project wizards
        layout.addNewWizardShortcut("org.eclipse.pde.ui.NewProjectWizard");
        layout.addNewWizardShortcut("org.eclipse.ecoretools.emf.design.wizardID");
        layout.addNewWizardShortcut("org.eclipse.sirius.ui.specificationproject.wizard");
        layout.addNewWizardShortcut(ModelingProjectWizard.ID);
        layout.addNewWizardShortcut("fr.inria.diverse.k3.ui.wizards.WizardNewProjectK3Plugin");
        // project content wizards
		layout.addNewWizardShortcut("org.eclipse.jdt.ui.wizards.NewPackageCreationWizard"); //$NON-NLS-1$
        layout.addNewWizardShortcut("org.eclipse.ui.wizards.new.folder"); //$NON-NLS-1$ 
        layout.addNewWizardShortcut("org.eclipse.ui.wizards.new.file"); //$NON-NLS-1$
        layout.addNewWizardShortcut(NewSessionWizard.ID);
        layout.addNewWizardShortcut("org.eclipse.sirius.presentation.DescriptionModelWizardID");
        
        
        // show view shortcuts
        layout.addShowViewShortcut(IPageLayout.ID_PROJECT_EXPLORER);
        layout.addShowViewShortcut(JavaUI.ID_PACKAGES);
        layout.addShowViewShortcut(IModelExplorerView.ID);
        layout.addShowViewShortcut(IPageLayout.ID_PROP_SHEET);
        layout.addShowViewShortcut(IPageLayout.ID_OUTLINE);
        layout.addShowViewShortcut(IPageLayout.ID_PROBLEM_VIEW);
        layout.addShowViewShortcut(IPDEUIConstants.PLUGINS_VIEW_ID);
        layout.addShowViewShortcut("org.eclipse.pde.runtime.RegistryBrowser");
        layout.addShowViewShortcut(JavaUI.ID_TYPE_HIERARCHY);
        layout.addShowViewShortcut(JavaUI.ID_JAVADOC_VIEW);
        layout.addShowViewShortcut(JavaUI.ID_SOURCE_VIEW);
        layout.addShowViewShortcut("org.eclipse.emf.ecoretools.registration.viewregisteredpackages");
		layout.addShowViewShortcut(IConsoleConstants.ID_CONSOLE_VIEW);
		layout.addShowViewShortcut("org.eclipse.emf.ecoretools.internal.views.EClassHierarchyView");
		layout.addShowViewShortcut("org.eclipse.emf.ecoretools.internal.views.EReferencesView");
        
        // 'Window' > 'Open Perspective' contributions
        layout.addPerspectiveShortcut("org.eclipse.sirius.ui.tools.perspective.design");
        layout.addPerspectiveShortcut("org.eclipse.pde.ui.PDEPerspective");
     	layout.addPerspectiveShortcut(JavaUI.ID_PERSPECTIVE);
     	layout.addPerspectiveShortcut(JavaUI.ID_BROWSING_PERSPECTIVE);
     	layout.addPerspectiveShortcut(IDebugUIConstants.ID_DEBUG_PERSPECTIVE);
     	layout.addPerspectiveShortcut("org.eclipse.emf.ecoretools.perspective");
     	
     	// Action set
		layout.addActionSet(IDebugUIConstants.LAUNCH_ACTION_SET);
		layout.addActionSet(JavaUI.ID_ACTION_SET);
		layout.addActionSet(JavaUI.ID_ELEMENT_CREATION_ACTION_SET);
		layout.addActionSet(IPageLayout.ID_NAVIGATE_ACTION_SET);
    }

    /**
     * add views to the layout
     * 
     * @param layout
     *            layout of the perspective
     */
    protected void defineLayout(final IPageLayout layout) {
        final String editorArea = layout.getEditorArea();
        
        // Left
        IFolderLayout topLeft = layout.createFolder("topLeft", //$NON-NLS-1$
				IPageLayout.LEFT, 0.25f, editorArea);
		topLeft.addView(IPageLayout.ID_PROJECT_EXPLORER);
		topLeft.addView(IModelExplorerView.ID);
		topLeft.addPlaceholder(JavaUI.ID_PACKAGES);
		topLeft.addPlaceholder(JavaUI.ID_TYPE_HIERARCHY);
		topLeft.addPlaceholder(IPDEUIConstants.PLUGINS_VIEW_ID);
		
		// Bottom
		IFolderLayout bottom = layout.createFolder("bottom", //$NON-NLS-1$
				IPageLayout.BOTTOM, 0.75f, editorArea);
        bottom.addView(IPageLayout.ID_PROP_SHEET);
		bottom.addView(IPageLayout.ID_PROBLEM_VIEW);
		bottom.addView("org.eclipse.pde.runtime.LogView");
		bottom.addView(JavaUI.ID_JAVADOC_VIEW);
		bottom.addPlaceholder(JavaUI.ID_SOURCE_VIEW);
		bottom.addPlaceholder(TargetStateView.VIEW_ID);
		bottom.addPlaceholder(IConsoleConstants.ID_CONSOLE_VIEW);
        
		// Bottom Right
		IFolderLayout bottomright = layout.createFolder("bottomRight", IPageLayout.RIGHT, (float) 0.60, "bottom"); //$NON-NLS-1$ //$NON-NLS-2$
		bottomright.addPlaceholder("org.eclipse.emf.ecoretools.internal.views.EClassHierarchyView");
		bottomright.addPlaceholder("org.eclipse.emf.ecoretools.internal.views.EReferencesView");
        
		// Right
		IFolderLayout outlineFolder = layout.createFolder("right", IPageLayout.RIGHT, (float)0.75, editorArea); //$NON-NLS-1$
		outlineFolder.addView(IPageLayout.ID_OUTLINE);

    }


}
