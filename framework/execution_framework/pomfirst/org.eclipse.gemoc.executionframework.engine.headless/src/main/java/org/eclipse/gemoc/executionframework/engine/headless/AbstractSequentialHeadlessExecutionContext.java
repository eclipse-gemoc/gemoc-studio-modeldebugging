package org.eclipse.gemoc.executionframework.engine.headless;

import org.eclipse.gemoc.executionframework.engine.commons.EngineContextException;
import org.eclipse.gemoc.executionframework.engine.commons.sequential.ISequentialModelExecutionContext;
import org.eclipse.gemoc.executionframework.engine.commons.sequential.ISequentialRunConfiguration;
import org.eclipse.gemoc.xdsmlframework.api.core.ExecutionMode;
import org.eclipse.gemoc.xdsmlframework.api.core.IExecutionWorkspace;
import org.eclipse.gemoc.xdsmlframework.api.extensions.languages.LanguageDefinitionExtension;

public abstract class AbstractSequentialHeadlessExecutionContext extends AbstractHeadlessExecutionContext<ISequentialRunConfiguration, LanguageDefinitionExtension> implements ISequentialModelExecutionContext<HeadlessExecutionPlatform> {

	public AbstractSequentialHeadlessExecutionContext(ISequentialRunConfiguration runConfiguration, ExecutionMode executionMode,
			LanguageDefinitionExtension languageDefinitionExtension, IExecutionWorkspace executionWorkspace, HeadlessExecutionPlatform executionPlatform)
			throws EngineContextException {
		super(runConfiguration, executionMode, languageDefinitionExtension, executionWorkspace, executionPlatform);
	}

}
