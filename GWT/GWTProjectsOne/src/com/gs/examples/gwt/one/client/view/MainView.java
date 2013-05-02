/**
 * File :: com.gs.examples.gwt.one.client.view.MainView
 * Date :: 01-May-2013
 */
package com.gs.examples.gwt.one.client.view;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public class MainView extends Composite {

	private VerticalPanel basePanel = new VerticalPanel();
	private VerticalPanel contentPanel;
	private MenuView menuView;
	
	
	/**
	 * 
	 */
	public MainView() {
		initWidget(basePanel);
		
		contentPanel = new VerticalPanel();
		basePanel.add(contentPanel);
		
		menuView = new MenuView(this);
		basePanel.add(menuView);
		contentPanel.add(new Label("Click a button to see a landscape..." ));
	}
	
	public void openLandscapeOne(){
		this.contentPanel.clear();
		
		LandscapeOne one = new LandscapeOne();
		this.contentPanel.add(one);
	}
	
	public void openLandscapeTwo(){
		this.contentPanel.clear();
		
		LandscapeTwo l = new LandscapeTwo();
		this.contentPanel.add(l);
	}
	
}
