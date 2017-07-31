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
package org.eclipse.gemoc.addon.diffviewer.logic;

public class Diff {

	public enum DiffKind {
		SUBST, IN, DEL, EQ
	}
	
	public final DiffKind kind;

	public final int idx1;
	
	public final int idx2;
	
	public Diff(DiffKind kind, int idx1, int idx2) {
		this.kind = kind;
		this.idx1 = idx1;
		this.idx2 = idx2;
	}
}
