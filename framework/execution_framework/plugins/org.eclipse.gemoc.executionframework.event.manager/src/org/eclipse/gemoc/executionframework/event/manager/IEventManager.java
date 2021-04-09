package org.eclipse.gemoc.executionframework.event.manager;

import java.util.Set;

import org.eclipse.gemoc.executionframework.event.model.event.EventOccurrence;
import org.eclipse.gemoc.xdsmlframework.api.engine_addon.IEngineAddon;
import org.eclipse.gemoc.executionframework.behavioralinterface.behavioralInterface.BehavioralInterface;
import org.eclipse.gemoc.executionframework.behavioralinterface.behavioralInterface.Event;

public interface IEventManager extends IEngineAddon {
	
	IRelationshipManager getRelationshipManager();

	void processEventOccurrence(EventOccurrence event);

	void processCallRequests();

	void waitForCallRequests();

	void addListener(IEventManagerListener listener);

	void removeListener(IEventManagerListener listener);

	Set<BehavioralInterface> getBehavioralInterfaces();
	
	Set<Event> getEvents();
	
	void emitEventOccurrence(EventOccurrence eventOccurrence);
	
	void processCallRequest(ICallRequest callRequest);
}
