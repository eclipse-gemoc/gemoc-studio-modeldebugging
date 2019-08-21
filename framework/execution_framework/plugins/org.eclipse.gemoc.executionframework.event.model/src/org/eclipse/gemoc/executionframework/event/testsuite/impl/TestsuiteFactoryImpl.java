/**
 */
package org.eclipse.gemoc.executionframework.event.testsuite.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.eclipse.gemoc.executionframework.event.testsuite.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class TestsuiteFactoryImpl extends EFactoryImpl implements TestsuiteFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static TestsuiteFactory init() {
		try {
			TestsuiteFactory theTestsuiteFactory = (TestsuiteFactory)EPackage.Registry.INSTANCE.getEFactory(TestsuitePackage.eNS_URI);
			if (theTestsuiteFactory != null) {
				return theTestsuiteFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new TestsuiteFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TestsuiteFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case TestsuitePackage.TEST_SUITE: return createTestSuite();
			case TestsuitePackage.TEST_CASE: return createTestCase();
			case TestsuitePackage.TEST_SUITE_REPORT: return createTestSuiteReport();
			case TestsuitePackage.TEST_CASE_SUCCESS: return createTestCaseSuccess();
			case TestsuitePackage.TEST_CASE_FAILURE: return createTestCaseFailure();
			case TestsuitePackage.TEST_CASE_ERROR: return createTestCaseError();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TestSuite createTestSuite() {
		TestSuiteImpl testSuite = new TestSuiteImpl();
		return testSuite;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TestCase createTestCase() {
		TestCaseImpl testCase = new TestCaseImpl();
		return testCase;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TestSuiteReport createTestSuiteReport() {
		TestSuiteReportImpl testSuiteReport = new TestSuiteReportImpl();
		return testSuiteReport;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TestCaseSuccess createTestCaseSuccess() {
		TestCaseSuccessImpl testCaseSuccess = new TestCaseSuccessImpl();
		return testCaseSuccess;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TestCaseFailure createTestCaseFailure() {
		TestCaseFailureImpl testCaseFailure = new TestCaseFailureImpl();
		return testCaseFailure;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TestCaseError createTestCaseError() {
		TestCaseErrorImpl testCaseError = new TestCaseErrorImpl();
		return testCaseError;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TestsuitePackage getTestsuitePackage() {
		return (TestsuitePackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static TestsuitePackage getPackage() {
		return TestsuitePackage.eINSTANCE;
	}

} //TestsuiteFactoryImpl
