/**
 */
package org.eclipse.gemoc.executionframework.event.model.event;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.gemoc.executionframework.event.model.event.EventPackage
 * @generated
 */
public interface EventFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	EventFactory eINSTANCE = org.eclipse.gemoc.executionframework.event.model.event.impl.EventFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Occurrence</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Occurrence</em>'.
	 * @generated
	 */
	EventOccurrence createEventOccurrence();

	/**
	 * Returns a new object of class '<em>Stop Event Occurrence</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Stop Event Occurrence</em>'.
	 * @generated
	 */
	StopEventOccurrence createStopEventOccurrence();

	/**
	 * Returns a new object of class '<em>Occurrence Argument</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Occurrence Argument</em>'.
	 * @generated
	 */
	EventOccurrenceArgument createEventOccurrenceArgument();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	EventPackage getEventPackage();

} //EventFactory
