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
import org.eclipse.core.resources.IProject
import org.eclipse.gemoc.dsl.Dsl

interface OperationalSemanticsViewGenerator {

	public def boolean canHandle(Dsl language, IProject melangeProject)

	public def OperationalSemanticsView generate(Dsl language, IProject melangeProject)
	

}
