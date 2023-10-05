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
package org.eclipse.gemoc.trace.gemoc.concurrent;

import org.eclipse.gemoc.executionframework.reflectivetrace.gemoc_execution_trace.Choice;
import org.eclipse.gemoc.executionframework.reflectivetrace.gemoc_execution_trace.ContextState;


public class GenericEventSchedulingModelExecutionTracingAddon extends AbstractEventSchedulingModelExecutionTracingAddon {

	@Override
	protected void storeSolverState(ContextState contextState) {
		// Do nothing
		
	}

	@Override
	protected void restoreSolverState(Choice choice) {
		// Do nothing
	}

}
