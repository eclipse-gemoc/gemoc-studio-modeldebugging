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
package org.eclipse.gemoc.execution.sequential.javaxdsml.languagecreation.system.tests

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.stream.Collectors
import org.eclipse.core.resources.IMarker
import org.eclipse.core.resources.IResource
import org.eclipse.core.resources.ResourcesPlugin
import org.eclipse.core.runtime.Path
import org.eclipse.gemoc.xdsmlframework.test.lib.MelangeUiInjectorProvider
import org.eclipse.gemoc.xdsmlframework.test.lib.WorkspaceTestHelper
import org.eclipse.swt.widgets.Button
import org.eclipse.swt.widgets.Display
import org.eclipse.swtbot.eclipse.finder.SWTWorkbenchBot
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner
import org.eclipse.swtbot.swt.finder.widgets.SWTBotButton
import org.eclipse.ui.PlatformUI
import org.eclipse.ui.part.FileEditorInput
import org.eclipse.xtext.junit4.AbstractXtextTests
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.ui.testing.util.IResourcesSetupUtil
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.BeforeClass
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters

import static org.eclipse.swtbot.swt.finder.matchers.WidgetMatcherFactory.*
import java.util.jar.Manifest
import org.eclipse.core.runtime.NullProgressMonitor
import org.eclipse.gemoc.xdsmlframework.ide.ui.builder.pde.PluginXMLHelper
import org.eclipse.gemoc.xdsmlframework.ide.ui.XDSMLFrameworkUI
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem
import org.eclipse.core.resources.IWorkspaceRunnable
import org.eclipse.core.runtime.IProgressMonitor
import org.eclipse.core.runtime.CoreException
import org.eclipse.core.resources.IProject

/**
 * This class check the result of the GEMOC Sequential xDSML project wizard
 */
@RunWith(SWTBotJunit4ClassRunner)
@InjectWith(MelangeUiInjectorProvider)
@FixMethodOrder(MethodSorters::NAME_ASCENDING)
public class CreateSingleSequentialLanguageFromOfficialFSM_Test extends AbstractXtextTests
{
	
	static WorkspaceTestHelper helper = new WorkspaceTestHelper
	

	static final String BASE_FOLDER_NAME = "tests-inputs-gen/SequentialFSM"
	static final String BASE_NAME = "org.eclipse.gemoc.sample.legacyfsm"
	static final String SOURCE_PROJECT_NAME = BASE_NAME + ".fsm"
	static final String PROJECT_NAME = BASE_NAME + ".xfsm"
	
	private static SWTWorkbenchBot	bot;
 
	@BeforeClass
	def static void beforeClass() throws Exception {
		bot = new SWTWorkbenchBot()
		IResourcesSetupUtil::cleanWorkspace
		helper.init
		helper.deployProject(SOURCE_PROJECT_NAME+".model",BASE_FOLDER_NAME+"/"+SOURCE_PROJECT_NAME+".model.zip")
		helper.deployProject(SOURCE_PROJECT_NAME+".k3dsa",BASE_FOLDER_NAME+"/"+SOURCE_PROJECT_NAME+".k3dsa.zip")
	}
	
	@Before
	override setUp() {
		// Nope
	}
	
	@After
	override tearDown() {
		// Nope
	}
	
	@Test
	def void test01_OpenXDSMLPerspective() throws Exception {
		bot.perspectiveById(XDSMLFrameworkUI.ID_PERSPECTIVE).activate()
		helper.assertContains("Menu does not contain", "GEMOC Sequential xDSML Project",
				bot.menu("File").menu("New").menuItems())

	}
	
	@Test
	def void test02_CreateSequentialLanguageProject() {
		
		IResourcesSetupUtil::reallyWaitForAutoBuild
		bot.menu("File").menu("New").menu("GEMOC Sequential xDSML Project").click();
		bot.text().setText(PROJECT_NAME);
		bot.button("Next >").click();
		bot.button("Next >").click();
		bot.textWithLabel("&Package name(*)").setText(BASE_NAME);
				
		val activeShell = bot.activeShell // the focus is lost after click on "Browse..."
		bot.button("Browse...",0).click();
		//bot.shell("File Selection").activate();
		bot.tree().getTreeItem(SOURCE_PROJECT_NAME + ".model").expand();
		bot.tree().getTreeItem(SOURCE_PROJECT_NAME + ".model").getNode("model").expand();
		bot.tree().getTreeItem(SOURCE_PROJECT_NAME + ".model").getNode("model").getNode("fsm.ecore").click();
		bot.button("OK").click();
		bot.button("Browse...",1).click();
		bot.button("OK").click();
		
		/* or 
		bot.textWithLabel("&Ecore file location").setText(SOURCE_PROJECT_NAME + ".model/model/fsm.ecore");
		bot.textWithLabel("K3 DSA Project name").setText(SOURCE_PROJECT_NAME + ".k3dsa");
		*/
		
		activeShell.bot.button("Finish").click()
		//bot.button("Finish").click();

		helper.assertProjectExists(PROJECT_NAME);

		IResourcesSetupUtil.reallyWaitForAutoBuild();
		helper.assertNoMarkers();
		
		
		/*
		val matcher = allOf(widgetOfType(Button), withText("Browse..."))
		val ecoreBrowse = new SWTBotButton(bot.widget(matcher,0) as Button);
		val k3Browse = new SWTBotButton(bot.widget(matcher,1) as Button);

		ecoreBrowse.click();
		bot.tree().getTreeItem("org.eclipse.gemoc.sample.legacyfsm.fsm.model").expand();
		bot.tree().getTreeItem("org.eclipse.gemoc.sample.legacyfsm.fsm.model").getNode("model").expand();
		bot.tree().getTreeItem("org.eclipse.gemoc.sample.legacyfsm.fsm.model").getNode("model").getNode("fsm.ecore").select();
		bot.button("OK").click();
		k3Browse.click();
		bot.button("OK").click();
		
		*/

		
	}
	
