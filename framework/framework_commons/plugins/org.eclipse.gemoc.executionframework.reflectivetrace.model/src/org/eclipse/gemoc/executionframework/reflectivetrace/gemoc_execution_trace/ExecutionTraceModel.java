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
 * A representation of the model object '<em><b>Execution Trace Model</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.gemoc.executionframework.reflectivetrace.gemoc_execution_trace.ExecutionTraceModel#getChoices <em>Choices</em>}</li>
 *   <li>{@link org.eclipse.gemoc.executionframework.reflectivetrace.gemoc_execution_trace.ExecutionTraceModel#getBranches <em>Branches</em>}</li>
 *   <li>{@link org.eclipse.gemoc.executionframework.reflectivetrace.gemoc_execution_trace.ExecutionTraceModel#getReachedStates <em>Reached States</em>}</li>
 * </ul>
 *
 * @see org.eclipse.gemoc.executionframework.reflectivetrace.gemoc_execution_trace.Gemoc_execution_tracePackage#getExecutionTraceModel()
 * @model
 * @generated
 */
public interface ExecutionTraceModel extends EObject {
	/**
	 * Returns the value of the '<em><b>Choices</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.gemoc.executionframework.reflectivetrace.gemoc_execution_trace.Choice}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Choices</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Choices</em>' containment reference list.
	 * @see org.eclipse.gemoc.executionframework.reflectivetrace.gemoc_execution_trace.Gemoc_execution_tracePackage#getExecutionTraceModel_Choices()
	 * @model containment="true"
	 * @generated
	 */
	EList<Choice> getChoices();

	/**
	 * Returns the value of the '<em><b>Branches</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.gemoc.executionframework.reflectivetrace.gemoc_execution_trace.Branch}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Branches</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Branches</em>' containment reference list.
	 * @see org.eclipse.gemoc.executionframework.reflectivetrace.gemoc_execution_trace.Gemoc_execution_tracePackage#getExecutionTraceModel_Branches()
	 * @model containment="true"
	 * @generated
	 */
	EList<Branch> getBranches();

	/**
	 * Returns the value of the '<em><b>Reached States</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.gemoc.executionframework.reflectivetrace.gemoc_execution_trace.ModelState}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Reached States</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Reached States</em>' containment reference list.
	 * @see org.eclipse.gemoc.executionframework.reflectivetrace.gemoc_execution_trace.Gemoc_execution_tracePackage#getExecutionTraceModel_ReachedStates()
	 * @model containment="true"
	 * @generated
	 */
	EList<ModelState> getReachedStates();

} // ExecutionTraceModel
