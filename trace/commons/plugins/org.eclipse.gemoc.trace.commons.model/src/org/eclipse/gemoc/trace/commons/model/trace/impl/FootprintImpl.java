/**
 */
package org.eclipse.gemoc.trace.commons.model.trace.impl;

import java.util.Collection;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

import org.eclipse.gemoc.trace.commons.model.trace.Footprint;
import org.eclipse.gemoc.trace.commons.model.trace.TracePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Footprint</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.gemoc.trace.commons.model.trace.impl.FootprintImpl#getAccesses <em>Accesses</em>}</li>
 *   <li>{@link org.eclipse.gemoc.trace.commons.model.trace.impl.FootprintImpl#getChanges <em>Changes</em>}</li>
 *   <li>{@link org.eclipse.gemoc.trace.commons.model.trace.impl.FootprintImpl#getInstantiations <em>Instantiations</em>}</li>
 * </ul>
 *
 * @generated
 */
public class FootprintImpl extends MinimalEObjectImpl.Container implements Footprint {
	/**
	 * The cached value of the '{@link #getAccesses() <em>Accesses</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAccesses()
	 * @generated
	 * @ordered
	 */
	protected EList<EObject> accesses;

	/**
	 * The cached value of the '{@link #getChanges() <em>Changes</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getChanges()
	 * @generated
	 * @ordered
	 */
	protected EList<EObject> changes;

	/**
	 * The cached value of the '{@link #getInstantiations() <em>Instantiations</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInstantiations()
	 * @generated
	 * @ordered
	 */
	protected EList<EClass> instantiations;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FootprintImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TracePackage.Literals.FOOTPRINT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<EObject> getAccesses() {
		if (accesses == null) {
			accesses = new EObjectResolvingEList<EObject>(EObject.class, this, TracePackage.FOOTPRINT__ACCESSES);
		}
		return accesses;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<EObject> getChanges() {
		if (changes == null) {
			changes = new EObjectResolvingEList<EObject>(EObject.class, this, TracePackage.FOOTPRINT__CHANGES);
		}
		return changes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<EClass> getInstantiations() {
		if (instantiations == null) {
			instantiations = new EObjectResolvingEList<EClass>(EClass.class, this, TracePackage.FOOTPRINT__INSTANTIATIONS);
		}
		return instantiations;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case TracePackage.FOOTPRINT__ACCESSES:
				return getAccesses();
			case TracePackage.FOOTPRINT__CHANGES:
				return getChanges();
			case TracePackage.FOOTPRINT__INSTANTIATIONS:
				return getInstantiations();
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
			case TracePackage.FOOTPRINT__ACCESSES:
				getAccesses().clear();
				getAccesses().addAll((Collection<? extends EObject>)newValue);
				return;
			case TracePackage.FOOTPRINT__CHANGES:
				getChanges().clear();
				getChanges().addAll((Collection<? extends EObject>)newValue);
				return;
			case TracePackage.FOOTPRINT__INSTANTIATIONS:
				getInstantiations().clear();
				getInstantiations().addAll((Collection<? extends EClass>)newValue);
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
			case TracePackage.FOOTPRINT__ACCESSES:
				getAccesses().clear();
				return;
			case TracePackage.FOOTPRINT__CHANGES:
				getChanges().clear();
				return;
			case TracePackage.FOOTPRINT__INSTANTIATIONS:
				getInstantiations().clear();
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
			case TracePackage.FOOTPRINT__ACCESSES:
				return accesses != null && !accesses.isEmpty();
			case TracePackage.FOOTPRINT__CHANGES:
				return changes != null && !changes.isEmpty();
			case TracePackage.FOOTPRINT__INSTANTIATIONS:
				return instantiations != null && !instantiations.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //FootprintImpl
