package org.eclipse.gemoc.executionframework.event.manager;

import java.io.IOException;
import java.util.Collections;
import java.util.function.Consumer;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.gemoc.executionframework.event.model.event.EventOccurrence;
import org.eclipse.gemoc.executionframework.behavioralinterface.behavioralInterface.BehavioralInterface;

public interface IImplementationRelationship {
	
	BehavioralInterface getBehavioralInterface();
	
	void processEventOccurrence(EventOccurrence eventOccurrence);
	
	void processCallNotification(CallNotification callNotification);
	
	void processReturnNotification(ReturnNotification returnNotification);
	
	void configure(Consumer<EventOccurrence> eventOccurrenceConsumer,
			Consumer<ICallRequest> callRequestConsumer);
	
	public static BehavioralInterface loadBehavioralInterface(String itfUri) {
		final URI uri = URI.createURI(itfUri);
		final ResourceSet resourceSet = new ResourceSetImpl();
		final Resource res = resourceSet.getResource(uri, true);
		try {
			res.load(Collections.emptyMap());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return (BehavioralInterface) res.getContents().get(0);
	}
}
