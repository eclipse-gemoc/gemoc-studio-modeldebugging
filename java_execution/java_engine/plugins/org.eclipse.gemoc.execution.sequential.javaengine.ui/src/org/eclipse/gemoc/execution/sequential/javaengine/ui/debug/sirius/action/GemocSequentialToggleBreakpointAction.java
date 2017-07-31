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
package org.eclipse.gemoc.execution.sequential.javaengine.ui.debug.sirius.action;

import org.eclipse.gemoc.execution.sequential.javaengine.ui.Activator;
import org.eclipse.gemoc.executionframework.debugger.ui.breakpoints.GemocToggleBreakpointAction;

/**
 * commons class for all Gemoc based models
 * @author dvojtise
 *
 */
public class GemocSequentialToggleBreakpointAction extends GemocToggleBreakpointAction {

	@Override
	protected String getModelIdentifier() {
		return Activator.DEBUG_MODEL_ID;
	}

	
	
}
