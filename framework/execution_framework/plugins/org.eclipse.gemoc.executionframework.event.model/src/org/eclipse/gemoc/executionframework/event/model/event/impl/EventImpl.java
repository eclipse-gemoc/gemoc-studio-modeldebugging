/**
 */
package org.eclipse.gemoc.executionframework.event.model.event.impl;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.gemoc.executionframework.event.model.event.Event;
import org.eclipse.gemoc.executionframework.event.model.event.EventPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Event</b></em>'.
 * <!-- end-user-doc -->
 *
 * @generated
 */
public abstract class EventImpl extends MinimalEObjectImpl.Container implements Event {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EventImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EventPackage.Literals.EVENT;
	}

} //EventImpl
