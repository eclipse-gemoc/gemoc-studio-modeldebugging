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
package org.eclipse.gemoc.xdsmlframework.api.engine_addon.modelchangelistener;

import java.util.List;

import org.eclipse.gemoc.xdsmlframework.api.engine_addon.IEngineAddon;

public interface IModelChangeListenerAddon {

	public List<FieldChange> getChanges(IEngineAddon addon);
		
	public boolean registerAddon(IEngineAddon addon);
}
