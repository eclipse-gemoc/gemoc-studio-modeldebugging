package org.eclipse.gemoc.xdsmlframework.api.extensions.metaprog;

import java.util.Collection;
import java.util.HashSet;

/**
 * RuleProvide used by other meta-programming approach using an Ecore meta-model
 *
 * @author GUEGUEN Ronan
 *
 */
public class EcoreRuleProvider implements IRuleProvider {
	
	private Collection<IRule> ruleSet = new HashSet<>();
	
	/**
	 * Creates a RuleProvider for the Ecore rule
	 */
	public EcoreRuleProvider() {
		this.ruleSet.add(new EcoreRule());
	}

	@Override
	public Collection<IRule> getValidationRules() {
		return this.ruleSet;
	}

}
