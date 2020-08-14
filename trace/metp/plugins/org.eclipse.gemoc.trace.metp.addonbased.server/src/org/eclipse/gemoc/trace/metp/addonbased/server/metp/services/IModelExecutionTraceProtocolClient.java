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
package org.eclipse.gemoc.trace.metp.addonbased.server.metp.services;

import org.eclipse.lsp4j.jsonrpc.services.JsonNotification;

public interface IModelExecutionTraceProtocolClient extends IModelExecutionAddonProtocolClient {

	// void statesAdded(List<State<?,?>> states);
	@JsonNotification
	void statesAdded();
	
	// void stepsStarted(List<Step<?>> steps);
	@JsonNotification
	void stepsStarted();
	
	// void stepsEnded(List<Step<?>> steps);
	@JsonNotification
	void stepsEnded();
	
	// void valuesAdded(List<Value<?>> values);
	@JsonNotification
	void valuesAdded();
	
	//void dimensionsAdded(List<Dimension<?>> dimensions);
	@JsonNotification
	void dimensionsAdded();
}
