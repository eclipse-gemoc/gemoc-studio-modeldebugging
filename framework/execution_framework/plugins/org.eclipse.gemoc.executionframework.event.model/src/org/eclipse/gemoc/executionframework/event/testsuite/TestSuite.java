/**
 */
package org.eclipse.gemoc.executionframework.event.testsuite;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Test Suite</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.gemoc.executionframework.event.testsuite.TestSuite#getTestCases <em>Test Cases</em>}</li>
 *   <li>{@link org.eclipse.gemoc.executionframework.event.testsuite.TestSuite#getStorage <em>Storage</em>}</li>
 * </ul>
 *
 * @see org.eclipse.gemoc.executionframework.event.testsuite.TestsuitePackage#getTestSuite()
 * @model
 * @generated
 */
public interface TestSuite extends EObject {
	/**
	 * Returns the value of the '<em><b>Test Cases</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.gemoc.executionframework.event.testsuite.TestCase}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Test Cases</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Test Cases</em>' containment reference list.
	 * @see org.eclipse.gemoc.executionframework.event.testsuite.TestsuitePackage#getTestSuite_TestCases()
	 * @model containment="true"
	 * @generated
	 */
	EList<TestCase> getTestCases();

	/**
	 * Returns the value of the '<em><b>Storage</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.EObject}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Storage</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Storage</em>' containment reference list.
	 * @see org.eclipse.gemoc.executionframework.event.testsuite.TestsuitePackage#getTestSuite_Storage()
	 * @model containment="true"
	 * @generated
	 */
	EList<EObject> getStorage();

} // TestSuite
