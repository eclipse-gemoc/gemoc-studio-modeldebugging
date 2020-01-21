/*******************************************************************************
 * Copyright (c) 2016, 2017 Inria and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Inria - initial API and implementation
 *******************************************************************************/
package org.eclipse.gemoc.executionframework.debugger;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.eclipse.debug.internal.ui.viewers.model.provisional.TreeModelViewer;
import org.eclipse.debug.internal.ui.views.launch.LaunchView;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.gemoc.dsl.debug.StackFrame;
import org.eclipse.gemoc.dsl.debug.ide.AbstractDSLDebugger;
import org.eclipse.gemoc.dsl.debug.ide.adapter.DSLStackFrameAdapter;
import org.eclipse.gemoc.dsl.debug.ide.event.IDSLDebugEventProcessor;
import org.eclipse.gemoc.executionframework.engine.commons.adapters.ResourceEObjectAdapter;
import org.eclipse.gemoc.trace.commons.model.trace.Step;
import org.eclipse.gemoc.xdsmlframework.api.core.IExecutionEngine;
import org.eclipse.gemoc.xdsmlframework.api.engine_addon.modelchangelistener.FieldChange;
import org.eclipse.gemoc.xdsmlframework.api.engine_addon.modelchangelistener.IModelChangeListenerAddon;
import org.eclipse.gemoc.xdsmlframework.api.engine_addon.modelchangelistener.SimpleModelChangeListenerAddon;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;

@SuppressWarnings("restriction")
public abstract class AbstractGemocDebugger extends AbstractDSLDebugger implements IGemocDebugger {

	public final static String SELF_VARIABLE_NAME = "self";
	
	public final static String MUTABLE_DATA_DECLARATION_TYPENAME = "mutable data";
	public final static String MUTABLE_STATIC_DATA_DECLARATION_TYPENAME = "mutable static data";
	public final static String GLOBAL_CONTEXT_FRAMENAME = "Engine";
	
	/**
	 * {@link MutableField} delta values.
	 */
	private Map<MutableField, Object> lastSuspendMutableFields;

	/**
	 * {@link MutableField} delta values.
	 */
	private Map<MutableField, Object> nextSuspendMutableFields;

	/**
	 * {@link MutableField} mutable values.
	 * The list of mutable fields is stored in a flat list but will be displayed using containment structure
	 */
	private final List<MutableField> mutableFields = new ArrayList<>();

	private IModelChangeListenerAddon modelChangeListenerAddon;

	protected EObject executedModelRoot = null;

	protected final IExecutionEngine<?> engine;

	private String bundleSymbolicName;

	private List<IMutableFieldExtractor> mutableFieldExtractors = new ArrayList<>();

	public AbstractGemocDebugger(IDSLDebugEventProcessor target, IExecutionEngine<?> engine) {
		super(target);
		this.engine = engine;

		// This prevents a null pointer exception if the engine does not have a
		// Language Definition Extension.
		// In that case, the getLanguageDefinitionExtension() returns null
		// e.g., the coordination engine
		if (engine.getExecutionContext().getLanguageDefinitionExtension() != null) {
			bundleSymbolicName = engine.getExecutionContext().getLanguageDefinitionExtension().getName().toLowerCase();
		}
		registerModelChangeListener();

		Activator openSourceActivator = Activator.getDefault();
		openSourceActivator.setHandlerFieldSuppliers(() -> this.engine, () -> this.bundleSymbolicName);

	}

	protected void registerModelChangeListener() {
		Set<IModelChangeListenerAddon> listenerAddons = engine.getAddonsTypedBy(IModelChangeListenerAddon.class);
		if (listenerAddons.isEmpty()) {
			modelChangeListenerAddon = new SimpleModelChangeListenerAddon(engine);
		} else {
			modelChangeListenerAddon = listenerAddons.stream().findFirst().get();
		}
		modelChangeListenerAddon.registerAddon(this);
	}

	public void setMutableFieldExtractors(List<IMutableFieldExtractor> mutableFieldExtractors) {
		this.mutableFieldExtractors = mutableFieldExtractors;
	}

	private Set<BiPredicate<IExecutionEngine<?>, Step<?>>> predicateBreakPoints = new HashSet<BiPredicate<IExecutionEngine<?>, Step<?>>>();
	private Set<BiPredicate<IExecutionEngine<?>, Step<?>>> predicateBreaks = new HashSet<BiPredicate<IExecutionEngine<?>, Step<?>>>();

	@Override
	/**
	 * Breakpoints are persistent, and can trigger pauses as long as they are not
	 * removed.
	 */
	public void addPredicateBreakpoint(BiPredicate<IExecutionEngine<?>, Step<?>> predicate) {
		predicateBreakPoints.add(predicate);
	}

