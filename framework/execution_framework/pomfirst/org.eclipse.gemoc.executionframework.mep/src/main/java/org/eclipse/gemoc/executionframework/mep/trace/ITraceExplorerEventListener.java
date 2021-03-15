package org.eclipse.gemoc.executionframework.mep.trace;

import java.util.EventListener;

public interface ITraceExplorerEventListener extends EventListener {
	
	abstract void updatedCallStack();

}
