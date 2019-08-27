/**
 */
package org.eclipse.gemoc.executionframework.event.testsuite;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Test Suite Report</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.gemoc.executionframework.event.testsuite.TestSuiteReport#getTestCaseReports <em>Test Case Reports</em>}</li>
 * </ul>
 *
 * @see org.eclipse.gemoc.executionframework.event.testsuite.TestsuitePackage#getTestSuiteReport()
 * @model
 * @generated
 */
public interface TestSuiteReport extends EObject {
	/**
	 * Returns the value of the '<em><b>Test Case Reports</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.gemoc.executionframework.event.testsuite.TestCaseReport}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Test Case Reports</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Test Case Reports</em>' containment reference list.
	 * @see org.eclipse.gemoc.executionframework.event.testsuite.TestsuitePackage#getTestSuiteReport_TestCaseReports()
	 * @model containment="true"
	 * @generated
	 */
	EList<TestCaseReport> getTestCaseReports();

} // TestSuiteReport
