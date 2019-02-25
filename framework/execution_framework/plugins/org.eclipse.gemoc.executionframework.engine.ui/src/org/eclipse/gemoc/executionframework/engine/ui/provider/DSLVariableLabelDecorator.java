/*******************************************************************************
 * Copyright (c) 2019 Inria.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Inria - initial API and implementation
 *******************************************************************************/
package org.eclipse.gemoc.executionframework.engine.ui.provider;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.gemoc.dsl.debug.Variable;
import org.eclipse.gemoc.dsl.debug.ide.ui.provider.OverlayImageDescriptor;
import org.eclipse.gemoc.executionframework.debugger.AbstractGemocDebugger;
import org.eclipse.gemoc.executionframework.engine.ui.Activator;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.BaseLabelProvider;
import org.eclipse.jface.viewers.ILabelDecorator;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

/**
 * A {@link ILabelDecorator} adding the variable decorator.
 * 
 */
public class DSLVariableLabelDecorator extends BaseLabelProvider implements ILabelDecorator {

	/**
	 * The {@link Image} cache.
	 */
	protected final Map<ImageDescriptor, Image> imagesCache = new HashMap<ImageDescriptor, Image>();

	/**
	 * Static Data locked {@link Image}.
	 */
	private final Image staticDataLocked = new Image(Display.getDefault(), 
			Activator.getImageDescriptor("icons/full/deco16/lock.gif").getImageData(100));

	/**
	 * Static data unlocked {@link Image}.
	 */
	private final Image staticDataUnlocked = new Image(Display.getDefault(), 
			Activator.getImageDescriptor("icons/full/deco16/lock_open.gif").getImageData(100));

	
	/**
	 * Static data unlocked {@link Image}.
	 */
	private final Image dynamicData = new Image(Display.getDefault(), 
			Activator.getImageDescriptor("icons/full/deco16/lock_blue_open.gif").getImageData(100));
	




	/**
	 * Constructor.
	 * 
	 * @param identifier
	 *            the debug model identifier
	 */
	public DSLVariableLabelDecorator() {
		
	}



	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.jface.viewers.ILabelDecorator#decorateImage(org.eclipse.swt.graphics.Image,
	 *      java.lang.Object)
	 */
	public Image decorateImage(Image image, Object element) {
		Image img = image;
		Image res;
		if(image == null ) {
			return image;
		}
		if (element instanceof Variable) {
			Variable v = (Variable)element;
			final OverlayImageDescriptor descriptor;
			if(v.getDeclarationType().equals(AbstractGemocDebugger.MUTABLE_STATIC_DATA_DECLARATION_TYPENAME)) {
				// static data
				if(v.isSupportModifications()) {
					descriptor = new OverlayImageDescriptor(img, staticDataUnlocked);
				} else {
					descriptor = new OverlayImageDescriptor(img, staticDataLocked);
				}	
			} else {
				// Runtime data
				descriptor = new OverlayImageDescriptor(img, dynamicData);
			}
			Image cachedImage = imagesCache.get(descriptor);
			if (cachedImage == null) {
				cachedImage = descriptor.createImage();
				imagesCache.put(descriptor, cachedImage);
			}
			res = cachedImage;
		
		} else { 
			res = image; 
		} 

		return res;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.jface.viewers.ILabelDecorator#decorateText(java.lang.String, java.lang.Object)
	 */
	public String decorateText(String text, Object element) {
		final String res;

	/*	if (element instanceof EObject) {
			res = text;
		} else { */
			res = text;
	//	} 

		return res;
	}


	@Override
	public void dispose() {
		staticDataLocked.dispose();
		staticDataUnlocked.dispose();
		for (Image cachedImage : imagesCache.values()) {
			cachedImage.dispose();
		}
		super.dispose();
	}


}
