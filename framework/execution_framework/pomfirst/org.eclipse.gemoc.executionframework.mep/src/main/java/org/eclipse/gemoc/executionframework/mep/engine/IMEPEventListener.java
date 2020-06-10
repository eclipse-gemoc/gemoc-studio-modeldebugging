package org.eclipse.gemoc.executionframework.mep.engine;

import java.util.EventListener;

import org.eclipse.gemoc.executionframework.mep.events.Output;
import org.eclipse.gemoc.executionframework.mep.events.Stopped;

public interface IMEPEventListener extends EventListener {

	abstract void outputReceived(Output event);
	
	abstract void stopReceived(Stopped event);
	
}
