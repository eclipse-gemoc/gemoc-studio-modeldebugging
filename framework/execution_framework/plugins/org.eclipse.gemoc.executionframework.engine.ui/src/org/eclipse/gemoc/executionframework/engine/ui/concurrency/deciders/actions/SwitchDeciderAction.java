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

import org.eclipse.gemoc.executionframework.engine.concurrency.AbstractConcurrentExecutionEngine;
import org.eclipse.gemoc.executionframework.ui.views.engine.actions.AbstractEngineAction;
import org.eclipse.gemoc.xdsmlframework.api.core.IExecutionEngine;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;

public class SwitchDeciderAction extends AbstractEngineAction
{
	
	private ArrayList<DeciderAction> _subActions;

	private DeciderAction _mainAction;
	
	public SwitchDeciderAction()
	{
		super( AS_DROP_DOWN_MENU);
	}
	
	@Override
	protected void init(){
		_mainAction = DeciderManager.getStepByStepDeciderAction();
		_subActions = new ArrayList<>();
		for(DeciderAction action : DeciderManager.getAllDeciderActions()){
			_subActions.add(action);
		}
	}
	@Override
	protected void updateButton() {
		if (_mainAction != null)
		{
			setText(_mainAction.getText());
			setToolTipText(_mainAction.getToolTipText());
			setImageDescriptor(_mainAction.getImageDescriptor());			
		}
	}

	@Override
	public void run()
	{
		if (getCurrentSelectedEngine() != null
			&& _mainAction != null)
		{
			_mainAction.run();
		}
	}

	private Menu _menu;
	
	@Override
	public Menu getMenu(Control parent) {
		if (_menu == null)
		{
			_menu = new Menu(parent);
			for (Action action : _subActions) 
			{
				ActionContributionItem item = new ActionContributionItem(action);
				item.fill(_menu, -1);
			}
		} 
		return _menu;
	}

	

	@Override
	public void dispose() 
	{
		super.dispose();
		if (_menu != null)
			_menu.dispose();
	}

	
	@Override
	public void engineSelectionChanged(IExecutionEngine engine) 
	{
		super.engineSelectionChanged(engine);
		if (engine instanceof AbstractConcurrentExecutionEngine) {
			for (DeciderAction action : DeciderManager.getAllDeciderActions())
			{
				action.setEngine((AbstractConcurrentExecutionEngine)getCurrentSelectedEngine());
			}		
		}
	}

}
