//declare module traceEcore {

    export interface Mse {
        eClass: string;
        $ref: string;
    }

    export interface Mseoccurrence {
        _id: string;
        mse: Mse;
    }

    export interface StartingState {
        eClass: string;
        $ref: string;
    }

    export interface Step {
        eClass: string;
        _id: string;
        mseoccurrence: Mseoccurrence;
        startingState: StartingState;
    }

//}

