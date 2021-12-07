/**
 */
package org.eclipse.gemoc.executionframework.event.testsuite.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.gemoc.executionframework.event.testsuite.TestCase;
import org.eclipse.gemoc.executionframework.event.testsuite.TestCaseReport;
import org.eclipse.gemoc.executionframework.event.testsuite.TestsuitePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Test Case Report</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.gemoc.executionframework.event.testsuite.impl.TestCaseReportImpl#getTestCase <em>Test Case</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class TestCaseReportImpl extends MinimalEObjectImpl.Container implements TestCaseReport {
	/**
	 * The cached value of the '{@link #getTestCase() <em>Test Case</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTestCase()
	 * @generated
	 * @ordered
	 */
	protected TestCase testCase;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TestCaseReportImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TestsuitePackage.Literals.TEST_CASE_REPORT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public TestCase getTestCase() {
		if (testCase != null && testCase.eIsProxy()) {
			InternalEObject oldTestCase = (InternalEObject)testCase;
			testCase = (TestCase)eResolveProxy(oldTestCase);
			if (testCase != oldTestCase) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, TestsuitePackage.TEST_CASE_REPORT__TEST_CASE, oldTestCase, testCase));
			}
		}
		return testCase;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TestCase basicGetTestCase() {
		return testCase;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTestCase(TestCase newTestCase) {
		TestCase oldTestCase = testCase;
		testCase = newTestCase;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TestsuitePackage.TEST_CASE_REPORT__TEST_CASE, oldTestCase, testCase));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case TestsuitePackage.TEST_CASE_REPORT__TEST_CASE:
				if (resolve) return getTestCase();
				return basicGetTestCase();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case TestsuitePackage.TEST_CASE_REPORT__TEST_CASE:
				setTestCase((TestCase)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case TestsuitePackage.TEST_CASE_REPORT__TEST_CASE:
				setTestCase((TestCase)null);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case TestsuitePackage.TEST_CASE_REPORT__TEST_CASE:
				return testCase != null;
		}
		return super.eIsSet(featureID);
	}

} //TestCaseReportImpl
