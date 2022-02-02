/*******************************************************************************
 * Copyright (c) 2016 Inria and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Inria - initial API and implementation
 *******************************************************************************/
package org.eclipse.gemoc.executionframework.engine.core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gemoc.executionframework.engine.Activator;
import org.eclipse.gemoc.trace.commons.model.generictrace.GenericSequentialStep;
import org.eclipse.gemoc.trace.commons.model.generictrace.GenerictraceFactory;
import org.eclipse.gemoc.trace.commons.model.trace.GenericMSE;
import org.eclipse.gemoc.trace.commons.model.trace.MSE;
import org.eclipse.gemoc.trace.commons.model.trace.MSEModel;
import org.eclipse.gemoc.trace.commons.model.trace.MSEOccurrence;
import org.eclipse.gemoc.trace.commons.model.trace.Step;
import org.eclipse.gemoc.trace.commons.model.trace.TraceFactory;
import org.eclipse.gemoc.trace.gemoc.api.IMultiDimensionalTraceAddon;
import org.eclipse.gemoc.xdsmlframework.api.core.IExecutionContext;
import org.eclipse.gemoc.xdsmlframework.api.core.IRunConfiguration;

/**
 * Abstract class providing a basic implementation for sequential engines
 * 
 * @author Didier Vojtisek<didier.vojtisek@inria.fr>
 *
 */
public abstract class AbstractSequentialExecutionEngine<C extends IExecutionContext<R, ?, ?>, R extends IRunConfiguration> extends AbstractExecutionEngine<C, R> {

	private MSEModel _actionModel;
	private IMultiDimensionalTraceAddon<?, ?, ?, ?, ?> traceAddon;

	protected abstract void executeEntryPoint();

	/**
	 * if it exists, calls the method tagged as @Initialize
	 */
	protected abstract void initializeModel();

	/**
	 * search for an applicable entry point for the simulation, this is typically a
	 * method having the @Main annotation
	 * 
	 * @param executionContext
	 *            the execution context of the simulation
	 */
	protected abstract void prepareEntryPoint(C executionContext);

	/**
	 * search for an applicable method tagged as @Initialize
	 */
	protected abstract void prepareInitializeModel(C executionContext);

	@Override
	public final void performInitialize(C executionContext) {
		@SuppressWarnings("rawtypes")
		Set<IMultiDimensionalTraceAddon> traceManagers = this.getAddonsTypedBy(IMultiDimensionalTraceAddon.class);
		if (!traceManagers.isEmpty()) {
			this.traceAddon = traceManagers.iterator().next();
		}
		prepareEntryPoint(executionContext);
		prepareInitializeModel(executionContext);
	}

	@Override
	protected final void performStart() {
		initializeModel();
		notifyEngineInitialized();
		executeEntryPoint();
		Activator.getDefault().info("Execution finished");
	}

	@Override
	protected final void afterExecutionStep() {
		super.afterExecutionStep();
	}

	/**
	 * To be called just before each execution step by an implementing engine.
	 */
	protected final void beforeExecutionStep(Object caller, String className, String operationName) {
		beforeExecutionStep(caller, className, operationName, Collections.emptyList());
	}

	protected final void beforeExecutionStep(Object caller, String className, String operationName, List<Object> args) {
		// We will trick the transaction with an empty command. This most
		// probably make rollbacks impossible, but at least we can manage
		// transactions the way we want.
		RecordingCommand rc = new RecordingCommand(editingDomain) {
			@Override
			protected void doExecute() {
			}
		};

		beforeExecutionStep(caller, className, operationName, rc, args);
		rc.execute();
	}

	protected final void beforeExecutionStep(Object caller, String className, String operationName, RecordingCommand rc) {
		beforeExecutionStep(caller, className, operationName, rc, Collections.emptyList());
	}

	/**
	 * To be called just after each execution step by an implementing engine. If the
	 * step was done through a RecordingCommand, it can be given.
	 */

	protected final void beforeExecutionStep(Object caller, String className, String operationName, RecordingCommand rc, List<Object> args) {
		if (caller != null && caller instanceof EObject && editingDomain != null) {
			// Call expected to be done from an EMF model, hence EObjects
			EObject callerCast = (EObject) caller;
			// We create a step
			Step<?> step = createStep(callerCast, className, operationName, args);

			beforeExecutionStep(step, rc);
		}
	}

