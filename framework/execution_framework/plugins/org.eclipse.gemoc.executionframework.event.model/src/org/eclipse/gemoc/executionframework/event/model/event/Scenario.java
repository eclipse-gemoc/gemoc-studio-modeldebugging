/**
 */
package org.eclipse.gemoc.executionframework.event.model.event;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Scenario</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.gemoc.executionframework.event.model.event.Scenario#getEvents <em>Events</em>}</li>
 * </ul>
 *
 * @see org.eclipse.gemoc.executionframework.event.model.event.EventPackage#getScenario()
 * @model
 * @generated
 */
public interface Scenario extends EObject {
	/**
	 * Returns the value of the '<em><b>Events</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.gemoc.executionframework.event.model.event.EventOccurrence}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Events</em>' containment reference list.
	 * @see org.eclipse.gemoc.executionframework.event.model.event.EventPackage#getScenario_Events()
	 * @model containment="true"
	 * @generated
	 */
	EList<EventOccurrence> getEvents();

} // Scenario
