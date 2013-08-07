package com.xchanging.etl.mgr.web.client.view;

import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.layout.Layout;
import com.smartgwt.client.widgets.layout.VLayout;

public final class BaseContainerView extends VLayout {

	private static BaseContainerView container;
	private Layout content;
	
	private BaseContainerView() {
		super();

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

	public void setView(final Layout view){
		this.content = view;
		Canvas[] children = getMembers();
		if(null != children && children.length > 0){
			removeMembers(children);
		}
		addMember(view);
	}

	/**
	 * @return the content
	 */
	public Layout getContent() {
		return content;
	}
	
}
