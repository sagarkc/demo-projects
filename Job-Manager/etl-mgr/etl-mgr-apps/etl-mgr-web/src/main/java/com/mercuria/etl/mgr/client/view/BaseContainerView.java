package com.mercuria.etl.mgr.client.view;

import com.google.gwt.core.client.GWT;
import com.smartgwt.client.widgets.layout.VLayout;

public final class BaseContainerView extends VLayout {

	private static BaseContainerView container;
	
	private BaseContainerView() {
		super();

		GWT.log("init Context Area()...", null);

		this.setWidth("*");
		this.setBackgroundColor("#EEEEEE");

	}

	public static BaseContainerView getContainer() {
		if(null != container)
			return container;
		synchronized (BaseContainerView.class) {
			if(null == container)
				container = new BaseContainerView();
		}
		return container;
	}

	
	
}
