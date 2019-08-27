/**
 */
package org.eclipse.gemoc.executionframework.value.model.value.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.gemoc.executionframework.value.model.value.BooleanObjectAttributeValue;
import org.eclipse.gemoc.executionframework.value.model.value.ValuePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Boolean Object Attribute Value</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.gemoc.executionframework.value.model.value.impl.BooleanObjectAttributeValueImpl#getAttributeValue <em>Attribute Value</em>}</li>
 * </ul>
 *
 * @generated
 */
public class BooleanObjectAttributeValueImpl extends AttributeValueImpl implements BooleanObjectAttributeValue {
	/**
	 * The default value of the '{@link #getAttributeValue() <em>Attribute Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttributeValue()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean ATTRIBUTE_VALUE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getAttributeValue() <em>Attribute Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttributeValue()
	 * @generated
	 * @ordered
	 */
	protected Boolean attributeValue = ATTRIBUTE_VALUE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BooleanObjectAttributeValueImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ValuePackage.Literals.BOOLEAN_OBJECT_ATTRIBUTE_VALUE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getAttributeValue() {
		return attributeValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAttributeValue(Boolean newAttributeValue) {
		Boolean oldAttributeValue = attributeValue;
		attributeValue = newAttributeValue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ValuePackage.BOOLEAN_OBJECT_ATTRIBUTE_VALUE__ATTRIBUTE_VALUE, oldAttributeValue, attributeValue));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ValuePackage.BOOLEAN_OBJECT_ATTRIBUTE_VALUE__ATTRIBUTE_VALUE:
				return getAttributeValue();
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
			case ValuePackage.BOOLEAN_OBJECT_ATTRIBUTE_VALUE__ATTRIBUTE_VALUE:
				setAttributeValue((Boolean)newValue);
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
			case ValuePackage.BOOLEAN_OBJECT_ATTRIBUTE_VALUE__ATTRIBUTE_VALUE:
				setAttributeValue(ATTRIBUTE_VALUE_EDEFAULT);
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
			case ValuePackage.BOOLEAN_OBJECT_ATTRIBUTE_VALUE__ATTRIBUTE_VALUE:
				return ATTRIBUTE_VALUE_EDEFAULT == null ? attributeValue != null : !ATTRIBUTE_VALUE_EDEFAULT.equals(attributeValue);
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

} //BooleanObjectAttributeValueImpl
