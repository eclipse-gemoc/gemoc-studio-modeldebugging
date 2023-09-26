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
package org.eclipse.gemoc.executionframework.engine.concurrency;

import java.util.List;

import org.eclipse.gemoc.trace.commons.model.trace.ParallelStep;
import org.eclipse.gemoc.xdsmlframework.api.core.IDisposable;


/**
 * allows to choose a LogicalStep among possible ones  
 * @author dvojtise
 *
 */
public interface ILogicalStepDecider extends IDisposable
{
	/**
	 * 
	 * @param possibleLogicalSteps
	 * @return The index of the selected logical step, -1 if no logical step selected.
	 * @throws DeciderException 
	 */
	ParallelStep<?,?> decide(AbstractConcurrentExecutionEngine<?, ?> engine, List<ParallelStep<?,?>> possibleLogicalSteps) throws DeciderException;
	
	/**
	 * Stop the decision making in progress.
	 * This should result in no chosen logical step.
	 * Has no effect if no decision making is in progress.
	 */
	void preempt();
	
}
