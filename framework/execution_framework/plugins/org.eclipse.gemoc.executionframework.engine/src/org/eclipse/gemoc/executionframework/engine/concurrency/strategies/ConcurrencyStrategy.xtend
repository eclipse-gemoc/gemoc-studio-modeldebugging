package org.eclipse.gemoc.executionframework.engine.concurrency.strategies

import org.eclipse.gemoc.trace.commons.model.trace.SmallStep

interface ConcurrencyStrategy extends Strategy {
	def boolean canBeConcurrent(SmallStep<?> step1, SmallStep<?> step2)
}
