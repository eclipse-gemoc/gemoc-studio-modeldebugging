/*******************************************************************************
 * Copyright (c) 2017 Inria.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Inria - initial API and implementation
 *******************************************************************************/
package org.eclipse.gemoc.xdsmlframework.test.lib

import org.eclipse.swtbot.eclipse.finder.SWTWorkbenchBot

import static org.eclipse.swtbot.swt.finder.matchers.WidgetMatcherFactory.allOf
import static org.eclipse.swtbot.swt.finder.matchers.WidgetMatcherFactory.anyOf
import static org.eclipse.swtbot.swt.finder.matchers.WidgetMatcherFactory.widgetOfType
import static org.eclipse.swtbot.swt.finder.matchers.WidgetMatcherFactory.withStyle
import static org.eclipse.swtbot.swt.finder.matchers.WidgetMatcherFactory.withTooltip
import org.eclipse.swt.widgets.Button
import org.eclipse.swt.widgets.ToolItem
import org.eclipse.swt.widgets.Widget
import java.util.List
import org.eclipse.swt.widgets.Display

/**
 * Class containing helper methods for testing a workspace in a GEMOC Language workbench
 */
class SWTBotHelper {
		
	
	/**
	 * relaunch the  waitForJobs several times in case some other background task
	 * also wait for idle time to triggers new jobs 
	 */
	static def void printShellList(SWTWorkbenchBot	bot) {
		val shells = bot.shells.toList
		println("SWTBot Known shells("+shells.size+"):")
		for (shell : shells) {
			println("\t"+shell)
		}
	}
	
	static def void printTreeList(SWTWorkbenchBot	bot) {
		println("SWTBot tree(" + bot.tree().allItems.size + "):")
		for (item : bot.tree().allItems) {
			println("\t" + item)
		}
	}
	
	static def printSWTBotStatus(SWTWorkbenchBot bot) {
		println("### SWTBot context analysis ###")
		printShellList(bot)
		printTreeList(bot)

		Display.^default.syncExec(new Runnable() {
			override run() {
				try {
					val matcherButton = allOf(widgetOfType(typeof(Button) /*, withLabel(label), withStyle(SWT.PUSH, "SWT.PUSH")*/ ))
					val buttons = bot.widgets(matcherButton).toList
					printWidgetList("Button",buttons)
				} catch (Exception e) {
					println("SWTBot Button(0) [no Button in the UI after 20000ms]")
					// this has required a 20000ms timeout but there is no Button in the bot now
				}
				val matcherToolItem = allOf(
					widgetOfType(typeof(ToolItem))
				// ,	anyOf(withTooltip("Step &Into (F5)"), withTooltip("Step &Into")), withStyle(SWT.PUSH, "SWT.PUSH")
				)
				val toolItems = bot.widgets(matcherToolItem).toList
				printWidgetList("ToolItem", toolItems)
			}
		})
	}

	static def printWidgetList(String itemType, List<? extends Widget> items) {
		println("SWTBot " + itemType + "(" + items.size + "):")
		for (item : items) {
			if(item instanceof ToolItem) {
				println("\t" + item + " toolTipText="+item.toolTipText)
			} else {
				println("\t" + item)	
			}
		}
	}
	
}
