/*******************************************************************************
 * Copyright (c) 2019 Inria.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Inria - initial API and implementation
 *******************************************************************************/
package org.eclipse.gemoc.xdsmlframework.test.lib;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.ZoneId;
import java.util.Date;

/**
 * Provides some helper methods to enhance information provided by the video recording of UI Tests
 *
 */
public class GEMOCTestVideoHelper {

	/**
	 * relative path to the log file
	 * this is relative to the environment variable "WORKSPACE"  that is usually set be JENKINS
	 */
	public static String LOGFILEPATH = "gemoc-studio/dev_support/full_compilation/target/system_test_timeline.log";
	
	/**
	 * if the file LOGFILENAME exists, then it reads the first line in order to get the test start time stamp
	 * It then add a new line indicating the duration since this time stamp (allowing to navigate in the 
	 * corresponding video of this amount of time) and the name of the test suite
	 */
	public static void addTestSuiteVideoLog(String msg) {
		File file = getVideoTimeStampFile();
		if(!file.exists()) {
			System.out.println("ignoring addTestSuiteVideoLog");
			System.out.println(file.getPath()+ " not found");
			return;
		}
		try {
			Date videoStartDate = readTimeStamp();
			try(PrintWriter printwriter = new PrintWriter(new FileWriter(file, true))){
				Date currentDate = new Date();
				Duration duration = Duration.between(currentDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime(),
						videoStartDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
				
				// print in the log file and in the system.out (for junit capture)
				printwriter.println(""+String.format("%1$13s", duration) +" | " + currentDate + " | "+msg);
				System.out.println(""+String.format("%1$13s", duration) +" | " + currentDate + " | "+msg);
			}
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
	}
	
	public static Date readTimeStamp() throws IOException, ParseException { 
		try (
				FileReader fr=new FileReader(getVideoTimeStampFile());    //reads the file  
				BufferedReader br=new BufferedReader(fr)) {  //creates a buffering character input stream  
			String line=br.readLine(); 
			if(line!=null)  
			{     
				//date --rfc-3339=s     
				//2019-11-29 09:43:30+00:00
				SimpleDateFormat sdf = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ssXXX");
				return sdf.parse(line);
			}
		}
		return null;
	}
	
	public static File getVideoTimeStampFile() {
		
		// search the git root using the JENKINS environment variable
		String jenkinsWorkspaceDir = System.getProperty("WORKSPACE");
		File file=new File(jenkinsWorkspaceDir+"/"+LOGFILEPATH);
		return file;
	}
	
}
