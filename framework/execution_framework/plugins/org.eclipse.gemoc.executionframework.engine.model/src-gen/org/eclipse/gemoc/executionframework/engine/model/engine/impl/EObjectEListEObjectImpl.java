/**
 */
package org.eclipse.gemoc.executionframework.engine.model.engine.impl;

import java.util.Collection;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.gemoc.executionframework.engine.model.engine.EObjectEListEObject;
import org.eclipse.gemoc.executionframework.engine.model.engine.EnginePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>EObject EList EObject</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.gemoc.executionframework.engine.model.engine.impl.EObjectEListEObjectImpl#getContents <em>Contents</em>}</li>
 * </ul>
 *
 * @generated
 */
public class EObjectEListEObjectImpl extends MinimalEObjectImpl.Container implements EObjectEListEObject {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EObjectEListEObjectImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EnginePackage.Literals.EOBJECT_ELIST_EOBJECT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<EObject> getContents() {
		// TODO: implement this method to return the 'Contents' reference list
		// Ensure that you remove @generated or mark it @generated NOT
		// The list is expected to implement org.eclipse.emf.ecore.util.InternalEList and org.eclipse.emf.ecore.EStructuralFeature.Setting
		// so it's likely that an appropriate subclass of org.eclipse.emf.ecore.util.EcoreEList should be used.
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case EnginePackage.EOBJECT_ELIST_EOBJECT__CONTENTS:
			return getContents();
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
		case EnginePackage.EOBJECT_ELIST_EOBJECT__CONTENTS:
			getContents().clear();
			getContents().addAll((Collection<? extends EObject>) newValue);
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
		case EnginePackage.EOBJECT_ELIST_EOBJECT__CONTENTS:
			getContents().clear();
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
		case EnginePackage.EOBJECT_ELIST_EOBJECT__CONTENTS:
			return !getContents().isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //EObjectEListEObjectImpl
