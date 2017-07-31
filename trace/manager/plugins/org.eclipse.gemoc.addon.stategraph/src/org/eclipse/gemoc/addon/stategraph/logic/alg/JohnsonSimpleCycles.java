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
package org.eclipse.gemoc.addon.stategraph.logic.alg;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.gemoc.addon.stategraph.logic.DirectedGraph;
import org.eclipse.gemoc.addon.stategraph.logic.DirectedGraph.Edge;

public class JohnsonSimpleCycles<T> {
	// The graph
	private DirectedGraph<T> graph = null;
	private List<T> vertice = null;
	
	// The main state of the algorithm.
	private List<List<T>> cycles = null;
	private Object[] iToV = null;
	private Map<T, Integer> vToI = null;
	private Set<T> blocked = null;
	private Map<T, Set<T>> bSets = null;
	private ArrayDeque<T> stack = null;

	// The state of the embedded Tarjan SCC algorithm.
	private List<Set<T>> SCCs = null;
	private int index = 0;
	private Map<T, Integer> vIndex = null;
	private Map<T, Integer> vLowlink = null;
	private ArrayDeque<T> path = null;
	private Set<T> pathSet = null;

	public JohnsonSimpleCycles() {
	}

	public JohnsonSimpleCycles(DirectedGraph<T> graph) {
		this.graph = graph;
		vertice = graph.getVertice();
	}

	public void setGraph(DirectedGraph<T> graph) {
		this.graph = graph;
		vertice = graph.getVertice();
		
	}
	
	@SuppressWarnings("unchecked")
	public List<List<T>> findSimpleCycles() {
		initState();

		int startIndex = 0;
		int size = vertice.size();
		while (startIndex < size) {
			Object[] minSCCGResult = findMinSCSG(startIndex);
			if (minSCCGResult[0] != null) {
				startIndex = (Integer) minSCCGResult[1];
				DirectedGraph<T> scg = (DirectedGraph<T>) minSCCGResult[0];
				T startV = toV(startIndex);
				for (Edge<T> e : scg.getOutgoingEdges(startV)) {
					T v = e.getTarget();
					blocked.remove(v);
					getBSet(v).clear();
				}
				findCyclesInSCG(startIndex, startIndex, scg);
				startIndex++;
			} else {
				break;
			}
		}

		List<List<T>> result = cycles;
		clearState();
		return result;
	}

	private Object[] findMinSCSG(int startIndex) {
		initMinSCGState();
		Object[] result = new Object[2];

		List<Set<T>> SCCs = findSCCS(startIndex);

		int minIndexFound = Integer.MAX_VALUE;
		Set<T> minSCC = null;
		for (Set<T> scc : SCCs) {
			for (T v : scc) {
				int t = toI(v);
				if (t < minIndexFound) {
					minIndexFound = t;
					minSCC = scc;
				}
			}
		}
		if (minSCC == null) {
			return result;
		}

		DirectedGraph<T> resultGraph = new DirectedGraph<T>();
		for (T v : minSCC) {
			resultGraph.addVertex(v);
		}
		for (T v : minSCC) {
			for (T w : minSCC) {
				if (graph.containsEdge(v, w)) {
					resultGraph.addEdge(v, w);
				}
			}
		}

		result[0] = resultGraph;
		result[1] = minIndexFound;

		clearMinSCCState();
		return result;
	}

	private List<Set<T>> findSCCS(int startIndex) {
		for (T v : vertice) {
			int vI = toI(v);
			if (vI < startIndex) {
				continue;
			}
			if (!vIndex.containsKey(v)) {
				getSCCs(startIndex, vI);
			}
		}
		List<Set<T>> result = SCCs;
		SCCs = null;
		return result;
	}

