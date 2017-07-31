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
package org.eclipse.gemoc.executionframework.ui.views.engine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.gemoc.executionframework.engine.core.GemocRunningEnginesRegistry;
import org.eclipse.gemoc.xdsmlframework.api.core.IExecutionEngine;

class ViewContentProvider implements ITreeContentProvider 
{

	public void inputChanged(Viewer v, Object oldInput, Object newInput) {
	}

	public void dispose() {
	}
	
	public Object[] getElements(Object parent) {
		if (parent instanceof GemocRunningEnginesRegistry)
		{
			GemocRunningEnginesRegistry registry = org.eclipse.gemoc.executionframework.engine.Activator.getDefault().gemocRunningEngineRegistry;
			List<IExecutionEngine> engines = new ArrayList<IExecutionEngine>(registry.getRunningEngines().values());
			Collections.sort(engines, getComparator()); 
			return engines.toArray();
		}
		return null;
	}
	public Object getParent(Object child) 
	{
		return null;
	}
	
	public Object [] getChildren(Object parent) 
	{
		return new Object[0];
	}
	
	public boolean hasChildren(Object parent) 
	{
		return false;
	}
	
	private Comparator<IExecutionEngine> getComparator()
	{
		Comparator<IExecutionEngine> comparator = new Comparator<IExecutionEngine>() {
		    public int compare(IExecutionEngine c1, IExecutionEngine c2) 
		    {
		    	int c1Value = c1.getRunningStatus().ordinal();
		    	int c2Value = c2.getRunningStatus().ordinal();
		        if (c1Value < c2Value) 
		        {
		        	return -1;
		        } 
		        else if (c1Value > c2Value) 
		        {
		        	return 1;
		        }  
		        return 0;
		    }
		};
		return comparator;
	}
	
}
