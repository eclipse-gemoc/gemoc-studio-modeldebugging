/*******************************************************************************
 *  Copyright (c) 2000, 2017 IBM Corporation and others.
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 * 
 *  Contributors:
 *  IBM - Initial API and implementation
 *  BEA - Daniel R Somerfield - Bug 88939
 *  Remy Chi Jian Suen - Bug 221973
 *******************************************************************************/
package org.eclipse.gemoc.execution.sequential.javaengine.ui.launcher;

import org.eclipse.osgi.util.NLS;

public class LauncherMessages extends NLS {
	private static final String PACKAGE_NAME = "org.eclipse.gemoc.execution.sequential.javaengine.ui.launcher.LauncherMessages";//$NON-NLS-1$

	

	public static String SequentialMainTab_Model_not_specified;
	public static String SequentialMainTab_invalid_model_file;
	public static String SequentialMainTab_model_doesnt_exist;
	

	public static String SequentialMainTab_Language_not_specified;
	public static String SequentialMainTab_incompatible_model_extension_for_language;
	public static String SequentialMainTab_missing_language;
	public static String SequentialMainTab_Invalid_Language_missing_xdsml;
	public static String SequentialMainTab_Invalid_Language_with_error;
	
	public static String SequentialMainTab_Language_main_method_not_selected;
	public static String SequentialMainTab_Language_main_methods_dont_exist;

	public static String SequentialMainTab_Language_root_element_not_selected;
	public static String SequentialMainTab_Language_incompatible_root_and_main;

	static {
		// load message values from bundle file
		NLS.initializeMessages(PACKAGE_NAME, LauncherMessages.class);
	}


}
