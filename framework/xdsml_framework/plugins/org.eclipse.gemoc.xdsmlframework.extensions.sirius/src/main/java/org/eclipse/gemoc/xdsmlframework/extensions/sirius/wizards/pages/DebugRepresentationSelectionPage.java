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
package org.eclipse.gemoc.xdsmlframework.extensions.sirius.wizards.pages;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.sirius.business.api.componentization.ViewpointRegistry;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.gemoc.xdsmlframework.extensions.sirius.wizards.NewGemocDebugRepresentationWizard;

/**
 * The representation selection page.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 *
 */
public class DebugRepresentationSelectionPage extends WizardPage {

	private final NewGemocDebugRepresentationWizard newGemocDebugRepresentationWizard;

	/** array id for viewpoint definition  */
	public static final int CREATE_VIEWPOINT_DEFINITION = 0;

	/** array id for viewpoint extension  */
	public static final int CREATE_VIEWPOINT_EXTENSION = 1;

	/** array id for new_layer  */
	public static final int ADD_DEBUG_LAYER = 2;

	private final Button[] radio = new Button[3];

	private int selected = -1;

	public DebugRepresentationSelectionPage(NewGemocDebugRepresentationWizard newGemocDebugRepresentationWizard) {
		super("Select debug representation mode");
		this.newGemocDebugRepresentationWizard = newGemocDebugRepresentationWizard;
	}

	@Override
	public void createControl(Composite parent) {
		final Composite composite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.numColumns = 1;
		composite.setLayout(layout);

		radio[CREATE_VIEWPOINT_DEFINITION] = new Button(composite, SWT.RADIO);
		radio[CREATE_VIEWPOINT_DEFINITION].setText("Create a debug diagram description.");
		radio[CREATE_VIEWPOINT_DEFINITION]
				.setToolTipText("creates a dedicated Viewpoint Specification in a new project");
		radio[CREATE_VIEWPOINT_DEFINITION].setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		radio[CREATE_VIEWPOINT_DEFINITION].addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event event) {
				selected = CREATE_VIEWPOINT_DEFINITION;
				setPageComplete(true);
			}
		});

		radio[CREATE_VIEWPOINT_EXTENSION] = new Button(composite, SWT.RADIO);
		radio[CREATE_VIEWPOINT_EXTENSION].setText("Extends an existing diagram description.");
		radio[CREATE_VIEWPOINT_EXTENSION].setToolTipText(
				"creates a dedicated Viewpoint Specification in a new project as an extension of an existing Viewpoint Specification.");
		radio[CREATE_VIEWPOINT_EXTENSION].setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		radio[CREATE_VIEWPOINT_EXTENSION].addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event event) {
				selected = CREATE_VIEWPOINT_EXTENSION;
				final List<DiagramDescription> descriptions = new ArrayList<DiagramDescription>();
				for (Viewpoint viewpoint : ViewpointRegistry.getInstance().getViewpoints()) {
					for (RepresentationDescription description : viewpoint.getOwnedRepresentations()) {
						if (description instanceof DiagramDescription) {
							descriptions.add((DiagramDescription) description);
						}
					}
					// TODO extensions ?
					// for (RepresentationDescription description :
					// viewpoint.getOwnedRepresentationExtensions())
					// {
					// if (description instanceof
					// DiagramDescription) {
					// descriptions.add((DiagramDescription)
					// description);
					// }
					// }
				}
				newGemocDebugRepresentationWizard.getSelectDiagramDefinitionPage().setDescriptions(descriptions);
				setPageComplete(true);
			}
		});

		radio[ADD_DEBUG_LAYER] = new Button(composite, SWT.RADIO);
		radio[ADD_DEBUG_LAYER].setText("Add a debug layer to an existing diagram description.");
		radio[ADD_DEBUG_LAYER].setToolTipText("modifies an existing Viewpoint Specification to add a layer.");

		radio[ADD_DEBUG_LAYER].setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		radio[ADD_DEBUG_LAYER].addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event event) {
				selected = ADD_DEBUG_LAYER;
				final List<DiagramDescription> descriptions = new ArrayList<DiagramDescription>();
				for (Viewpoint viewpoint : ViewpointRegistry.getInstance().getViewpoints()) {
					if (!ViewpointRegistry.getInstance().isFromPlugin(viewpoint)) {
						for (RepresentationDescription description : viewpoint.getOwnedRepresentations()) {
							if (description instanceof DiagramDescription) {
								descriptions.add((DiagramDescription) description);
							}
						}
						// TODO extensions ?
						// for (RepresentationDescription description :
						// viewpoint.getOwnedRepresentationExtensions()) {
						// if (description instanceof DiagramDescription) {
						// descriptions.add((DiagramDescription)
						// description);
						// }
						// }
					}
				}
				newGemocDebugRepresentationWizard.getSelectDiagramDefinitionPage().setDescriptions(descriptions);
				setPageComplete(true);
			}
		});

		setControl(composite);
		setPageComplete(false);
	}

	public int getSelected() {
		return selected;
	}

	@Override
	public IWizardPage getNextPage() {
		final IWizardPage res;

		if (selected == CREATE_VIEWPOINT_DEFINITION) {
			res = newGemocDebugRepresentationWizard.getNewViewPointProjectPage();
		} else if (selected == CREATE_VIEWPOINT_EXTENSION || selected == ADD_DEBUG_LAYER) {
			res = newGemocDebugRepresentationWizard.getSelectDiagramDefinitionPage();
		} else {
			res = null;
		}

		return res;
	}

	@Override
	public void dispose() {
		super.dispose();
		radio[CREATE_VIEWPOINT_DEFINITION].dispose();
		radio[CREATE_VIEWPOINT_EXTENSION].dispose();
		radio[ADD_DEBUG_LAYER].dispose();
	}

}