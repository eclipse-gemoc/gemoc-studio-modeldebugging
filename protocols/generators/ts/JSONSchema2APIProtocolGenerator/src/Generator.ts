/*---------------------------------------------------------
 * Copyright (c) 2020 Inria and others.. All rights reserved.
 *--------------------------------------------------------*/

'use strict';

import * as fs from 'fs';
import * as path from 'path';
//import {IProtocol, Protocol as P} from './json_schema';
import {TSGeneratorModule} from './modules/TsAPIGenerator';
import {JavaServerGeneratorModule} from './modules/JavaServerGenerator';
import {XtendArgumentsGeneratorModule} from './modules/XtendArgumentsGenerator';
import {JavaClientGeneratorModule} from './modules/JavaClientGenerator';
import {PlantumlGeneratorModule} from './modules/plantumlGenerator';


import * as pconf from './modules/generatorConfig'

function ensureDirForFile(file : string) {
    var dirName = path.dirname(file)
    if (!fs.existsSync(dirName)) {
        fs.mkdirSync(dirName, {
            recursive: true
        });
    }
}


/// Main
const protocolName = 'ModelExecutionServerProtocol';

var args = process.argv.slice(2);

const protocolGenConfs : pconf.ProtocolGenConfigs = JSON.parse(fs.readFileSync(args[0]).toString());

protocolGenConfs.protocolGenConfigs.forEach(protocolGenConf => {
    //console.table(protocolGenConf)
    console.table(protocolGenConf, ["destFileName"])
    console.table(protocolGenConf, ["packageName", "Values"])
    let protocolSchema = JSON.parse(fs.readFileSync(protocolGenConf.protocolJSONSchemaPath).toString());
    
    if(protocolGenConf.tsAPI != null) {
        console.log("*****");
        console.log("***** Generate TS API for "+protocolGenConf.protocolName);
        const emitTSStr = TSGeneratorModule(protocolGenConf.protocolName, protocolSchema);
        ensureDirForFile(protocolGenConf.tsAPI.destFileName)
        fs.writeFileSync(protocolGenConf.tsAPI.destFileName, emitTSStr, { encoding: 'utf-8'});
        console.info("Saved "+protocolGenConf.tsAPI.destFileName)
    }
    if(protocolGenConf.javaServer != null) {
        console.log("*****");
        console.log("***** Generate I"+protocolGenConf.protocolName+"Server.java");
        const emitJavaServerStr = JavaServerGeneratorModule("I"+protocolGenConf.protocolName+"Server",
            protocolGenConf.javaServer.packageName, 
            protocolSchema);
        ensureDirForFile(protocolGenConf.javaServer.destFileName)
        fs.writeFileSync(protocolGenConf.javaServer.destFileName, emitJavaServerStr, { encoding: 'utf-8'});
        console.info("Saved "+protocolGenConf.javaServer.destFileName)
    }
    if(protocolGenConf.javaClient != null) {
        console.log("*****");
        console.log("***** Generate I"+protocolGenConf.protocolName+"Client.java");
        const emitJavaClientStr = JavaClientGeneratorModule("I"+protocolGenConf.protocolName+"Client",
            protocolGenConf.javaClient.packageName, 
            protocolSchema);
        
        ensureDirForFile(protocolGenConf.javaClient.destFileName)
        fs.writeFileSync(protocolGenConf.javaClient.destFileName, emitJavaClientStr, { encoding: 'utf-8'});
        console.info("Saved "+protocolGenConf.javaClient.destFileName)
    }
    if(protocolGenConf.javaAPI != null) {
        console.log("*****");
        console.log("***** Generate I"+protocolGenConf.protocolName+"Data.xtend");
        const emitXtendArgumentsStr = XtendArgumentsGeneratorModule(protocolGenConf.protocolName+"Data",
            protocolGenConf.javaAPI.packageName, 
            protocolSchema);
        ensureDirForFile(protocolGenConf.javaAPI.destFileName)
        fs.writeFileSync(protocolGenConf.javaAPI.destFileName, emitXtendArgumentsStr, { encoding: 'utf-8'});
        console.info("Saved "+protocolGenConf.javaAPI.destFileName)
    }
    if(protocolGenConf.plantuml != null) {
        console.log("*****");
        console.log("***** Generate Plantuml for "+protocolGenConf.protocolName);
        const emitTSStr = PlantumlGeneratorModule(protocolGenConf.protocolName, protocolSchema);
        ensureDirForFile(protocolGenConf.plantuml.destFileName)
        fs.writeFileSync(protocolGenConf.plantuml.destFileName, emitTSStr, { encoding: 'utf-8'});
        console.info("Saved "+protocolGenConf.plantuml.destFileName)
    }

});