package org.eclipse.gemoc.executionframework.event.manager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.eclipse.gemoc.executionframework.event.model.event.EventFactory;
import org.eclipse.gemoc.executionframework.event.model.event.EventOccurrence;
import org.eclipse.gemoc.executionframework.event.model.event.EventOccurrenceArgument;
import org.eclipse.gemoc.executionframework.event.model.event.EventOccurrenceType;
import org.eclipse.gemoc.executionframework.behavioralinterface.behavioralInterface.BehavioralInterface;
import org.eclipse.gemoc.executionframework.behavioralinterface.behavioralInterface.Event;

public class SimpleImplementationRelationship implements IImplementationRelationship {

	private final BehavioralInterface behavioralInterface;

	private final Map<String, Event> behavioralUnitToEvent = new HashMap<>();

	private final Function<BehavioralUnitNotification, EventOccurrence> behavioralUnitNotificationToEventOccurrence;

	private final Function<EventOccurrence, ICallRequest> eventOccurrenceToCallRequest;

	private Consumer<EventOccurrence> eventOccurrenceConsumer;

	private Consumer<ICallRequest> callRequestConsumer;

	private final Set<String> runToCompletionSet;

	public SimpleImplementationRelationship(BehavioralInterface behavioralInterface,
			Set<String> runToCompletionSet, String metalanguage) {
		this(behavioralInterface, runToCompletionSet, new HashMap<>(), metalanguage);
	}

	public SimpleImplementationRelationship(BehavioralInterface behavioralInterface,
			Set<String> runToCompletionSet, Map<String, String> eventNameToExecutionRule,
			String metalanguage) {
		this.behavioralInterface = behavioralInterface;
		this.runToCompletionSet = runToCompletionSet;

		behavioralUnitNotificationToEventOccurrence = notification -> {
			final boolean isCallNotification = notification instanceof CallNotification;
			final String behavioralUnit = notification.getBehavioralUnit();
			final String eventName = eventNameToExecutionRule.containsValue(behavioralUnit) ?
				eventNameToExecutionRule.entrySet().stream()
						.filter(e -> e.getValue().equals(behavioralUnit))
						.findFirst().map(e -> e.getKey()).get() :
				notification.getBehavioralUnit() + (isCallNotification ? "_called" : "_returned");
			final Event event = behavioralUnitToEvent.computeIfAbsent(eventName, k -> behavioralInterface.getEvents()
					.stream().filter(e -> e.getName().equals(k)).findFirst().orElse(null));
			if (event != null) {
				final EventOccurrence eventOccurrence = EventFactory.eINSTANCE.createEventOccurrence();
				eventOccurrence.setEvent(event);
				eventOccurrence.setType(EventOccurrenceType.EXPOSED);
				event.getParams().stream().forEach(p -> notification.getArguments().entrySet().stream()
						.filter(a -> p.getName().equals(a.getKey())).findFirst().ifPresent(a -> {
							final EventOccurrenceArgument arg = EventFactory.eINSTANCE.createEventOccurrenceArgument();
							arg.setValue(EventManagerUtils.convertObjectToValue(a.getValue()));
							arg.setParameter(p);
							eventOccurrence.getArgs().add(arg);
						}));
				if (!isCallNotification) {
					event.getParams().stream()
							.filter(p -> p.getName().equals(notification.getBehavioralUnit() + "_result")).findFirst()
							.ifPresent(p -> {
								final EventOccurrenceArgument arg = EventFactory.eINSTANCE
										.createEventOccurrenceArgument();
								arg.setValue(EventManagerUtils
										.convertObjectToValue(((ReturnNotification) notification).getResult()));
								arg.setParameter(p);
								eventOccurrence.getArgs().add(arg);
							});
				}
				return eventOccurrence;
			}
			return null;
		};

		eventOccurrenceToCallRequest = eventOccurrence -> {
			if (eventOccurrence.getType() == EventOccurrenceType.ACCEPTED) {
				final String eventName = eventOccurrence.getEvent().getName();
				final String name = eventNameToExecutionRule.computeIfAbsent(eventName, s -> s.substring(5));

				final List<Object> arguments = eventOccurrence.getArgs().stream()
						.map(a -> EventManagerUtils.convertValueToObject(a.getValue())).collect(Collectors.toList());

				final boolean rtc = this.runToCompletionSet.contains(name);

				return new SimpleCallRequest(name, arguments, rtc, metalanguage);
			}
			return null;
		};
	}

	@Override
	public BehavioralInterface getBehavioralInterface() {
		return behavioralInterface;
	}

	@Override
	public void processEventOccurrence(EventOccurrence eventOccurrence) {
		final ICallRequest callRequest = eventOccurrenceToCallRequest.apply(eventOccurrence);
		callRequestConsumer.accept(callRequest);
	}

	@Override
	public void processCallNotification(CallNotification notification) {
		processBehavioralUnitNotification(notification);
	}

	@Override
	public void processReturnNotification(ReturnNotification notification) {
		processBehavioralUnitNotification(notification);
	}
	
	private void processBehavioralUnitNotification(BehavioralUnitNotification notification) {
		final EventOccurrence eventOccurrence = behavioralUnitNotificationToEventOccurrence.apply(notification);
		if (eventOccurrence != null) {
			eventOccurrenceConsumer.accept(eventOccurrence);
		}
	}

	@Override
	public void configure(Consumer<EventOccurrence> eventOccurrenceConsumer,
			Consumer<ICallRequest> callRequestConsumer) {
		this.eventOccurrenceConsumer = eventOccurrenceConsumer;
		this.callRequestConsumer = callRequestConsumer;
	}

}
