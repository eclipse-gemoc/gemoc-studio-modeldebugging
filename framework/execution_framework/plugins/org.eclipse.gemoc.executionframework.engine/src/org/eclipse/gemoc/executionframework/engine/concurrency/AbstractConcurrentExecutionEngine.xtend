package org.eclipse.gemoc.executionframework.engine.concurrency

import java.util.ArrayList
import java.util.Collections
import java.util.Comparator
import java.util.HashSet
import java.util.List
import java.util.Set
import org.chocosolver.solver.Model
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.util.EcoreUtil
import org.eclipse.gemoc.executionframework.engine.Activator
import org.eclipse.gemoc.executionframework.engine.concurrency.deciders.DeciderException
import org.eclipse.gemoc.executionframework.engine.concurrency.deciders.ILogicalStepDecider
import org.eclipse.gemoc.executionframework.engine.concurrency.strategies.ConcurrencyStrategy
import org.eclipse.gemoc.executionframework.engine.concurrency.strategies.EnumeratingFilteringStrategy
import org.eclipse.gemoc.executionframework.engine.concurrency.strategies.FilteringStrategy
import org.eclipse.gemoc.executionframework.engine.concurrency.strategies.Strategy
import org.eclipse.gemoc.executionframework.engine.concurrency.strategies.SymbolicFilteringStrategy
import org.eclipse.gemoc.executionframework.engine.core.AbstractExecutionEngine
import org.eclipse.gemoc.executionframework.engine.core.EngineStoppedException
import org.eclipse.gemoc.trace.commons.model.generictrace.GenericParallelStep
import org.eclipse.gemoc.trace.commons.model.trace.ParallelStep
import org.eclipse.gemoc.trace.commons.model.trace.SmallStep
import org.eclipse.gemoc.trace.commons.model.trace.Step
import org.eclipse.gemoc.xdsmlframework.api.core.EngineStatus
import org.eclipse.gemoc.xdsmlframework.api.engine_addon.IEngineAddon
import org.eclipse.xtend.lib.annotations.Accessors

import static extension org.eclipse.gemoc.executionframework.engine.concurrency.ChocoHelper.*

//TODO manage runconfiguration with strategies?
abstract class AbstractConcurrentExecutionEngine<C extends AbstractConcurrentModelExecutionContext<R, ?, ?>, R extends IConcurrentRunConfiguration> extends AbstractExecutionEngine<C, R> {

	def protected abstract void doAfterLogicalStepExecution(ParallelStep<?, ?> logicalStep)

	def protected abstract void executeSmallStep(SmallStep<?> smallStep) throws ConcurrentStepException

	def protected abstract void performSpecificInitialize(C executionContext)

	def protected abstract Model computeInitialLogicalSteps()

	def abstract Set<String> getSemanticRules()

	def abstract Set<EPackage> getAbstractSyntax()

	ILogicalStepDecider _logicalStepDecider

	protected Model symbolicLogicalSteps

	protected Set<ParallelStep<? extends Step<?>, ?>> _possibleLogicalSteps = new HashSet<ParallelStep<? extends Step<?>, ?>>
	ParallelStep<?, ?> _selectedLogicalStep

	@Accessors
	val List<ConcurrencyStrategy> concurrencyStrategies = new ArrayList<ConcurrencyStrategy>()
	@Accessors
	val List<Strategy> filteringStrategies = new ArrayList<Strategy>()

	/**
	 * Factory managing the steps this engine places inside the parallel steps it generates.  
	 */
	static class StepFactory implements Comparator<Step<?>> {
		/**
		 * Create a clone of the given inner step, assuming that this step has previously been created by this engine.
		 * 
		 * If needed, can be overridden by any engine that has its own custom class for inner steps.
		 */
		def Step<?> createClonedInnerStep(Step<?> ss) {
			return EcoreUtil::copy(ss)
		}

		/**
		 * Return 0 if the two inner steps are equal, assuming that the steps have previously been created by this engine.
		 * 
		 * If needed, can be overridden by any engine that has its own custom class for inner steps.
		 */
		override compare(Step<?> step1, Step<?> step2) {
			if(EcoreUtil::equals(step1, step2)) 0 else -1
		}
	}

	extension protected val StepFactory stepFactory = createStepFactory

	protected def createStepFactory() {
		new StepFactory
	}

	def protected Set<ParallelStep<? extends Step<?>, ?>> computePossibleLogicalSteps() {
		val model = computeInitialLogicalSteps()

		val steps = model.smallSteps.toList

		steps.forEach [ s1, idx |
			steps.subList(idx, steps.size).forEach [ s2 |
				if (!applyConcurrencyStrategies(s1, s2)) {
					model.addExclusionConstraint(s1, s2)
				}
			]
		]

		return filterByStrategies(model)
	}

