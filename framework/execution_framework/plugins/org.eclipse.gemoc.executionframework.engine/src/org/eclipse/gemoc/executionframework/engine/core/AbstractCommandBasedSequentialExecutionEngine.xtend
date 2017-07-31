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

import org.eclipse.emf.transaction.RecordingCommand

abstract class AbstractCommandBasedSequentialExecutionEngine extends AbstractSequentialExecutionEngine {

	/**
	 * Must be called in a callback from the executed code from the operational
	 * semantics.
	 * 
	 * @param caller
	 * @param operationName
	 * @param operation
	 */
	protected def void executeOperation(Object caller, String className, String operationName, Runnable operation) {
		val RecordingCommand rc = new RecordingCommand(editingDomain) {
			override doExecute() {
				operation.run()
			}
		}
		try {
			beforeExecutionStep(caller, className, operationName, rc)
			rc.execute
			afterExecutionStep
		} finally {
			// Important to remove notifiers.
			rc.dispose
		}
	}
}