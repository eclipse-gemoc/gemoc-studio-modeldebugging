package org.eclipse.gemoc.executionframework.mep.launch;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.function.Consumer;
import java.util.function.Function;

import org.eclipse.gemoc.executionframework.mep.services.IModelExecutionProtocolClient;
import org.eclipse.gemoc.executionframework.mep.services.IModelExecutionProtocolServer;
import org.eclipse.lsp4j.jsonrpc.Endpoint;
import org.eclipse.lsp4j.jsonrpc.Launcher;
import org.eclipse.lsp4j.jsonrpc.MessageConsumer;
import org.eclipse.lsp4j.jsonrpc.RemoteEndpoint;
import org.eclipse.lsp4j.jsonrpc.debug.DebugRemoteEndpoint;
import org.eclipse.lsp4j.jsonrpc.debug.json.DebugMessageJsonHandler;
import org.eclipse.lsp4j.jsonrpc.json.JsonRpcMethod;
import org.eclipse.lsp4j.jsonrpc.json.MessageJsonHandler;
import org.eclipse.lsp4j.jsonrpc.json.StreamMessageConsumer;
import org.eclipse.lsp4j.jsonrpc.services.ServiceEndpoints;
import org.eclipse.lsp4j.jsonrpc.validation.ReflectiveMessageValidator;

import com.google.gson.GsonBuilder;

/**
 * Specialized launcher for the MEP Server Protocol.
 */
public class MEPLauncher {
	
	private MEPLauncher() {}
	
	
	
	/**
	 * Create a new Launcher for a given local service object, a given remote
	 * interface and an input and output stream.
	 *
	 * @param localService
	 *            - an object on which classes RPC methods are looked up
	 * @param remoteInterface
	 *            - an interface on which RPC methods are looked up
	 * @param in
	 *            - inputstream to listen for incoming messages
	 * @param out
	 *            - outputstream to send outgoing messages
	 */
	public static <T> Launcher<T> createLauncher(Object localService, Class<T> remoteInterface, InputStream in,
			OutputStream out) {
		return new Builder<T>()
				.setLocalService(localService)
				.setRemoteInterface(remoteInterface)
				.setInput(in)
				.setOutput(out)
				.create();
	}

	/**
	 * Create a new Launcher for a given local service object, a given remote
	 * interface and an input and output stream, and set up message validation and
	 * tracing.
	 *
	 * @param localService
	 *            - an object on which classes RPC methods are looked up
	 * @param remoteInterface
	 *            - an interface on which RPC methods are looked up
	 * @param in
	 *            - inputstream to listen for incoming messages
	 * @param out
	 *            - outputstream to send outgoing messages
	 * @param validate
	 *            - whether messages should be validated with the
	 *            {@link ReflectiveMessageValidator}
	 * @param trace
	 *            - a writer to which incoming and outgoing messages are traced, or
	 *            {@code null} to disable tracing
	 */
	public static <T> Launcher<T> createLauncher(Object localService, Class<T> remoteInterface, InputStream in,
			OutputStream out, boolean validate, PrintWriter trace) {
		return new Builder<T>()
				.setLocalService(localService)
				.setRemoteInterface(remoteInterface)
				.setInput(in)
				.setOutput(out)
				.validateMessages(validate)
				.traceMessages(trace)
				.create();
	}

	/**
	 * Create a new Launcher for a given local service object, a given remote
	 * interface and an input and output stream. Threads are started with the given
	 * executor service. The wrapper function is applied to the incoming and
	 * outgoing message streams so additional message handling such as validation
	 * and tracing can be included.
	 *
	 * @param localService
	 *            - an object on which classes RPC methods are looked up
	 * @param remoteInterface
	 *            - an interface on which RPC methods are looked up
	 * @param in
	 *            - inputstream to listen for incoming messages
	 * @param out
	 *            - outputstream to send outgoing messages
	 * @param executorService
	 *            - the executor service used to start threads
	 * @param wrapper
	 *            - a function for plugging in additional message consumers
	 */
	public static <T> Launcher<T> createLauncher(Object localService, Class<T> remoteInterface, InputStream in,
			OutputStream out, ExecutorService executorService, Function<MessageConsumer, MessageConsumer> wrapper) {
		return createIoLauncher(localService, remoteInterface, in, out, executorService, wrapper);
	}

