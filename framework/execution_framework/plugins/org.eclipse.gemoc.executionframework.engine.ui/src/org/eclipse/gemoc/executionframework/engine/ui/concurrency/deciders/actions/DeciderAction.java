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

import org.eclipse.core.runtime.CoreException;
import org.eclipse.gemoc.executionframework.engine.concurrency.AbstractConcurrentExecutionEngine;
import org.eclipse.gemoc.executionframework.engine.concurrency.ILogicalStepDecider;
import org.eclipse.gemoc.executionframework.engine.concurrency.deciders.DeciderSpecificationExtension;
import org.eclipse.gemoc.executionframework.engine.ui.Activator;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;

public class DeciderAction extends Action 
{
	
	protected DeciderSpecificationExtension _specification;

	

	public DeciderAction(DeciderSpecificationExtension specification)
	{
		_specification = specification;
		setText(_specification.getName());
		setToolTipText(_specification.getDescription());
		ImageDescriptor id = ImageDescriptor.createFromURL(specification.getIconURL());
		setImageDescriptor(id);
	}
	
	@Override
	public void run() {
		ILogicalStepDecider newDecider;
		try {
			newDecider = _specification.instanciateDecider();
			_engine.changeLogicalStepDecider(newDecider);
		} catch (CoreException e) {
			Activator.error(e.getMessage(), e);
			e.printStackTrace();
		}
	}

	protected AbstractConcurrentExecutionEngine _engine;
	public void setEngine(AbstractConcurrentExecutionEngine engine) {
		_engine = engine;
	}
	
	public DeciderSpecificationExtension getSpecification() {
		return _specification;
	}
	
}
