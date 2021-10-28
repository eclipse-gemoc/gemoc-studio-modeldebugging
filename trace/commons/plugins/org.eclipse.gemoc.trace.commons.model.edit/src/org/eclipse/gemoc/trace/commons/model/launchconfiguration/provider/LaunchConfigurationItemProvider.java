/**
 */
package org.eclipse.gemoc.trace.commons.model.launchconfiguration.provider;


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
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ViewerNotification;

import org.eclipse.gemoc.trace.commons.model.launchconfiguration.LaunchConfiguration;
import org.eclipse.gemoc.trace.commons.model.launchconfiguration.LaunchconfigurationFactory;
import org.eclipse.gemoc.trace.commons.model.launchconfiguration.LaunchconfigurationPackage;

/**
 * This is the item provider adapter for a {@link org.eclipse.gemoc.trace.commons.model.launchconfiguration.LaunchConfiguration} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class LaunchConfigurationItemProvider 
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
	public LaunchConfigurationItemProvider(AdapterFactory adapterFactory) {
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

			addTypePropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Type feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addTypePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_LaunchConfiguration_type_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_LaunchConfiguration_type_feature", "_UI_LaunchConfiguration_type"),
				 LaunchconfigurationPackage.Literals.LAUNCH_CONFIGURATION__TYPE,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
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
			childrenFeatures.add(LaunchconfigurationPackage.Literals.LAUNCH_CONFIGURATION__PARAMETERS);
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
	 * This returns LaunchConfiguration.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/LaunchConfiguration"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((LaunchConfiguration)object).getType();
		return label == null || label.length() == 0 ?
			getString("_UI_LaunchConfiguration_type") :
			getString("_UI_LaunchConfiguration_type") + " " + label;
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

		switch (notification.getFeatureID(LaunchConfiguration.class)) {
			case LaunchconfigurationPackage.LAUNCH_CONFIGURATION__TYPE:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
			case LaunchconfigurationPackage.LAUNCH_CONFIGURATION__PARAMETERS:
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
				(LaunchconfigurationPackage.Literals.LAUNCH_CONFIGURATION__PARAMETERS,
				 LaunchconfigurationFactory.eINSTANCE.createLanguageNameParameter()));

		newChildDescriptors.add
			(createChildParameter
				(LaunchconfigurationPackage.Literals.LAUNCH_CONFIGURATION__PARAMETERS,
				 LaunchconfigurationFactory.eINSTANCE.createAddonExtensionParameter()));

		newChildDescriptors.add
			(createChildParameter
				(LaunchconfigurationPackage.Literals.LAUNCH_CONFIGURATION__PARAMETERS,
				 LaunchconfigurationFactory.eINSTANCE.createModelURIParameter()));

		newChildDescriptors.add
			(createChildParameter
				(LaunchconfigurationPackage.Literals.LAUNCH_CONFIGURATION__PARAMETERS,
				 LaunchconfigurationFactory.eINSTANCE.createAnimatorURIParameter()));

		newChildDescriptors.add
			(createChildParameter
				(LaunchconfigurationPackage.Literals.LAUNCH_CONFIGURATION__PARAMETERS,
				 LaunchconfigurationFactory.eINSTANCE.createEntryPointParameter()));

		newChildDescriptors.add
			(createChildParameter
				(LaunchconfigurationPackage.Literals.LAUNCH_CONFIGURATION__PARAMETERS,
				 LaunchconfigurationFactory.eINSTANCE.createInitializationArgumentsParameter()));

		newChildDescriptors.add
			(createChildParameter
				(LaunchconfigurationPackage.Literals.LAUNCH_CONFIGURATION__PARAMETERS,
				 LaunchconfigurationFactory.eINSTANCE.createModelRootParameter()));

		newChildDescriptors.add
			(createChildParameter
				(LaunchconfigurationPackage.Literals.LAUNCH_CONFIGURATION__PARAMETERS,
				 LaunchconfigurationFactory.eINSTANCE.createInitializationMethodParameter()));
	}

	/**
	 * Return the resource locator for this item provider's resources.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		return LaunchConfigurationEditPlugin.INSTANCE;
	}

}
