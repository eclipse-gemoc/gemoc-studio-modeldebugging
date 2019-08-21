/**
 */
package org.eclipse.gemoc.executionframework.event.testsuite.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.gemoc.executionframework.event.testsuite.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.gemoc.executionframework.event.testsuite.TestsuitePackage
 * @generated
 */
public class TestsuiteAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static TestsuitePackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TestsuiteAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = TestsuitePackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TestsuiteSwitch<Adapter> modelSwitch =
		new TestsuiteSwitch<Adapter>() {
			@Override
			public Adapter caseTestSuite(TestSuite object) {
				return createTestSuiteAdapter();
			}
			@Override
			public Adapter caseTestCase(TestCase object) {
				return createTestCaseAdapter();
			}
			@Override
			public Adapter caseTestSuiteReport(TestSuiteReport object) {
				return createTestSuiteReportAdapter();
			}
			@Override
			public Adapter caseTestCaseReport(TestCaseReport object) {
				return createTestCaseReportAdapter();
			}
			@Override
			public Adapter caseTestCaseSuccess(TestCaseSuccess object) {
				return createTestCaseSuccessAdapter();
			}
			@Override
			public Adapter caseTestCaseFailure(TestCaseFailure object) {
				return createTestCaseFailureAdapter();
			}
			@Override
			public Adapter caseTestCaseError(TestCaseError object) {
				return createTestCaseErrorAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gemoc.executionframework.event.testsuite.TestSuite <em>Test Suite</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gemoc.executionframework.event.testsuite.TestSuite
	 * @generated
	 */
	public Adapter createTestSuiteAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gemoc.executionframework.event.testsuite.TestCase <em>Test Case</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gemoc.executionframework.event.testsuite.TestCase
	 * @generated
	 */
	public Adapter createTestCaseAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gemoc.executionframework.event.testsuite.TestSuiteReport <em>Test Suite Report</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gemoc.executionframework.event.testsuite.TestSuiteReport
	 * @generated
	 */
	public Adapter createTestSuiteReportAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gemoc.executionframework.event.testsuite.TestCaseReport <em>Test Case Report</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gemoc.executionframework.event.testsuite.TestCaseReport
	 * @generated
	 */
	public Adapter createTestCaseReportAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gemoc.executionframework.event.testsuite.TestCaseSuccess <em>Test Case Success</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gemoc.executionframework.event.testsuite.TestCaseSuccess
	 * @generated
	 */
	public Adapter createTestCaseSuccessAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gemoc.executionframework.event.testsuite.TestCaseFailure <em>Test Case Failure</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gemoc.executionframework.event.testsuite.TestCaseFailure
	 * @generated
	 */
	public Adapter createTestCaseFailureAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gemoc.executionframework.event.testsuite.TestCaseError <em>Test Case Error</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gemoc.executionframework.event.testsuite.TestCaseError
	 * @generated
	 */
	public Adapter createTestCaseErrorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //TestsuiteAdapterFactory
