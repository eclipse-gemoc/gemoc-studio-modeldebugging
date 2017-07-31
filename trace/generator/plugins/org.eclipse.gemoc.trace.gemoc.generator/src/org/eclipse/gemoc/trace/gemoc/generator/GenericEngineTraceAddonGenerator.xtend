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
package org.eclipse.gemoc.trace.gemoc.generator

import org.eclipse.gemoc.trace.commons.CodeGenUtil
import org.eclipse.gemoc.trace.commons.EclipseUtil
import org.eclipse.gemoc.trace.commons.EcoreCraftingUtil
import org.eclipse.gemoc.trace.commons.ManifestUtil
import org.eclipse.gemoc.trace.commons.PluginXMLHelper
import org.eclipse.gemoc.trace.gemoc.generator.codegen.StateManagerGeneratorJava
import org.eclipse.gemoc.trace.gemoc.generator.codegen.TraceConstructorGeneratorJava
import org.eclipse.gemoc.trace.gemoc.generator.util.StandaloneEMFProjectGenerator
import org.eclipse.gemoc.trace.metamodel.generator.TraceMMGenerationTraceability
import org.eclipse.gemoc.trace.metamodel.generator.TraceMMGenerator
import java.util.HashSet
import java.util.List
import java.util.Set
import opsemanticsview.OperationalSemanticsView
import opsemanticsview.Rule
import org.eclipse.core.resources.IFile
import org.eclipse.core.resources.IProject
import org.eclipse.core.runtime.IProgressMonitor
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EClassifier
import org.eclipse.emf.ecore.EOperation
import org.eclipse.emf.ecore.EPackage
import org.eclipse.jdt.core.IJavaProject
import org.eclipse.jdt.core.IPackageFragment
import org.eclipse.jdt.core.IPackageFragmentRoot
import org.eclipse.jdt.core.JavaCore
import org.eclipse.ui.PlatformUI
import org.eclipse.xtend.lib.annotations.Accessors
import org.eclipse.gemoc.xdsmlframework.api.extensions.engine_addon.EngineAddonSpecificationExtensionPoint
import org.jdom2.Element
import org.jdom2.filter.ElementFilter

class GenericEngineTraceAddonGenerator {

	// Inputs
	private val OperationalSemanticsView opsemanticsview // URI
	private val String pluginName
	
	// Transient vals (derived just from inputs)
	private val String packageQN
	private val String className
	private val String languageName
	private val String tracedLanguageName
	private val String stepFactoryClassName
	
	// Transient vars
	private var String traceConstructorClassName
	private var String stateManagerClassName
	private var TraceMMGenerationTraceability traceability
	private var Set<GenPackage> genPackages
	private var IPackageFragment packageFragment
	private var EPackage tracemm
	private var boolean partialTraceManagement = false

	// Output
	@Accessors(PUBLIC_GETTER, PROTECTED_SETTER)
	IProject project
	


	new(OperationalSemanticsView opsemanticsview, String pluginName) {
		this.opsemanticsview = opsemanticsview
		this.pluginName = pluginName
		this.packageQN = pluginName + ".tracemanager"
		this.tracedLanguageName = opsemanticsview.executionMetamodel.name
		this.languageName = tracedLanguageName.replaceAll(" ", "") + "Trace"
		this.className = languageName.replaceAll(" ", "").toFirstUpper + "EngineAddon"
		this.stepFactoryClassName = languageName.replaceAll(" ", "").toFirstUpper + "StepFactory"
	}

	public def void generateCompleteAddon() {
		PlatformUI.workbench.activeWorkbenchWindow.run(false, true, [ m |
			generateCompleteAddon(m)
		])
	}

	public static def String getBaseFQN(Rule r) {
		val EOperation o = r.operation
		val EClass c = r.containingClass
		return EcoreCraftingUtil.getBaseFQN(c) + "." + o.name
	}
	
	private def String getJavaFQN(EClassifier c) {
		return getJavaFQN(c,false)
	}
	
	private def String getJavaFQN(EClassifier c, boolean enforcePrimitiveJavaClass) {
		return EcoreCraftingUtil.getJavaFQN(c,genPackages,enforcePrimitiveJavaClass)
	}
	
	private static def Set<GenPackage> findNestedGenpackages(GenPackage p) {
		val result = p.nestedGenPackages.toSet
		result.add(p)
		for (n : p.nestedGenPackages) {
			result.addAll(findNestedGenpackages(n))
		}
		return result
	}
	
	
	public def void generateCompleteAddon(IProgressMonitor m) {
		generateTraceMetamodelAndPlugin(m)
		prepareManifest(m)
		generateTraceManagementCode(m)
		addExtensionPoint()
	}
	
