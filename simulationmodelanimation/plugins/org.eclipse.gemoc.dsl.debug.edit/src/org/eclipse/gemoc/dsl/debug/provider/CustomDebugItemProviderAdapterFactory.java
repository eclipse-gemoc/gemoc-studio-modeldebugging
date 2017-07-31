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
package org.eclipse.gemoc.dsl.debug.provider;

import org.eclipse.emf.common.notify.Adapter;

/**
 * Custom implementation of {@link DebugItemProviderAdapterFactory}.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class CustomDebugItemProviderAdapterFactory extends DebugItemProviderAdapterFactory {

	@Override
	public Adapter createDebugTargetAdapter() {
		if (debugTargetItemProvider == null) {
			debugTargetItemProvider = new CustomDebugTargetItemProvider(this);
		}

		return debugTargetItemProvider;
	}

	@Override
	public Adapter createStackFrameAdapter() {
		if (stackFrameItemProvider == null) {
			stackFrameItemProvider = new CustomStackFrameItemProvider(this);
		}

		return stackFrameItemProvider;
	}

	@Override
	public Adapter createThreadAdapter() {
		if (threadItemProvider == null) {
			threadItemProvider = new CustomThreadItemProvider(this);
		}

		return threadItemProvider;
	}

}
