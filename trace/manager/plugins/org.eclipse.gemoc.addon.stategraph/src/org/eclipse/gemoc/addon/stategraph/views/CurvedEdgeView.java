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
import javafx.scene.shape.Polygon;
import javafx.scene.shape.QuadCurve;

public class CurvedEdgeView extends EdgeView {

	public CurvedEdgeView(final DoubleProperty sX, final DoubleProperty sY, final DoubleProperty eX,
			final DoubleProperty eY) {
		super(sX, sY, eX, eY);
		
		final DoubleBinding cX = new DoubleBinding() {

			{
				super.bind(sX, eX, a);
			}

			@Override
			protected double computeValue() {
				final double tX = (sX.get() + eX.get()) / 2;
				return tX + 20 * Math.cos(a.get() - Math.PI / 2);
			}
		};

		final DoubleBinding cY = new DoubleBinding() {

			{
				super.bind(sY, eY, a);
			}

			@Override
			protected double computeValue() {
				final double tY = (sY.get() + eY.get()) / 2;
				return tY + 20 * Math.sin(a.get() - Math.PI / 2);
			}
		};

		final DoubleBinding aX = new DoubleBinding() {

			{
				super.bind(sX, eX, cX);
			}

			@Override
			protected double computeValue() {
				return .25 * sX.get() + .5 * cX.get() + 0.25 * eX.get();
			}
		};

		final DoubleBinding aY = new DoubleBinding() {

			{
				super.bind(sY, eY, cY);
			}

			@Override
			protected double computeValue() {
				return .25 * sY.get() + .5 * cY.get() + 0.25 * eY.get();
			}
		};

		QuadCurve quadCurve = new QuadCurve();
		quadCurve.setStrokeWidth(1.5);
		quadCurve.setStroke(Color.BLUE);
		quadCurve.setFill(Color.TRANSPARENT);
		quadCurve.startXProperty().bind(sX);
		quadCurve.startYProperty().bind(sY);
		quadCurve.endXProperty().bind(eX);
		quadCurve.endYProperty().bind(eY);
		quadCurve.controlXProperty().bind(cX);
		quadCurve.controlYProperty().bind(cY);

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
		a.addListener(listener);

		getChildren().addAll(quadCurve, arrow);
	}
}
