/*******************************************************************************
 * Copyright (c) 2016, 2019 Inria and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Inria - initial API and implementation
 *******************************************************************************/
package org.eclipse.gemoc.execution.sequential.javaengine.ui.launcher;

import java.util.Formatter;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.gemoc.commons.eclipse.emf.EObjectUtil;
import org.eclipse.gemoc.dsl.debug.DebugTarget;
import org.eclipse.gemoc.dsl.debug.StackFrame;
import org.eclipse.gemoc.dsl.debug.ide.adapter.DSLDebugTargetAdapter;
import org.eclipse.gemoc.dsl.debug.ide.adapter.DSLThreadAdapter;
import org.eclipse.gemoc.execution.sequential.javaengine.ui.Activator;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.viewers.IFontProvider;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.xtext.naming.DefaultDeclarativeQualifiedNameProvider;
import org.eclipse.xtext.naming.QualifiedName;

public class PlainK3DebugModelPresentation extends GemocDebugModelPresentation implements IFontProvider {
	
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
	
	
	
	private Image image;
	
	@Override
	public Image getImage(Object element) {
		if (element instanceof DSLDebugTargetAdapter || element instanceof DSLThreadAdapter) {
			ImageDescriptor descriptor = Activator.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "icons/debugt_obj.png");
			Image cachedImage = imagesCache.get(descriptor);
			if (cachedImage == null) {
				cachedImage = new Image(Display.getDefault(), descriptor.getImageData(100));
				imagesCache.put(descriptor, cachedImage);
			}
			return image;
		}
		return super.getImage(element);
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