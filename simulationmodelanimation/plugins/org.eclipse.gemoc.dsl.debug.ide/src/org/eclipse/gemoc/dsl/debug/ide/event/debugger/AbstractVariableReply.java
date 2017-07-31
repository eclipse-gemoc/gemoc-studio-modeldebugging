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
package org.eclipse.gemoc.dsl.debug.ide.event.debugger;

/**
 * A {@link org.eclipse.gemoc.dsl.debug.Varaible variable} contextual {@link IDSLDebuggerReply reply}.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public abstract class AbstractVariableReply extends AbstractThreadReply {

	/**
	 * The {@link org.eclipse.gemoc.dsl.debug.StackFrame#getName() stack frame name}.
	 */
	private final String stackName;

	/**
	 * The variable name.
	 */
	private final String variableName;

	/**
	 * Constructor for {@link org.eclipse.gemoc.dsl.debug.Thread Thread}.
	 * 
	 * @param threadName
	 *            the {@link org.eclipse.gemoc.dsl.debug.Thread#getName() thread name}
	 * @param stackName
	 *            the {@link org.eclipse.gemoc.dsl.debug.StackFrame#getName() stack frame name}
	 * @param variableName
	 *            the {@link org.eclipse.gemoc.dsl.debug.Variable#getName() variable name}
	 */
	public AbstractVariableReply(String threadName, String stackName, String variableName) {
		super(threadName);
		this.stackName = stackName;
		this.variableName = variableName;
	}

	/**
	 * Gets the {@link org.eclipse.gemoc.dsl.debug.StackFrame#getName() stack frame name}.
	 * 
	 * @return the {@link org.eclipse.gemoc.dsl.debug.StackFrame#getName() stack frame name}
	 */
	public String getStackName() {
		return stackName;
	}

	/**
	 * Gets the {@link org.eclipse.gemoc.dsl.debug.Variable#getName() variable name}.
	 * 
	 * @return the {@link org.eclipse.gemoc.dsl.debug.Variable#getName() variable name}
	 */
	public String getVariableName() {
		return variableName;
	}

}
