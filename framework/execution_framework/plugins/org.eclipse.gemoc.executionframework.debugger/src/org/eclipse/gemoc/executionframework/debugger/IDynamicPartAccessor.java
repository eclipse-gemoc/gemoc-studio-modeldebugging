package org.eclipse.gemoc.executionframework.debugger;

import org.eclipse.emf.ecore.EObject;

public interface IDynamicPartAccessor extends IMutableFieldExtractor {

	/**
	 * Return true if the class of {@link obj} is defined in the semantics
	 */
	public boolean isDynamic(EObject obj);
	
}
