package org.eclipse.gemoc.executionframework.engine.commons.adapters;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gemoc.executionframework.engine.model.engine.impl.ResourceEObjectImpl;

public class ResourceEObjectAdapter extends ResourceEObjectImpl implements EObject {

	private final String name;
	private final EList<EObject> target;
	
	public ResourceEObjectAdapter(String name, EList<EObject> target) {
		this.name = name;
		this.target = target;
	}

	@Override
	public EList<EObject> getContents() {
		return target;
	}

	@Override
	public String getName() {
		return name;
	}
	
	
}
