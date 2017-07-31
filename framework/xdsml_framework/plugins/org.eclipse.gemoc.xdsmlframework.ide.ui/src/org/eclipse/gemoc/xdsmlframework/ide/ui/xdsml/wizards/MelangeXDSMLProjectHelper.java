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
package org.eclipse.gemoc.xdsmlframework.ide.ui.xdsml.wizards;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;

import fr.inria.diverse.melange.metamodel.melange.Import;
import fr.inria.diverse.melange.metamodel.melange.Language;
import fr.inria.diverse.melange.metamodel.melange.Operator;

public class MelangeXDSMLProjectHelper {

	/**
	 * Computer the base name for a project base on xdsml project
	 * ie. if it ends with .xdsml this suffix is removed
	 * @param xdsmlProject
	 * @return
	 */
	public static String baseProjectName(IProject xdsmlProject){
		int index = xdsmlProject.getName().indexOf(".xdsml");
		if(index != -1){
			return xdsmlProject.getName().substring(0, index);		
		}
		return xdsmlProject.getName();
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
	
}
