package org.eclipse.gemoc.executionframework.event.manager;

import org.eclipse.gemoc.xdsmlframework.api.core.IExecutionEngine;

public interface IMetalanguageRuleExecutor {

	Object handleCallRequest(SimpleCallRequest callRequest);
	
	void setExecutionEngine(IExecutionEngine<?> executionEngine);
}
