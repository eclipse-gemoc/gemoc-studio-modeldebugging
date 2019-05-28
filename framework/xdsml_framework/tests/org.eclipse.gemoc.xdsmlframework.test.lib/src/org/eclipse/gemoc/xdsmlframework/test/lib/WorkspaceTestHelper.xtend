/*******************************************************************************
 * Copyright (c) 2017 Inria.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Inria - initial API and implementation
 *******************************************************************************/
package org.eclipse.gemoc.xdsmlframework.test.lib

import java.io.ByteArrayInputStream
import java.io.File
import java.net.URL
import java.net.URLClassLoader
import java.util.ArrayList
import java.util.HashSet
import java.util.List
import java.util.Set
import java.util.zip.ZipFile
import org.eclipse.core.expressions.IEvaluationContext
import org.eclipse.core.resources.IMarker
import org.eclipse.core.resources.IProject
import org.eclipse.core.resources.IResource
import org.eclipse.core.resources.ResourcesPlugin
import org.eclipse.core.runtime.NullProgressMonitor
import org.eclipse.core.runtime.Path
import org.eclipse.core.runtime.Platform
import org.eclipse.core.runtime.jobs.Job
import org.eclipse.debug.core.DebugPlugin
import org.eclipse.debug.core.ILaunchManager
import org.eclipse.debug.ui.IDebugUIConstants
import org.eclipse.gemoc.xdsmlframework.ide.ui.XDSMLFrameworkUI
import org.eclipse.jdt.core.IJavaProject
import org.eclipse.jdt.core.JavaCore
import org.eclipse.jdt.launching.IJavaLaunchConfigurationConstants
import org.eclipse.jdt.launching.JavaRuntime
import org.eclipse.jface.viewers.StructuredSelection
import org.eclipse.osgi.internal.framework.EquinoxBundle
import org.eclipse.osgi.storage.BundleInfo.Generation
import org.eclipse.pde.core.target.ITargetDefinition
import org.eclipse.pde.core.target.ITargetLocation
import org.eclipse.pde.core.target.ITargetPlatformService
import org.eclipse.pde.core.target.LoadTargetDefinitionJob
import org.eclipse.pde.internal.core.target.TargetPlatformService
import org.eclipse.swt.widgets.Display
import org.eclipse.ui.ISources
import org.eclipse.ui.IWindowListener
import org.eclipse.ui.IWorkbenchWindow
import org.eclipse.ui.PlatformUI
import org.eclipse.ui.commands.ICommandService
import org.eclipse.ui.dialogs.IOverwriteQuery
import org.eclipse.ui.handlers.IHandlerService
import org.eclipse.ui.internal.wizards.datatransfer.ZipLeveledStructureProvider
import org.eclipse.ui.wizards.datatransfer.ImportOperation
import org.eclipse.xtext.junit4.ui.util.IResourcesSetupUtil
import org.eclipse.xtext.junit4.ui.util.JavaProjectSetupUtil
import org.junit.Assert
import org.osgi.framework.Bundle

/**
 * Class containing helper methods for testing a workspace in a GEMOC Language workbench
 */
class WorkspaceTestHelper {
		
	public static final String CMD_PROJECT_CLEAN = "org.eclipse.ui.project.cleanAction"	
	
	
	/**
	 * Value to use by default for SWTBotPreferences.TIMEOUT 
	 * It must be used for any GEMOC test using swtbot 
	 * we should increase this value up to the value where we don't have any false failed tests
	 */ 
	public static final int SWTBotPreferencesTIMEOUT_4_GEMOC = 12000;
		/**
	 * Value to use by default for SWTBotPreferences.PLAYBACK_DELAY 
	 * It must be used for any GEMOC test using swtbot 
	 * we should increase this value up to the value where we don't have any false failed tests
	 */
	public static final int SWTBotPreferencesPLAYBACK_DELAY_4_GEMOC = 100;
	
