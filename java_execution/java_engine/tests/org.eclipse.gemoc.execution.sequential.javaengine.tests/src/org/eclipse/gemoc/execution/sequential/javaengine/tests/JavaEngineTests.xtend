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
package org.eclipse.gemoc.execution.sequential.javaengine.tests

import org.eclipse.gemoc.execution.sequential.javaengine.tests.languages.LegacyFSM
import org.eclipse.gemoc.execution.sequential.javaengine.tests.languages.TFSM
import org.eclipse.gemoc.execution.sequential.javaengine.tests.wrapper.JavaEngineWrapper
import org.eclipse.gemoc.executionframework.test.lib.impl.TestHelper
import org.eclipse.gemoc.executionframework.test.lib.impl.TestModel
import org.junit.Test

class JavaEngineTests {

	public static val tfsmModelsPlugin = "org.eclipse.gemoc.sample.tfsm.sequential.single_traffic_light_sample"
	public static val legacyFsmModelsPlugin = "org.eclipse.gemoc.sample.legacyfsm.model_examples"

	@Test
	def void testTFSM() {
		TestHelper::testWithoutExtraAddons(new JavaEngineWrapper(), new TFSM(),
			new TestModel(tfsmModelsPlugin, "/", "single_traffic_light.xtfsm", "",""))
	}

	@Test
	def void testTFSMGenericTrace() {
		TestHelper::testWithGenericTrace(new JavaEngineWrapper(), new TFSM(),
			new TestModel(tfsmModelsPlugin, "/", "single_traffic_light.xtfsm", "",""))
	}

	@Test
	def void testLegacyFSM() {
		TestHelper::testWithGenericTrace(new JavaEngineWrapper(), new LegacyFSM(),
			new TestModel(legacyFsmModelsPlugin, "/", "BitShifting.fsm", "000101010","?lang=org.eclipse.gemoc.sample.legacyfsm.xsfsm.XSFSM"))
	}

	@Test
	def void testLegacyFSMGenericTrace() {
		TestHelper::testWithoutExtraAddons(new JavaEngineWrapper(), new LegacyFSM(),
			new TestModel(legacyFsmModelsPlugin, "/", "BitShifting.fsm", "000101010","?lang=org.eclipse.gemoc.sample.legacyfsm.xsfsm.XSFSM"))
	}

}
