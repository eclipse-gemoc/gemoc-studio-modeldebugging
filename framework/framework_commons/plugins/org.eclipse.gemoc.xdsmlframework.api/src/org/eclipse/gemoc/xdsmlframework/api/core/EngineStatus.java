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

import org.eclipse.gemoc.trace.commons.model.trace.Step;

/**
 * This class provides some data about the status of an engine
 *
 */
public class  EngineStatus {
	long nbLogicalStepRun = 0;
	
	Step<?> chosenLogicalStep;


	public enum RunStatus { Initializing, Running, WaitingLogicalStepSelection, Stopped}

	
	/**
	 * Numbers of Logical step that have finished
	 * Ie. in case nested steps (eg. BigStep, SmallStep) 
	 * this means that the BigStep will be incremented only when all its internal SmallStep have finished too 
	 * This information is used by some views (Engine status view for example) in order to know
	 * Note: the initialization of the model is usually not considered as a Step
	 * @return number of steps that have run and finished
	 */
	public long getNbLogicalStepRun() {
		return nbLogicalStepRun;
	}

	/**
	 * This method may be used by engines supporting backward navigation 
	 * @param nbLogicalStepRun new value for nbLogicalStepRun
	 */
	public void setNbLogicalStepRun(long nbLogicalStepRun) {
		this.nbLogicalStepRun = nbLogicalStepRun;
	}
	
	/**
	 * An engine is responsible for incrementing this when a LogicalStep finishes
	 */
	public void incrementNbLogicalStepRun() {
		this.nbLogicalStepRun +=1;
	}
		
	
}
