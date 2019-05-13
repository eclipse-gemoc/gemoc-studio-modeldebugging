/**
 */
package org.eclipse.gemoc.executionframework.engine.model.engine.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.gemoc.executionframework.engine.model.engine.EObjectEListEObject;
import org.eclipse.gemoc.executionframework.engine.model.engine.EngineFactory;
import org.eclipse.gemoc.executionframework.engine.model.engine.EnginePackage;
import org.eclipse.gemoc.executionframework.engine.model.engine.ResourceEObject;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class EnginePackageImpl extends EPackageImpl implements EnginePackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass eObjectEListEObjectEClass = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass resourceEObjectEClass = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.eclipse.gemoc.executionframework.engine.model.engine.EnginePackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private EnginePackageImpl() {
		super(eNS_URI, EngineFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 *
	 * <p>This method is used to initialize {@link EnginePackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static EnginePackage init() {
		if (isInited)
			return (EnginePackage) EPackage.Registry.INSTANCE.getEPackage(EnginePackage.eNS_URI);

		// Obtain or create and register package
		Object registeredEnginePackage = EPackage.Registry.INSTANCE.get(eNS_URI);
		EnginePackageImpl theEnginePackage = registeredEnginePackage instanceof EnginePackageImpl
				? (EnginePackageImpl) registeredEnginePackage
				: new EnginePackageImpl();

		isInited = true;

		// Create package meta-data objects
		theEnginePackage.createPackageContents();

		// Initialize created meta-data
		theEnginePackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theEnginePackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(EnginePackage.eNS_URI, theEnginePackage);
		return theEnginePackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEObjectEListEObject() {
		return eObjectEListEObjectEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEObjectEListEObject_Contents() {
		return (EReference) eObjectEListEObjectEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getResourceEObject() {
		return resourceEObjectEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getResourceEObject_Contents() {
		return (EReference) resourceEObjectEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getResourceEObject_Name() {
		return (EAttribute) resourceEObjectEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EngineFactory getEngineFactory() {
		return (EngineFactory) getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated)
			return;
		isCreated = true;

		// Create classes and their features
		eObjectEListEObjectEClass = createEClass(EOBJECT_ELIST_EOBJECT);
		createEReference(eObjectEListEObjectEClass, EOBJECT_ELIST_EOBJECT__CONTENTS);

		resourceEObjectEClass = createEClass(RESOURCE_EOBJECT);
		createEReference(resourceEObjectEClass, RESOURCE_EOBJECT__CONTENTS);
		createEAttribute(resourceEObjectEClass, RESOURCE_EOBJECT__NAME);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized)
			return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes

		// Initialize classes, features, and operations; add parameters
		initEClass(eObjectEListEObjectEClass, EObjectEListEObject.class, "EObjectEListEObject", !IS_ABSTRACT,
				!IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getEObjectEListEObject_Contents(), ecorePackage.getEObject(), null, "contents", null, 0, -1,
				EObjectEListEObject.class, !IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(resourceEObjectEClass, ResourceEObject.class, "ResourceEObject", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getResourceEObject_Contents(), ecorePackage.getEObject(), null, "contents", null, 0, -1,
				ResourceEObject.class, !IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getResourceEObject_Name(), ecorePackage.getEString(), "name", null, 0, 1, ResourceEObject.class,
				!IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);
	}

} //EnginePackageImpl
