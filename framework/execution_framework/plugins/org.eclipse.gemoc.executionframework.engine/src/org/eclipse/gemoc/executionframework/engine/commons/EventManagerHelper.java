package org.eclipse.gemoc.executionframework.engine.commons;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.gemoc.dsl.Dsl;
import org.eclipse.gemoc.dsl.Entry;
import org.eclipse.gemoc.executionframework.event.manager.GenericEventManager;
import org.eclipse.gemoc.executionframework.event.manager.IImplementationRelationship;
import org.eclipse.gemoc.executionframework.event.manager.ISubtypingRelationship;

public class EventManagerHelper {

	public static GenericEventManager getEventManager(String languageName) {
		final Dsl dsl = DslHelper.load(languageName);
		final Entry implementationRelationshipEntry = dsl.getEntry("implementation_relationships");
		if (implementationRelationshipEntry != null) {
			final Entry subtypingRelationshipEntry = dsl.getEntry("subtyping_relationships");
			List<IImplementationRelationship> implementationRelationships = Arrays.stream(implementationRelationshipEntry.getValue()
					.split(",")).map(s -> getImplementationRelationship(s.trim()))
					.filter(r -> r != null).collect(Collectors.toList());
			List<ISubtypingRelationship> subtypingRelationships = subtypingRelationshipEntry != null
					? Arrays.stream(subtypingRelationshipEntry.getValue()
							.split(",")).map(s -> getSubtypingRelationship(s.trim()))
							.filter(r -> r != null).collect(Collectors.toList())
					: Collections.emptyList();
			return new GenericEventManager(languageName, implementationRelationships, subtypingRelationships);
		}

		return null;
	}

	private static IImplementationRelationship getImplementationRelationship(String relationshipId) {
		IConfigurationElement[] implementationRelationships = Platform.getExtensionRegistry()
				.getConfigurationElementsFor("org.eclipse.gemoc.executionframework.event.implementationrelationship");
		return Arrays.stream(implementationRelationships).filter(r -> r.getAttribute("id").equals(relationshipId)).findFirst().map(c -> {
			try {
				return (IImplementationRelationship) c.createExecutableExtension("class");
			} catch (CoreException e) {
				e.printStackTrace();
			}
			return null;
		}).orElse(null);
	}

	private static ISubtypingRelationship getSubtypingRelationship(String relationshipId) {
		IConfigurationElement[] subtypingRelationships = Platform.getExtensionRegistry()
				.getConfigurationElementsFor("org.eclipse.gemoc.executionframework.event.subtypingrelationship");
		return Arrays.stream(subtypingRelationships).filter(r -> r.getAttribute("id").equals(relationshipId)).findFirst().map(c -> {
			try {
				return (ISubtypingRelationship) c.createExecutableExtension("Class");
			} catch (CoreException e) {
				e.printStackTrace();
			}
			return null;
		}).orElse(null);
	}
}