	private def void generateTraceMetamodelAndPlugin(IProgressMonitor m) {
		
//TODO disabled for now, the whole approach must be adapted since Ecorext is not used anymore
//		if (tracingAnnotations != null) {
//			var Set<EClass> classesToTrace = new HashSet
//			var Set<EStructuralFeature> propertiesToTrace = new HashSet
//			classesToTrace.addAll(tracingAnnotations.classestoTrace)
//			propertiesToTrace.addAll(tracingAnnotations.propertiesToTrace)
//			val filter = new ExtensionFilter(opsemanticsview, classesToTrace, propertiesToTrace)
//			filter.execute()
//			partialTraceManagement = filter.didFilterSomething
//		}

		// Generate trace metamodel
		val TraceMMGenerator tmmgenerator = new TraceMMGenerator(opsemanticsview, true)
		tmmgenerator.computeAllMaterial
		tmmgenerator.sortResult
		tracemm = tmmgenerator.tracemmresult

		// Generate EMF project
		val StandaloneEMFProjectGenerator emfGen = new StandaloneEMFProjectGenerator(pluginName, tracemm)
		emfGen.generateBaseEMFProject(m)
		val referencedGenPackagesRoots = emfGen.referencedGenPackages
		this.genPackages = referencedGenPackagesRoots.map[findNestedGenpackages].flatten.toSet

		// At this point the wizard has created and reloaded a new resource with the trace metamodel
		// We access this new metamodel/resource thanks to emfGen.rootPackages
		// And we add add the missing gemoc getCaller implementations to the trace metamodel
		tmmgenerator.addGetCallerEOperations(emfGen.rootPackages, genPackages)
		emfGen.rootPackages.head.eResource.save(null)

		// Generate code
		emfGen.generateModelCode(m)
		this.project = emfGen.project

		// Finding the "src folder" in which to generate code
		val IJavaProject javaProject = JavaCore.create(project)
		val sourceFolders = EclipseUtil.findSrcFoldersOf(javaProject)

		// Now we need lots of things that require a monitor, so we do that in a dedicated action
		// We use JDT to create the package folders from a string "xxx.yyy.zzz"
		val IPackageFragmentRoot srcFolderFragment = javaProject.getPackageFragmentRoot(sourceFolders.get(0));
		packageFragment = srcFolderFragment.createPackageFragment(packageQN, true, m)


		this.traceability = tmmgenerator.traceability
	}
	
	private def void generateTraceManagementCode(IProgressMonitor m) {
		
		// Generate trace constructor
		val TraceConstructorGeneratorJava tconstructorgen = new TraceConstructorGeneratorJava(languageName,
			pluginName + ".tracemanager", tracemm, traceability, genPackages,
			opsemanticsview.executionMetamodel, partialTraceManagement)
		traceConstructorClassName = tconstructorgen.className
		packageFragment.createCompilationUnit(traceConstructorClassName + ".java", tconstructorgen.generateCode, true, m)

		// Generate state manager
		val StateManagerGeneratorJava statemanagergem = new StateManagerGeneratorJava(languageName,
			pluginName + ".tracemanager", tracemm, traceability, genPackages)
		stateManagerClassName = statemanagergem.className
		packageFragment.createCompilationUnit(stateManagerClassName + ".java", statemanagergem.generateCode, true, m)
		
			// Getting java fragment to create classes
		val IPackageFragment fragment = packageFragment

		// Generate trace engine addon class (same package as the trace manager)
		val String prettyCode = CodeGenUtil.formatJavaCode(generateAddonClassCode())
		fragment.createCompilationUnit(className + ".java", prettyCode, true, m)
		
		// Generate step factory class (same package as the trace manager)
		val String uglyFactoryCode = generateStepFactory 
		val String prettyCodeStepFactory = CodeGenUtil.formatJavaCode(uglyFactoryCode)
		fragment.createCompilationUnit(stepFactoryClassName + ".java", prettyCodeStepFactory, true, m)
		
	}
	
