package org.eclipse.gemoc.executionframework.mep.engine;

import org.eclipse.gemoc.executionframework.mep.events.StoppedReason;
import org.eclipse.gemoc.executionframework.mep.launch.MEPLauncherParameters;
import org.eclipse.gemoc.executionframework.mep.types.SourceBreakpoint;
import org.eclipse.gemoc.executionframework.mep.types.StackFrame;
import org.eclipse.gemoc.executionframework.mep.types.Variable;

public interface IMEPEngine {

	abstract void internalLaunchEngine(MEPLauncherParameters launchParameters);
	
	abstract StoppedReason internalNext();
	
	abstract StoppedReason internalStepIn();

	abstract StoppedReason internalStepOut();
	
	abstract void internalSetBreakpoints(SourceBreakpoint[] breakpoints);
	
	abstract void internalTerminate();
	
	abstract void internalContinue();
	
	abstract Variable[] internalVariables();
	
	abstract StackFrame[] internalStackTrace();
	
	abstract String internalSource();
	
    abstract void addMEPEventListener(IMEPEventListener listener);

    abstract void removeMEPEventListener(IMEPEventListener listener);
    
    abstract void removeAllMEPEventListeners();
	
}
