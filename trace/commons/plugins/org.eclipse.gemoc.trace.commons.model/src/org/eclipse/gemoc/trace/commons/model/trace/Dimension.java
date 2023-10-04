/**
 */
package org.eclipse.gemoc.trace.commons.model.trace;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Dimension</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.gemoc.trace.commons.model.trace.Dimension#getValues <em>Values</em>}</li>
 * </ul>
 *
 * @see org.eclipse.gemoc.trace.commons.model.trace.TracePackage#getDimension()
 * @model abstract="true"
 * @generated
 */
public interface Dimension<ValueSubType extends Value<?>> extends EObject {
	/**
	 * Returns the value of the '<em><b>Values</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Values</em>' containment reference list.
	 * @see org.eclipse.gemoc.trace.commons.model.trace.TracePackage#getDimension_Values()
	 * @model containment="true"
	 * @generated
	 */
	EList<ValueSubType> getValues();

} // Dimension
