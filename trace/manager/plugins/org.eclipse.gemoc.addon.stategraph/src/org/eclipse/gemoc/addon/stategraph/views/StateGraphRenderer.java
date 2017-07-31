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
package org.eclipse.gemoc.addon.stategraph.views;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.gemoc.addon.stategraph.layout.StateGraphLayoutCommand;
import org.eclipse.gemoc.addon.stategraph.logic.DirectedGraph.Edge;
import org.eclipse.gemoc.addon.stategraph.logic.StateGraph;
import org.eclipse.gemoc.addon.stategraph.logic.StateVertex;
import org.eclipse.gemoc.addon.stategraph.logic.alg.JohnsonSimpleCycles;

import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class StateGraphRenderer extends Pane {

	private StateGraph stateGraph;

	final private StateGraphLayoutCommand layoutCommand;

	final private Map<StateVertex, VertexView> nodeToGroup = new HashMap<>();

	final private Map<Edge<StateVertex>, EdgeView> edgeToGroup = new HashMap<>();

	final private Set<StateVertex> movedVertice = new HashSet<>();

	final private List<CycleView> hulls = new ArrayList<>();
	
	final private JohnsonSimpleCycles<StateVertex> cycleFindingAlgorithm;

	public StateGraphRenderer(StateGraph stateGraph, StateGraphLayoutCommand layoutCommand) {
		this.layoutCommand = layoutCommand;
		this.stateGraph = stateGraph;
		this.stateGraph.setUpdateCommand((clear, currentVertex) -> render(clear, currentVertex));
		setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
		cycleFindingAlgorithm = new JohnsonSimpleCycles<>(this.stateGraph);
	}

	public void setStateGraph(final StateGraph stateGraph) {
		this.stateGraph = stateGraph;
		this.stateGraph.setUpdateCommand((clear, currentVertex) -> render(clear, currentVertex));
		cycleFindingAlgorithm.setGraph(this.stateGraph);
	}

	private boolean changed = false;
	private StateVertex currentVertex = null;

	private void render(boolean clear, StateVertex currentVertex) {
		if (currentVertex != null) {
			this.currentVertex = currentVertex;
		}
		Platform.runLater(() -> {

			if (clear) {
				changed = true;
				getChildren().clear();
				nodeToGroup.clear();
				edgeToGroup.clear();
			}

			final Map<Edge<StateVertex>, StateVertex> workList = new HashMap<>();
			final List<VertexView> vertexShapesToAdd = new ArrayList<>();
			final List<EdgeView> edgeShapesToAdd = new ArrayList<>();
			final List<StateVertex> vertice = stateGraph.getVertice().stream()
					.sorted((v1, v2) -> {return v1.getIndex() - v2.getIndex();})
					.collect(Collectors.toList());
			final List<StateVertex> verticeToRemove = new ArrayList<>(nodeToGroup.keySet());
			verticeToRemove.removeAll(vertice);

			if (!verticeToRemove.isEmpty()) {
				changed = true;
			}

			verticeToRemove.forEach(n -> {
				final VertexView vertexShape = nodeToGroup.get(n);
				nodeToGroup.remove(n);
				if (vertexShape != null) {
					getChildren().remove(vertexShape);
				}
			});

			for (int i = 0; i < vertice.size(); i++) {
				final StateVertex v = vertice.get(i);
				VertexView vertexShape = nodeToGroup.get(v);
				if (vertexShape == null) {
					changed = true;
					vertexShape = new VertexView(i, v);
					nodeToGroup.put(v, vertexShape);
					vertexShapesToAdd.add(vertexShape);
				}
				vertexShape.setCurrentState(currentVertex == v);
				vertexShape.setStateIndex(i);

				for (Edge<StateVertex> e : stateGraph.getOutgoingEdges(v)) {
					workList.put(e, v);
				}
			}

			final List<Edge<StateVertex>> edgesToRemove = new ArrayList<>(edgeToGroup.keySet());

			for (StateVertex v : vertice) {
				for (Edge<StateVertex> e : stateGraph.getIncomingEdges(v)) {
					final StateVertex w = workList.get(e);
					if (w != null && v != w) {
						final Edge<StateVertex> reverseEdge = stateGraph.getEdge(v, w);
						if (reverseEdge != null) {
							EdgeView edgeGroup = edgeToGroup.get(e);
							if (edgeGroup == null || edgeGroup instanceof StraightEdgeView) {
								changed = true;
								if (edgeGroup != null) {
									getChildren().remove(edgeGroup);
								}
								final VertexView source = nodeToGroup.get(w);
								final VertexView target = nodeToGroup.get(v);
								edgeGroup = new CurvedEdgeView(source.translateXProperty(), source.translateYProperty(),
										target.translateXProperty(), target.translateYProperty());
								edgeToGroup.put(e, edgeGroup);
								edgeShapesToAdd.add(edgeGroup);
							}
							edgesToRemove.remove(e);
							EdgeView reverseEdgeGroup = edgeToGroup.get(reverseEdge);
							if (reverseEdgeGroup == null || reverseEdgeGroup instanceof StraightEdgeView) {
								changed = true;
								if (reverseEdgeGroup != null) {
									getChildren().remove(reverseEdgeGroup);
								}
								final VertexView source = nodeToGroup.get(v);
								final VertexView target = nodeToGroup.get(w);
								reverseEdgeGroup = new CurvedEdgeView(source.translateXProperty(), source.translateYProperty(),
										target.translateXProperty(), target.translateYProperty());
								edgeToGroup.put(reverseEdge, reverseEdgeGroup);
								edgeShapesToAdd.add(reverseEdgeGroup);
							}
							edgesToRemove.remove(reverseEdge);
						} else {
							EdgeView edgeGroup = edgeToGroup.get(e);
							if (edgeGroup == null || edgeGroup instanceof CurvedEdgeView) {
								changed = true;
								if (edgeGroup != null) {
									getChildren().remove(edgeGroup);
								}
								final VertexView source = nodeToGroup.get(w);
								final VertexView target = nodeToGroup.get(v);
								edgeGroup = new StraightEdgeView(source.translateXProperty(), source.translateYProperty(),
										target.translateXProperty(), target.translateYProperty());
								edgeToGroup.put(e, edgeGroup);
								edgeShapesToAdd.add(edgeGroup);
							}
							edgesToRemove.remove(e);
						}
					}
				}
			}

			if (!edgesToRemove.isEmpty()) {
				changed = true;
			}

			edgesToRemove.forEach(e -> {
				final Group edgeGroup = edgeToGroup.get(e);
				edgeToGroup.remove(e);
				if (edgeGroup != null) {
					getChildren().remove(edgeGroup);
				}
			});

			getChildren().addAll(0, edgeShapesToAdd);
			getChildren().addAll(vertexShapesToAdd);

			if (changed) {
				layoutCommand.applyLayout(nodeToGroup, movedVertice);
			}

			if (!isCycleColorationEnabled) {
				getChildren().removeAll(hulls);
				hulls.clear();
			} else if (changed) {
				getChildren().removeAll(hulls);
				hulls.clear();
				final List<List<StateVertex>> cycles = cycleFindingAlgorithm.findSimpleCycles();
				if (!cycles.isEmpty()) {
					double hueInterval = 360. / (double) cycles.size();
					for (int i = 0; i < cycles.size(); i++) {
						List<Node> nodes = cycles.get(i).stream().map(v -> (Node) nodeToGroup.get(v))
								.collect(Collectors.toList());
						CycleView cycleView = new CycleView(nodes, i, hueInterval);
						getChildren().add(0, cycleView);
						hulls.add(cycleView);
					}
				}
			}

			changed = false;
		});
	}

	private boolean isCycleColorationEnabled = false;

	public void setCycleColorationEnabled(boolean checked) {
		if (isCycleColorationEnabled != checked) {
			isCycleColorationEnabled = checked;
			changed = true;
			render(false, currentVertex);
		}
	}

	public void forceLayout() {
		movedVertice.clear();
		Platform.runLater(() -> layoutCommand.applyLayout(nodeToGroup, movedVertice));
	}

	public Set<StateVertex> getMovedVertice() {
		return movedVertice;
	}
}
