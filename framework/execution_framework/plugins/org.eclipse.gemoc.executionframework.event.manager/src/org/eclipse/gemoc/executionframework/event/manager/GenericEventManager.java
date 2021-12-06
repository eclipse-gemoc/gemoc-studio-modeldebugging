package org.eclipse.gemoc.executionframework.event.manager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.LinkedTransferQueue;
import java.util.stream.Collectors;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gemoc.executionframework.behavioralinterface.behavioralInterface.BehavioralInterface;
import org.eclipse.gemoc.executionframework.behavioralinterface.behavioralInterface.Event;
import org.eclipse.gemoc.executionframework.event.model.event.EventOccurrence;
import org.eclipse.gemoc.executionframework.event.model.event.EventOccurrenceType;
import org.eclipse.gemoc.executionframework.event.model.event.StopEventOccurrence;
import org.eclipse.gemoc.executionframework.value.model.value.ManyReferenceValue;
import org.eclipse.gemoc.executionframework.value.model.value.SingleObjectValue;
import org.eclipse.gemoc.executionframework.value.model.value.SingleReferenceValue;
import org.eclipse.gemoc.executionframework.value.model.value.Value;
import org.eclipse.gemoc.trace.commons.model.trace.MSE;
import org.eclipse.gemoc.trace.commons.model.trace.MSEOccurrence;
import org.eclipse.gemoc.trace.commons.model.trace.Step;
import org.eclipse.gemoc.xdsmlframework.api.core.EngineStatus.RunStatus;
import org.eclipse.gemoc.xdsmlframework.api.core.IExecutionEngine;

public class GenericEventManager implements IEventManager {

	private final LinkedTransferQueue<ICallRequest> callRequestQueue = new LinkedTransferQueue<>();
	
	private final LinkedTransferQueue<EventOccurrence> eventOccurrenceQueue = new LinkedTransferQueue<>();

	private boolean canManageEvents = true;

	private boolean waitForCallRequests = false;

	private IExecutionEngine<?> engine;
	
	private boolean initialized = false;

	private final RelationshipManager relationshipManager;
	
	private final Map<String, IMetalanguageRuleExecutor> metalanguageIntegrations = new HashMap<>();
	
	public GenericEventManager(String languageName,
			List<IImplementationRelationship> implementationRelationships,
			List<ISubtypingRelationship> subtypingRelationships) {
		relationshipManager = new RelationshipManager(this);
		implementationRelationships.forEach(r -> relationshipManager.registerImplementationRelationship(r));
		subtypingRelationships.forEach(r -> relationshipManager.registerSubtypingRelationship(r));
	}
	
	public RelationshipManager getRelationshipManager() {
		return relationshipManager;
	}

	@Override
	public void engineInitialized(IExecutionEngine<?> executionEngine) {
		engine = executionEngine;
		relationshipManager.setExecutedResource(engine.getExecutionContext().getResourceModel());
		IConfigurationElement[] eventEmitters = Platform.getExtensionRegistry()
				.getConfigurationElementsFor("org.eclipse.gemoc.executionframework.event.event_emitter");
		Arrays.stream(eventEmitters).forEach(e -> {
			try {
				((IEventEmitter) e.createExecutableExtension("class")).setEventManager(this,
						engine.getExecutionContext().getResourceModel());
			} catch (CoreException e1) {
				e1.printStackTrace();
			}
		});
		initialized = true;
		EventOccurrence eventOccurrence = eventOccurrenceQueue.poll();
		while (eventOccurrence != null) {
			processEventOccurrence(eventOccurrence);
			eventOccurrence = eventOccurrenceQueue.poll();
		}
	}

	@Override
	public void processEventOccurrence(EventOccurrence eventOccurrence) {
		if (eventOccurrence instanceof StopEventOccurrence) {
			processCallRequest(new StopRequest());
		} else if (initialized) {
			convertEventToExecutedResource(eventOccurrence, engine.getExecutionContext().getResourceModel());
			relationshipManager.notifyEventOccurrence(eventOccurrence);
		} else {
			eventOccurrenceQueue.add(eventOccurrence);
		}
	}

	private Map<BehavioralInterface, List<IEventManagerListener>> listeners = new HashMap<>();

	@Override
	public void addListener(IEventManagerListener listener) {
		listener.getBehavioralInterfaces().forEach(bi -> {
			final List<IEventManagerListener> interfaceListeners = listeners.computeIfAbsent(bi,
					itf -> new ArrayList<>());
			interfaceListeners.add(listener);
		});
	}

