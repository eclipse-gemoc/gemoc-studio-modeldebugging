package org.eclipse.gemoc.executionframework.mep.launch;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.function.Supplier;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.gemoc.executionframework.mep.services.IModelExecutionProtocolClient;
import org.eclipse.gemoc.executionframework.mep.services.IModelExecutionProtocolServer;
import org.eclipse.gemoc.executionframework.mep.services.ModelExecutionClientAware;
import org.eclipse.lsp4j.debug.Capabilities;
import org.eclipse.lsp4j.debug.InitializeRequestArguments;
import org.eclipse.lsp4j.debug.NextArguments;
import org.eclipse.lsp4j.debug.TerminateArguments;
import org.eclipse.lsp4j.jsonrpc.Endpoint;
import org.eclipse.lsp4j.jsonrpc.json.JsonRpcMethod;
import org.eclipse.lsp4j.jsonrpc.json.JsonRpcMethodProvider;
import org.eclipse.lsp4j.jsonrpc.messages.Either;
import org.eclipse.lsp4j.jsonrpc.services.ServiceEndpoints;

import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.Pair;

import com.google.common.base.Objects;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.Multimap;
import com.google.inject.Inject;

abstract public class GemocMEPServerImpl implements IModelExecutionProtocolServer, Endpoint, JsonRpcMethodProvider, ModelExecutionClientAware {

	private static final Logger LOG = Logger.getLogger(GemocMEPServerImpl.class);

	private Map<String, JsonRpcMethod> supportedMethods;
	
	//private final Multimap<String, Endpoint> extensionProviders = LinkedListMultimap.create();
	
	private IModelExecutionProtocolClient client;
	
	
	@Override
	public Map<String, JsonRpcMethod> supportedMethods() {
		if (supportedMethods != null) {
			return supportedMethods;
		}
		//synchronized (extensionProviders) {
			Map<String, JsonRpcMethod> supportedMethods = new LinkedHashMap<>();
			supportedMethods.putAll(ServiceEndpoints.getSupportedMethods(getClass()));

			for(JsonRpcMethod supportedMethod : supportedMethods.values()) {
				LOG.info("supported method "+supportedMethod.getMethodName()+ " "+supportedMethod.getParameterTypes());
			}
			/*
			Map<String, JsonRpcMethod> extensions = new LinkedHashMap<>();
			for (IResourceServiceProvider resourceServiceProvider : getAllLanguages()) {
				ILanguageServerExtension ext = resourceServiceProvider.get(ILanguageServerExtension.class);
				if (ext != null) {
					ext.initialize(access);
					Map<String, JsonRpcMethod> supportedExtensions = ext instanceof JsonRpcMethodProvider
							? ((JsonRpcMethodProvider) ext).supportedMethods()
							: ServiceEndpoints.getSupportedMethods(ext.getClass());
					for (Map.Entry<String, JsonRpcMethod> entry : supportedExtensions.entrySet()) {
						if (supportedMethods.containsKey(entry.getKey())) {
							LOG.error("The json rpc method \'" + entry.getKey()
									+ "\' can not be an extension as it is already defined in the LSP standard.");
						} else {
							JsonRpcMethod existing = extensions.put(entry.getKey(), entry.getValue());
							if (existing != null && !Objects.equal(existing, entry.getValue())) {
								LOG.error("An incompatible LSP extension \'" + entry.getKey()
										+ "\' has already been registered. Using 1 ignoring 2. \n1 : " + existing
										+ " \n2 : " + entry.getValue());
								extensions.put(entry.getKey(), existing);
							} else {
								Endpoint endpoint = ServiceEndpoints.toEndpoint(ext);
								extensionProviders.put(entry.getKey(), endpoint);
								supportedMethods.put(entry.getKey(), entry.getValue());
							}
						}
					}
				}
			}
			*/
			this.supportedMethods = supportedMethods;
			return supportedMethods;
		//}
	}

	@Override
	public void notify(String method, Object parameter) {
	/*	for (Endpoint endpoint : extensionProviders.get(method)) {
			try {
				endpoint.notify(method, parameter);
			} catch (UnsupportedOperationException e) {
				if (e != ILanguageServerExtension.NOT_HANDLED_EXCEPTION) {
					throw e;
				}
			}
		}*/
		LOG.info("notify "+method);
	}

	@Override
	public CompletableFuture<?> request(String method, Object parameter) {
	/*	if (!extensionProviders.containsKey(method)) {
			throw new UnsupportedOperationException("The json request \'" + method + "\' is unknown.");
		}
		for (Endpoint endpoint : extensionProviders.get(method)) {
			try {
				return endpoint.request(method, parameter);
			} catch (UnsupportedOperationException e) {
				if (e != ILanguageServerExtension.NOT_HANDLED_EXCEPTION) {
					throw e;
				}
			}
		}*/
		throw new UnsupportedOperationException("The json request \'" + method + "\' is unknown.");
		//return null;
	}
	
