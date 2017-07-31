/*******************************************************************************
 * Copyright (c) 2015, 2017 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.gemoc.dsl.debug.ide.ui.launch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.ui.model.WorkbenchContentProvider;

/**
 * a {@link WorkbenchContentProvider} keeping only {@link IFile} with supported extension.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class FilteredFileContentProvider extends WorkbenchContentProvider {

	/**
	 * The supported file extensions.
	 */
	private final String[] extensions;

	/**
	 * Constructor.
	 * 
	 * @param extensions
	 *            the supported file extensions
	 */
	public FilteredFileContentProvider(final String[] extensions) {
		this.extensions = extensions;
	}

	@Override
	public Object[] getChildren(final Object element) {
		List<Object> children = Arrays.asList(super.getChildren(element));
		List<Object> filteredChildren = new ArrayList<Object>(children);

		for (Object child : children) {
			if ((child instanceof IFile) && (!isAccepted((IFile)child))) {
				filteredChildren.remove(child);
			}
		}

		return filteredChildren.toArray();
	}

	/**
	 * Tells if the given {@link IFile} should be accepted as a child of the tree.
	 * 
	 * @param child
	 *            the {@link IFile} to check
	 * @return <code>true</code> if the {@link IFile} extension is one of
	 *         {@link FilteredFileContentProvider#extensions}, <code>false</code> otherwise
	 */
	private boolean isAccepted(final IFile child) {
		boolean res = false;

		for (String extension : extensions) {
			if (extension.equalsIgnoreCase(child.getFileExtension())) {
				res = true;
				break;
			}
		}

		return res;
	}
}
