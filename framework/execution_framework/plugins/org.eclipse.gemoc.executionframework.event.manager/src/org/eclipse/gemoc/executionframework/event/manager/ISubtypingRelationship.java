package org.eclipse.gemoc.executionframework.event.manager;

import java.util.function.Consumer;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.gemoc.executionframework.event.model.event.EventOccurrence;
import org.eclipse.gemoc.executionframework.behavioralinterface.behavioralInterface.BehavioralInterface;

public interface ISubtypingRelationship {

	BehavioralInterface getSupertype();
	
	BehavioralInterface getSubtype();
	
	void processEventOccurrence(EventOccurrence eventOccurrence);
	
	void configure(Consumer<EventOccurrence> consumer);

	void setExecutedResource(Resource executedResource);
}
