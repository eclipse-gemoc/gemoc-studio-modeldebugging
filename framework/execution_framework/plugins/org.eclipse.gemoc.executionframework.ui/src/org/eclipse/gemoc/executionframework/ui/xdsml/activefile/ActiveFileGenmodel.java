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
import org.eclipse.core.runtime.CoreException;
import org.eclipse.gemoc.commons.eclipse.core.resources.FileFinderVisitor;
import org.eclipse.gemoc.executionframework.ui.Activator;

public class ActiveFileGenmodel extends ActiveFileEcore {

	public ActiveFileGenmodel(IProject gemocLanguageProject) {
		super(gemocLanguageProject);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public IFile getActiveFile() {
		IProject projectWithGenmodel = this.getProject(this.gemocLanguageProject);
		FileFinderVisitor genmodelFinder = new FileFinderVisitor("genmodel");
		
		try {

			projectWithGenmodel.accept(genmodelFinder);
		} catch (CoreException e) {
			Activator.error(e.getMessage(), e);
		}

		if(genmodelFinder.getFiles().size() > 0){
			return genmodelFinder.getFiles().get(0);
		} else {
			return null;
		}
	}

}
