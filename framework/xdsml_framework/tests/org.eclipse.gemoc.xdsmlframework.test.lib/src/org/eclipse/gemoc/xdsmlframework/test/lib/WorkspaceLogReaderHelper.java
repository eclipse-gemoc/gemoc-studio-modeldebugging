package org.eclipse.gemoc.xdsmlframework.test.lib;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import org.eclipse.core.runtime.Platform;

public class WorkspaceLogReaderHelper {
	
	private static final int ONE_HUNDRED_KILO_BYTE_IN_BYTES = 100 * 1024;

   
	/**
	 * tail the current workspace error log into a printstream
	 * typical usage : tailWorkspaceLogToPrintStream(System.err)
	 * @param out
	 */
    public static void tailWorkspaceLogToPrintStream(PrintStream out) {
    	File file = Platform.getLogFileLocation().toFile();

    	System.err.println("------------------------------");
		System.err.println("----- Tail of "+file.getPath());
    	try (BufferedReader reader = new BufferedReader(
				new InputStreamReader(new TailInputStream(file, ONE_HUNDRED_KILO_BYTE_IN_BYTES), StandardCharsets.UTF_8))) {
			for (;;) {
				String line0 = reader.readLine();
				if (line0 == null)
					break;
				out.println(line0);
			}
		} catch (IOException e) { // do nothing
		} finally {
		}
    	System.err.println("------------------------------");
    }

}
