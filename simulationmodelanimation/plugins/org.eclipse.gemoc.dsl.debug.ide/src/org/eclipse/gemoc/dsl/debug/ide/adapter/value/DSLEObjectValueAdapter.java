/*******************************************************************************
 * Copyright (c) 2015, 2017 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.gemoc.dsl.debug.ide.adapter.value;

import org.eclipse.gemoc.dsl.debug.ide.DSLEclipseDebugIntegration;

import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IValue;
import org.eclipse.debug.core.model.IVariable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * An {@link EObject} {@link IValue}.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class DSLEObjectValueAdapter extends AbstractDSLValue {

	/**
	 * Constructor.
	 * 
	 * @param factory
	 *            the {@link DSLEclipseDebugIntegration} factory
	 * @param referenceTypeName
	 *            the reference type name
	 * @param value
	 *            the value {@link Object}
	 */
	public DSLEObjectValueAdapter(DSLEclipseDebugIntegration factory, String referenceTypeName, Object value) {
		super(factory, referenceTypeName, value);
	}

	/**
	 * Gets the adapted {@link EObject}.
	 * 
	 * @return the adapted {@link EObject}
	 */
	EObject getHost() {
		assert target instanceof EObject;
		return (EObject)target;
	}

	@Override
	public boolean isAdapterForType(Object type) {
		return super.isAdapterForType(type) || type == IValue.class;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.debug.core.model.IValue#getValueString()
	 */
	public String getValueString() throws DebugException {
		return getHost().toString();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.debug.core.model.IValue#isAllocated()
	 */
	public boolean isAllocated() throws DebugException {
		return true;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.debug.core.model.IValue#getVariables()
	 */
	public IVariable[] getVariables() throws DebugException {
		IVariable[] res = new IVariable[getHost().eClass().getEAllStructuralFeatures().size()];
		int i = 0;
		for (EStructuralFeature feature : getHost().eClass().getEAllStructuralFeatures()) {
			res[i] = (IVariable)factory.getVariable(feature.getEType().getName(), feature.getName(),
					getHost().eGet(feature));
			++i;
		}
		return res;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.debug.core.model.IValue#hasVariables()
	 */
	public boolean hasVariables() throws DebugException {
		return getHost().eClass().getEAllStructuralFeatures().size() > 0;
	}

}
