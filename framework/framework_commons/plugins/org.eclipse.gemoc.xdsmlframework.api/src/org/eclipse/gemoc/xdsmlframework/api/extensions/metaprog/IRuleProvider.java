package org.eclipse.gemoc.xdsmlframework.api.extensions.metaprog;

import java.util.Collection;

/**
 * Interface used to create RuleProvider.
 * RuleProviders provide rules to the DslValidator.
 * 
 * @author GUEGUEN Ronan
 *
 */
public interface IRuleProvider {
	
	/**
	 * Returns a Collection of IRules from the IRuleProvider
	 * 
	 * @return a Collection of IRules from the IRuleProvider
	 */
	public abstract Collection<IRule> getValidationRules();

}
