/**
 */
package org.eclipse.gemoc.executionframework.value.model.value;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Many Reference Value</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.gemoc.executionframework.value.model.value.ManyReferenceValue#getReferenceValues <em>Reference Values</em>}</li>
 * </ul>
 *
 * @see org.eclipse.gemoc.executionframework.value.model.value.ValuePackage#getManyReferenceValue()
 * @model
 * @generated
 */
public interface ManyReferenceValue extends ReferenceValue {
	/**
	 * Returns the value of the '<em><b>Reference Values</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.EObject}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Reference Values</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Reference Values</em>' reference list.
	 * @see org.eclipse.gemoc.executionframework.value.model.value.ValuePackage#getManyReferenceValue_ReferenceValues()
	 * @model
	 * @generated
	 */
	EList<EObject> getReferenceValues();

} // ManyReferenceValue
