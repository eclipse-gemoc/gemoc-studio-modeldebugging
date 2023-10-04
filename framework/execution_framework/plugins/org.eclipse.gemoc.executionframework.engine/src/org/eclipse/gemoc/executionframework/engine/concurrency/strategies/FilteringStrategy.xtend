package org.eclipse.gemoc.executionframework.engine.concurrency.strategies

/**
 * A strategy that filters the set of logical steps based on constraints other than which steps can be run concurrently.
 * 
 * Refer to the sub-interfaces for specific types of filtering strategies.
 */
interface FilteringStrategy extends Strategy { }
