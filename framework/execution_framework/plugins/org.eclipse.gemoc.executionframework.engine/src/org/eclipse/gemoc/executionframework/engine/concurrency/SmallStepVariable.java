package org.eclipse.gemoc.executionframework.engine.concurrency;

import org.chocosolver.solver.Model;
import org.chocosolver.solver.variables.impl.BoolVarImpl;
import org.eclipse.gemoc.trace.commons.model.trace.SmallStep;

public class SmallStepVariable extends BoolVarImpl {
	
	    public SmallStep<?> associatedSmallStep = null;
	    public SmallStepVariable(String name, Model model, SmallStep<?> smallStep) {
	        super(name, model);
	        associatedSmallStep = smallStep;
	}

}
