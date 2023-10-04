/*******************************************************************************
 * Copyright (c) 2017 INRIA and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     INRIA - initial API and implementation
 *     I3S Laboratory - API update and bug fix
 *******************************************************************************/
package org.eclipse.gemoc.executionframework.engine.ui.concurrency.views.step;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gemoc.commons.eclipse.ui.TreeViewerHelper;
import org.eclipse.gemoc.executionframework.engine.concurrency.AbstractConcurrentExecutionEngine;
import org.eclipse.gemoc.executionframework.engine.core.GemocRunningEnginesRegistry;
import org.eclipse.gemoc.executionframework.engine.ui.Activator;
import org.eclipse.gemoc.executionframework.engine.ui.concurrency.SharedIcons;
import org.eclipse.gemoc.executionframework.engine.ui.concurrency.deciders.actions.PauseResumeEngineDeciderAction;
import org.eclipse.gemoc.executionframework.engine.ui.concurrency.deciders.actions.SwitchDeciderAction;
import org.eclipse.gemoc.executionframework.ui.IMSEPresenter;
import org.eclipse.gemoc.executionframework.ui.utils.ViewUtils;
import org.eclipse.gemoc.executionframework.ui.views.engine.EngineSelectionDependentViewPart;
import org.eclipse.gemoc.executionframework.ui.views.engine.actions.StopEngineAction;
import org.eclipse.gemoc.trace.commons.model.generictrace.GenericParallelStep;
import org.eclipse.gemoc.trace.commons.model.helper.StepHelper;
import org.eclipse.gemoc.trace.commons.model.trace.MSE;
import org.eclipse.gemoc.trace.commons.model.trace.MSEOccurrence;
import org.eclipse.gemoc.trace.commons.model.trace.Step;
import org.eclipse.gemoc.xdsmlframework.api.core.EngineStatus.RunStatus;
import org.eclipse.gemoc.xdsmlframework.api.core.ExecutionMode;
import org.eclipse.gemoc.xdsmlframework.api.core.IExecutionEngine;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.GroupMarker;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuListener2;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IWorkbenchActionConstants;

public class LogicalStepsView extends EngineSelectionDependentViewPart implements IMSEPresenter {

	public static final String ID = "org.eclipse.gemoc.executionframework.engine.io.views.steps.LogicalStepsView";

	private Color _representedEventColor;

	private TreeViewer _viewer;

	private ColumnLabelProvider _column1LabelProvider;

	private ColumnLabelProvider _column2LabelProvider;

	private List<URI> _eventsToPresent = new ArrayList<URI>();

	public LogicalStepsView() {
		super();
	}

	private LogicalStepsViewContentProvider _contentProvider;

	private MenuManager _menuManager;

	@Override
	public void createPartControl(Composite parent) {
		_representedEventColor = new Color(parent.getDisplay(), 255, 235, 174);
		// The main parent will be made of a single column
		GridLayout layout = new GridLayout();
		parent.setLayout(layout);
		parent.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		
		createTreeViewer(parent);
		createMenuManager();
		buildActionToolbar();
		org.eclipse.gemoc.executionframework.ui.Activator.getDefault().getEventPresenters().add(this);
	}

	public void refresh() {
		runInDisplayThread(() -> {
			_viewer.refresh();
			TreeViewerHelper.resizeColumns(_viewer);
			_viewer.expandAll();
		}); 
	}

	private void createTreeViewer(Composite parent) {
		_viewer = new TreeViewer(parent, SWT.FULL_SELECTION | SWT.SINGLE);
		_viewer.setUseHashlookup(true);
		_contentProvider = new LogicalStepsViewContentProvider();
		_viewer.setContentProvider(_contentProvider);
		Font mono = JFaceResources.getFont(JFaceResources.TEXT_FONT);
		_viewer.getTree().setFont(mono);
		createColumns();
		// The table will take all the horizontal and vertical excess space
		GridData grid = new GridData(SWT.FILL, SWT.FILL, true, true);
		_viewer.getControl().setLayoutData(grid);
		_viewer.getTree().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		
		
		// invoke the columns pack, in order to let a column fit to it�s contents width
		Tree tree = (Tree) _viewer.getControl();
		tree.setHeaderVisible(true);
		Listener listener = new Listener() {
		   @Override
		   public void handleEvent(Event event) {
		      Display.getDefault().asyncExec(new Runnable() {
		         @Override
		         public void run() {
		        	 TreeViewerHelper.resizeColumns(_viewer); 
		         }
		      });
		   }
		};

		tree.addListener(SWT.Expand, listener);
		// adjust the table when expanding
	}

