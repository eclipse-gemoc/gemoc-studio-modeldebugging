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
import org.eclipse.emf.ecore.EOperation;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Rule</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link opsemanticsview.Rule#getCalledRules <em>Called Rules</em>}</li>
 *   <li>{@link opsemanticsview.Rule#getOperation <em>Operation</em>}</li>
 *   <li>{@link opsemanticsview.Rule#isStepRule <em>Step Rule</em>}</li>
 *   <li>{@link opsemanticsview.Rule#getOverridenBy <em>Overriden By</em>}</li>
 *   <li>{@link opsemanticsview.Rule#getOverrides <em>Overrides</em>}</li>
 *   <li>{@link opsemanticsview.Rule#getContainingClass <em>Containing Class</em>}</li>
 *   <li>{@link opsemanticsview.Rule#isAbstract <em>Abstract</em>}</li>
 *   <li>{@link opsemanticsview.Rule#isMain <em>Main</em>}</li>
 * </ul>
 *
 * @see opsemanticsview.OpsemanticsviewPackage#getRule()
 * @model
 * @generated
 */
public interface Rule extends EObject {
	/**
	 * Returns the value of the '<em><b>Called Rules</b></em>' reference list.
	 * The list contents are of type {@link opsemanticsview.Rule}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Called Rules</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Called Rules</em>' reference list.
	 * @see opsemanticsview.OpsemanticsviewPackage#getRule_CalledRules()
	 * @model ordered="false"
	 * @generated
	 */
	EList<Rule> getCalledRules();

	/**
	 * Returns the value of the '<em><b>Operation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Operation</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operation</em>' containment reference.
	 * @see #setOperation(EOperation)
	 * @see opsemanticsview.OpsemanticsviewPackage#getRule_Operation()
	 * @model containment="true" required="true"
	 * @generated
	 */
	EOperation getOperation();

	/**
	 * Sets the value of the '{@link opsemanticsview.Rule#getOperation <em>Operation</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Operation</em>' containment reference.
	 * @see #getOperation()
	 * @generated
	 */
	void setOperation(EOperation value);

	/**
	 * Returns the value of the '<em><b>Step Rule</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Step Rule</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Step Rule</em>' attribute.
	 * @see #setStepRule(boolean)
	 * @see opsemanticsview.OpsemanticsviewPackage#getRule_StepRule()
	 * @model required="true"
	 * @generated
	 */
	boolean isStepRule();

	/**
	 * Sets the value of the '{@link opsemanticsview.Rule#isStepRule <em>Step Rule</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Step Rule</em>' attribute.
	 * @see #isStepRule()
	 * @generated
	 */
	void setStepRule(boolean value);

	/**
	 * Returns the value of the '<em><b>Overriden By</b></em>' reference list.
	 * The list contents are of type {@link opsemanticsview.Rule}.
	 * It is bidirectional and its opposite is '{@link opsemanticsview.Rule#getOverrides <em>Overrides</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Overriden By</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Overriden By</em>' reference list.
	 * @see opsemanticsview.OpsemanticsviewPackage#getRule_OverridenBy()
	 * @see opsemanticsview.Rule#getOverrides
	 * @model opposite="overrides" ordered="false"
	 * @generated
	 */
	EList<Rule> getOverridenBy();

	/**
	 * Returns the value of the '<em><b>Overrides</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link opsemanticsview.Rule#getOverridenBy <em>Overriden By</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Overrides</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Overrides</em>' reference.
	 * @see #setOverrides(Rule)
	 * @see opsemanticsview.OpsemanticsviewPackage#getRule_Overrides()
	 * @see opsemanticsview.Rule#getOverridenBy
	 * @model opposite="overridenBy"
	 * @generated
	 */
	Rule getOverrides();

	/**
	 * Sets the value of the '{@link opsemanticsview.Rule#getOverrides <em>Overrides</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Overrides</em>' reference.
	 * @see #getOverrides()
	 * @generated
	 */
	void setOverrides(Rule value);

	/**
	 * Returns the value of the '<em><b>Containing Class</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Containing Class</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Containing Class</em>' reference.
	 * @see #setContainingClass(EClass)
	 * @see opsemanticsview.OpsemanticsviewPackage#getRule_ContainingClass()
	 * @model
	 * @generated
	 */
	EClass getContainingClass();

	/**
	 * Sets the value of the '{@link opsemanticsview.Rule#getContainingClass <em>Containing Class</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Containing Class</em>' reference.
	 * @see #getContainingClass()
	 * @generated
	 */
	void setContainingClass(EClass value);

	/**
	 * Returns the value of the '<em><b>Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Abstract</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Abstract</em>' attribute.
	 * @see #setAbstract(boolean)
	 * @see opsemanticsview.OpsemanticsviewPackage#getRule_Abstract()
	 * @model required="true"
	 * @generated
	 */
	boolean isAbstract();

	/**
	 * Sets the value of the '{@link opsemanticsview.Rule#isAbstract <em>Abstract</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Abstract</em>' attribute.
	 * @see #isAbstract()
	 * @generated
	 */
	void setAbstract(boolean value);

	/**
	 * Returns the value of the '<em><b>Main</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Main</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Main</em>' attribute.
	 * @see #setMain(boolean)
	 * @see opsemanticsview.OpsemanticsviewPackage#getRule_Main()
	 * @model
	 * @generated
	 */
	boolean isMain();

	/**
	 * Sets the value of the '{@link opsemanticsview.Rule#isMain <em>Main</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Main</em>' attribute.
	 * @see #isMain()
	 * @generated
	 */
	void setMain(boolean value);

} // Rule
