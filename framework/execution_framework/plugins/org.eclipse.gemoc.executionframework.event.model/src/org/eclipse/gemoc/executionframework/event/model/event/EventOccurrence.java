/**
 */
package org.eclipse.gemoc.executionframework.event.model.event;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.gemoc.executionframework.behavioralinterface.behavioralInterface.Event;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Occurrence</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.gemoc.executionframework.event.model.event.EventOccurrence#getEvent <em>Event</em>}</li>
 *   <li>{@link org.eclipse.gemoc.executionframework.event.model.event.EventOccurrence#getArgs <em>Args</em>}</li>
 *   <li>{@link org.eclipse.gemoc.executionframework.event.model.event.EventOccurrence#getType <em>Type</em>}</li>
 * </ul>
 *
 * @see org.eclipse.gemoc.executionframework.event.model.event.EventPackage#getEventOccurrence()
 * @model
 * @generated
 */
public interface EventOccurrence extends EObject {
	/**
	 * Returns the value of the '<em><b>Event</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Event</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Event</em>' reference.
	 * @see #setEvent(Event)
	 * @see org.eclipse.gemoc.executionframework.event.model.event.EventPackage#getEventOccurrence_Event()
	 * @model
	 * @generated
	 */
	Event getEvent();

	/**
	 * Sets the value of the '{@link org.eclipse.gemoc.executionframework.event.model.event.EventOccurrence#getEvent <em>Event</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Event</em>' reference.
	 * @see #getEvent()
	 * @generated
	 */
	void setEvent(Event value);

	/**
	 * Returns the value of the '<em><b>Args</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.gemoc.executionframework.event.model.event.EventOccurrenceArgument}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Args</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Args</em>' containment reference list.
	 * @see org.eclipse.gemoc.executionframework.event.model.event.EventPackage#getEventOccurrence_Args()
	 * @model containment="true"
	 * @generated
	 */
	EList<EventOccurrenceArgument> getArgs();

	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.gemoc.executionframework.event.model.event.EventOccurrenceType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see org.eclipse.gemoc.executionframework.event.model.event.EventOccurrenceType
	 * @see #setType(EventOccurrenceType)
	 * @see org.eclipse.gemoc.executionframework.event.model.event.EventPackage#getEventOccurrence_Type()
	 * @model required="true"
	 * @generated
	 */
	EventOccurrenceType getType();

	/**
	 * Sets the value of the '{@link org.eclipse.gemoc.executionframework.event.model.event.EventOccurrence#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see org.eclipse.gemoc.executionframework.event.model.event.EventOccurrenceType
	 * @see #getType()
	 * @generated
	 */
	void setType(EventOccurrenceType value);

} // EventOccurrence
