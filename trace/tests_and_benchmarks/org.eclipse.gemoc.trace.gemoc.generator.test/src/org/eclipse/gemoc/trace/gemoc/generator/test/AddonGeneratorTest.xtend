package org.eclipse.gemoc.trace.gemoc.generator.test

import org.junit.Test
import org.eclipse.gemoc.trace.commons.testutil.EclipseTestUtil
import org.eclipse.gemoc.trace.gemoc.generator.GenericEngineTraceAddonGenerator
import org.eclipse.emf.common.util.URI
import java.util.Random
import org.junit.After
import org.junit.AfterClass
import org.eclipse.core.resources.IProject
import org.eclipse.emf.ecore.EPackage
import org.eclipse.gemoc.trace.commons.EMFUtil
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl
import org.eclipse.emf.ecore.resource.Resource
import java.io.IOException

class AddonGeneratorTest {

	static val String root = "platform:/plugin/org.eclipse.gemoc.trace.plugin.generator.test/inputs/"

	@Test
	def void testPetrinet() throws IOException {
		genericTest("petrinet")
	}

	@Test
	def void testTFSM() throws IOException {
		genericTest("tfsm")
	}

	var IProject currentProject

	def void genericTest(String name) throws IOException {
		/*
		val abstractSyntaxEcoreURI = URI.createURI(root + name + ".ecore")
		val executionEcorExtURI = URI.createURI(root + name + "ext.xmi")
		val eventsMetamodelURI = URI.createURI(root + name + "events.ecore")
		val ResourceSet rs = new ResourceSetImpl()

		// Load the three models
		val Resource abstractSyntaxResource = EMFUtil.loadModelURI(abstractSyntaxEcoreURI, rs)
		val EPackage abstractSyntax = abstractSyntaxResource.contents.filter(EPackage).get(0)
		val Resource executionEcorExtResource = EMFUtil.loadModelURI(executionEcorExtURI, rs)
		val Ecorext executionEcorExt = executionEcorExtResource.contents.filter(Ecorext).get(0)
		val Resource eventsMetamodelResource = EMFUtil.loadModelURI(eventsMetamodelURI, rs)
		val EPackage eventsMetamodel = eventsMetamodelResource.contents.filter(EPackage).get(0)

		val gen = new GenericEngineTraceAddonGenerator(abstractSyntax, executionEcorExt, eventsMetamodel,
			"awesomeProject" + new Random().nextInt(100))
		gen.generateCompleteAddon
		currentProject = gen.project
		*/

	}

	@After
	def void waitOne() {
		EclipseTestUtil.waitForJobs
	}

	@AfterClass
	def static void waitAll() {
		EclipseTestUtil.waitForJobsThenWindowClosed
	}

}
