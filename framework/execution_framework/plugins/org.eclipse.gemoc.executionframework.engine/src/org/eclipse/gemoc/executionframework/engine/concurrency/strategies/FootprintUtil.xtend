package org.eclipse.gemoc.executionframework.engine.concurrency.strategies

import org.eclipse.gemoc.trace.commons.model.trace.Footprint

/**
 * Utility class for handling footprint objects.
 */
abstract class FootprintUtil {
	static def equalTo(Footprint f1, Footprint f2) {
		(f1.accesses == f2.accesses) &&
		(f1.changes == f2.changes) &&
		(f1.instantiations == f2.instantiations)
	}

	static def getAllUnchangedEObjects(Footprint footprint) {
		footprint.accesses.reject[footprint.changes.contains(it)].toSet
	}
}
