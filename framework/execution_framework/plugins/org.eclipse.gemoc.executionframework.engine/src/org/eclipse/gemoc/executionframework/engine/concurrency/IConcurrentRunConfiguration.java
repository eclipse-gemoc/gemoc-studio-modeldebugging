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
package org.eclipse.gemoc.executionframework.engine.concurrency;

import org.eclipse.gemoc.xdsmlframework.api.core.IRunConfiguration;

public interface IConcurrentRunConfiguration extends IRunConfiguration{

	public static final String LAUNCH_INITIALIZATION_METHOD = "GEMOC_LAUNCH_INITIALIZATION_METHOD";
	public static final String LAUNCH_INITIALIZATION_ARGUMENTS = "GEMOC_LAUNCH_INITIALIZATION_ARGUMENTS";

	String getDeciderName();
	String getModelInitializationMethod();
	String getModelInitializationArguments();
}
