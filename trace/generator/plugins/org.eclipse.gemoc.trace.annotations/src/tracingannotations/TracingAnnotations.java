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

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Tracing Annotations</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link tracingannotations.TracingAnnotations#getPropertiesToTrace <em>Properties To Trace</em>}</li>
 *   <li>{@link tracingannotations.TracingAnnotations#getClassestoTrace <em>Classesto Trace</em>}</li>
 * </ul>
 * </p>
 *
 * @see tracingannotations.TracingannotationsPackage#getTracingAnnotations()
 * @model
 * @generated
 */
public interface TracingAnnotations extends EObject {
	/**
	 * Returns the value of the '<em><b>Properties To Trace</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.EStructuralFeature}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Properties To Trace</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Properties To Trace</em>' reference list.
	 * @see tracingannotations.TracingannotationsPackage#getTracingAnnotations_PropertiesToTrace()
	 * @model ordered="false"
	 * @generated
	 */
	EList<EStructuralFeature> getPropertiesToTrace();

	/**
	 * Returns the value of the '<em><b>Classesto Trace</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.EClass}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Classesto Trace</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Classesto Trace</em>' reference list.
	 * @see tracingannotations.TracingannotationsPackage#getTracingAnnotations_ClassestoTrace()
	 * @model ordered="false"
	 * @generated
	 */
	EList<EClass> getClassestoTrace();

} // TracingAnnotations
