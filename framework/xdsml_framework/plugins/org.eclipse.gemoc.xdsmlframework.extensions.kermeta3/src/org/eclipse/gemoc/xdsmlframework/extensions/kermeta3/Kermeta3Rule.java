package org.eclipse.gemoc.xdsmlframework.extensions.kermeta3;

import java.util.ArrayList;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.gemoc.dsl.Dsl;
import org.eclipse.gemoc.dsl.Entry;
import org.eclipse.gemoc.xdsmlframework.api.extensions.metaprog.IRule;
import org.eclipse.gemoc.xdsmlframework.api.extensions.metaprog.Message;
import org.eclipse.gemoc.xdsmlframework.api.extensions.metaprog.Severity;
import org.eclipse.jdt.core.IAnnotation;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaCore;

public class Kermeta3Rule implements IRule{
	
	boolean tagTests; 
	
	public Kermeta3Rule(boolean performsTagTest) {
		tagTests = performsTagTest;
	}

	@Override
	public Message execute(Dsl dsl) {
		ArrayList<String> entriesNames = new ArrayList<String>();
		
		for (Entry e : dsl.getEntries()) {
			entriesNames.add(e.getKey());
		}
		
		if(!entriesNames.contains("k3")) {
			return (new Message("Missing entry \"k3\"", Severity.ERROR));
		}
			
		return (new Message("",Severity.DEFAULT));
	}

	@Override
	public Message execute(Entry entry) {
		if("k3".matches(entry.getKey())) {
			String aspectsFields = entry.getValue();
			
			IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
			IFile file = root.getFile(new Path(entry.eResource().getURI().toPlatformString(true)));
			
			IProject proj = file.getProject();
			IJavaProject jProj = JavaCore.create(proj);
			
			if(jProj == null) {
				return (new Message("No project dsa in the workspace", Severity.ERROR));
			}
			
			ArrayList<String> aspectsName = new ArrayList<>();
			ArrayList<String> aspectsAnnotation = new ArrayList<>();
			
			for(String s : aspectsFields.split(",")) {
				aspectsName.add(s.trim());
			}
			
			
			for(String asp : aspectsName) {
				try {
					IType type = jProj.findType(asp);
					for(IMethod meth : type.getMethods()) {
						for(IAnnotation annot : meth.getAnnotations()) {
							aspectsAnnotation.add(annot.getElementName());
						}
					}
				} catch (Exception e) {
					return (new Message("No aspect matching \""+asp+ "\" in the project", Severity.ERROR));
				}
			}
			
			if(tagTests) {
				
				if(!aspectsAnnotation.contains("Main")) {
					return (new Message("No method annotated with \"Main\" in the project", Severity.ERROR));
				}
				
				if(!aspectsAnnotation.contains("InitializeModel")) {
					return (new Message("No method annotated with \"InitializeModel\" in the project", Severity.WARNING));
				}
				
			}
			
		}
		return (new Message("", Severity.DEFAULT));
	}
}
