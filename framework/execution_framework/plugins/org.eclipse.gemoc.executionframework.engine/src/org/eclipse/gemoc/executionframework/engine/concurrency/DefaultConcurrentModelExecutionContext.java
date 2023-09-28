package org.eclipse.gemoc.executionframework.engine.concurrency;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.gemoc.executionframework.engine.commons.DefaultExecutionPlatform;
import org.eclipse.gemoc.executionframework.engine.commons.EngineContextException;
import org.eclipse.gemoc.executionframework.engine.concurrency.ConcurrentRunConfiguration;
import org.eclipse.gemoc.xdsmlframework.api.core.ExecutionMode;
import org.eclipse.gemoc.xdsmlframework.api.extensions.languages.LanguageDefinitionExtension;
import org.eclipse.gemoc.xdsmlframework.api.extensions.languages.LanguageDefinitionExtensionPoint;

public class DefaultConcurrentModelExecutionContext extends
		BaseConcurrentModelExecutionContext<ConcurrentRunConfiguration, DefaultExecutionPlatform, LanguageDefinitionExtension> {

	public DefaultConcurrentModelExecutionContext(ConcurrentRunConfiguration runConfiguration,
			ExecutionMode executionMode) throws EngineContextException {
		super(runConfiguration, executionMode);
	}

	@Override
	protected String getDefaultRunDeciderName() {
		return "Random decider";
	}

	@Override
	protected DefaultExecutionPlatform createExecutionPlatform() throws CoreException {
		return new DefaultExecutionPlatform(_languageDefinition, _runConfiguration);
	}

	@Override
	protected LanguageDefinitionExtension getLanguageDefinitionExtension(String arg0) throws EngineContextException {
		return LanguageDefinitionExtensionPoint.findDefinition(this._runConfiguration.getLanguageName());
	}

}
