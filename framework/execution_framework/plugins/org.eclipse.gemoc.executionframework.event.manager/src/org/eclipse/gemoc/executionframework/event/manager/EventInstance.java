package org.eclipse.gemoc.executionframework.event.manager;

import java.util.Map;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gemoc.executionframework.event.model.event.Event;

public class EventInstance {

	private final Event originalEvent;

	private final Map<EStructuralFeature, Object> parameters;

	public EventInstance(Event originalEvent, Map<EStructuralFeature, Object> parameters) {
		this.originalEvent = originalEvent;
		this.parameters = parameters;
	}

	public Event getOriginalEvent() {
		return originalEvent;
	}

	public Map<EStructuralFeature, Object> getParameters() {
		return parameters;
	}
}
