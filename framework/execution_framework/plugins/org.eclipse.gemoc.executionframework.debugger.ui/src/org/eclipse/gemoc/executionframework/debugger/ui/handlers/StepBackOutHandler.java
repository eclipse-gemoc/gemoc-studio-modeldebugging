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
package org.eclipse.gemoc.executionframework.debugger.ui.handlers;

import java.util.function.Supplier;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.gemoc.executionframework.debugger.Activator;
import org.eclipse.gemoc.executionframework.debugger.OmniscientGenericSequentialModelDebugger;
/**
 * Our sample handler extends AbstractHandler, an IHandler base class.
 * @see org.eclipse.core.commands.IHandler
 * @see org.eclipse.core.commands.AbstractHandler
 */
public class StepBackOutHandler extends AbstractHandler {
	
	/**
	 * The constructor.
	 */
	public StepBackOutHandler() {
		setBaseEnabled(false);
	}

	@Override
	/**
	 * the command has been executed, so extract extract the needed information
	 * from the application context.
	 */
	public Object execute(ExecutionEvent event) throws ExecutionException {
		Supplier<OmniscientGenericSequentialModelDebugger> debuggerSupplier = Activator.getDefault().getDebuggerSupplier();
		if (debuggerSupplier != null) {
			OmniscientGenericSequentialModelDebugger debugger = debuggerSupplier.get();
			debugger.stepBackOut();
		}
		
		return null;
	}
	
	@Override
	public boolean isEnabled() {
		boolean result = false;
		Supplier<OmniscientGenericSequentialModelDebugger> debuggerSupplier = Activator.getDefault().getDebuggerSupplier();
		if (debuggerSupplier != null) {
			OmniscientGenericSequentialModelDebugger debugger = debuggerSupplier.get();
			if (debugger != null) {
				result = debugger.canStepBackOut();
			}
		}
		return result;
	}
}
