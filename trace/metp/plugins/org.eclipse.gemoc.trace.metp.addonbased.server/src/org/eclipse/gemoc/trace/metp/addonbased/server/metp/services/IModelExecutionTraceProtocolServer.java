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

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.eclipse.gemoc.trace.commons.model.trace.Dimension;
import org.eclipse.gemoc.trace.commons.model.trace.State;
import org.eclipse.gemoc.trace.commons.model.trace.Step;
import org.eclipse.gemoc.trace.commons.model.trace.Value;
import org.eclipse.gemoc.trace.metp.addonbased.server.metp.data.EngineEventType;
import org.eclipse.gemoc.trace.metp.addonbased.server.metp.data.TraceCapabilities;
import org.eclipse.lsp4j.jsonrpc.services.JsonNotification;
import org.eclipse.lsp4j.jsonrpc.services.JsonRequest;

public interface IModelExecutionTraceProtocolServer extends IModelExecutionAddonProtocolServer {

	@JsonRequest
	public CompletableFuture<TraceCapabilities> initializeTrace() ;

}
