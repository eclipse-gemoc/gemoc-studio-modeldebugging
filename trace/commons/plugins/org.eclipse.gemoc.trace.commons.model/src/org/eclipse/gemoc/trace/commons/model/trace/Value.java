/*******************************************************************************
 * Copyright (c) 2017 Inria and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Inria - initial API and implementation
 *******************************************************************************/
/**
 */
package org.eclipse.gemoc.trace.commons.model.trace;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Value</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.gemoc.trace.commons.model.trace.Value#getStates <em>States</em>}</li>
 * </ul>
 *
 * @see org.eclipse.gemoc.trace.commons.model.trace.TracePackage#getValue()
 * @model abstract="true"
 * @generated
 */
public interface Value<StateSubType extends State<?, ?>> extends EObject {
	/**
	 * Returns the value of the '<em><b>States</b></em>' reference list.
	 * It is bidirectional and its opposite is '{@link org.eclipse.gemoc.trace.commons.model.trace.State#getValues <em>Values</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>States</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>States</em>' reference list.
	 * @see org.eclipse.gemoc.trace.commons.model.trace.TracePackage#getValue_States()
	 * @see org.eclipse.gemoc.trace.commons.model.trace.State#getValues
	 * @model opposite="values"
	 * @generated
	 */
	EList<StateSubType> getStates();

} // Value
