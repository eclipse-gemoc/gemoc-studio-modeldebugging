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

import org.eclipse.gemoc.trace.commons.model.trace.TracedObject;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Generic Traced Object</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.gemoc.trace.commons.model.generictrace.GenericTracedObject#getOriginalObject <em>Original Object</em>}</li>
 *   <li>{@link org.eclipse.gemoc.trace.commons.model.generictrace.GenericTracedObject#getAllDimensions <em>All Dimensions</em>}</li>
 * </ul>
 *
 * @see org.eclipse.gemoc.trace.commons.model.generictrace.GenerictracePackage#getGenericTracedObject()
 * @model
 * @generated
 */
public interface GenericTracedObject extends TracedObject<GenericDimension> {

	/**
	 * Returns the value of the '<em><b>Original Object</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Original Object</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Original Object</em>' reference.
	 * @see #setOriginalObject(EObject)
	 * @see org.eclipse.gemoc.trace.commons.model.generictrace.GenerictracePackage#getGenericTracedObject_OriginalObject()
	 * @model
	 * @generated
	 */
	EObject getOriginalObject();

	/**
	 * Sets the value of the '{@link org.eclipse.gemoc.trace.commons.model.generictrace.GenericTracedObject#getOriginalObject <em>Original Object</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Original Object</em>' reference.
	 * @see #getOriginalObject()
	 * @generated
	 */
	void setOriginalObject(EObject value);

	/**
	 * Returns the value of the '<em><b>All Dimensions</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.gemoc.trace.commons.model.generictrace.GenericDimension}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>All Dimensions</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>All Dimensions</em>' containment reference list.
	 * @see org.eclipse.gemoc.trace.commons.model.generictrace.GenerictracePackage#getGenericTracedObject_AllDimensions()
	 * @model containment="true"
	 * @generated
	 */
	EList<GenericDimension> getAllDimensions();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='final EList&lt;GenericDimension&gt; result = new org.eclipse.emf.ecore.util.BasicInternalEList&lt;GenericDimension&gt;(Object.class);\nresult.addAll(super.getDimensionsInternal());\nresult.addAll(getAllDimensions());\nreturn result;\n'"
	 * @generated
	 */
	EList<GenericDimension> getDimensionsInternal();
} // GenericTracedObject
