package org.eclipse.gemoc.trace.commons.testutil

import java.util.Collection
import java.util.HashSet
import java.util.Set
import java.util.function.Predicate
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EcorePackage
import org.eclipse.emf.ecore.resource.Resource

class Investigation {

	static val Set<EClass> ignoredEClasses = #{EcorePackage.eINSTANCE.EFactory}

	private static def void findObjectsThatPointToNoExplore(Set<EObject> from,
		Predicate<EObject> predicateReferencedObject, Set<EObject> pointersToObjectsSatifPredicate,
		Set<EObject> pointedObjectsSatifPredicate, Set<EObject> globalPointed, HashSet<EObject> checkedObjects,
		boolean checkOutsideContainements, boolean checkEContainer, EObject boundariesRoot) {

			for (potentialPointer : from.filter [ o |
				o != null && !ignoredEClasses.contains(o.eClass) && !checkedObjects.contains(o)
			]) {
				checkedObjects.add(potentialPointer)
				val localPointed = new HashSet<EObject>
				val localPointedContained = new HashSet<EObject>

				if (potentialPointer.eContainer != null && checkEContainer)
					localPointed.add(potentialPointer.eContainer)
				for (f : potentialPointer.eClass.EAllReferences) {
					val Object stuffRefed = potentialPointer.eGet(f)
					if (f.many) {
						localPointed.addAll(stuffRefed as Collection<EObject>)
						if (f.isContainment)
							localPointedContained.addAll(stuffRefed as Collection<EObject>)
					} else {
						localPointed.add(stuffRefed as EObject)
						if (f.isContainment)
							localPointedContained.add(stuffRefed as EObject)
					}
				}

				localPointed.removeIf([o|o == null || ignoredEClasses.contains(o.eClass) || (boundariesRoot != null && !boundariesRoot.eAllContents.toSet.contains(o)) ])
				if (checkOutsideContainements)
					globalPointed.addAll(localPointed)
				else
					globalPointed.addAll(localPointedContained)

				val localPointedObjectsSatifPredicate = localPointed.filter[y|predicateReferencedObject.test(y)]
				if (!localPointedObjectsSatifPredicate.empty) {
					pointersToObjectsSatifPredicate.add(potentialPointer)
					pointedObjectsSatifPredicate.addAll(localPointedObjectsSatifPredicate)
				}
			}
		}

		private static def void findObjectsThatPointTo(Set<EObject> roots, Predicate<EObject> predicateReferencedObject,
			Set<EObject> pointersToObjectsSatifPredicate, Set<EObject> pointedObjectsSatifPredicate,
			HashSet<EObject> checkedObjects, boolean checkOutsideContainements,boolean checkEContainer, EObject boundariesRoot) {

			val toCheck = new HashSet<EObject>
			toCheck.addAll(roots)

			var int i = 0
			val int limit = 100000;

			while (!toCheck.empty && i < limit) {
				i++
				val globalPointedObjects = new HashSet<EObject>
				findObjectsThatPointToNoExplore(toCheck, predicateReferencedObject, pointersToObjectsSatifPredicate,
					pointedObjectsSatifPredicate, globalPointedObjects, checkedObjects, checkOutsideContainements, checkEContainer, boundariesRoot)
				toCheck.clear
				toCheck.addAll(globalPointedObjects)
				toCheck.removeAll(checkedObjects)
			}

			if (i == limit) {
				throw new Exception("LOOPED TOO LONG")
			}

		}

		private static def void findObjectsThatPointTo(EObject root, Predicate<EObject> predicateReferencedObject,
			Set<EObject> pointersToObjectsSatifPredicate, Set<EObject> pointedObjectsSatifPredicate,
			HashSet<EObject> checkedObjects, EObject boundariesRoot) {
				findObjectsThatPointTo(#{root}, predicateReferencedObject, pointersToObjectsSatifPredicate,
					pointedObjectsSatifPredicate, checkedObjects, true, true, boundariesRoot)
			}

			public static def Set<EObject> findAllReachableObjects(Resource resource) {
				val Predicate<EObject> predicate = [o|true]
				val checked = new HashSet
				findObjectsThatPointTo(resource.contents.toSet, predicate, new HashSet(), new HashSet(), checked, true, true, null)
				return checked

			}
			
			public static def Set<EObject> findAllReachableObjects(EObject o, boolean followContainers, EObject boundaries) {
				val Predicate<EObject> predicate = [o2|true]
				val checked = new HashSet
				findObjectsThatPointTo(#{o}, predicate, new HashSet(), new HashSet(), checked, true, followContainers, boundaries)
				return checked
			}
			
			public static def Set<EObject> findAllReachableObjectsWithin(EObject from, EObject boundaries) {
				val Predicate<EObject> predicate = [o2|true]
				val checked = new HashSet
				findObjectsThatPointTo(#{from}, predicate, new HashSet(), new HashSet(), checked, true, false, boundaries)
				return checked
			}

			private static def Set<EObject> findObjectsThatPointToObjectsWithoutResource(EObject root,
				Set<EObject> pointedObjectsSatifPreficate, HashSet<EObject> checkedObjects, EObject boundariesRoot) {

				val pointersToObjectsSatifPredicate = new HashSet<EObject>
				val Predicate<EObject> predicate = [EObject o|o != null && o.eResource == null]
				findObjectsThatPointTo(root, predicate, pointersToObjectsSatifPredicate, pointedObjectsSatifPreficate,
					checkedObjects, boundariesRoot)
				return pointersToObjectsSatifPredicate
			}

			public static def Set<EObject> findObjectsThatPointToObjectsWithoutResource(EObject root,
				Set<EObject> pointedObjects) {
				val checkedObjects = new HashSet<EObject>
				return findObjectsThatPointToObjectsWithoutResource(root, pointedObjects, checkedObjects, null)
			}

			public static def void findObjectsThatPointTo(EObject root, Set<EObject> pointers,
				Set<EObject> pointedObjects, Predicate<EObject> p) {
				val HashSet<EObject> checkedObjects = new HashSet<EObject>
				findObjectsThatPointTo(#{root}, p, pointers, pointedObjects, checkedObjects, false, false, null)
			}

			public static def Set<EObject> findObjectsThatPointToObjectsWithoutResource(Resource resource,
				Set<EObject> pointedObjects) {
				val result = new HashSet
				val checkedObjects = new HashSet<EObject>
				for (c : resource.contents) {
					result.addAll(findObjectsThatPointToObjectsWithoutResource(c, pointedObjects, checkedObjects, null))
				}
				return result
			}

			public static def EObject findRoot(EObject o, Collection<EObject> stopAt) {
				if (o.eContainer == null || stopAt.contains(o.eContainer))
					return o
				else
					return findRoot(o.eContainer, stopAt)
			}

			public static def Set<EObject> findRoots(Collection<EObject> os, Collection<EObject> stopAt) {
				val newRoots = new HashSet<EObject>
				for (p : os) {
					val root = Investigation::findRoot(p, stopAt)
					newRoots.add(root)
				}
				return newRoots
			}

			public static def Set<EObject> findRoots(Collection<EObject> os) {
				return findRoots(os, #{})
			}

		}
		