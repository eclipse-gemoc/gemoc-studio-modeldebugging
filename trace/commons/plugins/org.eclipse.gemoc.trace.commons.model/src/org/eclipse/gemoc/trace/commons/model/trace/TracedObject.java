/**
 */
package org.eclipse.gemoc.trace.commons.model.trace;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Traced Object</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.gemoc.trace.commons.model.trace.TracedObject#getDimensions <em>Dimensions</em>}</li>
 * </ul>
 *
 * @see org.eclipse.gemoc.trace.commons.model.trace.TracePackage#getTracedObject()
 * @model abstract="true"
 * @generated
 */
public interface TracedObject<DimensionSubType extends Dimension<?>> extends EObject {
	/**
	 * Returns the value of the '<em><b>Dimensions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Dimensions</em>' reference list.
	 * @see org.eclipse.gemoc.trace.commons.model.trace.TracePackage#getTracedObject_Dimensions()
	 * @model transient="true" volatile="true" derived="true"
	 * @generated
	 */
	EList<DimensionSubType> getDimensions();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	EList<DimensionSubType> getDimensionsInternal();

} // TracedObject
