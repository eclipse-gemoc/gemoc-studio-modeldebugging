/*******************************************************************************
 * Copyright (c) 2017 Inria and Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Inria - initial API and implementation
 *******************************************************************************/
package org.eclipse.gemoc.execution.sequential.javaxdsml.languagecreation.system.tests;

import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.gemoc.xdsmlframework.ide.ui.XDSMLFrameworkUI;
import org.eclipse.gemoc.xdsmlframework.test.lib.WorkspaceTestHelper;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swtbot.eclipse.finder.SWTWorkbenchBot;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.results.VoidResult;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.WorkbenchException;
import org.eclipse.xtext.ui.testing.util.IResourcesSetupUtil;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

/**
 * This class tests that we can create a single Sequential language using
 * 
 * The tests are run in fixed order so it provides a better feed back in case of
 * trouble
 */
@RunWith(SWTBotJunit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CreateSingleSequentialLanguageFromOfficialFSM_old_Test {

	WorkspaceTestHelper helper = new WorkspaceTestHelper();

	private static SWTWorkbenchBot bot;
	EPartService t;

	static final String BASE_FOLDER_NAME = "tests-inputs-gen/SequentialFSM";
	static final String BASE_NAME = "org.eclipse.gemoc.sample.legacyfsm";
	static final String SOURCE_PROJECT_NAME = BASE_NAME + ".fsm";
	static final String PROJECT_NAME = BASE_NAME + ".xfsm";

	@BeforeClass
	public static void beforeClass() throws Exception {

		bot = new SWTWorkbenchBot();

		IResourcesSetupUtil.cleanWorkspace();

		// Close Welcome page if present
		for (SWTBotView view : bot.views()) {
			if (view.getTitle().equals("Welcome")) {
				view.close();
			}
		}

		// Display Console view
		Display.getDefault().asyncExec(new Runnable() {
			@Override
			public void run() {
				try {
					IWorkbench workbench = PlatformUI.getWorkbench();
					workbench.showPerspective("org.eclipse.sirius.ui.tools.perspective.modeling",
							workbench.getActiveWorkbenchWindow());
					workbench.getActiveWorkbenchWindow().getActivePage().showView("org.eclipse.ui.console.ConsoleView");
				} catch (WorkbenchException e) {
					e.printStackTrace();
				}
			}
		});

	}

	@Before
	public void setUp() throws Exception {
		UIThreadRunnable.syncExec(new VoidResult() {
			public void run() {
				PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell().forceActive();
			}
		});
		helper.setTargetPlatform();
		if (!helper.projectExists(SOURCE_PROJECT_NAME + ".model")) {
			helper.init();
			// try to respect build order in order to ease compilation, this will speed up
			// the test
			helper.deployProject(SOURCE_PROJECT_NAME + ".model",
					BASE_FOLDER_NAME + "/" + SOURCE_PROJECT_NAME + ".model.zip");
			helper.deployProject(SOURCE_PROJECT_NAME + ".k3dsa",
					BASE_FOLDER_NAME + "/" + SOURCE_PROJECT_NAME + ".k3dsa.zip");

			IResourcesSetupUtil.reallyWaitForAutoBuild();
		}
	}

	@Test
	public void test01OpenXDSMLPerspective() throws Exception {
		bot.perspectiveById(XDSMLFrameworkUI.ID_PERSPECTIVE).activate();
		helper.assertContains("Menu does not contain", "GEMOC Sequential xDSML Project",
				bot.menu("File").menu("New").menuItems());
		helper.assertContains("Menu does not contain", "K3 Project", bot.menu("File").menu("New").menuItems());
		helper.assertContains("Menu does not contain", "Ecore Modeling Project",
				bot.menu("File").menu("New").menuItems());

	}

	/**
	 * Create a Sequential language using provided ecore project and k3dsa and the
	 * basic template that creates a single melange language. The projects uses
	 * parameters so it fits a naming convention that minimizes the number of
	 * generated projects (ie. the language runtime will go in the same project)
	 * 
	 * @result Project will be created without any errors, workspace must not report
	 *         any error after a full build
	 */
	@Test
	public void test02CreateSequentialLanguageProject() throws Exception {

		bot.perspectiveById(XDSMLFrameworkUI.ID_PERSPECTIVE).activate();
		bot.menu("File").menu("New").menu("GEMOC Sequential xDSML Project").click();
		bot.text().setText(PROJECT_NAME);
		bot.button("Next >").click();
		bot.button("Next >").click();
		bot.textWithLabel("&Package name(*)").setText(BASE_NAME);
		
		// due to a bug in swtbot I can't use the browse , so set it directly in the text
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
		
		
		bot.button("Finish").click();

		helper.assertProjectExists(PROJECT_NAME);

		IResourcesSetupUtil.reallyWaitForAutoBuild();
		helper.assertNoMarkers();
	}

	/**
	 * based on test02CreateSequentialLanguageProject() call melange to generate all
	 * 
	 * @result Runtime language project and other files will be created without any
	 *         errors, workspace must not report any error after a full build
	 * @throws Exception
	 */
	@Test
	public void test03GenerateAllMelangeAtifacts() throws Exception {
		bot.viewByTitle("Project Explorer").setFocus();
		bot.tree().getTreeItem(PROJECT_NAME).expand();
		bot.tree().getTreeItem(PROJECT_NAME).getNode("src").expand();
		bot.tree().getTreeItem(PROJECT_NAME).getNode("src").getNode(PROJECT_NAME).expand();
		SWTBotTreeItem melangeFileItem = bot.tree().getTreeItem(PROJECT_NAME).getNode("src").getNode(PROJECT_NAME)
				.getNode("Xfsm.melange").select();
		melangeFileItem.contextMenu("Melange").menu("Generate All").click();

		IResourcesSetupUtil.reallyWaitForAutoBuild();

		// if the package name is correct all the files are created in the current
		// language project
		// Language runtime classes
		helper.assertFileExists(
				PROJECT_NAME + "/src-model-gen/org/eclipse/gemoc/sample/legacyfsm/xfsm/fsm/FsmPackage.java");
		// ModelType classes
		helper.assertFileExists(PROJECT_NAME + "/src-gen/org/eclipse/gemoc/sample/legacyfsm/Xfsm.java");
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
	public void test04CreateTraceAddon() throws Exception {
		SWTBotTreeItem projectItem = bot.tree().getTreeItem(PROJECT_NAME).select();
		projectItem.contextMenu("GEMOC Language").menu("Generate Multidimensional Trace Addon project for language")
				.click();
		bot.button("OK").click();
		bot.button("OK").click();

		IResourcesSetupUtil.reallyWaitForAutoBuild();

		helper.assertProjectExists(PROJECT_NAME + ".trace");

		helper.assertNoMarkers();
	}
	
	/**
	 * This test use the GEMOC menu to create a Sirius editor for a language
	 * @throws Exception
	 */
	@Test
	public void test05CreateSiriusEditorForLanguage() throws Exception {
		
		SWTBotTreeItem projectItem = bot.tree().getTreeItem("org.eclipse.gemoc.sample.legacyfsm.xfsm").select();
		projectItem.contextMenu("GEMOC Language").menu("Create Sirius Editor Project for language").click();
		bot.button("OK").click();
		
		helper.assertProjectExists(PROJECT_NAME + ".design");
		
		bot.editorByTitle("xfsm.odesign").show();
		bot.tree().getTreeItem("platform:/resource/"+PROJECT_NAME+".design/description/xfsm.odesign").expand();
		// TODO recreate a basic representation in the default layer
		

		helper.assertNoMarkers();
	}
	
	/**
	 * This test creates a sirius editor the classic Viewpoint specification Project wizard 
	 * @throws Exception
	 */
	@Test
	public void test06CreateSiriusViewpointSpecificationProject() throws Exception {
		bot.tree().getTreeItem(PROJECT_NAME).select();
		bot.menu("File").menu("New").menu("Viewpoint Specification Project").click();
		bot.textWithLabel("&Project name:").setText(PROJECT_NAME + ".design2");
		bot.button("Next >").click();
		bot.button("Finish").click();

		helper.assertProjectExists(PROJECT_NAME + ".design");
		
		bot.editorByTitle("xfsm.odesign").show();
		bot.tree().getTreeItem("platform:/resource/"+PROJECT_NAME+".design/description/xfsm.odesign").expand();
		// TODO recreate a basic representation in the default layer
		

		helper.assertNoMarkers();
	}
	
	/**
	 * This test suppose that we have an existing odesign and will populate it with a new debug layer
	 * with the associated java services
	 * @throws Exception
	 */
	@Test
	public void test07ExtendSiriusEditorIntoAnimator() throws Exception {
		SWTBotTreeItem projectItem = bot.tree().getTreeItem("org.eclipse.gemoc.sample.legacyfsm.xfsm").select();
		projectItem.contextMenu("GEMOC Language").menu("Create Sirius Editor Project for language").click();
		bot.button("OK").click();
		SWTBotTreeItem selection = bot.tree().getTreeItem("org.eclipse.gemoc.sample.legacyfsm.xfsm").select();
		selection.contextMenu("GEMOC Language").menu("Create Animator Project for language").click();
		bot.button("OK").click();
		bot.button("Next >").click();
		bot.button("Finish").click();
		
		//helper.assertProjectExists(PROJECT_NAME + ".design");
		// TODO find a way to assert the result
		
		helper.assertNoMarkers();
	}
	

}
