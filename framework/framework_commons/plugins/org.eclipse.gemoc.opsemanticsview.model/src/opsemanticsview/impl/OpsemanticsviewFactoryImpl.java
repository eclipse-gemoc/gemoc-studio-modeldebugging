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

import opsemanticsview.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class OpsemanticsviewFactoryImpl extends EFactoryImpl implements OpsemanticsviewFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static OpsemanticsviewFactory init() {
		try {
			OpsemanticsviewFactory theOpsemanticsviewFactory = (OpsemanticsviewFactory)EPackage.Registry.INSTANCE.getEFactory(OpsemanticsviewPackage.eNS_URI);
			if (theOpsemanticsviewFactory != null) {
				return theOpsemanticsviewFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new OpsemanticsviewFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OpsemanticsviewFactoryImpl() {
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
			case OpsemanticsviewPackage.OPERATIONAL_SEMANTICS_VIEW: return createOperationalSemanticsView();
			case OpsemanticsviewPackage.RULE: return createRule();
			case OpsemanticsviewPackage.EXECUTION_TO_AS_ENTRY: return createExecutionToASEntry();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OperationalSemanticsView createOperationalSemanticsView() {
		OperationalSemanticsViewImpl operationalSemanticsView = new OperationalSemanticsViewImpl();
		return operationalSemanticsView;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Rule createRule() {
		RuleImpl rule = new RuleImpl();
		return rule;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExecutionToASEntry createExecutionToASEntry() {
		ExecutionToASEntryImpl executionToASEntry = new ExecutionToASEntryImpl();
		return executionToASEntry;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OpsemanticsviewPackage getOpsemanticsviewPackage() {
		return (OpsemanticsviewPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static OpsemanticsviewPackage getPackage() {
		return OpsemanticsviewPackage.eINSTANCE;
	}

} //OpsemanticsviewFactoryImpl
