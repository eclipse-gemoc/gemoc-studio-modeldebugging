/*******************************************************************************
 * Copyright (c) 2016 Inria and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Inria - initial API and implementation
 *******************************************************************************/
package org.eclipse.gemoc.executionframework.engine.commons;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.gemoc.executionframework.engine.Activator;
import org.eclipse.gemoc.xdsmlframework.api.core.IExecutionPlatform;
import org.eclipse.gemoc.xdsmlframework.api.core.IModelLoader;
import org.eclipse.gemoc.xdsmlframework.api.core.IRunConfiguration;
import org.eclipse.gemoc.xdsmlframework.api.engine_addon.EngineAddonSortingRule;
import org.eclipse.gemoc.xdsmlframework.api.engine_addon.EngineAddonSortingRule.EngineEvent;
import org.eclipse.gemoc.xdsmlframework.api.engine_addon.IEngineAddon;
import org.eclipse.gemoc.xdsmlframework.api.extensions.engine_addon.EngineAddonSpecificationExtension;
import org.eclipse.gemoc.xdsmlframework.api.extensions.languages.LanguageDefinitionExtension;

public class DefaultExecutionPlatform implements IExecutionPlatform {

	protected IModelLoader _modelLoader;
	protected Collection<IEngineAddon> _addons;

	public DefaultExecutionPlatform(LanguageDefinitionExtension _languageDefinition, IRunConfiguration runConfiguration) throws CoreException {
		_modelLoader = _languageDefinition.instanciateModelLoader();
		_addons = new ArrayList<IEngineAddon>();

		// instanciate addons (this includes language specific addons)
		for (EngineAddonSpecificationExtension extension : runConfiguration.getEngineAddonExtensions()) {
			Activator.getDefault().debug("Enabled addon: "+extension.getName());
			addEngineAddon(extension.instanciateComponent());
		}
	}

	@Override
	public IModelLoader getModelLoader() {
		return _modelLoader;
	}

	@Override
	public Iterable<IEngineAddon> getEngineAddons() {
		synchronized (_addonLock) {
			return Collections.unmodifiableCollection(new ArrayList<IEngineAddon>(_addons));
		}
	}

	@Override
	public void dispose() {
		_addons.clear();
	}

	private Object _addonLock = new Object();

	@Override
	public void addEngineAddon(IEngineAddon addon) {
		synchronized (_addonLock) {
			_addons.add(addon);
		}
	}

	@Override
	public void removeEngineAddon(IEngineAddon addon) {
		synchronized (_addonLock) {
			_addons.remove(addon);
		}
	}

	
	class AddonRuleConnector {
		IEngineAddon before;
		IEngineAddon after;
		
		IEngineAddon ruleOwner;
		EngineAddonSortingRule originRule;
		
		/**
		 * @param before
		 * @param after
		 * @param ruleOwner
		 * @param originRule
		 */
		public AddonRuleConnector(IEngineAddon before, IEngineAddon after, IEngineAddon ruleOwner, EngineAddonSortingRule originRule) {
			super();
			this.before = before;
			this.after = after;
			this.ruleOwner = ruleOwner;
			this.originRule = originRule;
		}

		public IEngineAddon getBefore() {
			return before;
		}

		public IEngineAddon getAfter() {
			return after;
		}

		public IEngineAddon getRuleOwner() {
			return ruleOwner;
		}

