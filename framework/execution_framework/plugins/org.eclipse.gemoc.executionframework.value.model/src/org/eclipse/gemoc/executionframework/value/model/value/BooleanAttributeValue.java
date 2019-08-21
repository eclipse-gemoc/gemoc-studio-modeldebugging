/**
 */
package org.eclipse.gemoc.executionframework.value.model.value;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Boolean Attribute Value</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.gemoc.executionframework.value.model.value.BooleanAttributeValue#isAttributeValue <em>Attribute Value</em>}</li>
 * </ul>
 *
 * @see org.eclipse.gemoc.executionframework.value.model.value.ValuePackage#getBooleanAttributeValue()
 * @model
 * @generated
 */
public interface BooleanAttributeValue extends AttributeValue {
	/**
	 * Returns the value of the '<em><b>Attribute Value</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Attribute Value</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Attribute Value</em>' attribute.
	 * @see #setAttributeValue(boolean)
	 * @see org.eclipse.gemoc.executionframework.value.model.value.ValuePackage#getBooleanAttributeValue_AttributeValue()
	 * @model default="false"
	 * @generated
	 */
	boolean isAttributeValue();

	/**
	 * Sets the value of the '{@link org.eclipse.gemoc.executionframework.value.model.value.BooleanAttributeValue#isAttributeValue <em>Attribute Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Attribute Value</em>' attribute.
	 * @see #isAttributeValue()
	 * @generated
	 */
	void setAttributeValue(boolean value);

} // BooleanAttributeValue
