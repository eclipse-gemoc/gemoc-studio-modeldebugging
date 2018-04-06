/*******************************************************************************
 * Copyright (c) 2016, 2017 Inria and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Inria - initial API and implementation
 *******************************************************************************/
package org.eclipse.gemoc.execution.sequential.javaengine.ui.launcher;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.gemoc.execution.sequential.javaengine.ui.Activator;

import org.eclipse.gemoc.dsl.debug.DebugTarget;
import org.eclipse.gemoc.dsl.debug.StackFrame;
import org.eclipse.gemoc.dsl.debug.ide.adapter.DSLDebugTargetAdapter;
import org.eclipse.gemoc.dsl.debug.ide.adapter.DSLThreadAdapter;

public class PlainK3DebugModelPresentation extends GemocDebugModelPresentation {
	
	@Override
	public String getText(Object element) {
		
		if(element instanceof Adapter) {
			Object target = ((Adapter)element).getTarget();
		
			if(target instanceof DebugTarget) {
				return ((DebugTarget)target).getName();
				
			} else if(target instanceof org.eclipse.gemoc.dsl.debug.Thread) {
				return ((org.eclipse.gemoc.dsl.debug.Thread)target).getName();
				
			} else if(target instanceof StackFrame) {
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

}