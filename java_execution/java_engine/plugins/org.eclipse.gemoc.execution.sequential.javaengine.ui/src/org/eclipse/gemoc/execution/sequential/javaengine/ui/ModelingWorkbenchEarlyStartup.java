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
package org.eclipse.gemoc.execution.sequential.javaengine.ui;

import org.eclipse.ui.IStartup;

public class ModelingWorkbenchEarlyStartup implements IStartup {

	@Override
	public void earlyStartup() {
		//nothing to do except making sure this plugin is started
		// because PropertyTester seems to be fully activated only when the containing plugin is started

	}

}
