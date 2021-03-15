package org.eclipse.gemoc.executionframework.mep.trace.types;

import java.io.Serializable;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.gemoc.trace.commons.model.trace.MSEModel;
import org.eclipse.gemoc.xdsmlframework.api.core.ExecutionMode;
import org.eclipse.gemoc.xdsmlframework.api.core.IExecutionContext;
import org.eclipse.gemoc.xdsmlframework.api.core.IExecutionPlatform;
import org.eclipse.gemoc.xdsmlframework.api.core.IExecutionWorkspace;
import org.eclipse.gemoc.xdsmlframework.api.core.IRunConfiguration;
import org.eclipse.gemoc.xdsmlframework.api.extensions.languages.LanguageDefinitionExtension;
import org.eclipse.xtext.EcoreUtil2;
import org.emfjson.jackson.databind.EMFContext;
import org.emfjson.jackson.module.EMFModule;
import org.osgi.framework.Bundle;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SerializableExecutionContext implements Serializable {

	private String resourceURI;
	private String jsonModel;
	
	public SerializableExecutionContext(IExecutionContext<?, ?, ?> executionContext) {
		Resource resourceModel = executionContext.getResourceModel();
		EcoreUtil2.resolveAll(resourceModel);
		ObjectMapper mapper = EMFModule.setupDefaultMapper();
		try {
			this.jsonModel = mapper.writeValueAsString(resourceModel.getContents().get(0));
			this.resourceURI = resourceModel.getURI().toFileString();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
	
	public IExecutionContext<?, ?, ?> getExecutionContext() {
		return new IExecutionContext<IRunConfiguration, IExecutionPlatform, LanguageDefinitionExtension>() {
			private Resource resourceModel = null;
			
			@Override
			public void initializeResourceModel() {

			}

			@Override
			public IExecutionWorkspace getWorkspace() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Resource getResourceModel() {
				if (this.resourceModel == null) {
					this.resourceModel = new ResourceSetImpl().createResource(URI.createFileURI(resourceURI));
					ObjectMapper mapper = EMFModule.setupDefaultMapper();
					try {
						this.resourceModel.getContents().add(mapper.reader()
									.withAttribute(EMFContext.Attributes.RESOURCE_SET, this.resourceModel.getResourceSet())
									.withAttribute(EMFContext.Attributes.RESOURCE_URI, resourceURI)
									.forType(EObject.class).readValue(jsonModel));
					} catch (JsonMappingException e) {
						e.printStackTrace();
					} catch (JsonProcessingException e) {
						e.printStackTrace();
					}
				}
				return this.resourceModel;
			}
			
			@Override
			public ExecutionMode getExecutionMode() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public MSEModel getMSEModel() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Bundle getDslBundle() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public LanguageDefinitionExtension getLanguageDefinitionExtension() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public IExecutionPlatform getExecutionPlatform() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public IRunConfiguration getRunConfiguration() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public void dispose() {
				// TODO Auto-generated method stub
				
			}
		};
	}
	
}
