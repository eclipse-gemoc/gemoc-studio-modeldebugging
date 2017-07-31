package org.eclipse.gemoc.trace.metamodel.test

import org.eclipse.gemoc.trace.commons.EMFUtil
import org.eclipse.gemoc.trace.metamodel.generator.TraceMMGenerator
import java.io.File
import org.eclipse.emf.common.util.Diagnostic
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.impl.EPackageRegistryImpl
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl
import org.eclipse.emf.ecore.util.Diagnostician
import org.eclipse.emf.ecore.util.EcoreUtil
import org.junit.Before
import org.junit.Test

import static org.junit.Assert.*

class TraceMMGeneratorTest {

	static var boolean saveInFiles = true;

	var ResourceSet rs
	
	protected def File getInputFolder() {
		return new File("model_inputs")
	}

	@Before
	def void init() {
		this.rs = new ResourceSetImpl
		EMFUtil.registerEcoreFactory(rs)
		EMFUtil.registerXMIFactory(rs)
	}

	def Resource loadModel(String path) {
		val res = rs.createResource(EMFUtil.createFileURI(path))
		res.load(null)
		EcoreUtil.resolveAll(rs) // IMPORTANT
		return res
	}

	@Test
	def void testModel1() {
		genericTest("model1")
	}

	@Test
	def void testModel2() {
		genericTest("model2")
	}

	@Test
	def void testAD() {
		genericTest("activitydiagram")
	}

	@Test
	def void testFuml() {
		genericTest("fuml", "http://www.eclipse.org/uml2/5.0.0/UML")
	}

	@Test
	def void testPetriNet() {
		genericTest("petrinet")
	}

	def void genericTest(String name) {
		genericTest(name, null)
	}

	protected def String getMMExtension() {
		return "ecore"
	}

	def void genericTest(String name, String nsURI) {
		/*
		println("Testing with input: " + name)

		var EPackage ecore

		if (nsURI == null)
			ecore = loadModel(new File(inputFolder, name + "." + MMExtension).absolutePath).contents.filter(EPackage).get(0)
		else {
			ecore = EPackageRegistryImpl.INSTANCE.getEPackage(nsURI)
		}

		val Resource ecorextResource = loadModel(new File(inputFolder, name + "ext.xmi").absolutePath)

		val ecorext = ecorextResource.contents.get(0) as Ecorext

		// Calling the method
		val stuff = new TraceMMGenerator(ecorext, ecore, false)
		stuff.computeAllMaterial
		
		// Just to check manually: save in files
		if (saveInFiles) {
			val Resource r1 = rs.createResource(EMFUtil.createFileURI("tmp/" + name + "tracemm.ecore"))
			r1.contents.add(stuff.tracemmresult)
			r1.save(null)
		}

		// Basic oracle: non empty models && validation
		assertTrue(stuff.tracemmresult.eAllContents.filter(EClass).size > 0)
		val results = Diagnostician.INSTANCE.validate(stuff.tracemmresult);
		val error = results.children.findFirst[r|r.severity == Diagnostic.ERROR]
		assertFalse("There is at least one error in the generated ecore model: " + error, error != null)
		*/
	}

}
