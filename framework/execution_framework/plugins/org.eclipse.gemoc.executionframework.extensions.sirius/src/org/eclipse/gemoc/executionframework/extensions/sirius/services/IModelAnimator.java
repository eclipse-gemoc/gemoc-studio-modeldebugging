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
package org.eclipse.gemoc.executionframework.extensions.sirius.services;

import org.eclipse.gemoc.xdsmlframework.api.engine_addon.EngineAddonSortingRule;
import org.eclipse.gemoc.xdsmlframework.api.engine_addon.IEngineAddon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.gemoc.executionframework.debugger.IGemocDebugger;
import org.eclipse.gemoc.trace.commons.model.trace.Step;

/**
 * Animator interface.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 * 
 */
public interface IModelAnimator extends IEngineAddon {

	/**
	 * Activates the given {@link LogicalStep}.
	 * 
	 * @param step
	 *            the {@link LogicalStep}
	 * @param context
	 *            the context {@link Object}
	 */
	void activate(Object context, Step<?> step);

	/**
	 * Clears {@link IModelAnimator#activate(LogicalStep) activated}
	 * {@link LogicalStep}.
	 * 
	 * @param context
	 *            the context {@link Object}
	 */
	void clear(Object context);
	
	@Override
	public default List<EngineAddonSortingRule> getAddonSortingRules() {
		// create rules to ensure good behavior with GemocDebugger
		// the debugger addon will stop the execution in this event
		// this rule makes sure to be called before in order to properly refresh the view
		ArrayList<EngineAddonSortingRule> sortingRules = new ArrayList<EngineAddonSortingRule>();
		sortingRules.add(new EngineAddonSortingRule( this,
				EngineAddonSortingRule.EngineEvent.aboutToExecuteStep,
				EngineAddonSortingRule.Priority.BEFORE,
				Arrays.asList(IGemocDebugger.GROUP_TAG)));
		return sortingRules;
	}

}
