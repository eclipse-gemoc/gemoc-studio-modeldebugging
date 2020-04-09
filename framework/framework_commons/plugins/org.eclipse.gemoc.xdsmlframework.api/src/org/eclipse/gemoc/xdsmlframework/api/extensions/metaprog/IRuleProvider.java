package org.eclipse.gemoc.xdsmlframework.api.extensions.metaprog;

import java.util.Collection;

public interface IRuleProvider {
	
	public abstract Collection<IRule> getValidationRules();

}
