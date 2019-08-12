package org.eclipse.gemoc.xdsmlframework.extensions.sirius.m2m;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.gemoc.xdsmlframework.extensions.sirius.helpers.SiriusBuildHelper;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.style.FlatContainerStyleDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

public class Ecore2BasicObjectDiagramSpecification {
	String diagramName;
	Viewpoint viewpoint;
	List<EClass> eClasses;
	List<EPackage> ePackages;
	String rootEClassAQLName;
	
	public String layerName = "Default";
	
	
	/**
	 * @param viewpoint
	 * @param diagramName
	 * @param eClasses
	 * @param ePackages
	 * @param rootEClass
	 */
	public Ecore2BasicObjectDiagramSpecification(Viewpoint viewpoint, String diagramName, List<EClass> eClasses,
			List<EPackage> ePackages, String rootEClassAQLName) {
		super();
		this.viewpoint = viewpoint;
		this.diagramName = diagramName;
		this.eClasses = eClasses;
		this.ePackages = ePackages;
		this.rootEClassAQLName = rootEClassAQLName;
	}
	

	public void addBasicObjectDiagram() {
		
		DiagramDescription diag = SiriusBuildHelper.createDiagram(viewpoint, diagramName);
		diag.setDomainClass(rootEClassAQLName); 
		diag.getMetamodel().addAll(ePackages);
		diag.setDefaultLayer(SiriusBuildHelper.createAdditionalLayer(layerName));
		
		// create ContainerNodes
		for(EClass anEClass : eClasses) {
			// consider only leaf classes
			if(anEClass.getESuperTypes().isEmpty()) {
				// only for leaf classes
				ContainerMapping node = SiriusBuildHelper.createContainerMapping(diag, anEClass.getName()+"Container");
				node.setDomainClass(anEClass.getEPackage().getName()+"::"+anEClass.getName());
				FlatContainerStyleDescription gradientStyle = SiriusBuildHelper.createFlatContainerStyleDescription(node);
				// TODO infer a nice label (from name or id or urifragment)
				//gradientStyle.setLabelExpression(arg0);
				
				// TODO create content for EAttributes // takes care of inheritance
			}
		}
		// TODO create Edge for containment references (takes care of eOpposite and inheritance)
		// TODO create Edge for normal references (takes care of eOpposite and inheritance)
	}
	
	
}
