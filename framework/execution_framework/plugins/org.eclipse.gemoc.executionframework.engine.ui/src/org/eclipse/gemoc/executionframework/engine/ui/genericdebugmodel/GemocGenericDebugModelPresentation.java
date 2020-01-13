/*******************************************************************************
 * Copyright (c) 2016, 2020 Inria and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Inria - initial API and implementation
 *******************************************************************************/
package org.eclipse.gemoc.executionframework.engine.ui.genericdebugmodel;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.debug.core.model.IStackFrame;
import org.eclipse.debug.internal.ui.DebugUIPlugin;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.gemoc.commons.eclipse.emf.EObjectUtil;
import org.eclipse.gemoc.dsl.debug.DebugTarget;
import org.eclipse.gemoc.dsl.debug.StackFrame;
import org.eclipse.gemoc.dsl.debug.Variable;
import org.eclipse.gemoc.dsl.debug.ide.adapter.DSLStackFrameAdapter;
import org.eclipse.gemoc.dsl.debug.ide.adapter.variable.DSLObjectVariable;
import org.eclipse.gemoc.dsl.debug.ide.sirius.ui.DSLDebugModelPresentation;
import org.eclipse.gemoc.dsl.debug.ide.sirius.ui.SiriusEditorUtils;
import org.eclipse.gemoc.dsl.debug.provider.CustomDebugItemProviderAdapterFactory;
import org.eclipse.gemoc.executionframework.engine.ui.provider.DSLVariableLabelDecorator;
import org.eclipse.gemoc.executionframework.ui.IMSEPresenter;
import org.eclipse.gemoc.trace.commons.model.trace.MSE;
import org.eclipse.gemoc.trace.commons.model.trace.Step;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.viewers.IFontProvider;
import org.eclipse.jface.viewers.ILabelDecorator;
import org.eclipse.sirius.ui.business.api.dialect.DialectEditor;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.xtext.naming.DefaultDeclarativeQualifiedNameProvider;
import org.eclipse.xtext.naming.QualifiedName;

public class GemocGenericDebugModelPresentation extends DSLDebugModelPresentation  implements IFontProvider {

	protected final ILabelDecorator labelDecorator;
	
	/**
	 * Constructor.
	 */
	public GemocGenericDebugModelPresentation() {
		super();
		final ComposedAdapterFactory adapterFactory = new ComposedAdapterFactory(
				ComposedAdapterFactory.Descriptor.Registry.INSTANCE);

		adapterFactory.addAdapterFactory(new ResourceItemProviderAdapterFactory());
		CustomDebugItemProviderAdapterFactory debugFactory = new CustomDebugItemProviderAdapterFactory();
		adapterFactory.addAdapterFactory(debugFactory);
		adapterFactory.addAdapterFactory(new ReflectiveItemProviderAdapterFactory());
		eLabelProvider = new AdapterFactoryLabelProvider(adapterFactory);
		
		labelDecorator = new DSLVariableLabelDecorator();
	}
	
	@Override
	public IEditorInput getEditorInput(Object element) {
		final IEditorInput res;

		if (element instanceof MSE && ((MSE) element).getCaller() != null) {
			res = super.getEditorInput(((MSE) element).getCaller());
		} else {
			res = super.getEditorInput(element);
		}

		return res;
	}

	@Override
	public String getEditorId(IEditorInput input, Object element) {
		final String res;

		if (element instanceof MSE && ((MSE) element).getCaller() != null) {
			res = super.getEditorId(input, ((MSE) element).getCaller());
		} else {
			res = super.getEditorId(input, element);
		}

		return res;
	}

