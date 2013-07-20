package com.mercuria.etl.mgr.web.client.view;

import com.mercuria.etl.mgr.web.client.UIConstants;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ResizedEvent;
import com.smartgwt.client.widgets.events.ResizedHandler;
import com.smartgwt.client.widgets.layout.HLayout;

public class TitleBar extends HLayout implements ResizedHandler {

	private String iconPath;
	
	public TitleBar(String title, String iconPath) {
		//super.setTitle(title);
		this.iconPath = iconPath;
		setHeight(UIConstants.TITLE_BAR_HEIGHT);
		setWidth("*");
		setBackgroundColor("green");
		
		setDefaultLayoutAlign(VerticalAlignment.CENTER);
		
		Label label = new Label(title);
		addMember(label);
	}


	public String getIconPath() {
		return iconPath;
	}


	public void setIconPath(String iconPath) {
		this.iconPath = iconPath;
	}


	@Override
	public void onResized(ResizedEvent event) {
		// TODO Auto-generated method stub

	}

}
