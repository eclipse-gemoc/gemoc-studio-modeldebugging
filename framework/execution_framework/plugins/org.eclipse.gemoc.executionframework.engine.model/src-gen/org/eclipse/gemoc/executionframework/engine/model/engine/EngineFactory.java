/**
 */
package org.eclipse.gemoc.executionframework.engine.model.engine;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.gemoc.executionframework.engine.model.engine.EnginePackage
 * @generated
 */
public interface EngineFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	EngineFactory eINSTANCE = org.eclipse.gemoc.executionframework.engine.model.engine.impl.EngineFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>EObject EList EObject</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>EObject EList EObject</em>'.
	 * @generated
	 */
	EObjectEListEObject createEObjectEListEObject();

	/**
	 * Returns a new object of class '<em>Resource EObject</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Resource EObject</em>'.
	 * @generated
	 */
	ResourceEObject createResourceEObject();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	EnginePackage getEnginePackage();

} //EngineFactory
