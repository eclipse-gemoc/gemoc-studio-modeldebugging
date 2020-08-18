/*---------------------------------------------------------
 * Copyright (c) 2020 Inria and others.. All rights reserved.
 *--------------------------------------------------------*/

'use strict';

import * as fs from 'fs';
//import {IProtocol, Protocol as P} from './json_schema';
import {TSGeneratorModule} from './tsGenerator';
import {JavaServerGeneratorModule} from './javaServerGenerator';
import {XtendArgumentsGeneratorModule} from './xtendArgumentsGenerator';
import {JavaClientGeneratorModule} from './javaClientGenerator';

/// Main

const debugProtocolSchema = JSON.parse(fs.readFileSync('./ModelExecutionTraceProtocol.json').toString());

console.table(debugProtocolSchema.definitions);

console.log("**************************************************");
console.log("***** Generate ModelExecutionTraceProtocol.ts ****");
const emitTSStr = TSGeneratorModule('ModelExecutionTraceProtocol', debugProtocolSchema);
fs.writeFileSync(`../metp_protocol/src/ModelExecutionTraceProtocol.ts`, emitTSStr, { encoding: 'utf-8'});

console.log("***********************************************************");
console.log("***** Generate IModelExecutionTraceProtocolServer.java ****");
const emitJavaServerStr = JavaServerGeneratorModule('IModelExecutionTraceProtocolServer_gen',
	`org.eclipse.gemoc.trace.metp.addonbased.server.metp`, 
	debugProtocolSchema);
fs.writeFileSync(`../../plugins/org.eclipse.gemoc.trace.metp.addonbased.server/src/org/eclipse/gemoc/trace/metp/addonbased/server/metp/services/IModelExecutionTraceProtocolServer_gen.java`, 
	emitJavaServerStr, 
	{ encoding: 'utf-8'});
	
console.log("***********************************************************");
console.log("***** Generate IModelExecutionTraceProtocolClient.java ****");
const emitJavaClientStr = JavaClientGeneratorModule('IModelExecutionTraceProtocolClient_gen',
	`org.eclipse.gemoc.trace.metp.addonbased.server.metp`, 
	debugProtocolSchema);
fs.writeFileSync(`../../plugins/org.eclipse.gemoc.trace.metp.addonbased.server/src/org/eclipse/gemoc/trace/metp/addonbased/server/metp/services/IModelExecutionTraceProtocolClient_gen.java`, 
	emitJavaClientStr, 
	{ encoding: 'utf-8'});
	
console.log("**********************************************************");
console.log("***** Generate IModelExecutionTraceProtocolData.xtend ****");
const emitXtendArgumentsStr = XtendArgumentsGeneratorModule('ModelExecutionTraceProtocolData',
	`org.eclipse.gemoc.trace.metp.addonbased.server.metp`, 
	debugProtocolSchema);
fs.writeFileSync(`../../plugins/org.eclipse.gemoc.trace.metp.addonbased.server/src/org/eclipse/gemoc/trace/metp/addonbased/server/metp/data/ModelExecutionTraceProtocolData_gen.xtend`, 
	emitXtendArgumentsStr, 
	{ encoding: 'utf-8'});