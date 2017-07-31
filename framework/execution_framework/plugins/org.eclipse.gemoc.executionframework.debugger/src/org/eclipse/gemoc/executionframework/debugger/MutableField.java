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
package org.eclipse.gemoc.executionframework.debugger;

import java.util.function.Consumer;
import java.util.function.Supplier;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

public class MutableField {
	private final String name;
	private final EObject eObject;
	private final Supplier<Object> getValue;
	private final Consumer<Object> setValue;
	private final EStructuralFeature mutableProperty; // is it useful?

	public MutableField(String name, EObject eObject, EStructuralFeature mutableProperty, Supplier<Object> getValue,
			Consumer<Object> setValue) {
		this.name = name;
		this.eObject = eObject;
		this.getValue = getValue;
		this.setValue = setValue;
		this.mutableProperty = mutableProperty;
	}

	public MutableField(String name, EObject eObject, Supplier<Object> getValue, Consumer<Object> setValue) {
		this(name, eObject, null, getValue, setValue);
	}

	public String getName() {
		return name;
	}

	public Object getValue() {
		return this.getValue.get();
	}

	public void setValue(Object value) {
		this.setValue.accept(value);
	}

	public EObject geteObject() {
		return eObject;
	}

	public EStructuralFeature getMutableProperty() {
		return mutableProperty;
	}

	@Override
	public String toString() {
		return name;
	}
}
