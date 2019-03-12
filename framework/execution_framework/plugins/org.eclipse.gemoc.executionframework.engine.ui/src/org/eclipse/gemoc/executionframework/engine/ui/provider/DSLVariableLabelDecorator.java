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

import org.eclipse.debug.core.DebugException;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.gemoc.dsl.debug.Variable;
import org.eclipse.gemoc.dsl.debug.ide.adapter.value.DSLObjectValue;
import org.eclipse.gemoc.dsl.debug.ide.adapter.variable.DSLObjectVariable;
import org.eclipse.gemoc.dsl.debug.ide.ui.provider.OverlayImageDescriptor;
import org.eclipse.gemoc.executionframework.debugger.AbstractGemocDebugger;
import org.eclipse.gemoc.executionframework.debugger.DefaultDynamicPartAccessor;
import org.eclipse.gemoc.executionframework.debugger.IDynamicPartAccessor;
import org.eclipse.gemoc.executionframework.engine.ui.Activator;
import org.eclipse.gemoc.xdsmlframework.commons.DynamicAnnotationHelper;
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
			Activator.getImageDescriptor("icons/full/deco16/bottomright_lock_closed.gif").getImageData(100));

	/**
	 * Static data unlocked {@link Image}.
	 */
	private final Image staticDataUnlocked = new Image(Display.getDefault(), 
			Activator.getImageDescriptor("icons/full/deco16/bottomright_lock_open.gif").getImageData(100));

	
	/**
	 * Static data unlocked {@link Image}.
	 */
	private final Image dynamicData = new Image(Display.getDefault(), 
			Activator.getImageDescriptor("icons/full/deco16/topright_lock.gif").getImageData(100));
	

	private final IDynamicPartAccessor dynamicPartAccessor = new DefaultDynamicPartAccessor();



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
			// root dynamic variables are currently created using this string as declartionType
			boolean isDynamic = v.getDeclarationType().equals(AbstractGemocDebugger.MUTABLE_DATA_DECLARATION_TYPENAME);
			res = getImageForMutableType(img,isDynamic, v.isSupportModifications());					
		} else if (element instanceof DSLObjectVariable) {
			DSLObjectVariable dslVar = ((DSLObjectVariable)element);
			boolean isDynamic = false;
			if(dslVar.getObject() instanceof EObject){
				isDynamic = dynamicPartAccessor.isDynamic((EObject)dslVar.getObject());
			} else {
				if(dslVar.getObject() instanceof EObjectContainmentEList) {
					EObjectContainmentEList l = (EObjectContainmentEList) dslVar.getObject();
					isDynamic = dynamicPartAccessor.isDynamic(l.getEObject());
				} else {
					if(dslVar.getParentValue() != null && dslVar.getParentValue().getValue() instanceof EObject) {
						EStructuralFeature p = null;
						try {
							p = ((EObject)dslVar.getParentValue().getValue()).eClass().getEStructuralFeature(dslVar.getName());
							if(p != null) {
								isDynamic = DynamicAnnotationHelper.isDynamic(p);
							}
						} catch (DebugException e) {}
					}
				}
			}
			res = getImageForMutableType(image, isDynamic, dslVar.supportsValueModification());
		} else if (element instanceof DSLObjectValue) {
			DSLObjectValue dslVar = ((DSLObjectValue)element);			
			res = getImageForMutableType(image, false, false);
		} else {
			res = image; 
		} 

		return res;
	}

	protected Image getImageForMutableType(Image baseImage, boolean isDynamic, boolean supportModification) {
		Image res;
		final OverlayImageDescriptor descriptor;
		if(!isDynamic) {
			// static data
			if(supportModification) {
				descriptor = new OverlayImageDescriptor(baseImage, staticDataUnlocked);
			} else {
				descriptor = new OverlayImageDescriptor(baseImage, staticDataLocked);
			}	
		} else {
			// Runtime data
			descriptor = new OverlayImageDescriptor(baseImage, dynamicData);
		}
		Image cachedImage = imagesCache.get(descriptor);
		if (cachedImage == null) {
			cachedImage = descriptor.createImage();
			imagesCache.put(descriptor, cachedImage);
		}
		res = cachedImage;
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
