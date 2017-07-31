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
package org.eclipse.gemoc.xdsmlframework.ui.utils.dialogs;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;

import fr.inria.diverse.k3.al.annotationprocessor.Aspect;


public class SelectMainMethodDialog extends ElementListSelectionDialog  {

	private Set<Class<?>> _aspects;

	/**
	 * Create a selection dialog displaying all available methods with @main
	 * from 'aspects'
	 */
	public SelectMainMethodDialog(Set<Class<?>> aspects, ILabelProvider renderer) {
		this(aspects,null,renderer);
	}
	
	/**
	 * Create a selection dialog displaying all available methods with @main
	 * from elements in 'aspects' weaving 'modelElem'.
	 * If 'modelElem' is null, selection dialog displays all @main.
	 */
	public SelectMainMethodDialog(Set<Class<?>> aspects, EObject modelElem, ILabelProvider renderer) {
		super(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), renderer);
		_aspects = aspects;
		
		if(aspects != null)
			update(modelElem);
	}

	/**
	 * Display only methods with @main from Aspects applied on modelElem
	 */
	public void update(EObject modelElem){
		
		Class<?> target = null;
		if(modelElem != null){
			target = modelElem.eClass().getInstanceClass();
		}
		
		List<Method> selection = new ArrayList<Method>();
		
		for (Class<?> asp : _aspects)
		{
			if(target == null || getTarget(asp).getSimpleName().equals(target.getSimpleName()))
			{
				for(Method m : asp.getMethods())
				{
					if(isAnnotedMain(m))
					{
						selection.add(m);
					}
				}
			}
		}
		
		this.setElements(selection.toArray());
	}

	/**
	 * Return true is their is @Main
	 */
	private boolean isAnnotedMain(Method m){
		return m.isAnnotationPresent(fr.inria.diverse.k3.al.annotationprocessor.Main.class);
	}

	/**
	 * Return the targeted class from the @Aspect
	 */
	private Class<?> getTarget(Class<?> aspect){
		Annotation annotation = aspect.getAnnotation(fr.inria.diverse.k3.al.annotationprocessor.Aspect.class);
		if(annotation != null){
				Aspect k3tag = (Aspect) annotation;
				return k3tag.className();
		}
		
		return null;
	}
}

