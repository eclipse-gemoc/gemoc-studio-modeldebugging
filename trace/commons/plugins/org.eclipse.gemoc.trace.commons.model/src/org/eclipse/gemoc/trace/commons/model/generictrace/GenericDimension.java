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

import org.eclipse.gemoc.trace.commons.model.trace.Dimension;

import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Generic Dimension</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.gemoc.trace.commons.model.generictrace.GenericDimension#getDynamicProperty <em>Dynamic Property</em>}</li>
 * </ul>
 *
 * @see org.eclipse.gemoc.trace.commons.model.generictrace.GenerictracePackage#getGenericDimension()
 * @model
 * @generated
 */
public interface GenericDimension extends Dimension<GenericValue> {
	/**
	 * Returns the value of the '<em><b>Dynamic Property</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Dynamic Property</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Dynamic Property</em>' reference.
	 * @see #setDynamicProperty(EStructuralFeature)
	 * @see org.eclipse.gemoc.trace.commons.model.generictrace.GenerictracePackage#getGenericDimension_DynamicProperty()
	 * @model
	 * @generated
	 */
	EStructuralFeature getDynamicProperty();

	/**
	 * Sets the value of the '{@link org.eclipse.gemoc.trace.commons.model.generictrace.GenericDimension#getDynamicProperty <em>Dynamic Property</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Dynamic Property</em>' reference.
	 * @see #getDynamicProperty()
	 * @generated
	 */
	void setDynamicProperty(EStructuralFeature value);

} // GenericDimension
