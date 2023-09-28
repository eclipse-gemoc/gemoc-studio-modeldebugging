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

import org.eclipse.gemoc.executionframework.engine.concurrency.AbstractConcurrentExecutionEngine;
import org.eclipse.gemoc.executionframework.engine.concurrency.deciders.DeciderException;
import org.eclipse.gemoc.executionframework.engine.concurrency.deciders.ILogicalStepDecider;
import org.eclipse.gemoc.executionframework.engine.ui.concurrency.SharedIcons;
import org.eclipse.gemoc.executionframework.engine.ui.concurrency.deciders.AbstractUserDecider;
import org.eclipse.gemoc.executionframework.ui.views.engine.actions.AbstractEngineAction;
import org.eclipse.gemoc.trace.commons.model.trace.ParallelStep;
import org.eclipse.gemoc.xdsmlframework.api.core.EngineStatus.RunStatus;
import org.eclipse.gemoc.xdsmlframework.api.core.IExecutionEngine;

public class PauseResumeEngineDeciderAction extends AbstractEngineAction
{
	
	
	private DeciderAction _currentAction;
	
	private DeciderAction _stepByStepDeciderAction;
	
	public PauseResumeEngineDeciderAction()
	{
		super();
	}
	
	protected void init(){
		_stepByStepDeciderAction = DeciderManager.getStepByStepDeciderAction();
		_currentAction = _stepByStepDeciderAction;		
		updateButton();
	}
	
	protected void updateButton() {
		if (_currentAction != null)
		{
			// base text from Decider
			setText(_currentAction.getText());
			setToolTipText(_currentAction.getToolTipText());
			//setImageDescriptor(_currentAction.getImageDescriptor());
			if (getCurrentSelectedEngine() == null || getCurrentSelectedEngine().getRunningStatus().equals(RunStatus.Stopped))
			{
				setEnabled(false);
				setImageDescriptor(SharedIcons.SUSPEND_ENGINE_DECIDER_ICON);
			}
			else
			{
				setEnabled(	true);
				
				// find the decider opposed to the one currently used by the engine
				if (getCurrentSelectedEngine() instanceof AbstractConcurrentExecutionEngine) {
					AbstractConcurrentExecutionEngine engine_cast = (AbstractConcurrentExecutionEngine) getCurrentSelectedEngine();
				_currentAction = DeciderManager.getSwitchDeciderAction(engine_cast.getLogicalStepDecider());
				if(_currentAction.equals(_stepByStepDeciderAction)){
					setToolTipText("Suspend associated engine using "+ _currentAction.getText());
					setImageDescriptor(SharedIcons.SUSPEND_ENGINE_DECIDER_ICON);
				} else {
					setToolTipText("Resume associated engine using "+ _currentAction.getText());
					setImageDescriptor(SharedIcons.RESUME_ENGINE_DECIDER_ICON);
				}
				}
			}
			
			
		}
	}

	@Override
	public void run()
	{
		if (getCurrentSelectedEngine() != null
			&& _currentAction != null && getCurrentSelectedEngine() instanceof AbstractConcurrentExecutionEngine)
		{
			AbstractConcurrentExecutionEngine engine_cast = (AbstractConcurrentExecutionEngine) getCurrentSelectedEngine();
			ILogicalStepDecider savedDecider = engine_cast.getLogicalStepDecider();
			// apply the decider change
			_currentAction.run();			
			// now switch UI to the alternative Action by  refreshing UI
			updateButton();
			// relaunch the engine Ie. unlock possibly locked StepByStepDecider, for non "StepByStepDecider, simply let them run one more time
			if(savedDecider instanceof AbstractUserDecider){
				// get the equivalent decision from the new Decider
				try {

					ParallelStep<?,?> selectedlogicalStep = engine_cast.getLogicalStepDecider().decide(engine_cast, engine_cast.getPossibleLogicalSteps());
					((AbstractUserDecider) savedDecider).decideFromTimeLine(selectedlogicalStep);
				} catch (DeciderException e) {
				}
			}
		}
	}

	@Override
	public void engineSelectionChanged(IExecutionEngine engine) {
		super.engineSelectionChanged(engine);
		if(engine != null){
			if (engine instanceof AbstractConcurrentExecutionEngine)
				_currentAction.setEngine((AbstractConcurrentExecutionEngine)engine);
			updateButton();
		}
	}

	
	

	

}
