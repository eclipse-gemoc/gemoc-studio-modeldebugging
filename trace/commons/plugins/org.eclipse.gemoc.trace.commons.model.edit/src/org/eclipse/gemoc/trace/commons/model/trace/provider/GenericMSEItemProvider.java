/**
 */
package org.eclipse.gemoc.trace.commons.model.trace.provider;


import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.gemoc.commons.eclipse.xtext.NameHelper;
import org.eclipse.gemoc.trace.commons.model.trace.GenericMSE;
import org.eclipse.gemoc.trace.commons.model.trace.TracePackage;

/**
 * This is the item provider adapter for a {@link org.eclipse.gemoc.trace.commons.model.trace.GenericMSE} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class GenericMSEItemProvider extends MSEItemProvider {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GenericMSEItemProvider(AdapterFactory adapterFactory) {
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

			addCallerReferencePropertyDescriptor(object);
			addActionReferencePropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Caller Reference feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addCallerReferencePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_GenericMSE_callerReference_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_GenericMSE_callerReference_feature", "_UI_GenericMSE_type"),
				 TracePackage.Literals.GENERIC_MSE__CALLER_REFERENCE,
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Action Reference feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addActionReferencePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_GenericMSE_actionReference_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_GenericMSE_actionReference_feature", "_UI_GenericMSE_type"),
				 TracePackage.Literals.GENERIC_MSE__ACTION_REFERENCE,
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This returns GenericMSE.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/GenericMSE"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public String getText(Object object) {
		StringBuilder sb = new StringBuilder();
		sb.append(getString("_UI_GenericMSE_type"));
		String label = ((GenericMSE)object).getName();
		if(sb != null) sb.append(" "+label);
		//EcoreUtil.resolveAll((GenericMSE)object);
		EObject caller = ((GenericMSE)object).getCallerReference();
		if(caller != null) {
			sb.append(" - ("+NameHelper.prettyObjectName(caller)+")");
		}
		else sb.append(" (null)");
		sb.append("#");
		EOperation op = ((GenericMSE)object).getActionReference();
		if(op != null) {
			sb.append("("+NameHelper.prettyObjectName(op)+")");
		}
		else sb.append("(null)");
		return sb.toString();
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
	}

}