	@Override
	/**
	 * A Break only trigger a single pause, then is removed.
	 */
	public void addPredicateBreak(BiPredicate<IExecutionEngine<?>, Step<?>> predicate) {
		predicateBreaks.add(predicate);
	}
	
	

	@Override
	public List<String> getTags() {
		return IGemocDebugger.super.getTags();
	}

	protected boolean shouldBreakPredicates(IExecutionEngine<?> engine, Step<?> step) {

		// We look at predicate breaks to remove the ones that are true
		boolean shouldBreak = false;
		Set<BiPredicate<IExecutionEngine<?>, Step<?>>> toRemove = new HashSet<BiPredicate<IExecutionEngine<?>, Step<?>>>();
		for (BiPredicate<IExecutionEngine<?>, Step<?>> pred : predicateBreaks) {
			if (pred.test(engine, step)) {
				shouldBreak = true;
				toRemove.add(pred);
			}
		}
		predicateBreaks.removeAll(toRemove);
		if (shouldBreak)
			return true;

		// If no break yet, we look at predicate breakpoints
		for (BiPredicate<IExecutionEngine<?>, Step<?>> pred : predicateBreakPoints) {
			if (pred.test(engine, step)) {
				return true;
			}
		}

		return false;

	}

	protected EObject getModelRoot() {
		if (executedModelRoot == null) {
			if (engine != null) {
				executedModelRoot = engine.getExecutionContext().getResourceModel().getContents().get(0);
			}
		}
		return executedModelRoot;
	}

	private boolean updateMutableFieldList(EObject eObject) {
		Iterator<IMutableFieldExtractor> extractors = mutableFieldExtractors.iterator();
		List<MutableField> newMutableFields = Collections.emptyList();
		while (newMutableFields.isEmpty() && extractors.hasNext()) {
			newMutableFields = extractors.next().extractMutableField(eObject);
		}
		return mutableFields.addAll(newMutableFields);
	}
	
	/**
	 * update the mutableField for the given eObject and recursively into its content
	 * will do nothing if the object is already known
	 * @param eObject
	 */
	private void recursiveUpdateMutableFieldList(EObject eObject) {
		List<MutableField> currentMutableFields = lookForMutableFields(eObject);
		if (currentMutableFields.isEmpty()) {
			// This is a new object
			updateMutableFieldList(eObject);
		}
		
		for (EObject content : eObject.eContents()) {
			recursiveUpdateMutableFieldList(content);
		}
	}

