package org.eclipse.gemoc.executionframework.engine.ui.launcher.tabs;


/**
 * allows for launchtab to register to changes of selected language (usually done in the main tab)
 * and then adapt their content accordingly 
 *
 */
public interface ILaunchLanguageSelectionListener {

	
	public void languageChanged();
}
