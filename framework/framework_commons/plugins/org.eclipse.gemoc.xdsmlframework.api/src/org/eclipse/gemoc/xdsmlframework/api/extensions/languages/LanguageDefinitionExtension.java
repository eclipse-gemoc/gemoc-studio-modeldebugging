/*******************************************************************************
 * Copyright (c) 2016, 2017 Inria and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Inria - initial API and implementation
 *******************************************************************************/
package org.eclipse.gemoc.xdsmlframework.api.extensions.languages;

//import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.gemoc.xdsmlframework.api.core.IModelLoader;
import org.eclipse.gemoc.xdsmlframework.api.engine_addon.IEngineAddon;
import org.eclipse.gemoc.xdsmlframework.api.extensions.Extension;

public class LanguageDefinitionExtension extends Extension {

//	final public ICodeExecutor instanciateCodeExecutor() throws CoreException {
//		Object instance = instanciate(LanguageDefinitionExtensionPoint.GEMOC_LANGUAGE_EXTENSION_POINT_XDSML_DEF_CODEEXECUTOR_ATT);
//		if (instance instanceof ICodeExecutor) {
//			return (ICodeExecutor) instance;
//		}
//		throwInstanciationCoreException();
//		return null;
//	}


	final public IModelLoader instanciateModelLoader() throws CoreException {
		Object instance = instanciate(LanguageDefinitionExtensionPoint.GEMOC_LANGUAGE_EXTENSION_POINT_XDSML_DEF_LOADMODEL_ATT);
		if (instance instanceof IModelLoader) {
			return (IModelLoader) instance;
		}
		throwInstanciationCoreException();
		return null;
	}


	final public Collection<IEngineAddon> instanciateEngineAddons() throws CoreException {
		ArrayList<IEngineAddon> addons = new ArrayList<IEngineAddon>();
		for (IConfigurationElement childConfElement : _configurationElement
				.getChildren(LanguageDefinitionExtensionPoint.GEMOC_LANGUAGE_EXTENSION_POINT_ENGINE_ADDON_DEF)) {
			childConfElement.getName();
			final Object addon = childConfElement
					.createExecutableExtension(LanguageDefinitionExtensionPoint.GEMOC_LANGUAGE_EXTENSION_POINT_ENGINE_ADDON_DEF_ENGINE_ADDON_ATT);
			if (addon instanceof IEngineAddon) {
				addons.add((IEngineAddon) addon);
			}
		}
		return addons;
	}


	public String getName() {
		return getAttribute(LanguageDefinitionExtensionPoint.GEMOC_LANGUAGE_EXTENSION_POINT_XDSML_DEF_NAME_ATT);
	}

	public String getXDSMLFilePath() {
		return getAttribute(LanguageDefinitionExtensionPoint.GEMOC_LANGUAGE_EXTENSION_POINT_XDSML_DEF_XDSML_FILE_PATH_ATT);
	}
	
	/**
	 * 
	 * @return the list of file extensions defined in the XDSML_Definition_customization
	 */
	public List<String> getFileExtensions() {
		ArrayList<String> fileExtensions = new ArrayList<String>();
		for (IConfigurationElement childConfElement : _configurationElement
				.getChildren(LanguageDefinitionExtensionPoint.GEMOC_LANGUAGE_EXTENSION_POINT_XDSML_CUSTOMIZATION_DEF)) {
			childConfElement.getName();
			final String fileExtensionsString = childConfElement
					.getAttribute(LanguageDefinitionExtensionPoint.GEMOC_LANGUAGE_EXTENSION_POINT_XDSML_CUSTOMIZATION_DEF_FILEEXTENSIONS_ATT);
			if (fileExtensionsString != null) {
				for( String fileExtension : fileExtensionsString.split(",")){
					fileExtensions.add(fileExtension.trim());
				}
			}
		}
		return fileExtensions;
	}

//	private LanguageDefinition _languageDefinitionCache;
//
//	public LanguageDefinition getLanguageDefinition() {
//		if (_languageDefinitionCache == null) {
//
//			// Loading languagedef model
//			ResourceSet rs = new ResourceSetImpl();
//			URI uri = URI.createPlatformPluginURI(getXDSMLFilePath(), true);
//			Resource res = rs.createResource(uri);
//			try {
//				res.load(null);
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			EcoreUtil.resolveAll(rs);// IMPORTANT
//
//			if (res != null) {
//				EObject first = res.getContents().get(0);
//
//				// Follow-up in other operation...
//				if (first instanceof LanguageDefinition) {
//					_languageDefinitionCache = (LanguageDefinition) first;
//				}
//			}
//		}
//		return _languageDefinitionCache;
//	}

}
