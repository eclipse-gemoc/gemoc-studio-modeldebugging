package org.eclipse.gemoc.commons.eclipse.xtext;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gemoc.commons.eclipse.emf.EObjectUtil;
import org.eclipse.xtext.naming.DefaultDeclarativeQualifiedNameProvider;
import org.eclipse.xtext.naming.QualifiedName;

public class NameHelper {
	static DefaultDeclarativeQualifiedNameProvider nameprovider = new DefaultDeclarativeQualifiedNameProvider();
	
	static public String prettyObjectName(Object o) {
		if(o == null) return "null";
		else if(o instanceof EObject) return prettyObjectName((EObject)o);
		else if(o instanceof String) return "\""+o+"\"";
		else return o.toString();
	}
	
	static public String prettyObjectName(EObject o) {
		String typeName = o.eClass().getName();
		String objectName;
		QualifiedName qn = nameprovider.getFullyQualifiedName(o);
		if(qn != null) {
			objectName = qn.toString();
		} else {
			String resBasedName = EObjectUtil.getResourceBasedName(o, false);
			if( resBasedName != null) {
				objectName = resBasedName;
			} else {
				objectName = o.toString();
			}
		}
		
		return "["+typeName+"] "+ objectName;
	}

}
