package org.eclipse.gemoc.executionframework.mep.launch;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.gemoc.executionframework.mep.services.IModelExecutionProtocolClient;
import org.eclipse.gemoc.executionframework.mep.services.IModelExecutionProtocolServer;
import org.eclipse.gemoc.executionframework.mep.services.ModelExecutionClientAware;
import org.eclipse.lsp4j.debug.Breakpoint;
import org.eclipse.lsp4j.debug.Capabilities;
import org.eclipse.lsp4j.debug.ContinueArguments;
import org.eclipse.lsp4j.debug.ContinueResponse;
import org.eclipse.lsp4j.debug.InitializeRequestArguments;
import org.eclipse.lsp4j.debug.NextArguments;
import org.eclipse.lsp4j.debug.OutputEventArguments;
import org.eclipse.lsp4j.debug.RestartArguments;
import org.eclipse.lsp4j.debug.SetBreakpointsArguments;
import org.eclipse.lsp4j.debug.SetBreakpointsResponse;
import org.eclipse.lsp4j.debug.SourceArguments;
import org.eclipse.lsp4j.debug.SourceBreakpoint;
import org.eclipse.lsp4j.debug.SourceResponse;
import org.eclipse.lsp4j.debug.StackFrame;
import org.eclipse.lsp4j.debug.StackTraceArguments;
import org.eclipse.lsp4j.debug.StackTraceResponse;
import org.eclipse.lsp4j.debug.StepInArguments;
import org.eclipse.lsp4j.debug.StepOutArguments;
import org.eclipse.lsp4j.debug.TerminateArguments;
import org.eclipse.lsp4j.debug.Variable;
import org.eclipse.lsp4j.debug.VariablesArguments;
import org.eclipse.lsp4j.debug.VariablesResponse;
import org.eclipse.lsp4j.jsonrpc.Endpoint;
import org.eclipse.lsp4j.jsonrpc.ResponseErrorException;
import org.eclipse.lsp4j.jsonrpc.json.JsonRpcMethod;
import org.eclipse.lsp4j.jsonrpc.json.JsonRpcMethodProvider;
import org.eclipse.lsp4j.jsonrpc.messages.ResponseError;
import org.eclipse.lsp4j.jsonrpc.services.ServiceEndpoints;

abstract public class GemocMEPServerImpl implements IModelExecutionProtocolServer, Endpoint, JsonRpcMethodProvider, ModelExecutionClientAware {

	private static final Logger LOG = Logger.getLogger(GemocMEPServerImpl.class);

	private Map<String, JsonRpcMethod> supportedMethods;
	
	//private final Multimap<String, Endpoint> extensionProviders = LinkedListMultimap.create();
	
	protected IModelExecutionProtocolClient client;
	
	protected boolean initialized = false;
	protected boolean simulationStarted = false;
	protected MEPLauncherParameters launcherParameters = null;
	protected Breakpoint[] breakpoints = new Breakpoint[0];
	
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
				if (initialized) {
					ResponseError error = new ResponseError();
					error.setMessage("Server already initialized");
					throw new ResponseErrorException(error);
				}
				
				Capabilities capabilities = new Capabilities();
				capabilities.setSupportsTerminateRequest(true);
				// TODO declare here DAP capabilities
				
				client.initialized();
				initialized = true;
				
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
				if (!initialized) {
					ResponseError error = new ResponseError();
					error.setMessage("Server not initialized");
					throw new ResponseErrorException(error);
				}
				if (simulationStarted) {
					ResponseError error = new ResponseError();
					error.setMessage("Another simulation is running");
					throw new ResponseErrorException(error);
				}
				
				// TODO launch the engine
			//	throw new Exception("failed to launch with args\'" + args + "\'.");
				LOG.info("launch received with args "+args);
				
				Resource res =  null;
				if (args.containsKey(MEPLaunchParameterKey.noDebug.name())) {
					//TODO: Normal launch if true
				}
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
				
				launcherParameters = new MEPLauncherParameters();
				launcherParameters.resourceModel = res;
				launcherParameters.modelEntryPoint = modelEntryPoint; 
				launcherParameters.methodEntryPoint = methodEntryPoint;
				launcherParameters.initializationMethod = initializationMethod;
				launcherParameters.initializationMethodArgs = initializationArguments;
				launchGemocEngine(launcherParameters);
				
