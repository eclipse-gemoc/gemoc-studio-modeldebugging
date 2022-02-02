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
package org.eclipse.gemoc.trace.commons.model.trace.impl;

import org.eclipse.gemoc.trace.commons.model.trace.MSE;
import org.eclipse.gemoc.trace.commons.model.trace.MSEModel;
import org.eclipse.gemoc.trace.commons.model.trace.TracePackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>MSE Model</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.gemoc.trace.commons.model.trace.impl.MSEModelImpl#getOwnedMSEs <em>Owned MS Es</em>}</li>
 *   <li>{@link org.eclipse.gemoc.trace.commons.model.trace.impl.MSEModelImpl#getOrphanOperations <em>Orphan Operations</em>}</li>
 * </ul>
 *
 * @generated
 */
public class MSEModelImpl extends MinimalEObjectImpl.Container implements MSEModel {
	/**
	 * The cached value of the '{@link #getOwnedMSEs() <em>Owned MS Es</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedMSEs()
	 * @generated
	 * @ordered
	 */
	protected EList<MSE> ownedMSEs;

	/**
	 * The cached value of the '{@link #getOrphanOperations() <em>Orphan Operations</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOrphanOperations()
	 * @generated
	 * @ordered
	 */
	protected EList<EOperation> orphanOperations;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MSEModelImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TracePackage.Literals.MSE_MODEL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<MSE> getOwnedMSEs() {
		if (ownedMSEs == null) {
			ownedMSEs = new EObjectContainmentEList<MSE>(MSE.class, this, TracePackage.MSE_MODEL__OWNED_MS_ES);
		}
		return ownedMSEs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<EOperation> getOrphanOperations() {
		if (orphanOperations == null) {
			orphanOperations = new EObjectContainmentEList<EOperation>(EOperation.class, this, TracePackage.MSE_MODEL__ORPHAN_OPERATIONS);
		}
		return orphanOperations;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case TracePackage.MSE_MODEL__OWNED_MS_ES:
				return ((InternalEList<?>)getOwnedMSEs()).basicRemove(otherEnd, msgs);
			case TracePackage.MSE_MODEL__ORPHAN_OPERATIONS:
				return ((InternalEList<?>)getOrphanOperations()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case TracePackage.MSE_MODEL__OWNED_MS_ES:
				return getOwnedMSEs();
			case TracePackage.MSE_MODEL__ORPHAN_OPERATIONS:
				return getOrphanOperations();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case TracePackage.MSE_MODEL__OWNED_MS_ES:
				getOwnedMSEs().clear();
				getOwnedMSEs().addAll((Collection<? extends MSE>)newValue);
				return;
			case TracePackage.MSE_MODEL__ORPHAN_OPERATIONS:
				getOrphanOperations().clear();
				getOrphanOperations().addAll((Collection<? extends EOperation>)newValue);
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
			case TracePackage.MSE_MODEL__OWNED_MS_ES:
				getOwnedMSEs().clear();
				return;
			case TracePackage.MSE_MODEL__ORPHAN_OPERATIONS:
				getOrphanOperations().clear();
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
			case TracePackage.MSE_MODEL__OWNED_MS_ES:
				return ownedMSEs != null && !ownedMSEs.isEmpty();
			case TracePackage.MSE_MODEL__ORPHAN_OPERATIONS:
				return orphanOperations != null && !orphanOperations.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //MSEModelImpl
