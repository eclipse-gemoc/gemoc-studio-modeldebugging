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
package opsemanticsview.impl;

import opsemanticsview.ExecutionToASEntry;
import opsemanticsview.OpsemanticsviewPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Execution To AS Entry</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link opsemanticsview.impl.ExecutionToASEntryImpl#getExecutionClass <em>Execution Class</em>}</li>
 *   <li>{@link opsemanticsview.impl.ExecutionToASEntryImpl#getASclass <em>ASclass</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ExecutionToASEntryImpl extends MinimalEObjectImpl.Container implements ExecutionToASEntry {
	/**
	 * The cached value of the '{@link #getExecutionClass() <em>Execution Class</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExecutionClass()
	 * @generated
	 * @ordered
	 */
	protected EClass executionClass;

	/**
	 * The cached value of the '{@link #getASclass() <em>ASclass</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getASclass()
	 * @generated
	 * @ordered
	 */
	protected EClass aSclass;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ExecutionToASEntryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return OpsemanticsviewPackage.Literals.EXECUTION_TO_AS_ENTRY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getExecutionClass() {
		if (executionClass != null && executionClass.eIsProxy()) {
			InternalEObject oldExecutionClass = (InternalEObject)executionClass;
			executionClass = (EClass)eResolveProxy(oldExecutionClass);
			if (executionClass != oldExecutionClass) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, OpsemanticsviewPackage.EXECUTION_TO_AS_ENTRY__EXECUTION_CLASS, oldExecutionClass, executionClass));
			}
		}
		return executionClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass basicGetExecutionClass() {
		return executionClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExecutionClass(EClass newExecutionClass) {
		EClass oldExecutionClass = executionClass;
		executionClass = newExecutionClass;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OpsemanticsviewPackage.EXECUTION_TO_AS_ENTRY__EXECUTION_CLASS, oldExecutionClass, executionClass));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getASclass() {
		if (aSclass != null && aSclass.eIsProxy()) {
			InternalEObject oldASclass = (InternalEObject)aSclass;
			aSclass = (EClass)eResolveProxy(oldASclass);
			if (aSclass != oldASclass) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, OpsemanticsviewPackage.EXECUTION_TO_AS_ENTRY__ASCLASS, oldASclass, aSclass));
			}
		}
		return aSclass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass basicGetASclass() {
		return aSclass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setASclass(EClass newASclass) {
		EClass oldASclass = aSclass;
		aSclass = newASclass;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OpsemanticsviewPackage.EXECUTION_TO_AS_ENTRY__ASCLASS, oldASclass, aSclass));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case OpsemanticsviewPackage.EXECUTION_TO_AS_ENTRY__EXECUTION_CLASS:
				if (resolve) return getExecutionClass();
				return basicGetExecutionClass();
			case OpsemanticsviewPackage.EXECUTION_TO_AS_ENTRY__ASCLASS:
				if (resolve) return getASclass();
				return basicGetASclass();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case OpsemanticsviewPackage.EXECUTION_TO_AS_ENTRY__EXECUTION_CLASS:
				setExecutionClass((EClass)newValue);
				return;
			case OpsemanticsviewPackage.EXECUTION_TO_AS_ENTRY__ASCLASS:
				setASclass((EClass)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case OpsemanticsviewPackage.EXECUTION_TO_AS_ENTRY__EXECUTION_CLASS:
				setExecutionClass((EClass)null);
				return;
			case OpsemanticsviewPackage.EXECUTION_TO_AS_ENTRY__ASCLASS:
				setASclass((EClass)null);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case OpsemanticsviewPackage.EXECUTION_TO_AS_ENTRY__EXECUTION_CLASS:
				return executionClass != null;
			case OpsemanticsviewPackage.EXECUTION_TO_AS_ENTRY__ASCLASS:
				return aSclass != null;
		}
		return super.eIsSet(featureID);
	}

} //ExecutionToASEntryImpl
