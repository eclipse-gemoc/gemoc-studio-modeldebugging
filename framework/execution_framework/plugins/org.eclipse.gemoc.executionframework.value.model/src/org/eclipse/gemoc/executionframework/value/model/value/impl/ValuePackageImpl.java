/**
 */
package org.eclipse.gemoc.executionframework.value.model.value.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.gemoc.executionframework.value.model.value.AttributeValue;
import org.eclipse.gemoc.executionframework.value.model.value.BooleanAttributeValue;
import org.eclipse.gemoc.executionframework.value.model.value.BooleanObjectAttributeValue;
import org.eclipse.gemoc.executionframework.value.model.value.FloatAttributeValue;
import org.eclipse.gemoc.executionframework.value.model.value.FloatObjectAttributeValue;
import org.eclipse.gemoc.executionframework.value.model.value.IntegerAttributeValue;
import org.eclipse.gemoc.executionframework.value.model.value.IntegerObjectAttributeValue;
import org.eclipse.gemoc.executionframework.value.model.value.LongAttributeValue;
import org.eclipse.gemoc.executionframework.value.model.value.LongObjectAttributeValue;
import org.eclipse.gemoc.executionframework.value.model.value.ManyBooleanAttributeValue;
import org.eclipse.gemoc.executionframework.value.model.value.ManyBooleanObjectAttributeValue;
import org.eclipse.gemoc.executionframework.value.model.value.ManyFloatAttributeValue;
import org.eclipse.gemoc.executionframework.value.model.value.ManyFloatObjectAttributeValue;
import org.eclipse.gemoc.executionframework.value.model.value.ManyIntegerAttributeValue;
import org.eclipse.gemoc.executionframework.value.model.value.ManyIntegerObjectAttributeValue;
import org.eclipse.gemoc.executionframework.value.model.value.ManyLongAttributeValue;
import org.eclipse.gemoc.executionframework.value.model.value.ManyLongObjectAttributeValue;
import org.eclipse.gemoc.executionframework.value.model.value.ManyObjectValue;
import org.eclipse.gemoc.executionframework.value.model.value.ManyReferenceValue;
import org.eclipse.gemoc.executionframework.value.model.value.ManyStringAttributeValue;
import org.eclipse.gemoc.executionframework.value.model.value.ReferenceValue;
import org.eclipse.gemoc.executionframework.value.model.value.SingleObjectValue;
import org.eclipse.gemoc.executionframework.value.model.value.SingleReferenceValue;
import org.eclipse.gemoc.executionframework.value.model.value.StringAttributeValue;
import org.eclipse.gemoc.executionframework.value.model.value.Value;
import org.eclipse.gemoc.executionframework.value.model.value.ValueFactory;
import org.eclipse.gemoc.executionframework.value.model.value.ValuePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ValuePackageImpl extends EPackageImpl implements ValuePackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass valueEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass attributeValueEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass referenceValueEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass booleanAttributeValueEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass booleanObjectAttributeValueEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass integerAttributeValueEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass integerObjectAttributeValueEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass longAttributeValueEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass longObjectAttributeValueEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass floatAttributeValueEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass floatObjectAttributeValueEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stringAttributeValueEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass manyBooleanAttributeValueEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass manyBooleanObjectAttributeValueEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass manyIntegerAttributeValueEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass manyIntegerObjectAttributeValueEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass manyLongAttributeValueEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass manyLongObjectAttributeValueEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass manyFloatAttributeValueEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass manyFloatObjectAttributeValueEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass manyStringAttributeValueEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass singleReferenceValueEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass manyReferenceValueEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass singleObjectValueEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass manyObjectValueEClass = null;

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
	 * @see org.eclipse.gemoc.executionframework.value.model.value.ValuePackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private ValuePackageImpl() {
		super(eNS_URI, ValueFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link ValuePackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static ValuePackage init() {
		if (isInited) return (ValuePackage)EPackage.Registry.INSTANCE.getEPackage(ValuePackage.eNS_URI);

		// Obtain or create and register package
		Object registeredValuePackage = EPackage.Registry.INSTANCE.get(eNS_URI);
		ValuePackageImpl theValuePackage = registeredValuePackage instanceof ValuePackageImpl ? (ValuePackageImpl)registeredValuePackage : new ValuePackageImpl();

		isInited = true;

		// Create package meta-data objects
		theValuePackage.createPackageContents();

		// Initialize created meta-data
		theValuePackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theValuePackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(ValuePackage.eNS_URI, theValuePackage);
		return theValuePackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getValue() {
		return valueEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getAttributeValue() {
		return attributeValueEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getReferenceValue() {
		return referenceValueEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getBooleanAttributeValue() {
		return booleanAttributeValueEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getBooleanAttributeValue_AttributeValue() {
		return (EAttribute)booleanAttributeValueEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getBooleanObjectAttributeValue() {
		return booleanObjectAttributeValueEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getBooleanObjectAttributeValue_AttributeValue() {
		return (EAttribute)booleanObjectAttributeValueEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getIntegerAttributeValue() {
		return integerAttributeValueEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getIntegerAttributeValue_AttributeValue() {
		return (EAttribute)integerAttributeValueEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getIntegerObjectAttributeValue() {
		return integerObjectAttributeValueEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getIntegerObjectAttributeValue_AttributeValue() {
		return (EAttribute)integerObjectAttributeValueEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getLongAttributeValue() {
		return longAttributeValueEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getLongAttributeValue_AttributeValue() {
		return (EAttribute)longAttributeValueEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getLongObjectAttributeValue() {
		return longObjectAttributeValueEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getLongObjectAttributeValue_AttributeValue() {
		return (EAttribute)longObjectAttributeValueEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getFloatAttributeValue() {
		return floatAttributeValueEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getFloatAttributeValue_AttributeValue() {
		return (EAttribute)floatAttributeValueEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getFloatObjectAttributeValue() {
		return floatObjectAttributeValueEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getFloatObjectAttributeValue_AttributeValue() {
		return (EAttribute)floatObjectAttributeValueEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getStringAttributeValue() {
		return stringAttributeValueEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getStringAttributeValue_AttributeValue() {
		return (EAttribute)stringAttributeValueEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getManyBooleanAttributeValue() {
		return manyBooleanAttributeValueEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getManyBooleanAttributeValue_AttributeValue() {
		return (EAttribute)manyBooleanAttributeValueEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getManyBooleanObjectAttributeValue() {
		return manyBooleanObjectAttributeValueEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getManyBooleanObjectAttributeValue_AttributeValue() {
		return (EAttribute)manyBooleanObjectAttributeValueEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getManyIntegerAttributeValue() {
		return manyIntegerAttributeValueEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getManyIntegerAttributeValue_AttributeValue() {
		return (EAttribute)manyIntegerAttributeValueEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getManyIntegerObjectAttributeValue() {
		return manyIntegerObjectAttributeValueEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getManyIntegerObjectAttributeValue_AttributeValue() {
		return (EAttribute)manyIntegerObjectAttributeValueEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getManyLongAttributeValue() {
		return manyLongAttributeValueEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getManyLongAttributeValue_AttributeValue() {
		return (EAttribute)manyLongAttributeValueEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getManyLongObjectAttributeValue() {
		return manyLongObjectAttributeValueEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getManyLongObjectAttributeValue_AttributeValue() {
		return (EAttribute)manyLongObjectAttributeValueEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getManyFloatAttributeValue() {
		return manyFloatAttributeValueEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getManyFloatAttributeValue_AttributeValue() {
		return (EAttribute)manyFloatAttributeValueEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getManyFloatObjectAttributeValue() {
		return manyFloatObjectAttributeValueEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getManyFloatObjectAttributeValue_AttributeValue() {
		return (EAttribute)manyFloatObjectAttributeValueEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getManyStringAttributeValue() {
		return manyStringAttributeValueEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getManyStringAttributeValue_AttributeValue() {
		return (EAttribute)manyStringAttributeValueEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getSingleReferenceValue() {
		return singleReferenceValueEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getSingleReferenceValue_ReferenceValue() {
		return (EReference)singleReferenceValueEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getManyReferenceValue() {
		return manyReferenceValueEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getManyReferenceValue_ReferenceValues() {
		return (EReference)manyReferenceValueEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getSingleObjectValue() {
		return singleObjectValueEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getSingleObjectValue_ObjectValue() {
		return (EReference)singleObjectValueEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getManyObjectValue() {
		return manyObjectValueEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getManyObjectValue_ObjectValues() {
		return (EReference)manyObjectValueEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ValueFactory getValueFactory() {
		return (ValueFactory)getEFactoryInstance();
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
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		valueEClass = createEClass(VALUE);

		attributeValueEClass = createEClass(ATTRIBUTE_VALUE);

		referenceValueEClass = createEClass(REFERENCE_VALUE);

		booleanAttributeValueEClass = createEClass(BOOLEAN_ATTRIBUTE_VALUE);
		createEAttribute(booleanAttributeValueEClass, BOOLEAN_ATTRIBUTE_VALUE__ATTRIBUTE_VALUE);

		booleanObjectAttributeValueEClass = createEClass(BOOLEAN_OBJECT_ATTRIBUTE_VALUE);
		createEAttribute(booleanObjectAttributeValueEClass, BOOLEAN_OBJECT_ATTRIBUTE_VALUE__ATTRIBUTE_VALUE);

		integerAttributeValueEClass = createEClass(INTEGER_ATTRIBUTE_VALUE);
		createEAttribute(integerAttributeValueEClass, INTEGER_ATTRIBUTE_VALUE__ATTRIBUTE_VALUE);

		integerObjectAttributeValueEClass = createEClass(INTEGER_OBJECT_ATTRIBUTE_VALUE);
		createEAttribute(integerObjectAttributeValueEClass, INTEGER_OBJECT_ATTRIBUTE_VALUE__ATTRIBUTE_VALUE);

		longAttributeValueEClass = createEClass(LONG_ATTRIBUTE_VALUE);
		createEAttribute(longAttributeValueEClass, LONG_ATTRIBUTE_VALUE__ATTRIBUTE_VALUE);

		longObjectAttributeValueEClass = createEClass(LONG_OBJECT_ATTRIBUTE_VALUE);
		createEAttribute(longObjectAttributeValueEClass, LONG_OBJECT_ATTRIBUTE_VALUE__ATTRIBUTE_VALUE);

		floatAttributeValueEClass = createEClass(FLOAT_ATTRIBUTE_VALUE);
		createEAttribute(floatAttributeValueEClass, FLOAT_ATTRIBUTE_VALUE__ATTRIBUTE_VALUE);

		floatObjectAttributeValueEClass = createEClass(FLOAT_OBJECT_ATTRIBUTE_VALUE);
		createEAttribute(floatObjectAttributeValueEClass, FLOAT_OBJECT_ATTRIBUTE_VALUE__ATTRIBUTE_VALUE);

		stringAttributeValueEClass = createEClass(STRING_ATTRIBUTE_VALUE);
		createEAttribute(stringAttributeValueEClass, STRING_ATTRIBUTE_VALUE__ATTRIBUTE_VALUE);

		manyBooleanAttributeValueEClass = createEClass(MANY_BOOLEAN_ATTRIBUTE_VALUE);
		createEAttribute(manyBooleanAttributeValueEClass, MANY_BOOLEAN_ATTRIBUTE_VALUE__ATTRIBUTE_VALUE);

		manyBooleanObjectAttributeValueEClass = createEClass(MANY_BOOLEAN_OBJECT_ATTRIBUTE_VALUE);
		createEAttribute(manyBooleanObjectAttributeValueEClass, MANY_BOOLEAN_OBJECT_ATTRIBUTE_VALUE__ATTRIBUTE_VALUE);

		manyIntegerAttributeValueEClass = createEClass(MANY_INTEGER_ATTRIBUTE_VALUE);
		createEAttribute(manyIntegerAttributeValueEClass, MANY_INTEGER_ATTRIBUTE_VALUE__ATTRIBUTE_VALUE);

		manyIntegerObjectAttributeValueEClass = createEClass(MANY_INTEGER_OBJECT_ATTRIBUTE_VALUE);
		createEAttribute(manyIntegerObjectAttributeValueEClass, MANY_INTEGER_OBJECT_ATTRIBUTE_VALUE__ATTRIBUTE_VALUE);

		manyLongAttributeValueEClass = createEClass(MANY_LONG_ATTRIBUTE_VALUE);
		createEAttribute(manyLongAttributeValueEClass, MANY_LONG_ATTRIBUTE_VALUE__ATTRIBUTE_VALUE);

		manyLongObjectAttributeValueEClass = createEClass(MANY_LONG_OBJECT_ATTRIBUTE_VALUE);
		createEAttribute(manyLongObjectAttributeValueEClass, MANY_LONG_OBJECT_ATTRIBUTE_VALUE__ATTRIBUTE_VALUE);

		manyFloatAttributeValueEClass = createEClass(MANY_FLOAT_ATTRIBUTE_VALUE);
		createEAttribute(manyFloatAttributeValueEClass, MANY_FLOAT_ATTRIBUTE_VALUE__ATTRIBUTE_VALUE);

		manyFloatObjectAttributeValueEClass = createEClass(MANY_FLOAT_OBJECT_ATTRIBUTE_VALUE);
		createEAttribute(manyFloatObjectAttributeValueEClass, MANY_FLOAT_OBJECT_ATTRIBUTE_VALUE__ATTRIBUTE_VALUE);

		manyStringAttributeValueEClass = createEClass(MANY_STRING_ATTRIBUTE_VALUE);
		createEAttribute(manyStringAttributeValueEClass, MANY_STRING_ATTRIBUTE_VALUE__ATTRIBUTE_VALUE);

		singleReferenceValueEClass = createEClass(SINGLE_REFERENCE_VALUE);
		createEReference(singleReferenceValueEClass, SINGLE_REFERENCE_VALUE__REFERENCE_VALUE);

		manyReferenceValueEClass = createEClass(MANY_REFERENCE_VALUE);
		createEReference(manyReferenceValueEClass, MANY_REFERENCE_VALUE__REFERENCE_VALUES);

		singleObjectValueEClass = createEClass(SINGLE_OBJECT_VALUE);
		createEReference(singleObjectValueEClass, SINGLE_OBJECT_VALUE__OBJECT_VALUE);

		manyObjectValueEClass = createEClass(MANY_OBJECT_VALUE);
		createEReference(manyObjectValueEClass, MANY_OBJECT_VALUE__OBJECT_VALUES);
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
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		attributeValueEClass.getESuperTypes().add(this.getValue());
		referenceValueEClass.getESuperTypes().add(this.getValue());
		booleanAttributeValueEClass.getESuperTypes().add(this.getAttributeValue());
		booleanObjectAttributeValueEClass.getESuperTypes().add(this.getAttributeValue());
		integerAttributeValueEClass.getESuperTypes().add(this.getAttributeValue());
		integerObjectAttributeValueEClass.getESuperTypes().add(this.getAttributeValue());
		longAttributeValueEClass.getESuperTypes().add(this.getAttributeValue());
		longObjectAttributeValueEClass.getESuperTypes().add(this.getAttributeValue());
		floatAttributeValueEClass.getESuperTypes().add(this.getAttributeValue());
		floatObjectAttributeValueEClass.getESuperTypes().add(this.getAttributeValue());
		stringAttributeValueEClass.getESuperTypes().add(this.getAttributeValue());
		manyBooleanAttributeValueEClass.getESuperTypes().add(this.getAttributeValue());
		manyBooleanObjectAttributeValueEClass.getESuperTypes().add(this.getAttributeValue());
		manyIntegerAttributeValueEClass.getESuperTypes().add(this.getAttributeValue());
		manyIntegerObjectAttributeValueEClass.getESuperTypes().add(this.getAttributeValue());
		manyLongAttributeValueEClass.getESuperTypes().add(this.getAttributeValue());
		manyLongObjectAttributeValueEClass.getESuperTypes().add(this.getAttributeValue());
		manyFloatAttributeValueEClass.getESuperTypes().add(this.getAttributeValue());
		manyFloatObjectAttributeValueEClass.getESuperTypes().add(this.getAttributeValue());
		manyStringAttributeValueEClass.getESuperTypes().add(this.getAttributeValue());
		singleReferenceValueEClass.getESuperTypes().add(this.getReferenceValue());
		manyReferenceValueEClass.getESuperTypes().add(this.getReferenceValue());
		singleObjectValueEClass.getESuperTypes().add(this.getReferenceValue());
		manyObjectValueEClass.getESuperTypes().add(this.getReferenceValue());

		// Initialize classes, features, and operations; add parameters
		initEClass(valueEClass, Value.class, "Value", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(attributeValueEClass, AttributeValue.class, "AttributeValue", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(referenceValueEClass, ReferenceValue.class, "ReferenceValue", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(booleanAttributeValueEClass, BooleanAttributeValue.class, "BooleanAttributeValue", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getBooleanAttributeValue_AttributeValue(), ecorePackage.getEBoolean(), "attributeValue", "false", 0, 1, BooleanAttributeValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(booleanObjectAttributeValueEClass, BooleanObjectAttributeValue.class, "BooleanObjectAttributeValue", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getBooleanObjectAttributeValue_AttributeValue(), ecorePackage.getEBooleanObject(), "attributeValue", null, 0, 1, BooleanObjectAttributeValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(integerAttributeValueEClass, IntegerAttributeValue.class, "IntegerAttributeValue", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getIntegerAttributeValue_AttributeValue(), ecorePackage.getEInt(), "attributeValue", null, 0, 1, IntegerAttributeValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(integerObjectAttributeValueEClass, IntegerObjectAttributeValue.class, "IntegerObjectAttributeValue", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getIntegerObjectAttributeValue_AttributeValue(), ecorePackage.getEIntegerObject(), "attributeValue", null, 0, 1, IntegerObjectAttributeValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(longAttributeValueEClass, LongAttributeValue.class, "LongAttributeValue", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getLongAttributeValue_AttributeValue(), ecorePackage.getELong(), "attributeValue", null, 0, 1, LongAttributeValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(longObjectAttributeValueEClass, LongObjectAttributeValue.class, "LongObjectAttributeValue", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getLongObjectAttributeValue_AttributeValue(), ecorePackage.getELongObject(), "attributeValue", null, 0, 1, LongObjectAttributeValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(floatAttributeValueEClass, FloatAttributeValue.class, "FloatAttributeValue", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getFloatAttributeValue_AttributeValue(), ecorePackage.getEFloat(), "attributeValue", null, 0, 1, FloatAttributeValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(floatObjectAttributeValueEClass, FloatObjectAttributeValue.class, "FloatObjectAttributeValue", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getFloatObjectAttributeValue_AttributeValue(), ecorePackage.getEFloatObject(), "attributeValue", null, 0, 1, FloatObjectAttributeValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(stringAttributeValueEClass, StringAttributeValue.class, "StringAttributeValue", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getStringAttributeValue_AttributeValue(), ecorePackage.getEString(), "attributeValue", null, 0, 1, StringAttributeValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(manyBooleanAttributeValueEClass, ManyBooleanAttributeValue.class, "ManyBooleanAttributeValue", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getManyBooleanAttributeValue_AttributeValue(), ecorePackage.getEBoolean(), "attributeValue", "false", 0, -1, ManyBooleanAttributeValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(manyBooleanObjectAttributeValueEClass, ManyBooleanObjectAttributeValue.class, "ManyBooleanObjectAttributeValue", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getManyBooleanObjectAttributeValue_AttributeValue(), ecorePackage.getEBooleanObject(), "attributeValue", "false", 0, -1, ManyBooleanObjectAttributeValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(manyIntegerAttributeValueEClass, ManyIntegerAttributeValue.class, "ManyIntegerAttributeValue", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getManyIntegerAttributeValue_AttributeValue(), ecorePackage.getEInt(), "attributeValue", null, 0, -1, ManyIntegerAttributeValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(manyIntegerObjectAttributeValueEClass, ManyIntegerObjectAttributeValue.class, "ManyIntegerObjectAttributeValue", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getManyIntegerObjectAttributeValue_AttributeValue(), ecorePackage.getEIntegerObject(), "attributeValue", null, 0, -1, ManyIntegerObjectAttributeValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(manyLongAttributeValueEClass, ManyLongAttributeValue.class, "ManyLongAttributeValue", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getManyLongAttributeValue_AttributeValue(), ecorePackage.getELong(), "attributeValue", null, 0, -1, ManyLongAttributeValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(manyLongObjectAttributeValueEClass, ManyLongObjectAttributeValue.class, "ManyLongObjectAttributeValue", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getManyLongObjectAttributeValue_AttributeValue(), ecorePackage.getELongObject(), "attributeValue", null, 0, -1, ManyLongObjectAttributeValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(manyFloatAttributeValueEClass, ManyFloatAttributeValue.class, "ManyFloatAttributeValue", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getManyFloatAttributeValue_AttributeValue(), ecorePackage.getEFloat(), "attributeValue", null, 0, -1, ManyFloatAttributeValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(manyFloatObjectAttributeValueEClass, ManyFloatObjectAttributeValue.class, "ManyFloatObjectAttributeValue", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getManyFloatObjectAttributeValue_AttributeValue(), ecorePackage.getEFloatObject(), "attributeValue", null, 0, -1, ManyFloatObjectAttributeValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(manyStringAttributeValueEClass, ManyStringAttributeValue.class, "ManyStringAttributeValue", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getManyStringAttributeValue_AttributeValue(), ecorePackage.getEString(), "attributeValue", null, 0, -1, ManyStringAttributeValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(singleReferenceValueEClass, SingleReferenceValue.class, "SingleReferenceValue", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSingleReferenceValue_ReferenceValue(), ecorePackage.getEObject(), null, "referenceValue", null, 0, 1, SingleReferenceValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(manyReferenceValueEClass, ManyReferenceValue.class, "ManyReferenceValue", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getManyReferenceValue_ReferenceValues(), ecorePackage.getEObject(), null, "referenceValues", null, 0, -1, ManyReferenceValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(singleObjectValueEClass, SingleObjectValue.class, "SingleObjectValue", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSingleObjectValue_ObjectValue(), ecorePackage.getEObject(), null, "objectValue", null, 0, 1, SingleObjectValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(manyObjectValueEClass, ManyObjectValue.class, "ManyObjectValue", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getManyObjectValue_ObjectValues(), ecorePackage.getEObject(), null, "objectValues", null, 0, -1, ManyObjectValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);
	}

} //ValuePackageImpl
