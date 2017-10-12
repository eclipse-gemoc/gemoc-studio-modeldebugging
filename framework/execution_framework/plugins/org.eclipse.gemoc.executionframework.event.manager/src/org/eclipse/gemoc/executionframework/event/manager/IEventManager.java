package org.eclipse.gemoc.executionframework.event.manager;

import org.eclipse.gemoc.xdsmlframework.api.engine_addon.IEngineAddon;

public interface IEventManager extends IEngineAddon {

	void queueEvent(EventInstance event);

	void processEvents();

	void waitForEvents();

	void addListener(IEventManagerListener listener);

	void removeListener(IEventManagerListener listener);

	boolean canSendEvent(EventInstance event);
}
