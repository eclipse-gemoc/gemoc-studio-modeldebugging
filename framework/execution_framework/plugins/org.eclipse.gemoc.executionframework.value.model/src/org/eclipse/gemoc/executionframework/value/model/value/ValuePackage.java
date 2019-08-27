/**
 */
package org.eclipse.gemoc.executionframework.value.model.value;

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
 * @see org.eclipse.gemoc.executionframework.value.model.value.ValueFactory
 * @model kind="package"
 * @generated
 */
public interface ValuePackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "value";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.eclipse.org/gemoc/value";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "value";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ValuePackage eINSTANCE = org.eclipse.gemoc.executionframework.value.model.value.impl.ValuePackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.gemoc.executionframework.value.model.value.impl.ValueImpl <em>Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gemoc.executionframework.value.model.value.impl.ValueImpl
	 * @see org.eclipse.gemoc.executionframework.value.model.value.impl.ValuePackageImpl#getValue()
	 * @generated
	 */
	int VALUE = 0;

	/**
	 * The number of structural features of the '<em>Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.gemoc.executionframework.value.model.value.impl.AttributeValueImpl <em>Attribute Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gemoc.executionframework.value.model.value.impl.AttributeValueImpl
	 * @see org.eclipse.gemoc.executionframework.value.model.value.impl.ValuePackageImpl#getAttributeValue()
	 * @generated
	 */
	int ATTRIBUTE_VALUE = 1;

	/**
	 * The number of structural features of the '<em>Attribute Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_VALUE_FEATURE_COUNT = VALUE_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Attribute Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_VALUE_OPERATION_COUNT = VALUE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.gemoc.executionframework.value.model.value.impl.ReferenceValueImpl <em>Reference Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gemoc.executionframework.value.model.value.impl.ReferenceValueImpl
	 * @see org.eclipse.gemoc.executionframework.value.model.value.impl.ValuePackageImpl#getReferenceValue()
	 * @generated
	 */
	int REFERENCE_VALUE = 2;

	/**
	 * The number of structural features of the '<em>Reference Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_VALUE_FEATURE_COUNT = VALUE_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Reference Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_VALUE_OPERATION_COUNT = VALUE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.gemoc.executionframework.value.model.value.impl.BooleanAttributeValueImpl <em>Boolean Attribute Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gemoc.executionframework.value.model.value.impl.BooleanAttributeValueImpl
	 * @see org.eclipse.gemoc.executionframework.value.model.value.impl.ValuePackageImpl#getBooleanAttributeValue()
	 * @generated
	 */
	int BOOLEAN_ATTRIBUTE_VALUE = 3;

	/**
	 * The feature id for the '<em><b>Attribute Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_ATTRIBUTE_VALUE__ATTRIBUTE_VALUE = ATTRIBUTE_VALUE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Boolean Attribute Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_ATTRIBUTE_VALUE_FEATURE_COUNT = ATTRIBUTE_VALUE_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Boolean Attribute Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_ATTRIBUTE_VALUE_OPERATION_COUNT = ATTRIBUTE_VALUE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.gemoc.executionframework.value.model.value.impl.BooleanObjectAttributeValueImpl <em>Boolean Object Attribute Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gemoc.executionframework.value.model.value.impl.BooleanObjectAttributeValueImpl
	 * @see org.eclipse.gemoc.executionframework.value.model.value.impl.ValuePackageImpl#getBooleanObjectAttributeValue()
	 * @generated
	 */
	int BOOLEAN_OBJECT_ATTRIBUTE_VALUE = 4;

	/**
	 * The feature id for the '<em><b>Attribute Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_OBJECT_ATTRIBUTE_VALUE__ATTRIBUTE_VALUE = ATTRIBUTE_VALUE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Boolean Object Attribute Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_OBJECT_ATTRIBUTE_VALUE_FEATURE_COUNT = ATTRIBUTE_VALUE_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Boolean Object Attribute Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_OBJECT_ATTRIBUTE_VALUE_OPERATION_COUNT = ATTRIBUTE_VALUE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.gemoc.executionframework.value.model.value.impl.IntegerAttributeValueImpl <em>Integer Attribute Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gemoc.executionframework.value.model.value.impl.IntegerAttributeValueImpl
	 * @see org.eclipse.gemoc.executionframework.value.model.value.impl.ValuePackageImpl#getIntegerAttributeValue()
	 * @generated
	 */
	int INTEGER_ATTRIBUTE_VALUE = 5;

	/**
	 * The feature id for the '<em><b>Attribute Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_ATTRIBUTE_VALUE__ATTRIBUTE_VALUE = ATTRIBUTE_VALUE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Integer Attribute Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_ATTRIBUTE_VALUE_FEATURE_COUNT = ATTRIBUTE_VALUE_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Integer Attribute Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_ATTRIBUTE_VALUE_OPERATION_COUNT = ATTRIBUTE_VALUE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.gemoc.executionframework.value.model.value.impl.IntegerObjectAttributeValueImpl <em>Integer Object Attribute Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gemoc.executionframework.value.model.value.impl.IntegerObjectAttributeValueImpl
	 * @see org.eclipse.gemoc.executionframework.value.model.value.impl.ValuePackageImpl#getIntegerObjectAttributeValue()
	 * @generated
	 */
	int INTEGER_OBJECT_ATTRIBUTE_VALUE = 6;

	/**
	 * The feature id for the '<em><b>Attribute Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_OBJECT_ATTRIBUTE_VALUE__ATTRIBUTE_VALUE = ATTRIBUTE_VALUE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Integer Object Attribute Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_OBJECT_ATTRIBUTE_VALUE_FEATURE_COUNT = ATTRIBUTE_VALUE_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Integer Object Attribute Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_OBJECT_ATTRIBUTE_VALUE_OPERATION_COUNT = ATTRIBUTE_VALUE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.gemoc.executionframework.value.model.value.impl.LongAttributeValueImpl <em>Long Attribute Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gemoc.executionframework.value.model.value.impl.LongAttributeValueImpl
	 * @see org.eclipse.gemoc.executionframework.value.model.value.impl.ValuePackageImpl#getLongAttributeValue()
	 * @generated
	 */
	int LONG_ATTRIBUTE_VALUE = 7;

	/**
	 * The feature id for the '<em><b>Attribute Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LONG_ATTRIBUTE_VALUE__ATTRIBUTE_VALUE = ATTRIBUTE_VALUE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Long Attribute Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LONG_ATTRIBUTE_VALUE_FEATURE_COUNT = ATTRIBUTE_VALUE_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Long Attribute Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LONG_ATTRIBUTE_VALUE_OPERATION_COUNT = ATTRIBUTE_VALUE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.gemoc.executionframework.value.model.value.impl.LongObjectAttributeValueImpl <em>Long Object Attribute Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gemoc.executionframework.value.model.value.impl.LongObjectAttributeValueImpl
	 * @see org.eclipse.gemoc.executionframework.value.model.value.impl.ValuePackageImpl#getLongObjectAttributeValue()
	 * @generated
	 */
	int LONG_OBJECT_ATTRIBUTE_VALUE = 8;

	/**
	 * The feature id for the '<em><b>Attribute Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LONG_OBJECT_ATTRIBUTE_VALUE__ATTRIBUTE_VALUE = ATTRIBUTE_VALUE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Long Object Attribute Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LONG_OBJECT_ATTRIBUTE_VALUE_FEATURE_COUNT = ATTRIBUTE_VALUE_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Long Object Attribute Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LONG_OBJECT_ATTRIBUTE_VALUE_OPERATION_COUNT = ATTRIBUTE_VALUE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.gemoc.executionframework.value.model.value.impl.FloatAttributeValueImpl <em>Float Attribute Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gemoc.executionframework.value.model.value.impl.FloatAttributeValueImpl
	 * @see org.eclipse.gemoc.executionframework.value.model.value.impl.ValuePackageImpl#getFloatAttributeValue()
	 * @generated
	 */
	int FLOAT_ATTRIBUTE_VALUE = 9;

	/**
	 * The feature id for the '<em><b>Attribute Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOAT_ATTRIBUTE_VALUE__ATTRIBUTE_VALUE = ATTRIBUTE_VALUE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Float Attribute Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOAT_ATTRIBUTE_VALUE_FEATURE_COUNT = ATTRIBUTE_VALUE_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Float Attribute Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOAT_ATTRIBUTE_VALUE_OPERATION_COUNT = ATTRIBUTE_VALUE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.gemoc.executionframework.value.model.value.impl.FloatObjectAttributeValueImpl <em>Float Object Attribute Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gemoc.executionframework.value.model.value.impl.FloatObjectAttributeValueImpl
	 * @see org.eclipse.gemoc.executionframework.value.model.value.impl.ValuePackageImpl#getFloatObjectAttributeValue()
	 * @generated
	 */
	int FLOAT_OBJECT_ATTRIBUTE_VALUE = 10;

	/**
	 * The feature id for the '<em><b>Attribute Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOAT_OBJECT_ATTRIBUTE_VALUE__ATTRIBUTE_VALUE = ATTRIBUTE_VALUE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Float Object Attribute Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOAT_OBJECT_ATTRIBUTE_VALUE_FEATURE_COUNT = ATTRIBUTE_VALUE_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Float Object Attribute Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOAT_OBJECT_ATTRIBUTE_VALUE_OPERATION_COUNT = ATTRIBUTE_VALUE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.gemoc.executionframework.value.model.value.impl.StringAttributeValueImpl <em>String Attribute Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gemoc.executionframework.value.model.value.impl.StringAttributeValueImpl
	 * @see org.eclipse.gemoc.executionframework.value.model.value.impl.ValuePackageImpl#getStringAttributeValue()
	 * @generated
	 */
	int STRING_ATTRIBUTE_VALUE = 11;

	/**
	 * The feature id for the '<em><b>Attribute Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_ATTRIBUTE_VALUE__ATTRIBUTE_VALUE = ATTRIBUTE_VALUE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>String Attribute Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_ATTRIBUTE_VALUE_FEATURE_COUNT = ATTRIBUTE_VALUE_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>String Attribute Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_ATTRIBUTE_VALUE_OPERATION_COUNT = ATTRIBUTE_VALUE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.gemoc.executionframework.value.model.value.impl.ManyBooleanAttributeValueImpl <em>Many Boolean Attribute Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gemoc.executionframework.value.model.value.impl.ManyBooleanAttributeValueImpl
	 * @see org.eclipse.gemoc.executionframework.value.model.value.impl.ValuePackageImpl#getManyBooleanAttributeValue()
	 * @generated
	 */
	int MANY_BOOLEAN_ATTRIBUTE_VALUE = 12;

	/**
	 * The feature id for the '<em><b>Attribute Value</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANY_BOOLEAN_ATTRIBUTE_VALUE__ATTRIBUTE_VALUE = ATTRIBUTE_VALUE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Many Boolean Attribute Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANY_BOOLEAN_ATTRIBUTE_VALUE_FEATURE_COUNT = ATTRIBUTE_VALUE_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Many Boolean Attribute Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANY_BOOLEAN_ATTRIBUTE_VALUE_OPERATION_COUNT = ATTRIBUTE_VALUE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.gemoc.executionframework.value.model.value.impl.ManyBooleanObjectAttributeValueImpl <em>Many Boolean Object Attribute Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gemoc.executionframework.value.model.value.impl.ManyBooleanObjectAttributeValueImpl
	 * @see org.eclipse.gemoc.executionframework.value.model.value.impl.ValuePackageImpl#getManyBooleanObjectAttributeValue()
	 * @generated
	 */
	int MANY_BOOLEAN_OBJECT_ATTRIBUTE_VALUE = 13;

	/**
	 * The feature id for the '<em><b>Attribute Value</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANY_BOOLEAN_OBJECT_ATTRIBUTE_VALUE__ATTRIBUTE_VALUE = ATTRIBUTE_VALUE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Many Boolean Object Attribute Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANY_BOOLEAN_OBJECT_ATTRIBUTE_VALUE_FEATURE_COUNT = ATTRIBUTE_VALUE_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Many Boolean Object Attribute Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANY_BOOLEAN_OBJECT_ATTRIBUTE_VALUE_OPERATION_COUNT = ATTRIBUTE_VALUE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.gemoc.executionframework.value.model.value.impl.ManyIntegerAttributeValueImpl <em>Many Integer Attribute Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gemoc.executionframework.value.model.value.impl.ManyIntegerAttributeValueImpl
	 * @see org.eclipse.gemoc.executionframework.value.model.value.impl.ValuePackageImpl#getManyIntegerAttributeValue()
	 * @generated
	 */
	int MANY_INTEGER_ATTRIBUTE_VALUE = 14;

	/**
	 * The feature id for the '<em><b>Attribute Value</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANY_INTEGER_ATTRIBUTE_VALUE__ATTRIBUTE_VALUE = ATTRIBUTE_VALUE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Many Integer Attribute Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANY_INTEGER_ATTRIBUTE_VALUE_FEATURE_COUNT = ATTRIBUTE_VALUE_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Many Integer Attribute Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANY_INTEGER_ATTRIBUTE_VALUE_OPERATION_COUNT = ATTRIBUTE_VALUE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.gemoc.executionframework.value.model.value.impl.ManyIntegerObjectAttributeValueImpl <em>Many Integer Object Attribute Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gemoc.executionframework.value.model.value.impl.ManyIntegerObjectAttributeValueImpl
	 * @see org.eclipse.gemoc.executionframework.value.model.value.impl.ValuePackageImpl#getManyIntegerObjectAttributeValue()
	 * @generated
	 */
	int MANY_INTEGER_OBJECT_ATTRIBUTE_VALUE = 15;

	/**
	 * The feature id for the '<em><b>Attribute Value</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANY_INTEGER_OBJECT_ATTRIBUTE_VALUE__ATTRIBUTE_VALUE = ATTRIBUTE_VALUE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Many Integer Object Attribute Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANY_INTEGER_OBJECT_ATTRIBUTE_VALUE_FEATURE_COUNT = ATTRIBUTE_VALUE_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Many Integer Object Attribute Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANY_INTEGER_OBJECT_ATTRIBUTE_VALUE_OPERATION_COUNT = ATTRIBUTE_VALUE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.gemoc.executionframework.value.model.value.impl.ManyLongAttributeValueImpl <em>Many Long Attribute Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gemoc.executionframework.value.model.value.impl.ManyLongAttributeValueImpl
	 * @see org.eclipse.gemoc.executionframework.value.model.value.impl.ValuePackageImpl#getManyLongAttributeValue()
	 * @generated
	 */
	int MANY_LONG_ATTRIBUTE_VALUE = 16;

	/**
	 * The feature id for the '<em><b>Attribute Value</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANY_LONG_ATTRIBUTE_VALUE__ATTRIBUTE_VALUE = ATTRIBUTE_VALUE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Many Long Attribute Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANY_LONG_ATTRIBUTE_VALUE_FEATURE_COUNT = ATTRIBUTE_VALUE_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Many Long Attribute Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANY_LONG_ATTRIBUTE_VALUE_OPERATION_COUNT = ATTRIBUTE_VALUE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.gemoc.executionframework.value.model.value.impl.ManyLongObjectAttributeValueImpl <em>Many Long Object Attribute Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gemoc.executionframework.value.model.value.impl.ManyLongObjectAttributeValueImpl
	 * @see org.eclipse.gemoc.executionframework.value.model.value.impl.ValuePackageImpl#getManyLongObjectAttributeValue()
	 * @generated
	 */
	int MANY_LONG_OBJECT_ATTRIBUTE_VALUE = 17;

	/**
	 * The feature id for the '<em><b>Attribute Value</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANY_LONG_OBJECT_ATTRIBUTE_VALUE__ATTRIBUTE_VALUE = ATTRIBUTE_VALUE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Many Long Object Attribute Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANY_LONG_OBJECT_ATTRIBUTE_VALUE_FEATURE_COUNT = ATTRIBUTE_VALUE_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Many Long Object Attribute Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANY_LONG_OBJECT_ATTRIBUTE_VALUE_OPERATION_COUNT = ATTRIBUTE_VALUE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.gemoc.executionframework.value.model.value.impl.ManyFloatAttributeValueImpl <em>Many Float Attribute Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gemoc.executionframework.value.model.value.impl.ManyFloatAttributeValueImpl
	 * @see org.eclipse.gemoc.executionframework.value.model.value.impl.ValuePackageImpl#getManyFloatAttributeValue()
	 * @generated
	 */
	int MANY_FLOAT_ATTRIBUTE_VALUE = 18;

	/**
	 * The feature id for the '<em><b>Attribute Value</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANY_FLOAT_ATTRIBUTE_VALUE__ATTRIBUTE_VALUE = ATTRIBUTE_VALUE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Many Float Attribute Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANY_FLOAT_ATTRIBUTE_VALUE_FEATURE_COUNT = ATTRIBUTE_VALUE_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Many Float Attribute Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANY_FLOAT_ATTRIBUTE_VALUE_OPERATION_COUNT = ATTRIBUTE_VALUE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.gemoc.executionframework.value.model.value.impl.ManyFloatObjectAttributeValueImpl <em>Many Float Object Attribute Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gemoc.executionframework.value.model.value.impl.ManyFloatObjectAttributeValueImpl
	 * @see org.eclipse.gemoc.executionframework.value.model.value.impl.ValuePackageImpl#getManyFloatObjectAttributeValue()
	 * @generated
	 */
	int MANY_FLOAT_OBJECT_ATTRIBUTE_VALUE = 19;

	/**
	 * The feature id for the '<em><b>Attribute Value</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANY_FLOAT_OBJECT_ATTRIBUTE_VALUE__ATTRIBUTE_VALUE = ATTRIBUTE_VALUE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Many Float Object Attribute Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANY_FLOAT_OBJECT_ATTRIBUTE_VALUE_FEATURE_COUNT = ATTRIBUTE_VALUE_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Many Float Object Attribute Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANY_FLOAT_OBJECT_ATTRIBUTE_VALUE_OPERATION_COUNT = ATTRIBUTE_VALUE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.gemoc.executionframework.value.model.value.impl.ManyStringAttributeValueImpl <em>Many String Attribute Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gemoc.executionframework.value.model.value.impl.ManyStringAttributeValueImpl
	 * @see org.eclipse.gemoc.executionframework.value.model.value.impl.ValuePackageImpl#getManyStringAttributeValue()
	 * @generated
	 */
	int MANY_STRING_ATTRIBUTE_VALUE = 20;

	/**
	 * The feature id for the '<em><b>Attribute Value</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANY_STRING_ATTRIBUTE_VALUE__ATTRIBUTE_VALUE = ATTRIBUTE_VALUE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Many String Attribute Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANY_STRING_ATTRIBUTE_VALUE_FEATURE_COUNT = ATTRIBUTE_VALUE_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Many String Attribute Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANY_STRING_ATTRIBUTE_VALUE_OPERATION_COUNT = ATTRIBUTE_VALUE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.gemoc.executionframework.value.model.value.impl.SingleReferenceValueImpl <em>Single Reference Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gemoc.executionframework.value.model.value.impl.SingleReferenceValueImpl
	 * @see org.eclipse.gemoc.executionframework.value.model.value.impl.ValuePackageImpl#getSingleReferenceValue()
	 * @generated
	 */
	int SINGLE_REFERENCE_VALUE = 21;

	/**
	 * The feature id for the '<em><b>Reference Value</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SINGLE_REFERENCE_VALUE__REFERENCE_VALUE = REFERENCE_VALUE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Single Reference Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SINGLE_REFERENCE_VALUE_FEATURE_COUNT = REFERENCE_VALUE_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Single Reference Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SINGLE_REFERENCE_VALUE_OPERATION_COUNT = REFERENCE_VALUE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.gemoc.executionframework.value.model.value.impl.ManyReferenceValueImpl <em>Many Reference Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gemoc.executionframework.value.model.value.impl.ManyReferenceValueImpl
	 * @see org.eclipse.gemoc.executionframework.value.model.value.impl.ValuePackageImpl#getManyReferenceValue()
	 * @generated
	 */
	int MANY_REFERENCE_VALUE = 22;

	/**
	 * The feature id for the '<em><b>Reference Values</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANY_REFERENCE_VALUE__REFERENCE_VALUES = REFERENCE_VALUE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Many Reference Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANY_REFERENCE_VALUE_FEATURE_COUNT = REFERENCE_VALUE_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Many Reference Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANY_REFERENCE_VALUE_OPERATION_COUNT = REFERENCE_VALUE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.gemoc.executionframework.value.model.value.impl.SingleObjectValueImpl <em>Single Object Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gemoc.executionframework.value.model.value.impl.SingleObjectValueImpl
	 * @see org.eclipse.gemoc.executionframework.value.model.value.impl.ValuePackageImpl#getSingleObjectValue()
	 * @generated
	 */
	int SINGLE_OBJECT_VALUE = 23;

	/**
	 * The feature id for the '<em><b>Object Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SINGLE_OBJECT_VALUE__OBJECT_VALUE = REFERENCE_VALUE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Single Object Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SINGLE_OBJECT_VALUE_FEATURE_COUNT = REFERENCE_VALUE_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Single Object Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SINGLE_OBJECT_VALUE_OPERATION_COUNT = REFERENCE_VALUE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.gemoc.executionframework.value.model.value.impl.ManyObjectValueImpl <em>Many Object Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gemoc.executionframework.value.model.value.impl.ManyObjectValueImpl
	 * @see org.eclipse.gemoc.executionframework.value.model.value.impl.ValuePackageImpl#getManyObjectValue()
	 * @generated
	 */
	int MANY_OBJECT_VALUE = 24;

	/**
	 * The feature id for the '<em><b>Object Values</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANY_OBJECT_VALUE__OBJECT_VALUES = REFERENCE_VALUE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Many Object Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANY_OBJECT_VALUE_FEATURE_COUNT = REFERENCE_VALUE_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Many Object Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANY_OBJECT_VALUE_OPERATION_COUNT = REFERENCE_VALUE_OPERATION_COUNT + 0;


	/**
	 * Returns the meta object for class '{@link org.eclipse.gemoc.executionframework.value.model.value.Value <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Value</em>'.
	 * @see org.eclipse.gemoc.executionframework.value.model.value.Value
	 * @generated
	 */
	EClass getValue();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gemoc.executionframework.value.model.value.AttributeValue <em>Attribute Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Attribute Value</em>'.
	 * @see org.eclipse.gemoc.executionframework.value.model.value.AttributeValue
	 * @generated
	 */
	EClass getAttributeValue();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gemoc.executionframework.value.model.value.ReferenceValue <em>Reference Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Reference Value</em>'.
	 * @see org.eclipse.gemoc.executionframework.value.model.value.ReferenceValue
	 * @generated
	 */
	EClass getReferenceValue();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gemoc.executionframework.value.model.value.BooleanAttributeValue <em>Boolean Attribute Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Boolean Attribute Value</em>'.
	 * @see org.eclipse.gemoc.executionframework.value.model.value.BooleanAttributeValue
	 * @generated
	 */
	EClass getBooleanAttributeValue();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.gemoc.executionframework.value.model.value.BooleanAttributeValue#isAttributeValue <em>Attribute Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attribute Value</em>'.
	 * @see org.eclipse.gemoc.executionframework.value.model.value.BooleanAttributeValue#isAttributeValue()
	 * @see #getBooleanAttributeValue()
	 * @generated
	 */
	EAttribute getBooleanAttributeValue_AttributeValue();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gemoc.executionframework.value.model.value.BooleanObjectAttributeValue <em>Boolean Object Attribute Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Boolean Object Attribute Value</em>'.
	 * @see org.eclipse.gemoc.executionframework.value.model.value.BooleanObjectAttributeValue
	 * @generated
	 */
	EClass getBooleanObjectAttributeValue();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.gemoc.executionframework.value.model.value.BooleanObjectAttributeValue#getAttributeValue <em>Attribute Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attribute Value</em>'.
	 * @see org.eclipse.gemoc.executionframework.value.model.value.BooleanObjectAttributeValue#getAttributeValue()
	 * @see #getBooleanObjectAttributeValue()
	 * @generated
	 */
	EAttribute getBooleanObjectAttributeValue_AttributeValue();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gemoc.executionframework.value.model.value.IntegerAttributeValue <em>Integer Attribute Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Integer Attribute Value</em>'.
	 * @see org.eclipse.gemoc.executionframework.value.model.value.IntegerAttributeValue
	 * @generated
	 */
	EClass getIntegerAttributeValue();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.gemoc.executionframework.value.model.value.IntegerAttributeValue#getAttributeValue <em>Attribute Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attribute Value</em>'.
	 * @see org.eclipse.gemoc.executionframework.value.model.value.IntegerAttributeValue#getAttributeValue()
	 * @see #getIntegerAttributeValue()
	 * @generated
	 */
	EAttribute getIntegerAttributeValue_AttributeValue();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gemoc.executionframework.value.model.value.IntegerObjectAttributeValue <em>Integer Object Attribute Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Integer Object Attribute Value</em>'.
	 * @see org.eclipse.gemoc.executionframework.value.model.value.IntegerObjectAttributeValue
	 * @generated
	 */
	EClass getIntegerObjectAttributeValue();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.gemoc.executionframework.value.model.value.IntegerObjectAttributeValue#getAttributeValue <em>Attribute Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attribute Value</em>'.
	 * @see org.eclipse.gemoc.executionframework.value.model.value.IntegerObjectAttributeValue#getAttributeValue()
	 * @see #getIntegerObjectAttributeValue()
	 * @generated
	 */
	EAttribute getIntegerObjectAttributeValue_AttributeValue();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gemoc.executionframework.value.model.value.LongAttributeValue <em>Long Attribute Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Long Attribute Value</em>'.
	 * @see org.eclipse.gemoc.executionframework.value.model.value.LongAttributeValue
	 * @generated
	 */
	EClass getLongAttributeValue();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.gemoc.executionframework.value.model.value.LongAttributeValue#getAttributeValue <em>Attribute Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attribute Value</em>'.
	 * @see org.eclipse.gemoc.executionframework.value.model.value.LongAttributeValue#getAttributeValue()
	 * @see #getLongAttributeValue()
	 * @generated
	 */
	EAttribute getLongAttributeValue_AttributeValue();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gemoc.executionframework.value.model.value.LongObjectAttributeValue <em>Long Object Attribute Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Long Object Attribute Value</em>'.
	 * @see org.eclipse.gemoc.executionframework.value.model.value.LongObjectAttributeValue
	 * @generated
	 */
	EClass getLongObjectAttributeValue();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.gemoc.executionframework.value.model.value.LongObjectAttributeValue#getAttributeValue <em>Attribute Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attribute Value</em>'.
	 * @see org.eclipse.gemoc.executionframework.value.model.value.LongObjectAttributeValue#getAttributeValue()
	 * @see #getLongObjectAttributeValue()
	 * @generated
	 */
	EAttribute getLongObjectAttributeValue_AttributeValue();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gemoc.executionframework.value.model.value.FloatAttributeValue <em>Float Attribute Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Float Attribute Value</em>'.
	 * @see org.eclipse.gemoc.executionframework.value.model.value.FloatAttributeValue
	 * @generated
	 */
	EClass getFloatAttributeValue();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.gemoc.executionframework.value.model.value.FloatAttributeValue#getAttributeValue <em>Attribute Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attribute Value</em>'.
	 * @see org.eclipse.gemoc.executionframework.value.model.value.FloatAttributeValue#getAttributeValue()
	 * @see #getFloatAttributeValue()
	 * @generated
	 */
	EAttribute getFloatAttributeValue_AttributeValue();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gemoc.executionframework.value.model.value.FloatObjectAttributeValue <em>Float Object Attribute Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Float Object Attribute Value</em>'.
	 * @see org.eclipse.gemoc.executionframework.value.model.value.FloatObjectAttributeValue
	 * @generated
	 */
	EClass getFloatObjectAttributeValue();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.gemoc.executionframework.value.model.value.FloatObjectAttributeValue#getAttributeValue <em>Attribute Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attribute Value</em>'.
	 * @see org.eclipse.gemoc.executionframework.value.model.value.FloatObjectAttributeValue#getAttributeValue()
	 * @see #getFloatObjectAttributeValue()
	 * @generated
	 */
	EAttribute getFloatObjectAttributeValue_AttributeValue();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gemoc.executionframework.value.model.value.StringAttributeValue <em>String Attribute Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>String Attribute Value</em>'.
	 * @see org.eclipse.gemoc.executionframework.value.model.value.StringAttributeValue
	 * @generated
	 */
	EClass getStringAttributeValue();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.gemoc.executionframework.value.model.value.StringAttributeValue#getAttributeValue <em>Attribute Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attribute Value</em>'.
	 * @see org.eclipse.gemoc.executionframework.value.model.value.StringAttributeValue#getAttributeValue()
	 * @see #getStringAttributeValue()
	 * @generated
	 */
	EAttribute getStringAttributeValue_AttributeValue();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gemoc.executionframework.value.model.value.ManyBooleanAttributeValue <em>Many Boolean Attribute Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Many Boolean Attribute Value</em>'.
	 * @see org.eclipse.gemoc.executionframework.value.model.value.ManyBooleanAttributeValue
	 * @generated
	 */
	EClass getManyBooleanAttributeValue();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.gemoc.executionframework.value.model.value.ManyBooleanAttributeValue#getAttributeValue <em>Attribute Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Attribute Value</em>'.
	 * @see org.eclipse.gemoc.executionframework.value.model.value.ManyBooleanAttributeValue#getAttributeValue()
	 * @see #getManyBooleanAttributeValue()
	 * @generated
	 */
	EAttribute getManyBooleanAttributeValue_AttributeValue();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gemoc.executionframework.value.model.value.ManyBooleanObjectAttributeValue <em>Many Boolean Object Attribute Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Many Boolean Object Attribute Value</em>'.
	 * @see org.eclipse.gemoc.executionframework.value.model.value.ManyBooleanObjectAttributeValue
	 * @generated
	 */
	EClass getManyBooleanObjectAttributeValue();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.gemoc.executionframework.value.model.value.ManyBooleanObjectAttributeValue#getAttributeValue <em>Attribute Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Attribute Value</em>'.
	 * @see org.eclipse.gemoc.executionframework.value.model.value.ManyBooleanObjectAttributeValue#getAttributeValue()
	 * @see #getManyBooleanObjectAttributeValue()
	 * @generated
	 */
	EAttribute getManyBooleanObjectAttributeValue_AttributeValue();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gemoc.executionframework.value.model.value.ManyIntegerAttributeValue <em>Many Integer Attribute Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Many Integer Attribute Value</em>'.
	 * @see org.eclipse.gemoc.executionframework.value.model.value.ManyIntegerAttributeValue
	 * @generated
	 */
	EClass getManyIntegerAttributeValue();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.gemoc.executionframework.value.model.value.ManyIntegerAttributeValue#getAttributeValue <em>Attribute Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Attribute Value</em>'.
	 * @see org.eclipse.gemoc.executionframework.value.model.value.ManyIntegerAttributeValue#getAttributeValue()
	 * @see #getManyIntegerAttributeValue()
	 * @generated
	 */
	EAttribute getManyIntegerAttributeValue_AttributeValue();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gemoc.executionframework.value.model.value.ManyIntegerObjectAttributeValue <em>Many Integer Object Attribute Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Many Integer Object Attribute Value</em>'.
	 * @see org.eclipse.gemoc.executionframework.value.model.value.ManyIntegerObjectAttributeValue
	 * @generated
	 */
	EClass getManyIntegerObjectAttributeValue();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.gemoc.executionframework.value.model.value.ManyIntegerObjectAttributeValue#getAttributeValue <em>Attribute Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Attribute Value</em>'.
	 * @see org.eclipse.gemoc.executionframework.value.model.value.ManyIntegerObjectAttributeValue#getAttributeValue()
	 * @see #getManyIntegerObjectAttributeValue()
	 * @generated
	 */
	EAttribute getManyIntegerObjectAttributeValue_AttributeValue();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gemoc.executionframework.value.model.value.ManyLongAttributeValue <em>Many Long Attribute Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Many Long Attribute Value</em>'.
	 * @see org.eclipse.gemoc.executionframework.value.model.value.ManyLongAttributeValue
	 * @generated
	 */
	EClass getManyLongAttributeValue();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.gemoc.executionframework.value.model.value.ManyLongAttributeValue#getAttributeValue <em>Attribute Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Attribute Value</em>'.
	 * @see org.eclipse.gemoc.executionframework.value.model.value.ManyLongAttributeValue#getAttributeValue()
	 * @see #getManyLongAttributeValue()
	 * @generated
	 */
	EAttribute getManyLongAttributeValue_AttributeValue();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gemoc.executionframework.value.model.value.ManyLongObjectAttributeValue <em>Many Long Object Attribute Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Many Long Object Attribute Value</em>'.
	 * @see org.eclipse.gemoc.executionframework.value.model.value.ManyLongObjectAttributeValue
	 * @generated
	 */
	EClass getManyLongObjectAttributeValue();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.gemoc.executionframework.value.model.value.ManyLongObjectAttributeValue#getAttributeValue <em>Attribute Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Attribute Value</em>'.
	 * @see org.eclipse.gemoc.executionframework.value.model.value.ManyLongObjectAttributeValue#getAttributeValue()
	 * @see #getManyLongObjectAttributeValue()
	 * @generated
	 */
	EAttribute getManyLongObjectAttributeValue_AttributeValue();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gemoc.executionframework.value.model.value.ManyFloatAttributeValue <em>Many Float Attribute Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Many Float Attribute Value</em>'.
	 * @see org.eclipse.gemoc.executionframework.value.model.value.ManyFloatAttributeValue
	 * @generated
	 */
	EClass getManyFloatAttributeValue();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.gemoc.executionframework.value.model.value.ManyFloatAttributeValue#getAttributeValue <em>Attribute Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Attribute Value</em>'.
	 * @see org.eclipse.gemoc.executionframework.value.model.value.ManyFloatAttributeValue#getAttributeValue()
	 * @see #getManyFloatAttributeValue()
	 * @generated
	 */
	EAttribute getManyFloatAttributeValue_AttributeValue();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gemoc.executionframework.value.model.value.ManyFloatObjectAttributeValue <em>Many Float Object Attribute Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Many Float Object Attribute Value</em>'.
	 * @see org.eclipse.gemoc.executionframework.value.model.value.ManyFloatObjectAttributeValue
	 * @generated
	 */
	EClass getManyFloatObjectAttributeValue();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.gemoc.executionframework.value.model.value.ManyFloatObjectAttributeValue#getAttributeValue <em>Attribute Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Attribute Value</em>'.
	 * @see org.eclipse.gemoc.executionframework.value.model.value.ManyFloatObjectAttributeValue#getAttributeValue()
	 * @see #getManyFloatObjectAttributeValue()
	 * @generated
	 */
	EAttribute getManyFloatObjectAttributeValue_AttributeValue();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gemoc.executionframework.value.model.value.ManyStringAttributeValue <em>Many String Attribute Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Many String Attribute Value</em>'.
	 * @see org.eclipse.gemoc.executionframework.value.model.value.ManyStringAttributeValue
	 * @generated
	 */
	EClass getManyStringAttributeValue();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.gemoc.executionframework.value.model.value.ManyStringAttributeValue#getAttributeValue <em>Attribute Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Attribute Value</em>'.
	 * @see org.eclipse.gemoc.executionframework.value.model.value.ManyStringAttributeValue#getAttributeValue()
	 * @see #getManyStringAttributeValue()
	 * @generated
	 */
	EAttribute getManyStringAttributeValue_AttributeValue();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gemoc.executionframework.value.model.value.SingleReferenceValue <em>Single Reference Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Single Reference Value</em>'.
	 * @see org.eclipse.gemoc.executionframework.value.model.value.SingleReferenceValue
	 * @generated
	 */
	EClass getSingleReferenceValue();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.gemoc.executionframework.value.model.value.SingleReferenceValue#getReferenceValue <em>Reference Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Reference Value</em>'.
	 * @see org.eclipse.gemoc.executionframework.value.model.value.SingleReferenceValue#getReferenceValue()
	 * @see #getSingleReferenceValue()
	 * @generated
	 */
	EReference getSingleReferenceValue_ReferenceValue();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gemoc.executionframework.value.model.value.ManyReferenceValue <em>Many Reference Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Many Reference Value</em>'.
	 * @see org.eclipse.gemoc.executionframework.value.model.value.ManyReferenceValue
	 * @generated
	 */
	EClass getManyReferenceValue();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.gemoc.executionframework.value.model.value.ManyReferenceValue#getReferenceValues <em>Reference Values</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Reference Values</em>'.
	 * @see org.eclipse.gemoc.executionframework.value.model.value.ManyReferenceValue#getReferenceValues()
	 * @see #getManyReferenceValue()
	 * @generated
	 */
	EReference getManyReferenceValue_ReferenceValues();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gemoc.executionframework.value.model.value.SingleObjectValue <em>Single Object Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Single Object Value</em>'.
	 * @see org.eclipse.gemoc.executionframework.value.model.value.SingleObjectValue
	 * @generated
	 */
	EClass getSingleObjectValue();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.gemoc.executionframework.value.model.value.SingleObjectValue#getObjectValue <em>Object Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Object Value</em>'.
	 * @see org.eclipse.gemoc.executionframework.value.model.value.SingleObjectValue#getObjectValue()
	 * @see #getSingleObjectValue()
	 * @generated
	 */
	EReference getSingleObjectValue_ObjectValue();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gemoc.executionframework.value.model.value.ManyObjectValue <em>Many Object Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Many Object Value</em>'.
	 * @see org.eclipse.gemoc.executionframework.value.model.value.ManyObjectValue
	 * @generated
	 */
	EClass getManyObjectValue();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.gemoc.executionframework.value.model.value.ManyObjectValue#getObjectValues <em>Object Values</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Object Values</em>'.
	 * @see org.eclipse.gemoc.executionframework.value.model.value.ManyObjectValue#getObjectValues()
	 * @see #getManyObjectValue()
	 * @generated
	 */
	EReference getManyObjectValue_ObjectValues();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	ValueFactory getValueFactory();

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
		 * The meta object literal for the '{@link org.eclipse.gemoc.executionframework.value.model.value.impl.ValueImpl <em>Value</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gemoc.executionframework.value.model.value.impl.ValueImpl
		 * @see org.eclipse.gemoc.executionframework.value.model.value.impl.ValuePackageImpl#getValue()
		 * @generated
		 */
		EClass VALUE = eINSTANCE.getValue();

		/**
		 * The meta object literal for the '{@link org.eclipse.gemoc.executionframework.value.model.value.impl.AttributeValueImpl <em>Attribute Value</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gemoc.executionframework.value.model.value.impl.AttributeValueImpl
		 * @see org.eclipse.gemoc.executionframework.value.model.value.impl.ValuePackageImpl#getAttributeValue()
		 * @generated
		 */
		EClass ATTRIBUTE_VALUE = eINSTANCE.getAttributeValue();

		/**
		 * The meta object literal for the '{@link org.eclipse.gemoc.executionframework.value.model.value.impl.ReferenceValueImpl <em>Reference Value</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gemoc.executionframework.value.model.value.impl.ReferenceValueImpl
		 * @see org.eclipse.gemoc.executionframework.value.model.value.impl.ValuePackageImpl#getReferenceValue()
		 * @generated
		 */
		EClass REFERENCE_VALUE = eINSTANCE.getReferenceValue();

		/**
		 * The meta object literal for the '{@link org.eclipse.gemoc.executionframework.value.model.value.impl.BooleanAttributeValueImpl <em>Boolean Attribute Value</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gemoc.executionframework.value.model.value.impl.BooleanAttributeValueImpl
		 * @see org.eclipse.gemoc.executionframework.value.model.value.impl.ValuePackageImpl#getBooleanAttributeValue()
		 * @generated
		 */
		EClass BOOLEAN_ATTRIBUTE_VALUE = eINSTANCE.getBooleanAttributeValue();

		/**
		 * The meta object literal for the '<em><b>Attribute Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BOOLEAN_ATTRIBUTE_VALUE__ATTRIBUTE_VALUE = eINSTANCE.getBooleanAttributeValue_AttributeValue();

		/**
		 * The meta object literal for the '{@link org.eclipse.gemoc.executionframework.value.model.value.impl.BooleanObjectAttributeValueImpl <em>Boolean Object Attribute Value</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gemoc.executionframework.value.model.value.impl.BooleanObjectAttributeValueImpl
		 * @see org.eclipse.gemoc.executionframework.value.model.value.impl.ValuePackageImpl#getBooleanObjectAttributeValue()
		 * @generated
		 */
		EClass BOOLEAN_OBJECT_ATTRIBUTE_VALUE = eINSTANCE.getBooleanObjectAttributeValue();

		/**
		 * The meta object literal for the '<em><b>Attribute Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BOOLEAN_OBJECT_ATTRIBUTE_VALUE__ATTRIBUTE_VALUE = eINSTANCE.getBooleanObjectAttributeValue_AttributeValue();

		/**
		 * The meta object literal for the '{@link org.eclipse.gemoc.executionframework.value.model.value.impl.IntegerAttributeValueImpl <em>Integer Attribute Value</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gemoc.executionframework.value.model.value.impl.IntegerAttributeValueImpl
		 * @see org.eclipse.gemoc.executionframework.value.model.value.impl.ValuePackageImpl#getIntegerAttributeValue()
		 * @generated
		 */
		EClass INTEGER_ATTRIBUTE_VALUE = eINSTANCE.getIntegerAttributeValue();

		/**
		 * The meta object literal for the '<em><b>Attribute Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INTEGER_ATTRIBUTE_VALUE__ATTRIBUTE_VALUE = eINSTANCE.getIntegerAttributeValue_AttributeValue();

		/**
		 * The meta object literal for the '{@link org.eclipse.gemoc.executionframework.value.model.value.impl.IntegerObjectAttributeValueImpl <em>Integer Object Attribute Value</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gemoc.executionframework.value.model.value.impl.IntegerObjectAttributeValueImpl
		 * @see org.eclipse.gemoc.executionframework.value.model.value.impl.ValuePackageImpl#getIntegerObjectAttributeValue()
		 * @generated
		 */
		EClass INTEGER_OBJECT_ATTRIBUTE_VALUE = eINSTANCE.getIntegerObjectAttributeValue();

		/**
		 * The meta object literal for the '<em><b>Attribute Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INTEGER_OBJECT_ATTRIBUTE_VALUE__ATTRIBUTE_VALUE = eINSTANCE.getIntegerObjectAttributeValue_AttributeValue();

		/**
		 * The meta object literal for the '{@link org.eclipse.gemoc.executionframework.value.model.value.impl.LongAttributeValueImpl <em>Long Attribute Value</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gemoc.executionframework.value.model.value.impl.LongAttributeValueImpl
		 * @see org.eclipse.gemoc.executionframework.value.model.value.impl.ValuePackageImpl#getLongAttributeValue()
		 * @generated
		 */
		EClass LONG_ATTRIBUTE_VALUE = eINSTANCE.getLongAttributeValue();

		/**
		 * The meta object literal for the '<em><b>Attribute Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LONG_ATTRIBUTE_VALUE__ATTRIBUTE_VALUE = eINSTANCE.getLongAttributeValue_AttributeValue();

		/**
		 * The meta object literal for the '{@link org.eclipse.gemoc.executionframework.value.model.value.impl.LongObjectAttributeValueImpl <em>Long Object Attribute Value</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gemoc.executionframework.value.model.value.impl.LongObjectAttributeValueImpl
		 * @see org.eclipse.gemoc.executionframework.value.model.value.impl.ValuePackageImpl#getLongObjectAttributeValue()
		 * @generated
		 */
		EClass LONG_OBJECT_ATTRIBUTE_VALUE = eINSTANCE.getLongObjectAttributeValue();

		/**
		 * The meta object literal for the '<em><b>Attribute Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LONG_OBJECT_ATTRIBUTE_VALUE__ATTRIBUTE_VALUE = eINSTANCE.getLongObjectAttributeValue_AttributeValue();

		/**
		 * The meta object literal for the '{@link org.eclipse.gemoc.executionframework.value.model.value.impl.FloatAttributeValueImpl <em>Float Attribute Value</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gemoc.executionframework.value.model.value.impl.FloatAttributeValueImpl
		 * @see org.eclipse.gemoc.executionframework.value.model.value.impl.ValuePackageImpl#getFloatAttributeValue()
		 * @generated
		 */
		EClass FLOAT_ATTRIBUTE_VALUE = eINSTANCE.getFloatAttributeValue();

		/**
		 * The meta object literal for the '<em><b>Attribute Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FLOAT_ATTRIBUTE_VALUE__ATTRIBUTE_VALUE = eINSTANCE.getFloatAttributeValue_AttributeValue();

		/**
		 * The meta object literal for the '{@link org.eclipse.gemoc.executionframework.value.model.value.impl.FloatObjectAttributeValueImpl <em>Float Object Attribute Value</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gemoc.executionframework.value.model.value.impl.FloatObjectAttributeValueImpl
		 * @see org.eclipse.gemoc.executionframework.value.model.value.impl.ValuePackageImpl#getFloatObjectAttributeValue()
		 * @generated
		 */
		EClass FLOAT_OBJECT_ATTRIBUTE_VALUE = eINSTANCE.getFloatObjectAttributeValue();

		/**
		 * The meta object literal for the '<em><b>Attribute Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FLOAT_OBJECT_ATTRIBUTE_VALUE__ATTRIBUTE_VALUE = eINSTANCE.getFloatObjectAttributeValue_AttributeValue();

		/**
		 * The meta object literal for the '{@link org.eclipse.gemoc.executionframework.value.model.value.impl.StringAttributeValueImpl <em>String Attribute Value</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gemoc.executionframework.value.model.value.impl.StringAttributeValueImpl
		 * @see org.eclipse.gemoc.executionframework.value.model.value.impl.ValuePackageImpl#getStringAttributeValue()
		 * @generated
		 */
		EClass STRING_ATTRIBUTE_VALUE = eINSTANCE.getStringAttributeValue();

		/**
		 * The meta object literal for the '<em><b>Attribute Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STRING_ATTRIBUTE_VALUE__ATTRIBUTE_VALUE = eINSTANCE.getStringAttributeValue_AttributeValue();

		/**
		 * The meta object literal for the '{@link org.eclipse.gemoc.executionframework.value.model.value.impl.ManyBooleanAttributeValueImpl <em>Many Boolean Attribute Value</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gemoc.executionframework.value.model.value.impl.ManyBooleanAttributeValueImpl
		 * @see org.eclipse.gemoc.executionframework.value.model.value.impl.ValuePackageImpl#getManyBooleanAttributeValue()
		 * @generated
		 */
		EClass MANY_BOOLEAN_ATTRIBUTE_VALUE = eINSTANCE.getManyBooleanAttributeValue();

		/**
		 * The meta object literal for the '<em><b>Attribute Value</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MANY_BOOLEAN_ATTRIBUTE_VALUE__ATTRIBUTE_VALUE = eINSTANCE.getManyBooleanAttributeValue_AttributeValue();

		/**
		 * The meta object literal for the '{@link org.eclipse.gemoc.executionframework.value.model.value.impl.ManyBooleanObjectAttributeValueImpl <em>Many Boolean Object Attribute Value</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gemoc.executionframework.value.model.value.impl.ManyBooleanObjectAttributeValueImpl
		 * @see org.eclipse.gemoc.executionframework.value.model.value.impl.ValuePackageImpl#getManyBooleanObjectAttributeValue()
		 * @generated
		 */
		EClass MANY_BOOLEAN_OBJECT_ATTRIBUTE_VALUE = eINSTANCE.getManyBooleanObjectAttributeValue();

		/**
		 * The meta object literal for the '<em><b>Attribute Value</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MANY_BOOLEAN_OBJECT_ATTRIBUTE_VALUE__ATTRIBUTE_VALUE = eINSTANCE.getManyBooleanObjectAttributeValue_AttributeValue();

		/**
		 * The meta object literal for the '{@link org.eclipse.gemoc.executionframework.value.model.value.impl.ManyIntegerAttributeValueImpl <em>Many Integer Attribute Value</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gemoc.executionframework.value.model.value.impl.ManyIntegerAttributeValueImpl
		 * @see org.eclipse.gemoc.executionframework.value.model.value.impl.ValuePackageImpl#getManyIntegerAttributeValue()
		 * @generated
		 */
		EClass MANY_INTEGER_ATTRIBUTE_VALUE = eINSTANCE.getManyIntegerAttributeValue();

		/**
		 * The meta object literal for the '<em><b>Attribute Value</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MANY_INTEGER_ATTRIBUTE_VALUE__ATTRIBUTE_VALUE = eINSTANCE.getManyIntegerAttributeValue_AttributeValue();

		/**
		 * The meta object literal for the '{@link org.eclipse.gemoc.executionframework.value.model.value.impl.ManyIntegerObjectAttributeValueImpl <em>Many Integer Object Attribute Value</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gemoc.executionframework.value.model.value.impl.ManyIntegerObjectAttributeValueImpl
		 * @see org.eclipse.gemoc.executionframework.value.model.value.impl.ValuePackageImpl#getManyIntegerObjectAttributeValue()
		 * @generated
		 */
		EClass MANY_INTEGER_OBJECT_ATTRIBUTE_VALUE = eINSTANCE.getManyIntegerObjectAttributeValue();

		/**
		 * The meta object literal for the '<em><b>Attribute Value</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MANY_INTEGER_OBJECT_ATTRIBUTE_VALUE__ATTRIBUTE_VALUE = eINSTANCE.getManyIntegerObjectAttributeValue_AttributeValue();

		/**
		 * The meta object literal for the '{@link org.eclipse.gemoc.executionframework.value.model.value.impl.ManyLongAttributeValueImpl <em>Many Long Attribute Value</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gemoc.executionframework.value.model.value.impl.ManyLongAttributeValueImpl
		 * @see org.eclipse.gemoc.executionframework.value.model.value.impl.ValuePackageImpl#getManyLongAttributeValue()
		 * @generated
		 */
		EClass MANY_LONG_ATTRIBUTE_VALUE = eINSTANCE.getManyLongAttributeValue();

		/**
		 * The meta object literal for the '<em><b>Attribute Value</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MANY_LONG_ATTRIBUTE_VALUE__ATTRIBUTE_VALUE = eINSTANCE.getManyLongAttributeValue_AttributeValue();

		/**
		 * The meta object literal for the '{@link org.eclipse.gemoc.executionframework.value.model.value.impl.ManyLongObjectAttributeValueImpl <em>Many Long Object Attribute Value</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gemoc.executionframework.value.model.value.impl.ManyLongObjectAttributeValueImpl
		 * @see org.eclipse.gemoc.executionframework.value.model.value.impl.ValuePackageImpl#getManyLongObjectAttributeValue()
		 * @generated
		 */
		EClass MANY_LONG_OBJECT_ATTRIBUTE_VALUE = eINSTANCE.getManyLongObjectAttributeValue();

		/**
		 * The meta object literal for the '<em><b>Attribute Value</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MANY_LONG_OBJECT_ATTRIBUTE_VALUE__ATTRIBUTE_VALUE = eINSTANCE.getManyLongObjectAttributeValue_AttributeValue();

		/**
		 * The meta object literal for the '{@link org.eclipse.gemoc.executionframework.value.model.value.impl.ManyFloatAttributeValueImpl <em>Many Float Attribute Value</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gemoc.executionframework.value.model.value.impl.ManyFloatAttributeValueImpl
		 * @see org.eclipse.gemoc.executionframework.value.model.value.impl.ValuePackageImpl#getManyFloatAttributeValue()
		 * @generated
		 */
		EClass MANY_FLOAT_ATTRIBUTE_VALUE = eINSTANCE.getManyFloatAttributeValue();

		/**
		 * The meta object literal for the '<em><b>Attribute Value</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MANY_FLOAT_ATTRIBUTE_VALUE__ATTRIBUTE_VALUE = eINSTANCE.getManyFloatAttributeValue_AttributeValue();

		/**
		 * The meta object literal for the '{@link org.eclipse.gemoc.executionframework.value.model.value.impl.ManyFloatObjectAttributeValueImpl <em>Many Float Object Attribute Value</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gemoc.executionframework.value.model.value.impl.ManyFloatObjectAttributeValueImpl
		 * @see org.eclipse.gemoc.executionframework.value.model.value.impl.ValuePackageImpl#getManyFloatObjectAttributeValue()
		 * @generated
		 */
		EClass MANY_FLOAT_OBJECT_ATTRIBUTE_VALUE = eINSTANCE.getManyFloatObjectAttributeValue();

		/**
		 * The meta object literal for the '<em><b>Attribute Value</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MANY_FLOAT_OBJECT_ATTRIBUTE_VALUE__ATTRIBUTE_VALUE = eINSTANCE.getManyFloatObjectAttributeValue_AttributeValue();

		/**
		 * The meta object literal for the '{@link org.eclipse.gemoc.executionframework.value.model.value.impl.ManyStringAttributeValueImpl <em>Many String Attribute Value</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gemoc.executionframework.value.model.value.impl.ManyStringAttributeValueImpl
		 * @see org.eclipse.gemoc.executionframework.value.model.value.impl.ValuePackageImpl#getManyStringAttributeValue()
		 * @generated
		 */
		EClass MANY_STRING_ATTRIBUTE_VALUE = eINSTANCE.getManyStringAttributeValue();

		/**
		 * The meta object literal for the '<em><b>Attribute Value</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MANY_STRING_ATTRIBUTE_VALUE__ATTRIBUTE_VALUE = eINSTANCE.getManyStringAttributeValue_AttributeValue();

		/**
		 * The meta object literal for the '{@link org.eclipse.gemoc.executionframework.value.model.value.impl.SingleReferenceValueImpl <em>Single Reference Value</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gemoc.executionframework.value.model.value.impl.SingleReferenceValueImpl
		 * @see org.eclipse.gemoc.executionframework.value.model.value.impl.ValuePackageImpl#getSingleReferenceValue()
		 * @generated
		 */
		EClass SINGLE_REFERENCE_VALUE = eINSTANCE.getSingleReferenceValue();

		/**
		 * The meta object literal for the '<em><b>Reference Value</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SINGLE_REFERENCE_VALUE__REFERENCE_VALUE = eINSTANCE.getSingleReferenceValue_ReferenceValue();

		/**
		 * The meta object literal for the '{@link org.eclipse.gemoc.executionframework.value.model.value.impl.ManyReferenceValueImpl <em>Many Reference Value</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gemoc.executionframework.value.model.value.impl.ManyReferenceValueImpl
		 * @see org.eclipse.gemoc.executionframework.value.model.value.impl.ValuePackageImpl#getManyReferenceValue()
		 * @generated
		 */
		EClass MANY_REFERENCE_VALUE = eINSTANCE.getManyReferenceValue();

		/**
		 * The meta object literal for the '<em><b>Reference Values</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MANY_REFERENCE_VALUE__REFERENCE_VALUES = eINSTANCE.getManyReferenceValue_ReferenceValues();

		/**
		 * The meta object literal for the '{@link org.eclipse.gemoc.executionframework.value.model.value.impl.SingleObjectValueImpl <em>Single Object Value</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gemoc.executionframework.value.model.value.impl.SingleObjectValueImpl
		 * @see org.eclipse.gemoc.executionframework.value.model.value.impl.ValuePackageImpl#getSingleObjectValue()
		 * @generated
		 */
		EClass SINGLE_OBJECT_VALUE = eINSTANCE.getSingleObjectValue();

		/**
		 * The meta object literal for the '<em><b>Object Value</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SINGLE_OBJECT_VALUE__OBJECT_VALUE = eINSTANCE.getSingleObjectValue_ObjectValue();

		/**
		 * The meta object literal for the '{@link org.eclipse.gemoc.executionframework.value.model.value.impl.ManyObjectValueImpl <em>Many Object Value</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gemoc.executionframework.value.model.value.impl.ManyObjectValueImpl
		 * @see org.eclipse.gemoc.executionframework.value.model.value.impl.ValuePackageImpl#getManyObjectValue()
		 * @generated
		 */
		EClass MANY_OBJECT_VALUE = eINSTANCE.getManyObjectValue();

		/**
		 * The meta object literal for the '<em><b>Object Values</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MANY_OBJECT_VALUE__OBJECT_VALUES = eINSTANCE.getManyObjectValue_ObjectValues();

	}

} //ValuePackage
