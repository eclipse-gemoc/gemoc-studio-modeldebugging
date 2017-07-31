rule MatchTraces
	match left : Trace
	with right : Trace
	{
		compare : compareTraces(left, right)
	}

operation compareTraces(left : Trace, right : Trace) : Boolean {
	var leftFiringActions : Sequence = left.collectFiringActions();
	var rightFiringActions : Sequence = right.collectFiringActions();
	return leftFiringActions.matches(rightFiringActions);
}

operation Trace collectFiringActions() : Sequence {
	var firingActionsMap : Map = new Map();
	for (tracedAction : TracedOpaqueActionActivation in self.tracedObjects.basicActions_tracedOpaqueActionActivations){
		var action : TracedAction = tracedAction.getAction();
		for (firingTrace : ActionActivation_firing_State in tracedAction.firingTrace) {
			if (firingTrace.firing) {
				var state : GlobalState = firingTrace.globalStates.at(0); 
				var stateIndex : Integer = self.globalTrace.indexOf(state);
				firingActionsMap.put(stateIndex, action);
			}
		}
	}
	var firingActions : Sequence = new Sequence();
	var sortedStateIndexes : Sequence = firingActionsMap.keySet().sortBy(f | f);
	for (index : Integer in sortedStateIndexes) {
		firingActions.add(firingActionsMap.get(index));		
	}	
	return firingActions;
}

operation TracedOpaqueActionActivation getAction() : TracedAction{
	var action : TracedOpaqueAction = null; 
	for (runtimeModelElementState : SemanticVisitor_runtimeModelElement_State in self.runtimeModelElementTrace) {
		var runtimeModelElement : TracedElement = runtimeModelElementState.runtimeModelElement;
		if (runtimeModelElement <> null) {
			action = runtimeModelElement;
		}
	}
	return action;
}

@Lazy
rule MatchActions
	match left : TracedOpaqueAction
	with right : TracedOpaqueAction
	{
		compare : compareActions(left, right) 
	}
	
operation compareActions(left : TracedOpaqueAction, right : TracedOpaqueAction) : Boolean {
	return left.name = right.name;
}

//---------------------------------------------------------------------------------------------
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