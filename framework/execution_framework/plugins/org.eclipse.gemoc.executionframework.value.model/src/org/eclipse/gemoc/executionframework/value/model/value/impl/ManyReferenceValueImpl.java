/**
 */
package org.eclipse.gemoc.executionframework.value.model.value.impl;

import java.util.Collection;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

import org.eclipse.gemoc.executionframework.value.model.value.ManyReferenceValue;
import org.eclipse.gemoc.executionframework.value.model.value.ValuePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Many Reference Value</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.gemoc.executionframework.value.model.value.impl.ManyReferenceValueImpl#getReferenceValues <em>Reference Values</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ManyReferenceValueImpl extends ReferenceValueImpl implements ManyReferenceValue {
	/**
	 * The cached value of the '{@link #getReferenceValues() <em>Reference Values</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReferenceValues()
	 * @generated
	 * @ordered
	 */
	protected EList<EObject> referenceValues;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ManyReferenceValueImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ValuePackage.Literals.MANY_REFERENCE_VALUE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<EObject> getReferenceValues() {
		if (referenceValues == null) {
			referenceValues = new EObjectResolvingEList<EObject>(EObject.class, this, ValuePackage.MANY_REFERENCE_VALUE__REFERENCE_VALUES);
		}
		return referenceValues;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ValuePackage.MANY_REFERENCE_VALUE__REFERENCE_VALUES:
				return getReferenceValues();
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
			case ValuePackage.MANY_REFERENCE_VALUE__REFERENCE_VALUES:
				getReferenceValues().clear();
				getReferenceValues().addAll((Collection<? extends EObject>)newValue);
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
			case ValuePackage.MANY_REFERENCE_VALUE__REFERENCE_VALUES:
				getReferenceValues().clear();
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
			case ValuePackage.MANY_REFERENCE_VALUE__REFERENCE_VALUES:
				return referenceValues != null && !referenceValues.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //ManyReferenceValueImpl
