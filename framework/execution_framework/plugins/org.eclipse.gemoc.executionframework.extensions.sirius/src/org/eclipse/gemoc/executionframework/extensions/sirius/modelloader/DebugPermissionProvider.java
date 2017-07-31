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
package org.eclipse.gemoc.executionframework.extensions.sirius.modelloader;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionListener;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.ecore.extender.business.api.permission.IPermissionAuthority;
import org.eclipse.sirius.ecore.extender.business.api.permission.IPermissionProvider;
import org.eclipse.gemoc.xdsmlframework.api.core.IExecutionCheckpoint;

public class DebugPermissionProvider implements IPermissionProvider {

	@Override
	public boolean provides(ResourceSet set) {
		final boolean res;

		if (set != null && set.getURIConverter() != null) {
			URIConverter converter = set.getURIConverter();
			if (converter.getURIHandlers().size() > 0) {
				res = converter.getURIHandlers().get(0) instanceof DebugURIHandler;
			} else {
				res = false;
			}
		} else {
			res = false;
		}

		return res;
	}

	@Override
	public IPermissionAuthority getAuthority(final ResourceSet set) {
		final DebugPermissionAuthority res;

		final DebugPermissionAuthority existing = (DebugPermissionAuthority) IExecutionCheckpoint.CHECKPOINTS
				.get(set);

		if (existing != null) {
			res = existing;
		} else {
			res = new DebugPermissionAuthority();
			IExecutionCheckpoint.CHECKPOINTS.put(set, res);
			if (set.getResources().size() > 0) {
				final Session session = SessionManager.INSTANCE.getSession(set
						.getResources().get(0));
				if (session != null) {
					session.addListener(new SessionListener() {
						@Override
						public void notify(int changeKind) {
							if (changeKind == SessionListener.CLOSED) {
								IExecutionCheckpoint.CHECKPOINTS.remove(set);
							}
						}
					});
				}
			}
		}

		return res;
	}

}