	@Override
	public void connect(IModelExecutionProtocolClient client) {
		this.client = client;
	}

	

	// ****************
	// * DAP protocol *
	// ****************
	@Override
	public CompletableFuture<Capabilities> initialize(InitializeRequestArguments args) {
		LOG.info("CompletableFuture<Capabilities> initialize(InitializeRequestArguments args)");
		CompletableFuture<Capabilities> future = CompletableFuture.supplyAsync(new Supplier<Capabilities>() {
			@Override
			public Capabilities get() {
				Capabilities capabilities = new Capabilities();
				capabilities.setSupportsTerminateRequest(true);
				// TODO declare here DAP capabilities
				return capabilities;
			}
		});
		return future;
	}

	
	
	@Override
	public CompletableFuture<Void> launch(Map<String, Object> args) {
		CompletableFuture<Void> future = CompletableFuture.runAsync(new Runnable() {
			@Override
		    public void run() {
				// TODO launch the engine
			//	throw new Exception("failed to launch with args\'" + args + "\'.");
				LOG.info("launch received with args "+args);
				
				Resource res =  null;
				if (args.containsKey(MEPLaunchParameterKey.modelContent.name())) {
					try {
						ResourceSet rs = createResourceSet();
						InputStream in = new ByteArrayInputStream("type foo type bar".getBytes());
						res = rs.createResource(URI.createURI("dummy:/example.k3fsm"));
					
						res.load(in, rs.getLoadOptions());
					
						//Model model = (Model) resource.getContents().get(0);
						LOG.info("root element in model-content is "+res.getContents().get(0));
						
					} catch (IOException e) {
						LOG.error(e.getMessage(), e);
					}
				}
				if(args.containsKey(MEPLaunchParameterKey.modelURI.name())) {
					String modelURIString = (String) args.get(MEPLaunchParameterKey.modelURI.name());
					URI uri = URI.createURI(modelURIString);
					ResourceSet rs = createResourceSet();
					res = rs.createResource(uri);
					try {
						res.load(rs.getLoadOptions());
						res.getContents().get(0);
					LOG.info("root element in model uri is "+res.getContents().get(0));
					} catch (IOException e) {
						LOG.error(e.getMessage(), e);
					}
				}
				String languageName = "K3FSM";
				if(args.containsKey(MEPLaunchParameterKey.language.name())) {
					languageName = (String) args.get(MEPLaunchParameterKey.language.name());
				}
				
				String methodEntryPoint = "";
				if(args.containsKey(MEPLaunchParameterKey.methodEntryPoint.name())) {
					methodEntryPoint = (String) args.get(MEPLaunchParameterKey.methodEntryPoint.name());
				}
				String initializationMethod = "";
				if(args.containsKey(MEPLaunchParameterKey.initializationMethod.name())) {
					initializationMethod = (String) args.get(MEPLaunchParameterKey.initializationMethod.name());
				}

				String modelEntryPoint = "/";
				if(args.containsKey(MEPLaunchParameterKey.modelEntryPoint.name())) {
					modelEntryPoint = (String) args.get(MEPLaunchParameterKey.modelEntryPoint.name());
				}
				String initializationArguments = "/";
				if(args.containsKey(MEPLaunchParameterKey.initializationArguments.name())) {
					initializationArguments = (String) args.get(MEPLaunchParameterKey.initializationArguments.name());
				}
				launchGemocEngine(res, 
						languageName, 
						modelEntryPoint, 
						methodEntryPoint, //methodEntryPoint, 
						initializationMethod, //initializationMethod, 
						initializationArguments); //initializationMethodArgs
			}
		});
		
		return future;
	}

	
	
	@Override
	public CompletableFuture<Void> next(NextArguments args) {
		CompletableFuture<Void> future = CompletableFuture.runAsync(new Runnable() {
			@Override
		    public void run() {
				internalNext();
			}
		});
		return future;
	}
	
	protected abstract void internalNext();

	@Override
	public CompletableFuture<Void> terminate(TerminateArguments args) {
		CompletableFuture<Void> future = CompletableFuture.runAsync(new Runnable() {
			@Override
		    public void run() {
				// TODO kill the engine
				internalTerminate();
			}
		});
		return future;
	}

	protected abstract void internalTerminate();
	
	/**
	 * create a resource set
	 * by default it only allows to load xmi models
	 * @return
	 */
	public ResourceSet createResourceSet() {
		//ResourceSetFactory.createFactory().createResourceSet(modelURI);
		return new ResourceSetImpl();
	}

	
	
	/**
	 * 
	 * @param resourceModel
	 * @param selectedLanguage
	 * @param modelEntryPoint
	 * @param methodEntryPoint
	 * @param initializationMethod
	 * @param initializationMethodArgs
	 */
	abstract public  void launchGemocEngine(Resource resourceModel, String selectedLanguage, String modelEntryPoint,
			String methodEntryPoint, String initializationMethod, String initializationMethodArgs);
}
