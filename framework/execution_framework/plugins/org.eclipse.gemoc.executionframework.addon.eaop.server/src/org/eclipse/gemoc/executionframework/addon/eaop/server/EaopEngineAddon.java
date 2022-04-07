package org.eclipse.gemoc.executionframework.addon.eaop.server;

import java.util.Collection;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.eclipse.gemoc.executionframework.addon.eaop.server.mapper.EngineMapper;
import org.eclipse.gemoc.protocols.eaop.api.data.EngineAboutToStartEventArguments;
import org.eclipse.gemoc.trace.commons.model.trace.Step;
import org.eclipse.gemoc.xdsmlframework.api.core.EngineStatus.RunStatus;
import org.eclipse.gemoc.xdsmlframework.api.core.IExecutionEngine;
import org.eclipse.gemoc.xdsmlframework.api.engine_addon.IEngineAddon;

public class EaopEngineAddon implements IEngineAddon {

	public EaopEngineAddon() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void engineAboutToStart(IExecutionEngine<?> engine) {
		
		// look for EaopServerImpl listening this engine (ie. those with regexp matching this engine name)
		Set<EaopServerImpl> applicableEaopServers = getApplicableEaopServers(engine);
		
		for(EaopServerImpl s : applicableEaopServers) {
			EngineAboutToStartEventArguments arg = new EngineAboutToStartEventArguments();
			arg.setEngine(EngineMapper.INSTANCE.executionEngineToExecutionEngineDto(engine));
			s.eaopClient.engineAboutToStart(arg);
		}
	}

	@Override
	public void engineStarted(IExecutionEngine<?> executionEngine) {
		// look for EaopServerImpl listening this engine (ie. those with regexp matching this engine name)
		Set<EaopServerImpl> applicableEaopServers = getApplicableEaopServers(executionEngine);
				
	}

	@Override
	public void engineInitialized(IExecutionEngine<?> executionEngine) {
		Set<EaopServerImpl> applicableEaopServers = getApplicableEaopServers(executionEngine);
	}

	@Override
	public void engineAboutToStop(IExecutionEngine<?> engine) {
		Set<EaopServerImpl> applicableEaopServers = getApplicableEaopServers(engine);
	}

	@Override
	public void engineStopped(IExecutionEngine<?> engine) {
		Set<EaopServerImpl> applicableEaopServers = getApplicableEaopServers(engine);
	}

	@Override
	public void engineAboutToDispose(IExecutionEngine<?> engine) {
		Set<EaopServerImpl> applicableEaopServers = getApplicableEaopServers(engine);
	}

	@Override
	public void aboutToSelectStep(IExecutionEngine<?> engine, Collection<Step<?>> steps) {
		Set<EaopServerImpl> applicableEaopServers = getApplicableEaopServers(engine);
	}

	@Override
	public void proposedStepsChanged(IExecutionEngine<?> engine, Collection<Step<?>> steps) {
		Set<EaopServerImpl> applicableEaopServers = getApplicableEaopServers(engine);
	}

	@Override
	public void stepSelected(IExecutionEngine<?> engine, Step<?> selectedStep) {
		Set<EaopServerImpl> applicableEaopServers = getApplicableEaopServers(engine);
	}

	@Override
	public void aboutToExecuteStep(IExecutionEngine<?> engine, Step<?> stepToExecute) {
		Set<EaopServerImpl> applicableEaopServers = getApplicableEaopServers(engine);
	}

	@Override
	public void stepExecuted(IExecutionEngine<?> engine, Step<?> stepExecuted) {
		IEngineAddon.super.stepExecuted(engine, stepExecuted);
	}

	@Override
	public void engineStatusChanged(IExecutionEngine<?> engine, RunStatus newStatus) {
		Set<EaopServerImpl> applicableEaopServers = getApplicableEaopServers(engine);
	}
	
	
	protected Set<EaopServerImpl> getApplicableEaopServers(IExecutionEngine<?> engine) {
		Set<EaopServerImpl> eaopServers = Activator.getDefault().getStartedEaopServer();
		return eaopServers.stream()
				.filter(s -> Pattern.compile(s.engineIdRegExp).matcher(engine.getName()).matches())
				.collect(Collectors.toSet());
	}

}
