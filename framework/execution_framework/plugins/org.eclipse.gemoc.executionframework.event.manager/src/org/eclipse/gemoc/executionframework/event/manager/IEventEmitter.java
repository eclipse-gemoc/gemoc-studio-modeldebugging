package org.eclipse.gemoc.executionframework.event.manager;

import org.eclipse.emf.ecore.resource.Resource;

public interface IEventEmitter {

	void setEventManager(IEventManager eventManager, Resource executedResource);
	
	default void dispose() {};
}
