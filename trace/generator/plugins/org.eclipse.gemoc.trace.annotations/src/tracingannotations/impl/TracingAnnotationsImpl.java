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
package tracingannotations.impl;

import java.util.Collection;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

import tracingannotations.TracingAnnotations;
import tracingannotations.TracingannotationsPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Tracing Annotations</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link tracingannotations.impl.TracingAnnotationsImpl#getPropertiesToTrace <em>Properties To Trace</em>}</li>
 *   <li>{@link tracingannotations.impl.TracingAnnotationsImpl#getClassestoTrace <em>Classesto Trace</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TracingAnnotationsImpl extends MinimalEObjectImpl.Container implements TracingAnnotations {
	/**
	 * The cached value of the '{@link #getPropertiesToTrace() <em>Properties To Trace</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPropertiesToTrace()
	 * @generated
	 * @ordered
	 */
	protected EList<EStructuralFeature> propertiesToTrace;

	/**
	 * The cached value of the '{@link #getClassestoTrace() <em>Classesto Trace</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClassestoTrace()
	 * @generated
	 * @ordered
	 */
	protected EList<EClass> classestoTrace;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TracingAnnotationsImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TracingannotationsPackage.Literals.TRACING_ANNOTATIONS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<EStructuralFeature> getPropertiesToTrace() {
		if (propertiesToTrace == null) {
			propertiesToTrace = new EObjectResolvingEList<EStructuralFeature>(EStructuralFeature.class, this, TracingannotationsPackage.TRACING_ANNOTATIONS__PROPERTIES_TO_TRACE);
		}
		return propertiesToTrace;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<EClass> getClassestoTrace() {
		if (classestoTrace == null) {
			classestoTrace = new EObjectResolvingEList<EClass>(EClass.class, this, TracingannotationsPackage.TRACING_ANNOTATIONS__CLASSESTO_TRACE);
		}
		return classestoTrace;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case TracingannotationsPackage.TRACING_ANNOTATIONS__PROPERTIES_TO_TRACE:
				return getPropertiesToTrace();
			case TracingannotationsPackage.TRACING_ANNOTATIONS__CLASSESTO_TRACE:
				return getClassestoTrace();
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
			case TracingannotationsPackage.TRACING_ANNOTATIONS__PROPERTIES_TO_TRACE:
				getPropertiesToTrace().clear();
				getPropertiesToTrace().addAll((Collection<? extends EStructuralFeature>)newValue);
				return;
			case TracingannotationsPackage.TRACING_ANNOTATIONS__CLASSESTO_TRACE:
				getClassestoTrace().clear();
				getClassestoTrace().addAll((Collection<? extends EClass>)newValue);
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
			case TracingannotationsPackage.TRACING_ANNOTATIONS__PROPERTIES_TO_TRACE:
				getPropertiesToTrace().clear();
				return;
			case TracingannotationsPackage.TRACING_ANNOTATIONS__CLASSESTO_TRACE:
				getClassestoTrace().clear();
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
			case TracingannotationsPackage.TRACING_ANNOTATIONS__PROPERTIES_TO_TRACE:
				return propertiesToTrace != null && !propertiesToTrace.isEmpty();
			case TracingannotationsPackage.TRACING_ANNOTATIONS__CLASSESTO_TRACE:
				return classestoTrace != null && !classestoTrace.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //TracingAnnotationsImpl
