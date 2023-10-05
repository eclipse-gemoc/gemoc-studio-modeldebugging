/*******************************************************************************
 * Copyright (c) 2017 Obeo and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.gemoc.addon.eventscheduling.timeline.view;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.eclipse.gemoc.executionframework.reflectivetrace.gemoc_execution_trace.Branch;
import org.eclipse.gemoc.executionframework.reflectivetrace.gemoc_execution_trace.Choice;
import org.eclipse.gemoc.executionframework.reflectivetrace.gemoc_execution_trace.ExecutionTraceModel;
import org.eclipse.gemoc.executionframework.reflectivetrace.gemoc_execution_trace.Gemoc_execution_traceFactory;
import org.eclipse.gemoc.executionframework.ui.utils.ViewUtils;
import org.eclipse.gemoc.timeline.view.AbstractTimelineProvider;
import org.eclipse.gemoc.trace.commons.model.helper.StepHelper;
import org.eclipse.gemoc.trace.commons.model.trace.MSEOccurrence;
import org.eclipse.gemoc.trace.commons.model.trace.Step;
import org.eclipse.gemoc.trace.gemoc.concurrent.AbstractEventSchedulingModelExecutionTracingAddon;
import org.eclipse.gemoc.xdsmlframework.api.core.EngineStatus.RunStatus;
import org.eclipse.gemoc.xdsmlframework.api.core.IDisposable;
import org.eclipse.gemoc.xdsmlframework.api.core.IExecutionEngine;
import org.eclipse.gemoc.xdsmlframework.api.engine_addon.IEngineAddon;

/**
 * This class is registered either as an addon on the launch config or via the view itself as it listen to engine
 * selection changes
 * 
 * @author dvojtise
 *
 */
public class EventSchedulingTimelineProvider extends AbstractTimelineProvider implements IEngineAddon, IDisposable {

	private IExecutionEngine<?> _engine;
	private AbstractEventSchedulingModelExecutionTracingAddon _tracingAddon;

	public EventSchedulingTimelineProvider(IExecutionEngine<?> engine) {
		_engine = engine;
		_engine.getExecutionContext().getExecutionPlatform().addEngineAddon(this);
	}

	private ExecutionTraceModel getExecutionTrace() {
		ExecutionTraceModel traceModel = null;
		Set<AbstractEventSchedulingModelExecutionTracingAddon> candidateAddons = _engine.getAddonsTypedBy(AbstractEventSchedulingModelExecutionTracingAddon.class);
		if (!candidateAddons.isEmpty()) {
			_tracingAddon = candidateAddons.iterator().next();
			traceModel = _tracingAddon.getExecutionTrace();
		} else {
			traceModel = Gemoc_execution_traceFactory.eINSTANCE.createExecutionTraceModel();
		}
		return traceModel;
	}

	private Branch getBranchAt(int branchIndex) {
		Branch result = null;
		if (getExecutionTrace() != null && getExecutionTrace().getBranches().size() >= branchIndex) {
			result = getExecutionTrace().getBranches().get(branchIndex);
		}
		return result;
	}
	
	public Choice getChoiceAt(int branchIndex, int executionStepIndex) {
		Choice result = null;
		Branch branch = getBranchAt(branchIndex);
		if (branch != null && (branch.getStartIndex() + branch.getChoices().size()) >= executionStepIndex) {
			int choiceIndex = executionStepIndex - branch.getStartIndex();
			if (choiceIndex >= 0) {
				result = branch.getChoices().get(choiceIndex);
			}
		}
		return result;
	}

	@Override
	public int getNumberOfBranches() {
		int result = 0;
		if (getExecutionTrace() != null) {
			result = getExecutionTrace().getBranches().size();
		}
		return result;
	}

	@Override
	public int getStart(int branchIndex) {
		int result = 0;
		Branch branch = getBranchAt(branchIndex);
		if (branch != null) {
			result = branch.getStartIndex();
		}
		return result;
	}

	@Override
	public int getEnd(int branchIndex) {
		int result = 0;
		Branch branch = getBranchAt(branchIndex);
		if (branch != null) {
			result = branch.getStartIndex() + branch.getChoices().size();
		}
		return result;
	}

	@Override
	public int getNumberOfPossibleStepsAt(int branchIndex, int executionStepIndex) {
		int numberOfPossibleSteps = 0;
		Choice choice = getChoiceAt(branchIndex, executionStepIndex);
		if (choice != null) {
			numberOfPossibleSteps = choice.getPossibleLogicalSteps().size();
		}
		return numberOfPossibleSteps;
	}

