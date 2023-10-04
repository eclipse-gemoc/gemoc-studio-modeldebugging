/*******************************************************************************
 * Copyright (c) 2017 INRIA and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     INRIA - initial API and implementation
 *     I3S Laboratory - API update and bug fix
 *******************************************************************************/
package org.eclipse.gemoc.executionframework.engine.concurrency.deciders;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.gemoc.xdsmlframework.api.core.ExecutionMode;

public class LogicalStepDeciderFactory {


	public static final String DECIDER_SOLVER = "Solver decider";
	public static ILogicalStepDecider createDecider(String deciderKind, ExecutionMode executionMode) throws CoreException 
	{
		ILogicalStepDecider decider = null;
		if (executionMode.equals(ExecutionMode.Run))
		{
			DeciderSpecificationExtension extension = DeciderSpecificationExtensionPoint.findDefinition(DECIDER_SOLVER);
			decider = extension.instanciateDecider();
		}
		else 
		{
			DeciderSpecificationExtension extension = DeciderSpecificationExtensionPoint.findDefinition(deciderKind);
			decider = extension.instanciateDecider();
		}		
		return decider;
	}
	
}
