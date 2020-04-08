package org.eclipse.gemoc.xdslframework.api.extension.metaprog;

import org.eclipse.gemoc.dsl.Dsl;
import org.eclipse.gemoc.dsl.Entry;

public interface IRule {
		
	public abstract Message execute(Dsl dsl);
	
	public abstract Message execute(Entry entry);

}
