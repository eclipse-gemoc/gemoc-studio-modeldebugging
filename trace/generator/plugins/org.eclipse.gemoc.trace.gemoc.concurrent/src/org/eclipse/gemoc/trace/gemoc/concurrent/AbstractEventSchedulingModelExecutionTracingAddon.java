package org.eclipse.gemoc.trace.gemoc.concurrent;

import java.io.File;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.BasicMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.compare.Comparison;
import org.eclipse.emf.compare.Diff;
import org.eclipse.emf.compare.EMFCompare;
import org.eclipse.emf.compare.merge.BatchMerger;
import org.eclipse.emf.compare.merge.IBatchMerger;
import org.eclipse.emf.compare.merge.IMerger;
import org.eclipse.emf.compare.scope.DefaultComparisonScope;
import org.eclipse.emf.compare.scope.IComparisonScope;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gemoc.executionframework.engine.Activator;
import org.eclipse.gemoc.executionframework.engine.concurrency.AbstractConcurrentExecutionEngine;
import org.eclipse.gemoc.executionframework.engine.concurrency.AbstractConcurrentModelExecutionContext;
import org.eclipse.gemoc.executionframework.engine.core.CommandExecution;
import org.eclipse.gemoc.executionframework.reflectivetrace.gemoc_execution_trace.Branch;
import org.eclipse.gemoc.executionframework.reflectivetrace.gemoc_execution_trace.Choice;
import org.eclipse.gemoc.executionframework.reflectivetrace.gemoc_execution_trace.ContextState;
import org.eclipse.gemoc.executionframework.reflectivetrace.gemoc_execution_trace.ExecutionTraceModel;
import org.eclipse.gemoc.executionframework.reflectivetrace.gemoc_execution_trace.Gemoc_execution_traceFactory;
import org.eclipse.gemoc.executionframework.reflectivetrace.gemoc_execution_trace.ModelState;
import org.eclipse.gemoc.trace.commons.model.generictrace.GenericParallelStep;
import org.eclipse.gemoc.trace.commons.model.trace.ParallelStep;
import org.eclipse.gemoc.trace.commons.model.trace.Step;
import org.eclipse.gemoc.trace.gemoc.api.IMultiDimensionalTraceAddon;
import org.eclipse.gemoc.xdsmlframework.api.core.IExecutionContext;
import org.eclipse.gemoc.xdsmlframework.api.core.IExecutionEngine;
import org.eclipse.gemoc.xdsmlframework.api.engine_addon.IEngineAddon;
import org.eclipse.gemoc.xdsmlframework.api.extensions.engine_addon.EngineAddonSpecificationExtensionPoint;

/**
 * 
 * Class responsible for feeding the trace model and to perform the move
 * backward/forward.
 * 
 * @author ftanguy
 *
 */
public abstract class AbstractEventSchedulingModelExecutionTracingAddon implements IEngineAddon {

	private IExecutionContext<?, ?, ?> _executionContext;
	protected AbstractConcurrentExecutionEngine<?, ?> _executionEngine;
	protected ExecutionTraceModel _executionTraceModel;
	private Choice _lastChoice;
	private Branch _currentBranch;
	protected ModelState currentState = null;
	byte[] _lastRestoredSolverState;
	private EContentAdapter adapter;
	private boolean shouldSave = true;
//	private boolean stateChanged = false;
	private boolean _backToPastHappened = false;
	private boolean _cannotSaveTrace = false;

	protected int stepNumber = 0;

	// if true, the trace doesn't work on a pure Concurrentengine, some features
	// will be disabled
	private boolean _limitedMode = false;

	protected abstract void storeSolverState(ContextState contextState);

	protected abstract void restoreSolverState(Choice choice);

	private void modifyTrace(final Runnable r) {
		RecordingCommand command = new RecordingCommand(getEditingDomain(), "update trace model") {
			@Override
			protected void doExecute() {
				r.run();
			}
		};
		CommandExecution.execute(getEditingDomain(), command);
	}

