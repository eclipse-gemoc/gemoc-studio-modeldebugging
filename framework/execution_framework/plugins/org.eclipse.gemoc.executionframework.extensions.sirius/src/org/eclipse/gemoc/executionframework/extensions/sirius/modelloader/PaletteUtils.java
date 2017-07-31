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
package org.eclipse.gemoc.executionframework.extensions.sirius.modelloader;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.eclipse.gef.ui.palette.FlyoutPaletteComposite;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditorWithFlyOutPalette;
import org.eclipse.swt.widgets.Display;

public final class PaletteUtils {

	private PaletteUtils() {
		// nothing to do here
	}

	public static void openPalette(DiagramEditorWithFlyOutPalette diagramEditor) {
		setState(diagramEditor, FlyoutPaletteComposite.STATE_PINNED_OPEN);
	}

	public static void colapsePalette(
			DiagramEditorWithFlyOutPalette diagramEditor) {
		setState(diagramEditor, FlyoutPaletteComposite.STATE_COLLAPSED);
	}

	private static void setState(DiagramEditorWithFlyOutPalette diagramEditor,
			int state) {
		try {
			final Field fieldSplitter = DiagramEditorWithFlyOutPalette.class
					.getDeclaredField("splitter");
			fieldSplitter.setAccessible(true);
			final FlyoutPaletteComposite palette = (FlyoutPaletteComposite) fieldSplitter
					.get(diagramEditor);
			
		final Method methodSetState = FlyoutPaletteComposite.class.getDeclaredMethod("setState", int.class);
		methodSetState.setAccessible(true);
		Display.getDefault().syncExec(new Runnable() {
			
			@Override
			public void run() {
				try {
					methodSetState.invoke(palette, state);
				} catch (IllegalAccessException e) {
					throw new RuntimeException(e);
				} catch (IllegalArgumentException e) {
					throw new RuntimeException(e);
				} catch (InvocationTargetException e) {
					throw new RuntimeException(e);
				}
			}
		});
		
		fieldSplitter.setAccessible(false);
		methodSetState.setAccessible(false);
		} catch (NoSuchFieldException e) {
			throw new RuntimeException(e);
		} catch (SecurityException e) {
			throw new RuntimeException(e);
		} catch (IllegalArgumentException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (NoSuchMethodException e) {
			throw new RuntimeException(e);
		}
	}

}
