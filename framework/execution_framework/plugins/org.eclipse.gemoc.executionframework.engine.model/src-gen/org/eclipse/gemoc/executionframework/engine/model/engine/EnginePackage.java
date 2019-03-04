/**
 */
package org.eclipse.gemoc.executionframework.engine.model.engine;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.eclipse.gemoc.executionframework.engine.model.engine.EngineFactory
 * @model kind="package"
 * @generated
 */
public interface EnginePackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "engine";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.eclipse.org/gemoc/engine";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "engine";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	EnginePackage eINSTANCE = org.eclipse.gemoc.executionframework.engine.model.engine.impl.EnginePackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.gemoc.executionframework.engine.model.engine.impl.EObjectEListEObjectImpl <em>EObject EList EObject</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gemoc.executionframework.engine.model.engine.impl.EObjectEListEObjectImpl
	 * @see org.eclipse.gemoc.executionframework.engine.model.engine.impl.EnginePackageImpl#getEObjectEListEObject()
	 * @generated
	 */
	int EOBJECT_ELIST_EOBJECT = 0;

	/**
	 * The feature id for the '<em><b>Contents</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EOBJECT_ELIST_EOBJECT__CONTENTS = 0;

	/**
	 * The number of structural features of the '<em>EObject EList EObject</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EOBJECT_ELIST_EOBJECT_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>EObject EList EObject</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EOBJECT_ELIST_EOBJECT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.gemoc.executionframework.engine.model.engine.impl.ResourceEObjectImpl <em>Resource EObject</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gemoc.executionframework.engine.model.engine.impl.ResourceEObjectImpl
	 * @see org.eclipse.gemoc.executionframework.engine.model.engine.impl.EnginePackageImpl#getResourceEObject()
	 * @generated
	 */
	int RESOURCE_EOBJECT = 1;

	/**
	 * The feature id for the '<em><b>Contents</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_EOBJECT__CONTENTS = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_EOBJECT__NAME = 1;

	/**
	 * The number of structural features of the '<em>Resource EObject</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_EOBJECT_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Resource EObject</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_EOBJECT_OPERATION_COUNT = 0;

	/**
	 * Returns the meta object for class '{@link org.eclipse.gemoc.executionframework.engine.model.engine.EObjectEListEObject <em>EObject EList EObject</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>EObject EList EObject</em>'.
	 * @see org.eclipse.gemoc.executionframework.engine.model.engine.EObjectEListEObject
	 * @generated
	 */
	EClass getEObjectEListEObject();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.gemoc.executionframework.engine.model.engine.EObjectEListEObject#getContents <em>Contents</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Contents</em>'.
	 * @see org.eclipse.gemoc.executionframework.engine.model.engine.EObjectEListEObject#getContents()
	 * @see #getEObjectEListEObject()
	 * @generated
	 */
	EReference getEObjectEListEObject_Contents();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gemoc.executionframework.engine.model.engine.ResourceEObject <em>Resource EObject</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Resource EObject</em>'.
	 * @see org.eclipse.gemoc.executionframework.engine.model.engine.ResourceEObject
	 * @generated
	 */
	EClass getResourceEObject();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.gemoc.executionframework.engine.model.engine.ResourceEObject#getContents <em>Contents</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Contents</em>'.
	 * @see org.eclipse.gemoc.executionframework.engine.model.engine.ResourceEObject#getContents()
	 * @see #getResourceEObject()
	 * @generated
	 */
	EReference getResourceEObject_Contents();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.gemoc.executionframework.engine.model.engine.ResourceEObject#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.gemoc.executionframework.engine.model.engine.ResourceEObject#getName()
	 * @see #getResourceEObject()
	 * @generated
	 */
	EAttribute getResourceEObject_Name();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	EngineFactory getEngineFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link org.eclipse.gemoc.executionframework.engine.model.engine.impl.EObjectEListEObjectImpl <em>EObject EList EObject</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gemoc.executionframework.engine.model.engine.impl.EObjectEListEObjectImpl
		 * @see org.eclipse.gemoc.executionframework.engine.model.engine.impl.EnginePackageImpl#getEObjectEListEObject()
		 * @generated
		 */
		EClass EOBJECT_ELIST_EOBJECT = eINSTANCE.getEObjectEListEObject();

		/**
		 * The meta object literal for the '<em><b>Contents</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EOBJECT_ELIST_EOBJECT__CONTENTS = eINSTANCE.getEObjectEListEObject_Contents();

		/**
		 * The meta object literal for the '{@link org.eclipse.gemoc.executionframework.engine.model.engine.impl.ResourceEObjectImpl <em>Resource EObject</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gemoc.executionframework.engine.model.engine.impl.ResourceEObjectImpl
		 * @see org.eclipse.gemoc.executionframework.engine.model.engine.impl.EnginePackageImpl#getResourceEObject()
		 * @generated
		 */
		EClass RESOURCE_EOBJECT = eINSTANCE.getResourceEObject();

		/**
		 * The meta object literal for the '<em><b>Contents</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RESOURCE_EOBJECT__CONTENTS = eINSTANCE.getResourceEObject_Contents();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RESOURCE_EOBJECT__NAME = eINSTANCE.getResourceEObject_Name();

	}

} //EnginePackage