	public void disableTraceSaving() {
		shouldSave = false;
	}

	private TransactionalEditingDomain getEditingDomain() {
		return TransactionUtil.getEditingDomain(_executionContext.getResourceModel());
	}

	public void branch(Choice choice) throws ModelExecutionTracingException {
		if (_limitedMode) {
			// Cannot Branch in limited mode
		} else {
			internalBranch(choice);
			_backToPastHappened = true;
			_executionEngine.getLogicalStepDecider().preempt();
		}
	}

	private void internalBranch(final Choice choice) {
		final int index = _executionTraceModel.getChoices().indexOf(choice);
		if (index != -1 && index != _executionTraceModel.getChoices().size()) {
			RecordingCommand command = new RecordingCommand(getEditingDomain(), "Back to " + index) {
				@Override
				protected void doExecute() {
					Branch previousBranch = choice.getPreviousChoice().getBranch();
					Branch newBranch = Gemoc_execution_traceFactory.eINSTANCE.createBranch();
					int previousChoiceIndex = previousBranch.getChoices().indexOf(choice.getPreviousChoice());
					int index = previousBranch.getStartIndex() + previousChoiceIndex + 1;
					newBranch.setStartIndex(index);
					_currentBranch = newBranch;
					_executionTraceModel.getBranches().add(newBranch);
					if (_executionTraceModel.getChoices().size() > 0) {
						_lastChoice = choice.getPreviousChoice();
						if (_lastChoice != null) {
							_lastChoice.setSelectedNextChoice(null);
						}
					}
					try {
						restoreModelState(choice);
						restoreSolverState(choice);
						// TODO: here we should notify the addons !
					} catch (Exception e) {
						Activator.getDefault().error("Error while creating branch", e);

					}
				}
			};
			CommandExecution.execute(getEditingDomain(), command);
		}
	}

	private void restoreModelState(Choice choice) {
		ModelState state = choice.getContextState().getModelState();
		restoreModelState(state);
	}

	private FileOutputStream getFileOutputStream(File f) {
		FileOutputStream res = null;
		try {
			res = new FileOutputStream(f);
		} catch (FileNotFoundException e) {
		}
		return res;
	}

	private String tmpRestoreFilePath;
	private File outputRestoreTmp = null;
	private FileOutputStream outputRestoreTmpStream = null;
	private PrintWriter outputRestoreTmpWriter = null;

