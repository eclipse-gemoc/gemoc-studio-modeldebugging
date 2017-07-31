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
package org.eclipse.gemoc.addon.diffviewer.views;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gemoc.addon.diffviewer.logic.Diff.DiffKind;

import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class ValueView extends HBox {

	private static final int HEIGHT = 8;
	private static final int H_MARGIN = 8;
	private static final int WIDTH = 24;

	private Shape getHatching(int length, int offset) {
		final Path p = new Path();
		for (int i = 0; i < (length / 2.5) + 1; i++) {
			final double x1 = (i - 1) * 2.5 + offset;
			final double y1 = 8;
			final double x2 = (i + 1) * 2.5 + offset;
			final double y2 = 0;
			final MoveTo move = new MoveTo(x1, y1);
			final LineTo line = new LineTo(x2, y2);
			p.getElements().addAll(move, line);
			p.setStrokeWidth(1);
		}
		return p;
	}

	public void setSegments(DiffKind... segmentParts) {
		if (segmentParts.length > 0) {
			int totalLength = (WIDTH + H_MARGIN * 2) * segmentParts.length - H_MARGIN * 2;
			final List<Shape> rectangles = new ArrayList<>();
			int j = 0;
			final List<Integer> segmentLengths = new ArrayList<>();
			final List<DiffKind> segmentTypes = new ArrayList<>();
			segmentLengths.add(1);
			segmentTypes.add(segmentParts[0]);
			for (int i = 1; i < segmentParts.length; i++) {
				if (segmentParts[i] != segmentParts[i - 1]) {
					j++;
					segmentLengths.add(1);
					segmentTypes.add(segmentParts[i]);
				} else {
					segmentLengths.add(1 + segmentLengths.remove(j));
				}
			}

			if (segmentLengths.size() > 1) {
				final int segmentCount = segmentLengths.size();
				for (int i = 1; i < segmentCount - 1; i++) {
					final Shape s;
					final int length = (WIDTH + H_MARGIN * 2) * segmentLengths.get(i);
					switch (segmentTypes.get(i)) {
					case EQ:
						s = new Rectangle(length, HEIGHT, Color.BLUE);
						break;
					case SUBST:
						s = new Rectangle(length, HEIGHT, Color.TOMATO);
						break;
					case DEL:
					case IN:
						final Rectangle r = new Rectangle(length, HEIGHT);
						s = Shape.subtract(r, getHatching(length, 0));
						s.setFill(Color.BROWN);
						break;
						default:
							s = null;
					}
					rectangles.add(s);
				}

				final Shape firstR;
				final Color firstC;
				final int l1 = (WIDTH + H_MARGIN * 2) * segmentLengths.get(0) - H_MARGIN;
				switch (segmentTypes.get(0)) {
				case EQ:
					firstR = new Rectangle(l1, HEIGHT);
					firstC = Color.BLUE;
					break;
				case SUBST:
					firstR = new Rectangle(l1, HEIGHT);
					firstC = Color.TOMATO;
					break;
				case DEL:
				case IN:
					final Rectangle r = new Rectangle(l1, HEIGHT);
					firstR = Shape.subtract(r, getHatching(l1, 0));
					firstC = Color.BROWN;
					break;
					default:
						firstR = null;
						firstC = null;
				}

				final Shape lastR;
				final Color lastC;
				final int l2 = (WIDTH + H_MARGIN * 2) * segmentLengths.get(segmentCount - 1) - H_MARGIN;
				switch (segmentTypes.get(segmentCount - 1)) {
				case EQ:
					lastR = new Rectangle(totalLength - l2, 0, l2, HEIGHT);
					lastC = Color.BLUE;
					break;
				case SUBST:
					lastR = new Rectangle(totalLength - l2, 0, l2, HEIGHT);
					lastC = Color.TOMATO;
					break;
				case DEL:
				case IN:
					final Rectangle r = new Rectangle(totalLength - l2, 0, l2, HEIGHT);
					lastR = Shape.subtract(r, getHatching(l2, totalLength - l2));
					lastC = Color.BROWN;
					break;
				default:
					lastR = null;
					lastC = null;
				}

				final Rectangle r = new Rectangle(totalLength, HEIGHT);
				r.setArcHeight(HEIGHT);
				r.setArcWidth(12);
				final Shape s1 = Shape.intersect(firstR, r);
				final Shape s2 = Shape.intersect(lastR, r);
				s1.setFill(firstC);
				s2.setFill(lastC);
				rectangles.add(0, s1);
				rectangles.add(s2);
			} else {
				final int length = (WIDTH + H_MARGIN * 2) * segmentLengths.get(0) - H_MARGIN * 2;
				final Rectangle r;
				switch (segmentTypes.get(0)) {
				case EQ:
					r = new Rectangle(length, HEIGHT, Color.BLUE);
					r.setArcHeight(HEIGHT);
					r.setArcWidth(12);
					rectangles.add(r);
					break;
				case SUBST:
					r = new Rectangle(length, HEIGHT, Color.TOMATO);
					r.setArcHeight(HEIGHT);
					r.setArcWidth(12);
					rectangles.add(r);
					break;
				case DEL:
				case IN:
					r = new Rectangle(length, HEIGHT);
					r.setArcHeight(HEIGHT);
					r.setArcWidth(12);
					final Shape s = Shape.subtract(r, getHatching(length, totalLength - length));
					s.setFill(Color.BROWN);
					rectangles.add(s);
					break;
				}
				
			}
			getChildren().addAll(rectangles);
		}
	}

	public ValueView(String description, DiffKind... segmentParts) {
		setSegments(segmentParts);
		final Tooltip tooltip = new Tooltip(description);
		Tooltip.install(this, tooltip);
	}
}
