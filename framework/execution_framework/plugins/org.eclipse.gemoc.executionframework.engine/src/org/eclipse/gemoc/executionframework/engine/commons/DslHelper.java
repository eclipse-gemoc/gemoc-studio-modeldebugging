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
import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.gemoc.dsl.Dsl;
import org.eclipse.gemoc.xdsmlframework.api.extensions.languages.LanguageDefinitionExtensionPoint;
import org.osgi.framework.Bundle;

/**
 * General helper for dsl file
 * 
 */
public class DslHelper {

	/**
	 * Return a bundle with a .dsl declaring 'languageName'
	 */
	public static Bundle getDslBundle(String languageName) {

		IConfigurationElement[] languages = Platform.getExtensionRegistry().getConfigurationElementsFor(LanguageDefinitionExtensionPoint.GEMOC_LANGUAGE_EXTENSION_POINT);
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
	public static List<String> getAllLanguages() {
		List<String> languagesNames = new ArrayList<String>();
		IConfigurationElement[] languages = Platform.getExtensionRegistry().getConfigurationElementsFor(LanguageDefinitionExtensionPoint.GEMOC_LANGUAGE_EXTENSION_POINT);
		for (IConfigurationElement lang : languages) {
			languagesNames.add(lang.getAttribute("name"));
		}
		return languagesNames;
	}

	/**
	 * Load the language from installed languages
	 * converts the xdsmlFilePath into platform:/plugin/ URI
	 * @param languageName
	 * @return the root element of the dsl model
	 */
	public static Dsl load(String languageName) {

		IConfigurationElement[] languages = Platform.getExtensionRegistry().getConfigurationElementsFor(LanguageDefinitionExtensionPoint.GEMOC_LANGUAGE_EXTENSION_POINT);
		for (IConfigurationElement lang : languages) {
			String xdsmlPath = lang.getAttribute("xdsmlFilePath");
			String xdsmlName = lang.getAttribute("name");
			if (xdsmlName.equals(languageName) && xdsmlPath.endsWith(".dsl")) {
				URI xdsmlURI = xdsmlPath.startsWith("platform:/") ? URI.createURI(xdsmlPath) : URI.createPlatformPluginURI(xdsmlPath, true);
				Resource res = (new ResourceSetImpl()).getResource(xdsmlURI, true);
				Dsl dsl = (Dsl) res.getContents().get(0);
				return dsl;
			}
		}
		return null;
	}

	/**
	 * Return the substring after the last '.' or the whole 'qualifiedName' if no
	 * dot character.
	 */
	public static String lastSegment(String qualifiedName) {
		if (qualifiedName.contains(".")) {
			return qualifiedName.substring(qualifiedName.lastIndexOf(".") + 1);
		} else {
			return qualifiedName;
		}
	}
}
