/*******************************************************************************
 * Copyright (c) 2016 Inria and others.
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
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.osgi.framework.Bundle;

import fr.inria.diverse.k3.al.annotationprocessor.Aspect;

/**
 * Helper class to get informations about languages defined in Melange
 */
public class MelangeHelper {

	/**
	 * Return a bundle with a .melange declaring 'language'
	 */
	public static Bundle getMelangeBundle(String languageName){
		
		IConfigurationElement[] melangeLanguages = Platform
				.getExtensionRegistry().getConfigurationElementsFor(
						"fr.inria.diverse.melange.language");
		String melangeBundleName = "";
		
		for (IConfigurationElement lang : melangeLanguages) {
			if(lang.getAttribute("id").equals(languageName)){
				melangeBundleName = lang.getContributor().getName();
				return Platform.getBundle(melangeBundleName);
			}
		}
		
		return null;
	}
	
	/**
	 * Return all Languages defined with Melange
	 */
	public static List<String> getAllLanguages(){
		List<String> languagesNames = new ArrayList<String>();
		IConfigurationElement[] melangeLanguages = Platform
				.getExtensionRegistry().getConfigurationElementsFor(
						"fr.inria.diverse.melange.language");
		for (IConfigurationElement lang : melangeLanguages) {
			languagesNames.add(lang.getAttribute("id"));
		}
		return languagesNames;
	}
	
	/**
	 * @param languageId
	 * @return the URI of the given language or null if no such language exists in the registry
	 */
	public static String getLanguageURI(String languageId){
		IConfigurationElement[] melangeLanguages = Platform
				.getExtensionRegistry().getConfigurationElementsFor(
						"fr.inria.diverse.melange.language");
		for (IConfigurationElement lang : melangeLanguages) {
			if(lang.getAttribute("id").equals(languageId)){
				return lang.getAttribute("uri");
			}
		}
		return null;
	}
	
	
	/**
	 * Note: in some situations, the same uri can be declared for several languages ! (for example using external keyword)
	 * this is usually a deployment issue
	 * @param uri
	 * @return the first language with the given URI or null if no such language exists in the registry
	 */
	public static String getLanguageNameForURI(String uri){
		IConfigurationElement[] melangeLanguages = Platform
				.getExtensionRegistry().getConfigurationElementsFor(
						"fr.inria.diverse.melange.language");
		for (IConfigurationElement lang : melangeLanguages) {
			if(lang.getAttribute("uri").equals(uri)){
				return lang.getAttribute("id");
			}
		}
		return null;
	}
	public static List<String> getLanguageNamesForURI(String uri){
		List<String> languagesNames = new ArrayList<String>();
		IConfigurationElement[] melangeLanguages = Platform
				.getExtensionRegistry().getConfigurationElementsFor(
						"fr.inria.diverse.melange.language");
		for (IConfigurationElement lang : melangeLanguages) {
			if(lang.getAttribute("uri").equals(uri)){
				String id = lang.getAttribute("id");
				if(!languagesNames.contains(id)){
					languagesNames.add(id);
				}
			}
		}
		return languagesNames;
	}
	
	/**
	 * Find in the given resource the native languages corresponding to the element at the root of the resource
	 * (built as a set in order to have no duplicate)
	 * @param res
	 * @return a list of all the native languages of the root elements of the resource  
	 */
	public static List<String> getNativeLanguagesUsedByResource(Resource res){
		LinkedHashSet<String> usedLanguages = new LinkedHashSet<String>();
		for(EObject eobj : res.getContents()){
			usedLanguages.addAll(getLanguageNamesForURI(eobj.eClass().eResource().getURI().toString()));
		}
		List<String> languagesNames = new ArrayList<String>();
		languagesNames.addAll(usedLanguages);
		return languagesNames;
	}
	
