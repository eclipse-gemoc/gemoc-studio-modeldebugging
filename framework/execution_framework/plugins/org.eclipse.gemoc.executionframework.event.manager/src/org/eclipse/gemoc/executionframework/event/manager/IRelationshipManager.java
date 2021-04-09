package org.eclipse.gemoc.executionframework.event.manager;

import java.util.Set;

import org.eclipse.gemoc.executionframework.event.model.event.EventOccurrence;
import org.eclipse.gemoc.executionframework.behavioralinterface.behavioralInterface.Event;

public interface IRelationshipManager {

	void registerImplementationRelationship(IImplementationRelationship implementationRelationship);
	
	void unregisterImplementationRelationship(IImplementationRelationship implementationRelationship);

	void registerSubtypingRelationship(ISubtypingRelationship subtypingRelationship);
	
	void unregisterSubtypingRelationship(ISubtypingRelationship subtypingRelationship);
	
	void notifyEventOccurrence(EventOccurrence eventOccurrence);
	
	void notifyCallRequest(ICallRequest callRequest);
	
	void notifyCall(CallNotification callNotification);
	
	void notifyCall(ReturnNotification returnNotification);

	Set<Event> getEvents();
}
