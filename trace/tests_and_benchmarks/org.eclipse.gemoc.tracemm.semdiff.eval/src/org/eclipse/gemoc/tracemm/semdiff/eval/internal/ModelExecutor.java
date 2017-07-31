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
package org.eclipse.gemoc.tracemm.semdiff.eval.internal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.junit.Assert;
import org.modelexecution.xmof.Semantics.CommonBehaviors.BasicBehaviors.ParameterValue;
import org.modelexecution.xmof.configuration.ConfigurationObjectMap;
import org.modelexecution.xmof.states.builder.StatesBuilder;
import org.modelexecution.xmof.states.builder.util.StatesBuilderUtil;
import org.modelexecution.xmof.vm.XMOFVirtualMachine;
import org.modelexecution.xmof.vm.util.EMFUtil;
import org.modelexecution.xmof.vm.util.XMOFUtil;

import org.eclipse.gemoc.tracemm.common.ConfigurableStatesBuilder;
import org.eclipse.gemoc.tracemm.common.GenericStatesBuilderConfigurationDynamicEObj;

public class ModelExecutor {

	private static final String CONFIGURATIONMODEL_PATH = "configurationmodel.xmi";

	private ResourceSet resourceSet;
	private TransactionalEditingDomain editingDomain;

	private Resource modelResource;
	private Resource configurationResource;
	private Resource parameterDefintionResource;
	private Resource configurationModelResource;

	private ConfigurationObjectMap configurationObjectMap;

	public ModelExecutor() {
		setupResourceSet();
	}

	private void setupResourceSet() {
		resourceSet = EMFUtil.createResourceSet();
		EMFUtil.registerXMIFactory(resourceSet);
		EMFUtil.registerEcoreFactory(resourceSet);
		editingDomain = EMFUtil.createTransactionalEditingDomain(resourceSet);
	}

	public EObject execute(String modelPath, String parameterDefinitionPath,
			String metamodelPath, String configurationPath,
			String tracemetamodelPath, String tracemodelPath,
			String... additionalModelInputPaths) {
		XMOFVirtualMachine vm = setupVM(modelPath, parameterDefinitionPath,
				metamodelPath, configurationPath, additionalModelInputPaths);

		ConfigurableStatesBuilder statesBuilderDomainSpecific = null;
		StatesBuilder statesBuilderGeneric = null;

		if (tracemetamodelPath != null) {
			statesBuilderDomainSpecific = setupDomainSpecificStatesBuilder(
					metamodelPath, tracemetamodelPath, vm);
		} else {
			statesBuilderGeneric = setupGenericStatesBuilder(vm, true);
		}

		vm.execute();

		EObject trace = statesBuilderGeneric != null ? statesBuilderGeneric.getStateSystem()
				: statesBuilderDomainSpecific.getConf().getTrace();
		persistTracemodel(tracemodelPath, trace);
		unloadResources();
		return trace;
	}
	
	private void unloadResources() {
		for(Resource resource : resourceSet.getResources()) {
			try {
				resource.unload();
			} catch(Exception e) {
			}
		}
		resourceSet = null;
	}

	private StatesBuilder setupGenericStatesBuilder(XMOFVirtualMachine vm, boolean filterStates) {
		if(!filterStates)
			return StatesBuilderUtil.createStatesBuilder(vm,
				configurationModelResource);
		else
			return setupFilteringGenericStatesBuilder(vm);
	}

	private StatesBuilder setupFilteringGenericStatesBuilder(
			XMOFVirtualMachine vm) {
		StatesBuilder statesBuilder = new CustomStatesBuilder(
				configurationModelResource);
		statesBuilder.setVM(vm);
		vm.setSynchronizeModel(true);
		vm.addRawExecutionEventListener(statesBuilder);
		return statesBuilder;
	}

