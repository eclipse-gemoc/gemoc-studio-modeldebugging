/**
 */
package org.eclipse.gemoc.executionframework.event.testsuite.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.gemoc.executionframework.behavioralinterface.behavioralInterface.BehavioralInterfacePackage;

import org.eclipse.gemoc.executionframework.event.model.event.EventPackage;

import org.eclipse.gemoc.executionframework.event.testsuite.TestCase;
import org.eclipse.gemoc.executionframework.event.testsuite.TestCaseError;
import org.eclipse.gemoc.executionframework.event.testsuite.TestCaseFailure;
import org.eclipse.gemoc.executionframework.event.testsuite.TestCaseReport;
import org.eclipse.gemoc.executionframework.event.testsuite.TestCaseSuccess;
import org.eclipse.gemoc.executionframework.event.testsuite.TestSuite;
import org.eclipse.gemoc.executionframework.event.testsuite.TestSuiteReport;
import org.eclipse.gemoc.executionframework.event.testsuite.TestsuiteFactory;
import org.eclipse.gemoc.executionframework.event.testsuite.TestsuitePackage;

import org.eclipse.gemoc.executionframework.value.model.value.ValuePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class TestsuitePackageImpl extends EPackageImpl implements TestsuitePackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass testSuiteEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass testCaseEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass testSuiteReportEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass testCaseReportEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass testCaseSuccessEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass testCaseFailureEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass testCaseErrorEClass = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.eclipse.gemoc.executionframework.event.testsuite.TestsuitePackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private TestsuitePackageImpl() {
		super(eNS_URI, TestsuiteFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 *
	 * <p>This method is used to initialize {@link TestsuitePackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static TestsuitePackage init() {
		if (isInited) return (TestsuitePackage)EPackage.Registry.INSTANCE.getEPackage(TestsuitePackage.eNS_URI);

		// Obtain or create and register package
		Object registeredTestsuitePackage = EPackage.Registry.INSTANCE.get(eNS_URI);
		TestsuitePackageImpl theTestsuitePackage = registeredTestsuitePackage instanceof TestsuitePackageImpl ? (TestsuitePackageImpl)registeredTestsuitePackage : new TestsuitePackageImpl();

		isInited = true;

		// Initialize simple dependencies
		EventPackage.eINSTANCE.eClass();
		BehavioralInterfacePackage.eINSTANCE.eClass();
		ValuePackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theTestsuitePackage.createPackageContents();

		// Initialize created meta-data
		theTestsuitePackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theTestsuitePackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(TestsuitePackage.eNS_URI, theTestsuitePackage);
		return theTestsuitePackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTestSuite() {
		return testSuiteEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTestSuite_TestCases() {
		return (EReference)testSuiteEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTestSuite_Storage() {
		return (EReference)testSuiteEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTestCase() {
		return testCaseEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTestCase_Name() {
		return (EAttribute)testCaseEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTestCase_Model() {
		return (EReference)testCaseEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTestCase_ExpectedTrace() {
		return (EAttribute)testCaseEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTestCase_Scenario() {
		return (EReference)testCaseEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTestSuiteReport() {
		return testSuiteReportEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTestSuiteReport_TestCaseReports() {
		return (EReference)testSuiteReportEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTestCaseReport() {
		return testCaseReportEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTestCaseReport_TestCase() {
		return (EReference)testCaseReportEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTestCaseSuccess() {
		return testCaseSuccessEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTestCaseFailure() {
		return testCaseFailureEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTestCaseFailure_Trace() {
		return (EAttribute)testCaseFailureEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTestCaseError() {
		return testCaseErrorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TestsuiteFactory getTestsuiteFactory() {
		return (TestsuiteFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		testSuiteEClass = createEClass(TEST_SUITE);
		createEReference(testSuiteEClass, TEST_SUITE__TEST_CASES);
		createEReference(testSuiteEClass, TEST_SUITE__STORAGE);

		testCaseEClass = createEClass(TEST_CASE);
		createEAttribute(testCaseEClass, TEST_CASE__NAME);
		createEReference(testCaseEClass, TEST_CASE__MODEL);
		createEAttribute(testCaseEClass, TEST_CASE__EXPECTED_TRACE);
		createEReference(testCaseEClass, TEST_CASE__SCENARIO);

		testSuiteReportEClass = createEClass(TEST_SUITE_REPORT);
		createEReference(testSuiteReportEClass, TEST_SUITE_REPORT__TEST_CASE_REPORTS);

		testCaseReportEClass = createEClass(TEST_CASE_REPORT);
		createEReference(testCaseReportEClass, TEST_CASE_REPORT__TEST_CASE);

		testCaseSuccessEClass = createEClass(TEST_CASE_SUCCESS);

		testCaseFailureEClass = createEClass(TEST_CASE_FAILURE);
		createEAttribute(testCaseFailureEClass, TEST_CASE_FAILURE__TRACE);

		testCaseErrorEClass = createEClass(TEST_CASE_ERROR);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		EventPackage theEventPackage = (EventPackage)EPackage.Registry.INSTANCE.getEPackage(EventPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		testCaseSuccessEClass.getESuperTypes().add(this.getTestCaseReport());
		testCaseFailureEClass.getESuperTypes().add(this.getTestCaseReport());
		testCaseErrorEClass.getESuperTypes().add(this.getTestCaseReport());

		// Initialize classes, features, and operations; add parameters
		initEClass(testSuiteEClass, TestSuite.class, "TestSuite", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTestSuite_TestCases(), this.getTestCase(), null, "testCases", null, 0, -1, TestSuite.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTestSuite_Storage(), ecorePackage.getEObject(), null, "storage", null, 0, -1, TestSuite.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(testCaseEClass, TestCase.class, "TestCase", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTestCase_Name(), ecorePackage.getEString(), "name", null, 0, 1, TestCase.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTestCase_Model(), ecorePackage.getEObject(), null, "model", null, 0, 1, TestCase.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTestCase_ExpectedTrace(), ecorePackage.getEString(), "expectedTrace", null, 0, 1, TestCase.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTestCase_Scenario(), theEventPackage.getEventOccurrence(), null, "scenario", null, 0, -1, TestCase.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(testSuiteReportEClass, TestSuiteReport.class, "TestSuiteReport", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTestSuiteReport_TestCaseReports(), this.getTestCaseReport(), null, "testCaseReports", null, 0, -1, TestSuiteReport.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(testCaseReportEClass, TestCaseReport.class, "TestCaseReport", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTestCaseReport_TestCase(), this.getTestCase(), null, "testCase", null, 0, 1, TestCaseReport.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(testCaseSuccessEClass, TestCaseSuccess.class, "TestCaseSuccess", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(testCaseFailureEClass, TestCaseFailure.class, "TestCaseFailure", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTestCaseFailure_Trace(), ecorePackage.getEString(), "trace", null, 0, 1, TestCaseFailure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(testCaseErrorEClass, TestCaseError.class, "TestCaseError", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		// Create resource
		createResource(eNS_URI);
	}

} //TestsuitePackageImpl
