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
package org.eclipse.gemoc.xdsmlframework.ide.ui.builder.pde;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.gemoc.xdsmlframework.api.extensions.languages.LanguageDefinitionExtensionPoint;
import org.eclipse.gemoc.xdsmlframework.ide.ui.Activator;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.filter.ElementFilter;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class PluginXMLHelper {

	public static final String PLUGIN_FILENAME = "plugin.xml";

	public static final String PLUGIN_TEMPLATE = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
			+ "<?eclipse version=\"3.4\"?>\n" + "<plugin>\n" + "</plugin>";

	public static void createEmptyTemplateFile(IFile pluginXmlFile, boolean overrideIfExist) {
		if (overrideIfExist && pluginXmlFile.exists()) {
			try {
				pluginXmlFile.delete(true, null);
			} catch (CoreException e) {
				Activator.error(e.getMessage(), e);
			}
		}
		if (!pluginXmlFile.exists()) {
			try {
				InputStream stream = new ByteArrayInputStream(PLUGIN_TEMPLATE.getBytes());
				if (pluginXmlFile.exists()) {
					pluginXmlFile.setContents(stream, true, true, null);
				} else {
					pluginXmlFile.create(stream, true, null);
				}
				stream.close();
			} catch (IOException e) {
				Activator.error(e.getMessage(), e);
			} catch (CoreException e) {
				Activator.error(e.getMessage(), e);
			}
		}
	}

	protected Document document;
	protected Element root;

	/**
	 * Load the xml document
	 * @param pluginXmlFile
	 */
	public void loadDocument(IFile pluginXmlFile) {
		SAXBuilder sxb = new SAXBuilder();
		try {
			if (!pluginXmlFile.isSynchronized(IResource.DEPTH_ZERO)) {
				pluginXmlFile.refreshLocal(IResource.DEPTH_ZERO, new NullProgressMonitor());
			}
			document = sxb.build(pluginXmlFile.getContents());

			root = document.getRootElement();
		} catch (Exception e) {
			Activator.error(e.getMessage(), e);
		}
	}

	/**
	 * Save the xml document
	 * @param pluginXmlFile
	 */
	public void saveDocument(IFile pluginXmlFile) {
		try {
			XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());

			ByteArrayOutputStream stream = new ByteArrayOutputStream();

			sortie.output(document, stream);
			InputStream inputStream = new ByteArrayInputStream(stream.toByteArray());
			pluginXmlFile.setContents(inputStream, IResource.FORCE, null);
		} catch (IOException e) {
			Activator.error(e.getMessage(), e);
		} catch (CoreException e) {
			Activator.error(e.getMessage(), e);
		}
	}

	/**
	 * in the given extension point, create or update an XDSML_Definition entry then
	 * set the name attribute it supposes that there is only one XDSML_Definition in
	 * the extension point
	 * 
	 * @param extensionPoint
	 * @param xDSMLName
	 * @return
	 */
	public Element updateXDSMLDefinitionInExtensionPoint(Element extensionPoint, String xDSMLName) {
		Element result;
		List<Element> elements = extensionPoint.getContent(
				new ElementFilter(LanguageDefinitionExtensionPoint.GEMOC_LANGUAGE_EXTENSION_POINT_XDSML_DEF));
		if (elements.size() == 0) {
			// create extension point
			result = new Element(LanguageDefinitionExtensionPoint.GEMOC_LANGUAGE_EXTENSION_POINT_XDSML_DEF);
			extensionPoint.addContent(result);
		} else {
			result = elements.get(0);
		}
		result.setAttribute("name", xDSMLName);
		return result;
	}

	/**
	 * in the given extension point, create or update an XDSML_Definition entry then
	 * set the attribute value it supposes that there is only one XDSML_Definition
	 * in the extension point
	 * 
	 * @param extensionPoint
	 * @param attributeName
	 * @param value
	 * @return
	 */
	public Element updateXDSMLDefinitionAttributeInExtensionPoint(Element extensionPoint, String attributeName,
			String value) {
		return updateAttributeInExtensionPoint(extensionPoint,
				LanguageDefinitionExtensionPoint.GEMOC_LANGUAGE_EXTENSION_POINT_XDSML_DEF, attributeName, value);
	}

	public String getXDSMLDefinitionAttributeInExtensionPointValue(Element extensionPoint, String atributeName) {
		Element result;
		List<Element> elements = extensionPoint.getContent(
				new ElementFilter(LanguageDefinitionExtensionPoint.GEMOC_LANGUAGE_EXTENSION_POINT_XDSML_DEF));
		if (elements.size() == 0) {
			// create extension point
			result = new Element(LanguageDefinitionExtensionPoint.GEMOC_LANGUAGE_EXTENSION_POINT_XDSML_DEF);
			extensionPoint.addContent(result);
		} else {
			result = elements.get(0);
		}
		return result.getAttributeValue(atributeName);
	}

	/**
	 * in the given extension point, create or update an entry as defined by
	 * extensionDefinition then set the attribute value it supposes that there is
	 * only one rootElementDefinition in the extension point
	 * 
	 * @param extensionPoint
	 * @param extensionDefinition
	 * @param attributeName
	 * @param value
	 * @return
	 */
	public Element updateAttributeInExtensionPoint(Element extensionPoint, String extensionDefinition,
			String attributeName, String value) {
		Element result;
		List<Element> elements = extensionPoint.getContent(new ElementFilter(extensionDefinition));
		if (elements.size() == 0) {
			// create extension point
			result = new Element(extensionDefinition);
			extensionPoint.addContent(result);
		} else {
			result = elements.get(0);
		}
		result.setAttribute(attributeName, value);
		return result;
	}

	public Element getOrCreateExtensionPoint(String extensionPointName) {
		Element result;
		List<Element> elements = root.getContent(new ExtensionFilter(extensionPointName));
		if (elements.size() == 0) {
			// create extension point
			result = new Element("extension");
			result.setAttribute("point", extensionPointName);
			root.addContent(result);
		} else {
			result = elements.get(0);
		}
		return result;
	}

	public List<Element> getExtensionPoints(String extensionPointName) {
		return root.getContent(new ExtensionFilter(extensionPointName));
	}

	/**
	 * in the given extension point, find the first XDSML_Defintion entry then get the attribute value.
	 * @param extensionPoint
	 * @param attributeName
	 * @return
	 */
	public String getXDSMLDefinitionAttribute(Element extensionPoint, String attributeName) {
		return getAttributeInExtension(extensionPoint,
				LanguageDefinitionExtensionPoint.GEMOC_LANGUAGE_EXTENSION_POINT_XDSML_DEF, attributeName);
	}

	/**	 
	 * in the given extension point, find the first entry as defined by
	 * extensionDefinition then get the attribute value.
	 * @param extensionPoint
	 * @param extensionDefinition
	 * @param attributeName
	 * @return
	 */
	public String getAttributeInExtension(Element extensionPoint, String extensionDefinition, String attributeName) {
		List<Element> elements = extensionPoint.getContent(new ElementFilter(extensionDefinition));
		if (!elements.isEmpty()) {
			return elements.get(0).getAttributeValue(attributeName);
		}
		return null;
	}

	public class ExtensionFilter extends ElementFilter {

		/**
		 * 
		 */
		private static final long serialVersionUID = -1941823619643486082L;
		protected String extensionPointName;

		public ExtensionFilter(String extensionPointName) {
			super("extension");
			this.extensionPointName = extensionPointName;
		}

		@Override
		public Element filter(Object content) {
			Element result = super.filter(content);
			if (result == null)
				return null;
			Attribute att = result.getAttribute("point");
			if (att == null)
				return null;
			if (att.getValue().equals(extensionPointName))
				return result;
			else
				return null;
		}

	}

}
