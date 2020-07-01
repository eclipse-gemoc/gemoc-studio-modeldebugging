package org.eclipse.gemoc.executionframework.engine.commons.sequential;

import org.eclipse.gemoc.xdsmlframework.api.core.IExecutionContext;
import org.eclipse.gemoc.xdsmlframework.api.core.IExecutionPlatform;
import org.eclipse.gemoc.xdsmlframework.api.extensions.languages.LanguageDefinitionExtension;

public interface ISequentialModelExecutionContext<P extends IExecutionPlatform> extends IExecutionContext<ISequentialRunConfiguration, P, LanguageDefinitionExtension> {

}
