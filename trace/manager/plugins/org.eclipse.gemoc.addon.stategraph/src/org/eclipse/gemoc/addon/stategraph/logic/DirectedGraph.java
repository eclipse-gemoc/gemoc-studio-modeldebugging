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
package org.eclipse.gemoc.addon.stategraph.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class DirectedGraph<T> {
	
	final private List<T> vertice = new ArrayList<>();
	final private List<Edge<T>> edges = new ArrayList<>();
	
	private final Map<T,List<Edge<T>>> outgoingEdges = new HashMap<>();
	private final Map<T,List<Edge<T>>> incomingEdges = new HashMap<>();
	
	public Edge<T> addEdge(T source, T target) {
		List<Edge<T>> sEdges = outgoingEdges.get(source);
		boolean add = sEdges == null || sEdges.stream().filter(e -> e.target == target).count() == 0;
		if (add) {
			final Edge<T> edge = new Edge<>(source, target);
			if (sEdges == null) {
				sEdges = new ArrayList<>();
				outgoingEdges.put(source, sEdges);
			}
			List<Edge<T>> tEdges = incomingEdges.get(target);
			if (tEdges == null) {
				tEdges = new ArrayList<>();
				incomingEdges.put(target, tEdges);
			}
			sEdges.add(edge);
			tEdges.add(edge);
			edges.add(edge);
			return edge;
		} else {
			return null;
		}
	}
	
	public Edge<T> addEdge(Edge<T> edge) {
		return addEdge(edge.source, edge.target);
	}
	
	public void removeEdge(T source, T target) {
		Optional.ofNullable(outgoingEdges.get(source))
				.ifPresent(l -> l.stream().filter(e -> e.target == target).findFirst()
						.ifPresent(e -> removeEdge(e)));
	}
	
	public void removeEdge(Edge<T> edge) {
		edges.remove(edge);
		Optional.ofNullable(outgoingEdges.get(edge.source)).ifPresent(l -> l.remove(edge));
		Optional.ofNullable(incomingEdges.get(edge.target)).ifPresent(l -> l.remove(edge));
	}
	
	public T addVertex(T vertex) {
		if (vertice.contains(vertex)) {
			return null;
		}
		vertice.add(vertex);
		return vertex;
	}
	
	public void removeVertex(T vertex) {
		final Set<Edge<T>> toRemove = new HashSet<>();
		Optional.ofNullable(incomingEdges.get(vertex)).ifPresent(l -> l.forEach(e -> toRemove.add(e)));
		Optional.ofNullable(outgoingEdges.get(vertex)).ifPresent(l -> l.forEach(e -> toRemove.add(e)));
		toRemove.forEach(e -> removeEdge(e));
		vertice.remove(vertex);
	}
	
	public List<T> getVertice() {
		return Collections.unmodifiableList(vertice);
	}
	
	public List<Edge<T>> getEdges() {
		return Collections.unmodifiableList(edges);
	}
	
	public boolean containsEdge(T source, T target) {
		List<Edge<T>> sEdges = outgoingEdges.get(source);
		if (sEdges == null) {
			return false;
		}
		return sEdges.stream().filter(e -> e.target == target).count() == 1;
	}
	
	public Edge<T> getEdge(T source, T target) {
		List<Edge<T>> sEdges = outgoingEdges.get(source);
		if (sEdges == null) {
			return null;
		}
		return sEdges.stream().filter(e -> e.target == target).findFirst().orElse(null);
	}
	
	public List<Edge<T>> getIncomingEdges(T vertex) {
		List<Edge<T>> vEdges = incomingEdges.get(vertex);
		if (vEdges == null) {
			return Collections.emptyList();
		}
		return vEdges;
	}
	
	public List<Edge<T>> getOutgoingEdges(T vertex) {
		List<Edge<T>> vEdges = outgoingEdges.get(vertex);
		if (vEdges == null) {
			return Collections.emptyList();
		}
		return vEdges;
	}
	
	public List<T> getPredecessors(T vertex) {
		List<Edge<T>> vEdges = incomingEdges.get(vertex);
		if (vEdges == null) {
			return Collections.emptyList();
		}
		return vEdges.stream().map(e -> e.source).collect(Collectors.toList());
	}
	
	public List<T> getSuccessors(T vertex) {
		List<Edge<T>> vEdges = outgoingEdges.get(vertex);
		if (vEdges == null) {
			return Collections.emptyList();
		}
		return vEdges.stream().map(e -> e.target).collect(Collectors.toList());
	}

	public static class Edge<T> {
		
		final private T source;
		final private T target;
		
		protected Edge(T source, T target) {
			this.source = source;
			this.target = target;
		}

		public T getSource() {
			return source;
		}
		
		public T getTarget() {
			return target;
		}
	}
}
