package org.eclipse.gemoc.trace.metp.addonbased.server.metp.services;

import org.eclipse.gemoc.trace.metp.addonbased.server.metp.data.EngineEventType;

public interface IModelExecutionAddonProtocolServer {

	public void initialize(String engineId, EngineEventType eventType) ;
}
