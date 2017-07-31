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
package org.eclipse.gemoc.addon.stategraph.layout;

import java.util.Collection;

import org.eclipse.elk.core.service.IDiagramLayoutConnector;
import org.eclipse.elk.core.service.ILayoutSetup;
import org.eclipse.gemoc.addon.stategraph.logic.StateVertex;
import org.eclipse.gemoc.addon.stategraph.views.StateGraphViewPart;

import com.google.inject.Binder;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.util.Modules;

public class StateGraphLayoutSetup implements ILayoutSetup {

	public Injector createInjector(final Module defaultModule) {
		return Guice.createInjector(Modules.override(defaultModule).with(new StateGraphLayoutModule()));
	}

	public static class StateGraphLayoutModule implements Module {
		@Override
		public void configure(final Binder binder) {
			binder.bind(IDiagramLayoutConnector.class).to(StateGraphLayoutConnector.class);
		}
	}

	@Override
	public boolean supports(Object object) {
		if (object instanceof Collection) {
			for (Object o : (Collection<?>) object) {
				if (o instanceof StateVertex) {
					return true;
				}
			}
			return false;
		}
		return object instanceof StateGraphViewPart || object instanceof StateVertex;
	}
}
