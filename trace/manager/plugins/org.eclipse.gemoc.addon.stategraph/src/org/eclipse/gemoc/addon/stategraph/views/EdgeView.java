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

import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;
import javafx.scene.Group;

public abstract class EdgeView extends Group {

	protected final DoubleBinding a;
	
	public EdgeView(final DoubleProperty sX, final DoubleProperty sY, final DoubleProperty eX,
			final DoubleProperty eY) {
		a = new DoubleBinding() {
			private final DoubleBinding dX = sX.subtract(eX);
			private final DoubleBinding dY = sY.subtract(eY);

			{
				super.bind(dX, dY);
			}

			@Override
			protected double computeValue() {
				return Math.atan2(dY.get(), dX.get());
			}
		};
	}
}
