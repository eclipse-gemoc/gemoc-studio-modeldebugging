package org.eclipse.gemoc.executionframework.engine.commons.sequential;

import org.eclipse.gemoc.executionframework.engine.commons.DefaultExecutionPlatform;
import org.eclipse.gemoc.executionframework.engine.commons.EngineContextException;
import org.eclipse.gemoc.executionframework.engine.commons.GenericModelExecutionContext;
import org.eclipse.gemoc.xdsmlframework.api.core.ExecutionMode;

public class SequentialModelExecutionContext extends GenericModelExecutionContext<ISequentialRunConfiguration> implements ISequentialModelExecutionContext<DefaultExecutionPlatform> {

	public SequentialModelExecutionContext(ISequentialRunConfiguration runConfiguration, ExecutionMode executionMode) throws EngineContextException {
		super(runConfiguration, executionMode);
	}

}
