/*******************************************************************************
 * Copyright (c) 2016, 2020 Inria and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Inria - initial API and implementation
 *******************************************************************************/
package org.eclipse.gemoc.executionframework.debugger;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiPredicate;

import org.eclipse.gemoc.trace.commons.model.trace.Step;
import org.eclipse.gemoc.xdsmlframework.api.core.IExecutionEngine;
import org.eclipse.gemoc.xdsmlframework.api.engine_addon.IEngineAddon;

public interface IGemocDebugger extends IEngineAddon {

	public abstract void addPredicateBreak(BiPredicate<IExecutionEngine<?>, Step<?>> predicate);

	public abstract void addPredicateBreakpoint(BiPredicate<IExecutionEngine<?>, Step<?>> predicate);

	@Override
	default List<String> getTags() {
		// add the "GemocDebugger" tag to the list
		return  Arrays.asList(GROUP_TAG, getAddonID());
	}
	
	public final String GROUP_TAG = "GemocDebugger";
}
