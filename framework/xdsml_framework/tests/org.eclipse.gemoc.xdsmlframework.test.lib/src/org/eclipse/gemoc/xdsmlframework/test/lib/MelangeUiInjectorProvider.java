/*******************************************************************************
 * Copyright (c) 2017 Inria.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Inria - initial API and implementation
 *******************************************************************************/
package org.eclipse.gemoc.xdsmlframework.test.lib;

import org.eclipse.xtext.junit4.IInjectorProvider;

import com.google.inject.Injector;

public class MelangeUiInjectorProvider implements IInjectorProvider {

	@Override
	public Injector getInjector() {
		return fr.inria.diverse.melange.ui.internal.MelangeActivator.getInstance().getInjector("fr.inria.diverse.melange.Melange");
	}

}
