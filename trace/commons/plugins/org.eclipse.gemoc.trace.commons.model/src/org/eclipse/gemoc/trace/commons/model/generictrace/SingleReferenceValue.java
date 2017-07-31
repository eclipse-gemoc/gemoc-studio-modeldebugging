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
package org.eclipse.gemoc.trace.commons.model.generictrace;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Single Reference Value</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.gemoc.trace.commons.model.generictrace.SingleReferenceValue#getReferenceValue <em>Reference Value</em>}</li>
 * </ul>
 *
 * @see org.eclipse.gemoc.trace.commons.model.generictrace.GenerictracePackage#getSingleReferenceValue()
 * @model
 * @generated
 */
public interface SingleReferenceValue extends GenericReferenceValue {
	/**
	 * Returns the value of the '<em><b>Reference Value</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Reference Value</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Reference Value</em>' reference.
	 * @see #setReferenceValue(EObject)
	 * @see org.eclipse.gemoc.trace.commons.model.generictrace.GenerictracePackage#getSingleReferenceValue_ReferenceValue()
	 * @model
	 * @generated
	 */
	EObject getReferenceValue();

	/**
	 * Sets the value of the '{@link org.eclipse.gemoc.trace.commons.model.generictrace.SingleReferenceValue#getReferenceValue <em>Reference Value</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Reference Value</em>' reference.
	 * @see #getReferenceValue()
	 * @generated
	 */
	void setReferenceValue(EObject value);

} // SingleReferenceValue
