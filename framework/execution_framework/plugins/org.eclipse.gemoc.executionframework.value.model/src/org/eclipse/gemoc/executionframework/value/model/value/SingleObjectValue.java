/**
 */
package org.eclipse.gemoc.executionframework.value.model.value;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Single Object Value</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.gemoc.executionframework.value.model.value.SingleObjectValue#getObjectValue <em>Object Value</em>}</li>
 * </ul>
 *
 * @see org.eclipse.gemoc.executionframework.value.model.value.ValuePackage#getSingleObjectValue()
 * @model
 * @generated
 */
public interface SingleObjectValue extends ReferenceValue {
	/**
	 * Returns the value of the '<em><b>Object Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Object Value</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Object Value</em>' containment reference.
	 * @see #setObjectValue(EObject)
	 * @see org.eclipse.gemoc.executionframework.value.model.value.ValuePackage#getSingleObjectValue_ObjectValue()
	 * @model containment="true"
	 * @generated
	 */
	EObject getObjectValue();

	/**
	 * Sets the value of the '{@link org.eclipse.gemoc.executionframework.value.model.value.SingleObjectValue#getObjectValue <em>Object Value</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Object Value</em>' containment reference.
	 * @see #getObjectValue()
	 * @generated
	 */
	void setObjectValue(EObject value);

} // SingleObjectValue
