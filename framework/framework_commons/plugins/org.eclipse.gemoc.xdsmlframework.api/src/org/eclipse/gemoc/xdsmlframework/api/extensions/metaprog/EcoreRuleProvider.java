package org.eclipse.gemoc.xdsmlframework.api.extensions.metaprog;

import java.util.ArrayList;
import java.util.Collection;

public class EcoreRuleProvider implements IRuleProvider {
	
	private ArrayList<IRule> ruleSet = new ArrayList<IRule>();

	public EcoreRuleProvider() {
		ruleSet.add(new EcoreRule());
	}

	@Override
	public Collection<IRule> getValidationRules() {
		return ruleSet;
	}

}
