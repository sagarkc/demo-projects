package com.gs.examples.gwt.one.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import com.gs.examples.gwt.one.client.view.MainView;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class GWTProjectsOne implements EntryPoint {
	
	
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		RootPanel.get().add(new MainView());
		
	}
	
	
	
}
