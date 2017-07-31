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
package org.eclipse.gemoc.trace.gemoc.api;

import java.util.List;

import org.eclipse.gemoc.trace.commons.model.trace.Dimension;
import org.eclipse.gemoc.trace.commons.model.trace.State;
import org.eclipse.gemoc.trace.commons.model.trace.Step;
import org.eclipse.gemoc.trace.commons.model.trace.Value;

public interface ITraceListener {
	
	void statesAdded(List<State<?,?>> states);
	
	void stepsStarted(List<Step<?>> steps);
	
	void stepsEnded(List<Step<?>> steps);
	
	void valuesAdded(List<Value<?>> values);
	
	void dimensionsAdded(List<Dimension<?>> dimensions);
}
