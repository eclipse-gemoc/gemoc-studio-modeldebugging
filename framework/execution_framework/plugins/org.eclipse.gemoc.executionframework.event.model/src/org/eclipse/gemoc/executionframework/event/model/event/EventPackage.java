/**
 */
package org.eclipse.gemoc.executionframework.event.model.event;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.eclipse.gemoc.executionframework.event.model.event.EventFactory
 * @model kind="package"
 * @generated
 */
public interface EventPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "event";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.eclipse.org/gemoc/event";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "event";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	EventPackage eINSTANCE = org.eclipse.gemoc.executionframework.event.model.event.impl.EventPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.gemoc.executionframework.event.model.event.impl.EventOccurrenceImpl <em>Occurrence</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gemoc.executionframework.event.model.event.impl.EventOccurrenceImpl
	 * @see org.eclipse.gemoc.executionframework.event.model.event.impl.EventPackageImpl#getEventOccurrence()
	 * @generated
	 */
	int EVENT_OCCURRENCE = 0;

	/**
	 * The feature id for the '<em><b>Event</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_OCCURRENCE__EVENT = 0;

	/**
	 * The feature id for the '<em><b>Args</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_OCCURRENCE__ARGS = 1;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_OCCURRENCE__TYPE = 2;

	/**
	 * The number of structural features of the '<em>Occurrence</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_OCCURRENCE_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Occurrence</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_OCCURRENCE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.gemoc.executionframework.event.model.event.impl.StopEventOccurrenceImpl <em>Stop Event Occurrence</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gemoc.executionframework.event.model.event.impl.StopEventOccurrenceImpl
	 * @see org.eclipse.gemoc.executionframework.event.model.event.impl.EventPackageImpl#getStopEventOccurrence()
	 * @generated
	 */
	int STOP_EVENT_OCCURRENCE = 1;

	/**
	 * The feature id for the '<em><b>Event</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STOP_EVENT_OCCURRENCE__EVENT = EVENT_OCCURRENCE__EVENT;

	/**
	 * The feature id for the '<em><b>Args</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STOP_EVENT_OCCURRENCE__ARGS = EVENT_OCCURRENCE__ARGS;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STOP_EVENT_OCCURRENCE__TYPE = EVENT_OCCURRENCE__TYPE;

	/**
	 * The number of structural features of the '<em>Stop Event Occurrence</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STOP_EVENT_OCCURRENCE_FEATURE_COUNT = EVENT_OCCURRENCE_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Stop Event Occurrence</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STOP_EVENT_OCCURRENCE_OPERATION_COUNT = EVENT_OCCURRENCE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.gemoc.executionframework.event.model.event.impl.EventOccurrenceArgumentImpl <em>Occurrence Argument</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gemoc.executionframework.event.model.event.impl.EventOccurrenceArgumentImpl
	 * @see org.eclipse.gemoc.executionframework.event.model.event.impl.EventPackageImpl#getEventOccurrenceArgument()
	 * @generated
	 */
	int EVENT_OCCURRENCE_ARGUMENT = 2;

	/**
	 * The feature id for the '<em><b>Parameter</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_OCCURRENCE_ARGUMENT__PARAMETER = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_OCCURRENCE_ARGUMENT__VALUE = 1;

	/**
	 * The number of structural features of the '<em>Occurrence Argument</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_OCCURRENCE_ARGUMENT_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Occurrence Argument</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_OCCURRENCE_ARGUMENT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.gemoc.executionframework.event.model.event.impl.ScenarioImpl <em>Scenario</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gemoc.executionframework.event.model.event.impl.ScenarioImpl
	 * @see org.eclipse.gemoc.executionframework.event.model.event.impl.EventPackageImpl#getScenario()
	 * @generated
	 */
	int SCENARIO = 3;

	/**
	 * The feature id for the '<em><b>Events</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCENARIO__EVENTS = 0;

	/**
	 * The number of structural features of the '<em>Scenario</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCENARIO_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Scenario</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCENARIO_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.gemoc.executionframework.event.model.event.EventOccurrenceType <em>Occurrence Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gemoc.executionframework.event.model.event.EventOccurrenceType
	 * @see org.eclipse.gemoc.executionframework.event.model.event.impl.EventPackageImpl#getEventOccurrenceType()
	 * @generated
	 */
	int EVENT_OCCURRENCE_TYPE = 4;


	/**
	 * Returns the meta object for class '{@link org.eclipse.gemoc.executionframework.event.model.event.EventOccurrence <em>Occurrence</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Occurrence</em>'.
	 * @see org.eclipse.gemoc.executionframework.event.model.event.EventOccurrence
	 * @generated
	 */
	EClass getEventOccurrence();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.gemoc.executionframework.event.model.event.EventOccurrence#getEvent <em>Event</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Event</em>'.
	 * @see org.eclipse.gemoc.executionframework.event.model.event.EventOccurrence#getEvent()
	 * @see #getEventOccurrence()
	 * @generated
	 */
	EReference getEventOccurrence_Event();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.gemoc.executionframework.event.model.event.EventOccurrence#getArgs <em>Args</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Args</em>'.
	 * @see org.eclipse.gemoc.executionframework.event.model.event.EventOccurrence#getArgs()
	 * @see #getEventOccurrence()
	 * @generated
	 */
	EReference getEventOccurrence_Args();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.gemoc.executionframework.event.model.event.EventOccurrence#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see org.eclipse.gemoc.executionframework.event.model.event.EventOccurrence#getType()
	 * @see #getEventOccurrence()
	 * @generated
	 */
	EAttribute getEventOccurrence_Type();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gemoc.executionframework.event.model.event.StopEventOccurrence <em>Stop Event Occurrence</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Stop Event Occurrence</em>'.
	 * @see org.eclipse.gemoc.executionframework.event.model.event.StopEventOccurrence
	 * @generated
	 */
	EClass getStopEventOccurrence();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gemoc.executionframework.event.model.event.EventOccurrenceArgument <em>Occurrence Argument</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Occurrence Argument</em>'.
	 * @see org.eclipse.gemoc.executionframework.event.model.event.EventOccurrenceArgument
	 * @generated
	 */
	EClass getEventOccurrenceArgument();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.gemoc.executionframework.event.model.event.EventOccurrenceArgument#getParameter <em>Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Parameter</em>'.
	 * @see org.eclipse.gemoc.executionframework.event.model.event.EventOccurrenceArgument#getParameter()
	 * @see #getEventOccurrenceArgument()
	 * @generated
	 */
	EReference getEventOccurrenceArgument_Parameter();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.gemoc.executionframework.event.model.event.EventOccurrenceArgument#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Value</em>'.
	 * @see org.eclipse.gemoc.executionframework.event.model.event.EventOccurrenceArgument#getValue()
	 * @see #getEventOccurrenceArgument()
	 * @generated
	 */
	EReference getEventOccurrenceArgument_Value();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gemoc.executionframework.event.model.event.Scenario <em>Scenario</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Scenario</em>'.
	 * @see org.eclipse.gemoc.executionframework.event.model.event.Scenario
	 * @generated
	 */
	EClass getScenario();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.gemoc.executionframework.event.model.event.Scenario#getEvents <em>Events</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Events</em>'.
	 * @see org.eclipse.gemoc.executionframework.event.model.event.Scenario#getEvents()
	 * @see #getScenario()
	 * @generated
	 */
	EReference getScenario_Events();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.gemoc.executionframework.event.model.event.EventOccurrenceType <em>Occurrence Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Occurrence Type</em>'.
	 * @see org.eclipse.gemoc.executionframework.event.model.event.EventOccurrenceType
	 * @generated
	 */
	EEnum getEventOccurrenceType();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	EventFactory getEventFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link org.eclipse.gemoc.executionframework.event.model.event.impl.EventOccurrenceImpl <em>Occurrence</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gemoc.executionframework.event.model.event.impl.EventOccurrenceImpl
		 * @see org.eclipse.gemoc.executionframework.event.model.event.impl.EventPackageImpl#getEventOccurrence()
		 * @generated
		 */
		EClass EVENT_OCCURRENCE = eINSTANCE.getEventOccurrence();

		/**
		 * The meta object literal for the '<em><b>Event</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EVENT_OCCURRENCE__EVENT = eINSTANCE.getEventOccurrence_Event();

		/**
		 * The meta object literal for the '<em><b>Args</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EVENT_OCCURRENCE__ARGS = eINSTANCE.getEventOccurrence_Args();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EVENT_OCCURRENCE__TYPE = eINSTANCE.getEventOccurrence_Type();

		/**
		 * The meta object literal for the '{@link org.eclipse.gemoc.executionframework.event.model.event.impl.StopEventOccurrenceImpl <em>Stop Event Occurrence</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gemoc.executionframework.event.model.event.impl.StopEventOccurrenceImpl
		 * @see org.eclipse.gemoc.executionframework.event.model.event.impl.EventPackageImpl#getStopEventOccurrence()
		 * @generated
		 */
		EClass STOP_EVENT_OCCURRENCE = eINSTANCE.getStopEventOccurrence();

		/**
		 * The meta object literal for the '{@link org.eclipse.gemoc.executionframework.event.model.event.impl.EventOccurrenceArgumentImpl <em>Occurrence Argument</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gemoc.executionframework.event.model.event.impl.EventOccurrenceArgumentImpl
		 * @see org.eclipse.gemoc.executionframework.event.model.event.impl.EventPackageImpl#getEventOccurrenceArgument()
		 * @generated
		 */
		EClass EVENT_OCCURRENCE_ARGUMENT = eINSTANCE.getEventOccurrenceArgument();

		/**
		 * The meta object literal for the '<em><b>Parameter</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EVENT_OCCURRENCE_ARGUMENT__PARAMETER = eINSTANCE.getEventOccurrenceArgument_Parameter();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EVENT_OCCURRENCE_ARGUMENT__VALUE = eINSTANCE.getEventOccurrenceArgument_Value();

		/**
		 * The meta object literal for the '{@link org.eclipse.gemoc.executionframework.event.model.event.impl.ScenarioImpl <em>Scenario</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gemoc.executionframework.event.model.event.impl.ScenarioImpl
		 * @see org.eclipse.gemoc.executionframework.event.model.event.impl.EventPackageImpl#getScenario()
		 * @generated
		 */
		EClass SCENARIO = eINSTANCE.getScenario();

		/**
		 * The meta object literal for the '<em><b>Events</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SCENARIO__EVENTS = eINSTANCE.getScenario_Events();

		/**
		 * The meta object literal for the '{@link org.eclipse.gemoc.executionframework.event.model.event.EventOccurrenceType <em>Occurrence Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gemoc.executionframework.event.model.event.EventOccurrenceType
		 * @see org.eclipse.gemoc.executionframework.event.model.event.impl.EventPackageImpl#getEventOccurrenceType()
		 * @generated
		 */
		EEnum EVENT_OCCURRENCE_TYPE = eINSTANCE.getEventOccurrenceType();

	}

} //EventPackage
