/*******************************************************************************
 * Copyright (c) 2016, 2017 Inria and others.
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
package tracingannotations;

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
 * @see tracingannotations.TracingannotationsFactory
 * @model kind="package"
 * @generated
 */
public interface TracingannotationsPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "tracingannotations";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "tracingannotations";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "tracingannotations";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	TracingannotationsPackage eINSTANCE = tracingannotations.impl.TracingannotationsPackageImpl.init();

	/**
	 * The meta object id for the '{@link tracingannotations.impl.TracingAnnotationsImpl <em>Tracing Annotations</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see tracingannotations.impl.TracingAnnotationsImpl
	 * @see tracingannotations.impl.TracingannotationsPackageImpl#getTracingAnnotations()
	 * @generated
	 */
	int TRACING_ANNOTATIONS = 0;

	/**
	 * The feature id for the '<em><b>Properties To Trace</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACING_ANNOTATIONS__PROPERTIES_TO_TRACE = 0;

	/**
	 * The feature id for the '<em><b>Classesto Trace</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACING_ANNOTATIONS__CLASSESTO_TRACE = 1;

	/**
	 * The number of structural features of the '<em>Tracing Annotations</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACING_ANNOTATIONS_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Tracing Annotations</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACING_ANNOTATIONS_OPERATION_COUNT = 0;


	/**
	 * Returns the meta object for class '{@link tracingannotations.TracingAnnotations <em>Tracing Annotations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Tracing Annotations</em>'.
	 * @see tracingannotations.TracingAnnotations
	 * @generated
	 */
	EClass getTracingAnnotations();

	/**
	 * Returns the meta object for the reference list '{@link tracingannotations.TracingAnnotations#getPropertiesToTrace <em>Properties To Trace</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Properties To Trace</em>'.
	 * @see tracingannotations.TracingAnnotations#getPropertiesToTrace()
	 * @see #getTracingAnnotations()
	 * @generated
	 */
	EReference getTracingAnnotations_PropertiesToTrace();

	/**
	 * Returns the meta object for the reference list '{@link tracingannotations.TracingAnnotations#getClassestoTrace <em>Classesto Trace</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Classesto Trace</em>'.
	 * @see tracingannotations.TracingAnnotations#getClassestoTrace()
	 * @see #getTracingAnnotations()
	 * @generated
	 */
	EReference getTracingAnnotations_ClassestoTrace();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	TracingannotationsFactory getTracingannotationsFactory();

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
		 * The meta object literal for the '{@link tracingannotations.impl.TracingAnnotationsImpl <em>Tracing Annotations</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see tracingannotations.impl.TracingAnnotationsImpl
		 * @see tracingannotations.impl.TracingannotationsPackageImpl#getTracingAnnotations()
		 * @generated
		 */
		EClass TRACING_ANNOTATIONS = eINSTANCE.getTracingAnnotations();

		/**
		 * The meta object literal for the '<em><b>Properties To Trace</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRACING_ANNOTATIONS__PROPERTIES_TO_TRACE = eINSTANCE.getTracingAnnotations_PropertiesToTrace();

		/**
		 * The meta object literal for the '<em><b>Classesto Trace</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRACING_ANNOTATIONS__CLASSESTO_TRACE = eINSTANCE.getTracingAnnotations_ClassestoTrace();

	}

} //TracingannotationsPackage
