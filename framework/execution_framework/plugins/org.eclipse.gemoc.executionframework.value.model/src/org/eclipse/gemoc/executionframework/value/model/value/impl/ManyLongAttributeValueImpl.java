/**
 */
package org.eclipse.gemoc.executionframework.value.model.value.impl;

import java.util.Collection;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;

import org.eclipse.gemoc.executionframework.value.model.value.ManyLongAttributeValue;
import org.eclipse.gemoc.executionframework.value.model.value.ValuePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Many Long Attribute Value</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.gemoc.executionframework.value.model.value.impl.ManyLongAttributeValueImpl#getAttributeValue <em>Attribute Value</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ManyLongAttributeValueImpl extends AttributeValueImpl implements ManyLongAttributeValue {
	/**
	 * The cached value of the '{@link #getAttributeValue() <em>Attribute Value</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttributeValue()
	 * @generated
	 * @ordered
	 */
	protected EList<Long> attributeValue;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ManyLongAttributeValueImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ValuePackage.Literals.MANY_LONG_ATTRIBUTE_VALUE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Long> getAttributeValue() {
		if (attributeValue == null) {
			attributeValue = new EDataTypeUniqueEList<Long>(Long.class, this, ValuePackage.MANY_LONG_ATTRIBUTE_VALUE__ATTRIBUTE_VALUE);
		}
		return attributeValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ValuePackage.MANY_LONG_ATTRIBUTE_VALUE__ATTRIBUTE_VALUE:
				return getAttributeValue();
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
			case ValuePackage.MANY_LONG_ATTRIBUTE_VALUE__ATTRIBUTE_VALUE:
				getAttributeValue().clear();
				getAttributeValue().addAll((Collection<? extends Long>)newValue);
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
			case ValuePackage.MANY_LONG_ATTRIBUTE_VALUE__ATTRIBUTE_VALUE:
				getAttributeValue().clear();
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
			case ValuePackage.MANY_LONG_ATTRIBUTE_VALUE__ATTRIBUTE_VALUE:
				return attributeValue != null && !attributeValue.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuilder result = new StringBuilder(super.toString());
		result.append(" (attributeValue: ");
		result.append(attributeValue);
		result.append(')');
		return result.toString();
	}

} //ManyLongAttributeValueImpl