	@Override
	public boolean addAnnotations(IEditorPart editorPart, IStackFrame frame) {
		if (frame instanceof DSLStackFrameAdapter) {
			changeCurrentStackFrame(frame);
			if (editorPart instanceof DialectEditor) {
				EObject instruction = ((DSLStackFrameAdapter) frame).getCurrentInstruction();
				if (instruction instanceof Step) {
					final List<MSE> tickedEvents = new ArrayList<>();
					tickedEvents.add(((Step<?>) instruction).getMseoccurrence().getMse());
					showEvents(tickedEvents);
					final Set<EObject> callers = new LinkedHashSet<EObject>();
					for (MSE event : tickedEvents) {
						if (event.getCaller() != null) {
							callers.add(event.getCaller());
						}
					}
					try {
						SiriusEditorUtils.showInstructions((DialectEditor) editorPart, new ArrayList<EObject>(callers));
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					try {
						SiriusEditorUtils.showInstruction((DialectEditor) editorPart, instruction);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			} else {
				super.addAnnotations(editorPart, frame);
			}
		}

		return false;
	}

	private void showEvents(List<MSE> events) {
		final List<URI> uris = new ArrayList<URI>();
		for (MSE event : events) {
			uris.add(EcoreUtil.getURI(event));
		}
		for (IMSEPresenter presenter : org.eclipse.gemoc.executionframework.ui.Activator.getDefault().getEventPresenters()) {
			presenter.present(uris);
		}
	}
	
	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.debug.ui.IDebugModelPresentation#getImage(java.lang.Object)
	 */
	public Image getImage(final Object element) {
		final Image res;

		final Object unwrapped = unwrapp(element);
		if (unwrapped instanceof Variable) {
			Image valueImage = getImage(((Variable)unwrapped).getValue());
			if(valueImage == null) {
				valueImage = super.getImage(element);
			} 
			if(valueImage == null) {
				valueImage = DebugUIPlugin.getDefaultLabelProvider().getImage(element);
			}
			res = labelDecorator.decorateImage(valueImage, unwrapped);

		} else	if( element instanceof DSLObjectVariable ) {
			Image valueImage = getImage(unwrapp(element));
			if(valueImage == null) {
				valueImage = super.getImage(element);
			} 
			if(valueImage == null) {
				valueImage = DebugUIPlugin.getDefaultLabelProvider().getImage(element);
			}
			res = labelDecorator.decorateImage(valueImage,element);
		} else {
			//res = super.getImage(element);
			res = labelDecorator.decorateImage(super.getImage(element), element);
		}
		return res;
	}
	
	@Override
	public String getText(Object element) {
		
		if(element instanceof Adapter) {
			Object target = ((Adapter)element).getTarget();
		
			if(target instanceof DebugTarget) {
				return ((DebugTarget)target).getName();
				
			} else if(target instanceof org.eclipse.gemoc.dsl.debug.Thread) {
				return ((org.eclipse.gemoc.dsl.debug.Thread)target).getName();
				
			} else if(target instanceof StackFrame) {
				StackFrame t = ((StackFrame) target);
				if(t.getCurrentInstruction() != t.getContext()) {
					DefaultDeclarativeQualifiedNameProvider nameprovider = new DefaultDeclarativeQualifiedNameProvider();
					StringBuilder sb = new StringBuilder();
					Formatter formatter = new Formatter(sb);
					QualifiedName qn = nameprovider.getFullyQualifiedName(t.getCurrentInstruction());
					if(qn != null) {
						formatter.format("%s => %s", t.getName(), qn);
					} else {
						String resBasedName = EObjectUtil.getResourceBasedName(t.getCurrentInstruction(), false);
						if (resBasedName != null) {
							formatter.format("%s  => %s", t.getName(), resBasedName);
						} else {
							formatter.format("%s  => %s", t.getName(), t.getCurrentInstruction());
						}
					}
					formatter.close();
					return sb.toString();
				}
				return ((StackFrame) target).getName();
			}
			
		}
		return super.getText(element);
	}

	@Override
	public Font getFont(Object element) {
		if(element instanceof Adapter) {
			Object target = ((Adapter)element).getTarget();
			if(target instanceof StackFrame) {
				StackFrame t = ((StackFrame) target);
				if(t.getChildFrame() == null) {
					// topmost frame is the stack created but not run yet
					// as it is different from java usual presentation, let's differenciate it but putting it in italic
					return JFaceResources.getFontRegistry().getItalic(JFaceResources.DEFAULT_FONT);
				}
			}
		}
		return null;
	}
	
}
