/**
 */
package org.eclipse.gemoc.executionframework.event.testsuite;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.gemoc.executionframework.event.testsuite.TestsuitePackage
 * @generated
 */
public interface TestsuiteFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	TestsuiteFactory eINSTANCE = org.eclipse.gemoc.executionframework.event.testsuite.impl.TestsuiteFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Test Suite</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Test Suite</em>'.
	 * @generated
	 */
	TestSuite createTestSuite();

	/**
	 * Returns a new object of class '<em>Test Case</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Test Case</em>'.
	 * @generated
	 */
	TestCase createTestCase();

	/**
	 * Returns a new object of class '<em>Test Suite Report</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Test Suite Report</em>'.
	 * @generated
	 */
	TestSuiteReport createTestSuiteReport();

	/**
	 * Returns a new object of class '<em>Test Case Success</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Test Case Success</em>'.
	 * @generated
	 */
	TestCaseSuccess createTestCaseSuccess();

	/**
	 * Returns a new object of class '<em>Test Case Failure</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Test Case Failure</em>'.
	 * @generated
	 */
	TestCaseFailure createTestCaseFailure();

	/**
	 * Returns a new object of class '<em>Test Case Error</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Test Case Error</em>'.
	 * @generated
	 */
	TestCaseError createTestCaseError();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	TestsuitePackage getTestsuitePackage();

} //TestsuiteFactory
