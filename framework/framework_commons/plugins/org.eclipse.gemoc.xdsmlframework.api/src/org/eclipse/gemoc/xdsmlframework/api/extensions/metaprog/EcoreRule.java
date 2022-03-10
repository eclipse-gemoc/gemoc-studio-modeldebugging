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
import org.eclipse.gemoc.dsl.Entry;

/**
 * Validation rule used by meta-programming approaches that uses Ecore meta-models
 * 
 * @author GUEGUEN Ronan
 *
 */
public class EcoreRule implements ILanguageComponentValidator{ 
	
	/*
	 * This method checks if the adress given in the "ecore" entry in the dsl file points to an existing file,
	 * the method then checks if the file contains both an ecore model and ecore classes.
	 * (non-Javadoc)
	 * @see metaprogramming.extensionpoint.IRule#execute(org.eclipse.gemoc.dsl.Entry)
	 */
	@Override
	public Message validate(Entry entry) {
		if("ecore".matches(entry.getKey())) {
			
			URI uri = URI.createURI(entry.getValue());
			
			if(!uri.isPlatformResource() && !uri.isPlatformPlugin()) {
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
