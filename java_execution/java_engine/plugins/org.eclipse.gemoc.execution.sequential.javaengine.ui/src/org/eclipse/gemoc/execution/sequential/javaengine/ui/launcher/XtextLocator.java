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
package org.eclipse.gemoc.execution.sequential.javaengine.ui.launcher;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;

import org.eclipse.gemoc.dsl.debug.ide.adapter.ILocator;

public class XtextLocator implements ILocator {

	@Override
	public Location getLocation(EObject eObject) {
		INode node = NodeModelUtils.getNode(eObject);
		if (node != null) {
			return new Location(Type.XTEXT_LOCATION, node.getStartLine());
		}
		return new Location(Type.XTEXT_LOCATION, -1);
	}
}