	def void init() {
		
		Display.^default.syncExec(new Runnable(){
				override run() {
					PlatformUI::workbench.showPerspective(XDSMLFrameworkUI.ID_PERSPECTIVE, PlatformUI.workbench.activeWorkbenchWindow)
					closeWelcomePage
				}
			})
	}

	def IProject getProject(String projectName) {
		return ResourcesPlugin::workspace.root.getProject(projectName)
	}

	def boolean projectExists(String projectName) {
		return getProject(projectName).exists
	}


	IProject deployProjectResult = null;
	def IProject deployProject(String projectName, String zipLocation) {
		
		deployProjectResult = null
		val ArrayList<Throwable> thrownException = newArrayList()
		Display.^default.syncExec([
			try {
				val newProject = JavaProjectSetupUtil::createSimpleProject(projectName)
				
				val zip = new ZipFile(zipLocation)
				val structureProvider = new ZipLeveledStructureProvider(zip)
				val queryOverwrite = new IOverwriteQuery() {
					override queryOverwrite(String file) { return ALL }
				}
		
				new ImportOperation(
					newProject.project.fullPath,
					structureProvider.root,
					structureProvider,
					queryOverwrite
				).run(new NullProgressMonitor)
		
				zip.close
				deployProjectResult = newProject.project
				
			} catch (Exception e) { thrownException.add(e) }
		])
		thrownException.forall[e| throw new Exception(e)] // rethrown exception that was executed in the ui thread
		return deployProjectResult
	}
	

	def void closeWelcomePage() {
		if (PlatformUI.workbench.introManager.intro !== null) {
			PlatformUI.workbench.introManager.closeIntro(PlatformUI.workbench.introManager.intro)
		}
	}

