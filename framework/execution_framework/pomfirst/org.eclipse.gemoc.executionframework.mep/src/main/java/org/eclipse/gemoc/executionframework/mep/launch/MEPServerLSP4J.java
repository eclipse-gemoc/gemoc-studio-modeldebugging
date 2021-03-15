package org.eclipse.gemoc.executionframework.mep.launch;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.gemoc.executionframework.mep.engine.ExecutionEngineSingleton;
import org.eclipse.gemoc.executionframework.mep.engine.IMEPEventListener;
import org.eclipse.gemoc.executionframework.mep.events.Output;
import org.eclipse.gemoc.executionframework.mep.events.Stopped;
import org.eclipse.gemoc.executionframework.mep.events.StoppedReason;
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
import org.eclipse.lsp4j.debug.StoppedEventArguments;
import org.eclipse.lsp4j.debug.TerminateArguments;
import org.eclipse.lsp4j.debug.TerminatedEventArguments;
import org.eclipse.lsp4j.debug.Variable;
import org.eclipse.lsp4j.debug.VariablesArguments;
import org.eclipse.lsp4j.debug.VariablesResponse;
import org.eclipse.lsp4j.jsonrpc.Endpoint;
import org.eclipse.lsp4j.jsonrpc.ResponseErrorException;
import org.eclipse.lsp4j.jsonrpc.json.JsonRpcMethod;
import org.eclipse.lsp4j.jsonrpc.json.JsonRpcMethodProvider;
import org.eclipse.lsp4j.jsonrpc.messages.ResponseError;
import org.eclipse.lsp4j.jsonrpc.services.ServiceEndpoints;

import com.google.common.base.Objects;
import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.Multimap;

abstract public class MEPServerLSP4J implements IModelExecutionProtocolServer, Endpoint, JsonRpcMethodProvider, ModelExecutionClientAware, IMEPEventListener {

	private static final Logger LOG = Logger.getLogger(MEPServerLSP4J.class);

	private Map<String, JsonRpcMethod> supportedMethods;
	
	private final Set<Endpoint> extensions = new HashSet<>();
	private final Multimap<String, Endpoint> extensionProviders = LinkedListMultimap.create();
		
	protected IModelExecutionProtocolClient client;
	
	protected boolean initialized = false;
	protected boolean simulationStarted = false;
	protected MEPLauncherParameters launcherParameters = null;
	protected Breakpoint[] breakpoints = new Breakpoint[0];
	
	public MEPServerLSP4J() {

	}
	
	public void init() {
		ExecutionEngineSingleton.acquireEngine().addMEPEventListener(this);
		ExecutionEngineSingleton.releaseEngine();
	}
	
	public void addExtension(Endpoint extension) {
		this.extensions.add(extension);
	}
	
