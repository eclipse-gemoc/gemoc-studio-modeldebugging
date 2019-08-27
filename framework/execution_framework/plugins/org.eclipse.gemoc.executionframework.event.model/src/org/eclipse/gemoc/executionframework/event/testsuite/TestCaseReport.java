/**
 */
package org.eclipse.gemoc.executionframework.event.testsuite;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Test Case Report</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.gemoc.executionframework.event.testsuite.TestCaseReport#getTestCase <em>Test Case</em>}</li>
 * </ul>
 *
 * @see org.eclipse.gemoc.executionframework.event.testsuite.TestsuitePackage#getTestCaseReport()
 * @model abstract="true"
 * @generated
 */
public interface TestCaseReport extends EObject {
	/**
	 * Returns the value of the '<em><b>Test Case</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Test Case</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Test Case</em>' reference.
	 * @see #setTestCase(TestCase)
	 * @see org.eclipse.gemoc.executionframework.event.testsuite.TestsuitePackage#getTestCaseReport_TestCase()
	 * @model
	 * @generated
	 */
	TestCase getTestCase();

	/**
	 * Sets the value of the '{@link org.eclipse.gemoc.executionframework.event.testsuite.TestCaseReport#getTestCase <em>Test Case</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Test Case</em>' reference.
	 * @see #getTestCase()
	 * @generated
	 */
	void setTestCase(TestCase value);

} // TestCaseReport
