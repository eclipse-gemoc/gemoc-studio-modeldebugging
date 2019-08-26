/**
 */
package org.eclipse.gemoc.executionframework.event.testsuite.provider;


import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.ResourceLocator;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ViewerNotification;

import org.eclipse.gemoc.executionframework.behavioralinterface.behavioralInterface.BehavioralInterfaceFactory;

import org.eclipse.gemoc.executionframework.event.model.event.EventFactory;

import org.eclipse.gemoc.executionframework.event.testsuite.TestSuite;
import org.eclipse.gemoc.executionframework.event.testsuite.TestsuiteFactory;
import org.eclipse.gemoc.executionframework.event.testsuite.TestsuitePackage;

import org.eclipse.gemoc.executionframework.value.model.value.ValueFactory;

/**
 * This is the item provider adapter for a {@link org.eclipse.gemoc.executionframework.event.testsuite.TestSuite} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class TestSuiteItemProvider 
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
	public TestSuiteItemProvider(AdapterFactory adapterFactory) {
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
			childrenFeatures.add(TestsuitePackage.Literals.TEST_SUITE__TEST_CASES);
			childrenFeatures.add(TestsuitePackage.Literals.TEST_SUITE__STORAGE);
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
	 * This returns TestSuite.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/TestSuite"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		return getString("_UI_TestSuite_type");
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

		switch (notification.getFeatureID(TestSuite.class)) {
			case TestsuitePackage.TEST_SUITE__TEST_CASES:
			case TestsuitePackage.TEST_SUITE__STORAGE:
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
				(TestsuitePackage.Literals.TEST_SUITE__TEST_CASES,
				 TestsuiteFactory.eINSTANCE.createTestCase()));

		newChildDescriptors.add
			(createChildParameter
				(TestsuitePackage.Literals.TEST_SUITE__STORAGE,
				 TestsuiteFactory.eINSTANCE.createTestSuite()));

		newChildDescriptors.add
			(createChildParameter
				(TestsuitePackage.Literals.TEST_SUITE__STORAGE,
				 TestsuiteFactory.eINSTANCE.createTestCase()));

		newChildDescriptors.add
			(createChildParameter
				(TestsuitePackage.Literals.TEST_SUITE__STORAGE,
				 TestsuiteFactory.eINSTANCE.createTestSuiteReport()));

		newChildDescriptors.add
			(createChildParameter
				(TestsuitePackage.Literals.TEST_SUITE__STORAGE,
				 TestsuiteFactory.eINSTANCE.createTestCaseSuccess()));

		newChildDescriptors.add
			(createChildParameter
				(TestsuitePackage.Literals.TEST_SUITE__STORAGE,
				 TestsuiteFactory.eINSTANCE.createTestCaseFailure()));

		newChildDescriptors.add
			(createChildParameter
				(TestsuitePackage.Literals.TEST_SUITE__STORAGE,
				 TestsuiteFactory.eINSTANCE.createTestCaseError()));

		newChildDescriptors.add
			(createChildParameter
				(TestsuitePackage.Literals.TEST_SUITE__STORAGE,
				 EventFactory.eINSTANCE.createEventOccurrence()));

		newChildDescriptors.add
			(createChildParameter
				(TestsuitePackage.Literals.TEST_SUITE__STORAGE,
				 EventFactory.eINSTANCE.createStopEventOccurrence()));

		newChildDescriptors.add
			(createChildParameter
				(TestsuitePackage.Literals.TEST_SUITE__STORAGE,
				 EventFactory.eINSTANCE.createEventOccurrenceArgument()));

		newChildDescriptors.add
			(createChildParameter
				(TestsuitePackage.Literals.TEST_SUITE__STORAGE,
				 BehavioralInterfaceFactory.eINSTANCE.createBehavioralInterface()));

		newChildDescriptors.add
			(createChildParameter
				(TestsuitePackage.Literals.TEST_SUITE__STORAGE,
				 BehavioralInterfaceFactory.eINSTANCE.createEvent()));

		newChildDescriptors.add
			(createChildParameter
				(TestsuitePackage.Literals.TEST_SUITE__STORAGE,
				 BehavioralInterfaceFactory.eINSTANCE.createEventParameter()));

		newChildDescriptors.add
			(createChildParameter
				(TestsuitePackage.Literals.TEST_SUITE__STORAGE,
				 ValueFactory.eINSTANCE.createBooleanAttributeValue()));

		newChildDescriptors.add
			(createChildParameter
				(TestsuitePackage.Literals.TEST_SUITE__STORAGE,
				 ValueFactory.eINSTANCE.createBooleanObjectAttributeValue()));

		newChildDescriptors.add
			(createChildParameter
				(TestsuitePackage.Literals.TEST_SUITE__STORAGE,
				 ValueFactory.eINSTANCE.createIntegerAttributeValue()));

		newChildDescriptors.add
			(createChildParameter
				(TestsuitePackage.Literals.TEST_SUITE__STORAGE,
				 ValueFactory.eINSTANCE.createIntegerObjectAttributeValue()));

		newChildDescriptors.add
			(createChildParameter
				(TestsuitePackage.Literals.TEST_SUITE__STORAGE,
				 ValueFactory.eINSTANCE.createFloatAttributeValue()));

		newChildDescriptors.add
			(createChildParameter
				(TestsuitePackage.Literals.TEST_SUITE__STORAGE,
				 ValueFactory.eINSTANCE.createFloatObjectAttributeValue()));

		newChildDescriptors.add
			(createChildParameter
				(TestsuitePackage.Literals.TEST_SUITE__STORAGE,
				 ValueFactory.eINSTANCE.createStringAttributeValue()));

		newChildDescriptors.add
			(createChildParameter
				(TestsuitePackage.Literals.TEST_SUITE__STORAGE,
				 ValueFactory.eINSTANCE.createManyBooleanAttributeValue()));

		newChildDescriptors.add
			(createChildParameter
				(TestsuitePackage.Literals.TEST_SUITE__STORAGE,
				 ValueFactory.eINSTANCE.createManyBooleanObjectAttributeValue()));

		newChildDescriptors.add
			(createChildParameter
				(TestsuitePackage.Literals.TEST_SUITE__STORAGE,
				 ValueFactory.eINSTANCE.createManyIntegerAttributeValue()));

		newChildDescriptors.add
			(createChildParameter
				(TestsuitePackage.Literals.TEST_SUITE__STORAGE,
				 ValueFactory.eINSTANCE.createManyIntegerObjectAttributeValue()));

		newChildDescriptors.add
			(createChildParameter
				(TestsuitePackage.Literals.TEST_SUITE__STORAGE,
				 ValueFactory.eINSTANCE.createManyFloatAttributeValue()));

		newChildDescriptors.add
			(createChildParameter
				(TestsuitePackage.Literals.TEST_SUITE__STORAGE,
				 ValueFactory.eINSTANCE.createManyFloatObjectAttributeValue()));

		newChildDescriptors.add
			(createChildParameter
				(TestsuitePackage.Literals.TEST_SUITE__STORAGE,
				 ValueFactory.eINSTANCE.createManyStringAttributeValue()));

		newChildDescriptors.add
			(createChildParameter
				(TestsuitePackage.Literals.TEST_SUITE__STORAGE,
				 ValueFactory.eINSTANCE.createSingleReferenceValue()));

		newChildDescriptors.add
			(createChildParameter
				(TestsuitePackage.Literals.TEST_SUITE__STORAGE,
				 ValueFactory.eINSTANCE.createManyReferenceValue()));

		newChildDescriptors.add
			(createChildParameter
				(TestsuitePackage.Literals.TEST_SUITE__STORAGE,
				 ValueFactory.eINSTANCE.createSingleObjectValue()));

		newChildDescriptors.add
			(createChildParameter
				(TestsuitePackage.Literals.TEST_SUITE__STORAGE,
				 ValueFactory.eINSTANCE.createManyObjectValue()));
	}

	/**
	 * This returns the label text for {@link org.eclipse.emf.edit.command.CreateChildCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getCreateChildText(Object owner, Object feature, Object child, Collection<?> selection) {
		Object childFeature = feature;
		Object childObject = child;

		boolean qualify =
			childFeature == TestsuitePackage.Literals.TEST_SUITE__TEST_CASES ||
			childFeature == TestsuitePackage.Literals.TEST_SUITE__STORAGE;

		if (qualify) {
			return getString
				("_UI_CreateChild_text2",
				 new Object[] { getTypeText(childObject), getFeatureText(childFeature), getTypeText(owner) });
		}
		return super.getCreateChildText(owner, feature, child, selection);
	}

	/**
	 * Return the resource locator for this item provider's resources.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		return TestsuiteEditPlugin.INSTANCE;
	}

}
