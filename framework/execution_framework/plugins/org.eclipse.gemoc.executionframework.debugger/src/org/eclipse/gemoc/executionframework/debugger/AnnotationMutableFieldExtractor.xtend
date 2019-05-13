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
import java.util.List
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.transaction.RecordingCommand
import org.eclipse.emf.transaction.util.TransactionUtil
import org.eclipse.gemoc.commons.eclipse.emf.EObjectUtil
import org.eclipse.gemoc.executionframework.engine.core.CommandExecution
import org.eclipse.gemoc.xdsmlframework.commons.DynamicAnnotationHelper
import org.eclipse.xtext.naming.DefaultDeclarativeQualifiedNameProvider

class AnnotationMutableFieldExtractor implements IMutableFieldExtractor {

	val DefaultDeclarativeQualifiedNameProvider nameprovider = new DefaultDeclarativeQualifiedNameProvider()

	override extractMutableField(EObject eObject) {

		val List<MutableField> result = new ArrayList<MutableField>()

		val idProp = eObject.eClass.getEIDAttribute
		val String objectName = if (idProp !== null && eObject.eGet(idProp) !==  null) {
				val id = eObject.eGet(idProp);
				val NumberFormat formatter = new DecimalFormat("00");
				val String idString = if(id instanceof Integer) formatter.format((id as Integer)) else id.toString;
				eObject.eClass.name + "_" + idString // "returned" value
			} else {
				val qname = nameprovider.getFullyQualifiedName(eObject)
				if (qname !== null) {
					qname.toString // "returned" value 
				} else { 
					val uriBasedName = EObjectUtil.getResourceBasedName(eObject, false)
					if( uriBasedName !== null) {
						uriBasedName // "returned" value 
					} else  {
						eObject.toString // "returned" value 
					}	
				}
			}

		for (prop : eObject.eClass.getEAllStructuralFeatures) {
			if (DynamicAnnotationHelper.isDynamic(prop)) {
				val mut = new MutableField(
					/* name    */ prop.name + " ([" +  eObject.eClass.getName + "] " + objectName + ")",
					/* eObject */ eObject,
					/* mutProp */ prop,
					/* getter  */ [eObject.eGet(prop)],
					/* setter  */ [ o |

						val ed = TransactionUtil.getEditingDomain(eObject.eResource);
						var RecordingCommand command = new RecordingCommand(ed,
							"Setting value " + o + " in " + objectName + "." + prop.name + " from the debugger") {
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
