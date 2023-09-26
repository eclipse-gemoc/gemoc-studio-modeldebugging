package org.eclipse.gemoc.executionframework.engine.concurrency;

import org.chocosolver.solver.variables.BoolVar;
import org.chocosolver.util.ESat;

/**
 * struct to store a var solution value
 */
class ChocoSolutionValue{
	ChocoSolutionValue(BoolVar v, ESat val){
        var = v;
        value = val;
    }
   public  BoolVar var;
   public  ESat value;
}

