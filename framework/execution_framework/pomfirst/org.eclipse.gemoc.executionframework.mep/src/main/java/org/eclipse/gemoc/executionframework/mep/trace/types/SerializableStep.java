package org.eclipse.gemoc.executionframework.mep.trace.types;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.gemoc.trace.commons.model.trace.Step;
import org.eclipse.gemoc.trace.commons.model.trace.impl.MSEOccurrenceImpl;
import org.eclipse.xtext.EcoreUtil2;
import org.emfjson.jackson.annotations.EcoreIdentityInfo;
import org.emfjson.jackson.module.EMFModule;
import org.emfjson.jackson.resource.JsonResourceFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class SerializableStep implements Serializable {
	
	private String jsonStep;

	public SerializableStep(Step<?> step) {
		EcoreUtil2.resolveAll(step);
				
		ObjectMapper mapper = new ObjectMapper();
		// Optional
		mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
		        
		EMFModule module = new EMFModule();
		module.configure(EMFModule.Feature.OPTION_USE_ID, true);
		// Optional
		module.configure(EMFModule.Feature.OPTION_SERIALIZE_TYPE, true);

		module.setIdentityInfo(new EcoreIdentityInfo("_id"));
		mapper.registerModule(module);
		
		try {
			System.err.println(mapper.writeValueAsString(step));
			System.err.println(mapper.writeValueAsString(step.getMseoccurrence().getMse()));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		ResourceSet jsonRS = new ResourceSetImpl();
		jsonRS.getResourceFactoryRegistry().getExtensionToFactoryMap().put("json", new JsonResourceFactory());
    	Resource jsonResource = jsonRS.createResource(URI.createFileURI("step.json"));
    	EObject container = step;
    	while (container.eContainer() != null) {
    		container = container.eContainer();
    	}
    	jsonResource.getContents().add(container);
    	jsonResource.getContents().add(step.getMseoccurrence().getMse());
    	ByteArrayOutputStream baos = new ByteArrayOutputStream();
    	try {
			jsonResource.save(baos, null);
			this.jsonStep = new String(baos.toByteArray());
			baos.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public Step<?> getStep() {
		ResourceSet rs = new ResourceSetImpl();
		if (!rs.getResourceFactoryRegistry().getExtensionToFactoryMap().containsKey("json")) {
			rs.getResourceFactoryRegistry().getExtensionToFactoryMap().put("json", new JsonResourceFactory());
		}
    	Resource jsonResource = rs.createResource(URI.createFileURI("step.json"));
    	ByteArrayInputStream bios = new ByteArrayInputStream(this.jsonStep.getBytes());
    	try {
			jsonResource.load(bios, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	EObject rootObj = jsonResource.getContents().get(0);
    	EcoreUtil2.resolveAll(jsonResource.getContents().get(1));
    	if (rootObj instanceof Step<?> && ((Step<?>) rootObj).getMseoccurrence() != null
    			&& ((Step<?>) rootObj).getMseoccurrence().getMse() == jsonResource.getContents().get(1)) {
    		return (Step<?>) rootObj;
    	} else {
	    	List<Step<?>> foundStep = new ArrayList<>();
	    	rootObj.eAllContents().forEachRemaining(obj -> {
	    		if (obj instanceof Step<?>) {
	    			Step<?> step = (Step<?>) obj;
	    			if (step.getMseoccurrence() != null &&
	    					!((MSEOccurrenceImpl)step.getMseoccurrence()).basicGetMse().eIsProxy() &&
	    				    step.getMseoccurrence().getMse() == jsonResource.getContents().get(1)) {
	    	    		//EcoreUtil2.resolveAll(step);
	    				foundStep.add(step);
	    			}
	    		}
	    	});
			return foundStep.get(0);
    	}
	}

}