	private void createColumns() {
		TreeColumn column1 = new TreeColumn(_viewer.getTree(), SWT.LEFT);
		column1.setText("Logical Steps/MSEs");
		TreeViewerColumn viewerColumn1 = new TreeViewerColumn(_viewer, column1);
		_column1LabelProvider = new ColumnLabelProvider() {

			@Override
			public String getText(Object element) {
				if (element instanceof Step) {
					Step<?> ls = (Step<?>) element;
					return StepHelper.getStepName(ls);
				} else if (element instanceof MSEOccurrence) {
					MSEOccurrence event = (MSEOccurrence) element;
					if (event.getMse() != null)
						return event.getMse().getName();
					else
						return "No EOperation";
				}
				return super.getText(element);
			}

			@Override
			public Image getImage(Object element) {
				if (element instanceof Step) {
					Step<?> ls = (Step<?>) element;
					if (_currentEngine != null && ls == _currentEngine.getSelectedLogicalStep()) {
						return SharedIcons.getSharedImage(SharedIcons.LOGICALSTEP_RUNNING_ICON);
					} else {
						return SharedIcons.getSharedImage(SharedIcons.LOGICALSTEP_ICON);
					}
				} else if (element instanceof MSEOccurrence) {
					return SharedIcons.getSharedImage(SharedIcons.VISIBLE_EVENT_ICON);
				}
				return null;
			}

			@Override
			public Color getBackground(Object element) {
				final Color res;

				if (element instanceof MSEOccurrence) {
					MSE mse = ((MSEOccurrence) element).getMse();
					if (mse != null && _eventsToPresent.contains(EcoreUtil.getURI(mse)))
						res = _representedEventColor;
					else
						res = super.getBackground(element);

				} else {
					res = super.getBackground(element);
				}

				return res;
			}

		};
		viewerColumn1.setLabelProvider(_column1LabelProvider);

		TreeColumn column2 = new TreeColumn(_viewer.getTree(), SWT.LEFT);
		column2.setText("DSA");
		TreeViewerColumn viewerColumn2 = new TreeViewerColumn(_viewer, column2);
		_column2LabelProvider = new ColumnLabelProvider() {

			@Override
			public String getText(Object element) {
				if (element instanceof MSEOccurrence) {
					MSE mse = ((MSEOccurrence) element).getMse();
					if (mse != null)
						return "   " + ViewUtils.eventToString(mse);
					else
						return "    (no MSE)";

				}
				return "";
			}

			@Override
			public Color getBackground(Object element) {
				final Color res;

				if (element instanceof MSEOccurrence) {
					MSE mse = ((MSEOccurrence) element).getMse();
					if (mse != null && _eventsToPresent.contains(EcoreUtil.getURI(mse)))
						res = _representedEventColor;
					else
						res = super.getBackground(element);

				} else {
					res = super.getBackground(element);
				}

				return res;
			}

		};
		viewerColumn2.setLabelProvider(_column2LabelProvider);
	}

	private void createMenuManager() {
		MenuManager menuManager = new MenuManager();
		_menuManager = menuManager;
		_menuManager.setRemoveAllWhenShown(true);
		_menuManager.addMenuListener(new IMenuListener() {
			@Override
			public void menuAboutToShow(IMenuManager mgr) {
				fillContextMenu(mgr);
			}
		});
		Menu menu = _menuManager.createContextMenu(_viewer.getControl());
		_viewer.getControl().setMenu(menu);
		getSite().registerContextMenu(_menuManager, _viewer);
		// make the selection available
		getSite().setSelectionProvider(_viewer);
	}

	private void fillContextMenu(IMenuManager mgr) {
		mgr.add(new GroupMarker(IWorkbenchActionConstants.MB_ADDITIONS));
		// mgr.add(new SwitchDeciderAction());
	}

	private void buildActionToolbar() {
		addActionToToolbar(new PauseResumeEngineDeciderAction());
		addActionToToolbar(new StopEngineAction());
		addSeparatorToToolbar();
		addActionToToolbar(new SwitchDeciderAction());
	}

