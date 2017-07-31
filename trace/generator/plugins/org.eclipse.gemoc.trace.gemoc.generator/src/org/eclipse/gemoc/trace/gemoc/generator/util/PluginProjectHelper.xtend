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
package org.eclipse.gemoc.trace.gemoc.generator.util;

import java.io.ByteArrayInputStream
import java.io.InputStream
import java.util.ArrayList
import java.util.Collections
import java.util.Iterator
import java.util.List
import java.util.Set
import org.eclipse.core.resources.ICommand
import org.eclipse.core.resources.IContainer
import org.eclipse.core.resources.IFile
import org.eclipse.core.resources.IFolder
import org.eclipse.core.resources.IProject
import org.eclipse.core.resources.IProjectDescription
import org.eclipse.core.resources.IWorkspace
import org.eclipse.core.resources.ResourcesPlugin
import org.eclipse.core.runtime.CoreException
import org.eclipse.core.runtime.IProgressMonitor
import org.eclipse.core.runtime.NullProgressMonitor
import org.eclipse.core.runtime.Path
import org.eclipse.core.runtime.SubProgressMonitor
import org.eclipse.jdt.core.IClasspathEntry
import org.eclipse.jdt.core.IJavaProject
import org.eclipse.jdt.core.JavaCore

/**
 * Adapted from http://architecturware.cvs.sourceforge.net/viewvc/architecturware/oaw_v4/core.plugin/plugin.oaw4/main/src/org/openarchitectureware/wizards/EclipseHelper.java?revision=1.13&content-type=text%2Fplain
 */
public class PluginProjectHelper {

	public static val String ISO_8859_1 = "iso-8859-1";

	public def static IProject createPluginProject(String projectName, List<String> srcFolders,
		List<IProject> referencedProjects, Set<String> requiredBundles, List<String> exportedPackages,
		IProgressMonitor progressMonitor) {
			var IProject project = null;

			progressMonitor.beginTask("", 10);
			progressMonitor.subTask("Creating project " + projectName);
			val IWorkspace workspace = ResourcesPlugin.getWorkspace();
			project = workspace.getRoot().getProject(projectName);

			val IJavaProject javaProject = JavaCore.create(project);
			val IProjectDescription projectDescription = ResourcesPlugin.getWorkspace().newProjectDescription(
				projectName);
			projectDescription.setLocation(null);
			if (!project.exists)
				project.create(projectDescription, new SubProgressMonitor(progressMonitor, 1));
			val List<IClasspathEntry> classpathEntries = new ArrayList<IClasspathEntry>();
			if (referencedProjects.size() != 0) {
				val IProject[] referencedProjectsArray = newArrayOfSize(referencedProjects.size())
				projectDescription.setReferencedProjects(referencedProjects.toArray(referencedProjectsArray))
				for (IProject referencedProject : referencedProjects) {
					val IClasspathEntry referencedProjectClasspathEntry = JavaCore.newProjectEntry(
						referencedProject.getFullPath());
					classpathEntries.add(referencedProjectClasspathEntry);
				}
			}
			val String[] natureIdsArray = #[JavaCore.NATURE_ID, "org.eclipse.pde.PluginNature"]

			projectDescription.setNatureIds(natureIdsArray);

			val ICommand java = projectDescription.newCommand();
			java.setBuilderName(JavaCore.BUILDER_ID);

			val ICommand manifest = projectDescription.newCommand();
			manifest.setBuilderName("org.eclipse.pde.ManifestBuilder");

			val ICommand schema = projectDescription.newCommand();
			schema.setBuilderName("org.eclipse.pde.SchemaBuilder");

			val ICommand[] commandArray = #[java, manifest, schema]

			projectDescription.setBuildSpec(commandArray);

			project.open(new SubProgressMonitor(progressMonitor, 1));
			project.setDescription(projectDescription, new SubProgressMonitor(progressMonitor, 1));

			Collections.reverse(srcFolders);
			for (String src : srcFolders) {
				val IFolder srcContainer = project.getFolder(src);
				if (!srcContainer.exists()) {
					srcContainer.create(false, true, new SubProgressMonitor(progressMonitor, 1));
				}
				val IClasspathEntry srcClasspathEntry = JavaCore.newSourceEntry(srcContainer.getFullPath());
				classpathEntries.add(0, srcClasspathEntry);
			}

			classpathEntries.add(JavaCore.newContainerEntry(new Path(
				"org.eclipse.jdt.launching.JRE_CONTAINER/org.eclipse.jdt.internal.debug.ui.launcher.StandardVMType/J2SE-1.5")));
			classpathEntries.add(JavaCore.newContainerEntry(new Path("org.eclipse.pde.core.requiredPlugins")));

			val IClasspathEntry[] classPathEntriesArray = newArrayOfSize(classpathEntries.size())

			javaProject.setRawClasspath(classpathEntries.toArray(classPathEntriesArray),
				new SubProgressMonitor(progressMonitor, 1));

			javaProject.setOutputLocation(new Path("/" + projectName + "/bin"),
				new SubProgressMonitor(progressMonitor, 1));
			createManifest(projectName, requiredBundles, exportedPackages, progressMonitor, project);
			createBuildProps(progressMonitor, project, srcFolders);

			return project;

		}

