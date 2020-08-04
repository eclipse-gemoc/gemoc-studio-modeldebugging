package org.eclipse.gemoc.trace.metp.addonbased.server;

public interface IClientAware<T> {
	void connectClient(T client);
}
