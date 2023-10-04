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
package org.eclipse.gemoc.executionframework.engine.ui.concurrency.views.step;

import org.eclipse.gemoc.executionframework.engine.concurrency.AbstractConcurrentExecutionEngine;
import org.eclipse.gemoc.trace.commons.model.helper.StepHelper;
import org.eclipse.gemoc.trace.commons.model.trace.Step;
import org.eclipse.gemoc.xdsmlframework.api.core.EngineStatus.RunStatus;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class LogicalStepsViewContentProvider implements ITreeContentProvider {

	@Override
	public void dispose() 
	{
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) 
	{
	}

	@Override
	public Object[] getElements(Object inputElement) {
		if (inputElement instanceof AbstractConcurrentExecutionEngine)
		{
			AbstractConcurrentExecutionEngine engine = (AbstractConcurrentExecutionEngine)inputElement;
			if (engine.getRunningStatus().equals(RunStatus.Stopped))
			{
				String message = "Engine is not running";
				return new Object[] {
					message
				};				
			}
			else
			{
				if (engine.getPossibleLogicalSteps() != null)
				{
					return engine.getPossibleLogicalSteps().toArray();				
				}
				else
				{
					return new Object[] {};
				}
			}
		}
		else if (inputElement instanceof Step)
		{
			Step ls = (Step)inputElement;
			return StepHelper.collectAllMSEOccurrences(ls).toArray();
		}
		return new Object[0];
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		if (parentElement instanceof AbstractConcurrentExecutionEngine)
		{
			AbstractConcurrentExecutionEngine engine = (AbstractConcurrentExecutionEngine)parentElement;
			return engine.getPossibleLogicalSteps().toArray();
		}
		else if (parentElement instanceof Step)
		{
			Step ls = (Step)parentElement;
			return StepHelper.collectAllMSEOccurrences(ls).toArray();
		}
		return new Object[0];	
	}

	@Override
	public Object getParent(Object element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasChildren(Object element) 
	{
		if (element instanceof AbstractConcurrentExecutionEngine)
		{
			AbstractConcurrentExecutionEngine engine = (AbstractConcurrentExecutionEngine)element;
			return engine.getPossibleLogicalSteps().size() > 0;
		}
		else if (element instanceof Step)
		{
			Step ls = (Step)element;
			return StepHelper.collectAllMSEOccurrences(ls).size() > 0;
		}
		return false;	
	}



}