	/**
	 * based on test02CreateSequentialLanguageProject() call melange to generate all
	 * 
	 * @result Runtime language project and other files will be created without any
	 *         errors, workspace must not report any error after a full build
	 * @throws Exception
	 */
	@Test
	def void test03_GenerateAllMelangeArtifacts() throws Exception {
		
		val projExplorerBot = bot.viewByTitle("Project Explorer").bot
		//bot.viewByTitle("Project Explorer").
		projExplorerBot.tree().getTreeItem(PROJECT_NAME).expand();
		projExplorerBot.tree().getTreeItem(PROJECT_NAME).getNode("src").expand();
		projExplorerBot.tree().getTreeItem(PROJECT_NAME).getNode("src").getNode(BASE_NAME).expand();
		val SWTBotTreeItem melangeFileItem = bot.tree().getTreeItem(PROJECT_NAME).getNode("src").getNode(BASE_NAME)
				.getNode("Xfsm.melange").select();
		melangeFileItem.contextMenu("Melange").menu("Generate All").click();

		// Melange "Generate all is a bit special as it trigger several jobs one after the other
		// retry in order to make sure they all have been done 
		WorkspaceTestHelper::reallyWaitForJobs(50)
		IResourcesSetupUtil::reallyWaitForAutoBuild
		
		// if the package name is correct all the files are created in the current project
		// Language runtime classes
		helper.waitFileExistOrAssert(PROJECT_NAME + "/src-model-gen/org/eclipse/gemoc/sample/legacyfsm/xfsm/fsm/FsmPackage.java", 10, 300)
		// ModelType classes
		helper.assertFileExists("org.eclipse.gemoc.sample.legacyfsm.xfsm/src-gen/org/eclipse/gemoc/sample/legacyfsm/Xfsm.java")
		helper.assertFolderExists(PROJECT_NAME + "/src-gen")
		helper.assertFileExists(PROJECT_NAME + "/src-gen/org/eclipse/gemoc/sample/legacyfsm/XfsmMT.java");
		// k3 aspects
		helper.assertFileExists(
				PROJECT_NAME + "/src-gen/org/eclipse/gemoc/sample/legacyfsm/xfsm/aspects/StateMachineAspect.java");
		// ecore files
		helper.assertFileExists(PROJECT_NAME + "/model-gen/Xfsm.dsl");
		helper.assertFileExists(PROJECT_NAME + "/model-gen/Xfsm.ecore");
		helper.assertFileExists(PROJECT_NAME + "/model-gen/Xfsm.genmodel");
		helper.assertFileExists(PROJECT_NAME + "/model-gen/XfsmMT.ecore");
		
		helper.assertNoMarkers();
	}
	
	@Test
	def void test04_CreateTraceAddon() throws Exception {
		val projExplorerBot = bot.viewByTitle("Project Explorer").bot
		val SWTBotTreeItem projectItem = projExplorerBot.tree().getTreeItem(PROJECT_NAME).select();
		projectItem.contextMenu("GEMOC Language").menu("Generate Multidimensional Trace Addon project for language")
				.click();
		bot.button("OK").click();
		bot.button("OK").click();

		IResourcesSetupUtil::reallyWaitForAutoBuild
		WorkspaceTestHelper::waitForJobs

		helper.assertProjectExists(PROJECT_NAME + ".trace");

		helper.assertNoMarkers();
	}

	/**
	 * This test use the GEMOC menu to create a Sirius editor for a language
	 * @throws Exception
	 */
	@Test
	def void test05CreateSiriusEditorForLanguage() throws Exception {
		
		val SWTBotTreeItem projectItem = bot.tree().getTreeItem("org.eclipse.gemoc.sample.legacyfsm.xfsm").select();
		projectItem.contextMenu("GEMOC Language").menu("Create Sirius Editor Project for language").click();
		bot.button("OK").click();
		
		helper.assertProjectExists(PROJECT_NAME + ".design");
		
		bot.editorByTitle("xfsm.odesign").show();
		bot.tree().getTreeItem("platform:/resource/"+PROJECT_NAME+".design/description/xfsm.odesign").expand();
		// TODO recreate a basic representation in the default layer
		

		helper.assertNoMarkers();
	}
	
}