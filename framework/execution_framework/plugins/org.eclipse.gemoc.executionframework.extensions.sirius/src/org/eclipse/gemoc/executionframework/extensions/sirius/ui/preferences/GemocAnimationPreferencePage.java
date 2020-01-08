/*******************************************************************************
 * Copyright (c) 201* Inria and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Inria - initial API and implementation
 *******************************************************************************/
package org.eclipse.gemoc.executionframework.extensions.sirius.ui.preferences;

import org.eclipse.gemoc.executionframework.extensions.sirius.Activator;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IntegerFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

/**
 * This class represents a preference page that is contributed to the
 * Preferences dialog. By subclassing <samp>FieldEditorPreferencePage</samp>, we
 * can use the field support built into JFace that allows us to create a page
 * that is small and knows how to save, restore and apply itself.
 * <p>
 * This page is used to modify preferences only. They are stored in the
 * preference store that belongs to the main plug-in class. That way,
 * preferences can be accessed directly via the preference store.
 */

public class GemocAnimationPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

	public GemocAnimationPreferencePage() {
		super(GRID);
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
		setDescription("Configuration of GEMOC Sirius Animation");
	}

	/**
	 * Creates the field editors. Field editors are abstractions of the common GUI
	 * blocks needed to manipulate various types of preferences. Each field editor
	 * knows how to save and restore itself.
	 */
	public void createFieldEditors() {

		String[][] entryNamesAndValues = new String[][] {
				{ "All notifications (blocks execution thread)(slowest)", AnimationRefreshStrategy.Every.toString()},
				{ "Best effort (use single job queue with discard oldest policy)",
					AnimationRefreshStrategy.CommandQueue.toString() },
				{ "Max frequency limit (notifications are delayed to enforce refresh rate)", AnimationRefreshStrategy.Frequencylimit.toString() },
				{ "Engine pause (refresh only on debug actions)", AnimationRefreshStrategy.OnPause.toString() },
				{ "Manual (no automatic refresh)", AnimationRefreshStrategy.Manual.toString() } };
		addField(new org.eclipse.jface.preference.ComboFieldEditor(PreferenceConstants.P_REFRESH_STRATEGY,
				"Animation refresh strategy", entryNamesAndValues, getFieldEditorParent()));
		addField(new IntegerFieldEditor(PreferenceConstants.P_REFRESH_FREQUENCY_LIMIT,
				"Frequency when using frequency limit strategy (in ms):", getFieldEditorParent()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	public void init(IWorkbench workbench) {
	}

	@Override
	protected void performApply() {
		super.performApply();
	}

	@Override
	public boolean performOk() {
		boolean result = super.performOk();
		return result;
	}

}