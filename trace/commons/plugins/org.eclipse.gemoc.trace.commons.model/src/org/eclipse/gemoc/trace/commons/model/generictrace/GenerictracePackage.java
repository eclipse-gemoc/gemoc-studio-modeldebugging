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
package org.eclipse.gemoc.trace.commons.model.generictrace;

import org.eclipse.gemoc.trace.commons.model.trace.TracePackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EOperation;
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
 * @see org.eclipse.gemoc.trace.commons.model.generictrace.GenerictraceFactory
 * @model kind="package"
 * @generated
 */
public interface GenerictracePackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "generictrace";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.gemoc.org/generic_trace_impl";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "generictrace";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	GenerictracePackage eINSTANCE = org.eclipse.gemoc.trace.commons.model.generictrace.impl.GenerictracePackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.gemoc.trace.commons.model.generictrace.impl.GenericStepImpl <em>Generic Step</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gemoc.trace.commons.model.generictrace.impl.GenericStepImpl
	 * @see org.eclipse.gemoc.trace.commons.model.generictrace.impl.GenerictracePackageImpl#getGenericStep()
	 * @generated
	 */
	int GENERIC_STEP = 15;

	/**
	 * The feature id for the '<em><b>Mseoccurrence</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_STEP__MSEOCCURRENCE = TracePackage.STEP__MSEOCCURRENCE;

	/**
	 * The feature id for the '<em><b>Starting State</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_STEP__STARTING_STATE = TracePackage.STEP__STARTING_STATE;

	/**
	 * The feature id for the '<em><b>Ending State</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_STEP__ENDING_STATE = TracePackage.STEP__ENDING_STATE;

	/**
	 * The number of structural features of the '<em>Generic Step</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_STEP_FEATURE_COUNT = TracePackage.STEP_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Generic Step</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_STEP_OPERATION_COUNT = TracePackage.STEP_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.gemoc.trace.commons.model.generictrace.impl.GenericSequentialStepImpl <em>Generic Sequential Step</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gemoc.trace.commons.model.generictrace.impl.GenericSequentialStepImpl
	 * @see org.eclipse.gemoc.trace.commons.model.generictrace.impl.GenerictracePackageImpl#getGenericSequentialStep()
	 * @generated
	 */
	int GENERIC_SEQUENTIAL_STEP = 0;

	/**
	 * The feature id for the '<em><b>Mseoccurrence</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_SEQUENTIAL_STEP__MSEOCCURRENCE = GENERIC_STEP__MSEOCCURRENCE;

	/**
	 * The feature id for the '<em><b>Starting State</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_SEQUENTIAL_STEP__STARTING_STATE = GENERIC_STEP__STARTING_STATE;

	/**
	 * The feature id for the '<em><b>Ending State</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_SEQUENTIAL_STEP__ENDING_STATE = GENERIC_STEP__ENDING_STATE;

	/**
	 * The feature id for the '<em><b>Sub Steps</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_SEQUENTIAL_STEP__SUB_STEPS = GENERIC_STEP_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Generic Sequential Step</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_SEQUENTIAL_STEP_FEATURE_COUNT = GENERIC_STEP_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Generic Sequential Step</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_SEQUENTIAL_STEP_OPERATION_COUNT = GENERIC_STEP_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.gemoc.trace.commons.model.generictrace.impl.GenericParallelStepImpl <em>Generic Parallel Step</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gemoc.trace.commons.model.generictrace.impl.GenericParallelStepImpl
	 * @see org.eclipse.gemoc.trace.commons.model.generictrace.impl.GenerictracePackageImpl#getGenericParallelStep()
	 * @generated
	 */
	int GENERIC_PARALLEL_STEP = 1;

	/**
	 * The feature id for the '<em><b>Mseoccurrence</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_PARALLEL_STEP__MSEOCCURRENCE = GENERIC_STEP__MSEOCCURRENCE;

	/**
	 * The feature id for the '<em><b>Starting State</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_PARALLEL_STEP__STARTING_STATE = GENERIC_STEP__STARTING_STATE;

	/**
	 * The feature id for the '<em><b>Ending State</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_PARALLEL_STEP__ENDING_STATE = GENERIC_STEP__ENDING_STATE;

	/**
	 * The feature id for the '<em><b>Sub Steps</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_PARALLEL_STEP__SUB_STEPS = GENERIC_STEP_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Generic Parallel Step</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_PARALLEL_STEP_FEATURE_COUNT = GENERIC_STEP_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Generic Parallel Step</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_PARALLEL_STEP_OPERATION_COUNT = GENERIC_STEP_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.gemoc.trace.commons.model.generictrace.impl.GenericSmallStepImpl <em>Generic Small Step</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gemoc.trace.commons.model.generictrace.impl.GenericSmallStepImpl
	 * @see org.eclipse.gemoc.trace.commons.model.generictrace.impl.GenerictracePackageImpl#getGenericSmallStep()
	 * @generated
	 */
	int GENERIC_SMALL_STEP = 2;

	/**
	 * The feature id for the '<em><b>Mseoccurrence</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_SMALL_STEP__MSEOCCURRENCE = GENERIC_STEP__MSEOCCURRENCE;

	/**
	 * The feature id for the '<em><b>Starting State</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_SMALL_STEP__STARTING_STATE = GENERIC_STEP__STARTING_STATE;

	/**
	 * The feature id for the '<em><b>Ending State</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_SMALL_STEP__ENDING_STATE = GENERIC_STEP__ENDING_STATE;

	/**
	 * The feature id for the '<em><b>Footprint</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_SMALL_STEP__FOOTPRINT = GENERIC_STEP_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Generic Small Step</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_SMALL_STEP_FEATURE_COUNT = GENERIC_STEP_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Generic Small Step</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_SMALL_STEP_OPERATION_COUNT = GENERIC_STEP_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.gemoc.trace.commons.model.generictrace.impl.GenericValueImpl <em>Generic Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gemoc.trace.commons.model.generictrace.impl.GenericValueImpl
	 * @see org.eclipse.gemoc.trace.commons.model.generictrace.impl.GenerictracePackageImpl#getGenericValue()
	 * @generated
	 */
	int GENERIC_VALUE = 16;

	/**
	 * The feature id for the '<em><b>States</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_VALUE__STATES = TracePackage.VALUE__STATES;

	/**
	 * The number of structural features of the '<em>Generic Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_VALUE_FEATURE_COUNT = TracePackage.VALUE_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Generic Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_VALUE_OPERATION_COUNT = TracePackage.VALUE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.gemoc.trace.commons.model.generictrace.impl.GenericReferenceValueImpl <em>Generic Reference Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gemoc.trace.commons.model.generictrace.impl.GenericReferenceValueImpl
	 * @see org.eclipse.gemoc.trace.commons.model.generictrace.impl.GenerictracePackageImpl#getGenericReferenceValue()
	 * @generated
	 */
	int GENERIC_REFERENCE_VALUE = 3;

	/**
	 * The feature id for the '<em><b>States</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_REFERENCE_VALUE__STATES = GENERIC_VALUE__STATES;

	/**
	 * The number of structural features of the '<em>Generic Reference Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_REFERENCE_VALUE_FEATURE_COUNT = GENERIC_VALUE_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Generic Reference Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_REFERENCE_VALUE_OPERATION_COUNT = GENERIC_VALUE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.gemoc.trace.commons.model.generictrace.impl.GenericDimensionImpl <em>Generic Dimension</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gemoc.trace.commons.model.generictrace.impl.GenericDimensionImpl
	 * @see org.eclipse.gemoc.trace.commons.model.generictrace.impl.GenerictracePackageImpl#getGenericDimension()
	 * @generated
	 */
	int GENERIC_DIMENSION = 4;

	/**
	 * The feature id for the '<em><b>Values</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_DIMENSION__VALUES = TracePackage.DIMENSION__VALUES;

	/**
	 * The feature id for the '<em><b>Dynamic Property</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_DIMENSION__DYNAMIC_PROPERTY = TracePackage.DIMENSION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Generic Dimension</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_DIMENSION_FEATURE_COUNT = TracePackage.DIMENSION_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Generic Dimension</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_DIMENSION_OPERATION_COUNT = TracePackage.DIMENSION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.gemoc.trace.commons.model.generictrace.impl.GenericTracedObjectImpl <em>Generic Traced Object</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gemoc.trace.commons.model.generictrace.impl.GenericTracedObjectImpl
	 * @see org.eclipse.gemoc.trace.commons.model.generictrace.impl.GenerictracePackageImpl#getGenericTracedObject()
	 * @generated
	 */
	int GENERIC_TRACED_OBJECT = 5;

	/**
	 * The feature id for the '<em><b>Dimensions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_TRACED_OBJECT__DIMENSIONS = TracePackage.TRACED_OBJECT__DIMENSIONS;

	/**
	 * The feature id for the '<em><b>Original Object</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_TRACED_OBJECT__ORIGINAL_OBJECT = TracePackage.TRACED_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>All Dimensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_TRACED_OBJECT__ALL_DIMENSIONS = TracePackage.TRACED_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Generic Traced Object</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_TRACED_OBJECT_FEATURE_COUNT = TracePackage.TRACED_OBJECT_FEATURE_COUNT + 2;

	/**
	 * The operation id for the '<em>Get Dimensions Internal</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_TRACED_OBJECT___GET_DIMENSIONS_INTERNAL = TracePackage.TRACED_OBJECT_OPERATION_COUNT + 0;

	/**
	 * The number of operations of the '<em>Generic Traced Object</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_TRACED_OBJECT_OPERATION_COUNT = TracePackage.TRACED_OBJECT_OPERATION_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.gemoc.trace.commons.model.generictrace.impl.GenericStateImpl <em>Generic State</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gemoc.trace.commons.model.generictrace.impl.GenericStateImpl
	 * @see org.eclipse.gemoc.trace.commons.model.generictrace.impl.GenerictracePackageImpl#getGenericState()
	 * @generated
	 */
	int GENERIC_STATE = 6;

	/**
	 * The feature id for the '<em><b>Started Steps</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_STATE__STARTED_STEPS = TracePackage.STATE__STARTED_STEPS;

	/**
	 * The feature id for the '<em><b>Ended Steps</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_STATE__ENDED_STEPS = TracePackage.STATE__ENDED_STEPS;

	/**
	 * The feature id for the '<em><b>Values</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_STATE__VALUES = TracePackage.STATE__VALUES;

	/**
	 * The number of structural features of the '<em>Generic State</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_STATE_FEATURE_COUNT = TracePackage.STATE_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Generic State</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_STATE_OPERATION_COUNT = TracePackage.STATE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.gemoc.trace.commons.model.generictrace.impl.GenericTraceImpl <em>Generic Trace</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gemoc.trace.commons.model.generictrace.impl.GenericTraceImpl
	 * @see org.eclipse.gemoc.trace.commons.model.generictrace.impl.GenerictracePackageImpl#getGenericTrace()
	 * @generated
	 */
	int GENERIC_TRACE = 7;

	/**
	 * The feature id for the '<em><b>Root Step</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_TRACE__ROOT_STEP = TracePackage.TRACE__ROOT_STEP;

	/**
	 * The feature id for the '<em><b>Traced Objects</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_TRACE__TRACED_OBJECTS = TracePackage.TRACE__TRACED_OBJECTS;

	/**
	 * The feature id for the '<em><b>States</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_TRACE__STATES = TracePackage.TRACE__STATES;

	/**
	 * The feature id for the '<em><b>Launchconfiguration</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_TRACE__LAUNCHCONFIGURATION = TracePackage.TRACE__LAUNCHCONFIGURATION;

	/**
	 * The number of structural features of the '<em>Generic Trace</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_TRACE_FEATURE_COUNT = TracePackage.TRACE_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Generic Trace</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_TRACE_OPERATION_COUNT = TracePackage.TRACE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.gemoc.trace.commons.model.generictrace.impl.GenericAttributeValueImpl <em>Generic Attribute Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gemoc.trace.commons.model.generictrace.impl.GenericAttributeValueImpl
	 * @see org.eclipse.gemoc.trace.commons.model.generictrace.impl.GenerictracePackageImpl#getGenericAttributeValue()
	 * @generated
	 */
	int GENERIC_ATTRIBUTE_VALUE = 8;

	/**
	 * The feature id for the '<em><b>States</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_ATTRIBUTE_VALUE__STATES = GENERIC_VALUE__STATES;

	/**
	 * The number of structural features of the '<em>Generic Attribute Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_ATTRIBUTE_VALUE_FEATURE_COUNT = GENERIC_VALUE_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Generic Attribute Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_ATTRIBUTE_VALUE_OPERATION_COUNT = GENERIC_VALUE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.gemoc.trace.commons.model.generictrace.impl.BooleanAttributeValueImpl <em>Boolean Attribute Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gemoc.trace.commons.model.generictrace.impl.BooleanAttributeValueImpl
	 * @see org.eclipse.gemoc.trace.commons.model.generictrace.impl.GenerictracePackageImpl#getBooleanAttributeValue()
	 * @generated
	 */
	int BOOLEAN_ATTRIBUTE_VALUE = 9;

	/**
	 * The feature id for the '<em><b>States</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_ATTRIBUTE_VALUE__STATES = GENERIC_ATTRIBUTE_VALUE__STATES;

	/**
	 * The feature id for the '<em><b>Attribute Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_ATTRIBUTE_VALUE__ATTRIBUTE_VALUE = GENERIC_ATTRIBUTE_VALUE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Boolean Attribute Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_ATTRIBUTE_VALUE_FEATURE_COUNT = GENERIC_ATTRIBUTE_VALUE_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Boolean Attribute Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_ATTRIBUTE_VALUE_OPERATION_COUNT = GENERIC_ATTRIBUTE_VALUE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.gemoc.trace.commons.model.generictrace.impl.IntegerAttributeValueImpl <em>Integer Attribute Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gemoc.trace.commons.model.generictrace.impl.IntegerAttributeValueImpl
	 * @see org.eclipse.gemoc.trace.commons.model.generictrace.impl.GenerictracePackageImpl#getIntegerAttributeValue()
	 * @generated
	 */
	int INTEGER_ATTRIBUTE_VALUE = 10;

	/**
	 * The feature id for the '<em><b>States</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_ATTRIBUTE_VALUE__STATES = GENERIC_ATTRIBUTE_VALUE__STATES;

	/**
	 * The feature id for the '<em><b>Attribute Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_ATTRIBUTE_VALUE__ATTRIBUTE_VALUE = GENERIC_ATTRIBUTE_VALUE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Integer Attribute Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_ATTRIBUTE_VALUE_FEATURE_COUNT = GENERIC_ATTRIBUTE_VALUE_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Integer Attribute Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_ATTRIBUTE_VALUE_OPERATION_COUNT = GENERIC_ATTRIBUTE_VALUE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.gemoc.trace.commons.model.generictrace.impl.StringAttributeValueImpl <em>String Attribute Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gemoc.trace.commons.model.generictrace.impl.StringAttributeValueImpl
	 * @see org.eclipse.gemoc.trace.commons.model.generictrace.impl.GenerictracePackageImpl#getStringAttributeValue()
	 * @generated
	 */
	int STRING_ATTRIBUTE_VALUE = 11;

	/**
	 * The feature id for the '<em><b>States</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_ATTRIBUTE_VALUE__STATES = GENERIC_ATTRIBUTE_VALUE__STATES;

	/**
	 * The feature id for the '<em><b>Attribute Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_ATTRIBUTE_VALUE__ATTRIBUTE_VALUE = GENERIC_ATTRIBUTE_VALUE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>String Attribute Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_ATTRIBUTE_VALUE_FEATURE_COUNT = GENERIC_ATTRIBUTE_VALUE_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>String Attribute Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_ATTRIBUTE_VALUE_OPERATION_COUNT = GENERIC_ATTRIBUTE_VALUE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.gemoc.trace.commons.model.generictrace.impl.ManyBooleanAttributeValueImpl <em>Many Boolean Attribute Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gemoc.trace.commons.model.generictrace.impl.ManyBooleanAttributeValueImpl
	 * @see org.eclipse.gemoc.trace.commons.model.generictrace.impl.GenerictracePackageImpl#getManyBooleanAttributeValue()
	 * @generated
	 */
	int MANY_BOOLEAN_ATTRIBUTE_VALUE = 12;

	/**
	 * The feature id for the '<em><b>States</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANY_BOOLEAN_ATTRIBUTE_VALUE__STATES = GENERIC_ATTRIBUTE_VALUE__STATES;

	/**
	 * The feature id for the '<em><b>Attribute Value</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANY_BOOLEAN_ATTRIBUTE_VALUE__ATTRIBUTE_VALUE = GENERIC_ATTRIBUTE_VALUE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Many Boolean Attribute Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANY_BOOLEAN_ATTRIBUTE_VALUE_FEATURE_COUNT = GENERIC_ATTRIBUTE_VALUE_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Many Boolean Attribute Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANY_BOOLEAN_ATTRIBUTE_VALUE_OPERATION_COUNT = GENERIC_ATTRIBUTE_VALUE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.gemoc.trace.commons.model.generictrace.impl.ManyIntegerAttributeValueImpl <em>Many Integer Attribute Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gemoc.trace.commons.model.generictrace.impl.ManyIntegerAttributeValueImpl
	 * @see org.eclipse.gemoc.trace.commons.model.generictrace.impl.GenerictracePackageImpl#getManyIntegerAttributeValue()
	 * @generated
	 */
	int MANY_INTEGER_ATTRIBUTE_VALUE = 13;

	/**
	 * The feature id for the '<em><b>States</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANY_INTEGER_ATTRIBUTE_VALUE__STATES = GENERIC_ATTRIBUTE_VALUE__STATES;

	/**
	 * The feature id for the '<em><b>Attribute Value</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANY_INTEGER_ATTRIBUTE_VALUE__ATTRIBUTE_VALUE = GENERIC_ATTRIBUTE_VALUE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Many Integer Attribute Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANY_INTEGER_ATTRIBUTE_VALUE_FEATURE_COUNT = GENERIC_ATTRIBUTE_VALUE_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Many Integer Attribute Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANY_INTEGER_ATTRIBUTE_VALUE_OPERATION_COUNT = GENERIC_ATTRIBUTE_VALUE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.gemoc.trace.commons.model.generictrace.impl.ManyStringAttributeValueImpl <em>Many String Attribute Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gemoc.trace.commons.model.generictrace.impl.ManyStringAttributeValueImpl
	 * @see org.eclipse.gemoc.trace.commons.model.generictrace.impl.GenerictracePackageImpl#getManyStringAttributeValue()
	 * @generated
	 */
	int MANY_STRING_ATTRIBUTE_VALUE = 14;

	/**
	 * The feature id for the '<em><b>States</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANY_STRING_ATTRIBUTE_VALUE__STATES = GENERIC_ATTRIBUTE_VALUE__STATES;

	/**
	 * The feature id for the '<em><b>Attribute Value</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANY_STRING_ATTRIBUTE_VALUE__ATTRIBUTE_VALUE = GENERIC_ATTRIBUTE_VALUE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Many String Attribute Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANY_STRING_ATTRIBUTE_VALUE_FEATURE_COUNT = GENERIC_ATTRIBUTE_VALUE_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Many String Attribute Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANY_STRING_ATTRIBUTE_VALUE_OPERATION_COUNT = GENERIC_ATTRIBUTE_VALUE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.gemoc.trace.commons.model.generictrace.impl.SingleReferenceValueImpl <em>Single Reference Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gemoc.trace.commons.model.generictrace.impl.SingleReferenceValueImpl
	 * @see org.eclipse.gemoc.trace.commons.model.generictrace.impl.GenerictracePackageImpl#getSingleReferenceValue()
	 * @generated
	 */
	int SINGLE_REFERENCE_VALUE = 17;

	/**
	 * The feature id for the '<em><b>States</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SINGLE_REFERENCE_VALUE__STATES = GENERIC_REFERENCE_VALUE__STATES;

	/**
	 * The feature id for the '<em><b>Reference Value</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SINGLE_REFERENCE_VALUE__REFERENCE_VALUE = GENERIC_REFERENCE_VALUE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Single Reference Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SINGLE_REFERENCE_VALUE_FEATURE_COUNT = GENERIC_REFERENCE_VALUE_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Single Reference Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SINGLE_REFERENCE_VALUE_OPERATION_COUNT = GENERIC_REFERENCE_VALUE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.gemoc.trace.commons.model.generictrace.impl.ManyReferenceValueImpl <em>Many Reference Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gemoc.trace.commons.model.generictrace.impl.ManyReferenceValueImpl
	 * @see org.eclipse.gemoc.trace.commons.model.generictrace.impl.GenerictracePackageImpl#getManyReferenceValue()
	 * @generated
	 */
	int MANY_REFERENCE_VALUE = 18;

	/**
	 * The feature id for the '<em><b>States</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANY_REFERENCE_VALUE__STATES = GENERIC_REFERENCE_VALUE__STATES;

	/**
	 * The feature id for the '<em><b>Reference Values</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANY_REFERENCE_VALUE__REFERENCE_VALUES = GENERIC_REFERENCE_VALUE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Many Reference Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANY_REFERENCE_VALUE_FEATURE_COUNT = GENERIC_REFERENCE_VALUE_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Many Reference Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANY_REFERENCE_VALUE_OPERATION_COUNT = GENERIC_REFERENCE_VALUE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.gemoc.trace.commons.model.generictrace.impl.IntegerObjectAttributeValueImpl <em>Integer Object Attribute Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gemoc.trace.commons.model.generictrace.impl.IntegerObjectAttributeValueImpl
	 * @see org.eclipse.gemoc.trace.commons.model.generictrace.impl.GenerictracePackageImpl#getIntegerObjectAttributeValue()
	 * @generated
	 */
	int INTEGER_OBJECT_ATTRIBUTE_VALUE = 19;

	/**
	 * The feature id for the '<em><b>States</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_OBJECT_ATTRIBUTE_VALUE__STATES = GENERIC_ATTRIBUTE_VALUE__STATES;

	/**
	 * The feature id for the '<em><b>Attribute Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_OBJECT_ATTRIBUTE_VALUE__ATTRIBUTE_VALUE = GENERIC_ATTRIBUTE_VALUE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Integer Object Attribute Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_OBJECT_ATTRIBUTE_VALUE_FEATURE_COUNT = GENERIC_ATTRIBUTE_VALUE_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Integer Object Attribute Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_OBJECT_ATTRIBUTE_VALUE_OPERATION_COUNT = GENERIC_ATTRIBUTE_VALUE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.gemoc.trace.commons.model.generictrace.impl.DoubleAttributeValueImpl <em>Double Attribute Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gemoc.trace.commons.model.generictrace.impl.DoubleAttributeValueImpl
	 * @see org.eclipse.gemoc.trace.commons.model.generictrace.impl.GenerictracePackageImpl#getDoubleAttributeValue()
	 * @generated
	 */
	int DOUBLE_ATTRIBUTE_VALUE = 20;

	/**
	 * The feature id for the '<em><b>States</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOUBLE_ATTRIBUTE_VALUE__STATES = GENERIC_ATTRIBUTE_VALUE__STATES;

	/**
	 * The feature id for the '<em><b>Attribute Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOUBLE_ATTRIBUTE_VALUE__ATTRIBUTE_VALUE = GENERIC_ATTRIBUTE_VALUE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Double Attribute Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOUBLE_ATTRIBUTE_VALUE_FEATURE_COUNT = GENERIC_ATTRIBUTE_VALUE_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Double Attribute Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOUBLE_ATTRIBUTE_VALUE_OPERATION_COUNT = GENERIC_ATTRIBUTE_VALUE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.gemoc.trace.commons.model.generictrace.impl.ManyDoubleAttributeValueImpl <em>Many Double Attribute Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gemoc.trace.commons.model.generictrace.impl.ManyDoubleAttributeValueImpl
	 * @see org.eclipse.gemoc.trace.commons.model.generictrace.impl.GenerictracePackageImpl#getManyDoubleAttributeValue()
	 * @generated
	 */
	int MANY_DOUBLE_ATTRIBUTE_VALUE = 21;

	/**
	 * The feature id for the '<em><b>States</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANY_DOUBLE_ATTRIBUTE_VALUE__STATES = GENERIC_ATTRIBUTE_VALUE__STATES;

	/**
	 * The feature id for the '<em><b>Attribute Value</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANY_DOUBLE_ATTRIBUTE_VALUE__ATTRIBUTE_VALUE = GENERIC_ATTRIBUTE_VALUE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Many Double Attribute Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANY_DOUBLE_ATTRIBUTE_VALUE_FEATURE_COUNT = GENERIC_ATTRIBUTE_VALUE_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Many Double Attribute Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANY_DOUBLE_ATTRIBUTE_VALUE_OPERATION_COUNT = GENERIC_ATTRIBUTE_VALUE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.gemoc.trace.commons.model.generictrace.impl.DoubleObjectAttributeValueImpl <em>Double Object Attribute Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gemoc.trace.commons.model.generictrace.impl.DoubleObjectAttributeValueImpl
	 * @see org.eclipse.gemoc.trace.commons.model.generictrace.impl.GenerictracePackageImpl#getDoubleObjectAttributeValue()
	 * @generated
	 */
	int DOUBLE_OBJECT_ATTRIBUTE_VALUE = 22;

	/**
	 * The feature id for the '<em><b>States</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOUBLE_OBJECT_ATTRIBUTE_VALUE__STATES = GENERIC_ATTRIBUTE_VALUE__STATES;

	/**
	 * The feature id for the '<em><b>Attribute Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOUBLE_OBJECT_ATTRIBUTE_VALUE__ATTRIBUTE_VALUE = GENERIC_ATTRIBUTE_VALUE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Double Object Attribute Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOUBLE_OBJECT_ATTRIBUTE_VALUE_FEATURE_COUNT = GENERIC_ATTRIBUTE_VALUE_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Double Object Attribute Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOUBLE_OBJECT_ATTRIBUTE_VALUE_OPERATION_COUNT = GENERIC_ATTRIBUTE_VALUE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.gemoc.trace.commons.model.generictrace.impl.LongAttributeValueImpl <em>Long Attribute Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gemoc.trace.commons.model.generictrace.impl.LongAttributeValueImpl
	 * @see org.eclipse.gemoc.trace.commons.model.generictrace.impl.GenerictracePackageImpl#getLongAttributeValue()
	 * @generated
	 */
	int LONG_ATTRIBUTE_VALUE = 23;

	/**
	 * The feature id for the '<em><b>States</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LONG_ATTRIBUTE_VALUE__STATES = GENERIC_ATTRIBUTE_VALUE__STATES;

	/**
	 * The feature id for the '<em><b>Attribute Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LONG_ATTRIBUTE_VALUE__ATTRIBUTE_VALUE = GENERIC_ATTRIBUTE_VALUE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Long Attribute Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LONG_ATTRIBUTE_VALUE_FEATURE_COUNT = GENERIC_ATTRIBUTE_VALUE_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Long Attribute Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LONG_ATTRIBUTE_VALUE_OPERATION_COUNT = GENERIC_ATTRIBUTE_VALUE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.gemoc.trace.commons.model.generictrace.impl.ManyLongAttributeValueImpl <em>Many Long Attribute Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gemoc.trace.commons.model.generictrace.impl.ManyLongAttributeValueImpl
	 * @see org.eclipse.gemoc.trace.commons.model.generictrace.impl.GenerictracePackageImpl#getManyLongAttributeValue()
	 * @generated
	 */
	int MANY_LONG_ATTRIBUTE_VALUE = 24;

	/**
	 * The feature id for the '<em><b>States</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANY_LONG_ATTRIBUTE_VALUE__STATES = GENERIC_ATTRIBUTE_VALUE__STATES;

	/**
	 * The feature id for the '<em><b>Attribute Value</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANY_LONG_ATTRIBUTE_VALUE__ATTRIBUTE_VALUE = GENERIC_ATTRIBUTE_VALUE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Many Long Attribute Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANY_LONG_ATTRIBUTE_VALUE_FEATURE_COUNT = GENERIC_ATTRIBUTE_VALUE_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Many Long Attribute Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANY_LONG_ATTRIBUTE_VALUE_OPERATION_COUNT = GENERIC_ATTRIBUTE_VALUE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.gemoc.trace.commons.model.generictrace.impl.LongObjectAttributeValueImpl <em>Long Object Attribute Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gemoc.trace.commons.model.generictrace.impl.LongObjectAttributeValueImpl
	 * @see org.eclipse.gemoc.trace.commons.model.generictrace.impl.GenerictracePackageImpl#getLongObjectAttributeValue()
	 * @generated
	 */
	int LONG_OBJECT_ATTRIBUTE_VALUE = 25;

	/**
	 * The feature id for the '<em><b>States</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LONG_OBJECT_ATTRIBUTE_VALUE__STATES = GENERIC_ATTRIBUTE_VALUE__STATES;

	/**
	 * The feature id for the '<em><b>Attribute Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LONG_OBJECT_ATTRIBUTE_VALUE__ATTRIBUTE_VALUE = GENERIC_ATTRIBUTE_VALUE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Long Object Attribute Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LONG_OBJECT_ATTRIBUTE_VALUE_FEATURE_COUNT = GENERIC_ATTRIBUTE_VALUE_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Long Object Attribute Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LONG_OBJECT_ATTRIBUTE_VALUE_OPERATION_COUNT = GENERIC_ATTRIBUTE_VALUE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '<em>ISerializable</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gemoc.trace.commons.model.generictrace.impl.GenerictracePackageImpl#getISerializable()
	 * @generated
	 */
	int ISERIALIZABLE = 26;


	/**
	 * Returns the meta object for class '{@link org.eclipse.gemoc.trace.commons.model.generictrace.GenericSequentialStep <em>Generic Sequential Step</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Generic Sequential Step</em>'.
	 * @see org.eclipse.gemoc.trace.commons.model.generictrace.GenericSequentialStep
	 * @generated
	 */
	EClass getGenericSequentialStep();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gemoc.trace.commons.model.generictrace.GenericParallelStep <em>Generic Parallel Step</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Generic Parallel Step</em>'.
	 * @see org.eclipse.gemoc.trace.commons.model.generictrace.GenericParallelStep
	 * @generated
	 */
	EClass getGenericParallelStep();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gemoc.trace.commons.model.generictrace.GenericSmallStep <em>Generic Small Step</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Generic Small Step</em>'.
	 * @see org.eclipse.gemoc.trace.commons.model.generictrace.GenericSmallStep
	 * @generated
	 */
	EClass getGenericSmallStep();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gemoc.trace.commons.model.generictrace.GenericReferenceValue <em>Generic Reference Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Generic Reference Value</em>'.
	 * @see org.eclipse.gemoc.trace.commons.model.generictrace.GenericReferenceValue
	 * @generated
	 */
	EClass getGenericReferenceValue();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gemoc.trace.commons.model.generictrace.GenericDimension <em>Generic Dimension</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Generic Dimension</em>'.
	 * @see org.eclipse.gemoc.trace.commons.model.generictrace.GenericDimension
	 * @generated
	 */
	EClass getGenericDimension();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.gemoc.trace.commons.model.generictrace.GenericDimension#getDynamicProperty <em>Dynamic Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Dynamic Property</em>'.
	 * @see org.eclipse.gemoc.trace.commons.model.generictrace.GenericDimension#getDynamicProperty()
	 * @see #getGenericDimension()
	 * @generated
	 */
	EReference getGenericDimension_DynamicProperty();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gemoc.trace.commons.model.generictrace.GenericTracedObject <em>Generic Traced Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Generic Traced Object</em>'.
	 * @see org.eclipse.gemoc.trace.commons.model.generictrace.GenericTracedObject
	 * @generated
	 */
	EClass getGenericTracedObject();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.gemoc.trace.commons.model.generictrace.GenericTracedObject#getOriginalObject <em>Original Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Original Object</em>'.
	 * @see org.eclipse.gemoc.trace.commons.model.generictrace.GenericTracedObject#getOriginalObject()
	 * @see #getGenericTracedObject()
	 * @generated
	 */
	EReference getGenericTracedObject_OriginalObject();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.gemoc.trace.commons.model.generictrace.GenericTracedObject#getAllDimensions <em>All Dimensions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>All Dimensions</em>'.
	 * @see org.eclipse.gemoc.trace.commons.model.generictrace.GenericTracedObject#getAllDimensions()
	 * @see #getGenericTracedObject()
	 * @generated
	 */
	EReference getGenericTracedObject_AllDimensions();

	/**
	 * Returns the meta object for the '{@link org.eclipse.gemoc.trace.commons.model.generictrace.GenericTracedObject#getDimensionsInternal() <em>Get Dimensions Internal</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get Dimensions Internal</em>' operation.
	 * @see org.eclipse.gemoc.trace.commons.model.generictrace.GenericTracedObject#getDimensionsInternal()
	 * @generated
	 */
	EOperation getGenericTracedObject__GetDimensionsInternal();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gemoc.trace.commons.model.generictrace.GenericState <em>Generic State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Generic State</em>'.
	 * @see org.eclipse.gemoc.trace.commons.model.generictrace.GenericState
	 * @generated
	 */
	EClass getGenericState();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gemoc.trace.commons.model.generictrace.GenericTrace <em>Generic Trace</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Generic Trace</em>'.
	 * @see org.eclipse.gemoc.trace.commons.model.generictrace.GenericTrace
	 * @generated
	 */
	EClass getGenericTrace();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gemoc.trace.commons.model.generictrace.GenericAttributeValue <em>Generic Attribute Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Generic Attribute Value</em>'.
	 * @see org.eclipse.gemoc.trace.commons.model.generictrace.GenericAttributeValue
	 * @generated
	 */
	EClass getGenericAttributeValue();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gemoc.trace.commons.model.generictrace.BooleanAttributeValue <em>Boolean Attribute Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Boolean Attribute Value</em>'.
	 * @see org.eclipse.gemoc.trace.commons.model.generictrace.BooleanAttributeValue
	 * @generated
	 */
	EClass getBooleanAttributeValue();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.gemoc.trace.commons.model.generictrace.BooleanAttributeValue#isAttributeValue <em>Attribute Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attribute Value</em>'.
	 * @see org.eclipse.gemoc.trace.commons.model.generictrace.BooleanAttributeValue#isAttributeValue()
	 * @see #getBooleanAttributeValue()
	 * @generated
	 */
	EAttribute getBooleanAttributeValue_AttributeValue();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gemoc.trace.commons.model.generictrace.IntegerAttributeValue <em>Integer Attribute Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Integer Attribute Value</em>'.
	 * @see org.eclipse.gemoc.trace.commons.model.generictrace.IntegerAttributeValue
	 * @generated
	 */
	EClass getIntegerAttributeValue();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.gemoc.trace.commons.model.generictrace.IntegerAttributeValue#getAttributeValue <em>Attribute Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attribute Value</em>'.
	 * @see org.eclipse.gemoc.trace.commons.model.generictrace.IntegerAttributeValue#getAttributeValue()
	 * @see #getIntegerAttributeValue()
	 * @generated
	 */
	EAttribute getIntegerAttributeValue_AttributeValue();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gemoc.trace.commons.model.generictrace.StringAttributeValue <em>String Attribute Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>String Attribute Value</em>'.
	 * @see org.eclipse.gemoc.trace.commons.model.generictrace.StringAttributeValue
	 * @generated
	 */
	EClass getStringAttributeValue();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.gemoc.trace.commons.model.generictrace.StringAttributeValue#getAttributeValue <em>Attribute Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attribute Value</em>'.
	 * @see org.eclipse.gemoc.trace.commons.model.generictrace.StringAttributeValue#getAttributeValue()
	 * @see #getStringAttributeValue()
	 * @generated
	 */
	EAttribute getStringAttributeValue_AttributeValue();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gemoc.trace.commons.model.generictrace.ManyBooleanAttributeValue <em>Many Boolean Attribute Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Many Boolean Attribute Value</em>'.
	 * @see org.eclipse.gemoc.trace.commons.model.generictrace.ManyBooleanAttributeValue
	 * @generated
	 */
	EClass getManyBooleanAttributeValue();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.gemoc.trace.commons.model.generictrace.ManyBooleanAttributeValue#getAttributeValue <em>Attribute Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Attribute Value</em>'.
	 * @see org.eclipse.gemoc.trace.commons.model.generictrace.ManyBooleanAttributeValue#getAttributeValue()
	 * @see #getManyBooleanAttributeValue()
	 * @generated
	 */
	EAttribute getManyBooleanAttributeValue_AttributeValue();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gemoc.trace.commons.model.generictrace.ManyIntegerAttributeValue <em>Many Integer Attribute Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Many Integer Attribute Value</em>'.
	 * @see org.eclipse.gemoc.trace.commons.model.generictrace.ManyIntegerAttributeValue
	 * @generated
	 */
	EClass getManyIntegerAttributeValue();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.gemoc.trace.commons.model.generictrace.ManyIntegerAttributeValue#getAttributeValue <em>Attribute Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Attribute Value</em>'.
	 * @see org.eclipse.gemoc.trace.commons.model.generictrace.ManyIntegerAttributeValue#getAttributeValue()
	 * @see #getManyIntegerAttributeValue()
	 * @generated
	 */
	EAttribute getManyIntegerAttributeValue_AttributeValue();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gemoc.trace.commons.model.generictrace.ManyStringAttributeValue <em>Many String Attribute Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Many String Attribute Value</em>'.
	 * @see org.eclipse.gemoc.trace.commons.model.generictrace.ManyStringAttributeValue
	 * @generated
	 */
	EClass getManyStringAttributeValue();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.gemoc.trace.commons.model.generictrace.ManyStringAttributeValue#getAttributeValue <em>Attribute Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Attribute Value</em>'.
	 * @see org.eclipse.gemoc.trace.commons.model.generictrace.ManyStringAttributeValue#getAttributeValue()
	 * @see #getManyStringAttributeValue()
	 * @generated
	 */
	EAttribute getManyStringAttributeValue_AttributeValue();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gemoc.trace.commons.model.generictrace.GenericStep <em>Generic Step</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Generic Step</em>'.
	 * @see org.eclipse.gemoc.trace.commons.model.generictrace.GenericStep
	 * @generated
	 */
	EClass getGenericStep();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gemoc.trace.commons.model.generictrace.GenericValue <em>Generic Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Generic Value</em>'.
	 * @see org.eclipse.gemoc.trace.commons.model.generictrace.GenericValue
	 * @generated
	 */
	EClass getGenericValue();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gemoc.trace.commons.model.generictrace.SingleReferenceValue <em>Single Reference Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Single Reference Value</em>'.
	 * @see org.eclipse.gemoc.trace.commons.model.generictrace.SingleReferenceValue
	 * @generated
	 */
	EClass getSingleReferenceValue();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.gemoc.trace.commons.model.generictrace.SingleReferenceValue#getReferenceValue <em>Reference Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Reference Value</em>'.
	 * @see org.eclipse.gemoc.trace.commons.model.generictrace.SingleReferenceValue#getReferenceValue()
	 * @see #getSingleReferenceValue()
	 * @generated
	 */
	EReference getSingleReferenceValue_ReferenceValue();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gemoc.trace.commons.model.generictrace.ManyReferenceValue <em>Many Reference Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Many Reference Value</em>'.
	 * @see org.eclipse.gemoc.trace.commons.model.generictrace.ManyReferenceValue
	 * @generated
	 */
	EClass getManyReferenceValue();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.gemoc.trace.commons.model.generictrace.ManyReferenceValue#getReferenceValues <em>Reference Values</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Reference Values</em>'.
	 * @see org.eclipse.gemoc.trace.commons.model.generictrace.ManyReferenceValue#getReferenceValues()
	 * @see #getManyReferenceValue()
	 * @generated
	 */
	EReference getManyReferenceValue_ReferenceValues();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gemoc.trace.commons.model.generictrace.IntegerObjectAttributeValue <em>Integer Object Attribute Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Integer Object Attribute Value</em>'.
	 * @see org.eclipse.gemoc.trace.commons.model.generictrace.IntegerObjectAttributeValue
	 * @generated
	 */
	EClass getIntegerObjectAttributeValue();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.gemoc.trace.commons.model.generictrace.IntegerObjectAttributeValue#getAttributeValue <em>Attribute Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attribute Value</em>'.
	 * @see org.eclipse.gemoc.trace.commons.model.generictrace.IntegerObjectAttributeValue#getAttributeValue()
	 * @see #getIntegerObjectAttributeValue()
	 * @generated
	 */
	EAttribute getIntegerObjectAttributeValue_AttributeValue();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gemoc.trace.commons.model.generictrace.DoubleAttributeValue <em>Double Attribute Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Double Attribute Value</em>'.
	 * @see org.eclipse.gemoc.trace.commons.model.generictrace.DoubleAttributeValue
	 * @generated
	 */
	EClass getDoubleAttributeValue();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.gemoc.trace.commons.model.generictrace.DoubleAttributeValue#getAttributeValue <em>Attribute Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attribute Value</em>'.
	 * @see org.eclipse.gemoc.trace.commons.model.generictrace.DoubleAttributeValue#getAttributeValue()
	 * @see #getDoubleAttributeValue()
	 * @generated
	 */
	EAttribute getDoubleAttributeValue_AttributeValue();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gemoc.trace.commons.model.generictrace.ManyDoubleAttributeValue <em>Many Double Attribute Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Many Double Attribute Value</em>'.
	 * @see org.eclipse.gemoc.trace.commons.model.generictrace.ManyDoubleAttributeValue
	 * @generated
	 */
	EClass getManyDoubleAttributeValue();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.gemoc.trace.commons.model.generictrace.ManyDoubleAttributeValue#getAttributeValue <em>Attribute Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Attribute Value</em>'.
	 * @see org.eclipse.gemoc.trace.commons.model.generictrace.ManyDoubleAttributeValue#getAttributeValue()
	 * @see #getManyDoubleAttributeValue()
	 * @generated
	 */
	EAttribute getManyDoubleAttributeValue_AttributeValue();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gemoc.trace.commons.model.generictrace.DoubleObjectAttributeValue <em>Double Object Attribute Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Double Object Attribute Value</em>'.
	 * @see org.eclipse.gemoc.trace.commons.model.generictrace.DoubleObjectAttributeValue
	 * @generated
	 */
	EClass getDoubleObjectAttributeValue();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.gemoc.trace.commons.model.generictrace.DoubleObjectAttributeValue#getAttributeValue <em>Attribute Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attribute Value</em>'.
	 * @see org.eclipse.gemoc.trace.commons.model.generictrace.DoubleObjectAttributeValue#getAttributeValue()
	 * @see #getDoubleObjectAttributeValue()
	 * @generated
	 */
	EAttribute getDoubleObjectAttributeValue_AttributeValue();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gemoc.trace.commons.model.generictrace.LongAttributeValue <em>Long Attribute Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Long Attribute Value</em>'.
	 * @see org.eclipse.gemoc.trace.commons.model.generictrace.LongAttributeValue
	 * @generated
	 */
	EClass getLongAttributeValue();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.gemoc.trace.commons.model.generictrace.LongAttributeValue#getAttributeValue <em>Attribute Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attribute Value</em>'.
	 * @see org.eclipse.gemoc.trace.commons.model.generictrace.LongAttributeValue#getAttributeValue()
	 * @see #getLongAttributeValue()
	 * @generated
	 */
	EAttribute getLongAttributeValue_AttributeValue();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gemoc.trace.commons.model.generictrace.ManyLongAttributeValue <em>Many Long Attribute Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Many Long Attribute Value</em>'.
	 * @see org.eclipse.gemoc.trace.commons.model.generictrace.ManyLongAttributeValue
	 * @generated
	 */
	EClass getManyLongAttributeValue();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.gemoc.trace.commons.model.generictrace.ManyLongAttributeValue#getAttributeValue <em>Attribute Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Attribute Value</em>'.
	 * @see org.eclipse.gemoc.trace.commons.model.generictrace.ManyLongAttributeValue#getAttributeValue()
	 * @see #getManyLongAttributeValue()
	 * @generated
	 */
	EAttribute getManyLongAttributeValue_AttributeValue();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gemoc.trace.commons.model.generictrace.LongObjectAttributeValue <em>Long Object Attribute Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Long Object Attribute Value</em>'.
	 * @see org.eclipse.gemoc.trace.commons.model.generictrace.LongObjectAttributeValue
	 * @generated
	 */
	EClass getLongObjectAttributeValue();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.gemoc.trace.commons.model.generictrace.LongObjectAttributeValue#getAttributeValue <em>Attribute Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attribute Value</em>'.
	 * @see org.eclipse.gemoc.trace.commons.model.generictrace.LongObjectAttributeValue#getAttributeValue()
	 * @see #getLongObjectAttributeValue()
	 * @generated
	 */
	EAttribute getLongObjectAttributeValue_AttributeValue();

	/**
	 * Returns the meta object for data type '<em>ISerializable</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>ISerializable</em>'.
	 * @model instanceClass="byte[]"
	 * @generated
	 */
	EDataType getISerializable();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	GenerictraceFactory getGenerictraceFactory();

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
		 * The meta object literal for the '{@link org.eclipse.gemoc.trace.commons.model.generictrace.impl.GenericSequentialStepImpl <em>Generic Sequential Step</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gemoc.trace.commons.model.generictrace.impl.GenericSequentialStepImpl
		 * @see org.eclipse.gemoc.trace.commons.model.generictrace.impl.GenerictracePackageImpl#getGenericSequentialStep()
		 * @generated
		 */
		EClass GENERIC_SEQUENTIAL_STEP = eINSTANCE.getGenericSequentialStep();

		/**
		 * The meta object literal for the '{@link org.eclipse.gemoc.trace.commons.model.generictrace.impl.GenericParallelStepImpl <em>Generic Parallel Step</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gemoc.trace.commons.model.generictrace.impl.GenericParallelStepImpl
		 * @see org.eclipse.gemoc.trace.commons.model.generictrace.impl.GenerictracePackageImpl#getGenericParallelStep()
		 * @generated
		 */
		EClass GENERIC_PARALLEL_STEP = eINSTANCE.getGenericParallelStep();

		/**
		 * The meta object literal for the '{@link org.eclipse.gemoc.trace.commons.model.generictrace.impl.GenericSmallStepImpl <em>Generic Small Step</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gemoc.trace.commons.model.generictrace.impl.GenericSmallStepImpl
		 * @see org.eclipse.gemoc.trace.commons.model.generictrace.impl.GenerictracePackageImpl#getGenericSmallStep()
		 * @generated
		 */
		EClass GENERIC_SMALL_STEP = eINSTANCE.getGenericSmallStep();

		/**
		 * The meta object literal for the '{@link org.eclipse.gemoc.trace.commons.model.generictrace.impl.GenericReferenceValueImpl <em>Generic Reference Value</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gemoc.trace.commons.model.generictrace.impl.GenericReferenceValueImpl
		 * @see org.eclipse.gemoc.trace.commons.model.generictrace.impl.GenerictracePackageImpl#getGenericReferenceValue()
		 * @generated
		 */
		EClass GENERIC_REFERENCE_VALUE = eINSTANCE.getGenericReferenceValue();

		/**
		 * The meta object literal for the '{@link org.eclipse.gemoc.trace.commons.model.generictrace.impl.GenericDimensionImpl <em>Generic Dimension</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gemoc.trace.commons.model.generictrace.impl.GenericDimensionImpl
		 * @see org.eclipse.gemoc.trace.commons.model.generictrace.impl.GenerictracePackageImpl#getGenericDimension()
		 * @generated
		 */
		EClass GENERIC_DIMENSION = eINSTANCE.getGenericDimension();

		/**
		 * The meta object literal for the '<em><b>Dynamic Property</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GENERIC_DIMENSION__DYNAMIC_PROPERTY = eINSTANCE.getGenericDimension_DynamicProperty();

		/**
		 * The meta object literal for the '{@link org.eclipse.gemoc.trace.commons.model.generictrace.impl.GenericTracedObjectImpl <em>Generic Traced Object</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gemoc.trace.commons.model.generictrace.impl.GenericTracedObjectImpl
		 * @see org.eclipse.gemoc.trace.commons.model.generictrace.impl.GenerictracePackageImpl#getGenericTracedObject()
		 * @generated
		 */
		EClass GENERIC_TRACED_OBJECT = eINSTANCE.getGenericTracedObject();

		/**
		 * The meta object literal for the '<em><b>Original Object</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GENERIC_TRACED_OBJECT__ORIGINAL_OBJECT = eINSTANCE.getGenericTracedObject_OriginalObject();

		/**
		 * The meta object literal for the '<em><b>All Dimensions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GENERIC_TRACED_OBJECT__ALL_DIMENSIONS = eINSTANCE.getGenericTracedObject_AllDimensions();

		/**
		 * The meta object literal for the '<em><b>Get Dimensions Internal</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation GENERIC_TRACED_OBJECT___GET_DIMENSIONS_INTERNAL = eINSTANCE.getGenericTracedObject__GetDimensionsInternal();

		/**
		 * The meta object literal for the '{@link org.eclipse.gemoc.trace.commons.model.generictrace.impl.GenericStateImpl <em>Generic State</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gemoc.trace.commons.model.generictrace.impl.GenericStateImpl
		 * @see org.eclipse.gemoc.trace.commons.model.generictrace.impl.GenerictracePackageImpl#getGenericState()
		 * @generated
		 */
		EClass GENERIC_STATE = eINSTANCE.getGenericState();

		/**
		 * The meta object literal for the '{@link org.eclipse.gemoc.trace.commons.model.generictrace.impl.GenericTraceImpl <em>Generic Trace</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gemoc.trace.commons.model.generictrace.impl.GenericTraceImpl
		 * @see org.eclipse.gemoc.trace.commons.model.generictrace.impl.GenerictracePackageImpl#getGenericTrace()
		 * @generated
		 */
		EClass GENERIC_TRACE = eINSTANCE.getGenericTrace();

		/**
		 * The meta object literal for the '{@link org.eclipse.gemoc.trace.commons.model.generictrace.impl.GenericAttributeValueImpl <em>Generic Attribute Value</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gemoc.trace.commons.model.generictrace.impl.GenericAttributeValueImpl
		 * @see org.eclipse.gemoc.trace.commons.model.generictrace.impl.GenerictracePackageImpl#getGenericAttributeValue()
		 * @generated
		 */
		EClass GENERIC_ATTRIBUTE_VALUE = eINSTANCE.getGenericAttributeValue();

		/**
		 * The meta object literal for the '{@link org.eclipse.gemoc.trace.commons.model.generictrace.impl.BooleanAttributeValueImpl <em>Boolean Attribute Value</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gemoc.trace.commons.model.generictrace.impl.BooleanAttributeValueImpl
		 * @see org.eclipse.gemoc.trace.commons.model.generictrace.impl.GenerictracePackageImpl#getBooleanAttributeValue()
		 * @generated
		 */
		EClass BOOLEAN_ATTRIBUTE_VALUE = eINSTANCE.getBooleanAttributeValue();

		/**
		 * The meta object literal for the '<em><b>Attribute Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BOOLEAN_ATTRIBUTE_VALUE__ATTRIBUTE_VALUE = eINSTANCE.getBooleanAttributeValue_AttributeValue();

		/**
		 * The meta object literal for the '{@link org.eclipse.gemoc.trace.commons.model.generictrace.impl.IntegerAttributeValueImpl <em>Integer Attribute Value</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gemoc.trace.commons.model.generictrace.impl.IntegerAttributeValueImpl
		 * @see org.eclipse.gemoc.trace.commons.model.generictrace.impl.GenerictracePackageImpl#getIntegerAttributeValue()
		 * @generated
		 */
		EClass INTEGER_ATTRIBUTE_VALUE = eINSTANCE.getIntegerAttributeValue();

		/**
		 * The meta object literal for the '<em><b>Attribute Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INTEGER_ATTRIBUTE_VALUE__ATTRIBUTE_VALUE = eINSTANCE.getIntegerAttributeValue_AttributeValue();

		/**
		 * The meta object literal for the '{@link org.eclipse.gemoc.trace.commons.model.generictrace.impl.StringAttributeValueImpl <em>String Attribute Value</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gemoc.trace.commons.model.generictrace.impl.StringAttributeValueImpl
		 * @see org.eclipse.gemoc.trace.commons.model.generictrace.impl.GenerictracePackageImpl#getStringAttributeValue()
		 * @generated
		 */
		EClass STRING_ATTRIBUTE_VALUE = eINSTANCE.getStringAttributeValue();

		/**
		 * The meta object literal for the '<em><b>Attribute Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STRING_ATTRIBUTE_VALUE__ATTRIBUTE_VALUE = eINSTANCE.getStringAttributeValue_AttributeValue();

		/**
		 * The meta object literal for the '{@link org.eclipse.gemoc.trace.commons.model.generictrace.impl.ManyBooleanAttributeValueImpl <em>Many Boolean Attribute Value</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gemoc.trace.commons.model.generictrace.impl.ManyBooleanAttributeValueImpl
		 * @see org.eclipse.gemoc.trace.commons.model.generictrace.impl.GenerictracePackageImpl#getManyBooleanAttributeValue()
		 * @generated
		 */
		EClass MANY_BOOLEAN_ATTRIBUTE_VALUE = eINSTANCE.getManyBooleanAttributeValue();

		/**
		 * The meta object literal for the '<em><b>Attribute Value</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MANY_BOOLEAN_ATTRIBUTE_VALUE__ATTRIBUTE_VALUE = eINSTANCE.getManyBooleanAttributeValue_AttributeValue();

		/**
		 * The meta object literal for the '{@link org.eclipse.gemoc.trace.commons.model.generictrace.impl.ManyIntegerAttributeValueImpl <em>Many Integer Attribute Value</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gemoc.trace.commons.model.generictrace.impl.ManyIntegerAttributeValueImpl
		 * @see org.eclipse.gemoc.trace.commons.model.generictrace.impl.GenerictracePackageImpl#getManyIntegerAttributeValue()
		 * @generated
		 */
		EClass MANY_INTEGER_ATTRIBUTE_VALUE = eINSTANCE.getManyIntegerAttributeValue();

		/**
		 * The meta object literal for the '<em><b>Attribute Value</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MANY_INTEGER_ATTRIBUTE_VALUE__ATTRIBUTE_VALUE = eINSTANCE.getManyIntegerAttributeValue_AttributeValue();

		/**
		 * The meta object literal for the '{@link org.eclipse.gemoc.trace.commons.model.generictrace.impl.ManyStringAttributeValueImpl <em>Many String Attribute Value</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gemoc.trace.commons.model.generictrace.impl.ManyStringAttributeValueImpl
		 * @see org.eclipse.gemoc.trace.commons.model.generictrace.impl.GenerictracePackageImpl#getManyStringAttributeValue()
		 * @generated
		 */
		EClass MANY_STRING_ATTRIBUTE_VALUE = eINSTANCE.getManyStringAttributeValue();

		/**
		 * The meta object literal for the '<em><b>Attribute Value</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MANY_STRING_ATTRIBUTE_VALUE__ATTRIBUTE_VALUE = eINSTANCE.getManyStringAttributeValue_AttributeValue();

		/**
		 * The meta object literal for the '{@link org.eclipse.gemoc.trace.commons.model.generictrace.impl.GenericStepImpl <em>Generic Step</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gemoc.trace.commons.model.generictrace.impl.GenericStepImpl
		 * @see org.eclipse.gemoc.trace.commons.model.generictrace.impl.GenerictracePackageImpl#getGenericStep()
		 * @generated
		 */
		EClass GENERIC_STEP = eINSTANCE.getGenericStep();

		/**
		 * The meta object literal for the '{@link org.eclipse.gemoc.trace.commons.model.generictrace.impl.GenericValueImpl <em>Generic Value</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gemoc.trace.commons.model.generictrace.impl.GenericValueImpl
		 * @see org.eclipse.gemoc.trace.commons.model.generictrace.impl.GenerictracePackageImpl#getGenericValue()
		 * @generated
		 */
		EClass GENERIC_VALUE = eINSTANCE.getGenericValue();

		/**
		 * The meta object literal for the '{@link org.eclipse.gemoc.trace.commons.model.generictrace.impl.SingleReferenceValueImpl <em>Single Reference Value</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gemoc.trace.commons.model.generictrace.impl.SingleReferenceValueImpl
		 * @see org.eclipse.gemoc.trace.commons.model.generictrace.impl.GenerictracePackageImpl#getSingleReferenceValue()
		 * @generated
		 */
		EClass SINGLE_REFERENCE_VALUE = eINSTANCE.getSingleReferenceValue();

		/**
		 * The meta object literal for the '<em><b>Reference Value</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SINGLE_REFERENCE_VALUE__REFERENCE_VALUE = eINSTANCE.getSingleReferenceValue_ReferenceValue();

		/**
		 * The meta object literal for the '{@link org.eclipse.gemoc.trace.commons.model.generictrace.impl.ManyReferenceValueImpl <em>Many Reference Value</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gemoc.trace.commons.model.generictrace.impl.ManyReferenceValueImpl
		 * @see org.eclipse.gemoc.trace.commons.model.generictrace.impl.GenerictracePackageImpl#getManyReferenceValue()
		 * @generated
		 */
		EClass MANY_REFERENCE_VALUE = eINSTANCE.getManyReferenceValue();

		/**
		 * The meta object literal for the '<em><b>Reference Values</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MANY_REFERENCE_VALUE__REFERENCE_VALUES = eINSTANCE.getManyReferenceValue_ReferenceValues();

		/**
		 * The meta object literal for the '{@link org.eclipse.gemoc.trace.commons.model.generictrace.impl.IntegerObjectAttributeValueImpl <em>Integer Object Attribute Value</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gemoc.trace.commons.model.generictrace.impl.IntegerObjectAttributeValueImpl
		 * @see org.eclipse.gemoc.trace.commons.model.generictrace.impl.GenerictracePackageImpl#getIntegerObjectAttributeValue()
		 * @generated
		 */
		EClass INTEGER_OBJECT_ATTRIBUTE_VALUE = eINSTANCE.getIntegerObjectAttributeValue();

		/**
		 * The meta object literal for the '<em><b>Attribute Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INTEGER_OBJECT_ATTRIBUTE_VALUE__ATTRIBUTE_VALUE = eINSTANCE.getIntegerObjectAttributeValue_AttributeValue();

		/**
		 * The meta object literal for the '{@link org.eclipse.gemoc.trace.commons.model.generictrace.impl.DoubleAttributeValueImpl <em>Double Attribute Value</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gemoc.trace.commons.model.generictrace.impl.DoubleAttributeValueImpl
		 * @see org.eclipse.gemoc.trace.commons.model.generictrace.impl.GenerictracePackageImpl#getDoubleAttributeValue()
		 * @generated
		 */
		EClass DOUBLE_ATTRIBUTE_VALUE = eINSTANCE.getDoubleAttributeValue();

		/**
		 * The meta object literal for the '<em><b>Attribute Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOUBLE_ATTRIBUTE_VALUE__ATTRIBUTE_VALUE = eINSTANCE.getDoubleAttributeValue_AttributeValue();

		/**
		 * The meta object literal for the '{@link org.eclipse.gemoc.trace.commons.model.generictrace.impl.ManyDoubleAttributeValueImpl <em>Many Double Attribute Value</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gemoc.trace.commons.model.generictrace.impl.ManyDoubleAttributeValueImpl
		 * @see org.eclipse.gemoc.trace.commons.model.generictrace.impl.GenerictracePackageImpl#getManyDoubleAttributeValue()
		 * @generated
		 */
		EClass MANY_DOUBLE_ATTRIBUTE_VALUE = eINSTANCE.getManyDoubleAttributeValue();

		/**
		 * The meta object literal for the '<em><b>Attribute Value</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MANY_DOUBLE_ATTRIBUTE_VALUE__ATTRIBUTE_VALUE = eINSTANCE.getManyDoubleAttributeValue_AttributeValue();

		/**
		 * The meta object literal for the '{@link org.eclipse.gemoc.trace.commons.model.generictrace.impl.DoubleObjectAttributeValueImpl <em>Double Object Attribute Value</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gemoc.trace.commons.model.generictrace.impl.DoubleObjectAttributeValueImpl
		 * @see org.eclipse.gemoc.trace.commons.model.generictrace.impl.GenerictracePackageImpl#getDoubleObjectAttributeValue()
		 * @generated
		 */
		EClass DOUBLE_OBJECT_ATTRIBUTE_VALUE = eINSTANCE.getDoubleObjectAttributeValue();

		/**
		 * The meta object literal for the '<em><b>Attribute Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOUBLE_OBJECT_ATTRIBUTE_VALUE__ATTRIBUTE_VALUE = eINSTANCE.getDoubleObjectAttributeValue_AttributeValue();

		/**
		 * The meta object literal for the '{@link org.eclipse.gemoc.trace.commons.model.generictrace.impl.LongAttributeValueImpl <em>Long Attribute Value</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gemoc.trace.commons.model.generictrace.impl.LongAttributeValueImpl
		 * @see org.eclipse.gemoc.trace.commons.model.generictrace.impl.GenerictracePackageImpl#getLongAttributeValue()
		 * @generated
		 */
		EClass LONG_ATTRIBUTE_VALUE = eINSTANCE.getLongAttributeValue();

		/**
		 * The meta object literal for the '<em><b>Attribute Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LONG_ATTRIBUTE_VALUE__ATTRIBUTE_VALUE = eINSTANCE.getLongAttributeValue_AttributeValue();

		/**
		 * The meta object literal for the '{@link org.eclipse.gemoc.trace.commons.model.generictrace.impl.ManyLongAttributeValueImpl <em>Many Long Attribute Value</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gemoc.trace.commons.model.generictrace.impl.ManyLongAttributeValueImpl
		 * @see org.eclipse.gemoc.trace.commons.model.generictrace.impl.GenerictracePackageImpl#getManyLongAttributeValue()
		 * @generated
		 */
		EClass MANY_LONG_ATTRIBUTE_VALUE = eINSTANCE.getManyLongAttributeValue();

		/**
		 * The meta object literal for the '<em><b>Attribute Value</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MANY_LONG_ATTRIBUTE_VALUE__ATTRIBUTE_VALUE = eINSTANCE.getManyLongAttributeValue_AttributeValue();

		/**
		 * The meta object literal for the '{@link org.eclipse.gemoc.trace.commons.model.generictrace.impl.LongObjectAttributeValueImpl <em>Long Object Attribute Value</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gemoc.trace.commons.model.generictrace.impl.LongObjectAttributeValueImpl
		 * @see org.eclipse.gemoc.trace.commons.model.generictrace.impl.GenerictracePackageImpl#getLongObjectAttributeValue()
		 * @generated
		 */
		EClass LONG_OBJECT_ATTRIBUTE_VALUE = eINSTANCE.getLongObjectAttributeValue();

		/**
		 * The meta object literal for the '<em><b>Attribute Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LONG_OBJECT_ATTRIBUTE_VALUE__ATTRIBUTE_VALUE = eINSTANCE.getLongObjectAttributeValue_AttributeValue();

		/**
		 * The meta object literal for the '<em>ISerializable</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gemoc.trace.commons.model.generictrace.impl.GenerictracePackageImpl#getISerializable()
		 * @generated
		 */
		EDataType ISERIALIZABLE = eINSTANCE.getISerializable();

	}

} //GenerictracePackage
