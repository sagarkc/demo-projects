/**
 * File :: com.gs.examples.gwt.one.client.view.LandscapeOne
 * Date :: 01-May-2013
 */
package com.gs.examples.gwt.one.client.view;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public class LandscapeTwo extends Composite {

	private VerticalPanel imagepPanel;
	
	/**
	 * 
	 */
	public LandscapeTwo() {
		imagepPanel = new VerticalPanel();
		initWidget(imagepPanel);
		
		Image image1 = new Image("/images/landscape_2.jpg");
		image1.setWidth("600px");
		
		imagepPanel.add(image1);
		
	}
}