package org.eclipse.gemoc.xdsmlframework.extensions.sirius.m2m;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.gemoc.xdsmlframework.extensions.sirius.helpers.SpecificationBuildHelper;
import org.eclipse.gemoc.xdsmlframework.ui.utils.ENamedElementQualifiedNameLabelProvider;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.description.style.EdgeStyleDescription;
import org.eclipse.sirius.diagram.description.style.FlatContainerStyleDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.xtext.EcoreUtil2;

public class Ecore2BasicObjectDiagramSpecification {
	String diagramName;
	Viewpoint viewpoint;
	List<EClass> eClasses;
	List<EPackage> ePackages;
	String rootEClassAQLName;
	
	public String layerName = "Default";
	
	List<EClass> leafEClasses;
	
	
	// for traceability and use in the different transformation steps
	protected HashMap<EClass, ContainerMapping> eClass2ContainerMap =  new HashMap<EClass, ContainerMapping>();
	
	
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
		
		DiagramDescription diag = SpecificationBuildHelper.createDiagram(viewpoint, diagramName);
		diag.setDomainClass(rootEClassAQLName); 
		diag.getMetamodel().addAll(ePackages);
		diag.setDefaultLayer(SpecificationBuildHelper.createAdditionalLayer(layerName));
		
		ENamedElementQualifiedNameLabelProvider nameProvider = new ENamedElementQualifiedNameLabelProvider();
		leafEClasses = eClasses.stream().filter(c -> !hasKnownSubClass(c, eClasses)
				&& !nameProvider.getText(c).equals(this.rootEClassAQLName)).collect(Collectors.toList());
		// create ContainerNodes
		for(EClass anEClass : leafEClasses) {
			// consider only leaf classes
			ContainerMapping node = SpecificationBuildHelper.createContainerMapping(diag, anEClass.getName()+"Container");
			eClass2ContainerMap.put(anEClass, node);
			node.setDomainClass(anEClass.getEPackage().getName()+"::"+anEClass.getName());
			FlatContainerStyleDescription gradientStyle = SpecificationBuildHelper.createFlatContainerStyleDescription(node);
			// TODO infer a nice label (from name or id or urifragment)
			gradientStyle.setLabelExpression("aql:self.name+' : '+self.eClass().name");
			
			// TODO create content for EAttributes // takes care of inheritance
		}
		// create Edge for references (takes care of inheritance)
		// TODO takes care of eOpposite and containment
		for(EClass anEClass : eClasses) {
			
			List<EClass> allApplicableLeafEClasses = leafEClasses.stream()
					.filter(e ->  e.getEAllSuperTypes().contains(anEClass) || e.equals(anEClass))
					.collect(Collectors.toList());
			if(!allApplicableLeafEClasses.isEmpty()) {
				for( EReference reference : anEClass.getEReferences()) {
					List<EClass> allApplicableTargetLeafEClasses = leafEClasses.stream()
							.filter(e ->  e.getEAllSuperTypes().contains(reference.getEReferenceType()) || e.equals(reference.getEReferenceType()))
							.collect(Collectors.toList());
					// ignore non applicable edges (for example related to the root element that is actually represented as the page itself) 
					if(!allApplicableLeafEClasses.isEmpty() && !allApplicableTargetLeafEClasses.isEmpty()) {
						EdgeMapping em = SpecificationBuildHelper.createEdgeMapping(diag, 
								anEClass.getName()+"_"+reference.getName()+"Edge");
						allApplicableLeafEClasses.stream().map(leafClass -> eClass2ContainerMap.get(leafClass));
						
						em.getSourceMapping().addAll(
								allApplicableLeafEClasses
									.stream()
									.map(leafClass -> eClass2ContainerMap.get(leafClass))
									.collect(Collectors.toList()));
						
						em.getTargetMapping().addAll(
								allApplicableTargetLeafEClasses
									.stream()
									.map(targetleafClass -> eClass2ContainerMap.get(targetleafClass))
									.collect(Collectors.toList()));
						em.setTargetFinderExpression("feature:"+reference.getName());
						EdgeStyleDescription styleDesc = SpecificationBuildHelper.createStyleDescription(em);
						SpecificationBuildHelper.createCenterLabelStyleDescription(styleDesc, "aql:'"+reference.getName()+"'");
					}
				}
			}
		}
	}
	
	
	public static boolean hasKnownSubClass(EClass anEClass, List<EClass> consideredEClasses) {
		return consideredEClasses
				.stream()
				.anyMatch(possibleSubClass -> 
					possibleSubClass.getEAllSuperTypes().contains(anEClass));
		
	}
	
}
