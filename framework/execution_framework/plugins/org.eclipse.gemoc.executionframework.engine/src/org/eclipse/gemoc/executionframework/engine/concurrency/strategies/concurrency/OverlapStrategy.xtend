package org.eclipse.gemoc.executionframework.engine.concurrency.strategies.concurrency

import org.eclipse.gemoc.executionframework.engine.concurrency.strategies.ConcurrencyStrategy
import org.eclipse.gemoc.trace.commons.model.trace.SmallStep
import org.eclipse.xtend.lib.annotations.Accessors

import static extension org.eclipse.gemoc.executionframework.engine.concurrency.strategies.FootprintUtil.*

class OverlapStrategy implements ConcurrencyStrategy {

	@Accessors
	var boolean requireFullOverlap = true

	@Accessors
	var boolean requireOnlyStaticOverlap = true

	override canBeConcurrent(SmallStep<?> step1, SmallStep<?> step2) {
		if (step1.footprint === null || step2.footprint === null){
			return true // if footprint is not present then by default it can be concurrent
		}
		val step1FootPrint = if(requireOnlyStaticOverlap) step1.footprint.allUnchangedEObjects else step1.footprint.
				accesses
		val step2FootPrint = if(requireOnlyStaticOverlap) step2.footprint.allUnchangedEObjects else step2.footprint.
				accesses

		if (requireFullOverlap) {
			(step1FootPrint.size === step2FootPrint.size) && step1FootPrint.forall[step2FootPrint.contains(it)] &&
				step2FootPrint.forall[step1FootPrint.contains(it)] && (requireOnlyStaticOverlap ||
					(step1.footprint.instantiations.forall[step2.footprint.instantiations.contains(it)] &&
						step2.footprint.instantiations.forall[step1.footprint.instantiations.contains(it)]))
		} else {
			step1FootPrint.exists[step2FootPrint.contains(it)] ||
				(!requireOnlyStaticOverlap && step1.footprint.instantiations.forall [
					step1.footprint.instantiations.contains(it)
				])
		}
	}
}
