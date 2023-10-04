package org.eclipse.gemoc.executionframework.engine.concurrency;

import java.util.ArrayList;

import org.chocosolver.util.ESat;

/**
 * a simple class to store choco's solution
 */
public class ChocoSolution extends ArrayList<ChocoSolutionValue>{
	
	

	    /**
	 * 
	 */
	private static final long serialVersionUID = 3373866573694257451L;

		/**
	     * return the set of undefined var in the solution
	     * @return
	     */
	    public ArrayList<ChocoSolutionValue> getUndefinedVar(){
	        ArrayList<ChocoSolutionValue> res = new ArrayList<ChocoSolutionValue>();
	        for(ChocoSolutionValue sv : this){
	            if(sv.value == ESat.UNDEFINED){
	                res.add(sv);
	            }
	        }
	        return res;
	    }

	    /**
	     * retuens true if at least one variable is undefined on the solution
	     * @return
	     */
	    public boolean containsUndefined(){
	        for(ChocoSolutionValue sv : this){
	            if(sv.value == ESat.UNDEFINED){
	                return true;
	            }
	        }
	        return false;
	    }

	    /**
	     * realize a deep copy of the solution
	     * @return
	     */
	    public ChocoSolution deepCopy(){
	        ChocoSolution res = new ChocoSolution();
	        for(ChocoSolutionValue sv : this){
	            res.add(new ChocoSolutionValue(sv.var, sv.value));
	        }
	        return res;
	    }

}
