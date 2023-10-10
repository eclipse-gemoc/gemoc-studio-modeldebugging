/*******************************************************************************
 * Copyright (c) 2017 Obeo and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.gemoc.addon.eventscheduling.timeline.view;

import java.util.Set;
import java.util.WeakHashMap;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.gemoc.commons.eclipse.ui.Activator;
import org.eclipse.gemoc.executionframework.engine.concurrency.AbstractConcurrentExecutionEngine;
import org.eclipse.gemoc.executionframework.engine.ui.concurrency.deciders.AbstractUserDecider;
import org.eclipse.gemoc.executionframework.reflectivetrace.gemoc_execution_trace.Branch;
import org.eclipse.gemoc.executionframework.reflectivetrace.gemoc_execution_trace.Choice;
import org.eclipse.gemoc.executionframework.ui.views.engine.IEngineSelectionListener;
import org.eclipse.gemoc.timeline.editpart.PossibleStepEditPart;
import org.eclipse.gemoc.timeline.editpart.TimelineEditPartFactory;
import org.eclipse.gemoc.timeline.view.AbstractTimelineView;
import org.eclipse.gemoc.timeline.view.ITimelineProvider;
import org.eclipse.gemoc.trace.commons.model.trace.ParallelStep;
import org.eclipse.gemoc.trace.commons.model.trace.Step;
import org.eclipse.gemoc.trace.gemoc.concurrent.AbstractEventSchedulingModelExecutionTracingAddon;
import org.eclipse.gemoc.trace.gemoc.concurrent.ModelExecutionTracingException;
import org.eclipse.gemoc.xdsmlframework.api.core.EngineStatus.RunStatus;
import org.eclipse.gemoc.xdsmlframework.api.core.ExecutionMode;
import org.eclipse.gemoc.xdsmlframework.api.core.IDisposable;
import org.eclipse.gemoc.xdsmlframework.api.core.IExecutionEngine;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

public class EventSchedulingTimeLineView extends AbstractTimelineView implements IEngineSelectionListener {

	public static final String ID = "org.eclipse.gemoc.addon.eventscheduling.timeline.EventSchedulingTimeLineView";

	public static final String FOLLOW_COMMAND_ID = "org.eclipse.gemoc.addon.eventscheduling.timeline.Follow";

	/**
	 * The {@link AdapterFactory} created from the EMF registry.
	 */
	private final AdapterFactory adapterFactory = new ComposedAdapterFactory(
			ComposedAdapterFactory.Descriptor.Registry.INSTANCE);

	private IContentProvider _contentProvider;
	private ILabelProvider _labelProvider;

	private IExecutionEngine<?> _currentEngine;

	private WeakHashMap<IExecutionEngine<?>, Integer> _positions = new WeakHashMap<IExecutionEngine<?>, Integer>();

	public EventSchedulingTimeLineView() {
		_contentProvider = new AdapterFactoryContentProvider(adapterFactory);
		_labelProvider = new AdapterFactoryLabelProvider(adapterFactory);
		// be sure the view is initialized
		IWorkbenchPage wbpage = getActivePage();
		IViewPart viewPart = wbpage.findView(
				"org.eclipse.gemoc.addon.eventscheduling.timeline.views.timeline.EventSchedulingTimeLineView");
		wbpage.activate(viewPart);
		wbpage.bringToTop(viewPart);
	}

	@Override
	public void init(IViewSite site) throws PartInitException {
		super.init(site);
		startListeningToEngineSelectionChange();
		// initialize the view with the currently selected engine (stored by the
		// EngineRegistry)
		configure(org.eclipse.gemoc.executionframework.ui.Activator.getDefault().getEngineSelectionManager()
				.get_lastSelectedEngine());
	}

	@Override
	public void dispose() {
		disposeTimeLineProvider();
		removeDoubleClickListener();
		stopListeningToEngineSelectionChange();
		super.dispose();
		_contentProvider.dispose();
		_labelProvider.dispose();
	}

	@Override
	public void createPartControl(Composite parent) {
		super.createPartControl(parent);
		setDetailViewerContentProvider(_contentProvider);
		setDetailViewerLabelProvider(_labelProvider);
		_mouseListener = new MouseListener() {

			@Override
			public void mouseUp(MouseEvent e) {
			}

			@Override
			public void mouseDown(MouseEvent e) {
			}

			@Override
			public void mouseDoubleClick(MouseEvent event) {
				handleDoubleCick();
			}
		};

		getTimelineViewer().getControl().addMouseListener(_mouseListener);
	}

	private void startListeningToEngineSelectionChange() {
		org.eclipse.gemoc.executionframework.ui.Activator.getDefault().getEngineSelectionManager()
				.addEngineSelectionListener(this);
	}

	private void stopListeningToEngineSelectionChange() {
		org.eclipse.gemoc.executionframework.ui.Activator.getDefault().getEngineSelectionManager()
				.removeEngineSelectionListener(this);
	}

	private ITimelineProvider _timelineProvider;
	private MouseListener _mouseListener = null;

	private TimelineEditPartFactory _factory;

	public void configure(IExecutionEngine<?> engine) {
		if (engine == null) {
			// TODO clear the view or leave it content set to the last engine ?
		} else if (_currentEngine != engine || _timelineProvider == null) {
			saveStartIndex();
			_currentEngine = engine;
			disposeTimeLineProvider();
			if (engine != null) {
				int start = getStartIndex(engine);

				// If using a trace addon did not work, we fallback to the Gemoc trace
				if (_timelineProvider == null) {
					_timelineProvider = new EventSchedulingTimelineProvider(engine);
				}

				setTimelineProvider(_timelineProvider, start);

			}
		}
	}

	private IWorkbenchPage getActivePage() {
		return PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
	}

	private int getStartIndex(IExecutionEngine<?> engine) {
		int start = 0;
		if (_positions.containsKey(engine)) {
			start = _positions.get(engine);
		}
		return start;
	}

	private void saveStartIndex() {
		if (_currentEngine != null) {
			_positions.put(_currentEngine, getStart());
		}
	}

	private void removeDoubleClickListener() {
		if (_mouseListener != null && getTimelineViewer() != null && getTimelineViewer().getControl() != null) {
			getTimelineViewer().getControl().removeMouseListener(_mouseListener);
		}
	}

	private void disposeTimeLineProvider() {
		if (_timelineProvider != null) {
			((IDisposable) _timelineProvider).dispose();
			_timelineProvider = null;
			setTimelineProvider(_timelineProvider, 0);
		}
	}

	@Override
	public void engineSelectionChanged(IExecutionEngine<?> engine) {
		update(engine);
	}

	private boolean canDisplayTimeline(IExecutionEngine<?> engine) {
		if (engine.getExecutionContext().getExecutionMode().equals(ExecutionMode.Run)
				&& engine.getRunningStatus().equals(RunStatus.Stopped)) {
			return true;
		}
		if (engine.getExecutionContext().getExecutionMode().equals(ExecutionMode.Animation)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean hasDetailViewer() {
		return false;
	}

	@Override
	public String getFollowCommandID() {
		return FOLLOW_COMMAND_ID;
	}

	private void handleDoubleCick() {
		final ISelection selection = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService()
				.getSelection();
		if (selection instanceof IStructuredSelection) {
			final Object selected = ((IStructuredSelection) selection).getFirstElement();
			if (selected instanceof PossibleStepEditPart) {
				final Object o1 = ((PossibleStepEditPart) selected).getModel().getChoice2();
				Object o2 = ((PossibleStepEditPart) selected).getModel().getPossibleStep();
				if (o1 instanceof Choice && o2 instanceof Step) {
					Choice choice = (Choice) o1;
					ParallelStep<?, ?> logicalStep = (ParallelStep<?, ?>) o2;
					if (_currentEngine.getRunningStatus().equals(RunStatus.WaitingLogicalStepSelection)) {
						// If this choice has never been executed, we execute
						// the chosen logical step
						if (choice.getSelectedNextChoice() == null) {
							performExecutionStep(logicalStep);
						}
						// Otherwise, we branch at the *next choice* of the
						// chosen one
						else {
							Choice choiceToRestore = choice.getSelectedNextChoice();
							branchIfPossible(choiceToRestore);
						}
					}
				} else {

//					for (ITraceAddon traceAddon : _currentEngine.getAddonsTypedBy(ITraceAddon.class)) {
//						if (o1 instanceof EObject)
//							traceAddon.goTo((EObject) o1);
//					}

				}
			}
		}
	}

	private void performExecutionStep(ParallelStep<?, ?> logicalStep) {
		if (_currentEngine instanceof AbstractConcurrentExecutionEngine) {
			AbstractConcurrentExecutionEngine<?, ?> engine_cast = (AbstractConcurrentExecutionEngine<?, ?>) _currentEngine;
			if (engine_cast.getLogicalStepDecider() instanceof AbstractUserDecider) {
				AbstractUserDecider decider = (AbstractUserDecider) engine_cast.getLogicalStepDecider();
				decider.decideFromTimeLine(logicalStep);
			}
		}
		return;
	}

	private void branchIfPossible(Choice choice) {
		Set<AbstractEventSchedulingModelExecutionTracingAddon> candidateAddons = _currentEngine
				.getAddonsTypedBy(AbstractEventSchedulingModelExecutionTracingAddon.class);
		if (!candidateAddons.isEmpty()) {
			AbstractEventSchedulingModelExecutionTracingAddon addon = candidateAddons.iterator().next();
			try {
				Choice previousChoice = choice.getPreviousChoice();
				Branch previousBranch = previousChoice.getBranch();
				// if the choice is the last before last one, then branch
				if (previousBranch.getChoices().indexOf(previousChoice) == (previousBranch.getChoices().size() - 2)) {
					addon.reintegrateBranch(choice);
				} else {
					addon.branch(choice);
				}
			} catch (ModelExecutionTracingException e) {
				Activator.error(e.getMessage(), e);
			}
		}
	}

	@Override
	protected TimelineEditPartFactory getTimelineEditPartFactory() {
		_factory = new TimelineEditPartFactory(false);
		return _factory;
	}

	public void update(IExecutionEngine<?> engine) {
		if (engine != null) {
			if (canDisplayTimeline(engine)) {
				configure(engine);
			} else {
				disposeTimeLineProvider();
			}
		}
	}
}
