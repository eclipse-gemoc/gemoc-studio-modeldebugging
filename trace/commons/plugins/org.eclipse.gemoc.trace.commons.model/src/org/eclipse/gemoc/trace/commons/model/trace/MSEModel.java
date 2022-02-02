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

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>MSE Model</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.gemoc.trace.commons.model.trace.MSEModel#getOwnedMSEs <em>Owned MS Es</em>}</li>
 *   <li>{@link org.eclipse.gemoc.trace.commons.model.trace.MSEModel#getOrphanOperations <em>Orphan Operations</em>}</li>
 * </ul>
 *
 * @see org.eclipse.gemoc.trace.commons.model.trace.TracePackage#getMSEModel()
 * @model
 * @generated
 */
public interface MSEModel extends EObject {
	/**
	 * Returns the value of the '<em><b>Owned MS Es</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.gemoc.trace.commons.model.trace.MSE}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned MS Es</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owned MS Es</em>' containment reference list.
	 * @see org.eclipse.gemoc.trace.commons.model.trace.TracePackage#getMSEModel_OwnedMSEs()
	 * @model containment="true"
	 * @generated
	 */
	EList<MSE> getOwnedMSEs();

	/**
	 * Returns the value of the '<em><b>Orphan Operations</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.EOperation}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Orphan Operations</em>' containment reference list.
	 * @see org.eclipse.gemoc.trace.commons.model.trace.TracePackage#getMSEModel_OrphanOperations()
	 * @model containment="true"
	 * @generated
	 */
	EList<EOperation> getOrphanOperations();

} // MSEModel
