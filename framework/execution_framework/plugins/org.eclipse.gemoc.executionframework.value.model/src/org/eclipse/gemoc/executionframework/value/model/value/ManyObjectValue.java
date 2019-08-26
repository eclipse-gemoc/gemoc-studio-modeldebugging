/**
 */
package org.eclipse.gemoc.executionframework.value.model.value;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Many Object Value</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.gemoc.executionframework.value.model.value.ManyObjectValue#getObjectValues <em>Object Values</em>}</li>
 * </ul>
 *
 * @see org.eclipse.gemoc.executionframework.value.model.value.ValuePackage#getManyObjectValue()
 * @model
 * @generated
 */
public interface ManyObjectValue extends ReferenceValue {
	/**
	 * Returns the value of the '<em><b>Object Values</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.EObject}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Object Values</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Object Values</em>' containment reference list.
	 * @see org.eclipse.gemoc.executionframework.value.model.value.ValuePackage#getManyObjectValue_ObjectValues()
	 * @model containment="true"
	 * @generated
	 */
	EList<EObject> getObjectValues();

} // ManyObjectValue