	@Override
	public Map<String, JsonRpcMethod> supportedMethods() {
		if (supportedMethods != null) {
			return supportedMethods;
		}

		Map<String, JsonRpcMethod> supportedMethods = new LinkedHashMap<>();
		supportedMethods.putAll(ServiceEndpoints.getSupportedMethods(getClass()));

		for(JsonRpcMethod supportedMethod : supportedMethods.values()) {
			LOG.info("supported method "+supportedMethod.getMethodName()+ " "+supportedMethod.getParameterTypes());
		}
		
		Map<String, JsonRpcMethod> extensions = new LinkedHashMap<>();
		for (Endpoint ext : this.extensions) {
			if (ext != null) {
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
		
		this.supportedMethods = supportedMethods;
		return supportedMethods;
	}

	@Override
	public void notify(String method, Object parameter) {
		for (Endpoint endpoint : extensionProviders.get(method)) {
			endpoint.notify(method, parameter);
		}
		LOG.info("notify "+method);
	}

	@Override
	public CompletableFuture<?> request(String method, Object parameter) {
		if (!extensionProviders.containsKey(method)) {
			throw new UnsupportedOperationException("The json request \'" + method + "\' is unknown.");
		}
		for (Endpoint endpoint : extensionProviders.get(method)) {
			return endpoint.request(method, parameter);
		}
		return null;
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
				
				launcherParameters = getDefaultLauncherParameters();
				if (args != null) {
					if (args.containsKey(MEPLaunchParameterKey.noDebug.name())) {
						//TODO: Normal launch if true
					}
					if (args.containsKey(MEPLaunchParameterKey.modelContent.name())) {
						try {
							ResourceSet rs = createResourceSet();
							InputStream in = new ByteArrayInputStream("type foo type bar".getBytes());
							launcherParameters.resourceModel = rs.createResource(URI.createURI("dummy:/example.k3fsm"));
						
							launcherParameters.resourceModel.load(in, rs.getLoadOptions());
						
							//Model model = (Model) resource.getContents().get(0);
							LOG.info("root element in model-content is "+launcherParameters.resourceModel.getContents().get(0));
							
						} catch (IOException e) {
							LOG.error(e.getMessage(), e);
						}
					} else if(args.containsKey(MEPLaunchParameterKey.modelURI.name())) {
						String modelURIString = (String) args.get(MEPLaunchParameterKey.modelURI.name());
						URI uri = URI.createURI(modelURIString);
						ResourceSet rs = createResourceSet();
						launcherParameters.resourceModel = rs.createResource(uri);
						try {
							launcherParameters.resourceModel.load(rs.getLoadOptions());
							launcherParameters.resourceModel.getContents().get(0);
							LOG.info("root element in model uri is "+launcherParameters.resourceModel.getContents().get(0));
						} catch (IOException e) {
							LOG.error(e.getMessage(), e);
						}
					}
					if(args.containsKey(MEPLaunchParameterKey.language.name())) {
						launcherParameters.languageName = (String) args.get(MEPLaunchParameterKey.language.name());
					}				
					if(args.containsKey(MEPLaunchParameterKey.methodEntryPoint.name())) {
						launcherParameters.methodEntryPoint = (String) args.get(MEPLaunchParameterKey.methodEntryPoint.name());
					}
					if(args.containsKey(MEPLaunchParameterKey.initializationMethod.name())) {
						launcherParameters.initializationMethod = (String) args.get(MEPLaunchParameterKey.initializationMethod.name());
					}
					if(args.containsKey(MEPLaunchParameterKey.modelEntryPoint.name())) {
						launcherParameters.modelEntryPoint = (String) args.get(MEPLaunchParameterKey.modelEntryPoint.name());
					}
					if(args.containsKey(MEPLaunchParameterKey.initializationArguments.name())) {
						launcherParameters.initializationMethodArgs = (String) args.get(MEPLaunchParameterKey.initializationArguments.name());
					}
				}
				
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
				
				ExecutionEngineSingleton.acquireEngine().internalContinue();
				ExecutionEngineSingleton.releaseEngine();
				
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
				
				ExecutionEngineSingleton.acquireEngine().internalNext();
				ExecutionEngineSingleton.releaseEngine();
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
				
				ExecutionEngineSingleton.acquireEngine().internalStepIn();
				ExecutionEngineSingleton.releaseEngine();
			}
		});
		return future;
	}
	
	protected void manageStop(StoppedReason stopReason) {
		switch (stopReason) {
			case REACHED_BREAKPOINT:
				StoppedEventArguments stoppedArgsBreakpoint = new StoppedEventArguments();
				stoppedArgsBreakpoint.setReason(stopReason.toString());
				stoppedArgsBreakpoint.setDescription("Reached breakpoint");
				client.stopped(stoppedArgsBreakpoint);
				break;
			case REACHED_NEXT_LOGICAL_STEP:
				StoppedEventArguments stoppedArgsStep = new StoppedEventArguments();
				stoppedArgsStep.setReason(stopReason.toString());
				stoppedArgsStep.setDescription("Reached new logical step");
				client.stopped(stoppedArgsStep);
				break;
			case REACHED_SIMULATION_END:
				TerminatedEventArguments terminatedArgs = new TerminatedEventArguments();
				client.terminated(terminatedArgs);
				simulationStarted = false;
				break;
			case TIME:
				break;
			default:
				break;
		}
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
				
				ExecutionEngineSingleton.acquireEngine().internalStepOut();
				ExecutionEngineSingleton.releaseEngine();
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
					
				List<Breakpoint> bps = new ArrayList<>();
				org.eclipse.gemoc.executionframework.mep.types.SourceBreakpoint[] mepBreakpoints =
						new org.eclipse.gemoc.executionframework.mep.types.SourceBreakpoint[args.getBreakpoints().length];
				int i = 0;
				for (SourceBreakpoint sbp : args.getBreakpoints()) {
					org.eclipse.gemoc.executionframework.mep.types.SourceBreakpoint mepBreakpoint =
							new org.eclipse.gemoc.executionframework.mep.types.SourceBreakpoint(sbp.getLine());
					mepBreakpoints[i++] = mepBreakpoint;
					Breakpoint bp = new Breakpoint();
					bp.setVerified(true);
					bp.setLine(sbp.getLine());
					bps.add(bp);
				}
				ExecutionEngineSingleton.acquireEngine().internalSetBreakpoints(mepBreakpoints);
				ExecutionEngineSingleton.releaseEngine();
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
					ExecutionEngineSingleton.acquireEngine().internalTerminate();
					ExecutionEngineSingleton.releaseEngine();
					simulationStarted = false;
				}
				launchGemocEngine(launcherParameters);
				simulationStarted = true;
				org.eclipse.gemoc.executionframework.mep.types.SourceBreakpoint mepBreakpoints[] =
						new org.eclipse.gemoc.executionframework.mep.types.SourceBreakpoint[breakpoints.length];
				int i = 0;
				for (Breakpoint bp : breakpoints) {
					mepBreakpoints[i++] = new org.eclipse.gemoc.executionframework.mep.types.SourceBreakpoint(bp.getLine().intValue());
				}
				ExecutionEngineSingleton.acquireEngine().internalSetBreakpoints(mepBreakpoints);
				ExecutionEngineSingleton.releaseEngine();
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
				org.eclipse.gemoc.executionframework.mep.types.StackFrame[] mepFrames = ExecutionEngineSingleton.acquireEngine().internalStackTrace();
				ExecutionEngineSingleton.releaseEngine();
				StackFrame[] dapFrames = new StackFrame[mepFrames.length];
				int i = 0;
				for (org.eclipse.gemoc.executionframework.mep.types.StackFrame mepFrame : mepFrames) {
					StackFrame dapFrame = new StackFrame();
					dapFrame.setId((int) mepFrame.getId());
					dapFrame.setName(mepFrame.getName());
					dapFrame.setLine((int) mepFrame.getLine());
					dapFrame.setColumn((int) mepFrame.getColumn());
					dapFrames[i++] = dapFrame;
				}
				response.setStackFrames(dapFrames);
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
				org.eclipse.gemoc.executionframework.mep.types.Variable[] mepVariables = ExecutionEngineSingleton.acquireEngine().internalVariables();
				ExecutionEngineSingleton.releaseEngine();
				Variable[] dapVariables = new Variable[mepVariables.length];
				int i = 0;
				for (org.eclipse.gemoc.executionframework.mep.types.Variable mepVariable : mepVariables) {
					Variable dapVariable = new Variable();
					dapVariable.setName(mepVariable.getName());
					dapVariable.setValue(mepVariable.getValue());
					dapVariables[i++] = dapVariable;
				}
				response.setVariables(dapVariables);
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
				response.setContent(ExecutionEngineSingleton.acquireEngine().internalSource());
				ExecutionEngineSingleton.releaseEngine();
				return response;
			}
		});
		return future;
	}

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
				ExecutionEngineSingleton.acquireEngine().internalTerminate();
				ExecutionEngineSingleton.releaseEngine();
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
	
	/**
	 * create a resource set
	 * by default it only allows to load xmi models
	 * @return
	 */
	public ResourceSet createResourceSet() {
		//ResourceSetFactory.createFactory().createResourceSet(modelURI);
		return new ResourceSetImpl();
	}
	
	public MEPLauncherParameters getDefaultLauncherParameters() {
		return new MEPLauncherParameters();
	}

	public void launchGemocEngine(MEPLauncherParameters parameters) {
		ExecutionEngineSingleton.acquireEngine().internalLaunchEngine(parameters);
		ExecutionEngineSingleton.releaseEngine();
	}
	
	@Override
	public void outputReceived(Output event) {
		this.sendOutput(event.getOutput());
	}
	
	@Override
	public void stopReceived(Stopped event) {
		this.manageStop(event.getReason());
	}
	
}
