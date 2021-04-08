package org.eclipse.gemoc.executionframework.event.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.gemoc.executionframework.behavioralinterface.behavioralInterface.BehavioralInterface;
import org.eclipse.gemoc.executionframework.behavioralinterface.behavioralInterface.Event;
import org.eclipse.gemoc.executionframework.event.model.event.EventOccurrence;
import org.eclipse.gemoc.executionframework.event.model.event.EventOccurrenceType;

public class RelationshipManager implements IRelationshipManager {

	private final IEventManager eventManager;
	
	private final Map<String, BehavioralInterface> uriToBehavioralInterface = new HashMap<>();

	private final Set<IImplementationRelationship> implementationRelationships = new HashSet<>();
	private final Map<String, List<IImplementationRelationship>> inputImplementationRelationships = new HashMap<>();
	private final Map<String, List<ISubtypingRelationship>> inputSubtypingRelationships = new HashMap<>();
	private final Map<String, List<ISubtypingRelationship>> outputSubtypingRelationships = new HashMap<>();

	public RelationshipManager(IEventManager eventManager) {
		this.eventManager = eventManager;
	}

	public void setExecutedResource(Resource executedResource) {
		inputSubtypingRelationships.values().stream().flatMap(l -> l.stream())
				.forEach(r -> r.setExecutedResource(executedResource));
	}
	
	@Override
	public void notifyEventOccurrence(EventOccurrence eventOccurrence) {
		final Event event = eventOccurrence.getEvent();
		final BehavioralInterface behavioralInterface = (BehavioralInterface) event.eContainer();
		final String uri = behavioralInterface.eResource().getURI().toString();
		if (eventOccurrence.getType() == EventOccurrenceType.ACCEPTED) {
			inputImplementationRelationships.computeIfAbsent(uri, bi -> new ArrayList<>())
					.forEach(r -> r.processEventOccurrence(eventOccurrence));
			inputSubtypingRelationships.computeIfAbsent(uri, bi -> new ArrayList<>())
					.forEach(r -> r.processEventOccurrence(eventOccurrence));
		} else {
			eventManager.emitEventOccurrence(eventOccurrence);
			outputSubtypingRelationships.computeIfAbsent(uri, bi -> new ArrayList<>())
					.forEach(r -> r.processEventOccurrence(eventOccurrence));
		}
	}

	@Override
	public void notifyCallRequest(ICallRequest callRequest) {
		eventManager.processCallRequest(callRequest);
	}

	@Override
	public void notifyCall(CallNotification callNotification) {
		implementationRelationships.forEach(rel -> rel.processCallNotification(callNotification));
	}

	@Override
	public void notifyCall(ReturnNotification returnNotification) {
		implementationRelationships.forEach(rel -> rel.processReturnNotification(returnNotification));
	}

	/*
	 * Relationship registration stuff
	 */
	@Override
	public void registerImplementationRelationship(IImplementationRelationship implementationRelationship) {
		implementationRelationships.add(implementationRelationship);
		final String uri = implementationRelationship.getBehavioralInterface().eResource().getURI().toString();
		final List<IImplementationRelationship> relationships = inputImplementationRelationships.computeIfAbsent(uri,
				k -> new ArrayList<>());
		relationships.add(implementationRelationship);
		implementationRelationship.configure(e -> notifyEventOccurrence(e), cr -> notifyCallRequest(cr));
		uriToBehavioralInterface.computeIfAbsent(uri, k -> implementationRelationship.getBehavioralInterface());
	}

	@Override
	public void unregisterImplementationRelationship(IImplementationRelationship implementationRelationship) {
		final String uri = implementationRelationship.getBehavioralInterface().eResource().getURI().toString();
		final List<IImplementationRelationship> relationships = inputImplementationRelationships.get(uri);
		if (relationships != null) {
			relationships.remove(implementationRelationship);
		}
		implementationRelationships.remove(implementationRelationship);
	}

	@Override
	public void registerSubtypingRelationship(ISubtypingRelationship subtypingRelationship) {
		final String subtypeUri = subtypingRelationship.getSubtype().eResource().getURI().toString();
		final String supertypeUri = subtypingRelationship.getSupertype().eResource().getURI().toString();
		final List<ISubtypingRelationship> inputRelationships = inputSubtypingRelationships.computeIfAbsent(supertypeUri,
				k -> new ArrayList<>());
		inputRelationships.add(subtypingRelationship);
		final List<ISubtypingRelationship> outputRelationships = outputSubtypingRelationships
				.computeIfAbsent(subtypeUri, k -> new ArrayList<>());
		outputRelationships.add(subtypingRelationship);
		subtypingRelationship.configure(e -> notifyEventOccurrence(e));
		uriToBehavioralInterface.computeIfAbsent(subtypeUri, k -> subtypingRelationship.getSubtype());
		uriToBehavioralInterface.computeIfAbsent(supertypeUri, k -> subtypingRelationship.getSupertype());
	}

	@Override
	public void unregisterSubtypingRelationship(ISubtypingRelationship subtypingRelationship) {
		final String subtypeUri = subtypingRelationship.getSubtype().eResource().getURI().toString();
		final String supertypeUri = subtypingRelationship.getSupertype().eResource().getURI().toString();
		final List<ISubtypingRelationship> inputRelationships = inputSubtypingRelationships.get(supertypeUri);
		if (inputRelationships != null) {
			inputRelationships.remove(subtypingRelationship);
		}
		final List<ISubtypingRelationship> outputRelationships = outputSubtypingRelationships.get(subtypeUri);
		if (outputRelationships != null) {
			outputRelationships.remove(subtypingRelationship);
		}
	}

	private Set<BehavioralInterface> getAllBehavioralInterfaces() {
		final Set<BehavioralInterface> result = new HashSet<>();
		final BiConsumer<String, List<?>> c = (k, v) -> {
			if (v != null && !v.isEmpty()) {
				result.add(uriToBehavioralInterface.get(k));
			}
		};
		inputImplementationRelationships.forEach(c);
		inputSubtypingRelationships.forEach(c);
		outputSubtypingRelationships.forEach(c);
		return result;
	}

	@Override
	public Set<Event> getEvents() {
		return getAllBehavioralInterfaces().stream().flatMap(i -> i.getEvents().stream()).collect(Collectors.toSet());
	}
}
