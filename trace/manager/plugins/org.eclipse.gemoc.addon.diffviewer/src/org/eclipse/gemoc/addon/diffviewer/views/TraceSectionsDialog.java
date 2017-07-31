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

import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import org.eclipse.gemoc.trace.gemoc.api.ITraceExtractor;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.embed.swt.FXCanvas;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.OverrunStyle;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class TraceSectionsDialog extends TitleAreaDialog {

	private int s1 = -1;
	private int s2 = -1;
	private int e1 = -1;
	private int e2 = -1;

	private ITraceExtractor extractor1;
	private ITraceExtractor extractor2;
	
	private static final Background TRANSPARENT_BACKGROUND = new Background(
			new BackgroundFill(Color.TRANSPARENT, null, null));

	public TraceSectionsDialog(Shell parentShell, ITraceExtractor extractor1, ITraceExtractor extractor2) {
		super(parentShell);
		this.extractor1 = extractor1;
		this.extractor2 = extractor2;
	}

	@Override
	public void create() {
		super.create();
		setTitle("Trace Sections");
		setMessage("Enter starting and ending states of trace sections", IMessageProvider.INFORMATION);
	}
	
	private static final Font FONT = Font.font("Arial", FontWeight.BOLD, 11);
	
	private Shape createCursor() {
		return new Polygon(0, 7.5, 5, 0, -5, 0);
	}
	
	private Pane createTraceWidget(ITraceExtractor extractor, String label, ReadOnlyDoubleProperty width) {
		final Pane pane = new Pane();
		pane.setBackground(TRANSPARENT_BACKGROUND);
		final Rectangle rectangle = new Rectangle(0, 0, 0, 12);
		rectangle.setFill(Color.LIGHTGRAY);
		rectangle.widthProperty().bind(width.subtract(10));
		rectangle.setArcHeight(12);
		rectangle.setArcWidth(12);
		Label text = new Label(label);
		text.setTextOverrun(OverrunStyle.ELLIPSIS);
		text.setAlignment(Pos.CENTER);
		text.setMouseTransparent(true);
		text.setTextFill(Color.WHITE);
		text.setFont(FONT);
		text.setMaxWidth(0);
		text.maxWidthProperty().bind(rectangle.widthProperty());
		StackPane layout = new StackPane();
		layout.getChildren().addAll(rectangle, text);
		pane.getChildren().add(layout);
		layout.setTranslateY(13);
		layout.setTranslateX(5);
		pane.setPrefHeight(25);
		pane.setMinHeight(25);
		pane.setMaxHeight(25);

		final Group group1 = new Group();
		final Label label1 = new Label();
		final Shape arrow1 = createCursor();
		final Group group2 = new Group();
		final Shape arrow2 = createCursor();
		arrow1.setTranslateX(5);
		arrow1.setTranslateY(4);
		arrow2.translateXProperty().bind(rectangle.widthProperty().add(5));
		arrow2.setTranslateY(4);
		pane.getChildren().add(arrow1);
		pane.getChildren().add(arrow2);
		
		return pane;
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite area = (Composite) super.createDialogArea(parent);
		Composite container = new Composite(area, SWT.NONE);
		container.setLayout(new GridLayout(1, false));
		container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		FXCanvas fxCanvas = new FXCanvas(container, SWT.NONE);
		fxCanvas.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		VBox vBox = new VBox();
		Scene scene = new Scene(vBox);
		fxCanvas.setScene(scene);
		vBox.getChildren().add(createTraceWidget(extractor1, "First Trace", scene.widthProperty()));
		vBox.getChildren().add(createTraceWidget(extractor2, "Second Trace", scene.widthProperty()));
		
		return area;
	}

	@Override
	protected boolean isResizable() {
		return true;
	}

	private void saveInput() {
//		s1 = new Integer(txts1.getText());
//		s2 = new Integer(txts2.getText());
//		e1 = new Integer(txte1.getText());
//		e2 = new Integer(txte2.getText());
	}

	@Override
	protected void okPressed() {
		saveInput();
		super.okPressed();
	}

	public int getS1() {
		return s1;
	}

	public int getS2() {
		return s2;
	}

	public int getE1() {
		return e1;
	}

	public int getE2() {
		return e2;
	}
}