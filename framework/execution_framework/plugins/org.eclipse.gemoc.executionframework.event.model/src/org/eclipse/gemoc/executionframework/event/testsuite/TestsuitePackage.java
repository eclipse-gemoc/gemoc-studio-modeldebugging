/**
 */
package org.eclipse.gemoc.executionframework.event.testsuite;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.eclipse.gemoc.executionframework.event.testsuite.TestsuiteFactory
 * @model kind="package"
 * @generated
 */
public interface TestsuitePackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "testsuite";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://http://www.eclipse.org/gemoc/event/testsuite";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "testsuite";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	TestsuitePackage eINSTANCE = org.eclipse.gemoc.executionframework.event.testsuite.impl.TestsuitePackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.gemoc.executionframework.event.testsuite.impl.TestSuiteImpl <em>Test Suite</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gemoc.executionframework.event.testsuite.impl.TestSuiteImpl
	 * @see org.eclipse.gemoc.executionframework.event.testsuite.impl.TestsuitePackageImpl#getTestSuite()
	 * @generated
	 */
	int TEST_SUITE = 0;

	/**
	 * The feature id for the '<em><b>Test Cases</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_SUITE__TEST_CASES = 0;

	/**
	 * The feature id for the '<em><b>Storage</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_SUITE__STORAGE = 1;

	/**
	 * The number of structural features of the '<em>Test Suite</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_SUITE_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Test Suite</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_SUITE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.gemoc.executionframework.event.testsuite.impl.TestCaseImpl <em>Test Case</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gemoc.executionframework.event.testsuite.impl.TestCaseImpl
	 * @see org.eclipse.gemoc.executionframework.event.testsuite.impl.TestsuitePackageImpl#getTestCase()
	 * @generated
	 */
	int TEST_CASE = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CASE__NAME = 0;

	/**
	 * The feature id for the '<em><b>Model</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CASE__MODEL = 1;

	/**
	 * The feature id for the '<em><b>Expected Trace</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CASE__EXPECTED_TRACE = 2;

	/**
	 * The feature id for the '<em><b>Scenario</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CASE__SCENARIO = 3;

	/**
	 * The number of structural features of the '<em>Test Case</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CASE_FEATURE_COUNT = 4;

	/**
	 * The number of operations of the '<em>Test Case</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CASE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.gemoc.executionframework.event.testsuite.impl.TestSuiteReportImpl <em>Test Suite Report</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gemoc.executionframework.event.testsuite.impl.TestSuiteReportImpl
	 * @see org.eclipse.gemoc.executionframework.event.testsuite.impl.TestsuitePackageImpl#getTestSuiteReport()
	 * @generated
	 */
	int TEST_SUITE_REPORT = 2;

	/**
	 * The feature id for the '<em><b>Test Case Reports</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_SUITE_REPORT__TEST_CASE_REPORTS = 0;

	/**
	 * The number of structural features of the '<em>Test Suite Report</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_SUITE_REPORT_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Test Suite Report</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_SUITE_REPORT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.gemoc.executionframework.event.testsuite.impl.TestCaseReportImpl <em>Test Case Report</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gemoc.executionframework.event.testsuite.impl.TestCaseReportImpl
	 * @see org.eclipse.gemoc.executionframework.event.testsuite.impl.TestsuitePackageImpl#getTestCaseReport()
	 * @generated
	 */
	int TEST_CASE_REPORT = 3;

	/**
	 * The feature id for the '<em><b>Test Case</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CASE_REPORT__TEST_CASE = 0;

	/**
	 * The number of structural features of the '<em>Test Case Report</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CASE_REPORT_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Test Case Report</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CASE_REPORT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.gemoc.executionframework.event.testsuite.impl.TestCaseSuccessImpl <em>Test Case Success</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gemoc.executionframework.event.testsuite.impl.TestCaseSuccessImpl
	 * @see org.eclipse.gemoc.executionframework.event.testsuite.impl.TestsuitePackageImpl#getTestCaseSuccess()
	 * @generated
	 */
	int TEST_CASE_SUCCESS = 4;

	/**
	 * The feature id for the '<em><b>Test Case</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CASE_SUCCESS__TEST_CASE = TEST_CASE_REPORT__TEST_CASE;

	/**
	 * The number of structural features of the '<em>Test Case Success</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CASE_SUCCESS_FEATURE_COUNT = TEST_CASE_REPORT_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Test Case Success</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CASE_SUCCESS_OPERATION_COUNT = TEST_CASE_REPORT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.gemoc.executionframework.event.testsuite.impl.TestCaseFailureImpl <em>Test Case Failure</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gemoc.executionframework.event.testsuite.impl.TestCaseFailureImpl
	 * @see org.eclipse.gemoc.executionframework.event.testsuite.impl.TestsuitePackageImpl#getTestCaseFailure()
	 * @generated
	 */
	int TEST_CASE_FAILURE = 5;

	/**
	 * The feature id for the '<em><b>Test Case</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CASE_FAILURE__TEST_CASE = TEST_CASE_REPORT__TEST_CASE;

	/**
	 * The feature id for the '<em><b>Trace</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CASE_FAILURE__TRACE = TEST_CASE_REPORT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Test Case Failure</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CASE_FAILURE_FEATURE_COUNT = TEST_CASE_REPORT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Test Case Failure</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CASE_FAILURE_OPERATION_COUNT = TEST_CASE_REPORT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.gemoc.executionframework.event.testsuite.impl.TestCaseErrorImpl <em>Test Case Error</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gemoc.executionframework.event.testsuite.impl.TestCaseErrorImpl
	 * @see org.eclipse.gemoc.executionframework.event.testsuite.impl.TestsuitePackageImpl#getTestCaseError()
	 * @generated
	 */
	int TEST_CASE_ERROR = 6;

	/**
	 * The feature id for the '<em><b>Test Case</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CASE_ERROR__TEST_CASE = TEST_CASE_REPORT__TEST_CASE;

	/**
	 * The number of structural features of the '<em>Test Case Error</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CASE_ERROR_FEATURE_COUNT = TEST_CASE_REPORT_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Test Case Error</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CASE_ERROR_OPERATION_COUNT = TEST_CASE_REPORT_OPERATION_COUNT + 0;


	/**
	 * Returns the meta object for class '{@link org.eclipse.gemoc.executionframework.event.testsuite.TestSuite <em>Test Suite</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Test Suite</em>'.
	 * @see org.eclipse.gemoc.executionframework.event.testsuite.TestSuite
	 * @generated
	 */
	EClass getTestSuite();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.gemoc.executionframework.event.testsuite.TestSuite#getTestCases <em>Test Cases</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Test Cases</em>'.
	 * @see org.eclipse.gemoc.executionframework.event.testsuite.TestSuite#getTestCases()
	 * @see #getTestSuite()
	 * @generated
	 */
	EReference getTestSuite_TestCases();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.gemoc.executionframework.event.testsuite.TestSuite#getStorage <em>Storage</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Storage</em>'.
	 * @see org.eclipse.gemoc.executionframework.event.testsuite.TestSuite#getStorage()
	 * @see #getTestSuite()
	 * @generated
	 */
	EReference getTestSuite_Storage();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gemoc.executionframework.event.testsuite.TestCase <em>Test Case</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Test Case</em>'.
	 * @see org.eclipse.gemoc.executionframework.event.testsuite.TestCase
	 * @generated
	 */
	EClass getTestCase();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.gemoc.executionframework.event.testsuite.TestCase#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.gemoc.executionframework.event.testsuite.TestCase#getName()
	 * @see #getTestCase()
	 * @generated
	 */
	EAttribute getTestCase_Name();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.gemoc.executionframework.event.testsuite.TestCase#getModel <em>Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Model</em>'.
	 * @see org.eclipse.gemoc.executionframework.event.testsuite.TestCase#getModel()
	 * @see #getTestCase()
	 * @generated
	 */
	EReference getTestCase_Model();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.gemoc.executionframework.event.testsuite.TestCase#getExpectedTrace <em>Expected Trace</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Expected Trace</em>'.
	 * @see org.eclipse.gemoc.executionframework.event.testsuite.TestCase#getExpectedTrace()
	 * @see #getTestCase()
	 * @generated
	 */
	EAttribute getTestCase_ExpectedTrace();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.gemoc.executionframework.event.testsuite.TestCase#getScenario <em>Scenario</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Scenario</em>'.
	 * @see org.eclipse.gemoc.executionframework.event.testsuite.TestCase#getScenario()
	 * @see #getTestCase()
	 * @generated
	 */
	EReference getTestCase_Scenario();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gemoc.executionframework.event.testsuite.TestSuiteReport <em>Test Suite Report</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Test Suite Report</em>'.
	 * @see org.eclipse.gemoc.executionframework.event.testsuite.TestSuiteReport
	 * @generated
	 */
	EClass getTestSuiteReport();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.gemoc.executionframework.event.testsuite.TestSuiteReport#getTestCaseReports <em>Test Case Reports</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Test Case Reports</em>'.
	 * @see org.eclipse.gemoc.executionframework.event.testsuite.TestSuiteReport#getTestCaseReports()
	 * @see #getTestSuiteReport()
	 * @generated
	 */
	EReference getTestSuiteReport_TestCaseReports();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gemoc.executionframework.event.testsuite.TestCaseReport <em>Test Case Report</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Test Case Report</em>'.
	 * @see org.eclipse.gemoc.executionframework.event.testsuite.TestCaseReport
	 * @generated
	 */
	EClass getTestCaseReport();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.gemoc.executionframework.event.testsuite.TestCaseReport#getTestCase <em>Test Case</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Test Case</em>'.
	 * @see org.eclipse.gemoc.executionframework.event.testsuite.TestCaseReport#getTestCase()
	 * @see #getTestCaseReport()
	 * @generated
	 */
	EReference getTestCaseReport_TestCase();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gemoc.executionframework.event.testsuite.TestCaseSuccess <em>Test Case Success</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Test Case Success</em>'.
	 * @see org.eclipse.gemoc.executionframework.event.testsuite.TestCaseSuccess
	 * @generated
	 */
	EClass getTestCaseSuccess();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gemoc.executionframework.event.testsuite.TestCaseFailure <em>Test Case Failure</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Test Case Failure</em>'.
	 * @see org.eclipse.gemoc.executionframework.event.testsuite.TestCaseFailure
	 * @generated
	 */
	EClass getTestCaseFailure();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.gemoc.executionframework.event.testsuite.TestCaseFailure#getTrace <em>Trace</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Trace</em>'.
	 * @see org.eclipse.gemoc.executionframework.event.testsuite.TestCaseFailure#getTrace()
	 * @see #getTestCaseFailure()
	 * @generated
	 */
	EAttribute getTestCaseFailure_Trace();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gemoc.executionframework.event.testsuite.TestCaseError <em>Test Case Error</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Test Case Error</em>'.
	 * @see org.eclipse.gemoc.executionframework.event.testsuite.TestCaseError
	 * @generated
	 */
	EClass getTestCaseError();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	TestsuiteFactory getTestsuiteFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link org.eclipse.gemoc.executionframework.event.testsuite.impl.TestSuiteImpl <em>Test Suite</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gemoc.executionframework.event.testsuite.impl.TestSuiteImpl
		 * @see org.eclipse.gemoc.executionframework.event.testsuite.impl.TestsuitePackageImpl#getTestSuite()
		 * @generated
		 */
		EClass TEST_SUITE = eINSTANCE.getTestSuite();

		/**
		 * The meta object literal for the '<em><b>Test Cases</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TEST_SUITE__TEST_CASES = eINSTANCE.getTestSuite_TestCases();

		/**
		 * The meta object literal for the '<em><b>Storage</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TEST_SUITE__STORAGE = eINSTANCE.getTestSuite_Storage();

		/**
		 * The meta object literal for the '{@link org.eclipse.gemoc.executionframework.event.testsuite.impl.TestCaseImpl <em>Test Case</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gemoc.executionframework.event.testsuite.impl.TestCaseImpl
		 * @see org.eclipse.gemoc.executionframework.event.testsuite.impl.TestsuitePackageImpl#getTestCase()
		 * @generated
		 */
		EClass TEST_CASE = eINSTANCE.getTestCase();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEST_CASE__NAME = eINSTANCE.getTestCase_Name();

		/**
		 * The meta object literal for the '<em><b>Model</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TEST_CASE__MODEL = eINSTANCE.getTestCase_Model();

		/**
		 * The meta object literal for the '<em><b>Expected Trace</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEST_CASE__EXPECTED_TRACE = eINSTANCE.getTestCase_ExpectedTrace();

		/**
		 * The meta object literal for the '<em><b>Scenario</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TEST_CASE__SCENARIO = eINSTANCE.getTestCase_Scenario();

		/**
		 * The meta object literal for the '{@link org.eclipse.gemoc.executionframework.event.testsuite.impl.TestSuiteReportImpl <em>Test Suite Report</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gemoc.executionframework.event.testsuite.impl.TestSuiteReportImpl
		 * @see org.eclipse.gemoc.executionframework.event.testsuite.impl.TestsuitePackageImpl#getTestSuiteReport()
		 * @generated
		 */
		EClass TEST_SUITE_REPORT = eINSTANCE.getTestSuiteReport();

		/**
		 * The meta object literal for the '<em><b>Test Case Reports</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TEST_SUITE_REPORT__TEST_CASE_REPORTS = eINSTANCE.getTestSuiteReport_TestCaseReports();

		/**
		 * The meta object literal for the '{@link org.eclipse.gemoc.executionframework.event.testsuite.impl.TestCaseReportImpl <em>Test Case Report</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gemoc.executionframework.event.testsuite.impl.TestCaseReportImpl
		 * @see org.eclipse.gemoc.executionframework.event.testsuite.impl.TestsuitePackageImpl#getTestCaseReport()
		 * @generated
		 */
		EClass TEST_CASE_REPORT = eINSTANCE.getTestCaseReport();

		/**
		 * The meta object literal for the '<em><b>Test Case</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TEST_CASE_REPORT__TEST_CASE = eINSTANCE.getTestCaseReport_TestCase();

		/**
		 * The meta object literal for the '{@link org.eclipse.gemoc.executionframework.event.testsuite.impl.TestCaseSuccessImpl <em>Test Case Success</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gemoc.executionframework.event.testsuite.impl.TestCaseSuccessImpl
		 * @see org.eclipse.gemoc.executionframework.event.testsuite.impl.TestsuitePackageImpl#getTestCaseSuccess()
		 * @generated
		 */
		EClass TEST_CASE_SUCCESS = eINSTANCE.getTestCaseSuccess();

		/**
		 * The meta object literal for the '{@link org.eclipse.gemoc.executionframework.event.testsuite.impl.TestCaseFailureImpl <em>Test Case Failure</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gemoc.executionframework.event.testsuite.impl.TestCaseFailureImpl
		 * @see org.eclipse.gemoc.executionframework.event.testsuite.impl.TestsuitePackageImpl#getTestCaseFailure()
		 * @generated
		 */
		EClass TEST_CASE_FAILURE = eINSTANCE.getTestCaseFailure();

		/**
		 * The meta object literal for the '<em><b>Trace</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEST_CASE_FAILURE__TRACE = eINSTANCE.getTestCaseFailure_Trace();

		/**
		 * The meta object literal for the '{@link org.eclipse.gemoc.executionframework.event.testsuite.impl.TestCaseErrorImpl <em>Test Case Error</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gemoc.executionframework.event.testsuite.impl.TestCaseErrorImpl
		 * @see org.eclipse.gemoc.executionframework.event.testsuite.impl.TestsuitePackageImpl#getTestCaseError()
		 * @generated
		 */
		EClass TEST_CASE_ERROR = eINSTANCE.getTestCaseError();

	}

} //TestsuitePackage