	/** 
	 * Return a list of steps filtered by all filtering strategies
	 */
	private def Set<ParallelStep<? extends Step<?>, ?>> filterByStrategies(Model symbolicPossibleSteps) {
		filteringStrategies.filter(SymbolicFilteringStrategy).forEach[filterSymbolically(symbolicPossibleSteps)]

		val possibleSteps = symbolicPossibleSteps.computePossibleStepInExtension(stepFactory)

		filteringStrategies.filter(EnumeratingFilteringStrategy).fold(possibleSteps, [ steps, fh |
			fh.filter(steps, stepFactory)
		])
	}

	/**
	 * Check that concurrency strategies allow two steps to run concurrently.
	 * Method to be used by subclasses in order to implement 'computeInitialLogicalSteps'.
	 * @param step1 The first step.
	 * @param step2 The second step.
	 * @return true if the concurrency strategies allow both steps to run concurrently.  
	 */
	protected final def boolean applyConcurrencyStrategies(SmallStep<?> step1, SmallStep<?> step2) {
		concurrencyStrategies.forall[ch|ch.canBeConcurrent(step1, step2)]
	}

	override protected void beforeStart() {
	}

	def final void changeLogicalStepDecider(ILogicalStepDecider newDecider) {
		_logicalStepDecider = newDecider
	}

	def final ILogicalStepDecider getLogicalStepDecider() {
		return _logicalStepDecider
	}

	def final ParallelStep<?, ?> getSelectedLogicalStep() {
		synchronized (this) {
			return _selectedLogicalStep
		}
	}

	def final protected void setSelectedLogicalStep(ParallelStep<?, ?> ls) {
		synchronized (this) {
			_selectedLogicalStep = ls
		}
	}

	def final protected void switchDeciderIfNecessary() {
		if (getLogicalStepDecider() !== null && getLogicalStepDecider() !== _logicalStepDecider) {
			_logicalStepDecider = getLogicalStepDecider()
		}
	}

	override final protected void performStart() {
		engineStatus.setNbLogicalStepRun(0)
		try {
			while (!_isStopped) {
				performExecutionStep()
			}
		} catch (EngineStoppedException ese) {
			throw ese // forward the stop exception
		} catch (Throwable e) {
			throw new RuntimeException(e)
		}

	}

	override protected final void performStop() {
		setSelectedLogicalStep(null)
		if (getLogicalStepDecider() !== null) {
			// unlock decider if this is a user decider
			getLogicalStepDecider().preempt()
		}
	}

	/** 
	 * run all the event occurrences of this logical step
	 * @param logicalStepToApply
	 * @throws CodeExecutionException
	 */
	@SuppressWarnings("unchecked") def final protected void executeSelectedLogicalStep() {
		if (!_isStopped) {
			// execute while stopped may occur when we push the
			// stop button when paused in the debugger
			beforeExecutionStep(_selectedLogicalStep)
			for (Step<?> step : ((_selectedLogicalStep as GenericParallelStep)).getSubSteps()) {
				var SmallStep<?> sstep = (step as SmallStep<?>)
				executeSmallStep(sstep)
			}
			afterExecutionStep(Collections.emptyList())
		}
	}

	private static def List<ParallelStep<?, ?>> sortSteps(Set<ParallelStep<? extends Step<?>, ?>> steps) {
		// Try to deterministically sort the possible steps, for better UI/UX
		val List<ParallelStep<?, ?>> sortedSteps = new ArrayList<ParallelStep<?, ?>>
		sortedSteps.addAll(steps.sortWith([ s1, s2 |

			// If different amount of atomic steps, sort per amount
			if (s1.subSteps.size != s2.subSteps.size) {
				return s1.subSteps.size - s2.subSteps.size
			} // Else, sort based on atomic steps names
			else {

				// Sort the lists of atomic steps based on names
				val s1AtomicSteps = new ArrayList(s1.subSteps)
				val s2AtomicSteps = new ArrayList(s2.subSteps)
				val Comparator<Step<?>> stepsComparator = [ Step<?> a1, Step<?> a2 |
					a1.mseoccurrence.mse.name.compareTo(a2.mseoccurrence.mse.name)
				]
				val sortedS1Substeps = s1AtomicSteps.sortWith(stepsComparator)
				val sortedS2Substeps = s2AtomicSteps.sortWith(stepsComparator)

				// Compare 2 by 2 the lists of atomic steps
				for (var int i = 0; i < sortedS1Substeps.size; i++) {
					val a1 = sortedS1Substeps.get(i)
					val a2 = sortedS2Substeps.get(i)
					val result = stepsComparator.compare(a1, a2)
					if(result != 0) return result
				}

				return 0
			}

		]))
		return sortedSteps
	}

