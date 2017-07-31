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
 package org.eclipse.gemoc.opsemanticsview.gen

import opsemanticsview.OperationalSemanticsView
import fr.inria.diverse.melange.metamodel.melange.Language
import org.eclipse.core.resources.IProject

interface OperationalSemanticsViewGenerator {

	public def boolean canHandle(Language language, IProject melangeProject)

	public def OperationalSemanticsView generate(Language language, IProject melangeProject)
	

}
