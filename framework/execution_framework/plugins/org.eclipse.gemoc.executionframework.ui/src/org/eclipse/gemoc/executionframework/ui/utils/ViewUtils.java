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
package org.eclipse.gemoc.executionframework.ui.utils;

import org.eclipse.xtext.util.SimpleAttributeResolver;

import org.eclipse.gemoc.trace.commons.model.trace.MSE;

public class ViewUtils {

	public static String eventToString(MSE mse) {
		StringBuilder builder = new StringBuilder();
		if (mse.getCaller() != null)
		{
			builder.append(mse.getCaller().eClass().getName());
			builder.append("->");
			builder.append(SimpleAttributeResolver.NAME_RESOLVER.apply(mse.getCaller()));
			
		}
		if (mse.getAction() != null)
		{
			builder.append(".");
			builder.append(mse.getAction().getName());			
			builder.append("()");
		}
		return builder.toString();
	}
	
}