	private void addSeparatorToToolbar() {
		IActionBars actionBars = getViewSite().getActionBars();
		IToolBarManager toolBar = actionBars.getToolBarManager();
		toolBar.add(new Separator());
	}

	private void addActionToToolbar(Action action) {
		IActionBars actionBars = getViewSite().getActionBars();
		IToolBarManager toolBar = actionBars.getToolBarManager();
		toolBar.add(action);
	}

	@Override
	public void setFocus() {
		_viewer.getControl().setFocus();
	}

	private AbstractConcurrentExecutionEngine _currentEngine;

	@Override
	public void engineSelectionChanged(IExecutionEngine<?> engine) {
		if (engine != null && engine instanceof AbstractConcurrentExecutionEngine
				&& engine.getExecutionContext().getExecutionMode().equals(ExecutionMode.Animation)) {
			_currentEngine = (AbstractConcurrentExecutionEngine) engine;
			_viewer.setInput(_currentEngine);
			if (_currentEngine != null && !_currentEngine.getRunningStatus().equals(RunStatus.Stopped)) {
				TreeViewerHelper.resizeColumns(_viewer);
				_viewer.expandAll();
			} else {
				_viewer.setInput(null);
			}

			// display engine full name in tooltip
			GemocRunningEnginesRegistry registry = org.eclipse.gemoc.executionframework.engine.Activator
					.getDefault().gemocRunningEngineRegistry;
			for (Entry<String, IExecutionEngine<?>> e : registry.getRunningEngines().entrySet()) {
				if (e.getValue() == engine) {
					setTitleToolTip(e.getKey()); // the key is the full name for
													// this engine
					break;
				}
			}

		}
	}

	@Override
	public void dispose() {
		org.eclipse.gemoc.executionframework.ui.Activator.getDefault().getEventPresenters().remove(this);
		super.dispose();
		_column1LabelProvider.dispose();
		_column2LabelProvider.dispose();
		_menuManager.dispose();
		_contentProvider.dispose();
		_representedEventColor.dispose();
		_eventsToPresent.clear();
	}

	private GenericParallelStep _lastSelectedLogicalStep;

	private void runInDisplayThread(Runnable r) {
		try {
			Display.getDefault().syncExec(r);
		} catch (Exception e) {
			Activator.error(e.getMessage(), e);
		}
	}

	public GenericParallelStep getSelectedLogicalStep() {
		Runnable r = new Runnable() {
			@Override
			public void run() {
				TreeSelection selection = (TreeSelection) _viewer.getSelection();
				if (selection.getPaths().length > 0) {
					TreePath path = selection.getPaths()[0];
					_lastSelectedLogicalStep = null;
					if (path.getLastSegment() instanceof Step) {
						_lastSelectedLogicalStep = (GenericParallelStep) path.getLastSegment();
					} else if (path.getLastSegment() instanceof MSEOccurrence) {
						_lastSelectedLogicalStep = (GenericParallelStep) path.getFirstSegment();
					}
				}
			}
		};
		runInDisplayThread(r);

		return _lastSelectedLogicalStep;
	}

	public void addMenuListener(IMenuListener2 menuListener) {
		_menuManager.addMenuListener(menuListener);
	}

	public void addDoubleClickListener(IDoubleClickListener doubleClickListener) {
		_viewer.addDoubleClickListener(doubleClickListener);
	}

	public void addSelectionChangedListener(ISelectionChangedListener listener) {
		_viewer.addSelectionChangedListener(listener);
	}

	public void removeSelectionChangedListener(ISelectionChangedListener listener) {
		_viewer.removeSelectionChangedListener(listener);
	}

	public void removeMenuListener(IMenuListener2 menuListener) {
		_menuManager.removeMenuListener(menuListener);
	}

	public void removeDoubleClickListener(IDoubleClickListener doubleClickListener) {
		_viewer.removeDoubleClickListener(doubleClickListener);
	}

	public TreeViewer getTreeViewer() {
		return _viewer;
	}

	@Override
	public void present(List<URI> events) {
		_eventsToPresent = events;
		if (_currentEngine != null) {
			ResourceSet rs = _currentEngine.getExecutionContext().getResourceModel().getResourceSet();
			for (URI uri : _eventsToPresent) {
				final EObject event = rs.getEObject(uri, false);
				if (event != null) {
					_viewer.refresh(event);
				}
			}
		}
	}

}
