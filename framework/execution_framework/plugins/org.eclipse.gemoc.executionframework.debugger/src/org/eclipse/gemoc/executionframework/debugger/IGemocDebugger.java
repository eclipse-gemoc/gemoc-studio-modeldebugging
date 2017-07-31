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
package org.eclipse.gemoc.executionframework.debugger;

import java.util.function.BiPredicate;

import org.eclipse.gemoc.xdsmlframework.api.core.IExecutionEngine;
import org.eclipse.gemoc.xdsmlframework.api.engine_addon.IEngineAddon;

import org.eclipse.gemoc.trace.commons.model.trace.MSEOccurrence;

public interface IGemocDebugger extends IEngineAddon {

	public abstract void addPredicateBreak(BiPredicate<IExecutionEngine, MSEOccurrence> predicate);

	public abstract void addPredicateBreakpoint(BiPredicate<IExecutionEngine, MSEOccurrence> predicate);

}
