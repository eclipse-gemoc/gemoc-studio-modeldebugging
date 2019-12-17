/*******************************************************************************
 * Copyright (c) 2016, 2019 Inria and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Inria - initial API and implementation
 *******************************************************************************/
package org.eclipse.gemoc.xdsmlframework.commons.ui.k3.dialogs;

import java.util.Arrays;

import org.eclipse.gemoc.commons.eclipse.ui.dialogs.SelectPluginIProjectWithFileExtensionDialog;
import org.eclipse.swt.widgets.Shell;

public class SelectDSAIProjectDialog extends SelectPluginIProjectWithFileExtensionDialog {

	public SelectDSAIProjectDialog(Shell parentShell) {
		super(parentShell, Arrays.asList("xtend"));
	}

}
