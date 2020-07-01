package org.eclipse.gemoc.executionframework.engine.headless;

import java.util.Collection;

import org.eclipse.emf.common.util.URI;
import org.eclipse.gemoc.executionframework.engine.commons.sequential.ISequentialRunConfiguration;
import org.eclipse.gemoc.xdsmlframework.api.extensions.engine_addon.EngineAddonSpecificationExtension;

public class HeadlessJavaEngineSequentialRunConfiguration implements ISequentialRunConfiguration {

	URI modelURI;
	String languageName;
	String modelEntryPoint;
	String methodEntryPoint; 
	String initializationMethod;
	String initializationMethodArgs;
	public HeadlessJavaEngineSequentialRunConfiguration(URI modelURI, String languageName, String modelEntryPoint,
			String methodEntryPoint, String initializationMethod, String initializationMethodArgs) {
		this.modelURI = modelURI;
		this.languageName =  languageName;
		this.modelEntryPoint = modelEntryPoint;
		this.methodEntryPoint = methodEntryPoint;
		this.initializationMethod = initializationMethod;
		this.initializationMethodArgs = initializationMethodArgs;
	}

	@Override
	public int getAnimationDelay() {
		return 0;
	}

	@Override
	public URI getAnimatorURI() {
		return null;
	}

	@Override
	public boolean getBreakStart() {
		return false;
	}

	@Override
	public String getDebugModelID() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<EngineAddonSpecificationExtension> getEngineAddonExtensions() {
		return null;
	}

	@Override
	public URI getExecutedModelAsMelangeURI() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public URI getExecutedModelURI() {
		return modelURI;
	}

	@Override
	public String getLanguageName() {
		return this.languageName;
	}

	@Override
	public String getMelangeQuery() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getExecutionEntryPoint() {
		return this.methodEntryPoint;
	}

	@Override
	public String getModelEntryPoint() {
		return this.modelEntryPoint;
	}

	@Override
	public String getModelInitializationMethod() {
		return this.initializationMethod;
	}

	@Override
	public String getModelInitializationArguments() {
		return this.initializationMethodArgs;
	}
	
	
	public String getAttribute(String attributeName, String defaultValue) {
		// there is no addon in this mode, so return the default value
		return defaultValue;
	}

	public Integer getAttribute(String attributeName, Integer defaultValue) {
		// there is no addon in this mode, so return the default value
		return defaultValue;
	}

	public Boolean getAttribute(String attributeName, Boolean defaultValue) {
		// there is no addon in this mode, so return the default value
		return defaultValue;
	}

}
