/*---------------------------------------------------------
 * Copyright (c) 2020 Inria and others.. All rights reserved.
 *--------------------------------------------------------*/

'use strict';

//import * as fs from 'fs';
import {IProtocol, Protocol as P} from './json_schema';
import {ResponseHelper, getJavaSafeName, capitalizeFirstLetter, isJavaKeyWord} from './json_schemaHelpers'

let numIndents = 0;

export function JavaServerGeneratorModule(moduleName: string, basePackageName: string, schema: IProtocol): string {

	let s = '';
	s += line("/*---------------------------------------------------------------------------------------------");
	s += line(" * Copyright (c) 2020 Inria and others.");
	s += line(" * All rights reserved. This program and the accompanying materials");
	s += line(" * are made available under the terms of the Eclipse Public License v1.0");
	s += line(" * which accompanies this distribution, and is available at");
	s += line(" * http://www.eclipse.org/legal/epl-v10.html");
	s += line(" *--------------------------------------------------------------------------------------------*/");
	s += line("/* GENERATED FILE, DO NOT MODIFY MANUALLY */");
	s += line();
	s += line("package "+basePackageName+".services;");
	s += line();

	s += line("import java.util.concurrent.CompletableFuture;");
	s += line("import org.eclipse.lsp4j.jsonrpc.services.JsonRequest;");

	s += line("import "+basePackageName+".data.*;");
	// TODO add the other imports individually 

	//s += comment(schema.description);
	s += comment({ description : 'Server interface for the model execution trace protocol.\nAuto-generated from json schema. Do not edit manually.'});

	s += openBlock(`public interface  ${moduleName}`);

	// for (let typeName in schema.definitions) {
	// 	console.log(typeName);
	// }

	//console.table(schema.definitions);
	
	for (let typeName in schema.definitions) {

		const d2 = schema.definitions[typeName];

		let supertype: string = null;
		if ((<P.AllOf>d2).allOf) {
			const array = (<P.AllOf>d2).allOf;
			for (let d of array) {
				if ((<P.RefType>d).$ref) {
					supertype = getRef((<P.RefType>d).$ref);
				} else {
					if(supertype == 'Request') {
						let requestName = typeName.replace(/(.*)Request$/, "$1");  // TODO find the real name of the command in the "command"" property rather than infer from  typeName
						let responseDefTypeName = typeName.replace(/(.*)Request$/, "$1Response"); // TODO do we have a better way to find the response from the request in the json schema ?
						let responseDefType =  schema.definitions[responseDefTypeName];
						var responseDef : P.Definition
						for (let respDef of (<P.AllOf>responseDefType).allOf) {
							if ((<P.RefType>respDef).$ref) {
							} else {
								responseDef = <P.Definition> respDef;
							}
						}
						const response : ResponseHelper = new ResponseHelper(responseDefTypeName, responseDef);
						
						s += RequestInterface(requestName, <P.Definition> d, response);
					}
				}
			}
		}
	}

	s += closeBlock();
	s += line();

	return s;
}

//function RequestInterface(interfaceName: string, definition: P.Definition, responsedefinition: P.Definition): string {
function RequestInterface(interfaceName: string, definition: P.Definition, responseHelper: ResponseHelper): string {
	let desc = definition.description;
	let methodName = "";
	if (definition.properties && definition.properties.event && definition.properties.event['enum']) {
		const eventName = `${definition.properties.event['enum'][0]}`;
		methodName = eventName;
		if (eventName) {
			desc = `Event message for '${eventName}' event type.\n${desc}`;
		}
	} else if (definition.properties && definition.properties.command && definition.properties.command['enum']) {
		const requestName = `${definition.properties.command['enum'][0]}`;
		methodName = requestName;
		if (requestName) {
			const RequestName = requestName[0].toUpperCase() + requestName.substr(1);
			desc = `${RequestName} request; value of command field is '${requestName}'.\n${desc}`;
		}
	}
	let s = line();
	s += comment({ description : desc });

	s += line("@JsonRequest");
	// find response type
	var returnType = 'CompletableFuture<Void>';

	if(responseHelper.responseDef && responseHelper.responseDef.properties && responseHelper.responseDef.properties.body){
		/*console.log(interfaceName+': responsedefinition = '+responsedefinition.properties.body);
		console.log(responsedefinition.properties.body);
		console.table(responsedefinition.properties.body);
		console.table(argumentType(responsedefinition.properties.body));*/

		if(responseHelper.responseDef.properties.body.type === 'object'){
			returnType = "CompletableFuture<"+responseHelper.responseDefTypeName+">";
		} else {
			returnType = "CompletableFuture<"+propertyType(responseHelper.responseDef.properties.body)+">";
		}
	}
	var argsString : string[] = [];
	for (let propName in definition.properties) {
		
		const type = propertyType(definition.properties[propName]);
		const propertyDef = `${type} ${propName}`;
		if (type[0] === '\'' && type[type.length-1] === '\'' && type.indexOf('|') < 0) {
			//s += line(`// ${propertyDef};`);
		} else {
			argsString.push(`${propertyDef}`);
		}
	}
	
	s += openBlock(`default ${returnType} ${methodName}(`+argsString.join(', ')+`)`);
	s += line(`throw new UnsupportedOperationException();`)
	s += closeBlock();
	return s;
}


