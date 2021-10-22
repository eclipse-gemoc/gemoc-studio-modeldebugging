/**
 */
package org.eclipse.gemoc.trace.commons.model.generictrace.provider;


import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.provider.EcoreItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.gemoc.trace.commons.model.generictrace.GenericDimension;
import org.eclipse.gemoc.trace.commons.model.generictrace.GenerictraceFactory;
import org.eclipse.gemoc.trace.commons.model.generictrace.GenerictracePackage;

import org.eclipse.gemoc.trace.commons.model.trace.TracePackage;

import org.eclipse.gemoc.trace.commons.model.trace.provider.DimensionItemProvider;

/**
 * This is the item provider adapter for a {@link org.eclipse.gemoc.trace.commons.model.generictrace.GenericDimension} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class GenericDimensionItemProvider extends DimensionItemProvider {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GenericDimensionItemProvider(AdapterFactory adapterFactory) {
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

			addDynamicPropertyPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Dynamic Property feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addDynamicPropertyPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_GenericDimension_dynamicProperty_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_GenericDimension_dynamicProperty_feature", "_UI_GenericDimension_type"),
				 GenerictracePackage.Literals.GENERIC_DIMENSION__DYNAMIC_PROPERTY,
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This returns GenericDimension.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/GenericDimension"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public String getText(Object object) {
		GenericDimension dim = (GenericDimension)object;
		String label="";
		if (dim.getDynamicProperty() != null) {
			EcoreItemProviderAdapterFactory factory = new EcoreItemProviderAdapterFactory();
			IItemLabelProvider labelProvider = (IItemLabelProvider) factory.adapt(dim.getDynamicProperty(), IItemLabelProvider.class);
			if (labelProvider != null) {
				label = " " +labelProvider.getText(dim.getDynamicProperty());
			}
		}
		return getString("_UI_GenericDimension_type")+label;
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
				(TracePackage.Literals.DIMENSION__VALUES,
				 GenerictraceFactory.eINSTANCE.createBooleanAttributeValue()));

		newChildDescriptors.add
			(createChildParameter
				(TracePackage.Literals.DIMENSION__VALUES,
				 GenerictraceFactory.eINSTANCE.createIntegerAttributeValue()));

		newChildDescriptors.add
			(createChildParameter
				(TracePackage.Literals.DIMENSION__VALUES,
				 GenerictraceFactory.eINSTANCE.createStringAttributeValue()));

		newChildDescriptors.add
			(createChildParameter
				(TracePackage.Literals.DIMENSION__VALUES,
				 GenerictraceFactory.eINSTANCE.createManyBooleanAttributeValue()));

		newChildDescriptors.add
			(createChildParameter
				(TracePackage.Literals.DIMENSION__VALUES,
				 GenerictraceFactory.eINSTANCE.createManyIntegerAttributeValue()));

		newChildDescriptors.add
			(createChildParameter
				(TracePackage.Literals.DIMENSION__VALUES,
				 GenerictraceFactory.eINSTANCE.createManyStringAttributeValue()));

		newChildDescriptors.add
			(createChildParameter
				(TracePackage.Literals.DIMENSION__VALUES,
				 GenerictraceFactory.eINSTANCE.createSingleReferenceValue()));

		newChildDescriptors.add
			(createChildParameter
				(TracePackage.Literals.DIMENSION__VALUES,
				 GenerictraceFactory.eINSTANCE.createManyReferenceValue()));

		newChildDescriptors.add
			(createChildParameter
				(TracePackage.Literals.DIMENSION__VALUES,
				 GenerictraceFactory.eINSTANCE.createIntegerObjectAttributeValue()));

		newChildDescriptors.add
			(createChildParameter
				(TracePackage.Literals.DIMENSION__VALUES,
				 GenerictraceFactory.eINSTANCE.createDoubleAttributeValue()));

		newChildDescriptors.add
			(createChildParameter
				(TracePackage.Literals.DIMENSION__VALUES,
				 GenerictraceFactory.eINSTANCE.createManyDoubleAttributeValue()));

		newChildDescriptors.add
			(createChildParameter
				(TracePackage.Literals.DIMENSION__VALUES,
				 GenerictraceFactory.eINSTANCE.createDoubleObjectAttributeValue()));

		newChildDescriptors.add
			(createChildParameter
				(TracePackage.Literals.DIMENSION__VALUES,
				 GenerictraceFactory.eINSTANCE.createLongAttributeValue()));

		newChildDescriptors.add
			(createChildParameter
				(TracePackage.Literals.DIMENSION__VALUES,
				 GenerictraceFactory.eINSTANCE.createManyLongAttributeValue()));

		newChildDescriptors.add
			(createChildParameter
				(TracePackage.Literals.DIMENSION__VALUES,
				 GenerictraceFactory.eINSTANCE.createLongObjectAttributeValue()));
	}

	/**
	 * Return the resource locator for this item provider's resources.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		return GenericTraceImplEditPlugin.INSTANCE;
	}

}
