/*******************************************************************************
 * Copyright (c) 2020 Inria and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Inria - initial API and implementation
 *******************************************************************************/
package org.eclipse.gemoc.ws.server;

import javax.servlet.ServletContext;
import javax.websocket.server.ServerContainer;
import javax.websocket.server.ServerEndpoint;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.gemoc.ws.server.endpoint.EndPointExtensionPointHelper;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.websocket.servlet.WebSocketUpgradeFilter;
import org.eclipse.jetty.websocket.server.JettyWebSocketServerContainer;
import org.eclipse.jetty.websocket.server.JettyWebSocketServlet;
import org.eclipse.jetty.websocket.server.JettyWebSocketServletFactory;
import org.eclipse.jetty.websocket.server.config.JettyWebSocketServletContainerInitializer;
//import org.eclipse.jetty.websocket.jsr356.server.deploy.WebSocketServerContainerInitializer;
import org.osgi.framework.BundleContext;

public class Activator extends Plugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.eclipse.gemoc.ws.server"; //$NON-NLS-1$

	// The shared instance
	private static Activator plugin;
	private BundleContext context;

	public static BundleContext getBundleContext() {
		return getDefault().context;
	}

	public static Activator getDefault() {
		return plugin;
	}

	public static synchronized void logError(String message, Throwable ex) {
		if (message == null) {
			message = ""; //$NON-NLS-1$
		}
		Status errorStatus = new Status(IStatus.ERROR, PLUGIN_ID, IStatus.OK, message, ex);
		Activator.getDefault().getLog().log(errorStatus);
	}

	public static synchronized void logStatus(IStatus errorStatus) {
		Activator.getDefault().getLog().log(errorStatus);
	}

	protected Server server;
	protected ServerContainer wsContainer;
	protected int assignedPort;

	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		this.context = context;

		// starts the server later in order to be able to load the classes of the contributing EndPoints
		Job job = new Job("Start GEMOC WebSocket Server") {
			protected IStatus run(IProgressMonitor monitor) {
				try {
					Activator.getDefault().startWSServer();
				} catch (Exception e) {
					logError(e.getMessage(), e);
				}
				return Status.OK_STATUS;
			}
		};
		job.setPriority(Job.SHORT);
		job.schedule();
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		stopWSServer();
		this.context = null;
		plugin = null;
		super.stop(context);
	}

	public synchronized void startWSServer() throws Exception {
		server = new Server();
		ServerConnector connector = new ServerConnector(server);
		connector.setPort(0);
		server.addConnector(connector);

		// Setup the basic application "context" for this application at "/"
		// This is also known as the handler tree (in jetty speak)
		ServletContextHandler servletContextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
		servletContextHandler.setContextPath("/");
		server.setHandler(servletContextHandler);

		try {
			// Initialize javax.websocket layer
			servletContextHandler.addServletContainerInitializer(
					new JettyWebSocketServletContainerInitializer(new JettyWebSocketServletContainerInitializer.Configurator() {
				
				@Override
				public void accept(ServletContext servletContext, JettyWebSocketServerContainer container) {
					for( Class<?> endPointClass :EndPointExtensionPointHelper.getAllEndPointClasses()) {
						ServerEndpoint endPointAnnotation = endPointClass.getDeclaredAnnotation(javax.websocket.server.ServerEndpoint.class);
						if(endPointAnnotation != null) {
							String path = endPointAnnotation.value();
							System.err.println("Adding EndPoint class: " + endPointClass.getCanonicalName() + " on "+path);
							//servletContext.get
							container.addMapping(path, endPointClass);
							//servletContextHandler.addServlet(new ServletHolder(endPointClass), path);
						}
					}
					
				}
			}) );
			//servletContextHandler.addServlet(EclipseGemocServlet.class,"/");
			/*for( Class<?> endPointClass :EndPointExtensionPointHelper.getAllEndPointClasses()) {
				ServerEndpoint endPointAnnotation = endPointClass.getDeclaredAnnotation(javax.websocket.server.ServerEndpoint.class);
				if(endPointAnnotation != null) {
					String path = endPointAnnotation.value();
					System.err.println("Adding EndPoint class: " + endPointClass.getCanonicalName() + " on "+path);
					servletContextHandler.addServlet(new ServletHolder(endPointClass), path);
				}
			}*/
			//servletContextHandler.getServletContext()
			//JettyWebSocketServerContainer wsContainer2 = JettyWebSocketServerContainer.getContainer(servletContextHandler.getServletContext());
			//wsContainer = JettyWebSocketServletContainerInitializer.configure(servletContext);

			// Add WebSocket endpoint to javax.websocket layer
			// wsContainer.addEndpoint(TestEndPoint.class);
			/*
			
			WebSocketUpgradeFilter wsuf = WebSocketUpgradeFilter.configureContext(servletContextHandler);
			
			JettyWebSocketServerContainer.ensureContainer(servletContextHandler.getServletContext());
			JettyWebSocketServerContainer wsContainer2 = JettyWebSocketServerContainer.getContainer(servletContextHandler.getServletContext());
			
			for( Class<?> endPointClass :EndPointExtensionPointHelper.getAllEndPointClasses()) {
				//wsContainer.addEndpoint(endPointClass);
				ServerEndpoint endPointAnnotation = endPointClass.getDeclaredAnnotation(javax.websocket.server.ServerEndpoint.class);
				if(endPointAnnotation != null) {
					String path = endPointAnnotation.value();
					System.err.println("Adding EndPoint class: " + endPointClass.getCanonicalName() + " on "+path);
					wsContainer2.addMapping(path, endPointClass);
				} else {
					System.err.println("Failed to add EndPoint class: " + endPointClass.getCanonicalName());
				}
			}
*/
			server.start();
			/*while(!server.isStarted()) {
				Thread.sleep(100);
			}
			JettyWebSocketServerContainer wsContainer2 = JettyWebSocketServerContainer.getContainer(servletContextHandler.getServletContext());
			
			for( Class<?> endPointClass :EndPointExtensionPointHelper.getAllEndPointClasses()) {
				//wsContainer.addEndpoint(endPointClass);
				ServerEndpoint endPointAnnotation = endPointClass.getDeclaredAnnotation(javax.websocket.server.ServerEndpoint.class);
				if(endPointAnnotation != null) {
					String path = endPointAnnotation.value();
					System.err.println("Adding EndPoint class: " + endPointClass.getCanonicalName() + " on "+path);
					wsContainer2.addMapping(new ServletPathSpec(path), endPointClass);
				} else {
					System.err.println("Failed to add EndPoint class: " + endPointClass.getCanonicalName());
				}
			}*/
			assignedPort = connector.getLocalPort();
			System.err.println("Assigned port: " + connector.getLocalPort());
			server.dump(System.err);
		} catch (Throwable t) {
			logError(t.getMessage(), t);
		}
	}
	
	
	
	
	public class EclipseGemocServlet extends JettyWebSocketServlet {
	    /**
		 * 
		 */
		private static final long serialVersionUID = -1383115328058942867L;

		@Override
	    public void configure(JettyWebSocketServletFactory factory) {
	    	for( Class<?> endPointClass :EndPointExtensionPointHelper.getAllEndPointClasses()) {
	    		factory.register(endPointClass);	
	    	}
	    }
	}

	public synchronized void stopWSServer() throws Exception {
		if (server != null)
			server.stop();
	}

	public Server getServer() {
		return server;
	}

	public ServerContainer getWsContainer() {
		return wsContainer;
	}

	public int getAssignedPort() {
		return assignedPort;
	}

}
