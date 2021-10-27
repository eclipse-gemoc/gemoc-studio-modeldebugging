/*---------------------------------------------------------
 * Copyright (c) 2020 Inria and others.. All rights reserved.
 *--------------------------------------------------------*/

'use strict';

import * as fs from 'fs';
//import {IProtocol, Protocol as P} from './json_schema';
import {TSGeneratorModule} from './modules/TsAPIGenerator';
import {JavaServerGeneratorModule} from './modules/JavaServerGenerator';
import {XtendArgumentsGeneratorModule} from './modules/XtendArgumentsGenerator';
import {JavaClientGeneratorModule} from './modules/JavaClientGenerator';

/// Main

const protocolName = 'ModelExecutionServerProtocol';

var args = process.argv.slice(2);

const debugProtocolSchema = JSON.parse(fs.readFileSync(args[0]).toString());

console.table(debugProtocolSchema.definitions);

console.log("**************************************************");
console.log("***** Generate ModelExecutionTraceProtocol.ts ****");
const emitTSStr = TSGeneratorModule('ModelExecutionTraceProtocol', debugProtocolSchema);
fs.writeFileSync(args[1], emitTSStr, { encoding: 'utf-8'});

console.log("***********************************************************");
console.log("***** Generate IModelExecutionTraceProtocolServer.java ****");
const emitJavaServerStr = JavaServerGeneratorModule('IModelExecutionTraceProtocolServer',
	`org.eclipse.gemoc.trace.metp.addonbased.server.metp`, 
	debugProtocolSchema);
fs.writeFileSync(args[2], emitJavaServerStr, { encoding: 'utf-8'});
	
console.log("***********************************************************");
console.log("***** Generate IModelExecutionTraceProtocolClient.java ****");
const emitJavaClientStr = JavaClientGeneratorModule('IModelExecutionTraceProtocolClient',
	`org.eclipse.gemoc.trace.metp.addonbased.server.metp`, 
	debugProtocolSchema);
fs.writeFileSync(args[3], emitJavaClientStr, { encoding: 'utf-8'});
	
console.log("**********************************************************");
console.log("***** Generate IModelExecutionTraceProtocolData.xtend ****");
const emitXtendArgumentsStr = XtendArgumentsGeneratorModule('ModelExecutionTraceProtocolData',
	`org.eclipse.gemoc.trace.metp.addonbased.server.metp`, 
	debugProtocolSchema);
fs.writeFileSync(args[4], emitXtendArgumentsStr, { encoding: 'utf-8'});