	private void initializeMutableDatas() {
		mutableFields.clear();
		lastSuspendMutableFields = new HashMap<MutableField, Object>();
		nextSuspendMutableFields = new HashMap<MutableField, Object>();

		// We fetch all resources concerned by the execution,
		// since they may contain mutable fields
		Resource executedResource = executedModelRoot.eResource();
		Set<Resource> allResources = org.eclipse.gemoc.commons.eclipse.emf.EMFResource
				.getRelatedResources(executedResource);
		allResources.add(executedResource);
		allResources.removeIf(r -> r == null);

		// We try each extractor
		for (IMutableFieldExtractor extractor : mutableFieldExtractors) {

			// On all objects of all resources
			for (Resource resource : allResources) {
				TreeIterator<EObject> iterator = resource.getAllContents();
				while (iterator.hasNext()) {
					EObject eObject = iterator.next();
					mutableFields.addAll(extractor.extractMutableField(eObject));

					// If we found private stuff, we make it public
					Arrays.asList(eObject.getClass().getDeclaredFields()).stream().forEach((f) -> {
						try {
							f.setAccessible(true);
						} catch (Exception e) {
						}
					});
				}
			}

			// If we found stuff with an extractor, we stop searching to
			// avoid redundancies
			if (!mutableFields.isEmpty())
				break;
		}

		// we sort the list of mutable data objects by name
		mutableFields.sort(new Comparator<MutableField>() {
			@Override
			public int compare(MutableField o1, MutableField o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});
	}

	private MutableField lookForMutableField(String variableName) {
		return mutableFields.stream().filter(m -> m.getName().equals(variableName)).findFirst().get();
	}

	private List<MutableField> lookForMutableFields(EObject eObject) {
		return mutableFields.stream().filter(m -> m.geteObject() == eObject).collect(Collectors.toList());
	}

	private boolean mutableDataChanged(MutableField mutableData, Object value) {
		final Object lastValue = lastSuspendMutableFields.get(mutableData);
		return (lastValue != null && value == null) || (lastValue == null && value != null)
				|| (lastValue != null && value != null && !lastValue.equals(value));
	}

	abstract protected void updateStack(String threadName, EObject instruction);

	protected void updateVariables(String threadName) {
		List<FieldChange> changes = modelChangeListenerAddon.getChanges(this);
		for (FieldChange change : changes) {
			switch (change.getChangeType()) {
			case MODIFY:
			case ADD:
				if (change.getValue() instanceof EObject) {
					EObject eObject = (EObject) change.getValue();
					recursiveUpdateMutableFieldList(eObject);
				}
				break;
			case REMOVE:
				Object value = change.getValue();
				if (value instanceof EObject && ((EObject) value).eContainer() == null) {
					List<MutableField> toRemove = lookForMutableFields((EObject) value);
					// deleteVariable will never work in our case in the current
					// state of the debug infrastructure, as it only searches
					// for variables in the top stackframe
					toRemove.stream().forEach(m -> deleteVariable(threadName, m.getName()));
					mutableFields.removeAll(toRemove);
				} else if (value instanceof List) {
					List<EObject> eObjects = ((List<?>) value).stream().filter(e -> e instanceof EObject)
							.map(e -> (EObject) e).collect(Collectors.toList());
					eObjects.forEach(e -> {
						if (e.eContainer() == null) {
							List<MutableField> toRemove = lookForMutableFields(e);
							toRemove.stream().forEach(m -> deleteVariable(threadName, m.getName()));
							mutableFields.removeAll(toRemove);
						}
					});
				}
				break;
			}
		}
		List<MutableField> changed = new ArrayList<MutableField>();
		mutableFields.forEach(e -> {
			nextSuspendMutableFields.put(e, e.getValue());
			if (mutableDataChanged(e, e.getValue())) {
				changed.add(e);
			}
		});

		// create a debug variable in all StackFrames (including the global context one)
		final String globalFrameName = GLOBAL_CONTEXT_FRAMENAME +" : " + engine.getExecutionContext().getResourceModel().getURI().lastSegment();
		for (MutableField m : changed) {
			if (!Activator.getDefault().isUseNestedDebugVariables() || isRootMutableField(m)) {
				for (String name : stackFrameNames) {
					variable(threadName, name, MUTABLE_DATA_DECLARATION_TYPENAME, m.getName(), m.getValue(), true);
				}
				variable(threadName, globalFrameName, MUTABLE_DATA_DECLARATION_TYPENAME, m.getName(), m.getValue(), true);
			}
		}

		if (!nextSuspendMutableFields.isEmpty()) {
			lastSuspendMutableFields = nextSuspendMutableFields;
			nextSuspendMutableFields = new HashMap<MutableField, Object>();
		}
	}

	/*
	 * Checks if the given string can be interpreted as a valid value for the given
	 * variable.
	 */
	@Override
	public boolean validateVariableValue(String threadName, String variableName, String value) {
		final MutableField data = lookForMutableField(variableName);
		return getValue(data, value) != null;
	}

	/*
	 * Returns the given string interpreted as a value of the same type as the
	 * current value of the data.
	 */
	private Object getValue(MutableField data, String value) {
		final Object res;

		final Object currentValue = data.getValue();

		if (currentValue instanceof String) {
			res = value;
		} else if (currentValue instanceof Integer) {
			Integer integerValue = null;
			try {
				integerValue = Integer.decode(value);
			} catch (Exception e) {
				// nothing to do here
			}
			res = integerValue;
		} else if (currentValue instanceof Double) {
			Double doubleValue = null;
			try {
				doubleValue = Double.parseDouble(value);
			} catch (Exception e) {
				// nothing to do here
			}
			res = doubleValue;
		} else if (currentValue instanceof Boolean) {
			res = Boolean.valueOf(value);
		} else {
			res = null;
		}

		return res;
	}

	private Deque<String> stackFrameNames = new ArrayDeque<>();

	@Override
	public void pushStackFrame(String threadName, String frameName, EObject context, EObject instruction) {
		super.pushStackFrame(threadName, frameName, context, instruction);
		stackFrameNames.push(frameName);
		
		// add a variable for "self" target
		// note: the variable is marked as not supporting modification, this may change in the future if we support "live modeling"
		variable(threadName, frameName, MUTABLE_STATIC_DATA_DECLARATION_TYPENAME, SELF_VARIABLE_NAME, context, false);
		
		// add all other mutable fields (but keep only root fields)
		
		for (MutableField m : mutableFields) {
			if (!Activator.getDefault().isUseNestedDebugVariables() || isRootMutableField(m)) {
				variable(threadName, frameName, MUTABLE_DATA_DECLARATION_TYPENAME, m.getName(), m.getValue(), true);
			}
		}
	}

	@Override
	public void popStackFrame(String threadName) {
		super.popStackFrame(threadName);
		stackFrameNames.pop();
	}

	@Override
	public Object getVariableValue(String threadName, String stackName, String variableName, String value) {
		final MutableField data = lookForMutableField(variableName);
		return getValue(data, value);
	}

	@Override
	public void setVariableValue(String threadName, String stackName, String variableName, Object value) {
		final MutableField data = lookForMutableField(variableName);
		data.setValue(value);
	}

	private ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

	@Override
	public void updateData(String threadName, EObject instruction) {
		if (executedModelRoot == null) {
			executedModelRoot = getModelRoot();
			
			EList<EObject> resourceContent  = engine.getExecutionContext().getResourceModel().getContents();
			EObject context  = new ResourceEObjectAdapter(engine.getExecutionContext().getResourceModel().getURI().lastSegment(), resourceContent);
			initializeMutableDatas();
			String frameName = GLOBAL_CONTEXT_FRAMENAME +" : " + engine.getExecutionContext().getResourceModel().getURI().lastSegment();
			pushStackFrame(threadName, frameName, context, instruction);
			
			for (MutableField m : mutableFields) {
				if (!Activator.getDefault().isUseNestedDebugVariables() || isRootMutableField(m)) {
					variable(threadName, frameName, MUTABLE_DATA_DECLARATION_TYPENAME, m.getName(), m.getValue(), true);
				}
			} 
		} else {
			// Updating mutable datas
			updateVariables(threadName);
		}
		updateStack(threadName, instruction);
		scheduleSelectLastStackframe(500);
	}

	protected void scheduleSelectLastStackframe(long delay) {
		executorService.schedule(() -> selectLastStackframe(), delay, TimeUnit.MILLISECONDS);
	}

	private <T> List<T> flatten(List<T> ts, Function<T, List<T>> provider) {
		if (ts.isEmpty()) {
			return ts;
		} else {
			List<T> res = new ArrayList<>();
			for (T t : ts) {
				res.addAll(flatten(provider.apply(t), provider));
				res.add(t);
			}
			return res;
		}
	}

	private void selectLastStackframe() {
		final IWorkbench workbench = PlatformUI.getWorkbench();
		workbench.getDisplay().asyncExec(() -> {
			final IWorkbenchPage workbenchPage = workbench.getActiveWorkbenchWindow().getActivePage();
			final IViewPart view = workbenchPage.findView("org.eclipse.debug.ui.DebugView");
			if (view == null) {
				Activator.getDefault().debug(
						"Cannot find view org.eclipse.debug.ui.DebugView (may be not opened yet), => not updating the debug stack. ");
				return;
			}
			view.setFocus();
			final ISelectionProvider selectionProvider = view.getSite().getSelectionProvider();
			selectionProvider.setSelection(StructuredSelection.EMPTY);
			if (view instanceof LaunchView) {
				final LaunchView launchView = (LaunchView) view;
				final Viewer viewer = launchView.getViewer();
				final Tree tree = ((TreeModelViewer) viewer).getTree();
				final TreeItem[] items = tree.getItems();
				final List<TreeItem> allItems = flatten(Arrays.asList(items), t -> Arrays.asList(t.getItems()));
				final List<TreeItem> leafItems = allItems.stream()
						.filter(i -> i.getData() instanceof DSLStackFrameAdapter)
						.filter(i -> ((DSLStackFrameAdapter) i.getData()).getTarget() instanceof StackFrame)
						.collect(Collectors.toList());
				for (TreeItem item : leafItems) {
					final DSLStackFrameAdapter stackFrameAdapter = (DSLStackFrameAdapter) item.getData();
					final StackFrame s = (StackFrame) stackFrameAdapter.getTarget();
					if(s.getChildFrame() != null && s.getChildFrame().getChildFrame() == null) {
						// we select the topmost frame where the step is actually started (ie. ignore the top step "about to start")
						// this allows to focus on a frame that shows runtime data changes in yellow
						tree.showItem(item);
						tree.select(item);
						final TreeSelection selection = (TreeSelection) viewer.getSelection();
						final TreePath[] paths = selection.getPathsFor(stackFrameAdapter);
						selectionProvider.setSelection(new TreeSelection(paths));
						break;
					}
				}
			}
		});
	}
	
	/**
	 * indicates if the provided field is not contained by another object that is himself a mutable field.
	 * This is useful in order to show only "root" fields
	 * Note: this methods does not cross non mutable object. 
	 * Ie. if A contains B, B contains C, C contains D
	 *  A, C and D are mutable, then isRootMutableField will return true for A and C
	 * @param field
	 * @return
	 */
	protected boolean isRootMutableField(MutableField field) {
		EObject parent = field.geteObject();
		if(parent != null) {
			for (MutableField m : mutableFields) {
				if(m.getMutableProperty() instanceof EReference) {
					EReference mRef = (EReference) m.getMutableProperty();
					if(mRef.isContainment()) {
						if(mRef.getUpperBound() != 1) {
							@SuppressWarnings("unchecked")
							EList<EObject> l = (EList<EObject>) m.getValue();
							if(l.contains(parent)) 
								return false;
						} else {
							if(m.getValue() == parent ) {
								return false;
							}
						}
					}
				}
			}
		}
		return true;
	}
}
