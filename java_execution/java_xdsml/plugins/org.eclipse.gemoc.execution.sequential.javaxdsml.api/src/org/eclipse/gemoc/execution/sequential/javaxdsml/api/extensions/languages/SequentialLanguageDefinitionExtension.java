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
package org.eclipse.gemoc.execution.sequential.javaxdsml.api.extensions.languages;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.gemoc.executionframework.debugger.AbstractGemocDebuggerFactory;
import org.eclipse.gemoc.xdsmlframework.api.extensions.languages.LanguageDefinitionExtension;

public class SequentialLanguageDefinitionExtension extends LanguageDefinitionExtension {

	final public AbstractGemocDebuggerFactory instanciateDSLDebuggerFactory() throws CoreException {
		Object instance = instanciate(SequentialLanguageDefinitionExtensionPoint.GEMOC_SEQUENTIAL_LANGUAGE_EXTENSION_POINT_XDSML_DEF_GEMOCDEBUGGERFACTORY_ATT);
		if (instance instanceof AbstractGemocDebuggerFactory) {
			return (AbstractGemocDebuggerFactory) instance;
		}
		throwInstanciationCoreException();
		return null;
	}

	final public String getDSLDebuggerFactoryName() {
		return getAttribute(SequentialLanguageDefinitionExtensionPoint.GEMOC_SEQUENTIAL_LANGUAGE_EXTENSION_POINT_XDSML_DEF_GEMOCDEBUGGERFACTORY_ATT);		
	}


//	final public Collection<IMSEStateController> instanciateMSEStateControllers() throws CoreException {
//		ArrayList<IMSEStateController> controllers = new ArrayList<IMSEStateController>();
//		for (IConfigurationElement childConfElement : _configurationElement
//				.getChildren(SequentialLanguageDefinitionExtensionPoint.GEMOC_LANGUAGE_EXTENSION_POINT_MSE_STATE_CONTROLLER_DEFINITION)) {
//			childConfElement.getName();
//			final Object c = childConfElement
//					.createExecutableExtension(SequentialLanguageDefinitionExtensionPoint.GEMOC_LANGUAGE_EXTENSION_POINT_MSE_STATE_CONTROLLER_CLASS_DEFINITION);
//			if (c instanceof IMSEStateController) {
//				controllers.add((IMSEStateController) c);
//			}
//		}
//		return controllers;
//	}


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