	@Override
	public void removeListener(IEventManagerListener listener) {
		listener.getBehavioralInterfaces().forEach(bi -> {
			final List<IEventManagerListener> interfaceListeners = listeners.get(bi);
			if (interfaceListeners != null) {
				interfaceListeners.add(listener);
			}
		});
	}

	@Override
	public void processCallRequests() {
		if (canManageEvents) {
			ICallRequest callRequest = null;
			if (waitForCallRequests) {
				try {
					engine.setEngineStatus(RunStatus.WaitingForEvent);
					callRequest = callRequestQueue.take();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				engine.setEngineStatus(RunStatus.Running);
				waitForCallRequests = false;
			} else {
				callRequest = callRequestQueue.poll();
			}
			while (callRequest != null) {
				final boolean runToCompletion = callRequest.isRunToCompletion();
				if (runToCompletion) {
					canManageEvents = false;
					handleCallRequest(callRequest);
					canManageEvents = true;
				} else {
					handleCallRequest(callRequest);
				}
				callRequest = callRequestQueue.poll();
			}
		}
	}

	@Override
	public void waitForCallRequests() {
		waitForCallRequests = true;
	}

	@Override
	public void aboutToExecuteStep(IExecutionEngine<?> engine, Step<?> stepToExecute) {
		if (initialized) {
			final MSEOccurrence mseOccurrence = stepToExecute.getMseoccurrence();
			final String behavioralUnit = mseOccurrence.getMse().getCaller().eClass().getInstanceClassName()
					+ "." + mseOccurrence.getMse().getAction().getName();
			final Map<String, Object> argsMap = getArguments(mseOccurrence);
			final CallNotification callNotification = new CallNotification(behavioralUnit, argsMap);
			relationshipManager.notifyCall(callNotification);
			processCallRequests();
		}
	}

	@Override
	public void stepExecuted(IExecutionEngine<?> engine, Step<?> stepExecuted) {
		if (initialized) {
			final MSEOccurrence mseOccurrence = stepExecuted.getMseoccurrence();
			final String behavioralUnit = mseOccurrence.getMse().getCaller().eClass().getInstanceClassName()
					+ "." + mseOccurrence.getMse().getAction().getName();
			final Map<String, Object> argsMap = getArguments(mseOccurrence);
			final ReturnNotification returnNotification = new ReturnNotification(behavioralUnit, argsMap,
					mseOccurrence.getResult());
			relationshipManager.notifyCall(returnNotification);
			processCallRequests();
		}
	}

	private Map<String, Object> getArguments(MSEOccurrence mseOccurrence) {
		final Map<String, Object> argsMap = new HashMap<>();
		final MSE mse = mseOccurrence.getMse();
		argsMap.put("_self", mse.getCaller());
		final List<EParameter> parameters = mse.getAction().getEParameters();
		final List<Object> arguments = mseOccurrence.getParameters();
		for (int i = 0; i < parameters.size(); i++) {
			final String key = parameters.get(i).getName();
			final Object value = arguments.get(i);
			argsMap.put(key, value);
		}
		return argsMap;
	}

	private final Set<BehavioralInterface> allBehavioralInterfaces = new HashSet<>();

	@Override
	public Set<BehavioralInterface> getBehavioralInterfaces() {
		if (allBehavioralInterfaces.isEmpty()) {
			relationshipManager.getEvents()
					.forEach(e -> allBehavioralInterfaces.add((BehavioralInterface) e.eContainer()));
		}
		return allBehavioralInterfaces;
	}

	@Override
	public Set<Event> getEvents() {
		return relationshipManager.getEvents();
	}

	private void handleCallRequest(ICallRequest callRequest) {
		if (callRequest instanceof StopRequest) {
			engine.stop();
		} else {
			if (callRequest instanceof CompositeCallRequest) {
				((CompositeCallRequest) callRequest).getCallRequests().forEach(cr -> handleCallRequest(cr));
			} else if (callRequest instanceof SimpleCallRequest) {
				final SimpleCallRequest simpleCallRequest = (SimpleCallRequest) callRequest;
				final IMetalanguageRuleExecutor ruleExecutor = metalanguageIntegrations
						.computeIfAbsent(simpleCallRequest.getMetalanguage(), m -> findMetalanguageRuleExecutor(m));
				if (ruleExecutor != null) {
					ruleExecutor.handleCallRequest(simpleCallRequest);
				} else {
					throw new IllegalArgumentException(
							"No metalanguage rule executor was found for metalanguage " + simpleCallRequest.getMetalanguage());
				}
			}
		}
	}

	private IMetalanguageRuleExecutor findMetalanguageRuleExecutor(String metalanguage) {
		return Arrays
				.stream(Platform.getExtensionRegistry().getConfigurationElementsFor("org.eclipse.gemoc.executionframework.event.metalanguage_rule_executor"))
				.filter(c -> c.getAttribute("metaprog").equals(metalanguage))
				.findFirst().map(c -> {
					IMetalanguageRuleExecutor result = null;
					try {
						result = (IMetalanguageRuleExecutor) c.createExecutableExtension("class");
						result.setExecutionEngine(engine);
					} catch (CoreException e) {
						e.printStackTrace();
					}
					return result;
				}).orElse(null);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void convertReferences(EObject object, Resource executedResource, String executedResourceURI) {
		final List<EReference> references = object.eClass().getEAllReferences();
		references.forEach(r -> {
			if (r.isMany()) {
				final Map<EObject, EObject> toChange = new HashMap<>();
				((List) object.eGet(r)).stream().forEach(o -> {
					final EObject refered = (EObject) o;
					if (refered != null) {
						EcoreUtil.resolveAll(refered);
						final Resource referedResource = refered.eResource();
						if (referedResource != null) {
							final String referedResourceURI = referedResource.getURI().toString();
							if (referedResourceURI.equals(executedResourceURI) && referedResource != executedResource) {
								final String uriFragment = referedResource.getURIFragment(refered);
								final EObject effectiveRefered = executedResource.getEObject(uriFragment);
								toChange.put(refered, effectiveRefered);
							}
						}
					}
				});
				toChange.forEach((o, n) -> {
					final List l = (List) object.eGet(r);
					l.add(l.indexOf(o), n);
					l.remove(o);
				});
			} else {
				final EObject refered = (EObject) object.eGet(r);
				if (refered != null) {
					EcoreUtil.resolveAll(refered);
					final Resource referedResource = refered.eResource();
					if (referedResource != null) {
						final String referedResourceURI = referedResource.getURI().toString();
						if (referedResourceURI.equals(executedResourceURI) && referedResource != executedResource) {
							final String uriFragment = referedResource.getURIFragment(refered);
							final EObject effectiveRefered = executedResource.getEObject(uriFragment);
							object.eSet(r, effectiveRefered);
						}
					}
				}
			}
		});
	}

	private void convertReferencesToExecutedResource(EObject root, Resource executedResource,
			String executedResourceURI) {
		convertReferences(root, executedResource, executedResourceURI);
		root.eAllContents().forEachRemaining(c -> {
			convertReferences(c, executedResource, executedResourceURI);
		});
	}

	private void convertEventToExecutedResource(EventOccurrence eventOccurrence, Resource executedResource) {
		final String executedResourceURI = executedResource.getURI().toString();
		EcoreUtil.resolveAll(eventOccurrence);
		eventOccurrence.getArgs().forEach(a -> {
			final Value value = a.getValue();
			if (value instanceof SingleReferenceValue) {
				final SingleReferenceValue v = ((SingleReferenceValue) value);
				final EObject parameter = v.getReferenceValue();
				final Resource parameterResource = parameter.eResource();
				final String uriFragment = parameterResource.getURIFragment(parameter);
				final EObject effectiveParameter = executedResource.getEObject(uriFragment);
				v.setReferenceValue(effectiveParameter);
			} else if (value instanceof SingleObjectValue) {
				final SingleObjectValue v = ((SingleObjectValue) value);
				final EObject parameter = v.getObjectValue();
				convertReferencesToExecutedResource(parameter, executedResource, executedResourceURI);
			} else if (value instanceof ManyReferenceValue) {
				final ManyReferenceValue v = ((ManyReferenceValue) value);
				final List<EObject> parameters = v.getReferenceValues();
				final List<EObject> effectiveParameters = parameters.stream().map(p -> {
					final Resource parameterResource = p.eResource();
					final String uriFragment = parameterResource.getURIFragment(p);
					return executedResource.getEObject(uriFragment);
				}).collect(Collectors.toList());
				parameters.clear();
				parameters.addAll(effectiveParameters);
			}
		});
	}

	@Override
	public void emitEventOccurrence(EventOccurrence eventOccurrence) {
		final Event event = eventOccurrence.getEvent();
		if (eventOccurrence.getType() == EventOccurrenceType.EXPOSED) {
			final BehavioralInterface behavioralInterface = (BehavioralInterface) event.eContainer();
			listeners.getOrDefault(behavioralInterface, Collections.emptyList())
					.forEach(l -> l.eventReceived(eventOccurrence));
		}
	}

	@Override
	public void processCallRequest(ICallRequest callRequest) {
		callRequestQueue.put(callRequest);
	}
}
