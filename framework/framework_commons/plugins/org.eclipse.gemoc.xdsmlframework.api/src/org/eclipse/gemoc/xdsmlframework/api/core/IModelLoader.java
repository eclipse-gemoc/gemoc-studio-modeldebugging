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

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.resource.Resource;

/**
 * Class in charge of making sure the model is loaded correctly.
 * 
 * @author flatombe
 * 
 */
public interface IModelLoader {

	/** load Model when running in normal mode */
	Resource loadModel(IExecutionContext context);
	/** load model when running in animation mode */
	Resource loadModelForAnimation(IExecutionContext context);
	/** if not null, the progress monitor used to report load progress */
	void setProgressMonitor(IProgressMonitor progressMonitor);

}
