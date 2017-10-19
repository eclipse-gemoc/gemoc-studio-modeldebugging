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
package org.eclipse.gemoc.execution.sequential.javaengine.tests.languages

import org.eclipse.gemoc.executionframework.test.lib.ILanguageWrapper

class LegacyFSM implements ILanguageWrapper {
	
	override getEntryPoint() {
		"public static void org.eclipse.gemoc.sample.legacyfsm.xsfsm.xsfsm.aspects.StateMachineAspect.main(org.eclipse.gemoc.sample.legacyfsm.xsfsm.xsfsm.fsm.StateMachine)"
	}
	
	override getLanguageName() {
		"org.eclipse.gemoc.sample.legacyfsm.xsfsm.XSFSM"
	}
	
	override getInitializationMethod() {
		"org.eclipse.gemoc.sample.legacyfsm.xsfsm.xsfsm.aspects.StateMachineAspect.initializeModel"
	}
	
	
	
}
