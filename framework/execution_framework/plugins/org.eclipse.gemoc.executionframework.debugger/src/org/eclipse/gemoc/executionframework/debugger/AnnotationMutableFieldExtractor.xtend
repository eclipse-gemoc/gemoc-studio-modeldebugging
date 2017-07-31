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
 package org.eclipse.gemoc.executionframework.debugger

import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.ArrayList
import java.util.HashMap
import java.util.List
import java.util.Map
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.transaction.RecordingCommand
import org.eclipse.emf.transaction.util.TransactionUtil
import org.eclipse.gemoc.executionframework.engine.core.CommandExecution
import org.eclipse.gemoc.xdsmlframework.commons.DynamicAnnotationHelper
import org.eclipse.xtext.naming.DefaultDeclarativeQualifiedNameProvider

class AnnotationMutableFieldExtractor implements IMutableFieldExtractor {

	private val Map<EClass, Integer> counters = new HashMap
	
	private val org.eclipse.xtext.naming.DefaultDeclarativeQualifiedNameProvider nameprovider = new DefaultDeclarativeQualifiedNameProvider()

	override extractMutableField(EObject eObject) {

		val List<MutableField> result = new ArrayList<MutableField>()

		val idProp = eObject.eClass.getEIDAttribute
		val String objectName = if (idProp != null) {
				val id = eObject.eGet(idProp);
				if (id != null) {
					val NumberFormat formatter = new DecimalFormat("00");
					val String idString = if(id instanceof Integer) formatter.format((id as Integer)) else id.toString;
					eObject.eClass.name + "_" + idString // "returned" value 
				} else {
					if (!counters.containsKey(eObject.eClass)) {
						counters.put(eObject.eClass, 0)
					}
					val Integer counter = counters.get(eObject.eClass)
					counters.put(eObject.eClass, counter + 1)
					eObject.eClass.name + "_" + counter
				}

			} else {
				val qname = nameprovider.getFullyQualifiedName(eObject)
				if(qname == null) 
					eObject.toString
				else 
					qname.toString
			}
		
		for (prop : eObject.eClass.getEAllStructuralFeatures) {
			if (DynamicAnnotationHelper.isDynamic(prop)) {
				val mut = new MutableField(
					/* name    */ prop.name+" ("+objectName+ " :"+eObject.eClass.getName +")",
					/* eObject */ eObject,
					/* mutProp */ prop,
					/* getter  */ [eObject.eGet(prop)],
					/* setter  */ [ o |

						val ed = TransactionUtil.getEditingDomain(eObject.eResource);
						var RecordingCommand command = new RecordingCommand(ed,
							"Setting value " + o + " in " + objectName +"."+prop.name+ " from the debugger") {
							protected override void doExecute() {
								eObject.eSet(prop, o)
							}
						};
						CommandExecution.execute(ed, command);

					]
				)
				result.add(mut)

			}
		}
		return result
	}

}
