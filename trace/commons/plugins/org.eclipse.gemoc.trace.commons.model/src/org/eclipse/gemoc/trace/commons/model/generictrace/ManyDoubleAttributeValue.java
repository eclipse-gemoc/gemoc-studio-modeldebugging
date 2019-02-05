/**
 */
package org.eclipse.gemoc.trace.commons.model.generictrace;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Many Double Attribute Value</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.gemoc.trace.commons.model.generictrace.ManyDoubleAttributeValue#getAttributeValue <em>Attribute Value</em>}</li>
 * </ul>
 *
 * @see org.eclipse.gemoc.trace.commons.model.generictrace.GenerictracePackage#getManyDoubleAttributeValue()
 * @model
 * @generated
 */
public interface ManyDoubleAttributeValue extends GenericAttributeValue {
	/**
	 * Returns the value of the '<em><b>Attribute Value</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.Double}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Attribute Value</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Attribute Value</em>' attribute list.
	 * @see org.eclipse.gemoc.trace.commons.model.generictrace.GenerictracePackage#getManyDoubleAttributeValue_AttributeValue()
	 * @model
	 * @generated
	 */
	EList<Double> getAttributeValue();

} // ManyDoubleAttributeValue
