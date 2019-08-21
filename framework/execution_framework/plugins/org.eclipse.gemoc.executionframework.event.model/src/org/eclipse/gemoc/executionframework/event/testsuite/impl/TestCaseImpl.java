/**
 */
package org.eclipse.gemoc.executionframework.event.testsuite.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.gemoc.executionframework.event.model.event.EventOccurrence;

import org.eclipse.gemoc.executionframework.event.testsuite.TestCase;
import org.eclipse.gemoc.executionframework.event.testsuite.TestsuitePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Test Case</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.gemoc.executionframework.event.testsuite.impl.TestCaseImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.gemoc.executionframework.event.testsuite.impl.TestCaseImpl#getModel <em>Model</em>}</li>
 *   <li>{@link org.eclipse.gemoc.executionframework.event.testsuite.impl.TestCaseImpl#getExpectedTrace <em>Expected Trace</em>}</li>
 *   <li>{@link org.eclipse.gemoc.executionframework.event.testsuite.impl.TestCaseImpl#getScenario <em>Scenario</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TestCaseImpl extends MinimalEObjectImpl.Container implements TestCase {
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getModel() <em>Model</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getModel()
	 * @generated
	 * @ordered
	 */
	protected EObject model;

	/**
	 * The default value of the '{@link #getExpectedTrace() <em>Expected Trace</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExpectedTrace()
	 * @generated
	 * @ordered
	 */
	protected static final String EXPECTED_TRACE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getExpectedTrace() <em>Expected Trace</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExpectedTrace()
	 * @generated
	 * @ordered
	 */
	protected String expectedTrace = EXPECTED_TRACE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getScenario() <em>Scenario</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getScenario()
	 * @generated
	 * @ordered
	 */
	protected EList<EventOccurrence> scenario;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TestCaseImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TestsuitePackage.Literals.TEST_CASE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TestsuitePackage.TEST_CASE__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EObject getModel() {
		if (model != null && model.eIsProxy()) {
			InternalEObject oldModel = (InternalEObject)model;
			model = eResolveProxy(oldModel);
			if (model != oldModel) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, TestsuitePackage.TEST_CASE__MODEL, oldModel, model));
			}
		}
		return model;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EObject basicGetModel() {
		return model;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setModel(EObject newModel) {
		EObject oldModel = model;
		model = newModel;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TestsuitePackage.TEST_CASE__MODEL, oldModel, model));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getExpectedTrace() {
		return expectedTrace;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExpectedTrace(String newExpectedTrace) {
		String oldExpectedTrace = expectedTrace;
		expectedTrace = newExpectedTrace;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TestsuitePackage.TEST_CASE__EXPECTED_TRACE, oldExpectedTrace, expectedTrace));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<EventOccurrence> getScenario() {
		if (scenario == null) {
			scenario = new EObjectContainmentEList<EventOccurrence>(EventOccurrence.class, this, TestsuitePackage.TEST_CASE__SCENARIO);
		}
		return scenario;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case TestsuitePackage.TEST_CASE__SCENARIO:
				return ((InternalEList<?>)getScenario()).basicRemove(otherEnd, msgs);
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
			case TestsuitePackage.TEST_CASE__NAME:
				return getName();
			case TestsuitePackage.TEST_CASE__MODEL:
				if (resolve) return getModel();
				return basicGetModel();
			case TestsuitePackage.TEST_CASE__EXPECTED_TRACE:
				return getExpectedTrace();
			case TestsuitePackage.TEST_CASE__SCENARIO:
				return getScenario();
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
			case TestsuitePackage.TEST_CASE__NAME:
				setName((String)newValue);
				return;
			case TestsuitePackage.TEST_CASE__MODEL:
				setModel((EObject)newValue);
				return;
			case TestsuitePackage.TEST_CASE__EXPECTED_TRACE:
				setExpectedTrace((String)newValue);
				return;
			case TestsuitePackage.TEST_CASE__SCENARIO:
				getScenario().clear();
				getScenario().addAll((Collection<? extends EventOccurrence>)newValue);
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
			case TestsuitePackage.TEST_CASE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case TestsuitePackage.TEST_CASE__MODEL:
				setModel((EObject)null);
				return;
			case TestsuitePackage.TEST_CASE__EXPECTED_TRACE:
				setExpectedTrace(EXPECTED_TRACE_EDEFAULT);
				return;
			case TestsuitePackage.TEST_CASE__SCENARIO:
				getScenario().clear();
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
			case TestsuitePackage.TEST_CASE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case TestsuitePackage.TEST_CASE__MODEL:
				return model != null;
			case TestsuitePackage.TEST_CASE__EXPECTED_TRACE:
				return EXPECTED_TRACE_EDEFAULT == null ? expectedTrace != null : !EXPECTED_TRACE_EDEFAULT.equals(expectedTrace);
			case TestsuitePackage.TEST_CASE__SCENARIO:
				return scenario != null && !scenario.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuilder result = new StringBuilder(super.toString());
		result.append(" (name: ");
		result.append(name);
		result.append(", expectedTrace: ");
		result.append(expectedTrace);
		result.append(')');
		return result.toString();
	}

} //TestCaseImpl
