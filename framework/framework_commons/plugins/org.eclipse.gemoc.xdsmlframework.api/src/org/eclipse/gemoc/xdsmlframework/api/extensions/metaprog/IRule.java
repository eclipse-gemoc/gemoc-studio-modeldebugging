package org.eclipse.gemoc.xdsmlframework.api.extensions.metaprog;

import org.eclipse.gemoc.dsl.Dsl;
import org.eclipse.gemoc.dsl.Entry;

public interface IRule {
		
	/**
	 * 
	 * @return a Message pointing 
	 */
	public abstract Message execute(Dsl dsl);
	
	public abstract Message execute(Entry entry);

}
