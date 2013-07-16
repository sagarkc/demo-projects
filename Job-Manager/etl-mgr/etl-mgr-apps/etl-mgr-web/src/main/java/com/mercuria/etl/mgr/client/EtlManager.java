package com.mercuria.etl.mgr.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;
import com.mercuria.etl.mgr.client.i18n.ApplicationMessages;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class EtlManager implements EntryPoint {

	public static final ApplicationMessages MESSAGES = GWT
			.create(ApplicationMessages.class);

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		AppController appViewer = new AppController();
	    appViewer.go(RootPanel.get());
	}
}
