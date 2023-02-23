/*******************************************************************************
 * Copyright (c) 2016 Inria and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Inria - initial API and implementation
 *******************************************************************************/
package org.eclipse.gemoc.executionframework.engine.core

import java.util.Arrays
import java.util.List
import java.util.concurrent.Callable
import org.eclipse.emf.transaction.RecordingCommand
import org.eclipse.gemoc.xdsmlframework.api.core.IExecutionContext
import org.eclipse.gemoc.xdsmlframework.api.core.IRunConfiguration

abstract class AbstractCommandBasedSequentialExecutionEngine<C extends IExecutionContext<R, ?, ?>, R extends IRunConfiguration> extends AbstractSequentialExecutionEngine<C, R> {

	/**
	 * Must be called in a callback from the executed code from the operational
	 * semantics.
	 * 
	 * @param caller
	 * @param operationName
	 * @param operation
	 */
	protected def void executeOperation(Object caller, String className, String operationName,
		Callable<List<Object>> operation) {
		executeOperation(caller, #{}, className, operationName, operation);
	}

	var List<Object> lastResult = null

	protected def void executeOperation(Object caller, Object[] parameters, String className, String operationName,
		Callable<List<Object>> operation) {
		val RecordingCommand rc = new RecordingCommand(editingDomain) {
			override doExecute() {
				AbstractCommandBasedSequentialExecutionEngine.this.lastResult = operation.call()
			}
		}
		try {
			beforeExecutionStep(caller, className, operationName, rc, Arrays.asList(parameters))
			rc.execute
			afterExecutionStep(lastResult)
			lastResult = null
		} finally {
			// Important to remove notifiers.
			rc.dispose
		}
	}
}
