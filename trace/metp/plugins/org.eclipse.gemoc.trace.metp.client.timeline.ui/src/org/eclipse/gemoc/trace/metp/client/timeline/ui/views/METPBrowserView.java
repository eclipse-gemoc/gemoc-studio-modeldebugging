/*******************************************************************************
 * Copyright (c) 2020 Inria and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Inria - initial API and implementation
 *******************************************************************************/
package org.eclipse.gemoc.trace.metp.client.timeline.ui.views;


import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import javax.inject.Inject;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.URIUtil;
import org.eclipse.gemoc.trace.metp.client.timeline.ui.Activator;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.LocationEvent;
import org.eclipse.swt.browser.LocationListener;
import org.eclipse.swt.browser.ProgressEvent;
import org.eclipse.swt.browser.ProgressListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;


/**
 * This sample class demonstrates how to plug-in a new
 * workbench view. The view shows data obtained from the
 * model. The sample creates a dummy model on the fly,
 * but a real implementation would connect to the model
 * available either in this or another plug-in (e.g. the workspace).
 * The view is connected to the model using a content provider.
 * <p>
 * The view uses a label provider to define how model
 * objects should be presented in the view. Each
 * view can present the same model objects using
 * different labels and icons, if needed. Alternatively,
 * a single label provider can be shared between views
 * in order to ensure that objects of the same type are
 * presented in the same way everywhere.
 * <p>
 */

public class METPBrowserView extends ViewPart {

	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "org.eclipse.gemoc.trace.metp.client.timeline.ui.views.METPBrowserView";
	
	public static final String SIMPLE_TIMELINE_PAGE_PATH = "web/simple-timeline/dist";
	public static final String TEST_PAGE_PATH = "web/metp_test";

	@Inject IWorkbench workbench;
	
	private Browser browserViewer;
	private Action openTestPageAction;
	private Action openMainTracePageAction;
//	private Action doubleClickAction;
	 
	/**
	 * indicates if the page is loaded
	 * prevents from calling scripts on page not loaded
	 */
	private Boolean completed = false;

	@Override
	public void createPartControl(Composite parent) {
		
		browserViewer = new Browser(parent, SWT.NONE);
		
		try {
			URL url = FileLocator.find(Activator.getDefault().getBundle(), new Path(SIMPLE_TIMELINE_PAGE_PATH), null);
			url = FileLocator.toFileURL(url);
			File file = URIUtil.toFile(URIUtil.toURI(url));
			browserViewer.setUrl(file.getCanonicalPath()+"/index.html");
			// launch the action when page is loaded
			browserViewer.addProgressListener(new ProgressListener() {
	            @Override
	            public void completed(ProgressEvent event) {
	            	synchronized (completed) {
	            		completed = true;	
					}
	            	String metpWSport = Integer.toString(org.eclipse.gemoc.ws.server.Activator.getDefault().getAssignedPort());
	            	//browserViewer.execute("document.initializeTimelineApp(\""+metpWSport+"\");");
	            	browserViewer.execute("document.webTimeline.initWS(\""+metpWSport+"\");");
	            	//System.out.println(browserViewer.execute("document.initializeTimelineApp(\""+metpWSport+"\");"));
	            	   	//System.out.println(browserViewer.execute("document.sayHello();"));
	                //System.out.println(browserViewer.execute("alert(windows.sayHello);"));
	            }
	            @Override
	            public void changed(ProgressEvent event) {
	            }
	        });
			
			browserViewer.addLocationListener(new LocationListener() {
				
				@Override
				public void changing(LocationEvent event) {
					synchronized (completed) {
	            		completed = false;	
					}
				}
				
				@Override
				public void changed(LocationEvent event) {
				}
			});
			// https://www.eclipse.org/articles/Article-SWT-browser-widget/browser.html

		} catch (IOException | URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		makeActions();
		contributeToActionBars();
	}

	private void contributeToActionBars() {
		IActionBars bars = getViewSite().getActionBars();
		fillLocalPullDown(bars.getMenuManager());
		fillLocalToolBar(bars.getToolBarManager());
	}

	private void fillLocalPullDown(IMenuManager manager) {
		manager.add(openTestPageAction);
		manager.add(new Separator());
		manager.add(openMainTracePageAction);
	}

	
	private void fillLocalToolBar(IToolBarManager manager) {
		manager.add(openTestPageAction);
		manager.add(openMainTracePageAction);
	}

	private void makeActions() {
		openTestPageAction = new Action() {
			public void run() {
				try {
					URL url = FileLocator.find(Activator.getDefault().getBundle(), new Path(SIMPLE_TIMELINE_PAGE_PATH), null);
					url = FileLocator.toFileURL(url);
				
					File file = URIUtil.toFile(URIUtil.toURI(url));
					browserViewer.setUrl(file.getCanonicalPath()+"/index.html");
					
				} catch (IOException | URISyntaxException e) {
					Activator.logError(e.getMessage(), e);
				}
			}
		};
		openTestPageAction.setText("Open Basic Timeline");
		openTestPageAction.setToolTipText("Basic Timeline tooltip");
		openTestPageAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
			getImageDescriptor(ISharedImages.IMG_OBJS_INFO_TSK));
		
		openMainTracePageAction = new Action() {
			public void run() {
				try {
					URL url = FileLocator.find(Activator.getDefault().getBundle(), new Path(TEST_PAGE_PATH), null);
					url = FileLocator.toFileURL(url);
				
					File file = URIUtil.toFile(URIUtil.toURI(url));
					browserViewer.setUrl(file.getCanonicalPath()+"/index.html");
				} catch (IOException | URISyntaxException e) {
					Activator.logError(e.getMessage(), e);
				}
			}
		};
		openMainTracePageAction.setText("Open Test Page");
		openMainTracePageAction.setToolTipText("test page allowing to debug some features");
		openMainTracePageAction.setImageDescriptor(workbench.getSharedImages().
				getImageDescriptor(ISharedImages.IMG_OBJS_INFO_TSK));

	}

	private void showMessage(String message) {
		MessageDialog.openInformation(
				browserViewer.getShell(),
			"METPTestBrowser View",
			message);
	}

	@Override
	public void setFocus() {
		//viewer.getControl().setFocus();
	}
}
