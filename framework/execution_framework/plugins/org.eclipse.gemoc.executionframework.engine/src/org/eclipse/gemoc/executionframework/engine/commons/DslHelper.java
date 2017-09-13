package org.eclipse.gemoc.executionframework.engine.commons;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.gemoc.dsl.Dsl;
import org.osgi.framework.Bundle;

public class DslHelper {
	
	/**
	 * Return a bundle with a .dsl declaring 'languageName'
	 */
	public static Bundle getDslBundle(String languageName) {
		
		IConfigurationElement[] languages = Platform.getExtensionRegistry().getConfigurationElementsFor("org.eclipse.gemoc.gemoc_language_workbench.sequential.xdsml");
		for (IConfigurationElement lang : languages) {
			String xdsmlPath = lang.getAttribute("xdsmlFilePath");
			String xdsmlName = lang.getAttribute("name");
			if (xdsmlName.equals(languageName) && xdsmlPath.endsWith(".dsl")) {
				String dslBundleName = lang.getContributor().getName();
				return Platform.getBundle(dslBundleName);
			}
		}
		return null;
	}
	
	/**
	 * Return all declared Languages
	 */
	public static List<String> getAllLanguages(){
		List<String> languagesNames = new ArrayList<String>();
		IConfigurationElement[] languages = Platform.getExtensionRegistry().getConfigurationElementsFor("org.eclipse.gemoc.gemoc_language_workbench.sequential.xdsml");
		for (IConfigurationElement lang : languages) {
			languagesNames.add(lang.getAttribute("name"));
		}
		return languagesNames;
	}
	
	public static Dsl load(String languageName) {
		
		IConfigurationElement[] languages = Platform.getExtensionRegistry().getConfigurationElementsFor("org.eclipse.gemoc.gemoc_language_workbench.sequential.xdsml");
		for (IConfigurationElement lang : languages) {
			String xdsmlPath = lang.getAttribute("xdsmlFilePath");
			String xdsmlName = lang.getAttribute("name");
			if (xdsmlName.equals(languageName) && xdsmlPath.endsWith(".dsl")) {
				Resource res = (new ResourceSetImpl()).getResource(URI.createURI(xdsmlPath), true);
				Dsl dsl = (Dsl) res.getContents().get(0);
				return dsl;
			}
		}
		return null;
	}
}
