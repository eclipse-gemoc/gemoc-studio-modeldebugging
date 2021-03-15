package org.eclipse.gemoc.executionframework.mep.trace;

import org.eclipse.gemoc.trace.commons.model.trace.Dimension;
import org.eclipse.gemoc.trace.commons.model.trace.State;
import org.eclipse.gemoc.trace.commons.model.trace.Step;
import org.eclipse.gemoc.trace.commons.model.trace.TracedObject;
import org.eclipse.gemoc.trace.commons.model.trace.Value;
import org.eclipse.gemoc.trace.gemoc.api.ITraceExplorer;
import org.eclipse.gemoc.xdsmlframework.api.core.IExecutionContext;

public interface IRemoteTraceAddon {
	
	void engineAboutToStart(IExecutionContext<?, ?, ?> context);
	
	void aboutToExecuteStep(Step<?> step);
	
	void stepExecuted(Step<?> step);

	void setTraceExplorer(ITraceExplorer<Step<?>, State<?, ?>, TracedObject<?>, Dimension<?>, Value<?>> traceExplorer);
	
}
