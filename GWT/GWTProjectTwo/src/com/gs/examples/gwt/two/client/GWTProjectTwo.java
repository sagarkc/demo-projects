package com.gs.examples.gwt.two.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;
import com.gs.examples.gwt.two.client.service.ExampleServiceClientImpl;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class GWTProjectTwo implements EntryPoint {
	
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		ExampleServiceClientImpl clientImpl =
				new ExampleServiceClientImpl(GWT.getModuleBaseURL() + "exampleservice");
		RootPanel.get().add(clientImpl.getMainView());
	}
}
