/**
 * File :: com.gs.examples.gwt.one.client.view.MenuView
 * Date :: 01-May-2013
 */
package com.gs.examples.gwt.one.client.view;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 * 
 */
public class MenuView extends Composite {

	private final MainView mainView;
	private HorizontalPanel basePanel = new HorizontalPanel();

	private Button landscape1;
	private Button landscape2;

	private MenuClickHandler menuClickHandler;
	
	/**
	 * 
	 */
	public MenuView(final MainView mainView) {
		this.mainView = mainView;
		initWidget(basePanel);
		menuClickHandler = new MenuClickHandler();
		
		landscape1 = new Button("Landscape 1");
		landscape1.addClickHandler(menuClickHandler);
		landscape2 = new Button("Landscape 2");
		landscape2.addClickHandler(menuClickHandler);

		basePanel.add(landscape1);
		basePanel.add(landscape2);
	}

	private class MenuClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			if (event.getSource().equals(landscape1)) {
				mainView.openLandscapeOne();
			}
			if (event.getSource().equals(landscape2)) {
				mainView.openLandscapeTwo();
			}
		}

	}
}
