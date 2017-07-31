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
package org.eclipse.gemoc.executionframework.ui.views.engine.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuCreator;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.gemoc.executionframework.ui.Activator;
import org.eclipse.gemoc.executionframework.ui.views.engine.IEngineSelectionListener;
import org.eclipse.gemoc.xdsmlframework.api.core.ExecutionMode;
import org.eclipse.gemoc.xdsmlframework.api.core.IExecutionEngine;
import org.eclipse.gemoc.xdsmlframework.api.core.EngineStatus.RunStatus;

public abstract class AbstractEngineAction extends Action  implements IMenuCreator, IEngineSelectionListener{

	public AbstractEngineAction(){	
		super("fake", AS_PUSH_BUTTON);
		setMenuCreator(this);
		setEnabled(false);
		
		init();
		updateButton();
		
		Activator.getDefault().getEngineSelectionManager().addEngineSelectionListener(this);
	}
	public AbstractEngineAction(int style){	
		super("fake", style);
		setMenuCreator(this);
		setEnabled(false);
		
		init();
		updateButton();
		
		Activator.getDefault().getEngineSelectionManager().addEngineSelectionListener(this);
	}
	
	protected void init(){
		
	}
	protected void updateButton(){
		
	}

	@Override
	public void dispose() 
	{
		Activator.getDefault().getEngineSelectionManager().removeEngineSelectionListener(this);
	}
	
	protected void showMessage(IWorkbenchPartSite partSite, String message) {
		MessageDialog.openInformation(
			partSite.getShell(),
			"Gemoc Engines Status",
			message);
	}
	
	
	protected IExecutionEngine _currentSelectedEngine;
	public IExecutionEngine getCurrentSelectedEngine(){
		return _currentSelectedEngine;
	}
	
	@Override
	public void engineSelectionChanged(IExecutionEngine engine) 
	{
		_currentSelectedEngine = engine;
		
		if (_currentSelectedEngine == null)
		{
			setEnabled(false);			
		}
		else
		{
			setEnabled(
					!_currentSelectedEngine.getRunningStatus().equals(RunStatus.Stopped)
					&& _currentSelectedEngine.getExecutionContext().getExecutionMode().equals(ExecutionMode.Animation));
						
		}
	}
	
	@Override
	public Menu getMenu(Control parent) {
		return null;
	}

	@Override
	public Menu getMenu(Menu parent) {
		return null;
	}
	
}
