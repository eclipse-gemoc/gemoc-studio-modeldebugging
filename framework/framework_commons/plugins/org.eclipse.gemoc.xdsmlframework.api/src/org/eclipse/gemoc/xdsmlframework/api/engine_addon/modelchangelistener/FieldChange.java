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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

public class FieldChange {
	
	public enum ChangeType { ADD, MODIFY, REMOVE }
	
	private EStructuralFeature feature;
	private EObject eObject;
	private Object value;
	private ChangeType changeType;
	
	public FieldChange(EStructuralFeature feature, EObject eObject, Object value, ChangeType changeType) {
		this.feature = feature;
		this.eObject = eObject;
		this.value = value;
		this.changeType = changeType;
	}

	public EStructuralFeature getFeature() {
		return feature;
	}

	public EObject geteObject() {
		return eObject;
	}

	public Object getValue() {
		return value;
	}
	
	public ChangeType getChangeType() {
		return changeType;
	}
}
