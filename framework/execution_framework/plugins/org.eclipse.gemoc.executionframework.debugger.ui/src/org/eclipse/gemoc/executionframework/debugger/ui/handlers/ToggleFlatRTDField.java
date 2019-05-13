package org.eclipse.gemoc.executionframework.debugger.ui.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.State;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.handlers.RegistryToggleState;


/**
 * https://wiki.eclipse.org/Menu_Contributions/Toggle_Button_Command
 *
 */
public class ToggleFlatRTDField extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		Command command = event.getCommand();
	    /*boolean oldValue =*/ HandlerUtil.toggleCommandState(command);
	     // use the old value and perform the operation
	 
	    applyCurrentState();
	    return null; 
 
	}
	
	public static void applyCurrentState() throws ExecutionException {
		ICommandService commandService = (ICommandService) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getService(ICommandService.class);
		Command command = commandService.getCommand("org.eclipse.gemoc.executionframework.debugger.ui.toggleFlatRTDField");
		State state = command.getState(RegistryToggleState.STATE_ID);
		if(state == null)
			throw new ExecutionException("The command does not have a toggle state"); //$NON-NLS-1$
		 if(!(state.getValue() instanceof Boolean))
			throw new ExecutionException("The command's toggle state doesn't contain a boolean value"); //$NON-NLS-1$

		org.eclipse.gemoc.executionframework.debugger.Activator.getDefault().setUseNestedDebugVariables(((Boolean) state.getValue()).booleanValue());
	}



}
