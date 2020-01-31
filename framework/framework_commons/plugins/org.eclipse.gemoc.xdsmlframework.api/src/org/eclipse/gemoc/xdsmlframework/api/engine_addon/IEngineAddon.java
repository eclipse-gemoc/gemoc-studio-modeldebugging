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
package org.eclipse.gemoc.xdsmlframework.api.engine_addon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.eclipse.gemoc.xdsmlframework.api.core.EngineStatus.RunStatus;
import org.eclipse.gemoc.xdsmlframework.api.core.IExecutionEngine;

import org.eclipse.gemoc.trace.commons.model.trace.Step;

public interface IEngineAddon {

	/**
	 * Operation called before the engine starts
	 */
	default public void engineAboutToStart(IExecutionEngine<?> engine) {
	};

	/**
	 * Operation called after the engine have started
	 */
	default public void engineStarted(IExecutionEngine<?> executionEngine) {
	};

	default public void engineInitialized(IExecutionEngine<?> executionEngine) {
	};

	default public void engineAboutToStop(IExecutionEngine<?> engine) {
	};

	/**
	 * Operation called after the engine has been stopped
	 */
	default public void engineStopped(IExecutionEngine<?> engine) {
	};

	/**
	 * Operation before the engine has been disposed (and after the engine has
	 * been stopped)
	 */
	default public void engineAboutToDispose(IExecutionEngine<?> engine) {
	};

	/**
	 * Operation called before the Step has been chosen
	 */
	default public void aboutToSelectStep(IExecutionEngine<?> engine, Collection<Step<?>> steps) {
	};

	default public void proposedStepsChanged(IExecutionEngine<?> engine, Collection<Step<?>> steps) {
	};

	/**
	 * Operation called after the Step has been chosen It also returns the
	 * chosen Step
	 */
	default public void stepSelected(IExecutionEngine<?> engine, Step<?> selectedStep) {
	};

	default public void aboutToExecuteStep(IExecutionEngine<?> engine, Step<?> stepToExecute) {
	};

	default public void stepExecuted(IExecutionEngine<?> engine, Step<?> stepExecuted) {
	};

	default public void engineStatusChanged(IExecutionEngine<?> engine, RunStatus newStatus) {
	};

	/**
	 * This operation check the current addon compatibility with elements in
	 * 'otherAddons'
	 * 
	 * @return A list of error messages if the check failed or an empty list
	 *         otherwise.
	 */
	default public List<String> validate(List<IEngineAddon> otherAddons) {
		return new ArrayList<String>();
	};
	
	/**
	 * indicates the ID of the addon as defined in the extension point for explicit addon
	 * or manually defined for implicit addons
	 * default implementation assumes that the addon ID is the full qualified name of the java class
	 * @return the addon ID
	 */
	default public String getAddonID() {
		return this.getClass().getCanonicalName();
	}
	
	/**
	 * provide the list of Tags are assigned to this addon
	 * it contains the addon ID as minimal tag
	 * @return the list of tags of this addon 
	 */
	default public List<String> getTags() {
		return  Arrays.asList(getAddonID());
	}
	
	/**
	 * provides the list of rules defined by this addon in order to sort the engine's calls to its addons 
	 * A given rule indicate when to call the current addon relatively to addons referred by the rule
	 * @return
	 */
	default public List<EngineAddonSortingRule> getAddonSortingRules() {
		return new ArrayList<EngineAddonSortingRule>();
	}
	
}
