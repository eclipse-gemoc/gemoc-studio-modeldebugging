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
package org.eclipse.gemoc.execution.sequential.javaxdsml.tests

import com.google.inject.Inject
import org.eclipse.core.resources.IProject
import org.eclipse.xtext.junit4.AbstractXtextTests
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.eclipse.xtext.junit4.ui.util.IResourcesSetupUtil
import org.junit.After
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters
import org.eclipse.gemoc.xdsmlframework.test.lib.WorkspaceTestHelper
import org.eclipse.gemoc.xdsmlframework.test.lib.MelangeUiInjectorProvider

@RunWith(XtextRunner)
@InjectWith(MelangeUiInjectorProvider)
@FixMethodOrder(MethodSorters::NAME_ASCENDING)
public class SequentialFsmTest extends AbstractXtextTests
{
	@Inject WorkspaceTestHelper helper
	IProject melangeProject
	IProject melangeProject2
	static final String BASE_FOLDER_NAME = "tests-inputs-gen/SequentialFSM"
	static final String BASE_PROJECT_NAME = "org.gemoc.sample.legacyfsm"
	static final String PROJECT_NAME = BASE_PROJECT_NAME+".fsm"
	static final String MELANGE_FILE = PROJECT_NAME+"/src/org/gemoc/sample/legacyfsm/fsm/FSM.melange"
	static final String PROJECT_NAME2 = BASE_PROJECT_NAME+".xsfsm"
	static final String MELANGE_FILE2 = PROJECT_NAME2+"/src/org/gemoc/sample/legacyfsm/xsfsm/language/XSFSM.melange"
	
	@Before
	override setUp() {
		helper.setTargetPlatform
		if (!helper.projectExists(PROJECT_NAME)) {
			super.setUp
			helper.init
			IResourcesSetupUtil::cleanWorkspace
			
			// try to respect build order in order to ease compilation, this will speed up the test
			helper.deployProject(PROJECT_NAME+".model",BASE_FOLDER_NAME+"/"+PROJECT_NAME+".model.zip")
			helper.deployProject(PROJECT_NAME+".k3dsa",BASE_FOLDER_NAME+"/"+PROJECT_NAME+".k3dsa.zip")
			melangeProject = helper.deployProject(PROJECT_NAME,BASE_FOLDER_NAME+"/"+PROJECT_NAME+".zip")
			helper.deployProject(PROJECT_NAME+".design",BASE_FOLDER_NAME+"/"+PROJECT_NAME+".design.zip")
			helper.deployProject(PROJECT_NAME+".model.edit",BASE_FOLDER_NAME+"/"+PROJECT_NAME+".model.edit.zip")
			helper.deployProject(PROJECT_NAME+".model.editor",BASE_FOLDER_NAME+"/"+PROJECT_NAME+".model.editor.zip")
			
			
			melangeProject2 = helper.deployProject(PROJECT_NAME2,BASE_FOLDER_NAME+"/"+PROJECT_NAME2+".zip")
			helper.deployProject(PROJECT_NAME2+".design",BASE_FOLDER_NAME+"/"+PROJECT_NAME2+".design.zip")
			
			IResourcesSetupUtil::reallyWaitForAutoBuild
			helper.cleanAll(MELANGE_FILE)
			helper.cleanAll(MELANGE_FILE2)
			IResourcesSetupUtil::reallyWaitForAutoBuild
			helper.openEditor(MELANGE_FILE)
			helper.openEditor(MELANGE_FILE2)
		} else {
			melangeProject = helper.getProject(PROJECT_NAME)
		}
	}

	@After
	override tearDown() {
		// Nope
	}
	
	@Test
	def void test01GenerateAllMelange_NoErrorsInWorkspace() {
		helper.generateAll(MELANGE_FILE)
		helper.generateAll(MELANGE_FILE2)
		IResourcesSetupUtil::reallyWaitForAutoBuild
		helper.assertNoMarkers
		
		helper.assertProjectExists(PROJECT_NAME)
		helper.assertProjectExists(PROJECT_NAME2)
		helper.assertProjectExists(PROJECT_NAME2+".xsfsm")
	}	
	
	@Test
	def void test03GenerateTrace_NoErrorsInWorkspace() {
		helper.generateTrace(MELANGE_FILE2, "XSFSM", PROJECT_NAME2+".trace")
		IResourcesSetupUtil::reallyWaitForAutoBuild
		helper.assertNoMarkers
		
		helper.assertProjectExists(PROJECT_NAME2+".trace")
	}
	
}
