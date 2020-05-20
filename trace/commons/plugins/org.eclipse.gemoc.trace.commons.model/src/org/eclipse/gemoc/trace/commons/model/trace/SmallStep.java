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
package org.eclipse.gemoc.trace.commons.model.trace;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Small Step</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.gemoc.trace.commons.model.trace.SmallStep#getFootprint <em>Footprint</em>}</li>
 * </ul>
 *
 * @see org.eclipse.gemoc.trace.commons.model.trace.TracePackage#getSmallStep()
 * @model abstract="true"
 * @generated
 */
public interface SmallStep<StateSubType extends State<?, ?>> extends Step<StateSubType> {

	/**
	 * Returns the value of the '<em><b>Footprint</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Footprint</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Footprint</em>' containment reference.
	 * @see #setFootprint(Footprint)
	 * @see org.eclipse.gemoc.trace.commons.model.trace.TracePackage#getSmallStep_Footprint()
	 * @model containment="true"
	 * @generated
	 */
	Footprint getFootprint();

	/**
	 * Sets the value of the '{@link org.eclipse.gemoc.trace.commons.model.trace.SmallStep#getFootprint <em>Footprint</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Footprint</em>' containment reference.
	 * @see #getFootprint()
	 * @generated
	 */
	void setFootprint(Footprint value);
} // SmallStep
