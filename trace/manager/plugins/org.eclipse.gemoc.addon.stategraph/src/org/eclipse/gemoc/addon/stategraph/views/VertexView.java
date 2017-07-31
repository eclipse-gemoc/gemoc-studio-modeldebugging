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

import org.eclipse.gemoc.addon.stategraph.logic.StateVertex;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.OverrunStyle;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class VertexView extends Group {

	private double mouseX;

	private double mouseY;

	private boolean currentState = false;

	private int stateIndex;

	private static final double RADIUS = 12;

	private static final DropShadow GLOW = new DropShadow(2.5, Color.BLACK);

	private static final Font STATE_INDEX_FONT = Font.font("Arial", FontWeight.BOLD, 9);

	private final Circle circle;

	private final Label label;

	public VertexView(final int stateIndex, StateVertex vertex) {
		circle = new Circle(RADIUS);
		circle.setFill(Color.SLATEBLUE);

		this.stateIndex = stateIndex;

		final String s = vertex.getTooltip();
		final Tooltip t = new Tooltip(s);
		Tooltip.install(this, t);
		vertex.setOnTooltipUpdateCommand(text -> Platform.runLater(() -> t.setText(text)));

		label = new Label(computeStateLabel(this.stateIndex));
		label.setTextOverrun(OverrunStyle.ELLIPSIS);
		label.setAlignment(Pos.CENTER);
		label.setMouseTransparent(true);
		label.setTextFill(Color.WHITE);
		label.setFont(STATE_INDEX_FONT);
		label.setMaxWidth(RADIUS * 2);
		StackPane layout = new StackPane();
		layout.getChildren().addAll(circle, label);
		layout.setTranslateX(-RADIUS);
		layout.setTranslateY(-RADIUS);

		getChildren().add(layout);

		setOnMousePressed(event -> {
			circle.setCursor(Cursor.MOVE);
			mouseX = event.getX();
			mouseY = event.getY();
			event.consume();
		});

		setOnMouseReleased(event -> {
			setCursor(Cursor.DEFAULT);
		});

		setOnMouseDragged(event -> {
			double deltaX = event.getX() - mouseX;
			double deltaY = event.getY() - mouseY;
			setTranslateX(getTranslateX() + deltaX);
			setTranslateY(getTranslateY() + deltaY);
			event.consume();
		});

		setOnMouseEntered(event -> circle.setEffect(GLOW));
		setOnMouseExited(event -> circle.setEffect(null));
	}

	private String computeStateLabel(int stateNumber) {
		if (stateNumber > 999) {
			return (stateNumber / 1000) + "k" + ((stateNumber % 1000) / 10);
		} else {
			return "" + stateNumber;
		}
	}

	public void setCurrentState(final boolean currentState) {
		if (this.currentState != currentState) {
			this.currentState = currentState;
			if (this.currentState) {
				circle.setFill(Color.CORAL);
			} else {
				circle.setFill(Color.SLATEBLUE);
			}
		}
	}

	public void setStateIndex(final int stateIndex) {
		if (this.stateIndex != stateIndex) {
			this.stateIndex = stateIndex;
			label.setText(computeStateLabel(this.stateIndex));
		}
	}
}
