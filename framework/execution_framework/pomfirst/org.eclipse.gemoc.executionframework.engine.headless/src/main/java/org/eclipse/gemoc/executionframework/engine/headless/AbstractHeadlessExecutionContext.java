package org.eclipse.gemoc.executionframework.engine.headless;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.gemoc.executionframework.engine.commons.EngineContextException;
import org.eclipse.gemoc.trace.commons.model.trace.MSEModel;
import org.eclipse.gemoc.xdsmlframework.api.core.ExecutionMode;
import org.eclipse.gemoc.xdsmlframework.api.core.IExecutionContext;
import org.eclipse.gemoc.xdsmlframework.api.core.IExecutionWorkspace;
import org.eclipse.gemoc.xdsmlframework.api.core.IRunConfiguration;
import org.eclipse.gemoc.xdsmlframework.api.extensions.languages.LanguageDefinitionExtension;
import org.osgi.framework.Bundle;

public abstract class AbstractHeadlessExecutionContext<R extends IRunConfiguration, L extends LanguageDefinitionExtension> implements IExecutionContext<R, HeadlessExecutionPlatform, L> {

	protected R _runConfiguration;

	protected HeadlessExecutionPlatform _executionPlatform;

	protected L _languageDefinition;

	protected Resource _resourceModel;

	protected ExecutionMode _executionMode;

	protected Bundle _dslBundle;

	protected IExecutionWorkspace _executionWorkspace;
	
	
	public AbstractHeadlessExecutionContext(R runConfiguration, ExecutionMode executionMode, L languageDefinitionExtension, IExecutionWorkspace executionWorkspace, HeadlessExecutionPlatform executionPlatform) throws EngineContextException {
		_runConfiguration = runConfiguration;
		_executionMode = executionMode;
		_executionWorkspace = executionWorkspace;
		_languageDefinition = languageDefinitionExtension;
		//_dslBundle = DslHelper.getDslBundle(_runConfiguration.getLanguageName());
		_executionPlatform = executionPlatform;
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
	public Resource getResourceModel() {
		return _resourceModel;
	}

	@Override
	public ExecutionMode getExecutionMode() {
		return _executionMode;
	}

	@Override
	public MSEModel getMSEModel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Bundle getDslBundle() {
		return _dslBundle;
	}

	@Override
	public L getLanguageDefinitionExtension() {
		return _languageDefinition;
	}

	@Override
	public HeadlessExecutionPlatform getExecutionPlatform() {
		return _executionPlatform;
	}

	@Override
	public R getRunConfiguration() {
		return _runConfiguration;
	}
	

}
