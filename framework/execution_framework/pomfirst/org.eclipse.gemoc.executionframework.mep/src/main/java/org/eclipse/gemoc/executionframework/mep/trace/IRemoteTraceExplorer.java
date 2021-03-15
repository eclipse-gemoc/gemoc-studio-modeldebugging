package org.eclipse.gemoc.executionframework.mep.trace;


public interface IRemoteTraceExplorer {
	
	void addTraceExplorerEventListener(ITraceExplorerEventListener listener);

	void removeTraceExplorerEventListener(ITraceExplorerEventListener listener);

	void removeAllTraceExplorerEventListeners();
	
}
