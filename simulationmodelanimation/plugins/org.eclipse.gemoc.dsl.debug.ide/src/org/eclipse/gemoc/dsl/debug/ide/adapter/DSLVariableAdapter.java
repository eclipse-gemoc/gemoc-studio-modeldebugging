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
package org.eclipse.gemoc.dsl.debug.ide.adapter;

import org.eclipse.gemoc.dsl.debug.ThreadUtils;
import org.eclipse.gemoc.dsl.debug.Variable;
import org.eclipse.gemoc.dsl.debug.ide.DSLEclipseDebugIntegration;
import org.eclipse.gemoc.dsl.debug.ide.event.model.SetVariableValueRequest;
import org.eclipse.gemoc.dsl.debug.ide.event.model.ValidateVariableValueRequest;

import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IValue;
import org.eclipse.debug.core.model.IVariable;

/**
 * The {@link Variable} DSL debug model.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class DSLVariableAdapter extends AbstractDSLDebugElementAdapter implements IVariable {

	/**
	 * Constructor.
	 * 
	 * @param factory
	 *            the {@link DSLEclipseDebugIntegration} factory
	 */
	public DSLVariableAdapter(DSLEclipseDebugIntegration factory) {
		super(factory);
	}

	@Override
	public boolean isAdapterForType(Object type) {
		return super.isAdapterForType(type) || type == IVariable.class;
	}

	/**
	 * Gets the {@link Variable}.
	 * 
	 * @return the {@link Variable}
	 */
	protected Variable getHost() {
		assert target instanceof Variable;
		return (Variable)target;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.debug.core.model.IValueModification#setValue(java.lang.String)
	 */
	public void setValue(String expression) throws DebugException {
		factory.getDebugger().handleEvent(
				new SetVariableValueRequest(ThreadUtils.getThread(getHost().getFrame()).getName(), getHost()
						.getFrame().getName(), getHost().getName(), expression));
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.debug.core.model.IValueModification#setValue(org.eclipse.debug.core.model.IValue)
	 */
	public void setValue(IValue value) throws DebugException {
		factory.getDebugger().handleEvent(
				new SetVariableValueRequest(ThreadUtils.getThread(getHost().getFrame()).getName(), getHost()
						.getFrame().getName(), getHost().getName(), value.getValueString()));
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.debug.core.model.IValueModification#supportsValueModification()
	 */
	public boolean supportsValueModification() {
		return getHost().isSupportModifications();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.debug.core.model.IValueModification#verifyValue(java.lang.String)
	 */
	public boolean verifyValue(String expression) throws DebugException {
		Object response = factory.getDebugger().handleEvent(
				new ValidateVariableValueRequest(ThreadUtils.getThread(getHost().getFrame()).getName(),
						getHost().getFrame().getName(), getHost().getName(), expression));

		return Boolean.TRUE == response;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.debug.core.model.IValueModification#verifyValue(org.eclipse.debug.core.model.IValue)
	 */
	public boolean verifyValue(IValue value) throws DebugException {
		Object response = factory.getDebugger().handleEvent(
				new ValidateVariableValueRequest(ThreadUtils.getThread(getHost().getFrame()).getName(),
						getHost().getFrame().getName(), getHost().getName(), value.getValueString()));

		return Boolean.TRUE == response;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.debug.core.model.IVariable#getValue()
	 */
	public IValue getValue() throws DebugException {
		return (IValue)factory.getValue(getHost().getDeclarationType(), getHost().getValue());
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.debug.core.model.IVariable#getName()
	 */
	public String getName() throws DebugException {
		return getHost().getName();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.debug.core.model.IVariable#getReferenceTypeName()
	 */
	public String getReferenceTypeName() throws DebugException {
		return getHost().getDeclarationType();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.debug.core.model.IVariable#hasValueChanged()
	 */
	public boolean hasValueChanged() throws DebugException {
		return getHost().isValueChanged();
	}

}
