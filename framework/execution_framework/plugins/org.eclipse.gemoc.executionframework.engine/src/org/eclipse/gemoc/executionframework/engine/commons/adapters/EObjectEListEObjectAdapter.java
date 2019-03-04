package org.eclipse.gemoc.executionframework.engine.commons.adapters;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gemoc.executionframework.engine.model.engine.impl.EObjectEListEObjectImpl;

public class EObjectEListEObjectAdapter extends EObjectEListEObjectImpl implements EObject {

	private final EList<EObject> target;
	
	public EObjectEListEObjectAdapter(EList<EObject> target) {
		this.target = target;
	}

	@Override
	public EList<EObject> getContents() {
		return target;
	}
}
