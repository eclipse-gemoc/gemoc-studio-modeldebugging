/*******************************************************************************
 * Copyright (c) 2016, 2017 Inria and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Inria - initial API and implementation
 *******************************************************************************/
package org.eclipse.gemoc.xdsmlframework.api.engine_addon.modelchangelistener;

import java.util.ArrayList
import java.util.Collection
import java.util.HashMap
import java.util.HashSet
import java.util.List
import java.util.Map
import java.util.Set
import org.eclipse.emf.common.notify.Notification
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EReference
import org.eclipse.emf.ecore.EStructuralFeature
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.ecore.util.EContentAdapter

 class BatchModelChangeListener {

	val EContentAdapter adapter;
	val Map<Object, List<Notification>> changes = new HashMap
	val Set<Object> registeredObservers = new HashSet
	val Set<Resource> observedResources = new HashSet<Resource>

	 new(Set<Resource> resources) {
		/*
		 * We create an adapter that stores and sort all the notifications for each object and field.
		 * This avoids us to sort everything afterwards.
		 */
		this.adapter = new EContentAdapter() {
			override void notifyChanged(Notification notification) {
				super.notifyChanged(notification);
				for (obs : registeredObservers) {
					changes.get(obs).add(notification)
				}
			}
		};

		observedResources.addAll(resources)

		observedResources.forEach [ r |
			if (r !== null) {
				r.eAdapters().add(adapter);
			}
		]
	}

	/**
	 * When an observer asks for the changes, we process all the notifications gathered for it since the last time. 
	 */
	def List<ModelChange> getChanges(Object addon) {
		val List<ModelChange> result = new ArrayList()
		val List<Notification> allNotifs = changes.get(addon);
		if (registeredObservers.contains(addon)) {
			changes.put(
				addon,
				new ArrayList<Notification>()
			);
		}

		// First we sort everything per object and field
		val Map<EObject, Map<EStructuralFeature, List<Notification>>> sortedNotifications = new HashMap
		val Map<Resource, List<Notification>> resourcesNotifications = new HashMap
		for (Notification notification : allNotifs) {
			val int eventType = notification.getEventType();
			if (eventType < Notification.EVENT_TYPE_COUNT && !notification.isTouch()) {

				if (notification.getNotifier() instanceof EObject &&
					notification.getFeature() instanceof EStructuralFeature) {
					val EStructuralFeature feature = notification.getFeature() as EStructuralFeature;
					val EObject changedObject = notification.getNotifier() as EObject;
					if (!sortedNotifications.containsKey(changedObject)) {
						sortedNotifications.put(changedObject, new HashMap)
					}
					val Map<EStructuralFeature, List<Notification>> objectsNotifications = sortedNotifications.get(
						changedObject);
					if (!objectsNotifications.containsKey(feature)) {
						objectsNotifications.put(feature, new ArrayList)
					}
					val List<Notification> fieldNotifications = objectsNotifications.get(feature);
					fieldNotifications.addUnique(notification);
				} else if (notification.getNotifier() instanceof Resource) {
					val Resource resource = notification.notifier as Resource
					if (!resourcesNotifications.containsKey(resource))
						resourcesNotifications.put(resource, new ArrayList)
					val resourceNotifications = resourcesNotifications.get(resource)
					resourceNotifications.addUnique(notification)
				}
			}
		}

		val newObjects = new HashSet<EObject>
		val removedObjects = new HashSet<EObject>
		val eventuallyRemoved = new HashSet<EObject>

		// First we find new objects added or removed at the root of the resource
		for (resource : resourcesNotifications.keySet) {
			val resourceNotifications = resourcesNotifications.get(resource)
			for (Notification notif : resourceNotifications) {
				BatchModelChangeListener.manageCollectionContainmentNotification(eventuallyRemoved, removedObjects,
					newObjects, notif)
			}
		}

		// Next we read all that and try to interpret everything as coarse grained model changes
		for (object : sortedNotifications.keySet) {
			val featureMap = sortedNotifications.get(object)
			for (feature : featureMap.keySet) {
				val notifs = featureMap.get(feature)

				// Case multiplicity 0..1: we compare the original value and the new one at the end of the step
				if (!feature.isMany) {

					val previousValue = notifs.head.oldValue
					val newValue = notifs.last.newValue

					// Case objects: we compare references
					if (feature instanceof EReference) {
						if (previousValue != newValue) {

							// Register model change
							result.add(new NonCollectionFieldModelChange(object, feature))

							// Register potentially new or removed object
							if ((feature as EReference).containment) {
								if (previousValue !== null && previousValue instanceof EObject)
									addToRemovedObjects(eventuallyRemoved, removedObjects, newObjects,
										previousValue as EObject)
								if (newValue !== null && newValue instanceof EObject)
									addToNewObjects(eventuallyRemoved, removedObjects, newObjects, newValue as EObject)
							}
						}
					} // Case data types: we compare values
					else if (if (previousValue === null) {
						newValue !== null
					} else {
						!previousValue.equals(newValue)
					}) {

						// Register model change
						result.add(new NonCollectionFieldModelChange(object, feature))
					}

				} // Case multiplicity 0..*: we consider that there was a potential change, but maybe following
				// all the adds ands remove, the collection went back to its state before the step
				else {

					// Very hard to decide if a collection has changed or not based on the notifications,
					// (e.g. if we remove and add the same object, the collection in fact doesn't change)
					// and we don't have a direct access to the previous content of the collection to compare 
					// similarly to what we do in a trace manager. 
					// So for now we simply state a "potential change", and the trace manager will have to compute
					// itself if there was a real change.
					result.add(new PotentialCollectionFieldModelChange(object, feature, notifs))

					// Yet we must still find new/removed objects
					for (notif : notifs) {

						if (feature instanceof EReference && (feature as EReference).containment) {
							BatchModelChangeListener.
								manageCollectionContainmentNotification(eventuallyRemoved, removedObjects, newObjects,
									notif)
						}
					}
				}
			}
		}

		// Finally we register the new and removed objects from the model
		for (newObject : newObjects) {
			result.add(0, new NewObjectModelChange(newObject))
		}
		for (removedObject : removedObjects) {
			result.add(0, new RemovedObjectModelChange(removedObject))
		}

		// And we remove changes registered in new/deleted objects
		result.removeIf([ c |
			c instanceof FieldModelChange &&
				(newObjects.contains(c.changedObject) || removedObjects.contains(c.changedObject) || eventuallyRemoved.contains(c.changedObject))
		])

		return result;
	}

	def boolean registerObserver(Object observer) {
		val boolean res = registeredObservers.add(observer);
		if (res) {
			changes.put(observer, new ArrayList<Notification>());
		}
		return res;
	}

	def void removeObserver(Object observer) {
		changes.remove(observer)
		registeredObservers.remove(observer)
	}

	private static def void addToNewObjects(Collection<EObject> eventuallyRemoved, Collection<EObject> removedObjects,
		Collection<EObject> newObjects, EObject object) {
		eventuallyRemoved.remove(object)
		if (object !== null) {
			val hasMoved = removedObjects.remove(object)
			if (!hasMoved) {
				newObjects.add(object)

			}
		}
	}

	private static def void addToRemovedObjects(Collection<EObject> eventuallyRemoved,
		Collection<EObject> removedObjects, Collection<EObject> newObjects, EObject object) {
		eventuallyRemoved.add(object)
		if (object !== null) {
			val hasMoved = newObjects.remove(object)
			if (!hasMoved)
				removedObjects.add(object)
		}
	}

	// TODO manage objects already contained in new objects ... ?
	private static def void manageCollectionContainmentNotification(Collection<EObject> eventuallyRemoved,
		Collection<EObject> removedObjects, Collection<EObject> newObjects, Notification notif) {
		switch (notif.eventType) {
			case Notification.ADD:
				addToNewObjects(eventuallyRemoved, removedObjects, newObjects, notif.newValue as EObject)
			case Notification.ADD_MANY:
				for (add : notif.newValue as List<EObject>)
					addToNewObjects(eventuallyRemoved, removedObjects, newObjects, add)
			case Notification.REMOVE:
				addToRemovedObjects(eventuallyRemoved, removedObjects, newObjects, notif.oldValue as EObject)
			case Notification.REMOVE_MANY:
				for (remove : notif.oldValue as List<EObject>)
					addToNewObjects(eventuallyRemoved, removedObjects, newObjects, remove)
		}
	}

	def void cleanUp() {
		for (r : observedResources.filter[r|r !== null]) {
			r.eAdapters().remove(adapter);
		}
	}

	private static def <T> addUnique(List<T> list, T o) {
		if (!list.contains(o)) {
			list.add(o);
		}
	}
}
