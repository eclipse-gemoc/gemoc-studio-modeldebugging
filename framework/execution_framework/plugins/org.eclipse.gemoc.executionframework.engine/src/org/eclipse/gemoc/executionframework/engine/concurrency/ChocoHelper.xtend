package org.eclipse.gemoc.executionframework.engine.concurrency

import java.util.ArrayList
import java.util.HashSet
import java.util.List
import java.util.Set
import org.chocosolver.solver.Model
import org.chocosolver.solver.Solver
import org.chocosolver.solver.variables.BoolVar
import org.chocosolver.solver.variables.Variable
import org.chocosolver.util.ESat
import org.eclipse.gemoc.trace.commons.model.generictrace.GenericParallelStep
import org.eclipse.gemoc.trace.commons.model.generictrace.GenericStep
import org.eclipse.gemoc.trace.commons.model.generictrace.GenerictraceFactory
import org.eclipse.gemoc.trace.commons.model.trace.ParallelStep
import org.eclipse.gemoc.trace.commons.model.trace.SmallStep
import org.eclipse.gemoc.trace.commons.model.trace.Step

class ChocoHelper {
	/** 
	 * help retrieving the exact logical step used for the logical step decider
	 */
	public static Set<ParallelStep<? extends Step<?>, ?>> lastChocoLogicalSteps = null

	def static Set<ParallelStep<? extends Step<?>, ?>> computePossibleStepInExtension(Model symbolicPossibleSteps,
		AbstractConcurrentExecutionEngine.StepFactory stepFactory) {
		var Solver solver = symbolicPossibleSteps.getSolver()
		var int nbSmallSteps = 0
		for (Variable v : symbolicPossibleSteps.getVars()) {
			if (v instanceof SmallStepVariable) {
				nbSmallSteps++
			}
		}
		// 6. Launch the resolution process (note, solutions provided by choco are folded in the sense that they contained "undefined" values
		// whose meaning is : whatever the value, the model is satisfied. So I created small helper to get the exhaustive set of solutions
		var ArrayList<ChocoSolution> allFoldedValues = new ArrayList<ChocoSolution>()
		while (solver.solve()) {
			var ChocoSolution aSoluce = new ChocoSolution()
			for (var int vNum = 0; vNum < nbSmallSteps; vNum++) {
				// not all vars since internal variable doesn't mind for us
				var Variable v = symbolicPossibleSteps.getVar(vNum)
				if (v instanceof BoolVar) {
					aSoluce.add(new ChocoSolutionValue(v.asBoolVar(), v.asBoolVar().getBooleanValue()))
				}
			}
			allFoldedValues.add(aSoluce)
		}
		// 6.1: unfold all solutions provided by the solver
		var ArrayList<ChocoSolution> allUnfoldedSolutions = unfoldSolutions(allFoldedValues)
		for (ChocoSolution s : allUnfoldedSolutions) {
			System.out.print("solution: ")
			for (ChocoSolutionValue sv : s) {
				if (sv.^var instanceof SmallStepVariable) {
					System.out.
						print(''' «sv.^var.getName()»(«((sv.^var as SmallStepVariable)).associatedSmallStep») =«sv.value»''')
				} else {
					System.out.print(''' «sv.^var.getName()»=«sv.value»''')
				}
			}
			System.out.println("")
		}
		lastChocoLogicalSteps = null
		var Set<ParallelStep<? extends Step<?>, ?>> result = new HashSet<ParallelStep<? extends Step<?>, ?>>()
		for (ChocoSolution s : allUnfoldedSolutions) {
			var GenericParallelStep parStep = GenerictraceFactory.eINSTANCE.createGenericParallelStep()
			for (ChocoSolutionValue sv : s) {
				if (sv.^var instanceof SmallStepVariable) {
					if (sv.value === ESat.TRUE) {
						parStep.getSubSteps().add(
							(stepFactory.createClonedInnerStep(
								((sv.^var as SmallStepVariable)).associatedSmallStep) as GenericStep)) // GenericSmallStep smallStep = GenerictraceFactory.eINSTANCE.createGenericSmallStep();
						// MSEOccurrence mseOccurrence = TraceFactory.eINSTANCE.createMSEOccurrence();
						// mseOccurrence.setMse(((GenericStep) ((SmallStepVariable)sv.var).associatedSmallStep).getMseoccurrence().getMse());
						// smallStep.setMseoccurrence(mseOccurrence);
						// parStep.getSubSteps().add(smallStep);
					}
				}
			}
			result.add(parStep)
		}
		lastChocoLogicalSteps = result
		return result
	}

	def static package ArrayList<ChocoSolution> unfoldSolutions(ArrayList<ChocoSolution> allFoldedSolutions) {
		var ArrayList<ChocoSolution> allUnfoldedSolutions = new ArrayList<ChocoSolution>()
		for (ChocoSolution s : allFoldedSolutions) {
			if (s.containsUndefined()) {
				var ArrayList<ChocoSolutionValue> allUndefined = s.getUndefinedVar()
				var double bound = Math.pow(2, allUndefined.size())
				for (var double i = 0; i < bound; i++) {
					var ChocoSolution sPrime = s.deepCopy()
					for (var int bitLocation = 0; bitLocation < allUndefined.size(); bitLocation++) {
						if (isBitSet((i as long), bitLocation)) {
							sPrime.get(s.indexOf(allUndefined.get(bitLocation))).value = ESat.TRUE
						} else {
							sPrime.get(s.indexOf(allUndefined.get(bitLocation))).value = ESat.FALSE
						}
					}
					allUnfoldedSolutions.add(sPrime)
				}
			} else {
				allUnfoldedSolutions.add(s)
			}
		}
		return allUnfoldedSolutions
	}

	def private static Boolean isBitSet(long b, int bit) {
		return (b.bitwiseAnd((1 << bit))) !== 0
	}

	def static List<SmallStepVariable> getSmallStepVariables(Model symbolicPossibleSteps) {
		symbolicPossibleSteps.vars.filter(SmallStepVariable).toList
	}

	def static Set<SmallStep<?>> getSmallSteps(Model symbolicPossibleSteps) {
		symbolicPossibleSteps.smallStepVariables.map[associatedSmallStep].toSet
	}

	def static void addExclusionConstraint(Model symbolicPossibleSteps, SmallStep<?> s1, SmallStep<?> s2) {		
		val stepVars = symbolicPossibleSteps.smallStepVariables.filter[(associatedSmallStep === s1) || (associatedSmallStep === s2)].toList
		if (stepVars.size === 2) {
			symbolicPossibleSteps.addClausesAtMostOne(stepVars)
		}
	}
}
