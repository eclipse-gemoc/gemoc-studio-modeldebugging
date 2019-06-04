/**
 */
package org.eclipse.gemoc.executionframework.engine.model.engine;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Resource EObject</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.gemoc.executionframework.engine.model.engine.ResourceEObject#getContents <em>Contents</em>}</li>
 *   <li>{@link org.eclipse.gemoc.executionframework.engine.model.engine.ResourceEObject#getName <em>Name</em>}</li>
 * </ul>
 *
 * @see org.eclipse.gemoc.executionframework.engine.model.engine.EnginePackage#getResourceEObject()
 * @model
 * @generated
 */
public interface ResourceEObject extends EObject {
	/**
	 * Returns the value of the '<em><b>Contents</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.EObject}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Contents</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Contents</em>' reference list.
	 * @see org.eclipse.gemoc.executionframework.engine.model.engine.EnginePackage#getResourceEObject_Contents()
	 * @model volatile="true" derived="true"
	 * @generated
	 */
	EList<EObject> getContents();

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.eclipse.gemoc.executionframework.engine.model.engine.EnginePackage#getResourceEObject_Name()
	 * @model volatile="true"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.eclipse.gemoc.executionframework.engine.model.engine.ResourceEObject#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

} // ResourceEObject
