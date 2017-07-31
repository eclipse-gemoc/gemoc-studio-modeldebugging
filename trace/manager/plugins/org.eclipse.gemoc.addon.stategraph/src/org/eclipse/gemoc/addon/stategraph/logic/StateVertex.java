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
package org.eclipse.gemoc.addon.stategraph.logic;

import java.util.function.Consumer;

public class StateVertex {

	private String tooltip;
	private int index;
	private Consumer<String> command;

	public StateVertex() {
	}

	public StateVertex(String tooltip, int index) {
		this.tooltip = tooltip;
	}

	public String getTooltip() {
		return tooltip;
	}

	public void setTooltip(String tooltip) {
		this.tooltip = tooltip;
		if (command != null) {
			command.accept(tooltip);
		}
	}

	public void setOnTooltipUpdateCommand(Consumer<String> command) {
		this.command = command;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
}
