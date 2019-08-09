package org.eclipse.gemoc.xdsmlframework.extensions.sirius.helpers;

import org.eclipse.sirius.diagram.description.AdditionalLayer;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.description.Layer;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.diagram.description.style.FlatContainerStyleDescription;
import org.eclipse.sirius.diagram.description.style.NodeStyleDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

public class SiriusCreationHelper {

	/**
	 * Create a {@link DiagramDescription}
	 * 
	 * @param viewpoint the parent {@link Viewpoint}
	 * @param dName     {@link DiagramDescription} label
	 * @return the created {@link DiagramDescription}
	 */
	public static DiagramDescription createDiagram(Viewpoint viewpoint, String dName) {
		DiagramDescription diagramDescription;
		diagramDescription = org.eclipse.sirius.diagram.description.DescriptionPackage.eINSTANCE.getDescriptionFactory()
				.createDiagramDescription();
		diagramDescription.setName(dName);
		viewpoint.getOwnedRepresentations().add(diagramDescription);
		return diagramDescription;
	}

	/**
	 * Create an {@link AdditionalLayer}
	 * 
	 * @param layerName the {@link AdditionalLayer} label
	 * @return the created {@link AdditionalLayer}
	 */
	public static AdditionalLayer createAdditionalLayer(String layerName) {
		final AdditionalLayer res = org.eclipse.sirius.diagram.description.DescriptionPackage.eINSTANCE
				.getDescriptionFactory().createAdditionalLayer();
		res.setName(layerName);
		res.setActiveByDefault(true);
		return res;
	}

	/**
	 * Create a NodeMapping
	 * 
	 * @param diag  a {@link DiagramDescription}
	 * @param label the {@link NodeMapping} label
	 * @return the created {@link NodeMapping}
	 */
	public static NodeMapping createNodeMapping(DiagramDescription diag, String label) {
		final NodeMapping res = org.eclipse.sirius.diagram.description.DescriptionPackage.eINSTANCE
				.getDescriptionFactory().createNodeMapping();

		res.setName(label);
		diag.getDefaultLayer().getNodeMappings().add(res);
		return res;
	}
	
	/**
	 * Create a NodeMapping
	 * 
	 * @param diag  a {@link DiagramDescription}
	 * @param label the {@link NodeMapping} label
	 * @return the created {@link NodeMapping}
	 */
	public static ContainerMapping createContainerapping(DiagramDescription diag, String label) {
		final ContainerMapping res = org.eclipse.sirius.diagram.description.DescriptionPackage.eINSTANCE
				.getDescriptionFactory().createContainerMapping();

		res.setName(label);
		diag.getDefaultLayer().getContainerMappings().add(res);
		return res;
	}
	
	// gradient 
	public static FlatContainerStyleDescription createFlatContainerStyleDescription(ContainerMapping container) {
		final FlatContainerStyleDescription res = org.eclipse.sirius.diagram.description.style.StylePackage.eINSTANCE
				.getStyleFactory().createFlatContainerStyleDescription();

		container.setStyle(res);
		return res;
	}
	

	/**
	 * Create an {@link EdgeMapping}
	 * 
	 * @param diag   a {@link DiagramDescription}
	 * @param emName the {@link EdgeMapping} label
	 * @param source the {@link EdgeMapping} source
	 * @param target the {@link EdgeMapping} target
	 * @return the created {@link EdgeMapping}
	 */
	public static EdgeMapping createEdgeMapping(DiagramDescription diag, String emName, String source, String target) {
		final EdgeMapping res = org.eclipse.sirius.diagram.description.DescriptionPackage.eINSTANCE
				.getDescriptionFactory().createEdgeMapping();
		res.setName(emName);
		res.setSourceFinderExpression(source);
		res.setTargetExpression(target);
		Layer layer = diag.getDefaultLayer();
		layer.getEdgeMappings().add(res);
		return res;
	}

	/**
	 * Create a {@link EdgeMapping}
	 * 
	 * @param layer  the parent {@link Layer} of the {@link EdgeMapping}
	 * @param emName the {@link EdgeMapping} label
	 * @return the created {@link EdgeMapping}
	 */
	public static EdgeMapping createEdgeMapping(Layer layer, String emName) {
		final EdgeMapping res = org.eclipse.sirius.diagram.description.DescriptionPackage.eINSTANCE
				.getDescriptionFactory().createEdgeMapping();
		res.setName(emName);
		layer.getEdgeMappings().add(res);
		return res;
	}

	/**
	 * Create a {@link ContainerMapping}
	 * 
	 * @param description the parent {@link DiagramDescription}
	 * @param name        the {@link ContainerMapping} label
	 * @return the created {@link ContainerMapping}
	 */
	public static ContainerMapping createContainerMapping(DiagramDescription description, String name) {
		final ContainerMapping res = org.eclipse.sirius.diagram.description.DescriptionPackage.eINSTANCE
				.getDescriptionFactory().createContainerMapping();
		res.setName(name);
		description.getDefaultLayer().getContainerMappings().add(res);
		return res;
	}

}
