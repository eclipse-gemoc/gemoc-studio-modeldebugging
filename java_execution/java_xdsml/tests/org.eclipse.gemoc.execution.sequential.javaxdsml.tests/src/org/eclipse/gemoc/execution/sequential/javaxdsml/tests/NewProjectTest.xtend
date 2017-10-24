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
import org.eclipse.xtext.junit4.ui.util.IResourcesSetupUtil
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.BeforeClass
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters
import org.eclipse.gemoc.execution.sequential.javaxdsml.api.extensions.languages.SequentialLanguageDefinitionExtensionPoint

import static org.eclipse.swtbot.swt.finder.matchers.WidgetMatcherFactory.*
import java.util.jar.Manifest
import org.eclipse.core.runtime.NullProgressMonitor
import org.eclipse.gemoc.xdsmlframework.ide.ui.builder.pde.PluginXMLHelper

/**
 * This class check the result of the GEMOC Sequential xDSML project wizard
 */
@RunWith(SWTBotJunit4ClassRunner)
@InjectWith(MelangeUiInjectorProvider)
@FixMethodOrder(MethodSorters::NAME_ASCENDING)
public class NewProjectTest extends AbstractXtextTests
{
	
	static WorkspaceTestHelper helper = new WorkspaceTestHelper
	
	static final String BASE_FOLDER_NAME = "tests-inputs-gen/SequentialFSM"
	static final String BASE_PROJECT_NAME = "org.gemoc.sample.legacyfsm"
	static final String PROJECT_NAME = BASE_PROJECT_NAME+".fsm"
	
	private static SWTWorkbenchBot	bot;
 
