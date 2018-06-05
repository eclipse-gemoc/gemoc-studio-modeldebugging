/*******************************************************************************
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.gemoc.dsl.debug.ide.sirius.ui.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.core.resources.IMarkerDelta;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.IBreakpointListener;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.ExceptionHandler;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.impl.AbstractTransactionalCommandStack;
import org.eclipse.gemoc.dsl.debug.StackFrame;
import org.eclipse.gemoc.dsl.debug.ide.DSLBreakpoint;
import org.eclipse.gemoc.dsl.debug.ide.adapter.IDSLCurrentInstructionListener;
import org.eclipse.gemoc.dsl.debug.ide.sirius.ui.DebugSiriusIdeUiPlugin;
import org.eclipse.gemoc.trace.commons.model.trace.MSEOccurrence;
import org.eclipse.gemoc.trace.commons.model.trace.ParallelStep;
import org.eclipse.gemoc.trace.commons.model.trace.Step;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.dialect.command.RefreshRepresentationsCommand;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.description.Layer;
import org.eclipse.sirius.ui.business.api.dialect.DialectEditor;
import org.eclipse.sirius.ui.business.api.session.IEditingSession;
import org.eclipse.sirius.ui.business.api.session.SessionUIManager;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;

/**
 * DSL debugger services class.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public abstract class AbstractDSLDebuggerServices {

	/**
	 * A couple of {@link String}.
	 * 
	 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
	 */
	public static final class StringCouple {

		/**
		 * The first string of the couple.
		 */
		private final String first;

		/**
		 * The second string of the couple.
		 */
		private final String second;

		/**
		 * Constructor.
		 * 
		 * @param first
		 *            the first string of the couple
		 * @param second
		 *            the second string of the couple
		 */
		public StringCouple(String first, String second) {
			this.first = first;
			this.second = second;
		}

		/**
		 * Gets the first string of the couple.
		 * 
		 * @return the first string of the couple
		 */
		public String getFirst() {
			return first;
		}

		/**
		 * Gets the second string of the couple.
		 * 
		 * @return the second string of the couple
		 */
		public String getSecond() {
			return second;
		}
	}

	/**
	 * An {@link IBreakpointListener} maintaining the breakpoints.
	 * 
	 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
	 */
	public static final class BreakpointListener implements IBreakpointListener, IDSLCurrentInstructionListener {

		/**
		 * Any layer {@link Set}, means always refresh the given {@link DRepresentation} no matter what its
		 * layer are. It should be used for trees and tables since they don't have layers.
		 */
		private static final Set<String> ANY_LAYER = new HashSet<String>();

		/**
		 * Mapping of the {@link RepresentationDescription#getName() representation identifier} to a
		 * {@link Layer#getName() layer identifier} or {@link BreakpointListener#ANY_LAYER any layer}.
		 */
		private final Map<String, Map<String, Set<String>>> representationToRefresh = new HashMap<String, Map<String, Set<String>>>();

		/**
		 * The current {@link StackFrame}.
		 */
		private StackFrame currentFrame;

		/**
		 * Constructs and installs a default BreakpointListener.
		 */
		public BreakpointListener() {
			install();
		}

		/**
		 * Installs this {@link IBreakpointListener}.
		 */
		public void install() {
			DebugPlugin.getDefault().getBreakpointManager().addBreakpointListener(this);
			for (IBreakpoint breakpoint : DebugPlugin.getDefault().getBreakpointManager().getBreakpoints()) {
				if (breakpoint instanceof DSLBreakpoint) {
					addBreakpoint((DSLBreakpoint)breakpoint);
				}
			}
		}

		/**
		 * Uninstalls this {@link IBreakpointListener}.
		 */
		public void uninstall() {
			DebugPlugin.getDefault().getBreakpointManager().removeBreakpointListener(this);
		}

		/**
		 * {@inheritDoc}
		 * 
		 * @see org.eclipse.debug.core.IBreakpointListener#breakpointAdded(org.eclipse.debug.core.model.IBreakpoint)
		 */
		public void breakpointAdded(IBreakpoint breakpoint) {
			if (breakpoint instanceof DSLBreakpoint) {
				addBreakpoint((DSLBreakpoint)breakpoint);
				final DSLBreakpoint dslBreakpoint = (DSLBreakpoint)breakpoint;
				final Set<URI> instructionURIs = new HashSet<URI>();
				instructionURIs.add(dslBreakpoint.getURI());
				notifySirius(instructionURIs, dslBreakpoint.getModelIdentifier());
			}
		}

		/**
		 * {@inheritDoc}
		 * 
		 * @see org.eclipse.debug.core.IBreakpointListener#breakpointRemoved(org.eclipse.debug.core.model.IBreakpoint,
		 *      org.eclipse.core.resources.IMarkerDelta)
		 */
		public void breakpointRemoved(IBreakpoint breakpoint, IMarkerDelta delta) {
			if (breakpoint instanceof DSLBreakpoint) {
				removeBreakpoint((DSLBreakpoint)breakpoint);
				final DSLBreakpoint dslBreakpoint = (DSLBreakpoint)breakpoint;
				final Set<URI> instructionURIs = new HashSet<URI>();
				instructionURIs.add(dslBreakpoint.getURI());
				notifySirius(instructionURIs, dslBreakpoint.getModelIdentifier());
			}
		}

		/**
		 * {@inheritDoc}
		 * 
		 * @see org.eclipse.debug.core.IBreakpointListener#breakpointChanged(org.eclipse.debug.core.model.IBreakpoint,
		 *      org.eclipse.core.resources.IMarkerDelta)
		 */
		public void breakpointChanged(IBreakpoint breakpoint, IMarkerDelta delta) {
			try {
				if (breakpoint instanceof DSLBreakpoint && delta.getAttribute(IBreakpoint.ENABLED) != null
						&& breakpoint.isEnabled() != ((Boolean)delta.getAttribute(IBreakpoint.ENABLED))
								.booleanValue()) {
					final DSLBreakpoint dslBreakpoint = (DSLBreakpoint)breakpoint;
					final Set<URI> instructionURIs = new HashSet<URI>();
					instructionURIs.add(dslBreakpoint.getURI());
					notifySirius(instructionURIs, dslBreakpoint.getModelIdentifier());
				}
			} catch (CoreException e) {
				// ignore
			}
		}

		/**
		 * Adds the given {@link DSLBreakpoint}.
		 * 
		 * @param breakpoint
		 *            the {@link DSLBreakpoint}
		 */
		protected void addBreakpoint(DSLBreakpoint breakpoint) {
			Set<DSLBreakpoint> brkps = BREAKPOINTS.get(breakpoint.getURI());
			if (brkps == null) {
				brkps = new HashSet<DSLBreakpoint>();
				BREAKPOINTS.put(breakpoint.getURI(), brkps);
			}
			brkps.add(breakpoint);
		}

		/**
		 * Removes the given {@link DSLBreakpoint}.
		 * 
		 * @param breakpoint
		 *            the {@link DSLBreakpoint}
		 */
		protected void removeBreakpoint(DSLBreakpoint breakpoint) {
			Set<DSLBreakpoint> brkps = BREAKPOINTS.get(breakpoint.getURI());
			if (brkps != null) {
				brkps.remove(breakpoint);
			}
		}

		/**
		 * Notifies Sirius about a change in the given {@link DSLBreakpoint}.
		 * 
		 * @param instructionUris
		 *            the {@link URI}s of the instructions to refresh.
		 * @param debugModelID
		 *            the debug model identifier
		 */
		public void notifySirius(Set<URI> instructionUris, String debugModelID) {
			Map<String, Set<String>> toRefresh = representationToRefresh.get(debugModelID);
			if (toRefresh != null) {
				for (IEditingSession session : SessionUIManager.INSTANCE.getUISessions()) {
					final TransactionalEditingDomain transactionalEditingDomain = session.getSession()
							.getTransactionalEditingDomain();
					final boolean instructionPresent = isOneInstructionPresent(instructionUris,
							transactionalEditingDomain.getResourceSet());
					if (instructionPresent) {
						final List<DRepresentation> representations = getRepresentationsToRefresh(toRefresh,
								session);
						refreshRepresentations(transactionalEditingDomain, representations);
					}
				}
			}
		}

		/**
		 * Refreshes given {@link DRepresentation} in the given {@link TransactionalEditingDomain}.
		 * 
		 * @param transactionalEditingDomain
		 *            the {@link TransactionalEditingDomain}
		 * @param representations
		 *            the {@link List} of {@link DRepresentation} to refresh
		 */
		public void refreshRepresentations(final TransactionalEditingDomain transactionalEditingDomain,
				final List<DRepresentation> representations) {
			// TODO prevent the editors from getting dirty
			if (representations.size() != 0) {
				final RefreshRepresentationsCommand refresh = new RefreshRepresentationsCommand(
						transactionalEditingDomain, new NullProgressMonitor(), representations);

				CommandStack commandStack = transactionalEditingDomain.getCommandStack();

				// If the command stack is transactionnal, we add a one-shot exception handler.
				if (commandStack instanceof AbstractTransactionalCommandStack) {
					AbstractTransactionalCommandStack transactionnalCommandStack = (AbstractTransactionalCommandStack)commandStack;
					transactionnalCommandStack.setExceptionHandler(new ExceptionHandler() {

						@Override
						public void handleException(Exception e) {
							// TODO Auto-generated method stub

							String repString = representations.stream().map(r -> r.getName()).collect(
									Collectors.joining(", "));
							DebugSiriusIdeUiPlugin.getPlugin().getLog().log(new Status(IStatus.WARNING,
									DebugSiriusIdeUiPlugin.ID, "Failed to refresh Sirius representation(s)["
											+ repString + "], we hope to be able to do it later", e));

							// Self-remove from the command stack.
							transactionnalCommandStack.setExceptionHandler(null);

						}
					});
				}

				commandStack.execute(refresh);

			}
		}

		/**
		 * Gets the {@link List} of {@link DRepresentation} to refresh in the given {@link IEditingSession}.
		 * 
		 * @param toRefresh
		 *            the representation names and layers to refresh
		 * @param session
		 *            the {@link IEditingSession}
		 * @return the {@link List} of {@link DRepresentation} to refresh in the given {@link IEditingSession}
		 */
		private List<DRepresentation> getRepresentationsToRefresh(Map<String, Set<String>> toRefresh,
				IEditingSession session) {
			final List<DRepresentation> representations = new ArrayList<DRepresentation>();
			for (DialectEditor editor : session.getEditors()) {
				final DRepresentation representation = editor.getRepresentation();
				if (representation == null) {
					System.out.println("Dammit");
				} else {
					final RepresentationDescription description = DialectManager.INSTANCE.getDescription(
							representation);
					if (description != null) {
						final String representationId = description.getName();
						final Set<String> layerIDs = toRefresh.get(representationId);
						if (layerIDs == ANY_LAYER) {
							representations.add(representation);
						} else if (layerIDs != null && representation instanceof DDiagram && isActiveLayer(
								(DDiagram)representation, layerIDs)) {
							representations.add(representation);
						}
					}
				}
			}
			return representations;
		}

		/**
		 * Tells if at least one of the given instruction {@link URI} is present in the given
		 * {@link ResourceSet}.
		 * 
		 * @param instructionUris
		 *            the {@link Set} of instructions {@link URI}
		 * @param resourceSet
		 *            the {@link ResourceSet}
		 * @return <code>true</code> if at least one of the given instruction {@link URI} is present in the
		 *         given {@link ResourceSet}, <code>false</code> otherwise
		 */
		private static boolean isOneInstructionPresent(Set<URI> instructionUris,
				final ResourceSet resourceSet) {
			boolean instructionPresent = false;
			for (URI instructionUri : instructionUris) {
				if (resourceSet.getEObject(instructionUri, false) != null) {
					instructionPresent = true;
					break;
				}
			}
			return instructionPresent;
		}

		/**
		 * Tells if any of the given {@link Layer#getName() layer identifier} is active for the given
		 * {@link DDiagram}.
		 * 
		 * @param diagram
		 *            the {@link DDiagram}
		 * @param layerIDs
		 *            the {@link Set} of {@link Layer#getName() layer identifiers}
		 * @return <code>true</code> if any of the given {@link Layer#getName() layer identifier} is active
		 *         for the given {@link DDiagram}, <code>false</code> otherwise
		 */
		private boolean isActiveLayer(DDiagram diagram, Set<String> layerIDs) {
			boolean res = false;

			for (Layer layer : diagram.getActivatedLayers()) {
				if (layerIDs.contains(layer.getName())) {
					res = true;
					break;
				}
			}

			return res;
		}

		/**
		 * Add the given {@link RepresentationDescription#getName() representation identifier} for
		 * {@link DRepresentation} refresh.
		 * 
		 * @param debugModelID
		 *            the debug model identifier
		 * @param representationID
		 *            the {@link RepresentationDescription#getName() representation identifier}
		 */
		public void addRepresentationToRefresh(String debugModelID, String representationID) {
			Map<String, Set<String>> toRefresh = representationToRefresh.get(debugModelID);
			if (toRefresh == null) {
				toRefresh = new HashMap<String, Set<String>>();
				representationToRefresh.put(debugModelID, toRefresh);
			}
			toRefresh.put(representationID, ANY_LAYER);
		}

		/**
		 * Add the given {@link RepresentationDescription#getName() representation identifier} and
		 * {@link Layer#getName() layer identifier} for {@link DRepresentation} refresh.
		 * 
		 * @param debugModelID
		 *            the debug model identifier
		 * @param representationID
		 *            the {@link RepresentationDescription#getName() representation identifier}
		 * @param layerID
		 *            the {@link Layer#getName() layer identifier}
		 */
		public void addRepresentationToRefresh(String debugModelID, String representationID, String layerID) {
			Map<String, Set<String>> toRefresh = representationToRefresh.get(debugModelID);
			if (toRefresh == null) {
				toRefresh = new HashMap<String, Set<String>>();
				representationToRefresh.put(debugModelID, toRefresh);
			}
			Set<String> layerIDs = toRefresh.get(representationID);
			if (layerIDs != ANY_LAYER) {
				if (layerIDs == null) {
					layerIDs = new HashSet<String>();
					toRefresh.put(representationID, layerIDs);
				}
				layerIDs.add(layerID);
			}
		}

		/**
		 * {@inheritDoc}
		 * 
		 * @see org.eclipse.gemoc.dsl.debug.ide.adapter.IDSLCurrentInstructionListener#currentInstructionChanged(String,
		 *      org.eclipse.gemoc.dsl.debug.StackFrame))
		 */
		public void currentInstructionChanged(String debugModelID, StackFrame frame) {
			EObject currentInstruction = frame.getCurrentInstruction();
			final Set<URI> instructionURIs = new HashSet<URI>();

			if (currentInstruction instanceof ParallelStep) {
				addMseOccurenceAndCallerToInstructionsURIs(instructionURIs,
						((ParallelStep<?, ?>)currentInstruction).getMseoccurrence());
				for (Step<?> step : ((ParallelStep<?, ?>)currentInstruction).getSubSteps()) {
					addMseOccurenceAndCallerToInstructionsURIs(instructionURIs, step.getMseoccurrence());
				}
			} else if (currentInstruction instanceof Step) {
				if (!(currentInstruction.eContainer() instanceof ParallelStep)) {
					// do not show internal step of parallel step, because they are already shown as a
					// parallel
					addMseOccurenceAndCallerToInstructionsURIs(instructionURIs, ((Step<?>)currentInstruction)
							.getMseoccurrence());
				}
			} else {
				instructionURIs.add(EcoreUtil.getURI(currentInstruction));
			}
			final Set<URI> lastInstructions = CURRENT_INSTRUCTIONS_PER_FRAME.remove(frame);
			if (lastInstructions != null) {
				notifySirius(lastInstructions, debugModelID);
			}
			CURRENT_INSTRUCTIONS_PER_FRAME.put(frame, instructionURIs);
			notifySirius(instructionURIs, debugModelID);
		}

		/**
		 * If possible, adds the MSE of an MSEOccurrence and its caller to instructions URIs.
		 * 
		 * @param instructionURIs
		 *            The collection of instructions URIS in which to add the MSE and caller.
		 * @param mseOccurrence
		 *            The MSEOccurrence from which the MSE must be considered.
		 */
		private void addMseOccurenceAndCallerToInstructionsURIs(Set<URI> instructionURIs,
				MSEOccurrence mseOccurrence) {
			if (mseOccurrence != null) {
				instructionURIs.add(EcoreUtil.getURI(mseOccurrence.getMse()));
				if (mseOccurrence.getMse().getCaller() != null) {
					instructionURIs.add(EcoreUtil.getURI(mseOccurrence.getMse().getCaller()));
				}
			}
		}

		/**
		 * {@inheritDoc}
		 * 
		 * @see org.eclipse.gemoc.dsl.debug.ide.adapter.IDSLCurrentInstructionListener#terminated(java.lang.String,
		 *      org.eclipse.gemoc.dsl.debug.StackFrame)
		 */
		public void terminated(String debugModelID, StackFrame frame) {
			final Set<URI> lastInstructions = CURRENT_INSTRUCTIONS_PER_FRAME.remove(frame);
			if (lastInstructions != null) {
				notifySirius(lastInstructions, debugModelID);
			}
		}

		/**
		 * Gets the current {@link StackFrame}.
		 * 
		 * @return the current {@link StackFrame}
		 */
		public StackFrame getCurrentFrame() {
			return currentFrame;
		}

		/**
		 * {@inheritDoc}
		 * 
		 * @see org.eclipse.gemoc.dsl.debug.ide.adapter.IDSLCurrentInstructionListener#setCurrentFrame(java.lang.String,
		 *      org.eclipse.gemoc.dsl.debug.StackFrame)
		 */
		public void setCurrentFrame(String debugModelID, StackFrame frame) {
			if (currentFrame != frame) {
				currentFrame = frame;
				Set<URI> instructionUris = CURRENT_INSTRUCTIONS_PER_FRAME.get(getCurrentFrame());
				if (instructionUris != null) {
					notifySirius(instructionUris, debugModelID);
				}
			}
		}

		/**
		 * Tells if the given layer id and representation id should be refreshed while debugging the given
		 * debug model id.
		 * 
		 * @param debugModelID
		 *            the debug model id
		 * @param representationId
		 *            the representation id
		 * @param layerID
		 *            the layer id, it can be <code>null</code>
		 * @return <code>true</code> if the given layer id and representation id should be refreshed while
		 *         debugging the given debug model id, <code>false</code> otherwise
		 */
		public boolean isRepresentationToRefresh(String debugModelID, String representationId,
				String layerID) {
			final boolean res;

			final Map<String, Set<String>> representations = representationToRefresh.get(debugModelID);
			if (representations != null) {
				final Set<String> layerIDs = representations.get(representationId);
				res = layerIDs == ANY_LAYER || (layerIDs != null && layerIDs.contains(layerID));
			} else {
				res = false;
			}

			return res;
		}

	}

	/**
	 * The {@link IBreakpointListener} maintaining breakpoints.
	 */
	public static final BreakpointListener LISTENER = new BreakpointListener();

	/**
	 * {@link Map} of {@link URI} pointing {@link DSLBreakpoint}.
	 */
	private static final Map<URI, Set<DSLBreakpoint>> BREAKPOINTS = new HashMap<URI, Set<DSLBreakpoint>>();

	/**
	 * Current instruction for a given {@link StackFrame}. Note : in concurrent mode, a ParallelStep may be
	 * represented by several URI (so we use a set)
	 */
	private static final Map<StackFrame, Set<URI>> CURRENT_INSTRUCTIONS_PER_FRAME = new HashMap<StackFrame, Set<URI>>();

	/**
	 * Constructor.
	 */
	public AbstractDSLDebuggerServices() {
		for (StringCouple couple : getRepresentationRefreshList()) {
			if (couple.getSecond() != null) {
				LISTENER.addRepresentationToRefresh(getModelIdentifier(), couple.getFirst(), couple
						.getSecond());
			} else {
				LISTENER.addRepresentationToRefresh(getModelIdentifier(), couple.getFirst());
			}
		}
	}

	/**
	 * Gets the {@link List} of {@link StringCouple} representing the
	 * {@link RepresentationDescription#getName() representation identifier} and the {@link Layer#getName()
	 * layer identifier} or <code>null</code> where services from this class are used.
	 * 
	 * @return the {@link List} of {@link StringCouple} representing the
	 *         {@link RepresentationDescription#getName() representation identifier} and the
	 *         {@link Layer#getName() layer identifier} or <code>null</code> where services from this class
	 *         are used
	 */
	protected abstract List<StringCouple> getRepresentationRefreshList();

	/**
	 * Tells if the given {@link EObject instruction} has a breakpoint.
	 * 
	 * @param instruction
	 *            the {@link EObject instruction}
	 * @return <code>true</code> if the given {@link EObject instruction} has a breakpoint, <code>false</code>
	 *         otherwise
	 */
	public boolean hasBreakpoint(EObject instruction) {
		final Set<DSLBreakpoint> brkps = getBreakpoints(instruction);
		return brkps != null && brkps.size() != 0;
	}

	/**
	 * Tells if the given {@link EObject instruction} has an enabled breakpoint.
	 * 
	 * @param instruction
	 *            the {@link EObject instruction}
	 * @return <code>true</code> if the given {@link EObject instruction} has an enabled breakpoint,
	 *         <code>false</code> otherwise
	 */
	public boolean hasEnabledBreakpoint(EObject instruction) {
		boolean res = false;
		final Set<DSLBreakpoint> brkps = getBreakpoints(instruction);

		if (brkps != null && brkps.size() != 0) {
			for (DSLBreakpoint breakpoint : brkps) {
				try {
					if (breakpoint.isEnabled()) {
						res = true;
						break;
					}
				} catch (CoreException e) {
					// ignore
				}
			}
		}

		return res;
	}

	/**
	 * Tells if the given {@link EObject instruction} has an disabled breakpoint.
	 * 
	 * @param instruction
	 *            the {@link EObject instruction}
	 * @return <code>true</code> if the given {@link EObject instruction} has an disabled breakpoint,
	 *         <code>false</code> otherwise
	 */
	public boolean hasDisabledBreakpoint(EObject instruction) {
		boolean res = false;
		final Set<DSLBreakpoint> brkps = getBreakpoints(instruction);

		if (brkps != null && brkps.size() != 0) {
			res = true;
			for (DSLBreakpoint breakpoint : brkps) {
				try {
					if (breakpoint.isEnabled()) {
						res = false;
						break;
					}
				} catch (CoreException e) {
					// ignore
				}
			}
		}

		return res;
	}

	/**
	 * Gets the {@link Set} of {@link DSLBreakpoint} for the
	 * {@link AbstractDSLDebuggerServices#getModelIdentifier() model identifier}.
	 * 
	 * @param instruction
	 *            the instruction to check
	 * @return the {@link Set} of {@link DSLBreakpoint} for the
	 *         {@link AbstractDSLDebuggerServices#getModelIdentifier() model identifier}
	 */
	protected Set<DSLBreakpoint> getBreakpoints(EObject instruction) {
		Set<DSLBreakpoint> res = new HashSet<DSLBreakpoint>();

		Set<DSLBreakpoint> brkps = BREAKPOINTS.get(EcoreUtil.getURI(instruction));
		if (brkps != null) {
			for (DSLBreakpoint breakpoint : brkps) {
				if (breakpoint.getModelIdentifier().equals(getModelIdentifier())) {
					res.add(breakpoint);
				}
			}
		}

		return res;
	}

	/**
	 * Tells if the given {@link EObject instruction} is a currently debugged instruction. A debugged
	 * instruction in this context is an instruction a debug target is suspended on. This service works in a
	 * similar way as {@link AbstractGemocAnimatorServices hasBeenActivated} but will be activated only when
	 * the engine is paused. In addition, its content is related to the selected stack frame.
	 * 
	 * @param instruction
	 *            the {@link EObject instruction}
	 * @return <code>true</code> if the given {@link EObject instruction} is a currently debugged instruction,
	 *         <code>false</code> otherwise
	 */
	public boolean isCurrentInstruction(EObject instruction) {
		final Set<URI> instructions = CURRENT_INSTRUCTIONS_PER_FRAME.get(LISTENER.getCurrentFrame());
		return instructions != null && instructions.contains(EcoreUtil.getURI(instruction));
	}

	/**
	 * Gets the debug model identifier.
	 * 
	 * @return the debug model identifier
	 */
	public abstract String getModelIdentifier();

}
