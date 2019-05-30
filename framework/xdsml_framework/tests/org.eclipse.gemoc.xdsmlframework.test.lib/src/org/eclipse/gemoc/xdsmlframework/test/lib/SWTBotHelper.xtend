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

/**
 * Class containing helper methods for testing a workspace in a GEMOC Language workbench
 */
class SWTBotHelper {
		
	
	/**
	 * relaunch the  waitForJobs several times in case some other background task
	 * also wait for idle time to triggers new jobs 
	 */
	static def void printShellList(SWTWorkbenchBot	bot) {
		println("SWTBOT Known shells :")
		for (shell : bot.shells) {
			println("\t"+shell.text)
		}
	}
	
}
