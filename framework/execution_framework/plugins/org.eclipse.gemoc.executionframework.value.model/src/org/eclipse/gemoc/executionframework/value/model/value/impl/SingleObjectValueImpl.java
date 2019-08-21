/**
 */
package org.eclipse.gemoc.executionframework.value.model.value.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.gemoc.executionframework.value.model.value.SingleObjectValue;
import org.eclipse.gemoc.executionframework.value.model.value.ValuePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Single Object Value</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.gemoc.executionframework.value.model.value.impl.SingleObjectValueImpl#getObjectValue <em>Object Value</em>}</li>
 * </ul>
 *
 * @generated
 */
public class SingleObjectValueImpl extends ReferenceValueImpl implements SingleObjectValue {
	/**
	 * The cached value of the '{@link #getObjectValue() <em>Object Value</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getObjectValue()
	 * @generated
	 * @ordered
	 */
	protected EObject objectValue;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SingleObjectValueImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ValuePackage.Literals.SINGLE_OBJECT_VALUE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EObject getObjectValue() {
		return objectValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetObjectValue(EObject newObjectValue, NotificationChain msgs) {
		EObject oldObjectValue = objectValue;
		objectValue = newObjectValue;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ValuePackage.SINGLE_OBJECT_VALUE__OBJECT_VALUE, oldObjectValue, newObjectValue);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setObjectValue(EObject newObjectValue) {
		if (newObjectValue != objectValue) {
			NotificationChain msgs = null;
			if (objectValue != null)
				msgs = ((InternalEObject)objectValue).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ValuePackage.SINGLE_OBJECT_VALUE__OBJECT_VALUE, null, msgs);
			if (newObjectValue != null)
				msgs = ((InternalEObject)newObjectValue).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ValuePackage.SINGLE_OBJECT_VALUE__OBJECT_VALUE, null, msgs);
			msgs = basicSetObjectValue(newObjectValue, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ValuePackage.SINGLE_OBJECT_VALUE__OBJECT_VALUE, newObjectValue, newObjectValue));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ValuePackage.SINGLE_OBJECT_VALUE__OBJECT_VALUE:
				return basicSetObjectValue(null, msgs);
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
			case ValuePackage.SINGLE_OBJECT_VALUE__OBJECT_VALUE:
				return getObjectValue();
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
			case ValuePackage.SINGLE_OBJECT_VALUE__OBJECT_VALUE:
				setObjectValue((EObject)newValue);
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
			case ValuePackage.SINGLE_OBJECT_VALUE__OBJECT_VALUE:
				setObjectValue((EObject)null);
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
			case ValuePackage.SINGLE_OBJECT_VALUE__OBJECT_VALUE:
				return objectValue != null;
		}
		return super.eIsSet(featureID);
	}

} //SingleObjectValueImpl
