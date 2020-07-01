package org.eclipse.gemoc.executionframework.engine.headless;

import java.util.ResourceBundle;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.RegistryFactory;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.spi.IRegistryProvider;
import org.eclipse.core.runtime.spi.RegistryStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FakeOSGI {
	private static final Logger LOGGER = LoggerFactory.getLogger("FakeOSGI");


	public static void start() {
		// If there isn't already a registry...
        //
        IExtensionRegistry registry = RegistryFactory.getRegistry();
        if (registry == null)
        {
          // Create a new registry.
          //
          final IExtensionRegistry newRegistry =
            RegistryFactory.createRegistry
              (new RegistryStrategy(null, null)
               {
                 @Override
                 public void log(IStatus status)
                 {
                	 switch (status.getSeverity()) {
					case Status.ERROR:
						 LOGGER.error(status.toString());
						break;
					case Status.WARNING:
						 LOGGER.warn(status.toString());
						break;

					default:
						 LOGGER.info(status.toString());
						break;
						
					}
                 }

                 @Override
                 public String translate(String key, ResourceBundle resources)
                 {
                   try
                   {
                     // The org.eclipse.core.resources bundle has keys that aren't translated, so avoid exception propagation.
                     //
                     return super.translate(key, resources);
                   }
                   catch (Throwable throwable)
                   {
                     return key;
                   }
                 }
               },
               null,
               null);

          // Make the new registry the default.
          //
          try
          {
            RegistryFactory.setDefaultRegistryProvider
              (new IRegistryProvider()
               {
                 public IExtensionRegistry getRegistry()
                 {
                  return newRegistry;
                 }
               });
          }
          catch (CoreException e)
          {
        	  LOGGER.error(e.getMessage(), e);
          }

          registry = newRegistry;
        }
        
        
        org.eclipse.gemoc.executionframework.engine.Activator activator = new org.eclipse.gemoc.executionframework.engine.Activator();
        org.eclipse.gemoc.executionframework.debugger.Activator activatorDebug = new org.eclipse.gemoc.executionframework.debugger.Activator();
		FakeBundleContext context = new FakeBundleContext();
        try {
			activator.start(context);
			activatorDebug.start(context);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
}