	private void getSCCs(int startIndex, int vertexIndex) {
		T vertex = toV(vertexIndex);
		vIndex.put(vertex, index);
		vLowlink.put(vertex, index);
		index++;
		path.push(vertex);
		pathSet.add(vertex);

		List<Edge<T>> edges = graph.getOutgoingEdges(vertex);
		for (Edge<T> e : edges) {
			T successor = e.getTarget();
			int successorIndex = toI(successor);
			if (successorIndex < startIndex) {
				continue;
			}
			if (!vIndex.containsKey(successor)) {
				getSCCs(startIndex, successorIndex);
				vLowlink.put(vertex, Math.min(vLowlink.get(vertex), vLowlink.get(successor)));
			} else if (pathSet.contains(successor)) {
				vLowlink.put(vertex, Math.min(vLowlink.get(vertex), vIndex.get(successor)));
			}
		}
		if (vLowlink.get(vertex).equals(vIndex.get(vertex))) {
			Set<T> result = new HashSet<>();
			T temp;
			do {
				temp = path.pop();
				pathSet.remove(temp);
				result.add(temp);
			} while (!vertex.equals(temp));
			if (result.size() == 1) {
				T v = result.iterator().next();
				if (graph.containsEdge(vertex, v)) {
					SCCs.add(result);
				}
			} else {
				SCCs.add(result);
			}
		}
	}

	private boolean findCyclesInSCG(int startIndex, int vertexIndex, DirectedGraph<T> scg) {
		boolean foundCycle = false;
		T vertex = toV(vertexIndex);
		stack.push(vertex);
		blocked.add(vertex);

		for (Edge<T> e : scg.getOutgoingEdges(vertex)) {
			T successor = e.getTarget();
			int successorIndex = toI(successor);
			if (successorIndex == startIndex) {
				List<T> cycle = new ArrayList<>();
				cycle.addAll(stack);
				cycles.add(cycle);
				foundCycle = true;
			} else if (!blocked.contains(successor)) {
				boolean gotCycle = findCyclesInSCG(startIndex, successorIndex, scg);
				foundCycle = foundCycle || gotCycle;
			}
		}
		if (foundCycle) {
			unblock(vertex);
		} else {
			for (Edge<T> ew : scg.getOutgoingEdges(vertex)) {
				T w = ew.getTarget();
				Set<T> bSet = getBSet(w);
				bSet.add(vertex);
			}
		}
		stack.pop();
		return foundCycle;
	}

	private void unblock(T vertex) {
		blocked.remove(vertex);
		Set<T> bSet = getBSet(vertex);
		while (bSet.size() > 0) {
			T w = bSet.iterator().next();
			bSet.remove(w);
			if (blocked.contains(w)) {
				unblock(w);
			}
		}
	}

	@SuppressWarnings("unchecked")
	private void initState() {
		cycles = new LinkedList<>();
		iToV = vertice.toArray();
		vToI = new HashMap<>();
		blocked = new HashSet<>();
		bSets = new HashMap<>();
		stack = new ArrayDeque<>();

		for (int i = 0; i < iToV.length; i++) {
			vToI.put((T) iToV[i], i);
		}
	}

	private void clearState() {
		cycles = null;
		iToV = null;
		vToI = null;
		blocked = null;
		bSets = null;
		stack = null;
	}

	private void initMinSCGState() {
		index = 0;
		SCCs = new ArrayList<>();
		vIndex = new HashMap<>();
		vLowlink = new HashMap<>();
		path = new ArrayDeque<>();
		pathSet = new HashSet<>();
	}

	private void clearMinSCCState() {
		index = 0;
		SCCs = null;
		vIndex = null;
		vLowlink = null;
		path = null;
		pathSet = null;
	}

	private Integer toI(T vertex) {
		return vToI.get(vertex);
	}

	@SuppressWarnings("unchecked")
	private T toV(Integer i) {
		return (T) iToV[i];
	}

	private Set<T> getBSet(T v) {
		Set<T> result = bSets.get(v);
		if (result == null) {
			result = new HashSet<>();
			bSets.put(v, result);
		}
		return result;
	}
}
