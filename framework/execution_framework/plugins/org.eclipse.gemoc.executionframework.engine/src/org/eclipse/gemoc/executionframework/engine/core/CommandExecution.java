/*******************************************************************************
 * Copyright (c) 2016 Inria and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Inria - initial API and implementation
 *******************************************************************************/
package org.eclipse.gemoc.executionframework.engine.core;

import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gemoc.xdsmlframework.api.core.IExecutionCheckpoint;

public final class CommandExecution {

	private CommandExecution() {
	}

	public static Object execute(TransactionalEditingDomain editingDomain, RecordingCommand command) {
		final CommandStack commandStack = editingDomain.getCommandStack();
		ResourceSet rs = editingDomain.getResourceSet();
		IExecutionCheckpoint checkpoint = IExecutionCheckpoint.CHECKPOINTS.get(rs);
		Object result = null;
		try {
			if (checkpoint != null) {
				checkpoint.allow(rs, true);
			}
			commandStack.execute(command);
			if (command.getResult() != null && command.getResult().size() == 1) {
				result = command.getResult().iterator().next();
			}
		} finally {
			if (checkpoint != null) {
				checkpoint.allow(rs, false);
			}
		}
		return result;
	}

}
