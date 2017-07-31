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
/**
 */
package org.eclipse.gemoc.executionframework.reflectivetrace.gemoc_execution_trace;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Branch</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.gemoc.executionframework.reflectivetrace.gemoc_execution_trace.Branch#getStartIndex <em>Start Index</em>}</li>
 *   <li>{@link org.eclipse.gemoc.executionframework.reflectivetrace.gemoc_execution_trace.Branch#getStopIndex <em>Stop Index</em>}</li>
 *   <li>{@link org.eclipse.gemoc.executionframework.reflectivetrace.gemoc_execution_trace.Branch#getChoices <em>Choices</em>}</li>
 * </ul>
 *
 * @see org.eclipse.gemoc.executionframework.reflectivetrace.gemoc_execution_trace.Gemoc_execution_tracePackage#getBranch()
 * @model
 * @generated
 */
public interface Branch extends EObject {
	/**
	 * Returns the value of the '<em><b>Start Index</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Start Index</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Start Index</em>' attribute.
	 * @see #setStartIndex(int)
	 * @see org.eclipse.gemoc.executionframework.reflectivetrace.gemoc_execution_trace.Gemoc_execution_tracePackage#getBranch_StartIndex()
	 * @model
	 * @generated
	 */
	int getStartIndex();

	/**
	 * Sets the value of the '{@link org.eclipse.gemoc.executionframework.reflectivetrace.gemoc_execution_trace.Branch#getStartIndex <em>Start Index</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Start Index</em>' attribute.
	 * @see #getStartIndex()
	 * @generated
	 */
	void setStartIndex(int value);

	/**
	 * Returns the value of the '<em><b>Stop Index</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Stop Index</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Stop Index</em>' attribute.
	 * @see #setStopIndex(int)
	 * @see org.eclipse.gemoc.executionframework.reflectivetrace.gemoc_execution_trace.Gemoc_execution_tracePackage#getBranch_StopIndex()
	 * @model
	 * @generated
	 */
	int getStopIndex();

	/**
	 * Sets the value of the '{@link org.eclipse.gemoc.executionframework.reflectivetrace.gemoc_execution_trace.Branch#getStopIndex <em>Stop Index</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Stop Index</em>' attribute.
	 * @see #getStopIndex()
	 * @generated
	 */
	void setStopIndex(int value);

	/**
	 * Returns the value of the '<em><b>Choices</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.gemoc.executionframework.reflectivetrace.gemoc_execution_trace.Choice}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.gemoc.executionframework.reflectivetrace.gemoc_execution_trace.Choice#getBranch <em>Branch</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Choices</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Choices</em>' reference list.
	 * @see org.eclipse.gemoc.executionframework.reflectivetrace.gemoc_execution_trace.Gemoc_execution_tracePackage#getBranch_Choices()
	 * @see org.eclipse.gemoc.executionframework.reflectivetrace.gemoc_execution_trace.Choice#getBranch
	 * @model opposite="branch"
	 * @generated
	 */
	EList<Choice> getChoices();

} // Branch
