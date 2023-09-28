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

import java.util.List;
import java.util.Random;

import org.eclipse.gemoc.executionframework.engine.concurrency.AbstractConcurrentExecutionEngine;
import org.eclipse.gemoc.trace.commons.model.trace.ParallelStep;

/**
 * Decider that will choose randomly
 * 
 */
public class RandomDecider implements ILogicalStepDecider {

	private Random rand = new Random();

	public RandomDecider() {
		super();
	}

	@Override
	public ParallelStep<?,?> decide(AbstractConcurrentExecutionEngine<?,?> engine, List<ParallelStep<?,?>> possibleLogicalSteps) {
		int index = -1;
		if (possibleLogicalSteps.size() < 2)
		{
			index = 0;
		}
		else
		{
			index = rand.nextInt(possibleLogicalSteps.size());
		}
		return possibleLogicalSteps.get(index);
	}

	@Override
	public void dispose() {
		// nothing to do
	}

	@Override
	public void preempt() {
		// not possible
	}

}
