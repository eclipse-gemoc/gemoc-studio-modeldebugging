/**
 */
package org.eclipse.gemoc.executionframework.engine.model.engine;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>EObject EList EObject</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.gemoc.executionframework.engine.model.engine.EObjectEListEObject#getContents <em>Contents</em>}</li>
 * </ul>
 *
 * @see org.eclipse.gemoc.executionframework.engine.model.engine.EnginePackage#getEObjectEListEObject()
 * @model
 * @generated
 */
public interface EObjectEListEObject extends EObject {
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
	 * @see org.eclipse.gemoc.executionframework.engine.model.engine.EnginePackage#getEObjectEListEObject_Contents()
	 * @model volatile="true" derived="true"
	 * @generated
	 */
	EList<EObject> getContents();

} // EObjectEListEObject