function Enum(typeName: string, definition: P.StringType): string {
	let s = line();
	s += comment(definition);
	const x = enumAsOrType(definition.enum);
	s += line(`export type ${typeName} = ${x};`);
	return s;
}

function enumAsOrType(enm: string[]) {
	return enm.map(v => `'${v}'`).join(' | ');
}

function comment(c: P.Commentable): string {

	let description = c.description || '';

	if ((<any>c).items) {	// array
		c = (<any>c).items;
	}

	// a 'closed' enum with individual descriptions
	if (c.enum && c.enumDescriptions) {
		for (let i = 0; i < c.enum.length; i++) {
			description += `\n'${c.enum[i]}': ${c.enumDescriptions[i]}`;
		}
	}

	// an 'open' enum
	if (c._enum) {
		description += '\nValues: ';
		if (c.enumDescriptions) {
			for (let i = 0; i < c._enum.length; i++) {
				description += `\n'${c._enum[i]}': ${c.enumDescriptions[i]}`;
			}
			description += '\netc.';
		} else {
			description += `${c._enum.map(v => `'${v}'`).join(', ')}, etc.`;
		}
	}

	if (description) {
		description = description.replace(/<code>(.*)<\/code>/g, "'$1'");
		numIndents++;
		description = description.replace(/\n/g, '\n' + indent());
		numIndents--;
		if (description.indexOf('\n') >= 0) {
			return line(`/** ${description}\n${indent()}*/`);
		} else {
			return line(`/** ${description} */`);
		}
	}
	return '';
}

function openBlock(str: string, openChar?: string, indent?: boolean): string {
	indent = typeof indent === 'boolean' ?  indent : true;
	openChar = openChar || ' {';
	let s = line(`${str}${openChar}`, true, indent);
	numIndents++;
	return s;
}

function closeBlock(closeChar?: string, newline?: boolean): string {
	newline = typeof newline === 'boolean' ? newline : true;
	closeChar = closeChar || '}';
	numIndents--;
	return line(closeChar, newline);
}

function propertyType(prop: any): string {
	if (prop.$ref) {
		return getRef(prop.$ref);
	}
	switch (prop.type) {
		case 'array':
			const s = propertyType(prop.items);
			if (s.indexOf(' ') >= 0) {
				return `(${s})[]`;
			}
			return `${s}[]`;
		case 'object':
			return objectType(prop);
		case 'string':
			if (prop.enum) {
				return enumAsOrType(prop.enum);
			}
			return `String`;
		case 'integer':
			return 'Integer';
	}
	if (Array.isArray(prop.type)) {
		if (prop.type.length === 7 && prop.type.sort().join() === 'array,boolean,integer,null,number,object,string') {	// silly way to detect all possible json schema types
			return 'Object';
		} else {
			return prop.type.map(v => v === 'integer' ? 'Integer' : v).join(' | ');
		}
	}
	return prop.type;
}

function objectType(prop: any): string {
	if (prop.properties) {
		let s = openBlock('', '{', false);

		for (let propName in prop.properties) {
			const required = prop.required ? prop.required.indexOf(propName) >= 0 : false;
			s += property(propName, !required, prop.properties[propName]);
		}

		s += closeBlock('}', false);
		return s;
	}
	if (prop.additionalProperties) {
		return `{ [key: string]: ${orType(prop.additionalProperties.type)}; }`;
	}
	return '{}';
}

function orType(enm: string | string[]): string {
	if (typeof enm === 'string') {
		return enm;
	}
	return enm.join(' | ');
}

function requestArg(name: string,  prop: P.PropertyType): string {
	let s = '';
	s += comment(prop);
	const type = propertyType(prop);
	const propertyDef = `${type} ${name}`;
	if (type[0] === '\'' && type[type.length-1] === '\'' && type.indexOf('|') < 0) {
		s += line(`// ${propertyDef};`);
	} else {
		s += line(`${propertyDef};`);
	}
	return s;
}

function property(name: string, optional: boolean, prop: P.PropertyType): string {
	let s = '';
	s += comment(prop);
	const type = propertyType(prop);
	const propertyDef = `${name}${optional ? '?' : ''}: ${type}`;
	if (type[0] === '\'' && type[type.length-1] === '\'' && type.indexOf('|') < 0) {
		s += line(`// ${propertyDef};`);
	} else {
		s += line(`${propertyDef};`);
	}
	return s;
}

function getRef(ref: string): string {
	const REXP = /#\/(.+)\/(.+)/;
	const matches = REXP.exec(ref);
	if (matches && matches.length === 3) {
		return matches[2];
	}
	console.log('error: ref');
	return ref;
}

function indent(): string {
	return '\t'.repeat(numIndents);
}

function line(str?: string, newline?: boolean, indnt?: boolean): string {
	newline = typeof newline === 'boolean' ? newline : true;
	indnt = typeof indnt === 'boolean' ? indnt : true;
	let s = '';
	if (str) {
		if (indnt) {
			s += indent();
		}
		s += str;
	}
	if (newline) {
		s += '\n';
	}
	return s;
}


/// Main

/*
const debugProtocolSchema = JSON.parse(fs.readFileSync('./ModelExecutionTraceProtocol.json').toString());

const emitStr = TSGeneratorModule('ModelExecutionTraceProtocol', debugProtocolSchema);

fs.writeFileSync(`../metp_protocol/src/ModelExecutionTraceProtocol.ts`, emitStr, { encoding: 'utf-8'});
*/
