package org.eclipse.gemoc.xdsmlframework.api.extensions.metaprog;

import org.eclipse.gemoc.dsl.Entry;

/**
 * Interface used to create validation rules for a meta-programming approach
 * Rules perform checks on a Dls and its entries and return a message if something that does not fit the meta-programming approach is noticed.
 * 
 * @author GUEGUEN Ronan
 *
 */
public interface ILanguageComponentValidator {
			
	/** 
	 * Returns a Message containing informations regarding the Dsl Entry with a specific Severity depending on the Message send.
	 * If nothing wrong is noticed in the Entry, the Severity should be set as DEFAULT.
	 * @param entry -Dsl entry on which the validation is performed
	 * @return Message pointing to the Entry
	 */
	public abstract Message validate(Entry entry);

}
