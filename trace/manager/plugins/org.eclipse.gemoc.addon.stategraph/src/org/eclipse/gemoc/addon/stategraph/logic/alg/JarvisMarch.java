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

import java.util.ArrayList;
import java.util.List;

public class JarvisMarch implements IHullAlgorithm {

	@Override
	public List<double[]> convexHull(List<double[]> points) {
		List<double[]> result = new ArrayList<double[]>();
		double[] extreme = findExtreme(points);
		result.add(new double[]{extreme[0], extreme[1]});
		double[] p = new double[]{extreme[0], extreme[1]};
		double[] q = new double[]{extreme[0], extreme[1]};

		while (true) {
			double[] r = new double[2];
			for (int i = 0; i < points.size(); i++) {
				final double[] tmp = points.get(i);
				if ((tmp[0] == p[0]) && (tmp[1] == p[1])) {
					continue;
				}
				r[0] = tmp[0];
				r[1] = tmp[1];
				int turn = orientation(p, q, r);
				double dist = compare(dist(p, r), dist(p, q));
				if (turn == -1 || turn == 0 && dist == 1) {
					q[0] = r[0];
					q[1] = r[1];
				}
			}
			if ((q[0] == result.get(0)[0]) && (q[1] == result.get(0)[1])) {
				break;
			}
			result.add(new double[]{q[0], q[1]});
			p[0] = q[0];
			p[1] = q[1];
		}
		return result;
	}
	
	private int compare(double a, double b) {
		int c = Double.compare(a, b);
		if (c > 0)
			return 1;
		else if (c < 0)
			return -1;
		return 0;
	}
	
	private double dist(double[] p, double[] q) {
		double dx = q[0] - p[0];
		double dy = q[1] - p[1];
		return ((dx * dx) + (dy * dy));
	}

	private int orientation(double[] p, double[] q, double[] r) {
		return compare(
				((q[0] - p[0]) * (r[1] - p[1])) - ((q[1] - p[1]) * (r[0] - p[0])),
				0);
	}
	
	private double[] findExtreme(List<double[]> points) {
		double[] p = new double[]{points.get(0)[0], points.get(0)[1]};
		for (int i = 1; i < points.size(); i++) {
			double x = points.get(i)[0];
			double y = points.get(i)[1];
			if (compare(x,p[0]) < 0 || compare(x, p[0]) == 0 && compare(y, p[1]) < 0) {
				p[0] = x;
				p[1] = y;
			}
		}
		return p;
	}
}
