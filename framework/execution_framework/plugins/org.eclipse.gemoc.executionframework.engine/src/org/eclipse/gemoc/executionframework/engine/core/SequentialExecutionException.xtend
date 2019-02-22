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
 package org.eclipse.gemoc.executionframework.engine.core;

import org.eclipse.emf.transaction.RollbackException
import org.eclipse.gemoc.trace.commons.model.trace.Step

/**
 * An exception that is caused by anything thrown from the execution of
 * sequential operational semantics.
 * 
 */
class SequentialExecutionException extends RuntimeException {

	Step<?> pendingStep;

	new(Step<?> pendingMSE, Throwable cause) {
		this.pendingStep = pendingStep;
		this.initCause(cause)
	}

	private def String prettyPrintMSEOcc() {
		if (pendingStep !==	null)
			return '''Pending MSEOccurrence: «pendingStep.mseoccurrence.mse.caller.eClass.name».«pendingStep.mseoccurrence.mse.action.name» called on «pendingStep.mseoccurrence.mse.caller».'''
		else
			return "No pending MSE."
	}

	override getMessage() {
		if (this.getCause() !== null && this.getCause() instanceof RollbackException) {
			return "An error occured during the execution of the operational semantics (originally catched as a RollbackException during the transaction commit).\n" +
				prettyPrintMSEOcc;
		} else if (this.getCause() !== null && this.getCause() instanceof InterruptedException) {
			return "The engine thread was interrupted while it was waiting for being allowed to start an execution step's transaction.\n" +
				prettyPrintMSEOcc;
		} else {
			return super.getMessage();
		}
	}

}
