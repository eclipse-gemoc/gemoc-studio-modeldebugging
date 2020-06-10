package org.eclipse.gemoc.xdsmlframework.api.extensions.metaprog;

import java.util.ArrayList;

import org.eclipse.core.runtime.IConfigurationElement;

public class LanguageComponentHelper {
	
	private IConfigurationElement[] exts = org.eclipse.core.runtime.Platform.getExtensionRegistry().getConfigurationElementsFor("org.eclipse.gemoc.gemoc_language_workbench.metaprog");
	
	public LanguageComponentHelper() {
		
	}
	
	public ArrayList<String> getAllMetaprogKeys() {

		ArrayList<String> allKeys = new ArrayList<String>();

		for(IConfigurationElement appr : exts) {
			IConfigurationElement[] keys = appr.getChildren("languageComponent");
			for (IConfigurationElement key : keys) {
				allKeys.add(key.getAttribute("name"));
			}
		}

		return allKeys;

	}


	public ArrayList<IConfigurationElement> getApproachKeys(IConfigurationElement approach){

		ArrayList<IConfigurationElement> allKeys = new ArrayList<IConfigurationElement>();

		for(IConfigurationElement comp : approach.getChildren("languageComponent")) {
			allKeys.add(comp);
		}

		return allKeys;
	}


	public ArrayList<IConfigurationElement> getApproachKeys(String approachName){

		ArrayList<IConfigurationElement> allKeys = new ArrayList<IConfigurationElement>();

		for (IConfigurationElement appr : exts) {
			if(approachName.matches(appr.getAttribute("name"))){
				for(IConfigurationElement comp : appr.getChildren("languageComponent")) {
					allKeys.add(comp);
				}
			}
		}

		return allKeys;
	}


	public ArrayList<IConfigurationElement> getFullApproachKeys(String approach){

		ArrayList<IConfigurationElement> allKeys = new ArrayList<IConfigurationElement>();

		for(IConfigurationElement appr : exts) {
			if(approach.matches(appr.getAttribute("name"))){
				allKeys.addAll(getApproachKeys(appr));

				String[] dependencies = appr.getAttribute("dependencies").split(",");

				for(String depen : dependencies){
					allKeys.addAll(getApproachKeys(depen));
				}

			}
		}

		return allKeys;
	}


}
