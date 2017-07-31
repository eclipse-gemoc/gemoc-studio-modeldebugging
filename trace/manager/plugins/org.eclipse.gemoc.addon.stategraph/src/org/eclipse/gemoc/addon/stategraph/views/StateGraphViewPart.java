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

import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionBars;
import org.eclipse.gemoc.executionframework.ui.views.engine.EngineSelectionDependentViewPart;
import org.eclipse.gemoc.executionframework.ui.views.engine.actions.AbstractEngineAction;
import org.eclipse.gemoc.addon.stategraph.Activator;
import org.eclipse.gemoc.addon.stategraph.layout.StateGraphLayoutCommand;
import org.eclipse.gemoc.addon.stategraph.logic.StateGraph;
import org.eclipse.gemoc.addon.stategraph.logic.StateVertex;
import org.eclipse.gemoc.xdsmlframework.api.core.IExecutionEngine;

import org.eclipse.gemoc.trace.gemoc.api.IMultiDimensionalTraceAddon;
import org.eclipse.gemoc.trace.gemoc.traceaddon.AbstractTraceAddon;
import javafx.embed.swt.FXCanvas;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class StateGraphViewPart extends EngineSelectionDependentViewPart {

	public static final String ID = "org.eclipse.gemoc.addon.multidimensional.timeline.views.timeline.StateGraphViewPart";

	private FXCanvas fxCanvas;

	private StateGraphRenderer renderer;

	private StateGraph stateGraph;

	private Pane root;

	private double mouseX;

	private double mouseY;

	public StateGraph getStateGraph() {
		return stateGraph;
	}

	@Override
	public void engineSelectionChanged(IExecutionEngine engine) {
		if (engine != null) {
			Set<IMultiDimensionalTraceAddon> traceAddons = engine.getAddonsTypedBy(IMultiDimensionalTraceAddon.class);
			if (!traceAddons.isEmpty()) {
				final IMultiDimensionalTraceAddon traceAddon = traceAddons.iterator().next();
				stateGraph = new StateGraph();
				stateGraph.setTraceExtractor(traceAddon.getTraceExtractor());
				stateGraph.setTraceExplorer(traceAddon.getTraceExplorer());
				traceAddon.getTraceNotifier().addListener(stateGraph);
				renderer.setStateGraph(stateGraph);
				stateGraph.update();
			}
		}
	}

	private void setupRoot() {
		stateGraph = new StateGraph();
		renderer = new StateGraphRenderer(stateGraph, new StateGraphLayoutCommand(this));
		root.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
		root.getChildren().add(renderer);
		renderer.minWidthProperty().bind(root.widthProperty());
		renderer.prefWidthProperty().bind(root.widthProperty());
		renderer.maxWidthProperty().bind(root.widthProperty());
		renderer.minHeightProperty().bind(root.heightProperty());
		renderer.prefHeightProperty().bind(root.heightProperty());
		renderer.maxHeightProperty().bind(root.heightProperty());

		root.setOnMousePressed(event -> {
			root.setCursor(Cursor.MOVE);
			mouseX = event.getX();
			mouseY = event.getY();
			event.consume();
		});

		root.setOnMouseReleased(event -> {
			root.setCursor(Cursor.DEFAULT);
		});

		root.setOnMouseDragged(event -> {
			double deltaX = event.getX() - mouseX;
			double deltaY = event.getY() - mouseY;
			renderer.setTranslateX(renderer.getTranslateX() + deltaX);
			renderer.setTranslateY(renderer.getTranslateY() + deltaY);
			mouseX = event.getX();
			mouseY = event.getY();
			event.consume();
		});
	}

	@Override
	public void createPartControl(Composite parent) {
		fxCanvas = new FXCanvas(parent, SWT.NONE);
		root = new Pane();
		final Scene scene = new Scene(root);
		fxCanvas.setScene(scene);
		setupRoot();
		buildMenu(parent.getShell());
	}
	
	private void buildMenu(Shell shell) {
		addActionToToolbar(new AbstractEngineAction(Action.AS_CHECK_BOX) {
			@Override
			protected void init() {
				super.init();
				setText("Toggle Cycle Coloration");
				setToolTipText("Toggle Cycle Coloration");
				ImageDescriptor id = Activator.imageDescriptorFromPlugin(Activator.PLUGIN_ID,
						"icons/cycle_coloration.gif");
				setImageDescriptor(id);
				setEnabled(true);
			}

			@Override
			public void engineSelectionChanged(IExecutionEngine engine) {
			}

			@Override
			public void run() {
				renderer.setCycleColorationEnabled(isChecked());
			}
		});

		addActionToToolbar(new AbstractEngineAction(Action.AS_PUSH_BUTTON) {
			@Override
			protected void init() {
				super.init();
				setText("Refresh Layout");
				setToolTipText("Refresh Layout");
				ImageDescriptor id = Activator.imageDescriptorFromPlugin(Activator.PLUGIN_ID,
						"icons/refresh_layout.gif");
				setImageDescriptor(id);
				setEnabled(true);
			}

			@Override
			public void engineSelectionChanged(IExecutionEngine engine) {
			}

			@Override
			public void run() {
				renderer.forceLayout();
			}
		});

		addActionToToolbar(new AbstractEngineAction(Action.AS_PUSH_BUTTON) {

			private FileDialog fileDialog;

			@Override
			protected void init() {
				super.init();
				setText("Open Trace");
				setToolTipText("Open Trace");
				ImageDescriptor id = Activator.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "icons/jload_obj.gif");
				setImageDescriptor(id);
				setEnabled(true);

				fileDialog = new FileDialog(shell, SWT.OPEN);
				fileDialog.setFilterExtensions(new String[] { "*.trace" });
			}

			@Override
			public void engineSelectionChanged(IExecutionEngine engine) {
			}

			@Override
			public void run() {
				fileDialog.setText("Open Trace");
				String filePath = fileDialog.open();

				if (filePath != null && !filePath.equals("")) {
					Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
					Map<String, Object> m = reg.getExtensionToFactoryMap();
					m.put("trace", new XMIResourceFactoryImpl());

					ResourceSet resSet = new ResourceSetImpl();
					URI filePath1URI = URI.createFileURI(filePath);
					Resource traceResource = resSet.getResource(filePath1URI, true);
					EcoreUtil.resolveAll(traceResource);
					AbstractTraceAddon newTraceAddon = null;
					try {
						IExtensionRegistry extReg = Platform.getExtensionRegistry();
						IExtensionPoint ep = extReg
								.getExtensionPoint("org.gemoc.gemoc_language_workbench.engine_addon");
						IExtension[] extensions = ep.getExtensions();
						for (int i = 0; i < extensions.length && newTraceAddon == null; i++) {
							IExtension ext = extensions[i];
							IConfigurationElement[] confElements = ext.getConfigurationElements();
							for (int j = 0; j < confElements.length; j++) {
								IConfigurationElement confElement = confElements[j];
								String attr = confElement.getAttribute("Class");
								if (attr != null) {
									Object obj = confElement.createExecutableExtension("Class");
									if (obj instanceof AbstractTraceAddon) {
										AbstractTraceAddon obj_cast = (AbstractTraceAddon) obj;
										if (obj_cast.isAddonForTrace(traceResource.getContents().get(0))) {
											newTraceAddon = obj_cast;
											break;
										}
									}
								}
							}
						}
					} catch (CoreException e) {
						e.printStackTrace();
					}

					if (newTraceAddon != null) {
						newTraceAddon.load(traceResource);
						stateGraph.setTraceExtractor(newTraceAddon.getTraceExtractor());
						stateGraph.update();
					}
				}
			}
		});
	}

	private void addActionToToolbar(Action action) {
		IActionBars actionBars = getViewSite().getActionBars();
		IToolBarManager toolBar = actionBars.getToolBarManager();
		toolBar.add(action);
	}
	
	public Set<StateVertex> getMovedVertice() {
		return renderer.getMovedVertice();
	}

	@Override
	public void setFocus() {
		if (fxCanvas != null) {
			fxCanvas.setFocus();
		}
	}
}