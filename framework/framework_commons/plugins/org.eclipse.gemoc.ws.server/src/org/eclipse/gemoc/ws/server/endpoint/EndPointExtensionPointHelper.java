/*******************************************************************************
 * Copyright (c) 2020 Inria and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Inria - initial API and implementation
 *******************************************************************************/
package org.eclipse.gemoc.ws.server.endpoint;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.gemoc.ws.server.Activator;

public class EndPointExtensionPointHelper {

		public static String ENDPOINT_EXTENSIONPOINT_ID = "org.eclipse.gemoc.ws.server.endpoint";
		public static String ENDPOINT_EXTENSIONPOINT_ID_ATT = "id";
		public static String ENDPOINT_EXTENSIONPOINT_CLASS_ATT = "class";
	
		public static IConfigurationElement[] getEndPointExtensionConfiguration() {
			return Platform.getExtensionRegistry().getConfigurationElementsFor(ENDPOINT_EXTENSIONPOINT_ID);
		}
		
		public static Object createEndPointObject(IConfigurationElement endPointExtensionConfiguration) throws CoreException {
			return endPointExtensionConfiguration.createExecutableExtension(ENDPOINT_EXTENSIONPOINT_CLASS_ATT);
		}
		
		public static String  getEndPointId(IConfigurationElement endPointExtensionConfiguration) throws CoreException {
			return endPointExtensionConfiguration.getAttribute(ENDPOINT_EXTENSIONPOINT_ID_ATT);
		}
		
		public static  List<Class<?>> getAllEndPointClasses()  {
			ArrayList<Class<?>> result = new ArrayList<Class<?>>();
			for(IConfigurationElement conf : getEndPointExtensionConfiguration()) {
				try {
					result.add(createEndPointObject(conf).getClass());
				} catch (CoreException e) {
					Activator.logError("Failed to get EndPoint class "+e.getMessage(), e);
				}
			}
			return result;
		}
}