				simulationStarted = true;
			}
		});
		
		return future;
	}
	
	@Override
	public CompletableFuture<ContinueResponse> continue_(ContinueArguments args) {
		CompletableFuture<ContinueResponse> future = CompletableFuture.supplyAsync(new Supplier<ContinueResponse>() {
			@Override
		    public ContinueResponse get() {
				if (!simulationStarted) {
					ResponseError error = new ResponseError();
					error.setMessage("Simulation not started");
					throw new ResponseErrorException(error);
				}
				
				internalContinue();
				
				ContinueResponse response = new ContinueResponse();
				return response;
			}
		});
		return future;
	}
	
	@Override
	public CompletableFuture<Void> next(NextArguments args) {
		CompletableFuture<Void> future = CompletableFuture.runAsync(new Runnable() {
			@Override
		    public void run() {
				if (!simulationStarted) {
					ResponseError error = new ResponseError();
					error.setMessage("Simulation not started");
					throw new ResponseErrorException(error);
				}
				
				internalNext();
			}
		});
		return future;
	}
	
	@Override
	public CompletableFuture<Void> stepIn(StepInArguments args) {
		CompletableFuture<Void> future = CompletableFuture.runAsync(new Runnable() {
			@Override
		    public void run() {
				if (!simulationStarted) {
					ResponseError error = new ResponseError();
					error.setMessage("Simulation not started");
					throw new ResponseErrorException(error);
				}
				
				internalStepIn();
			}
		});
		return future;
	}
	
	@Override
	public CompletableFuture<Void> stepOut(StepOutArguments args) {
		CompletableFuture<Void> future = CompletableFuture.runAsync(new Runnable() {
			@Override
		    public void run() {
				if (!simulationStarted) {
					ResponseError error = new ResponseError();
					error.setMessage("Simulation not started");
					throw new ResponseErrorException(error);
				}
				
				internalStepOut();
			}
		});
		return future;
	}
	
	@Override
	public CompletableFuture<SetBreakpointsResponse> setBreakpoints(SetBreakpointsArguments args) {
		CompletableFuture<SetBreakpointsResponse> future = CompletableFuture.supplyAsync(new Supplier<SetBreakpointsResponse>() {
			@Override
		    public SetBreakpointsResponse get() {
				//TODO: Manage different sources for breakpoints
				SetBreakpointsResponse response = new SetBreakpointsResponse();
	
				internalClearBreakpoints();
				
				List<Breakpoint> bps = new ArrayList<>();
				for (SourceBreakpoint sbp : args.getBreakpoints()) {
					Breakpoint bp = new Breakpoint();
					bp.setVerified(true);
					bp.setLine(sbp.getLine());
					internalToggleBreakpoint(sbp.getLine().intValue());
					bps.add(bp);
				}
				breakpoints = bps.toArray(new Breakpoint[0]);
				response.setBreakpoints(breakpoints);
				
				return response;
			}
		});
		return future;
	}
	
	@Override
	public CompletableFuture<Void> restart(RestartArguments args) {
		CompletableFuture<Void> future = CompletableFuture.runAsync(new Runnable() {
			@Override
		    public void run() {
				if (launcherParameters == null) {
					ResponseError error = new ResponseError();
					error.setMessage("Simulation was not started before");
					throw new ResponseErrorException(error);
				}
				if (simulationStarted) {
					internalTerminate();
					simulationStarted = false;
				}
				launchGemocEngine(launcherParameters);
				simulationStarted = true;
				for (Breakpoint bp : breakpoints) {
					internalToggleBreakpoint(bp.getLine().intValue());
				}
			}
		});
		return future;
	}

	@Override
	public CompletableFuture<StackTraceResponse> stackTrace(StackTraceArguments args) {
		CompletableFuture<StackTraceResponse> future = CompletableFuture.supplyAsync(new Supplier<StackTraceResponse>() {
			@Override
		    public StackTraceResponse get() {
				if (!simulationStarted) {
					ResponseError error = new ResponseError();
					error.setMessage("Simulation not started");
					throw new ResponseErrorException(error);
				}
				StackTraceResponse response = new StackTraceResponse();
				response.setStackFrames(internalStackTrace());
				return response;
			}
		});
		return future;
	}

	@Override
	public CompletableFuture<VariablesResponse> variables(VariablesArguments args) {
		CompletableFuture<VariablesResponse> future = CompletableFuture.supplyAsync(new Supplier<VariablesResponse>() {
			@Override
		    public VariablesResponse get() {
				if (!simulationStarted) {
					ResponseError error = new ResponseError();
					error.setMessage("Simulation not started");
					throw new ResponseErrorException(error);
				}
				VariablesResponse response = new VariablesResponse();
				response.setVariables(internalVariables());
				return response;
			}
		});
		return future;
	}
	
	@Override
	public CompletableFuture<SourceResponse> source(SourceArguments args) {
		CompletableFuture<SourceResponse> future = CompletableFuture.supplyAsync(new Supplier<SourceResponse>() {
			@Override
		    public SourceResponse get() {
				if (!simulationStarted) {
					ResponseError error = new ResponseError();
					error.setMessage("Simulation not started");
					throw new ResponseErrorException(error);
				}
				SourceResponse response = new SourceResponse();
				response.setContent(internalSource());
				return response;
			}
		});
		return future;
	}

	protected abstract void internalNext();
	
	protected abstract void internalStepIn();

	protected abstract void internalStepOut();

	@Override
	public CompletableFuture<Void> terminate(TerminateArguments args) {
		CompletableFuture<Void> future = CompletableFuture.runAsync(new Runnable() {
			@Override
		    public void run() {
				if (!simulationStarted) {
					ResponseError error = new ResponseError();
					error.setMessage("Simulation not started");
					throw new ResponseErrorException(error);
				}
				internalTerminate();
				simulationStarted = false;
			}
		});
		return future;
	}

	public void sendOutput(String output) {
		OutputEventArguments args = new OutputEventArguments();
		args.setCategory("stdout");
		args.setOutput(output);
		client.output(args);
	}
	
	protected abstract void internalClearBreakpoints();
	
	protected abstract void internalToggleBreakpoint(int line);
	
	protected abstract void internalTerminate();
	
	protected abstract void internalContinue();
	
	protected abstract Variable[] internalVariables();
	
	protected abstract StackFrame[] internalStackTrace();
	
	protected abstract String internalSource();
	
	/**
	 * create a resource set
	 * by default it only allows to load xmi models
	 * @return
	 */
	public ResourceSet createResourceSet() {
		//ResourceSetFactory.createFactory().createResourceSet(modelURI);
		return new ResourceSetImpl();
	}

	public void launchGemocEngine(MEPLauncherParameters parameters) {
		launchGemocEngine(parameters.resourceModel, parameters.modelEntryPoint, parameters.methodEntryPoint,
				parameters.initializationMethod, parameters.initializationMethodArgs);
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
	abstract public void launchGemocEngine(Resource resourceModel, String modelEntryPoint,
			String methodEntryPoint, String initializationMethod, String initializationMethodArgs);
}
