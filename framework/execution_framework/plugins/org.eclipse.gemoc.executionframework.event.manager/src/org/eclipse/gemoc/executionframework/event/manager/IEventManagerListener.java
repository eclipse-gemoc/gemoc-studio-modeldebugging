package org.eclipse.gemoc.executionframework.event.manager;

import org.eclipse.gemoc.executionframework.event.model.event.Event;

public interface IEventManagerListener {
	
	public void eventReceived(Event event);
}
