/**
 */
package org.eclipse.gemoc.executionframework.value.model.value.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.gemoc.executionframework.value.model.value.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.gemoc.executionframework.value.model.value.ValuePackage
 * @generated
 */
public class ValueAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static ValuePackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ValueAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = ValuePackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ValueSwitch<Adapter> modelSwitch =
		new ValueSwitch<Adapter>() {
			@Override
			public Adapter caseValue(Value object) {
				return createValueAdapter();
			}
			@Override
			public Adapter caseAttributeValue(AttributeValue object) {
				return createAttributeValueAdapter();
			}
			@Override
			public Adapter caseReferenceValue(ReferenceValue object) {
				return createReferenceValueAdapter();
			}
			@Override
			public Adapter caseBooleanAttributeValue(BooleanAttributeValue object) {
				return createBooleanAttributeValueAdapter();
			}
			@Override
			public Adapter caseBooleanObjectAttributeValue(BooleanObjectAttributeValue object) {
				return createBooleanObjectAttributeValueAdapter();
			}
			@Override
			public Adapter caseIntegerAttributeValue(IntegerAttributeValue object) {
				return createIntegerAttributeValueAdapter();
			}
			@Override
			public Adapter caseIntegerObjectAttributeValue(IntegerObjectAttributeValue object) {
				return createIntegerObjectAttributeValueAdapter();
			}
			@Override
			public Adapter caseLongAttributeValue(LongAttributeValue object) {
				return createLongAttributeValueAdapter();
			}
			@Override
			public Adapter caseLongObjectAttributeValue(LongObjectAttributeValue object) {
				return createLongObjectAttributeValueAdapter();
			}
			@Override
			public Adapter caseFloatAttributeValue(FloatAttributeValue object) {
				return createFloatAttributeValueAdapter();
			}
			@Override
			public Adapter caseFloatObjectAttributeValue(FloatObjectAttributeValue object) {
				return createFloatObjectAttributeValueAdapter();
			}
			@Override
			public Adapter caseStringAttributeValue(StringAttributeValue object) {
				return createStringAttributeValueAdapter();
			}
			@Override
			public Adapter caseManyBooleanAttributeValue(ManyBooleanAttributeValue object) {
				return createManyBooleanAttributeValueAdapter();
			}
			@Override
			public Adapter caseManyBooleanObjectAttributeValue(ManyBooleanObjectAttributeValue object) {
				return createManyBooleanObjectAttributeValueAdapter();
			}
			@Override
			public Adapter caseManyIntegerAttributeValue(ManyIntegerAttributeValue object) {
				return createManyIntegerAttributeValueAdapter();
			}
			@Override
			public Adapter caseManyIntegerObjectAttributeValue(ManyIntegerObjectAttributeValue object) {
				return createManyIntegerObjectAttributeValueAdapter();
			}
			@Override
			public Adapter caseManyLongAttributeValue(ManyLongAttributeValue object) {
				return createManyLongAttributeValueAdapter();
			}
			@Override
			public Adapter caseManyLongObjectAttributeValue(ManyLongObjectAttributeValue object) {
				return createManyLongObjectAttributeValueAdapter();
			}
			@Override
			public Adapter caseManyFloatAttributeValue(ManyFloatAttributeValue object) {
				return createManyFloatAttributeValueAdapter();
			}
			@Override
			public Adapter caseManyFloatObjectAttributeValue(ManyFloatObjectAttributeValue object) {
				return createManyFloatObjectAttributeValueAdapter();
			}
			@Override
			public Adapter caseManyStringAttributeValue(ManyStringAttributeValue object) {
				return createManyStringAttributeValueAdapter();
			}
			@Override
			public Adapter caseSingleReferenceValue(SingleReferenceValue object) {
				return createSingleReferenceValueAdapter();
			}
			@Override
			public Adapter caseManyReferenceValue(ManyReferenceValue object) {
				return createManyReferenceValueAdapter();
			}
			@Override
			public Adapter caseSingleObjectValue(SingleObjectValue object) {
				return createSingleObjectValueAdapter();
			}
			@Override
			public Adapter caseManyObjectValue(ManyObjectValue object) {
				return createManyObjectValueAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gemoc.executionframework.value.model.value.Value <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gemoc.executionframework.value.model.value.Value
	 * @generated
	 */
	public Adapter createValueAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gemoc.executionframework.value.model.value.AttributeValue <em>Attribute Value</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gemoc.executionframework.value.model.value.AttributeValue
	 * @generated
	 */
	public Adapter createAttributeValueAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gemoc.executionframework.value.model.value.ReferenceValue <em>Reference Value</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gemoc.executionframework.value.model.value.ReferenceValue
	 * @generated
	 */
	public Adapter createReferenceValueAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gemoc.executionframework.value.model.value.BooleanAttributeValue <em>Boolean Attribute Value</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gemoc.executionframework.value.model.value.BooleanAttributeValue
	 * @generated
	 */
	public Adapter createBooleanAttributeValueAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gemoc.executionframework.value.model.value.BooleanObjectAttributeValue <em>Boolean Object Attribute Value</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gemoc.executionframework.value.model.value.BooleanObjectAttributeValue
	 * @generated
	 */
	public Adapter createBooleanObjectAttributeValueAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gemoc.executionframework.value.model.value.IntegerAttributeValue <em>Integer Attribute Value</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gemoc.executionframework.value.model.value.IntegerAttributeValue
	 * @generated
	 */
	public Adapter createIntegerAttributeValueAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gemoc.executionframework.value.model.value.IntegerObjectAttributeValue <em>Integer Object Attribute Value</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gemoc.executionframework.value.model.value.IntegerObjectAttributeValue
	 * @generated
	 */
	public Adapter createIntegerObjectAttributeValueAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gemoc.executionframework.value.model.value.LongAttributeValue <em>Long Attribute Value</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gemoc.executionframework.value.model.value.LongAttributeValue
	 * @generated
	 */
	public Adapter createLongAttributeValueAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gemoc.executionframework.value.model.value.LongObjectAttributeValue <em>Long Object Attribute Value</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gemoc.executionframework.value.model.value.LongObjectAttributeValue
	 * @generated
	 */
	public Adapter createLongObjectAttributeValueAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gemoc.executionframework.value.model.value.FloatAttributeValue <em>Float Attribute Value</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gemoc.executionframework.value.model.value.FloatAttributeValue
	 * @generated
	 */
	public Adapter createFloatAttributeValueAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gemoc.executionframework.value.model.value.FloatObjectAttributeValue <em>Float Object Attribute Value</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gemoc.executionframework.value.model.value.FloatObjectAttributeValue
	 * @generated
	 */
	public Adapter createFloatObjectAttributeValueAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gemoc.executionframework.value.model.value.StringAttributeValue <em>String Attribute Value</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gemoc.executionframework.value.model.value.StringAttributeValue
	 * @generated
	 */
	public Adapter createStringAttributeValueAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gemoc.executionframework.value.model.value.ManyBooleanAttributeValue <em>Many Boolean Attribute Value</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gemoc.executionframework.value.model.value.ManyBooleanAttributeValue
	 * @generated
	 */
	public Adapter createManyBooleanAttributeValueAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gemoc.executionframework.value.model.value.ManyBooleanObjectAttributeValue <em>Many Boolean Object Attribute Value</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gemoc.executionframework.value.model.value.ManyBooleanObjectAttributeValue
	 * @generated
	 */
	public Adapter createManyBooleanObjectAttributeValueAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gemoc.executionframework.value.model.value.ManyIntegerAttributeValue <em>Many Integer Attribute Value</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gemoc.executionframework.value.model.value.ManyIntegerAttributeValue
	 * @generated
	 */
	public Adapter createManyIntegerAttributeValueAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gemoc.executionframework.value.model.value.ManyIntegerObjectAttributeValue <em>Many Integer Object Attribute Value</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gemoc.executionframework.value.model.value.ManyIntegerObjectAttributeValue
	 * @generated
	 */
	public Adapter createManyIntegerObjectAttributeValueAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gemoc.executionframework.value.model.value.ManyLongAttributeValue <em>Many Long Attribute Value</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gemoc.executionframework.value.model.value.ManyLongAttributeValue
	 * @generated
	 */
	public Adapter createManyLongAttributeValueAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gemoc.executionframework.value.model.value.ManyLongObjectAttributeValue <em>Many Long Object Attribute Value</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gemoc.executionframework.value.model.value.ManyLongObjectAttributeValue
	 * @generated
	 */
	public Adapter createManyLongObjectAttributeValueAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gemoc.executionframework.value.model.value.ManyFloatAttributeValue <em>Many Float Attribute Value</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gemoc.executionframework.value.model.value.ManyFloatAttributeValue
	 * @generated
	 */
	public Adapter createManyFloatAttributeValueAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gemoc.executionframework.value.model.value.ManyFloatObjectAttributeValue <em>Many Float Object Attribute Value</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gemoc.executionframework.value.model.value.ManyFloatObjectAttributeValue
	 * @generated
	 */
	public Adapter createManyFloatObjectAttributeValueAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gemoc.executionframework.value.model.value.ManyStringAttributeValue <em>Many String Attribute Value</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gemoc.executionframework.value.model.value.ManyStringAttributeValue
	 * @generated
	 */
	public Adapter createManyStringAttributeValueAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gemoc.executionframework.value.model.value.SingleReferenceValue <em>Single Reference Value</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gemoc.executionframework.value.model.value.SingleReferenceValue
	 * @generated
	 */
	public Adapter createSingleReferenceValueAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gemoc.executionframework.value.model.value.ManyReferenceValue <em>Many Reference Value</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gemoc.executionframework.value.model.value.ManyReferenceValue
	 * @generated
	 */
	public Adapter createManyReferenceValueAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gemoc.executionframework.value.model.value.SingleObjectValue <em>Single Object Value</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gemoc.executionframework.value.model.value.SingleObjectValue
	 * @generated
	 */
	public Adapter createSingleObjectValueAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gemoc.executionframework.value.model.value.ManyObjectValue <em>Many Object Value</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gemoc.executionframework.value.model.value.ManyObjectValue
	 * @generated
	 */
	public Adapter createManyObjectValueAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //ValueAdapterFactory
