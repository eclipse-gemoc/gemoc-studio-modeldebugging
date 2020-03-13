package org.eclipse.gemoc.executionframework.debugger

import org.eclipse.emf.ecore.EObject
import java.util.ArrayList
import java.util.List


/**
 * Dynamic partAccessor that compose the behavior of several IDynamicPartAccessor
 * The order in which they are declared is used to ignore conflicting properties/features 
 */
class CompositeDynamicPartAccessor implements IDynamicPartAccessor {
	
	List<IDynamicPartAccessor> internalDynamicPartAccessors = new ArrayList<IDynamicPartAccessor>();
	
	
	new(List<IDynamicPartAccessor> internalDynamicPartAccessors) {
		this.internalDynamicPartAccessors.addAll(internalDynamicPartAccessors);
	}
	
	override isDynamic(EObject obj) {
		return internalDynamicPartAccessors.exists[accessor | accessor.isDynamic(obj)]
	}
	
	override extractMutableField(EObject eObject) {
		val List<MutableField> res =  new ArrayList<MutableField>();
		for(IDynamicPartAccessor accessor : internalDynamicPartAccessors){
			val List<MutableField> candidateMutableFields = accessor.extractMutableField(eObject)
			// consider mutable field only if there is no previously found mutable field with the same name
			val List<MutableField> acceptedMutableFields = candidateMutableFields.filter[mf1 | ! res.exists[mf2 | mf1.name == mf2.name]].toList
			res.addAll(acceptedMutableFields)
		}
		return res;
	}
	
}