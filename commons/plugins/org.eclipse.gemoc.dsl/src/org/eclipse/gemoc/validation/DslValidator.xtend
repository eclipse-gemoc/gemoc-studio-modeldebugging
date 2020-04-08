/*
 * generated by Xtext 2.10.0
 */
package org.eclipse.gemoc.validation

import org.eclipse.gemoc.dsl.Dsl
import org.eclipse.gemoc.dsl.DslPackage
import org.eclipse.gemoc.dsl.Entry
import org.eclipse.xtext.validation.Check
import org.eclipse.core.runtime.IConfigurationElement
import org.eclipse.gemoc.dsl.approach.Message
import org.eclipse.gemoc.dsl.approach.IRule
import java.util.ArrayList
import org.eclipse.gemoc.dsl.approach.Severity
import org.eclipse.gemoc.dsl.approach.IRuleProvider

/**
 * This class contains custom validation rules. 
 *
 * See https://www.eclipse.org/Xtext/documentation/303_runtime_concepts.html#validation
 */
class DslValidator extends AbstractDslValidator {
	
	public static val MISSING_NAME = 'missingName'
	public static val DUPLICATEKEY = 'duplicateKey'
	
	
	public val IConfigurationElement[] exts = org.eclipse.core.runtime.Platform.getExtensionRegistry().getConfigurationElementsFor("metaprogramming.extensionpoint")	
	public var IRuleProvider providedValidator

	@Check
	def checkDSLHasName(Dsl dsl) {
		if (dsl.name.isEmpty) {
			error('Missing an entry "name"', 
					DslPackage.Literals.DSL__NAME,
					MISSING_NAME)
		}
	}
	
	@Check
	def checkDuplicateKeys(Entry entry) {
		val dsl = entry.eContainer as Dsl
		if (!dsl.entries.filter[e | e.key == entry.key].forall[e | e === entry]) {
			error('Duplicate key "'+entry.key+'"', 
					DslPackage.Literals.ENTRY__KEY,
					DUPLICATEKEY)
		}
	}
	

	@Check
	def checkForDSL(Dsl dsl) {
		
		for(IRule rule : providedValidator.getValidationRules()) {
			var message = rule.execute(dsl)
			
			switch message.getSeverity() {
				
				case message.getSeverity() == Severity.ERROR :	error(message.getContent(),
														DslPackage.Literals.DSL__NAME
														)
				case message.getSeverity() == Severity.WARNING :	warning(message.getContent(),
														DslPackage.Literals.DSL__NAME
													)
				case message.getSeverity() == Severity.INFO :	info(message.getContent(),
														DslPackage.Literals.DSL__NAME
													)
				default : message = new Message()
			}
		}
	}
		
	
	// Performs checks provided by the required metaprogramming approach's validator for each entry in the dsl file
	@Check
	def checkForEntry(Entry entry) {
		
		for(IRule rule : providedValidator.getValidationRules()) {
			var message = rule.execute(entry)
			
			switch message.getSeverity() {
				
				case Severity.ERROR :	error(message.getContent(),
														DslPackage.Literals.ENTRY__VALUE
														)
				case Severity.WARNING :	warning(message.getContent(),
														DslPackage.Literals.ENTRY__VALUE
													)
				case Severity.INFO :	info(message.getContent(),
														DslPackage.Literals.ENTRY__VALUE
													)
				case Severity.DEFAULT : message = new Message()
				
				default : print("Unknown severity")
			}
		}

	}
	
	@Check
	def checkForApproachEntry(Dsl dsl) {
		if(dsl.getEntries().filter[e | e.key == "approach"].isEmpty) {
			warning("Missing \"approach\" entry to define the metaprogramming approach used", DslPackage.Literals.DSL__NAME)
		}
	}
	
	@Check
	def checkForApproach(Entry entry){
		if(entry.key == "approach") {	
			for(IConfigurationElement elem : exts) {
				if(entry.value.equals(elem.getAttribute("name"))) {
					providedValidator = elem.createExecutableExtension("validator") as IRuleProvider
				}
			}
						
			var ArrayList<String> approachesList = new ArrayList()
			for (IConfigurationElement e : exts) {
				approachesList.add(e.getAttribute("name"))
			}
			if(!approachesList.contains(entry.value)){
				error("Unknown metaprogramming approach", DslPackage.Literals.ENTRY__VALUE)
			}
			if(entry.value == "Ecore Approach"){
				info("The Ecore Approach only exist to provide an IRuleProvider to other metaprogramming approaches, replace it with one of them instead.", DslPackage.Literals.ENTRY__VALUE)
			}
		}
	}

}
