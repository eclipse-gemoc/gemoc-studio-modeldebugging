package org.eclipse.gemoc.executionframework.addon.eaop.server;

import java.util.Collection;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.eclipse.gemoc.executionframework.addon.eaop.server.mapper.EngineMapper;
import org.eclipse.gemoc.executionframework.addon.eaop.server.mapper.StepMapper;
import org.eclipse.gemoc.protocols.eaop.api.data.AboutToExecuteStepEventArguments;
import org.eclipse.gemoc.protocols.eaop.api.data.EngineAboutToDisposeEventArguments;
import org.eclipse.gemoc.protocols.eaop.api.data.EngineAboutToStartEventArguments;
import org.eclipse.gemoc.protocols.eaop.api.data.EngineAboutToStopEventArguments;
import org.eclipse.gemoc.protocols.eaop.api.data.EngineInitializedEventArguments;
import org.eclipse.gemoc.protocols.eaop.api.data.EngineStartedEventArguments;
import org.eclipse.gemoc.protocols.eaop.api.data.EngineStoppedEventArguments;
import org.eclipse.gemoc.protocols.eaop.api.data.StepExecutedEventArguments;
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
	public void engineStarted(IExecutionEngine<?> engine) {
		// look for EaopServerImpl listening this engine (ie. those with regexp matching this engine name)
		Set<EaopServerImpl> applicableEaopServers = getApplicableEaopServers(engine);
		for(EaopServerImpl s : applicableEaopServers) {
			EngineStartedEventArguments arg = new EngineStartedEventArguments();
			arg.setEngine(EngineMapper.INSTANCE.executionEngineToExecutionEngineDto(engine));
			s.eaopClient.engineStarted(arg);
		}
				
	}

	@Override
	public void engineInitialized(IExecutionEngine<?> engine) {
		Set<EaopServerImpl> applicableEaopServers = getApplicableEaopServers(engine);
		for(EaopServerImpl s : applicableEaopServers) {
			EngineInitializedEventArguments arg = new EngineInitializedEventArguments();
			arg.setEngine(EngineMapper.INSTANCE.executionEngineToExecutionEngineDto(engine));
			s.eaopClient.engineInitialized(arg);
		}
	}

	@Override
	public void engineAboutToStop(IExecutionEngine<?> engine) {
		Set<EaopServerImpl> applicableEaopServers = getApplicableEaopServers(engine);
		for(EaopServerImpl s : applicableEaopServers) {
			EngineAboutToStopEventArguments arg = new EngineAboutToStopEventArguments();
			arg.setEngine(EngineMapper.INSTANCE.executionEngineToExecutionEngineDto(engine));
			s.eaopClient.engineAboutToStop(arg);
		}
	}

	@Override
	public void engineStopped(IExecutionEngine<?> engine) {
		Set<EaopServerImpl> applicableEaopServers = getApplicableEaopServers(engine);
		for(EaopServerImpl s : applicableEaopServers) {
			EngineStoppedEventArguments arg = new EngineStoppedEventArguments();
			arg.setEngine(EngineMapper.INSTANCE.executionEngineToExecutionEngineDto(engine));
			s.eaopClient.engineStopped(arg);
		}
	}

	@Override
	public void engineAboutToDispose(IExecutionEngine<?> engine) {
		Set<EaopServerImpl> applicableEaopServers = getApplicableEaopServers(engine);
		for(EaopServerImpl s : applicableEaopServers) {
			EngineAboutToDisposeEventArguments arg = new EngineAboutToDisposeEventArguments();
			arg.setEngine(EngineMapper.INSTANCE.executionEngineToExecutionEngineDto(engine));
			s.eaopClient.engineAboutToDispose(arg);
		}
	}

	@Override
	public void aboutToSelectStep(IExecutionEngine<?> engine, Collection<Step<?>> steps) {
		// TODO
	}

	@Override
	public void proposedStepsChanged(IExecutionEngine<?> engine, Collection<Step<?>> steps) {
		// TODO
	}

	@Override
	public void stepSelected(IExecutionEngine<?> engine, Step<?> selectedStep) {
		// TODO
	}

	@Override
	public void aboutToExecuteStep(IExecutionEngine<?> engine, Step<?> stepToExecute) {
		Set<EaopServerImpl> applicableEaopServers = getApplicableEaopServers(engine);
		for(EaopServerImpl s : applicableEaopServers) {
			AboutToExecuteStepEventArguments arg = new AboutToExecuteStepEventArguments();
			arg.setEngine(EngineMapper.INSTANCE.executionEngineToExecutionEngineDto(engine));
			arg.setStepToExecute(StepMapper.INSTANCE.stepToStepDto(stepToExecute));
			s.eaopClient.aboutToExecuteStep(arg);
		}
	}

	@Override
	public void stepExecuted(IExecutionEngine<?> engine, Step<?> stepExecuted) {
		Set<EaopServerImpl> applicableEaopServers = getApplicableEaopServers(engine);
		IEngineAddon.super.stepExecuted(engine, stepExecuted);for(EaopServerImpl s : applicableEaopServers) {
			StepExecutedEventArguments arg = new StepExecutedEventArguments();
			arg.setEngine(EngineMapper.INSTANCE.executionEngineToExecutionEngineDto(engine));
			arg.setStepToExecute(StepMapper.INSTANCE.stepToStepDto(stepExecuted));
			s.eaopClient.stepExecuted(arg);
		}
	}

	@Override
	public void engineStatusChanged(IExecutionEngine<?> engine, RunStatus newStatus) {
		// TODO
	}
	
	
	protected Set<EaopServerImpl> getApplicableEaopServers(IExecutionEngine<?> engine) {
		Set<EaopServerImpl> eaopServers = Activator.getDefault().getStartedEaopServer();
		return eaopServers.stream()
				.filter(s -> Pattern.compile(s.engineIdRegExp).matcher(engine.getName()).matches())
				.collect(Collectors.toSet());
	}

}
