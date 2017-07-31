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
import org.eclipse.gemoc.dsl.debug.ide.adapter.AbstractDSLDebugElementAdapter;

import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IValue;
import org.eclipse.debug.core.model.IVariable;

/**
 * A <code>null</code> {@link IValue value}.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public abstract class AbstractDSLValue extends AbstractDSLDebugElementAdapter implements IValue {

	/**
	 * Empty {@link IVariable variables}.
	 */
	public static final IVariable[] EMPTY = new IVariable[0];

	/**
	 * The value.
	 */
	private final Object value;

	/**
	 * The reference type name.
	 */
	private final String referenceTypeName;

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
	public AbstractDSLValue(DSLEclipseDebugIntegration factory, String referenceTypeName, Object value) {
		super(factory);
		this.referenceTypeName = referenceTypeName;
		this.value = value;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.debug.core.model.IValue#getReferenceTypeName()
	 */
	public String getReferenceTypeName() throws DebugException {
		return referenceTypeName;
	}

	/**
	 * Gets the value {@link Object}.
	 * 
	 * @return the value {@link Object}
	 */
	public Object getValue() {
		return value;
	}

}
