/**
 */
package org.eclipse.gemoc.trace.commons.model.trace;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Footprint</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.gemoc.trace.commons.model.trace.Footprint#getAccesses <em>Accesses</em>}</li>
 *   <li>{@link org.eclipse.gemoc.trace.commons.model.trace.Footprint#getChanges <em>Changes</em>}</li>
 *   <li>{@link org.eclipse.gemoc.trace.commons.model.trace.Footprint#getInstantiations <em>Instantiations</em>}</li>
 * </ul>
 *
 * @see org.eclipse.gemoc.trace.commons.model.trace.TracePackage#getFootprint()
 * @model
 * @generated
 */
public interface Footprint extends EObject {
	/**
	 * Returns the value of the '<em><b>Accesses</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.EObject}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Accesses</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Accesses</em>' reference list.
	 * @see org.eclipse.gemoc.trace.commons.model.trace.TracePackage#getFootprint_Accesses()
	 * @model
	 * @generated
	 */
	EList<EObject> getAccesses();

	/**
	 * Returns the value of the '<em><b>Changes</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.EObject}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Changes</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Changes</em>' reference list.
	 * @see org.eclipse.gemoc.trace.commons.model.trace.TracePackage#getFootprint_Changes()
	 * @model
	 * @generated
	 */
	EList<EObject> getChanges();

	/**
	 * Returns the value of the '<em><b>Instantiations</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.EClass}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Instantiations</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Instantiations</em>' reference list.
	 * @see org.eclipse.gemoc.trace.commons.model.trace.TracePackage#getFootprint_Instantiations()
	 * @model
	 * @generated
	 */
	EList<EClass> getInstantiations();

} // Footprint
