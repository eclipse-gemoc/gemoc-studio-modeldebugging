//declare module traceEcore {
// simplified version of the GenericTrace.ecore  for use with EMFJSON


    export interface MSEOccurrence {
        eClass: string;
        _id: string;
		parameters: any[];
		result: any[];
	}
	
    export interface Trace {
        eClass: string;
        _id: string;
        rootStep: Step;
        tracedObjects: TracedObject[];
        states: State[];
		launconfiguration: any; 
    }

    export interface Step {
        eClass: string;
        _id: string;
        mseoccurrence: MSEOccurrence;
        startingState: State_Ref;
        endingState: State_Ref;
    }
	export interface Step_Ref {
			eClass: string;
        	$ref: string;
	}

    export interface BigStep  extends Step{
        subSteps: Step[];
    }
    export interface SmallStep  extends Step{
    }
    export interface SequentialStep  extends BigStep{
    }
    export interface ParallelStep  extends BigStep{
    }

    export interface TracedObject {
        eClass: string;
        _id: string;
       // originalObject: OriginalObject;
       // allDimensions: AllDimension[];
		dimensions: Dimension_Ref;
    }

	export interface Dimension_Ref {
			eClass: string;
        	$ref: string;
	}
	
	export interface Dimension {
        eClass: string;
        _id: string;
        values: Value[];
    }


    export interface Value {
        eClass: string;
        _id: string;
        states: State_Ref[];
       // referenceValue: ReferenceValue;
       // attributeValue: string;
    }
	export interface Value_Ref {
			eClass: string;
        	$ref: string;
	}


    export interface State {
        eClass: string;
        _id: string;
        startedSteps: Step_Ref[];
        values: Value_Ref[];
        endedSteps: Step_Ref[];
    }
	export interface State_Ref {
			eClass: string;
        	$ref: string;
	}



//}

