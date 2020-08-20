
import * as genericTraceEcore from "./genericTraceEcore"

//declare module traceEcore {
// simplified version of the GenericTrace.ecore  for use with EMFJSON

    export interface EObject_Ref extends genericTraceEcore.EMFJSONRef {
		ref: any; // the resolved EObject
    }
    export interface EStructuralFeature_Ref extends genericTraceEcore.EMFJSONRef  {
		ref: any; // the resolved EStructuralFeature
    }

    export interface GenericTracedObject  extends genericTraceEcore.TracedObject{
       originalObject: EObject_Ref;
       allDimensions: GenericDimension[];
    }

	export interface GenericDimension extends genericTraceEcore.Dimension {
        dynamicProperty: EStructuralFeature_Ref;
	}
	
	//  TODO check the simplification, can we maintain it ?
	export interface GenericNumberAttributeValue extends genericTraceEcore.Value{
		attributeValue: number
	}
	export interface GenericStringAttributeValue extends genericTraceEcore.Value{
		attributeValue: string
	}
	export interface GenericManyNumberAttributeValue extends genericTraceEcore.Value{
		attributeValue: number[]
	}
	export interface GenericManyStringAttributeValue extends genericTraceEcore.Value{
		attributeValue: string[]
	}

//}