	@Override
	public String getTextAt(int branchIndex) {
		return "Current execution";
	}

	@Override
	public String getTextAt(int branchIndex, int index) {
		return String.valueOf(index);
	}

	@Override
	public Object getAt(int branchIndex, int executionStepIndex, int logicalStepIndex) {
		Object result = null;
		Choice choice = getChoiceAt(branchIndex, executionStepIndex);
		if (choice != null) {
			if (choice.getPossibleLogicalSteps().size() >= logicalStepIndex) {
				result = choice.getPossibleLogicalSteps().get(logicalStepIndex);
			}
		}
		return result;
	}

	@Override
	public Object getAt(int branchIndex, int executionStepIndex) {
		Choice choice = getChoiceAt(branchIndex, executionStepIndex);
		return choice;
	}

	@Override
	public String getTextAt(int branchIndex, int choiceIndex, int logicalStepIndex) {
		StringBuilder builder = new StringBuilder();
		Step<?> ls = (Step<?>) getAt(branchIndex, choiceIndex, logicalStepIndex);
		builder.append(StepHelper.getStepName(ls));
		builder.append(System.getProperty("line.separator"));
		for (MSEOccurrence mseOccurrence : StepHelper.collectAllMSEOccurrences(ls)) {
			appendToolTipTextToBuilder(builder, mseOccurrence);
			builder.append(System.getProperty("line.separator"));
		}
		return builder.toString();
	}

	private void appendToolTipTextToBuilder(StringBuilder builder, MSEOccurrence mseOccurrence) {
		String s = "";
		if (mseOccurrence.getMse() != null)
			s = String.format("%-50s%s", mseOccurrence.getMse().getName(),
					ViewUtils.eventToString(mseOccurrence.getMse()));
		builder.append(s);
	}

	@Override
	public int[][] getFollowings(int branchIndex, int executionStepIndex, int logicalStepIndex) {
		int[][] res = { { branchIndex, -1 } };
		Choice choice = getChoiceAt(branchIndex, executionStepIndex);
		if (choice != null && !choice.getNextChoices().isEmpty()) {
			res = new int[choice.getNextChoices().size()][1];
			for (int i = 0; i < choice.getNextChoices().size(); i++) {
				Choice next = choice.getNextChoices().get(i);
				Branch nextBranch = next.getBranch();
				int nextBranchNumber = getExecutionTrace().getBranches().indexOf(nextBranch);
				if (next.getChosenLogicalStep() != null) {
					int nextLogicalStepindex = next.getPossibleLogicalSteps().indexOf(next.getChosenLogicalStep());
					int content[] = { nextBranchNumber, nextLogicalStepindex };
					res[i] = content;
				} else {
					int content[] = { nextBranchNumber, -1 };
					res[i] = content;
				}
			}
		}
		return res;
	}

	@Override
	public int[][] getPrecedings(int branchIndex, int executionStepIndex, int logicalStepIndex) {
		int[][] res = { { branchIndex, -1 } };
		Choice choice = getChoiceAt(branchIndex, executionStepIndex);
		if (choice != null && choice.getPreviousChoice() != null) {
			Choice previous = choice.getPreviousChoice();
			Branch previousBranch = previous.getBranch();
			int previousBranchNumber = getExecutionTrace().getBranches().indexOf(previousBranch);

			if (previous.getChosenLogicalStep() != null) {
				int previousLogicalStepindex = previous.getPossibleLogicalSteps().indexOf(
						previous.getChosenLogicalStep());
				int content[] = { previousBranchNumber, previousLogicalStepindex };
				res[0] = content;
			} else {
				int content[] = { previousBranchNumber, -1 };
				res[0] = content;
			}
		}
		return res;
	}

	private int _numberOfChoices = 0;
	private int _numberOfSteps = 0;

