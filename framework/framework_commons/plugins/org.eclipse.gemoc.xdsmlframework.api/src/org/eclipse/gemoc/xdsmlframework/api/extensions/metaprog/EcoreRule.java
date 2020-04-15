package org.eclipse.gemoc.xdsmlframework.api.extensions.metaprog;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.gemoc.dsl.Dsl;
import org.eclipse.gemoc.dsl.Entry;

/**
 * Validation rule used by meta-programming approaches that uses Ecore meta-models
 * 
 * @author GUEGUEN Ronan
 *
 */
public class EcoreRule implements IRule{ 
		
	/*
	 * The method checks for the presence of an "ecore" entry in the dsl file.
	 * (non-Javadoc)
	 * @see metaprogramming.extensionpoint.IRule#execute(org.eclipse.gemoc.dsl.Dsl)
	 */
	@Override
	public Message execute(Dsl dsl) {
		
		ArrayList<String> entriesNames = new ArrayList<String>();
		
		for (Entry e : dsl.getEntries()) {
			entriesNames.add(e.getKey());
		}
		
		if(!entriesNames.contains("ecore")) {
			return (new Message("Missing entry \"ecore\"", Severity.ERROR));
		}
			
		return (new Message("",Severity.DEFAULT));
		
	}
	
	
	/*
	 * This method checks if the adress given in the "ecore" entry in the dsl file points to an existing file,
	 * the method then checks if the file contains both an ecore model and ecore classes.
	 * (non-Javadoc)
	 * @see metaprogramming.extensionpoint.IRule#execute(org.eclipse.gemoc.dsl.Entry)
	 */
	@Override
	public Message execute(Entry entry) {
		if("ecore".matches(entry.getKey())) {
			
			URI uri = URI.createURI(entry.getValue());
			
			if(!uri.isPlatformResource()) {
				return (new Message("File for \"ecore\" entry not in the workspace", Severity.ERROR));
			}
						
			ResourceSet rs = new ResourceSetImpl();
			Resource res;
			
			try {
				
				res = rs.getResource(uri, true);
				
				TreeIterator<EObject> tree = res.getAllContents();
				Boolean hasModel = false;
				List<EClassifier> classes = new ArrayList<>();
				
				while(tree.hasNext()) {
					EObject node = tree.next();
					if(node instanceof EPackage) {
						hasModel = !hasModel;
						EPackage node2 = (EPackage) node;
						classes = node2.getEClassifiers();
					}
				}
				
				if(!hasModel) {
					return new Message("The ecore file does not contain an ecore model", Severity.WARNING);
				}
				
				if(classes.isEmpty()) {
					return (new Message("No classes in the ecore file", Severity.WARNING));
				}
				
			}catch (RuntimeException e) {
				return (new Message("The file for the \"ecore\" entry does not exist", Severity.ERROR));
			}
			
		}
		return (new Message("",Severity.DEFAULT));
	}
}
