package org.eclipse.gemoc.trace.plugin.generator.test

import org.eclipse.core.resources.IProject
import org.eclipse.gemoc.trace.commons.testutil.EclipseTestUtil
import org.junit.After
import org.junit.AfterClass
import org.junit.Test

/**
 * Taken from http://www.informit.com/articles/article.aspx?p=1315271&seqNum=8
 */
class TestTracePluginGenerator {

	static val String root = "platform:/plugin/org.eclipse.gemoc.trace.plugin.generator.test/inputs/"

	@Test
	def void testPetrinet() {
		genericTest("petrinet")
	}

	@Test
	def void testTFSM() {
		genericTest("tfsm")
	}

	var IProject currentProject

	def void genericTest(String name) {
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

		val gen = new GenericTracePluginGenerator(abstractSyntax, executionEcorExt, eventsMetamodel,
			"awesomeProject" + new Random().nextInt(100))
		gen.generate
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
