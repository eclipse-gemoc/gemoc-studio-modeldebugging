rule MatchStateSystems
	match left : StateSystem
	with right : StateSystem
	{
		compare : compareTraces(left, right)
	}

operation compareTraces(left : StateSystem, right : StateSystem) : Boolean {
	var leftFiringActionStates : OrderedSet = left.getStatesWithFiringAction();
	var rightFiringActionStates : OrderedSet = right.getStatesWithFiringAction();
	return leftFiringActionStates.matches(rightFiringActionStates);
}

operation StateSystem getStatesWithFiringAction() : OrderedSet {
	var statesWithFiringAction : OrderedSet = new OrderedSet();
	var i : Integer = 0;
	while (i < self.states.size() - 1) {
		var state : states::State = self.states.at(i);
		if (state.hasFiringAction()) {
			if (statesWithFiringAction.size() == 0) {
				statesWithFiringAction.add(state);
			} else {
				var previousState : states::State = self.states.at(i-1);
				if (not compareStates(state, previousState)) {				
					statesWithFiringAction.add(state);
				}
			}
		}
		i = i + 1;
	}
	return statesWithFiringAction;
}

operation states::State hasFiringAction() : Boolean {
	var firingActions : OrderedSet = self.getFiringActions();
	return firingActions.size() > 0;
}

operation states::State getFiringActions() : OrderedSet {
	var firingActionActivations : OrderedSet = new OrderedSet();
	var activityExecution : fumlConfiguration::Activities::IntermediateActivities::ActivityExecution = self.getActivityExecution();
	if (activityExecution <> null) {
		firingActionActivations = activityExecution.getFiringActions();
	} 
	return firingActionActivations;
}

operation fumlConfiguration::Activities::IntermediateActivities::ActivityExecution getFiringActions() : OrderedSet {
	var firingActions : OrderedSet = new OrderedSet();
	if (self.activationGroup <> null) {
		for(nodeActivation : ActivityNodeActivation in self.activationGroup.nodeActivations) {
			if (nodeActivation.isKindOf(OpaqueActionActivation)) {
				var actionActivation : ActionActivation = nodeActivation;
				if (actionActivation.firing) {			
					firingActions.add(actionActivation);
				}
			}
		}
	}
	return firingActions;
}

operation states::State getActivityExecution() : fumlConfiguration::Activities::IntermediateActivities::ActivityExecution {
	var activityExecution : fumlConfiguration::Activities::IntermediateActivities::ActivityExecution = null;
	var executionEnvironment : fumlConfiguration::Loci::ExecutionEnvironment = self.getExecutionEnvironment();
	var locus : Locus = executionEnvironment.locus_ExecutionEnvironment;
	activityExecution = locus.getActivityExecution();
	if (activityExecution <> null and activityExecution.runtimeModelElement = null) {
		locus = self.getLocus();
		activityExecution = locus.getActivityExecution();
	}
	return activityExecution;
}

operation states::State getExecutionEnvironment() : fumlConfiguration::Loci::ExecutionEnvironment {
	var executionEnvironment : fumlConfiguration::Loci::ExecutionEnvironment = null;
	for (object : Any in self.objects) {
		if (object.isKindOf(fumlConfiguration::Loci::ExecutionEnvironment)) {
			executionEnvironment = object;
		}
	}
	return executionEnvironment;
}

operation states::State getLocus() : Locus {
	var locus : Locus = null;
	for (object : Any in self.objects) {
		if (object.isKindOf(Locus)) {
			locus = object;
		}
	}
	return locus;
}

operation Locus getActivityExecution() : fumlConfiguration::Activities::IntermediateActivities::ActivityExecution {
	for (extensionalValue : ExtensionalValue in self.extensionalValues) {
		if (extensionalValue.isKindOf(fumlConfiguration::Activities::IntermediateActivities::ActivityExecution)) {
			return extensionalValue;
		}
	}	
	return null;
}

@Lazy
rule MatchStates
	match left : states::State
	with right : states::State
	{
		compare : compareStates(left, right)
	}
	
operation compareStates(leftState : states::State, rightState : states::State) : Boolean {
	var leftFiringActions : OrderedSet = leftState.getFiringActions();
	var rightFiringActions : OrderedSet = rightState.getFiringActions();
	return compareActionActivations(leftFiringActions, rightFiringActions);
}

operation compareActionActivations(actionActivationList1 : OrderedSet, actionActivationList2 : OrderedSet) : Boolean {
	var actionActivationSet1 : Set = actionActivationList1.asSet();
	var actionActivationSet2 : Set = actionActivationList2.asSet();
	return actionActivationSet1.matches(actionActivationSet2);
}

@Lazy
rule MatchOpaqueActionActivations
	match left : OpaqueActionActivation
	with right : OpaqueActionActivation
	{
		compare : compareActions(left, right)
	}
	
operation compareActions(left : OpaqueActionActivation, right : OpaqueActionActivation) : Boolean {
	var leftOpaqueAction : OpaqueAction = left.runtimeModelElement;
	var rightOpaqueAction : OpaqueAction = right.runtimeModelElement;				
	return (leftOpaqueAction.name = rightOpaqueAction.name) and (left.firing = right.firing);
}

//-----------------------------------------------------------------------------------------------------
operation Set matches(targetSet : Set) : Boolean {
	var matches : Boolean = true;
	for (source : Any in self) {
		var sourceTargetMatchFound : Boolean = false;
		for (target : Any in targetSet) {
			if (source.matches(target)) 
				sourceTargetMatchFound = true;
		}
		if (not sourceTargetMatchFound) {
			matches = false;
		}
	}
	return matches;
}

operation OrderedSet matches(targetSet : OrderedSet) : Boolean {
	if (self.size() <> targetSet.size()) {
		return false;
	}	
	var matches : Boolean = true;
	var i : Integer = 0;
	while (i < self.size()) {
		var source = self.at(i);
		var target = targetSet.at(i);
		if (not source.matches(target)) {
			matches = false;
		}
		i = i + 1;
	}
	return matches;
}