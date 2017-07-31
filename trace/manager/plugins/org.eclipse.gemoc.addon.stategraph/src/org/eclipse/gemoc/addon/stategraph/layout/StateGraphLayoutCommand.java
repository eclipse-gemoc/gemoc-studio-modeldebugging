/*******************************************************************************
 * Copyright (c) 2017 Inria and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Inria - initial API and implementation
 *******************************************************************************/
package org.eclipse.gemoc.addon.stategraph.layout;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.eclipse.elk.alg.layered.p2layers.LayeringStrategy;
import org.eclipse.elk.alg.layered.properties.LayeredOptions;
import org.eclipse.elk.core.LayoutConfigurator;
import org.eclipse.elk.core.options.CoreOptions;
import org.eclipse.elk.core.service.DiagramLayoutEngine;
import org.eclipse.elk.core.service.DiagramLayoutEngine.Parameters;
import org.eclipse.elk.graph.KNode;
import org.eclipse.elk.graph.properties.Property;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.gemoc.addon.stategraph.logic.StateVertex;
import org.eclipse.gemoc.addon.stategraph.views.VertexView;

public class StateGraphLayoutCommand {

	final private IWorkbenchPart workbenchPart;

	final private Property<Map<StateVertex, VertexView>> VERTEX2SHAPE_MAP = new Property<Map<StateVertex, VertexView>>(
			"vertex.to.shape.map", new HashMap<>());

	public StateGraphLayoutCommand(IWorkbenchPart workbenchPart) {
		this.workbenchPart = workbenchPart;
	}

	public void applyLayout(Map<StateVertex, VertexView> nodeToShape, Set<StateVertex> movedVertice) {
		Parameters params = new Parameters();
		LayoutConfigurator configurator = new LayoutConfigurator();
		configurator.configure(KNode.class)
				.setProperty(CoreOptions.ALGORITHM, "org.eclipse.elk.layered")
				.setProperty(LayeredOptions.LAYERING_STRATEGY, LayeringStrategy.COFFMAN_GRAHAM)
				.setProperty(LayeredOptions.LAYERING_COFFMAN_GRAHAM_LAYER_BOUND, 5)
				.setProperty(CoreOptions.SPACING_NODE, 50.0f)
				.setProperty(VERTEX2SHAPE_MAP, nodeToShape);
		params.addLayoutRun(configurator);
		DiagramLayoutEngine.invokeLayout(workbenchPart, null, params);
	}
}
