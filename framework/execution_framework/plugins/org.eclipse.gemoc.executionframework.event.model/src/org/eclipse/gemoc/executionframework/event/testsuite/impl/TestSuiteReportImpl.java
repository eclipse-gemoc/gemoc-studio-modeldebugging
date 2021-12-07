/**
 */
package org.eclipse.gemoc.executionframework.event.testsuite.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.gemoc.executionframework.event.testsuite.TestCaseReport;
import org.eclipse.gemoc.executionframework.event.testsuite.TestSuiteReport;
import org.eclipse.gemoc.executionframework.event.testsuite.TestsuitePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Test Suite Report</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.gemoc.executionframework.event.testsuite.impl.TestSuiteReportImpl#getTestCaseReports <em>Test Case Reports</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TestSuiteReportImpl extends MinimalEObjectImpl.Container implements TestSuiteReport {
	/**
	 * The cached value of the '{@link #getTestCaseReports() <em>Test Case Reports</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTestCaseReports()
	 * @generated
	 * @ordered
	 */
	protected EList<TestCaseReport> testCaseReports;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TestSuiteReportImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TestsuitePackage.Literals.TEST_SUITE_REPORT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<TestCaseReport> getTestCaseReports() {
		if (testCaseReports == null) {
			testCaseReports = new EObjectContainmentEList<TestCaseReport>(TestCaseReport.class, this, TestsuitePackage.TEST_SUITE_REPORT__TEST_CASE_REPORTS);
		}
		return testCaseReports;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case TestsuitePackage.TEST_SUITE_REPORT__TEST_CASE_REPORTS:
				return ((InternalEList<?>)getTestCaseReports()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case TestsuitePackage.TEST_SUITE_REPORT__TEST_CASE_REPORTS:
				return getTestCaseReports();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case TestsuitePackage.TEST_SUITE_REPORT__TEST_CASE_REPORTS:
				getTestCaseReports().clear();
				getTestCaseReports().addAll((Collection<? extends TestCaseReport>)newValue);
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
			case TestsuitePackage.TEST_SUITE_REPORT__TEST_CASE_REPORTS:
				getTestCaseReports().clear();
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
			case TestsuitePackage.TEST_SUITE_REPORT__TEST_CASE_REPORTS:
				return testCaseReports != null && !testCaseReports.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //TestSuiteReportImpl
