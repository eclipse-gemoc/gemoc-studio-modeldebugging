/**
 */
package org.eclipse.gemoc.executionframework.event.model.event;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.gemoc.executionframework.behavioralinterface.behavioralInterface.EventParameter;

import org.eclipse.gemoc.executionframework.value.model.value.Value;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Occurrence Argument</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.gemoc.executionframework.event.model.event.EventOccurrenceArgument#getParameter <em>Parameter</em>}</li>
 *   <li>{@link org.eclipse.gemoc.executionframework.event.model.event.EventOccurrenceArgument#getValue <em>Value</em>}</li>
 * </ul>
 *
 * @see org.eclipse.gemoc.executionframework.event.model.event.EventPackage#getEventOccurrenceArgument()
 * @model
 * @generated
 */
public interface EventOccurrenceArgument extends EObject {
	/**
	 * Returns the value of the '<em><b>Parameter</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parameter</em>' reference.
	 * @see #setParameter(EventParameter)
	 * @see org.eclipse.gemoc.executionframework.event.model.event.EventPackage#getEventOccurrenceArgument_Parameter()
	 * @model
	 * @generated
	 */
	EventParameter getParameter();

	/**
	 * Sets the value of the '{@link org.eclipse.gemoc.executionframework.event.model.event.EventOccurrenceArgument#getParameter <em>Parameter</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parameter</em>' reference.
	 * @see #getParameter()
	 * @generated
	 */
	void setParameter(EventParameter value);

	/**
	 * Returns the value of the '<em><b>Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Value</em>' containment reference.
	 * @see #setValue(Value)
	 * @see org.eclipse.gemoc.executionframework.event.model.event.EventPackage#getEventOccurrenceArgument_Value()
	 * @model containment="true"
	 * @generated
	 */
	Value getValue();

	/**
	 * Sets the value of the '{@link org.eclipse.gemoc.executionframework.event.model.event.EventOccurrenceArgument#getValue <em>Value</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value</em>' containment reference.
	 * @see #getValue()
	 * @generated
	 */
	void setValue(Value value);

} // EventOccurrenceArgument
