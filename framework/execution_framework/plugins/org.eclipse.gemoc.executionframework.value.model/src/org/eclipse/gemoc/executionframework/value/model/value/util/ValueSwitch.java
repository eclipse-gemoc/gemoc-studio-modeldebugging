/**
 */
package org.eclipse.gemoc.executionframework.value.model.value.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

import org.eclipse.gemoc.executionframework.value.model.value.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see org.eclipse.gemoc.executionframework.value.model.value.ValuePackage
 * @generated
 */
public class ValueSwitch<T> extends Switch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static ValuePackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ValueSwitch() {
		if (modelPackage == null) {
			modelPackage = ValuePackage.eINSTANCE;
		}
	}

	/**
	 * Checks whether this is a switch for the given package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param ePackage the package in question.
	 * @return whether this is a switch for the given package.
	 * @generated
	 */
	@Override
	protected boolean isSwitchFor(EPackage ePackage) {
		return ePackage == modelPackage;
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	@Override
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case ValuePackage.VALUE: {
				Value value = (Value)theEObject;
				T result = caseValue(value);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ValuePackage.ATTRIBUTE_VALUE: {
				AttributeValue attributeValue = (AttributeValue)theEObject;
				T result = caseAttributeValue(attributeValue);
				if (result == null) result = caseValue(attributeValue);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ValuePackage.REFERENCE_VALUE: {
				ReferenceValue referenceValue = (ReferenceValue)theEObject;
				T result = caseReferenceValue(referenceValue);
				if (result == null) result = caseValue(referenceValue);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ValuePackage.BOOLEAN_ATTRIBUTE_VALUE: {
				BooleanAttributeValue booleanAttributeValue = (BooleanAttributeValue)theEObject;
				T result = caseBooleanAttributeValue(booleanAttributeValue);
				if (result == null) result = caseAttributeValue(booleanAttributeValue);
				if (result == null) result = caseValue(booleanAttributeValue);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ValuePackage.BOOLEAN_OBJECT_ATTRIBUTE_VALUE: {
				BooleanObjectAttributeValue booleanObjectAttributeValue = (BooleanObjectAttributeValue)theEObject;
				T result = caseBooleanObjectAttributeValue(booleanObjectAttributeValue);
				if (result == null) result = caseAttributeValue(booleanObjectAttributeValue);
				if (result == null) result = caseValue(booleanObjectAttributeValue);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ValuePackage.INTEGER_ATTRIBUTE_VALUE: {
				IntegerAttributeValue integerAttributeValue = (IntegerAttributeValue)theEObject;
				T result = caseIntegerAttributeValue(integerAttributeValue);
				if (result == null) result = caseAttributeValue(integerAttributeValue);
				if (result == null) result = caseValue(integerAttributeValue);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ValuePackage.INTEGER_OBJECT_ATTRIBUTE_VALUE: {
				IntegerObjectAttributeValue integerObjectAttributeValue = (IntegerObjectAttributeValue)theEObject;
				T result = caseIntegerObjectAttributeValue(integerObjectAttributeValue);
				if (result == null) result = caseAttributeValue(integerObjectAttributeValue);
				if (result == null) result = caseValue(integerObjectAttributeValue);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ValuePackage.LONG_ATTRIBUTE_VALUE: {
				LongAttributeValue longAttributeValue = (LongAttributeValue)theEObject;
				T result = caseLongAttributeValue(longAttributeValue);
				if (result == null) result = caseAttributeValue(longAttributeValue);
				if (result == null) result = caseValue(longAttributeValue);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ValuePackage.LONG_OBJECT_ATTRIBUTE_VALUE: {
				LongObjectAttributeValue longObjectAttributeValue = (LongObjectAttributeValue)theEObject;
				T result = caseLongObjectAttributeValue(longObjectAttributeValue);
				if (result == null) result = caseAttributeValue(longObjectAttributeValue);
				if (result == null) result = caseValue(longObjectAttributeValue);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ValuePackage.FLOAT_ATTRIBUTE_VALUE: {
				FloatAttributeValue floatAttributeValue = (FloatAttributeValue)theEObject;
				T result = caseFloatAttributeValue(floatAttributeValue);
				if (result == null) result = caseAttributeValue(floatAttributeValue);
				if (result == null) result = caseValue(floatAttributeValue);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ValuePackage.FLOAT_OBJECT_ATTRIBUTE_VALUE: {
				FloatObjectAttributeValue floatObjectAttributeValue = (FloatObjectAttributeValue)theEObject;
				T result = caseFloatObjectAttributeValue(floatObjectAttributeValue);
				if (result == null) result = caseAttributeValue(floatObjectAttributeValue);
				if (result == null) result = caseValue(floatObjectAttributeValue);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ValuePackage.STRING_ATTRIBUTE_VALUE: {
				StringAttributeValue stringAttributeValue = (StringAttributeValue)theEObject;
				T result = caseStringAttributeValue(stringAttributeValue);
				if (result == null) result = caseAttributeValue(stringAttributeValue);
				if (result == null) result = caseValue(stringAttributeValue);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ValuePackage.MANY_BOOLEAN_ATTRIBUTE_VALUE: {
				ManyBooleanAttributeValue manyBooleanAttributeValue = (ManyBooleanAttributeValue)theEObject;
				T result = caseManyBooleanAttributeValue(manyBooleanAttributeValue);
				if (result == null) result = caseAttributeValue(manyBooleanAttributeValue);
				if (result == null) result = caseValue(manyBooleanAttributeValue);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ValuePackage.MANY_BOOLEAN_OBJECT_ATTRIBUTE_VALUE: {
				ManyBooleanObjectAttributeValue manyBooleanObjectAttributeValue = (ManyBooleanObjectAttributeValue)theEObject;
				T result = caseManyBooleanObjectAttributeValue(manyBooleanObjectAttributeValue);
				if (result == null) result = caseAttributeValue(manyBooleanObjectAttributeValue);
				if (result == null) result = caseValue(manyBooleanObjectAttributeValue);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ValuePackage.MANY_INTEGER_ATTRIBUTE_VALUE: {
				ManyIntegerAttributeValue manyIntegerAttributeValue = (ManyIntegerAttributeValue)theEObject;
				T result = caseManyIntegerAttributeValue(manyIntegerAttributeValue);
				if (result == null) result = caseAttributeValue(manyIntegerAttributeValue);
				if (result == null) result = caseValue(manyIntegerAttributeValue);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ValuePackage.MANY_INTEGER_OBJECT_ATTRIBUTE_VALUE: {
				ManyIntegerObjectAttributeValue manyIntegerObjectAttributeValue = (ManyIntegerObjectAttributeValue)theEObject;
				T result = caseManyIntegerObjectAttributeValue(manyIntegerObjectAttributeValue);
				if (result == null) result = caseAttributeValue(manyIntegerObjectAttributeValue);
				if (result == null) result = caseValue(manyIntegerObjectAttributeValue);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ValuePackage.MANY_LONG_ATTRIBUTE_VALUE: {
				ManyLongAttributeValue manyLongAttributeValue = (ManyLongAttributeValue)theEObject;
				T result = caseManyLongAttributeValue(manyLongAttributeValue);
				if (result == null) result = caseAttributeValue(manyLongAttributeValue);
				if (result == null) result = caseValue(manyLongAttributeValue);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ValuePackage.MANY_LONG_OBJECT_ATTRIBUTE_VALUE: {
				ManyLongObjectAttributeValue manyLongObjectAttributeValue = (ManyLongObjectAttributeValue)theEObject;
				T result = caseManyLongObjectAttributeValue(manyLongObjectAttributeValue);
				if (result == null) result = caseAttributeValue(manyLongObjectAttributeValue);
				if (result == null) result = caseValue(manyLongObjectAttributeValue);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ValuePackage.MANY_FLOAT_ATTRIBUTE_VALUE: {
				ManyFloatAttributeValue manyFloatAttributeValue = (ManyFloatAttributeValue)theEObject;
				T result = caseManyFloatAttributeValue(manyFloatAttributeValue);
				if (result == null) result = caseAttributeValue(manyFloatAttributeValue);
				if (result == null) result = caseValue(manyFloatAttributeValue);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ValuePackage.MANY_FLOAT_OBJECT_ATTRIBUTE_VALUE: {
				ManyFloatObjectAttributeValue manyFloatObjectAttributeValue = (ManyFloatObjectAttributeValue)theEObject;
				T result = caseManyFloatObjectAttributeValue(manyFloatObjectAttributeValue);
				if (result == null) result = caseAttributeValue(manyFloatObjectAttributeValue);
				if (result == null) result = caseValue(manyFloatObjectAttributeValue);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ValuePackage.MANY_STRING_ATTRIBUTE_VALUE: {
				ManyStringAttributeValue manyStringAttributeValue = (ManyStringAttributeValue)theEObject;
				T result = caseManyStringAttributeValue(manyStringAttributeValue);
				if (result == null) result = caseAttributeValue(manyStringAttributeValue);
				if (result == null) result = caseValue(manyStringAttributeValue);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ValuePackage.SINGLE_REFERENCE_VALUE: {
				SingleReferenceValue singleReferenceValue = (SingleReferenceValue)theEObject;
				T result = caseSingleReferenceValue(singleReferenceValue);
				if (result == null) result = caseReferenceValue(singleReferenceValue);
				if (result == null) result = caseValue(singleReferenceValue);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ValuePackage.MANY_REFERENCE_VALUE: {
				ManyReferenceValue manyReferenceValue = (ManyReferenceValue)theEObject;
				T result = caseManyReferenceValue(manyReferenceValue);
				if (result == null) result = caseReferenceValue(manyReferenceValue);
				if (result == null) result = caseValue(manyReferenceValue);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ValuePackage.SINGLE_OBJECT_VALUE: {
				SingleObjectValue singleObjectValue = (SingleObjectValue)theEObject;
				T result = caseSingleObjectValue(singleObjectValue);
				if (result == null) result = caseReferenceValue(singleObjectValue);
				if (result == null) result = caseValue(singleObjectValue);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ValuePackage.MANY_OBJECT_VALUE: {
				ManyObjectValue manyObjectValue = (ManyObjectValue)theEObject;
				T result = caseManyObjectValue(manyObjectValue);
				if (result == null) result = caseReferenceValue(manyObjectValue);
				if (result == null) result = caseValue(manyObjectValue);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Value</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Value</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseValue(Value object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Attribute Value</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Attribute Value</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAttributeValue(AttributeValue object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Reference Value</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Reference Value</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseReferenceValue(ReferenceValue object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Boolean Attribute Value</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Boolean Attribute Value</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBooleanAttributeValue(BooleanAttributeValue object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Boolean Object Attribute Value</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Boolean Object Attribute Value</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBooleanObjectAttributeValue(BooleanObjectAttributeValue object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Integer Attribute Value</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Integer Attribute Value</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIntegerAttributeValue(IntegerAttributeValue object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Integer Object Attribute Value</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Integer Object Attribute Value</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIntegerObjectAttributeValue(IntegerObjectAttributeValue object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Long Attribute Value</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Long Attribute Value</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseLongAttributeValue(LongAttributeValue object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Long Object Attribute Value</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Long Object Attribute Value</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseLongObjectAttributeValue(LongObjectAttributeValue object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Float Attribute Value</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Float Attribute Value</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFloatAttributeValue(FloatAttributeValue object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Float Object Attribute Value</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Float Object Attribute Value</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFloatObjectAttributeValue(FloatObjectAttributeValue object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>String Attribute Value</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>String Attribute Value</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStringAttributeValue(StringAttributeValue object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Many Boolean Attribute Value</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Many Boolean Attribute Value</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseManyBooleanAttributeValue(ManyBooleanAttributeValue object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Many Boolean Object Attribute Value</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Many Boolean Object Attribute Value</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseManyBooleanObjectAttributeValue(ManyBooleanObjectAttributeValue object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Many Integer Attribute Value</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Many Integer Attribute Value</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseManyIntegerAttributeValue(ManyIntegerAttributeValue object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Many Integer Object Attribute Value</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Many Integer Object Attribute Value</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseManyIntegerObjectAttributeValue(ManyIntegerObjectAttributeValue object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Many Long Attribute Value</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Many Long Attribute Value</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseManyLongAttributeValue(ManyLongAttributeValue object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Many Long Object Attribute Value</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Many Long Object Attribute Value</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseManyLongObjectAttributeValue(ManyLongObjectAttributeValue object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Many Float Attribute Value</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Many Float Attribute Value</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseManyFloatAttributeValue(ManyFloatAttributeValue object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Many Float Object Attribute Value</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Many Float Object Attribute Value</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseManyFloatObjectAttributeValue(ManyFloatObjectAttributeValue object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Many String Attribute Value</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Many String Attribute Value</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseManyStringAttributeValue(ManyStringAttributeValue object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Single Reference Value</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Single Reference Value</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSingleReferenceValue(SingleReferenceValue object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Many Reference Value</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Many Reference Value</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseManyReferenceValue(ManyReferenceValue object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Single Object Value</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Single Object Value</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSingleObjectValue(SingleObjectValue object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Many Object Value</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Many Object Value</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseManyObjectValue(ManyObjectValue object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	@Override
	public T defaultCase(EObject object) {
		return null;
	}

} //ValueSwitch
