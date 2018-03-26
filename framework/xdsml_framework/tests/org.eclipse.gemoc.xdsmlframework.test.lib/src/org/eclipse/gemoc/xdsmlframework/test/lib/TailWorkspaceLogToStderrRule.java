package org.eclipse.gemoc.xdsmlframework.test.lib;

import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

public class TailWorkspaceLogToStderrRule extends TestWatcher {
	@Override
    protected void failed(Throwable e, Description description) {
		WorkspaceLogReaderHelper.tailWorkspaceLogToPrintStream(System.err);
    }
}
