

// data structure to configure the generator inputs and outputs for several 
export interface ProtocolGenConfigs {
    protocolGenConfigs: ProtocolGenConfig[];
}

export interface ProtocolGenConfig {
    protocolName:           string;
    protocolShortName:      string;
    protocolJSONSchemaPath: string;
    tsAPI:                  FileGen;
    javaServer:             PackageGen;
    javaClient:             PackageGen;
    javaAPI:                PackageGen;
    plantuml:               FileGen;
}

export interface PackageGen {
    packageName:  string;
    destFileName: string;
}

export interface FileGen {
    destFileName: string;
}