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

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.sirius.ecore.extender.business.api.permission.IAuthorityListener;
import org.eclipse.sirius.ecore.extender.business.api.permission.LockStatus;
import org.eclipse.sirius.ecore.extender.business.internal.permission.AbstractPermissionAuthority;
import org.eclipse.gemoc.xdsmlframework.api.core.IExecutionCheckpoint;

public class DebugPermissionAuthority extends AbstractPermissionAuthority
		implements IExecutionCheckpoint {

	/**
	 * Strictly positive if allowed. FIXME we use a map here because of
	 * https://support.jira.obeo.fr/browse/VP-2710
	 */
	private static final Map<ResourceSet, Integer> allow = new HashMap<ResourceSet, Integer>();

	public void allow(ResourceSet rs, boolean allow) {
		Integer integer = this.allow.get(rs);
		if (allow) {
			if (integer == null) {
				this.allow.put(rs, Integer.valueOf(1));
			} else {
				this.allow.put(rs, Integer.valueOf(integer.intValue() + 1));
			}
		} else {
			if (integer == null) {
				this.allow.put(rs, Integer.valueOf(-1));
			} else {
				this.allow.put(rs, Integer.valueOf(integer.intValue() - 1));
			}
		}
	}

	@Override
	public boolean canEditFeature(EObject eObj, String featureName) {
		Integer integer = allow.get(eObj.eResource().getResourceSet());
		return integer != null && integer.intValue() > 0;
	}

	@Override
	public boolean canEditInstance(EObject eObj) {
		Integer integer = allow.get(eObj.eResource().getResourceSet());
		return integer != null && integer.intValue() > 0;
	}

	@Override
	public boolean canCreateIn(EObject eObj) {
		Integer integer = allow.get(eObj.eResource().getResourceSet());
		return integer != null && integer.intValue() > 0;
	}

	@Override
	public boolean canDeleteInstance(EObject target) {
		Integer integer = allow.get(target.eResource().getResourceSet());
		return integer != null && integer.intValue() > 0;
	}

	@Override
	public void notifyInstanceChange(EObject instance) {
		// nothing to do here
	}

	@Override
	public void notifyNewInstanceCreation(EObject instance) {
		// nothing to do here
	}

	@Override
	public void notifyInstanceDeletion(EObject instance) {
		// nothing to do here
	}

	@Override
	public void setReportIssues(boolean report) {
		// nothing to do here
	}

	@Override
	public void notifyLock(Collection<? extends EObject> elements) {
		for (IAuthorityListener listener : listeners) {
			listener.notifyIsLocked((Collection<EObject>) elements);
		}
	}

	@Override
	public void notifyUnlock(Collection<? extends EObject> elements) {
		for (IAuthorityListener listener : listeners) {
			listener.notifyIsReleased((Collection<EObject>) elements);
		}
	}

	@Override
	public LockStatus getLockStatus(EObject element) {
		return LockStatus.NOT_LOCKED;
	}

}
