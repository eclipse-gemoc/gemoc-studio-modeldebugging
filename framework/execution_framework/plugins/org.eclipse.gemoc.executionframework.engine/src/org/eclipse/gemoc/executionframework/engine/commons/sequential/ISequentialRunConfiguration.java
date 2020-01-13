package org.eclipse.gemoc.executionframework.engine.commons.sequential;

import org.eclipse.gemoc.xdsmlframework.api.core.IRunConfiguration;

/**
 * Basic IRunConfiguration that can be used by sequential engines
 */
public interface ISequentialRunConfiguration extends IRunConfiguration {

	public static final String LAUNCH_MODEL_ENTRY_POINT = "LAUNCH_MODEL_ENTRY_POINT";
	public static final String LAUNCH_METHOD_ENTRY_POINT = "LAUNCH_METHOD_ENTRY_POINT";
	public static final String LAUNCH_INITIALIZATION_METHOD = "GEMOC_LAUNCH_INITIALIZATION_METHOD";
	public static final String LAUNCH_INITIALIZATION_ARGUMENTS = "GEMOC_LAUNCH_INITIALIZATION_ARGUMENTS";

	String getExecutionEntryPoint();

	String getModelEntryPoint();

	String getModelInitializationMethod();

	String getModelInitializationArguments();
}
