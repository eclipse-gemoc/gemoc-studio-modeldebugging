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

import com.google.inject.Inject
import fr.inria.diverse.melange.metamodel.melange.ModelTypingSpace
import java.util.List
import java.util.zip.ZipFile
import org.eclipse.core.expressions.IEvaluationContext
import org.eclipse.core.resources.IProject
import org.eclipse.core.resources.ResourcesPlugin
import org.eclipse.core.runtime.IProgressMonitor
import org.eclipse.core.runtime.NullProgressMonitor
import org.eclipse.core.runtime.Path
import org.eclipse.core.runtime.Status
import org.eclipse.core.runtime.jobs.Job
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.compare.EMFCompare
import org.eclipse.emf.compare.Match
import org.eclipse.emf.compare.diff.DefaultDiffEngine
import org.eclipse.emf.compare.diff.FeatureFilter
import org.eclipse.emf.compare.scope.DefaultComparisonScope
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.EReference
import org.eclipse.emf.ecore.EStructuralFeature
import org.eclipse.emf.ecore.EcorePackage
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl
import org.eclipse.gemoc.trace.gemoc.generator.TraceAddonGeneratorIntegration
import org.eclipse.gemoc.xdsmlframework.ide.ui.XDSMLFrameworkUI
import org.eclipse.jdt.core.JavaCore
import org.eclipse.jface.viewers.StructuredSelection
import org.eclipse.jface.viewers.TreeViewer
import org.eclipse.pde.internal.core.natures.PDE
import org.eclipse.swt.widgets.Display
import org.eclipse.ui.ISources
import org.eclipse.ui.PlatformUI
import org.eclipse.ui.commands.ICommandService
import org.eclipse.ui.dialogs.IOverwriteQuery
import org.eclipse.ui.handlers.IHandlerService
import org.eclipse.ui.internal.wizards.datatransfer.ZipLeveledStructureProvider
import org.eclipse.ui.part.FileEditorInput
import org.eclipse.ui.views.contentoutline.IContentOutlinePage
import org.eclipse.ui.wizards.datatransfer.ImportOperation
import org.eclipse.xtext.junit4.ui.util.IResourcesSetupUtil
import org.eclipse.xtext.junit4.ui.util.JavaProjectSetupUtil
import org.eclipse.xtext.resource.DerivedStateAwareResource
import org.eclipse.xtext.ui.XtextProjectHelper
import org.eclipse.xtext.ui.editor.XtextEditor
import org.eclipse.xtext.ui.editor.outline.IOutlineNode
import org.eclipse.xtext.ui.editor.outline.impl.OutlinePage
import org.eclipse.xtext.ui.editor.utils.EditorUtils
import org.eclipse.xtext.ui.resource.XtextResourceSetProvider
import org.junit.Assert

/**
 * Class containing helper methods for testing a workspace in a GEMOC Language workbench
 */
class MelangeWorkspaceTestHelper extends WorkspaceTestHelper {
	static final String MELANGE_CMD_GENERATE_ALL        = "fr.inria.diverse.melange.GenerateAll"
	static final String MELANGE_CMD_GENERATE_ADAPTERS   = "fr.inria.diverse.melange.GenerateAdapters"
	static final String MELANGE_CMD_GENERATE_LANGUAGES  = "fr.inria.diverse.melange.GenerateLanguages"
	static final String MELANGE_CMD_GENERATE_INTERFACES = "fr.inria.diverse.melange.GenerateInterfaces"
	static final String MELANGE_CMD_GENERATE_TRACE      = "org.eclipse.gemoc.execution.sequential.javaxdsml.ide.ui.commands.GenerateTraceAddonPlugin"
	static final String MELANGE_CMD_CLEAN_ALL           = "fr.inria.diverse.melange.CleanAll"

	static final String MELANGE_EDITOR_ID = "fr.inria.diverse.melange.Melange"
	
	@Inject XtextResourceSetProvider rsProvider

	override void init() {
		Display.^default.syncExec(new Runnable(){
				override run() {
					PlatformUI::workbench.showPerspective(XDSMLFrameworkUI.ID_PERSPECTIVE, PlatformUI.workbench.activeWorkbenchWindow)
					closeWelcomePage
				}
			})
	}