	private Step<?> createStep(EObject caller, String className, String methodName, List<Object> args) {
		MSE mse = findOrCreateMSE(caller, className, methodName);
		Step<?> result;
		if (traceAddon == null) {
			GenericSequentialStep step = GenerictraceFactory.eINSTANCE.createGenericSequentialStep();
			MSEOccurrence occurrence = TraceFactory.eINSTANCE.createMSEOccurrence();
			for (Object arg : args) {
				occurrence.getParameters().add(arg);
			}
			step.setMseoccurrence(occurrence);
			occurrence.setMse(mse);
			result = step;
		} else {
			result = traceAddon.getFactory().createStep(mse, new ArrayList<Object>(), new ArrayList<Object>());
		}
		result.getMseoccurrence().getParameters().addAll(Arrays.asList(args));
		return result;
	}
	
	private Map<EClass, Set<EOperation>> orphanOperations = new HashMap<EClass, Set<EOperation>>();
	
	private void addOrpanOperation(EClass c, EOperation op) {
		if (!orphanOperations.containsKey(c)) {
			orphanOperations.put(c, new HashSet<>());
		}
		orphanOperations.get(c).add(op);
	}

	private EOperation findOperation(EObject object, String className, String methodName) {
		// We try to find the corresponding EOperation
		EOperation result = null;
		final List<EClass> openSet = new ArrayList<>();
		openSet.add(object.eClass());
		
		while(result == null && !openSet.isEmpty()) {
			final EClass eClass = openSet.remove(0);
			for (EOperation operation : eClass.getEOperations()) {
				if (operation.getName().equalsIgnoreCase(methodName)) {
					result = operation;
				}
			}
			openSet.addAll(eClass.getESuperTypes());
		}
		
		if (result != null) {
			return result;
		}

		// Else if the EOperation was created already
		if (orphanOperations.containsKey(object.eClass())) {
			for (EOperation op : orphanOperations.get(object.eClass())) {
				if (op.getName().equals(methodName)) {
					return op;
				}
			}
		}
		// Else we create the missing operation (VERY approximatively)
		EOperation operation = EcoreFactory.eINSTANCE.createEOperation();
		this.getActionModel().getOrphanOperations().add(operation);
		operation.setName(methodName);
		addOrpanOperation(object.eClass(),operation);
		return operation;
	}

	private MSEModel getActionModel() {
		if (_actionModel == null) {
			_actionModel = TraceFactory.eINSTANCE.createMSEModel();
		}
		return _actionModel;
	}
	
	/**
	 * Find the MSE element for the triplet caller/className/MethodName in the model
	 * of precalculated possible MSE. If it doesn't exist yet, create one and add it
	 * to the model.
	 * 
	 * @param caller
	 *            the caller object
	 * @param className
	 *            the class containing the method
	 * @param methodName
	 *            the name of the method
	 * @return the retrieved or created MSE
	 */
	public final MSE findOrCreateMSE(EObject caller, String className, String methodName) {
		EOperation operation = findOperation(caller, className, methodName);

		if (getActionModel() != null) {
			for (MSE existingMSE : getActionModel().getOwnedMSEs()) {
				if (existingMSE.getCaller().equals(caller) && ((existingMSE.getAction() != null && existingMSE.getAction().equals(operation)) || (existingMSE.getAction() == null && operation == null))) {
					// no need to create one, we already have it
					return existingMSE;
				}
			}
		}
		// let's create a MSE
		final GenericMSE mse = TraceFactory.eINSTANCE.createGenericMSE();
		mse.setCallerReference(caller);
		mse.setActionReference(operation);
		if (operation != null)
			mse.setName("MSE_" + caller.getClass().getSimpleName() + "_" + operation.getName());
		else
			mse.setName("MSE_" + caller.getClass().getSimpleName() + "_" + methodName);
		// and add it for possible reuse
		if (getActionModel() != null) {

			if (getActionModel().eResource() != null) {
				TransactionUtil.getEditingDomain(getActionModel().eResource());
				RecordingCommand command = new RecordingCommand(TransactionUtil.getEditingDomain(getActionModel().eResource()), "Saving new MSE ") {
					@Override
					protected void doExecute() {
						getActionModel().getOwnedMSEs().add(mse);
						try {
							getActionModel().eResource().save(null);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				};
				TransactionUtil.getEditingDomain(getActionModel().eResource()).getCommandStack().execute(command);
			}
		} else {
			getActionModel().getOwnedMSEs().add(mse);
		}
		return mse;
	}
	
	@Override
	protected void beforeStart() {

	}

	@Override
	protected void performStop() {

	}

	@Override
	protected void finishDispose() {

	}
}
