/*******************************************************************************
 * Copyright (c) 2020 Inria and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Inria - initial API and implementation
 *******************************************************************************/
package org.eclipse.gemoc.trace.metp.addonbased.server;

import java.util.List;

import org.eclipse.gemoc.trace.commons.model.trace.Dimension;
import org.eclipse.gemoc.trace.commons.model.trace.State;
import org.eclipse.gemoc.trace.commons.model.trace.Step;
import org.eclipse.gemoc.trace.commons.model.trace.Value;
import org.eclipse.gemoc.trace.gemoc.api.ITraceListener;

/**
 *
 */
public class ServerAddonTraceListener implements ITraceListener {

	METPServerImpl metpServerImpl;

	public ServerAddonTraceListener(METPServerImpl metpServerImpl) {
		this.metpServerImpl = metpServerImpl;
	}

	@Override
	public void statesAdded(List<State<?, ?>> states) {
		metpServerImpl.traceClient.statesAdded();
	}

	@Override
	public void stepsStarted(List<Step<?>> steps) {
		metpServerImpl.traceClient.stepsStarted();
	}

	@Override
	public void stepsEnded(List<Step<?>> steps) {
		metpServerImpl.traceClient.stepsEnded();
	}

	@Override
	public void valuesAdded(List<Value<?>> values) {
		metpServerImpl.traceClient.valuesAdded();
	}

	@Override
	public void dimensionsAdded(List<Dimension<?>> dimensions) {
		metpServerImpl.traceClient.dimensionsAdded();
	}

}