	@BeforeClass
	def static void beforeClass() throws Exception {
		bot = new SWTWorkbenchBot()
		IResourcesSetupUtil::cleanWorkspace
		helper.init
		helper.deployProject(PROJECT_NAME+".model",BASE_FOLDER_NAME+"/"+PROJECT_NAME+".model.zip")
		helper.deployProject(PROJECT_NAME+".k3dsa",BASE_FOLDER_NAME+"/"+PROJECT_NAME+".k3dsa.zip")
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
	def void test0_createNewProject() {
		
		IResourcesSetupUtil::reallyWaitForAutoBuild
		
		bot.menu("File").menu("New").menu("Project...").click();
		bot.tree().getTreeItem("GEMOC Language").expand();
		bot.tree().getTreeItem("GEMOC Language").getNode("GEMOC Sequential xDSML Project").select();
		bot.button("Next >").click();
		bot.button("Next >").click();
		bot.button("Next >").click();
		
		val activeShell = bot.activeShell // the focus is lost after click on "Browse..."
		
		val matcher = allOf(widgetOfType(Button), withText("Browse..."))
		val ecoreBrowse = new SWTBotButton(bot.widget(matcher,0) as Button);
		val k3Browse = new SWTBotButton(bot.widget(matcher,1) as Button);

		ecoreBrowse.click();
		bot.tree().getTreeItem("org.gemoc.sample.legacyfsm.fsm.model").expand();
		bot.tree().getTreeItem("org.gemoc.sample.legacyfsm.fsm.model").getNode("model").expand();
		bot.tree().getTreeItem("org.gemoc.sample.legacyfsm.fsm.model").getNode("model").getNode("fsm.ecore").select();
		bot.button("OK").click();
		k3Browse.click();
		bot.button("OK").click();

		activeShell.bot.button("Finish").click()
	}
	
	
	@Test
	def void test1_HasNewProject() {
		IResourcesSetupUtil::reallyWaitForAutoBuild
		val projects = ResourcesPlugin.workspace.root.projects
		assertEquals(3,projects.size)
		helper.assertProjectExists("org.gemoc.sample.legacyfsm.fsm.model")
		helper.assertProjectExists("org.gemoc.sample.legacyfsm.fsm.k3dsa")
		helper.assertProjectExists("org.company.mySequentialLanguage")
	}
	
	@Test
	def void test2_ProjectContent() {
		helper.assertFileExists("org.company.mySequentialLanguage/src/org/company/mysequentiallanguage/MySequentialLanguage.dsl");
		helper.assertFileExists("org.company.mySequentialLanguage/META-INF/MANIFEST.MF");
		helper.assertFileExists("org.company.mySequentialLanguage/plugin.xml");
	}
	
	@Test
	def void test3_ProjectNatures() {
		val projects = ResourcesPlugin.workspace.root.projects
		val project = projects.findFirst[project | project.name == "org.company.mySequentialLanguage"]
		assertTrue(project.hasNature("org.eclipse.gemoc.execution.sequential.javaxdsml.ide.ui.GemocSequentialLanguageNature"))
		assertTrue(project.hasNature("org.eclipse.xtext.ui.shared.xtextNature"))
	}
	
	@Test
	def void test4_NoError() {
		IResourcesSetupUtil::reallyWaitForAutoBuild
		ResourcesPlugin::workspace.root.projects.forEach[project |
			project.findMarkers(IMarker::PROBLEM, true, IResource::DEPTH_INFINITE).forEach[m|
				println('''Found marker «m.getAttribute(IMarker::MESSAGE)» («m.getAttribute(IMarker::SEVERITY)»)''')
				Assert.assertFalse(
					"Unexpected marker: " + m.getAttribute(IMarker::MESSAGE) + " on "+m.resource.fullPath,
					m.getAttribute(IMarker::SEVERITY) == IMarker::SEVERITY_ERROR
				)
				
			]
		]
	}
	
	
	@Test
	def void test5_DslFileContent() {
		val ws = ResourcesPlugin::workspace
		val dslFile = ws.root.getFile(new Path("org.company.mySequentialLanguage/src/org/company/mysequentiallanguage/MySequentialLanguage.dsl"))
		val buffer = new BufferedReader(new InputStreamReader(dslFile.contents))
		val dslString = buffer.lines().collect(Collectors.joining("\n"))

		val expected = 
		'''DSL org.company.mysequentiallanguage.MySequentialLanguage {
	
	abstract-syntax {
		ecore = "platform:/resource/org.gemoc.sample.legacyfsm.fsm.model/model/fsm.ecore"
	}
	
	semantic {
		k3 = "org.gemoc.sample.legacyfsm.fsm.k3dsa.StateAspect",
		     "org.gemoc.sample.legacyfsm.fsm.k3dsa.StateMachineAspect",
		     "org.gemoc.sample.legacyfsm.fsm.k3dsa.TransitionAspect"
	}
}'''

		assertEquals(expected,dslString)
	}
	
	@Test
	def void test6_NoErrorInDsl() {
		
		Display.^default.syncExec(
			new Runnable() {
				override run() {
					try {
						val ws = ResourcesPlugin::workspace
						val dslFile = ws.root.getFile(
							new Path(
								"org.company.mySequentialLanguage/src/org/company/mysequentiallanguage/MySequentialLanguage.dsl"))
						val page = PlatformUI.workbench.workbenchWindows.head.activePage
						val openEditor = page.openEditor(new FileEditorInput(dslFile), "org.eclipse.gemoc.Dsl");
					} catch (Exception e) {
						e.printStackTrace
						Assert.fail(e.message)
					}
				}
			})
		helper.assertNoMarkers
	}
	
	@Test
	def void test7_ManifestContent() {
		val projects = ResourcesPlugin.workspace.root.projects
		val project = projects.findFirst[project | project.name == "org.company.mySequentialLanguage"]
		val manifest =  new Manifest(project.getFile(new Path("META-INF/MANIFEST.MF")).getContents())
		val dependencies = manifest.mainAttributes.getValue("Require-Bundle")
		
		assertTrue(dependencies.contains("org.gemoc.sample.legacyfsm.fsm.model"))
		assertTrue(dependencies.contains("org.gemoc.sample.legacyfsm.fsm.k3dsa"))
	}
	
	@Test
	def void test7_PluginContent() {
		val projects = ResourcesPlugin.workspace.root.projects
		val project = projects.findFirst[project | project.name == "org.company.mySequentialLanguage"]
		val pluginXmlFile = project.getFile(PluginXMLHelper.PLUGIN_FILENAME);
		
		val PluginXMLHelper helper = new PluginXMLHelper();
		helper.loadDocument(pluginXmlFile);
		val elements = helper.getExtensionPoints(SequentialLanguageDefinitionExtensionPoint.GEMOC_SEQUENTIAL_LANGUAGE_EXTENSION_POINT)
		assertEquals(1, elements.size)
		
		assertEquals("org.company.mysequentiallanguage.MySequentialLanguage",helper.getXDSMLDefinitionAttribute(elements.head,"name"))
		assertEquals("platform:/plugin/org.company.mySequentialLanguage/src/org/company/mysequentiallanguage/MySequentialLanguage.dsl",helper.getXDSMLDefinitionAttribute(elements.head,"xdsmlFilePath"))
		assertEquals("org.eclipse.gemoc.executionframework.extensions.sirius.modelloader.DefaultModelLoader",helper.getXDSMLDefinitionAttribute(elements.head,"modelLoader_class"))
	}
}