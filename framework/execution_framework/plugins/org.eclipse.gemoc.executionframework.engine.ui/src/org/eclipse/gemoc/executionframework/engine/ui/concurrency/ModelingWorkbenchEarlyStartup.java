/*******************************************************************************
 * Copyright (c) 2017 INRIA and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     INRIA - initial API and implementation
 *     I3S Laboratory - API update and bug fix
 *******************************************************************************/
package org.eclipse.gemoc.executionframework.engine.ui.concurrency;

import org.eclipse.ui.IStartup;

public class ModelingWorkbenchEarlyStartup implements IStartup {

	@Override
	public void earlyStartup() {
		//nothing to do except making sure this plugin is started
		// because PropertyTester seems to be fully activated only when the containing plugin is started

	}

}
