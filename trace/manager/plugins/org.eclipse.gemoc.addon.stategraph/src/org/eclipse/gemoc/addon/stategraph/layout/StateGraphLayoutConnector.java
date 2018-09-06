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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.elk.core.math.KVector;
import org.eclipse.elk.core.options.CoreOptions;
import org.eclipse.elk.core.service.IDiagramLayoutConnector;
import org.eclipse.elk.core.service.LayoutMapping;
import org.eclipse.elk.core.util.ElkUtil;
import org.eclipse.elk.graph.ElkEdge;
import org.eclipse.elk.graph.ElkGraphElement;
import org.eclipse.elk.graph.ElkNode;
import org.eclipse.elk.graph.properties.IPropertyHolder;
import org.eclipse.elk.graph.properties.Property;
import org.eclipse.elk.graph.util.ElkGraphUtil;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.gemoc.addon.stategraph.logic.DirectedGraph;
import org.eclipse.gemoc.addon.stategraph.logic.StateGraph;
import org.eclipse.gemoc.addon.stategraph.logic.StateVertex;
import org.eclipse.gemoc.addon.stategraph.views.StateGraphViewPart;
import org.eclipse.gemoc.addon.stategraph.views.VertexView;

public class StateGraphLayoutConnector implements IDiagramLayoutConnector {

	@Override
	public LayoutMapping buildLayoutGraph(IWorkbenchPart workbenchPart, Object diagramPart) {

		StateGraphViewPart layoutRootPart = null;

		if (!(workbenchPart instanceof StateGraphViewPart)) {
			return null;
		}

		layoutRootPart = (StateGraphViewPart) workbenchPart;
		final StateGraph stateGraph = layoutRootPart.getStateGraph();
		LayoutMapping mapping = new LayoutMapping(workbenchPart);
		mapping.setParentElement(layoutRootPart);

		ElkNode topNode = ElkGraphUtil.createNode(null);
		mapping.getGraphMap().put(topNode, layoutRootPart);
		mapping.setLayoutGraph(topNode);

		final Set<StateVertex> movedVertice = layoutRootPart.getMovedVertice();

		final List<StateVertex> vertice = new ArrayList<>(stateGraph.getVertice());
		vertice.removeAll(movedVertice);

		for (StateVertex vertex : vertice) {
			ElkNode node = createNode(mapping, vertex, topNode);
			mapping.getGraphMap().put(node, vertex);
		}

		List<DirectedGraph.Edge<StateVertex>> edges = stateGraph.getEdges().stream()
				.filter(e -> !movedVertice.contains(e.getSource()) && !movedVertice.contains(e.getTarget()))
				.collect(Collectors.toList());

		for (DirectedGraph.Edge<StateVertex> edge : edges) {
			ElkEdge kEdge = createEdge(mapping, edge);
			mapping.getGraphMap().put(kEdge, edge);
		}

		return mapping;
	}

	private ElkNode createNode(final LayoutMapping mapping, final StateVertex nodeStateVertex, final ElkNode rootNode) {
		ElkNode childLayoutNode = ElkGraphUtil.createNode(null);
		rootNode.getChildren().add(childLayoutNode);
		/* KShapeLayout nodeLayout = childLayoutNode.getData(KShapeLayout.class);
		nodeLayout.setSize(24, 24);
		((KShapeLayout) nodeLayout).resetModificationFlag();
		nodeLayout.setProperty(CoreOptions.NODE_SIZE_MINIMUM, new KVector(24, 24)); */
		mapping.getGraphMap().put(childLayoutNode, nodeStateVertex);
		return childLayoutNode;
	}

	private ElkEdge createEdge(final LayoutMapping mapping, final DirectedGraph.Edge<StateVertex> edge) {
		ElkEdge layoutEdge = ElkGraphUtil.createEdge(null);
		layoutEdge.getSources().add((ElkNode) mapping.getGraphMap().inverse().get(edge.getSource()));
		layoutEdge.getTargets().add((ElkNode) mapping.getGraphMap().inverse().get(edge.getTarget()));
		mapping.getGraphMap().put(layoutEdge, edge);
		return layoutEdge;
	}

	final private Property<Map<StateVertex, VertexView>> VERTEX2SHAPE_MAP = new Property<Map<StateVertex, VertexView>>(
			"vertex.to.shape.map", new HashMap<>());

	@Override
	public void applyLayout(LayoutMapping layoutMapping, IPropertyHolder propertyHolder) {
		for (Entry<ElkGraphElement, Object> entry : layoutMapping.getGraphMap().entrySet()) {
			/* final KShapeLayout layout = entry.getKey().getData(KShapeLayout.class);
			if (layout != null) {
				final double xPos = layout.getXpos();
				final double yPos = layout.getYpos();
				Map<StateVertex, VertexView> map = layout.getProperty(VERTEX2SHAPE_MAP);
				Optional.ofNullable(map.get(entry.getValue())).ifPresent(v -> {
					v.setTranslateX(xPos);
					v.setTranslateY(yPos);
				});
			} */
		}
	}
}