	@Override
	public void engineAboutToStop(IExecutionEngine<?> engine) {
		tmpRestoreFilePath = System.getProperty("tmpRestoreFileProperty");
		outputRestoreTmp = tmpRestoreFilePath != null && tmpRestoreFilePath.length() > 0 ? new File(tmpRestoreFilePath)
				: null;
		outputRestoreTmpStream = outputRestoreTmp != null ? getFileOutputStream(outputRestoreTmp) : null;
		outputRestoreTmpWriter = outputRestoreTmpStream != null ? new PrintWriter(outputRestoreTmpStream, true) : null;
		if (outputRestoreTmpWriter != null) {
			for (int i = 0; i < _executionTraceModel.getReachedStates().size(); i++) {
				jump(_executionTraceModel.getReachedStates().get(i));
			}
			try {
				outputRestoreTmpStream.close();
				outputAddTmpStream.close();
				outputRestoreTmpWriter.close();
				outputAddTmpWriter.close();
				outputRestoreTmpStream = null;
				outputAddTmpStream = null;
				outputRestoreTmpWriter = null;
				outputAddTmpWriter = null;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void jump(final ModelState state) {
//		if(_limitedMode){
//			// Cannot jump in limited mode
//		}
//		else{
		modifyTrace(new Runnable() {
			@Override
			public void run() {
				long t1 = System.nanoTime();
				restoreModelState(state);
				long t2 = System.nanoTime();
				System.out.println("MEASURED TIME: " + (t2 - t1));
				if (outputRestoreTmpWriter != null) {
					outputRestoreTmpWriter.println(t2 - t1);
				}
			}
		});
//		}
	}

	protected void restoreModelState(ModelState state) {

		EObject left = state.getModel();
		EObject right = _executionContext.getResourceModel().getContents().get(0);

		IComparisonScope scope = new DefaultComparisonScope(left, right, null);
		EMFCompare build = EMFCompare.builder().build();
		Comparison comparison = build.compare(scope);
		List<Diff> differences = comparison.getDifferences();

		IMerger.Registry mergerRegistry = IMerger.RegistryImpl.createStandaloneInstance();
		IBatchMerger merger = new BatchMerger(mergerRegistry);
		merger.copyAllLeftToRight(differences, new BasicMonitor());

	}

	public boolean hasRewindHappened(boolean resetFlag) {
		boolean result = _backToPastHappened;
		if (resetFlag)
			_backToPastHappened = false;
		return result;
	}

	/**
	 * Store the current context State
	 */
	private void storeCurrentContextState() {
		Resource traceResource = _executionTraceModel.eResource();
		if (traceResource.getContents().size() > 0) {

			ExecutionTraceModel traceModel = (ExecutionTraceModel) traceResource.getContents().get(0);
			List<Choice> choices = traceModel.getChoices();
			if (choices.size() > 0) {

				storeModelStateIfChanged();

				ContextState contextState = Gemoc_execution_traceFactory.eINSTANCE.createContextState();
				ModelState modelState = currentState;
				contextState.setModelState(modelState);

				storeSolverState(contextState);

				choices.get(choices.size() - 1).setContextState(contextState);
				contextState.setChoice(choices.get(choices.size() - 1));

			}
		}
	}

	protected void storeModelStateIfChanged() {
		Resource traceResource = _executionTraceModel.eResource();
		if (traceResource.getContents().size() > 0) {
			ExecutionTraceModel traceModel = (ExecutionTraceModel) traceResource.getContents().get(0);
			Activator.getDefault().debug(String.format("[trace-%10s] new model state %3d detected",
					getCurrentEngineShortName(), traceModel.getReachedStates().size()));

			// Recursive copy of the model
			EObject result = EcoreUtil.copy(_executionContext.getResourceModel().getContents().get(0));
			result.eAdapters().clear();

			// Store the copy in the trace
			ModelState modelState = Gemoc_execution_traceFactory.eINSTANCE.createModelState();
			traceModel.getReachedStates().add(modelState);
			modelState.setModel(result);
			currentState = modelState;
			traceResource.getContents().add(result);

		}

	}

	/**
	 * S
	 * 
	 * @param stepNumber
	 */
	private void saveTraceModel() {

		Resource traceResource = _executionTraceModel.eResource();
		if (traceResource.getContents().size() > 0) {
			if (!_cannotSaveTrace && shouldSave) {
				try {
//					String path = System.getProperty("saveTracePath");
//					if (path != null && path.length() > 0) {
//						traceResource.setURI(URI.createFileURI(path));
//					}

					Activator.getDefault()
							.debug(String.format("[trace-%10s] %d states saved to %s", getCurrentEngineShortName(),
									_executionTraceModel.getReachedStates().size(),
									_executionTraceModel.eResource().getURI()));
					traceResource.save(null);
				} catch (IOException e) {
					// chuuuut :-/ TODO: context State seems to miss a container
					Activator.getDefault().error("Error while saving trace to disk", e);
					// _cannotSaveTrace = true;
				}
			}

		}
	}

	public Branch getCurrentBranch() {
		return _currentBranch;
	}

	public Choice getCurrentChoice() {
		return _lastChoice;
	}

	private void setUp(IExecutionEngine<?> engine) {
		if (_executionContext == null) {

			if (!(engine.getExecutionContext() instanceof AbstractConcurrentModelExecutionContext)) {
				// DVK current implementation of this addon is Concurrent specific (due to the
				// use of the CodeExecutor
				// for now fail with an error message, later work may generalize this and remove
				// the dependency (by removing some features ?)
				System.err.println(
						"use of MultibranchTracingAddon with non concurrent engine, will work in a limited mode");
				Activator.getDefault().warn(
						"Use of MultibranchTracingAddon with non concurrent engine. The trace will work in a limited mode");
				_limitedMode = true;
			}
			if (engine instanceof AbstractConcurrentExecutionEngine) {
				_executionEngine = (AbstractConcurrentExecutionEngine<?, ?>) engine;
			} else {
				throw new RuntimeException(
						"The event scheduling tracing addon cannot work with a non-concurrent engine.");
			}
			if (_executionTraceModel == null) {
				_executionTraceModel = Gemoc_execution_traceFactory.eINSTANCE.createExecutionTraceModel();
				_currentBranch = Gemoc_execution_traceFactory.eINSTANCE.createBranch();
				_currentBranch.setStartIndex(0);
				_executionTraceModel.getBranches().add(_currentBranch);
				setModelExecutionContext(engine.getExecutionContext());
			}
			adapter = new EContentAdapter() {

				@Override
				public void notifyChanged(Notification notification) {
					super.notifyChanged(notification);
//					stateChanged = true;

				}

			};

			_executionContext.getResourceModel().eAdapters().add(adapter);

		}
	}

	// private static class GemocTraceResource extends ResourceImpl

	private void setModelExecutionContext(IExecutionContext<?, ?, ?> executionContext) {
		_executionContext = executionContext;
		ResourceSet rs = _executionContext.getResourceModel().getResourceSet();
		URI traceModelURI = URI.createPlatformResourceURI(
				_executionContext.getWorkspace().getExecutionPath().toString() + "/execution.trace", false);
		final Resource modelResource = rs.createResource(traceModelURI);

		RecordingCommand command = new RecordingCommand(getEditingDomain(), "set model execution context") {
			@Override
			protected void doExecute() {
				modelResource.getContents().add(_executionTraceModel);
			}
		};
		CommandExecution.execute(getEditingDomain(), command);
	}

	private Choice createChoice() {
		Choice choice = Gemoc_execution_traceFactory.eINSTANCE.createChoice();
		return choice;
	}

	private String tmpAddFilePath;
	private File outputAddTmp;
	private FileOutputStream outputAddTmpStream;
	private PrintWriter outputAddTmpWriter;

	private void updateTraceModelBeforeDeciding(final Collection<Step<?>> possibleLogicalSteps) {

		RecordingCommand command = new RecordingCommand(getEditingDomain(), "update trace model") {

			@Override
			protected void doExecute() {
				Choice choice = createChoice();
				_executionTraceModel.getChoices().add(choice);
				_currentBranch.getChoices().add(choice);
				if (_lastChoice != null) {
					_lastChoice.getNextChoices().add(choice);
					_lastChoice.setSelectedNextChoice(choice);
				}
				choice.getPossibleLogicalSteps().addAll(possibleLogicalSteps);
				_lastChoice = choice;

				long t1 = System.nanoTime();
				storeCurrentContextState();
				long t2 = System.nanoTime();
				if (outputAddTmpWriter != null) {
					outputAddTmpWriter.println(t2 - t1);
				}
			}
		};
		CommandExecution.execute(getEditingDomain(), command);
	}

	private void updateTraceModelAfterExecution(final Step<?> selectedLogicalStep) {
		RecordingCommand command = new RecordingCommand(getEditingDomain(), "update trace model after deciding") {
			@Override
			protected void doExecute() {
				// in limited mode do nothing, we trace only the method call rather than the
				// method return
				if (!_limitedMode) {
					if (_lastChoice != null) {
						if (_lastChoice.getPossibleLogicalSteps().size() == 0)
							return;
						if (_lastChoice.getPossibleLogicalSteps().contains(selectedLogicalStep.eContainer())) {
							_lastChoice.setChosenLogicalStep((GenericParallelStep) selectedLogicalStep.eContainer());
						} else if (_lastChoice.getPossibleLogicalSteps().contains(selectedLogicalStep)) {
							_lastChoice.setChosenLogicalStep((GenericParallelStep) selectedLogicalStep);
						}
					}
				} else {
					// limited mode
					// this is actually the end of the call, which may be in a stack
					// retrieve the choice in the previous choice that has this logicalStep
					// then mark it as chosen (ie. fully executed)
					Choice choiceFullyExecuted = findPreviousChoiceWithLogicalStep(_lastChoice, selectedLogicalStep);
					if (choiceFullyExecuted != null) {
						choiceFullyExecuted.setChosenLogicalStep(selectedLogicalStep);
					}
				}

			}
		};
		CommandExecution.execute(getEditingDomain(), command);
	}

	private Choice findPreviousChoiceWithLogicalStep(final Choice startingChoice, final Step<?> selectedLogicalStep) {
		if (startingChoice.getPossibleLogicalSteps().contains(selectedLogicalStep)) {
			return startingChoice;
		} else if (startingChoice.getPreviousChoice() != null) {
			return findPreviousChoiceWithLogicalStep(startingChoice.getPreviousChoice(), selectedLogicalStep);
		} else
			return null;
	}

	public ExecutionTraceModel getExecutionTrace() {
		return _executionTraceModel;
	}

	@Override
	public void aboutToSelectStep(IExecutionEngine<?> engine, Collection<Step<?>> logicalSteps) {
		setUp(engine);

		// Inverse the order of the step list, to display the same order as the decider
		// view
		List<Step<?>> stepsAsList = new ArrayList<Step<?>>();
		stepsAsList.addAll(logicalSteps);
		Collections.reverse(stepsAsList);

		updateTraceModelBeforeDeciding(stepsAsList);
	}

	@Override
	public void aboutToExecuteStep(IExecutionEngine<?> executionEngine, Step<?> logicalStepToApply) {
		if (_limitedMode) {
			// in limited mode the engine is not concurrent so it will not call the
			// aboutToSelectLogicalStep method
			// so we do it here
			setUp(executionEngine);
			ArrayList<Step<?>> beforeDecing = new ArrayList<Step<?>>();

			if (logicalStepToApply.getMseoccurrence().getMse().eContainer() == null) {
				System.out.println("MSE CONTAINER NULL");
			}

			beforeDecing.add(logicalStepToApply);
			updateTraceModelBeforeDeciding(beforeDecing);
		}
	}

	@Override
	public void stepExecuted(IExecutionEngine<?> engine, Step<?> logicalStepExecuted) {
		setUp(engine);
		updateTraceModelAfterExecution(logicalStepExecuted);
		RecordingCommand command = new RecordingCommand(getEditingDomain(), "Save trace model") {
			@Override
			protected void doExecute() {
//				_executionTraceModel.getChoices().forEach(c -> c.getPossibleLogicalSteps()
//						.forEach(s -> ((ParallelStep<?,?>)s).getSubSteps().forEach((ss -> ss.getMseoccurrence().setMse(null))))); //TODO: why null ?
				_executionTraceModel.getChoices().forEach(c -> {
					ContextState s = c.getContextState();
					if (s.eContainer() == null) {
						c.setContextState(s);
					}
				});
				saveTraceModel();
			}
		};
		CommandExecution.execute(getEditingDomain(), command);
		;
	}

	@Override
	public void engineAboutToDispose(IExecutionEngine<?> engine) {
	}

	public void reintegrateBranch(final Choice choice) {
		if (_limitedMode) {
			// Cannot reintegrateBranch in limited mode
		} else {
			RecordingCommand command = new RecordingCommand(getEditingDomain(), "Reintegrate branch") {
				@Override
				protected void doExecute() {
					_currentBranch = choice.getBranch();
					_lastChoice = choice.getPreviousChoice();
					choice.setPreviousChoice(null);
					_lastChoice.setSelectedNextChoice(null);
					_currentBranch.getChoices().remove(choice);
					try {
						restoreModelState(choice);
						restoreSolverState(choice);
						_executionEngine.getLogicalStepDecider().preempt();
					} catch (Exception e) {
						Activator.getDefault().error("Error while reintegrating branch", e);
					}
				}
			};
			CommandExecution.execute(getEditingDomain(), command);
		}
	}

//	@Override
//	public void proposedStepsChanged(IExecutionEngine engine, final Collection<Step<?>> logicalSteps) {
//		RecordingCommand command = new RecordingCommand(getEditingDomain(), "update trace model") {
//
//			@Override
//			protected void doExecute() {
//				if (_lastChoice != null) {
//					_lastChoice.getPossibleLogicalSteps().clear();
//					_lastChoice.getPossibleLogicalSteps().addAll(logicalSteps);
//				}
//				//storeCurrentError while saving trace();
//				saveTraceModel();
//			}
//		};
//		CommandExecution.execute(getEditingDomain(), command);
//	}

//	@Override
//	public void mseOccurrenceExecuted(IExecutionEngine engine, MSEOccurrence mseOccurrence) {
//
//		if (stateChanged || currentState == null) {
//
//			modifyTrace(new Runnable() {
//
//				@Override
//				public void run() {
//
//					// We store the states when MSE ends, to be able to
//					// distinguish precisely changes made by a single MSE
//					// Not used by any tool for not
//					// Can be used with "jump" programmatically
//					addModelStateIfChanged();
//				}
//			});
//		}
//
//	}

	@Override
	public void engineStopped(IExecutionEngine<?> engine) {
		modifyTrace(new Runnable() {

			@Override
			public void run() {

				// Same as in "mseOccurrenceExecuted", and probably redundant
				// addModelStateIfChanged();

				// No need to observe changes in the model anymore
				_executionContext.getResourceModel().eAdapters().remove(adapter);

			}
		});
		RecordingCommand command = new RecordingCommand(getEditingDomain(), "Save trace model") {
			@Override
			protected void doExecute() {
				_executionTraceModel.getChoices()
						.forEach(c -> c.getPossibleLogicalSteps().forEach(s -> ((ParallelStep<?, ?>) s).getSubSteps()
								.forEach((ss -> ss.getMseoccurrence().setMse(null)))));
				_executionTraceModel.getChoices().forEach(c -> {
					ContextState s = c.getContextState();
					if (s.eContainer() == null) {
						c.setContextState(s);
					}
				});
				saveTraceModel();
			}
		};
		CommandExecution.execute(getEditingDomain(), command);
	}

	@Override
	public void engineAboutToStart(IExecutionEngine<?> engine) {
		setUp(engine);
		tmpAddFilePath = System.getProperty("tmpAddFileProperty");
		outputAddTmp = tmpAddFilePath != null && tmpAddFilePath.length() > 0 ? new File(tmpAddFilePath) : null;
		outputAddTmpStream = outputAddTmp != null ? getFileOutputStream(outputAddTmp) : null;
		outputAddTmpWriter = outputAddTmpStream != null ? new PrintWriter(outputAddTmpStream, true) : null;
	}

	@Override
	public List<String> validate(List<IEngineAddon> otherAddons) {
		ArrayList<String> errors = new ArrayList<String>();

		boolean found = false;
		String addonName = "";
		for (IEngineAddon iEngineAddon : otherAddons) {
			if (iEngineAddon instanceof IMultiDimensionalTraceAddon) {
				found = true;
				addonName = EngineAddonSpecificationExtensionPoint.getName(iEngineAddon);
				break;
			}
		}

		if (found) {
			String thisName = EngineAddonSpecificationExtensionPoint.getName(this);
			errors.add(thisName + " can't run with " + addonName);
		}

		return errors;
	}

	public String getCurrentEngineShortName() {
		return _executionContext.getRunConfiguration().getExecutedModelURI().lastSegment();
	}
}