	private void update(IExecutionEngine<?> engine) {
		if (engine == _engine && getExecutionTrace() != null && _tracingAddon != null
				&& _tracingAddon.getCurrentBranch() != null) {
			Branch branch = _tracingAddon.getCurrentBranch();
			if (branch.getChoices().size() > 0) {
				int branchIndex = getExecutionTrace().getBranches().indexOf(branch);
				boolean mustNotify = false;

				Choice gemocChoice = branch.getChoices().get(branch.getChoices().size() - 1);
				if (gemocChoice.getPossibleLogicalSteps().size() != _numberOfSteps) {
					_numberOfSteps = gemocChoice.getPossibleLogicalSteps().size();
					mustNotify = true;
				}

				if (branch.getChoices().size() != _numberOfChoices) {
					_numberOfChoices = branch.getChoices().size();
					mustNotify = true;
				}

				mustNotify = true;

				if (mustNotify) {
					int stepIndex = branch.getStartIndex() + branch.getChoices().size();
					boolean isSelected = gemocChoice.getChosenLogicalStep() != null;
					notifyIsSelectedChanged(branchIndex, stepIndex,
							gemocChoice.getPossibleLogicalSteps().indexOf(gemocChoice.getChosenLogicalStep()),
							isSelected);
					notifyEndChanged(branchIndex, stepIndex);
					notifyStartChanged(branchIndex, branch.getStartIndex());
				}
			}
		}
	}

	@Override
	public int getSelectedPossibleStep(int branchIndex, int executionStepIndex) {
		int result = -1;
		Branch branch = getBranchAt(branchIndex);
		if (branch != null) {
			int choiceIndex = executionStepIndex - branch.getStartIndex();
			if (branch.getChoices().size() >= choiceIndex) {
				Choice choice = branch.getChoices().get(choiceIndex);
				if (choice.getSelectedNextChoice() != null) {
					result = choice.getPossibleLogicalSteps().indexOf(choice.getChosenLogicalStep());
				}
			}
		}
		return result;
	}

	@Override
	public void dispose() {
		if (_engine != null) {
			_engine.getExecutionContext().getExecutionPlatform().removeEngineAddon(this);
		}
	}

	@Override
	public void engineAboutToStart(IExecutionEngine<?> engine) {

	}

	@Override
	public void engineStarted(IExecutionEngine<?> executionEngine) {
	}

	@Override
	public void aboutToExecuteStep(IExecutionEngine<?> executionEngine, Step<?> logicalStepToApply) {
		update(executionEngine);
	}

//	@Override
//	public void aboutToExecuteMSEOccurrence(IExecutionEngine executionEngine, MSEOccurrence mseOccurrence) {
//	}

	@Override
	public void engineAboutToStop(IExecutionEngine<?> engine) {
		// TODO Auto-generated method stub

	}

	@Override
	public void engineStopped(IExecutionEngine<?> engine) {
		// TODO Auto-generated method stub

	}

	@Override
	public void aboutToSelectStep(IExecutionEngine<?> engine, Collection<Step<?>> logicalSteps) {
		update(engine);
	}

	@Override
	public void stepSelected(IExecutionEngine<?> engine, Step<?> selectedLogicalStep) {
		update(engine);
	}


	@Override
	public void stepExecuted(IExecutionEngine<?> engine, Step<?> step) {
		// update(engine);
	}

	@Override
	public void engineStatusChanged(IExecutionEngine<?> engine, RunStatus newStatus) {
	}

	protected void setSelectedStep(Step<?> ls) {
	}

	@Override
	public void proposedStepsChanged(IExecutionEngine<?> engine, Collection<Step<?>> logicalSteps) {
		update(engine);
	}

	@Override
	public void engineAboutToDispose(IExecutionEngine<?> engine) {
	}

	@Override
	public List<String> validate(List<IEngineAddon> otherAddons) {
		return new ArrayList<String>();
	}

	@Override
	public int getCurrentBranch() {
		Branch currentBranch = _tracingAddon.getCurrentBranch();
		if(currentBranch.getChoices().indexOf(_tracingAddon.getCurrentChoice()) == 0){
			return getExecutionTrace().getBranches().indexOf(_tracingAddon.getCurrentBranch().getChoices().get(0).getPreviousChoice().getBranch());
		}
		return getExecutionTrace().getBranches().indexOf(_tracingAddon.getCurrentBranch());
	}

	@Override
	public int getCurrentChoice() {
		Branch branch = _tracingAddon.getCurrentBranch();
		return branch.getChoices().indexOf(_tracingAddon.getCurrentChoice())+branch.getStartIndex()-1;
	}

	@Override
	public int getCurrentPossibleStep() {
		Choice choice = _tracingAddon.getCurrentChoice();
		Choice previous = choice.getPreviousChoice();
		if(previous != null){			
			return previous.getPossibleLogicalSteps().indexOf(previous.getChosenLogicalStep());
		}
		return -1;
	}




}
