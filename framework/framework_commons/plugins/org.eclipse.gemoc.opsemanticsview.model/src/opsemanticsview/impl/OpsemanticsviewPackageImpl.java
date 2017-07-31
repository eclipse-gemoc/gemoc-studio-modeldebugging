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

import opsemanticsview.ExecutionToASEntry;
import opsemanticsview.OperationalSemanticsView;
import opsemanticsview.OpsemanticsviewFactory;
import opsemanticsview.OpsemanticsviewPackage;
import opsemanticsview.Rule;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class OpsemanticsviewPackageImpl extends EPackageImpl implements OpsemanticsviewPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass operationalSemanticsViewEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass ruleEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass executionToASEntryEClass = null;

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
	 * @see opsemanticsview.OpsemanticsviewPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private OpsemanticsviewPackageImpl() {
		super(eNS_URI, OpsemanticsviewFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link OpsemanticsviewPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static OpsemanticsviewPackage init() {
		if (isInited) return (OpsemanticsviewPackage)EPackage.Registry.INSTANCE.getEPackage(OpsemanticsviewPackage.eNS_URI);

		// Obtain or create and register package
		OpsemanticsviewPackageImpl theOpsemanticsviewPackage = (OpsemanticsviewPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof OpsemanticsviewPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new OpsemanticsviewPackageImpl());

		isInited = true;

		// Create package meta-data objects
		theOpsemanticsviewPackage.createPackageContents();

		// Initialize created meta-data
		theOpsemanticsviewPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theOpsemanticsviewPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(OpsemanticsviewPackage.eNS_URI, theOpsemanticsviewPackage);
		return theOpsemanticsviewPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getOperationalSemanticsView() {
		return operationalSemanticsViewEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOperationalSemanticsView_Rules() {
		return (EReference)operationalSemanticsViewEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOperationalSemanticsView_DynamicProperties() {
		return (EReference)operationalSemanticsViewEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOperationalSemanticsView_DynamicClasses() {
		return (EReference)operationalSemanticsViewEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOperationalSemanticsView_ExecutionToASmapping() {
		return (EReference)operationalSemanticsViewEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOperationalSemanticsView_ExecutionMetamodel() {
		return (EReference)operationalSemanticsViewEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOperationalSemanticsView_AbstractSyntax() {
		return (EReference)operationalSemanticsViewEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRule() {
		return ruleEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRule_CalledRules() {
		return (EReference)ruleEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRule_Operation() {
		return (EReference)ruleEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRule_StepRule() {
		return (EAttribute)ruleEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRule_OverridenBy() {
		return (EReference)ruleEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRule_Overrides() {
		return (EReference)ruleEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRule_ContainingClass() {
		return (EReference)ruleEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRule_Abstract() {
		return (EAttribute)ruleEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRule_Main() {
		return (EAttribute)ruleEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getExecutionToASEntry() {
		return executionToASEntryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getExecutionToASEntry_ExecutionClass() {
		return (EReference)executionToASEntryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getExecutionToASEntry_ASclass() {
		return (EReference)executionToASEntryEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OpsemanticsviewFactory getOpsemanticsviewFactory() {
		return (OpsemanticsviewFactory)getEFactoryInstance();
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
		operationalSemanticsViewEClass = createEClass(OPERATIONAL_SEMANTICS_VIEW);
		createEReference(operationalSemanticsViewEClass, OPERATIONAL_SEMANTICS_VIEW__RULES);
		createEReference(operationalSemanticsViewEClass, OPERATIONAL_SEMANTICS_VIEW__DYNAMIC_PROPERTIES);
		createEReference(operationalSemanticsViewEClass, OPERATIONAL_SEMANTICS_VIEW__DYNAMIC_CLASSES);
		createEReference(operationalSemanticsViewEClass, OPERATIONAL_SEMANTICS_VIEW__EXECUTION_TO_ASMAPPING);
		createEReference(operationalSemanticsViewEClass, OPERATIONAL_SEMANTICS_VIEW__EXECUTION_METAMODEL);
		createEReference(operationalSemanticsViewEClass, OPERATIONAL_SEMANTICS_VIEW__ABSTRACT_SYNTAX);

		ruleEClass = createEClass(RULE);
		createEReference(ruleEClass, RULE__CALLED_RULES);
		createEReference(ruleEClass, RULE__OPERATION);
		createEAttribute(ruleEClass, RULE__STEP_RULE);
		createEReference(ruleEClass, RULE__OVERRIDEN_BY);
		createEReference(ruleEClass, RULE__OVERRIDES);
		createEReference(ruleEClass, RULE__CONTAINING_CLASS);
		createEAttribute(ruleEClass, RULE__ABSTRACT);
		createEAttribute(ruleEClass, RULE__MAIN);

		executionToASEntryEClass = createEClass(EXECUTION_TO_AS_ENTRY);
		createEReference(executionToASEntryEClass, EXECUTION_TO_AS_ENTRY__EXECUTION_CLASS);
		createEReference(executionToASEntryEClass, EXECUTION_TO_AS_ENTRY__ASCLASS);
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

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes

		// Initialize classes, features, and operations; add parameters
		initEClass(operationalSemanticsViewEClass, OperationalSemanticsView.class, "OperationalSemanticsView", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getOperationalSemanticsView_Rules(), this.getRule(), null, "rules", null, 0, -1, OperationalSemanticsView.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getOperationalSemanticsView_DynamicProperties(), ecorePackage.getEStructuralFeature(), null, "dynamicProperties", null, 0, -1, OperationalSemanticsView.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getOperationalSemanticsView_DynamicClasses(), ecorePackage.getEClass(), null, "dynamicClasses", null, 0, -1, OperationalSemanticsView.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getOperationalSemanticsView_ExecutionToASmapping(), this.getExecutionToASEntry(), null, "executionToASmapping", null, 0, -1, OperationalSemanticsView.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getOperationalSemanticsView_ExecutionMetamodel(), ecorePackage.getEPackage(), null, "executionMetamodel", null, 1, 1, OperationalSemanticsView.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getOperationalSemanticsView_AbstractSyntax(), ecorePackage.getEPackage(), null, "abstractSyntax", null, 0, 1, OperationalSemanticsView.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(ruleEClass, Rule.class, "Rule", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getRule_CalledRules(), this.getRule(), null, "calledRules", null, 0, -1, Rule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getRule_Operation(), ecorePackage.getEOperation(), null, "operation", null, 1, 1, Rule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRule_StepRule(), ecorePackage.getEBoolean(), "stepRule", null, 1, 1, Rule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getRule_OverridenBy(), this.getRule(), this.getRule_Overrides(), "overridenBy", null, 0, -1, Rule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getRule_Overrides(), this.getRule(), this.getRule_OverridenBy(), "overrides", null, 0, 1, Rule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getRule_ContainingClass(), ecorePackage.getEClass(), null, "containingClass", null, 0, 1, Rule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRule_Abstract(), ecorePackage.getEBoolean(), "abstract", null, 1, 1, Rule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRule_Main(), ecorePackage.getEBoolean(), "main", null, 0, 1, Rule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(executionToASEntryEClass, ExecutionToASEntry.class, "ExecutionToASEntry", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getExecutionToASEntry_ExecutionClass(), ecorePackage.getEClass(), null, "executionClass", null, 1, 1, ExecutionToASEntry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getExecutionToASEntry_ASclass(), ecorePackage.getEClass(), null, "ASclass", null, 1, 1, ExecutionToASEntry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);
	}

} //OpsemanticsviewPackageImpl
