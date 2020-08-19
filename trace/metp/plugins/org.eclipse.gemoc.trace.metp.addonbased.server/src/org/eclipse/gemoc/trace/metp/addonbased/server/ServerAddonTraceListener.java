/*******************************************************************************
 * Copyright (c) 2020 Inria and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Inria - initial API and implementation
 *******************************************************************************/
package org.eclipse.gemoc.trace.metp.addonbased.server;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import org.eclipse.gemoc.trace.commons.model.trace.Dimension;
import org.eclipse.gemoc.trace.commons.model.trace.State;
import org.eclipse.gemoc.trace.commons.model.trace.Step;
import org.eclipse.gemoc.trace.commons.model.trace.Value;
import org.eclipse.gemoc.trace.gemoc.api.ITraceListener;
import org.eclipse.gemoc.trace.metp.addonbased.server.metp.data.DimensionsAddedEventArguments;
import org.eclipse.gemoc.trace.metp.addonbased.server.metp.data.StatesAddedEventArguments;
import org.eclipse.gemoc.trace.metp.addonbased.server.metp.data.StepsEndedEventArguments;
import org.eclipse.gemoc.trace.metp.addonbased.server.metp.data.StepsStartedEventArguments;
import org.eclipse.gemoc.trace.metp.addonbased.server.metp.data.ValuesAddedEventArguments;
import org.emfjson.jackson.annotations.EcoreIdentityInfo;
import org.emfjson.jackson.module.EMFModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * send ITraceListener events to the protocol client
 */
public class ServerAddonTraceListener implements ITraceListener {
	private static final Logger LOG = LoggerFactory.getLogger(ServerAddonTraceListener.class);
	
	private ObjectMapper emfjsonMapper;
	private EMFModule emfjsonModule;
	
	METPServerImpl metpServerImpl;

	public ServerAddonTraceListener(METPServerImpl metpServerImpl) {
		this.metpServerImpl = metpServerImpl;
		initEMFJSON();
	}

	@Override
	public void statesAdded(List<State<?, ?>> states) {
		try {
			StatesAddedEventArguments args = new StatesAddedEventArguments();
			args.setStateListAsEMFJSON(this.emfjsonMapper.writeValueAsString(states));
			LOG.debug(args.getStateListAsEMFJSON());
			metpServerImpl.traceClient.statesAdded(args);
		} catch (JsonProcessingException e) {
			LOG.error(e.getMessage(), e);
		}
	}

	@Override
	public void stepsStarted(List<Step<?>> steps) {
		try {
			StepsStartedEventArguments args = new StepsStartedEventArguments();
			args.setStateListAsEMFJSON(this.emfjsonMapper.writeValueAsString(steps));
			metpServerImpl.traceClient.stepsStarted(args);
		} catch (JsonProcessingException e) {
			LOG.error(e.getMessage(), e);
		}
	}

	@Override
	public void stepsEnded(List<Step<?>> steps) {
		try {
			StepsEndedEventArguments args = new StepsEndedEventArguments();
			args.setStateListAsEMFJSON(this.emfjsonMapper.writeValueAsString(steps));
			metpServerImpl.traceClient.stepsEnded(args);
		} catch (JsonProcessingException e) {
			LOG.error(e.getMessage(), e);
		}
	}

	@Override
	public void valuesAdded(List<Value<?>> values) {
		try {
			ValuesAddedEventArguments args = new ValuesAddedEventArguments();
			args.setValueListAsEMFJSON(this.emfjsonMapper.writeValueAsString(values));
			metpServerImpl.traceClient.valuesAdded(args);
		} catch (JsonProcessingException e) {
			LOG.error(e.getMessage(), e);
		}
	}

	@Override
	public void dimensionsAdded(List<Dimension<?>> dimensions) {
		try {
			DimensionsAddedEventArguments args = new DimensionsAddedEventArguments();
			args.setDimensionListAsEMFJSON(this.emfjsonMapper.writeValueAsString(dimensions));
			metpServerImpl.traceClient.dimensionsAdded(args);
		} catch (JsonProcessingException e) {
			LOG.error(e.getMessage(), e);
		}
	}
	
	
	protected void initEMFJSON() {
		this.emfjsonMapper = new ObjectMapper();
		final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH);
		dateFormat.setTimeZone(TimeZone.getDefault());

		this.emfjsonMapper.setDateFormat(dateFormat);
		this.emfjsonMapper.setTimeZone(TimeZone.getDefault());

		
		// Optional
		this.emfjsonMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
		        
		this.emfjsonModule = new EMFModule();
		this.emfjsonModule.configure(EMFModule.Feature.OPTION_USE_ID, true);
		// Optional
		this.emfjsonModule.configure(EMFModule.Feature.OPTION_SERIALIZE_TYPE, true);

		this.emfjsonModule.setIdentityInfo(new EcoreIdentityInfo("_id"));
		this.emfjsonMapper.registerModule(this.emfjsonModule);
		
	}

}
