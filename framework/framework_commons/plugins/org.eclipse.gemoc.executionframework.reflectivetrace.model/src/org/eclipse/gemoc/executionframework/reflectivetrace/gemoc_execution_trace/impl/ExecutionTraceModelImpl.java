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
package org.eclipse.gemoc.executionframework.reflectivetrace.gemoc_execution_trace.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.gemoc.executionframework.reflectivetrace.gemoc_execution_trace.Branch;
import org.eclipse.gemoc.executionframework.reflectivetrace.gemoc_execution_trace.Choice;
import org.eclipse.gemoc.executionframework.reflectivetrace.gemoc_execution_trace.ExecutionTraceModel;
import org.eclipse.gemoc.executionframework.reflectivetrace.gemoc_execution_trace.Gemoc_execution_tracePackage;
import org.eclipse.gemoc.executionframework.reflectivetrace.gemoc_execution_trace.ModelState;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Execution Trace Model</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.gemoc.executionframework.reflectivetrace.gemoc_execution_trace.impl.ExecutionTraceModelImpl#getChoices <em>Choices</em>}</li>
 *   <li>{@link org.eclipse.gemoc.executionframework.reflectivetrace.gemoc_execution_trace.impl.ExecutionTraceModelImpl#getBranches <em>Branches</em>}</li>
 *   <li>{@link org.eclipse.gemoc.executionframework.reflectivetrace.gemoc_execution_trace.impl.ExecutionTraceModelImpl#getReachedStates <em>Reached States</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ExecutionTraceModelImpl extends MinimalEObjectImpl.Container implements ExecutionTraceModel {
	/**
	 * The cached value of the '{@link #getChoices() <em>Choices</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getChoices()
	 * @generated
	 * @ordered
	 */
	protected EList<Choice> choices;

	/**
	 * The cached value of the '{@link #getBranches() <em>Branches</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBranches()
	 * @generated
	 * @ordered
	 */
	protected EList<Branch> branches;

	/**
	 * The cached value of the '{@link #getReachedStates() <em>Reached States</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReachedStates()
	 * @generated
	 * @ordered
	 */
	protected EList<ModelState> reachedStates;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ExecutionTraceModelImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Gemoc_execution_tracePackage.Literals.EXECUTION_TRACE_MODEL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Choice> getChoices() {
		if (choices == null) {
			choices = new EObjectContainmentEList<Choice>(Choice.class, this, Gemoc_execution_tracePackage.EXECUTION_TRACE_MODEL__CHOICES);
		}
		return choices;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Branch> getBranches() {
		if (branches == null) {
			branches = new EObjectContainmentEList<Branch>(Branch.class, this, Gemoc_execution_tracePackage.EXECUTION_TRACE_MODEL__BRANCHES);
		}
		return branches;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ModelState> getReachedStates() {
		if (reachedStates == null) {
			reachedStates = new EObjectContainmentEList<ModelState>(ModelState.class, this, Gemoc_execution_tracePackage.EXECUTION_TRACE_MODEL__REACHED_STATES);
		}
		return reachedStates;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case Gemoc_execution_tracePackage.EXECUTION_TRACE_MODEL__CHOICES:
				return ((InternalEList<?>)getChoices()).basicRemove(otherEnd, msgs);
			case Gemoc_execution_tracePackage.EXECUTION_TRACE_MODEL__BRANCHES:
				return ((InternalEList<?>)getBranches()).basicRemove(otherEnd, msgs);
			case Gemoc_execution_tracePackage.EXECUTION_TRACE_MODEL__REACHED_STATES:
				return ((InternalEList<?>)getReachedStates()).basicRemove(otherEnd, msgs);
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
			case Gemoc_execution_tracePackage.EXECUTION_TRACE_MODEL__CHOICES:
				return getChoices();
			case Gemoc_execution_tracePackage.EXECUTION_TRACE_MODEL__BRANCHES:
				return getBranches();
			case Gemoc_execution_tracePackage.EXECUTION_TRACE_MODEL__REACHED_STATES:
				return getReachedStates();
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
			case Gemoc_execution_tracePackage.EXECUTION_TRACE_MODEL__CHOICES:
				getChoices().clear();
				getChoices().addAll((Collection<? extends Choice>)newValue);
				return;
			case Gemoc_execution_tracePackage.EXECUTION_TRACE_MODEL__BRANCHES:
				getBranches().clear();
				getBranches().addAll((Collection<? extends Branch>)newValue);
				return;
			case Gemoc_execution_tracePackage.EXECUTION_TRACE_MODEL__REACHED_STATES:
				getReachedStates().clear();
				getReachedStates().addAll((Collection<? extends ModelState>)newValue);
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
			case Gemoc_execution_tracePackage.EXECUTION_TRACE_MODEL__CHOICES:
				getChoices().clear();
				return;
			case Gemoc_execution_tracePackage.EXECUTION_TRACE_MODEL__BRANCHES:
				getBranches().clear();
				return;
			case Gemoc_execution_tracePackage.EXECUTION_TRACE_MODEL__REACHED_STATES:
				getReachedStates().clear();
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
			case Gemoc_execution_tracePackage.EXECUTION_TRACE_MODEL__CHOICES:
				return choices != null && !choices.isEmpty();
			case Gemoc_execution_tracePackage.EXECUTION_TRACE_MODEL__BRANCHES:
				return branches != null && !branches.isEmpty();
			case Gemoc_execution_tracePackage.EXECUTION_TRACE_MODEL__REACHED_STATES:
				return reachedStates != null && !reachedStates.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //ExecutionTraceModelImpl