	/**
	 * Create a new Launcher for a given local service object, a given remote
	 * interface and an input and output stream. Threads are started with the given
	 * executor service. The wrapper function is applied to the incoming and
	 * outgoing message streams so additional message handling such as validation
	 * and tracing can be included.
	 *
	 * @param localService
	 *            - an object on which classes RPC methods are looked up
	 * @param remoteInterface
	 *            - an interface on which RPC methods are looked up
	 * @param in
	 *            - inputstream to listen for incoming messages
	 * @param out
	 *            - outputstream to send outgoing messages
	 * @param executorService
	 *            - the executor service used to start threads
	 * @param wrapper
	 *            - a function for plugging in additional message consumers
	 */
	public static <T> Launcher<T> createIoLauncher(Object localService, Class<T> remoteInterface, InputStream in,
			OutputStream out, ExecutorService executorService, Function<MessageConsumer, MessageConsumer> wrapper) {
		return new Builder<T>()
				.setLocalService(localService)
				.setRemoteInterface(remoteInterface)
				.setInput(in)
				.setOutput(out)
				.setExecutorService(executorService)
				.wrapMessages(wrapper)
				.create();
	}

	/**
	 * Create a new Launcher for a given local service object, a given remote
	 * interface and an input and output stream. Threads are started with the given
	 * executor service. The wrapper function is applied to the incoming and
	 * outgoing message streams so additional message handling such as validation
	 * and tracing can be included. The {@code configureGson} function can be used
	 * to register additional type adapters in the {@link GsonBuilder} in order to
	 * support protocol classes that cannot be handled by Gson's reflective
	 * capabilities.
	 *
	 * @param localService
	 *            - an object on which classes RPC methods are looked up
	 * @param remoteInterface
	 *            - an interface on which RPC methods are looked up
	 * @param in
	 *            - inputstream to listen for incoming messages
	 * @param out
	 *            - outputstream to send outgoing messages
	 * @param executorService
	 *            - the executor service used to start threads
	 * @param wrapper
	 *            - a function for plugging in additional message consumers
	 * @param configureGson
	 *            - a function for Gson configuration
	 */
	public static <T> Launcher<T> createIoLauncher(Object localService, Class<T> remoteInterface, InputStream in,
			OutputStream out, ExecutorService executorService, Function<MessageConsumer, MessageConsumer> wrapper,
			Consumer<GsonBuilder> configureGson) {
		return new Builder<T>()
				.setLocalService(localService)
				.setRemoteInterface(remoteInterface)
				.setInput(in)
				.setOutput(out)
				.setExecutorService(executorService)
				.wrapMessages(wrapper)
				.configureGson(configureGson)
				.create();
	}
	
	/**
	 * Launcher builder for the debug protocol. Adapts the JSON-RPC message classes to the JSON format used
	 * by the debug protocol.
	 */
	public static class Builder<T> extends Launcher.Builder<T> {
		
		@Override
		protected MessageJsonHandler createJsonHandler() {
			Map<String, JsonRpcMethod> supportedMethods = getSupportedMethods();
			if (configureGson != null)
				return new DebugMessageJsonHandler(supportedMethods, configureGson);
			else
				return new DebugMessageJsonHandler(supportedMethods);
		}
		
		@Override
		protected RemoteEndpoint createRemoteEndpoint(MessageJsonHandler jsonHandler) {
			try {
			MessageConsumer outgoingMessageStream = new StreamMessageConsumer(output, jsonHandler);
			outgoingMessageStream = wrapMessageConsumer(outgoingMessageStream);
			Endpoint localEndpoint = ServiceEndpoints.toEndpoint(localServices);
			RemoteEndpoint remoteEndpoint;
			
			// !!!! nosuchfieldException that is trapped by quarkus
			// check if this is a version issue ?
			if (exceptionHandler == null)
				remoteEndpoint = new MEPRemoteEndpoint(outgoingMessageStream, localEndpoint);
			else
				remoteEndpoint = new MEPRemoteEndpoint(outgoingMessageStream, localEndpoint, exceptionHandler);
			jsonHandler.setMethodProvider(remoteEndpoint);
			return remoteEndpoint;
			} catch (Exception e) {
				System.out.println("   issue in createRemoteEndpoint"+e);
				e.printStackTrace();
				return null;
			}
		}
		
	}

}
