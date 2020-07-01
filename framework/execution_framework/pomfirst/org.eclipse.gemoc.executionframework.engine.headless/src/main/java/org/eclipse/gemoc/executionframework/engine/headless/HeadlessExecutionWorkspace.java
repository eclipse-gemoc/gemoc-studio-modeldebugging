package org.eclipse.gemoc.executionframework.engine.headless;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.gemoc.xdsmlframework.api.core.IExecutionWorkspace;

public class HeadlessExecutionWorkspace implements IExecutionWorkspace {

	@Override
	public IPath getProjectPath() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IPath getModelPath() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IPath getMoCPath() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IPath getMSEModelPath() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IPath getExecutionPath() {
		return new Path(System.getProperty("user.home")).append("gemoc_headless_execution");
	}

	@Override
	public void copyFileToExecutionFolder(IPath filePath) throws CoreException {
		// TODO Auto-generated method stub

	}

}
