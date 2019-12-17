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
package org.eclipse.gemoc.xdsmlframework.ui.utils;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.gemoc.commons.eclipse.core.resources.FileFinderVisitor;

import fr.inria.diverse.melange.metamodel.melange.Import;
import fr.inria.diverse.melange.metamodel.melange.Language;
import fr.inria.diverse.melange.metamodel.melange.Operator;

public class XDSMLProjectHelper {

	public static final String DSLFILE_EXTENSION = "dsl";
	
	/**
	 * Computes the base name from an xdsml/dsml project
	 * ie. if it ends with .xdsml or .dsml this suffix is removed
	 * @param xdsmlProject
	 * @return
	 */
	public static String baseProjectName(IProject xdsmlProject){
		return baseProjectName(xdsmlProject.getName());
	}
	public static String baseProjectName(String xdsmlProjectName){
		int index = xdsmlProjectName.indexOf(".xdsml");
		if(index != -1){
			return xdsmlProjectName.substring(0, index);		
		}
		index = xdsmlProjectName.indexOf(".dsml");
		if(index != -1){
			return xdsmlProjectName.substring(0, index);		
		}
		return xdsmlProjectName;
	}
	
	public static String getFirstEcorePath(Language language){
		Import firstImport = null;
		for(Operator op: language.getOperators()){
			if(op instanceof Import){
				firstImport = (Import)op;
				break;
			}
		}
		if(firstImport != null){
			return firstImport.getEcoreUri();
		}
		return null;
	}
	
	public static  IFile getFirstEcore(Language lang){
		final String ecoreURI = getFirstEcorePath(lang);
		if(ecoreURI != null){
			final URI uri = org.eclipse.emf.common.util.URI.createURI(ecoreURI);
			final String filePath = uri.toPlatformString(true);
			final IPath path = new Path(filePath);
			return ResourcesPlugin.getWorkspace().getRoot().getFile(path);
		}
		return null;
	}
	
	/**
	 * find and return the first dsl file in the given project
	 * @param updatedGemocLanguageProject
	 * @return
	 * @throws CoreException
	 */
	public static IFile getFirstDslFileFromProject(IProject updatedGemocLanguageProject) throws CoreException {
		FileFinderVisitor dslProjectVisitor = new FileFinderVisitor(DSLFILE_EXTENSION);
		updatedGemocLanguageProject.accept(dslProjectVisitor);
		for (IFile projectDslIFile : dslProjectVisitor.getFiles()) {
			// consider all dsl files in the project
			if (!(projectDslIFile.getFullPath().toString().contains("/bin/")
					| projectDslIFile.getFullPath().toString().contains("/target/"))) {
				return projectDslIFile;
			}
		}
		return null;
	}
	
}
