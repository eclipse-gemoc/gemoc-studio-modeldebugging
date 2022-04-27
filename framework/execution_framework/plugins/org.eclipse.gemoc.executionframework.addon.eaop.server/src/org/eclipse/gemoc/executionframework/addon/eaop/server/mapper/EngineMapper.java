package org.eclipse.gemoc.executionframework.addon.eaop.server.mapper;

import org.eclipse.gemoc.protocols.eaop.api.data.ExecutionEngineDto;
import org.eclipse.gemoc.xdsmlframework.api.core.IExecutionEngine;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EngineMapper {
	EngineMapper INSTANCE = Mappers.getMapper( EngineMapper.class );
	
	@Mapping(expression = "java(engine.engineKindName())", target = "engineKindName")
	@Mapping(source = "name", target = "engineName")
	ExecutionEngineDto executionEngineToExecutionEngineDto(IExecutionEngine<?> engine); 
}
