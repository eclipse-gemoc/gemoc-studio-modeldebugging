/**
 */
package org.eclipse.gemoc.trace.commons.model.generictrace.provider;


import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.ResourceLocator;

import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;

import org.eclipse.gemoc.trace.commons.model.generictrace.GenerictraceFactory;

import org.eclipse.gemoc.trace.commons.model.trace.TracePackage;

import org.eclipse.gemoc.trace.commons.model.trace.provider.TraceItemProvider;

/**
 * This is the item provider adapter for a {@link org.eclipse.gemoc.trace.commons.model.generictrace.GenericTrace} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class GenericTraceItemProvider extends TraceItemProvider {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GenericTraceItemProvider(AdapterFactory adapterFactory) {
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
	 * This returns GenericTrace.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/GenericTrace"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		return getString("_UI_GenericTrace_type");
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
				(TracePackage.Literals.TRACE__ROOT_STEP,
				 GenerictraceFactory.eINSTANCE.createGenericSequentialStep()));

		newChildDescriptors.add
			(createChildParameter
				(TracePackage.Literals.TRACE__ROOT_STEP,
				 GenerictraceFactory.eINSTANCE.createGenericParallelStep()));

		newChildDescriptors.add
			(createChildParameter
				(TracePackage.Literals.TRACE__ROOT_STEP,
				 GenerictraceFactory.eINSTANCE.createGenericSmallStep()));

		newChildDescriptors.add
			(createChildParameter
				(TracePackage.Literals.TRACE__TRACED_OBJECTS,
				 GenerictraceFactory.eINSTANCE.createGenericTracedObject()));

		newChildDescriptors.add
			(createChildParameter
				(TracePackage.Literals.TRACE__STATES,
				 GenerictraceFactory.eINSTANCE.createGenericState()));
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
