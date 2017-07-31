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

import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;

public class StraightEdgeView extends EdgeView {

	private final DoubleBinding aX;
	
	private final DoubleBinding aY;
	
	public StraightEdgeView(DoubleProperty sX, DoubleProperty sY, DoubleProperty eX, DoubleProperty eY) {
		super(sX, sY, eX, eY);
		
		aX = new DoubleBinding() {

			{
				super.bind(sX, eX);
			}

			@Override
			protected double computeValue() {
				return (sX.get() + eX.get()) * .5;
			}
		};

		aY = new DoubleBinding() {

			{
				super.bind(sY, eY);
			}

			@Override
			protected double computeValue() {
				return (sY.get() + eY.get()) * .5;
			}
		};
		
		Line line = new Line();
		line.setStrokeWidth(1.5);
		line.setStroke(Color.BLUE);
		line.startXProperty().bind(sX);
		line.startYProperty().bind(sY);
		line.endXProperty().bind(eX);
		line.endYProperty().bind(eY);
		
		Polygon arrow = new Polygon();
		arrow.setFill(Color.BLUE);
		final ChangeListener<Number> listener = (_0, _1, _2) -> {
			final double angle = a.get() + Math.PI;
			final double x = aX.get();
			final double y = aY.get();
			final double x0 = x + 5 * Math.cos(angle);
			final double x1 = x + 5 * Math.cos(angle + 2 * Math.PI / 3);
			final double x2 = x + 5 * Math.cos(angle - 2 * Math.PI / 3);
			final double y0 = y + 5 * Math.sin(angle);
			final double y1 = y + 5 * Math.sin(angle + 2 * Math.PI / 3);
			final double y2 = y + 5 * Math.sin(angle - 2 * Math.PI / 3);
			arrow.getPoints().clear();
			arrow.getPoints().addAll(x0, y0, x1, y1, x2, y2);
		};

		aX.addListener(listener);
		aY.addListener(listener);
		
		getChildren().addAll(line, arrow);
	}
}
