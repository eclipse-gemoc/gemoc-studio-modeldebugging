/*******************************************************************************
 * Copyright (c) 2014, 2017 Université de Rennes 1.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Erwan Bousse - initial API and implementation
 ******************************************************************************/
package org.eclipse.gemoc.trace.gemoc.ui.launch;

import java.io.File;
import java.io.FileFilter;



public class EcoreFileFilter implements FileFilter {
	public boolean accept(File file) {
		return file.getName().toLowerCase().endsWith(".ecore");
	}
}
