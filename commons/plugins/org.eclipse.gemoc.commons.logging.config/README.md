This plugin configures SLF4J/Logback in eclipse with a configuration for GEMOC

To enable it, simply start the plugin

Starting a plugin can be done usiong several ways:
- the launch configuration (in the underlying workbench) if you started your eclipse from another one
- in the eclipse.ini
- in the product configuration
- in the plugin registry view, find the org.eclipse.gemoc.commons.logging.config plugin, show advanced Operations, Start

Note: due to the start order, some messages may still be logged using the previous configuration.


This plugin follows tutorial from https://www.vogella.com/tutorials/EclipseLogging/article.html