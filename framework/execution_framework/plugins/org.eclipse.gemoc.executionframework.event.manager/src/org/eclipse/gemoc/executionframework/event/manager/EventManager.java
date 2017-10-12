package org.eclipse.gemoc.executionframework.event.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.eclipse.gemoc.xdsmlframework.api.core.IExecutionEngine;

public class EventManager implements IEventManager {

	private final Queue<EventInstance> eventQueue = new ConcurrentLinkedQueue<>();

	private boolean canManageEvents = true;

	private boolean waitForEvents = false;

	private Thread t = null;

	private IBehavioralAPI api;

	@Override
	public void queueEvent(EventInstance input) {
		eventQueue.add((EventInstance) input);
		if (t != null) {
			synchronized (t) {
				t.notify();
			}
			t = null;
		}
	}

	@Override
	public boolean canSendEvent(EventInstance event) {
		return api == null ? false : api.canSendEvent(event);
	}

	private List<IEventManagerListener> listeners = new ArrayList<>();

	@Override
	public void addListener(IEventManagerListener listener) {
		listeners.add(listener);
	}

	@Override
	public void removeListener(IEventManagerListener listener) {
		listeners.remove(listener);
	}

	@Override
	public void processEvents() {
		if (api != null) {
			if (canManageEvents) {
				canManageEvents = false;
				if (waitForEvents && eventQueue.isEmpty()) {
					t = Thread.currentThread();
					synchronized (t) {
						try {
							t.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					waitForEvents = false;
				}
				EventInstance event = eventQueue.poll();
				while (event != null) {
					api.dispatchEvent(event);
					event = eventQueue.poll();
				}
				canManageEvents = true;
			}
		}
	}

	@Override
	public void waitForEvents() {
		waitForEvents = true;
	}

	@Override
	public void engineAboutToStart(IExecutionEngine engine) {
		final Set<IBehavioralAPI> apis = engine.getAddonsTypedBy(IBehavioralAPI.class);
		if (!apis.isEmpty()) {
			api = apis.iterator().next();
		}
	}
}
