/**
 */
package org.eclipse.gemoc.executionframework.engine.model.engine.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.eclipse.gemoc.executionframework.engine.model.engine.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class EngineFactoryImpl extends EFactoryImpl implements EngineFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static EngineFactory init() {
		try {
			EngineFactory theEngineFactory = (EngineFactory) EPackage.Registry.INSTANCE
					.getEFactory(EnginePackage.eNS_URI);
			if (theEngineFactory != null) {
				return theEngineFactory;
			}
		} catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new EngineFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EngineFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
		case EnginePackage.EOBJECT_ELIST_EOBJECT:
			return createEObjectEListEObject();
		case EnginePackage.RESOURCE_EOBJECT:
			return createResourceEObject();
		default:
			throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EObjectEListEObject createEObjectEListEObject() {
		EObjectEListEObjectImpl eObjectEListEObject = new EObjectEListEObjectImpl();
		return eObjectEListEObject;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ResourceEObject createResourceEObject() {
		ResourceEObjectImpl resourceEObject = new ResourceEObjectImpl();
		return resourceEObject;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EnginePackage getEnginePackage() {
		return (EnginePackage) getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static EnginePackage getPackage() {
		return EnginePackage.eINSTANCE;
	}

} //EngineFactoryImpl
