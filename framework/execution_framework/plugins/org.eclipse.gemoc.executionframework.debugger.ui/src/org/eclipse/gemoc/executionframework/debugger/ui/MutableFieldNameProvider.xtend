/*******************************************************************************
 * Copyright (c) 2016 Inria and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Inria - initial API and implementation
 *******************************************************************************/
 package org.eclipse.gemoc.executionframework.debugger.ui

import org.eclipse.xtext.naming.DefaultDeclarativeQualifiedNameProvider;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.gemoc.executionframework.debugger.MutableField

public class MutableFieldNameProvider extends DefaultDeclarativeQualifiedNameProvider{
 
    def QualifiedName qualifiedName(MutableField e) {
        var qnameSegments = this.getFullyQualifiedName(e.geteObject).segments
        qnameSegments.add(e.mutableProperty.name)
        return QualifiedName.create(qnameSegments);
    }
 
}
