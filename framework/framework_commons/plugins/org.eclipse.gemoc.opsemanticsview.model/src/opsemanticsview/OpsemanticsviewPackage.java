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
package opsemanticsview;

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
 * @see opsemanticsview.OpsemanticsviewFactory
 * @model kind="package"
 * @generated
 */
public interface OpsemanticsviewPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "opsemanticsview";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://opsemanticsview/1.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "opsemanticsview";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	OpsemanticsviewPackage eINSTANCE = opsemanticsview.impl.OpsemanticsviewPackageImpl.init();

	/**
	 * The meta object id for the '{@link opsemanticsview.impl.OperationalSemanticsViewImpl <em>Operational Semantics View</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see opsemanticsview.impl.OperationalSemanticsViewImpl
	 * @see opsemanticsview.impl.OpsemanticsviewPackageImpl#getOperationalSemanticsView()
	 * @generated
	 */
	int OPERATIONAL_SEMANTICS_VIEW = 0;

	/**
	 * The feature id for the '<em><b>Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATIONAL_SEMANTICS_VIEW__RULES = 0;

	/**
	 * The feature id for the '<em><b>Dynamic Properties</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATIONAL_SEMANTICS_VIEW__DYNAMIC_PROPERTIES = 1;

	/**
	 * The feature id for the '<em><b>Dynamic Classes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATIONAL_SEMANTICS_VIEW__DYNAMIC_CLASSES = 2;

	/**
	 * The feature id for the '<em><b>Execution To ASmapping</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATIONAL_SEMANTICS_VIEW__EXECUTION_TO_ASMAPPING = 3;

	/**
	 * The feature id for the '<em><b>Execution Metamodel</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATIONAL_SEMANTICS_VIEW__EXECUTION_METAMODEL = 4;

	/**
	 * The feature id for the '<em><b>Abstract Syntax</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATIONAL_SEMANTICS_VIEW__ABSTRACT_SYNTAX = 5;

	/**
	 * The number of structural features of the '<em>Operational Semantics View</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATIONAL_SEMANTICS_VIEW_FEATURE_COUNT = 6;

	/**
	 * The number of operations of the '<em>Operational Semantics View</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATIONAL_SEMANTICS_VIEW_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link opsemanticsview.impl.RuleImpl <em>Rule</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see opsemanticsview.impl.RuleImpl
	 * @see opsemanticsview.impl.OpsemanticsviewPackageImpl#getRule()
	 * @generated
	 */
	int RULE = 1;

	/**
	 * The feature id for the '<em><b>Called Rules</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE__CALLED_RULES = 0;

	/**
	 * The feature id for the '<em><b>Operation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE__OPERATION = 1;

	/**
	 * The feature id for the '<em><b>Step Rule</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE__STEP_RULE = 2;

	/**
	 * The feature id for the '<em><b>Overriden By</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE__OVERRIDEN_BY = 3;

	/**
	 * The feature id for the '<em><b>Overrides</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE__OVERRIDES = 4;

	/**
	 * The feature id for the '<em><b>Containing Class</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE__CONTAINING_CLASS = 5;

	/**
	 * The feature id for the '<em><b>Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE__ABSTRACT = 6;

	/**
	 * The feature id for the '<em><b>Main</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE__MAIN = 7;

	/**
	 * The number of structural features of the '<em>Rule</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE_FEATURE_COUNT = 8;

	/**
	 * The number of operations of the '<em>Rule</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link opsemanticsview.impl.ExecutionToASEntryImpl <em>Execution To AS Entry</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see opsemanticsview.impl.ExecutionToASEntryImpl
	 * @see opsemanticsview.impl.OpsemanticsviewPackageImpl#getExecutionToASEntry()
	 * @generated
	 */
	int EXECUTION_TO_AS_ENTRY = 2;

	/**
	 * The feature id for the '<em><b>Execution Class</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTION_TO_AS_ENTRY__EXECUTION_CLASS = 0;

	/**
	 * The feature id for the '<em><b>ASclass</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTION_TO_AS_ENTRY__ASCLASS = 1;

	/**
	 * The number of structural features of the '<em>Execution To AS Entry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTION_TO_AS_ENTRY_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Execution To AS Entry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTION_TO_AS_ENTRY_OPERATION_COUNT = 0;


	/**
	 * Returns the meta object for class '{@link opsemanticsview.OperationalSemanticsView <em>Operational Semantics View</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Operational Semantics View</em>'.
	 * @see opsemanticsview.OperationalSemanticsView
	 * @generated
	 */
	EClass getOperationalSemanticsView();

	/**
	 * Returns the meta object for the containment reference list '{@link opsemanticsview.OperationalSemanticsView#getRules <em>Rules</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Rules</em>'.
	 * @see opsemanticsview.OperationalSemanticsView#getRules()
	 * @see #getOperationalSemanticsView()
	 * @generated
	 */
	EReference getOperationalSemanticsView_Rules();

	/**
	 * Returns the meta object for the reference list '{@link opsemanticsview.OperationalSemanticsView#getDynamicProperties <em>Dynamic Properties</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Dynamic Properties</em>'.
	 * @see opsemanticsview.OperationalSemanticsView#getDynamicProperties()
	 * @see #getOperationalSemanticsView()
	 * @generated
	 */
	EReference getOperationalSemanticsView_DynamicProperties();

	/**
	 * Returns the meta object for the reference list '{@link opsemanticsview.OperationalSemanticsView#getDynamicClasses <em>Dynamic Classes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Dynamic Classes</em>'.
	 * @see opsemanticsview.OperationalSemanticsView#getDynamicClasses()
	 * @see #getOperationalSemanticsView()
	 * @generated
	 */
	EReference getOperationalSemanticsView_DynamicClasses();

	/**
	 * Returns the meta object for the containment reference list '{@link opsemanticsview.OperationalSemanticsView#getExecutionToASmapping <em>Execution To ASmapping</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Execution To ASmapping</em>'.
	 * @see opsemanticsview.OperationalSemanticsView#getExecutionToASmapping()
	 * @see #getOperationalSemanticsView()
	 * @generated
	 */
	EReference getOperationalSemanticsView_ExecutionToASmapping();

	/**
	 * Returns the meta object for the reference '{@link opsemanticsview.OperationalSemanticsView#getExecutionMetamodel <em>Execution Metamodel</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Execution Metamodel</em>'.
	 * @see opsemanticsview.OperationalSemanticsView#getExecutionMetamodel()
	 * @see #getOperationalSemanticsView()
	 * @generated
	 */
	EReference getOperationalSemanticsView_ExecutionMetamodel();

	/**
	 * Returns the meta object for the reference '{@link opsemanticsview.OperationalSemanticsView#getAbstractSyntax <em>Abstract Syntax</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Abstract Syntax</em>'.
	 * @see opsemanticsview.OperationalSemanticsView#getAbstractSyntax()
	 * @see #getOperationalSemanticsView()
	 * @generated
	 */
	EReference getOperationalSemanticsView_AbstractSyntax();

	/**
	 * Returns the meta object for class '{@link opsemanticsview.Rule <em>Rule</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Rule</em>'.
	 * @see opsemanticsview.Rule
	 * @generated
	 */
	EClass getRule();

	/**
	 * Returns the meta object for the reference list '{@link opsemanticsview.Rule#getCalledRules <em>Called Rules</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Called Rules</em>'.
	 * @see opsemanticsview.Rule#getCalledRules()
	 * @see #getRule()
	 * @generated
	 */
	EReference getRule_CalledRules();

	/**
	 * Returns the meta object for the containment reference '{@link opsemanticsview.Rule#getOperation <em>Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Operation</em>'.
	 * @see opsemanticsview.Rule#getOperation()
	 * @see #getRule()
	 * @generated
	 */
	EReference getRule_Operation();

	/**
	 * Returns the meta object for the attribute '{@link opsemanticsview.Rule#isStepRule <em>Step Rule</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Step Rule</em>'.
	 * @see opsemanticsview.Rule#isStepRule()
	 * @see #getRule()
	 * @generated
	 */
	EAttribute getRule_StepRule();

	/**
	 * Returns the meta object for the reference list '{@link opsemanticsview.Rule#getOverridenBy <em>Overriden By</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Overriden By</em>'.
	 * @see opsemanticsview.Rule#getOverridenBy()
	 * @see #getRule()
	 * @generated
	 */
	EReference getRule_OverridenBy();

	/**
	 * Returns the meta object for the reference '{@link opsemanticsview.Rule#getOverrides <em>Overrides</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Overrides</em>'.
	 * @see opsemanticsview.Rule#getOverrides()
	 * @see #getRule()
	 * @generated
	 */
	EReference getRule_Overrides();

	/**
	 * Returns the meta object for the reference '{@link opsemanticsview.Rule#getContainingClass <em>Containing Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Containing Class</em>'.
	 * @see opsemanticsview.Rule#getContainingClass()
	 * @see #getRule()
	 * @generated
	 */
	EReference getRule_ContainingClass();

	/**
	 * Returns the meta object for the attribute '{@link opsemanticsview.Rule#isAbstract <em>Abstract</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Abstract</em>'.
	 * @see opsemanticsview.Rule#isAbstract()
	 * @see #getRule()
	 * @generated
	 */
	EAttribute getRule_Abstract();

	/**
	 * Returns the meta object for the attribute '{@link opsemanticsview.Rule#isMain <em>Main</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Main</em>'.
	 * @see opsemanticsview.Rule#isMain()
	 * @see #getRule()
	 * @generated
	 */
	EAttribute getRule_Main();

	/**
	 * Returns the meta object for class '{@link opsemanticsview.ExecutionToASEntry <em>Execution To AS Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Execution To AS Entry</em>'.
	 * @see opsemanticsview.ExecutionToASEntry
	 * @generated
	 */
	EClass getExecutionToASEntry();

	/**
	 * Returns the meta object for the reference '{@link opsemanticsview.ExecutionToASEntry#getExecutionClass <em>Execution Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Execution Class</em>'.
	 * @see opsemanticsview.ExecutionToASEntry#getExecutionClass()
	 * @see #getExecutionToASEntry()
	 * @generated
	 */
	EReference getExecutionToASEntry_ExecutionClass();

	/**
	 * Returns the meta object for the reference '{@link opsemanticsview.ExecutionToASEntry#getASclass <em>ASclass</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>ASclass</em>'.
	 * @see opsemanticsview.ExecutionToASEntry#getASclass()
	 * @see #getExecutionToASEntry()
	 * @generated
	 */
	EReference getExecutionToASEntry_ASclass();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	OpsemanticsviewFactory getOpsemanticsviewFactory();

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
		 * The meta object literal for the '{@link opsemanticsview.impl.OperationalSemanticsViewImpl <em>Operational Semantics View</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see opsemanticsview.impl.OperationalSemanticsViewImpl
		 * @see opsemanticsview.impl.OpsemanticsviewPackageImpl#getOperationalSemanticsView()
		 * @generated
		 */
		EClass OPERATIONAL_SEMANTICS_VIEW = eINSTANCE.getOperationalSemanticsView();

		/**
		 * The meta object literal for the '<em><b>Rules</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OPERATIONAL_SEMANTICS_VIEW__RULES = eINSTANCE.getOperationalSemanticsView_Rules();

		/**
		 * The meta object literal for the '<em><b>Dynamic Properties</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OPERATIONAL_SEMANTICS_VIEW__DYNAMIC_PROPERTIES = eINSTANCE.getOperationalSemanticsView_DynamicProperties();

		/**
		 * The meta object literal for the '<em><b>Dynamic Classes</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OPERATIONAL_SEMANTICS_VIEW__DYNAMIC_CLASSES = eINSTANCE.getOperationalSemanticsView_DynamicClasses();

		/**
		 * The meta object literal for the '<em><b>Execution To ASmapping</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OPERATIONAL_SEMANTICS_VIEW__EXECUTION_TO_ASMAPPING = eINSTANCE.getOperationalSemanticsView_ExecutionToASmapping();

		/**
		 * The meta object literal for the '<em><b>Execution Metamodel</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OPERATIONAL_SEMANTICS_VIEW__EXECUTION_METAMODEL = eINSTANCE.getOperationalSemanticsView_ExecutionMetamodel();

		/**
		 * The meta object literal for the '<em><b>Abstract Syntax</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OPERATIONAL_SEMANTICS_VIEW__ABSTRACT_SYNTAX = eINSTANCE.getOperationalSemanticsView_AbstractSyntax();

		/**
		 * The meta object literal for the '{@link opsemanticsview.impl.RuleImpl <em>Rule</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see opsemanticsview.impl.RuleImpl
		 * @see opsemanticsview.impl.OpsemanticsviewPackageImpl#getRule()
		 * @generated
		 */
		EClass RULE = eINSTANCE.getRule();

		/**
		 * The meta object literal for the '<em><b>Called Rules</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RULE__CALLED_RULES = eINSTANCE.getRule_CalledRules();

		/**
		 * The meta object literal for the '<em><b>Operation</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RULE__OPERATION = eINSTANCE.getRule_Operation();

		/**
		 * The meta object literal for the '<em><b>Step Rule</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RULE__STEP_RULE = eINSTANCE.getRule_StepRule();

		/**
		 * The meta object literal for the '<em><b>Overriden By</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RULE__OVERRIDEN_BY = eINSTANCE.getRule_OverridenBy();

		/**
		 * The meta object literal for the '<em><b>Overrides</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RULE__OVERRIDES = eINSTANCE.getRule_Overrides();

		/**
		 * The meta object literal for the '<em><b>Containing Class</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RULE__CONTAINING_CLASS = eINSTANCE.getRule_ContainingClass();

		/**
		 * The meta object literal for the '<em><b>Abstract</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RULE__ABSTRACT = eINSTANCE.getRule_Abstract();

		/**
		 * The meta object literal for the '<em><b>Main</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RULE__MAIN = eINSTANCE.getRule_Main();

		/**
		 * The meta object literal for the '{@link opsemanticsview.impl.ExecutionToASEntryImpl <em>Execution To AS Entry</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see opsemanticsview.impl.ExecutionToASEntryImpl
		 * @see opsemanticsview.impl.OpsemanticsviewPackageImpl#getExecutionToASEntry()
		 * @generated
		 */
		EClass EXECUTION_TO_AS_ENTRY = eINSTANCE.getExecutionToASEntry();

		/**
		 * The meta object literal for the '<em><b>Execution Class</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXECUTION_TO_AS_ENTRY__EXECUTION_CLASS = eINSTANCE.getExecutionToASEntry_ExecutionClass();

		/**
		 * The meta object literal for the '<em><b>ASclass</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXECUTION_TO_AS_ENTRY__ASCLASS = eINSTANCE.getExecutionToASEntry_ASclass();

	}

} //OpsemanticsviewPackage
