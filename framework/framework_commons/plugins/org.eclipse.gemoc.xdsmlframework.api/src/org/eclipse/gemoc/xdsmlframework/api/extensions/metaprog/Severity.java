package org.eclipse.gemoc.xdsmlframework.api.extensions.metaprog;

/**
 * Enumeration used to indicate whether a message send by a validation rule to the DslValidator
 * is an error, a warning, an information or just the default message send when nothing wrong is detected.
 * 
 * @author GUEGUEN Ronan
 *
 */
public enum Severity {
	
	ERROR,
	WARNING,
	INFO,
	DEFAULT
}
