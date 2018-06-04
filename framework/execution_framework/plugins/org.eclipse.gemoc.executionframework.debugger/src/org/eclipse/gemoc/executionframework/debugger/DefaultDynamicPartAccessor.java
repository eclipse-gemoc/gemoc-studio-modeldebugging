package org.eclipse.gemoc.executionframework.debugger;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gemoc.xdsmlframework.commons.DynamicAnnotationHelper;
import org.eclipse.xtext.naming.DefaultDeclarativeQualifiedNameProvider;
import org.eclipse.xtext.naming.QualifiedName;

public class DefaultDynamicPartAccessor implements IDynamicPartAccessor {
	
	Map<EClass, Integer> counters = new HashMap<>();

	@Override
	public List<MutableField> extractMutableField(EObject eObject) {
		
		List<MutableField> result = new ArrayList<MutableField>();
		if(eObject == null) {
			return result;
		}

		final List<EStructuralFeature> mutableProperties = 
			eObject
			.eClass()
			.getEAllStructuralFeatures()
			.stream()
			.filter(p -> DynamicAnnotationHelper.isDynamic(p))
			.collect(Collectors.toList());
		
		for(EStructuralFeature feature : mutableProperties) {
			
			Supplier<Object> getter = () -> {
				return eObject.eGet(feature);
			};
			
			Consumer<Object> setter = newValue -> {
				eObject.eSet(feature, newValue);
			};
			
			MutableField field = new MutableField(
				feature.getName()+" ("+getName(eObject)+ " :"+eObject.eClass().getName() +")",
				eObject,
				feature,
				getter,
				setter
				);
			result.add(field);
		}
		
		return result;
	}
	
	private String getName(EObject eObject) {
		DefaultDeclarativeQualifiedNameProvider nameprovider = new DefaultDeclarativeQualifiedNameProvider();
		
		EAttribute idProp = eObject.eClass().getEIDAttribute();
		if (idProp != null) {
			Object id = eObject.eGet(idProp);
			if (id != null) {
				NumberFormat formatter = new DecimalFormat("00");
				String idString = id.toString();
				if(id instanceof Integer){
					idString = formatter.format((Integer)id);
				}
				return eObject.eClass().getName() + "_" + idString;
			} else {
				if (!counters.containsKey(eObject.eClass())) {
					counters.put(eObject.eClass(), 0);
				}
				Integer counter = counters.get(eObject.eClass());
				counters.put(eObject.eClass(), counter + 1);
				return eObject.eClass().getName() + "_" + counter;
			}

		} else {
			QualifiedName qname = nameprovider.getFullyQualifiedName(eObject);
			if(qname == null) 
				return eObject.toString();
			else 
				return qname.toString();
		}
	}

	@Override
	public boolean isDynamic(EObject obj) {
		
		if(obj == null) {
			return false;
		}
		
		return DynamicAnnotationHelper.isDynamic(obj.eClass());
	}

}
