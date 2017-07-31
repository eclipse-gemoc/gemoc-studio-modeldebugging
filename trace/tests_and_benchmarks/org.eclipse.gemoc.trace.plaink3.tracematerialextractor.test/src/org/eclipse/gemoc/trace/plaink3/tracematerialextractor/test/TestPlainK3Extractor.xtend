package org.eclipse.gemoc.trace.plaink3.tracematerialextractor.test

import org.eclipse.gemoc.trace.commons.EMFUtil
import org.eclipse.gemoc.trace.commons.EclipseUtil
import org.eclipse.gemoc.trace.commons.testutil.EclipseTestUtil
import org.eclipse.gemoc.trace.plaink3.tracematerialextractor.EventsMetamodelGenerator
import org.eclipse.gemoc.trace.plaink3.tracematerialextractor.ExecutionExtensionGenerator
import java.io.File
import java.io.IOException
import java.lang.reflect.InvocationTargetException
import org.eclipse.core.resources.IProject
import org.eclipse.core.resources.ResourcesPlugin
import org.eclipse.core.runtime.CoreException
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl
import org.eclipse.jdt.core.IJavaProject
import org.eclipse.jdt.core.JavaCore
import org.eclipse.ui.PlatformUI
import org.junit.Assert
import org.junit.Test

class TestPlainK3Extractor {

	private static val String projectName = "org.gemoc.sample.tfsm.plaink3.dsa"

	@Test
	def void test() throws IOException , InvocationTargetException, InterruptedException {

		val ResourceSet rs = new ResourceSetImpl()

		val Resource extendedMetamodelResource = EMFUtil.loadModelPath("inputs/TfsmExtended.ecore", rs)
		val EPackage extendedMetamodel = extendedMetamodelResource.contents.get(0) as EPackage

		// Prepare workspace with a project with xtend code
		val File input = new File("inputs/" + projectName)
		val File output = new File(ResourcesPlugin.getWorkspace.root.location.toFile, projectName)
		EclipseUtil.copyFolder(input, output)
		val IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
		PlatformUI.workbench.activeWorkbenchWindow.run(false, true,
			[ m |
				try {
					project.create(m)
					project.open(m)
				} catch (CoreException e) {
					Assert.fail
				}
			])
		val IJavaProject javaProject = JavaCore.create(project)

		// Extract events from xtend
		val emmgen = new EventsMetamodelGenerator(javaProject, "tfsm", extendedMetamodel)
		emmgen.generate

		// Serialize events
		val Resource eventsResource = rs.createResource(EMFUtil.createFileURI("tmp/tfsmevents.ecore"))
		eventsResource.contents.add(emmgen.eventsMM)
		eventsResource.save(null)

		// Extract extension from ecore
		val extgen = new ExecutionExtensionGenerator(extendedMetamodel)
		extgen.generate

		// Serialize ext
		val Resource extResource = rs.createResource(EMFUtil.createFileURI("tmp/tfsmext.xmi"))
		extResource.contents.add(extgen.mmextensionResult)
		extResource.save(null)

		EclipseTestUtil.waitForJobs

	}

}
