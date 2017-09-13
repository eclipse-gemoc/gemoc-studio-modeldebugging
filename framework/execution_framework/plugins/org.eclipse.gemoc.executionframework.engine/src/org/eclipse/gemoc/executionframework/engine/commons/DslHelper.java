package org.eclipse.gemoc.executionframework.engine.commons;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;

public class DslHelper {
	
	/**
	 * Return a bundle with a .dsl declaring 'languageName'
	 */
	public static Bundle getDslBundle(String languageName) {
		
		IConfigurationElement[] languages = Platform.getExtensionRegistry().getConfigurationElementsFor("org.gemoc.gemoc_language_workbench.sequential.xdsml");
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
		IConfigurationElement[] languages = Platform.getExtensionRegistry().getConfigurationElementsFor("org.gemoc.gemoc_language_workbench.sequential.xdsml");
		for (IConfigurationElement lang : languages) {
			languagesNames.add(lang.getAttribute("name"));
		}
		return languagesNames;
	}
	
	public static Properties load(String languageName) {
		
		InputStream dslFile = null;
		IConfigurationElement[] languages = Platform.getExtensionRegistry().getConfigurationElementsFor("org.gemoc.gemoc_language_workbench.sequential.xdsml");
		for (IConfigurationElement lang : languages) {
			String xdsmlPath = lang.getAttribute("xdsmlFilePath");
			String xdsmlName = lang.getAttribute("name");
			if (xdsmlName.equals(languageName) && xdsmlPath.endsWith(".dsl")) {
				String dslBundleName = lang.getContributor().getName();
				Bundle bundle = Platform.getBundle(dslBundleName);
				URL fileURL = bundle.getEntry(xdsmlPath.substring(dslBundleName.length()+1));
				try {
					dslFile = fileURL.openConnection().getInputStream();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		Properties dslProp = new Properties();
		if(dslFile != null) {
			try {
				dslProp.load(dslFile);
				dslFile.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return dslProp;
	}
}