	private def prepareManifest(IProgressMonitor m) {
		ManifestUtil.addToPluginManifest(project, m, "org.eclipse.emf.transaction")
		ManifestUtil.addToPluginManifest(project, m, "org.eclipse.emf.compare")
		ManifestUtil.addToPluginManifest(project, m, "org.eclipse.gemoc.executionframework.engine")
		ManifestUtil.addToPluginManifest(project, m, "org.eclipse.xtext")
		ManifestUtil.addToPluginManifest(project, m, "org.eclipse.gemoc.commons.eclipse")
		ManifestUtil.addToPluginManifest(project, m, "org.eclipse.gemoc.trace.gemoc")
		ManifestUtil.addToPluginManifest(project, m, "org.eclipse.gemoc.trace.gemoc.api")
		ManifestUtil.addToPluginManifest(project, m, "org.eclipse.gemoc.xdsmlframework.api")
		ManifestUtil.addToPluginManifest(project, m, "org.eclipse.gemoc.trace.commons.model")
		ManifestUtil.addToPluginManifest(project, m, "org.eclipse.gemoc.addon.multidimensional.timeline")
		ManifestUtil.addToPluginManifest(project, m, "org.eclipse.gemoc.timeline")
		ManifestUtil.addToPluginManifest(project, m, "org.eclipse.gemoc.trace.commons")
		ManifestUtil.addToPluginManifest(project, m, "org.eclipse.gemoc.xdsmlframework.api")
		ManifestUtil.addToPluginManifest(project, m, "org.eclipse.gemoc.trace.commons.model")
		ManifestUtil.setRequiredExecutionEnvironmentToPluginManifest(project, m, "JavaSE-1.8")
	}
	
	private def void addExtensionPoint() {
		
		// Add extension point (taken from GemocLanguageDesignerBuilder)
		this.project = project
		val IFile pluginfile = project.getFile(PluginXMLHelper.PLUGIN_FILENAME);
		PluginXMLHelper.createEmptyTemplateFile(pluginfile, false);
		val PluginXMLHelper helper = new PluginXMLHelper();
		helper.loadDocument(pluginfile);
		val Element extensionPoint = helper.getOrCreateExtensionPoint(
			EngineAddonSpecificationExtensionPoint.GEMOC_ENGINE_ADDON_EXTENSION_POINT);
		updateDefinitionAttributeInExtensionPoint(extensionPoint, 
			EngineAddonSpecificationExtensionPoint.GEMOC_ENGINE_ADDON_EXTENSION_POINT_CLASS, packageQN + "." + className
		);
		updateDefinitionAttributeInExtensionPoint(extensionPoint, 
			EngineAddonSpecificationExtensionPoint.GEMOC_ENGINE_ADDON_EXTENSION_POINT_DEFAULT, "false"
		);
		updateDefinitionAttributeInExtensionPoint(extensionPoint, 
			EngineAddonSpecificationExtensionPoint.GEMOC_ENGINE_ADDON_EXTENSION_POINT_ID, pluginName
		);
		updateDefinitionAttributeInExtensionPoint(extensionPoint, 
			EngineAddonSpecificationExtensionPoint.GEMOC_ENGINE_ADDON_EXTENSION_POINT_NAME, 
			tracedLanguageName + " MultiDimensional Trace"
		);
		updateDefinitionAttributeInExtensionPoint(extensionPoint, 
			EngineAddonSpecificationExtensionPoint.GEMOC_ENGINE_ADDON_EXTENSION_POINT_SHORTDESCRIPTION, 
			"MultiDimensional Trace support dedicated to "+tracedLanguageName+" language"
		);
		updateDefinitionAttributeInExtensionPoint(extensionPoint, 
			EngineAddonSpecificationExtensionPoint.GEMOC_ENGINE_ADDON_EXTENSION_POINT_OPENVIEWIDS, "org.eclipse.gemoc.addon.multidimensional.timeline.views.timeline.MultidimensionalTimeLineView"
		);
		updateDefinitionAttributeInExtensionPoint(extensionPoint, 
			EngineAddonSpecificationExtensionPoint.GEMOC_ENGINE_ADDON_EXTENSION_POINT_ADDONGROUPID, "Sequential.AddonGroup"
		);
		helper.saveDocument(pluginfile);
		
	}

	private def static Element updateDefinitionAttributeInExtensionPoint(Element extensionPoint, String atributeName,
		String value) {
		var Element result;
		val String defName = "Addon"
		val List<Element> elements = extensionPoint.getContent(new ElementFilter(defName));
		if (elements.size() == 0) {

			// create extension point
			result = new Element(defName);
			extensionPoint.addContent(result);
		} else {
			result = elements.get(0);
		}
		result.setAttribute(atributeName, value);
		return result;
	}

