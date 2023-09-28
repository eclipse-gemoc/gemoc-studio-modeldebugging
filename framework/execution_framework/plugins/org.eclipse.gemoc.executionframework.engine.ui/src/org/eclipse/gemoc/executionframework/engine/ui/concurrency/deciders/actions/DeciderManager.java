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
package org.eclipse.gemoc.executionframework.engine.ui.concurrency.deciders.actions;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gemoc.executionframework.engine.concurrency.deciders.DeciderSpecificationExtension;
import org.eclipse.gemoc.executionframework.engine.concurrency.deciders.DeciderSpecificationExtensionPoint;
import org.eclipse.gemoc.executionframework.engine.concurrency.deciders.ILogicalStepDecider;

public class DeciderManager {

	
	public static DeciderAction getStepByStepDeciderAction(){
		for (DeciderAction action : getAllDeciderActions())
		{
			
			if (action.getText().contains("step"))
				return action;
		}
		return null;
	}
	
	public static DeciderAction getDeciderAction(ILogicalStepDecider decider){
		for (DeciderAction action : getAllDeciderActions())
		{
			
			if (action.getSpecification().getDeciderClassName().equals(decider.getClass().getName()))
				return action;
		}
		return null;
	}
	
	/** return a Step by step decider if the current decider is a running one, and a solver decider if this is a step by step decider
	 * 
	 * @param decider
	 * @return
	 */
	public static DeciderAction getSwitchDeciderAction(ILogicalStepDecider currentEngineDecider){
		String searchedDecider = "Step";
		if(currentEngineDecider.getClass().getName().contains("Step")){
			searchedDecider = "Solver";
		}
		for (DeciderAction action : getAllDeciderActions())
		{
			
			if (action.getSpecification().getDeciderClassName().contains(searchedDecider))
				return action;
		}
		return getStepByStepDeciderAction();
	}
	
	protected static List<DeciderAction> _deciderActions;
	
	public static List<DeciderAction> getAllDeciderActions(){
		if(_deciderActions == null){
			_deciderActions = new ArrayList<DeciderAction>();
			for (DeciderSpecificationExtension spec : DeciderSpecificationExtensionPoint.getSpecifications())
			{
				_deciderActions.add( new DeciderAction(spec));
			}
		}
		return _deciderActions;
	}
}