	def final List<ParallelStep<?, ?>> getPossibleLogicalSteps() {
		synchronized (this) {
			return sortSteps(_possibleLogicalSteps)
		}
	}

	def final protected ParallelStep<?, ?> selectAndExecuteLogicalStep() throws DeciderException {
		setEngineStatus(EngineStatus.RunStatus::WaitingLogicalStepSelection)
		notifyAboutToSelectLogicalStep()
		var ParallelStep<?, ?> selectedLogicalStep = getLogicalStepDecider().decide(this, getPossibleLogicalSteps())
		if (selectedLogicalStep !== null) {
			setSelectedLogicalStep(selectedLogicalStep)
			setEngineStatus(EngineStatus.RunStatus::Running)
			notifyLogicalStepSelected()
			// run all the event occurrences of this logical
			// step
			executeSelectedLogicalStep()
		}
		return selectedLogicalStep
	}

	def protected void performExecutionStep() {
		switchDeciderIfNecessary()
		_possibleLogicalSteps = computePossibleLogicalSteps()
		/* Since we can use Strategy to explore, this is boring if it stops. Moreover we cannot go backward in time anymore...
		 * if (_possibleLogicalSteps.size() === 0) {
			Activator::getDefault().debug("No more LogicalStep to run")
			stop()
		} else {*/
			try {
				var ParallelStep<?, ?> selectedLogicalStep = selectAndExecuteLogicalStep()
				// 3 - run the selected logical step
				// inform the solver that we will run this step
				if (selectedLogicalStep !== null) {
					doAfterLogicalStepExecution(selectedLogicalStep)
					engineStatus.incrementNbLogicalStepRun()
				} else { // no logical step was selected, this is most probably due to a
					// preempt on the LogicalStepDecider and a change of Decider,
					// notify Addons that we'll rerun this ExecutionStep
					// recomputePossibleLogicalSteps();
				}
			} catch (Throwable t) {
				throw new RuntimeException(t)
			}

		//}
	}

	def void addFilteringStrategy(FilteringStrategy strategy) {
		this.filteringStrategies.add(strategy)
	}

	def void addConcurrencyStrategy(ConcurrencyStrategy strategy) {
		this.concurrencyStrategies.add(strategy)
	}

	override protected final void performInitialize(C executionContext) {
		this.changeLogicalStepDecider(executionContext.getLogicalStepDecider())

// FIXME: A variant of this code needs instantiating here, but this requires that we can access the strategy registry from here, which we cannot currently do.
//
//		val config = executionContext.getRunConfiguration() as ConcurrentRunConfiguration
//
//		config.getStrategies.forEach [ extension h |
//			val h = hd.instantiate
//			h.initialise(config.getConfigDetailFor(hd), lcc)
//
//			if (hd.group === StrategyGroup.FILTERING_STRATEGY) {
//				filteringStrategies.add(h as FilteringStrategy)
//			} else {
//				concurrencyStrategies.add(h as ConcurrencyStrategy)
//			}
//		]
		performSpecificInitialize(executionContext)

		Activator::getDefault().debug("*** Engine initialization done. ***")
	}

	def protected final void notifyAboutToSelectLogicalStep() {
		for (IEngineAddon addon : getExecutionContext().getExecutionPlatform().getEngineAddons()) {
			try {
				addon.aboutToSelectStep(this, possibleLogicalSteps.map[s|s as Step<?>])
			} catch (Exception e) {
				Activator::getDefault().error('''Exception in Addon «addon», «e.getMessage()»'''.toString, e)
			}

		}
	}

	def protected final void notifyLogicalStepSelected() {
		for (IEngineAddon addon : getExecutionContext().getExecutionPlatform().getEngineAddons()) {
			try {
				addon.stepSelected(this, getSelectedLogicalStep())
			} catch (Exception e) {
				Activator::getDefault().error('''Exception in Addon «addon», «e.getMessage()»'''.toString, e)
			}

		}
	}

	def protected final void notifyProposedLogicalStepsChanged() {
		for (IEngineAddon addon : getExecutionContext().getExecutionPlatform().getEngineAddons()) {
			try {
				addon.proposedStepsChanged(this, possibleLogicalSteps.map[s|s as Step<?>])
			} catch (Exception e) {
				Activator::getDefault().error('''Exception in Addon «addon», «e.getMessage()»'''.toString, e)
			}

		}
	}
}