	/**
	 * @return Aspects defined in 'languageName'
	 */
	public static Set<Class<?>> getAspects(String languageName){
		Set<Class<?>> res = new HashSet<Class<?>>();
		
		IConfigurationElement[] melangeLanguages = Platform
				.getExtensionRegistry().getConfigurationElementsFor(
						"fr.inria.diverse.melange.language");
		
		String serializedAspects = "";
		for (IConfigurationElement lang : melangeLanguages) {
			if (lang.getAttribute("id").equals(languageName)) {
				serializedAspects = lang.getAttribute("aspects");
				break;
			}
		}
		if(serializedAspects.isEmpty()) return res;
		Set<String> classNames = new HashSet<String>();
		//serializedAspects is a list of pairs (target : aspects)
		for (String rawPair : serializedAspects.split(";")) { // ; is the separator between pairs
			String[] pair = rawPair.split(":"); // : the separator between target & aspects
			String[] weavedAsp = pair[1].split(","); // , the separator between aspects
			for (String asp : weavedAsp) {
				classNames.add(asp);
			}
		}
		for (String asp : classNames) {
			Class<?> cls = loadAspect(languageName, asp);
			res.add(cls);
		}
		
		return res;
	}
	
	/**
	 * Return all ModelTypes matching 'language'
	 */
	public static List<String> getModelTypes(String language){
		List<String> modelTypeNames = new ArrayList<String>();
		IConfigurationElement[] melangeLanguages = Platform
				.getExtensionRegistry().getConfigurationElementsFor(
						"fr.inria.diverse.melange.language");
		for (IConfigurationElement lang : melangeLanguages) {
			if (lang.getAttribute("id").equals(language)) {
				IConfigurationElement[] adapters = lang
						.getChildren("adapter");
				for (IConfigurationElement adapter : adapters) {
					modelTypeNames.add(adapter
							.getAttribute("modeltypeId"));
				}
			}
		}
		return modelTypeNames;
	}
	
	/**
	 * Return the  ModelType for 'language' or null if not found
	 */
	public static String getModelType(String language){
		IConfigurationElement[] melangeLanguages = Platform
				.getExtensionRegistry().getConfigurationElementsFor(
						"fr.inria.diverse.melange.language");
		for (IConfigurationElement lang : melangeLanguages) {
			if (lang.getAttribute("id").equals(language)) {
				return lang.getAttribute("modeltypeId");
			}
		}
		return null;
	}

	/**
	 * Return a class matching 'aspectName' or null if can't be loaded.
	 */
	public static Class<?> loadAspect(String languageName, String aspectName){
		try {
			return getMelangeBundle(languageName).loadClass(aspectName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
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
	
	
	public static String[] getSignatures(String entryPoints){
		return entryPoints.split(";");
	}
	
	/**
	 * Expected format for signature:
	 * qualifiedClassName.methodName(qualifiedClassName,qualifiedClassName,...)
	 * 
	 * @return qualified class name before '(' without method name
	 */
	public static String getClassName(String signature){
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
	public static String getMethodName(String signature) {
		String method = signature.substring(0, signature.indexOf("("));
		String methodName = method.substring(method.lastIndexOf("."));
		return methodName;
	}
	
	/**
	 * Expected format for signature:
	 * qualifiedClassName.methodName(qualifiedClassName,qualifiedClassName,...)
	 * 
	 * @return qualified class names between '(' and ')' 
	 */
	public static String[] getParametersType(String signature){
		String args = signature.substring(signature.indexOf("(")+1,signature.indexOf(")"));
		return args.split(",");
	}
	
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
	 * Return the substring after the last '.' or the whole 'qualifiedName' if
	 * no dot character.
	 */
	public static String lastSegment(String qualifiedName){
		try{
			return qualifiedName.substring(qualifiedName.lastIndexOf(".")+1);
		}
		catch(IndexOutOfBoundsException e){
			//The String end with '.'
		}
		return "";
	}
	
	/**
	 * Return true is their is @Main
	 */
	public static boolean isAnnotedMain(Method m){
		return m.isAnnotationPresent(fr.inria.diverse.k3.al.annotationprocessor.Main.class);
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
}