		public EngineAddonSortingRule getOriginRule() {
			return originRule;
		}
		
	}
	@Override
	public List<IEngineAddon> getSortedEngineAddons(EngineEvent engineEvent) {
		synchronized (_addonLock) {
			// returns the addons of self in a topologically sorted order
			// Kahn's algorithm from https://en.wikipedia.org/wiki/Topological_sorting
			// L <- Empty list that will contain the sorted elements
			ArrayList<IEngineAddon> resList = new ArrayList<IEngineAddon>(); //Empty list that will contain the sorted elements
			
			// list of applicable connectors
			ArrayList<AddonRuleConnector> consideredAddonRuleConnectors = new ArrayList<AddonRuleConnector>();
			for (IEngineAddon iEngineAddon : _addons) {
				for( EngineAddonSortingRule rule : iEngineAddon.getAddonSortingRules()) {
					if(rule.getEvent() == engineEvent) {
						// this rule applies to the engine event
						
						// find target Addons
						Predicate<IEngineAddon> inList = addon -> rule.getAddonsWithTags().stream().anyMatch(tag -> addon.getTags().contains(tag));
						List<IEngineAddon> targetAddons = _addons.stream().filter(inList).collect(Collectors.toList());
						
						switch(rule.getPriority()) {
						case BEFORE:
							for(IEngineAddon targetAddon : targetAddons) {
								consideredAddonRuleConnectors.add(new AddonRuleConnector(iEngineAddon, targetAddon, iEngineAddon, rule));
							}
							break;
						case AFTER:
							for(IEngineAddon targetAddon : targetAddons) {
								consideredAddonRuleConnectors.add(new AddonRuleConnector(targetAddon, iEngineAddon, iEngineAddon, rule));
							}
							break;
						}
					}
				}
			}
			
			// S <- Set of all nodes with no incoming edge
			List<IEngineAddon> nodeWithIncomingEdge = consideredAddonRuleConnectors.stream().map( connector -> connector.getAfter()).collect(Collectors.toList());
			List<IEngineAddon> nodeWithNoIncomingEdgeSet = _addons.stream().filter(addon -> !nodeWithIncomingEdge.contains(addon)).collect(Collectors.toList());
			// while S is non-empty do
			while(! nodeWithNoIncomingEdgeSet.isEmpty()){
				// remove a node n from S
				IEngineAddon n = nodeWithNoIncomingEdgeSet.get(0);
				nodeWithNoIncomingEdgeSet.remove(0);
				// add n to tail of L
				resList.add(n);
				// for each node m with an edge e from n to m do
				List<AddonRuleConnector> connectorEmittedByN = consideredAddonRuleConnectors.stream().filter(c -> c.getBefore().equals(n)).collect(Collectors.toList());
				for(AddonRuleConnector e : connectorEmittedByN) {
					// remove edge e from the graph
					consideredAddonRuleConnectors.remove(e);
					//self.devDebug('\tsortedSubfunctions remove '+fc.getQualifiedName()+' from the graph');
					IEngineAddon m = e.getAfter();
					// if m has no other incoming edges then
					if(!consideredAddonRuleConnectors.stream().anyMatch(c -> c.getAfter().equals(m))){
						// insert m into S
		            	nodeWithNoIncomingEdgeSet.add(m);
					}
				}
			}
			// if graph has edges then
			if(! consideredAddonRuleConnectors.isEmpty()){
		    	// 	return error   (graph has at least one cycle)
				Activator.getDefault().warn("Found at least one cycle in addon rules for engine event: "+engineEvent.toString());
				Activator.getDefault().warn("Rules involved in cycle(s) are:\n  "+
						consideredAddonRuleConnectors.stream().map(c ->c.getOriginRule().toReadableString()).collect(Collectors.joining("\n  ")));
				for(AddonRuleConnector c : consideredAddonRuleConnectors) {
					resList.add(c.after);
				}
				Activator.getDefault().warn("Fall back to unsorted rules: "+resList.stream().map(addon->addon.getAddonID()).collect(Collectors.joining(", ")));
				
				return Collections.unmodifiableList(resList);
		    }
			else { 
				//  	 return L   (a topologically sorted order)
				Activator.getDefault().debug("sorted addons for engine event "+engineEvent.toString()+
						": "+
						resList.stream().map(addon->addon.getAddonID()).collect(Collectors.joining(", ")));
				return Collections.unmodifiableList(resList);
			}

		}
	}

}