	def IProject deployMelangeProject(String projectName, String zipLocation) {
		val newProject = JavaProjectSetupUtil::createJavaProject(projectName)
		JavaProjectSetupUtil::addSourceFolder(newProject, "src")
		JavaProjectSetupUtil::addSourceFolder(newProject, "src-gen")
		IResourcesSetupUtil::addNature(newProject.project, XtextProjectHelper::NATURE_ID)
		IResourcesSetupUtil::addNature(newProject.project, PDE::PLUGIN_NATURE)
		IResourcesSetupUtil::addBuilder(newProject.project, XtextProjectHelper::BUILDER_ID)
		IResourcesSetupUtil::addBuilder(newProject.project, PDE::MANIFEST_BUILDER_ID)
		IResourcesSetupUtil::addBuilder(newProject.project, PDE::SCHEMA_BUILDER_ID)
		JavaProjectSetupUtil::addToClasspath(newProject,
			JavaCore::newContainerEntry(new Path("org.eclipse.xtend.XTEND_CONTAINER")))
		JavaProjectSetupUtil::addToClasspath(newProject,
			JavaCore::newContainerEntry(new Path("org.eclipse.pde.core.requiredPlugins")))

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
		return newProject.project
	}

	def void generateAll(String melangeFile) {
		invokeCommandOnSelectedFile(MELANGE_CMD_GENERATE_ALL, melangeFile)
	}

	def void generateAdapters(String melangeFile) {
		invokeCommandOnSelectedFile(MELANGE_CMD_GENERATE_ADAPTERS, melangeFile)
	}
	
	def void generateLanguages(String melangeFile) {
		invokeCommandOnSelectedFile(MELANGE_CMD_GENERATE_LANGUAGES, melangeFile)
	}
	
	def void generateInterfaces(String melangeFile) {
		invokeCommandOnSelectedFile(MELANGE_CMD_GENERATE_INTERFACES, melangeFile)
	}
	
	def void generateTrace(String melangeFile, String languageName, String targetProjectName) {
		//invokeMelangeCommand(MELANGE_CMD_GENERATE_TRACE, melangeFile)
		val mlgFile = ResourcesPlugin::workspace.root.getFile(new Path(melangeFile))
		
		val j = new Job("Generating trace addon plugin for " + melangeFile.toString) {
			public Exception reportedJobException 
			override protected run(IProgressMonitor monitor) {
				try{
					TraceAddonGeneratorIntegration.generateAddon(mlgFile, languageName, targetProjectName, true,
						monitor)
				}
				catch (Exception e){
					this.reportedJobException = e
				}
				
				return new Status(Status.OK, "org.eclipse.gemoc.trace.gemoc.ui", "Multidimensional Trace addon plugin generated.")
			}
		}
		// And we start the job and wait
		j.schedule
		j.join
		if (j.reportedJobException != null) throw j.reportedJobException
	}
	
	def void cleanAll(String melangeFile) {
		Display.^default.syncExec(new Runnable() {
			override run() {
				invokeCommandOnSelectedFile(MELANGE_CMD_CLEAN_ALL, melangeFile)
			}
		})
	}


	def XtextEditor openEditor(String melangeFile) {
		try {
			val ws = ResourcesPlugin::workspace
			val mlgFile = ws.root.getFile(new Path(melangeFile))
			val wb = PlatformUI::workbench
			val openEditor = wb.activeWorkbenchWindow.activePage
				.openEditor(new FileEditorInput(mlgFile), MELANGE_EDITOR_ID);
			val xtextEditor = EditorUtils::getXtextEditor(openEditor)
			if (xtextEditor !== null) {
				xtextEditor.selectAndReveal(0, 0)
				xtextEditor.internalSourceViewer.setSelectedRange(0, 0)
				xtextEditor.internalSourceViewer.textWidget.setFocus
				return xtextEditor
			}
		} catch (Exception e) {
			e.printStackTrace
			Assert.fail(e.message)
		}

		return null
	}
	
	def TreeViewer getOutline(String melangeFile){
		val editor = openEditor(melangeFile)
		val outlinePage = editor.getAdapter(typeof(IContentOutlinePage)) as OutlinePage
		val treeViewer = outlinePage.treeViewer
		return treeViewer
	}

	
	/**
	 * Check for each aspect from {@link aspects} that K3-generated files are inside {@link project}
	 * 
	 * @param aspects Pairs of [AspectName->AspectedClass]
	 */
	def void assertK3AspectsExists(List<Pair<String,String>> aspects, String project){
		
		val ASPECTS_NS = project+".aspects"
		val ASPECTS_FOLDER = ASPECTS_NS.replaceAll("\\.","/")
		val SRC_GEN = "src-gen"
		
		aspects.forEach[asp |
			val aspectName = asp.key
			val targetClass = asp.value
			val aspect     = '''«project»/«SRC_GEN»/«ASPECTS_FOLDER»/«aspectName».java'''
			val context    = '''«project»/«SRC_GEN»/«ASPECTS_FOLDER»/«aspectName»«targetClass»AspectContext.java'''
			val properties = '''«project»/«SRC_GEN»/«ASPECTS_FOLDER»/«aspectName»«targetClass»AspectProperties.java'''
			assertFileExists(aspect)
			assertFileExists(context)
			assertFileExists(properties)
		]
	}
	
