package org.eclipse.gemoc.commons.logging.config;

import java.io.IOException;
import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.joran.util.ConfigurationWatchListUtil;

public class Activator implements BundleActivator {

	private static final Logger logger = LoggerFactory.getLogger(Activator.class);
	
	private static BundleContext context;

	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
		//findcCurrentLogbackFile();
		configureLogbackInBundle(bundleContext.getBundle());
		
		
	}

	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
	}

	
	private void findcCurrentLogbackFile() {
		LoggerContext loggerContext = ((ch.qos.logback.classic.Logger)logger).getLoggerContext();
        URL mainURL = ConfigurationWatchListUtil.getMainWatchURL(loggerContext);
        System.out.println(mainURL);
        // or even
        logger.info("Logback used '{}' as the configuration file.", mainURL);
	}
	
	private void configureLogbackInBundle(Bundle bundle) throws JoranException, IOException {
        LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
        JoranConfigurator jc = new JoranConfigurator();
        jc.setContext(context);
        context.reset();

        // this assumes that the logback.xml file is in the root of the bundle.
        URL logbackConfigFileUrl = FileLocator.find(bundle, new Path("logback.xml"),null);
        jc.doConfigure(logbackConfigFileUrl.openStream());
    }
}
