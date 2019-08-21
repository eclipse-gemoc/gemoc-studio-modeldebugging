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

import org.eclipse.gemoc.executionframework.value.model.value.SingleObjectValue;
import org.eclipse.gemoc.executionframework.value.model.value.ValueFactory;
import org.eclipse.gemoc.executionframework.value.model.value.ValuePackage;

/**
 * This is the item provider adapter for a {@link org.eclipse.gemoc.executionframework.value.model.value.SingleObjectValue} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class SingleObjectValueItemProvider extends ReferenceValueItemProvider {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SingleObjectValueItemProvider(AdapterFactory adapterFactory) {
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
			childrenFeatures.add(ValuePackage.Literals.SINGLE_OBJECT_VALUE__OBJECT_VALUE);
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
	 * This returns SingleObjectValue.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/SingleObjectValue"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		return getString("_UI_SingleObjectValue_type");
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

		switch (notification.getFeatureID(SingleObjectValue.class)) {
			case ValuePackage.SINGLE_OBJECT_VALUE__OBJECT_VALUE:
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
				(ValuePackage.Literals.SINGLE_OBJECT_VALUE__OBJECT_VALUE,
				 ValueFactory.eINSTANCE.createBooleanAttributeValue()));

		newChildDescriptors.add
			(createChildParameter
				(ValuePackage.Literals.SINGLE_OBJECT_VALUE__OBJECT_VALUE,
				 ValueFactory.eINSTANCE.createBooleanObjectAttributeValue()));

		newChildDescriptors.add
			(createChildParameter
				(ValuePackage.Literals.SINGLE_OBJECT_VALUE__OBJECT_VALUE,
				 ValueFactory.eINSTANCE.createIntegerAttributeValue()));

		newChildDescriptors.add
			(createChildParameter
				(ValuePackage.Literals.SINGLE_OBJECT_VALUE__OBJECT_VALUE,
				 ValueFactory.eINSTANCE.createIntegerObjectAttributeValue()));

		newChildDescriptors.add
			(createChildParameter
				(ValuePackage.Literals.SINGLE_OBJECT_VALUE__OBJECT_VALUE,
				 ValueFactory.eINSTANCE.createLongAttributeValue()));

		newChildDescriptors.add
			(createChildParameter
				(ValuePackage.Literals.SINGLE_OBJECT_VALUE__OBJECT_VALUE,
				 ValueFactory.eINSTANCE.createLongObjectAttributeValue()));

		newChildDescriptors.add
			(createChildParameter
				(ValuePackage.Literals.SINGLE_OBJECT_VALUE__OBJECT_VALUE,
				 ValueFactory.eINSTANCE.createFloatAttributeValue()));

		newChildDescriptors.add
			(createChildParameter
				(ValuePackage.Literals.SINGLE_OBJECT_VALUE__OBJECT_VALUE,
				 ValueFactory.eINSTANCE.createFloatObjectAttributeValue()));

		newChildDescriptors.add
			(createChildParameter
				(ValuePackage.Literals.SINGLE_OBJECT_VALUE__OBJECT_VALUE,
				 ValueFactory.eINSTANCE.createStringAttributeValue()));

		newChildDescriptors.add
			(createChildParameter
				(ValuePackage.Literals.SINGLE_OBJECT_VALUE__OBJECT_VALUE,
				 ValueFactory.eINSTANCE.createManyBooleanAttributeValue()));

		newChildDescriptors.add
			(createChildParameter
				(ValuePackage.Literals.SINGLE_OBJECT_VALUE__OBJECT_VALUE,
				 ValueFactory.eINSTANCE.createManyBooleanObjectAttributeValue()));

		newChildDescriptors.add
			(createChildParameter
				(ValuePackage.Literals.SINGLE_OBJECT_VALUE__OBJECT_VALUE,
				 ValueFactory.eINSTANCE.createManyIntegerAttributeValue()));

		newChildDescriptors.add
			(createChildParameter
				(ValuePackage.Literals.SINGLE_OBJECT_VALUE__OBJECT_VALUE,
				 ValueFactory.eINSTANCE.createManyIntegerObjectAttributeValue()));

		newChildDescriptors.add
			(createChildParameter
				(ValuePackage.Literals.SINGLE_OBJECT_VALUE__OBJECT_VALUE,
				 ValueFactory.eINSTANCE.createManyLongAttributeValue()));

		newChildDescriptors.add
			(createChildParameter
				(ValuePackage.Literals.SINGLE_OBJECT_VALUE__OBJECT_VALUE,
				 ValueFactory.eINSTANCE.createManyLongObjectAttributeValue()));

		newChildDescriptors.add
			(createChildParameter
				(ValuePackage.Literals.SINGLE_OBJECT_VALUE__OBJECT_VALUE,
				 ValueFactory.eINSTANCE.createManyFloatAttributeValue()));

		newChildDescriptors.add
			(createChildParameter
				(ValuePackage.Literals.SINGLE_OBJECT_VALUE__OBJECT_VALUE,
				 ValueFactory.eINSTANCE.createManyFloatObjectAttributeValue()));

		newChildDescriptors.add
			(createChildParameter
				(ValuePackage.Literals.SINGLE_OBJECT_VALUE__OBJECT_VALUE,
				 ValueFactory.eINSTANCE.createManyStringAttributeValue()));

		newChildDescriptors.add
			(createChildParameter
				(ValuePackage.Literals.SINGLE_OBJECT_VALUE__OBJECT_VALUE,
				 ValueFactory.eINSTANCE.createSingleReferenceValue()));

		newChildDescriptors.add
			(createChildParameter
				(ValuePackage.Literals.SINGLE_OBJECT_VALUE__OBJECT_VALUE,
				 ValueFactory.eINSTANCE.createManyReferenceValue()));

		newChildDescriptors.add
			(createChildParameter
				(ValuePackage.Literals.SINGLE_OBJECT_VALUE__OBJECT_VALUE,
				 ValueFactory.eINSTANCE.createSingleObjectValue()));

		newChildDescriptors.add
			(createChildParameter
				(ValuePackage.Literals.SINGLE_OBJECT_VALUE__OBJECT_VALUE,
				 ValueFactory.eINSTANCE.createManyObjectValue()));
	}

}
