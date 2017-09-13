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
package org.eclipse.gemoc.execution.sequential.javaxdsml.ide.ui.menu

import java.util.List
import java.util.Set
import org.eclipse.core.commands.ExecutionEvent
import org.eclipse.emf.ecore.EStructuralFeature
import org.eclipse.xtext.nodemodel.INode
import org.eclipse.xtext.nodemodel.util.NodeModelUtils
import org.eclipse.xtext.ui.editor.utils.EditorUtils
import org.eclipse.gemoc.execution.sequential.javaxdsml.ide.ui.commands.CreateDSAProjectHandler

class AddDSA extends CreateDSAProjectHandler {
	
	//FIXME: deprecated?
	
//	override protected updateMelange(ExecutionEvent event, Language language, Set<String> aspects) {
//		val editor = EditorUtils.getActiveXtextEditor(event)
//		if (editor != null) {
//			val document = editor.document
//			document.modify[
//				val EStructuralFeature operators = language.eClass().getEStructuralFeature("operators");
//				val List<INode> nodesOp = NodeModelUtils.findNodesForFeature(language, operators);
//				var int lastOffset = -1
//				for(node : nodesOp){
//					if(node.endOffset > lastOffset) lastOffset = node.endOffset
//				}
//				if(lastOffset != -1){
//					
//					val StringBuilder insertion = new StringBuilder
//					aspects.forEach[asp |
//						insertion.append("\twith "+ asp + "\n")
//					] 
//					document.replace(lastOffset,0, "\n\n"+insertion.toString)
//					return null // no computed value
//				}
//			]
//		}
//	}
}