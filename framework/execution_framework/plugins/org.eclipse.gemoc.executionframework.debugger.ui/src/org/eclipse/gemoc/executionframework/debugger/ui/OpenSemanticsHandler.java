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
package org.eclipse.gemoc.executionframework.debugger.ui;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Supplier;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Platform;
import org.eclipse.debug.core.DebugException;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.gemoc.commons.eclipse.ui.OpenEditor;
import org.eclipse.gemoc.executionframework.engine.core.AbstractExecutionEngine;
import org.eclipse.gemoc.xdsmlframework.api.core.IExecutionEngine;
import org.osgi.framework.Bundle;

import org.eclipse.gemoc.dsl.debug.ide.adapter.DSLThreadAdapter;

public class OpenSemanticsHandler extends AbstractHandler {

	private IExecutionEngine engine;

	private String bundleSymbolicName;

	public OpenSemanticsHandler() {
		Activator.getDefault().setHandler(this);
	}

	private void searchForFile(File dir, List<File> accumulator, String name) {
		File[] files = dir.listFiles();
		for (File f : files) {
			if (f.isFile() && f.getName().equals(name)) {
				accumulator.add(f);
			} else if (f.isDirectory()) {
				searchForFile(f, accumulator, name);
			}
		}
	}

	public void locateAndOpenSource(TreeSelection selection) {
		TreePath path = selection.getPaths()[0].getParentPath();
		Object obj = path.getLastSegment();
		int idx = 0;
		if (obj instanceof DSLThreadAdapter) {
			try {
				idx = Arrays.asList(((DSLThreadAdapter) obj).getStackFrames()).indexOf(selection.getFirstElement());
			} catch (DebugException e) {
				Activator.error(e.getMessage(), e);
			}
		}

		Thread thread = ((AbstractExecutionEngine) engine).thread;
		StackTraceElement[] stackTrace = thread.getStackTrace();
		String className = engine.getClass().getName();
		String methodName = "executeStep";
		LinkedList<StackTraceElement> elements = new LinkedList<>();
		for (int i = 0; i < stackTrace.length; i++) {
			StackTraceElement element = stackTrace[i];
			if (element.getMethodName().equals(methodName) && element.getClassName().equals(className)) {
				if (idx == 0) {
					elements.add(stackTrace[i + 1]);
					break;
				} else {
					idx--;
				}
			}
		}
		if (!elements.isEmpty()) {
			final Bundle bundle = Platform.getBundle(bundleSymbolicName);
			File bundleFile = null;
			try {
				bundleFile = FileLocator.getBundleFile(bundle);
				List<File> files = new ArrayList<>();
				for (StackTraceElement e : elements) {
					searchForFile(bundleFile, files, e.getFileName());
				}
				for (File f : files) {
					OpenEditor.openFile(f);
				}
			} catch (IOException e) {
				Activator.error(e.getMessage(), e);
			}
		}
	}

	public Object execute(ExecutionEvent event) throws ExecutionException {
		Supplier<IExecutionEngine> engineSupplier = org.eclipse.gemoc.executionframework.debugger.Activator.getDefault().getEngineSupplier();
		Supplier<String> bundleSupplier = org.eclipse.gemoc.executionframework.debugger.Activator.getDefault().getBundleSymbolicNameSupplier();
		if (engineSupplier != null) {
			this.engine = engineSupplier.get();
		}
		if (bundleSupplier != null) {
			this.bundleSymbolicName = bundleSupplier.get();
		}
		
		TreeSelection selection = (TreeSelection) HandlerUtil.getCurrentSelection(event);
		locateAndOpenSource(selection);
		return null;
	}

	public void setBundleSymbolicName(String bundleSymbolicName) {
		this.bundleSymbolicName = bundleSymbolicName;
	}

	public void setEngine(IExecutionEngine engine) {
		this.engine = engine;
	}
}