	def void assertNoMarkers() {
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
	
	def void assertNoMarkers(String filename) {
		val mlgFile = ResourcesPlugin::workspace.root.getFile(new Path(filename))
		mlgFile.findMarkers(IMarker::PROBLEM, true, IResource::DEPTH_INFINITE).forEach[
				println('''Found marker «getAttribute(IMarker::MESSAGE)» («getAttribute(IMarker::SEVERITY)»)''')
				Assert.assertFalse(
					"Unexpected marker: " + getAttribute(IMarker::MESSAGE) + " on file "+filename,
					getAttribute(IMarker::SEVERITY) == IMarker::SEVERITY_ERROR
				)
			]
	}


	/**
	 * Usage : projectName/folder/file
	 */
	def void assertFileExists(String filename) {
		val ws = ResourcesPlugin::workspace
		Assert.assertTrue(
			"Cannot find file " + filename,
			ws.root.getFile(new Path(filename)).exists
		)
	}
	
	/**
	 * Usage : projectName/folder/file
	 */
	def void assertFileDontExists(String filename) {
		val ws = ResourcesPlugin::workspace
		Assert.assertFalse(
			"Found file " + filename,
			ws.root.getFile(new Path(filename)).exists
		)
	}
	
	/**
	 * Usage : projectName/folder
	 */
	def void assertFolderExists(String foldername) {
		val ws = ResourcesPlugin::workspace
		Assert.assertTrue(
			"Cannot find file " + foldername,
			ws.root.getFolder(new Path(foldername)).exists
		)
	}
	
	/**
	 * Check if {@link project} exist
	 */
	def void assertProjectExists(String project){
		val ws = ResourcesPlugin::workspace
		Assert.assertTrue(
			"Cannot find project " + project,
			ws.root.getProject(project).exists
		)
	}
	
	/**
	 * Check if {@link project} doesn't exist
	 */
	def void assertProjectDoesntExists(String project){
		val ws = ResourcesPlugin::workspace
		Assert.assertFalse(
			"Can find project " + project,
			ws.root.getProject(project).exists
		)
	}


	/** search in the given list if it contains the searchedString, report the error with a message*/
	def void assertContains(String baseMessage, String searchedString, List<String> list){
		Assert.assertTrue(baseMessage+" "+searchedString, list.contains(searchedString));
	}

	/**
	 * Creates and lauches a new run configuration for {@link project}
	 * using {@link mainClass} as the main Java class.
	 *
	 * @return the produced console output
	 */
	def String runMainClass(IProject project, String mainClass) {
		val outputFileName = "output.txt"
		val manager = DebugPlugin::getDefault.launchManager
		val type = manager.getLaunchConfigurationType(IJavaLaunchConfigurationConstants::ID_JAVA_APPLICATION)
		val newLaunchConfig = type.newInstance(project, "RunMainTransfo")
		newLaunchConfig.setAttribute(IJavaLaunchConfigurationConstants::ATTR_PROJECT_NAME, project.name)
		newLaunchConfig.setAttribute(IJavaLaunchConfigurationConstants::ATTR_MAIN_TYPE_NAME, mainClass)
		newLaunchConfig.setAttribute(IDebugUIConstants::ATTR_CAPTURE_IN_FILE, '''${workspace_loc:/«project.name»/«outputFileName»}''')
		newLaunchConfig.doSave

		val outputFile = project.getFile(outputFileName)
		if (!outputFile.exists)
			outputFile.create(new ByteArrayInputStream("".bytes), true, null)

		val launch = newLaunchConfig.launch(ILaunchManager::RUN_MODE, null)
		
		val long TIMEOUT = 1000 * 60 * 5 // 5 minutes
		val long startTime = System.currentTimeMillis();
		while (!launch.isTerminated && System.currentTimeMillis() < (startTime + TIMEOUT)) {
		    try {
		        Thread.sleep(50);
		    } catch (InterruptedException e) {
		    }
		}

		outputFile.refreshLocal(IResource::DEPTH_ONE, null)

		val bi = outputFile.contents
		
		val byte[] buffer = newByteArrayOfSize(bi.available() )
		/*val int bytesRead =*/ bi.read(buffer);
		val String out = new String(buffer);
		return out
	}


	
	
	def void debug(){
		ResourcesPlugin::workspace.root.projects.forEach[project |
	         println("Error markers: " + project.name)
	         project.findMarkers(IMarker::PROBLEM, true, IResource::DEPTH_INFINITE).forEach[ marker |
	             println("   Resource: " + marker.resource.name)
	             println("   Location: " + marker.getAttribute(IMarker::LOCATION))
	             println("   Message: " + marker.getAttribute(IMarker::MESSAGE) + "\n")
	         ]
	         
	         println("\n")
	         
	         println("Classpath: " + project.name)
		       val jProject = JavaCore.create(project)
		       val cp = new StringBuffer
		       jProject.getResolvedClasspath(false).forEach[entry|
		           cp.append(" "+entry.path.toString+"\n")
	       	]
			val str = cp.toString
			println(str)
	     ]
	}
	
	/**
	 * Sets a target platform in the test platform to get workspace builds OK
	 * with PDE
	 * 
	 * @throws Exception
	 */
	def void setTargetPlatform() throws Exception {
		val ITargetPlatformService tpService = TargetPlatformService.getDefault();
		val ITargetDefinition targetDef = tpService.newTarget();
		targetDef.setName("Tycho platform");
		val Bundle[] bundles = Platform.getBundle("org.eclipse.core.runtime").getBundleContext().getBundles();
		val List<ITargetLocation> bundleContainers = new ArrayList<ITargetLocation>();
		val Set<File> dirs = new HashSet<File>();
		for (Bundle bundle : bundles) {
			val EquinoxBundle bundleImpl = bundle as EquinoxBundle;
			val Generation generation = bundleImpl.getModule().getCurrentRevision().getRevisionInfo() as Generation;
			val File file = generation.getBundleFile().getBaseFile();
			val File folder = file.getParentFile();
			if (!dirs.contains(folder)) {
				dirs.add(folder);
				bundleContainers.add(tpService.newDirectoryLocation(folder.getAbsolutePath()));
			}
		}
		val ITargetLocation[] bundleContainersArray = bundleContainers
		targetDef.setTargetLocations(bundleContainersArray);
		targetDef.setArch(Platform.getOSArch());
		targetDef.setOS(Platform.getOS());
		targetDef.setWS(Platform.getWS());
		targetDef.setNL(Platform.getNL());
		// targetDef.setJREContainer()
		tpService.saveTargetDefinition(targetDef);

		val Job job = new LoadTargetDefinitionJob(targetDef);
		job.schedule();
		job.join();
	}
	

	
	
	/**
	 * call a command the selection file, if file is null or empty it will call the command without selection
	 */
	def static void invokeCommandOnSelectedFile(String commandId, String file) {
		val ws = ResourcesPlugin::workspace
		val wb = PlatformUI::workbench
		
		val commandService = wb.getService(typeof(ICommandService)) as ICommandService
		val handlerService = wb.getService(typeof(IHandlerService)) as IHandlerService

		val command = commandService.getCommand(commandId)
		val executionEvent = handlerService.createExecutionEvent(command, null)
		val context = executionEvent.applicationContext as IEvaluationContext

		if(!file.isNullOrEmpty){
			val iFile = ws.root.getFile(new Path(file))
			context.parent.addVariable(ISources.ACTIVE_MENU_SELECTION_NAME,
				new StructuredSelection(iFile))
		}
		command.executeWithChecks(executionEvent)
	
		IResourcesSetupUtil::reallyWaitForAutoBuild
	}
	
	def ClassLoader createClassLoader(IJavaProject project) {
		val classPathEntries = JavaRuntime.computeDefaultRuntimeClassPath(project);
		val urlList = new ArrayList<URL>();
		for (var i = 0; i < classPathEntries.length; i++) {
		 val entry = classPathEntries.get(i);
		 val path = new Path(entry);
		 val url = path.toFile().toURI().toURL();
		 urlList.add(url);
		}
		val parentClassLoader = project.getClass().getClassLoader();
		return new URLClassLoader(urlList, parentClassLoader);
	}
	
	def void waitFileExistOrAssert(String fileName, int retry, long sleep){
		val ws = ResourcesPlugin::workspace
		for (i : 0 ..< retry) {
			if(ws.root.getFile(new Path(fileName)).exists) {
				return
			} 
			try {
				Thread.sleep(sleep)
			} catch (InterruptedException e){}
		}
		assertFileExists(fileName)
	}
	/**
	 * relaunch the  waitForJobs several times in case some other background task
	 * also wait for idle time to triggers new jobs 
	 */
	static def void reallyWaitForJobs(int retry) {
		for(i : 0.. retry){
			waitForJobs
			Thread.sleep(100)
		}
		waitForJobs
	}
	
	static def void waitForJobs() {
		while (!Job.getJobManager().isIdle())
			delay(100);
	}
	static var closed = false;
	static def void delay(long waitTimeMillis) {
		val Display display = Display.getCurrent();

		// We try to capture when the window is closed by the tester
		PlatformUI.getWorkbench.addWindowListener(
			new IWindowListener() {

				override windowActivated(IWorkbenchWindow window) {
				}

				override windowClosed(IWorkbenchWindow window) {
					closed = true
				}

				override windowDeactivated(IWorkbenchWindow window) {
				}

				override windowOpened(IWorkbenchWindow window) {
				}

			}
		)

		// If this is the UI thread,
		// then process input.
		if (display !== null) {
			val long endTimeMillis = System.currentTimeMillis() + waitTimeMillis;
			while (System.currentTimeMillis() < endTimeMillis && !closed) {
				if (!display.readAndDispatch())
					display.sleep();
			}
			display.update();
		}
      // Otherwise, perform a simple sleep.
		else {
			try {
				Thread.sleep(waitTimeMillis);
			} catch (InterruptedException e) {
				// Ignored.
			}
		}
	}	
}