	private ConfigurableStatesBuilder setupDomainSpecificStatesBuilder(
			String metamodelPath, String tracemetamodelPath,
			XMOFVirtualMachine vm) {
		ConfigurableStatesBuilder statesBuilderDomainSpecific;
		Resource tracemetamodelResource = Util.loadResource(resourceSet,
				tracemetamodelPath);
		GenericStatesBuilderConfigurationDynamicEObj conf = new GenericStatesBuilderConfigurationDynamicEObj(
				tracemetamodelResource, metamodelPath, configurationResource,
				configurationObjectMap);
		statesBuilderDomainSpecific = new ConfigurableStatesBuilder(
				configurationModelResource, conf);
		statesBuilderDomainSpecific.setVM(vm);
		vm.addRawExecutionEventListener(statesBuilderDomainSpecific);
		vm.setSynchronizeModel(true);
		return statesBuilderDomainSpecific;
	}

	private XMOFVirtualMachine setupVM(String modelPath,
			String parameterDefinitionPath, String metamodelPath,
			String configurationPath, String... additionalModelInputPaths) {
		loadMetamodel(metamodelPath);

		modelResource = Util.loadResource(resourceSet, modelPath);

		configurationResource = Util.loadResource(resourceSet,
				configurationPath);

		parameterDefintionResource = Util.loadResource(resourceSet,
				parameterDefinitionPath);

		List<Resource> additionalModelInputResources = new ArrayList<Resource>();
		for (String additionalModelInputPath : additionalModelInputPaths) {
			additionalModelInputResources.add(Util.loadResource(resourceSet,
					additionalModelInputPath));
		}

		EcoreUtil.resolveAll(resourceSet);

		XMOFVirtualMachine vm = createVM(modelResource, configurationResource,
				parameterDefintionResource,
				additionalModelInputResources
						.toArray(new Resource[additionalModelInputResources
								.size()]));

		return vm;
	}

	private void loadMetamodel(String metamodelPath) {
		if (metamodelPath != null && !metamodelPath.startsWith("http://"))
			EMFUtil.loadMetamodel(resourceSet,
					EMFUtil.createFileURI(metamodelPath));
	}

	private XMOFVirtualMachine createVM(Resource modelResource,
			Resource configurationResource,
			Resource parameterDefintionResource,
			Resource... additionalModelInputResources) {
		configurationObjectMap = createConfigurationObjectMap(modelResource,
				configurationResource, parameterDefintionResource,
				additionalModelInputResources);

		configurationModelResource = EMFUtil.createResource(resourceSet,
				editingDomain, EMFUtil.createFileURI(CONFIGURATIONMODEL_PATH),
				configurationObjectMap.getConfigurationObjects());

		List<ParameterValue> parameterValueConfiguration = XMOFUtil
				.getParameterValueConfiguration(parameterDefintionResource,
						configurationObjectMap);

		XMOFVirtualMachine vm = XMOFUtil.createXMOFVirtualMachine(resourceSet,
				editingDomain, configurationModelResource,
				parameterValueConfiguration);

		return vm;
	}

	ConfigurationObjectMap createConfigurationObjectMap(Resource modelResource,
			Resource configurationResource,
			Resource parameterDefintionResource,
			Resource... additionalModelInputResources) {
		ConfigurationObjectMap configurationObjectMap = XMOFUtil
				.createConfigurationObjectMap(configurationResource,
						modelResource, parameterDefintionResource,
						additionalModelInputResources);
		return configurationObjectMap;
	}

	private void persistTracemodel(String tracemodelPath, EObject stateSystem) {
		URI outputUri = EMFUtil.createFileURI(tracemodelPath);
		Resource traceResource = resourceSet.createResource(outputUri);

		Command cmd = new AddCommand(editingDomain,
				traceResource.getContents(), stateSystem);
		editingDomain.getCommandStack().execute(cmd);

		HashMap<String, Object> options = new HashMap<String, Object>();
		options.put(XMIResource.OPTION_SCHEMA_LOCATION, true);

		// TODO what is the matter with hrefs?
		options.put(XMIResource.OPTION_PROCESS_DANGLING_HREF,
				XMIResource.OPTION_PROCESS_DANGLING_HREF_DISCARD);
		try {
			traceResource.save(options);
		} catch (IOException e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

}