	private def String generateAddonClassCode() {
		return '''package «packageQN»;

import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

import org.eclipse.gemoc.trace.commons.model.trace.State;
import org.eclipse.gemoc.trace.gemoc.api.IStateManager;
import org.eclipse.gemoc.trace.gemoc.api.IStepFactory;
import org.eclipse.gemoc.trace.commons.model.trace.TracedObject;
import org.eclipse.gemoc.trace.gemoc.api.ITraceConstructor;
import org.eclipse.gemoc.trace.gemoc.traceaddon.AbstractTraceAddon;

public class «className» extends AbstractTraceAddon {
	
	private «stepFactoryClassName» factory = null;

	@Override
	public ITraceConstructor constructTraceConstructor(Resource modelResource, Resource traceResource, Map<EObject, TracedObject<?>> exeToTraced) {
		return new «traceConstructorClassName»(modelResource, traceResource, exeToTraced);
	}
	
	@Override
	public IStateManager<State<?, ?>> constructStateManager(Resource modelResource, Map<TracedObject<?>, EObject> tracedToExe) {
		return new «stateManagerClassName»(modelResource, tracedToExe);
	}
	
	@Override
	public IStepFactory getFactory() {
		if (factory == null)
			factory = new «stepFactoryClassName»();
		return factory;
	}
	
	@Override
	public boolean isAddonForTrace(EObject root) {
		return root instanceof «getJavaFQN(traceability.traceMMExplorer.getSpecificTraceClass)»;
	}

}'''
	}
	
	private def Set<EClass> potentialCallerClasses(EClass stepCallerClass) {
		val possibleCallerClasses = new HashSet<EClass>
		possibleCallerClasses.addAll(opsemanticsview.executionMetamodel.EClassifiers.filter(EClass))
		possibleCallerClasses.addAll(traceability.allMutableClasses)
		val filtered = possibleCallerClasses.filter(EClass)
			.filter[c|c.equals(stepCallerClass)||c.EAllSuperTypes.contains(stepCallerClass)]
			.sortBy[name].toSet
		return filtered
	}
	
	private def String generateStepFactory() {
		return '''
		
	package «packageQN»;
		
	import java.util.List;
	import org.eclipse.gemoc.trace.gemoc.api.IStepFactory;

	public class «stepFactoryClassName» implements IStepFactory {	
		
	@Override
	public org.eclipse.gemoc.trace.commons.model.trace.Step<?> createStep(org.eclipse.gemoc.trace.commons.model.trace.MSE mse, List<Object> parameters, List<Object> result) {

		org.eclipse.gemoc.trace.commons.model.trace.Step<?> step = null;
org.eclipse.emf.ecore.EClass ec = mse.getCaller().eClass();
String stepRule = org.eclipse.gemoc.trace.commons.EcoreCraftingUtil.getFQN(ec, ".") + "."
							+ mse.getAction().getName();

		«FOR Rule rule : opsemanticsview.rules.sortBy[baseFQN] SEPARATOR "else" AFTER "else"»

			«val stepCallerClass = rule.containingClass»
			«val filtered = potentialCallerClasses(stepCallerClass)»
			
			«IF filtered.empty»
			
			if (stepRule.equalsIgnoreCase("«getBaseFQN(rule)»")) {
			«ELSE»
			if (
				mse.getAction().getName().equalsIgnoreCase("«rule.operation.name»")
				&& (
				«FOR possibleCallerClass: filtered SEPARATOR " || "»
					ec.getClassifierID()== «EcoreCraftingUtil.stringClassifierID(possibleCallerClass, genPackages)»
				«ENDFOR»
				)
			)
			
			 {
			«ENDIF»
			step = «EcoreCraftingUtil.stringCreate(traceability.getStepClassFromStepRule(rule))»;
			} 
			
		«ENDFOR»
		{
		step = org.eclipse.gemoc.trace.commons.model.generictrace.GenerictraceFactory.eINSTANCE.createGenericSequentialStep();
		}
	
		org.eclipse.gemoc.trace.commons.model.trace.MSEOccurrence mseocc = org.eclipse.gemoc.trace.commons.model.trace.TraceFactory.eINSTANCE.createMSEOccurrence();
		mseocc.setMse(mse);
		mseocc.getParameters().addAll(parameters);
		mseocc.getResult().addAll(result);
		step.setMseoccurrence(mseocc);

		return step;
	}
	}
		'''
	}
		

}
