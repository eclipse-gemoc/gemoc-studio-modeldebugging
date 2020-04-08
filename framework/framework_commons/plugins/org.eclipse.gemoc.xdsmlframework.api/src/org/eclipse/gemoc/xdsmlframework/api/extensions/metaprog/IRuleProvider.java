package org.eclipse.gemoc.xdslframework.api.extension.metaprog;

import java.util.Collection;

public interface IRuleProvider {
	
	public abstract Collection<IRule> getValidationRules();

}
