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
package opsemanticsview;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Operational Semantics View</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link opsemanticsview.OperationalSemanticsView#getRules <em>Rules</em>}</li>
 *   <li>{@link opsemanticsview.OperationalSemanticsView#getDynamicProperties <em>Dynamic Properties</em>}</li>
 *   <li>{@link opsemanticsview.OperationalSemanticsView#getDynamicClasses <em>Dynamic Classes</em>}</li>
 *   <li>{@link opsemanticsview.OperationalSemanticsView#getExecutionToASmapping <em>Execution To ASmapping</em>}</li>
 *   <li>{@link opsemanticsview.OperationalSemanticsView#getExecutionMetamodel <em>Execution Metamodel</em>}</li>
 *   <li>{@link opsemanticsview.OperationalSemanticsView#getAbstractSyntax <em>Abstract Syntax</em>}</li>
 * </ul>
 *
 * @see opsemanticsview.OpsemanticsviewPackage#getOperationalSemanticsView()
 * @model
 * @generated
 */
public interface OperationalSemanticsView extends EObject {
	/**
	 * Returns the value of the '<em><b>Rules</b></em>' containment reference list.
	 * The list contents are of type {@link opsemanticsview.Rule}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Rules</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Rules</em>' containment reference list.
	 * @see opsemanticsview.OpsemanticsviewPackage#getOperationalSemanticsView_Rules()
	 * @model containment="true"
	 * @generated
	 */
	EList<Rule> getRules();

	/**
	 * Returns the value of the '<em><b>Dynamic Properties</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.EStructuralFeature}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Dynamic Properties</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Dynamic Properties</em>' reference list.
	 * @see opsemanticsview.OpsemanticsviewPackage#getOperationalSemanticsView_DynamicProperties()
	 * @model
	 * @generated
	 */
	EList<EStructuralFeature> getDynamicProperties();

	/**
	 * Returns the value of the '<em><b>Dynamic Classes</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.EClass}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Dynamic Classes</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Dynamic Classes</em>' reference list.
	 * @see opsemanticsview.OpsemanticsviewPackage#getOperationalSemanticsView_DynamicClasses()
	 * @model
	 * @generated
	 */
	EList<EClass> getDynamicClasses();

	/**
	 * Returns the value of the '<em><b>Execution To ASmapping</b></em>' containment reference list.
	 * The list contents are of type {@link opsemanticsview.ExecutionToASEntry}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Execution To ASmapping</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Execution To ASmapping</em>' containment reference list.
	 * @see opsemanticsview.OpsemanticsviewPackage#getOperationalSemanticsView_ExecutionToASmapping()
	 * @model containment="true"
	 * @generated
	 */
	EList<ExecutionToASEntry> getExecutionToASmapping();

	/**
	 * Returns the value of the '<em><b>Execution Metamodel</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Execution Metamodel</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Execution Metamodel</em>' reference.
	 * @see #setExecutionMetamodel(EPackage)
	 * @see opsemanticsview.OpsemanticsviewPackage#getOperationalSemanticsView_ExecutionMetamodel()
	 * @model required="true"
	 * @generated
	 */
	EPackage getExecutionMetamodel();

	/**
	 * Sets the value of the '{@link opsemanticsview.OperationalSemanticsView#getExecutionMetamodel <em>Execution Metamodel</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Execution Metamodel</em>' reference.
	 * @see #getExecutionMetamodel()
	 * @generated
	 */
	void setExecutionMetamodel(EPackage value);

	/**
	 * Returns the value of the '<em><b>Abstract Syntax</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Abstract Syntax</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Abstract Syntax</em>' reference.
	 * @see #setAbstractSyntax(EPackage)
	 * @see opsemanticsview.OpsemanticsviewPackage#getOperationalSemanticsView_AbstractSyntax()
	 * @model
	 * @generated
	 */
	EPackage getAbstractSyntax();

	/**
	 * Sets the value of the '{@link opsemanticsview.OperationalSemanticsView#getAbstractSyntax <em>Abstract Syntax</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Abstract Syntax</em>' reference.
	 * @see #getAbstractSyntax()
	 * @generated
	 */
	void setAbstractSyntax(EPackage value);

} // OperationalSemanticsView
