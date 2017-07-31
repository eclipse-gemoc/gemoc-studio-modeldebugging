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
package org.eclipse.gemoc.xdsmlframework.api.core;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;

public interface IExecutionWorkspace 
{

	public IPath getProjectPath();

	public IPath getModelPath();
	
	public IPath getMoCPath();

	public IPath getMSEModelPath();

	public IPath getExecutionPath();

	public void copyFileToExecutionFolder(IPath filePath) throws CoreException;

}
