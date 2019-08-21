/**
 */
package org.eclipse.gemoc.executionframework.value.model.value.provider;


import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;

import org.eclipse.gemoc.executionframework.value.model.value.ManyObjectValue;
import org.eclipse.gemoc.executionframework.value.model.value.ValueFactory;
import org.eclipse.gemoc.executionframework.value.model.value.ValuePackage;

/**
 * This is the item provider adapter for a {@link org.eclipse.gemoc.executionframework.value.model.value.ManyObjectValue} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ManyObjectValueItemProvider extends ReferenceValueItemProvider {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ManyObjectValueItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

		}
		return itemPropertyDescriptors;
	}

	/**
	 * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
	 * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
	 * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object) {
		if (childrenFeatures == null) {
			super.getChildrenFeatures(object);
			childrenFeatures.add(ValuePackage.Literals.MANY_OBJECT_VALUE__OBJECT_VALUES);
		}
		return childrenFeatures;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EStructuralFeature getChildFeature(Object object, Object child) {
		// Check the type of the specified child object and return the proper feature to use for
		// adding (see {@link AddCommand}) it as a child.

		return super.getChildFeature(object, child);
	}

	/**
	 * This returns ManyObjectValue.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/ManyObjectValue"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		return getString("_UI_ManyObjectValue_type");
	}


	/**
	 * This handles model notifications by calling {@link #updateChildren} to update any cached
	 * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void notifyChanged(Notification notification) {
		updateChildren(notification);

		switch (notification.getFeatureID(ManyObjectValue.class)) {
			case ValuePackage.MANY_OBJECT_VALUE__OBJECT_VALUES:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
				return;
		}
		super.notifyChanged(notification);
	}

	/**
	 * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
	 * that can be created under this object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);

		newChildDescriptors.add
			(createChildParameter
				(ValuePackage.Literals.MANY_OBJECT_VALUE__OBJECT_VALUES,
				 ValueFactory.eINSTANCE.createBooleanAttributeValue()));

		newChildDescriptors.add
			(createChildParameter
				(ValuePackage.Literals.MANY_OBJECT_VALUE__OBJECT_VALUES,
				 ValueFactory.eINSTANCE.createBooleanObjectAttributeValue()));

		newChildDescriptors.add
			(createChildParameter
				(ValuePackage.Literals.MANY_OBJECT_VALUE__OBJECT_VALUES,
				 ValueFactory.eINSTANCE.createIntegerAttributeValue()));

		newChildDescriptors.add
			(createChildParameter
				(ValuePackage.Literals.MANY_OBJECT_VALUE__OBJECT_VALUES,
				 ValueFactory.eINSTANCE.createIntegerObjectAttributeValue()));

		newChildDescriptors.add
			(createChildParameter
				(ValuePackage.Literals.MANY_OBJECT_VALUE__OBJECT_VALUES,
				 ValueFactory.eINSTANCE.createLongAttributeValue()));

		newChildDescriptors.add
			(createChildParameter
				(ValuePackage.Literals.MANY_OBJECT_VALUE__OBJECT_VALUES,
				 ValueFactory.eINSTANCE.createLongObjectAttributeValue()));

		newChildDescriptors.add
			(createChildParameter
				(ValuePackage.Literals.MANY_OBJECT_VALUE__OBJECT_VALUES,
				 ValueFactory.eINSTANCE.createFloatAttributeValue()));

		newChildDescriptors.add
			(createChildParameter
				(ValuePackage.Literals.MANY_OBJECT_VALUE__OBJECT_VALUES,
				 ValueFactory.eINSTANCE.createFloatObjectAttributeValue()));

		newChildDescriptors.add
			(createChildParameter
				(ValuePackage.Literals.MANY_OBJECT_VALUE__OBJECT_VALUES,
				 ValueFactory.eINSTANCE.createStringAttributeValue()));

		newChildDescriptors.add
			(createChildParameter
				(ValuePackage.Literals.MANY_OBJECT_VALUE__OBJECT_VALUES,
				 ValueFactory.eINSTANCE.createManyBooleanAttributeValue()));

		newChildDescriptors.add
			(createChildParameter
				(ValuePackage.Literals.MANY_OBJECT_VALUE__OBJECT_VALUES,
				 ValueFactory.eINSTANCE.createManyBooleanObjectAttributeValue()));

		newChildDescriptors.add
			(createChildParameter
				(ValuePackage.Literals.MANY_OBJECT_VALUE__OBJECT_VALUES,
				 ValueFactory.eINSTANCE.createManyIntegerAttributeValue()));

		newChildDescriptors.add
			(createChildParameter
				(ValuePackage.Literals.MANY_OBJECT_VALUE__OBJECT_VALUES,
				 ValueFactory.eINSTANCE.createManyIntegerObjectAttributeValue()));

		newChildDescriptors.add
			(createChildParameter
				(ValuePackage.Literals.MANY_OBJECT_VALUE__OBJECT_VALUES,
				 ValueFactory.eINSTANCE.createManyLongAttributeValue()));

		newChildDescriptors.add
			(createChildParameter
				(ValuePackage.Literals.MANY_OBJECT_VALUE__OBJECT_VALUES,
				 ValueFactory.eINSTANCE.createManyLongObjectAttributeValue()));

		newChildDescriptors.add
			(createChildParameter
				(ValuePackage.Literals.MANY_OBJECT_VALUE__OBJECT_VALUES,
				 ValueFactory.eINSTANCE.createManyFloatAttributeValue()));

		newChildDescriptors.add
			(createChildParameter
				(ValuePackage.Literals.MANY_OBJECT_VALUE__OBJECT_VALUES,
				 ValueFactory.eINSTANCE.createManyFloatObjectAttributeValue()));

		newChildDescriptors.add
			(createChildParameter
				(ValuePackage.Literals.MANY_OBJECT_VALUE__OBJECT_VALUES,
				 ValueFactory.eINSTANCE.createManyStringAttributeValue()));

		newChildDescriptors.add
			(createChildParameter
				(ValuePackage.Literals.MANY_OBJECT_VALUE__OBJECT_VALUES,
				 ValueFactory.eINSTANCE.createSingleReferenceValue()));

		newChildDescriptors.add
			(createChildParameter
				(ValuePackage.Literals.MANY_OBJECT_VALUE__OBJECT_VALUES,
				 ValueFactory.eINSTANCE.createManyReferenceValue()));

		newChildDescriptors.add
			(createChildParameter
				(ValuePackage.Literals.MANY_OBJECT_VALUE__OBJECT_VALUES,
				 ValueFactory.eINSTANCE.createSingleObjectValue()));

		newChildDescriptors.add
			(createChildParameter
				(ValuePackage.Literals.MANY_OBJECT_VALUE__OBJECT_VALUES,
				 ValueFactory.eINSTANCE.createManyObjectValue()));
	}

}
