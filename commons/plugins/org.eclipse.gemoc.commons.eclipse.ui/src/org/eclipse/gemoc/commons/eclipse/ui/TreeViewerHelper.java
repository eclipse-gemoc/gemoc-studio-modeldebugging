/*******************************************************************************
 * Copyright (c) 2017, 2020 Inria and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Inria - initial API and implementation
 *******************************************************************************/
package org.eclipse.gemoc.commons.eclipse.ui;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.TreeColumn;

public class TreeViewerHelper
{

	/**
	 * resize all the columns of the tree viewer to their preferred sizes
	 * @param viewer
	 * @param force, if false, the resize happens only if the column size is 0
	 */
	public static void resizeColumns(TreeViewer viewer, boolean force)
	{
	    for (TreeColumn tc : viewer.getTree().getColumns()) {
	    	if(tc.getWidth() == 0 || force )
	    		tc.pack();
		}
	}
	
	/**
	 * resize all the columns of the tree viewer to their preferred sizes if their size is 0
	 * @param viewer
	 */
	public static void resizeColumns(TreeViewer viewer)
	{
		resizeColumns(viewer, false);
	}
	
}
