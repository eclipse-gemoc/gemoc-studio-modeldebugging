package org.eclipse.gemoc.executionframework.engine.ui.concurrency.strategies

import java.beans.PropertyChangeListener
import java.util.Set
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EPackage
import org.eclipse.gemoc.xdsmlframework.api.core.IExecutionEngine

interface LaunchConfigurationContext {
	
	/**
	 * Identification string for metamodels property.
	 */
	val METAMODELS = "metamodels"
	
	/**
	 * Identification string for semantics property.
	 */
	val SEMANTICS = "semantics"
	
	/**
	 * Identification string for models property.
	 */
	val MODELS = "models"
	
	/**
	 * Return the metamodel currently specified in the launch configuration, if any. 
	 * 
	 * Multiple calls will return the same result unless the user has made a change to their selection in the meantime.
	 */
	def Set<EPackage> getMetamodels()
	
	/**
	 * Register a listener to be informed on any changes of the metamodel selected in this launch configuration. 
	 */
	def void addMetamodelChangeListener(PropertyChangeListener pcl)
	
	/**
	 * Return the semantics (rules) currently specified in the launch configuration, if any. 
	 * 
	 * Multiple calls will return the same result unless the user has made a change to their selection in the meantime.
	 */
	def Set<String> getSemantics()

	/**
	 * Register a listener to be informed on any changes of the semantics (rule set) selected in this launch configuration. 
	 */
	def void addSemanticsChangeListener(PropertyChangeListener pcl)	
	
	/**
	 * Return the root of the model currently under simulation. 
	 * 
	 */
	def EObject getModelRoot()
	
	/**
	 * Return simulation engine, if any. 
	 * 
	 */
	def IExecutionEngine<?> getEngine()
}
