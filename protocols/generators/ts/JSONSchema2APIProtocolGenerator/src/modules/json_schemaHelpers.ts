/*---------------------------------------------------------
 * Copyright (c) 2020 Inria and others.. All rights reserved.
 *--------------------------------------------------------*/

'use strict';

import {IProtocol, Protocol as P } from './json_schema';

/**
 * Helper class to compute information from a property definition in  json_schema
 */
export class PropertyHelper {
    containerDefinitionName: string;
    propertyName: string;
    propertyType : (P.PropertyType | P.RefType );
    constructor(containerDefinitionName: string, propertyName: string, propertyType : (P.PropertyType | P.RefType )) {
        this.containerDefinitionName = containerDefinitionName;
        this.propertyName = propertyName
        this.propertyType = propertyType
    } 

    propertyTypeOrRefJavaName(): string {
        return this.internalPropertyTypeOrRefJavaName(this.propertyType);
    }

    protected internalPropertyTypeOrRefJavaName(prop: (P.PropertyType | P.RefType )): string {
        if (((prop as P.RefType)?.$ref)) {
            return getRef((prop as P.RefType)?.$ref);
        } else {
            return this.internalPropertyTypeJavaName( prop as P.PropertyType);
        }
    }

    protected internalPropertyTypeJavaName(prop: P.PropertyType ): string {
        switch (prop.type) {
            case 'array':
                const s = this.internalPropertyTypeOrRefJavaName( prop.items);
                //console.table(prop.items);
                //console.table(this);
                if (s.indexOf(' ') >= 0) {
                    return `(${s})[]`;
                }
                return `${s}[]`;
            case 'object':
                
                return this.objectTypeJavaName(prop);
            case 'string':
                if (prop.enum) {
                    return this.getDedicatedTypeJavaName();
                }
                return `String`;
            case 'integer':
                return 'Integer';
            case 'number':
                    return 'Float';
        }
        if (Array.isArray(prop.type)) {

            if(prop.type.length === 2 && prop.type['null']) {
                // this is actually a nullable
                let a = prop.type.filter(e  => e !== 'null')
                switch (a[0]){
                case 'string':
                    return 'String';
                case 'integer':
                    return 'Integer';
                case 'number':
                    return 'Float';
                }
                return 'Object';
            } else {
                return 'Object';
            }

            // if (prop.type.length === 7 && prop.type.sort().join() === 'array,boolean,integer,null,number,object,string') {	// silly way to detect all possible json schema types
            //     return 'Object';
            // } else {
            //     return prop.type.map(v => v === 'integer' ? 'Integer' : v).join(' | ');
            // }
        }
        return prop.type;
    }

    getDedicatedTypeJavaName() {
        return this.containerDefinitionName+capitalizeFirstLetter(this.propertyName);
    }

    objectTypeJavaName( prop: P.ObjectType): string {
        // if (prop.properties) {
        //     let s = openBlock('', '{', false);
    
        //     for (let propName in prop.properties) {
        //         const required = prop.required ? prop.required.indexOf(propName) >= 0 : false;
        //         s += property(hostDefinitionName, propName, !required, prop.properties[propName]);
        //     }
    
        //     s += closeBlock('}', false);
        //     return s;
        // }
        // if (prop.additionalProperties) {
        //     return `Map<String, Object>`;
        //     //return `{ [key: string]: ${orType(prop.additionalProperties.type)}; }`;
        // }
        // return '{}';
        if (prop.properties) {
            if(prop.properties['body']) {
                return this.internalPropertyTypeOrRefJavaName(prop.properties['body']);
            } else {
                return `\/*  TODO objectTypeJavaName() properties ? ${prop} ${this.containerDefinitionName+capitalizeFirstLetter(this.propertyName)} *\/`
            }
            
            // for (let propName in prop.properties) {
            //     const required = prop.required ? prop.required.indexOf(propName) >= 0 : false;
            //     return property(hostDefinitionName, propName, !required, prop.properties[propName]);
            // }
        } else if (prop.additionalProperties) {
            // TODO check version of json_schema.d.ts and use of it ...
            return `Map<String, String>`;
        } else {
            return `\/*  TODO objectTypeJavaName() ? ${prop} ${this.containerDefinitionName+capitalizeFirstLetter(this.propertyName)} *\/`
        }
    }

}

export class ResponseHelper {
    responseDefTypeName:  string;
    responseDef: P.Definition;
    constructor(responseDefTypeName:  string, responseDef: P.Definition){
        this.responseDef=responseDef;
        this.responseDefTypeName=responseDefTypeName;
    }

}

function orType(enm: string | string[]): string {
	if (typeof enm === 'string') {
		return 'String';
	} 
	return enm.join(' | ');
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

// function toEnumLiteral(string: string) {
// 	// TODO  transform camelcase into  uppercase and _
// 	return string.toUpperCase() ;
// }

/**
 * replace java keywords 
 * @param s 
 */
export function isJavaKeyWord(s: string): boolean {
    return s === 'continue' ||s === 'default' || s === 'goto' || s === 'switch' ;
}

export function getJavaSafeName(s: string): string {
    if(isJavaKeyWord(s)){
      return '_'+s;   
    } else return s;
}

export function capitalizeFirstLetter(s: string) {
	return s.charAt(0).toUpperCase() + s.slice(1);
}