package org.eclipse.gemoc.executionframework.event.manager;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Platform;
import org.eclipse.gemoc.xdsmlframework.api.core.IExecutionEngine;

public class IntegrationFacade {

	private final Map<String, IMetalanguageRuleExecutor> metalanguageIntegrations = new HashMap<>();
	private final IExecutionEngine<?> executionEngine;

	public IntegrationFacade(IExecutionEngine<?> executionEngine) {
		this.executionEngine = executionEngine;
	}

	public void handleCallRequest(SimpleCallRequest callRequest) throws IllegalArgumentException {
		final IMetalanguageRuleExecutor ruleExecutor = metalanguageIntegrations
				.computeIfAbsent(callRequest.getMetalanguage(), m -> findMetalanguageRuleExecutor(m));
		if (ruleExecutor != null) {
			ruleExecutor.handleCallRequest(callRequest);
		} else {
			throw new IllegalArgumentException(
					"No metalanguage rule executor was found for metalanguage " + callRequest.getMetalanguage());
		}
	}

	private IMetalanguageRuleExecutor findMetalanguageRuleExecutor(String metalanguage) {
		return Arrays
				.asList(Platform.getExtensionRegistry().getExtension(metalanguage).getConfigurationElements())
				.stream().findFirst().map(c -> {
					IMetalanguageRuleExecutor result = null;
					try {
						result = (IMetalanguageRuleExecutor) c.createExecutableExtension("class");
						result.setExecutionEngine(executionEngine);
					} catch (CoreException e) {
						e.printStackTrace();
					}
					return result;
				}).orElse(null);
	}
}
