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
package org.eclipse.gemoc.executionframework.engine.commons;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gemoc.executionframework.engine.Activator;
import org.eclipse.gemoc.executionframework.engine.core.ExecutionWorkspace;
import org.eclipse.gemoc.xdsmlframework.api.core.ExecutionMode;
import org.eclipse.gemoc.xdsmlframework.api.core.IExecutionContext;
import org.eclipse.gemoc.xdsmlframework.api.core.IExecutionPlatform;
import org.eclipse.gemoc.xdsmlframework.api.core.IExecutionWorkspace;
import org.eclipse.gemoc.xdsmlframework.api.core.IRunConfiguration;
import org.eclipse.gemoc.xdsmlframework.api.extensions.languages.LanguageDefinitionExtension;
import org.osgi.framework.Bundle;

public abstract class AbstractModelExecutionContext<R extends IRunConfiguration, P extends IExecutionPlatform, L extends LanguageDefinitionExtension> implements
		IExecutionContext<R, P, L> {

	protected R _runConfiguration;

	protected P _executionPlatform;

	protected L _languageDefinition;

	protected Resource _resourceModel;

	protected ExecutionMode _executionMode;

	protected Bundle _dslBundle;

	private IExecutionWorkspace _executionWorkspace;

	public AbstractModelExecutionContext(R runConfiguration, ExecutionMode executionMode) throws EngineContextException {
		_runConfiguration = runConfiguration;
		_executionMode = executionMode;
		try {
			_executionWorkspace = new ExecutionWorkspace(_runConfiguration.getExecutedModelURI());
			try {
				_executionWorkspace.copyFileToExecutionFolder(_executionWorkspace.getModelPath());
			} catch (CoreException e) {
				// TODO throw warning that we couldn't copy the model
			}
			_languageDefinition = getLanguageDefinition(_runConfiguration.getLanguageName());
			_dslBundle = DslHelper.getDslBundle(_runConfiguration.getLanguageName());
			_executionPlatform = createExecutionPlatform(); // new
															// DefaultExecutionPlatform(_languageDefinition,
															// _runConfiguration);

		} catch (CoreException e) {
			EngineContextException exception = new EngineContextException("Cannot initialize the execution context, see inner exception.", e);
			throw exception;
		}
	}

	protected abstract P createExecutionPlatform() throws CoreException;

	protected abstract L getLanguageDefinition(String languageName) throws EngineContextException;

	@Override
	public R getRunConfiguration() {
		return _runConfiguration;
	}

	@Override
	public P getExecutionPlatform() {
		return _executionPlatform;
	}

	@Override
	public L getLanguageDefinitionExtension() {
		return _languageDefinition;
	}

	@Override
	public void initializeResourceModel() {
		if (_runConfiguration.getAnimatorURI() != null) // TODO maybe add a
														// toggle in the
														// launcher tab to
														// temporarily enable or
														// disable the use of
														// the animation
		{
			_resourceModel = _executionPlatform.getModelLoader().loadModelForAnimation(this);
		} else {
			_resourceModel = _executionPlatform.getModelLoader().loadModel(this);
		}

		setUpEditingDomain();

		// checkResourceSetContent();

	}

	@Override
	public Resource getResourceModel() {
		return _resourceModel;
	}

	@Override
	public void dispose() {
		_executionPlatform.dispose();
	}

	@Override
	public IExecutionWorkspace getWorkspace() {
		return _executionWorkspace;
	}

	@Override
	public ExecutionMode getExecutionMode() {
		return _executionMode;
	}

	@Override
	public Bundle getDslBundle() {
		return _dslBundle;
	}

	protected void checkResourceSetContent() {
		// check that the initial resource hasn't been loaded more than once
		// (e.g. via melange)
		// pure debug code: has no side effect on anything
		boolean foundOnce = false;
		for (Resource res : _resourceModel.getResourceSet().getResources()) {
			boolean found = res.getURI().path().equals(_runConfiguration.getExecutedModelURI().path());

			if (found && foundOnce) {
				Activator.getDefault().error("Error: found more than one resource in the resourceSet with the following path :" + _runConfiguration.getExecutedModelURI().path());
				for (Resource r : _resourceModel.getResourceSet().getResources()) {
					Activator.getDefault().info(r.getURI().toString());
				}
				break;
			} else if (found) {
				foundOnce = true;
			}
		}
	}

	private ResourceSet getResourceSet() {
		return _resourceModel.getResourceSet();
	}

	private void setUpEditingDomain() {
		TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(getResourceSet());
		if (editingDomain == null) {
			editingDomain = TransactionalEditingDomain.Factory.INSTANCE.createEditingDomain(getResourceSet());
		}
	}
	
	
	
}
