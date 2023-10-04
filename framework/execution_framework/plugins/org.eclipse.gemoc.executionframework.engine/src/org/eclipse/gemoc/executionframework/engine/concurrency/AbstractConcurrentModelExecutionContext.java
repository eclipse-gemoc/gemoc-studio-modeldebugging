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

import org.eclipse.gemoc.executionframework.engine.commons.AbstractModelExecutionContext;
import org.eclipse.gemoc.executionframework.engine.commons.EngineContextException;
import org.eclipse.gemoc.executionframework.engine.concurrency.deciders.ILogicalStepDecider;
import org.eclipse.gemoc.xdsmlframework.api.core.ExecutionMode;
import org.eclipse.gemoc.xdsmlframework.api.core.IExecutionPlatform;
import org.eclipse.gemoc.xdsmlframework.api.extensions.languages.LanguageDefinitionExtension;

public abstract class AbstractConcurrentModelExecutionContext<R extends IConcurrentRunConfiguration, P extends IExecutionPlatform, L extends LanguageDefinitionExtension>
		extends AbstractModelExecutionContext<R, P, L> {
	public AbstractConcurrentModelExecutionContext(R runConfiguration, ExecutionMode executionMode)
			throws EngineContextException {
		super(runConfiguration, executionMode);
	}

	public abstract void setUpMSEModel();

	public abstract ILogicalStepDecider getLogicalStepDecider();

	protected abstract String getDefaultRunDeciderName();

}
