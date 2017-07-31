/*******************************************************************************
 * Copyright (c) 2017 Inria and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Inria - initial API and implementation
 *******************************************************************************/
/**
 */
package opsemanticsview.impl;

import java.util.Collection;

import opsemanticsview.OpsemanticsviewPackage;
import opsemanticsview.Rule;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Rule</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link opsemanticsview.impl.RuleImpl#getCalledRules <em>Called Rules</em>}</li>
 *   <li>{@link opsemanticsview.impl.RuleImpl#getOperation <em>Operation</em>}</li>
 *   <li>{@link opsemanticsview.impl.RuleImpl#isStepRule <em>Step Rule</em>}</li>
 *   <li>{@link opsemanticsview.impl.RuleImpl#getOverridenBy <em>Overriden By</em>}</li>
 *   <li>{@link opsemanticsview.impl.RuleImpl#getOverrides <em>Overrides</em>}</li>
 *   <li>{@link opsemanticsview.impl.RuleImpl#getContainingClass <em>Containing Class</em>}</li>
 *   <li>{@link opsemanticsview.impl.RuleImpl#isAbstract <em>Abstract</em>}</li>
 *   <li>{@link opsemanticsview.impl.RuleImpl#isMain <em>Main</em>}</li>
 * </ul>
 *
 * @generated
 */
public class RuleImpl extends MinimalEObjectImpl.Container implements Rule {
	/**
	 * The cached value of the '{@link #getCalledRules() <em>Called Rules</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCalledRules()
	 * @generated
	 * @ordered
	 */
	protected EList<Rule> calledRules;

	/**
	 * The cached value of the '{@link #getOperation() <em>Operation</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOperation()
	 * @generated
	 * @ordered
	 */
	protected EOperation operation;

	/**
	 * The default value of the '{@link #isStepRule() <em>Step Rule</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isStepRule()
	 * @generated
	 * @ordered
	 */
	protected static final boolean STEP_RULE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isStepRule() <em>Step Rule</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isStepRule()
	 * @generated
	 * @ordered
	 */
	protected boolean stepRule = STEP_RULE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getOverridenBy() <em>Overriden By</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOverridenBy()
	 * @generated
	 * @ordered
	 */
	protected EList<Rule> overridenBy;

	/**
	 * The cached value of the '{@link #getOverrides() <em>Overrides</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOverrides()
	 * @generated
	 * @ordered
	 */
	protected Rule overrides;

	/**
	 * The cached value of the '{@link #getContainingClass() <em>Containing Class</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContainingClass()
	 * @generated
	 * @ordered
	 */
	protected EClass containingClass;

	/**
	 * The default value of the '{@link #isAbstract() <em>Abstract</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAbstract()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ABSTRACT_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isAbstract() <em>Abstract</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAbstract()
	 * @generated
	 * @ordered
	 */
	protected boolean abstract_ = ABSTRACT_EDEFAULT;

	/**
	 * The default value of the '{@link #isMain() <em>Main</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isMain()
	 * @generated
	 * @ordered
	 */
	protected static final boolean MAIN_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isMain() <em>Main</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isMain()
	 * @generated
	 * @ordered
	 */
	protected boolean main = MAIN_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RuleImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return OpsemanticsviewPackage.Literals.RULE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Rule> getCalledRules() {
		if (calledRules == null) {
			calledRules = new EObjectResolvingEList<Rule>(Rule.class, this, OpsemanticsviewPackage.RULE__CALLED_RULES);
		}
		return calledRules;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getOperation() {
		return operation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOperation(EOperation newOperation, NotificationChain msgs) {
		EOperation oldOperation = operation;
		operation = newOperation;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, OpsemanticsviewPackage.RULE__OPERATION, oldOperation, newOperation);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOperation(EOperation newOperation) {
		if (newOperation != operation) {
			NotificationChain msgs = null;
			if (operation != null)
				msgs = ((InternalEObject)operation).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - OpsemanticsviewPackage.RULE__OPERATION, null, msgs);
			if (newOperation != null)
				msgs = ((InternalEObject)newOperation).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - OpsemanticsviewPackage.RULE__OPERATION, null, msgs);
			msgs = basicSetOperation(newOperation, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OpsemanticsviewPackage.RULE__OPERATION, newOperation, newOperation));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isStepRule() {
		return stepRule;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStepRule(boolean newStepRule) {
		boolean oldStepRule = stepRule;
		stepRule = newStepRule;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OpsemanticsviewPackage.RULE__STEP_RULE, oldStepRule, stepRule));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Rule> getOverridenBy() {
		if (overridenBy == null) {
			overridenBy = new EObjectWithInverseResolvingEList<Rule>(Rule.class, this, OpsemanticsviewPackage.RULE__OVERRIDEN_BY, OpsemanticsviewPackage.RULE__OVERRIDES);
		}
		return overridenBy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Rule getOverrides() {
		if (overrides != null && overrides.eIsProxy()) {
			InternalEObject oldOverrides = (InternalEObject)overrides;
			overrides = (Rule)eResolveProxy(oldOverrides);
			if (overrides != oldOverrides) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, OpsemanticsviewPackage.RULE__OVERRIDES, oldOverrides, overrides));
			}
		}
		return overrides;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Rule basicGetOverrides() {
		return overrides;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOverrides(Rule newOverrides, NotificationChain msgs) {
		Rule oldOverrides = overrides;
		overrides = newOverrides;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, OpsemanticsviewPackage.RULE__OVERRIDES, oldOverrides, newOverrides);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOverrides(Rule newOverrides) {
		if (newOverrides != overrides) {
			NotificationChain msgs = null;
			if (overrides != null)
				msgs = ((InternalEObject)overrides).eInverseRemove(this, OpsemanticsviewPackage.RULE__OVERRIDEN_BY, Rule.class, msgs);
			if (newOverrides != null)
				msgs = ((InternalEObject)newOverrides).eInverseAdd(this, OpsemanticsviewPackage.RULE__OVERRIDEN_BY, Rule.class, msgs);
			msgs = basicSetOverrides(newOverrides, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OpsemanticsviewPackage.RULE__OVERRIDES, newOverrides, newOverrides));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getContainingClass() {
		if (containingClass != null && containingClass.eIsProxy()) {
			InternalEObject oldContainingClass = (InternalEObject)containingClass;
			containingClass = (EClass)eResolveProxy(oldContainingClass);
			if (containingClass != oldContainingClass) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, OpsemanticsviewPackage.RULE__CONTAINING_CLASS, oldContainingClass, containingClass));
			}
		}
		return containingClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass basicGetContainingClass() {
		return containingClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setContainingClass(EClass newContainingClass) {
		EClass oldContainingClass = containingClass;
		containingClass = newContainingClass;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OpsemanticsviewPackage.RULE__CONTAINING_CLASS, oldContainingClass, containingClass));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isAbstract() {
		return abstract_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAbstract(boolean newAbstract) {
		boolean oldAbstract = abstract_;
		abstract_ = newAbstract;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OpsemanticsviewPackage.RULE__ABSTRACT, oldAbstract, abstract_));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isMain() {
		return main;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMain(boolean newMain) {
		boolean oldMain = main;
		main = newMain;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OpsemanticsviewPackage.RULE__MAIN, oldMain, main));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case OpsemanticsviewPackage.RULE__OVERRIDEN_BY:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOverridenBy()).basicAdd(otherEnd, msgs);
			case OpsemanticsviewPackage.RULE__OVERRIDES:
				if (overrides != null)
					msgs = ((InternalEObject)overrides).eInverseRemove(this, OpsemanticsviewPackage.RULE__OVERRIDEN_BY, Rule.class, msgs);
				return basicSetOverrides((Rule)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case OpsemanticsviewPackage.RULE__OPERATION:
				return basicSetOperation(null, msgs);
			case OpsemanticsviewPackage.RULE__OVERRIDEN_BY:
				return ((InternalEList<?>)getOverridenBy()).basicRemove(otherEnd, msgs);
			case OpsemanticsviewPackage.RULE__OVERRIDES:
				return basicSetOverrides(null, msgs);
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
			case OpsemanticsviewPackage.RULE__CALLED_RULES:
				return getCalledRules();
			case OpsemanticsviewPackage.RULE__OPERATION:
				return getOperation();
			case OpsemanticsviewPackage.RULE__STEP_RULE:
				return isStepRule();
			case OpsemanticsviewPackage.RULE__OVERRIDEN_BY:
				return getOverridenBy();
			case OpsemanticsviewPackage.RULE__OVERRIDES:
				if (resolve) return getOverrides();
				return basicGetOverrides();
			case OpsemanticsviewPackage.RULE__CONTAINING_CLASS:
				if (resolve) return getContainingClass();
				return basicGetContainingClass();
			case OpsemanticsviewPackage.RULE__ABSTRACT:
				return isAbstract();
			case OpsemanticsviewPackage.RULE__MAIN:
				return isMain();
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
			case OpsemanticsviewPackage.RULE__CALLED_RULES:
				getCalledRules().clear();
				getCalledRules().addAll((Collection<? extends Rule>)newValue);
				return;
			case OpsemanticsviewPackage.RULE__OPERATION:
				setOperation((EOperation)newValue);
				return;
			case OpsemanticsviewPackage.RULE__STEP_RULE:
				setStepRule((Boolean)newValue);
				return;
			case OpsemanticsviewPackage.RULE__OVERRIDEN_BY:
				getOverridenBy().clear();
				getOverridenBy().addAll((Collection<? extends Rule>)newValue);
				return;
			case OpsemanticsviewPackage.RULE__OVERRIDES:
				setOverrides((Rule)newValue);
				return;
			case OpsemanticsviewPackage.RULE__CONTAINING_CLASS:
				setContainingClass((EClass)newValue);
				return;
			case OpsemanticsviewPackage.RULE__ABSTRACT:
				setAbstract((Boolean)newValue);
				return;
			case OpsemanticsviewPackage.RULE__MAIN:
				setMain((Boolean)newValue);
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
			case OpsemanticsviewPackage.RULE__CALLED_RULES:
				getCalledRules().clear();
				return;
			case OpsemanticsviewPackage.RULE__OPERATION:
				setOperation((EOperation)null);
				return;
			case OpsemanticsviewPackage.RULE__STEP_RULE:
				setStepRule(STEP_RULE_EDEFAULT);
				return;
			case OpsemanticsviewPackage.RULE__OVERRIDEN_BY:
				getOverridenBy().clear();
				return;
			case OpsemanticsviewPackage.RULE__OVERRIDES:
				setOverrides((Rule)null);
				return;
			case OpsemanticsviewPackage.RULE__CONTAINING_CLASS:
				setContainingClass((EClass)null);
				return;
			case OpsemanticsviewPackage.RULE__ABSTRACT:
				setAbstract(ABSTRACT_EDEFAULT);
				return;
			case OpsemanticsviewPackage.RULE__MAIN:
				setMain(MAIN_EDEFAULT);
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
			case OpsemanticsviewPackage.RULE__CALLED_RULES:
				return calledRules != null && !calledRules.isEmpty();
			case OpsemanticsviewPackage.RULE__OPERATION:
				return operation != null;
			case OpsemanticsviewPackage.RULE__STEP_RULE:
				return stepRule != STEP_RULE_EDEFAULT;
			case OpsemanticsviewPackage.RULE__OVERRIDEN_BY:
				return overridenBy != null && !overridenBy.isEmpty();
			case OpsemanticsviewPackage.RULE__OVERRIDES:
				return overrides != null;
			case OpsemanticsviewPackage.RULE__CONTAINING_CLASS:
				return containingClass != null;
			case OpsemanticsviewPackage.RULE__ABSTRACT:
				return abstract_ != ABSTRACT_EDEFAULT;
			case OpsemanticsviewPackage.RULE__MAIN:
				return main != MAIN_EDEFAULT;
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

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (stepRule: ");
		result.append(stepRule);
		result.append(", abstract: ");
		result.append(abstract_);
		result.append(", main: ");
		result.append(main);
		result.append(')');
		return result.toString();
	}

} //RuleImpl
