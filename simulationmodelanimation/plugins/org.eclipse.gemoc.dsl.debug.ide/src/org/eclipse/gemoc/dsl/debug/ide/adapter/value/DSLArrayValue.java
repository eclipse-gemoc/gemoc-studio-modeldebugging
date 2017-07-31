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

import java.util.Arrays;

import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IIndexedValue;
import org.eclipse.debug.core.model.IVariable;

/**
 * Array {@link org.eclipse.debug.core.model.IValue IValue}.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class DSLArrayValue extends AbstractDSLValue implements IIndexedValue {

	/**
	 * The buffer size.
	 */
	private static final int BUFFER_SIZE = 512;

	/**
	 * The array of {@link IVariable}.
	 */
	private final IVariable[] variables;

	/**
	 * Constructor.
	 * 
	 * @param factory
	 *            the {@link DSLEclipseDebugIntegration} factory
	 * @param referenceTypeName
	 *            the reference type name
	 * @param array
	 *            the array of {@link Object}
	 */
	public DSLArrayValue(DSLEclipseDebugIntegration factory, String referenceTypeName, Object[] array) {
		super(factory, referenceTypeName, array);
		variables = new IVariable[array.length];
		for (int i = 0; i < array.length; ++i) {
			variables[i] = factory.getVariable(getActualTypeName(array), "[" + i + "]", array[i]);
		}
	}

	/**
	 * Gets the actual type name of the array.
	 * 
	 * @param array
	 *            the array of {@link Object}
	 * @return the actual type name of the array
	 */
	protected String getActualTypeName(Object[] array) {
		return array.getClass().getCanonicalName();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.debug.core.model.IValue#getValueString()
	 */
	public String getValueString() throws DebugException {
		StringBuilder builder = new StringBuilder(BUFFER_SIZE);

		builder.append('[');
		if (variables.length > 0) {
			for (IVariable variable : variables) {
				builder = builder.append(variable.getValue().getValueString());
				builder = builder.append(", ");
			}
			builder = builder.delete(builder.length() - ", ".length(), builder.length());
		}
		builder.append(']');

		return builder.toString();
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
		return variables;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.debug.core.model.IValue#hasVariables()
	 */
	public boolean hasVariables() throws DebugException {
		return variables.length > 0;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.debug.core.model.IIndexedValue#getVariable(int)
	 */
	public IVariable getVariable(int offset) throws DebugException {
		return variables[offset];
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.debug.core.model.IIndexedValue#getVariables(int, int)
	 */
	public IVariable[] getVariables(int offset, int length) throws DebugException {
		return Arrays.copyOfRange(variables, offset, offset + length);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.debug.core.model.IIndexedValue#getSize()
	 */
	public int getSize() throws DebugException {
		return variables.length;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.debug.core.model.IIndexedValue#getInitialOffset()
	 */
	public int getInitialOffset() {
		return 0;
	}

}
