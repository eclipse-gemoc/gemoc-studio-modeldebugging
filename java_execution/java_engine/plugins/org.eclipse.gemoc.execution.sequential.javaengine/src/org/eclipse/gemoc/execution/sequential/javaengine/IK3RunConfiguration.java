package org.eclipse.gemoc.execution.sequential.javaengine;

import org.eclipse.gemoc.xdsmlframework.api.core.IRunConfiguration;

public interface IK3RunConfiguration extends IRunConfiguration {

	public static final String LAUNCH_MODEL_ENTRY_POINT = "LAUNCH_MODEL_ENTRY_POINT";
	public static final String LAUNCH_METHOD_ENTRY_POINT = "LAUNCH_METHOD_ENTRY_POINT";
	public static final String LAUNCH_INITIALIZATION_METHOD = "GEMOC_LAUNCH_INITIALIZATION_METHOD";
	public static final String LAUNCH_INITIALIZATION_ARGUMENTS = "GEMOC_LAUNCH_INITIALIZATION_ARGUMENTS";

	String getExecutionEntryPoint();

	String getModelEntryPoint();

	String getModelInitializationMethod();

	String getModelInitializationArguments();
}
