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

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.osgi.framework.Bundle;

/**
 * Helper class to get informations about languages defined in Melange
 * 
 */
public class MelangeHelper extends K3DslHelper{

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
	public static List<String> getAllMelangeLanguages(){
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

}