		public static def IFile createFile(String name, IContainer container, String content,
			IProgressMonitor progressMonitor) {

			val IFile file = container.getFile(new Path(name));
			assertExist(file.getParent());

			val InputStream stream = new ByteArrayInputStream(content.getBytes(file.getCharset()));
			try {
				if (file.exists()) {
					file.setContents(stream, true, true, progressMonitor);
				} else {
					file.create(stream, true, progressMonitor);
				}
				progressMonitor.worked(1);
				return file
			} finally {
				stream.close();
			}
		}

		private static def void createBuildProps(IProgressMonitor progressMonitor, IProject project,
			List<String> srcFolders) {
			val StringBuilder bpContent = new StringBuilder("source.. = ");
			for (val Iterator<String> iterator = srcFolders.iterator(); iterator.hasNext();) {
				bpContent.append(iterator.next()).append('/');
				if (iterator.hasNext()) {
					bpContent.append(",");
				}
			}
			bpContent.append("\n");
			bpContent.append("bin.includes = META-INF/,.\n");
			createFile("build.properties", project, bpContent.toString(), progressMonitor);
		}

		private static def void createManifest(String projectName, Set<String> requiredBundles,
			List<String> exportedPackages, IProgressMonitor progressMonitor, IProject project) throws CoreException {
			val StringBuilder maniContent = new StringBuilder("Manifest-Version: 1.0\n");
			maniContent.append("Bundle-ManifestVersion: 2\n");
			maniContent.append("Bundle-Name: " + projectName + "\n");
			maniContent.append("Bundle-SymbolicName: " + projectName + "; singleton:=true\n");
			maniContent.append("Bundle-Version: 1.0.0\n");
			// maniContent.append("Bundle-Localization: plugin\n");
			if (!requiredBundles.empty)
				maniContent.append("Require-Bundle: ");
			for (String entry : requiredBundles) {

				maniContent.append(" " + entry + ",\n");
			}

			if (exportedPackages != null && !exportedPackages.isEmpty()) {
				maniContent.append("Require-Bundle: " + exportedPackages.get(0));
				val int x = exportedPackages.size()
				for (var int i = 1; i < x; i++) {
					maniContent.append(",\n " + exportedPackages.get(i));
				}
				maniContent.append("\n");
			}

			maniContent.append("Bundle-RequiredExecutionEnvironment: J2SE-1.5\r\n");

			val IFolder metaInf = project.getFolder("META-INF");
			metaInf.create(false, true, new SubProgressMonitor(progressMonitor, 1));

			createFile("MANIFEST.MF", metaInf, maniContent.toString(), progressMonitor);
		}

		private static def void assertExist(
			IContainer c
		) {
			if (!c.exists()) {
				if (!c.getParent().exists()) {
					assertExist(c.getParent());
				}
				if (c instanceof IFolder) {

					(c as IFolder).create(false, true, new NullProgressMonitor());

				}
			}
		}

	}
	