# Protocol Generator

This folder contains the API generator for protocols expressed using a json schema.

## Configuration

* `generator_config.json` : contains generation directives (ie. list of protocols and locations of output files) 


Currently, for each protocol, the generator is able to produce:
 
* a typescript interface for the protocol

* java interfaces and classes compatible with `org.eclipse.lsp4j.jsonrpc` library from https://github.com/eclipse/lsp4j in order to implement Client and Server in java.

* an overview of the protocol as a plantuml diagram. 

## Useful commands


* `npm run build` compiles the generator (in the `dist` folder)

* `npm run watch` continuously compiles the generator (useful in dev)

* `npm run  generate` calls the generator to generate the language specific interface code from the `*.json` schemas defined in `generator_config.json` 
