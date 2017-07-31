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
package org.eclipse.gemoc.executionframework.ui.xdsml.activefile;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;

public abstract class ActiveFile {

	IProject gemocLanguageProject;
	
	public ActiveFile(IProject gemocLanguageProject){
		this.gemocLanguageProject = gemocLanguageProject;
	}
	
	public IFile getActiveFile() {
		return null;
	}
	
	protected IProject getProject(IProject gemocLanguageIProject) {
		return null;
	}
}
