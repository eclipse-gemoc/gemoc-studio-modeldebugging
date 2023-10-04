/*******************************************************************************
 * Copyright (c) 2017 INRIA and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     INRIA - initial API and implementation
 *     I3S Laboratory - API update and bug fix
 *******************************************************************************/
package org.eclipse.gemoc.executionframework.engine.concurrency;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.gemoc.executionframework.engine.commons.EngineContextException;
import org.eclipse.gemoc.executionframework.engine.concurrency.deciders.DeciderSpecificationExtension;
import org.eclipse.gemoc.executionframework.engine.concurrency.deciders.DeciderSpecificationExtensionPoint;
import org.eclipse.gemoc.executionframework.engine.concurrency.deciders.ILogicalStepDecider;
import org.eclipse.gemoc.executionframework.engine.concurrency.deciders.LogicalStepDeciderFactory;
import org.eclipse.gemoc.trace.commons.model.trace.MSEModel;
import org.eclipse.gemoc.xdsmlframework.api.core.ExecutionMode;
import org.eclipse.gemoc.xdsmlframework.api.core.IExecutionPlatform;
import org.eclipse.gemoc.xdsmlframework.api.extensions.languages.LanguageDefinitionExtension;

public abstract class BaseConcurrentModelExecutionContext<R extends IConcurrentRunConfiguration, P extends IExecutionPlatform, L extends LanguageDefinitionExtension>
		extends AbstractConcurrentModelExecutionContext<R, P, L> {


	protected ILogicalStepDecider _logicalStepDecider;

	protected MSEModel _mseModel;

	public BaseConcurrentModelExecutionContext(R runConfiguration, ExecutionMode executionMode)
			throws EngineContextException {
		super(runConfiguration, executionMode);
		try {
			if (executionMode.equals(ExecutionMode.Run)) {
				_logicalStepDecider = createRunDecider();
			} else {
				_logicalStepDecider = LogicalStepDeciderFactory.createDecider(runConfiguration.getDeciderName(),
						executionMode);
			}

		} catch (CoreException e) {
			EngineContextException exception = new EngineContextException(
					"Cannot initialize the execution context, see inner exception.", e);
			throw exception;
		}

	}

	
	@Override
	public void dispose() {
		super.dispose();
		_logicalStepDecider.dispose();
	}

	@Override
	public final ILogicalStepDecider getLogicalStepDecider() {
		return _logicalStepDecider;
	}

	@Override
	public final MSEModel getMSEModel() {
		if (_mseModel == null) {
			setUpMSEModel();
		}
		return _mseModel;
	}

	@Override
	public final void setUpMSEModel() {
		URI msemodelPlatformURI = URI.createPlatformResourceURI(
				getWorkspace().getMSEModelPath().removeFileExtension().addFileExtension("msemodel").toString(), true);
		try {
			Resource resource = this.getResourceModel().getResourceSet().getResource(msemodelPlatformURI, true);
			_mseModel = (MSEModel) resource.getContents().get(0);
		} catch (Exception e) {
			// file will be created later
		}
	}

	private ILogicalStepDecider createRunDecider() throws CoreException {
		DeciderSpecificationExtension extension = DeciderSpecificationExtensionPoint.findDefinition(getDefaultRunDeciderName());
		return extension.instanciateDecider();
	}

}