	/**
	 * Check for each aspect from {@link aspects} that K3-generated files are NOT inside {@link project}
	 * 
	 * @param aspects Pairs of [AspectName->AspectedClass]
	 */
	def void assertK3AspectsDontExists(List<Pair<String,String>> aspects, String project){
		
		val ASPECTS_NS = project+".aspects"
		val ASPECTS_FOLDER = ASPECTS_NS.replaceAll("\\.","/")
		val SRC_GEN = "src-gen"
		
		aspects.forEach[asp |
			val aspectName = asp.key
			val targetClass = asp.value
			val aspect     = '''«project»/«SRC_GEN»/«ASPECTS_FOLDER»/«aspectName».java'''
			val context    = '''«project»/«SRC_GEN»/«ASPECTS_FOLDER»/«aspectName»«targetClass»AspectContext.java'''
			val properties = '''«project»/«SRC_GEN»/«ASPECTS_FOLDER»/«aspectName»«targetClass»AspectProperties.java'''
			assertFileDontExists(aspect)
			assertFileDontExists(context)
			assertFileDontExists(properties)
		]
	}


	/**
	 * Returns the EPackage for generated model type {@link mtName}
	 * in {@link project}
	 */
	def EPackage getMT(IProject project, String mtName) {
		val rs = new ResourceSetImpl
		val res = rs.getResource(URI::createURI('''platform:/resource/«project.name»/model-gen/«mtName».ecore'''), true)

		return res.contents.head as EPackage
	}

	def void assertMatch(EPackage pkg, String refEcore) {
		val rs = new ResourceSetImpl
		val uri = URI::createURI(refEcore)
		val res = rs.getResource(uri, true)
		val ref = res.contents.head as EPackage

		val scope = new DefaultComparisonScope(pkg, ref, null)
		// We don't want to take order into account
		// We don't want to take eAnnotations (especially "aspect") into account
		val comparison = EMFCompare.builder().setDiffEngine(
			new DefaultDiffEngine() {
				override def FeatureFilter createFeatureFilter() {
					return new FeatureFilter() {
						override boolean isIgnoredReference(Match match, EReference ref) {
							return ref == EcorePackage.Literals.EMODEL_ELEMENT__EANNOTATIONS
							        || super.isIgnoredReference(match, ref)
						}

						override boolean checkForOrderingChanges(EStructuralFeature f) {
							return false
						}
					}
				}
			}
		).build.compare(scope)

		if (!comparison.differences.empty)
			Assert::fail(comparison.differences.join(", "))

		Assert::assertTrue(comparison.differences.empty)
	}
	
	/*
	 * Assert node contains at least pkg
	 */
	def void assertMatch(EPackage pkg, IOutlineNode node){
		
		Assert.assertEquals(pkg.name, node.text.toString)
		
		val subPack = pkg.getESubpackages
		val classes = pkg.getEClassifiers
		val subNodes = node.children
		
		subPack.forEach[p |
			val n = subNodes.findFirst[text.toString == p.name]
			Assert.assertNotNull(n)
			assertMatch(p,n)
		]
		
		classes.forEach[c |
			val n = subNodes.findFirst[text.toString == c.name]
			Assert.assertNotNull(n)
			if(c instanceof EClass){
				assertMatch(c,n)
			}
		]
	}
	
	def void assertMatch(EClass cls, IOutlineNode node){
		
		Assert.assertEquals(cls.name, node.text.toString)
		
		val ref = cls.getEAllReferences
		val att = cls.getEAllAttributes
		val op  = cls.getEAllOperations
		val subNodes = node.children
		
		ref.forEach[r |
			val n = subNodes.findFirst[text.toString == (r.name +" : "+ r.getEType.name)]
			Assert.assertNotNull(n)
		]
		
		att.forEach[a |
			val n = subNodes.findFirst[text.toString == a.name +" : "+ a.getEType.name]
			Assert.assertNotNull(n)
		]
		
		op.forEach[o |
			val n = subNodes.findFirst[text.toString == o.name +" : "+ o.getEType.name]
			Assert.assertNotNull(n)
		]
	}
	
	def void assertMatch(String refEcore, IOutlineNode node){
		val rs = new ResourceSetImpl
		val uri = URI::createURI(refEcore)
		val res = rs.getResource(uri, true)
		val ref = res.contents.head as EPackage
		assertMatch(ref,node)
	}
	
	def ModelTypingSpace getResource(String projectName, String melangeFile){
		val melangeProject = getProject(projectName)
		val rs = rsProvider.get(melangeProject)
		val uri = URI::createPlatformResourceURI(melangeFile, true)
		val res = rs.getResource(uri, true) as DerivedStateAwareResource
		res.installDerivedState(false)
		return res.contents.head as ModelTypingSpace
	}
	

}
