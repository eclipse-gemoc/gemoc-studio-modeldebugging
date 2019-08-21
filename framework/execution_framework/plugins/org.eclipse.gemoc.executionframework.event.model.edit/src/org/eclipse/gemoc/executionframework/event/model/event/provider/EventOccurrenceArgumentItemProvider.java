/**
 */
package org.eclipse.gemoc.executionframework.event.model.event.provider;


import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.ResourceLocator;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ViewerNotification;

import org.eclipse.gemoc.executionframework.event.model.event.EventOccurrenceArgument;
import org.eclipse.gemoc.executionframework.event.model.event.EventPackage;

import org.eclipse.gemoc.executionframework.value.model.value.ValueFactory;

/**
 * This is the item provider adapter for a {@link org.eclipse.gemoc.executionframework.event.model.event.EventOccurrenceArgument} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class EventOccurrenceArgumentItemProvider 
	extends ItemProviderAdapter
	implements
		IEditingDomainItemProvider,
		IStructuredItemContentProvider,
		ITreeItemContentProvider,
		IItemLabelProvider,
		IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EventOccurrenceArgumentItemProvider(AdapterFactory adapterFactory) {
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

			addParameterPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Parameter feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addParameterPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_EventOccurrenceArgument_parameter_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_EventOccurrenceArgument_parameter_feature", "_UI_EventOccurrenceArgument_type"),
				 EventPackage.Literals.EVENT_OCCURRENCE_ARGUMENT__PARAMETER,
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
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
			childrenFeatures.add(EventPackage.Literals.EVENT_OCCURRENCE_ARGUMENT__VALUE);
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
	 * This returns EventOccurrenceArgument.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/EventOccurrenceArgument"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		return getString("_UI_EventOccurrenceArgument_type");
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

		switch (notification.getFeatureID(EventOccurrenceArgument.class)) {
			case EventPackage.EVENT_OCCURRENCE_ARGUMENT__VALUE:
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
				(EventPackage.Literals.EVENT_OCCURRENCE_ARGUMENT__VALUE,
				 ValueFactory.eINSTANCE.createBooleanAttributeValue()));

		newChildDescriptors.add
			(createChildParameter
				(EventPackage.Literals.EVENT_OCCURRENCE_ARGUMENT__VALUE,
				 ValueFactory.eINSTANCE.createBooleanObjectAttributeValue()));

		newChildDescriptors.add
			(createChildParameter
				(EventPackage.Literals.EVENT_OCCURRENCE_ARGUMENT__VALUE,
				 ValueFactory.eINSTANCE.createIntegerAttributeValue()));

		newChildDescriptors.add
			(createChildParameter
				(EventPackage.Literals.EVENT_OCCURRENCE_ARGUMENT__VALUE,
				 ValueFactory.eINSTANCE.createIntegerObjectAttributeValue()));

		newChildDescriptors.add
			(createChildParameter
				(EventPackage.Literals.EVENT_OCCURRENCE_ARGUMENT__VALUE,
				 ValueFactory.eINSTANCE.createFloatAttributeValue()));

		newChildDescriptors.add
			(createChildParameter
				(EventPackage.Literals.EVENT_OCCURRENCE_ARGUMENT__VALUE,
				 ValueFactory.eINSTANCE.createFloatObjectAttributeValue()));

		newChildDescriptors.add
			(createChildParameter
				(EventPackage.Literals.EVENT_OCCURRENCE_ARGUMENT__VALUE,
				 ValueFactory.eINSTANCE.createStringAttributeValue()));

		newChildDescriptors.add
			(createChildParameter
				(EventPackage.Literals.EVENT_OCCURRENCE_ARGUMENT__VALUE,
				 ValueFactory.eINSTANCE.createManyBooleanAttributeValue()));

		newChildDescriptors.add
			(createChildParameter
				(EventPackage.Literals.EVENT_OCCURRENCE_ARGUMENT__VALUE,
				 ValueFactory.eINSTANCE.createManyBooleanObjectAttributeValue()));

		newChildDescriptors.add
			(createChildParameter
				(EventPackage.Literals.EVENT_OCCURRENCE_ARGUMENT__VALUE,
				 ValueFactory.eINSTANCE.createManyIntegerAttributeValue()));

		newChildDescriptors.add
			(createChildParameter
				(EventPackage.Literals.EVENT_OCCURRENCE_ARGUMENT__VALUE,
				 ValueFactory.eINSTANCE.createManyIntegerObjectAttributeValue()));

		newChildDescriptors.add
			(createChildParameter
				(EventPackage.Literals.EVENT_OCCURRENCE_ARGUMENT__VALUE,
				 ValueFactory.eINSTANCE.createManyFloatAttributeValue()));

		newChildDescriptors.add
			(createChildParameter
				(EventPackage.Literals.EVENT_OCCURRENCE_ARGUMENT__VALUE,
				 ValueFactory.eINSTANCE.createManyFloatObjectAttributeValue()));

		newChildDescriptors.add
			(createChildParameter
				(EventPackage.Literals.EVENT_OCCURRENCE_ARGUMENT__VALUE,
				 ValueFactory.eINSTANCE.createManyStringAttributeValue()));

		newChildDescriptors.add
			(createChildParameter
				(EventPackage.Literals.EVENT_OCCURRENCE_ARGUMENT__VALUE,
				 ValueFactory.eINSTANCE.createSingleReferenceValue()));

		newChildDescriptors.add
			(createChildParameter
				(EventPackage.Literals.EVENT_OCCURRENCE_ARGUMENT__VALUE,
				 ValueFactory.eINSTANCE.createManyReferenceValue()));

		newChildDescriptors.add
			(createChildParameter
				(EventPackage.Literals.EVENT_OCCURRENCE_ARGUMENT__VALUE,
				 ValueFactory.eINSTANCE.createSingleObjectValue()));

		newChildDescriptors.add
			(createChildParameter
				(EventPackage.Literals.EVENT_OCCURRENCE_ARGUMENT__VALUE,
				 ValueFactory.eINSTANCE.createManyObjectValue()));
	}

	/**
	 * Return the resource locator for this item provider's resources.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		return EventEditPlugin.INSTANCE;
	}

}
