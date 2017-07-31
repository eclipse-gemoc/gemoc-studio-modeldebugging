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

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.gemoc.xdsmlframework.api.extensions.languages.LanguageDefinitionExtension;
import org.osgi.framework.Bundle;

import org.eclipse.gemoc.trace.commons.model.trace.MSEModel;

public interface IExecutionContext extends IDisposable
{

	void initializeResourceModel();
	
	LanguageDefinitionExtension getLanguageDefinitionExtension();
	
	IExecutionWorkspace getWorkspace();

	IExecutionPlatform getExecutionPlatform();
	
	IRunConfiguration getRunConfiguration();

	Resource getResourceModel();

	ExecutionMode getExecutionMode();

	MSEModel getMSEModel();
	
	Bundle getMelangeBundle();
	 
}
