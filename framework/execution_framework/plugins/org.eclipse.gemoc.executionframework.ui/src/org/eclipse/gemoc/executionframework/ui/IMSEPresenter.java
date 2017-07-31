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
package org.eclipse.gemoc.executionframework.ui;

import java.util.List;

import org.eclipse.emf.common.util.URI;

/**
 * a class that implements IMSEPresenter is a graphical UI that 
 * presents or displays ModelSpecificEvents
 * When asked it should highlight or focus on the requested  ModelSpecificEvents
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 *
 */
public interface IMSEPresenter {

	/**
	 * Ask the view to present or highlight the given {@link List} of ModelSpecificEvent
	 * 
	 * @param events ModelSpecificEvent to highlight
	 */
	void present(List<URI> events);

}
