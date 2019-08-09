/*******************************************************************************
 * Copyright (c) 2006, 2017 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *     Code 9 Corporation - ongoing development
 *     Volker Wegert - bug 243087
 *     Inria - adaptation for K3
 *******************************************************************************/
package org.eclipse.gemoc.xdsmlframework.extensions.sirius.wizards.templates;

import org.eclipse.gemoc.xdsmlframework.extensions.sirius.Activator;
import org.eclipse.osgi.util.NLS;

public class TemplateMessages extends NLS {
	private static final String BASE_NAME = Activator.PLUGIN_ID+".wizards.templates.messages"; //$NON-NLS-1$

	static {
		// load message values from bundle file
		NLS.initializeMessages(BASE_NAME, TemplateMessages.class);
	}
		
	// MiniAspectSample constants
	public static String BasicObjectDiagramNewWizard_wtitle;
	public static String BasicObjectDiagramTemplate_diagramName;
	public static String BasicObjectDiagramTemplate_ecoreFilePath;
	public static String BasicObjectDiagramTemplate_title;
	public static String BasicObjectDiagramTemplate_desc;

}