/*******************************************************************************
 * Copyright (c) 2018 Inria and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Inria - initial API and implementation
 *******************************************************************************/
package org.eclipse.gemoc.executionframework.engine.commons;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gemoc.dsl.Dsl;
import org.eclipse.gemoc.dsl.Entry;

import fr.inria.diverse.k3.al.annotationprocessor.Aspect;

/**
 * Helper for dsl file that contains K3 aspects
 *
 */
public class K3DslHelper extends DslHelper {

	/**
	 * Search in 'aspect' for a method named 'calledMethodName' and with one parameter.
	 * The type of this parameter has the same simple name as the EClass of 'caller'.
	 * 
	 * @return Found method from 'aspect'
	 * 
	 * @throws NoSuchMethodException 
	 */
	public static Method findMethod(Class<?> aspect, EObject caller, String calledMethodName) throws NoSuchMethodException{
		String callerClassName = caller.eClass().getName(); 
		for(Method method : aspect.getMethods()){
			String methodName = method.getName();
			Class<?>[] paramTypes = method.getParameterTypes();
			if(methodName.equals(calledMethodName) 
					&& paramTypes.length == 1 
					&& paramTypes[0].getSimpleName().equals(callerClassName)){
				return method;
			}
		}
		
		throw new java.lang.NoSuchMethodException();
	}
	
	/**
	 * Get all methods from Aspects of 'Language' tagged @Main
	 */
	public static List<Method> getEntryPoints(String language){
		
		List<Method> res = new ArrayList<Method>();
		
		Set<Class<?>> aspects = getAspects(language);
		for (Class<?> asp : aspects) {
			for(Method m : asp.getMethods())
			{
				if(isAnnotedMain(m))
				{
					res.add(m);
				}
			}
		}
		
		return res;
	}
	
	/**
	 * @return Aspects defined in 'languageName'
	 */
	public static Set<Class<?>> getAspects(String languageName) {
		Set<Class<?>> res = new HashSet<Class<?>>();
		
		Dsl dsl = DslHelper.load(languageName);
		if(dsl != null) {
			
			Optional<Entry> semantics = dsl.getEntries()
				.stream()
				.filter(entry -> entry.getKey().equals("k3"))
				.findFirst();
			if(semantics.isPresent()) {
				String[] classNames = semantics.get().getValue().split(",");
				for (String asp : classNames) {
					Class<?> cls = loadAspect(languageName, asp.trim());
					if(cls != null) {
						res.add(cls);
					}
				}
			}
		}
		
		return res;
	}

	/**
	 * Return the targeted class from the @Aspect
	 */
	public static Class<?> getTarget(Class<?> aspect){
		Annotation annotation = aspect.getAnnotation(fr.inria.diverse.k3.al.annotationprocessor.Aspect.class);
		if(annotation != null){
				Aspect k3tag = (Aspect) annotation;
				return k3tag.className();
		}
		
		return null;
	}

	/**
	 * Return all classes from 'languageName' weaved on 'target'
	 */
	public static List<Class<?>> getAspectsOn(String languageName, Class<?> target){
		List<Class<?>> res = new ArrayList<Class<?>>();
		
		for(Class<?> aspect : getAspects(languageName)){
			Class<?> aspectTarget = getTarget(aspect);
			if(aspectTarget.isAssignableFrom(target)){
				res.add(aspect);
			}
		}
		
		return res;
	}
	
	/**
	 * Return a class matching 'aspectName' or null if can't be loaded.
	 */
	public static Class<?> loadAspect(String languageName, String aspectName){
		try {
			return getDslBundle(languageName).loadClass(aspectName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	/**
	 * Return true is their is @Main
	 */
	public static boolean isAnnotedMain(Method m){
		return m.isAnnotationPresent(fr.inria.diverse.k3.al.annotationprocessor.Main.class);
	}
	
	/**
	 * Expected format for signature:
	 * qualifiedClassName.methodName(qualifiedClassName,qualifiedClassName,...)
	 * 
	 * @return qualified class name before '(' without method name
	 */
	public static String getClassNameFromMethodSignature(String signature){
		String method = signature.substring(0, signature.indexOf("("));
		String className = method.substring(0,method.lastIndexOf("."));
		return className;
	}
	
	/**
	 * Expected format for signature:
	 * qualifiedClassName.methodName(qualifiedClassName,qualifiedClassName,...)
	 * 
	 * @return method name before '(' without class name
	 */
	public static String getMethodNameFromMethodSignature(String signature) {
		String method = signature.substring(0, signature.indexOf("("));
		String methodName = method.substring(method.lastIndexOf("."));
		return methodName;
	}
	/**
	 * Create an array of the parameter types from a method signature
	 * Expected format for signature:
	 * qualifiedClassName.methodName(qualifiedClassName,qualifiedClassName,...)
	 * such signature string is typically obtained via {@link SelectMainMethodDialog}
	 * 
	 * @return array of qualified class names between '(' and ')' 
	 * 
	 * ex: "org.company.MyClass.foo(java.lang.String,java.lang.String,java.lang.Integer)"
	 * will return the following array ["java.lang.String","java.lang.String","java.lang.Integer"]
	 */
	public static String[] getParametersTypeFromMethodSignature(String signature){
		String args = signature.substring(signature.indexOf("(")+1,signature.indexOf(")"));
		return args.split(",");
	}
	
}
