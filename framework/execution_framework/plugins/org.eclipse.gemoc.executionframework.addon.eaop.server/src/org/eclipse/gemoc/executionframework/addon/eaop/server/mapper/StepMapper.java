package org.eclipse.gemoc.executionframework.addon.eaop.server.mapper;

import java.util.stream.Collectors;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gemoc.protocols.eaop.api.data.MSEDto;
import org.eclipse.gemoc.protocols.eaop.api.data.MSEOccurenceDto;
import org.eclipse.gemoc.protocols.eaop.api.data.StepDto;
import org.eclipse.gemoc.trace.commons.model.trace.MSE;
import org.eclipse.gemoc.trace.commons.model.trace.MSEOccurrence;
import org.eclipse.gemoc.trace.commons.model.trace.State;
import org.eclipse.gemoc.trace.commons.model.trace.Step;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class StepMapper {
	public static final StepMapper INSTANCE = Mappers.getMapper( StepMapper.class );
	
	@Mapping(source = "mseoccurrence", target = "mseOccurence")
	@Mapping(source = "startingState", target = "startingStateID")
	@Mapping(source = "endingState", target = "endingStateID")
	public abstract StepDto stepToStepDto(Step<?> step); 
	
	public abstract MSEOccurenceDto mSEOccurenceToMSEOccurenceDto(MSEOccurrence mseOccurrence);
	
	
	@Mapping(source = "caller", target = "callerEObjectURI")
	@Mapping(source = "action", target = "actionID")
	public abstract MSEDto mSEToMSEDto(MSE mSE);
	
	String mapEObjectToURI(EObject eObj) {
		if(eObj == null ) return "";
		if(eObj.eResource() !=  null) {
			return eObj.eResource().getURIFragment(eObj).toString();
		} else {
			// TODO
			return eObj.toString();
		}
	}
	
	String map(EList<Object> value) {
		// TODO find nicer parameter represention / uri
		return value.stream().map(o -> o.toString()).collect(Collectors.joining(","));		
	}
	
	@SuppressWarnings("rawtypes")
	String getStateID(State state) {
		return mapEObjectToURI(state);
	}
}
