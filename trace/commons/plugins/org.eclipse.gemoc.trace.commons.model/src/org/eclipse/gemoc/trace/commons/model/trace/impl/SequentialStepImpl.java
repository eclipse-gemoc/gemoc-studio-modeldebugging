/**
 */
package org.eclipse.gemoc.trace.commons.model.trace.impl;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.gemoc.trace.commons.model.trace.SequentialStep;
import org.eclipse.gemoc.trace.commons.model.trace.State;
import org.eclipse.gemoc.trace.commons.model.trace.Step;
import org.eclipse.gemoc.trace.commons.model.trace.TracePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Sequential Step</b></em>'.
 * <!-- end-user-doc -->
 *
 * @generated
 */
public abstract class SequentialStepImpl<StepSubtype extends Step<StateSubType>, StateSubType extends State<?, ?>> extends BigStepImpl<StepSubtype, StateSubType> implements SequentialStep<StepSubtype, StateSubType> {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SequentialStepImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TracePackage.Literals.SEQUENTIAL_STEP;
	}

} //SequentialStepImpl
