/**
 */
package org.eclipse.gemoc.executionframework.event.testsuite;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.gemoc.executionframework.event.model.event.EventOccurrence;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Test Case</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.gemoc.executionframework.event.testsuite.TestCase#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.gemoc.executionframework.event.testsuite.TestCase#getModel <em>Model</em>}</li>
 *   <li>{@link org.eclipse.gemoc.executionframework.event.testsuite.TestCase#getExpectedTrace <em>Expected Trace</em>}</li>
 *   <li>{@link org.eclipse.gemoc.executionframework.event.testsuite.TestCase#getScenario <em>Scenario</em>}</li>
 * </ul>
 *
 * @see org.eclipse.gemoc.executionframework.event.testsuite.TestsuitePackage#getTestCase()
 * @model
 * @generated
 */
public interface TestCase extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.eclipse.gemoc.executionframework.event.testsuite.TestsuitePackage#getTestCase_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.eclipse.gemoc.executionframework.event.testsuite.TestCase#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Model</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Model</em>' reference.
	 * @see #setModel(EObject)
	 * @see org.eclipse.gemoc.executionframework.event.testsuite.TestsuitePackage#getTestCase_Model()
	 * @model
	 * @generated
	 */
	EObject getModel();

	/**
	 * Sets the value of the '{@link org.eclipse.gemoc.executionframework.event.testsuite.TestCase#getModel <em>Model</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Model</em>' reference.
	 * @see #getModel()
	 * @generated
	 */
	void setModel(EObject value);

	/**
	 * Returns the value of the '<em><b>Expected Trace</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Expected Trace</em>' attribute.
	 * @see #setExpectedTrace(String)
	 * @see org.eclipse.gemoc.executionframework.event.testsuite.TestsuitePackage#getTestCase_ExpectedTrace()
	 * @model
	 * @generated
	 */
	String getExpectedTrace();

	/**
	 * Sets the value of the '{@link org.eclipse.gemoc.executionframework.event.testsuite.TestCase#getExpectedTrace <em>Expected Trace</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Expected Trace</em>' attribute.
	 * @see #getExpectedTrace()
	 * @generated
	 */
	void setExpectedTrace(String value);

	/**
	 * Returns the value of the '<em><b>Scenario</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.gemoc.executionframework.event.model.event.EventOccurrence}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Scenario</em>' containment reference list.
	 * @see org.eclipse.gemoc.executionframework.event.testsuite.TestsuitePackage#getTestCase_Scenario()
	 * @model containment="true"
	 * @generated
	 */
	EList<EventOccurrence> getScenario();

} // TestCase
