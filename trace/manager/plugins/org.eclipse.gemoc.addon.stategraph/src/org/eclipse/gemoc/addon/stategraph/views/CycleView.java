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
import java.util.List;

import org.eclipse.gemoc.addon.stategraph.logic.alg.IHullAlgorithm;
import org.eclipse.gemoc.addon.stategraph.logic.alg.JarvisMarch;

import javafx.beans.value.ChangeListener;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.StrokeLineJoin;
import javafx.scene.shape.StrokeType;

public class CycleView extends Polygon {

	private final static IHullAlgorithm HULL_ALGORITHM = new JarvisMarch();
	
	public CycleView(final List<Node> nodes, final int cycleIndex, final double hueInterval) {
		Paint p = Color.hsb(cycleIndex * hueInterval, 0.75, 0.70, 0.25);
		setFill(p);
		setStroke(p);
		setStrokeWidth(20);
		setStrokeLineJoin(StrokeLineJoin.ROUND);
		setStrokeType(StrokeType.OUTSIDE);
		updateHull(nodes, HULL_ALGORITHM);
		final ChangeListener<Number> listener = (_0, _1, _2) -> {
			updateHull(nodes, HULL_ALGORITHM);
		};
		for (Node n : nodes) {
			n.translateXProperty().addListener(listener);
			n.translateYProperty().addListener(listener);
		}
	}
	
	private void updateHull(List<Node> nodes, IHullAlgorithm hullAlgo) {
		List<double[]> points = new ArrayList<>();

		for (Node n : nodes) {
			points.add(new double[] { n.getTranslateX(), n.getTranslateY() });
		}

		List<Double> coordinates = new ArrayList<>();
		if (points.size() <= 3) {
			for (double[] point : points) {
				coordinates.add(point[0]);
				coordinates.add(point[1]);
			}
		} else {
			for (double[] point : hullAlgo.convexHull(points)) {
				coordinates.add(point[0]);
				coordinates.add(point[1]);
			}
		}

		getPoints().clear();
		getPoints().addAll(coordinates);
	}
}
