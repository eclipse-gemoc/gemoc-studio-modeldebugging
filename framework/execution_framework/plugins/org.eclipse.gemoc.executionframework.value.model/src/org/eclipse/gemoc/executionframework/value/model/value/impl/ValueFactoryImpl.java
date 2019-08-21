/**
 */
package org.eclipse.gemoc.executionframework.value.model.value.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.eclipse.gemoc.executionframework.value.model.value.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ValueFactoryImpl extends EFactoryImpl implements ValueFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ValueFactory init() {
		try {
			ValueFactory theValueFactory = (ValueFactory)EPackage.Registry.INSTANCE.getEFactory(ValuePackage.eNS_URI);
			if (theValueFactory != null) {
				return theValueFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new ValueFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ValueFactoryImpl() {
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
			case ValuePackage.BOOLEAN_ATTRIBUTE_VALUE: return createBooleanAttributeValue();
			case ValuePackage.BOOLEAN_OBJECT_ATTRIBUTE_VALUE: return createBooleanObjectAttributeValue();
			case ValuePackage.INTEGER_ATTRIBUTE_VALUE: return createIntegerAttributeValue();
			case ValuePackage.INTEGER_OBJECT_ATTRIBUTE_VALUE: return createIntegerObjectAttributeValue();
			case ValuePackage.LONG_ATTRIBUTE_VALUE: return createLongAttributeValue();
			case ValuePackage.LONG_OBJECT_ATTRIBUTE_VALUE: return createLongObjectAttributeValue();
			case ValuePackage.FLOAT_ATTRIBUTE_VALUE: return createFloatAttributeValue();
			case ValuePackage.FLOAT_OBJECT_ATTRIBUTE_VALUE: return createFloatObjectAttributeValue();
			case ValuePackage.STRING_ATTRIBUTE_VALUE: return createStringAttributeValue();
			case ValuePackage.MANY_BOOLEAN_ATTRIBUTE_VALUE: return createManyBooleanAttributeValue();
			case ValuePackage.MANY_BOOLEAN_OBJECT_ATTRIBUTE_VALUE: return createManyBooleanObjectAttributeValue();
			case ValuePackage.MANY_INTEGER_ATTRIBUTE_VALUE: return createManyIntegerAttributeValue();
			case ValuePackage.MANY_INTEGER_OBJECT_ATTRIBUTE_VALUE: return createManyIntegerObjectAttributeValue();
			case ValuePackage.MANY_LONG_ATTRIBUTE_VALUE: return createManyLongAttributeValue();
			case ValuePackage.MANY_LONG_OBJECT_ATTRIBUTE_VALUE: return createManyLongObjectAttributeValue();
			case ValuePackage.MANY_FLOAT_ATTRIBUTE_VALUE: return createManyFloatAttributeValue();
			case ValuePackage.MANY_FLOAT_OBJECT_ATTRIBUTE_VALUE: return createManyFloatObjectAttributeValue();
			case ValuePackage.MANY_STRING_ATTRIBUTE_VALUE: return createManyStringAttributeValue();
			case ValuePackage.SINGLE_REFERENCE_VALUE: return createSingleReferenceValue();
			case ValuePackage.MANY_REFERENCE_VALUE: return createManyReferenceValue();
			case ValuePackage.SINGLE_OBJECT_VALUE: return createSingleObjectValue();
			case ValuePackage.MANY_OBJECT_VALUE: return createManyObjectValue();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BooleanAttributeValue createBooleanAttributeValue() {
		BooleanAttributeValueImpl booleanAttributeValue = new BooleanAttributeValueImpl();
		return booleanAttributeValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BooleanObjectAttributeValue createBooleanObjectAttributeValue() {
		BooleanObjectAttributeValueImpl booleanObjectAttributeValue = new BooleanObjectAttributeValueImpl();
		return booleanObjectAttributeValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IntegerAttributeValue createIntegerAttributeValue() {
		IntegerAttributeValueImpl integerAttributeValue = new IntegerAttributeValueImpl();
		return integerAttributeValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IntegerObjectAttributeValue createIntegerObjectAttributeValue() {
		IntegerObjectAttributeValueImpl integerObjectAttributeValue = new IntegerObjectAttributeValueImpl();
		return integerObjectAttributeValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LongAttributeValue createLongAttributeValue() {
		LongAttributeValueImpl longAttributeValue = new LongAttributeValueImpl();
		return longAttributeValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LongObjectAttributeValue createLongObjectAttributeValue() {
		LongObjectAttributeValueImpl longObjectAttributeValue = new LongObjectAttributeValueImpl();
		return longObjectAttributeValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FloatAttributeValue createFloatAttributeValue() {
		FloatAttributeValueImpl floatAttributeValue = new FloatAttributeValueImpl();
		return floatAttributeValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FloatObjectAttributeValue createFloatObjectAttributeValue() {
		FloatObjectAttributeValueImpl floatObjectAttributeValue = new FloatObjectAttributeValueImpl();
		return floatObjectAttributeValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StringAttributeValue createStringAttributeValue() {
		StringAttributeValueImpl stringAttributeValue = new StringAttributeValueImpl();
		return stringAttributeValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ManyBooleanAttributeValue createManyBooleanAttributeValue() {
		ManyBooleanAttributeValueImpl manyBooleanAttributeValue = new ManyBooleanAttributeValueImpl();
		return manyBooleanAttributeValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ManyBooleanObjectAttributeValue createManyBooleanObjectAttributeValue() {
		ManyBooleanObjectAttributeValueImpl manyBooleanObjectAttributeValue = new ManyBooleanObjectAttributeValueImpl();
		return manyBooleanObjectAttributeValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ManyIntegerAttributeValue createManyIntegerAttributeValue() {
		ManyIntegerAttributeValueImpl manyIntegerAttributeValue = new ManyIntegerAttributeValueImpl();
		return manyIntegerAttributeValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ManyIntegerObjectAttributeValue createManyIntegerObjectAttributeValue() {
		ManyIntegerObjectAttributeValueImpl manyIntegerObjectAttributeValue = new ManyIntegerObjectAttributeValueImpl();
		return manyIntegerObjectAttributeValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ManyLongAttributeValue createManyLongAttributeValue() {
		ManyLongAttributeValueImpl manyLongAttributeValue = new ManyLongAttributeValueImpl();
		return manyLongAttributeValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ManyLongObjectAttributeValue createManyLongObjectAttributeValue() {
		ManyLongObjectAttributeValueImpl manyLongObjectAttributeValue = new ManyLongObjectAttributeValueImpl();
		return manyLongObjectAttributeValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ManyFloatAttributeValue createManyFloatAttributeValue() {
		ManyFloatAttributeValueImpl manyFloatAttributeValue = new ManyFloatAttributeValueImpl();
		return manyFloatAttributeValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ManyFloatObjectAttributeValue createManyFloatObjectAttributeValue() {
		ManyFloatObjectAttributeValueImpl manyFloatObjectAttributeValue = new ManyFloatObjectAttributeValueImpl();
		return manyFloatObjectAttributeValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ManyStringAttributeValue createManyStringAttributeValue() {
		ManyStringAttributeValueImpl manyStringAttributeValue = new ManyStringAttributeValueImpl();
		return manyStringAttributeValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SingleReferenceValue createSingleReferenceValue() {
		SingleReferenceValueImpl singleReferenceValue = new SingleReferenceValueImpl();
		return singleReferenceValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ManyReferenceValue createManyReferenceValue() {
		ManyReferenceValueImpl manyReferenceValue = new ManyReferenceValueImpl();
		return manyReferenceValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SingleObjectValue createSingleObjectValue() {
		SingleObjectValueImpl singleObjectValue = new SingleObjectValueImpl();
		return singleObjectValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ManyObjectValue createManyObjectValue() {
		ManyObjectValueImpl manyObjectValue = new ManyObjectValueImpl();
		return manyObjectValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ValuePackage getValuePackage() {
		return (ValuePackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static ValuePackage getPackage() {
		return ValuePackage.eINSTANCE;
	}

} //ValueFactoryImpl
