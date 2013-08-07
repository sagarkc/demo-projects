package com.xchanging.etl.mgr.web.client.view;

import com.google.gwt.core.client.GWT;
import com.smartgwt.client.widgets.layout.VLayout;

public final class BaseContainerView extends VLayout {

	private static BaseContainerView container;
	
	private BaseContainerView() {
		super();

		GWT.log("init Context Area()...", null);

		setWidth100();
        setHeight100();
		setBorder("1px solid #a1a1a1"); 
		setLayoutMargin(2);             

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
