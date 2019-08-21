/**
 */
package org.eclipse.gemoc.executionframework.event.testsuite;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Test Case Failure</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.gemoc.executionframework.event.testsuite.TestCaseFailure#getTrace <em>Trace</em>}</li>
 * </ul>
 *
 * @see org.eclipse.gemoc.executionframework.event.testsuite.TestsuitePackage#getTestCaseFailure()
 * @model
 * @generated
 */
public interface TestCaseFailure extends TestCaseReport {
	/**
	 * Returns the value of the '<em><b>Trace</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Trace</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Trace</em>' attribute.
	 * @see #setTrace(String)
	 * @see org.eclipse.gemoc.executionframework.event.testsuite.TestsuitePackage#getTestCaseFailure_Trace()
	 * @model
	 * @generated
	 */
	String getTrace();

	/**
	 * Sets the value of the '{@link org.eclipse.gemoc.executionframework.event.testsuite.TestCaseFailure#getTrace <em>Trace</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Trace</em>' attribute.
	 * @see #getTrace()
	 * @generated
	 */
	void setTrace(String value);

} // TestCaseFailure
